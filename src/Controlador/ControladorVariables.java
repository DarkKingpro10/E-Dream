/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Blob;

/**
 *
 * @author lenny
 */
public class ControladorVariables {
    public static int Idioma = 1;
    
    public static int Color = 1;
    private static int idUsuario;
    private static String usuario;
    public static int intento; 
    public static int idEstadoU;
    public static int idEmpleado;
    public static int idTipoE;
    public static String TipoE = null;
    public static String nombreE = null;
    public static String apellidoE = null;
    private static String ClaveUsuario;
    private static String dui;
    private static Blob Imagen;
    
    public int getIdioma() {
        return Idioma;
    }
    public void setIdioma(Integer Pidioma) {
        this.Idioma = Pidioma;
    }
    
    public int getColor() {
        return Color;
    }
    
    public void setColor(Integer color) {
        this.Color = color;
    }
    
    //METODOS GET Y SET
     public int getidUsuario(){
        return idUsuario;
    }
    
    public void setidUsuario(int idUsuario){
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
    
    public int getEmpleado(){
        return idEmpleado;
    }
    
    public void setEmpleado(int idempleado){
        this.idEmpleado = idempleado;
    }
    
    public int getidTipoE(){
        return idTipoE;
    }
    
    public void setidTipoE(int idTipoEmpleado){
        this.idTipoE = idTipoEmpleado;
    }
    
    public String getTipoE(){
        return TipoE;
    }
    
    public void setTipoE(String TipoEmpleado){
        this.TipoE = TipoEmpleado;
    }
    
    public String getNombreE(){
        return nombreE;
    }
    
    public void setNombreE(String NombreEmpleado){
        this.nombreE = NombreEmpleado;
    }
    
    public String getApellidoE(){
        return apellidoE;
    }
    
    public void setApellidoE(String ApellidoEmpleado){
        this.apellidoE = ApellidoEmpleado;
    }
    
    public String getDUI(){
        return dui;
    }
    
    public void setDUI(String Dui){
        this.dui = Dui;
    }
    
    public Blob getImagen(){
        return Imagen;
    }
    
    public void setImagen(Blob ImagenE){
        this.Imagen = ImagenE;
    }
}
