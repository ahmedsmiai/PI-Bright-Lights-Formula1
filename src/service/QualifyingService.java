package service;

import entite.Qualifying;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;


public class QualifyingService implements IService<Qualifying> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public QualifyingService() {
        conn = MyConnection.getInstance().getCnx();
    }

    @Override
    public void insert(Qualifying q) {
        String req = "insert into qualifying (q1, q2, q3, position,pilote_id,course_id) values (?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, q.getQ1());
            pst.setString(2, q.getQ2());
            pst.setString(3, q.getQ3());
            pst.setInt(4, q.getPosition());
            pst.setInt(5, q.getPilote_id());
            pst.setInt(6, q.getCourse_id());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Qualifying q) {
        String req = "DELETE FROM qualifying WHERE qualifying_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, q.getQualifying_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(Qualifying q) {
        String req = "update qualifying set q1=?, q2=?, q3=?, position=?, pilote_id=?, course_id=? where qualifying_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, q.getQ1());
            pst.setString(2, q.getQ2());
            pst.setString(3, q.getQ3());
            pst.setInt(4, q.getPosition());
            pst.setInt(5, q.getPilote_id());
            pst.setInt(6, q.getCourse_id());
            pst.setInt(7, q.getQualifying_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public List<Qualifying> read() {
        String req = "select * from qualifying";
        List<Qualifying> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Qualifying(rs.getInt("qualifying_id"), rs.getString("q1"), rs.getString("q2"), rs.getString("q3"),
                        rs.getInt("position"), rs.getInt("pilote_id"), rs.getInt("course_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QualifyingService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Qualifying readById(int id) {
        String req = "select * from qualifying where qualifying_id="+id;
        Qualifying q = new Qualifying();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                q.setQualifying_id(rs.getInt("qualifying_id"));
                q.setQ1(rs.getString("q1"));
                q.setQ2(rs.getString("q2"));
                q.setQ3(rs.getString("q3"));
                q.setCourse_id(rs.getInt("course_id"));
                q.setPilote_id(rs.getInt("pilote_id"));
                q.setPosition(rs.getInt("position"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
    }

}
