/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
//Importamos todas las librerias a utilizar
import Modelo.ModelRecuperarContraseña;
import Vista.FrmNotiError;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Jesus Gerardo
 */
public class RecuperarContraseñaController {
    Encriptar encriptar = new Encriptar();
    //Creamos objeto del formulario de error
    FrmNotiError error = new FrmNotiError();
    //Creamos una variable que sera el id del usuario
    private static String Usuario ="DarkKing";
    //Creamos metodo que analice si se encontro el usuario pasando como string el nombre de usuario al model
    public boolean ConfirmarUsuario(String str){
        //Creamos objeto del modelo
        ModelRecuperarContraseña obj = new ModelRecuperarContraseña();
        //Creamos objeto de tipo boleano para confirmar
        boolean confirm=false;
        //Creamos objeto de tipo resultSet para guardar el resultado
        ResultSet res = obj.ConfirmUsuario(str);
        //Verificamos si en el resultado obtenido es null o existente
        try{
            if(res.next()){
                //Obtenemos el id de usuario
                //Asignamos a la variable Usuario el Usuario enviado
                Usuario = str;
                confirm=true;
            }else{
                //Como no se encontro resultado la confirmación sera falsa
                confirm=false;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        //retornamos la confirmación
        return confirm;
    }
    public boolean ConfirmarPIN(String str){
        //Creamos objeto del modelo
        ModelRecuperarContraseña obj = new ModelRecuperarContraseña();
        //Creamos objeto de tipo boleano para confirmar
        boolean confirm=false;
        //Creamos objeto de tipo resultSet para guardar el resultado
        ResultSet res = obj.ConfirmPIN(encriptar.Encriptar(str));
        //Verificamos si en el resultado obtenido es null o existente
        try{
            if(res.next()){
                //Como se encontro resultado la confirmación sera verdadera                
                confirm=true;
            }else{
                //Como no se encontro resultado la confirmación sera falsa
                confirm=false;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        //retornamos la confirmación
        return confirm;
    }
    //Creamos metodo con expresiones regulares que verifique que el correo tenga la sintaxis correcta
    public boolean ConfirmarCorreo(String str){
        //Creamos el patron del correo que sera la sintaxis correcta
        // Patrón para validar el email
        Pattern patron = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]{3}))+");
        //Creamos un objeto de la clase matcher que comparará el patron con el email
        Matcher sintaxis = patron.matcher(str);
        if (sintaxis.find()==true) {
            return true;
        }else{
            return false;
        }
    }
    //Creamos metodo que genere un PIN aleatorio de 4 digitos
    public String GenerarPIN(){
        //Creamos cuatro variables de tipo int que generen un número entero entre 1 y 10
        int n1=(int) (Math.random()*10+0),
            n2=(int) (Math.random()*10+0),
            n3=(int) (Math.random()*10+0),
            n4=(int) (Math.random()*10+0);
        String PIN = String.valueOf(String.valueOf(n1)+String.valueOf(n2)+String.valueOf(n3)+
                String.valueOf(n4));
        System.out.println(PIN);
        return PIN;
    }
    //Creamos metodo que envie al correo el PIN de recuperación
    public boolean EnviarCorreo(String PIN, String correo){
        //Creamos objeto de tipo Properties para almacenar las propiedades del correo
        Properties propiedad = new Properties();
        //Creamos las propiedades del correo
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");
        //Creamos la variable para la sesion
        Session sesion= Session.getDefaultInstance(propiedad);
        //Definimos las variables a utilizar para el correo
        String emisor="edreams.officialcompany@gmail.com",
               contrasena="edreamscontra",
               destinatario=correo,
               asunto="PIN de renovación de contraseña",
               mensaje="¡Ouch!Tal parece has olvidado tu contraseña, "
               + "no te preocupes estamos para ayudarte y por esto utiliza este PIN: "
               +PIN+" para comprobar que realmente eres tu. "
               + "Este PIN solo dura por una hora asique se rapido";
        //En un try enviamos los datos al objeto que sera al final un correo
        try {
            //Creamos objeto que almacene en un correo la sesion
            MimeMessage mail = new MimeMessage(sesion);
            //Añadimos la direccion del emisor
            mail.setFrom(new InternetAddress(emisor));
            //Añadimos la direccion del destinatario
            mail.addRecipient(Message.RecipientType.TO,new InternetAddress(destinatario));
            //Añadimos el asunto
            mail.setSubject(asunto);
            //Añadimos el mensaje
            mail.setText(mensaje);
            //Creamo objeto que transporte todo
            Transport transporte = sesion.getTransport("smtp");
            //Añadimos los datos necesarios para el login del correo del emisor y abrimos conexion
            transporte.connect(emisor,contrasena);
            //Añadimos el mensaje o correo al transporte
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            //Cerramos la conexión
            transporte.close();
            //Se envio
            System.out.println("Se envio");//Esto es solo para comprobar y se puede eliminar para aumentar la optimización
            return true;
        } catch (AddressException ex) {
            error.TAnotiError.setText("¡Error!No se pudo acceder al correo, por favor verificar el correo o intentelo de nuevo");
            error.setVisible(true);
            return false;
//            Logger.getLogger(RecuperarContraseñaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
//            Logger.getLogger(RecuperarContraseñaController.class.getName()).log(Level.SEVERE, null, ex);
            error.TAnotiError.setText("¡Error!No se pudo enviar el correo, por favor vuelvalo a intentar");
            System.out.println(ex);
            error.setVisible(true);
            return false;
        }
    }
    //Creamos metodo que obtenga la hora y la devuelva
    public int obtenerhora(){
        //Creamos un objeto del calendario gregoriano
        GregorianCalendar calendario = new GregorianCalendar();
        //Creamos variable hora que sera igual a la hora en formato 24hrs
        int hora=calendario.get(Calendar.HOUR);
        return hora;
    }
    /**
     * @return the Usuario
     */
    public String getUsuario() {
        return Usuario;
    }
}

