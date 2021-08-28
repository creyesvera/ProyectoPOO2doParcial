/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.proyectopoo2doparcial.App;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author camil
 */
public class NoUserException extends Exception{

    public NoUserException(String message) {
        super(message);
        try {
                FXMLLoader fxmlloader = App.loadFXMLLoader("iniciarSesion");
                App.setRoot(fxmlloader);
                IniciarSesionController sesion = fxmlloader.getController();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
    
}
