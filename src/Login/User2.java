/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Chris Chewa
 */
public class User2 {
    
    private String ID;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String DOB;
   
    
    public User2(String id, String fName, String lName, String mail, String uname, String pword, String dob)
    {
        super();
        this.ID = id;
        this.firstName = fName;
        this.lastName = lName;
        this.email =  mail;
        this.username =  uname;
        this.password = pword;
        this.DOB = dob;
        
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDOB() {
        return DOB;
    }
    
    

    public void setID(String id){
    
        this.ID = id;
    }
    
    public void setFirstName(String fName){
    
        this.firstName =fName ;
    }
    
    public void setLastName(String lName){
    
        this.lastName = lName;
    }

    public void setEmail(String mail){
    
        this.email = mail;
    }
  
    
    public void setUsername(String uname){
    
        this.username = uname;
    }
    
    public void setPassword(String pword){
    
        this.password = pword;
    }
    
    public void setDOB(String dob){
    
        this.DOB = dob;
    }
    
    
}