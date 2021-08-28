/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.controller.NoUserException;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.ValueTypeException;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.proyectopoo2doparcial.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class IngresarVehiculoController implements Initializable {
    private ArrayList<Vehiculo> vehiculos = Vehiculo.readFile("vehiculos.ser");
    private Usuario usuario;
    @FXML
    private Button btregresar;
    @FXML
    private Button btingresar;
    @FXML
    private TextField placabox;
    @FXML
    private TextField marcabox;
    @FXML
    private TextField modelobox;
    @FXML
    private TextField tipo_motorbox;
    @FXML
    private TextField yearbox;
    @FXML
    private TextField recorridobox;
    @FXML
    private TextField colorbox;
    @FXML
    private TextField tipo_combustiblebox;
    @FXML
    private TextField vidriosbox;
    @FXML
    private TextField transmicionbox;
    @FXML
    private TextField traccionbox;
    @FXML
    private TextField preciobox;
    @FXML
    private TextField imgbox;
    @FXML
    private Button btimport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        placabox.clear();
        marcabox.clear();
        modelobox.clear();
        tipo_motorbox.clear();
        yearbox.clear();
        recorridobox.clear();
        colorbox.clear();
        tipo_combustiblebox.clear();
        preciobox.clear();
        imgbox.clear();
        vidriosbox.clear(); //null si es moto 
        transmicionbox.clear(); //null si es moto 
        traccionbox.clear(); //null si es moto y carros
        
    }    

    @FXML
    private void regresar(MouseEvent event) {
    }

    @FXML
    private void ingresar(MouseEvent event) throws NoUserException {
        if(usuario == null){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION,"Por favor iniciar sesión");
            alerta.show(); 
            throw new NoUserException("no existe el usuario");   
        }
        
        try{
            String placa = placabox.getText();
            String marca = marcabox.getText();
            String modelo = modelobox.getText();
            String tipo_motor = tipo_motorbox.getText();
            int year = Integer.parseInt(yearbox.getText());
            double recorrido = Double.parseDouble(recorridobox.getText());
            String color = colorbox.getText();
            String tipo_combustible = tipo_combustiblebox.getText();
            double precio = Double.parseDouble(preciobox.getText());
            String imagen = imgbox.getText();
            
            int id=0 ;
            Usuario vendedor=null;
            int id_vendedor=0;
            
            Vehiculo v;
            /**/
            if(traccionbox == null){
                if(transmicionbox == null && vidriosbox == null){
                    v = new Vehiculo(id, placa, marca, modelo, tipo_motor, year, recorrido, color, tipo_combustible, precio, vendedor, id_vendedor, imagen);
                }
                else{
                    int vidrios = Integer.parseInt(vidriosbox.getText()); //null si es moto 
                    String transmicion = transmicionbox.getText();//null si es moto 
                    v = new Vehiculo(id, placa, marca, modelo, tipo_motor, year, recorrido, color, tipo_combustible, vidrios, transmicion, precio, vendedor, id_vendedor, imagen);
                    }
                }
            else{
            int vidrios = Integer.parseInt(vidriosbox.getText()); //null si es moto 
            String transmicion = transmicionbox.getText();//null si es moto 
            String traccion = traccionbox.getText();//null si es moto y carros
            v = new Vehiculo(id, placa, marca, modelo, tipo_motor, year, recorrido, color, tipo_combustible, vidrios, transmicion, traccion, precio, vendedor, id_vendedor, imagen);
            }
            
            vehiculos.add(v);
            Vehiculo.saveFile("vehiculos.ser", vehiculos);
        }catch(NumberFormatException e){
            Alert a = new Alert(Alert.AlertType.ERROR,"Por favor ingrese valores numericos");
            a.show();
        } catch (ValueTypeException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"Por favor ingrese valores positivos");
            a.show();
        }
    }

    @FXML
    private void importar(MouseEvent event) {
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
}