/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keven.ServiceA.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author CHRISTIAN
 */
@Entity
public class Token{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
       
  //  private static List<Token> tokenBuffer = new ArrayList(); // This will serve as our persistence entity
    
    @Column(name = "seller_code")
    private String sellerCode;
    
    @OneToMany(mappedBy = "token")
    @JsonIgnoreProperties("token")
    private List<Number> numbers;

    public Token(String sellerCode, List<Number> numbers){
        this.sellerCode = sellerCode;
        this.numbers = numbers;
    }
    public Token(){} // Default constructor. For Deserialization
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public List<Number> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
    }

//    public static List<Token> getTokenBuffer() {
//        return tokenBuffer;
//    }
//
//    public static void setTokenBuffer(List<Token> tokenBuffer) {
//        Token.tokenBuffer = tokenBuffer;
//    }
//    
    
    
//    public static Token addToken(Token receivedToken) {
//            
//        if( tokenBuffer.add(receivedToken) )
//            return receivedToken;
//        return null;
//    }
//    
}
