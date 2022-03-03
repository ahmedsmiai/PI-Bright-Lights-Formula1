/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.sun.prism.GraphicsPipeline;
import entite.Saison;
import java.util.Date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import entite.ClassementPilotes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Datasource;

/**
 *
 * @author win10LIGHT
 */
public class ClassementPilotesService implements IService<ClassementPilotes> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private ResultSet rss;
    

    public ClassementPilotesService() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void insert(ClassementPilotes u) {
        String req = "insert into classement_pilotes (pilotes_pilote_id,saisons_year,points_total,position) values ('" + u.getPilotes_pilote_id() + "','" + u.getSaisons_year() + "','" + u.getPoints_total() + "','" + u.getPosition() + "')";
        try {
    
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("pilote_id ou saison invalide");

        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
             
        }
    }

    public void insertclassemet_pilotePst(ClassementPilotes u) {
        String req = "insert into classement_pilotes (pilotes_pilote_id,saisons_year,points_total,position) values (?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getPilotes_pilote_id());
            pst.setInt(2, u.getSaisons_year());
            pst.setInt(3, u.getPoints_total());
            pst.setInt(4, u.getPosition());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(ClassementPilotes u) {
        String req = "DELETE FROM classement_pilotes WHERE classementP_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getClassementP_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(ClassementPilotes u) {
        try {

            String req = "update classement_pilotes set pilotes_pilote_id=?,saisons_year=?,points_total=?, position=? where classementP_id=?";

            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getPilotes_pilote_id());
            pst.setInt(2, u.getSaisons_year());
            pst.setInt(3, u.getPoints_total());
            pst.setInt(4, u.getPosition());
            pst.setInt(5, u.getClassementP_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public List<ClassementPilotes> read() {
        String req = "select * from classement_pilotes";
        List<ClassementPilotes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementPilotes(rs.getInt("classementP_id"), rs.getInt("pilotes_pilote_id"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @Override
    public ClassementPilotes readById(int classementP_id) {
        String req = "select * from classement_pilotes where classementP_id="+classementP_id;
        ClassementPilotes u = new ClassementPilotes();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                u.setClassementE_id(rs.getInt("classementP_id"));
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
