/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camil
 */
public class Vehiculo {
    private int id;
    private String placa;       //validar que sea unica
    private String marca;
    private String modelo;
    private String tipo_motor;
    private int year;           //validar que sea positivo
    private double recorrido;   //validar que sea positivo
    private String color;
    private String tipo_combustible;
    private String vidrios;        //validar que sea positivo
    private String transmicion;
    private String traccion;
    private double precio;      //validar que sea positivo
    private ArrayList<Oferta> ofertas;
    private Usuario vendedor; 
    private int id_vendedor;  
    private String imagen;
    private TipoVehiculo tipo;
    
    //constructor de carros, no tiene traccion
    public Vehiculo(int id, String placa, String marca, String modelo, String tipo_motor, int year, double recorrido, String color, String tipo_combustible, String vidrios, String transmicion, double precio, Usuario vendedor, int id_vendedor, String imagen) throws ValueTypeException {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.transmicion = transmicion;
        this.ofertas = new ArrayList<>();
        this.vendedor = vendedor;
        this.id_vendedor = id_vendedor;
        if (imagen != null)
            this.imagen = imagen;
        else
            throw new ValueTypeException("Error al guardar la imagen, verificar que se haya importado una imagen y que el numero de placa es correcto");
        this.tipo = TipoVehiculo.AUTOS;
        
        if(year>1495 && recorrido>=0 && precio>=0){
            this.year = year;
            this.recorrido = recorrido;
            this.precio = precio;
        }
        else
            throw new ValueTypeException("NO SE CUMPLE year>1495 && recorrido>=0 && vidrios>=0 && precio>=0");
            
    }
    
    //constructor de motos, no tiene vidrios, transmicion y traccion
    public Vehiculo(int id, String placa, String marca, String modelo, String tipo_motor, int year, double recorrido, String color, String tipo_combustible, double precio, Usuario vendedor, int id_vendedor, String imagen) throws ValueTypeException {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.ofertas = new ArrayList<>();
        this.vendedor = vendedor;
        this.id_vendedor = id_vendedor;
        if (imagen != null)
            this.imagen = imagen;
        else
            throw new ValueTypeException("Error al guardar la imagen, verificar que se haya importado una imagen y que el numero de placa es correcto");
        this.tipo = TipoVehiculo.MOTOCICLETAS;
        
        if(year>1495 && recorrido>=0 && precio>=0){
            this.year = year;
            this.recorrido = recorrido;
            this.precio = precio;
        }
        else
            throw new ValueTypeException("NO SE CUMPLE year>1495 && recorrido>=0 && vidrios>=0 && precio>=0");
         
    }
    
    //construcctor de camionetas tiene todo

    public Vehiculo(int id, String placa, String marca, String modelo, String tipo_motor, int year, double recorrido, String color, String tipo_combustible, String vidrios, String transmicion, String traccion, double precio, Usuario vendedor, int id_vendedor, String imagen) throws ValueTypeException {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.transmicion = transmicion;
        this.traccion = traccion;
        this.ofertas = new ArrayList<>();
        this.vendedor = vendedor;
        this.id_vendedor = id_vendedor;
        this.vidrios = vidrios;
        if (imagen != null)
            this.imagen = imagen;
        else
            throw new ValueTypeException("Error al guardar la imagen, verificar que se haya importado una imagen y que el numero de placa es correcto");
        
        this.tipo = TipoVehiculo.CAMIONETAS;
        
        if(year>1495 && recorrido>=0 && precio>=0){
            this.year = year;
            this.recorrido = recorrido;
            this.precio = precio;
        }
        else
            throw new ValueTypeException("NO SE CUMPLE year>1495 && recorrido>=0 && vidrios>=0 && precio>=0");        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo_motor() {
        return tipo_motor;
    }

    public void setTipo_motor(String tipo_motor) {
        this.tipo_motor = tipo_motor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) throws ValueTypeException {
        if(year>1495){
            this.year = year;
        }
        else
            throw new ValueTypeException("NO SE CUMPLE year>1495");
         
    }

    public double getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(double recorrido) throws ValueTypeException {
        if(recorrido>=0){
            this.recorrido = recorrido;
        }
        else
            throw new ValueTypeException("NO SE CUMPLE recorrido>=0");
         
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo_combustible() {
        return tipo_combustible;
    }

    public void setTipo_combustible(String tipo_combustible) {
        this.tipo_combustible = tipo_combustible;
    }

    public String getVidrios() {
        return vidrios;
    }

    public void setVidrios(String vidrios){
       
            this.vidrios = vidrios;
     
    }

    public String getTransmicion() {
        return transmicion;
    }

    public void setTransmicion(String transmicion) {
        this.transmicion = transmicion;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) throws ValueTypeException {
        if(precio>=0){
            this.precio = precio;
        }
        else
            throw new ValueTypeException("NO SE CUMPLE precio>=0");
        
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) throws ValueTypeException {
        if (imagen != null)
            this.imagen = imagen;
        else
            throw new ValueTypeException("Error al guardar la imagen, verificar que se haya importado una imagen y que el numero de placa es correcto");
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    public static void saveFile(String nomfile, ArrayList<Vehiculo> vehiculos){
        try
        {
            FileOutputStream file = new FileOutputStream(nomfile);
            ObjectOutputStream object = new ObjectOutputStream(file);
            object.writeObject(vehiculos);
            object.flush();
        }
        catch(IOException e){
        }
    }
    
    public static ArrayList<Vehiculo> readFile(String nomfile){
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try{
            FileInputStream file = new FileInputStream(nomfile);
            ObjectInputStream object = new ObjectInputStream(file);
            vehiculos = (ArrayList<Vehiculo>) object.readObject();
        }
        catch(ClassNotFoundException e){
        }
        catch(FileNotFoundException e){
        }
        catch(IOException e){
        }
        return vehiculos;
        
    }
    
    
    /*para cada vehiculo en la lista vehículo se añade el objeto vehículo a la 
    lista de vehículos del vendedor con el id_comprador  correspondiente al vehículo,
    y se pone asigna el objeto vendedor al objeto vehiculo
    */
    public static void link(ArrayList<Usuario> vendedores, ArrayList<Vehiculo> vehiculos){
        for(Vehiculo v: vehiculos){
            Usuario ven = Usuario.searchByID(vendedores, v.getId_vendedor());
            ven.getVehiculos().add(v);
            v.setVendedor(ven);
        }
    }
    
    /*estas funciones devuelven una lista de vehículos que coinciden con el tipo 
    y que están dentro del rango de recorrido, precio, año respectivamente. (abajo)
    */
    public static Vehiculo searchByID(ArrayList<Vehiculo> vehiculos, int id)
    {
        for(Vehiculo veh : vehiculos)
        {
            if(veh.id == id)
                return veh;
        }
        return null;
    }
    
        public static Vehiculo searchByPlaca(ArrayList<Vehiculo> vehiculos, String placa)
    {
        for(Vehiculo veh : vehiculos)
        {
            if(veh.placa.equals(placa))
                return veh;
        }
        return null;
    }
        
    public static ArrayList<Vehiculo> searchByTipo(ArrayList<Vehiculo> vehiculos, TipoVehiculo tipo){
        ArrayList<Vehiculo> filtro_vehiculos = new ArrayList<>();
        for(Vehiculo vehiculo : vehiculos){
            if (vehiculo.getTipo() == tipo) //moto
                filtro_vehiculos.add(vehiculo);
            }        
        return filtro_vehiculos;
    }
    
    
    public static ArrayList<Vehiculo> searchByRecorrido(ArrayList<Vehiculo> vehiculos, double max_rec, double min_rec)
    {
        ArrayList<Vehiculo> filtro_vehiculos = new ArrayList<>();
        for(Vehiculo veh : vehiculos)
        {
            if(veh.recorrido<= max_rec && veh.recorrido>= min_rec)
                filtro_vehiculos.add(veh);
        }
        if(max_rec == 0 && min_rec == 0)
            filtro_vehiculos = vehiculos;
        
        return filtro_vehiculos;
    }
    
    public static ArrayList<Vehiculo> searchByPrecio(ArrayList<Vehiculo> vehiculos, double max_prec, double min_prec)
    {
        ArrayList<Vehiculo> filtro_vehiculos = new ArrayList<>();
        for(Vehiculo veh : vehiculos)
        {
            if(veh.precio<= max_prec && veh.precio>= min_prec)
                filtro_vehiculos.add(veh);
        }
        if(max_prec == 0 && min_prec == 0)
            filtro_vehiculos = vehiculos;
        return filtro_vehiculos;
    }
    
    public static ArrayList<Vehiculo> searchByYear(ArrayList<Vehiculo> vehiculos, int max_year, int min_year)
    {
        ArrayList<Vehiculo> filtro_vehiculos = new ArrayList<>();
        for(Vehiculo veh : vehiculos)
        {
            if(veh.year<= max_year && veh.year >= min_year)
                filtro_vehiculos.add(veh);
        }
        if(max_year == 0 && min_year == 0)
            filtro_vehiculos = vehiculos;
        return filtro_vehiculos;
    }
    /*estas funciones devuelven una lista de vehículos que coinciden con el tipo 
    y que están dentro del rango de recorrido, precio, año respectivamente.(arriba)
    */
    
    //devuelve una lista de vehiiculos unicos apartir de los elementos de las listas vehiculos1 y vehiculos2
    public static ArrayList<Vehiculo> interseccionList(ArrayList<Vehiculo> vehiculos1,ArrayList<Vehiculo> vehiculos2){
        ArrayList<Vehiculo> filtro_vehiculos = new ArrayList<>();
        for(Vehiculo veh1 : vehiculos1){
           for(Vehiculo veh2 : vehiculos2){
            if(veh1.equals(veh2))
               filtro_vehiculos.add(veh1);
           
           }
       }
        return filtro_vehiculos;
    }
    
    public static int nextID(String nomfile){
        int id = 0;
        ArrayList<Vehiculo> vehiculos = Vehiculo.readFile(nomfile);
        for(Vehiculo v: vehiculos){
            if (v.getId()> id)
                id = v.getId();
        }
        return id+1;
    }
    
    public static ArrayList<Vehiculo> filtrarVehiculos(List<Vehiculo> vehiculos,TipoVehiculo tipo, double[] rangoRecorrido,  int[] rangoAno, double[] rangoPrecio){
        ArrayList<Vehiculo> vehiculoSeleccionados = new ArrayList<>();  
        if(tipo!=null){
            for(Vehiculo v: vehiculos){
                if (v.getTipo().equals(tipo) && v.getRecorrido()>=rangoRecorrido[0] && v.getRecorrido()<=rangoRecorrido[1]
                        && v.getYear()>=rangoAno[0] && v.getYear()<=rangoAno[1] && v.getPrecio()>=rangoPrecio[0] && v.getPrecio()<=rangoPrecio[1])
                    vehiculoSeleccionados.add(v);                
            }
        }else{
            for(Vehiculo v: vehiculos){
                if ((v.getRecorrido()>=rangoRecorrido[0] && v.getRecorrido()<=rangoRecorrido[1])
                        && (v.getYear()>=rangoAno[0] && v.getYear()<=rangoAno[1]) 
                        && (v.getPrecio()>=rangoPrecio[0] && v.getPrecio()<=rangoPrecio[1]))
                    vehiculoSeleccionados.add(v);                
            }
        }            
        return vehiculoSeleccionados;
    }
    
    
}
