/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entite.Saison;
import java.util.Date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import entite.ClassementEquipes;
import entite.ClassementEquipes;
import entite.Equipe;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.Datasource;

/**
 *
 * @author win10LIGHT
 */
public class ClassementEquipesService implements IService<ClassementEquipes> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private ResultSet rss;

    public ClassementEquipesService() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void insert(ClassementEquipes u) {
        String req = "insert into classement_equipes (equipes_equipe_id,saisons_year,points_total,position) values ('" + u.getEquipes_equipe_id() + "','" + u.getSaisons_year() + "','" + u.getPoints_total() + "','" + u.getPosition() + "')";
        try {

            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("equipe_id ou saison invalide");

        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void insertclassemet_equipePst(ClassementEquipes u) {
        String req = "insert into classement_equipes (equipes_equipe_id,saisons_year,points_total) values (?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getEquipes_equipe_id());
            pst.setInt(2, u.getSaisons_year());
            pst.setInt(3, u.getPoints_total());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(ClassementEquipes u) {
        System.out.println("delete equipe service");
        String req = "DELETE FROM classement_equipes WHERE classementE_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getClassementE_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(ClassementEquipes u) {
        try {

            String req = "update classement_equipes set equipes_equipe_id=?,saisons_year=?,points_total=?, position=? where classementE_id=?";

            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getEquipes_equipe_id());
            pst.setInt(2, u.getSaisons_year());
            pst.setInt(3, u.getPoints_total());
            pst.setInt(4, u.getPosition());
            pst.setInt(5, u.getClassementE_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    
    
      public List<ClassementEquipes> upds() { //pos+bd to list
        ClassementEquipes u = new ClassementEquipes();
        List<ClassementEquipes> lcp = new ArrayList<ClassementEquipes>();
        try {
            String req = "select sum(pilotes_pilote_id) from classement_pilotes pilote_name like'%'hne t7ot esm pilote order by saisons_year";//points_total DESC
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            int pos = 1;
            int saison = 0;
            while (rs.next()) {
                if(rs.getInt("saisons_year")!=saison){
                    pos=1;
                }
         saison = rs.getInt("saisons_year");
         
                lcp.add(new ClassementEquipes(rs.getInt("classementE_id"), rs.getInt("equipes_equipe_id"), rs.getInt("saisons_year"), rs.getInt("points_total"), pos));
                pos++;
            }
        } catch (Exception e) {
            lcp = null;
        }
        
        return lcp;

    }

    
    @Override
    public List<ClassementEquipes> read() {
        String req = "select * from classement_equipes";
        List<ClassementEquipes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementEquipes(rs.getInt("classementE_id"), rs.getInt("equipes_equipe_id"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
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
//            String req = "update classement_equipes set points_total= where classementE_id=?";
//String req2 ="select sum(points) from participation join courses on participation.course_id=courses.course_id WHERE equipe_id=? and saison_year=?" ;
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

    ////////////////////////////////////////////////////////////////////////read with equipe name 
public List<ClassementEquipes> read2() {
        String req = "SELECT C.classementE_id , C.saisons_year, C.points_total, C.position, P.nom FROM classement_equipes C LEFT OUTER JOIN equipes P ON C.equipes_equipe_id = P.equipe_id";
        List<ClassementEquipes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementEquipes(rs.getInt("classementE_id"), rs.getString("nom"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    /////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////tri
    public List<ClassementEquipes> triID() {
        String req = "SELECT C.classementE_id , C.saisons_year, C.points_total, C.position, P.nom FROM classement_equipes C LEFT OUTER JOIN equipes P ON C.equipes_equipe_id = P.equipe_id order by classementE_id ";
        List<ClassementEquipes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementEquipes(rs.getInt("classementE_id"), rs.getString("nom"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ClassementEquipes> triSaison() {
        String req = "SELECT C.classementE_id , C.saisons_year, C.points_total, C.position, P.nom FROM classement_equipes C LEFT OUTER JOIN equipes P ON C.equipes_equipe_id = P.equipe_id order by saisons_year, position";
        List<ClassementEquipes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementEquipes(rs.getInt("classementE_id"), rs.getString("nom"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ClassementEquipes> triPilote() {
        String req = "SELECT C.classementE_id , C.saisons_year, C.points_total, C.position, P.nom FROM classement_equipes C LEFT OUTER JOIN equipes P ON C.equipes_equipe_id = P.equipe_id order by equipes_equipe_id ";
        List<ClassementEquipes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementEquipes(rs.getInt("classementE_id"), rs.getString("nom"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ClassementEquipes> triPos() {
        String req = "SELECT C.classementE_id , C.saisons_year, C.points_total, C.position, P.nom FROM classement_equipes C LEFT OUTER JOIN equipes P ON C.equipes_equipe_id = P.equipe_id order by position ";
        List<ClassementEquipes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementEquipes(rs.getInt("classementE_id"), rs.getString("nom"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //////////////////////////////////////////////////////////////////////////
    public List<ClassementEquipes> readjustid() {
        String req = "select * from classement_equipes";

        List<ClassementEquipes> list = new ArrayList<>();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementEquipes(rs.getInt("classementE_id"), rs.getInt("equipes_equipe_id"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
         public List<ClassementEquipes> readjustnom() {
        String req = "SELECT nom from equipes group by nom";

        List<ClassementEquipes> list = new ArrayList<>();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementEquipes(rs.getString("nom")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ClassementEquipes readById(int classementE_id) {
        String req = "select * from classement_equipes where classementE_id=" + classementE_id;
        ClassementEquipes u = new ClassementEquipes();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                u.setClassementE_id(rs.getInt("classementE_id"));
                u.setEquipes_equipe_id(rs.getInt("equipes_equipe_id"));
                u.setSaisons_year(rs.getInt("saisons_year"));
                u.setPoints_total(rs.getInt("points_total"));
                u.setPosition(rs.getInt("position"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(SaisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
         public ClassementEquipes convert(String nom) {
        
          //System.out.println("req ="+req);
       ClassementEquipes u = new ClassementEquipes();

        try {
            String req = "SELECT equipe_id  from equipes where nom='"+nom+"'";
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                System.out.println("convert ligne 267"+rs.getInt("equipe_id"));
                u.setEquipes_equipe_id(rs.getInt("equipe_id"));
  

            }
        } catch (SQLException ex) {
            Logger.getLogger(SaisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
          System.out.println("id="+u.getEquipes_equipe_id());
        return u;
    }

/////////////////////////////////////////////////////////////////position fcts    
    public List<ClassementEquipes> findAll() { //pos+bd to list
        ClassementEquipes u = new ClassementEquipes();
        List<ClassementEquipes> lcp = new ArrayList<ClassementEquipes>();
        try {
            String req = "select * from classement_equipes order by saisons_year, points_total DESC";//points_total DESC
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            int pos = 1;
            int saison = 0;
            while (rs.next()) {
                if(rs.getInt("saisons_year")!=saison){
                    pos=1;
                }
         saison = rs.getInt("saisons_year");
         
                lcp.add(new ClassementEquipes(rs.getInt("classementE_id"), rs.getInt("equipes_equipe_id"), rs.getInt("saisons_year"), rs.getInt("points_total"), pos));
                pos++;
            }
        } catch (Exception e) {
            lcp = null;
        }
        
        return lcp;

    }
    
    
  



 
    


    public void afficher() {

        List<ClassementEquipes> lcp = findAll();
        for (ClassementEquipes u : lcp) {
            System.out.println("Id: " + u.getClassementE_id());
            //	System.out.println("saison: " + u.getSaisons_year());
            //System.out.println("equipeID: " + u.getPilotes_equipe_id());
            System.out.println("pts: " + u.getPoints_total());
            System.out.println("position: " + u.getPosition());

            System.out.println("==========================================");
        }
    }

    public void position() { //list to db

        List<ClassementEquipes> lcp = findAll();

        for (ClassementEquipes u : lcp) {
            try {

                String req = "update classement_equipes set position=? where classementE_id=?";

                pst = conn.prepareStatement(req);

                pst.setInt(1, u.getPosition());
                pst.setInt(2, u.getClassementE_id());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }
    
    //select sum(points) from participation join courses on participation.course_id=courses.course_id WHERE equipe_id=5 and saison_year=2022 ;
    
    

/////////////////////////////////////////////////////////
    public int verifajout(int equipeID, int saisonyear) {//verif add clp

        String req = "select count(*) as count from classement_equipes where equipes_equipe_id='" + equipeID + "' and saisons_year= '" + saisonyear + "'";
        int x = 0;
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                x = rs.getInt("count");
            }

            if (x == 1) {

                System.out.println("equipe deja existe");
            } else {
                System.out.println("vous pouvez ajouter");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
public List<ClassementEquipes> readbys(int saisons_year) {
        String req = "SELECT C.classementE_id, C.saisons_year, C.points_total, C.position, P.nom FROM classement_equipes C LEFT OUTER JOIN equipes P ON C.equipes_equipe_id = P.equipe_id where saisons_year=" + saisons_year ;
       List<ClassementEquipes> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new ClassementEquipes(rs.getInt("classementE_id"), rs.getString("nom"), rs.getInt("saisons_year"), rs.getInt("points_total"), rs.getInt("position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassementPilotesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


public void GeneratePdf() throws DocumentException{
        List<ClassementEquipes> equipe=triID();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String strDate = formatter.format(date);  
        
        String path="C:\\Users\\qwiw\\Desktop\\pi1\\GestionCS\\src\\view\\";
        //double x=Math.random(100,999);
        double x=Math.random() * ( 100 - 999 );
        String xchar=Double.toString(x);
        String name="Equipe"+xchar;
        Document doc= new Document();
        try {
            //Tableau 1
            PdfWriter.getInstance(doc, new FileOutputStream(path+name+".pdf"));
            doc.open();
            PdfPTable col=new PdfPTable(2);
            PdfPCell col1=new PdfPCell(new Paragraph(""));
            col1.setHorizontalAlignment(Element.HEADER);
            col1.setBorder(0);
            col1.setFixedHeight(70);
            col.addCell(col1);
            
            
            PdfPCell col2=new PdfPCell(new Paragraph(strDate));
            col2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            col2.setFixedHeight(70);
            col2.setBorder(0);
            col.addCell(col2);
            
            doc.add(col);
            //end tableau 1
            
            
            //Tableau 2
            PdfPTable tab2=new PdfPTable(1);
            PdfPCell t2=new PdfPCell(new Paragraph("FormulaOne",FontFactory.getFont(FontFactory.COURIER_BOLD)));
            t2.setHorizontalAlignment(Element.ALIGN_CENTER);
            t2.setBorder(0);
            t2.setFixedHeight(50);
            tab2.addCell(t2);
            
            
            
            
            doc.add(tab2);
            //end tableau2
            
            
            //Tableau 3
            PdfPTable tab=new PdfPTable(5);
            //add title
            PdfPCell cell=new PdfPCell(new Paragraph("Liste des équipe inscrits"));
            cell.setColspan(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
     //       cell.setBackgroundColor(Color.RED);
            cell.setFixedHeight(25);
            tab.addCell(cell);
            //addheader
            PdfPCell id=new PdfPCell(new Paragraph("ID"));
            id.setHorizontalAlignment(Element.ALIGN_CENTER);
    //        id.setBackgroundColor(Color.LIGHT_GRAY);
            id.setFixedHeight(20);
            tab.addCell(id);
            
            PdfPCell nom=new PdfPCell(new Paragraph("Nom"));
            nom.setHorizontalAlignment(Element.ALIGN_CENTER);
       //     nom.setBackgroundColor(Color.LIGHT_GRAY);
            nom.setFixedHeight(20);
            tab.addCell(nom);
            
            
            PdfPCell saison=new PdfPCell(new Paragraph("SAISON"));
            saison.setHorizontalAlignment(Element.ALIGN_CENTER);
         //   saison.setBackgroundColor(Color.LIGHT_GRAY);
            saison.setFixedHeight(20);
            tab.addCell(saison);
            
            PdfPCell pts=new PdfPCell(new Paragraph("POINTS"));
            pts.setHorizontalAlignment(Element.ALIGN_CENTER);
    //        pts.setBackgroundColor(Color.LIGHT_GRAY);
            pts.setFixedHeight(20);
            tab.addCell(pts);
            
            
            PdfPCell position=new PdfPCell(new Paragraph("POSITION"));
            position.setHorizontalAlignment(Element.ALIGN_CENTER);
        //    position.setBackgroundColor(Color.RED);
            position.setFixedHeight(20);
            tab.addCell(position);
          
             
            for(ClassementEquipes e:equipe=triID()){
                String idChar=Integer.toString(e.getClassementE_id());
                
                PdfPCell idc=new PdfPCell(new Paragraph(idChar));
                id.setHorizontalAlignment(Element.ALIGN_CENTER);
                id.setFixedHeight(20);
                tab.addCell(idc);

                PdfPCell nomc=new PdfPCell(new Paragraph(e.getNom()));
                nom.setHorizontalAlignment(Element.ALIGN_CENTER);
                nom.setFixedHeight(20);
                tab.addCell(nomc);


                PdfPCell saisonc=new PdfPCell(new Paragraph(e.getSaisons_year()));
                saison.setHorizontalAlignment(Element.ALIGN_CENTER);
                saison.setFixedHeight(20);
                tab.addCell(saisonc);

                PdfPCell ptsc=new PdfPCell(new Paragraph(e.getPoints_total()));
                pts.setHorizontalAlignment(Element.ALIGN_CENTER);
                pts.setFixedHeight(20);
                tab.addCell(ptsc);
                
                PdfPCell positionc=new PdfPCell(new Paragraph(e.getPosition()));
                position.setHorizontalAlignment(Element.ALIGN_CENTER);
                position.setFixedHeight(20);
                tab.addCell(positionc);

            }
            doc.add(tab);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClassementEquipesService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ClassementEquipesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        doc.close();
        
    }

}

  
    

   


