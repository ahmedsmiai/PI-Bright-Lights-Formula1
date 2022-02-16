/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author manaa
 */
public class Tickets {
    private int tickets_id;
    private int course_id;
    private int user_id;

    public Tickets() {
    }

    public Tickets(int tickets_id, int course_id, int user_id) {
        this.tickets_id = tickets_id;
        this.course_id = course_id;
        this.user_id = user_id;
    }
 
    public Tickets( int course_id, int user_id) {
        
        this.course_id = course_id;
        this.user_id = user_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getTickets_id() {
        return tickets_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setTickets_id(int tickets_id) {
        this.tickets_id = tickets_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    
    public String toString() {
        return "ticket{" + "ticket_id=" + tickets_id + ", course id=" + course_id + ", user id=" + user_id + '}';    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}