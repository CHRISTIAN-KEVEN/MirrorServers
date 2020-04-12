/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keven.ServiceA.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author CHRISTIAN
 */
@Entity
public class Token implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    
    private static List<Token> tokenBuffer = new ArrayList(); // This will serve as our persistence entity
    
    
    private String sellerCode;
    private int[] numbers = new int[6];
    
    public Token(String sellerCode, int[] nums){
        this.sellerCode = sellerCode;
        this.numbers = nums;
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

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public static List<Token> getTokenBuffer() {
        return tokenBuffer;
    }

    public static void setTokenBuffer(List<Token> tokenBuffer) {
        Token.tokenBuffer = tokenBuffer;
    }
    
    
    
    public static Token addToken(Token receivedToken) {
            
        if( tokenBuffer.add(receivedToken) )
            return receivedToken;
        return null;
    }
    
}
