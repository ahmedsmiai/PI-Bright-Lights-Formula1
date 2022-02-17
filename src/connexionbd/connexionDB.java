/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexionbd;

import entite.Saison;
//import java.sql.*;
//import java.util.Date;
import java.sql.Date;
import java.time.LocalDate;

import service.SaisonService;
import utils.Datasource;

/**
 *
 * @author qwiw
 */
public class connexionDB {
    
    /**
     * 
     * @param args the command line arguments
     */
    
   
   
    public static void main(String[] args){
 // TODO code application logic here
//        Datasource ds1=Datasource.getInstance();
//        System.out.println(ds1);
//        
//        Datasource ds2=Datasource.getInstance();
//        System.out.println(ds2);

      
//        Date d1=new Date(2022-01-03);
//        Date d2=new Date(2002-11-03);
        
        LocalDate d1= LocalDate.of(2011,01,01) ;
        LocalDate d2= LocalDate.of(2019,11,11) ;
        
        Saison s1=new Saison(2030,d1,d2);
//        Saison s2=new Saison(2021);
//        Saison s3=new Saison(2019,d1,d2);
      

        SaisonService ss=new SaisonService();
        
//         ss.inserSaisonPst(s1);
//       ss.delete(s2);
     ss.update(s1);
     
       
       
//        for(Saison e:ss.read()){
//           System.out.println(e.toString());
//        }
    }
}
