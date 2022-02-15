/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexionbd3a11;

import entite.Participation;
import entite.Qualifying;
import java.sql.*;
import service.ParticipationService;
import service.QualifyingService;
import utils.Datasource;

/**
 *
 * @author wiemhjiri
 */
public class ConnexionBD3A11 {

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Datasource ds1=Datasource.getInstance();
//        System.out.println(ds1);
//        
//        Datasource ds2=Datasource.getInstance();
//        System.out.println(ds2);

        Participation p = new Participation(1,5,1,1,3,22,60,100);
        ParticipationService qs = new ParticipationService();
       
        qs.delete(p);

    }

}
