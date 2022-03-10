/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;
import entite.*;
import java.util.Date;

/**
 *
 * @author manaa
 */
public class course {
    private int course_id ;
    private String course_nom ;
    private Date date;
    private Circuits circuit_circuit_id ;
    private Saison saison_year;
    private User users;
    

    public course() {
    }
      public course( String course_nom,Date date,Circuits circuit_circuit_id ,   Saison saison_year , User user ) {
        this.course_nom = course_nom;
        this.date = date;
        this.circuit_circuit_id = circuit_circuit_id;
        this.saison_year = saison_year;
        this.users = user ;
    }
    public course(int course_id, String course_nom, Date date, Circuits circuit_circuit_id, Saison saison_year, User user) {
        this.course_id = course_id;
        this.course_nom = course_nom;
        this.date = date;
        this.circuit_circuit_id = circuit_circuit_id;
        this.saison_year = saison_year;
        this.users = user ;
    }
     public course(int course_id, String course_nom, Date date, Saison saison_year , User user) {
        this.course_id = course_id;
        this.course_nom = course_nom;
        this.date = date;
       this.users = user ;
        this.saison_year = saison_year;
    }
          public course(int course_id, String course_nom, Date date, User user) {
        this.course_id = course_id;
        this.course_nom = course_nom;
        this.date = date;
       this.users = user ;
        
    }

    public course(int aInt, String string, java.sql.Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

   

    public Circuits getCircuit_circuit_id() {
        return circuit_circuit_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public String getCourse_nom() {
        return course_nom;
    }

    public Date getDate() {
        return date;
    }

    public Saison getSaison_year() {
        return saison_year;
    }

    public void setCircuit_circuit_id(Circuits circuit_circuit_id) {
        this.circuit_circuit_id = circuit_circuit_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setCourse_nom(String course_nom) {
        this.course_nom = course_nom;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSaison_year(Saison saison_year) {
        this.saison_year = saison_year;
    }

    @Override
    public String toString() {
        return "course{" + "course_id=" + course_id + ", nom=" + course_nom + ", date=" + date + ",circuit id =" + circuit_circuit_id+",saison year =" +saison_year+ '}';
    }
    
    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final course other = (course) obj;
        if (this.course_id != other.course_id) {
            return false;
        }
        return true;
    }

 
}
