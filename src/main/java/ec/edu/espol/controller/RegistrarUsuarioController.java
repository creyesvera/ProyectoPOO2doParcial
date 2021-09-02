/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.TipoUsuario;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyectopoo2doparcial.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class RegistrarUsuarioController implements Initializable {
    private ArrayList<Usuario> usuarios = Usuario.readFile("usuarios.ser");
    private Usuario usuario;
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
    @FXML
    private RadioButton compradortype;
    @FXML
    private ToggleGroup tipo;
    @FXML
    private RadioButton vendedortype;
    @FXML
    private RadioButton ambostype;
    
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
        try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("registrarUsuario");
            App.setRoot(fxmlloader);
            IniciarSesionController sesion = fxmlloader.getController();
            if (usuario != null)
                sesion.setUsuario(usuario);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
            TipoUsuario tipo;
            if (compradortype.isSelected())
                tipo = TipoUsuario.COMPRADOR;
            else if (vendedortype.isSelected())
                tipo = TipoUsuario.VENDEDOR;
            else
                tipo = TipoUsuario.AMBOS;
            
            this.usuario = new Usuario(id, nombres, apellidos, correo_elec, organizacion, clave,tipo);
            usuarios.add(usuario);
            Usuario.saveFile("usuarios.ser", usuarios);
        
        }catch(Exception e){
        Alert a = new Alert(AlertType.ERROR,"El correo que ingreso no es valido");
        a.show();
        }
    }

    @FXML
    private void iniciarsesion(MouseEvent event) {
        try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("iniciarSesion");
            App.setRoot(fxmlloader);
            IniciarSesionController sesion = fxmlloader.getController();
            if (usuario != null)
                sesion.setUsuario(usuario);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
