/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entite.Equipe;
import entite.Membre;
import entite.Pilote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Datasource;

/**
 *
 * @author nechi
 */
public class MembreService implements IService<Membre>{

    
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs,rc;
    
    public MembreService() {
        conn = Datasource.getInstance().getCnx();
    }
    
    
    @Override
    public void insert(Membre m) {
        System.out.println("mn MembreService insert : "+m.getImage());
        java.sql.Date DateNaissance = new java.sql.Date(m.getDate_naissance().getTime());
        String req ="insert into membres (nom ,image, role, nationalite, date_naissance , equipe_id) values (?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, m.getNom());
            pst.setString(2, m.getImage());
            pst.setString(3, m.getRole());
            pst.setString(4, m.getNationalite());
            pst.setDate(5, DateNaissance);
            pst.setInt(6, m.getEquipe().getEquipe_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Le Membre est ajouté avec succé");
    }

    @Override
    public void delete(int id) {
            String req = "DELETE FROM membres WHERE membre_id=?";
            try {
                pst = conn.prepareStatement(req);
                pst.setInt(1, id);
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void update(Membre m) {
        Membre membre=readById(m.getMembre_id());
        if(membre.equals(m)){
            java.sql.Date DateNaissance = new java.sql.Date(m.getDate_naissance().getTime());
            String req="UPDATE membres SET nom=? ,image=? , role =? , nationalite = ? , date_naissance = ? , equipe_id = ? WHERE membre_id = ?";
            try {
                pst = conn.prepareStatement(req);
                pst.setString(1, m.getNom());
                pst.setString(2, m.getImage());
                pst.setString(3, m.getRole());
                pst.setString(4, m.getNationalite());
                pst.setDate(5, DateNaissance);
                pst.setInt(6, m.getEquipe().getEquipe_id());
                pst.setInt(7, m.getMembre_id());
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
                String sql="select * from equipes where equipe_id="+rs.getInt("equipe_id");
                rc= ste.executeQuery(sql);
                if(rc.next()){
                    Equipe eq=new Equipe(rs.getInt("equipe_id"),rs.getString("nom"),rs.getString("email"),rs.getString("logo"),rs.getString("voiture"),rs.getString("pays_origin"));
                    list.add(new Membre(rs.getInt("membre_id"),rs.getString("nom"),rs.getString("image"),rs.getString("role"),rs.getString("nationalite"),rs.getDate("date_naissance"),eq));
                }else{
                    list.add(new Membre(rs.getInt("membre_id"),rs.getString("nom"),rs.getString("image"),rs.getString("role"),rs.getString("nationalite"),rs.getDate("date_naissance"),null));
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Membre readById(int id) {
        //String req="select * from membres where membre_id = '"+ id+"'";
        String req="SELECT * FROM equipes INNER JOIN membres ON equipes.equipe_id = membres.equipe_id WHERE membres.membre_id="+id;
        Membre mb=new Membre();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                Equipe eq=new Equipe(rs.getInt("equipe_id"),rs.getString("nom"),rs.getString("email"),rs.getString("logo"),rs.getString("voiture"),rs.getString("pays_origine"));
                Membre m=new Membre(rs.getInt("membre_id"),rs.getString("nom"),rs.getString("image"),rs.getString("role"),rs.getString("nationalite"),rs.getDate("date_naissance"),eq);
                mb=m;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mb;
    }
    
    
    public List<Pilote> readByEquipe(Equipe e){
        //System.out.println("m service : "+e.getEquipe_id());
        String req="select * from membres m LEFT JOIN pilotes p ON p.pilote_id=m.membre_id where m.equipe_id ="+e.getEquipe_id();
        List<Pilote> list = new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                String path="/view/images/membre/"+rs.getString("image");
                ImageView img = new ImageView(new Image(this.getClass().getResourceAsStream(path)));
                img.setFitHeight(50);
                img.setFitWidth(50);
                list.add(new Pilote(rs.getInt("pilote_id"),rs.getInt("numero"),rs.getInt("membre_id"),img,rs.getString("nom"),rs.getString("image"),rs.getString("role"),rs.getString("nationalite"),rs.getDate("date_naissance"),e));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public List<Pilote> SearchByName(String name){
        String req="select * from membres m LEFT JOIN pilotes p ON p.pilote_id=m.membre_id where m.nom LIKE '"+name+"%'";
        List<Pilote> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                String path="/view/images/membre/"+rs.getString("image");
                ImageView img = new ImageView(new Image(this.getClass().getResourceAsStream(path)));
                img.setFitHeight(50);
                img.setFitWidth(50);
                list.add(new Pilote(rs.getInt("pilote_id"),rs.getInt("numero"),rs.getInt("membre_id"),img,rs.getString("nom"),rs.getString("image"),rs.getString("role"),rs.getString("nationalite"),rs.getDate("date_naissance")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public Equipe getEquipe(Membre m){
        String req="select * from equipes e, membres m where e.equipe_id=m.equipe_id and m.membre_id="+m.getMembre_id();
        Equipe equipe=new Equipe();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                Equipe e=new Equipe(rs.getInt("equipe_id"),rs.getString("nom"),rs.getString("email"),rs.getString("logo"),rs.getString("voiture"),rs.getString("pays_origine"));
                equipe=e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equipe;
    }
    
    
    public List<Pilote> GetOnlyPilote(){
        String req="SELECT * FROM membres INNER JOIN pilotes ON membres.membre_id=pilotes.pilote_id WHERE membres.role=\"Pilote\";";
        List<Pilote> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                String path="/view/images/membre/"+rs.getString("image");
                ImageView img = new ImageView(new Image(this.getClass().getResourceAsStream(path)));
                img.setFitHeight(50);
                img.setFitWidth(50);
                list.add(new Pilote(rs.getInt("pilote_id"),rs.getInt("numero"),rs.getInt("membre_id"),img,rs.getString("nom"),rs.getString("image"),rs.getString("role"),rs.getString("nationalite"),rs.getDate("date_naissance")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
