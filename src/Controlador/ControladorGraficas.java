/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.ModeloGraficas;
import java.util.ArrayList;

/**
 *
 * @author Oliver
 */
public class ControladorGraficas {
    //Objeto del modelo de gráfica
    ModeloGraficas modelo = new ModeloGraficas();
    
    //Devuelve la cantidad total de las facturas de renta
    public int cantidadTotalRenta(){
        return modelo.totalRenta();
    }
    //Devuelve las cantidades de las categorias en la renta
    public int[] DatosGraficaRenta(String extra){
        return modelo.datosRenta(extra);
    }
    //Devuelve las cantidades de las categorias en la venta
    public int[] DatosGraficaVenta(String extra){
        return modelo.datosVenta(extra);
    }
    //Devuelve la cantidad total de las  ventas
    public double cantidadTotalVenta(String[] datos){
        return modelo.totalVenta(datos);
    }
    //Carga los datos del empleados, nombre y cantidad
    public ArrayList<Object[]> Empleados(String[] datos){
        return modelo.empleados(datos);
    }
    //Carga las cantidades de los empleados
    public double CantidadEmpleados(String codigo){
        return modelo.TotalVentaEmpleados(codigo);
    }
    
}
