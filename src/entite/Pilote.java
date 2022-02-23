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
public class Pilote extends Membre{
    private int pilote_id;
    private int numero;

    public Pilote(int pilote_id, int numero, int membre_id, String nom, String role, String nationalite, Date date_naissance, Equipe equipe) {
        super(membre_id, nom, role, nationalite, date_naissance, equipe);
        this.pilote_id = pilote_id;
        this.numero = numero;
    }

    public Pilote(int numero, String nom, String role, String nationalite, Date date_naissance, Equipe equipe) {
        super(nom, role, nationalite, date_naissance, equipe);
        this.numero = numero;
    }

    public Pilote(int pilote_id, int numero) {
        this.pilote_id = pilote_id;
        this.numero = numero;
    }
    
    public Pilote(int numero) {
        this.numero = numero;
    }
    
    public Pilote(){
        
    }

    public int getPilote_id() {
        return pilote_id;
    }

    public void setPilote_id(int pilote_id) {
        this.pilote_id = pilote_id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        String ch=super.toString();
        return ch+"Pilote{" + "pilote_id=" + pilote_id + ", numero=" + numero + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.pilote_id;
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
        final Pilote other = (Pilote) obj;
        if (this.pilote_id != other.pilote_id) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
