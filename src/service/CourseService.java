/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entite.Circuits;

import entite.course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import service.IService;
import utils.Datasource;
/**
 *
 * @author manaa
 */



    public class CourseService implements IService<course>{
       


    private final Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    


    public CourseService() {
        conn = Datasource.getInstance().getCnx();
    }

        @Override
        public void insert(course c) {
            String req;
                    java.sql.Date DateCourse = new java.sql.Date(c.getDate().getTime());

        
        try {
                  req = " insert into courses (course_nom, date,circuit_id,saison_year,user_id) values (?,?,?,?,?)";
                  pst = conn.prepareStatement(req);
                  pst.setString(1,c.getCourse_nom());
                  pst.setDate(2, DateCourse);
                  pst.setObject(3,c.getCircuit_circuit_id().getCircuit_id());
                  pst.setInt(4, c.getSaison_year().getYear());
                  pst.setInt(5, c.getUsers().getUser_id());
                  pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        
        }}

      

        

        @Override
        public List<course> read() {
                          String req;
        req = "select * from courses ";
        circuitService cs = new circuitService();
        SaisonService s = new SaisonService();
        UserService us = new UserService();
                    List<course> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
//            while(rs.next()){
//                list.add(new course(rs.getInt("course_id"), rs.getString("course_nom"), rs.getString("date"), (Circuits) rs.getObject("circuit_id"), rs.getInt("saison_year")));
//            }
            while(rs.next()){
                
                
                
                    
                    list.add(new course(rs.getInt("course_id"), rs.getString("course_nom"), rs.getDate("date"),cs.readById(rs.getInt("circuit_id")),s.readById( rs.getInt("saison_year")),us.readById(rs.getInt("user_id"))));
                
                
            }
        } catch (SQLException ex) {
            System.out.println(ex);        }
        return list;

        }

        @Override
        public course readById(int id) {
 String req = "select * from courses where course_id="+id;
        course crs = new course();
        circuitService cs = new circuitService();
        UserService us = new UserService();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                crs.setCourse_id(rs.getInt("course_id"));
                crs.setCourse_nom(rs.getString("course_nom"));
                crs.setDate(rs.getDate("date"));
                crs.setCircuit_circuit_id(cs.readById((int) rs.getObject("circuit_id")));
                crs.setUsers(us.readById(rs.getInt("user_id")));
//                crs.setSaison_year(rs.getInt("saison_year"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return crs;           }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM courses WHERE course_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    public List<course> SearchByName(String name){
        String req="SELECT * FROM `courses` WHERE course_nom LIKE '%"+name+"%'";
        List<course> list=new ArrayList<>();
        try {
            circuitService sss=new circuitService();
            SaisonService sv=new SaisonService();
            UserService us= new UserService();
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                
                list.add(new course(rs.getInt("course_id"), rs.getString("course_nom"), rs.getDate("date"),sss.readById(rs.getInt("circuit_id")),sv.readById(rs.getInt("saison_year")), us.readById(rs.getInt("user_id"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    @Override
        public void update(course c) {
            System.out.println("update couse "+c.getCourse_id());
               try{  
                   
                  java.sql.Date DateCourse = new java.sql.Date(c.getDate().getTime());
              
                  String req = "update courses set course_nom=? , date=?,circuit_id=?,saison_year=? , user_id=? where course_id=?";
                  pst = conn.prepareStatement(req);
                  pst.setString(1,c.getCourse_nom());
                  pst.setDate(2, DateCourse);
                  pst.setInt(3,c.getCircuit_circuit_id().getCircuit_id());
                  pst.setInt(4, c.getSaison_year().getYear());
                  pst.setInt(5, c.getUsers().getUser_id());
                  pst.setInt(6,c.getCourse_id());
                  
                  pst.executeUpdate();
                             // pst.close();

                  
                  
                
             }catch(SQLException ex){ Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);}
        }
    }