/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaone;

import entite.Equipe;
import entite.Membre;
import entite.Pilote;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import service.EquipeService;
import service.MembreService;
import service.PiloteService;
import utils.Datasource;
/**
 *
 * @author nechi
 */


public class FormulaOne {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
/*******************************************************************Tester crud Ã©quipe*********************************************/
        EquipeService es=new EquipeService();
        /******* Ajouter Ã©quipe**********/
//        Equipe e1=new Equipe("Mercedes","C507","Allemagne");
//        Equipe e3=new Equipe("BMW","E301","Allemagne");
//        Equipe e2=new Equipe("KIA","RMP","Chine");
//        
//        es.insert(e1);
//        es.insert(e2);
//        es.insert(e3);
        
        
        /************** Afficher les Ã©quipe ********/
//        for(Equipe e:es.read()){
//            System.out.println(e.toString());
//        }
       
        
        /*************Afficher par id**************/
//        System.out.println("avant modification");
//        System.out.println(es.readById(16).toString());
        


        /*************Supprimer Ã©quipe************/
//        es.delete(20);


        /*************Modifier Ã©quipe **************/
//        Equipe e5=new Equipe(17,"Hyundai","I10","Tokyo");
//        es.update(e5);
//        System.out.println("aprÃ©s modification");
//        System.out.println(es.readById(17).toString());
        
 

/***********************************************************************************Tester Crud Membre*********************************************/
        MembreService ms=new MembreService();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        /******** Test ajout *****************/
//        Membre m1=new Membre("Nechi hamza","Technicien","Tunisienne",df.parse("1997-09-12"),16);
//        Membre m2=new Membre("Youssef hamouda","chef d'équipe","Tunisienne",df.parse("1999-07-10"),16);
//        ms.insert(m1);
//        ms.insert(m2);
        /********* Test read ****************/
//        for(Membre e:ms.read()){
//            System.out.println(e.toString());
//        }
        /********* Test readById ***************/
//        System.out.print(ms.readById(27).toString());


       /*********** Test update ***************/
//       System.out.println("avant modfication");
//       System.out.print(ms.readById(28).toString());
//       Membre m3=new Membre(28,"Med aziz belhadj khelifa","chef d'équipe","française",df.parse("1999-02-14"),16);
//       ms.update(m3);
//       System.out.println("aprés la modification");
//       System.out.print(ms.readById(28).toString());
       

        /******* Test delete *******************/
        //ms.delete(27);




/************************************************************************************ Test Crud Pilote *******************************************/
        PiloteService ps=new PiloteService();
        /************Test insert pilote *********/
//        Pilote p1=new Pilote(55,"Ahmed smiai","Pilote","Tunnisienne",df.parse("1998-05-05"),16);
//        Pilote p2=new Pilote(77,"Yassine manaa","Pilote","Tunnisienne",df.parse("1999-06-07"),16);
//        
//        ps.insert(p1);
//        ps.insert(p2);
        /************** Test read pilote ********/
//        for(Pilote p:ps.read()){
//            System.out.println(p.toString());
//        }

        /************* Test readById pilote ***********/
       // System.out.println(ps.readById(29));
        
        /************ Test update pilote *************/
//        Pilote p3=new Pilote(30,105,30,"Nechi hamza","Pilote","Tunnisienne",df.parse("1999-06-07"),16);
//        System.out.println("Avant Modification//"+ps.readById(30));
//        ps.update(p3);
//        System.out.println("AprÃ©s Modification//"+ps.readById(30));
        

        /************ Test delete pilote **************/
 //       ps.delete(29);
    }
    
}
