/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.jfoenix.controls.*;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author Chris Chewa
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private String myPass;
    
    Connection conn;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    AESAlg aesAlgo;
    private byte[] keyValue = new byte[]{'T','h','e','B','e','s','t','S','e','c','r','e','t','K','e','y',};
   
    
    
  
    
    @FXML
    private JFXButton btnLogin;
    
    @FXML
    private TextField txtUsername;
    
    @FXML
    private Label lblMessage;
    
    @FXML
    private Label lblUsernameValidation;
    
    @FXML
    private Label lblPasswordValidation;
    
    @FXML
    private TextField txtPassword;
    
    
    
    @FXML
    private void btnLoginAction(ActionEvent  event)throws Exception
    {
        
     
            try
        {
        conn = SQLConnection.DbConnector();
        String query = "select * from UserDatabase where Username = ? and Password=?";    
        pst = conn.prepareStatement(query);
        
        
        pst.setString(1, txtUsername.getText());
        
        pst.setString(2, txtPassword.getText());
        
        
      
       
        
        
        
        rs = pst.executeQuery();
        
        if(rs.next())
        {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/Login/MainPage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Main Page");
            stage.show();
            
        }
        else
        {
            lblMessage.setText("Login Failed");
        
        }
        pst.close();
        rs.close();
        }
        
        catch(Exception e1)
                {
                   lblMessage.setText("SQL Error");
                   System.err.println(e1);
                }
        }
       
       
        
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
