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
import ec.edu.espol.util.Util;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    //VEHICULO
    private void mostrarVenderVehiculo(){
        this.rootAceptarOferta.setVisible(true);
        this.rootMostarOfertados.setVisible(false);
        this.rootOfertar.setVisible(false);
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
            cerrarVentana();
        } catch (IOException ex) {
            alertaError("Ha ocurrido un error",ex.getMessage());
        }
          }                    
             
    
    //COMPRADOR
    @FXML
    private void hacerOferta(){
        mostrarHacerOfertas();
        mostrarVehiculosTableView();
        //Tipovehiculo, recorrido, año, precio
       
    }

    private void mostrarVehiculosTableView(){
        TableColumn<Vehiculo,String> col1 =  new TableColumn<>("Placa");
        col1.setCellValueFactory(new PropertyValueFactory<>("placa"));
        
        TableColumn<Vehiculo,String> col2 =  new TableColumn<>("Marca");
        col2.setCellValueFactory(new PropertyValueFactory<>("marca"));
        
        TableColumn<Vehiculo,String> col3 =  new TableColumn<>("Modelo");
        col3.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        
        TableColumn<Vehiculo,String> col4 =  new TableColumn<>("Tipo de motor");
        col4.setCellValueFactory(new PropertyValueFactory<>("tipo_motor"));
        
        TableColumn<Vehiculo,Integer> col5 =  new TableColumn<>("Año");
        col5.setCellValueFactory(new PropertyValueFactory<>("year"));
        
        TableColumn<Vehiculo,Double> col6 =  new TableColumn<>("Recorrido");
        col6.setCellValueFactory(new PropertyValueFactory<>("recorrido"));
        
        TableColumn<Vehiculo,String> col7 =  new TableColumn<>("Color");
        col7.setCellValueFactory(new PropertyValueFactory<>("color"));
        
        TableColumn<Vehiculo,String> col8 =  new TableColumn<>("Tipo de combustible");
        col8.setCellValueFactory(new PropertyValueFactory<>("tipo_combustible"));
        
        TableColumn<Vehiculo,String> col9 =  new TableColumn<>("Tipo de vidrios");
        col9.setCellValueFactory(new PropertyValueFactory<>("vidrios"));
        
        TableColumn<Vehiculo,String> col10 =  new TableColumn<>("Transmisión");
        col10.setCellValueFactory(new PropertyValueFactory<>("transmicion"));
        
        TableColumn<Vehiculo,String> col11 =  new TableColumn<>("Tracción");
        col11.setCellValueFactory(new PropertyValueFactory<>("traccion"));
        
        TableColumn<Vehiculo,Double> col12 =  new TableColumn<>("Precio");
        col12.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
        tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12);
        
        tableView.getItems().clear();
        System.out.println(vehiculos);
        tableView.getItems().addAll(vehiculos);
    }
    
    private void mostrarHacerOfertas(){
        limpiarRootMostrarOfertas();
        llenarCbBoxTipoVehiculo();        
        this.rootAceptarOferta.setVisible(false);
        this.rootMostarOfertados.setVisible(false);
        this.rootOfertar.setVisible(true);
    }
    
    private void llenarCbBoxTipoVehiculo() {
        this.cbTipoVehiculo.getItems().addAll(TipoVehiculo.values());
        this.cbTipoVehiculo.setOnAction(e->realizarBusqueda());
    }
    
    private void limpiarRootMostrarOfertas(){
        List<TextField> txts = Arrays.asList(this.txtRecorridoI,this.txtRecorridoF,this.txtAnioI,this.txtAnioF,this.txtPrecioI,this.txtPrecioF);
        txts.forEach(e->e.clear());
        this.cbTipoVehiculo.getItems().clear();
        this.cbTipoVehiculo.setPromptText("Tipo de vehiculo");
    }
        
    private boolean validarParametros(){
        List<TextField> txts = Arrays.asList(this.txtRecorridoI,this.txtRecorridoF,this.txtAnioI,this.txtAnioF,this.txtPrecioI,this.txtPrecioF);
        for (int i = 0; i < txts.size(); i++) {
            if(i%2==0){
                if(txts.get(i).getText().trim().isEmpty() && !txts.get(i+1).getText().trim().isEmpty())
                    return false;
            }
        }
        for(TextField t: txts){
            if(!t.getText().trim().isEmpty()){
                if(!esDouble(t.getText().trim()))
                    return false;
            }
        }
        if(!txts.get(2).getText().trim().isEmpty() )
            return esInteger(txts.get(2).getText());
        if(!txts.get(3).getText().trim().isEmpty())
            return esInteger(txts.get(3).getText());
        return true;
    }
    
    @FXML
    private void realizarBusqueda(){
        if(validarParametros()){
            String recorridos = txtRecorridoI.getText().trim() + "-"+txtRecorridoF.getText().trim();
            String anio = txtAnioI.getText().trim() +"-"+txtAnioF.getText().trim();
            String precios = txtPrecioI.getText().trim()+"-"+txtPrecioF.getText().trim();
            double[] arr_recorridos = Util.validarRangosDouble(recorridos);
            System.out.println(Arrays.toString(arr_recorridos));            
            int[] arr_anios = Util.validarRangosInt(anio);
            System.out.println(Arrays.toString(arr_anios));
            double[] arr_precios =  Util.validarRangosDouble(precios);
            List<Vehiculo> vehiculosFiltrados = Vehiculo.filtrarVehiculos(vehiculos, this.cbTipoVehiculo.getValue(), arr_recorridos, arr_anios, arr_precios);
            mostrarResultadosBusqueda(vehiculosFiltrados);
        }else
            alertaError("PARAMETROS INCORRECTOS","- Solo debe ingresar valores numericos\n- El valor final no puede ser menor que el inicial");
    }   

   private void mostrarResultadosBusqueda(List<Vehiculo> vehiculosT){
       tableView.getItems().clear();
       tableView.getItems().addAll(vehiculosT);
   }
    //VENDEDOR

    @FXML
    private void aceptarOferta(ActionEvent event) {
        mostrarVenderVehiculo();
    }
    
    private void cerrarVentana(){
        Stage myStage = (Stage) this.btnBuscar.getScene().getWindow();
        myStage.close();
    }
}
