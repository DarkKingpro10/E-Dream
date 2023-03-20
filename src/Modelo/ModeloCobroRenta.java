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
public class ModeloCobroRenta {
    
    
     //Cargar combobox de producto
     public static ResultSet CargarTipopago(){
        ResultSet res;
        try{
            String sql = "SELECT TipoPago  from tbTipoPago";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            res = cmdselect.executeQuery();
            return res;
        }
        catch(Exception e){
            return res = null;
        }
        finally{
            try{
                Conexion.Conexion().close();
            }
            catch(Exception e){
                
            }
        }
    }
     
      //Cargar id de combobox de tipo pago
    public  ArrayList<Integer> ConseguirIdTipoPago(){
           String sql = "select idTipoPago from tbTipopago ";
           ArrayList<Integer> arreglo=new ArrayList<Integer>();
           
         try{ 
                 PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
                 ResultSet cmdselect = cmdinsert.executeQuery();
                 
                 while (cmdselect.next()){
                     arreglo.add(cmdselect.getInt(1));
                     
              }
            }
                catch(Exception e){
                    System.out.println(e);
            }
         return arreglo;
    } 
     
    //Cargar combobox de id
     public static ResultSet CargarIdVenta(){
        ResultSet res;
        try{
            String sql = "SELECT idFacturaVenta as idVenta FROM tbFacturaVenta WHERE idFacturaVenta = (SELECT MAX(idFacturaVenta) FROM tbFacturaVenta)";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            res = cmdselect.executeQuery();
            return res;
        }
        catch(Exception e){
            return res = null;
        }
        finally{
            try{
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
        return codigoIdVenta;
    }
     
      //Cargar combobox de id de detlle
     public static ResultSet CargarIdDetalle(){
        ResultSet res;
        try{
            String sql = "SELECT idDetalleFacturaV as idDetalle FROM tbDetalleFacturaVenta WHERE idDetalleFacturaV = (SELECT MAX(idDetalleFacturaV) FROM tbDetalleFacturaVenta)";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            res = cmdselect.executeQuery();
            return res;
        }
        catch(Exception e){
            return res = null;
        }
        finally{
            try{
                Conexion.Conexion().close();
            }
            catch(Exception e){
                
            }
        }
    }
    
     //método para consultar ultimo id de detalle
    public static int ConsultarIdDetalle (int idVenta){
        int codigoIdVenta =0;
     
        try{ //Realizar consulta UPDATE
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
        return codigoIdVenta;
    }
    
    
     
     //Actualizamos datos en venta

    public static boolean ActualizarVenta(Double MontoTotalV, Double vuelto, int idTipoPago, int idVenta ){
        boolean Insercion = false;
        try{
            String sql = "update tbFacturaVenta set MontoTotalV = ? , vuelto= ? , idTipoPago=? where idFacturaVenta = ?";
            //pide importar clase Prepared StatementINSERT INTO tbEmpresa (nombreEmpresa, nit, anioFundacion, telefonoEmpresa, direccionEmpresa, idPais) VALUES (?,?,?,?,?,?)
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            
            cmdinsert.setDouble(1, MontoTotalV);
            cmdinsert.setDouble(2, vuelto);
            cmdinsert.setInt(3, idTipoPago);
            cmdinsert.setInt(4, idVenta);
            
            
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
                Conexion.Conexion().close();
            }
                catch(Exception e){
            }
        }
        return Insercion;
    }
    
    
     public static boolean EliminarDetalleV(int idDetalle){
        boolean Insercion = false;
        try{
            String sql = "delete from tbDetalleFacturaVenta where idDetalleFacturaV = ?";
//            String sqlD = "delete from tbFacturaVenta where idFacturaVenta = ?";
            //pide importar clase Prepared StatementINSERT INTO tbEmpresa (nombreEmpresa, nit, anioFundacion, telefonoEmpresa, direccionEmpresa, idPais) VALUES (?,?,?,?,?,?)
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
//            PreparedStatement cmdinsertD = Conexion.Conexion().prepareStatement(sqlD);
            
            cmdinsert.setInt(1, idDetalle);
//            cmdinsertD.setInt(1, idVenta);
          
            
            
            if(!cmdinsert.execute()){
                Insercion=true;
            }
            cmdinsert.close();
//            cmdinsertD.close();
        }
        catch(Exception e){
            System.out.println(e);
            return Insercion;
            
        }
        
        finally{
            try{ 
                Conexion.Conexion().close();
            }
                catch(Exception e){
            }
        }
        return Insercion;
    }
    
     
      
      
     
    //Para conseguir id de combobox de venta
     public  ArrayList<Integer> ConseguirIdVenta(){
           String sql = "SELECT idFacturaVenta  FROM tbFacturaVenta WHERE idFacturaVenta = (SELECT MAX(idFacturaVenta) FROM tbFacturaVenta);";
           ArrayList<Integer> arreglo=new ArrayList<Integer>();
           
         try{ 
                 PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
                 ResultSet cmdselect = cmdinsert.executeQuery();
                 
                 while (cmdselect.next()){
                     arreglo.add(cmdselect.getInt(1));
                     
              }
            }
                catch(Exception e){
                    System.out.println(e);
            }
         return arreglo;
    } 
     
       //Para conseguir id de combobox de detalle
     public  ArrayList<Integer> ConseguirIdDetalle(){
           String sql = "SELECT idDetalleFacturaV as idDetalle FROM tbDetalleFacturaVenta WHERE idDetalleFacturaV = (SELECT MAX(idDetalleFacturaV) FROM tbDetalleFacturaVenta)";
           ArrayList<Integer> arreglo=new ArrayList<Integer>();
           
         try{ 
                 PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
                 ResultSet cmdselect = cmdinsert.executeQuery();
                 
                 while (cmdselect.next()){
                     arreglo.add(cmdselect.getInt(1));
                     
              }
            }
                catch(Exception e){
                    System.out.println(e);
            }
         return arreglo;
    } 
}
