package Controlador;

import Modelo.ModeloLogin;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author lenny
 */
public class ControladorLogin {
    public static int ValidarExistenciaEmpresa_Controller(){
        return Modelo.ModeloLogin.ValidarExistenciaEmpresa();
    }
    
    public static int ValidarExistenciaPrimerUsuario_Controller(){
        return Modelo.ModeloLogin.ValidarExistenciaPrimerUsuario();
    }
    
    public static int Acceso_Controller(){
        return Modelo.ModeloLogin.Acceso(usuario, ClaveUsuario);
    }
    
    public static int ValidarUsuarioEmpleado_Controller(){
        return Modelo.ModeloLogin.ValidarUsuarioEmpleado(usuario);
    } 
    
    public static ArrayList<Object>Nivel_Controller(){
        return Modelo.ModeloLogin.ObtenerDatos(usuario);
    }
    
    public static boolean ActualizarEstado_Controller(){
        return ModeloLogin.ActualizarEstado(usuario, idEstadoU);
    }
    
    public static boolean ActualizarIntentos_Controller(){
        return ModeloLogin.ActualizarIntentos(usuario, intento);
    }
    
    public static boolean HabilitarIntentos_Controller(){
        return ModeloLogin.HabilitarIntentos(usuario, intento, idEstadoU);
    }
    
    public static ArrayList<Time> Hora_Controller(){
        return ModeloLogin.ObtenerHora(usuario);
    }
    
    public static boolean AgregarHoraI_Controller(){
        return ModeloLogin.RegistrarHoraIntento(usuario, HoraInactivación, HoraActivación);
    }
    
    //ATRIBUTOS
    private static String idUsuario;
    private static String usuario;
    private static int intento; 
    private static int idEstadoU;
    private static String ClaveUsuario ;
    private static Time HoraInactivación;
    private static Time HoraActivación;
    private static Time HoraBlock;
    private static Time HoraDesBlock;
    
    //METODOS GET Y SET
    public String getidUsuario(){
        return idUsuario;
    }
    
    public void setidUsuario(String idUsuario){
        this.idUsuario = idUsuario;
    }
    
    public String getUsuario(){
        return usuario;
    }
    
    public void setUsuario(String Usuario){
        this.usuario = Usuario;
    }
    
    public String getClave_Usuario(){
        return ClaveUsuario;
    }
    
    public void setClave_Usuario(String ClaveUsuario){
        this.ClaveUsuario = ClaveUsuario;
    }
    
    public int getIntento(){
        return intento;
    }
    
    public void setIntento(int Intento){
        this.intento = Intento;
    }
    
    public int getEstadoU(){
        return idEstadoU;
    }
    
    public void setEstadoU(int EstadoUsuario){
        this.idEstadoU = EstadoUsuario;
    }
    
    public Time getHoraInactivación(){
        return HoraInactivación;
    }
    
    public void setHoraInactivación(Time HoraIntento){
        this.HoraInactivación = HoraIntento;
    }
    
    public Time getHoraActivacion(){
        return HoraActivación;
    }
    
    public void setHoraActivacion(Time HoraActivación){
        this.HoraActivación = HoraActivación;
    }
    
    public Time getHoraB(){
        return HoraBlock;
    }
    
    public void setHoraB(Time HoraBloqueo){
        this.HoraBlock = HoraBloqueo;
    }
    
    public Time getHoraD(){
        return HoraDesBlock;
    }
    
    public void setHoraD(Time HoraDesbloqueo){
        this.HoraDesBlock = HoraDesbloqueo;
    }
}
