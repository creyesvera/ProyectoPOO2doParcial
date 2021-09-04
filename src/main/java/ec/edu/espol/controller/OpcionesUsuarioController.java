/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.TipoUsuario;
import ec.edu.espol.model.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;

import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
/**
 * FXML Controller class
 *
 * @author User
 */
public class OpcionesUsuarioController implements Initializable {
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
    @FXML
    private BorderPane root;
    
    private OpcionesUsuarioController opC;
    private Usuario user;
    @FXML
    private Menu menuComprador;
    @FXML
    private Menu menuVendedor;
    @FXML
    private Menu menuCuenta;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        opC = this;               
        
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
}
