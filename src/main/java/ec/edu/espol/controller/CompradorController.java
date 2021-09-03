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
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CompradorController implements Initializable {

    @FXML
    private Button registrar;
    @FXML
    private Button ofertar;
    @FXML
    private Button regresar;
    
    private CompradorController cC;// para que es esto?
    
    private Usuario usuario;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cC = this;// se inicializa el mismo atributo con su mismo valor ... No le veo mucho sentido
    }    
    public void recibirParametros(Usuario usuario) {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
