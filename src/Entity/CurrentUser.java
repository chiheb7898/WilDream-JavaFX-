/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author eyana
 */
public class CurrentUser {
    private int id;
    private int iduser;
    private String email;
    private String password;
    private String username;
    private String tel;
    private Date birthDate;
    private String adresse;
    private String roles;
    private String image;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public CurrentUser(int iduser, String email, String password, String username, String tel, String adresse, String roles, String image) {
        this.iduser = iduser;
        this.email = email;
        this.password = password;
        this.username = username;
        this.tel = tel;
        this.adresse = adresse;
        this.roles = roles;
        this.image = image;
    }
    
    
    

    public CurrentUser(int id, String email, String password, String username, String tel, Date birthDate, String adresse, String roles, String image) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.tel = tel;
        this.birthDate = birthDate;
        this.adresse = adresse;
        this.roles = roles;
        this.image = image;
    }

   
    
    public CurrentUser(int id, String email, String password, String username, String tel, String adresse, String roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.tel = tel;
        this.adresse = adresse;
        this.roles = roles;
    }

    public CurrentUser(int id, String email, String password, String username, String tel, Date birthDate, String adresse, String roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.tel = tel;
        this.birthDate = birthDate;
        this.adresse = adresse;
        this.roles = roles;
    }

    public CurrentUser(int id, String email, String password, String username, String tel, Date birthDate, String adresse) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.tel = tel;
        this.birthDate = birthDate;
        this.adresse = adresse;
    }

    public CurrentUser(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
    

    public CurrentUser(int id, String email, String password, String username, String tel, Date birthDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.tel = tel;
        this.birthDate = birthDate;
    }
    

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CurrentUser() {
    }
    
    
    
    
}
