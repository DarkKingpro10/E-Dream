/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloDetalleRenta;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Oliver
 */
public class ControladorDetalleRenta {

    //Se crea el objeto del modelo
    ModeloDetalleRenta modelo = new ModeloDetalleRenta();
    //Se crea la variable del adelanto
    public static double adel = 0;
    /*Existe una diferencia entre la lista y el objeto, y es que al guardar los productos
    *en el detalle, no se sabe cual será la cantidad de datos, ya que pueden ser 1 o 100 o más
    *y es por eso que se guarda en una lista, la cuál se le pueden agregar tanto datos se necesiten,
    *y allí dentro de guardará la fila (Vector de objetos) con los datos de los productos.
    *En el vector de objetos se guardaran los datos de la factura que se actualizara
    *(Solo 1 fila) y por eso solo se crea el objeto, al contrario de la lista de vectores de objetos
    *que se utilizan en el detalle.
     */
    //Se crea una lista dónde se guardarán los datos
    private static ArrayList<Object[]> datos;
    //Se guardan los datos para actualizar los datos
    private static Object[] actualizarfactura;

    public ArrayList<Object[]> DatosProductos() {
        return modelo.DatosProductos();
    }

    //Método que obtiene los datos del producto a base de la selección del combobox
    public Object[] CargarDatosCMB(String codigo) {
        return modelo.CargarDatosCMB(codigo);
    }

    //Método que devuelve el modelo de comboboxCategoria
    public DefaultComboBoxModel CargarCategoria() {
        return modelo.Categorias();
    }

    //Método que devuele los datos que se obtienen por el codigo ingresado por el buscador
    public Object[] ejecutar(String codigo) {
        return modelo.CargarDatos(codigo);
    }

    //Devuelve el codigo de la ultima factura creada
    public int codigoFactura() {
        return modelo.IDInsertar();
    }

    //Método que guarda los productos en el detalle
    public boolean GuardarDetalle(Object[] datos) {
        return modelo.GuardarFactura(datos);
    }

    //Método que devuelve si la factura fue correctamente actualizada
    public boolean ActualizarFactura() {
        return modelo.ActualizarFactura(actualizarfactura);
    }

    //Método que obtiene la cantidad de productos en el inventario
    public int CantProductoIN(String codigo){
        return modelo.cantidadProducto(codigo);
    }

    //Método que obtiene el estado de la actualización de la cantidad
    //del producto
    public boolean ActualizarProductos(Object[] datos) {
        return modelo.ActualizaProductosC(datos);
    }

    //Get y Set de los datos que se obtienen del detalle a la finalización de la renta
    public double getAdel() {
        return adel;
    }

    public void setAdel(double adelanto) {
        this.adel = adelanto;
    }

    public ArrayList<Object[]> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<Object[]> datos) {
        this.datos = datos;
    }

    public Object[] getActualizarfactura() {
        return actualizarfactura;
    }

    public void setActualizarfactura(Object[] actualizarfactura) {
        this.actualizarfactura = actualizarfactura;
    }

}
