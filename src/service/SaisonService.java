/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entite.Saison;

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
public class SaisonService implements IService<Saison> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public SaisonService() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void insert(Saison s) {
        String req = "insert into saisons (year,date_debut,date_fin) values ('" + s.getYear() + "','" + s.getDate_debut() + "','" + s.getDate_fin() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(SaisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inserSaisonPst(Saison s) {
        String req = "insert into saisons (year,date_debut,date_fin) values (?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, s.getYear());
            Date dateDeb = Date.valueOf(s.getDate_debut());
            pst.setDate(2,dateDeb);
            Date dateFin = Date.valueOf(s.getDate_fin());
            pst.setDate(3,dateFin);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SaisonService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
  
//    @Override
//    public void delete(Saison t) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public void delete(Saison s) {
        String req = "DELETE FROM saisons WHERE year=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, s.getYear());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SaisonService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(Saison s) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
     try{


            String req = "update saisons set date_debut=?,date_fin=? where year=?";

            pst =conn.prepareStatement(req);
            
            pst.setInt(1,s.getYear());
            Date dateDeb = Date.valueOf(s.getDate_debut());
            pst.setDate(2,dateDeb);
            Date dateFin = Date.valueOf(s.getDate_fin());
            pst.setDate(3,dateFin);
           
            pst.executeUpdate();
            pst.close();
            conn.close();
        }
        catch(SQLException e){e.printStackTrace();}
    }

    @Override
    public List<Saison> read() {
                    String req="select * from saisons";
                    List<Saison> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Saison(rs.getInt("year"), rs.getDate("date_debut").toLocalDate(), rs.getDate("date_fin").toLocalDate()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @Override
    public Saison readById(int year) {
       String req = "select * from saisons where year="+year;
        Saison s = new Saison();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                s.setYear(rs.getInt("year"));
               s.setDate_debut(rs.getDate("date_debut").toLocalDate());
                s.setDate_fin(rs.getDate("date_fin").toLocalDate());
               
    }
        }
    catch (SQLException ex) {
            Logger.getLogger(SaisonService.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    
    return s ;
        }
}
