/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.FrmNoti1;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Lenny
 */

public class ModeloLogin{
    public static int ValidarExistenciaEmpresa(){
        int retorno = 0;
        try{
            String sql = "SELECT * FROM tbEmpresa";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            ResultSet validacion = cmdselect.executeQuery();
            if(validacion.next() == true){
                retorno = 1;
            }
            else{
                retorno = 2;
            }
            return retorno;
        }
        catch (Exception e){
            return retorno = 3;
        }
        finally{
            try{
                Conexion.Conexion().close();
            }
            catch(Exception e){
                FrmNoti1 notificacion = new FrmNoti1();
                notificacion.lblTituloNoti1.setText("Error de finalizar Conexión");
                notificacion.TAMensajeError.setText("No se ha podido cerrar la conexión con la base de datos.");
            }
        }
    }
    
    public static int ValidarExistenciaPrimerUsuario(){
        int retorno = 0;
        try{
            String sql = "SELECT * FROM tbUsuario";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            ResultSet validacion = cmdselect.executeQuery();
            if(validacion.next() == true){
                retorno = 1;
            }
            else{
                retorno = 2;
            }
            return retorno;
        }
        catch (Exception e){
            return retorno = 3;
        }
        finally{
            try{
                Conexion.Conexion().close();
            }
            catch(Exception e){
                FrmNoti1 notificacion = new FrmNoti1();
                notificacion.lblTituloNoti1.setText("Error de finalizar Conexión");
                notificacion.TAMensajeError.setText("No se ha podido cerrar la conexión con la base de datos.");
            }
        }
    }
    
    public static int ValidarUsuarioEmpleado(String usuario){
        int retorno = 0;
        try{
            String sql = "SELECT * FROM tbEmpleado te, tbUsuario tu WHERE te.idUsuario = tu.idUsuario AND tu.usuario = ?";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            cmdselect.setString(1, usuario);
            ResultSet validacion = cmdselect.executeQuery();
            if(validacion.next() == true){
                retorno = 1;
            }
            else{
                retorno = 2;
            }
            return retorno;
        }
        catch (Exception e){
            return retorno = 3;
        }
        finally{
            try{
                Conexion.Conexion().close();
            }
            catch(Exception e){
                FrmNoti1 notificacion = new FrmNoti1();
                notificacion.lblTituloNoti1.setText("Error de finalizar Conexión");
                notificacion.TAMensajeError.setText("No se ha podido cerrar la conexión con la base de datos.");
            }
        }
    }
    
    //Método de consultar existencia de usuarios
    public static int Acceso(String usuario, String Clave_Usuario){
    int retorno = 0;
        try{
            String sql = "SELECT * FROM tbUsuario tu WHERE usuario = ? AND claveUsuario = ?";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            cmdselect.setString(1, usuario);
            cmdselect.setString(2, Clave_Usuario);
            ResultSet validacion = cmdselect.executeQuery();
            if(validacion.next() == true){
                retorno = 1;
            }
            else{
                retorno = 2;
            }
            return retorno;
        }
        catch (Exception e){
            return retorno = 3;
        }
        finally{
            try{
                Conexion.Conexion().close();
            }
            catch(Exception e){
                FrmNoti1 notificacion = new FrmNoti1();
                notificacion.lblTituloNoti1.setText("Error de finalizar Conexión");
                notificacion.TAMensajeError.setText("No se ha podido cerrar la conexión con la base de datos.");
            }
        }
    }
    
    
    
    //Método para traer los datos de el usuario
    public static ArrayList<Object>ObtenerDatos(String usuario){
        ArrayList<Object> datos = new ArrayList<Object>();
        try{
            String sql = "SELECT tu.idUsuario, tu.usuario, tu.intento, tu.idEstadoUsuario, te.idEmpleado, te.idTipoEmpleado, tte.tipoEmpleado, te.nombreEmpleado, te.apellidoEmpleado, te.DUI, te.imagenEmpleado FROM tbUsuario tu, tbEmpleado te, tbTipoEmpleado tte WHERE tu.usuario =  ? AND tu.idUsuario = te.idUsuario AND te.idTipoEmpleado = tte.idTipoEmpleado";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            cmdselect.setString(1, usuario);
            ResultSet reader = cmdselect.executeQuery();
            while(reader.next()){
                datos.add(reader.getInt(1));//Capturando idUsuario
                datos.add(reader.getString(2));//Capturando usuario
                datos.add(reader.getInt(3));//Capturando intento
                datos.add(reader.getInt(4));//Capturando idestado del usuario
                datos.add(reader.getInt(5));//Capturando idempleado
                datos.add(reader.getInt(6));//Capturando idtipoempleado del empleado
                datos.add(reader.getString(7));//Capturando tipoempleado del empleado
                datos.add(reader.getString(8));//Capturando nombreEmpleado
                datos.add(reader.getString(9));//Capturando apellidoEmpleado
                datos.add(reader.getString(10));//Capturando DUI
                datos.add(reader.getBlob(11));//Capturando Imagen
            }
            return datos;
        }
        catch (Exception e){
            return datos;
        }
    }
        
    //Método para traer los datos de el usuario
    public static boolean ActualizarEstado(String usuario, int EstadoU){
        boolean retorno = false;
        try{
            String sql = "UPDATE tbUsuario SET idEstadoUsuario = ? WHERE usuario = ?";
            PreparedStatement cmdupdate = Conexion.Conexion().prepareStatement(sql);
            cmdupdate.setInt(1, EstadoU);
            cmdupdate.setString(2, usuario);
            if(!cmdupdate.execute()){
              retorno=true;
            }
            return retorno;
        }
        catch (Exception ex){
            return retorno;
        }
    }
    
    //Método para actualizar intentos
    public static boolean ActualizarIntentos(String usuario, int intento){
        boolean retorno = false;
        try{
            String sql = "UPDATE tbUsuario SET intento = ? WHERE usuario = ?";
            PreparedStatement cmdupdate = Conexion.Conexion().prepareStatement(sql);
            cmdupdate.setInt(1, intento);
            cmdupdate.setString(2, usuario);
            if(!cmdupdate.execute()){
                //Si se ejecuta, se devuelve un true
                retorno=true;
            }
            return retorno;
        }
        catch(Exception e){
            return retorno;
        }
    }
    
    //Método para habilitar intentos
    public static boolean HabilitarIntentos(String usuario, int intento, int idEstadoU){
        boolean retorno = false;
        try{
            String sql = "UPDATE tbUsuario SET horaBlock = NULL, horaDesBlock= NULL, intento = ?, idEstadoUsuario = ? WHERE usuario = ?";
            PreparedStatement cmdupdate = Conexion.Conexion().prepareStatement(sql);
            cmdupdate.setInt(1, intento);
            cmdupdate.setInt(2, idEstadoU);
            cmdupdate.setString(3, usuario);
            if(!cmdupdate.execute()){
                //Si se ejecuta, se devuelve un true
                retorno=true;
            }
            return retorno;
        }
        catch(Exception e){
            return retorno;
        }
    }
    
    //Obtener las horas del usuario
    public static ArrayList<Time> ObtenerHora(String usuario){
        ArrayList<Time> datos = new ArrayList<Time>();
        try{
            String sql = "SELECT horaBlock, horaDesBlock FROM tbUsuario WHERE usuario = ?";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            cmdselect.setString(1, usuario);
            ResultSet reader = cmdselect.executeQuery();
            while (reader.next()){
                datos.add(reader.getTime(1));//Capturando hora bloqueo
                datos.add(reader.getTime(2));//Capturando hora desbloqueo
            }
            return datos;
        }
        catch (Exception e){
            return datos;
        }
    }
    
    //Método para registrar hora del intento
    public static boolean RegistrarHoraIntento(String usuario, Time horaInactivación, Time horaActivación){
        boolean retorno = false;
        try{
            String sql = "UPDATE tbusuario SET HoraBlock = ? , HoraDesBlock = ? WHERE Usuario = ?";
            PreparedStatement cmdupdate = Conexion.Conexion().prepareStatement(sql);
            cmdupdate.setTime(1, horaInactivación);
            cmdupdate.setTime(2, horaActivación);
            cmdupdate.setString(3, usuario);
            if(!cmdupdate.execute()){
                //Si se ejecuta, se devuelve un true
                return retorno = true;
            }
            return retorno;
        }
        catch (Exception ex){
            return retorno = false;
        }
    }
}
