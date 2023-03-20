/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.ModeloDetalleFactura;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Oliver
 */
public class ControladorDetalleFactura {
    ModeloDetalleFactura modelo = new ModeloDetalleFactura();
    public static double adel=0;
    private static ArrayList<Object[]> datos;
    private static Object[] actualizarfactura;
    
    public DefaultComboBoxModel CargarProducto(){
        return modelo.Productos();
    }
    public ArrayList<String> CargaIDProductos(){
        return modelo.IDProductos();
    }
    public Object[] CargarDatosCMB(String codigo){
        return modelo.CargarDatosCMB(codigo);
    }
    public DefaultComboBoxModel CargarCategoria(){
        return modelo.Categorias();
    }
    public Object[] ejecutar(String codigo){
        return modelo.CargarDatos(codigo);
    }
    public int codigoFactura(){
        return modelo.IDInsertar();
    }
    public boolean GuardarDetalle(Object[] datos){
        return modelo.GuardarFactura(datos);
    }
    
    public boolean ActualizarFactura(){
        return modelo.ActualizarFactura(actualizarfactura);
    }
    //Me encanta el premio ¿Y a ustedes?
    /**
     * @return the adel
     */
    public double getAdel() {
        return adel;
    }
    
    public void setAdel(double adelanto){
        this.adel = adelanto;
    }

    /**
     * @return the datos
     */
    public ArrayList<Object[]> getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(ArrayList<Object[]> datos) {
        this.datos = datos;
    }

    /**
     * @return the actualizarfactura
     */
    public Object[] getActualizarfactura() {
        return actualizarfactura;
    }

    /**
     * @param actualizarfactura the actualizarfactura to set
     */
    public void setActualizarfactura(Object[] actualizarfactura) {
        this.actualizarfactura = actualizarfactura;
    }
    
}
