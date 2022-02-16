/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author win10LIGHT
 */
public class User {
    private int user_id;
    private String username;
    private String email;
    private String password;
    private Timestamp  create_time;
    private int role_id;

    public User() {
    }

    public User(int user_id, String username, String email, String password, Timestamp create_time, int role_id) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.create_time = create_time;
        this.role_id = role_id;
    }

    public User(String username, String email, String password, Timestamp create_time, int role_id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.create_time = create_time;
        this.role_id = role_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

 

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
     @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", username=" + username + ", email=" + email +", password=" + password +", create_time=" + create_time +", role_id=" + role_id + '}';
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

    
}