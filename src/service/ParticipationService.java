/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Participation;
import java.sql.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utils.Datasource;

public class ParticipationService implements IServiceA<Participation> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public ParticipationService() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void insert(Participation p) {
        String req = "insert into participation (pilote_id, equipe_id, course_id, qualifying_id, grid, position, points) values (?,?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, p.getPilote().getPilote_id());
            pst.setInt(2, p.getEquipe().getEquipe_id());
            pst.setInt(3, p.getCourse().getCourse_id());
            pst.setInt(4, p.getQualifying().getQualifying_id());
            pst.setInt(5, p.getQualifying().getPosition());
            pst.setInt(6, p.getPosition());
            pst.setInt(7, p.getPoints());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Participation p) {
        String req = "DELETE FROM participation WHERE participation_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, p.getParticipation_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void update(Participation p) {
        String req = "update participation set pilote_id=?, equipe_id=?, course_id=?, qualifying_id=?"
                + ", grid=?, position=?, points=?  where participation_id=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, p.getPilote().getPilote_id());
            pst.setInt(2, p.getEquipe().getEquipe_id());
            pst.setInt(3, p.getCourse().getCourse_id());
            pst.setInt(4, p.getQualifying().getQualifying_id());
            pst.setInt(5, p.getQualifying().getPosition());
            pst.setInt(6, p.getPosition());
            pst.setInt(7, p.getPoints());
            pst.setInt(8, p.getParticipation_id());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public List<Participation> read() {
        String req = "select * from participation";
        List<Participation> list = new ArrayList<>();

        PiloteService ps = new PiloteService();
        EquipeService es = new EquipeService();
        CourseService cs = new CourseService();
        QualifyingService qs = new QualifyingService();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Participation(
                        rs.getInt("participation_id"),
                        ps.readById1(rs.getInt("pilote_id")),
                        es.readById(rs.getInt("equipe_id")),
                        cs.readById(rs.getInt("course_id")),
                        qs.readById(rs.getInt("qualifying_id")),
                        rs.getInt("grid"),
                        rs.getInt("position"),
                        rs.getInt("points")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Participation> readWinners(int y) {
        String req = "select * from participation p join courses c on p.course_id=c.course_id where c.saison_year=" + y + " and position=1";
        List<Participation> list = new ArrayList<>();

        PiloteService ps = new PiloteService();
        EquipeService es = new EquipeService();
        CourseService cs = new CourseService();
        QualifyingService qs = new QualifyingService();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Participation(
                        rs.getInt("participation_id"),
                        ps.readById1(rs.getInt("pilote_id")),
                        es.readById(rs.getInt("equipe_id")),
                        cs.readById(rs.getInt("course_id")),
                        qs.readById(rs.getInt("qualifying_id")),
                        rs.getInt("grid"),
                        rs.getInt("position"),
                        rs.getInt("points")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Participation> search(String query) {
        String req = "select * from participation p "
                + "join courses c on p.course_id=c.course_id "
                + "join pilotes pi on pi.pilote_id=p.pilote_id "
                + "join membres m on m.membre_id=pi.pilote_id "
                + "join equipes e on p.equipe_id=e.equipe_id where "
                + "m.nom like '%"+query
                + "%' or e.nom like '%"+query
                + "%' or c.course_nom like '%"+query
                +"%'";
        List<Participation> list = new ArrayList<>();
        PiloteService ps = new PiloteService();
        EquipeService es = new EquipeService();
        CourseService cs = new CourseService();
        QualifyingService qs = new QualifyingService();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Participation(
                        rs.getInt("participation_id"),
                        ps.readById1(rs.getInt("pilote_id")),
                        es.readById(rs.getInt("equipe_id")),
                        cs.readById(rs.getInt("course_id")),
                        qs.readById(rs.getInt("qualifying_id")),
                        rs.getInt("grid"),
                        rs.getInt("position"),
                        rs.getInt("points")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Participation readById(int id) {
        String req = "select * from participation where participation_id=" + id;
        Participation p = new Participation();

        PiloteService ps = new PiloteService();
        EquipeService es = new EquipeService();
        CourseService cs = new CourseService();
        QualifyingService qs = new QualifyingService();

        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                p.setParticipation_id(rs.getInt("participation_id"));
                p.setPilote(ps.readById1(rs.getInt("pilote_id")));
                p.setEquipe(es.readById(rs.getInt("equipe_id")));
                p.setCourse(cs.readById(rs.getInt("course_id")));
                p.setQualifying(qs.readById(rs.getInt("qualifying_id")));
                p.setGrid(rs.getInt("grid"));
                p.setPosition(rs.getInt("position"));
                p.setPoints(rs.getInt("points"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public List<Participation> filter(Integer year, Integer course_id, Integer pilote_id, Integer equipe_id) {

        String req = ("select * from participation p "
                + "join courses c on p.course_id=c.course_id "
                + "join pilotes pi on pi.pilote_id=p.pilote_id "
                + "join membres m on m.membre_id=pi.pilote_id "
                + "join equipes e on p.equipe_id=e.equipe_id where 1=1");

        if (year != null) {
            req = req + (" AND c.saison_year=" + year);
        }
        if (course_id != null) {
            req = req + (" AND c.course_id =" + course_id);
        }
        if (pilote_id != null) {
            req = req + (" AND pi.pilote_id =" + pilote_id);
        }
        if (equipe_id != null) {
            req = req + (" AND e.equipe_id =" + equipe_id);
        }

        List<Participation> list = new ArrayList<>();
        PiloteService ps = new PiloteService();
        EquipeService es = new EquipeService();
        CourseService cs = new CourseService();
        QualifyingService qs = new QualifyingService();
        try {
            ste = conn.createStatement();

            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Participation(
                        rs.getInt("participation_id"),
                        ps.readById1(rs.getInt("pilote_id")),
                        es.readById(rs.getInt("equipe_id")),
                        cs.readById(rs.getInt("course_id")),
                        qs.readById(rs.getInt("qualifying_id")),
                        rs.getInt("grid"),
                        rs.getInt("position"),
                        rs.getInt("points")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Participation> readBySaison(int year) {
        String req = "select * from participation p join courses c on p.course_id=c.course_id where c.saison_year=" + year;
        List<Participation> list = new ArrayList<>();

        PiloteService ps = new PiloteService();
        EquipeService es = new EquipeService();
        CourseService cs = new CourseService();
        QualifyingService qs = new QualifyingService();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Participation(
                        rs.getInt("participation_id"),
                        ps.readById1(rs.getInt("pilote_id")),
                        es.readById(rs.getInt("equipe_id")),
                        cs.readById(rs.getInt("course_id")),
                        qs.readById(rs.getInt("qualifying_id")),
                        rs.getInt("grid"),
                        rs.getInt("position"),
                        rs.getInt("points")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Participation> readBySaison2(int year) {
        String req = "select * from participation p join courses c on p.course_id=c.course_id where c.saison_year=" + year + "and position=1";

        List<Participation> list = new ArrayList<>();

        PiloteService ps = new PiloteService();
        EquipeService es = new EquipeService();
        CourseService cs = new CourseService();
        QualifyingService qs = new QualifyingService();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Participation(
                        rs.getInt("participation_id"),
                        ps.readById1(rs.getInt("pilote_id")),
                        es.readById(rs.getInt("equipe_id")),
                        cs.readById(rs.getInt("course_id")),
                        qs.readById(rs.getInt("qualifying_id")),
                        rs.getInt("grid"),
                        rs.getInt("position"),
                        rs.getInt("points")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void sendMail(String recepient, Participation p) throws Exception {
        String email = "formula1brightlights@gmail.com";
        String password = "Formula1bright";

        System.out.println("Preparing to send email");

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });
        Message message = prepareMessage(session, email, recepient, p);
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String email, String recepient, Participation p) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
            message.setSubject("Resultat de participation " + p.getPilote().getNom() + " a " + p.getCourse().getCourse_nom());
            String template = "<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n"
                    + "</head>\n"
                    + "<body style=\"background-color: #FFFFFF; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #FFFFFF;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"heading_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"width:100%;text-align:center;\">\n"
                    + "<h1 style=\"margin: 0; color: #555555; font-size: 23px; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; line-height: 120%; text-align: center; direction: ltr; font-weight: 700; letter-spacing: normal; margin-top: 0; margin-bottom: 0;\">Salut cher participant,</h1>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 12px;\"><span style=\"font-size:15px;\">Merci pour votre participation, vous pouvez trouver vos résultats de participation ici, merci de nous contacter pour toute réclamation</span></p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-9\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n"
                    + "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 16px;\"><strong><i>Information participation:</i></strong></p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-10\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"25%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\"><strong>Nom du pilote:</strong></p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"75%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\">$pilote</p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"25%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 12px;\"><span style=\"font-size:14px;\"><strong>Numero:</strong></span></p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"75%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\">$numero </p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"25%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\"><strong>Equipe:</strong></p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"75%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\">$equipe </p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-5\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"25%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\"><strong>Course:</strong></p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"75%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\">$course </p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-6\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"25%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 12px;\"><strong><span style=\"font-size:14px;\">Starting grid:</span></strong></p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"75%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\">$grid </p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-7\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"25%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\"><strong>Position:</strong></p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"75%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\">$position </p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-8\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"25%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 12px;\"><span style=\"font-size:14px;\"><strong>Points:</strong></span></p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"75%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\">$points </p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-9\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n"
                    + "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 16px;\"><strong><i>Information qualification:</i></strong></p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-10\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"25%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\"><strong>Q1:</strong></p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"75%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\">$q1 </p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-11\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"25%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\"><strong>Q2:</strong></p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"75%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\">$q2 </p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-12\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"25%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\"><strong>Q3:</strong></p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"75%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"padding-top:15px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n"
                    + "<div style=\"font-family: sans-serif\">\n"
                    + "<div style=\"font-size: 14px; mso-line-height-alt: 16.8px; color: #555555; line-height: 1.2; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;\">\n"
                    + "<p style=\"margin: 0; font-size: 14px;\">$q3 </p>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-13\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\" width=\"500\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"icons_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"vertical-align: middle; color: #9d9d9d; font-family: inherit; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n"
                    + "<table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
                    + "<tr>\n"
                    + "<td style=\"vertical-align: middle; text-align: center;\">\n"
                    + "<!--[if vml]><table align=\"left\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->\n"
                    + "<!--[if !vml]><!-->\n"
                    + "<table cellpadding=\"0\" cellspacing=\"0\" class=\"icons-inner\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;\">\n"
                    + "<!--<![endif]-->\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table><!-- End -->\n"
                    + "</body>\n"
                    + "</html>";
            template = template.replace("$pilote", p.getPilote().getNom());
            template = template.replace("$numero", String.valueOf(p.getPilote().getNumero()));
            template = template.replace("$equipe", p.getEquipe().getNom());
            template = template.replace("$course", p.getCourse().getCourse_nom());
            template = template.replace("$grid", String.valueOf(p.getGrid()));
            template = template.replace("$position", String.valueOf(p.getPosition()));
            template = template.replace("$points", String.valueOf(p.getPoints()));
            template = template.replace("$q1", String.valueOf(p.getQualifying().getQ1()));
            template = template.replace("$q2", String.valueOf(p.getQualifying().getQ2()));
            template = template.replace("$q3", String.valueOf(p.getQualifying().getQ3()));

            message.setContent(template, "text/html");
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    private static Message prepareMessage(Session session, String email, String recepient, Participation p) {  
//        String template = "<html>\n"
//                + "    <body>\n"
//                + "       <p> My name dsdsd is"+ p.getCourse().getCourse_nom() +"</p>\n"
//                + "\n"
//                + "     </body>\n"
//                + "</html>";
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(email));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
//            message.setSubject("Information de participation");
////            String msg = MessageFormat.format(template, "hi");
//            String htmlCode = "<h1>Bienvenue chez Formula1</h1> <br/> <h2><b>Next Line </b></h2>";
//            message.setContent(htmlCode, "text/html");
//            return message;
//        } catch (Exception ex) {
//            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
}
