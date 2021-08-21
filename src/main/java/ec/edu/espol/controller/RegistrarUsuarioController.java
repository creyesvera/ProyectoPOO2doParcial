/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class RegistrarUsuarioController implements Initializable {

    @FXML
    private Button btregresar;
    @FXML
    private TextField nombox;
    @FXML
    private TextField apelbox;
    @FXML
    private TextField organibox;
    @FXML
    private TextField emailbox;
    @FXML
    private PasswordField passwordbox;
    @FXML
    private Button btregistrar;
    @FXML
    private Button btiniciarsesion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombox.clear();
        apelbox.clear();
        organibox.clear();
        emailbox.clear();
        passwordbox.clear();
    }    

    @FXML
    private void regresar(MouseEvent event) {
        
    }

    @FXML
    private void registrar(MouseEvent event) {
        try{
            String nombres = nombox.getText();
            String apellidos = apelbox.getText();
            String correo_elec = emailbox.getText();
            String organizacion = organibox.getText();
            String clave = passwordbox.getText();
            int id=0 ;
            Usuario u = new Usuario(id, nombres, apellidos, correo_elec, organizacion, clave);
        
        }catch(Exception e){
        Alert a = new Alert(AlertType.ERROR,"El correo que ingreso no es valido");
        a.show();
        }
    }

    @FXML
    private void iniciarsesion(MouseEvent event) {
    }

}
