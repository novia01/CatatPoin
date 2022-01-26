/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemcatatpoinkeaktifan;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author acer
 */
public class JabatanKemahasiswaan {
    @FXML
    private Button cancelButton;
    
     @FXML
    private void switchToSecondary() throws IOException {
            App.setRoot("secondary");
    }
}
