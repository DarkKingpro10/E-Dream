/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloReporteP;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author pc
 */
public class ControladorReporteP {
 
    //Creamos objeto de modelo de reporte
    ModeloReporteP obj = new ModeloReporteP();
    
    //Creamos metodo para retornar el metodo de insertar problema
     public boolean IngresarErrorController(){
        return Modelo.ModeloReporteP.InsertarP(correo,error, imagenP);
    }
     
     //Creamos metodo con expresiones regulares que verifique que el correo tenga la sintaxis correcta
    public static boolean ConfirmarCorreo(String str) {
        //Creamos el patron del correo que sera la sintaxis correcta
        // Patrón para validar el email
        Pattern patron = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]{3}))+");
        //Creamos un objeto de la clase matcher que comparará el patron con el email
        Matcher sintaxis = patron.matcher(str);
        if (sintaxis.find() == true) {
            return true;
        } else {
            return false;

        }
    }
     
     
     //Atributos
     private static String correo;//Atributo de correo
     private static String error;//Atributo de descripcion de error
     private static String imagenP;//Atributo de imagen
     
     //Creamos get y set
    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the imagenP
     */
    public String getImagenP() {
        return imagenP;
    }

    /**
     * @param imagenP the imagenP to set
     */
    public void setImagenP(String imagenP) {
        this.imagenP = imagenP;
    }
}
