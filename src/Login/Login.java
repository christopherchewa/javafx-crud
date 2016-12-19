/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Chris Chewa
 */
public class Login extends Application {
    
    Connection conn;
            
    @Override
    public void start(Stage primaryStage)throws Exception {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/Login/Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Page");
        CheckConnection();
        primaryStage.show();
        
        
    }
    
    public void CheckConnection()
    {
        conn = SQLConnection.DbConnector();
        if (conn == null)
        {
            System.out.println("Connection Not Successful");
            System.exit(1);
        }
        else
        {
            System.out.println("Connection Successful");
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
