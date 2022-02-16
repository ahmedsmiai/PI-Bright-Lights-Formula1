/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Equipe;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Datasource;
/**
 *
 * @author nechi
 */
public class EquipeService implements IService<Equipe>{
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public EquipeService() {
        conn = Datasource.getInstance().getCnx();
    }
    
    @Override
    public void insert(Equipe e) {
        String req = "insert into equipes (nom,voiture,pays_origine) values ('" + e.getNom() + "','" + e.getVoiture() + "','"+ e.getPays_origin()+"')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        Equipe eq=readById(id);
        if(eq.getEquipe_id() != 0){
            String req = "DELETE FROM equipes WHERE equipe_id=?";
            try {
                pst = conn.prepareStatement(req);
                pst.setInt(1, id);
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("L'équipe est supprimer avec succés");
        }else{
            System.out.println("L'équipe n'a pas trouver dans la base de données");
        }
    }

    @Override
    public void update(Equipe e) {
        Equipe eq=readById(e.getEquipe_id());
        if(eq.equals(e)){
            String req = "UPDATE equipes SET nom=? , voiture =? , pays_origine = ? WHERE equipe_id = ?";
            try {
                pst = conn.prepareStatement(req);
                pst.setString(1, e.getNom());
                pst.setString(2, e.getVoiture());
                pst.setString(3, e.getPays_origin());
                pst.setInt(4, e.getEquipe_id());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("L'équipe est modifier avec succés");
        }else{
            System.out.println("L'équipe n'a pas trouver dans la base de données");
        }
        
    }

    @Override
    public List<Equipe> read() {
        String req="select * from equipes";
        List<Equipe> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Equipe(rs.getInt("equipe_id"),rs.getString("nom"), rs.getString("voiture"),rs.getString("pays_origine")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Equipe readById(int id) {
        String req="select * from equipes where equipe_id = '"+ id+"'";
        Equipe equipe=new Equipe();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
               Equipe e= new Equipe(rs.getInt("equipe_id"),rs.getString("nom"), rs.getString("voiture"),rs.getString("pays_origine"));
               equipe=e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equipe;
    }

    
    
}
