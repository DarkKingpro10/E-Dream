/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Oliver
 */
public class ModeloPagarRenta {

    private Connection conexion;

    //Se inicializa la conexion
    public ModeloPagarRenta() {
        conexion = Conexion.Conexion();
    }

    //Método que obtiene los datos mostrados en los productos
    public Object[] DatosFinalizar(int codigo) {
        //Se crea el vector de objetos
        Object[] datos = new Object[7];
        datos[0] = "-"; //Nombre
        datos[1] = "-"; //Estado
        datos[2] = "-"; //DUI
        datos[3] = "0.0"; //Mora
        datos[4] = "0.0"; //Monto
        datos[5] = "0.0"; //Adelanto
        datos[6] = "-"; //Fecha realizada
        //Sentencia
        String SQL = "SELECT TOP 1 nombreCliente, estadoFacturaRenta, DUI, mora, MontoTotalR, adelantoPago, fechaFacturaRenta FROM tbFacturaRenta fr, tbEstadoFacturaRenta efr, tbDetalleFacturaRenta dfr WHERE efr.idEstadoFacturaRenta = dfr.idEstadoFacturaRenta AND fr.idFacturaRenta = dfr.idFacturaRenta AND fr.idFacturaRenta = ? AND (dfr.idEstadoFacturaRenta = 1 or dfr.idEstadoFacturaRenta=3)";
        try {
            //Se prepara la sentencia
            PreparedStatement SELECT = conexion.prepareStatement(SQL);
            //Se inserta el codigo de la factura
            SELECT.setInt(1, codigo);
            //Se obtiene los datos de l ejecución
            ResultSet Linea = SELECT.executeQuery();
            while (Linea.next()) {
                //Se guardan los datos
                datos[0] = Linea.getString(1); //Nombre
                datos[1] = Linea.getString(2); //Estado
                datos[2] = Linea.getInt(3); //DUI
                datos[3] = Linea.getDouble(4); //Mora
                datos[4] = Linea.getDouble(5); //Monto
                datos[5] = Linea.getDouble(6); //Adelanto
                datos[6] = Linea.getString(7); //Fecha realizada

            }
        } catch (Exception e) {

        }
        //Se devuelven los datos
        return datos;
    }

    //Método que actualiza el estado y el adelanto
    public boolean Insertar(Double pago, int codigo) {
        //Se crea el estado de las actualizaciones
        boolean estado = false;
        //Se crean las sentencias
        String sentencia = "UPDATE tbFacturaRenta SET adelantoPago = ?  WHERE idFacturaRenta = ?;";
        String Actualizar = "UPDATE tbDetalleFacturaRenta SET idEstadoFacturaRenta = 2 WHERE idFacturaRenta = ?;";
        try {
            //Se preparan las sentencias
            PreparedStatement UPDATE = conexion.prepareStatement(sentencia);
           PreparedStatement UPDATEEstado = conexion.prepareStatement(Actualizar);
            //Se insertan los datos para actualizar el adelanto
            UPDATE.setDouble(1, pago);
            UPDATE.setInt(2, codigo);
            //Se inserta el datos
            UPDATEEstado.setInt(1, codigo);
            //Se obtiene el estado de la ejecución
            boolean estado1 = UPDATE.execute();
            estado = UPDATEEstado.execute();
            //Si ambas son positivas se guarda como verdadero el dato
            if (!estado1 && !estado) {
                estado = true;
            }
        } catch (Exception e) {
        }
        //Se devuelve el estado
        return estado;
    }

    //Método que obtiene todos los productos que se devolverán
    public ArrayList<Object[]> ProductosRenta(int codigo) {
        //Lista de vecotres de objetos
        ArrayList<Object[]> lista = new ArrayList();
        //Vector de objetos
        Object[] datos = new Object[2];
        //Setencia
        String SQL = "SELECT dfr.codigoProducto, dfr.cantidad FROM tbProducto p, tbFacturaRenta fr, tbDetalleFacturaRenta dfr WHERE p.codigoProducto = dfr.codigoProducto AND fr.idFacturaRenta = dfr.idFacturaRenta AND  fr.idFacturaRenta=?";
        try {
            //Preparación de la sentencia
            PreparedStatement SELECT = conexion.prepareStatement(SQL);
            //Se inserta el codigo de la factura
            SELECT.setInt(1, codigo);
            ResultSet informacion = SELECT.executeQuery();
            while (informacion.next()) {
                datos[0] = informacion.getString(1); //Codigo producto
                datos[1] = informacion.getInt(2); //Cantidad en la factura
                //Se agregan los datos obtenidos a la lista
                lista.add(datos);
            }
        } catch (Exception e) {
            System.out.println("Problema al cargar los productos al devolver" + e);
        }
        return lista;
    }

    //Método que guarda lor productos 
    public boolean actualizarproductos(String codigo, int cantidad) {
        //Sentencia
        String SQL = "UPDATE tbProducto SET cantidad = ? WHERE codigoProducto=?;";
        try {
            //Preparación de la sentencia
            PreparedStatement UPDATE = conexion.prepareStatement(SQL);
            UPDATE.setString(2, codigo); //Codigo del producto
            UPDATE.setInt(1, cantidad); //Cantidad del producto devuelto + existente
            return !UPDATE.execute();
        } catch (Exception e) {
            return false;
        }
    }

    //Método que obtiene los productos de la renta
    public int productoInventario(String factura) {
        //Cantidad de productos en el ticket
        int cantidad = 0;
        //Sentencia
        String SQL = "SELECT cantidad FROM tbProducto WHERE codigoProducto=?";
        try {
            //Preparación de la sentecia
            PreparedStatement SELECT = conexion.prepareStatement(SQL);
            //Se inserta el codigo del producto del cual se obtendrá la cantidad exitente
            SELECT.setString(1, factura);
            ResultSet datos = SELECT.executeQuery();
            while (datos.next()) {
                //Se obtiene la cantidad
                cantidad = datos.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Obtener cantidad del inventario" + e);
        }
        return cantidad;
    }
}
