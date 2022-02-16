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
public class Circuits {
    private int circuit_id;
    private String nom;
    private String pays;
    private int lang;
    private int course_distance;
    private String description;
    private int capacite;

    public Circuits() {
    }

    public Circuits(int circuit_id, String nom, String pays, int lang, int course_distance, String description, int capacite) {
        this.circuit_id = circuit_id;
        this.nom = nom;
        this.pays = pays;
        this.lang = lang;
        this.course_distance = course_distance;
        this.description = description;
        this.capacite = capacite;
    }
    public Circuits( String nom, String pays, int lang, int course_distance, String description, int capacite) {
        
        this.nom = nom;
        this.pays = pays;
        this.lang = lang;
        this.course_distance = course_distance;
        this.description = description;
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
    }

    public int getCircuit_id() {
        return circuit_id;
    }

    public int getCourse_distance() {
        return course_distance;
    }

    public String getDescription() {
        return description;
    }

    public int getLang() {
        return lang;
    }

    public String getNom() {
        return nom;
    }

    public String getPays() {
        return pays;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setCircuit_id(int circuit_id) {
        this.circuit_id = circuit_id;
    }

    public void setCourse_distance(int course_distance) {
        this.course_distance = course_distance;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLang(int lang) {
        this.lang = lang;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    
public String toString() {
        return "circuit{" + "circuit id = " + circuit_id+ ", nom = " + nom  + ", pays = " + pays + ", longeur = " + lang + ",course distance = " + course_distance +",description = " +description+", capaciter = "+capacite+ '}';    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
