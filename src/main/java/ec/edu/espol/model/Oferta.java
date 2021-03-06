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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author camil
 */
public class Oferta implements Serializable{
    private int id;
    private Usuario comprador;
    private Vehiculo vehiculo;
    private double precio;
        
    public Oferta(int id, Usuario comprador, Vehiculo vehiculo, double precio) {
        this.id = id;
        this.comprador = comprador;
        this.vehiculo = vehiculo;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Oferta{" + "id=" + id + ", comprador=" + comprador + ", vehiculo=" + vehiculo + ", precio=" + precio + '}';
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
        final Oferta other = (Oferta) obj;
        if (!Objects.equals(this.comprador, other.comprador)) {
            return false;
        }
        if (!Objects.equals(this.vehiculo, other.vehiculo)) {
            return false;
        }
        return true;
    }

   

    
    public static void saveFile(String nomfile, ArrayList<Oferta> ofertas){
        try
        {
            FileOutputStream file = new FileOutputStream(nomfile);
            ObjectOutputStream object = new ObjectOutputStream(file);
            object.writeObject(ofertas);
            object.flush();
        }
        catch(IOException e){
        }
    }
    
    public static ArrayList<Oferta> readFile(String nomfile){
        ArrayList<Oferta> ofertas = new ArrayList<>();
        try{
            FileInputStream file = new FileInputStream(nomfile);
            ObjectInputStream object = new ObjectInputStream(file);
            ofertas = (ArrayList<Oferta>) object.readObject();
        }catch(FileNotFoundException e){
        }
        catch(ClassNotFoundException | IOException e){
        }
        return ofertas;
        
    }
    
    /*para cada oferta de la lista ofertas a??ade el objeto oferta a las listas de ofertas del comprador y 
    veh??culo correspondiente, se asigna los objetos comprador y vendedor al objeto oferta*/
    public static void link(ArrayList<Usuario> compradores, ArrayList<Vehiculo> vehiculos, ArrayList<Oferta> ofertas){
        for(Oferta o: ofertas){
            Usuario comp = Usuario.searchByID(compradores, o.comprador.getId());
            Vehiculo veh = Vehiculo.searchByID(vehiculos, o.getVehiculo().getId());
            comp.getOfertas().add(o);
            veh.getOfertas().add(o);
            o.setComprador(comp);
            o.setVehiculo(veh);
        }
    }
    
    
    /*devuelve el objeto oferta que contenga el id ???id??? dentro de la lista ofertas*/
    public static Oferta searchByID(ArrayList<Oferta> ofertas, int id)
    {
        for(Oferta o : ofertas)
        {
            if(o.id == id)
                return o;
        }
        return null;
    }
    
    public static int nextID(String nomfile){
        int id = 0;
        ArrayList<Oferta> ofertas = Oferta.readFile(nomfile);
        for(Oferta o: ofertas){
            if (o.getId()> id)
                id = o.getId();
        }
        return id+1;
    }
    
    public static Oferta buscarOferta(Oferta o){
        ArrayList<Oferta> ofertas = Oferta.readFile("ofertas.ser");
        for(Oferta of: ofertas){
            if(of.equals(o))
                return of;
            }
        return null;
    }
    
    public static ArrayList<Oferta> separaOFertasUsuario(List<Oferta> of, Usuario u){
        ArrayList<Oferta> ofertas = new ArrayList<>();
        of.forEach(e->{
            
            if(e.getVehiculo().getVendedor().equals(u))
                ofertas.add(e);
        });
        return ofertas;
    }
    
    public static ArrayList<Oferta> separaOfertasPlaca(List<Oferta> of, String placa){
       ArrayList<Oferta> ofertas = new ArrayList<>();
       of.forEach(e->{
            if(e.getVehiculo().getPlaca().equalsIgnoreCase(placa))
                ofertas.add(e);
        });
       return ofertas;
    }
}
