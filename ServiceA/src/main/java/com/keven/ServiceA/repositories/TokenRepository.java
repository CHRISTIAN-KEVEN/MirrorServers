/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keven.ServiceA.repositories;

import com.keven.ServiceA.models.Token;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CHRISTIAN
 */
public interface TokenRepository extends CrudRepository<Token, Integer>{
    
}
