/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formula1;

import entite.Participation;
import entite.Qualifying;
import java.sql.*;
import service.ParticipationService;
import service.QualifyingService;
import utils.MyConnection;


public class Formula1 {

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        MyConnection ds1=MyConnection.getInstance();
//        System.out.println(ds1);
//        
//        MyConnection ds2=MyConnection.getInstance();
//        System.out.println(ds2);
            ParticipationService ps = new ParticipationService();
            QualifyingService qs = new QualifyingService();
        System.out.println(qs.readById(ps.readById(2).getQualifying_id()));

    }

}
