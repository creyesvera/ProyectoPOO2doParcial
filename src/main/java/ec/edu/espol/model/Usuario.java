/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.excepciones.ValueTypeException;
import ec.edu.espol.util.Util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Usuario implements Serializable {
    private int id;
    private String nombres, apellidos, correo_elec, organizacion, clave; //validar que el correo sea unico
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Oferta> ofertas;
    private TipoUsuario tipo;
    private static final long serialVersionUID = 8456845698545269L;

    public Usuario(int id, String nombres, String apellidos, String correo_elec, String organizacion, String clave, TipoUsuario tipo) throws ValueTypeException {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        if(Util.validacionCorreo(correo_elec))
            this.correo_elec = correo_elec;
        else
            throw new ValueTypeException("El correo que ingreso no es valido");
        this.organizacion = organizacion;
        this.clave = Util.toHexString(Util.getSHA(clave));
        this.vehiculos = new ArrayList<>();
        this.ofertas = new ArrayList<>();
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo_elec() {
        return correo_elec;
    }

    public void setCorreo_elec(String correo_elec) throws ValueTypeException {
        if(Util.validacionCorreo(correo_elec))
            this.correo_elec = correo_elec;
        else
            throw new ValueTypeException("El correo es incorrecto");
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = Util.toHexString(Util.getSHA(clave));
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
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
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", correo_elec=" + correo_elec + ", organizacion=" + organizacion + ", vehiculos=" + vehiculos + ", ofertas=" + ofertas + '}';
    }
    
    public static void saveFile(String nomfile, ArrayList<Usuario> usuarios){
        try
        {
            FileOutputStream file = new FileOutputStream(nomfile);
            ObjectOutputStream object = new ObjectOutputStream(file);
            object.writeObject(usuarios);
            object.flush();
        }
        catch(IOException e){
        }
    }
    
    public static ArrayList<Usuario> readFile(String nomfile){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try{
            FileInputStream file = new FileInputStream(nomfile);
            ObjectInputStream object = new ObjectInputStream(file);
            usuarios = (ArrayList<Usuario>) object.readObject();
        }
        catch(ClassNotFoundException e){
        }
        catch(FileNotFoundException e){
        }
        catch(IOException e){
        }
        return usuarios;
        
    }    
    
    //devuelve un objeto usuario (obtenido de la lista) que coincida con el id
    public static Usuario searchByID(ArrayList<Usuario> usuarios, int id)
    {
        for(Usuario user : usuarios)
        {
            if(user.id == id)
                return user;
        }
        return null;
    }
    
    //devuelve un objeto usuario (obtenido de la lista) que coincida con el correo y la clave
    public static Usuario searchByCorreoYClave(ArrayList<Usuario> usuarios, String correo,String clave)
    {
        for(Usuario user : usuarios)
        {
            if(user.correo_elec.equals(correo) && user.clave.equals(Util.toHexString(Util.getSHA(clave))))
                return user;
        }
        return null;
    }
    
    //devuelve el id de un usuario segun el correo
    public static int searchBycorreoID(String correo,ArrayList<Usuario> usuarios){
        for(Usuario user : usuarios)
        {
            if(user.getCorreo_elec().equals(correo))
                return user.getId();
        }
        return -1;
    }
    
    public void comprar(Vehiculo v, String nomfile_ofertas, double precio){ //ofertas.txt
        int id_oferta = Oferta.nextID(nomfile_ofertas);
        Oferta new_oferta = new Oferta(id_oferta, this.getId(), v.getId(), precio);
        ArrayList<Oferta> ofertas = Oferta.readFile(nomfile_ofertas);
        ofertas.add(new_oferta);
        Oferta.saveFile(nomfile_ofertas, ofertas);
    }
    
    public static void vender(Vehiculo v,String nomfile_vehiculos, String nomfile_ofertas){       //lista de vehiculos del vendedor
        ArrayList<Vehiculo> vehiculos = Vehiculo.readFile(nomfile_vehiculos);
        vehiculos.remove(v);
        Vehiculo.saveFile(nomfile_vehiculos,vehiculos);
        ArrayList<Oferta> ofertas_v = v.getOfertas();
        ArrayList<Oferta> ofertas = Oferta.readFile(nomfile_ofertas);
        for(Oferta o : ofertas_v)
            ofertas.remove(o);
        Oferta.saveFile(nomfile_ofertas,ofertas);       
        }
    
    public static int nextID(String nomfile){
        int id = 0;
        ArrayList<Usuario> usuarios = Usuario.readFile(nomfile);
        for(Usuario u: usuarios){
            if (u.getId()> id)
                id = u.getId();
        }
        return id+1;
    }
}
