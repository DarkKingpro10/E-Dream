/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
//Realizamos las importaciones
import javax.swing.table.DefaultTableModel;
import Modelo.ModeloUsuario;
import Vista.*;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author Jesus Gerardo
 */
public class ControladorUsuario {
    
    Encriptar encriptar = new Encriptar();
    
    //Creamos objeto del modelo
    ModeloUsuario obj = new ModeloUsuario();
    //Creamos arreglo que almacene el id de los estados de usuario
    public static ArrayList <Integer> idestadoUsuario = new ArrayList<Integer>();
    //Creamos arreglo que almacene el id de los empleados
    public static ArrayList <Integer> idempleado = new ArrayList<Integer>();
    //Creamos variables globales de utilidad
    private static String usuario;
    private static String clave;
    private static String horaDesbloq;
    private static int idEstado;
    private static int idUsuario;
    private static int idEmpleado;
    
    //Creamos metodo que obtenga el idMaximo
    public int idMaximo(){
        //Creamos variable que guaradara el idmaximo e inicializamos con 0
        int idmax=0;
        //CReamos objeto de tipo ResultSet
        ResultSet rs = obj.idUsuarioMax();
        try{
            while(rs.next()){
                idmax=(rs.getInt(1))+1;
                System.out.println(String.valueOf(idmax));
            }
        }catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido seleccionar el idMaximo para inserción");
            error.setVisible(true);
        }
        return idmax;
    }
    //Creamos metodo que devuelva un modelo de tabla
    public DefaultTableModel obtenerUsuarios(){
        //Creamos un objeto de tipo Modelo de tabla
        DefaultTableModel modelo = new DefaultTableModel();
        //Creamos objeto de tipo ResultSet
        ResultSet rs = obj.cargarUsuarios();
        //Añadimos las columnas a la tabla
        modelo.setColumnIdentifiers(new Object [] {"Usuario","clave Usuario","Intento","Hora bloqueo","Hora desbloqueo","Estado usuario","Empleado"});
        try{
            while(rs.next()){
                String contraseña = encriptar.Desencriptar(rs.getString("claveUsuario"));
                //Creamos un String que almacene el nombre y el apellido del usuario
                String nombrec=(rs.getString("nombreEmpleado")+" "+rs.getString("apellidoEmpleado"));
                //Identificamos si el usuario esta asignado
                if (nombrec.equals("null null")) {
                    nombrec="No asignado";
                }else{
                    nombrec=nombrec;
                }
                //Añadimos a la tabla los datos o filas a la tabla
                modelo.addRow(new Object [] {rs.getString("usuario"),contraseña,rs.getString("intento"),rs.getTime("horaBlock"),rs.getTime("horaDesBlock"),rs.getString("estadoUsuario"),nombrec});
            }
        }catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido cargar los datos en la tabla");
            error.setVisible(true);
        }
        return modelo;
    }
    //Creamos metodo que defina el modelo del estado de usuario
    public  DefaultComboBoxModel obtenerEstados(){
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        ResultSet res = obj.cargarEstados();
        try{
            while(res.next()){
                //Añadimos el id al array de los estados de usuario
                idestadoUsuario.add(res.getInt("idEstadoUsuario"));
                lista.addElement(res.getString("estadoUsuario"));
            }
            res.close();
        }catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido cargar los estados de usuario");
            error.setVisible(true);
        }
        return lista;
    }
    //Creamos metodo que defina el modelo del combobox de los empleados
    public  DefaultComboBoxModel obtenerEmpleado(){
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        ResultSet res = obj.cargarEmpleado();
        try{
            while(res.next()){
                //añadimos el id al array de los empleados
                idempleado.add(res.getInt("idEmpleado"));
                //creamos un String con el nombre y el apellido concatenado
                String nombre = res.getString("nombreEmpleado");
                String apellido= res.getString("apellidoEmpleado");
                String nombrec=(nombre+" "+apellido);
                lista.addElement(nombrec);
            }
            res.close();
        }catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido cargar los estados de usuario");
            error.setVisible(true);
        }
        return lista;
    }
    //Creamos metodo que envie los parametros al modelo para guardar y de comprobación de esto
    public void guardarUsuario(String usuario, String clave, Integer estado){
        int id=idMaximo();
        boolean confirm=obj.guardarUsuario(id,usuario, encriptar.Encriptar(clave), estado);
        if (confirm==true) {
            FrmNoti2 error = new FrmNoti2();
            error.lblTituloNoti2.setText("¡Felicitaciones!");
            error.TANoti2.setText("Has insertado un nuevo usuario con exito");
            error.setVisible(true);
        }else{
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido guardar un usuario");
            error.setVisible(true);
        }
    }
    //Creamos metodo que busque el idEstado y lo devuelva
    public int obtenerIdEstado(int id){
        id=idestadoUsuario.get(id);
        return id;
    }
    
    //Creamos metodo que busque el idEstado y lo devuelva
    public int ObtnerIdEmpleado(int id){
        id=idempleado.get(id);
        return id;
    }
    
    //Creamos metodo que devuelva un int del usuario
    public int obtenerIdUsuario(String usuario){
        //Creamos variable int
        int id=0;
        //Creamos objeto de tipo resultset que sea la ejecucion del metodo en el modelo
        ResultSet res = obj.obtenerIdUsuario(usuario);
        try{
            while(res.next()){
                //obtenemos el id
                id=res.getInt("idUsuario");
            }
            res.close();
        }catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido obtner el id de usuario");
            error.setVisible(true);
        }
        return id;
    }
    
    //Creamos metodo que actualice el empleado
    public void actualizarUsuario(boolean caso){
        boolean confirm,confirme=true;
        confirm=obj.actualizarUsuario(usuario, encriptar.Encriptar(clave), horaDesbloq, idEstado,idUsuario);
        if (caso==true){
           //Quiere actualizar el usuario con empleado
           confirme=obj.actualizarIdUsuario(idUsuario, idEmpleado);
        }else{
            //Quiere actualizar el usuario sin empleado por lo que no se ejecuta el metodo
        }
        if (confirm==true && confirme==true) {
            FrmNoti2 error = new FrmNoti2();
            error.lblTituloNoti2.setText("¡Felicitaciones!");
            error.TANoti2.setText("Has actualizado un usuario con exito");
            error.setVisible(true);
        }else{
           FrmNotiError error = new FrmNotiError();
           error.TAnotiError.setText("¡Error!No se ha podido actualizar el usuario");
           error.setVisible(true); 
        }
    }
    
    //Creamos metodo que valide que la fecha sea correcta
    public boolean fechaCorrecta(String horai){
        //Creamos variable de tipo booleano
        boolean confirm=false;
        //Intentamos convetir la hora en formato localtime, sino se puede la fecha fue escrita de forma erronea
        try{
           LocalTime hora = LocalTime.parse(horai);
           LocalTime horalim = LocalTime.parse("12:59:59");
           if (hora.isAfter(horalim)) {
               FrmNotiError error = new FrmNotiError();
               error.TAnotiError.setText("¡Error!La fecha esta mal escrita, por revisarla y escribirla en formato de 12h");
               error.setVisible(true); 
               confirm=false;
           }else{
               confirm=true;
           }
        }catch(Exception e){
               FrmNotiError error = new FrmNotiError();
               error.TAnotiError.setText("¡Error!La fecha esta mal escrita, por revisarla y escribirla en formato de 12h");
               error.setVisible(true); 
               confirm=false;
        }
        return confirm;
    }
    //Creamos metodo que elimine el usuario
    public void eliminarUsuario(int idUsuario){
        boolean confirm;
        confirm=obj.eliminarUsuario(idUsuario);
        if (confirm==true) {
            FrmNoti2 error = new FrmNoti2();
            error.lblTituloNoti2.setText("¡Felicitaciones!");
            error.TANoti2.setText("Has eliminado un usuario con exito");
            error.setVisible(true);
        }else{
           FrmNotiError error = new FrmNotiError();
           error.TAnotiError.setText("¡Error!No se ha podido eliminar el usuario");
           error.setVisible(true); 
        }
    }
    //Creamos metodo que indique si existe el usuario
    public boolean confirmarUsuario(){
        //Creamos objeto del modelo
        ModeloUsuario obj = new ModeloUsuario();
        //Creamos objeto de tipo boleano para confirmar
        boolean confirm=false;
        //Creamos objeto de tipo resultSet para guardar el resultado
        ResultSet res = obj.obtenerIdUsuario(usuario);
        //Verificamos si en el resultado obtenido es null o existente
        try{
            if(res.next()){
                //Como se encontro resultado la confirmación sera verdadera                
                confirm=true;
            }else{
                //Como no se encontro resultado la confirmación sera falsa
                confirm=false;
            }
        }catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido encontrar el usuario");
            error.setVisible(true); 
        }
        //retornamos la confirmación
        return confirm;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getHoraDesbloq() {
        return horaDesbloq;
    }

    public void setHoraDesbloq(String horaDesbloq) {
        this.horaDesbloq = horaDesbloq;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
