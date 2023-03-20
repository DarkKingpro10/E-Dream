/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Ericksson
 */
public class ModeloPrimerUso {
    //Método para obtener el Pais
    public static ResultSet CargarPais(){
        ResultSet res;
        try{
            String sql = "SELECT pais from tbPais";
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
    
    //Método para obtener el idPais
    public static ResultSet ObtenerIdPais(String Pais){
        ResultSet res;
            try{
                String sql = "SELECT idPais from tbPais WHERE Pais = ?";
                //pide importar clase Prepared Statement
                PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
                cmdselect.setString(1, Pais);
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
    
    //Insercion de Empresa
    public static boolean RegistrarEmpresa(String nombreEmpresa, String nit, Date anioFundacion, int telefonoEmpresa, String direccionEmpresa, int idPais){
        boolean Insercion = false;
        try{
            String sql = "INSERT INTO tbEmpresa (nombreEmpresa, nit, anioFundación, telefonoEmpresa, direccionEmpresa, idPais) VALUES (?,?,?,?,?,?)";
            //pide importar clase Prepared StatementINSERT INTO tbEmpresa (nombreEmpresa, nit, anioFundacion, telefonoEmpresa, direccionEmpresa, idPais) VALUES (?,?,?,?,?,?)
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            
            cmdinsert.setString(1, nombreEmpresa);
            cmdinsert.setString(2, nit);
            cmdinsert.setDate(3, anioFundacion);
            cmdinsert.setInt(4, telefonoEmpresa);
            cmdinsert.setString(5, direccionEmpresa);
            cmdinsert.setInt(6, idPais+1);
            
            if(!cmdinsert.execute()){
                Insercion=true;
            }
            cmdinsert.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
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
}
