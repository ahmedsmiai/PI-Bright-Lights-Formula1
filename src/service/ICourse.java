/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author manaa
 */
public interface ICourse<C> {
     void insert(C c);
   void delete(C c);
   void update(C c);
    List<C>read();
    
    C readById(int id);
    
    
}
