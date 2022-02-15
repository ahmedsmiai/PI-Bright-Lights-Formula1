/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wiemhjiri
 */
public class MyConnection {
    
   private  String url="jdbc:mysql://localhost:3306/mydb";
   private  String login="root";
   private  String pwd="root";
   
   private  Connection cnx;
   private static MyConnection instance;

    private MyConnection() {

        try {
            cnx=DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MyConnection getInstance(){
         if(instance==null)
             instance=new MyConnection();
         return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
   
   
    
}
