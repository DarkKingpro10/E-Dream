/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.FrmNotiError;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author Ericksson
 */

public class ModeloPrimerUsuario {
    //Incersion ComboBox Tipo Empleado 
    public static ResultSet CargarTipoEmpleado(){
        ResultSet res;
        try{
            String sql = "SELECT tipoEmpleado from tbTipoEmpleado where IdTipoEmpleado!=1";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            res = cmdselect.executeQuery();
            System.out.println(String.valueOf(res));
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
    
    //Cargar id de tipo empleado
    public static ArrayList<Integer> ConseguirIdTipoEmpleado(){
        String sql = "select idTipoEmpleado from tbTipoEmpleado where tipoEmpleado != 'Administrador'";
        ArrayList<Integer> arreglo=new ArrayList<Integer>();
           
        try{ 
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            ResultSet cmdselect = cmdinsert.executeQuery();
                 
            while (cmdselect.next()){
                arreglo.add(cmdselect.getInt(1));
            }
        }
        catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("Ha ocurrido un error, no se ha obtenido el identificador Tipo de Empleado"); 
            error.setVisible(true);
        }
        return arreglo;
    } 
    
    //Incersion de Usuario
    public static boolean RegistrarUsuario(int idUsuario, String usuario, String contraseña){
        boolean Insercion = false;
        try{
            String sql = "INSERT INTO tbUsuario (idUsuario, usuario , claveUsuario, intento,idEstadoUsuario) VALUES (?, ?, ?, ?, ?)";

            //pide importar clase Prepared Statement INSERT INTO tbUsuario (usuario , claveUsuario, idEstadoUsuario) VALUES (?,?)
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);

            cmdinsert.setInt(1,idUsuario);
            cmdinsert.setString(2, usuario);
            cmdinsert.setString(3, contraseña);
            cmdinsert.setInt(4, 0);
            cmdinsert.setInt(5, 1);
            if(!cmdinsert.execute()){
                Insercion=true;
            }
        }
        catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("Ha ocurrido un error, no se ha registrado el usuario");
            error.setVisible(true);
            return Insercion;
        }
        finally {
            try{
                Conexion.Conexion().close();
            }
            catch (Exception e) {
                
            }
        }
        return Insercion;
    }
        
    //Incersion de Empleado
    public static boolean RegistrarEmpleado(String nombreE, String apellidoE, String dui, Date edadUsu, String telefonoE, String correoE, String imagen, String sexoE,int idEmpresa,int idTipoEmpleado, int idUsuario){
        boolean Insercion = false;
        try{
            String sql = "INSERT INTO tbEmpleado (nombreEmpleado, apellidoEmpleado, DUI, edadEmpleado, telefonoEmpleado, correoEmpleado, imagenEmpleado, sexo, idEmpresa, idEstadoEmpleado, idTipoEmpleado, idUsuario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            //pide importar clase Prepared Statement INSERT INTO tbUsuario (usuario , claveUsuario, idEstadoUsuario) VALUES (?,?)
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            
            FileInputStream imagenE = null;
            String foto = imagen;
            File objFile = new File(foto);
            imagenE = new FileInputStream(objFile);

            cmdinsert.setString(1, nombreE);
            cmdinsert.setString(2, apellidoE);
            cmdinsert.setString(3, dui);
            cmdinsert.setDate(4, edadUsu);
            cmdinsert.setString(5, telefonoE);
            cmdinsert.setString(6, correoE);
            cmdinsert.setBinaryStream(7, imagenE);
            cmdinsert.setString(8, sexoE);
            cmdinsert.setInt(9, idEmpresa);
            cmdinsert.setInt(10, 1);
            cmdinsert.setInt(11, idTipoEmpleado);
            cmdinsert.setInt(12, idUsuario);
            if(!cmdinsert.execute()){
                Insercion=true;
            }
            cmdinsert.close();
        }
        catch(Exception e){
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
    
    //Cargamos  id de usuario reciente
    public static ResultSet CargarIdUsuario(){
        ResultSet res;
        try{
            String sql = "SELECT idUsuario FROM tbUsuario WHERE idUsuario = (SELECT MAX(idUsuario) FROM tbUsuario)";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            res = cmdselect.executeQuery();
            return res;
        }
        catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("Ha ocurrido un error, no se ha obtenido el identificador Usuario"); 
            error.setVisible(true);
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
    
    //Método para conseguir el id del Usuario
    public static ArrayList<Integer> ConseguirIdUsuario(){
        String sql = "SELECT idUsuario as idUsuario FROM tbUsuario WHERE idUsuario = (SELECT MAX(idUsuario) FROM tbUsuario)";
        ArrayList<Integer> arreglo=new ArrayList<Integer>();
        try{ 
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            ResultSet cmdselect = cmdinsert.executeQuery();
            while (cmdselect.next()){
                arreglo.add(cmdselect.getInt(1));     
            }
        }
        catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("Ha ocurrido un error, no se ha obtenido el identificador del Usuario"); 
            error.setVisible(true);
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
    
    
    
     //Cargamos el id de empresa reciente
    public static ResultSet CargarIdEmpresa(){
        ResultSet res;
        try{
            String sql = "SELECT idEmpresa as idEmpresa FROM tbEmpresa WHERE idEmpresa = (SELECT MAX(idEmpresa) FROM tbEmpresa)";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            res = cmdselect.executeQuery();
            return res;
        }
        catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("Ha ocurrido un error, no se ha obtenido el identificador de la empresa."); 
            error.setVisible(true);
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
    
    public static ArrayList<Integer> ConseguirIdEmpresa(){
        String sql = "SELECT idEmpresa as idEmpresa FROM tbEmpresa WHERE idEmpresa = (SELECT MAX(idEmpresa) FROM tbEmpresa)";
        ArrayList<Integer> arreglo=new ArrayList<Integer>();
        try{ 
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            ResultSet cmdselect = cmdinsert.executeQuery();     
            while (cmdselect.next()){
                arreglo.add(cmdselect.getInt(1));
                     
            }
        }
        catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("Ha ocurrido un error, no se ha obtenido el identificador de la empresa."); 
            error.setVisible(true);
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
    
    //Método de consultar existencia de usuarios
    public static int ExistenciaPrimerUsuario_Controller(String usuario){
    int retorno = 0;
    boolean retornoaux;
        try{
            String sql = "SELECT * FROM tbUsuario tu WHERE usuario = ?";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            cmdselect.setString(1, usuario);
            retornoaux = cmdselect.execute();
            ResultSet validacion = cmdselect.executeQuery();
            if (validacion.next() == true){
                retorno = 1;
            }
            else{
                retorno = 2;
            }
            return retorno;
        }    
        catch (Exception e){
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de existencia");
            error.TAnotiError.setText("Ha ocurrido un error, no se ha obtenido el identificador de la empresa."); 
            error.setVisible(true);
            return retorno = 3;
        }
        finally{
            try{
                Conexion.Conexion().close();
            }
            catch(Exception e){
            }
        }
    }
        
    //Creamos metodo que confirme que el pin ingresado este vinculado a un empleado de tipo admin
    public static ResultSet RevisarTablaUsuario(){
        //Creamos variable de tipo resulset
        ResultSet res=null;
        try{ //Realizar consulta para ver si existen campos dentro de la Tabla <3
            String sql = "Select * From tbUsuario";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            //Ejecutar la consulta
            //pedirá importar la clase ResultSet
            res= cmd.executeQuery();
        }
        catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de existencia");
            error.TAnotiError.setText("Ha ocurrido un error, no se ha obtenido existencia de usuario."); 
            error.setVisible(true);
        }
        //Devolvemos el resultado de la busqueda
        return res;
    }
        
    //Método de consultar existencia de usuarios
    public static int VerificacionUsuario(String usuario){
        int retorno = 0;
        boolean retornoaux;
            try{
                String sql = "SELECT * FROM tbUsuario as usu  WHERE usu.usuario = ? and usu.idUsuario not in(SELECT emp.idUsuario FROM tbEmpleado as emp, tbUsuario as usu where emp.idUsuario=usu.idUsuario)";
                PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
                cmdselect.setString(1, usuario);
                
                retornoaux = cmdselect.execute();
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
        }
        
    //Creamos metodo que obtenga el idUsuario más reciente
    public static ResultSet idUsuarioMax(){
        ResultSet res = null;
        try{
            String sql = "SELECT MAX(idUsuario) FROM tbUsuario";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            res = cmd.executeQuery();
        }
        catch(Exception e ){
        
        }
        finally{
            try{
                Conexion.Conexion().close();
            }
            catch(Exception e){
            }
        }
        return res;
    }
       
        
    //Creamos metodo que consulte el id del usuario
    public static ResultSet ObtenerIdUsuario(String usuario){
        ResultSet res = null;
        try{
            String sql = "SELECT idUsuario FROM tbUsuario where usuario=?";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            cmd.setString(1,usuario);
            res = cmd.executeQuery();
        }
        catch(Exception e ){

        }
        finally{
            try{
                Conexion.Conexion().close();
            }
            catch(Exception e){
            }
        }
        return res;
    }
    
    //Incersion de Usuario
    public static boolean ActualizarContraseña(int idUsuario, String contraseña){
        boolean Insercion = false;
        try{
            String sql = "UPDATE tbUsuario set claveUsuario = ? WHERE idUsuario = ?";
            //pide importar clase Prepared Statement INSERT INTO tbUsuario (usuario , claveUsuario, idEstadoUsuario) VALUES (?,?)
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            cmdinsert.setString(1, contraseña);
            cmdinsert.setInt(2, idUsuario);
            if(!cmdinsert.execute()){
                Insercion=true;
            }
        }
        catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de actualización");
            error.TAnotiError.setText("Ha ocurrido un error, no se ha actualizado la contraseña"); 
            error.setVisible(true);
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
    
    public ResultSet ObtenerDuiEmpleado(String Dui){
        ResultSet res = null;
        try{ 
            String sql = "SELECT idEmpleado FROM tbEmpleado where DUI = ?";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            cmd.setString(1,Dui); 
            res = cmd.executeQuery();
         }catch(Exception e ){
//            FrmNotiError error = new FrmNotiError();
//            error.TAnotiError.setText("¡Erro!No se ha podido obtener el id");
//            error.setVisible(true);
        }
        return res;
    }
    
     public ResultSet ObtenerTelefonoEmpleado(String Telefono){
        ResultSet res = null;
        try{ 
            String sql = "SELECT idEmpleado FROM tbEmpleado where telefonoEmpleado = ?";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            cmd.setString(1,Telefono); 
            res = cmd.executeQuery();
         }catch(Exception e ){
        }
        return res;
    }
}
