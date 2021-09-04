/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author User
 */
public class Alarmas {
    public static void alertaError(String encabezado, String contenido){
        Alert a = new Alert(AlertType.ERROR);
        a.setHeaderText(encabezado);
        a.setContentText(contenido);
        a.setResizable(true);
        a.show();
    }
    
    public static void alertaInformacion(String encabezado, String contenido){
        Alert a = new Alert(AlertType.INFORMATION);
        a.setHeaderText(encabezado);
        a.setContentText(contenido);
        a.show();
    }
    
    public static void alertWarning(String encabezado, String contenido){
        Alert a = new Alert(AlertType.WARNING);
        a.setHeaderText(encabezado);
        a.setContentText(contenido);
        a.show();
    }
    
    
}
