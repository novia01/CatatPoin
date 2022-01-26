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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author acer
 */
public class KemahasiswaanController implements Initializable{
    
    @FXML
    private Button cancelButton;
    
     @FXML
    private void switchToSecondary() throws IOException {
            App.setRoot("secondary");
    }
 

    @FXML
    private TextField IDTextField;

    @FXML
    private ComboBox<String> comboJenis;

    @FXML
    private Label labelSifat;

    @FXML
    private Label labelPoin;

    @FXML
    private TableView<Kemahasiswaan> kemahasiswaanTableView;

    @FXML
    private TableColumn<Kemahasiswaan, Integer> colID;

    @FXML
    private TableColumn<Kemahasiswaan, String> colJenis;

    @FXML
    private TableColumn<Kemahasiswaan, Integer> colPoin;

    @FXML
    private TableColumn<Kemahasiswaan, String> colSifat;

    @FXML
    private Button updateButton;

    @FXML
    private Button insertButton;

    @FXML
    private Button deleteButton;
    
    @FXML
    private TextField filterField;
    
    
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
    
    ObservableList<String> list = FXCollections.observableArrayList();
    private void organisasi(){
       list.removeAll(list);
       String a="Orientasi Kehidupan Akademika";
       String b="Pengembangan Pribadi/Diri";
       String c="Pelatihan Kepemimpinan";
       String d="Keakraban Mahasiswa Prodi";
       String e="Upacara Peringatan hari besar Nasional";
       String f="Studi banding Luar Kota";
       String g="Voluntir Kerja Sosial (min 36 jam)";
       String h="Mentoring";
       list.addAll(a,b,c,d,e,f,g,h);
       comboJenis.getItems().addAll(list);
   }
    
    public ObservableList<Kemahasiswaan> getKemahasiswaanList(){
        ObservableList<Kemahasiswaan> kegiatankemahasiswaanList = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "SELECT * FROM kegiatankemahasiswaan";
        Statement st;
        ResultSet rs;
        
        try{
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            Kemahasiswaan kegiatankemahasiswaan;
            while(rs.next()){
                kegiatankemahasiswaan = new Kemahasiswaan(rs.getInt("IDKegiatan"), rs.getString("JenisKegiatan"), rs.getInt("Poin"), rs.getString("Sifat"));
                kegiatankemahasiswaanList.add(kegiatankemahasiswaan);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kegiatankemahasiswaanList;
    }    
    
   public void showKemahasiswaan(){

        ObservableList<Kemahasiswaan> list = getKemahasiswaanList();

        colID.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, Integer>("IDKegiatan"));
        colJenis.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, String>("JenisKegiatan"));
        colPoin.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, Integer>("Poin"));
        colSifat.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, String>("Sifat"));
        
        kemahasiswaanTableView.setItems(list);
        search();
    }
    private void insertRecord(){
        String query = "INSERT INTO kegiatankemahasiswaan VALUES (" + IDTextField.getText() + ", '" + comboJenis.getValue() + "'," + labelPoin.getText()+ ",'" + labelSifat.getText()  + "')";
        executeQuery(query);
         search();
        showKemahasiswaan();
    }
    
    private void updateRecord(){
        String query = "UPDATE kegiatankemahasiswaan SET JenisKegiatan = '" + comboJenis.getValue() + "', Poin = " + labelPoin.getText() + " , Sifat = '" + labelSifat.getText() + "' WHERE IDKegiatan = " + IDTextField.getText() + "";
        executeQuery(query);
         search();
        showKemahasiswaan();
        
    }
    
    private void deleteButton(){
        String query = "DELETE FROM kegiatankemahasiswaan WHERE IDKegiatan = '" + IDTextField.getText() + "'";
        executeQuery(query);
        search();
        showKemahasiswaan();
        
    }
    
    @FXML
    void search(){
        ObservableList<Kemahasiswaan> list = getKemahasiswaanList();

        colID.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, Integer>("IDKegiatan"));
        colJenis.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, String>("JenisKegiatan"));
        colPoin.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, Integer>("Poin"));
        colSifat.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, String>("Sifat"));
        
        kemahasiswaanTableView.setItems(list);
        FilteredList<Kemahasiswaan> filteredData = new FilteredList<>(list, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(kemahasiswaan -> {
                // If filter text is empty, display all data.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (kemahasiswaan.getJenisKegiatan().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches the JenisKegiatan.
                } else if (kemahasiswaan.getSifat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches the Sifat.
                }
                return false; // Does not match.
            });
        });
        SortedList<Kemahasiswaan> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(kemahasiswaanTableView.comparatorProperty());
        kemahasiswaanTableView.setItems(sortedData);
        
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        organisasi();
        search();
        showKemahasiswaan();
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
    
     
    
    public void comboChanged(ActionEvent event){
        String jenis = comboJenis.getValue();
        if(jenis.equals("Orientasi Kehidupan Akademika")){
            labelPoin.setText("10");
            labelSifat.setText("Wajib");
        }else if(jenis.equals("Pengembangan Pribadi/Diri")){
            labelPoin.setText("10");
            labelSifat.setText("Wajib");
        }else if(jenis.equals("Pelatihan Kepemimpinan")){
            labelPoin.setText("10");
            labelSifat.setText("Wajib");
        }else if(jenis.equals("Keakraban Mahasiswa Prodi")){
            labelPoin.setText("5");
            labelSifat.setText("Sukarela");
        }else if(jenis.equals("Upacara Peringatan hari besar Nasional")){
            labelPoin.setText("3/hadir");
            labelSifat.setText("Wajib");
        }else if(jenis.equals("Studi banding Luar Kota")){
            labelPoin.setText("3");
            labelSifat.setText("Sukarela");
        }else if(jenis.equals("Voluntir Kerja Sosial (min 36 jam)")){
            labelPoin.setText("10");
            labelSifat.setText("Sukarela");
        }else if(jenis.equals("Mentoring")){
            labelPoin.setText("8");
            labelSifat.setText("Wajib");
        }
    }
    
    
}
