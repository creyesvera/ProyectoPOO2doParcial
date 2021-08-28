/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyectopoo2doparcial.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private ArrayList<Usuario> usuarios = Usuario.readFile("usuarios.ser");
    private Usuario usuario;
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
        try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("registrarUsuario.fxml");
            App.setRoot(fxmlloader);
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void registrar(MouseEvent event) {
        //App.setRoot(fxmlLoader);
    }

    @FXML
    private void iniciarsesion(MouseEvent event) {
        //validar correo y contrase;a
        String email = emailbox.getText();
        String password = passwordbox.getText();
        this.setUsuario(Usuario.searchByCorreoYClave(usuarios, email, password));
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
