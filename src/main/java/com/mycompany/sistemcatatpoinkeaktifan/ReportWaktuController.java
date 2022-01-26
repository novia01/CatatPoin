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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReportWaktuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button backButton;
    
    @FXML
    private TableView<Prestasi> PrestasiTableView;

    @FXML
    private TableColumn<Prestasi, Integer> colIDPrestasi;
 
    @FXML
    private TableColumn<Prestasi, String> colJenisPrestasi;
    
    @FXML
    private TableColumn<Prestasi, String> colTanggal;
    
    @FXML
    private TableView<Jabatan> jabatanTableView;

    @FXML
    private TableColumn<Jabatan, Integer> colIDJabatan;

    @FXML
    private TableColumn<Jabatan, String> colJenisJabatan;
    
    @FXML
    private TableColumn<Jabatan, String> colMasa;
    
    @FXML
    private void switchToMenuAdmin() throws IOException {
        App.setRoot("menuAdmin");
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
        
        colIDPrestasi.setCellValueFactory(new PropertyValueFactory<Prestasi, Integer>("IDPrestasi"));
        colJenisPrestasi.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("JenisPrestasi"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("Tanggal"));
        
        
        PrestasiTableView.setItems(list);
    }
    public ObservableList<Jabatan> getJabatanList(){
        ObservableList<Jabatan> kegiatanjabatanList = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "SELECT * FROM KegiatanJabatan";
        Statement st;
        ResultSet rs;
        
        try{
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            Jabatan kegiatanjabatan;
            while(rs.next()){
                kegiatanjabatan = new Jabatan(rs.getInt("IDJabatan"), rs.getString("Organisasi"), rs.getString("Tingkat"), rs.getString("JenisJabatan"), rs.getString("Keterangan"), rs.getString("MasaJabatan"), rs.getInt("Poin"));    
                kegiatanjabatanList.add(kegiatanjabatan);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kegiatanjabatanList;
    }
    public void showJabatan(){
        ObservableList<Jabatan> list = getJabatanList();
        
        colIDJabatan.setCellValueFactory(new PropertyValueFactory<Jabatan, Integer>("IDJabatan"));
        colJenisJabatan.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("JenisJabatan"));
        colMasa.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("MasaJabatan"));
        
        
        jabatanTableView.setItems(list);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showPrestasi();
        showJabatan();
    }    
    
}
