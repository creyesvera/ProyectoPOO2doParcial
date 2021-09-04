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
public class Oferta {
    private int id;
    private Usuario comprador;
    private int id_comprador;
    private Vehiculo vehiculo;
    private int id_vehiculo;
    private double precio;

    public Oferta(int id, int id_comprador, int id_vehiculo, double precio) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vehiculo = id_vehiculo;
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

    public int getId_comprador() {
        return id_comprador;
    }

    public void setId_comprador(int id_comprador) {
        this.id_comprador = id_comprador;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Oferta{" + "id=" + id + ", comprador=" + comprador + ", id_comprador=" + id_comprador + ", vehiculo=" + vehiculo + ", id_vehiculo=" + id_vehiculo + ", precio=" + precio + '}';
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
        if (this.id != other.id) {
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
        }
        catch(ClassNotFoundException e){
        }
        catch(FileNotFoundException e){
        }
        catch(IOException e){
        }
        return ofertas;
        
    }
    
    /*para cada oferta de la lista ofertas añade el objeto oferta a las listas de ofertas del comprador y 
    vehículo correspondiente, se asigna los objetos comprador y vendedor al objeto oferta*/
    public static void link(ArrayList<Usuario> compradores, ArrayList<Vehiculo> vehiculos, ArrayList<Oferta> ofertas){
        for(Oferta o: ofertas){
            Usuario comp = Usuario.searchByID(compradores, o.getId_comprador());
            Vehiculo veh = Vehiculo.searchByID(vehiculos, o.getId_vehiculo());
            comp.getOfertas().add(o);
            veh.getOfertas().add(o);
            o.setComprador(comp);
            o.setVehiculo(veh);
        }
    }
    
    
    /*devuelve el objeto oferta que contenga el id “id” dentro de la lista ofertas*/
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
    
    
    
}
