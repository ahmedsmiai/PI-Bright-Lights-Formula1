/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formula1;

import entite.*;
import java.text.ParseException;
import service.*;
/**
 *
 * @author manaa
 */
public class Formula1 {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException {
        
        // TODO code application logic here
                
        
        TicketService ps=new TicketService();
        CourseService cs=new CourseService();
        circuitService crs=new circuitService();
        Tickets t1 =new Tickets( 2, 2);
        Tickets t2 =new Tickets(16,2,3);  
        course cr1= new course( "miami","2016-10-10", 1, 2016);
        course cr2=new course(2, "course_nom", "2014-10-10", 5, 2017);
        Circuits c1=new Circuits("tunisie","tunis",10,10,"aa",10);
        Circuits c2=new Circuits(5, "algeria", "arabCup", 250, 220, "desc", 10);
        //cs.insert(cr1);
        //cs.update(cr2);
        //cs.delete(cr2);
        //System.out.println(cs.readById(7));
        //ps.insert(t1);
        //ps.update(t2);
        //ps.delete(t1);
        //System.out.println(ps.readById(16));
        //crs.insert(c1);
        //crs.update(c2);
        //crs.delete(c2);
        //System.out.println(crs.readById(5));
    }
    
}
