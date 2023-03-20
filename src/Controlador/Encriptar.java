package Controlador;
//Importamos las librerias necesarias
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author Jesus Gerardo
 */
public class Encriptar{
    //Creamos la llave para encriptar y desencriptar
    String LLAVE = "SomosEdreams";
    //Clave de encriptación/desencriptar
    public SecretKeySpec CrearClave(String llave){
        try{
            //Creamos un arreglo de byte que obtenga los byte de la llave añadiendo caracteres especiales
            byte[] cadena = llave.getBytes("UTF-8");
            //Creamos un objeto de tipo MessageDigest que utilizara el metodo SHA-1 para crear la clave
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            //Asignamos el metodo SHA-1 en la cade
            cadena = md.digest();
            //Limitamos el número de caracteres a 16 en clave
            cadena = Arrays.copyOf(cadena, 16);
            //Añadimos encriptacion AES a una llave
            SecretKeySpec secretKeySpec = new SecretKeySpec(cadena, "AES");
            //Retornamos la clave que servira para encriptar y desencripta
            return secretKeySpec;
        }catch(Exception e){
            return null;
        }
    }
    //Creamos metodos de encriptar
    public String Encriptar(String encriptar){
        try{
            //Creamos una clave para encriptar que sera igual a la llave del metodo
            SecretKeySpec secretKeySpec = CrearClave(LLAVE);
            //Creamos objeto de tipo cipher para encriptar con el metodo AES
            Cipher cipher = Cipher.getInstance("AES");
            //Añadimos encriptación en la clave y la unimos
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
            //Creamos arreglo de byte que sera igual a la clave(encriptar) y se permite caracteres especiales
            byte[] cadena = encriptar.getBytes("UTF-8");
            //Al objeto cipher que mantiene el metodo de encriptacion lo asignamos a la clave
            byte[] encriptada = cipher.doFinal(cadena);
            //Convertimos el byte a String permaneciendo la encriptacion
            String cadena_encriptada = Base64.encode(encriptada);
            //retornamos el String encriptado
            return cadena_encriptada;
        }catch(Exception e){
            //Enviamos un valor nulo
            return "";
        }
    }
    //Desencriptar
    public String Desencriptar(String descencriptar){
        try{
            //Definimos la clave para desencriptar
            SecretKeySpec secretKeySpec = CrearClave(LLAVE);
            //Definimos el metodo de encriptación AES y se lo asignamos a un objeto de tipo Cipher
            Cipher cipher = Cipher.getInstance("AES");
            //Al objeto cipher le asignamos el metodo de desencriptar
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
            //Convertimos a cadena de byte la clave a desencriptar
            byte[] cadena = Base64.decode(descencriptar);
            //Creamos un arreglo de byte que desencriptara el arreglo de bytes con el objeto cipher que contiene el metodo de desencriptado
            byte[] descencriptacion = cipher.doFinal(cadena);
            //Convertimos el arreglo a string
            String cadena_desencriptada = new String(descencriptacion);
            //Retornamos la clave desencriptada
            return cadena_desencriptada;
        }catch(Exception e){
            //Enviamos un valor nulo
            return "";
        }
    }
}
