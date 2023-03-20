/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

//Importamos las librerias necesarias para el uso de comandos SQL
import java.sql.Connection;
import Modelo.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Jesus Gerardo
 */
public class ModelPreguntasFrecuentes {
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
    //estableciendo la conexión en el constructor
    public ModelPreguntasFrecuentes(){
    //Establecemos la conexion
       Conexion con = new Conexion();
       cn = con.Conexion();
    }
    //Creamos metodo que consulte las preguntas de la tabla
    public ResultSet CargarPreguntasFrecuentes(){
            ResultSet res = null;
            try{ 
             String sql = "SELECT * FROM tbPreguntasFrecuentes";
            //pide importar clase Prepared Statement
             PreparedStatement cmd = cn.prepareStatement(sql);
             res = cmd.executeQuery(); 
             cmd.setInt(1, codigoE);
             cmd.close();
             cn.close();
                }catch(Exception e ){
             System.out.println(e.toString());
            }
            return res;
    }
}
