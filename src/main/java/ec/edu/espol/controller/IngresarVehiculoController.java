/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.TipoUsuario;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.ValueTypeException;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.proyectopoo2doparcial.App;
import ec.edu.espol.util.Alarmas;
import static ec.edu.espol.util.Alarmas.alertaError;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
    @FXML
    private RadioButton btauto;
    @FXML
    private ToggleGroup tipovehiculo;
    @FXML
    private RadioButton btcamioneta;
    @FXML
    private RadioButton btmoto;
    @FXML
    private GridPane gridPane;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        byteimage = new byte[0];
    }    
   
    
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
        }
    }

    @FXML
    private void ingresar(MouseEvent event) {        
        try{
            String placa = placabox.getText();
            if(placa!= null && !Util.validacionPlaca(placa,vehiculos)){
                String imagen = saveImgName(placa);                           
                String marca = marcabox.getText();
                String modelo = modelobox.getText();
                String tipo_motor = tipo_motorbox.getText();
                int year = Integer.parseInt(yearbox.getText());
                double recorrido = Double.parseDouble(recorridobox.getText());
                String color = colorbox.getText();
                String tipo_combustible = tipo_combustiblebox.getText();
                double precio = Double.parseDouble(preciobox.getText());
                String vidrios ; //null si es moto 
                String transmicion;//null si es moto 
                String traccion;//null si es moto y carros


                int id = Vehiculo.nextID("vehiculos.ser") ; 
                //Usuario vendedor=null;
                int id_vendedor= Usuario.nextID("usuario.ser");

                Vehiculo v;

                if (btauto.isSelected()){
                    vidrios = vidriosbox.getText();
                    transmicion = transmicionbox.getText();
                    v = new Vehiculo(id, placa, marca, modelo, tipo_motor, year, recorrido, color, tipo_combustible, vidrios, transmicion, precio, usuario, id_vendedor, imagen);
                }else if (btmoto.isSelected()){
                    v = new Vehiculo(id, placa, marca, modelo, tipo_motor, year, recorrido, color, tipo_combustible, precio, usuario, id_vendedor, imagen);
                }else{
                    vidrios = vidriosbox.getText();
                    transmicion = transmicionbox.getText();
                    traccion = traccionbox.getText();
                    v = new Vehiculo(id, placa, marca, modelo, tipo_motor, year, recorrido, color, tipo_combustible, vidrios, transmicion, traccion, precio, usuario, id_vendedor, imagen);

                }
                int i = usuarios.indexOf(usuario);            
                usuario.getVehiculos().add(v);
                usuarios.set(i, usuario);
                Usuario.saveFile("usuarios.ser", usuarios);

                vehiculos.add(v);
                Vehiculo.saveFile("vehiculos.ser", vehiculos);

                Alert ok = new Alert(Alert.AlertType.CONFIRMATION,"El vehículo se a registrado con éxito");
                ok.show();
            }else{
                alertaError("","Por favor ingrese otro numero de placa");
            }
        }catch(NumberFormatException e){
            Alert a = new Alert(Alert.AlertType.ERROR,"Por favor ingrese valores numericos");
            a.show();
        }catch (ValueTypeException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,ex.getMessage());
            a.show();
        }catch(IOException ex){
            Alert a = new Alert(AlertType.WARNING,"Por favor importe la imagen del vehiculo");
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
                byteimage = new byte[1024*2000];//2M bytes
                imgbox.setText(selectedfile.getName());
                FileInputStream img = new FileInputStream(selectedfile);
                img.read(byteimage);
                    } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
        }
        }
    }
       
    private String saveImgName(String placa) throws IOException{
            //FileOutputStream archivosimg = new FileOutputStream(getClass().getResource("/ec/edu/espol/guardadas/"+placabox.getText()+".jpeg").toExternalForm());
            FileOutputStream archivosimg = new FileOutputStream("src/main/resources/ec/edu/espol/imagenes_vehiculos/"+placa+".jpg");
            if  (byteimage.length == 0)
                throw new IOException();
            archivosimg.write(byteimage);
            String imagen = placa+".jpg";
            
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

    @FXML
    private void autoSelected(MouseEvent event) {
         //constructor de carros, no tiene traccion
        if(!gridPane.getChildren().contains(vidriosbox))    
            gridPane.getChildren().add(vidriosbox);
        if(!gridPane.getChildren().contains(transmicionbox)) 
            gridPane.getChildren().add(transmicionbox);
            gridPane.getChildren().remove(traccionbox);
    }

    @FXML
    private void camionetaSelected(MouseEvent event) {
        //construcctor de camionetas tiene todo
        if(!gridPane.getChildren().contains(vidriosbox))    
            gridPane.getChildren().add(vidriosbox);
        if(!gridPane.getChildren().contains(transmicionbox)) 
            gridPane.getChildren().add(transmicionbox);
        if(!gridPane.getChildren().contains(traccionbox)) 
            gridPane.getChildren().add(traccionbox);
    }

    @FXML
    private void motoSelected(MouseEvent event) {
        //constructor de motos, no tiene vidrios, transmicion y traccion
        gridPane.getChildren().remove(vidriosbox);
        gridPane.getChildren().remove(transmicionbox);
        gridPane.getChildren().remove(traccionbox);
        
    }
   
}
