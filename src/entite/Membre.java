/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Date;

/**
 *
 * @author nechi
 */
public class Membre {
    private int membre_id;
    private String nom;
    private String role;
    private String nationalite;
    private Date date_naissance;
    private int equipe_id;

    public Membre(){
        
    }
    
    public Membre(int membre_id, String nom, String role, String nationalite, Date date_naissance, int equipe_id) {
        this.membre_id = membre_id;
        this.nom = nom;
        this.role = role;
        this.nationalite = nationalite;
        this.date_naissance = date_naissance;
        this.equipe_id = equipe_id;
    }

    public Membre(String nom, String role, String nationalite, Date date_naissance, int equipe_id) {
        this.nom = nom;
        this.role = role;
        this.nationalite = nationalite;
        this.date_naissance = date_naissance;
        this.equipe_id = equipe_id;
    }

    public int getMembre_id() {
        return membre_id;
    }

    public void setMembre_id(int membre_id) {
        this.membre_id = membre_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public int getEquipe_id() {
        return equipe_id;
    }

    public void setEquipe_id(int equipe_id) {
        this.equipe_id = equipe_id;
    }

    @Override
    public String toString() {
        return "Membre{" + "membre_id=" + membre_id + ", nom=" + nom + ", role=" + role + ", nationalite=" + nationalite + ", date_naissance=" + date_naissance + ", equipe_id=" + equipe_id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.membre_id;
        return hash;
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
        final Membre other = (Membre) obj;
        if (this.membre_id != other.membre_id) {
            return false;
        }
        return true;
    }
    
    
}
