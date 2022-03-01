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
public class classement_equipes {
      private int classementE_id ;
    private int equipes_equipe_id;//equipes_equipe_id
    private int saisons_year;//saisons_year
    private int points_total;//points_total
    private int position;//position
   

    public classement_equipes() {
    }

    public classement_equipes(int classementE_id, int equipes_equipe_id, int saisons_year, int points_total, int position) {
        this.classementE_id = classementE_id;
        this.equipes_equipe_id = equipes_equipe_id;
        this.saisons_year = saisons_year;
        this.points_total = points_total;
        this.position = position;
    }

    public classement_equipes(int equipes_equipe_id, int saisons_year, int points_total, int position) {
        this.equipes_equipe_id = equipes_equipe_id;
        this.saisons_year = saisons_year;
        this.points_total = points_total;
        this.position = position;
    }

    

    

   
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
        return "classement_equipes{" + "classementE_id=" + classementE_id + ", equipes_equipe_id=" + equipes_equipe_id + ", saisons_year=" + saisons_year +", points_total=" + points_total +", position=" + position + '}';
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
        final classement_equipes other = (classement_equipes) obj;
        if (this.classementE_id != other.classementE_id) {
            return false;
        }
        return true;
    }


    
}