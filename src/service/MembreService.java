/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entite.Membre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Datasource;

/**
 *
 * @author nechi
 */
public class MembreService implements IService<Membre>{

    
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public MembreService() {
        conn = Datasource.getInstance().getCnx();
    }
    
    
    @Override
    public void insert(Membre m) {
        java.sql.Date DateNaissance = new java.sql.Date(m.getDate_naissance().getTime());
        String req ="insert into membres (nom , role, nationalite, date_naissance , equipe_id) values (?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, m.getNom());
            pst.setString(2, m.getRole());
            pst.setString(3, m.getNationalite());
            pst.setDate(4, DateNaissance);
            pst.setInt(5, m.getEquipe_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Le Membre est ajouté avec succé");
    }

    @Override
    public void delete(int id) {
        Membre mb=readById(id);
        if(mb.getMembre_id() != 0){
            String req = "DELETE FROM membres WHERE membre_id=?";
            try {
                pst = conn.prepareStatement(req);
                pst.setInt(1, id);
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Le membre est supprimer avec succés");
        }else{
            System.out.println("Le membre n'a pas trouver dans la base de données");
        }
    }

    @Override
    public void update(Membre m) {
        Membre membre=readById(m.getMembre_id());
        if(membre.equals(m)){
            java.sql.Date DateNaissance = new java.sql.Date(m.getDate_naissance().getTime());
            String req="UPDATE membres SET nom=? , role =? , nationalite = ? , date_naissance = ? , equipe_id = ? WHERE membre_id = ?";
            try {
                pst = conn.prepareStatement(req);
                pst.setString(1, m.getNom());
                pst.setString(2, m.getRole());
                pst.setString(3, m.getNationalite());
                pst.setDate(4, DateNaissance);
                pst.setInt(5, m.getEquipe_id());
                pst.setInt(6, m.getMembre_id());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Le Membre est modifier avec succé");
        }else{
            System.out.println("Le Membre n'a pas trouvé dans la base de données");
        }
    }

    @Override
    public List<Membre> read() {
        String req="select * from membres";
        List<Membre> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Membre(rs.getInt("membre_id"),rs.getString("nom"),rs.getString("role"),rs.getString("nationalite"),rs.getDate("date_naissance"),rs.getInt("equipe_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Membre readById(int id) {
        String req="select * from membres where membre_id = '"+ id+"'";
        Membre mb=new Membre();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
               Membre m=new Membre(rs.getInt("membre_id"),rs.getString("nom"),rs.getString("role"),rs.getString("nationalite"),rs.getDate("date_naissance"),rs.getInt("equipe_id"));
               mb=m;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mb;
    }
    
}
