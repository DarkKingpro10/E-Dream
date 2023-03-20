/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.ModeloPagarRenta;
import java.util.ArrayList;
/**
 *
 * @author Oliver
 */
public class ControladorPagarRenta {
    //Creamos el modelo 
    ModeloPagarRenta modelo = new ModeloPagarRenta();
    
    //Método que busca los datos de la factura
    public Object[] FinalizarFactura(int codigo){
        return modelo.DatosFinalizar(codigo);
    }
    
    //Método que actualiza el pago final
    public boolean GuardarRenta(double cantidad, int codigo){
        return modelo.Insertar(cantidad, codigo);
    }
    //Método que obtiene los prodcutos que se rentaron del inventario (El total restante)
    public ArrayList<Object[]> ProductosRenta(int codigo){
        return modelo.ProductosRenta(codigo);
    }
    
    //Método que confirma la actualización
    public boolean confirmacion(String codigo, int cantidad){
        return modelo.actualizarproductos(codigo,cantidad);
    }
    
    //Método que obtiene los productos del detalle (Productos en factura)
    public int ProductosInventario(String codigo){
        return modelo.productoInventario(codigo);
    }
}
