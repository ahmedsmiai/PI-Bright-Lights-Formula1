/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import javafx.scene.image.ImageView;

/**
 *
 * @author nechi
 */
public class Equipe {
    private int equipe_id;
    private String nom;
    private String logo;
    private String voiture;
    private String pays_origin;
    private String mail;
    private ImageView img;

    public Equipe(){
        
    }
    
    
    public Equipe(int equipe_id, String nom,String logo,ImageView img, String voiture, String pays_origin) {
        this.equipe_id = equipe_id;
        this.nom = nom;
        this.img=img;
        this.voiture = voiture;
        this.pays_origin = pays_origin;
        this.logo=logo;
    }
    
    public Equipe(int equipe_id, String nom,String mail,String logo, String voiture, String pays_origin) {
        this.equipe_id = equipe_id;
        this.nom = nom;
        this.mail=mail;
        this.logo=logo;
        this.voiture = voiture;
        this.pays_origin = pays_origin;
    }
    
    
    public Equipe(int equipe_id, String nom,String logo, String voiture, String pays_origin) {
        this.equipe_id = equipe_id;
        this.nom = nom;
        this.logo=logo;
        this.voiture = voiture;
        this.pays_origin = pays_origin;
    }
    
    public Equipe(String nom,String mail,String logo, String voiture, String pays_origin) {
        this.nom = nom;
        this.mail=mail;
        this.logo=logo;
        this.voiture = voiture;
        this.pays_origin = pays_origin;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getEquipe_id() {
        return equipe_id;
    }

    public void setEquipe_id(int equipe_id) {
        this.equipe_id = equipe_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVoiture() {
        return voiture;
    }

    public void setVoiture(String voiture) {
        this.voiture = voiture;
    }

    public String getPays_origin() {
        return pays_origin;
    }

    public void setPays_origin(String pays_origin) {
        this.pays_origin = pays_origin;
    }

     @Override
    public String toString() {
        return this.nom;
    }
    
    public String toString2() {
       return "Equipe{" + "equipe_id=" + equipe_id + ", nom=" + nom + ", voiture=" + voiture + ", pays_origin=" + pays_origin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.equipe_id;
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
        final Equipe other = (Equipe) obj;
        if (this.equipe_id != other.equipe_id) {
            return false;
        }
        return true;
    }
    
    
    
}
