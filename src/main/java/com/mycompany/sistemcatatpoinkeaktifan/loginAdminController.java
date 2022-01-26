/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemcatatpoinkeaktifan;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class loginAdminController {

     @FXML
    private TextField usernameTextField;

    @FXML
    private TextField enterPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;
    
    @FXML
    private void cancelSwitchTampilan() throws IOException {
        App.setRoot("TampilanAwal");
    }
    
    public void loginButtonOnAction(ActionEvent event){
       
       if(usernameTextField.getText().isBlank()== false && enterPasswordField.getText().isBlank() == false){
           validateLogin();
       }else {
           loginMessageLabel.setText("Please enter username and password");
       }
   }
    
     public void validateLogin(){
       DatabaseConnection connectNow = new DatabaseConnection();
       Connection connectDB = connectNow.getConnection();
       
       String verifyLogin = "SELECT count(1) FROM admin_account WHERE username = '" + usernameTextField.getText() + "' AND password = '" + enterPasswordField.getText() + "'";
       
       try {
           Statement statement = connectDB.createStatement();
           ResultSet queryResult = statement.executeQuery(verifyLogin);
           
           while (queryResult.next()){
               if(queryResult.getInt(1) == 1) {
                   loginMessageLabel.setText("Congratulation!");
                    App.setRoot("menuAdmin");
               } else {
                   loginMessageLabel.setText("Invalid Login. Please try again");
               }
           }
       } catch (Exception e){
           e.printStackTrace();
           e.getCause();
        }
   }

}

