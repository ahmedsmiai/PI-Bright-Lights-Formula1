/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;



/**
 *
 * @author qwiw
 */
public class classement_pilotes {
     private int classementP_id ;
    private int pilotes_pilote_id;//equipes_equipe_id
    private int saisons_year;//saisons_year
    private int points_total;//points_total
    private int position;//position
   

    public classement_pilotes() {
    }

    public classement_pilotes(int classementP_id, int pilotes_pilote_id, int saisons_year, int points_total, int position) {
        this.classementP_id = classementP_id;
        this.pilotes_pilote_id = pilotes_pilote_id;
        this.saisons_year = saisons_year;
        this.points_total = points_total;
        this.position = position;
    }

    public classement_pilotes(int pilotes_pilote_id, int saisons_year, int points_total, int position) {
        this.pilotes_pilote_id = pilotes_pilote_id;
        this.saisons_year = saisons_year;
        this.points_total = points_total;
        this.position = position;
    }

    

    

   
    public int getClassementP_id() {
        return classementP_id;
    }

    public void setClassementP_id(int classementP_id) {
        this.classementP_id = classementP_id;
    }

    public int getPilotes_pilote_id() {
        return pilotes_pilote_id;
    }

    public void setPilotes_pilote_id(int pilotes_pilote_id) {
        this.pilotes_pilote_id = pilotes_pilote_id;
    }

    public int getSaisons_year() {
        return saisons_year;
    }

    public void setSaisons_year(int saisons_year) {
        this.saisons_year = saisons_year;
    }

    public int getPoints_total() {
        return points_total;
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
     @Override
    public String toString() {
        return "classement_pilotes{" + "classementP_id=" + classementP_id + ", pilotes_pilote_id=" + pilotes_pilote_id + ", saisons_year=" + saisons_year +", points_total=" + points_total +", position=" + position + '}';
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
        final classement_pilotes other = (classement_pilotes) obj;
        if (this.classementP_id != other.classementP_id) {
            return false;
        }
        return true;
    }

    
    
}