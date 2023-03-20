/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author lenny
 */
public class ModeloCalendario {
    public static ResultSet ObtenerFechaRenta(){
        ResultSet res;
        try{
            String sql = "SELECT tfr.fechaDevolucion FROM tbFacturaRenta tfr, tbDetalleFacturaRenta tdfr, tbEstadoFacturaRenta tefr WHERE tdfr.idFacturaRenta =tfr.idFacturaRenta AND tdfr.idEstadoFacturaRenta = tefr.idEstadoFacturaRenta AND (tdfr.idEstadoFacturaRenta = 1 OR tdfr.idEstadoFacturaRenta = 3)";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            res = cmdselect.executeQuery();
            return res;
        }
        catch(Exception ex){
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
    
    //Método para traer los datos de el usuario
    public static boolean ActualizarEstadoRenta(){
        boolean retorno = false;
        try{
            String sql = "UPDATE tbDetalleFacturaRenta SET idEstadoFacturaRenta = 3 FROM tbDetalleFacturaRenta tdfr, tbFacturaRenta tfr WHERE tdfr.idFacturaRenta = tfr.idFacturaRenta AND tfr.fechaDevolucion < GETDATE() AND tdfr.idEstadoFacturaRenta != 2";
            PreparedStatement cmdupdate = Conexion.Conexion().prepareStatement(sql);
            if(!cmdupdate.execute()){
              retorno=true;
            }
            return retorno;
        }
        catch (Exception ex){
            return retorno;
        }
    }
}    
