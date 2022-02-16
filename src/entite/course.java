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
public class course {
    private int course_id ;
    private String course_nom ;
    private String date;
    private int circuit_circuit_id ;
    private int saison_year;
    

    public course() {
    }
      public course( String course_nom,String date,int circuit_circuit_id ,   int saison_year ) {
        this.course_nom = course_nom;
        this.date = date;
        this.circuit_circuit_id = circuit_circuit_id;
        this.saison_year = saison_year;
    }
    public course(int course_id, String course_nom, String date, int circuit_circuit_id, int saison_year) {
        this.course_id = course_id;
        this.course_nom = course_nom;
        this.date = date;
        this.circuit_circuit_id = circuit_circuit_id;
        this.saison_year = saison_year;
    }

   

    public int getCircuit_circuit_id() {
        return circuit_circuit_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public String getCourse_nom() {
        return course_nom;
    }

    public String getDate() {
        return date;
    }

    public int getSaison_year() {
        return saison_year;
    }

    public void setCircuit_circuit_id(int circuit_circuit_id) {
        this.circuit_circuit_id = circuit_circuit_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setCourse_nom(String course_nom) {
        this.course_nom = course_nom;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSaison_year(int saison_year) {
        this.saison_year = saison_year;
    }

    @Override
    public String toString() {
        return "course{" + "course_id=" + course_id + ", nom=" + course_nom + ", date=" + date + ",circuit id =" + circuit_circuit_id +",saison year =" +saison_year+ '}';
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

 
}
