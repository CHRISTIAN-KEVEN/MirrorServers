/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keven.ServiceA.controllers;

import com.keven.ServiceA.models.Token;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import utilities.GenerateTokenUtility;

/**
 *
 * @author CHRISTIAN
 */
@RestController
public class TokenController {
    
    RestTemplate rt = new RestTemplate();
    
    
    @RequestMapping(value="/tokens", method=RequestMethod.POST)
    public Token receiveToken(@RequestBody Token receivedToken){
        
        Token token = Token.addToken(receivedToken);
        if( token == null)
            throw new RuntimeException("Failed to add Token !");
        
        System.out.println("Printed from server A: ");
        
        List<Token> tokens = Token.getTokenBuffer();
        for(Token t: tokens){
            System.out.println("Seller code: " + t.getSellerCode());
            System.out.println("Token numbers: " );
            for(int i: t.getNumbers())
                System.out.println(i+" ");
            
        }
        return token;    
    }
    
    @RequestMapping(value="/generate-token/{sellerCode}")
    public Token getAToken(@PathVariable String sellerCode){
        
        // Generate Token
        Token generatedToken = GenerateTokenUtility.generateToken(sellerCode);
        // Store in tokenBuffer
        Token.addToken(generatedToken);
        System.out.println(generatedToken.getSellerCode());
        for(int k: generatedToken.getNumbers()){
            System.out.print(" "+k);
        }
        
        // Call other service (Service C) and pass the generated token to it
        Token t = null;
        try {
            URI uri = new URI("http://localhost:8086/tokens");
            t = rt.postForObject(uri, generatedToken, Token.class);
            
            System.out.println("Feedback received from Server-C: " + t.getSellerCode() + " " + t.getNumbers());
           
        } catch (URISyntaxException exc) { }
        
        System.out.println("Printed from server A: ");
        
        List<Token> tokens = Token.getTokenBuffer();
        for (Token tk : tokens) {
            System.out.println("Seller code: " + tk.getSellerCode());
            System.out.println("Token numbers: ");
            int[] numbs = tk.getNumbers();
            for (int i =0; i<numbs.length; i++) {
                System.out.print(numbs[i] + " ");
            }

        }
        return t;
    }
  
}
