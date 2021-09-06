/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyectopoo2doparcial.App;
import static ec.edu.espol.util.Alarmas.alertaAceptar;
import static ec.edu.espol.util.Alarmas.alertaError;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DetalleOfertaController implements Initializable {

    @FXML
    private Label lblnombre;
    @FXML
    private Label lblApellido;
    @FXML
    private Label lblCorreo;
    @FXML
    private Label lblPofertado;
    @FXML
    private Label lblPecioPedido;
    
    private Usuario dueno;
    private Oferta oferta;
    private DetalleOfertaController doC;
    @FXML
    private Button btnregresar;
    @FXML
    private ImageView imgVehiculo;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        doC = this;
    }    

    public void recibirParametros(Oferta oferta, Usuario dueno){
        this.oferta = oferta;
        this.dueno = dueno;
        mostrarDatos();
    }
    
    private void mostrarDatos(){
        String ruta = getClass().getResource("/ec/edu/espol/imagenes_vehiculos/"+oferta.getVehiculo().getImagen()).toExternalForm();
        this.imgVehiculo.setImage(new Image(ruta));
        this.lblApellido.setText(oferta.getComprador().getApellidos());
        this.lblnombre.setText(oferta.getComprador().getNombres());
        this.lblCorreo.setText(oferta.getComprador().getCorreo_elec());
        this.lblPecioPedido.setText(String.valueOf(oferta.getVehiculo().getPrecio()));
        this.lblPofertado.setText(String.valueOf(oferta.getPrecio()));        
    }
    
    @FXML
    private void vender(ActionEvent event) {
        dueno.vender(oferta, "vehiculos.ser", "ofertas.ser", "usuarios.ser");        
        boolean acep = alertaAceptar("Venta realizada exitosamente","Presione aceptar para actulizar");
        if(acep)
            this.btnregresar.fire();       
    }

    @FXML
    private void regresar(ActionEvent event) {
         OpcionesUsuarioController opC;
         try{
            FXMLLoader fxmlloader = App.loadFXMLLoader("opcionesUsuario");
            Parent root = fxmlloader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            OpcionesUsuarioController ouc = fxmlloader.getController();
            ouc.recibirParametros(dueno);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            cerrarVentana();
        } catch (IOException ex) {
            alertaError("Error inesperado",ex.getMessage());
        }
    }

    private void cerrarVentana() {
        Stage myStage = (Stage) this.lblPecioPedido.getScene().getWindow();
        myStage.close();
    }
    
}
