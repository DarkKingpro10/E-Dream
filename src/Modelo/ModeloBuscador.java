package Modelo;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Lenny
 */
public class ModeloBuscador {
    public static ArrayList<Blob>ObtenerImagenes(int id){
        ArrayList<Blob> arreglo = new ArrayList<Blob>();
        try{
            String sql = "SELECT TOP 6 imagenProducto FROM tbProducto WHERE nombreProducto LIKE '%%' AND codigoProducto NOT IN(SELECT TOP "+id+" codigoProducto FROM tbProducto);";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            ResultSet reader = cmdselect.executeQuery();
            while(reader.next()){
                arreglo.add(reader.getBlob(1));
            }
        }
        catch(Exception e){
            return arreglo;
        }
        finally{
            try{
                Conexion.Conexion().close();
            }
            catch(Exception e){
            
            }
        }
        return arreglo;
    }
    
    public static ArrayList<String>ObtenerCodigo(int id){
        ArrayList<String> arreglo = new ArrayList<String>();
        try{
            String sql = "SELECT TOP 6 codigoProducto FROM tbProducto WHERE nombreProducto LIKE '%%' AND codigoProducto NOT IN(SELECT TOP "+id+" codigoProducto FROM tbProducto);";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            ResultSet reader = cmdselect.executeQuery();
            while(reader.next()){
                arreglo.add(reader.getString(1));
            }
        }
        catch(Exception e){
            return arreglo;
        }
        finally{
            try{
                Conexion.Conexion().close();
            }
            catch(Exception e){
            
            }
        }
        return arreglo;
    }
    
    public static ArrayList<Blob> ObtenerImagenes2(int id, String NombreP){
        ArrayList<Blob> arreglo = new ArrayList<Blob>();
        try{
            String sql = "SELECT TOP 6 imagenProducto FROM tbProducto WHERE nombreProducto LIKE '%"+NombreP+"%' AND codigoProducto NOT IN(SELECT TOP "+id+" codigoProducto FROM tbProducto);";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            ResultSet reader = cmdselect.executeQuery();
            while(reader.next()){
                arreglo.add(reader.getBlob(1));
            }
        }
        catch(Exception e){
            return arreglo;
        }
        finally{
            try{
                Conexion.Conexion().close();
            }
            catch(Exception e){
            
            }
        }
        return arreglo;
    }
    
    public static ArrayList<String> ObtenerCodigo2(int id, String NombreP){
        ArrayList<String> arreglo = new ArrayList<String>();
        try{
            String sql = "SELECT TOP 6 codigoProducto FROM tbProducto WHERE nombreProducto LIKE '%"+NombreP+"%' AND codigoProducto NOT IN(SELECT TOP "+id+" codigoProducto FROM tbProducto);";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            ResultSet reader = cmdselect.executeQuery();
            while(reader.next()){
                arreglo.add(reader.getString(1));
            }
        }
        catch(Exception e){
            return arreglo;
        }
        finally{
            try{
                Conexion.Conexion().close();
            }
            catch(Exception e){
            
            }
        }
        return arreglo;
    }
    
    public static int TotalProductos(){
        int cantidad = 0; 
        try{
            String sql = "SELECT COUNT(*) codigoProducto FROM tbProducto";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            ResultSet validacion = cmdselect.executeQuery();
            if(validacion.next() == true){
                cantidad = validacion.getInt(1);
            }
        }
        catch(Exception e){
            cantidad = -1;
        }
        return cantidad;
    }
    
    
    public static int TotalProductos2(String NombreP){
        int cantidad = 0; 
        try{
            String sql = "SELECT COUNT(*) codigoProducto FROM tbProducto WHERE nombreProducto LIKE '%"+NombreP+"%'";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            ResultSet validacion = cmdselect.executeQuery();
            if(validacion.next() == true){
                cantidad = validacion.getInt(1);
            }
        }
        catch(Exception e){
            cantidad = -1;
        }
        return cantidad;
    }
    
    public static int TotalProductos3(int id){
        int cantidad = 0; 
        try{
            String sql = "SELECT COUNT(*) codigoProducto FROM tbProducto WHERE nombreProducto LIKE '%%' AND codigoProducto NOT IN(SELECT TOP "+id+" codigoProducto FROM tbProducto);";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            ResultSet validacion = cmdselect.executeQuery();
            if(validacion.next() == true){
                cantidad = validacion.getInt(1);
            }
        }
        catch(Exception e){
            cantidad = -1;
        }
        return cantidad;
    }
}
