/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloVenta;
import java.sql.ResultSet;

/**
 *
 * @author pc
 */

import java.sql.Date;
import java.util.ArrayList;
public class ControladorVenta {
    
    //Creamos objeto
    ModeloVenta obj = new ModeloVenta();
    
    //Se crea metodo que retorna el nombre de empresa
      public static ResultSet CargarEmpresaControlador(){
        return Modelo.ModeloVenta.CargarEmpresa();
    }
      //Se crea metodo para retoornar el empleado
       public static ResultSet CargarEmpleadoControlador(){
        return Modelo.ModeloVenta.CargarEmpleado();
    }
       //Se crea metodo que retorna el tipo de pago
       public static ResultSet CargarTipoPagoControlador(){
        return Modelo.ModeloVenta.CargarTipoPago();
    }
    //Se crea metodo que retorna el metodo para insertar una venta
     public boolean IngresarVentaController(){
        return Modelo.ModeloVenta.IngresarVenta(fechaFacturaVenta, MontoTotalV, nombreCliente, telefonoCliente, vuelto, idEmpresa, idTipoPago, idEmpleado);
    }
     //Se crea metodo de Arraylist que retorna el id de empleado
      public ArrayList<Integer> idEmpleado(){
        return obj.ConseguirIdEmpleado(); 
    }
      //Se crea metodo de rraysList que retorna el id de empresa
       public ArrayList<Integer> idEmpresa(){
        return obj.ConseguirIdEmpresa(); 
    }
       //Se crea metodo de ArraysList que retorna el id tipo de pago 
       public ArrayList<Integer> idTipoPago(){
        return obj.ConseguirIdTipoPago(); 
    }
       
        //Método que devuelve el IDEmpresa
    public int CargarEmpresa(int ID) {
        return obj.IdEmpresa(ID);
    }
    
     
    
    //Atributos
     private static Date fechaFacturaVenta;//Atributo de fecha de factura
     private static double MontoTotalV;//Atributo de monto total de venta
     private static String nombreCliente;//Atributo de nombre de cliente 
     private static String telefonoCliente;//Atributo de telefono de cluente
     private static double vuelto;//Atributo de vuelto
     private static Integer idEmpleado;//Atributo de id de empleado
     private static Integer idTipoPago;//Atributo de id de tipo de pago
     private static Integer idEmpresa;//Atributo de id de empresa
     private static String empleado;
     private static String empresa;
     
    /**
     * @return the fechaFacturaVenta
     */
    public Date getFechaFacturaVenta() {
        return fechaFacturaVenta;
    }

    /**
     * @param fechaFacturaVenta the fechaFacturaVenta to set
     */
    public void setFechaFacturaVenta(Date fechaFacturaVenta) {
        this.fechaFacturaVenta = fechaFacturaVenta;
    }

    /**
     * @return the MontoTotalV
     */
    public double getMontoTotalV() {
        return MontoTotalV;
    }

    /**
     * @param MontoTotalV the MontoTotalV to set
     */
    public void setMontoTotalV(double MontoTotalV) {
        this.MontoTotalV = MontoTotalV;
    }

    /**
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @return the telefonoCliente
     */
    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    /**
     * @param telefonoCliente the telefonoCliente to set
     */
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    /**
     * @return the vuelto
     */
    public double getVuelto() {
        return vuelto;
    }

    /**
     * @param vuelto the vuelto to set
     */
    public void setVuelto(double vuelto) {
        this.vuelto = vuelto;
    }

    /**
     * @return the idEmpleado
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado the idEmpleado to set
     */
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return the idTipoPago
     */
    public int getIdTipoPago() {
        return idTipoPago;
    }

    /**
     * @param idTipoPago the idTipoPago to set
     */
    public void setIdTipoPago(Integer idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    /**
     * @return the idEmpresa
     */
    public int getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the empleado
     */
    public String getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

}
