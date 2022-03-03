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
public class ClassementPilotes{
    private int classementP_id;
    private int pilotes_pilote_id ;
    private int saisons_year;
    private int points_total ;
    
    private int position;

    public ClassementPilotes() {
    }

    public ClassementPilotes (int classementP_id, int pilotes_pilote_id , int saisons_year, int points_total ,  int position) {
        this.classementP_id = classementP_id;
        this.pilotes_pilote_id  = pilotes_pilote_id ;
        this.saisons_year = saisons_year;
        this.points_total  = points_total ;
       
        this.position = position;
    }

    public ClassementPilotes (int pilotes_pilote_id , int saisons_year, int points_total , int position) {
        this.pilotes_pilote_id  = pilotes_pilote_id ;
        this.saisons_year = saisons_year;
        this.points_total  = points_total ;
      
        this.position = position;
    }
     public ClassementPilotes (int classementP_id, int points_total) {
        this.classementP_id  = classementP_id ;
    
        this.points_total  = points_total ;
      
  
    }
    
    
     
     public ClassementPilotes (int pilotes_pilote_id ) {
        this.pilotes_pilote_id  = pilotes_pilote_id ;

    }
public ClassementPilotes ( int saisons_year,int pilotes_pilote_id , int points_total ) {
        this.pilotes_pilote_id  = pilotes_pilote_id ;
        this.saisons_year = saisons_year;
        this.points_total  = points_total ;
      
      
    }
    

    

   
    public int getClassementP_id() {
        return classementP_id;
    }

    public int getPoints_total() {
        return points_total;
    }
    

    public void setClassementE_id(int classementP_id) {
        this.classementP_id = classementP_id;
    }

    public int getPilotes_pilote_id() {
        return pilotes_pilote_id ;
    }

    public void setPilotes_pilote_id(int pilotes_pilote_id ) {
        this.pilotes_pilote_id  = pilotes_pilote_id ;
    }

    public int getSaisons_year() {
        return saisons_year;
    }

    public void setSaisons_year(int saisons_year) {
        this.saisons_year = saisons_year;
    }

    public void setPoints_total(int points_total) {
        this.points_total = points_total;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

//     @Override
//    public String toString() {
//        return "classement_pilotes{" + "classementP_id=" + classementP_id + ", pilotes_pilote_id =" + pilotes_pilote_id  + ", saisons_year=" + saisons_year +", points_total =" + points_total  +", position=" + position + '}';
//    }
      @Override
    public String toString() {
        return "" + classementP_id + "";
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
        final ClassementPilotes other = (ClassementPilotes) obj;
        if (this.classementP_id != other.classementP_id) {
            return false;
        }
        return true;
    }

  
}
