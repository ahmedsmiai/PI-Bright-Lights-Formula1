/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

  

import java.io.File;
import java.util.*;
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
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import static java.nio.file.Files.list;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
   private ResultSet rc;
    
    


    public UserService() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void insert(User u) {
        String req = "insert into user (username,email,tel,password,role,image_name,status) values ('" + u.getUsername() + "','" + u.getEmail() + "','" + u.getTel() + "','" + u.getPassword() + "','" + u.getRole() + "','" + u.getImage_name() + "','" + u.getStatus() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertUserPst(User u) {
        String req = "insert into user (username,email,tel,password,role,image_name,status) values (?,?,?,?,?,?,?)";
        try {
        
            pst = conn.prepareStatement(req);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getTel());
            pst.setString(4, u.getPassword());
            pst.setString(5, u.getRole());
            pst.setString(6, u.getImage_name());
            pst.setInt(7, u.getStatus());
            pst.executeUpdate();
             System.out.println("ajoute"+u.getImage_name());

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

    
    public void updateRole(User u,String role) {
        try {

            String req = "update user set role=? where user_id=?";
            pst = conn.prepareStatement(req);
            pst.setString(1, role);
            pst.setInt(2, u.getUser_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    
       public void updatePassword(User u,String password) {
        try {
            
            String req = "update user set password=? where user_id=?";
            pst = conn.prepareStatement(req);
            pst.setString(1, password);
            pst.setInt(2, u.getUser_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
       public void updateProfil(User u){
           try {
            String req = "update user set username=?,tel=?,password=?,image_name=? where user_id=?";
            pst = conn.prepareStatement(req);
            pst.setString(1,u.getUsername());
            pst.setString(2, u.getTel());
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getImage_name());
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
                list.add(new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("email"),rs.getString("tel"), rs.getString("password"), rs.getTimestamp("create_time"), rs.getString("role"), rs.getString("image_name"), rs.getInt("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
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
                String path="/view/images/User/"+rs.getString("image_name");
                ImageView img = new ImageView(new Image(this.getClass().getResourceAsStream(path)));
                img.setFitHeight(50);
                img.setFitWidth(50);
                u.setUser_id(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setCreate_time(rs.getTimestamp("create_time"));
                u.setRole(rs.getString("role"));
                u.setImage_name(rs.getString("image_name"));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
 
   
  public User login(String email, String password) throws SQLException {
         String req = "select * from user where email='"+email+"' and password= '"+password+"'"; 
         try{
           ste =conn.createStatement();
           rs= ste.executeQuery(req);
           
             if(rs.next()){
                 System.out.println("fel rs t5al");
                  String query = "update user set status = 1 where user_id=?";
                String path="/view/images/User/"+rs.getString("image_name");
                ImageView img = new ImageView(new Image(this.getClass().getResourceAsStream(path)));
                img.setFitHeight(50);
                img.setFitWidth(50);
               
                   User u = new User (rs.getInt("user_id"), rs.getString("username"), rs.getString("email"),rs.getString("tel"), rs.getString("password"), rs.getTimestamp("create_time"), rs.getString("role"), rs.getString("image_name"), rs.getInt("status"),img);
                  try{
                      pst = conn.prepareStatement(query);
                        pst.setInt(1, u.getUser_id());  
                       pst.executeUpdate();
                
                  }catch(SQLException ex){
                      Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  System.out.println("fel rs l user = "+u.toString());
                 return u;    
             }
             else {
                 System.out.println("fel else mta3 login service");
                 return null;
             }
                   }catch(SQLException ex){
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
  }
  
  
  
  
  
   public void logOut(User u) throws SQLException{
       System.out.println("logoutService"+u.toString());
                String req = "update user set status=0 where user_id=?"; 
try{
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getUser_id());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

        }
       
   }
   
   
   
   
   
    public List<User> SearchByTel(String tel){
        String req="SELECT * FROM user WHERE tel LIKE '%"+tel+"%'";
        List<User> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                String path="/View/images/User/"+rs.getString("image_name");
                ImageView img = new ImageView(new Image(this.getClass().getResourceAsStream(path)));
                img.setFitHeight(50);
                img.setFitWidth(50);
               list.add(new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("email"),rs.getString("tel"), rs.getString("password"), rs.getTimestamp("create_time"), rs.getString("role"), rs.getString("image_name"), rs.getInt("status"),img));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
   

  public List<User> readWithImageView() {
      
        String req="select * from user";
        List<User> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            
            while(rs.next()){
                String path="/View/images/User/"+rs.getString("image_name");
                ImageView img = new ImageView(new Image(this.getClass().getResourceAsStream(path)));
                img.setFitHeight(50);
                img.setFitWidth(50);
                list.add(new User (rs.getInt("user_id"),rs.getString("username"),rs.getString("email"),rs.getString("tel"), rs.getString("password"),rs.getTimestamp("create_time"),rs.getString("role"),rs.getString("image_name"),rs.getInt("status"),img));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
  
   public void envoyerMail(String recepient) throws Exception
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
       message.setSubject("Bienvenue chez Fomrula1, Suivez-nous dans notre");
       String htmlCode ="<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
"<head>\n" +
"<title></title>\n" +
"<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
"<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"/>\n" +
"<!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->\n" +
"<style>\n" +
"		* {\n" +
"			box-sizing: border-box;\n" +
"		}\n" +
"\n" +
"		body {\n" +
"			margin: 0;\n" +
"			padding: 0;\n" +
"		}\n" +
"\n" +
"		a[x-apple-data-detectors] {\n" +
"			color: inherit !important;\n" +
"			text-decoration: inherit !important;\n" +
"		}\n" +
"\n" +
"		#MessageViewBody a {\n" +
"			color: inherit;\n" +
"			text-decoration: none;\n" +
"		}\n" +
"\n" +
"		p {\n" +
"			line-height: inherit\n" +
"		}\n" +
"\n" +
"		@media (max-width:520px) {\n" +
"			.icons-inner {\n" +
"				text-align: center;\n" +
"			}\n" +
"\n" +
"			.icons-inner td {\n" +
"				margin: 0 auto;\n" +
"			}\n" +
"\n" +
"			.row-content {\n" +
"				width: 100% !important;\n" +
"			}\n" +
"\n" +
"			.column .border {\n" +
"				display: none;\n" +
"			}\n" +
"\n" +
"			.stack .column {\n" +
"				width: 100%;\n" +
"				display: block;\n" +
"			}\n" +
"		}\n" +
"	</style>\n" +
"</head>\n" +
"<body style=\"background-color: #FFFFFF; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #FFFFFF;\" width=\"100%\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"heading_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td style=\"width:100%;text-align:center;\">\n" +
"<h1 style=\"margin: 0; color: #555555; font-size: 23px; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; line-height: 120%; text-align: center; direction: ltr; font-weight: 700; letter-spacing: normal; margin-top: 0; margin-bottom: 0;\">Bienvenue chez Formula1</h1>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
"<tr>\n" +
"<td>\n" +
"<div style=\"font-family: sans-serif\">\n" +
"<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n" +
"<p style=\"margin: 0; font-size: 14px;\">Votre compte est bien enregistré,</p>\n" +
"<p style=\"margin: 0; font-size: 14px;\">Merci de vous être inscrit $username.</p>\n" +
"</div>\n" +
"</div>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td>\n" +
"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n" +
"<tbody>\n" +
"<tr>\n" +
"<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"icons_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td style=\"vertical-align: middle; color: #9d9d9d; font-family: inherit; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n" +
"<table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
"<tr>\n" +
"<td style=\"vertical-align: middle; text-align: center;\">\n" +
"<!--[if vml]><table align=\"left\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->\n" +
"<!--[if !vml]><!-->\n" +
"<table cellpadding=\"0\" cellspacing=\"0\" class=\"icons-inner\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;\">\n" +
"<!--<![endif]-->\n" +
"<tr>\n" +
"<td style=\"vertical-align: middle; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 6px;\"><a href=\"https://www.designedwithbee.com/\" style=\"text-decoration: none;\" target=\"_blank\"><img align=\"center\" alt=\"Designed with BEE\" class=\"icon\" height=\"32\" src=\"images/bee.png\" style=\"display: block; height: auto; margin: 0 auto; border: 0;\" width=\"34\"/></a></td>\n" +
"<td style=\"font-family: Arial, Helvetica Neue, Helvetica, sans-serif; font-size: 15px; color: #9d9d9d; vertical-align: middle; letter-spacing: undefined; text-align: center;\"><a href=\"https://www.designedwithbee.com/\" style=\"color: #9d9d9d; text-decoration: none;\" target=\"_blank\">Designed with BEE</a></td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table>\n" +
"</td>\n" +
"</tr>\n" +
"</tbody>\n" +
"</table><!-- End -->\n" +
"</body>\n" +
"</html>";
     message.setContent(htmlCode,"text/html");
     return message;
   }catch (Exception ex){
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
   }
       return null;
   } 
   
   
   
  public  void excel() throws FileNotFoundException, IOException {
      
      String req = "select * from user" ;
  
      try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            
            XSSFWorkbook workbook=new XSSFWorkbook();
            XSSFSheet sheet=workbook.createSheet("Users");
		
		XSSFRow row=sheet.createRow(0);
                row.createCell(0).setCellValue("USER_ID");
		row.createCell(1).setCellValue("USERNAME");
		row.createCell(2).setCellValue("EMAIL");
                row.createCell(3).setCellValue("ROLE");
                
		
		int r=1;
		while(rs.next())
		{
                        int user_id=rs.getInt("USER_ID");
			String username=rs.getString("USERNAME");
			String email=rs.getString("EMAIL");
                        String role=rs.getString("Role");
			
			
			row=sheet.createRow(r++);
			
                        row.createCell(0).setCellValue(user_id);
			row.createCell(1).setCellValue(username);
			row.createCell(2).setCellValue(email);
                        row.createCell(3).setCellValue(role);
			
			
		}
		
		
		FileOutputStream fos=new FileOutputStream("C:\\Users\\nechi\\Desktop\\ProjetF1\\FormulaOne\\src\\view\\Users.xlsx");
		workbook.write(fos);
		
		workbook.close();
		fos.close();
	
		
		System.out.println("Done!!!");
      }catch(SQLException ex){
          Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

    @Override
    public void update(User t) {
        System.out.println("ssssssssssss");
    }
  
  


    
    
//    private static class Userservice {
//
//        public Userservice() {
//        }
//    }
    
    //get user connected 
    public User getUserConnected(){
        User user=new User();
        try {
            String req="select * from user where status=1";
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            
            if(rs.next()){
                String path="/View/images/User/"+rs.getString("image_name");
                ImageView img = new ImageView(new Image(this.getClass().getResourceAsStream(path)));
                img.setFitHeight(50);
                img.setFitWidth(50);
                User u=new User (rs.getInt("user_id"),rs.getString("username"),rs.getString("email"),rs.getString("tel"), rs.getString("password"),rs.getTimestamp("create_time"),rs.getString("role"),rs.getString("image_name"),rs.getInt("status"),img);
                user=u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public List<User> readOrganisateur() {
        String req = "select * from user where  role='Organisateur'" ;
         
        List<User> list = new ArrayList<>();

       try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getTimestamp("create_time"), rs.getString("role")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
