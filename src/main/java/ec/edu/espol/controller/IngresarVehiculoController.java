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
import ec.edu.espol.util.Alarmas;
import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class IngresarVehiculoController implements Initializable {
    private ArrayList<Vehiculo> vehiculos;
    
    private ArrayList<Usuario> usuarios;
    
    private IngresarVehiculoController ivC;
    private Usuario usuario;
    
    private byte[] byteimage;
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
    private Text imgbox;
    @FXML
    private Button btimport;
    @FXML
    private BorderPane root;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ivC = this;
        vehiculos = Vehiculo.readFile("vehiculos.ser");
        placabox.clear();
        usuarios = Usuario.readFile("usuarios.ser");
        marcabox.clear();
        modelobox.clear();
        tipo_motorbox.clear();
        yearbox.clear();
        recorridobox.clear();
        colorbox.clear();
        tipo_combustiblebox.clear();
        preciobox.clear();
        vidriosbox.clear(); //null si es moto 
        transmicionbox.clear(); //null si es moto 
        traccionbox.clear(); //null si es moto y carros        
    }    
    
    public void recibirParametros(Usuario user){
        this.usuario = user;
    }
    
    @FXML
    private void regresar(MouseEvent event) {
        /*
        if (usuario.getTipo() == TipoUsuario.COMPRADOR)
                compradorsesion();
            else if (usuario.getTipo() == TipoUsuario.VENDEDOR)
                vendedorsesion();
            else 
                ambossesion();*/
    }

    @FXML
    private void ingresar(MouseEvent event) {        
        try{
            String placa = placabox.getText();
            if (placa== null || Util.validacionPlaca(placa,vehiculos)){
                Alert a = new Alert(Alert.AlertType.ERROR,"Por favor ingrese otro numero de placa");
                a.show();
            }
            String marca = marcabox.getText();
            String modelo = modelobox.getText();
            String tipo_motor = tipo_motorbox.getText();
            int year = Integer.parseInt(yearbox.getText());
            double recorrido = Double.parseDouble(recorridobox.getText());
            String color = colorbox.getText();
            String tipo_combustible = tipo_combustiblebox.getText();
            double precio = Double.parseDouble(preciobox.getText());
            
            String imagen = saveImgName();
            if(imagen  == null){
                Alert a = new Alert(AlertType.WARNING,"Por favor importe la imagen del vehiculo");
                a.show();
            }
                        
            int id = Vehiculo.nextID("vehiculos.ser") ; 
            //Usuario vendedor=null;
            int id_vendedor=0;
            
            Vehiculo v;
            
            if(traccionbox == null){
                if(transmicionbox == null && vidriosbox == null){
                    v = new Vehiculo(id, placa, marca, modelo, tipo_motor, year, recorrido, color, tipo_combustible, precio, usuario, id_vendedor, imagen);
                }
                else{
                    int vidrios = Integer.parseInt(vidriosbox.getText()); //null si es moto 
                    String transmicion = transmicionbox.getText();//null si es moto 
                    v = new Vehiculo(id, placa, marca, modelo, tipo_motor, year, recorrido, color, tipo_combustible, vidrios, transmicion, precio, usuario, id_vendedor, imagen);
                    }
                }
            else{
            int vidrios = Integer.parseInt(vidriosbox.getText()); //null si es moto 
            String transmicion = transmicionbox.getText();//null si es moto 
            String traccion = traccionbox.getText();//null si es moto y carros
            v = new Vehiculo(id, placa, marca, modelo, tipo_motor, year, recorrido, color, tipo_combustible, vidrios, transmicion, traccion, precio, usuario, id_vendedor, imagen);
            }
            usuario.getVehiculos().add(v);
            Usuario.saveFile("usuarios.ser", usuarios);
            vehiculos.add(v);
            Vehiculo.saveFile("vehiculos.ser", vehiculos);
           
            Alert ok = new Alert(Alert.AlertType.CONFIRMATION,"El vehículo se a registrado con éxito");
            ok.show();
            
        }catch(NumberFormatException e){
            Alert a = new Alert(Alert.AlertType.ERROR,"Por favor ingrese valores numericos");
            a.show();
        }catch (ValueTypeException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"Por favor ingrese valores positivos");
            a.show();
        }
    }

    @FXML
    private void importar(MouseEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll( ///imagenes menores a 100M
        new ExtensionFilter ("Image Files","*.jpg", "*.png","*.jpeg"));
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null){
            try {                
                byteimage = new byte[1024*1000];//100M bytes
                imgbox.setText(selectedfile.getName());
                FileInputStream img = new FileInputStream(selectedfile);
                img.read(byteimage);
                    } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
        }
        }
    }
       
    private String saveImgName(){ 
        String imagen = null;
        try (FileOutputStream archivosimg = new FileOutputStream(getClass().getResource("/guardadas/"+placabox.getText()+".jpeg").toExternalForm())){
            //FileOutputStream archivosimg = new FileOutputStream("/guardadas/"+placabox.getText()+".jpg");
            archivosimg.write(byteimage);
            imagen = placabox.getText()+".jpeg";
            
        }catch (IOException ex) {
        }
            
        return imagen;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
     private void cerrarVentana(){
        Stage myStage = (Stage) this.btregresar.getScene().getWindow();
        myStage.close();
    }
    /*
    private void compradorsesion(){
    try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("comprador");
            Parent root = fxmlloader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            
            OpcionesUsuarioController sesion = fxmlloader.getController();
            if (usuario != null)
                sesion.recibirParametros(usuario);///Comprador Controller debe tener atributo vendedor
            
            cerrarVentana();
        } catch (IOException ex) {
            Alarmas.alertaError("ERROR", ex.getMessage());
        }
    }
    
    private void vendedorsesion(){
    try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("vendedor");
            Parent root = fxmlloader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            cerrarVentana();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void ambossesion(){
    try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("ambos");
            Parent root = fxmlloader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            /*
            AmbosController sesion = fxmlloader.getController();
            if (usuario != null)
                sesion.setUsuario(usuario);
            cerrarVentana();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   
    */

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }
    
}
