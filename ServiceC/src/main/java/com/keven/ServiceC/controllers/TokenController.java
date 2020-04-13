/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keven.ServiceC.controllers;

import com.keven.ServiceC.models.Token;
import com.keven.ServiceC.repositories.TokenRepository;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.keven.ServiceC.utilities.GenerateTokenUtility;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.keven.ServiceC.models.Number;
import com.keven.ServiceC.repositories.NumberRepository;

/**
 *
 * @author CHRISTIAN
 */
@RestController
public class TokenController {
    
   @Autowired
    TokenRepository tokenRepo;
   
   @Autowired
   NumberRepository numberRepo;
    
    RestTemplate rt = new RestTemplate();
    
    
    @RequestMapping(value="/tokens", method=RequestMethod.POST)
    public Token receiveToken(@RequestBody Token receivedToken){
        
       // Token token = Token.addToken(receivedToken);
       Token newToken = tokenRepo.save(receivedToken); // Store in ServiceA db
     
        if( newToken == null)
            throw new RuntimeException("Failed to add Token !");
        
        for(Number num: newToken.getNumbers()){
            num.setToken(newToken);
            numberRepo.save(num);
        }
        
        System.out.println("Printed from server A: ");
        
        List<Token> tokens = new java.util.ArrayList();
        tokenRepo.findAll().forEach(tokens::add);
      
    // 
        for(Token t: tokens){
            System.out.println("Seller code: " + t.getSellerCode());
            System.out.println("Token numbers: " );
            t.getNumbers().forEach((n) -> {
                System.out.print(n.getValue() + " ");
           });
            
        }
        return newToken;    
    }
    
    @RequestMapping(value="/generate-token/{sellerCode}")
    public Token getAToken(@PathVariable String sellerCode){
        
        // Generate Token
        Token generatedToken = GenerateTokenUtility.generateToken(sellerCode);
        // Store in tokenBuffer
       // Token.addToken(generatedToken);
       Token newToken = tokenRepo.save(generatedToken); // Storing token in the actual service's db
       for(Number num: newToken.getNumbers()){
            num.setToken(newToken);
            numberRepo.save(num);
        }
        
        // Call other service (Service C) and pass the generated token to it
        Token t = null;
        try {
            URI uri = new URI("http://localhost:8085/tokens");
            t = rt.postForObject(uri, generatedToken, Token.class);
            
            System.out.println("Feedback received from Server-A: " + t.getSellerCode() + " " + t.getNumbers());
           
        } catch (URISyntaxException exc) { }
        
        System.out.println("Printed from server C: ");
        
        List<Token> tokens = new ArrayList();
        tokenRepo.findAll().forEach(tokens::add);
        
        // These printings are just to see the state of buffers
        for (Token tk : tokens) {
            
            System.out.println("Seller code: " + tk.getSellerCode());
            System.out.println("Token numbers: ");
            tk.getNumbers().forEach((i) -> System.out.print(i.getValue()));

        }
        // End of printing
        return t;
    }

  
}
