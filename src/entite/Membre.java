/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author nechi
 */
public class Membre {

    private int membre_id;
    private String nom;
    private String image;
    private String role;
    private String nationalite;
    private Date date_naissance;
    private Equipe equipe;
    private ImageView img;
    private int equipe_id;

    public Membre() {

    }

    public Membre(String nom, String role, String nationalite, Date date_naissance, int equipe_id) {
        this.nom = nom;
        this.role = role;
        this.nationalite = nationalite;
        this.date_naissance = date_naissance;
        this.equipe_id = equipe_id;
    }

    public Membre(int membre_id, String nom, String image, String role, String nationalite, Date date_naissance, Equipe equipe) {
        this.membre_id = membre_id;
        this.nom = nom;
        this.image = image;
        this.role = role;
        this.nationalite = nationalite;
        this.date_naissance = date_naissance;
        this.equipe = equipe;
    }

    public Membre(int membre_id, String nom, String image, String role, String nationalite, Date date_naissance) {
        this.membre_id = membre_id;
        this.nom = nom;
        this.image = image;
        this.role = role;
        this.nationalite = nationalite;
        this.date_naissance = date_naissance;
    }

    public Membre(String nom, String image, String role, String nationalite, Date date_naissance, Equipe equipe) {
        this.nom = nom;
        this.image = image;
        this.role = role;
        this.nationalite = nationalite;
        this.date_naissance = date_naissance;
        this.equipe = equipe;
    }

    //constructeur with imageview
    public Membre(int membre_id, ImageView img, String nom, String image, String role, String nationalite, Date date_naissance, Equipe equipe) {
        this.membre_id = membre_id;
        this.img = img;
        this.nom = nom;
        this.image = image;
        this.role = role;
        this.nationalite = nationalite;
        this.date_naissance = date_naissance;
        this.equipe = equipe;
    }

    public Membre(int membre_id, ImageView img, String nom, String image, String role, String nationalite, Date date_naissance) {
        this.membre_id = membre_id;
        this.img = img;
        this.nom = nom;
        this.image = image;
        this.role = role;
        this.nationalite = nationalite;
        this.date_naissance = date_naissance;
    }

    public int getEquipe_id() {
        return equipe_id;
    }

    public void setEquipe_id(int equipe_id) {
        this.equipe_id = equipe_id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public String getImage() {
        return image;
    }

    public ImageView getImg() {
        return img;
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

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    @Override
    public String toString() {
        return "Membre{" + "membre_id=" + membre_id + ", nom=" + nom + ", role=" + role + ", nationalite=" + nationalite + ", date_naissance=" + date_naissance + '}';
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
