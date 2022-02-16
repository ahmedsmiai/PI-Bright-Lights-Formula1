/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formula1;

import entite.User;
import entite.Role;
import java.sql.*;
import service.UserService;
import service.RoleService;
import utils.Datasource;

/**
 *
 * @author win10LIGHT
 */
public class Formula1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

     UserService us = new UserService();
        //RoleService rs = new RoleService();
        
        
   Timestamp timestamp = new Timestamp(System.currentTimeMillis());

      

        
 //User u1 = new User("hamzanechi", "hamzanechi@gamil.com", "acab",timestamp,1);
  // us.insertUserPst(u1);
   
    //Role r1 = new Role("organosateur");
       //rs.insertRolePst(r1);

        //User u2 = new User(9, "yassine", "manaa@gamil.com", "yassine123456",timestamp,2);
        //us.update(u2);
        
        //Role r2 = new Role(7,"organisateur");
        //rs.update(r2);
        
       User u2 = new User(9,"yassine", "manaa@gamil.com", "manaa123",timestamp,2);
        us.delete(u2);
        
        //Role r2 = new Role(6,"admin");
        //rs.delete(r2);
        
       //System.out.println(us.readById(1).toString());    //user
      // System.out.println(rs.readById(1).toString());    //role
        
        
//System.out.println(us.read());    //user
//System.out.println(rs.read());   //role
       
    }

}
