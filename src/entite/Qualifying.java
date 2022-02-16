
package entite;

public class Qualifying {
    
    private int qualifying_id;
    private String q1;
    private String q2;
    private String q3; 
    private int position;
    private int pilote_id;
    private int course_id;

    public Qualifying(int qualifying_id, String q1, String q2, String q3, int position, int pilote_id, int course_id) {
        this.qualifying_id = qualifying_id;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.position = position;
        this.pilote_id = pilote_id;
        this.course_id = course_id;
    }

    public Qualifying(String q1, String q2, String q3, int position, int pilote_id, int course_id) {
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.position = position;
        this.pilote_id = pilote_id;
        this.course_id = course_id;
    }

    public Qualifying() {
    }

    @Override
    public String toString() {
        return "Qualifying{" + "qualifying_id=" + qualifying_id + ", q1=" + q1 + ", q2=" + q2 + ", q3=" + q3 + ", position=" + position + ", pilote_id=" + pilote_id + ", course_id=" + course_id + '}';
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

    public int getPilote_id() {
        return pilote_id;
    }

    public int getCourse_id() {
        return course_id;
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

    public void setPilote_id(int pilote_id) {
        this.pilote_id = pilote_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
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
