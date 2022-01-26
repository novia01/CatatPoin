package com.mycompany.sistemcatatpoinkeaktifan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class PrimaryController implements Initializable{
   @FXML
   private Button cancelButton;
   @FXML
   private Text loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField enterPasswordField;
   
   
   
   public void loginButtonOnAction(ActionEvent event){
       
       if(usernameTextField.getText().isBlank()== false && enterPasswordField.getText().isBlank() == false){
           validateLogin();
       }else {
           loginMessageLabel.setText("Please enter username and password");
       }
   }
   
   
   
   public void cancelButtonOnAction() throws IOException{
       App.setRoot("TampilanAwal");
   }
   
   
   public void validateLogin(){
       DatabaseConnection connectNow = new DatabaseConnection();
       Connection connectDB = connectNow.getConnection();
       
       String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + usernameTextField.getText() + "' AND password = '" + enterPasswordField.getText() + "'";
       
       try {
           Statement statement = connectDB.createStatement();
           ResultSet queryResult = statement.executeQuery(verifyLogin);
           
           while (queryResult.next()){
               if(queryResult.getInt(1) == 1) {
                   loginMessageLabel.setText("Congratulation!");
                    App.setRoot("secondary");
               } else {
                   loginMessageLabel.setText("Invalid Login. Please try again");
               }
           }
       } catch (Exception e){
           e.printStackTrace();
           e.getCause();
        }
   }
   
   
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
