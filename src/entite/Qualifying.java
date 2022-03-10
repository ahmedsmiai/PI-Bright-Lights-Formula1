
package entite;
import entite.*;

public class Qualifying {
    
    private int qualifying_id;
    private String q1;
    private String q2;
    private String q3; 
    private int position;
    private Pilote pilote;
    private course course;

    public Qualifying(int qualifying_id, String q1, String q2, String q3, int position, Pilote pilote, course course) {
        this.qualifying_id = qualifying_id;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.position = position;
        this.pilote = pilote;
        this.course = course;
    }

    public Qualifying(String q1, String q2, String q3, int position, Pilote pilote, course course) {
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.position = position;
        this.pilote = pilote;
        this.course = course;
    }

    public Qualifying() {
    }

    @Override
    public String toString() {
        return "Qualifying{" + "qualifying_id=" + qualifying_id + ", q1=" + q1 + ", q2=" + q2 + ", q3=" + q3 + ", position=" + position + ", pilote_id=" + pilote.getPilote_id() + ", course_id=" + course.getCourse_id() + '}';
    }
    

    public int getQualifying_id() {
        return qualifying_id;
    }

    public String getQ1() {
        return q1;
    }

    public String getQ2() {
        return q2;
    }

    public String getQ3() {
        return q3;
    }

    public int getPosition() {
        return position;
    }

    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    public void setCourse(course course) {
        this.course = course;
    }

    public Pilote getPilote() {
        return pilote;
    }

    public course getCourse() {
        return course;
    }


    public void setQualifying_id(int qualifying_id) {
        this.qualifying_id = qualifying_id;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public void setPosition(int position) {
        this.position = position;
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
        final Qualifying other = (Qualifying) obj;
        if (this.qualifying_id != other.qualifying_id) {
            return false;
        }
        return true;
    }


    
    
    
    
}
