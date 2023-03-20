/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author pc
 */
public class ModeloReporteP {
    //Creamos metodo booleano para insertar error 
     public static boolean InsertarP(String correo, String error, String imagen ){
        boolean res = false;
        try{ //Realizar consulta INSERT
           String sql = "insert into tbErrores (correo,error,imagenError) values (?,?,?)"; //se pasan por referencia por seguridad
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la tabla
         
           //Creamos objeto FileInputStream para acceder a los bytes de imagenes 
          FileInputStream imagenP = null;
            String foto = imagen;
             //Creamos objeto File para almacenr el archivo de la imagen
            File objFile = new File(foto);
             //Guardamos la iamgen en el parametro de ImagenE
            imagenP = new FileInputStream(objFile); 

            cmd.setString(1, correo);//Guardamos correo
            cmd.setString(2, error);//Guardamos la decripcion de error
            cmd.setBinaryStream(3,imagenP);//Guardamos imagen
          
            //Si da error devuelve 1, caso contrario 
           //Tomar en cuenta que el ! es negación
           if(!cmd.execute()){
              res=true;
            }
            //cerrando conexion
            cmd.close();
           Conexion.Conexion().close();
        }catch(Exception e ){
            System.out.println(e.toString());
        }
        //Retornamos res
        return res;
    }
}
