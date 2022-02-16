/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;


/**
 *
 * @author win10LIGHT
 */
public class Role {
private int role_id;
    private String nom;
    
    public Role() {
        
}
       public Role(int role_id, String nom) {
        this.role_id = role_id;
        this.nom = nom;
    }
       
       public Role(String nom) {
        this.nom = nom;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Role{" + "role_id=" + role_id + ", nom=" + nom + '}';
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
        final Role other = (Role) obj;
        if (this.role_id != other.role_id) {
            return false;
        }
        return true;
    }
       
       
    
}
    