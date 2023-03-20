/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
//Importamos las librerias necesarias para el uso de comandos SQL
import java.sql.Connection;
import Modelo.Conexion;
import Vista.FrmNotiError;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Jesus Gerardo
 */
public class ModelRecuperarContraseña {
    //Definiendo atributos
    private Connection cn;
    private Integer codigoE;
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
    public ModelRecuperarContraseña(){
    //Establecemos la conexion
       Conexion con = new Conexion();
       cn = con.Conexion();
    }
    //Creamos metodo que confirme que el usuario este añadio a un empleado y exista
    public ResultSet ConfirmUsuario(String str){
        //Creamos variable de tipo resulset
        ResultSet res=null;
        try{ //Realizar consulta UPDATE
           String sql = "SELECT us.idUsuario,emp.idEmpleado, us.usuario FROM tbEmpleado as emp, tbUsuario as us WHERE emp.idUsuario = us.idUsuario AND us.usuario=?";
           //pide importar clase Prepared Statement
           PreparedStatement cmd = cn.prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la consulta
           cmd.setString(1, str);
           //Ejecutar la consulta
            //pedirá importar la clase ResultSet
            res= cmd.executeQuery();
        }catch(Exception e ){
            System.out.println(e.toString());
        }
        //Devolvemos el resultado de la busqueda
        return res;
    }
    //Creamos metodo que confirme que el pin ingresado este vinculado a un empleado de tipo admin
    public ResultSet ConfirmPIN(String str){
        //Creamos variable de tipo resulset
        ResultSet res=null;
        try{ //Realizar consulta UPDATE
           String sql = "Select us.idUsuario,emp.idEmpleado, us.usuario, te.tipoEmpleado from tbEmpleado as emp, tbUsuario as us, tbTipoEmpleado as te where emp.idUsuario = us.idUsuario and us.claveUsuario=? AND emp.idTipoEmpleado=te.idTipoEmpleado AND te.tipoEmpleado='Administrador'";
           //pide importar clase Prepared Statement
           PreparedStatement cmd = cn.prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la consulta
           cmd.setString(1, str);
           //Ejecutar la consulta
            //pedirá importar la clase ResultSet
            res= cmd.executeQuery();
        }catch(Exception e ){
            System.out.println(e.toString());
        }
        //Devolvemos el resultado de la busqueda
        return res;
    }
}    
