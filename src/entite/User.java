/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;
import javafx.scene.image.ImageView;
import sun.security.util.Password;


/**
 *
 * @author win10LIGHT
 */
public class User {

    public static Object getEmail;
    public static Object getPassword;
    private int user_id;
    private String username;
    private String email;
    private String tel;
    private String password;
    private Timestamp  create_time;
    private String role;
    private String image_name;
    private ImageView img;
    private int status;

    public User() {
    }
    public User(int user_id, String username, String email,String tel, String password, Timestamp create_time, String role, String image_name,int status, ImageView img) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.create_time = create_time;
        this.role = role;
        this.image_name = image_name;
        this.status = status;
        this.img = img;
    }

    public User(int user_id, String username, String email, String password, Timestamp create_time, String role) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.create_time = create_time;
        this.role = role;
    }
    
    
    
    public User(int user_id, String username, String email,String tel, String password, Timestamp create_time, String role, String image_name, int status) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.create_time = create_time;
        this.role = role;
        this.image_name = image_name;
        this.status = status;
    }

    public User(String username, String email,String tel, String password, Timestamp create_time, String role, String image_name, int status) {
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.create_time = create_time;
        this.role = role;
        this.image_name = image_name;
        this.status = status;
    }
    public User(int user_id,String password) {
        this.user_id = user_id;
        this.password = password;
    }
    
  public User(String role,int user_id){
      this.role = role;
      this.user_id = user_id;
      
  }

    public User(int user_id,String username, String tel, String password, String image_name) {
        this.user_id = user_id;
         this.username = username;
      this.tel = tel;
      this.password = password;
      this.image_name = image_name;
    }

    public User(String username, String email, String tel, String password, String role, String image_name, int status) {
         this.username = username;
         this.email = email;
      this.tel = tel;
      this.password = password;
      this.role = role;
      this.image_name = image_name;
      this.status = status;
    }

    public User(String username, String email, String tel, String password, String role, int status) {
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.role =role;
        this.status = status;
    }

    public User(String username, String email, String tel, String password, Timestamp timestamp, String role, int status, String img) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

 

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
        public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name; 
    }

    public static void setGetPassword(Object getPassword) {
        User.getPassword = getPassword;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
  public ImageView getImg() {
        return img;
    }
  
    public void setImg(ImageView img) {
        this.img = img;
    }
    

    @Override
    public String toString() {
        return this.username;
    }
    
    public String toString2(){
        return "User{" + "user_id=" + user_id + ", username=" + username + ", email=" + email + ", tel=" + tel +", password=" + password +", create_time=" + create_time +", role=" + role +", image_name=" + image_name +", status=" + status +'}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }


    private static class Create_time {

        public Create_time() {
             
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date=new Date();
        System.out.println(sdf.format(date));
        }
    }
    
 public static String passwordcrypte (String password) {
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

    
}
