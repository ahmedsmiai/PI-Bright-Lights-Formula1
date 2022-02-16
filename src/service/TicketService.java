/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entite.Tickets;
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
public class TicketService implements ICourse<Tickets>{
    
    private final Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public TicketService() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void insert(Tickets c) {
        String req;
        req = "insert into Tickets (course_id, user_id ) values ('" + c.getCourse_id()+ "','"+c.getUser_id()+"')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }

    @Override
    public void delete(Tickets c) {
        String req = "DELETE FROM tickets WHERE tickets_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, c.getTickets_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(Tickets c) {
       try {

            String req = "update tickets set course_id=?,user_id=?  where tickets_id =?";
            pst = conn.prepareStatement(req);
            pst.setInt(1, c.getCourse_id());
            pst.setInt(2, c.getUser_id());
            pst.setInt(3, c.getTickets_id());
            pst.executeUpdate();
           pst.close();
           

        } catch (SQLException ex) { Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Tickets> read() {
         String req = "select * from tickets";
        List<Tickets> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Tickets(rs.getInt("tickets_id"), rs.getInt("course_id"),rs.getInt("user_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Tickets readById(int tickets_id) {
        
        String req = "select * from tickets where tickets_id="+tickets_id;
        Tickets t = new Tickets();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                t.setTickets_id(rs.getInt("tickets_id"));
                t.setCourse_id(rs.getInt("course_id"));
                t.setUser_id(rs.getInt("user_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    
    }

    
    
}
