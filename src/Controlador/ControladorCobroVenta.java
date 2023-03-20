/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloCobroVenta;
import Modelo.ModeloDetalleVenta;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class ControladorCobroVenta {
    
    //Creamos objeto
    ModeloCobroVenta obj = new ModeloCobroVenta();
    
    //Se crea metodo para retornar el tipo de pago
    public static ResultSet CargarTipoPagoController(){
        return Modelo.ModeloCobroVenta.CargarTipopago();
    }
    //Se crea metodo de Arraylist para retornar el id de tipo de pago
    public ArrayList<Integer> idTipoPago(){
        return obj.ConseguirIdTipoPago(); 
    }
    //Se crea metodo de ArrayList para retornar el ide de venta
     public ArrayList<Integer> idVenta(){
        return obj.ConseguirIdVenta(); 
    }
     //Se crea metodo de ArrayList que retorne el id de detalle
      public ArrayList<Integer> idDetalle(){
        return obj.ConseguirIdDetalle(); 
    }
     //Se crea metodo que retorne el id de detalle
      public static ResultSet CargarIdDetalleController(){
        return Modelo.ModeloCobroVenta.CargarIdDetalle();
    }
      //Se crea metodo que retorne el actualizar venta
     public boolean ActualizarVentaController(){
        return Modelo.ModeloCobroVenta.ActualizarVenta(MontoTotalV, vuelto, idTipoPago, idVenta);
    }
     //Se crea metodo que retorne eliminar detalle
      public boolean EliminarDetalleVentaController(){
        return Modelo.ModeloCobroVenta.EliminarDetalleV( idDetalle);
    }
      
    //Cargamos uma total de venta      
        public static int CargarSumaTotalV(){
        return Modelo.ModeloCobroVenta.ConsultarSumaTotalV();
    }
     
    //Atributos
    private static double totalPago=0;//Atributo de total de pago
    private static double vueltoC;//Atributo de vuelto
    private static double MontoTotalV;//Atributo de monto total de venta
    private static double vuelto;//Atributo de vuelto
    private static Integer idTipoPago;//Atributo de id de tipo de pago
    private static Integer idVenta;//Atributo de id de venta
    private static Integer idDetalle;//Atributo de id de detalle
     
    //Generamos get y set de lo atributos
    
   public double gettotalPago(){
         return totalPago;
        }
   public void settotalPago(double Pago){
        this.totalPago= Pago;
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
     * @return the idTipoPago
     */
    public Integer getIdTipoPago() {
        return idTipoPago;
    }

    /**
     * @param idTipoPago the idTipoPago to set
     */
    public void setIdTipoPago(Integer idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    /**
     * @return the idVenta
     */
    public Integer getIdVenta() {
        return idVenta;
    }

    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    /**
     * @return the vueltoC
     */
    public static double getVueltoC() {
        return vueltoC;
    }

    /**
     * @param aVueltoC the vueltoC to set
     */
    public static void setVueltoC(double aVueltoC) {
        vueltoC = aVueltoC;
    }

    /**
     * @return the idDetalle
     */
    public Integer getIdDetalle() {
        return idDetalle;
    }

    /**
     * @param idDetalle the idDetalle to set
     */
    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }
}
