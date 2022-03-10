/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.sun.prism.GraphicsPipeline;
import entite.Saison;
import java.util.Date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import entite.ClassementPilotes;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class ClassementPilotesService implements IService<ClassementPilotes> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private ResultSet rss;

    public ClassementPilotesService() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void insert(ClassementPilotes u) {
        String req = "insert into classement_pilotes (pilotes_pilote_id,saisons_year,points_total,position) values ('" + u.getPilotes_pilote_id() + "','" + u.getSaisons_year() + "','" + u.getPoints_total() + "','" + u.getPosition() + "')";
        try {

            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("pilote_id ou saison invalide");

        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void insertclassemet_pilotePst(ClassementPilotes u) {
        String req = "insert into classement_pilotes (pilotes_pilote_id,saisons_year,points_total) values (?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getPilotes_pilote_id());
            pst.setInt(2, u.getSaisons_year());
            pst.setInt(3, u.getPoints_total());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(ClassementPilotes u) {
        System.out.println("delete pilote service");
        String req = "DELETE FROM classement_pilotes WHERE classementP_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getClassementP_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(ClassementPilotes u) {
        try {

            String req = "update classement_pilotes set pilotes_pilote_id=?,saisons_year=?,points_total=?, position=? where classementP_id=?";

            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getPilotes_pilote_id());
            pst.setInt(2, u.getSaisons_year());
            pst.setInt(3, u.getPoints_total());
            pst.setInt(4, u.getPosition());
            pst.setInt(5, u.getClassementP_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public List<ClassementPilotes> read() {
        String req = "select * from classement_pilotes";
        List<ClassementPilotes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementPilotes(rs.getInt("classementP_id"), rs.getInt("pilotes_pilote_id"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    ////////////////////////////////////////////////////////////////////////////////upd score
    
//        public void score() {
//             try {
// ClassementPilotes u = new ClassementPilotes();
//            String req = "update classement_pilotes set points_total= where classementP_id=?";
//String req2 ="select sum(points) from participation join courses on participation.course_id=courses.course_id WHERE pilote_id=? and saison_year=?" ;
//            pst = conn.prepareStatement(req);
//            pst = conn.prepareStatement(req2);
//           
//            pst.setInt(3, u.getPoints_total());
//
//            pst.setInt(5, u.getClassementP_id());
//            pst.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//
//    
//        }

    ////////////////////////////////////////////////////////////////////////read with pilote name 
public List<ClassementPilotes> read2() {
        String req = "SELECT C.classementP_id, C.saisons_year, C.points_total, C.position, P.nom FROM classement_pilotes C LEFT OUTER JOIN membres P ON C.pilotes_pilote_id = P.membre_id";
        List<ClassementPilotes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementPilotes(rs.getInt("classementP_id"), rs.getString("nom"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

//"select * from classement_pilotes where classementP_id=" + classementP_id
public List<ClassementPilotes> readbys(int saisons_year) {
        String req = "SELECT C.classementP_id, C.saisons_year, C.points_total, C.position, P.nom FROM classement_pilotes C LEFT OUTER JOIN membres P ON C.pilotes_pilote_id = P.membre_id where saisons_year=" + saisons_year ;
       List<ClassementPilotes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementPilotes(rs.getInt("classementP_id"), rs.getString("nom"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////tri
    public List<ClassementPilotes> triID() {
        String req = "SELECT C.classementP_id, C.saisons_year, C.points_total, C.position, P.nom FROM classement_pilotes C LEFT OUTER JOIN membres P ON C.pilotes_pilote_id = P.membre_id  order by classementP_id ";
        List<ClassementPilotes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementPilotes(rs.getInt("classementP_id"), rs.getString("nom"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ClassementPilotes> triSaison() {
        String req = "SELECT C.classementP_id, C.saisons_year, C.points_total, C.position, P.nom FROM classement_pilotes C LEFT OUTER JOIN membres P ON C.pilotes_pilote_id = P.membre_id  order by saisons_year, position";
        List<ClassementPilotes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementPilotes(rs.getInt("classementP_id"), rs.getString("nom"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ClassementPilotes> triPilote() {
        String req = "SELECT C.classementP_id, C.saisons_year, C.points_total, C.position, P.nom FROM classement_pilotes C LEFT OUTER JOIN membres P ON C.pilotes_pilote_id = P.membre_id  order by pilotes_pilote_id ";
        List<ClassementPilotes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementPilotes(rs.getInt("classementP_id"), rs.getString("nom"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ClassementPilotes> triPos() {
        String req = "SELECT C.classementP_id, C.saisons_year, C.points_total, C.position, P.nom FROM classement_pilotes C LEFT OUTER JOIN membres P ON C.pilotes_pilote_id = P.membre_id  order by position ";
        List<ClassementPilotes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementPilotes(rs.getInt("classementP_id"), rs.getString("nom"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //////////////////////////////////////////////////////////////////////////
    public List<ClassementPilotes> readjustid() {
        String req = "select * from classement_pilotes";

        List<ClassementPilotes> list = new ArrayList<>();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementPilotes(rs.getInt("classementP_id"), rs.getInt("pilotes_pilote_id"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public List<ClassementPilotes> readjustnom() {
        String req = "SELECT nom FROM membres INNER JOIN pilotes ON membres.membre_id=pilotes.pilote_id; ";

        List<ClassementPilotes> list = new ArrayList<>();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementPilotes(rs.getString("nom")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public ClassementPilotes convert(String nom) {
        
          //System.out.println("req ="+req);
       ClassementPilotes u = new ClassementPilotes();

        try {
            String req = "SELECT membre_id from membres where nom='"+nom+"'";
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                System.out.println("convert ligne 267"+rs.getInt("membre_id "));
                u.setPilotes_pilote_id(rs.getInt("membre_id"));
  

            }
        } catch (SQLException ex) {
            Logger.getLogger(SaisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
          System.out.println("id="+u.getPilotes_pilote_id());
        return u;
    }
     
     
//         public List<ClassementPilotes> readjustid2() {
//                    String req="SELECT nom from membres group by saison ";
//                  
//                    List<ClassementPilotes> list=new ArrayList<>();
//        try {
//            ste=conn.createStatement();
//            rs= ste.executeQuery(req);
//            while(rs.next()){
//                list.add(new ClassementPilotes(rs.getString("nom")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }

    @Override
    public ClassementPilotes readById(int classementP_id) {
        String req = "select * from classement_pilotes where classementP_id=" + classementP_id;
        ClassementPilotes u = new ClassementPilotes();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                u.setClassementE_id(rs.getInt("classementP_id"));
                u.setPilotes_pilote_id(rs.getInt("pilotes_pilote_id"));
                u.setSaisons_year(rs.getInt("saisons_year"));
                u.setPoints_total(rs.getInt("points_total"));
                u.setPosition(rs.getInt("position"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(SaisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

/////////////////////////////////////////////////////////////////position fcts    
    public List<ClassementPilotes> findAll() { //pos+bd to list
        ClassementPilotes u = new ClassementPilotes();
        List<ClassementPilotes> lcp = new ArrayList<ClassementPilotes>();
        try {
            String req = "select * from classement_pilotes order by saisons_year, points_total DESC";//points_total DESC
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            int pos = 1;
            int saison = 0;
            while (rs.next()) {
                if(rs.getInt("saisons_year")!=saison){
                    pos=1;
                }
         saison = rs.getInt("saisons_year");
         
                lcp.add(new ClassementPilotes(rs.getInt("classementP_id"), rs.getInt("pilotes_pilote_id"), rs.getInt("saisons_year"), rs.getInt("points_total"), pos));
                pos++;
            }
        } catch (Exception e) {
            lcp = null;
        }
        
        return lcp;

    }

     public List<ClassementPilotes> pts() {
        String req = "SELECT  C.pilote_id, P.saison_year, C.points FROM participation C  LEFT OUTER JOIN courses P ON C.course_id = P.course_id order by saison_year;";
        List<ClassementPilotes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementPilotes( rs.getInt("pilote_id"), rs.getInt("saison_year"), rs.getInt("points")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       public void score() { //list to db

        List<ClassementPilotes> lcp = pts();

        for (ClassementPilotes u : lcp) {
            try {

                String req = "update classement_pilotes set points_total=? where pilotes_pilote_id=? and saisons_year=?";
                String s="update classement_pilotes set position=? where classementP_id=?";

                pst = conn.prepareStatement(req);

                pst.setInt(1, u.getPosition());
                pst.setInt(2, u.getPilotes_pilote_id());
                pst.setInt(3, u.getSaisons_year());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }
    


    public void afficher() {

        List<ClassementPilotes> lcp = pts();
        for (ClassementPilotes u : lcp) {
            System.out.println("Id: " + u.getClassementP_id());
            //	System.out.println("saison: " + u.getSaisons_year());
            //System.out.println("piloteID: " + u.getPilotes_pilote_id());
            System.out.println("pts: " + u.getPoints_total());
            System.out.println("position: " + u.getPosition());

            System.out.println("==========================================");
        }
    }

    public void position() { //list to db

        List<ClassementPilotes> lcp = findAll();

        for (ClassementPilotes u : lcp) {
            try {

                String req = "update classement_pilotes set position=? where classementP_id=?";

                pst = conn.prepareStatement(req);

                pst.setInt(1, u.getPosition());
                pst.setInt(2, u.getClassementP_id());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }
    
    //select sum(points) from participation join courses on participation.course_id=courses.course_id WHERE pilote_id=5 and saison_year=2022 ;
    
    

/////////////////////////////////////////////////////////
    public int verifajout(int piloteID, int saisonyear) {//verif add clp

        String req = "select count(*) as count from classement_pilotes where pilotes_pilote_id='" + piloteID + "' and saisons_year= '" + saisonyear + "'";
        int x = 0;
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                x = rs.getInt("count");
            }

            if (x == 1) {

                System.out.println("pilote deja existe");
            } else {
                System.out.println("vous pouvez ajouter");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
public  void excel() throws FileNotFoundException, IOException {

     
}

}
