/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entite.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Datasource;

/**
 *
 * @author manaa
 */
public class SaisonService implements IService<Saison>{
     private final Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public SaisonService() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void insert(Saison t) {
        String req;
        req = "insert into saisons (year) values (?}";
         try {
             pst = conn.prepareStatement(req);
             pst.setInt(1,t.getYear());
             pst.executeUpdate();
             pst.close();

         } catch (SQLException ex) {
             Logger.getLogger(SaisonService.class.getName()).log(Level.SEVERE, null, ex);
         }
                  
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Saison t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Saison> read() {
       String req = "select * from saisons ";
            List<Saison> list = new ArrayList<>();
         try {
            
             
             
             ste = conn.createStatement();
             rs = ste.executeQuery(req);
             while (rs.next()) {
                 list.add(new Saison(rs.getInt("year")));
             }    } catch (SQLException ex) {
             Logger.getLogger(SaisonService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return list;
    }

    @Override
    public Saison readById(int id) 
    {       String req = "select * from saisons where year="+id;
             Saison p = new Saison();
         try {
            
             
             
             ste = conn.createStatement();
             rs = ste.executeQuery(req);
             while (rs.next()) {
                 p.setYear(rs.getInt("year"));
             }    } catch (SQLException ex) {
             Logger.getLogger(SaisonService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return p;
    }
}



