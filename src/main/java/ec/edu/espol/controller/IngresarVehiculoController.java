/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class IngresarVehiculoController implements Initializable {

    @FXML
    private Button btregresar;
    @FXML
    private Button btingresar;
    @FXML
    private TextField placabox;
    @FXML
    private TextField marcabox;
    @FXML
    private TextField modelobox;
    @FXML
    private TextField tipo_motorbox;
    @FXML
    private TextField yearbox;
    @FXML
    private TextField recorridobox;
    @FXML
    private TextField colorbox;
    @FXML
    private TextField tipo_combustiblebox;
    @FXML
    private TextField vidriosbox;
    @FXML
    private TextField transmicionbox;
    @FXML
    private TextField traccionbox;
    @FXML
    private TextField preciobox;
    @FXML
    private TextField imgbox;
    @FXML
    private Button btimport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regresar(MouseEvent event) {
    }

    @FXML
    private void ingresar(MouseEvent event) {
    }

    @FXML
    private void importar(MouseEvent event) {
    }
    
}
