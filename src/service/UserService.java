/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Role;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import entite.User;
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
public class UserService implements IService<User> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public UserService() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void insert(User u) {
        String req = "insert into user (username,email,password,role_id) values ('" + u.getUsername() + "','" + u.getEmail() + "','" + u.getPassword() + "','" + u.getRole_id() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertUserPst(User u) {
        String req = "insert into user (username,email,password,role_id) values (?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPassword());
            pst.setInt(4, u.getRole_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(User u) {
        String req = "DELETE FROM user WHERE user_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getUser_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(User u) {
        try {

            String req = "update user set username=?,email=?,password=?, role_id=? where user_id=?";

            pst = conn.prepareStatement(req);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPassword());
            pst.setInt(4, u.getRole_id());
            pst.setInt(5, u.getUser_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public List<User> read() {
        String req = "select * from user";
        List<User> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getTimestamp("create_time"), rs.getInt("role_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public User readById(int id) {
        String req = "select * from user where user_id="+id;
        User u = new User();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                u.setUser_id(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setCreate_time(rs.getTimestamp("create_time"));
                u.setRole_id(rs.getInt("role_id"));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    @Override
    public void login(User u) {
        /* try{
           String req = "select from user where email=?,password=?";  
           pst =conn.prepareStatement(req);
           pst.setString(1,u.getEmail());
            pst.setString(2,u.getPassword());
            rs= ste.executeQuery(req);
            if(rs.next()){
                System.out.println("Email and password matched");
            }else{
                System.out.println("Email and password non correct");
            }
        }catch(SQLException ex){
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    private static class Userservice {

        public Userservice() {
        }
    }

}
