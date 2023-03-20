/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class ModeloCobroVenta {
    
    
     //Cargar combobox de producto
     public static ResultSet CargarTipopago(){
        ResultSet res;
        try{
            //Creamos conulta para seleccionar tipo de pago
            String sql = "SELECT TipoPago  from tbTipoPago";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
           //Ejecutamos consulta
            res = cmdselect.executeQuery();
            return res;
        }
        catch(Exception e){
            return res = null;
        }
        finally{
            try{
                //Cerramos consexion
                Conexion.Conexion().close();
            }
            catch(Exception e){
                
            }
        }
    }
     
      //Cargar id de combobox de tipo pago
    public  ArrayList<Integer> ConseguirIdTipoPago(){
           //Creamos la consulta para seleccionar el id de empresa
           String sql = "select idTipoPago from tbTipopago ";
           //Creamos un ArrayList
           ArrayList<Integer> arreglo=new ArrayList<Integer>();
           
         try{ 
                 PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
                 //Ejecutamos la consulta 
                 ResultSet cmdselect = cmdinsert.executeQuery();
                 
                 while (cmdselect.next()){
                     //Agregamos el tipo de pago al arreglo
                     arreglo.add(cmdselect.getInt(1));
                     
              }
            }
                catch(Exception e){
                    System.out.println(e);
            }
         //Retornamos arreglo
         return arreglo;
    } 
     
    //Cargar combobox de id de factura venta
     public static ResultSet CargarIdVenta(){
        ResultSet res;
        try{
            //Se crea la consulta para seleccionar id de venta
            String sql = "SELECT idFacturaVenta as idVenta FROM tbFacturaVenta WHERE idFacturaVenta = (SELECT MAX(idFacturaVenta) FROM tbFacturaVenta)";
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
                //Cerramos la conexion
                Conexion.Conexion().close();
            }
            catch(Exception e){
                
            }
        }
    }
    
     //método para consultar ultimo id de venta
    public static int ConsultarIdVenta (int idVenta){
        int codigoIdVenta =0;
     
        try{ //Realizar consulta UPDATE
           String sql = "SELECT idFacturaVenta FROM tbFacturaVenta WHERE idFacturaVenta = (SELECT MAX(idFacturaVenta) FROM tbFacturaVenta)";
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la consulta
           // cmd.setString(1,proyecto);
           
             //Ejecutar la consulta
             //pedirá importar la clase ResultSet
             ResultSet rs = cmd.executeQuery();
             //recorrer la lista de registros
            if(rs.next()){
             
             //asignándole a los atributos de la clase
             codigoIdVenta = rs.getInt(1);
             return codigoIdVenta;
             // proyecto = rs.getString(5);
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        }catch(Exception e ){
            System.out.println(e.toString());
        }
        //Retornamos el codigo de venta
        return codigoIdVenta;
    }
     
      //Cargar combobox de id de detlle
     public static ResultSet CargarIdDetalle(){
        ResultSet res;
        try{
            //Se crea la consulta para seleccionar el id de detalle de factura venta
            String sql = "SELECT idDetalleFacturaV as idDetalle FROM tbDetalleFacturaVenta WHERE idDetalleFacturaV = (SELECT MAX(idDetalleFacturaV) FROM tbDetalleFacturaVenta)";
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
    
     //método para consultar ultimo id de detalle
    public static int ConsultarIdDetalle (int idVenta){
        int codigoIdVenta =0;
     
        try{ //Realizar consulta SELECT
           String sql = "SELECT idDetalleFacturaV as idDetalle FROM tbDetalleFacturaVenta WHERE idDetalleFacturaV = (SELECT MAX(idDetalleFacturaV) FROM tbDetalleFacturaVenta)";
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la consulta
           // cmd.setString(1,proyecto);
           
           //Ejecutar la consulta
            //pedirá importar la clase ResultSet
            ResultSet rs = cmd.executeQuery();
            //recorrer la lista de registros
            if(rs.next()){
             
              //asignándole a los atributos de la clase
             codigoIdVenta = rs.getInt(1);
             return codigoIdVenta;
             // proyecto = rs.getString(5);
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        }catch(Exception e ){
            System.out.println(e.toString());
        }
        //Se retorna el codigo de venta
        return codigoIdVenta;
    }
    
    
     //Actualizamos datos en venta

    public static boolean ActualizarVenta(Double MontoTotalV, Double vuelto, int idTipoPago, int idVenta ){
        boolean Insercion = false;
        try{//Se crea el UPDAte
            String sql = "update tbFacturaVenta set MontoTotalV = ? , vuelto= ? , idTipoPago=? where idFacturaVenta = ?";
            //pide importar clase Prepared StatementINSERT INTO tbEmpresa (nombreEmpresa, nit, anioFundacion, telefonoEmpresa, direccionEmpresa, idPais) VALUES (?,?,?,?,?,?)
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            
            cmdinsert.setDouble(1, MontoTotalV);//Se envia el monto total
            cmdinsert.setDouble(2, vuelto);//SSe envia el vuelto
            cmdinsert.setInt(3, idTipoPago);//Se envia el tipo de pago
            cmdinsert.setInt(4, idVenta);//Se envia el id de venta
            
            
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
                //Se cieera conexion
                Conexion.Conexion().close();
            }
                catch(Exception e){
            }
        }//Se retorna
        return Insercion;
    }
    
    
     public static boolean EliminarDetalleV(int idDetalle){
        boolean Eliminar = false;
        try{//Se hace el delete
            String sql = "delete from tbDetalleFacturaVenta where idDetalleFacturaV = ?";
//            String sqlD = "delete from tbFacturaVenta where idFacturaVenta = ?";
            //pide importar clase Prepared StatementINSERT INTO tbEmpresa (nombreEmpresa, nit, anioFundacion, telefonoEmpresa, direccionEmpresa, idPais) VALUES (?,?,?,?,?,?)
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            
            cmdinsert.setInt(1, idDetalle);//Se envia el id de deralle
   
            if(!cmdinsert.execute()){
                Eliminar=true;
            }
            cmdinsert.close();

        }
        catch(Exception e){
            System.out.println(e);
            return Eliminar;
            
        }
        
        finally{
            try{ 
                //Se cierra la conexion
                Conexion.Conexion().close();
            }
                catch(Exception e){
            }
        }
        //Se retorna eliminar
        return Eliminar;
    }
    
     
    //Para conseguir id de combobox de venta
     public  ArrayList<Integer> ConseguirIdVenta(){
           //Se crea la consulta
           String sql = "SELECT idFacturaVenta  FROM tbFacturaVenta WHERE idFacturaVenta = (SELECT MAX(idFacturaVenta) FROM tbFacturaVenta);";
           //Se crea un Arraylist
           ArrayList<Integer> arreglo=new ArrayList<Integer>();
           
         try{ 
                 PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
                 //Se ejecuta la conulta
                 ResultSet cmdselect = cmdinsert.executeQuery();
                 
                 while (cmdselect.next()){
                     //Se agrega el id al arreglo
                     arreglo.add(cmdselect.getInt(1));
                     
              }
            }
                catch(Exception e){
                    System.out.println(e);
            }
         //Se retorna arreglo
         return arreglo;
    } 
     
       //Para conseguir id de combobox de detalle
     public  ArrayList<Integer> ConseguirIdDetalle(){
          //Se crea la consulta para el id de detalle factura venta
           String sql = "SELECT idDetalleFacturaV as idDetalle FROM tbDetalleFacturaVenta WHERE idDetalleFacturaV = (SELECT MAX(idDetalleFacturaV) FROM tbDetalleFacturaVenta)";
           ArrayList<Integer> arreglo=new ArrayList<Integer>();
           
         try{ 
                 PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
                 //Se ejcuta el codigo
                 ResultSet cmdselect = cmdinsert.executeQuery();
                 
                 while (cmdselect.next()){
                     //Se agrega el id al arreglo
                     arreglo.add(cmdselect.getInt(1));
                     
              }
            }
                catch(Exception e){
                    System.out.println(e);
            }
         //Se retorna arreglo
         return arreglo;
    } 
     
     
     //Metodo para cargar suma total de venta de ultimo cliente 
       public static int ConsultarSumaTotalV (){
        int codigoIdVenta =0;
     
        try{ //Realizar consulta SELECT
           String sql = "select sum (descuento) from tbDetalleFacturaVenta where idFacturaVenta = (SELECT MAX(idFacturaVenta) FROM tbFacturaVenta);";
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la consulta
           // cmd.setString(1,proyecto);
           
           //Ejecutar la consulta
            //pedirá importar la clase ResultSet
            ResultSet rs = cmd.executeQuery();
            //recorrer la lista de registros
            if(rs.next()){
             
              //asignándole a los atributos de la clase
             codigoIdVenta = rs.getInt(1);
             return codigoIdVenta;
             // proyecto = rs.getString(5);
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        }catch(Exception e ){
            System.out.println(e.toString());
        }
        //Se retorna el codigo de venta
        return codigoIdVenta;
    }
   
}

