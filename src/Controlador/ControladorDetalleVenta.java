/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.ResultSet;
import Modelo.ModeloDetalleVenta;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class ControladorDetalleVenta {

    //Creamos objeto e modelo de detalle de venta
    ModeloDetalleVenta obj = new ModeloDetalleVenta();

    //Se crea metodo para retornar nombre de producto
    public static ResultSet CargarProductoController() {
        return Modelo.ModeloDetalleVenta.CargarProducto();
    }

    //Se cre metodo para retornar ide de venta
    public static ResultSet CargarIdVentaController() {
        return Modelo.ModeloDetalleVenta.CargarIdVenta();
    }

    //Se crea metodo para retornar la insercion de venta
    public boolean IngresarDetalleFacturaVentaController() {
        return Modelo.ModeloDetalleVenta.IngresarDetalleVenta(cantidad, descuento, costoTotalV, codigoProducto, idFacturaVenta);
    }
    //Se crea metodo de ArrayList para retornar los codigos de producto

    public ArrayList<String> idProducto() {
        return obj.ConseguirIdProducto();
    }
    //Se crea metodo de ArrayList para retornar los id de venta

    public ArrayList<Integer> idVenta() {
        return obj.ConseguirIdVenta();
    }

    //Se crea metodo para retornar la carga de tabla
    public static ResultSet CargarTablaController() {
        return Modelo.ModeloDetalleVenta.CargarTabla();
    }

    //Se crea metodo para retornar los datos de menu
    public boolean CargarDatoMenu() {
        return Modelo.ModeloDetalleVenta.CargarDatosMenu(cantidad, descuento, costoTotalV, codigoProducto, idFacturaVenta, identificador);
    }

    //Se crea metodo para retornar las vcaracteristicas de los productos del conbobox
    public Object[] CargarDatosCmb(String codigo) {
        return Modelo.ModeloDetalleVenta.CargarDescuentoPrecio(codigo);
    }
    //Se crea metodo para retornar los codigos de producto

    public ArrayList<String> CargaIDProductos() {
        return obj.IDProductos();
    }
    //Creamos metodo para retornar la cantidad de productos segun su codigo    

    public Object[] CargarDatoCantidad(String codigo) {
        return Modelo.ModeloDetalleVenta.CargarCantidad(codigo);
    }
    //Se crea metodo para retornar toda las cantidades

    public ArrayList<Integer> CargarCantidades() {
        return obj.CantidadesP();
    }
    //Se crea metodo para retornar el metodo de actualizar inventario

    public boolean ActualizarInventarioController() {
        return Modelo.ModeloDetalleVenta.ActualizarInventario(cantidadI, codigoProducto);
    }
    
    //Se crea metodo de tipo objeto para regrear el metodo de cargar datos de controlador
    public Object[] ejecutar(String codigo){
        return Modelo.ModeloDetalleVenta.CargarDatos(codigo);
    }

    //Atributos
    private static int cantidad;//Atributo de cantidad
    private static Double descuento;//Atributo de descuento
    private static Double costoTotalV;//Atributo de costo total de venta
    private static String codigoProducto;//Atributo de codigo de producto
    private static Integer idFacturaVenta;//Atributo de id de factura venta

    //Atributos para la id
    private static double costoUnitario;//Atributo de costo uniatrio de producto
    private static String identificador;//Atributo identificador
    private static int cantidadI;//Atributo de cantidad de productos que hay en inventario
    private static int cantidadT;//Atributo de cantidad que se vendera

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getCostoTotalV() {
        return costoTotalV;
    }

    public void setCostoTotalV(Double costoTotalV) {
        this.costoTotalV = costoTotalV;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Integer getIdFacturaVenta() {
        return idFacturaVenta;
    }

    public void setIdFacturaVenta(Integer idFacturaVenta) {
        this.idFacturaVenta = idFacturaVenta;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getCantidadI() {
        return cantidadI;
    }

    public void setCantidadI(int cantidadI) {
        this.cantidadI = cantidadI;
    }

    public int getCantidadT() {
        return cantidadT;
    }

    public void setCantidadT(int cantidadT) {
        this.cantidadT = cantidadT;
    }

}
