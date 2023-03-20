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

/**
 *
 * @author Jesus Gerardo
 */
public class ModeloModificarUsuario {
    //Definiendo atributos
    private Connection cn;
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
    public ModeloModificarUsuario(){
    //Establecemos la conexion
       Conexion con = new Conexion();
       cn = con.Conexion();
    }
    //Creamos metodo que obtenga los datos del empleado
    public ResultSet datosEmpleado(int idUsuario){
        ResultSet res = null;
        try{ 
            String sql = "SELECT * FROM tbEmpleado WHERE idEmpleado=?";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1,idUsuario);
            res = cmd.executeQuery();            
         }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido obtener datos del empleado de la base de datos");
            error.setVisible(true);
        }
        return res;
    }
    //Creamos metodo que actualice los datos del empleado
     //Creamos metodo que actualice el usuario
    public boolean actualizarEmpleadoImg(int id,String nombre, String apellido, int telefono, String correo,byte[] imagen, String sexo){
        boolean res = false;
        try{ //Realizar consulta INSERT
           String sql = "UPDATE tbEmpleado set nombreEmpleado=?, apellidoEmpleado=?,telefonoEmpleado=?,correoEmpleado=?,imagenEmpleado=?,sexo=? where idEmpleado=?"; //se pasan por referencia por seguridad
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la tabla
           cmd.setString(1,nombre); //codigoP es como se definio en la clase aunque en la base se llama codigoProyecto
           cmd.setString(2, apellido);
           cmd.setInt(3, telefono);
           cmd.setString(4, correo);
           cmd.setBytes(5, imagen);
           cmd.setString(6, sexo);
           cmd.setInt(7, id);
           //Tomar en cuenta que el ! es negación
           if(!cmd.execute()){
              res=true;
            }
        }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido actualizar los datos");
            error.setVisible(true);
        }
        return res;
    }
    //Creamos metodo que actulize sin image
    public boolean actualizarEmpleado(int id,String nombre, String apellido, int telefono, String correo,String sexo){
        boolean res = false;
        try{ //Realizar consulta INSERT
           String sql = "UPDATE tbEmpleado set nombreEmpleado=?, apellidoEmpleado=?,telefonoEmpleado=?,correoEmpleado=?,sexo=? where idEmpleado=?"; //se pasan por referencia por seguridad
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la tabla
           cmd.setString(1,nombre); //codigoP es como se definio en la clase aunque en la base se llama codigoProyecto
           cmd.setString(2, apellido);
           cmd.setInt(3, telefono);
           cmd.setString(4, correo);
           cmd.setString(5, sexo);
           cmd.setInt(6, id);
           //Tomar en cuenta que el ! es negación
           if(!cmd.execute()){
              res=true;
            }
        }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido actualizar los datos");
            error.setVisible(true);
        }
        return res;
    }
}
