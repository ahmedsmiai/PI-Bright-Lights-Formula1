/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

public class Participation {

    private int participation_id;
    private int pilote_id;
    private int equipe_id;
    private int course_id;
    private int qualifying_id;
    private int grid;
    private int position;
    private int points;
    
    
    
    
    //--------constructors--------//
 public Participation(int participation_id, int pilote_id, int equipe_id, int course_id, int qualifying_id, int grid, int position, int points) {
        this.participation_id = participation_id;
        this.pilote_id = pilote_id;
        this.equipe_id = equipe_id;
        this.course_id = course_id;
        this.qualifying_id = qualifying_id;
        this.grid = grid;
        this.position = position;
        this.points = points;
    }
 
  public Participation(int pilote_id, int equipe_id, int course_id, int qualifying_id, int grid, int position, int points) {
        this.pilote_id = pilote_id;
        this.equipe_id = equipe_id;
        this.course_id = course_id;
        this.qualifying_id = qualifying_id;
        this.grid = grid;
        this.position = position;
        this.points = points;
    }
  
  
   public Participation() {
    }

    
    
    //--------getters-------//

   
     public int getParticipation_id() {
        return participation_id;
    }

    public int getPilote_id() {
        return pilote_id;
    }

    public int getEquipe_id() {
        return equipe_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public int getQualifying_id() {
        return qualifying_id;
    }

    public int getGrid() {
        return grid;
    }

    public int getPosition() {
        return position;
    }

    public int getPoints() {
        return points;
    }
    
    //-----------setters------------//

    public void setParticipation_id(int participation_id) {
        this.participation_id = participation_id;
    }

    public void setPilote_id(int pilote_id) {
        this.pilote_id = pilote_id;
    }

    public void setEquipe_id(int equipe_id) {
        this.equipe_id = equipe_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setQualifying_id(int qualifying_id) {
        this.qualifying_id = qualifying_id;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPoints(int points) {
        this.points = points;
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
        final Participation other = (Participation) obj;
        if (this.participation_id != other.participation_id) {
            return false;
        }
        return true;
    }

}
