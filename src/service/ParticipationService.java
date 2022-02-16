/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Participation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

public class ParticipationService implements IService<Participation> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public ParticipationService() {
        conn = MyConnection.getInstance().getCnx();
    }

    @Override
    public void insert(Participation p) {
        String req = "insert into participation (pilote_id, equipe_id, course_id, qualifying_id, grid, position, points) values (?,?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, p.getPilote_id());
            pst.setInt(2, p.getEquipe_id());
            pst.setInt(3, p.getCourse_id());
            pst.setInt(4, p.getQualifying_id());
            pst.setInt(5, p.getGrid());
            pst.setInt(6, p.getPosition());
            pst.setInt(7, p.getPoints());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Participation p) {
        String req = "DELETE FROM participation WHERE participation_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, p.getParticipation_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(Participation p) {
        String req = "update participation set pilote_id=?, equipe_id=?, course_id=?, qualifying_id=?"
                + ", grid=?, position=?, points=?  where participation_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, p.getPilote_id());
            pst.setInt(2, p.getEquipe_id());
            pst.setInt(3, p.getCourse_id());
            pst.setInt(4, p.getQualifying_id());
            pst.setInt(5, p.getGrid());
            pst.setInt(6, p.getPosition());
            pst.setInt(7, p.getPoints());
            pst.setInt(8, p.getParticipation_id());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public List<Participation> read() {
        String req = "select * from participation";
        List<Participation> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Participation(rs.getInt("participation_id"), rs.getInt("pilote_id"), rs.getInt("equipe_id"),
                        rs.getInt("course_id"), rs.getInt("qualifying_id"), rs.getInt("grid"),
                        rs.getInt("position"), rs.getInt("points")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Participation readById(int id) {
        String req = "select * from participation where participation_id="+id;
        Participation p = new Participation();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                p.setParticipation_id(rs.getInt("participation_id"));
                p.setPilote_id(rs.getInt("pilote_id"));
                p.setEquipe_id(rs.getInt("equipe_id"));
                p.setCourse_id(rs.getInt("course_id"));
                p.setQualifying_id(rs.getInt("qualifying_id"));
                p.setGrid(rs.getInt("grid"));
                p.setPosition(rs.getInt("position"));
                p.setPoints(rs.getInt("points"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
