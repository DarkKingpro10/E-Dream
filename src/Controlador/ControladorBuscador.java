/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Alumno
 */
public class ControladorBuscador {
    public static int ExistenciaProductosControlador(){
        return Modelo.ModeloBuscador.TotalProductos();
    }
    
    public static int ExistenciaProductosControlador2(){
        return Modelo.ModeloBuscador.TotalProductos2(NombreP);
    }
    
    public static int ExistenciaProductosControlador3(){
        return Modelo.ModeloBuscador.TotalProductos3(Id);
    }
    
    public static ArrayList<Blob> ObtenerImagenesControlador(){
        return Modelo.ModeloBuscador.ObtenerImagenes(Id);
    }
    
    public static ArrayList<String> ObtenerCodigoControlador(){
        return Modelo.ModeloBuscador.ObtenerCodigo(Id);
    }
    
    public static ArrayList<Blob> ObtenerImagenesControlador2(){
        return Modelo.ModeloBuscador.ObtenerImagenes2(Id, NombreP);
    }
    
    public static ArrayList<String> ObtenerCodigoControlador2(){
        return Modelo.ModeloBuscador.ObtenerCodigo2(Id, NombreP);
    }
    
    public static int Id;
    public static String NombreP;
    
    public int getCantidad(){
        return Id;
    }
    
    public void setCantidad(int cantidad){
        this.Id = cantidad;
    }
    
    public String getNombreP(){
        return NombreP;
    }
    
    public void setNombreP(String nombre){
        this.NombreP = nombre;
    }
}
