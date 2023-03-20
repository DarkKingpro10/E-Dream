/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.FrmNotiError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ericksonn
 */
public class ModeloDistribuidor {
    //Metodo para cargar los datos de la Tabla
    public static ResultSet CargarTabla(){
        ResultSet res;
        try {
            String sql = "select idDistribuidor, nombreDistribuidor from tbDistribuidor";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            res = cmdselect.executeQuery();
            return res;
        }
        catch (Exception e) {
            return res = null;
        } 
        finally {
            try {
                Conexion.Conexion().close();
            } catch (Exception e) {

            }
        }
    }

    //Metodo par ingresar datos a la base de datos    
    public static boolean IngresarDistribuidor(String nombreDis) {
        boolean Insercion = false;
        try {
            String sql = "INSERT INTO tbDistribuidor (nombreDistribuidor) VALUES (?)";
            //pide importar clase Prepared StatementINSERT INTO tbEmpresa (nombreEmpresa, nit, anioFundacion, telefonoEmpresa, direccionEmpresa, idPais) VALUES (?,?,?,?,?,?)
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);

            cmdinsert.setString(1, nombreDis);

            if (!cmdinsert.execute()) {
                Insercion = true;
            }
            cmdinsert.close();
        } 
        catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de Inserción");
            error.TAnotiError.setText("Ha ocurrido un error en el momento de insertar distribuidor.");
            error.setVisible(true);
            return Insercion;
        } 
        finally {
            try {
                Conexion.Conexion().close();
            } 
            catch (Exception e) {
            }
        }
        return Insercion;
    }

    //Metodo de actualizar distribuidor     
    public static boolean ActualizarDistribuidor(String nombreDis, int idDistribuidor) {
        boolean Insercion = false;
        try {
            String sql = "update tbDistribuidor set nombreDistribuidor=? where idDistribuidor=? ";
            //pide importar clase Prepared StatementINSERT INTO tbEmpresa (nombreEmpresa, nit, anioFundacion, telefonoEmpresa, direccionEmpresa, idPais) VALUES (?,?,?,?,?,?)
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);

            cmdinsert.setString(1, nombreDis);
            cmdinsert.setInt(2, idDistribuidor);

            if (!cmdinsert.execute()) {
                Insercion = true;
            }
            cmdinsert.close();
        }
        catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de Actualización");
            error.TAnotiError.setText("Ha ocurrido un error en el momento de actualizar distribuidor.");
            error.setVisible(true);
            return Insercion;
        } 
        finally {
            try {
                Conexion.Conexion().close();
            } 
            catch (Exception e) {
                
            }
        }
        return Insercion;
    }

    //Metodo de eliminar distribuidor    
    public static boolean EliminarDistribuidor(int idDistribuidor) {
        boolean Insercion = false;
        try {
            String sql = "delete from tbDistribuidor where idDistribuidor =? ";
            //pide importar clase Prepared StatementINSERT INTO tbEmpresa (nombreEmpresa, nit, anioFundacion, telefonoEmpresa, direccionEmpresa, idPais) VALUES (?,?,?,?,?,?)
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            cmdinsert.setInt(1, idDistribuidor);

            if (!cmdinsert.execute()) {
                Insercion = true;
            }
            cmdinsert.close();
        }catch(Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de eliminación");
            error.TAnotiError.setText("Ha ocurrido un error en el momento de eliminar distribuidor.");
            error.setVisible(true);
            return Insercion;
        }
        finally{
            try {
                Conexion.Conexion().close();
            }catch(Exception e){
                
            }
        }
        return Insercion;
    }

    //Metodo de la tabla Distribuidor
    public static ResultSet TablaDistribuidor() {
        Conexion con = new Conexion();
        Connection cn = con.Conexion();

        Statement st;
        ResultSet datos = null;
        try {
            st = cn.createStatement();
            datos = st.executeQuery("SELECT p.nombreProducto, m.nombreMarca, e.nombreTipo, p.precio, p.cantidad from tbProducto p, tbMarca m, tbEstadoProducto e WHERE m.idMarca = p.idMarca AND e.idEstadoProducto = p.idEstadoProducto AND p.idTipoInventario =1 AND p.idTipo BETWEEN 1 AND 3");
        } catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("Ha ocurrido un error en el momento de obtener valores de la Tabla Distribuidor");
            error.setVisible(true);
        }
        return datos;
    }

    //Metodo para cargar el codigo de Distribuidor    
    public ResultSet CargarCodigoDistribuidor() {
        ResultSet productos = null;
        String SQL = "SELECT idDistribuidor FROM tbDistribuidor";
        try {
            PreparedStatement Select = Conexion.Conexion().prepareStatement(SQL);
            productos = Select.executeQuery();
        } catch (SQLException e) {
            System.out.print("Error al cargar los dstribuidores> " + e.toString());
        }
        return productos;
    }

    //Metodo para incrementear el Id de Distribuidor    
    public ArrayList<Integer> IDDistribuidor() {
        ArrayList<Integer> codigo = new ArrayList<>();
        try {
            ResultSet Codigos = CargarCodigoDistribuidor();
            while (Codigos.next()) {
                codigo.add(Codigos.getInt(1));
            }
        } catch (SQLException e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("Error al cargar las distribuidres");
            error.setVisible(true);
        }
        return codigo;
    }

    //Metodo para cargar dstos del menu     
    public static Object[] CargarDatosDistribuidor(Integer Identificador) {
        Object[] datos = new Object[13];
        try {
            String SQL = "select * from tbDistribuidor where idDistribuidor = ?";
            PreparedStatement ejecucion = Conexion.Conexion().prepareStatement(SQL);
            ejecucion.setInt(1, Identificador);
            ResultSet DatosMenu = ejecucion.executeQuery();
            if (DatosMenu.next()) {
                datos[0] = DatosMenu.getInt(1); //id
                datos[1] = DatosMenu.getString(2); //nombre distribuidor

            }
        } 
        catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("Error al cargar los datos del distribuidor");
            error.setVisible(true);
        }
        return datos;
    }
    //Creamos metodo para buscar distribuidor
     public static ResultSet BuscadorDistribuidor(String distribuidor) {
        ResultSet datos = null;
        try {
            //Ejecutamos consulta
            String SQL = "select * from tbDistribuidor where nombreDistribuidor LIKE '%"+distribuidor+"%'";
            PreparedStatement ejecucion = Conexion.Conexion().prepareStatement(SQL);
           // ejecucion.setString(1, distribuidor);
             datos = ejecucion.executeQuery();
           
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        //Retornamos datos
        return datos;
    }
}
