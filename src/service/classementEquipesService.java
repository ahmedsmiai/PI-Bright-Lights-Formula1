/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Saison;
import java.util.Date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import entite.ClassementEquipes;
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
public class ClassementEquipesService implements IService<ClassementEquipes> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public ClassementEquipesService() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void insert(ClassementEquipes u) {
        String req = "insert into classement_equipes (equipes_equipe_id,saisons_year,points_total,position) values ('" + u.getEquipes_equipe_id() + "','" + u.getSaisons_year() + "','" + u.getPoints_total() + "','" + u.getPosition() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(ClassementEquipesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertclassemet_equipePst(ClassementEquipes u) {
        String req = "insert into classement_equipes (equipes_equipe_id,saisons_year,points_total,position) values (?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getEquipes_equipe_id());
            pst.setInt(2, u.getSaisons_year());
            pst.setInt(3, u.getPoints_total());
            pst.setInt(4, u.getPosition());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClassementEquipesService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(ClassementEquipes u) {
        String req = "DELETE FROM classement_equipes WHERE classementE_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getClassementE_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassementEquipesService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(ClassementEquipes u) {
        try {

            String req = "update classement_equipes set equipes_equipe_id=?,saisons_year=?,points_total=?, position=? where classementE_id=?";

            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getEquipes_equipe_id());
            pst.setInt(2, u.getSaisons_year());
            pst.setInt(3, u.getPoints_total());
            pst.setInt(4, u.getPosition());
            pst.setInt(5, u.getClassementE_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassementEquipesService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public List<ClassementEquipes> read() {
        String req = "select * from classement_equipes";
        List<ClassementEquipes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementEquipes(rs.getInt("classementE_id"), rs.getInt("equipes_equipe_id"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementEquipesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ClassementEquipes readById(int classementE_id) {
        String req = "select * from classement_equipes where classementE_id="+classementE_id;
        ClassementEquipes u = new ClassementEquipes();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                u.setClassementE_id(rs.getInt("classementE_id"));
                u.setEquipes_equipe_id(rs.getInt("equipes_equipe_id"));
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
