/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

/**
 *
 * @author lenny
 */
public class ControladorPrimerUso {
    
    public static ResultSet CargarPaisControlador(){
        return Modelo.ModeloPrimerUso.CargarPais();
    }
    
    public boolean InsercionPrimerUsoController(){
        return Modelo.ModeloPrimerUso.RegistrarEmpresa(nombreEmpresa, nit, anioFundacion, telefonoEmpresa, direccionEmpresa, idPais);
    }
    
    public static ResultSet ObtenerIdPaisControlador(){
        return Modelo.ModeloPrimerUso.ObtenerIdPais(Pais);
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
    
    private static String nombreEmpresa;
    private static String nit;
    private static Date anioFundacion;
    private static int telefonoEmpresa;
    private static String direccionEmpresa;
    private static int idPais;
    private static String Pais;
    
    
    public String getNombreE(){
        return nombreEmpresa;
    }
    
    public void setNombreE(String nombreE){
        this.nombreEmpresa = nombreE;
    }
    
    public String getNitE(){
        return nit;
    }
    
    public void setNitE(String NITE){
        this.nit = NITE;
    }
    
    public Date getAnioFundacionE(){
        return anioFundacion;
    }
    
    public void setAnioFundacionE(Date ANIOE){
        this.anioFundacion = ANIOE;
    }
    
    public int gettelefonoEmpresaE(){
        return telefonoEmpresa;
    }
    
    public void settelefonoEmpresaE(int TELEFONOE){
        this.telefonoEmpresa = TELEFONOE;
    }
    
    public String getdireccionEmpresaE(){
        return direccionEmpresa;
    }
    
    public void setdireccionEmpresaE(String DIRECCIONE){
        this.direccionEmpresa = DIRECCIONE;
    }
    
    public int getPaisE(){
        return idPais;
    }
    
    public void setPaisE(int paisN){
        this.idPais = paisN;
    }
    
    public int getidPaisE(){
        return idPais;
    }
    
    public void setidPaisE(int PAISE){
        this.idPais = PAISE;
    }
}
