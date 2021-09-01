/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

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
/**
 *
 * @author camil
 */
public class Util {
   private Util(){}
   
   public boolean enviarCorreo(String cuerpo, String email) {
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
   
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

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

            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));   
            message.setSubject(asunto);
            message.setText(cuerpo);
            try (Transport transport = session.getTransport("smtp")) {
                transport.connect("smtp.office365.com", remitente, clave);
                
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

}
