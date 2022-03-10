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
    private course course;
    private User user_id;
    private String type;

    public Tickets() {
    }

    public Tickets(int tickets_id, course course_id, User user_id,String type) {
        this.tickets_id = tickets_id;
        this.course = course_id;
        this.user_id = user_id;
        this.type=type;
    }
 
    public Tickets( course course_id, User user_id,String type) {
        
        this.course = course_id;
        this.user_id = user_id;
        this.type=type;
    }
     public Tickets( course course_id,String type) {
        
        this.course = course_id;
        this.type=type;
    }
      public Tickets( course course_id) {
        
        this.course = course_id;
        
    }
    

    public course getCourse() {
        return course;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getTickets_id() {
        return tickets_id;
    }

    public void setCourse(course course_id) {
        this.course = course_id;
    }

    public void setTickets_id(int tickets_id) {
        this.tickets_id = tickets_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    
    public String toString() {
        return "ticket{" + "ticket_id=" + tickets_id + ", course id=" + course + ", user id=" + user_id + '}';    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
