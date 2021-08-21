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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class IniciarSesionController implements Initializable {

    @FXML
    private Button btregresar;
    @FXML
    private Button btregistrar;
    @FXML
    private Button btiniciarsesion;
    @FXML
    private TextField emailbox;
    @FXML
    private PasswordField passwordbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emailbox.clear();
        passwordbox.clear();// TODO
    }    

    @FXML
    private void regresar(MouseEvent event) {
    }

    @FXML
    private void registrar(MouseEvent event) {
    }

    @FXML
    private void iniciarsesion(MouseEvent event) {
    }
    
}
