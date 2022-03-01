/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.classement_equipes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Datasource;

/**
 *
 * @author qwiw
 */
public class classementEquipesService {
    
final private Connection conn;   ///////////////////////////////////////////////////////////////////////////added final to connection 
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public classementEquipesService() {
        conn = Datasource.getInstance().getCnx();
    }

    
    public void insert(classement_equipes u) {
        String req = "insert into user (equipes_equipe_id,saisons_year,points_total,role_id) values ('" + u.getEquipes_equipe_id() + "','" + u.getSaisons_year() + "','" + u.getPoints_total() + "','" + u.getPosition() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(classementEquipesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertclassement_equipesPst(classement_equipes u) {
        String req = "insert into user (equipes_equipe_id,saisons_year,points_total,role_id) values (?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getEquipes_equipe_id());
            pst.setInt(2, u.getSaisons_year());
            pst.setInt(3, u.getPoints_total());
            pst.setInt(4, u.getPosition());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(classementEquipesService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
    public void delete(classement_equipes u) {
        String req = "DELETE FROM user WHERE classementE_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getClassementE_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(classementEquipesService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

   
    public void update(classement_equipes u) {
        try {

            String req = "update user set equipes_equipe_id=?,saisons_year=?,points_total=?, position=? where classementE_id=?";

            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getEquipes_equipe_id());
            pst.setInt(2, u.getSaisons_year());
            pst.setInt(3, u.getPoints_total());
            pst.setInt(4, u.getPosition());
            pst.setInt(5, u.getClassementE_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(classementEquipesService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    
    public List<classement_equipes> read() {
        String req = "select * from user";
        List<classement_equipes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new classement_equipes(rs.getInt("classementE_id"), rs.getInt("equipes_equipe_id"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(classementEquipesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

  
    public classement_equipes readById(int id) {
        String req = "select * from user where classementE_id="+id;
        classement_equipes u = new classement_equipes();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                u.setClassementE_id(rs.getInt("classementE_id"));
                u.setEquipes_equipe_id(rs.getInt("equipes_equipe_id"));
                u.setSaisons_year(rs.getInt("saisons_year"));
                u.setPoints_total(rs.getInt("points_total"));
                u.setPosition(rs.getInt("role_id"));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

   


}

