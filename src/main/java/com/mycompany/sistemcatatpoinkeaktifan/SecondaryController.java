package com.mycompany.sistemcatatpoinkeaktifan;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecondaryController {

    @FXML
    private Button secondaryButton;

    @FXML
    private void switchToPrimary() throws IOException {
            App.setRoot("primary");
    }
    
    @FXML
    private Button prestasiButton;
    
    @FXML
    private void switchToJabatan() throws IOException {
        App.setRoot("jabatanKegiatan");
    }
    

    @FXML
    private Button jabatanButton;
    

    @FXML
    private Button kemahasiswaanButton;
    
    @FXML
    private void switchToKemahasiswaan() throws IOException {
        App.setRoot("kemahasiswaan");
    } 
    
    @FXML
    private void switchToPrestasi() throws IOException {
        App.setRoot("prestasi");
    }
}