package Controlador;

import Modelo.ModeloPrimerUsuario;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Erick
 *
 */
public class ControladorPrimerUsuario {
    public static ResultSet CargarTipoEmpleadoControlador() {
        return Modelo.ModeloPrimerUsuario.CargarTipoEmpleado();
    }

    public boolean InsercionPrimerUsoUsuarioController() {
        return Modelo.ModeloPrimerUsuario.RegistrarUsuario(getIdUsuario(), usuario, contraseña);
    }

//     public static int ExistenciaPrimerUso_Controller(){
//        return Modelo.ModeloPrimerUsuario(usuario);
//    }
    public boolean IncercionEmpleadoController() {
        return Modelo.ModeloPrimerUsuario.RegistrarEmpleado(nombreUsuario, apellidoUsuario, dui, edadUsu, telefonoUsu, correoUsu, imagen, sexoUsu, idEmpresa, idTipoEmpleado, idUsuario);
    }

    public static ResultSet CargarIdUsuarioController() {
        return Modelo.ModeloPrimerUsuario.CargarIdUsuario();
    }

    public ArrayList<Integer> idUsuario() {
        return Modelo.ModeloPrimerUsuario.ConseguirIdUsuario();
    }

    public ArrayList<Integer> idEmpresa() {
        return Modelo.ModeloPrimerUsuario.ConseguirIdEmpresa();
    }

    public static ResultSet CargarIdEmpresaController() {
        return Modelo.ModeloPrimerUsuario.CargarIdEmpresa();
    }

    public ArrayList<Integer> idTipoEmpleado() {
        return Modelo.ModeloPrimerUsuario.ConseguirIdTipoEmpleado();
    }

    public static boolean RevisarTablaUsuController() {
        boolean confirm = false;

        ResultSet rs = Modelo.ModeloPrimerUsuario.RevisarTablaUsuario();
        try {
            while (rs.next()) {
                confirm = true;
                return confirm;
            }
        }catch (Exception e) {
            
        }
        return confirm;
    }

    public static int RevisarUsuarioController() {
        return Modelo.ModeloPrimerUsuario.VerificacionUsuario(usuario);
    }

    public int idMaximo() {
        //Creamos variable que guaradara el idmaximo e inicializamos con 0
        int idmax = 0;
        //Creamos objeto de tipo ResultSet
        ResultSet rs = Modelo.ModeloPrimerUsuario.idUsuarioMax();
        try {
            while (rs.next()) {
                idmax = (rs.getInt(1)) + 1;
                System.out.println(String.valueOf(idmax));
            }
        } catch (Exception e) {
        }
        return idmax;
    }

    public static int obtenerIdUsuario(String usuario) {
        //Creamos variable int
        int id = 0;
        //Creamos objeto de tipo resultset que sea la ejecucion del metodo en el modelo
        ResultSet res = ModeloPrimerUsuario.ObtenerIdUsuario(usuario);
        try {
            while (res.next()) {
                //obtenemos el id
                id = res.getInt("idUsuario");
            }
            res.close();
        } catch (Exception e) {
        }
        return id;

    }
    
    //Creamos metodo con expresiones regulares que verifique que el correo tenga la sintaxis correcta
    public static boolean ConfirmarCorreo(String str) {
        //Creamos el patron del correo que sera la sintaxis correcta
        // Patrón para validar el email
        Pattern patron = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]{3}))+");
        //Creamos un objeto de la clase matcher que comparará el patron con el email
        Matcher sintaxis = patron.matcher(str);
        if (sintaxis.find() == true) {
            return true;
        } else {
            return false;

        }
    }

    public static boolean ActualizarContraseña(String claveUsuario, String usuario) {
        int id = obtenerIdUsuario(usuario);
        boolean confirm = ModeloPrimerUsuario.ActualizarContraseña(id, claveUsuario);
        if (confirm == true) {
            return true;
        } else {

            return false;
        }
    }
    
    public boolean ConfirmarDui(){
        //Creamos objeto del modelo
        ModeloPrimerUsuario obj = new ModeloPrimerUsuario();
        //Creamos objeto de tipo boleano para confirmar
        boolean confirm=false;
        //Creamos objeto de tipo resultSet para guardar el resultado
        ResultSet res = obj.ObtenerDuiEmpleado(dui);
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
//            FrmNotiError error = new FrmNotiError();
//            error.TAnotiError.setText("¡Error!No se ha podido encontrar el usuario");
//            error.setVisible(true); 
        }
        //retornamos la confirmación
        return confirm;
    }
    
     public boolean ConfirmarTelefono(){
        //Creamos objeto del modelo
        ModeloPrimerUsuario obj = new ModeloPrimerUsuario();
        //Creamos objeto de tipo boleano para confirmar
        boolean confirm=false;
        //Creamos objeto de tipo resultSet para guardar el resultado
        ResultSet res = obj.ObtenerTelefonoEmpleado(telefonoUsu);
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
//            FrmNotiError error = new FrmNotiError();
//            error.TAnotiError.setText("¡Error!No se ha podido encontrar el usuario");
//            error.setVisible(true); 
        }
        //retornamos la confirmación
        return confirm;
    }
     
    public boolean contraIgual(String contra, String contrac){
        if (contra.equals(contrac)) {
            return true;
        }else{
            return false;
        }
    }    
    
    public int validarFecha(java.util.Date fecha) {
        //Creamos un objeto del calendario y obtenemos su instancia
        Calendar calendario = Calendar.getInstance();
        //Añadimos al calendiario la fecha de nacimiento del empleado
        calendario.setTime(fecha);
        //Creamos variables año,mes y dia y las inicializamos obteniendo sus respectivos datos del calendario
        int año = calendario.get(Calendar.YEAR);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = (calendario.get(Calendar.MONTH)) + 1;//Se le suma uno ya que el calendario que se usa posee a enero como indice 0.
        //Creamos dos variables que seran de tipo LocalDate para comparar
        LocalDate f1 = LocalDate.of(año, mes, dia);//Añadimos a la fecha 1 el año,mes y dia
        LocalDate f2 = LocalDate.now();//Obtenemos la fecha actual
        //Creamos una variable de tipo Period que sera el periodo de tiempo entre fecha 1 y fecha 2
        Period periodo = Period.between(f1, f2);
        //retornamos el peridodo convertido con los años que han pasado
        return periodo.getYears();
    }


    //ATRIBUTOS
    private static String usuario;
    private static String nombreUsuario;
    private static String apellidoUsuario;
    private static String dui;
    private static String telefonoUsu;
    private static String correoUsu;
    private static String sexoUsu;
    private static Date edadUsu;
    private static int tipoEmpleado;
    private static int estadoUsu;
    private static String contraseña;
    private static String imagen;
    private static int idUsuario;
    private static int idTipoEmpleado;
    private static int idEmpresa;

    //METODOS GET Y SET
    public String getNombreUsuE() {
        return nombreUsuario;
    }

    public void setNombreE(String nombreE) {
        this.nombreUsuario = nombreE;
    }

    public String getApellidoUsuE() {
        return apellidoUsuario;
    }

    public void setApellidoUsuE(String apellidoE) {
        this.apellidoUsuario = apellidoE;
    }

    public String getTelefonoUsuE() {
        return telefonoUsu;
    }

    public void setTelefonoUsuE(String telefonousuE) {
        this.telefonoUsu = telefonousuE;
    }

    public String getCorreoUsuE() {
        return correoUsu;
    }

    public void setCorreoUsuE(String correousuE) {
        this.correoUsu = correousuE;
    }

    public Date getEdadUsuuE() {
        return edadUsu;
    }

    public void setEdadUsuE(Date EdadE) {
        this.edadUsu = EdadE;
    }

    public int getTipoEmpleadoE() {
        return getTipoEmpleado();
    }

    public void setTipoEmpleadoE(int tipoeE) {
        this.setTipoEmpleado(tipoeE);
    }

    public String getUsuarioE() {
        return usuario;
    }

    public void setUsuarioE(String usuarioE) {
        this.usuario = usuarioE;
    }

    public String getClaveE() {
        return contraseña;
    }

    public void setClaveE(String claveUsuE) {
        this.contraseña = claveUsuE;
    }

    public int getEstadousuE(int EstadousuE) {
        return estadoUsu;
    }

    public void setEstadousuE(int EstadousuE) {
        this.estadoUsu = EstadousuE;
    }

    public String getImagenUsuE() {
        return imagen;
    }

    public void setImagenUsuE(String imagenusuE) {
        this.imagen = imagenusuE;
    }

    public int getIdUsuarioE() {
        return getIdUsuario();
    }

    public void setIdUsuarioE(int idusuE) {
        this.setIdUsuario(idusuE);
    }

    public int getIdEmpresaE() {
        return idEmpresa;
    }

    public void setIdEmpresaE(int idempresaE) {
        this.idEmpresa = idempresaE;
    }

    public static String getSexoUsu() {
        return sexoUsu;
    }

    public static void setSexoUsu(String aSexoUsu) {
        sexoUsu = aSexoUsu;
    }

    public static String getDui() {
        return dui;
    }

    public static void setDui(String aDui) {
        dui = aDui;
    }

    public static int getTipoEmpleado() {
        return tipoEmpleado;
    }

    public static void setTipoEmpleado(int aTipoEmpleado) {
        tipoEmpleado = aTipoEmpleado;
    }

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int aIdUsuario) {
        idUsuario = aIdUsuario;
    }

    public int getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(int idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }
}
