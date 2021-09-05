/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DetalleVehiculoController implements Initializable {

    @FXML
    private ImageView imgVehiculo;
    @FXML
    private TextField txtPrecioSug;
    @FXML
    private TextField txtPrecioOfer;
    @FXML
    private Button ofertar;
    @FXML
    private VBox vbInfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ofertar(ActionEvent event) {
    }
    
}
