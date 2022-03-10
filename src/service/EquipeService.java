/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import entite.Equipe;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Datasource;
/**
 *
 * @author nechi
 */
public class EquipeService implements IService<Equipe>{
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public EquipeService() {
        conn = Datasource.getInstance().getCnx();
    }
    
    @Override
    public void insert(Equipe e) {
        String req = "insert into equipes (nom,email,logo,voiture,pays_origine) values ('" + e.getNom() + "','" + e.getMail() + "','" + e.getLogo() + "','" + e.getVoiture() + "','"+ e.getPays_origin()+"')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        Equipe eq=readById(id);
        //Supprimer les membres avant de supprimer l'équipe
       // MembreService ms=new MembreService();
        
        
        
        //Supprimer l'équipe
        if(eq.getEquipe_id() != 0){
            String req = "DELETE FROM equipes WHERE equipe_id=?";
            try {
                pst = conn.prepareStatement(req);
                pst.setInt(1, id);
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("L'équipe est supprimer avec succés");
        }else{
            System.out.println("L'équipe n'a pas trouver dans la base de données");
        }
    }

    @Override
    public void update(Equipe e) {
        Equipe eq=readById(e.getEquipe_id());
        if(eq.equals(e)){
            String req = "UPDATE equipes SET nom=? ,logo=?, voiture =? , pays_origine = ? WHERE equipe_id = ?";
            try {
                pst = conn.prepareStatement(req);
                pst.setString(1, e.getNom());
                pst.setString(2, e.getLogo());
                pst.setString(3, e.getVoiture());
                pst.setString(4, e.getPays_origin());
                pst.setInt(5, e.getEquipe_id());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("L'équipe est modifier avec succés");
        }else{
            System.out.println("L'équipe n'a pas trouver dans la base de données");
        }
        
    }

    @Override
    public List<Equipe> read() {
        String req="select * from equipes";
        List<Equipe> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Equipe(rs.getInt("equipe_id"),rs.getString("nom"),rs.getString("email"),rs.getString("logo"), rs.getString("voiture"),rs.getString("pays_origine")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public List<Equipe> readWithImageView() {
        String req="select * from equipes";
        List<Equipe> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            
            
            while(rs.next()){
                String path="/view/images/"+rs.getString("logo");
                ImageView img = new ImageView(new Image(this.getClass().getResourceAsStream(path)));
                img.setFitHeight(50);
                img.setFitWidth(50);
                list.add(new Equipe(rs.getInt("equipe_id"),rs.getString("nom"),rs.getString("logo"),img, rs.getString("voiture"),rs.getString("pays_origine")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Equipe readById(int id) {
        String req="select * from equipes where equipe_id = '"+ id+"'";
        Equipe equipe=new Equipe();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
               Equipe e= new Equipe(rs.getInt("equipe_id"),rs.getString("nom"),rs.getString("email"),rs.getString("logo"), rs.getString("voiture"),rs.getString("pays_origine"));
               equipe=e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equipe;
    }
    
    public void GeneratePdf(String name){
        List<Equipe> equipe=read();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String strDate = formatter.format(date);  
        
        String path="C:\\Users\\nechi\\Desktop\\ProjetF1\\FormulaOne\\src\\documents\\";
        //double x=Math.random(100,999);
        
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
            PdfPTable tab=new PdfPTable(4);
            //add title
            PdfPCell cell=new PdfPCell(new Paragraph("Liste des équipe inscrits"));
            cell.setColspan(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.RED);
            cell.setFixedHeight(25);
            tab.addCell(cell);
            //addheader
            PdfPCell id=new PdfPCell(new Paragraph("ID"));
            id.setHorizontalAlignment(Element.ALIGN_CENTER);
            id.setBackgroundColor(Color.LIGHT_GRAY);
            id.setFixedHeight(20);
            tab.addCell(id);
            
            PdfPCell nom=new PdfPCell(new Paragraph("Nom"));
            nom.setHorizontalAlignment(Element.ALIGN_CENTER);
            nom.setBackgroundColor(Color.LIGHT_GRAY);
            nom.setFixedHeight(20);
            tab.addCell(nom);
            
           
            
            PdfPCell voiture=new PdfPCell(new Paragraph("Voiture"));
            voiture.setHorizontalAlignment(Element.ALIGN_CENTER);
            voiture.setBackgroundColor(Color.LIGHT_GRAY);
            voiture.setFixedHeight(20);
            tab.addCell(voiture);
            
            PdfPCell pays=new PdfPCell(new Paragraph("Pays d'origine"));
            pays.setHorizontalAlignment(Element.ALIGN_CENTER);
            pays.setBackgroundColor(Color.LIGHT_GRAY);
            pays.setFixedHeight(20);
            tab.addCell(pays);
            
            for(Equipe e:equipe){
                String idChar=Integer.toString(e.getEquipe_id());
                
                PdfPCell idd=new PdfPCell(new Paragraph(idChar));
                id.setHorizontalAlignment(Element.ALIGN_CENTER);
                id.setFixedHeight(20);
                tab.addCell(idd);

                PdfPCell nomm=new PdfPCell(new Paragraph(e.getNom()));
                nom.setHorizontalAlignment(Element.ALIGN_CENTER);
                nom.setFixedHeight(20);
                tab.addCell(nomm);

                PdfPCell voituree=new PdfPCell(new Paragraph(e.getVoiture()));
                voiture.setHorizontalAlignment(Element.ALIGN_CENTER);
                voiture.setFixedHeight(20);
                tab.addCell(voituree);

                PdfPCell payss=new PdfPCell(new Paragraph(e.getPays_origin()));
                pays.setHorizontalAlignment(Element.ALIGN_CENTER);
                pays.setFixedHeight(20);
                tab.addCell(payss);
            }
            doc.add(tab);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        doc.close();
        
    }
    
    
    public List<Equipe> SearchByName(String name){
        String req="SELECT * FROM `equipes` WHERE nom LIKE '%"+name+"%'";
        List<Equipe> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                String path="/view/images/"+rs.getString("logo");
                ImageView img = new ImageView(new Image(this.getClass().getResourceAsStream(path)));
                img.setFitHeight(50);
                img.setFitWidth(50);
                list.add(new Equipe(rs.getInt("equipe_id"),rs.getString("nom"),rs.getString("logo"),img, rs.getString("voiture"),rs.getString("pays_origine")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public List<Equipe> filtrer(String clef){
        String req="";
        if(clef.equals("Par pays")){
            req="SELECT * FROM `equipes` ORDER BY pays_origine;";
        }
        
        List<Equipe> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                String path="/view/images/"+rs.getString("logo");
                ImageView img = new ImageView(new Image(this.getClass().getResourceAsStream(path)));
                img.setFitHeight(50);
                img.setFitWidth(50);
                list.add(new Equipe(rs.getInt("equipe_id"),rs.getString("nom"),rs.getString("logo"),img, rs.getString("voiture"),rs.getString("pays_origine")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
    
    
    
    
    
    
}
