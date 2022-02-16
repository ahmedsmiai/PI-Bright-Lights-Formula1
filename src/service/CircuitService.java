/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Circuits;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author manaa
 */
public class CircuitService implements IService<Circuits> {

    private final Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public CircuitService() {
        conn = MyConnection.getInstance().getCnx();
    }

    @Override
    public void insert(Circuits c) {
//        String req;
//        req = "insert into Circuits ( nom , pays , long, course_distance , description , capacite) values ('" + c.getNom() + "','" + c.getPays() + "','" + c.getLang() + "','" + c.getCourse_distance() + "','" + c.getDescription() + "','" + c.getCapacite() + "')";
//        try {
//            ste = conn.createStatement();
//            ste.executeUpdate(req);
//        } catch (SQLException ex) {
//            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
//
//        }

        String req = "insert into circuits (nom , pays , longeur, course_distance , description , capacite) values (?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getPays());
            pst.setInt(3, c.getLang());
            pst.setInt(4, c.getCourse_distance());
            pst.setString(5, c.getDescription());
            pst.setInt(6, c.getCapacite());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CircuitService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Circuits c) {
       String req = "DELETE FROM circuits WHERE circuit_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, c.getCircuit_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CircuitService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    

    @Override
    public void update(Circuits c) {
        try {

            String req = "update circuits set nom=?,pays=?,longeur=?,course_distance=? ,description=?,capacite=?  where Circuit_id=?";
            pst = conn.prepareStatement(req);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getPays());
            pst.setInt(3, c.getLang());
            pst.setInt(4, c.getCourse_distance());
            pst.setString(5, c.getDescription());
            pst.setInt(6, c.getCapacite());
            pst.setInt(7, c.getCircuit_id());
            pst.executeUpdate();
            pst.close();

        } catch (SQLException ex) {
        }

    }

    @Override
    public List<Circuits> read() {
        String req = "select * from circuits";
        List<Circuits> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Circuits(rs.getInt("circuit_id"), rs.getString("nom"), rs.getString("pays"), rs.getInt("longeur"), rs.getInt("course_distance"), rs.getString("description"), rs.getInt("capacite")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CircuitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Circuits readById(int id) {
        String req = "select * from circuits where circuit_id="+id;
        Circuits p = new Circuits();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                p.setCircuit_id(rs.getInt("circuit_id"));
                p.setNom(rs.getString("nom"));
                p.setPays(rs.getString("pays"));
                p.setLang(rs.getInt("longeur"));
                p.setCourse_distance(rs.getInt("course_distance"));
                p.setDescription(rs.getString("description"));
                p.setCapacite(rs.getInt("capacite"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CircuitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;    }

}