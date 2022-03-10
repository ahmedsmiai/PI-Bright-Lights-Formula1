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
import utils.Datasource;

/**
 *
 * @author nechi
 */
public class PiloteService implements IService<Pilote>{
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs,rc;
    
    public PiloteService() {
        conn = Datasource.getInstance().getCnx();
    }
    
    //insert pilote sans ajouter membre
    public void insertSeulementPilote(Pilote p){
        String req ="insert into pilotes (pilote_id,numero) values (?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, p.getPilote_id());
            pst.setInt(2, p.getNumero());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PiloteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Le Pilote est ajouté avec succé");
    }

    @Override
    public void insert(Pilote p) {
        //add Membre
        MembreService ms=new MembreService();
        ms.insert(new Membre(p.getNom(),p.getImage(),p.getRole(),p.getNationalite(),p.getDate_naissance(),p.getEquipe()));
        //Get last Id inserted in membre
        int last=0;
        String sql="select max(membre_id) as last from membres";
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(sql);
            if(rs.next()){
                last=rs.getInt("last");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PiloteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //add pilote
        String req ="insert into pilotes (pilote_id,numero) values (?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, last);
            pst.setInt(2, p.getNumero());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PiloteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Le Pilote est ajouté avec succé");
    }

    @Override
    public void delete(int id) {
            //Delete pilote from pilote
            String reqp="DELETE FROM pilotes WHERE pilote_id=?";
            try {
                pst = conn.prepareStatement(reqp);
                pst.setInt(1, id);
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PiloteService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Le pilote est supprimer avec succés");
        
        //Delete from table membre
        MembreService ms=new MembreService();
        ms.delete(id);
        
    }

    @Override
    public void update(Pilote p) {
        if(p.getRole().equals("Pilote")){
            //update Membre
            MembreService ms=new MembreService();
            ms.update(new Membre(p.getMembre_id(),p.getNom(),p.getImage(),p.getRole(),p.getNationalite(),p.getDate_naissance(),p.getEquipe()));

            //update pilote
            String req ="UPDATE pilotes SET numero=? WHERE pilote_id = '"+p.getPilote_id()+"'";
            try {
                pst = conn.prepareStatement(req);
                pst.setInt(1, p.getNumero());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PiloteService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Le Pilote est modifier avec succé");
        }else{
            MembreService ms=new MembreService();
            ms.update(new Membre(p.getMembre_id(),p.getNom(),p.getImage(),p.getRole(),p.getNationalite(),p.getDate_naissance(),p.getEquipe()));
            //delete from table pilote
            String reqp="DELETE FROM pilotes WHERE pilote_id=?";
            try {
                pst = conn.prepareStatement(reqp);
                pst.setInt(1, p.getMembre_id());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PiloteService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
     
    public List<Pilote> read1() {
        String req="select * from membres m join pilotes p where m.membre_id=p.pilote_id";
        List<Pilote> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Pilote(rs.getInt("pilote_id"),rs.getInt("numero"),rs.getString("nom"),rs.getString("role"),rs.getString("nationalite"),rs.getDate("date_naissance"),rs.getInt("equipe_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PiloteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
    
    
    
    

    @Override
    public List<Pilote> read() {
        String req="select * from membres m,pilotes p where m.membre_id=p.pilote_id";
        List<Pilote> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                String sql="select * from equipes where equipe_id="+rs.getInt("equipe_id");
                rc= ste.executeQuery(sql);
                if(rc.next()){
                    Equipe eq=new Equipe(rc.getInt("equipe_id"),rc.getString("nom"),rc.getString("email"),rc.getString("logo"),rc.getString("voiture"),rc.getString("pays_origine"));
                    list.add(new Pilote(rs.getInt("pilote_id"),rs.getInt("numero"),rs.getInt("membre_id"),rs.getString("nom"),rs.getString("image"),rs.getString("role"),rs.getString("nationalite"),rs.getDate("date_naissance"),eq));
                }else{
                    list.add(new Pilote(rs.getInt("pilote_id"),rs.getInt("numero"),rs.getInt("membre_id"),rs.getString("nom"),rs.getString("image"),rs.getString("role"),rs.getString("nationalite"),rs.getDate("date_naissance"),null));
                }                
            }
        } catch (SQLException ex) {
            Logger.getLogger(PiloteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
    public Pilote readById1(int id) {
        String req="select * from membres m,pilotes p where m.membre_id=p.pilote_id and pilote_id='"+id+"'";
        Pilote pl=new Pilote();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                Pilote p=new Pilote(rs.getInt("pilote_id"),rs.getInt("numero"),rs.getString("nom"),rs.getString("role"),rs.getString("nationalite"),rs.getDate("date_naissance"),rs.getInt("equipe_id"));
                pl=p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PiloteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pl;
    }
    
    @Override
    public Pilote readById(int id) {
        String req="select * from membres m,pilotes p where m.membre_id=p.pilote_id and pilote_id='"+id+"'";
        Pilote pl=new Pilote();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                System.out.println("readById = "+rs.getInt("equipe_id"));
                String sql="select * from equipes where equipe_id="+rs.getInt("equipe_id");
                rc= ste.executeQuery(sql);
                if(rc.next()){
                    Equipe eq=new Equipe(rc.getInt("equipe_id"),rc.getString("nom"),rc.getString("email"),rc.getString("logo"),rc.getString("voiture"),rc.getString("pays_origine"));
                    Pilote p=new Pilote(rs.getInt("pilote_id"),rs.getInt("numero"),rs.getInt("membre_id"),rs.getString("nom"),rs.getString("image"),rs.getString("role"),rs.getString("nationalite"),rs.getDate("date_naissance"),eq);
                    pl=p;
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(PiloteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pl;
    }
}
