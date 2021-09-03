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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class IniciarSesionController implements Initializable {
    private ArrayList<Usuario> usuarios = Usuario.readFile("usuarios.ser");
    private Usuario usuario;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emailbox.clear();
        passwordbox.clear();// TODO
    }    

    @FXML
    private void registrar(MouseEvent event) {
        FXMLLoader fxmlloader;
        try {
            fxmlloader = App.loadFXMLLoader("registrarUsuario");
            Parent root = fxmlloader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            cerrarVentana();
        } catch (IOException ex) {
        }
    }

    @FXML
    private void iniciarsesion(MouseEvent event) {
        //validar correo y contrase;a
        String email = emailbox.getText();
        String password = passwordbox.getText();
        this.setUsuario(Usuario.searchByCorreoYClave(usuarios, email, password)); //dentro de searchByCorreoYClave la clave se convierte a hash y se compara con las otras claves de la lista (las cuales ya estan en hash)
        if(getUsuario()!= null){
        if (usuario.getTipo() == TipoUsuario.COMPRADOR)
            compradorsesion();
        else if (usuario.getTipo() == TipoUsuario.VENDEDOR)
            vendedorsesion();
        else 
            ambossesion();
        }
        else{
            Alert a = new Alert(Alert.AlertType.INFORMATION,"El correo y/o la clave son incorrectos");
            a.show();
        }
            
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    private void cerrarVentana(){
        Stage myStage = (Stage) this.btiniciarsesion.getScene().getWindow();
        myStage.close();
    }
    
    private void compradorsesion(){
    try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("comprador");
            Parent root = fxmlloader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            
            CompradorController sesion = fxmlloader.getController();
            if (usuario != null)
                sesion.setUsuario(usuario);///Comprador Controller debe tener atributo vendedor
            
            cerrarVentana();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void vendedorsesion(){
    try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("vendedor");
            Parent root = fxmlloader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            /*
            VendedorController sesion = fxmlloader.getController();
            if (usuario != null)
                sesion.setUsuario(usuario);
            */
            cerrarVentana();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void ambossesion(){
    
    }
    
    private void cerrarVentana(){
        Stage myStage = (Stage) this.btiniciarsesion.getScene().getWindow();
        myStage.close();
    }
}
