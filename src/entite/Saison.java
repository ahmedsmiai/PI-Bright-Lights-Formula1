/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;


import java.sql.Date;
import java.time.LocalDate;
//import java.util.Date.*;

/**
 *
 * @author wiemhjiri
 */
public class Saison {
    private int year;
    private LocalDate  date_debut;
    private LocalDate date_fin;
    public Saison() {
    }

    public Saison(int year, LocalDate date_debut, LocalDate date_fin) {
        this.year = year;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        
    }
    public Saison(int year) {
        this.year = year;
       
        
    }
    public Saison(LocalDate date_debut,LocalDate date_fin) {
       
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Saison{" + "year=" + year + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
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
        final Saison other = (Saison) obj;
        if (this.year != other.year) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
