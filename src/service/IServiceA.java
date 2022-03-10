/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 * @param <T>
 */
public interface IServiceA<T> {
   void insert(T t);
   void delete(T t);
   
   void update(T t);
    List<T>read();
    
    T readById(int id);
    
}
