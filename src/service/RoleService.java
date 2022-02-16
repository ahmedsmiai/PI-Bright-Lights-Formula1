/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Role;
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

public class RoleService implements IService<Role> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public RoleService() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void insert(Role r) {
        String req = "insert into role (nom) values ('" + r.getNom() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertRolePst(Role r) {
        String req = "insert into role (nom) values (?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, r.getNom());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Role r) {
        String req = "DELETE FROM role WHERE role_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, r.getRole_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(Role r) {
        try {

            String req = "update role set nom=? where role_id=?";

            pst = conn.prepareStatement(req);
            pst.setString(1, r.getNom());
            pst.setInt(2, r.getRole_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public List<Role> read() {
        String req = "select * from role";
        List<Role> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Role(rs.getInt("role_id"), rs.getString("nom")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Roleservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Role readById(int id) {
        String req = "select * from role where role_id="+id;
        Role r = new Role();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                r.setRole_id(rs.getInt("role_id"));
                r.setNom(rs.getString("nom"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @Override
    public void login(Role t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    private static class Roleservice {

        public Roleservice() {
        }
    }

}
