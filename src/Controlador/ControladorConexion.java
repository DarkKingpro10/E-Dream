/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import Modelo.Conexion;
import java.sql.SQLException;

/**
 *
 * @author lenny
 */
public class ControladorConexion {
    
    //definiendo atributos
    private Connection cn;
    
    public Connection getCn() {
        return cn;
    }
    
    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    //estableciendo la conexión en el constructor
    public ControladorConexion(){
    //Establecemos la conexion
       Conexion conectar = new Conexion();
       cn = conectar.Conexion();
    }
    
    public static Connection ConnectController(){
        return Modelo.Conexion.Conexion();
    }
    
    public static boolean EstadoConnectController(){
        return Modelo.Conexion.EstadoConeccion(0);
    }
}
