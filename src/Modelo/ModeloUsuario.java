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
/**
 *
 * @author Jesus Gerardo
 */
public class ModeloUsuario {
    //Creamos metodo que obtenga el idUsuario más reciente
    public ResultSet idUsuarioMax(){
        ResultSet res = null;
        try{ 
            String sql = "SELECT MAX(idUsuario) FROM tbUsuario";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            res = cmd.executeQuery();            
         }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido seleccionar el idMaximo para inserción");
            error.setVisible(true);
        }
        return res;
    }
    //Creamos metodo que cargue los datos a mostrar en la tabla de usuario
    public ResultSet cargarUsuarios(){
        Statement st;
        ResultSet datos = null;
        try{
            st=Conexion.Conexion().createStatement();
            datos=st.executeQuery("SELECT usu.usuario, usu.claveUsuario, usu.intento, usu.horaBlock, usu.horaDesBlock,estusu.estadoUsuario,emp.nombreEmpleado,emp.apellidoEmpleado FROM tbUsuario AS usu\n" +
                                  "Left JOIN tbEmpleado AS emp\n" +
                                  "ON usu.idUsuario=emp.idUsuario\n" +
                                  "left JOIN tbEstadoUsuario as estusu\n" +
                                  "on usu.idEstadoUsuario=estusu.idEstadoUsuario");
        }catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido realizar cargar los usuario en la tabla"+"  "+e);
            error.setVisible(true);
        }
        return datos;
    }
    //Creamos metodo que obtenga los estados de usuario
    public ResultSet cargarEstados(){
        ResultSet res = null;
        try{ 
            String sql = "SELECT * FROM tbEstadoUsuario";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            res = cmd.executeQuery();            
         }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido realizar cargar de los estados de usuario");
            error.setVisible(true);
        }
        return res;
    }
    //Creamos metodo que obtenga los empleados
    public ResultSet cargarEmpleado(){
        ResultSet res = null;
        try{ 
            String sql = "SELECT * FROM tbEmpleado";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            res = cmd.executeQuery();
         }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido realizar cargar los empleados");
            error.setVisible(true);
        }
        return res;
    }
    //Creamos metodo que ingrese un usuario a la bd
    public boolean guardarUsuario(Integer id,String usuario, String clave, Integer estado){
        boolean res = false;
        try{ //Realizar consulta INSERT
           String sql = "INSERT INTO tbUsuario (idUsuario,usuario,claveUsuario,primerUso,idEstadoUsuario) values (?,?,?,0,?);"; //se pasan por referencia por seguridad
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la tabla
           cmd.setInt(1,id);
           cmd.setString(2,usuario); //codigoP es como se definio en la clase aunque en la base se llama codigoProyecto
           cmd.setString(3, clave);
           cmd.setInt(4, estado);

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
    //Creamos metodo que consulte el id del usuario
    public ResultSet obtenerIdUsuario(String usuario){
        ResultSet res = null;
        try{ 
            String sql = "SELECT idUsuario FROM tbUsuario where usuario=?";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            cmd.setString(1,usuario); 
            res = cmd.executeQuery();
         }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido obtener el id");
            error.setVisible(true);
        }
        return res;
    }
    //Creamos metodo que actualice el usuario
    public boolean actualizarUsuario(String usuario, String clave, String horaDebloq, Integer idEstado,Integer idUsuario){
        boolean res = false;
        try{ //Realizar consulta INSERT
           String sql = "UPDATE tbUsuario set usuario=?, claveUsuario=?,horaDesBlock=?,idEstadoUsuario=? where idUsuario=?"; //se pasan por referencia por seguridad
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la tabla
           cmd.setString(1,usuario); //codigoP es como se definio en la clase aunque en la base se llama codigoProyecto
           cmd.setString(2, clave);
           cmd.setString(3, horaDebloq);
           cmd.setInt(4, idEstado);
           cmd.setInt(5, idUsuario);
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
    //Creamos metodo que actualice el id del usuario del empleado
    public boolean actualizarIdUsuario(Integer idUsuario, Integer idEmpleado){
        boolean res = false;
        try{ //Realizar consulta INSERT
           String sql = "UPDATE tbEmpleado SET idUsuario=? where idEmpleado=?; UPDATE tbUsuario SET primerUso=1 WHERE idUsuario=?"; //se pasan por referencia por seguridad
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la tabla
           cmd.setInt(1,idUsuario); //codigoP es como se definio en la clase aunque en la base se llama codigoProyecto
           cmd.setInt(2, idEmpleado);
           cmd.setInt(3, idUsuario);
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
    //Cremos metodo para eliminar el usuario
    public boolean eliminarUsuario(Integer IdUsuario){
        boolean res = false;
        try{ //Realizar consulta INSERT
           String sql = "DELETE tbUsuario where idUsuario=?"; //se pasan por referencia por seguridad
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la tabla
           cmd.setInt(1,IdUsuario);//codigoP es como se definio en la clase aunque en la base se llama codigoProyecto
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
}
