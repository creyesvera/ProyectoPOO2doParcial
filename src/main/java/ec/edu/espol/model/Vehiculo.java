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

/**
 *
 * @author camil
 */
public class Vehiculo {
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private String tipo_motor;
    private int year;
    private double recorrido;
    private String color;
    private String tipo_combustible;
    private int vidrios;
    private String transmicion;
    private String traccion;
    private double precio;
    private ArrayList<Oferta> ofertas;
    private Usuario vendedor; 
    private int id_vendedor;  
    private String imagen;
    
    //constructor de carros, no tiene traccion
    public Vehiculo(int id, String placa, String marca, String modelo, String tipo_motor, int year, double recorrido, String color, String tipo_combustible, int vidrios, String transmicion, double precio, Usuario vendedor, int id_vendedor, String imagen) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.year = year;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.vidrios = vidrios;
        this.transmicion = transmicion;
        this.precio = precio;
        this.ofertas = new ArrayList<>();
        this.vendedor = vendedor;
        this.id_vendedor = id_vendedor;
        this.imagen = imagen;
    }
    
    //constructor de motos, no tiene vidrios, transmicion y traccion
    public Vehiculo(int id, String placa, String marca, String modelo, String tipo_motor, int year, double recorrido, String color, String tipo_combustible, double precio, Usuario vendedor, int id_vendedor, String imagen) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.year = year;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.precio = precio;
        this.ofertas = new ArrayList<>();
        this.vendedor = vendedor;
        this.id_vendedor = id_vendedor;
        this.imagen = imagen;
    }
    
    //construcctor de camionetas tiene todo

    public Vehiculo(int id, String placa, String marca, String modelo, String tipo_motor, int year, double recorrido, String color, String tipo_combustible, int vidrios, String transmicion, String traccion, double precio, Usuario vendedor, int id_vendedor, String imagen) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.year = year;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.vidrios = vidrios;
        this.transmicion = transmicion;
        this.traccion = traccion;
        this.precio = precio;
        this.ofertas = new ArrayList<>();
        this.vendedor = vendedor;
        this.id_vendedor = id_vendedor;
        this.imagen = imagen;
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

    public void setYear(int year) {
        this.year = year;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
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

    public int getVidrios() {
        return vidrios;
    }

    public void setVidrios(int vidrios) {
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

    public void setPrecio(double precio) {
        this.precio = precio;
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

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
    
    
    public void saveFile(String nomfile, ArrayList<Vehiculo> vehiculos){
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
    
    public static ArrayList<Vehiculo> searchByTipo(ArrayList<Vehiculo> vehiculos, String tipo){
        ArrayList<Vehiculo> filtro_vehiculos = new ArrayList<>();
        if(tipo.equals("moto")){
        for(Vehiculo vehiculo : vehiculos)
            {
            if (vehiculo.getVidrios()==0 && vehiculo.getTraccion().equals("null") && vehiculo.getTransmicion().equals("null")) //moto
                    filtro_vehiculos.add(vehiculo);
            }
        }
        
        if(tipo.equals("carro")){
        for(Vehiculo vehiculo : vehiculos)
            {
            if (vehiculo.getVidrios()!=0 && vehiculo.getTraccion().equals("null") && !vehiculo.getTransmicion().equals("null")) //carro
                    filtro_vehiculos.add(vehiculo);
            }
        }
        if(tipo.equals("camioneta")){
        for(Vehiculo vehiculo : vehiculos)
            {
            if (vehiculo.getVidrios()!=0 && !vehiculo.getTraccion().equals("null") && !vehiculo.getTransmicion().equals("null")) //camioneta
                    filtro_vehiculos.add(vehiculo);
            }
        }
        
        else
            filtro_vehiculos = vehiculos;
            
        
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
    
}
