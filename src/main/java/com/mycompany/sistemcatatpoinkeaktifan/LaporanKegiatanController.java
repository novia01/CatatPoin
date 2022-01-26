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
 * FXML Controller class
 *
 * @author NATASHA N HARJANTO
 */
public class LaporanKegiatanController implements Initializable {

    @FXML
    private Button backButton;
    
    @FXML
    private TableView<Kemahasiswaan> kemahasiswaanTableView;

    @FXML
    private TableColumn<Kemahasiswaan, Integer> colIDKemahasiswaan;

    @FXML
    private TableColumn<Kemahasiswaan, String> colJenisKemahasiswaan;

    @FXML
    private TableColumn<Kemahasiswaan, Integer> colPoinKemahasiswaan;

    @FXML
    private TableColumn<Kemahasiswaan, String> colSifatKemahasiswaan;
    
    @FXML
    private TableColumn<Kemahasiswaan, String> colStatusKemahasiswaan;
    
    @FXML
    private TableView<Prestasi> PrestasiTableView;

    @FXML
    private TableColumn<Prestasi, Integer> colIDPrestasi;

    @FXML
    private TableColumn<Prestasi, String> colJenisPrestasi;

    @FXML
    private TableColumn<Prestasi, String> colTingkatPrestasi;

    @FXML
    private TableColumn<Prestasi, String> colKeteranganPrestasi;
    
    @FXML
    private TableColumn<Prestasi, String> colTanggal;
    
    @FXML
    private TableColumn<Prestasi, Integer> colPoinPrestasi;
    
    @FXML
    private TableColumn<Prestasi, String> colStatusPrestasi;
    
    @FXML
    private TableView<Jabatan> jabatanTableView;

    @FXML
    private TableColumn<Jabatan, Integer> colIDJabatan;

    @FXML
    private TableColumn<Jabatan, String> colOrganisasi;
    
    @FXML
    private TableColumn<Jabatan, String> colTingkatJabatan;
    

    @FXML
    private TableColumn<Jabatan, String> colJenisJabatan;

    @FXML
    private TableColumn<Jabatan, String> colKeteranganJabatan;

    @FXML
    private TableColumn<Jabatan, String> colMasa;

    @FXML
    private TableColumn<Jabatan, Integer> colPoinJabatan;
    
    @FXML
    private TableColumn<Jabatan, String> colStatusJabatan;
    
    
    @FXML
    private void switchToMenuAdmin() throws IOException {
        App.setRoot("menuAdmin");
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
                kegiatankemahasiswaan = new Kemahasiswaan(rs.getInt("IDKegiatan"), rs.getString("JenisKegiatan"), rs.getInt("Poin"), rs.getString("Sifat"), rs.getString("Status"));
                kegiatankemahasiswaanList.add(kegiatankemahasiswaan);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kegiatankemahasiswaanList;
    }
    public void showKemahasiswaan(){

        ObservableList<Kemahasiswaan> list = getKemahasiswaanList();

        colIDKemahasiswaan.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, Integer>("IDKegiatan"));
        colJenisKemahasiswaan.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, String>("JenisKegiatan"));
        colPoinKemahasiswaan.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, Integer>("Poin"));
        colSifatKemahasiswaan.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, String>("Sifat"));
        colStatusKemahasiswaan.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, String>("Status"));
        
        kemahasiswaanTableView.setItems(list);
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
                kegiatanprestasi = new Prestasi(rs.getInt("IDPrestasi"), rs.getString("JenisPrestasi"), rs.getString("TingkatPrestasi"), rs.getString("Keterangan"),rs.getString("Tanggal"),rs.getInt("Poin"), rs.getString("Sifat"));
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
        colTingkatPrestasi.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("TingkatPrestasi"));
        colKeteranganPrestasi.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("Keterangan"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("Tanggal"));
        colPoinPrestasi.setCellValueFactory(new PropertyValueFactory<Prestasi, Integer>("Poin"));
        colStatusPrestasi.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("Status"));

        
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
                kegiatanjabatan = new Jabatan(rs.getInt("IDJabatan"), rs.getString("Organisasi"), rs.getString("Tingkat"), rs.getString("JenisJabatan"), rs.getString("Keterangan"), rs.getString("MasaJabatan"), rs.getInt("Poin"), rs.getString("Sifat"));    
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
        colOrganisasi.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("Organisasi"));
        colTingkatJabatan.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("Tingkat"));
        colJenisJabatan.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("JenisJabatan"));
        colKeteranganJabatan.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("Keterangan"));
        colMasa.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("MasaJabatan"));
        colPoinJabatan.setCellValueFactory(new PropertyValueFactory<Jabatan, Integer>("Poin"));
        colStatusJabatan.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("Status"));
        
        
        jabatanTableView.setItems(list);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showKemahasiswaan();
        showPrestasi();
        showJabatan();
    }    
    
}
