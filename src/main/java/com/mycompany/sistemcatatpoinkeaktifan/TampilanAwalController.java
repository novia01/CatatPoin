/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemcatatpoinkeaktifan;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TampilanAwalController {

    @FXML
    private Button adminButton;

    @FXML
    private Button mahasiswaButton;
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
     @FXML
    private void switchToAdmin() throws IOException {
        App.setRoot("loginAdmin");
    }

}