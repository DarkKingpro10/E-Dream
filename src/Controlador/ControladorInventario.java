/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.ModeloInventario;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Oliver
 */
public class ControladorInventario {
    //Objeto del modelo : ModeloInventario
    ModeloInventario modelo = new ModeloInventario();
    
    //Método que devuelve el estado del INSERT (Se le envían los datos de la vista)
    public boolean GuardarProducto(Object[] datos){
        //Devuelve el valor que reciba del modelo
        return modelo.guardarNuevoProducto(datos);
    }
    //Método que devuelve el estado del UPDATE hecho en la base
    public boolean ActualizarProducto(Object[] datos){
        //Devuelve el valor que reciba del modelo
        return modelo.ActualizarProducto(datos);
    }
    //Método que devuelve el estado del UPDATE (Ocultar-Eliminar)
    public boolean EliminarProducto(String producto){
        //Devuelve el estado 
        return modelo.eliminarProducto(producto);
    }
    //Método que devueve los datos que se cargarán el el menú de visualizar - Se enviarán el identrificador para buscar
    public static Object[] Cargarmenu(String Identificador){
        //Retorna el obejcto que cargará los datos
        return ModeloInventario.CargarDatosMenu(Identificador);
    }
    
    //Metodo que cargar los datos del menú para modficar el producto
    public static Object[] CargarMenuModificar(String Identificador){
        //Retorna el objeto con los datos
        return ModeloInventario.CargarDatosModificar(Identificador);
    }
    //Método para cargas la tabla de inventario para la renta
    public ResultSet CargarTablaRenta(){
        return modelo.tablaRenta();
    }
    //Método parar cargar la tabla de inventario para la venta
    public ResultSet CargarTablaVenta(){
        return modelo.TablaVenta();
    }
    //Método que carga los ID de los productos de venta
    public ArrayList<String> IDVenta(){
        return modelo.CargasIDVenta();
    }
    //Método que cargas los ID de los productos de Renta
    public ArrayList<String> IDRenta(){
        return modelo.CargasID();
    }

       //Método que devuelve el IDEmpresa
    public String CargarEmpresa(int ID) {
        return modelo.Empresa(ID);
    }
}
