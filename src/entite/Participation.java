/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

public class Participation {

    private int participation_id;
    private Pilote pilote;
    private Equipe equipe;
    private course course;
    private Qualifying qualifying;
    private int grid;
    private int position;
    private int points;
    
    
    
    
    //--------constructors--------//
 public Participation(int participation_id, Pilote pilote, Equipe equipe, course course, Qualifying qualifying, int grid, int position, int points) {
        this.participation_id = participation_id;
        this.pilote = pilote;
        this.equipe = equipe;
        this.course = course;
        this.qualifying = qualifying;
        this.grid = grid;
        this.position = position;
        this.points = points;
    }
 
 
  public Participation(Pilote pilote, Equipe equipe, course course, Qualifying qualifying, int grid, int position, int points) {
        this.pilote = pilote;
        this.equipe = equipe;
        this.course = course;
        this.qualifying = qualifying;
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

    public Pilote getPilote() {
        return pilote;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public course getCourse() {
        return course;
    }

    public Qualifying getQualifying() {
        return qualifying;
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

    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public void setCourse(course course) {
        this.course = course;
    }

    public void setQualifying(Qualifying qualifying) {
        this.qualifying = qualifying;
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
    public String toString() {
        return "Participation{" + "participation_id=" + participation_id + ", pilote=" + pilote + ", equipe=" + equipe + ", course=" + course + ", qualifying=" + qualifying + ", grid=" + grid + ", position=" + position + ", points=" + points + '}';
    }
    
    public String toString2() {
        return "participation_id=" + participation_id + ", numero="+pilote.getNumero()+", nom pilote=" + pilote.getNom() + ", equipe=" + equipe.getNom() + ", course=" + course.getCourse_nom()  + ", grid=" + grid + ", position=" + position + ", points=" + points ;
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
