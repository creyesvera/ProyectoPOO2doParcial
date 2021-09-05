/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.TipoUsuario;
import ec.edu.espol.model.TipoVehiculo;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.proyectopoo2doparcial.App;
import static ec.edu.espol.util.Alarmas.alertaError;
import static ec.edu.espol.util.Util.esDouble;
import static ec.edu.espol.util.Util.esInteger;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author User
 */
public class OpcionesUsuarioController implements Initializable {
    private ArrayList<Vehiculo> vehiculos;
    @FXML
    private MenuItem itemOfertar;
    @FXML
    private MenuItem itemVerOfertas;
    @FXML
    private MenuItem itemAgregarVehiculo;
    @FXML
    private MenuItem itemAceptarOferta;
    @FXML
    private MenuItem itemConfiguracion;
    @FXML
    private MenuItem itemCerrarSesion;
        
    private OpcionesUsuarioController opC;
    private Usuario user;
    @FXML
    private Menu menuComprador;
    @FXML
    private Menu menuVendedor;
    @FXML
    private Menu menuCuenta;
    @FXML
    private BorderPane rootOfertar;
    @FXML
    private BorderPane rootAceptarOferta;
    @FXML
    private BorderPane rootMostarOfertados;
    @FXML
    private ComboBox<TipoVehiculo> cbTipoVehiculo;
    @FXML
    private TextField txtRecorridoI;
    @FXML
    private TextField txtRecorridoF;
    @FXML
    private TextField txtAnioI;
    @FXML
    private TextField txtAnioF;
    @FXML
    private TextField txtPrecioI;
    @FXML
    private TextField txtPrecioF;
    @FXML
    private Button btnBuscar;
    @FXML
    private TableView<Vehiculo> tableView;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        opC = this;               
        vehiculos = Vehiculo.readFile("vehiculos.ser");
        this.rootAceptarOferta.setVisible(false);
        this.rootMostarOfertados.setVisible(false);
        this.rootOfertar.setVisible(false);
    }    
    
    private void mostrarMenu(){
        switch (user.getTipo()) {
            case COMPRADOR:
                menuVendedor.setVisible(false);                
                break;
            case VENDEDOR:
                menuComprador.setVisible(false);
                break;
            default:
                menuComprador.setVisible(true);
                menuVendedor.setVisible(true);            
                break;
        }                        
    } 
        
    public void recibirParametros(Usuario u){
        user = u;
        mostrarMenu();
    }
    
    @FXML
    private void agregarVehiculo(){
        FXMLLoader loader; 
        try {
            loader = App.loadFXMLLoader("ingresarVehiculo");
            Parent rootp = loader.load();  
            IngresarVehiculoController opU = loader.getController();            
            opU.setUsuario(user);
            Scene scene = new Scene(rootp);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();                        
        } catch (IOException ex) {
            alertaError("Ha ocurrido un error",ex.getMessage());
        }
          }                    
                
    @FXML
    private void hacerOferta(){
        mostrarHacerOfertas();
        //Tipovehiculo, recorrido, año, precio
       
    }
    
    private void mostrarHacerOfertas(){
        this.rootAceptarOferta.setVisible(false);
        this.rootMostarOfertados.setVisible(false);
        this.rootOfertar.setVisible(true);
    }
    
    private void mostrarVenderVehiculo(){
        this.rootAceptarOferta.setVisible(true);
        this.rootMostarOfertados.setVisible(false);
        this.rootOfertar.setVisible(false);
    }
    
    private boolean validarParametros(List<TextField> txts){
        for(TextField t: txts){
            if(!t.getText().trim().isEmpty()){
                if(!esDouble(t.getText().trim()))
                    return false;
            }
        }
        return !(!esInteger(txts.get(2).getText()) || !esInteger(txts.get(3).getText()));
    }
    
   private void realizarBusqueda(List<TextField> txts){
        
        
   }   
    

    @FXML
    private void aceptarOferta(ActionEvent event) {
        mostrarVenderVehiculo();
    }
    
}
