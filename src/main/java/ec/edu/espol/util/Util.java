/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.model.ValoresFueraDeRango;
import ec.edu.espol.model.Vehiculo;
import static ec.edu.espol.util.Alarmas.alertaError;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author camil
 */
public class Util {
   private Util(){}
   
   public static boolean enviarCorreo(String cuerpo, String email) {
        String remitente = "";
        String clave = "";
        String asunto = "";
        Properties propsCorreo =  new Properties();
       try {
           propsCorreo.load(new FileReader("correo.properties"));
           remitente = propsCorreo.getProperty("Correo");
           clave = propsCorreo.getProperty("Clave");
           asunto = propsCorreo.getProperty("Asunto");
       } catch (FileNotFoundException ex) {
       } catch (IOException ex) {
       }
       
        Properties props = System.getProperties();
        System.out.println("Enviando correo...");
   
        props.put("mail.smtp.host", "smtp.live.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "25"); //El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(props);    
        MimeMessage message = new MimeMessage(session);  
        
        boolean isValid = false;
        try {           
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            isValid = true;
        } 
        catch (AddressException e) { 
            System.out.println("You are in catch block -- Exception Occurred for: " + email); 
            return false;
        }       
        try {
            session.getProperties().put("mail.smtp.ssl.trust", "smtp.live.com");
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));   
            message.setSubject(asunto);
            message.setText(cuerpo);
            try (Transport transport = session.getTransport("smtp")) {
                transport.connect("smtp.live.com", remitente, clave);
                
                transport.sendMessage(message, message.getAllRecipients());
            }
        }
        catch (MessagingException me) {
            System.err.println(me.getMessage());   //Si se produce un error
        }
        return true;
    }
    
    //comportamientos Hashcode
    public static byte[] getSHA(String input) {
        MessageDigest md =null;
        
        try {
            md = MessageDigest.getInstance("SHA-256");
        }catch (NoSuchAlgorithmException ex) {
            //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex); ///REVISAR
        }
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convierte un array de bytes en representación de signum
        BigInteger number = new BigInteger(1, hash);
        // Convierte el mensaje en un valor hexadecimal
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');// coloca ceros a la izquierda
        }
        return hexString.toString(); //retorna el string en el formato toString() antes programado
    }
    
    public static Boolean validacionCorreo(String email){
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");         
        Matcher mather = pattern.matcher(email);
        
        return mather.find();
    } //true si el correo esta bien escrito

    //true si la placa existe en la lista de vehiculos
    public static Boolean validacionPlaca(String placa, ArrayList<Vehiculo> vehiculos){        
       if (vehiculos.stream().anyMatch(vehiculo -> (vehiculo.getPlaca().equals(placa)))) {
           return true;
       }
        return false;
    }
    
    public static boolean esDouble(String numero){
        try{
            double num = Double.parseDouble(numero);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }
    public static boolean esInteger(String numero){
        try{
            int num = Integer.parseInt(numero);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }
    
    public static int[] validarRangosInt(String datos) throws ValoresFueraDeRango{
        int [] rangos = new int[2];        
        String [] data = datos.split("-");
       switch (data.length) {
           case 0:
               rangos[0] = 0;
               rangos[1] = Integer.MAX_VALUE;
               break;
           case 1:
               rangos[0] = Integer.parseInt(data[0].trim());
               rangos[1] = Integer.MAX_VALUE;
               break;
           case 2:
               if(Integer.parseInt(data[0].trim())>Integer.parseInt(data[1].trim())){
                   throw new ValoresFueraDeRango("El valor inicial no puede ser mayor que el final");
               }else{
                   rangos[0] = Integer.parseInt(data[0].trim());
                   rangos[1] = Integer.parseInt(data[1].trim());
               }  
               break;
           default:
               break;
       }
        return rangos;
    }
    
    public static double[] validarRangosDouble(String datos) throws ValoresFueraDeRango{
        double [] rangos = new double[2];        
        String [] data = datos.split("-");
       switch (data.length) {
           case 0:
               rangos[0] = 0;
               rangos[1] = Double.MAX_VALUE;
               return rangos;
           case 1:
               rangos[0] = Double.parseDouble(data[0].trim());
               rangos[1] = Double.MAX_VALUE;
               return rangos;
           case 2:
               if(Double.parseDouble(data[0].trim())>Double.parseDouble(data[1].trim())){
                   throw new ValoresFueraDeRango("El valor inicial no puede ser mayor que el final");
               }else{
                   rangos[0] = Double.parseDouble(data[0].trim());
                   rangos[1] = Double.parseDouble(data[1].trim());
                   return rangos;
               }
           default:
               break;
       }
        return null;
    }
}
