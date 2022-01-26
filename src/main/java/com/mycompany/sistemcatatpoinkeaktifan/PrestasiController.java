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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author acer
 */
public class PrestasiController implements Initializable{
    
    @FXML
    private Button cancelButton;
    
     @FXML
    private void switchToSecondary() throws IOException {
            App.setRoot("secondary");
    }
    
    @FXML
    private TextField filterField;
      
      @FXML
    private TextField IDTextField;

    @FXML
    private TextField KeteranganTextField;
    
      @FXML
    private DatePicker TanggalTextField;
    
     @FXML
    private Label labelPoin;
    @FXML
    private TableView<Prestasi> PrestasiTableView;

    @FXML
    private TableColumn<Prestasi, Integer> colID;

    @FXML
    private TableColumn<Prestasi, String> colJenis;

    @FXML
    private TableColumn<Prestasi, String> colTingkat;

    @FXML
    private TableColumn<Prestasi, String> colKeterangan;
    
    @FXML
    private TableColumn<Prestasi, String> colTanggal;
    
    @FXML
    private TableColumn<Prestasi, String> colStatusPrestasi;
    
    @FXML
    private TableColumn<Prestasi, Integer> colPoin;

    @FXML
    private Button updateButton;

    @FXML
    private Button insertButton;

    @FXML
    private Button deleteButton;
    
    @FXML 
    public ComboBox<String> combobox;
    
     @FXML 
    public ComboBox<String> combobox1;
    
    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<String> list1 = FXCollections.observableArrayList();
    
    private void lingkup(){
       list1.removeAll(list1);
       String a="Intern";
       String b="DIY";
       String c="Nasional";
       String d="Internasional";
       list1.addAll(a,b,c,d);
       combobox1.getItems().addAll(list1);
   }
   
    @FXML
    private void jenisprestasi(){
       list.removeAll(list);
       String a="Juara I";
       String b="Juara II";
       String c="Juara III";
       String d="Juara Harapan I";
       String e="Juara Harapan II";
       String f="Pembicara Seminar";
       String g="Moderator Seminar";
       String h="Peserta Seminar/Utusan";
       String i="Penulis Artikel";
       list.addAll(a,b,c,d,e,f,g,h,i);
       combobox.getItems().addAll(list);
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
    
    public ObservableList<Prestasi> getPrestasiList(){
        ObservableList<Prestasi> kegiatanprestasiList = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "SELECT * FROM KegiatanPrestasi";
        Statement st;
        ResultSet rs;
        
        try{
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            Prestasi kegiatanprestasi;
            while(rs.next()){
                kegiatanprestasi = new Prestasi(rs.getInt("IDPrestasi"), rs.getString("JenisPrestasi"), rs.getString("TingkatPrestasi"), rs.getString("Keterangan"),rs.getString("Tanggal"),rs.getInt("Poin"));
                kegiatanprestasiList.add(kegiatanprestasi);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kegiatanprestasiList;
    }
    
    public void showPrestasi(){
        ObservableList<Prestasi> list = getPrestasiList();
        
        colID.setCellValueFactory(new PropertyValueFactory<Prestasi, Integer>("IDPrestasi"));
        colJenis.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("JenisPrestasi"));
        colTingkat.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("TingkatPrestasi"));
        colKeterangan.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("Keterangan"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("Tanggal"));
        colPoin.setCellValueFactory(new PropertyValueFactory<Prestasi, Integer>("Poin"));
        
        
        PrestasiTableView.setItems(list);
        search();
    }
    
    private void insertRecord(){
        String query = "INSERT INTO KegiatanPrestasi VALUES (" + IDTextField.getText() + ", '" + combobox.getValue() + "', '" + combobox1.getValue() + "', '" + KeteranganTextField.getText()+ "', '" + TanggalTextField.getValue()+ "'," + labelPoin.getText()+ ")";
        executeQuery(query);
        search();
        showPrestasi();
    }
    
    private void updateRecord(){
        String query = "UPDATE KegiatanPrestasi SET JenisPrestasi = '" + combobox.getValue() + "',  TingkatPrestasi = '" + combobox.getValue() + "' , Keterangan = '" + KeteranganTextField.getText() + "' , Tanggal = '" + TanggalTextField.getValue() + "' , Poin = " + labelPoin.getText() + " WHERE IDPrestasi = " + IDTextField.getText() + "";
        executeQuery(query);
        search();
        showPrestasi();
        
    }
    
    private void deleteButton(){
        String query = "DELETE FROM KegiatanPrestasi WHERE IDPrestasi = '" + IDTextField.getText() + "'";
        executeQuery(query);
        search();
        showPrestasi();
    }
    
     @FXML
    void search(){
        ObservableList<Prestasi> list = getPrestasiList();
        
        colID.setCellValueFactory(new PropertyValueFactory<Prestasi, Integer>("IDPrestasi"));
        colJenis.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("JenisPrestasi"));
        colTingkat.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("TingkatPrestasi"));
        colKeterangan.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("Keterangan"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("Tanggal"));
        colPoin.setCellValueFactory(new PropertyValueFactory<Prestasi, Integer>("Poin"));
        
        PrestasiTableView.setItems(list);
        FilteredList<Prestasi> filteredData = new FilteredList<>(list, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(prestasi -> {
                // If filter text is empty, display all data.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (prestasi.getJenisPrestasi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches the JenisPrestasi
                } else if (prestasi.getKeterangan().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches the Keterangan
                }else if (prestasi.getTingkatPrestasi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches the Tingkat
                }
                return false; // Does not match.
            });
        });
        SortedList<Prestasi> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(PrestasiTableView.comparatorProperty());
        PrestasiTableView.setItems(sortedData);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lingkup();
        jenisprestasi();
        search();
        showPrestasi();
        
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
    
    @FXML
    public void comboChanged(ActionEvent event){
        //String jenis = combobox.getSelectionModel().getSelectedItem();
        String jenis = combobox.getValue();
        String tingkat = combobox1.getValue();
        if(jenis.equals("Juara I") && tingkat.equals("Intern")){
            labelPoin.setText("8");
        }else if(jenis.equals("Juara II") && tingkat.equals("Intern")){
            labelPoin.setText("7");
        }else if(jenis.equals("Juara III") && tingkat.equals("Intern")){
            labelPoin.setText("6");
        }else if(jenis.equals("Juara Harapan I") && tingkat.equals("Intern")){
            labelPoin.setText("5");
        }else if(jenis.equals("Juara Harapan II") && tingkat.equals("Intern")){
            labelPoin.setText("4");
        }else if(jenis.equals("Pembicara Seminar") && tingkat.equals("Intern")){
            labelPoin.setText("7");
        }else if(jenis.equals("Moderator Seminar") && tingkat.equals("Intern")){
            labelPoin.setText("4");
        }else if(jenis.equals("Peserta Seminar/Utusan") && tingkat.equals("Intern")){
            labelPoin.setText("2");
        }else if(jenis.equals("Penulis Artikel") && tingkat.equals("Intern")){
            labelPoin.setText("5");
        }else if(jenis.equals("Juara I") && tingkat.equals("DIY")){
            labelPoin.setText("12");
        }else if(jenis.equals("Juara II") && tingkat.equals("DIY")){
            labelPoin.setText("10");
        }else if(jenis.equals("Juara III") && tingkat.equals("DIY")){
            labelPoin.setText("8");
        }else if(jenis.equals("Juara Harapan I") && tingkat.equals("DIY")){
            labelPoin.setText("6");
        }else if(jenis.equals("Juara Harapan II") && tingkat.equals("DIY")){
            labelPoin.setText("5");
        }else if(jenis.equals("Pembicara Seminar") && tingkat.equals("DIY")){
            labelPoin.setText("10");
        }else if(jenis.equals("Moderator Seminar") && tingkat.equals("DIY")){
            labelPoin.setText("6");
        }else if(jenis.equals("Peserta Seminar/Utusan") && tingkat.equals("DIY")){
            labelPoin.setText("3");
        }else if(jenis.equals("Penulis Artikel") && tingkat.equals("DIY")){
            labelPoin.setText("10");
        }else if(jenis.equals("Juara I") && tingkat.equals("Nasional")){
            labelPoin.setText("16");
        }else if(jenis.equals("Juara II") && tingkat.equals("Nasional")){
            labelPoin.setText("13");
        }else if(jenis.equals("Juara III") && tingkat.equals("Nasional")){
            labelPoin.setText("10");
        }else if(jenis.equals("Juara Harapan I") && tingkat.equals("Nasional")){
            labelPoin.setText("8");
        }else if(jenis.equals("Juara Harapan II") && tingkat.equals("Nasional")){
            labelPoin.setText("6");
        }else if(jenis.equals("Pembicara Seminar") && tingkat.equals("Nasional")){
            labelPoin.setText("14");
        }else if(jenis.equals("Moderator Seminar") && tingkat.equals("Nasional")){
            labelPoin.setText("8");
        }else if(jenis.equals("Peserta Seminar/Utusan") && tingkat.equals("Nasional")){
            labelPoin.setText("4");
        }else if(jenis.equals("Penulis Artikel") && tingkat.equals("Nasional")){
            labelPoin.setText("15");
        }else if(jenis.equals("Juara I") && tingkat.equals("Internasional")){
            labelPoin.setText("24");
        }else if(jenis.equals("Juara II") && tingkat.equals("Internasional")){
            labelPoin.setText("20");
        }else if(jenis.equals("Juara III") && tingkat.equals("Internasional")){
            labelPoin.setText("12");
        }else if(jenis.equals("Juara Harapan I") && tingkat.equals("Internasional")){
            labelPoin.setText("13");
        }else if(jenis.equals("Juara Harapan II") && tingkat.equals("Internasional")){
            labelPoin.setText("10");
        }else if(jenis.equals("Pembicara Seminar") && tingkat.equals("Internasional")){
            labelPoin.setText("20");
        }else if(jenis.equals("Moderator Seminar") && tingkat.equals("Internasional")){
            labelPoin.setText("10");
        }else if(jenis.equals("Peserta Seminar/Utusan") && tingkat.equals("Internasional")){
            labelPoin.setText("5");
        }else if(jenis.equals("Penulis Artikel") && tingkat.equals("Internasional")){
            labelPoin.setText("20");
        }
    }
    
}
