/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import Modelo.Conexion;
import Vista.FrmNotiError;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Issela Mejía Clase para el CRUD de proyectos Segundo desarrollo de
 * software
 */
public class modeloEmpleado {

    //definiendo atributos
    private Connection cn;

    private static ResultSet datosMenu;
    private static String Codigo;
    private static ResultSet Codigos;
    private static ResultSetMetaData tablaCodigos;
    private Integer codigoE;

    //Obtenemos tabla resumida
    public static ResultSet getTablaResumida() {
        //Conectamos con la conexion
        Conexion con = new Conexion();
        Connection cn = con.Conexion();
        Statement st;
        //Inicializamos el ResultSet a null
        ResultSet datos = null;
        try {
            st = cn.createStatement();
            //Le asignamos la consulta al ResultSet
            datos = st.executeQuery("select e.nombreEmpleado, e.apellidoEmpleado, e.Dui, e.edadEmpleado, e.telefonoEmpleado, e.correoEmpleado,e.imagenEmpleado, e.sexo, emp.nombreEmpresa, estado.estadoEmpleado, tipo.tipoEmpleado, usuario.usuario from tbEmpleado as e\n" +
            "left outer join tbEmpresa as emp on e.idEmpresa = emp.idEmpresa\n" +
            "left outer join tbEstadoEmpleado as estado on e.idEstadoEmpleado = estado.idEstadoEmpleado\n" +
            "left outer join tbTipoEmpleado as tipo on e.idTipoEmpleado=tipo.idTipoEmpleado\n" +
            "left outer join tbUsuario as usuario on e.idUsuario = usuario.idUsuario\n" +
            "where e.idEstadoEmpleado IN(1,2,3,4)");
         
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Retornamos la consulta
        return datos;
    }

    //método para guardar el empleado en la base de datos, creamos parametros
    public static boolean guardarEmpleado(String nombreE, String apellidoE, String duiE, Date edadE, String telefonoE, String correoE, String imagen, String sexoE, int idEmpresa, int idEstadoEmpleado, int idTipoEmpleado, int idUsuario) {
        //Craemos variable tiipo booleano llamada res
        boolean res = false;//Inicializamos con falso
        try { //Realizar consulta INSERT
            String sql = "insert into tbEmpleado (nombreEmpleado,apellidoEmpleado,DUI,edadEmpleado,telefonoEmpleado,correoEmpleado,imagenEmpleado,sexo,idEmpresa,idEstadoEmpleado,idTipoEmpleado,idUsuario) values (?,?,?,?,?,?,?,?,?,?,?,?)"; //se pasan por referencia por seguridad
           // String select = "UPDATE tbUsuario SET  idEstadoUsuario =1 where  idUsuario=?"; //se pasan por referencia por seguridad
            
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
         //   PreparedStatement cmdSelect = Conexion.Conexion().prepareStatement(select);
            //Lenar los parámetros de la clase, se coloca en el orden de la tabla
            // cmd.setInt(1,codigoE); //codigoP es como se definio en la clase aunque en la base se llama codigoProyecto

            //Creamos objeto FileInputStream para acceder a los bytes de imagenes
            FileInputStream imagenE = null;
            String foto = imagen;
            //Creamos objeto File para almacenr el archivo de la imagen
            File objFile = new File(foto);
            //Guardamos la iamgen en el parametro de ImagenE
            imagenE = new FileInputStream(objFile);
            cmd.setString(1, nombreE);//Guardamos nombre de empleado
            cmd.setString(2, apellidoE);//Guardamos apellido de empleado
            cmd.setString(3, duiE);//Guardamos dui de empleado
            cmd.setDate(4, edadE);//Guardamos fecha de nacimiento de empleado
            cmd.setString(5, telefonoE);//Guardamos telefeno de empleado
            cmd.setString(6, correoE);//Guardamos correo de empleado
            cmd.setBinaryStream(7, imagenE);//Guardamos imagen
            cmd.setString(8, String.valueOf(sexoE));//Guardamo sexo de empleado
            cmd.setInt(9, idEmpresa);//Guardamos id de la empresa - llave foranea
            cmd.setInt(10, idEstadoEmpleado);//Guardamos el id de estado de empleado - llave foranea
            cmd.setInt(11, idTipoEmpleado);//Guardamos el id de tipo de empleado - llave foranea
            cmd.setInt(12, idUsuario);//Guardamo el id de usuario de empleado - llave foranea

          //  cmdSelect.setInt(1,idUsuario);
            //Si da error devuelve 1, caso contrario 
            //Tomar en cuenta que el ! es negación
            if (!cmd.execute()) {
                res = true;
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Devolvemos variable res
        return res;
    }

    //método para modificar el empleado, le asignamos parametros con datos a modificar
    public static boolean modificarEmpleado(String nombreE, String apellidoE, String duiE, Date edadE, String telefonoE, String correoE, String imagen, String sexoE, int idEmpresa, int idEstadoEmpleado, int idTipoEmpleado, int idUsuario, int idEmpleado) {
        boolean res = false;
        try { //Realizar consulta UPDATE
            String sql = "UPDATE tbEmpleado SET  nombreEmpleado =?, apellidoEmpleado =?, DUI=?, edadEmpleado=? ,telefonoEmpleado=? ,correoEmpleado=?,imagenEmpleado=?,sexo=?,idEmpresa=?,idEstadoEmpleado=?,idTipoEmpleado=?,idUsuario=? where idEmpleado=?";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            //Lenar los parámetros de la clase, se coloca en el orden de la consulta
            // cmd.setInt(0, codigoP);

            //Creamos objeto FileInputStream para acceder a los bytes de imagenes
            FileInputStream imagenE = null;
            String foto = imagen;
            //Creamos objeto File para almacenr el archivo de la imagen
            File objFile = new File(foto);
            //Guardamos la iamgen en el parametro de ImagenE
            imagenE = new FileInputStream(objFile);

            cmd.setString(1, nombreE);//Guardamos nombre de empleado
            cmd.setString(2, apellidoE);//Guardamos apellido de empleado
            cmd.setString(3, duiE);//Guardamos dui de empleado
            cmd.setDate(4, edadE);//Guardamos fecha de nacimiento de empleado
            cmd.setString(5, telefonoE);//Guardamos telefeno de empleado
            cmd.setString(6, correoE);//Guardamos correo de empleado
            cmd.setBinaryStream(7, imagenE);//Guardamos imagen
            cmd.setString(8, String.valueOf(sexoE));//Guardamo sexo de empleado
            cmd.setInt(9, idEmpresa);//Guardamos id de la empresa - llave foranea
            cmd.setInt(10, idEstadoEmpleado);//Guardamos el id de estado de empleado - llave foranea
            cmd.setInt(11, idTipoEmpleado);//Guardamos el id de tipo de empleado - llave foranea
            cmd.setInt(12, idUsuario);//Guardamos el id de usuario de empleado - llave foranea
            cmd.setInt(13, idEmpleado);//Guardamos el id e empleado

            //Si da error devuelve 1, caso contrario 
            //Tomar en cuenta que el ! es negación
            if (!cmd.execute()) {
                res = true;
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Devolvemos la variable de res
        return res;
    }
    
    //Metodo para actualizar elid de estado de usuario
     public static boolean ActualizarIdEstadoUsuario(int idUsuario) {
        boolean res = false;
        try { //Realizar consulta UPDATE
            String sql = "UPDATE tbUsuario SET  idEstadoUsuario =1 where  idUsuario=?";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            //Lenar los parámetros de la clase, se coloca en el orden de la consulta
     
            cmd.setInt(1, idUsuario);//Guardamos el id de usuario de empleado - llave foranea
            
            //Si da error devuelve 1, caso contrario 
            //Tomar en cuenta que el ! es negación
            if (!cmd.execute()) {
                res = true;
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Devolvemos la variable de res
        return res;
    }
     
     //Metodo para actualizar elid de estado de usuario si estan los campos totales
     public static boolean ActulizarIdEstadoUsuarioT( int idUsuario) {
        boolean res = false;
        try { //Realizar consulta UPDATE
            String sql = "UPDATE tbUsuario SET  idEstadoUsuario =4 where idUsuario=?";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            //Lenar los parámetros de la clase, se coloca en el orden de la consulta
     
            cmd.setInt(1, idUsuario);//Guardamos el id de usuario de empleado - llave foranea
            
            //Si da error devuelve 1, caso contrario 
            //Tomar en cuenta que el ! es negación
            if (!cmd.execute()) {
                res = true;
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Devolvemos la variable de res
        return res;
    }

      //Metodo para actualizar elid de usuario de empleado
     public static boolean ActualizarCampoIdUsuario( int idUsuario) {
        boolean res = false;
        try { //Realizar consulta UPDATE
            String sql = "UPDATE tbEmpleado SET  idUsuario = null where idUsuario=?";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            //Lenar los parámetros de la clase, se coloca en el orden de la consulta
     
            cmd.setInt(1, idUsuario);//Guardamos el id de usuario de empleado - llave foranea
            
            //Si da error devuelve 1, caso contrario 
            //Tomar en cuenta que el ! es negación
            if (!cmd.execute()) {
                res = true;
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Devolvemos la variable de res
        return res;
    }
     
    //método para modificar el empleado sin imagen, le asignamos parametros con datos a modificar
    public static boolean modificarSinImagen(String nombreE, String apellidoE, String duiE, Date edadE, String telefonoE, String correoE, String sexoE, int idEmpresa, int idEstadoEmpleado, int idTipoEmpleado, int idUsuario, int idEmpleado) {
        boolean res = false;
        try { //Realizar consulta UPDATE
            String sql = "UPDATE tbEmpleado SET  nombreEmpleado =?, apellidoEmpleado =?, DUI=?, edadEmpleado=? ,telefonoEmpleado=? ,correoEmpleado=?,sexo=?,idEmpresa=?,idEstadoEmpleado=?,idTipoEmpleado=?,idUsuario=? where idEmpleado=?";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            //Lenar los parámetros de la clase, se coloca en el orden de la consulta
            // cmd.setInt(0, codigoP);

            cmd.setString(1, nombreE);//Guardamos nombre de empleado
            cmd.setString(2, apellidoE);//Guardamos apellido de empleado
            cmd.setString(3, duiE);//Guardamos dui de empleado
            cmd.setDate(4, edadE);//Guardamos fecha de nacimiento de empleado
            cmd.setString(5, telefonoE);//Guardamos telefeno de empleado
            cmd.setString(6, correoE);//Guardamos correo de empleado
            cmd.setString(7, String.valueOf(sexoE));//Guardamo sexo de empleado
            cmd.setInt(8, idEmpresa);//Guardamos id de la empresa - llave foranea
            cmd.setInt(9, idEstadoEmpleado);//Guardamos el id de estado de empleado - llave foranea
            cmd.setInt(10, idTipoEmpleado);//Guardamos el id de tipo de empleado - llave foranea
            cmd.setInt(11, idUsuario);//Guardamos el id de usuario de empleado - llave foranea
            cmd.setInt(12, idEmpleado);//Guardamos el id e empleado

            //Si da error devuelve 1, caso contrario 
            //Tomar en cuenta que el ! es negación
            if (!cmd.execute()) {
                res = true;
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Devolvemos la variable de res
        return res;
    }

    //método para eliminar el empleado, con el parametro para eliminar
    public static boolean eliminarEmpleado(int codigoE) {
        //Creamos variable booleana res
        boolean res = false;
        try { //Realizar consulta DELETE
            String sql = "update tbEmpleado set idEstadoEmpleado = 5 where idEmpleado = ?";
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            cmd.setInt(1, codigoE);//Guardamos el id del empleado

            //Si da error devuelve 1, caso contrario 
            //Tomar en cuenta que el ! es negación
            if (!cmd.execute()) {
                res = true;
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Devolvemos la variable res
        return res;
    }

    //Creamos un ResultSet para obtener el id de empleado
    public static ResultSet llenarCodigosEmpleado(){
        //Creamos la consulta
        String SQL = "SELECT idEmpleado from tbEmpleado where idEstadoEmpleado between 1 and 4";
        ResultSet CodigosE = null;
        try {
            //Probamos la consulta
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(SQL);
            CodigosE = cmd.executeQuery();
            //cmd.close();           
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        //Retornamos el CodigoE
        return CodigosE;
    }

    //Creamos un ArrayList para guadar todos los id de empleados
    public static ArrayList<String> CargasIDE() {
        //Le asignmos un nombre
        ArrayList<String> lista = new ArrayList<String>();
        try {
            //Le asignamos al ResultSet de CodigoE el ResultSet de llenar codigos de empleado
            ResultSet CodigosE = llenarCodigosEmpleado();
            ResultSetMetaData TablaCodigosE = CodigosE.getMetaData();
            int contador = 0;
            while (CodigosE.next()) {
                //Creamos String nulo
                String fila = null;
                //Creamos un for para ir llenando el arrays
                for (int i = 0; i < TablaCodigosE.getColumnCount(); i++) {
                    fila = CodigosE.getString(i + 1);
                    //añadimos la lit de arrays
                    lista.add(fila);
                }
            }
            //Retornamos la lista
            return lista;
        } catch (Exception e) {
            System.out.print(e.toString());
            return lista;
        }
    }

    //Creamos metodo de objeto para seleccionar todos los datos de la tabla y guardarlos  
    public static Object[] CargarDatosMenu(String Identificador) {
        Object[] datos = new Object[9];
        try {
            //Creamos consulta
            String SQL = "select * from tbEmpleado where idEmpleado = ?  ";
            PreparedStatement ejecucion = Conexion.Conexion().prepareStatement(SQL);
            ejecucion.setString(1, Identificador);
            //Ejecutamos consulta
            ResultSet DatosMenu = ejecucion.executeQuery();
            //Guardamos datos en objeto
            if (DatosMenu.next()) {
                datos[0] = DatosMenu.getInt(1); //id
                datos[1] = DatosMenu.getString(2); //nombre empleado
                datos[2] = DatosMenu.getString(3); //apellido empleado
                datos[3] = DatosMenu.getString(4); //dui
                datos[4] = DatosMenu.getDate(5); //edad
                datos[5] = DatosMenu.getString(6);//telefono empleado
                datos[6] = DatosMenu.getString(7); //correo empleado
                Blob blob = DatosMenu.getBlob(8);
                //Creamos byte de imagen
                byte[] imagen = (blob.getBytes(1, (int) blob.length()));
                System.out.print(String.valueOf(imagen));
                datos[7] = imagen; //Imagen empleado
                datos[8] = DatosMenu.getString(9); //sexo empleado
//                datos[9] = DatosMenu.getInt(10); //id empresa 
//                datos[10] = DatosMenu.getInt(11); //id estado
//                datos[11] = DatosMenu.getInt(12); //id tipo
//                datos[12] = DatosMenu.getInt(13); //id usuario
                for(int i=0;i<datos.length;i++)
                {
                    System.out.println(datos[i]);
                }

            } else {
                System.out.print("ERROR INESPERADO");
            }

        } catch (Exception e) {
            System.out.print(e.toString());
        }
        //Retornamos datos
        return datos;
    }

    //Para cargar el combobox de empresa
    public static ResultSet CargarEmpresa() {
        ResultSet res = null;
        try {
            //Creamos conulta para seleccionar nombre de empresa
            String sql = "SELECT idEmpresa, nombreEmpresa from tbEmpresa";
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta
            res = cmdselect.executeQuery();
            return res;
        } catch (Exception e) {
            return res = null;
        } finally {
            try {
                //Cerramos conexion
                Conexion.Conexion().close();
            } catch (Exception e) {

            }
        }
    }

    //Para cargar el combobox de estado de Empleado
    public static ResultSet CargarEstado() {
        ResultSet res = null;
        try {
            //Creamos la onsulta para sekeccionar estado de empleado donde su id de estado empleado este entre 1 y 4
            String sql = "SELECT idEstadoEmpleado, estadoEmpleado from tbEstadoEmpleado where idEstadoEmpleado between 1 and 4";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos consulta
            res = cmdselect.executeQuery();
            return res;
        } catch (Exception e) {
            return res = null;
        } finally {
            try {
                //Cerramos conexion
                Conexion.Conexion().close();
            } catch (Exception e) {

            }
        }
    }

    //Para cargar el combobox de usuario
    public static ResultSet CargarUsuario() {
        ResultSet res = null;
        try {
            //Se crea la consulta para seleccionar el usuario
            String sql = "SELECT idUsuario, usuario from tbUsuario where idEstadoUsuario=4";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta
            res = cmdselect.executeQuery();
            return res;
        } catch (Exception e) {
            return res = null;
        } finally {
            try {
                //Cerramos conexion
                Conexion.Conexion().close();
            } catch (Exception e) {

            }
        }
    }
    
     //Para cargar el combobox de usuario
    public static ResultSet CargarUsuarioTotal() {
        ResultSet res = null;
        try {
            //Se crea la consulta para seleccionar el usuario
            String sql = "SELECT idUsuario, usuario  from tbUsuario";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta
            res = cmdselect.executeQuery();
            return res;
        } catch (Exception e) {
            return res = null;
        } finally {
            try {
                //Cerramos conexion
                Conexion.Conexion().close();
            } catch (Exception e) {

            }
        }
    }
    
    //Para cargar el combobox de tipoEmpleado
    public static ResultSet CargarTipoEmpleado() {
        ResultSet res = null;
        try {
            //Se crea la consulta para seleccionar el tipo de empleado
            String sql = "SELECT idTipoEmpleado, tipoEmpleado from tbTipoEmpleado";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta
            res = cmdselect.executeQuery();
            return res;
        } catch (Exception e) {
            return res = null;
        } finally {
            try {
                //Cerramos la conexion
                Conexion.Conexion().close();
            } catch (Exception e) {

            }
        }
    }

    //Para conseguir id de combobox de empresa
    public static ArrayList<Integer> ConseguirIdEmpresa() {
        //Creamos la consulta para seleccionar el id de empresa
        String sql = "select idEmpresa from tbEmpresa ";
        ArrayList<Integer> arreglo = new ArrayList<Integer>();
        try {
            //Establecemos la conexion
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta 
            ResultSet cmdselect = cmdinsert.executeQuery();

            while (cmdselect.next()) {
                //Agregamos el id al arreglo
                arreglo.add(cmdselect.getInt(1));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //Retornamos el arreglo
        return arreglo;
    }

    //Para conseguir id de combobox de estado empleado
    public static ArrayList<Integer> ConseguirIdEstado() {
        //Creamos la consulta del id de estado de empleado 
        String sql = "select idEstadoEmpleado from tbEstadoEmpleado ";
        //Cremos un Arraylist arreglo
        ArrayList<Integer> arreglo = new ArrayList<Integer>();
        try{
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta
            ResultSet cmdselect = cmdinsert.executeQuery();

            while (cmdselect.next()) {
                //Agregamos el id al arreglo
                arreglo.add(cmdselect.getInt(1));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //Regresamos el arreglo
        return arreglo;
    }

    //Para conseguir id de combobox de tipo empleado
    public static ArrayList<Integer> ConseguirIdTipo() {
        //Crear consulta para seleccionar el id de tipo de empleado
        String sql = "select idTipoEmpleado from tbTipoEmpleado ";
        //Creamos un ArrayList arreglo
        ArrayList<Integer> arreglo = new ArrayList<Integer>();
        try {
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos lo de consulta
            ResultSet cmdselect = cmdinsert.executeQuery();

            while (cmdselect.next()) {
                //Agregramos el id al arreglo
                arreglo.add(cmdselect.getInt(1));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //Regresamos el arreglo
        return arreglo;
    }

    //Para conseguir id de combobox de usuario
    public static ArrayList<Integer> ConseguirIdUsuario() {
        //Creamos la consulta para seleccionar el id de usuario
        String sql = "select idUsuario from tbUsuario where idEstadoUsuario=4 ";
        //Creamos un ArrayList arreglo
        ArrayList<Integer> arreglo = new ArrayList<Integer>();
        try {
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta
            ResultSet cmdselect = cmdinsert.executeQuery();

            while (cmdselect.next()) {
                //Agregamos el Id al arreglo
                arreglo.add(cmdselect.getInt(1));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //Regresamos el arreglo
        return arreglo;
    }
    
    //Para conseguir id de combobox de usuario
    public static ArrayList<Integer> ConseguirIdUsuarioTotal() {
        //Creamos la consulta para seleccionar el id de usuario
        String sql = "select idUsuario from tbUsuario ";
        //Creamos un ArrayList arreglo
        ArrayList<Integer> arreglo = new ArrayList<Integer>();
        try {
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta
            ResultSet cmdselect = cmdinsert.executeQuery();

            while (cmdselect.next()) {
                //Agregamos el Id al arreglo
                arreglo.add(cmdselect.getInt(1));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //Regresamos el arreglo
        return arreglo;
    }
    
    
     //Creamos metodo que consulte el id del empleado para sacar el dui
    public ResultSet ObtenerDui(String dui){
        ResultSet res = null;
        try{ 
            String sql = "SELECT idEmpleado FROM tbEmpleado where DUI=?";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            cmd.setString(1,dui); 
            res = cmd.executeQuery();
         }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido obtener el id");
            error.setVisible(true);
        }
        return res;
    }
    
    
     //Creamos metodo que consulte el id del usuario
    public ResultSet ObtenerTelefono(String telefono){
        ResultSet res = null;
        try{ 
            String sql = "SELECT idEmpleado FROM tbEmpleado where telefonoEmpleado=?";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            cmd.setString(1,telefono); 
            res = cmd.executeQuery();
         }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido obtener el id");
            error.setVisible(true);
        }
        return res;
    }
    
    //Creamos metodo que consulte el id del correo
    public ResultSet ObtenerCorreo(String correo){
        ResultSet res = null;
        try{ 
            String sql = "SELECT idEmpleado FROM tbEmpleado where correoEmpleado=?";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            cmd.setString(1,correo); 
            res = cmd.executeQuery();
         }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido obtener el id");
            error.setVisible(true);
        }
        return res;
    }
    
    //Creamos metodo que consulte el id del usuario
    public ResultSet ObtenerUsuario(Integer idUsuario){
        ResultSet res = null;
        try{ 
            String sql = "SELECT idUsuario FROM tbUsuario where idUsuario=? and idEstadoUsuario IN(1,2,3) ";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            cmd.setInt(1,idUsuario); 
           
            res = cmd.executeQuery();
         }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido obtener el id");
            error.setVisible(true);
        }
        return res;
    }
    
    //Creamos metodo que consulte el id del usuario totales
    public ResultSet ObtenerUsuarioT(Integer idUsuario){
        ResultSet res = null;
        try{ 
            String sql = "SELECT idUsuario FROM tbUsuario where idUsuario=? ";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            cmd.setInt(1,idUsuario); 
           
            res = cmd.executeQuery();
         }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido obtener el id");
            error.setVisible(true);
        }
        return res;
    }

    //método para eliminar el id de de estado de usuario, con el parametro para eliminar
    public static boolean ActualizarIdEstadoUsuarioEliminar(int idUsuario) {
        //Creamos variable booleana res
        boolean res = false;
        try { //Realizar consulta DELETE
            String sql = "update tbUsuario set idEstadoUsuario=4 where idUsuario=?";
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            cmd.setInt(1, idUsuario);//Guardamos el id del empleado

            //Si da error devuelve 1, caso contrario 
            //Tomar en cuenta que el ! es negación
            if (!cmd.execute()) {
                res = true;
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Devolvemos la variable res
        return res;
    }
    
    public static int ConsultarUsuario(String usuario){
        int codigop=0;
        try{ //Realizar consulta UPDATE
           String sql = "SELECT idUsuario FROM tbUsuario WHERE usuario=?";
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la consulta
           cmd.setString(1, usuario);

           //Ejecutar la consulta
            //pedirá importar la clase ResultSet
            ResultSet rs = cmd.executeQuery();
            //recorrer la lista de registros
            if(rs.next()){
              //asignándole a los atributos de la clase
              codigop = rs.getInt(1);
              return codigop;
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        }catch(Exception e ){
            System.out.println(e.toString());
        }
        return codigop;
    }
    
     public static int ConsultarUsuarioInicial(String usuario){
        int codigop=0;
        try{ //Realizar consulta UPDATE
           String sql = "SELECT idUsuario FROM tbUsuario WHERE usuario=?";
           //pide importar clase Prepared Statement
           PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
           //Lenar los parámetros de la clase, se coloca en el orden de la consulta
           cmd.setString(1, usuario);

           //Ejecutar la consulta
            //pedirá importar la clase ResultSet
            ResultSet rs = cmd.executeQuery();
            //recorrer la lista de registros
            if(rs.next()){
              //asignándole a los atributos de la clase
              codigop = rs.getInt(1);
              return codigop;
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        }catch(Exception e ){
            System.out.println(e.toString());
        }
        return codigop;
    }
     
     
    
    public static String getCodigo() {
        return Codigo;
    }

    public static void setCodigo(String aCodigo) {
        Codigo = aCodigo;
    }

    public static ResultSet getCodigos() {
        return Codigos;
    }

    public static void setCodigos(ResultSet aCodigos) {
        Codigos = aCodigos;
    }

    public static ResultSetMetaData getTablaCodigos() {
        return tablaCodigos;
    }

    public static void setTablaCodigos(ResultSetMetaData aTablaCodigos) {
        tablaCodigos = aTablaCodigos;
    }

    public Integer getCodigoE() {
        return codigoE;
    }

    public void setCodigoE(Integer codigoE) {
        this.codigoE = codigoE;
    }

    public static ResultSet getDatosMenu() {
        return datosMenu;
    }

    public static void setDatosMenu(ResultSet aDatosMenu) {
        datosMenu = aDatosMenu;
    }

}
