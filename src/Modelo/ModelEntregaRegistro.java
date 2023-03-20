/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
//Importamos las clases a utilizar
import java.sql.Connection;
import Modelo.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import Vista.*;
import java.sql.Time;
import java.sql.Date;
/**
 *
 * @author Jesus Gerardo
 */
public class ModelEntregaRegistro {
    //Creamos metodo que obtenga los productos devolviendo el resultado de la busqueda
    public ResultSet cargarProductos(){
        ResultSet res = null;
        try{ 
            String sql = "SELECT * FROM tbProducto";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            res = cmd.executeQuery();            
         }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido encontrar productos");
            error.setVisible(true);
        }
        return res;
    }
    //Creamos metodo que obtenga los proveedores delvolviendo el resultado de la busqueda
    public ResultSet cargarProveedores(){
        ResultSet res = null;
        try{ 
            String sql = "SELECT * FROM tbDistribuidor";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            res = cmd.executeQuery();            
         }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido encontrar distribuidores");
            error.setVisible(true);
        }
        return res;
    }
    //Creamos metodo que obtenga los registros delvolviendo el resultado de la busqueda
    public ResultSet cargarRegistros(){
        ResultSet res = null;
        try{ 
            String sql = "SELECT etg.idEntregaRegistro, etg.fechaEntrega, etg.horaIngreso, etg.cantidad, etg.precio,prd.nombreProducto,(emp.nombreEmpleado+' '+emp.apellidoEmpleado) as empleado, dst.nombreDistribuidor, prd.cantidad, etg.cantidadInicial, etg.descripcion\n" +
            "FROM tbEntregaRegistro as etg, tbProducto as prd, tbEmpleado as emp, tbDistribuidor as dst\n" +
            "WHERE etg.codigoProducto = prd.codigoProducto and etg.idEmpleado = emp.idEmpleado and etg.idProveedor = dst.idDistribuidor order by etg.idEntregaRegistro desc;";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            res = cmd.executeQuery();            
         }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido encontrar registros");
            error.setVisible(true);
        }
        return res;
    }
    //Creamos metodo que inserte en la base de datos un registro
    public boolean guardarRegistro(Date fechai,Time horai, int cantidad, int cantidadi, double precio, String descripcion, String codigoProducto,int idEmpleado, int idProveedor){
        boolean res = false;
        try{ //Realizar consulta INSERT
           String sql = "INSERT INTO tbEntregaRegistro values (?,?,?,?,?,?,?,?,?);"; //se pasan por referencia por seguridad
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la tabla
           cmd.setDate(1, fechai);
           cmd.setTime(2,horai); //codigoP es como se definio en la clase aunque en la base se llama codigoProyecto
           cmd.setInt(3, cantidad);
           cmd.setInt(4, cantidadi);
           cmd.setDouble(5, precio);
           cmd.setString(6, descripcion);
           cmd.setString(7, codigoProducto);
           cmd.setInt(8, idEmpleado);
           cmd.setInt(9, idProveedor);
            //Si da error devuelve 1, caso contrario 
           //Tomar en cuenta que el ! es negación
           if(!cmd.execute()){
              res=true;
            }
            //cerrando conexion
//            cmd.close();
//            cn.close();
        }catch(Exception e ){
            System.out.println(e.toString());
        }
        return res;
    }
    //Creamos metodo que actualice la cantiad de existencias en el inventario del producto
    public boolean actualizarCantidad(String codigoProducto, int cantidad){
        boolean res = false;
        try{ //Realizar consulta INSERT
           String sql = "UPDATE tbProducto set cantidad = ? WHERE codigoProducto=?;"; //se pasan por referencia por seguridad
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la tabla
           cmd.setInt(1, cantidad);
           cmd.setString(2,codigoProducto); 
            //Si da error devuelve 1, caso contrario 
           //Tomar en cuenta que el ! es negación
           if(!cmd.execute()){
              res=true;
            }
            //cerrando conexion
//            cmd.close();
//            cn.close();
        }catch(Exception e ){
            System.out.println(e.toString());
        }
        return res;
    }
    //Creamos metodo que actualice el Registro
    public boolean actualizarRegistro(Date fechai,Time horai, int cantidad, double precio, String descripcion, String codigoProducto,int idEmpleado, int idProveedor, int idRegistro){
        boolean res = false;
        try{ //Realizar consulta INSERT
           String sql = "update tbEntregaRegistro set fechaEntrega=?, horaIngreso=?, cantidad=?, precio=?, descripcion=?, codigoProducto= ?, idEmpleado=?, idProveedor=? where idEntregaRegistro = ?;"; //se pasan por referencia por seguridad
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la tabla
           cmd.setDate(1, fechai);
           cmd.setTime(2,horai); //codigoP es como se definio en la clase aunque en la base se llama codigoProyecto
           cmd.setInt(3, cantidad);
           cmd.setDouble(4, precio);
           cmd.setString(5, descripcion);
           cmd.setString(6, codigoProducto);
           cmd.setInt(7, idEmpleado);
           cmd.setInt(8, idProveedor);
           cmd.setInt(9, idRegistro);
            //Si da error devuelve 1, caso contrario 
           //Tomar en cuenta que el ! es negación
           if(!cmd.execute()){
              res=true;
            }
            //cerrando conexion
//            cmd.close();
//            cn.close();
        }catch(Exception e ){
            System.out.println(e.toString());
        }
        return res;
    }
    
    
    //Creamos metodo para buscar distribuidore fechaI, Date fechaFi) {
        ResultSet datos = null;
     public static ResultSet BuscadorFechas(Date fechaI, Date fechaFi) {
        ResultSet datos = null;
        try {
            //Ejecutamos consulta
            String SQL = "select e.idEntregaRegistro, e.fechaEntrega, e.horaIngreso, e.cantidad, e.cantidadInicial, e.precio, p.nombreProducto ,(emp.nombreEmpleado+' '+emp.apellidoEmpleado) as empleado, d.nombreDistribuidor  from tbEntregaRegistro e, tbProducto p, tbEmpleado emp, tbDistribuidor d where p.codigoProducto=e.codigoProducto and emp.idEmpleado=e.idEmpleado and d.idDistribuidor = e.idProveedor  and e.fechaEntrega >= '"+fechaI+"' and e.fechaEntrega<= '"+fechaFi+"' order by fechaEntrega";
            PreparedStatement ejecucion = Conexion.Conexion().prepareStatement(SQL);
           // ejecucion.setString(1, distribuidor);
             datos = ejecucion.executeQuery();
            System.out.println(fechaI);
            System.out.println(fechaFi);
            System.out.println(datos);
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Retornamos datos
        return datos;
    }
}
