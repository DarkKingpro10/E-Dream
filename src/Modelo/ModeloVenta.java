/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author pc
 */

import java.sql.Connection;
import Modelo.Conexion;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;

public class ModeloVenta {

    //definiendo atributos
     private Connection cn;

    //estableciendo la conexión en el constructor
    public ModeloVenta(){
   //Establecemos la conexion
       Conexion con = new Conexion();
       cn = con.Conexion();
    }
    
 
    
         //Insercion de Venta
    public static boolean IngresarVenta(Date fechaFacturaVenta, Double MontoTotalV , String nombreCliente, String telefonoCliente, Double vuelto, int idEmpresa, int idTipoPago, int idEmpleado ){
        //Craemos variable tiipo booleano llamada res
        boolean Insercion = false;//Inicializamos con falso
        try{//Se crea consulta de insercion
            String sql = "insert into tbFacturaVenta (fechaFacturaVenta,MontoTotalV,nombreCliente,telefonoCliente,vuelto,idEmpresa,idTipoPago,idEmpleado) VALUES (?,?,?,?,?,?,?,?)";
            //pide importar clase Prepared StatementINSERT INTO tbEmpresa (nombreEmpresa, nit, anioFundacion, telefonoEmpresa, direccionEmpresa, idPais) VALUES (?,?,?,?,?,?)
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            
            cmdinsert.setDate(1, fechaFacturaVenta);//Guardamos fecha de factura
            cmdinsert.setDouble(2, MontoTotalV);//Guardamos monto total de venta
            cmdinsert.setString(3, nombreCliente);//Guardamos nombre de cliente
            cmdinsert.setString(4, telefonoCliente);//Guardamos telefono de cliente
            cmdinsert.setDouble(5, vuelto);//Guardamos vuelto
            cmdinsert.setInt(6, idEmpresa);//Guardamos el id de empleado
            cmdinsert.setInt(7, idTipoPago);//Guardamos el id de tipo de pago
            cmdinsert.setInt(8, idEmpleado);//Guardamos id de empresa
            
            //Si da error devuelve 1, caso contrario 
            //Tomar en cuenta que el ! es negación
            if(!cmdinsert.execute()){
                Insercion=true;
            }
            
            cmdinsert.close();
        }
        catch(Exception e){
            System.out.println(e);
            return Insercion;
            
        }
        
        finally{
            try{ 
                 //cerrando conexion
                Conexion.Conexion().close();
            }
                catch(Exception e){
            }
        }
        //Devolvemos variable res
        return Insercion;
    }
    
    //Cargar combobox de empresa
     public static ResultSet CargarEmpresa(){
        ResultSet res;
        try{
            //Creamos conulta para seleccionar nombre de empresa
            String sql = "SELECT nombreEmpresa  from tbEmpresa";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta
            res = cmdselect.executeQuery();
            return res;
        }
        catch(Exception e){
            return res = null;
        }
        finally{
            try{
                //Cerramos conexion
                Conexion.Conexion().close();
            }
            catch(Exception e){
                
            }
        }
    }
    

       //Cargar combobox de empleado
     public static ResultSet CargarEmpleado(){
        ResultSet res;
        try{
            //Creamos conulta para seleccionar nombre de empleado
            String sql = "select nombreEmpleado from tbEmpleado";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta
            res = cmdselect.executeQuery();
            return res;
        }
        catch(Exception e){
            return res = null;
        }
        finally{
            try{
                 //Cerramos conexion
                Conexion.Conexion().close();
            }
            catch(Exception e){
                
            }
        }
    }
    
      
    //Cargar combobox de tipoPago
     public static ResultSet CargarTipoPago(){
        ResultSet res;
        try{
            //Creamos conulta para seleccionar tipo de pago
            String sql = "SELECT TipoPago  from tbTipoPago";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta
            res = cmdselect.executeQuery();
            return res;
        }
        catch(Exception e){
            return res = null;
        }
        finally{
            try{
                //Cerramos conexion
                Conexion.Conexion().close();
            }
            catch(Exception e){
                
            }
        }
    }
     
    
    
    //Para conseguir id de combobox de empresa
     public  ArrayList<Integer> ConseguirIdEmpresa(){
           //Creamos la consulta para seleccionar el id de empresa
           String sql = "select idEmpresa from tbEmpresa ";
           //Creamos arreglo
           ArrayList<Integer> arreglo=new ArrayList<Integer>();
           
         try{ 
                 //Establecemos la conexion
                 PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
                 //Ejecutamos la consulta 
                 ResultSet cmdselect = cmdinsert.executeQuery();
                 
                 while (cmdselect.next()){
                     //Agregamos el id al arreglo
                     arreglo.add(cmdselect.getInt(1));
                     
              }
            }
                catch(Exception e){
                    System.out.println(e);
            }
         //Regresamos arreglo
         return arreglo;
    } 
    
    
    //Para conseguir los id de combobox de empleado
    public  ArrayList<Integer> ConseguirIdEmpleado(){
        //Creamos la consulta para seleccionar el id de empleado
           String sql = "select idEmpleado from tbEmpleado ";
           ArrayList<Integer> arreglo=new ArrayList<Integer>();
           
         try{ 
                 //Establecemos la conexion
                 PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
                  //Ejecutamos la consulta 
                 ResultSet cmdselect = cmdinsert.executeQuery();
                 
                 while (cmdselect.next()){
                     //Agregamos el id al arreglo
                     arreglo.add(cmdselect.getInt(1));
                     
              }
            }
                catch(Exception e){
                    System.out.println(e);
            }
          //Retornamos el arreglo
         return arreglo;
    } 
    
    //Cargar id de combobox de tipo pago
    public  ArrayList<Integer> ConseguirIdTipoPago(){
           //Creamos consulta de id de tipo pago
           String sql = "select idTipoPago from tbTipopago ";
           //Creamos arreglo
           ArrayList<Integer> arreglo=new ArrayList<Integer>();
           
         try{ 
                 PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
                 //Ejecutamos la consulta
                 ResultSet cmdselect = cmdinsert.executeQuery();
                 
                 while (cmdselect.next()){
                     //Ingresamos en arreglo el id
                     arreglo.add(cmdselect.getInt(1));
                     
              }
            }
                catch(Exception e){
                    System.out.println(e);
            }
         //Se regresa el arreglo
         return arreglo;
    } 
  
    public static int ConsultrEmpleado(String empleado){
        int codigop=0;
        try{ //Realizar consulta UPDATE
           String sql = "SELECT idEmpleado, (emp.nombreEmpleado+' '+emp.apellidoEmpleado) as empleado FROM ebEmpleado WHERE empleado=?";
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la consulta
           cmd.setString(1, empleado);

           //Ejecutar la consulta
            //pedirá importar la clase ResultSet
            ResultSet rs = cmd.executeQuery();
            //recorrer la lista de registros
            if(rs.next()){
              //asignándole a los atributos de la clase
              codigop = rs.getInt(1);
              return codigop;
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        }catch(Exception e ){
            System.out.println(e.toString());
        }
        return codigop;
    }
     
     public int IdEmpresa(int ID) {
        int Empresa = 0;
        try {
            String SQL = "SELECT idEmpresa FROM tbEmpleado WHERE idEmpleado=?;";
            PreparedStatement SELECT = Conexion.Conexion().prepareStatement(SQL);
            SELECT.setInt(1, ID);
            ResultSet datos = SELECT.executeQuery();
            while (datos.next()) {
                Empresa = datos.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Modelo IDEmpresa" + e);
        }
        return Empresa;
    }
     
    /**
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }



}
