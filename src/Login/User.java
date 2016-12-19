/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;



/**
 *
 * @author Chris Chewa
 */
public class User {
    
    private final StringProperty ID;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty gender;
    private final StringProperty email;
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty DOB;
   private final StringProperty phoneNumber;
   private final StringProperty hobbies;
   
    
    public User(String id, String fName, String lName, String gen, String mail, String uname, String pword, String dob , String pnum, String hobb)
    {
        this.ID = new SimpleStringProperty(id);
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.gender = new SimpleStringProperty(gen);
        this.email = new SimpleStringProperty(mail);
        this.username = new SimpleStringProperty(uname);
        this.password = new SimpleStringProperty(pword);
        this.DOB = new SimpleStringProperty(dob);
        this.phoneNumber = new SimpleStringProperty(pnum);
        this.hobbies = new SimpleStringProperty(hobb);
    }
    
     public String getID()
    {
        return ID.get();
    }
    
    public String getFirstName()
    {
        return firstName.get();
    }
    
    public String getLastName()
    {
        return lastName.get();
    }
    
   public String getGender()
    {
        return gender.get();
    }
    
    
    public String getEmail()
    {
        return email.get();
    }
    public String getUsername()
    {
        return username.get();
    }
    
    public String getPassword()
    {
        return password.get();
    }
    
    public String getDOB()
    {
        return DOB.get();
    }
    
    public String getPhoneNumber()
    {
        return phoneNumber.get();
    }
    
    public String getHobbies()
    {
        return hobbies.get();
    }

    
     public void setID(String id)
    {
        ID.set(id);
    }
    
    public void setFirstName(String fName)
    {
        this.firstName.set(fName);
    }
    
    public void setLastName(String lName)
    {
        this.lastName.set(lName);
    }
    
     public void setGender(String gen)
    {
        this.gender.set(gen);
    }
     
    public void setEmail(String mail)
    {
        this.email.set(mail);
    }
    public void setUsername(String uname)
    {
        this.username.set(uname);
    }
    
    public void setPassword(String pword)
    {
        this.password.set(pword);
    }
    
    public void setDOB(String dob)
    {
        this.DOB.set(dob);
    }
    
    public void setPhoneNumber(String pnum)
    {
        this.phoneNumber.set(pnum);
    }
    
    public void setHobbies(String hobb)
    {
        this.hobbies.set(hobb);
    }
    
    public StringProperty IDProperty()
    {
        return ID;
    }
    
    public StringProperty firstNameProperty()
    {
        return firstName;
    }
    
    public StringProperty lastNameProperty()
    {
        return lastName;
    }
    
       public StringProperty genderProperty()
    {
        return gender;
    }
    
    
    public StringProperty emailProperty()
    {
        return email;
    }
    
    public StringProperty usernameProperty()
    {
        return username;
    }
    
    public StringProperty passwordProperty()
    {
        return password;
    }
    
    public StringProperty DOBProperty()
    {
        return DOB;
    }

    public StringProperty phoneNumberProperty()
    {
        return phoneNumber;
    }
    
    public StringProperty hobbiesProperty()
    {
        return hobbies;
    }
    
}
