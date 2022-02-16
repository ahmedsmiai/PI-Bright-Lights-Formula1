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
        // TODO code application logic here
//        MyConnection ds1=MyConnection.getInstance();
//        System.out.println(ds1);
//        
//        MyConnection ds2=MyConnection.getInstance();
//        System.out.println(ds2);
            RoleService rs = new RoleService();
            QualifyingService qs = new QualifyingService();
            
            Role r = new Role("new role");
            rs.insertRolePst(r);
    }

}
