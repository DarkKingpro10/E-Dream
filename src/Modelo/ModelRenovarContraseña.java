/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.FrmNotiError;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Jesus Gerardo
 */
public class ModelRenovarContraseña {
    //Definiendo atributos
    private Connection cn;
    //Colocamos los set y get de los parametros
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }
    //Creamos constructor donde establecemos la conexión
    public ModelRenovarContraseña(){
    //Establecemos la conexion
       Conexion con = new Conexion();
       cn = con.Conexion();
    }
    //Creamos metodo que renueve contraseña
    public boolean actualizarContraseña(String usuario, String contraseña){
        boolean res = false;
        try{ //Realizar consulta UPDATE
           String sql = "UPDATE tbUsuario set claveUsuario=?, intento=0, horaBlock=null,horaDesBlock=null where usuario=?";
           //pide importar clase Prepared Statement
           PreparedStatement cmd = cn.prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la consulta
           //codigoP es como se definio en la clase aunque en la base se llama codigoProyecto
           cmd.setString(1, contraseña);
           cmd.setString(2, usuario);
            //Si da error devuelve 1, caso contrario 
           //Tomar en cuenta que el ! es negación
           if(!cmd.execute()){
              res=true;
            }
            //cerrando conexion
            cmd.close();
            cn.close();
        }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error! "+e);
            error.setVisible(true);
        }
        return res;
    }
}
