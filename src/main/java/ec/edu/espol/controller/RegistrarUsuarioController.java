/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.TipoUsuario;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.excepciones.ValueTypeException;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.proyectopoo2doparcial.App;
import static ec.edu.espol.util.Alarmas.alertaInformacion;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class RegistrarUsuarioController implements Initializable {
    private ArrayList<Usuario> usuarios;    
    
    
    private Usuario usuario;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarios = Usuario.readFile("usuarios.ser");
        nombox.clear();
        apelbox.clear();
        organibox.clear();
        emailbox.clear();
        passwordbox.clear();
    }    

    private void regresar(MouseEvent event) {
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

    @FXML
    private void registrar(MouseEvent event) throws ValueTypeException{
        try{
            String nombres = nombox.getText();
            String apellidos = apelbox.getText();
            String correo_elec = emailbox.getText();
            String organizacion = organibox.getText();
            String clave = passwordbox.getText();
            if(Usuario.searchBycorreoID(correo_elec,usuarios)!=-1)
                throw new ValueTypeException("El usuario ya existe");
            
            int id =  Usuario.nextID("usuarios.ser");
            TipoUsuario tipoU;
            if (compradortype.isSelected())
                tipoU = TipoUsuario.COMPRADOR;
            else if (vendedortype.isSelected())
                tipoU = TipoUsuario.VENDEDOR;
            else
                tipoU = TipoUsuario.AMBOS;
            
            this.usuario = new Usuario(id, nombres, apellidos, correo_elec, organizacion, clave,tipoU);
            usuarios.add(usuario);
            Usuario.saveFile("usuarios.ser", usuarios);
            alertaInformacion("USUARIO REGISTRADO","Se ha resgistrado correctamente el usuario");
        }catch(ValueTypeException e){
            Alert a = new Alert(AlertType.ERROR,e.getMessage());
            a.show();
        }
    }

    @FXML
    private void iniciarsesion(MouseEvent event) {
        try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("iniciarSesion");
            Parent root = fxmlloader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            /*
            IniciarSesionController sesion = fxmlloader.getController();
            if (usuario != null)
                sesion.setUsuario(usuario);
            */
            cerrarVentana();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void cerrarVentana(){
        Stage myStage = (Stage) this.btiniciarsesion.getScene().getWindow();
        myStage.close();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
