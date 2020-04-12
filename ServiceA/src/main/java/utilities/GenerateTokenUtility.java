/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import com.keven.ServiceA.models.Token;

/**
 *
 * @author CHRISTIAN
 */
public class GenerateTokenUtility {
    
    public static Token generateToken(String sellerCode){
        
        int[] numbers = new int[6];
        
        for(int i=0; i<numbers.length; i++){
            numbers[i] = 1 + (int)(40*Math.random());
        }
        
      
       return new Token(sellerCode, numbers);
    
    }
}
