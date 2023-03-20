/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
//importamos las librerias necesarias
import Controlador.RecuperarContraseñaController;
import Modelo.ModelRenovarContraseña;
import Vista.FrmNotiError;
/**
 *
 * @author Jesus Gerardo
 */
public class RenovarContraseñaController {
    Encriptar encriptar = new Encriptar();
    //Creamos variable que ayudara a verificar si el form se cierra o no se cierra
    private static int cerrar=0;
    //Creamos objeto de tipo Recuperar Contraseña
    RecuperarContraseñaController obj = new RecuperarContraseñaController();
    //Creamos metodo que evalue que los textbox sean iguales
    public boolean contraIgual(String contra, String contrar){
        if (contra.equals(contrar)) {
            return true;
        }else{
            return false;
        }
    }
    //Creamos metodo que ejecute la actualización de la contraseña del usuario
    public boolean actualizarContra(String contraseña){
        //Creamos un String que obtenga el valor de usuario
        String usuario=obj.getUsuario();
        //Creamos objeto del modelo renovar contraseña
        ModelRenovarContraseña model = new ModelRenovarContraseña();
        boolean confirm=model.actualizarContraseña(usuario, encriptar.Encriptar(contraseña));
        return confirm;
    }

    public int getCerrar() {
        return cerrar;
    }

    public void setCerrar(int cerrar) {
        this.cerrar = cerrar;
    }
}
