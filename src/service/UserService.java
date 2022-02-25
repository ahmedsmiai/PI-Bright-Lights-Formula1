/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import entite.User;
import entite.Admin;
import entite.Organisateur;
import entite.Spectateur;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
        String req = "insert into user (username,email,password,role) values ('" + u.getUsername() + "','" + u.getEmail() + "','" + u.getPassword() + "','" + u.getRole() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertUserPst(User u) {
        String req = "insert into user (username,email,password,role) values (?,?,?,?)";
        try {
        
            pst = conn.prepareStatement(req);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getRole());
            pst.executeUpdate();
            

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM user WHERE user_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(User u) {
        try {

            String req = "update user set username=?,email=?,password=?, role=? where user_id=?";

            pst = conn.prepareStatement(req);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getRole());
            pst.setInt(5, u.getUser_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public List<User> read() {
        String req = "select * from user order by username ASC";
        List<User> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getTimestamp("create_time"), rs.getString("role")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Userservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public User readById(int id) {
        String req = "select * from user where  user_id="+id;
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
                u.setRole(rs.getString("role"));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    
  public int login(String email, String password) {
      
         String req = "select count(*) as count from user where email='"+email+"' and password= '"+password+"'"; 
           int x = 0;
         try{
           ste =conn.createStatement();
           rs= ste.executeQuery(req);
             while (rs.next()) {
                x=rs.getInt("count");
             }
           
         if (x==1) { 

        System.out.println("Bienvenue!");
         }
    else {
        System.out.println("Adresse mail ou mot de passe invalide");
    }
            
           
        }catch(SQLException ex){
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
  
   public static void envoyerMail(String recepient) throws Exception
   {   
        String email ="formula1brightlights@gmail.com";
        String password ="Formula1bright";
        
       System.out.println("Preparing to send email");
       
     Properties properties = new Properties();
     
     properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable", "true");
       properties.put("mail.smtp.host", "smtp.gmail.com");
       properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.protocols","TLSv1.2");
        
        
       
             
             Session session = Session.getDefaultInstance(properties, new Authenticator() {
               @Override
               protected PasswordAuthentication getPasswordAuthentication(){
                   return new PasswordAuthentication(email,password);
               }
             });      
             Message message = prepareMessage(session, email, recepient);
             Transport.send(message);
             System.out.println("Message sent successfully");
   }
   
   private static Message prepareMessage(Session session, String email, String recepient){
       try{
       Message message = new MimeMessage(session);
       message.setFrom(new InternetAddress(email));
       message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
       message.setSubject("My first email from java app");
       String htmlCode ="<h1>We Love Java</h1> <br/> <h2><b>Next Line </b></h2>";
     message.setContent(htmlCode,"text/html");
     return message;
   }catch (Exception ex){
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
   }
       return null;
   }



 public static String cryptage (String password) {
  try {
   MessageDigest messageDigest = MessageDigest.getInstance("MD5");
   
   messageDigest.update(password.getBytes());
   
   byte[] resultByteArray = messageDigest.digest();
   
   StringBuilder sb = new StringBuilder();
   
   for (byte b : resultByteArray) {
    sb.append(String.format("%02x", b));
   }
   
   return sb.toString();
   
  } catch (NoSuchAlgorithmException e) {
   e.printStackTrace();
  }
  
  return "";
 }
 
  public  void excel() throws FileNotFoundException, IOException {

      String req = "select * from user" ;
      
      try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            
            XSSFWorkbook workbook=new XSSFWorkbook();
                    XSSFSheet sheet=workbook.createSheet("User Data");
		
		XSSFRow row=sheet.createRow(0);
                row.createCell(0).setCellValue("USER_ID");
		row.createCell(1).setCellValue("USERNAME");
		row.createCell(2).setCellValue("EMAIL");
		
		int r=1;
		while(rs.next())
		{
                        int user_id=rs.getInt("USER_ID");
			String username=rs.getString("USERNAME");
			String email=rs.getString("EMAIL");
			
			
			row=sheet.createRow(r++);
			
                        row.createCell(0).setCellValue(user_id);
			row.createCell(1).setCellValue(username);
			row.createCell(2).setCellValue(email);
			
			
		}
		
		
		FileOutputStream fos=new FileOutputStream("C:\\Users\\win10LIGHT\\Desktop\\Formula1\\users.xlsx");
		workbook.write(fos);
		
		workbook.close();
		fos.close();
		conn.close();
		
		System.out.println("Done!!!");
      }catch(SQLException ex){
          Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  


    
    
    private static class Userservice {

        public Userservice() {
        }
    }

}
