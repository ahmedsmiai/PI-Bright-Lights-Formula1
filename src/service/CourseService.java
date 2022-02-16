/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entite.course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Datasource;
/**
 *
 * @author manaa
 */



    public class CourseService implements ICourse<course>{
       


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
        req = "insert into courses (Course_nom, date , circuit_id ,saison_year , course_id) values ('" + c.getCourse_nom() + "','"+c.getDate()+"','" + c.getCircuit_circuit_id() + "','"+c.getSaison_year()+"','"+c.getCourse_id()+"')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        
        }}

        @Override
        public void delete(course c) {
             String req = "DELETE FROM courses WHERE course_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, c.getCourse_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);

        }
        }

        @Override
        public void update(course c) {
               try{  
                   

              
                  String req = "update courses set course_nom=? , date=?,circuit_id=?,saison_year=? where course_id=?";
                  pst = conn.prepareStatement(req);
                  pst.setString(1,c.getCourse_nom());
                  pst.setString(2,c.getDate());
                  pst.setInt(3,c.getCircuit_circuit_id());
                  pst.setInt(4, c.getSaison_year());
                  pst.setInt(5,c.getCourse_id());
                  pst.executeUpdate();
                  pst.close();
                
             }catch(SQLException ex){ Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);}
        }

        @Override
        public List<course> read() {
                          String req="select * from courses";
                    List<course> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new course(rs.getInt("course_id"), rs.getString("course_nom"), rs.getString("date"), rs.getInt("circuit_id"), rs.getInt("saison_year")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

        }

        @Override
        public course readById(int id) {
 String req = "select * from courses where course_id="+id;
        course crs = new course();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                crs.setCourse_id(rs.getInt("course_id"));
                crs.setCourse_nom(rs.getString("course_nom"));
                crs.setDate(rs.getString("date"));
                crs.setCircuit_circuit_id(rs.getInt("circuit_id"));
                crs.setSaison_year(rs.getInt("saison_year"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return crs;           }
    }