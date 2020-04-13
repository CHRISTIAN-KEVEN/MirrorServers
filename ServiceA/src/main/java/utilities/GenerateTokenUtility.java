/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import com.keven.ServiceA.models.Token;
import java.util.ArrayList;
import com.keven.ServiceA.models.Number;

/**
 *
 * @author CHRISTIAN
 */
public class GenerateTokenUtility {
    
    public static Token generateToken(String sellerCode){
        
        java.util.List<Number> numbers = new ArrayList();
        
        for(int i=0; i<6; i++){
            int rand = 1 + (int)(40*Math.random());
            numbers.add(new com.keven.ServiceA.models.Number(rand));
        }
        
      
       return new Token(sellerCode, numbers);
    
    }
}
