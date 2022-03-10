//*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package entite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 *
 * @author win10LIGHT
 */
public class ClassementEquipes{
    private int classementE_id;//usid
    private int equipes_equipe_id;//usrna
    private int saisons_year;//mai
    
    private int points_total ;//pw
    
    private int position;//rolid
     private String nom;
    

    public ClassementEquipes() {
    }

    public ClassementEquipes (int classementE_id, int equipes_equipe_id, int saisons_year, int points_total ,  int position) {
        this.classementE_id = classementE_id;
        this.equipes_equipe_id = equipes_equipe_id;
        this.saisons_year = saisons_year;
        this.points_total  = points_total ;
       
        this.position = position;
    }
    
      public ClassementEquipes (int classementE_id,String nom, int saisons_year, int points_total ,  int position) {
        this.classementE_id = classementE_id;
        this.nom  = nom ;
        
        this.saisons_year = saisons_year;
        this.points_total  = points_total ;
       
        this.position = position;
    }

    public ClassementEquipes (int equipes_equipe_id, int saisons_year, int points_total , int position) {
        this.equipes_equipe_id = equipes_equipe_id;
        this.saisons_year = saisons_year;
        this.points_total  = points_total ;
      
        this.position = position;
    }
      public ClassementEquipes (int equipes_equipe_id, int saisons_year, int points_total ) {
        this.equipes_equipe_id = equipes_equipe_id;
        this.saisons_year = saisons_year;
        this.points_total  = points_total ;
      
     
    }
    public ClassementEquipes (int classementE_id, int points_total) {
        this.classementE_id  = classementE_id ;
    
        this.points_total  = points_total ;
      
    }
    
    
     
     public ClassementEquipes (int equipes_equipe_id ) {
        this.equipes_equipe_id  = equipes_equipe_id ;

    }
     
       public ClassementEquipes (String nom ) {
        this.nom  = nom ;

    }
//public ClassementEquipes ( int saisons_year,int equipes_equipe_id , int points_total ) {
//        this.equipes_equipe_id  = equipes_equipe_id ;
//        this.saisons_year = saisons_year;
//        this.points_total  = points_total ;
//      
//      
//    }

    

    

   
    public int getClassementE_id() {
        return classementE_id;
    }

    public void setClassementE_id(int classementE_id) {
        this.classementE_id = classementE_id;
    }

    public int getEquipes_equipe_id() {
        return equipes_equipe_id;
    }

    public void setEquipes_equipe_id(int equipes_equipe_id) {
        this.equipes_equipe_id = equipes_equipe_id;
    }

    public int getSaisons_year() {
        return saisons_year;
    }

    public void setSaisons_year(int saisons_year) {
        this.saisons_year = saisons_year;
    }

    public int getPoints_total() {
        return points_total ;
    }

    public void setPoints_total(int points_total ) {
        this.points_total  = points_total ;
    }

   

 

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
     public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
//     @Override
//    public String toString() {
//        return "classement_equipes{" + "classementE_id=" + classementE_id + ", equipes_equipe_id=" + equipes_equipe_id + ", saisons_year=" + saisons_year +", points_total =" + points_total  +", position=" + position + '}';
//    }
    
    
    
    
            @Override 
    public String toString() {
        return "" + nom + "";
    }
           
    public String toString3() {
        return "" + classementE_id + "";
    }

    public String toString2() {
        return "" + classementE_id + " " + nom  + " " + saisons_year +" " + points_total  +" " + position + "";
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
        final ClassementEquipes other = (ClassementEquipes) obj;
        if (this.classementE_id != other.classementE_id) {
            return false;
        }
        return true;
    }

  
}
