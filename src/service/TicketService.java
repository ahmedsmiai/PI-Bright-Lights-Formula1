/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import controller.QRcodeController;
import entite.Tickets;
import entite.course;
import java.io.File;
import java.io.IOException;
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
public class TicketService implements IService<Tickets>{
    
    private final Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public TicketService() {
        conn = Datasource.getInstance().getCnx();
    }
//    public  void envoyerMail(String recepient) throws Exception
//   {
//        String email ="formulamailone@gmail.com";
//        String password ="formulaone1_";
//
//       System.out.println("Preparing to send email");
//
//     Properties properties = new Properties();
//
//     properties.put("mail.smtp.auth", "true");
//     properties.put("mail.smtp.starttls.enable", "true");
//     properties.put("mail.smtp.host", "smtp.gmail.com");
//     properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//     properties.put("mail.smtp.port", "587");
//     properties.put("mail.smtp.ssl.protocols","TLSv1.2");
//
//
//
//
//             Session session = Session.getDefaultInstance(properties, new Authenticator() {
//               @Override
//               protected PasswordAuthentication getPasswordAuthentication(){
//                   return new PasswordAuthentication(email,password);
//               }
//             });
//             Message message = prepareMessage(session, email, recepient);
//             Transport.send(message);
//             System.out.println("Message sent successfully");
//   }
//
//   private static Message prepareMessage(Session session, String email, String recepient){
//       try{
//       Message message = new MimeMessage(session);
//       message.setFrom(new InternetAddress(email));
//       message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
//       message.setSubject("paiement de ticket du course Formula one");
//        String htmlCode =" <form method=\"post\" data-centralpay=\"form\" action=\"https://example.centralpay.net/v2/process/display\">"
//                + "<div class=\"row mx-0 text-center\">\n" +
//"      <div class=\"col-4 px-0\">\n" +
//"         <div class=\"card card-offer\">\n" +
//"            <div class=\"card-header\">\n" +
//"               <label for=\"amount0\"><span data-display=\"offer-name\">offer</span></label>\n" +
//"            </div>\n" +
//"            <div class=\"card-body\">\n" +
//"               <p class=\"text-price\"><span data-display=\"amount\"></span><span data-display=\"currency-symbol\">-</span></p>\n" +
//"               <input type=\"radio\" name=\"offer\" id=\"amount0\" data-select=\"amount\" required />\n" +
//"            </div>\n" +
//"         </div>\n" +
//"      </div>\n" +
//"      <div class=\"col-4 px-0\">\n" +
//"         <div class=\"card card-offer\">\n" +
//"            <div class=\"card-header\">\n" +
//"               <label for=\"amount1\"><span data-display=\"offer-name\">offer</span></label>\n" +
//"            </div>\n" +
//"            <div class=\"card-body\">\n" +
//"               <p class=\"text-price\"><span data-display=\"amount\"></span><span data-display=\"currency-symbol\">-</span></p>\n" +
//"               <input type=\"radio\" name=\"offer\" id=\"amount1\" data-select=\"amount\" />\n" +
//"            </div>\n" +
//"         </div>\n" +
//"      </div>\n" +
//"      <div class=\"col-4 px-0\">\n" +
//"         <div class=\"card card-offer\">\n" +
//"            <div class=\"card-header\">\n" +
//"               <label for=\"amount2\"><span data-display=\"offer-name\">offer</span></label>\n" +
//"            </div>\n" +
//"            <div class=\"card-body\">\n" +
//"               <p class=\"text-price\"><span data-display=\"amount\"></span><span data-display=\"currency-symbol\">-</span></p>\n" +
//"               <input type=\"radio\" name=\"offer\" id=\"amount2\" data-select=\"amount\" />\n" +
//"            </div>\n" +
//"         </div>\n" +
//"      </div>\n" +
//"   </div>\n" +
//"   <div data-centralpay=\"errors\" class=\"custom-form\"></div>\n" +
//"   <p data-form=\"main-description\" class=\"sr-only\">Card data</p>\n" +
//"   <div class=\"form-row mt-3\" data-toggle=\"popover-example-data\">\n" +
//"      <div class=\"form-group col-6\">\n" +
//"         <div class=\"has-feedback input-group\">\n" +
//"            <div class=\"input-group-prepend\">\n" +
//"               <div class=\"input-group-text\"><div class=\"ico ico-user\"></div></div>\n" +
//"            </div>\n" +
//"            <input data-centralpay=\"firstName\" type=\"text\" name=\"order[firstName]\" autocomplete=\"off\"\n" +
//"                  data-form=\"card-user\" class=\"form-control\" placeholder=\"First name\" required=\"required\" />\n" +
//"         </div>\n" +
//"      </div>\n" +
//"      <div class=\"form-group col-6\">\n" +
//"         <div class=\"has-feedback input-group\">\n" +
//"            <div class=\"input-group-prepend\">\n" +
//"               <div class=\"input-group-text\"><div class=\"ico ico-user\"></div></div>\n" +
//"            </div>\n" +
//"            <input data-centralpay=\"lastName\" type=\"text\" name=\"order[lastName]\" autocomplete=\"off\"\n" +
//"                  data-form=\"card-user\" class=\"form-control\" placeholder=\"Last name\" required=\"required\" />\n" +
//"         </div>\n" +
//"      </div>\n" +
//"   </div>\n" +
//"   <div data-form=\"card-validation\">\n" +
//"      <div class=\"form-row\">\n" +
//"         <div class=\"form-group col-12\">\n" +
//"            <div class=\"has-feedback input-group\">\n" +
//"               <div class=\"input-group-prepend\">\n" +
//"                  <div class=\"input-group-text\"><div class=\"ico ico-envelope\"></div></div>\n" +
//"               </div>\n" +
//"               <input data-centralpay=\"card[holderEmail]\" type=\"email\" name=\"card[cardholderEmail]\"\n" +
//"                     autocomplete=\"off\" class=\"form-control\" data-form=\"card-user\"\n" +
//"                     placeholder=\"E-mail\" required=\"required\" />\n" +
//"            </div>\n" +
//"         </div>\n" +
//"      </div>\n" +
//"      <div class=\"form-row\">\n" +
//"         <div class=\"col-12\">\n" +
//"            <div data-form=\"remember-div\" class=\"remember remember-option d-flex align-items-center mb-3\">\n" +
//"               <span class=\"remember-checkbox form-check\">\n" +
//"                  <input class=\"form-check-input\" data-form=\"remember-select\" id=\"remember\" type=\"checkbox\"\n" +
//"                        name=\"store-card\"/>\n" +
//"                  <label class=\"form-check-label\" for=\"remember\">remember me</label>\n" +
//"               </span>\n" +
//"               <span class=\"card-stored\">Use your card</span>\n" +
//"               <a class=\"help-icon\" data-toggle=\"popover-info\">\n" +
//"                  <img src=\"/v2/src/img/question-mark.png\" alt=\"\">\n" +
//"               </a>\n" +
//"               <div id=\"popover-card-info\" class=\"d-none\">\n" +
//"                  <span class=\"np-popover-style\">Your card data is stored securely. <a href=\"#\">More information</a></span>\n" +
//"               </div>\n" +
//"            </div>\n" +
//"         </div>\n" +
//"      </div>\n" +
//"      <div class=\"form-row d-none\">\n" +
//"         <div class=\"form-group col-12\">\n" +
//"            <select list=\"userCards\" data-form=\"card-list\" type=\"text\" class=\"form-control input-large\">\n" +
//"               <option> -- pay with a new credit card -- </option>\n" +
//"            </select>\n" +
//"         </div>\n" +
//"      </div>\n" +
//"      <div id=\"card-option\" class=\"new-card-ok\">\n" +
//"         <div class=\"form-row\">\n" +
//"            <div class=\"form-group col-12 new-card\">\n" +
//"               <div class=\"has-feedback input-group\">\n" +
//"                  <div class=\"input-group-prepend\">\n" +
//"                     <div class=\"input-group-text\"><div class=\"ico ico-cb\"></div></div>\n" +
//"                  </div>\n" +
//"                  <input data-centralpay=\"card[number]\" type=\"tel\" pattern=\"([0-9 ]{14,20})?\" autocomplete=\"off\"\n" +
//"                        class=\"form-control form-card-data form-card-number\" placeholder=\"Credit card number\"\n" +
//"                        maxlength=\"20\" data-form=\"card-data\" />\n" +
//"               </div>\n" +
//"            </div>\n" +
//"         </div>\n" +
//"         <div class=\"form-row align-items-center\">\n" +
//"            <div class=\"form-group col-8 new-card\">\n" +
//"               <div class=\"has-feedback input-group\">\n" +
//"                  <div class=\"input-group-prepend\">\n" +
//"                     <div class=\"input-group-text\"><div class=\"ico ico-calendar\"></div></div>\n" +
//"                  </div>\n" +
//"                  <input data-centralpay=\"card[expirationMonth]\" type=\"tel\" pattern=\"[0-9]{1,2}\"\n" +
//"                        maxlength=\"2\" autocomplete=\"off\" class=\"form-control form-card-data\" placeholder=\"MM\"\n" +
//"                        data-form=\"card-data\" />\n" +
//"                  <input data-centralpay=\"card[expirationYear]\" type=\"tel\" max=\"9999\" inputmode=\"tel\"\n" +
//"                        pattern=\"[0-9]{4}\" autocomplete=\"off\" class=\"form-control form-card-data\"\n" +
//"                        data-form=\"card-data\" placeholder=\"YYYY\" />\n" +
//"               </div>\n" +
//"            </div>\n" +
//"            <div class=\"form-group col-8 stored-card use-cvv text-right\">Select card and enter CVV</div>\n" +
//"            <div class=\"form-group col-4 use-cvv\">\n" +
//"               <div class=\"has-feedback input-group\">\n" +
//"                  <div class=\"input-group-prepend\">\n" +
//"                     <div class=\"input-group-text\"><div class=\"ico ico-lock\"></div></div>\n" +
//"                  </div>\n" +
//"                  <input data-centralpay=\"card[cvc]\" type=\"tel\" pattern=\"[0-9]{3,4}\" maxlength=\"4\"\n" +
//"                        autocomplete=\"off\" class=\"form-control form-card-data\" placeholder=\"CVV2\"\n" +
//"                        name=\"customerValidationValue\" />\n" +
//"               </div>\n" +
//"            </div>\n" +
//"         </div>\n" +
//"      </div>\n" +
//"   </div>\n" +
//"   <div class=\"text-center mt-2\">\n" +
//"      <button class=\"btn btn-cpay\" type=\"submit\" value=\"submit\" data-form=\"submit\" data-toggle=\"popover\"\n" +
//"            data-placement=\"bottom\" data-trigger=\"hover\">\n" +
//"         <div class=\"np-button-content\">\n" +
//"            <span data-display=\"submit-value\">Pay</span>\n" +
//"            <div class=\"np-loader\"></div>\n" +
//"         </div>\n" +
//"      </button>\n" +
//"   </div>\n" +
//"</form>";
//     message.setContent(htmlCode,"text/html");
//     return message;
//   }catch (Exception ex){
//        Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
//   }
//       return null;
//   }

    @Override
    public void insert(Tickets c) {
        String req;
       
        try {
             req = " insert into tickets (course_id, user_id ,type) values (?,?,?)";
                  pst = conn.prepareStatement(req);
                  pst.setObject(1,c.getCourse().getCourse_id());
                  pst.setObject(2, c.getUser_id().getUser_id());
                  pst.setString(3,c.getType());
                  pst.executeUpdate();
                  
                  

        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }

    

    @Override
    public void update(Tickets c) {
       try {

            String req = "update tickets set type=?  where tickets_id =?";
            pst = conn.prepareStatement(req);
            pst.setString(1, c.getType());
            pst.setInt(2, c.getTickets_id());
            pst.executeUpdate();
           pst.close();
           

        } catch (SQLException ex) { Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void update(String c1 , Tickets c ) {
         
       try {

            String req = "update tickets set type=?  where tickets_id =?";
            pst = conn.prepareStatement(req);
            pst.setString(1, c1);
            pst.setInt(2, c.getTickets_id());
            pst.executeUpdate();
           
           

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
            CourseService sc=new CourseService();
            UserService us= new UserService();
            while (rs.next()) {
                list.add(new Tickets(rs.getInt("tickets_id"),sc.readById( rs.getInt("course_id")),us.readById(rs.getInt("user_id")),rs.getString("type")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Tickets> readTickets(int id) {
         String req = "select * from tickets where user_id="+id;
        List<Tickets> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            CourseService sc=new CourseService();
            UserService us= new UserService();
            while (rs.next()) {
                list.add(new Tickets(rs.getInt("tickets_id"),sc.readById( rs.getInt("course_id")),us.readById(rs.getInt("user_id")),rs.getString("type")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
   
    public List<Tickets> read1() {
         String req = "select course_id  , type from tickets";
        List<Tickets> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            CourseService sc=new CourseService();
            UserService us= new UserService();
            while (rs.next()) {
                list.add(new Tickets(sc.readById( rs.getInt("course_id")),rs.getString("type")));
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
        CourseService sc=new CourseService();
        UserService us= new UserService();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                t.setTickets_id(rs.getInt("tickets_id"));
                t.setCourse(sc.readById(rs.getInt("course_id")));
                t.setUser_id(us.readById(rs.getInt("user_id")));
                t.setType(rs.getString("type"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    
    }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM tickets WHERE tickets_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    public  Tickets     ReadbyUser(int userid)
    {
         String req = "select * from tickets where user_id="+userid;
        Tickets t = new Tickets();
        CourseService sc=new CourseService();
        UserService us= new UserService();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                t.setTickets_id(rs.getInt("tickets_id"));
                t.setCourse(sc.readById(rs.getInt("course_id")));
                t.setUser_id(us.readById(rs.getInt("user_id")));
                t.setType(rs.getString("type"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    
    }
    
    
  
public void readQR(int id){
    TicketService ts = new TicketService();
        
        Tickets t = ts.readById(id);
        
        
        String data = t.toString();
        String path = "C:\\Users\\nechi\\Desktop\\ProjetF1\\FormulaOne\\src\\view\\images\\qr\\"+id+".jpg";
        
        BitMatrix mx = null;
        try {
            mx = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
                        
        } catch (WriterException ex) {
            Logger.getLogger(QRcodeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            MatrixToImageWriter.writeToFile(mx, "jpg", new File(path) );
        } catch (IOException ex) {
            Logger.getLogger(QRcodeController.class.getName()).log(Level.SEVERE, null, ex);
        }

}
 public Boolean countTicketsNTUser(int id,course t){
        Boolean v = true;
        int count =0;
        try {
            String requete = "SELECT COUNT(*) FROM tickets WHERE course_id=?";
            pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            rs = pst.executeQuery(requete);

             if (rs.next()) {
    count = rs.getInt(1); // access first column in result
}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if(count>=t.getCircuit_circuit_id().getCapacite()){
        v=false;
        }
        return v;
    
 }
 public String AfficherPrix(String prix){
     String res = null;
     if("VIP".equals(prix)){
            
            res="100DT";}
        else if("LOGUE".equals(prix)){
            
            res="90DT";}
        else if ("TRIBUNE".equals(prix)){
            
            res="80DT";}
        else if ("VIRAGE DROITE".equals(prix)){
            
            res="50DT";}
        else if("VIRAGE GAUCHE".equals(prix)) {
            
            res="50DT";}
        return res;
     
 
 }
    
    
}
