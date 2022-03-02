/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formula1;

import entite.*;
import service.*;

public class Formula1 {

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     

        ParticipationService pars = new ParticipationService();

        pars.read().forEach((p)->{
            System.out.println(p.getQualifying().getQ1());
        });
        
       
        
                
        

    }

    }
