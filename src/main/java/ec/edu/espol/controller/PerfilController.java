/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.TipoUsuario;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.ValueTypeException;
import ec.edu.espol.proyectopoo2doparcial.App;
import ec.edu.espol.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class PerfilController implements Initializable {
    private Usuario usuario;
    @FXML
    private Button btregresar;
    @FXML
    private Button btguargar;
    @FXML
    private Text txtoldpassword;
    @FXML
    private Text nombres;
    @FXML
    private Text apellidos;
    @FXML
    private Text organizacion;
    @FXML
    private PasswordField passwordbox;
    @FXML
    private RadioButton compradortype;
    @FXML
    private ToggleGroup tipo;
    @FXML
    private RadioButton vendedortype;
    @FXML
    private RadioButton ambostype;
    @FXML
    private Text correo_elec;
    @FXML
    private Button btchngpassword;
    @FXML
    private Text txtnewpassword;
    @FXML
    private PasswordField newpasswordbox;
    @FXML
    private GridPane gridpane;
    @FXML
    private Button btcancelar;

    private PerfilController pC;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
<<<<<<< HEAD
        pC = this;
        //usuario = new Usuario(5,"s","s","camila@gmail.com","s","s",TipoUsuario.COMPRADOR); //usuario de prueba
=======
>>>>>>> 47c964a6019e7b8cb03d910730127eb885a78f52
        
        
        
    }    
    
    private void configUsuario(){
        nombres.setText(usuario.getNombres());
        apellidos.setText(usuario.getApellidos());
        organizacion.setText(usuario.getOrganizacion());
        correo_elec.setText(usuario.getCorreo_elec());
        if(usuario.getTipo()== TipoUsuario.COMPRADOR)
            tipo.selectToggle(compradortype);
        else if(usuario.getTipo()== TipoUsuario.VENDEDOR)
            tipo.selectToggle(vendedortype);
        else
            tipo.selectToggle(ambostype);
        gridpane.getChildren().remove(txtnewpassword);
        gridpane.getChildren().remove(txtoldpassword);
        gridpane.getChildren().remove(newpasswordbox);
        gridpane.getChildren().remove(passwordbox);
        gridpane.getChildren().remove(btcancelar);
<<<<<<< HEAD
    }
=======
    
        
        
    }    
>>>>>>> 47c964a6019e7b8cb03d910730127eb885a78f52

    @FXML
    private void regresar(MouseEvent event) {
        try{
            FXMLLoader fxmlloader = App.loadFXMLLoader("opcionesUsuario");
            Parent root = fxmlloader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            OpcionesUsuarioController ouc = fxmlloader.getController();
            ouc.recibirParametros(usuario);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            cerrarVentana();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void guardar(MouseEvent event) {
            if (compradortype.isSelected())
                usuario.setTipo(TipoUsuario.COMPRADOR);
            else if (vendedortype.isSelected())
                usuario.setTipo(TipoUsuario.VENDEDOR);
            else
                usuario.setTipo(TipoUsuario.AMBOS);
            if(gridpane.getChildren().contains(btcancelar)){
                if(usuario.getClave().equals(Util.toHexString(Util.getSHA(passwordbox.getText())))){
                    usuario.setClave(newpasswordbox.getText());
                    newpasswordbox.clear();
                    passwordbox.clear();
                    cancelarcambio(event);
                }
                else{
                    Alert a = new Alert(Alert.AlertType.ERROR,"La contraseña es incorrecta");
                    a.show();   
                }
            }
    }

    @FXML
    private void cambiarpassword(MouseEvent event) {
        gridpane.getChildren().remove(btchngpassword);
        gridpane.getChildren().add(btcancelar);
        gridpane.getChildren().add(txtnewpassword);
        gridpane.getChildren().add(txtoldpassword);
        gridpane.getChildren().add(newpasswordbox);
        gridpane.getChildren().add(passwordbox);
        
        
    }
    
    @FXML
    private void cancelarcambio(MouseEvent event) {
        gridpane.getChildren().remove(txtnewpassword);
        gridpane.getChildren().remove(txtoldpassword);
        gridpane.getChildren().remove(newpasswordbox);
        gridpane.getChildren().remove(passwordbox);
        gridpane.getChildren().remove(btcancelar);
        gridpane.getChildren().add(btchngpassword);
    }
    
    private void cerrarVentana(){
        Stage myStage = (Stage) this.btregresar.getScene().getWindow();
        myStage.close();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        configUsuario();
    }
    
}
