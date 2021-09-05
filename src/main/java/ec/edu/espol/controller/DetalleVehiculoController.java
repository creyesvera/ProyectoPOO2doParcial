/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import static ec.edu.espol.util.Alarmas.alertaError;
import static ec.edu.espol.util.Alarmas.alertaInformacion;
import static ec.edu.espol.util.Util.esDouble;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    
    private DetalleVehiculoController dtC;
    private Usuario user;
    private Vehiculo vehiculo;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Vehiculo> vehiculos;
    
    @FXML
    private Label lblMarca;
    @FXML
    private Label lblAnio;
    @FXML
    private Label lblTdMotor;
    
    @FXML
    private Label lblModelo;
    @FXML
    private Label lblRecorrido;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dtC = this;
        usuarios = Usuario.readFile("usuarios.ser");
        vehiculos = Vehiculo.readFile("vehiculos.ser");
    }    

    private void mostrarDatos(){
        String ruta = getClass().getResource("/ec/edu/espol/imagenes_vehiculos/"+vehiculo.getImagen()).toExternalForm();
        this.imgVehiculo.setImage(new Image(ruta));
        lblMarca.setText(vehiculo.getMarca());
        lblModelo.setText(vehiculo.getModelo());
        lblAnio.setText(String.valueOf(vehiculo.getYear()));
        lblTdMotor.setText(vehiculo.getTipo_motor());
        lblRecorrido.setText(String.valueOf(vehiculo.getRecorrido()));
        txtPrecioSug.setText(String.valueOf(vehiculo.getPrecio()));
        buscarOferta();
    }
    @FXML
    private void ofertar(ActionEvent event) {
        if(validarCampos()){
            user.comprar(vehiculo, "ofertas.ser", Double.parseDouble(txtPrecioOfer.getText()), usuarios,vehiculos); 
            alertaInformacion("OFERTA REALIZADA","Se ha agregado una nueva oferta para el vehículo");            
            cerrarVentana();
        }else
            alertaError("Ha ocurrido un problema","Debe escribir un precio para ofertar");        
    }
    
    private boolean validarCampos(){
        return !(this.txtPrecioOfer.getText().isEmpty() || !esDouble(txtPrecioOfer.getText().trim()));
    }
    
    public void recibirParametros(Usuario user, Vehiculo vehiculo){
        this.user = user;
        this.vehiculo = vehiculo;
        mostrarDatos();
    }
    
    private void buscarOferta(){
        user.getOfertas().forEach(e->{
            if(e.getVehiculo().equals(vehiculo))
                this.txtPrecioOfer.setText(String.valueOf(e.getPrecio()));
        });
    }
    
    
    private void cerrarVentana(){
        Stage myStage = (Stage) this.lblAnio.getScene().getWindow();
        myStage.close();
    }
}
