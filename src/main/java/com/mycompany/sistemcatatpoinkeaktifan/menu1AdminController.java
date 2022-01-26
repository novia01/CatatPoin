/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemcatatpoinkeaktifan;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author acer
 */
public class menu1AdminController implements Initializable{
     @FXML
    private TextField IDTextField;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;
    
     @FXML
    private TableView<menu1Admin> tvMenu1Admin;

    @FXML
    private TableColumn<menu1Admin, Integer> colID;

    @FXML
    private TableColumn<menu1Admin, String> colFirst;

    @FXML
    private TableColumn<menu1Admin, String> colLast;

    @FXML
    private TableColumn<menu1Admin, String> colUsername;

    @FXML
    private TableColumn<menu1Admin, String> colPassword;

    @FXML
    private Button deleteButton;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button backButton;
    
     @FXML
    private void cancelSwitchTampilan() throws IOException {
        App.setRoot("menuAdmin");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showmenu1Admin();
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event){
        if(event.getSource() ==  insertButton){
            insertRecord();
        }else if (event.getSource() == updateButton){
            updateRecord();
        }else if(event.getSource() == deleteButton){
            deleteButton();
    } 
        
    }
    
        public ObservableList<menu1Admin> getmenu1AdminList(){
        ObservableList<menu1Admin> menu1AdminList = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "SELECT * FROM user_account";
        Statement st;
        ResultSet rs;
        
        try{
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            menu1Admin user_account;
            while(rs.next()){
                user_account = new menu1Admin(rs.getInt("account_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"),rs.getString("password"));
                menu1AdminList.add(user_account);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return menu1AdminList;
    }
    
    public void showmenu1Admin(){
        ObservableList<menu1Admin> list = getmenu1AdminList();
        
        colID.setCellValueFactory(new PropertyValueFactory<menu1Admin, Integer>("account_id"));
        colFirst.setCellValueFactory(new PropertyValueFactory<menu1Admin, String>("firstname"));
        colLast.setCellValueFactory(new PropertyValueFactory<menu1Admin, String>("lastname"));
        colUsername.setCellValueFactory(new PropertyValueFactory<menu1Admin, String>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<menu1Admin, String>("password"));
        
        tvMenu1Admin.setItems(list);
    }
    
    private void insertRecord(){
        String query = "INSERT INTO user_account VALUES (" + IDTextField.getText() + ", '" + firstnameTextField.getText() + "', '" + lastnameTextField.getText() + "' , '" + usernameTextField.getText()+ "', '" + passwordTextField.getText()+ "')";
        executeQuery(query);
        showmenu1Admin();
    }
    
    private void updateRecord(){
        String query = "UPDATE user_account SET firstname = '" + firstnameTextField.getText() + "',  lastname = '" + lastnameTextField.getText() + "' , username = '" + usernameTextField.getText() + "' , password = '" + passwordTextField.getText()+ "' WHERE account_id = " + IDTextField.getText() + "";
        executeQuery(query);
        showmenu1Admin();
        
    }
    
    private void deleteButton(){
        String query = "DELETE FROM user_account WHERE account_id = '" + IDTextField.getText() + "'";
        executeQuery(query);
        showmenu1Admin();
    }


    private void executeQuery(String query) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement st;
        try{
            st = connectDB.createStatement();
            st.execute(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
