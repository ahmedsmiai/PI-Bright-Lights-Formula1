/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;



import entite.classement_pilotes;

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
public class classementPilotesService {  // test // 
    
final private Connection conn;   ///////////////////////////////////////////////////////////////////////////added final to connection 
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public classementPilotesService() {
        conn = Datasource.getInstance().getCnx();
    }

    
    public void insert(classement_pilotes u) {
        String req = "insert into user (pilotes_pilote_id,saisons_year,points_total,position) values ('" + u.getPilotes_pilote_id() + "','" + u.getSaisons_year() + "','" + u.getPoints_total() + "','" + u.getPosition() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(classementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertclassement_pilotesPst(classement_pilotes u) {
        String req = "insert into user (pilotes_pilote_id,saisons_year,points_total,position) values (?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getPilotes_pilote_id());
            pst.setInt(2, u.getSaisons_year());
            pst.setInt(3, u.getPoints_total());
            pst.setInt(4, u.getPosition());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(classementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
    public void delete(classement_pilotes u) {
        String req = "DELETE FROM user WHERE classementP_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getClassementP_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(classementPilotesService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

   
    public void update(classement_pilotes u) {
        try {

            String req = "update user set pilotes_pilote_id=?,saisons_year=?,points_total=?, position=? where classementP_id=?";

            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getPilotes_pilote_id());
            pst.setInt(2, u.getSaisons_year());
            pst.setInt(3, u.getPoints_total());
            pst.setInt(4, u.getPosition());
            pst.setInt(5, u.getClassementP_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(classementPilotesService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    
    public List<classement_pilotes> read() {
        String req = "select * from user";
        List<classement_pilotes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new classement_pilotes(rs.getInt("classementP_id"), rs.getInt("pilotes_pilote_id"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(classementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

  
    public classement_pilotes readById(int id) {
        String req = "select * from user where classementP_id="+id;
        classement_pilotes u = new classement_pilotes();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                u.setClassementP_id(rs.getInt("classementP_id"));
                u.setPilotes_pilote_id(rs.getInt("pilotes_pilote_id"));
                u.setSaisons_year(rs.getInt("saisons_year"));
                u.setPoints_total(rs.getInt("points_total"));
                u.setPosition(rs.getInt("position"));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

   


}

