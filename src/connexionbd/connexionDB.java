/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexionbd;

import entite.Saison;
import entite.ClassementEquipes;
import entite.ClassementPilotes;
//import java.sql.*;
//import java.util.Date;
import java.sql.Date;
import java.time.LocalDate;

import service.SaisonService;
import service.ClassementPilotesService;
import service.ClassementEquipesService;
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
        
        LocalDate d1= LocalDate.of(2099,02,02) ;
        LocalDate d2= LocalDate.of(2019,01,01) ;
        Saison s1=new Saison(2039,d1,d2);
        Saison s2=new Saison(2022,d1,d2);
          Saison s3=new Saison(2019,d1,d2);
      

        ClassementPilotes p1=new ClassementPilotes(2, 1, 2, 2);
        ClassementPilotes p2=new ClassementPilotes(2, 1, 1, 2, 66);//for del and up 
        ClassementEquipes p3=new ClassementEquipes(1, 1, 1, 1);
        
        
        
        

        SaisonService ss=new SaisonService();
        
        
     // ss.inserSaisonPst(s1);
//       ss.delete(s2);
   // ss.update(s2);
 //  ss.readByYear();
     
        ClassementPilotesService pp=new ClassementPilotesService();
     // pp.insertclassemet_pilotePst(p1);
    //   pp.delete(p2);
   //  pp.update(p2);
//     pp.position();
  pp.afficher();
  pp.score();
 //pp.position();

    
     ClassementEquipesService ee=new ClassementEquipesService();
      //  ee.insertclassemet_equipePst(e1);
//       ee.delete(e1);
    // ee.update(e1);
      
       
//        for(Saison e:ss.read()){
//           System.out.println(e.toString3());
//        }
//        
//        
//         for(ClassementPilotes e:pp.readbys(2022)){
//           System.out.println(e.toString2());
//        }
        
//         for(ClassementEquipes e:ee.read()){
//           System.out.println(e.toString());
//        }
    }
}
