/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Login;

import com.jfoenix.controls.*;
import java.io.File;
import Login.LoginController;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.text.Font;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


/**
 * FXML Controller class
 *
 * @author Chris Chewa
 */
public class MainPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    //AES
    AESAlg aesAlgo;
    private byte[] keyValue = new byte[]{'T','h','e','B','e','s','t','S','e','c','r','e','t','K','e','y',};
    
    //Database related variables
    Connection conn = SQLConnection.DbConnector();
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    //regular variables
    private String radioButtonLabel;
    private String genderStuff;
    private String hobbiesStuff;
    
    
    //observable lists
            
    final ObservableList<User> data = FXCollections.observableArrayList();
    final ObservableList options = FXCollections.observableArrayList();
    ObservableList<String> checkBoxList = FXCollections.observableArrayList();
    FilteredList<User> filteredData = new FilteredList<>(data, event -> true);
    
    private FileChooser fileChooser;
    private File file;
    private Desktop desktop = Desktop.getDesktop();
    private FileInputStream fis;
    
    @FXML
    private CheckBox checkBoxPlaying, checkBoxSinging, checkBoxDancing;
    
    @FXML
    private ComboBox userCombo;
    
    @FXML
    private ImageView profilePicture;
    
    @FXML
    private Image profilePictureImage;
            
    @FXML
    private ListView userListView;
    
    @FXML
    private TableView<User> tableData;
  
    @FXML
    private TableColumn<User, String> colID;

    @FXML
    private TableColumn<User, String> colFirstName;

    @FXML
    private TableColumn<User, String> colLastName;

    @FXML
    private TableColumn<User, String> colGender;

    @FXML
    private TableColumn<User, String> colEmail;

    @FXML
    private TableColumn<User, String> colUsername;

    @FXML
    private TableColumn<User, String> colPassword;

    @FXML
    private TableColumn<User, String> colDOB; 
    
    @FXML
    private TableColumn<User, String> colPhoneNumber;
    
    @FXML
    private TableColumn<User, String> colHobbies;
    
    @FXML
    private Label lblNewUser;
    
    @FXML
    private Label lblIDValidation;

    @FXML
    private Label lblFirstNameValidation;

    @FXML
    private Label lblLastNameValidation;

    @FXML
    private Label lblGenderValidation;

    @FXML
    private Label lblEmailValidation;

    @FXML
    private Label lblUsernameValidation;

    @FXML
    private Label lblPasswordValidation;

    @FXML
    private Label lblDOBValidation;
    
    @FXML
    private Label lblPhoneNumberValidation;
    
    @FXML
    private Label lblHobbiesValidation;
    
    @FXML
    private Label lblIDValidation1;

    @FXML
    private Label lblFirstNameValidation1;

    @FXML
    private Label lblLastNameValidation1;

    @FXML
    private Label lblGenderValidation1;

    @FXML
    private Label lblEmailValidation1;

    @FXML
    private Label lblUsernameValidation1;

    @FXML
    private Label lblPasswordValidation1;

    @FXML
    private Label lblDOBValidation1;
    
    @FXML
    private Label lblPhoneNumberValidation1;
    
    
    @FXML
    private TextField txtID;

    @FXML
    private TextField txtFirstName;
            
    @FXML
    private TextField txtLastName;
    
    @FXML
    private RadioButton radioMale;
  
    @FXML
    private RadioButton radioFemale;
    
    @FXML
    private TextField txtEmail;  

    @FXML
    private TextField txtUsername ;

    @FXML
    private PasswordField passPassword;
    
    @FXML
    private DatePicker dateofbirth;
    
    @FXML
    private TextField txtPhoneNumber;
    
    @FXML
    private TextField txtSearch;
    
    @FXML
    private TextArea pathTextArea;
   
    @FXML
    private JFXButton btnLogout;
    
    @FXML
    private JFXButton btnSave;
    
    @FXML
    private JFXButton btnUpdate;
    
    
    

   
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
            aesAlgo = new AESAlg(keyValue); 
               
            fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files" , "*txt"),
		new ExtensionFilter("Image Files" , "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("Audio Files", "*wav", "*.mp3" , "*.aac"),
                new ExtensionFilter("Video Files", "*.mp4", "*.flv" , "*.wmv"),
                new ExtensionFilter("All Files", "*.*")
                    );
                
        colID.setCellValueFactory(cellData -> cellData.getValue().IDProperty());
        colFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        colLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        colGender.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colUsername.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        colPassword.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        colDOB.setCellValueFactory(cellData -> cellData.getValue().DOBProperty());
        colPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        colHobbies.setCellValueFactory(cellData -> cellData.getValue().hobbiesProperty());
        
        dateofbirth.requestFocus();
        
        radioMale.requestFocus();
        radioFemale.requestFocus();
        
        
        
        
        checkBoxDancing.requestFocus();
        checkBoxSinging.requestFocus();
        checkBoxPlaying.requestFocus();
        
        
        tableData.setTableMenuButtonVisible(true); 
        loadTable();
        
        ToggleGroup genderGroup = new ToggleGroup();
        radioMale.setToggleGroup(genderGroup);
        radioFemale.setToggleGroup(genderGroup);
        
        userCombo.setItems(options);
        
        fillComboBox();
        
        userListView.setItems(options);
       

    }  
   
    //functions
    
    
    
  
    
    public static boolean TextFieldNotEmpty(TextField i)
    {
        boolean r = false;
        
        if(!"".equals(i.getText()) && !i.getText().isEmpty() && i.getText() != null)
        {
            r = true;
        }
        
         return r;
    }
    
    public static boolean TextFieldNotEmpty(TextField i , Label l, String sValidation)
    {
        boolean r = true;
        String c = null;
        
        if(!TextFieldNotEmpty(i))
        {
            r = false;
            c = sValidation;
        }
        
        l.setText(c);
        return r;
    }
    
    
    public static boolean GenderNotSelected(RadioButton rbMale, RadioButton rbFemale )
    {
        boolean r = false;
        
        
         if(rbMale.isSelected() || rbFemale.isSelected())
        {
            r = true;
        }
        
         return r;
    }
    
   
    
    public static boolean GenderNotSelected(RadioButton rbMale, RadioButton rbFemale, Label l, String sValidation)
    {
        boolean r = true;
        String c = null;
        
        if(!GenderNotSelected(rbMale, rbFemale))
        {
            r = false;
            c = sValidation;
        }
        
        l.setText(c);
        return r;
    }
    
        public static boolean HobbiesNotSelected(CheckBox cbDancing, CheckBox cbSinging, CheckBox cbPlaying) 
            {
                boolean r = false;
                
                if( (cbDancing.isSelected() | cbSinging.isSelected() | cbPlaying.isSelected()) )
                {
                    r = true;
                }
                
                
                
                return r;
            }
        public static boolean HobbiesNotSelected(CheckBox cbDancing, CheckBox cbSinging, CheckBox cbPlaying, Label l, String sValidation) 
        {
            boolean r = true;
            String c = null;
            
            if(!HobbiesNotSelected(cbDancing, cbSinging, cbPlaying))
            {
                r = false;
                c = sValidation;
            }
            
            l.setText(c);
            return r;
        }
    
    public boolean InvalidData(TextField i)
    {
     Pattern IDPattern = Pattern.compile("[0-9]+");
     Matcher IDMatcher = IDPattern.matcher(txtID.getText());
     
     
     Pattern firstNamePattern = Pattern.compile("[a-zA-Z]+");
     Matcher firstNameMatcher = firstNamePattern.matcher(txtFirstName.getText());
     
     Pattern lastNamePattern = Pattern.compile("[a-zA-Z]+");
     Matcher lastNameMatcher = lastNamePattern.matcher(txtLastName.getText());
     
     Pattern emailPattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
     Matcher emailMatcher = emailPattern.matcher(txtEmail.getText());
   
     //((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,15}) must have atleast one digit,one lowercase and one uppercase letter and one special symbol and should be between 6 - 15 characters
     Pattern passwordPattern = Pattern.compile("((?=.*[a-z]).{3,15})");
     Matcher passwordMatcher = passwordPattern.matcher(passPassword.getText());
     
     Pattern phoneNumberPattern = Pattern.compile("[0][7][0-9]{8}");
     Matcher phoneNumberMatcher = phoneNumberPattern.matcher(txtPhoneNumber.getText());
     
     boolean r = false;
     
     if(IDMatcher.find() && IDMatcher.group().equals(i.getText()))
     {
         r =true;
     }
     
     if(firstNameMatcher.find() && firstNameMatcher.group().equals(i.getText()))
     {
         r =true;
     }
     
     if(lastNameMatcher.find() && lastNameMatcher.group().equals(i.getText()))
     {
         r =true;
     }
     if(emailMatcher.find() && emailMatcher.group().equals(i.getText()))
     {
         r =true;
     }
     if(passwordMatcher.find() && passwordMatcher.group().equals(i.getText()))
     {
         r =true;
     }
     if(phoneNumberMatcher.find() && phoneNumberMatcher.group().equals(i.getText()))
     {
         r =true;
     }
     
     return r;
    }
    
     public boolean InvalidData(TextField i, Label l, String sValidation)
    {
        boolean r = true;
        String c = null;
        
        if(!InvalidData(i))
        {
         r = false;
            c = sValidation;
        }
        
        l.setText(c);
        return r;
    }
     
    /**
     *
     */
    
    public void fillComboBox()
    {
       options.clear();
    //   checkBoxList.clear();
        try 
        {
            String query = "Select FirstName from UserDatabase";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                options.add(rs.getString("FirstName"));
            }
            
            pst.close();
            rs.close();
            
          } 
        catch (SQLException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }
   
    private void loadTable()
    {
      
      data.removeAll(data);
     // checkBoxList.removeAll(checkBoxList);
      
      try
      {
          String query = "select * from UserDatabase"; 
          pst = conn.prepareStatement(query);
          rs = pst.executeQuery();
          
          while(rs.next())
          {
              
                 data.add(new User (
                 rs.getString("ID"),
                 rs.getString("FirstName"),
                 rs.getString("LastName"),
                 rs.getString("Gender"),
                 rs.getString("Email"),
                 rs.getString("Username"),
                 rs.getString("Password"),
                 rs.getString("DOB"),
                 rs.getString("PhoneNumber"),
                 rs.getString("Hobbies")        
                
             ));
             
              tableData.setItems(data);
              
          }                                                  
          pst.close();
          rs.close();
          
         
          
      }
      catch(Exception e)
      {
          lblNewUser.setText("Table Error");
          System.err.println(e);
      } 
   }
   
    private void clearFields()
    {
        txtID.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
        txtUsername.setText("");
        passPassword.setText("");
        dateofbirth.setValue(null);
        dateofbirth.getEditor().setText("");
        radioMale.setSelected(false);
        radioFemale.setSelected(false);
        txtPhoneNumber.setText("");
        checkBoxDancing.setSelected(false);
        checkBoxSinging.setSelected(false);
        checkBoxPlaying.setSelected(false);
        checkBoxList.clear();
    }
    
    
   
   //Events
    
    @FXML
    public void checkBoxDancingAction(ActionEvent event)throws Exception
    {
        checkBoxList.add(checkBoxDancing.getText());
    }

    @FXML
    public void checkBoxPlayingAction(ActionEvent event)throws Exception
    {
        checkBoxList.add(checkBoxPlaying.getText());
    }

    @FXML
    public void checkBoxSingingAction(ActionEvent event)throws Exception
    {
       checkBoxList.add(checkBoxSinging.getText());
    }
   
   @FXML
    private void btnLogoutAction (ActionEvent e)throws Exception
    {
        
    }
  
           
   @FXML
   public void radioMaleAction(ActionEvent event)throws Exception
   {
       radioButtonLabel = "Male";
   }
   
   @FXML
   private void radioFemaleAction(ActionEvent event)throws Exception
   {
       radioButtonLabel = "Female";
   }
   
   @FXML
   private void btnDeleteAction(ActionEvent event)throws Exception
   {
       try
       {
       String query = "delete from userDatabase where ID = ?";
       pst= conn.prepareStatement(query);
       pst.setString(1,txtID.getText());
       pst.executeUpdate();
       
       pst.close();
       }
       catch(SQLException e)
       {
           System.err.println(e);
       }
       
       clearFields();
       fillComboBox();
       loadTable();
   }
    
   

  @FXML
  private void listViewAction(MouseEvent arg0)throws Exception
  {
        
      try{
        String query = "Select * from UserDatabase where FirstName = ?";
        pst = conn.prepareStatement(query);
        pst.setString(1, (String)userListView.getSelectionModel().getSelectedItem());
        rs = pst.executeQuery();
        
        while(rs.next())
            {
            txtID.setText(rs.getString("ID"));
             txtFirstName.setText(rs.getString("FirstName"));
              txtLastName.setText(rs.getString("LastName"));
               txtEmail.setText(rs.getString("Email"));
                   genderStuff = rs.getString("Gender");
               
               if("Female".equals(genderStuff))
               {
                  radioFemale.setSelected(true);
                 
               }
               else if("Male".equals(genderStuff))
               {
                  
                 radioMale.setSelected(true);
                 
               }
               else
               {
                  radioFemale.setSelected(false);
                  radioFemale.setSelected(false);
               }
               
                txtUsername.setText(rs.getString("Username"));
                 String encryptedText = rs.getString("Password");
                 String plainText = aesAlgo.decrypt(encryptedText);
                 passPassword.setText(plainText);
                ((TextField)dateofbirth.getEditor()).setText(rs.getString("DOB"));
                txtPhoneNumber.setText(rs.getString("PhoneNumber"));
                  
                
                
                
                
                  
            }
        pst.close();
        rs.close();
        }
        catch(SQLException e)
        {
            System.err.println(e);
            
        }
  }
  
    @FXML
    private void comboBoxAction(ActionEvent event)throws Exception
    {
        try{
        String query = "Select * from UserDatabase where FirstName = ?";
        pst = conn.prepareStatement(query);
        pst.setString(1, (String)userCombo.getSelectionModel().getSelectedItem());
        rs = pst.executeQuery();
        
        while(rs.next())
            {
            txtID.setText(rs.getString("ID"));
             txtFirstName.setText(rs.getString("FirstName"));
              txtLastName.setText(rs.getString("LastName"));
               txtEmail.setText(rs.getString("Email"));
                   genderStuff = rs.getString("Gender");
               
               if("Female".equals(genderStuff))
               {
                  radioFemale.setSelected(true);
               }
               else if("Male".equals(genderStuff))
               {
                  
                 radioMale.setSelected(true);
               }
               else
               {
                  radioFemale.setSelected(false);
                  radioFemale.setSelected(false);
               }
               
                txtUsername.setText(rs.getString("Username"));
                 String encryptedText = rs.getString("Password");
                 String plainText = aesAlgo.decrypt(encryptedText);
                 passPassword.setText(plainText);
                  ((TextField)dateofbirth.getEditor()).setText(rs.getString("DOB"));
                   txtPhoneNumber.setText(rs.getString("PhoneNumber"));
                  
                  if(rs.getString("Hobbies") != null && rs.getString("Hobbies") != "" && !rs.getString("Hobbies").isEmpty()) 
                  {
                      checkBoxDancing.setSelected(false);
                      checkBoxSinging.setSelected(false);
                      checkBoxPlaying.setSelected(false);
                      
                      System.out.println(rs.getString("Hobbies"));
                      
                      String checkBoxString = rs.getString("Hobbies").replace("[","").replace("[","");
                      System.out.println(checkBoxString);
                      
                      List<String> hobbyList = Arrays.asList(checkBoxString.split("\\s*,\\s*"));
                      System.out.println(hobbyList);
                      
                      for(String hobby : hobbyList)
                      {
                          switch(hobby)
                          {
                              case "Playing" : checkBoxPlaying.setSelected(true);
                              break;
                              
                              case "Singing" : checkBoxSinging.setSelected(true);
                              break;
                              
                              case "Dancing" : checkBoxDancing.setSelected(true);
                              break;
                              
                              default:
                                      checkBoxPlaying.setSelected(false);
                                      checkBoxSinging.setSelected(false);
                                      checkBoxDancing.setSelected(false);
                              
                          }
                      }
                  
                  
                  }
                  else
                  {
                      checkBoxPlaying.setSelected(false);
                      checkBoxSinging.setSelected(false);
                      checkBoxDancing.setSelected(false);
                  }
                        
                   
                   
                       
            }
        pst.close();
        rs.close();
        }
        catch(SQLException e)
        {
            System.err.println(e);
            
        }
    }
    
    @FXML
    private void tableAction(MouseEvent arg0)throws Exception
    {
       try{
           User user = tableData.getSelectionModel().getSelectedItem();
        String query = "Select * from UserDatabase where ID = ?";
        pst = conn.prepareStatement(query);
        pst.setString(1, user.getID());
        rs = pst.executeQuery();
        
        while(rs.next())
            {
            txtID.setText(rs.getString("ID"));
             txtFirstName.setText(rs.getString("FirstName"));
              txtLastName.setText(rs.getString("LastName"));
               txtEmail.setText(rs.getString("Email"));
                   genderStuff = rs.getString("Gender");
               
               if("Female".equals(genderStuff))
               {
                  radioFemale.setSelected(true);
                 
               }
               else if("Male".equals(genderStuff))
               {
                  
                 radioMale.setSelected(true);
                 
               }
               else
               {
                  radioFemale.setSelected(false);
                  radioFemale.setSelected(false);
               }
               
                txtUsername.setText(rs.getString("Username"));
               String encryptedText = rs.getString("Password");
                 String plainText = aesAlgo.decrypt(encryptedText);
                 passPassword.setText(plainText);
                ((TextField)dateofbirth.getEditor()).setText(rs.getString("DOB"));
                txtPhoneNumber.setText(rs.getString("PhoneNumber"));
                hobbiesStuff = rs.getString("Hobbies");
                   
                
                  
            }
        pst.close();
        rs.close();
        }
        catch(SQLException e)
        {
            System.err.println(e);
            
        } 
    }
    @FXML
    private void txtSearchAction(KeyEvent event)throws Exception
    {
        txtSearch.textProperty().addListener((observableValue, oldValue, newValue) ->{
         
            filteredData.setPredicate((Predicate<? super User>) user ->{
              if(newValue == null || newValue.isEmpty() || newValue == "")
                {
                     return true;   
                }
              String lowerCaseFilter = newValue.toLowerCase();
              if(user.getID().contains(newValue))
                {
                     return true;
                }else if(user.getFirstName().toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }else if(user.getLastName().toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
              
              return false;
          }); 
        });
        
        SortedList<User> sortedData = new SortedList<>(filteredData);
        
        sortedData.comparatorProperty().bind(tableData.comparatorProperty());
        tableData.setItems(sortedData);
    }
    
    
    @FXML
    private void tableReleaseAction(KeyEvent event)throws Exception
    {
      if(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN)
      {
          try{
           User user = tableData.getSelectionModel().getSelectedItem();
        String query = "Select * from UserDatabase where ID = ?";
        pst = conn.prepareStatement(query);
        pst.setString(1, user.getID());
        rs = pst.executeQuery();
        
        while(rs.next())
            {
            txtID.setText(rs.getString("ID"));
             txtFirstName.setText(rs.getString("FirstName"));
              txtLastName.setText(rs.getString("LastName"));
               txtEmail.setText(rs.getString("Email"));
                   genderStuff = rs.getString("Gender");
               
               if("Female".equals(genderStuff))
               {
                  radioFemale.setSelected(true);
                 
               }
               else if("Male".equals(genderStuff))
               {
                  
                 radioMale.setSelected(true);
                 
               }
               else
               {
                  radioFemale.setSelected(false);
                  radioFemale.setSelected(false);
               }
               
                txtUsername.setText(rs.getString("Username"));
                 String encryptedText = rs.getString("Password");
                 String plainText = aesAlgo.decrypt(encryptedText);
                 passPassword.setText(plainText);
                ((TextField)dateofbirth.getEditor()).setText(rs.getString("DOB"));
                txtPhoneNumber.setText(rs.getString("PhoneNumber"));
                  hobbiesStuff = rs.getString("Hobbies");
                   
                
                  
            }
        pst.close();
        rs.close();
        }
        catch(SQLException e)
        {
            System.err.println(e);
            
        } 
      }
    }
    
    @FXML
    private void btnBrowseAction(ActionEvent event)throws Exception
    {
        
        
        Stage stage = new Stage();
        
        
        
        //Single file selection
        
        
        file = fileChooser.showOpenDialog(stage);
        
        if(file != null)
        {
            try
            {
              //desktop.open(file); 
              pathTextArea.setText(file.getAbsolutePath());
              profilePictureImage = new Image(file.toURI().toString(), 160, 176, true, true);//path, width, height, preserve ratio, smooth
              
              profilePicture = new ImageView(profilePictureImage);
              profilePicture.setFitWidth(160);
              profilePicture.setFitHeight(176);
              profilePicture.setPreserveRatio(true);
              
              
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        
        } 
        
        
        //multiple file selection
        
        /*
        List<File> fileList = fileChooser.showOpenMultipleDialog(stage);
        if(fileList != null)
        {
            fileList.stream().forEach(selectedFiles ->{
                     
                   // desktop.open(selectedFiles);
                    pathTextArea.setText(fileList.toString());
           
            });
        }
          */
    }
    @FXML
    private void btnSaveAction(ActionEvent event)throws Exception
    {
        //Form validation
        
        boolean valID = TextFieldNotEmpty(txtID , lblIDValidation, "*");
        boolean valFirstName = TextFieldNotEmpty(txtFirstName, lblFirstNameValidation, "*");
        boolean valLastName = TextFieldNotEmpty(txtLastName, lblLastNameValidation, "*");
        boolean valGender = GenderNotSelected(radioMale, radioFemale, lblGenderValidation,"*");
        boolean valEmail = TextFieldNotEmpty(txtEmail, lblEmailValidation, "*");
        boolean valUsername = TextFieldNotEmpty(txtUsername, lblUsernameValidation, "*");
        boolean valPassword = TextFieldNotEmpty(passPassword, lblPasswordValidation, "*");
        boolean valDOB = TextFieldNotEmpty(dateofbirth.getEditor(), lblDOBValidation, "*");
        boolean valPhoneNumber = TextFieldNotEmpty(txtPhoneNumber, lblPhoneNumberValidation, "*");
        boolean valHobbies = HobbiesNotSelected(checkBoxDancing, checkBoxSinging, checkBoxPlaying, lblHobbiesValidation, "* Select at least one hobby");
        
        boolean valID1 = InvalidData(txtID, lblIDValidation1, "??");
        boolean valFirstName1 = InvalidData(txtFirstName, lblFirstNameValidation1, "Enter a valid name");
        boolean valLastName1 = InvalidData(txtLastName, lblLastNameValidation1, "Enter a valid name");
        boolean valEmail1 = InvalidData(txtEmail, lblEmailValidation1, "Enter a valid email");
       // boolean valUsername1 = InvalidData(txtFirstName, lblFirstNameValidation1, "Enter a valid name");
        boolean valPassword1 = InvalidData(passPassword, lblPasswordValidation1, "Enter a valid password");
       //  boolean valDOB1 = InvalidData(dateofbirth.getEditor(), lblDOBValidation1, "Enter a valid date");
         boolean valPhoneNumber1 = InvalidData(txtPhoneNumber, lblPhoneNumberValidation1, "Enter a valid phone No.");
         
        if(valID && valID1 && valFirstName && valFirstName1 && valLastName && valLastName1 && valGender && valEmail && valEmail1 && valUsername && valPassword && valPassword1 && valDOB && valPhoneNumber && valPhoneNumber1 && valHobbies)
        {
                
        
            try        
        {
        
        String query = "INSERT INTO UserDatabase (ID, FirstName, LastName, Gender, Email, Username, Password, DOB, PhoneNumber, Hobbies, Image) VALUES (?,?,?,?,?,?,?,?,?,?,?)";    
        pst = conn.prepareStatement(query);
        
        
        
        pst.setString(1, txtID.getText());
        pst.setString(2, txtFirstName.getText());
        pst.setString(3, txtLastName.getText());
        pst.setString(4, radioButtonLabel);
        pst.setString(5, txtEmail.getText());
        pst.setString(6, txtUsername.getText());
        pst.setString(7, passPassword.getText());
        pst.setString(8, dateofbirth.getEditor().getText());
        pst.setString(9, txtPhoneNumber.getText());
        pst.setString(10, checkBoxList.toString());
        
        fis = new FileInputStream(file);
        pst.setBinaryStream(11, (InputStream)fis, (int)file.length());
        
       
        pst.execute();
        
        
        pst.close();
        
        
        clearFields();
        
     
                  }
        
        
        catch(Exception e1)
                {
                  
                   System.err.println(e1);
                }
        
          
          fillComboBox();
          loadTable();
        
        }
        }
    
    @FXML
    private void btnUpdateAction(ActionEvent event)throws Exception
    {
        //Form validation
        
        boolean valID = TextFieldNotEmpty(txtID , lblIDValidation, "*");
        boolean valFirstName = TextFieldNotEmpty(txtFirstName, lblFirstNameValidation, "*");
        boolean valLastName = TextFieldNotEmpty(txtLastName, lblLastNameValidation, "*");
        boolean valGender = GenderNotSelected(radioMale, radioFemale, lblGenderValidation,"*");
        boolean valEmail = TextFieldNotEmpty(txtEmail, lblEmailValidation, "*");
        boolean valUsername = TextFieldNotEmpty(txtUsername, lblUsernameValidation, "*");
        boolean valPassword = TextFieldNotEmpty(passPassword, lblPasswordValidation, "*");
        boolean valDOB = TextFieldNotEmpty(dateofbirth.getEditor(), lblDOBValidation, "*");
        boolean valPhoneNumber = TextFieldNotEmpty(txtPhoneNumber, lblPhoneNumberValidation, "*");
        boolean valHobbies = HobbiesNotSelected(checkBoxDancing, checkBoxSinging, checkBoxPlaying, lblHobbiesValidation, "* Select at least one hobby");
        
        boolean valID1 = InvalidData(txtID, lblIDValidation1, "Enter a valid ID");
        boolean valFirstName1 = InvalidData(txtFirstName, lblFirstNameValidation1, "Enter a valid name");
        boolean valLastName1 = InvalidData(txtLastName, lblLastNameValidation1, "Enter a valid name");
        boolean valEmail1 = InvalidData(txtEmail, lblEmailValidation1, "Enter a valid email");
       // boolean valUsername1 = InvalidData(txtFirstName, lblFirstNameValidation1, "Enter a valid name");
        boolean valPassword1 = InvalidData(passPassword, lblPasswordValidation1, "Enter a valid password");
       //  boolean valDOB1 = InvalidData(dateofbirth.getEditor(), lblDOBValidation1, "Enter a valid date");
         boolean valPhoneNumber1 = InvalidData(txtPhoneNumber, lblPhoneNumberValidation1, "Enter a valid phone No.");
         
        if(valID && valID1 && valFirstName && valFirstName1 && valLastName && valLastName1 && valGender && valEmail && valEmail1 && valUsername && valPassword && valPassword1 && valDOB && valPhoneNumber && valPhoneNumber1 && valHobbies)
        {
                
        
            try        
        {
        
        String query = "update UserDatabase set ID = ?, FirstName =? , LastName = ?, Gender = ?, Email = ?, Username = ? , Password = ?, DOB = ?, PhoneNumber = ?, Hobbies = ? where ID = '"+ txtID.getText() +"' ";    
        pst = conn.prepareStatement(query);
        
        
        
        pst.setString(1, txtID.getText());
        pst.setString(2, txtFirstName.getText());
        pst.setString(3, txtLastName.getText());
        pst.setString(4, radioButtonLabel);
        pst.setString(5, txtEmail.getText());
        pst.setString(6, txtUsername.getText());
        
        String plainText = passPassword.getText();
        String encryptedText = aesAlgo.encrypt(plainText);
        
        pst.setString(7, encryptedText);
        
        pst.setString(8, dateofbirth.getEditor().getText());
        pst.setString(9, txtPhoneNumber.getText());
        pst.setString(10, checkBoxList.toString());
        
        fis = new FileInputStream(file);
        pst.setBinaryStream(11, (InputStream)fis, (int)file.length());
        
        pst.execute();
        
        
        pst.close();
        
        
        clearFields();
        
     
                  }
        
        
        catch(Exception e1)
                {
                  
                   System.err.println(e1);
                }
        
          
          fillComboBox();
          loadTable();
        
        }
        }
    
    
    }
    

    
    
  
  
    
     

