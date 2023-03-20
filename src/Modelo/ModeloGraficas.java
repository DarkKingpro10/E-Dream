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
public class ModeloGraficas {
    //Conexión
    private static Connection Conect;
    
    public ModeloGraficas(){
        //Se iguala el contenido de la conexión
        Conect = Conexion.Conexion();
    }
    //Método que trae la cantidad de productos vendidos
    public int[] datosVenta(String extra) {
        //Se crea el objeto dónde se guardarán los datos
        int[] datos = new int[5];
        //Se crean las cinco sentencias
        // Obtiene lo que son de la categoria Decoración
        String SQLDecoracion = "SELECT SUM(dfv.cantidad) FROM tbDetalleFacturaVenta dfv, tbCategoriaProducto cp, tbProducto p, tbFacturaVenta fv WHERE dfv.codigoProducto = p.codigoProducto AND cp.idCategoriaProducto = p.idCategoriaProducto AND cp.idCategoriaProducto = 1 "+extra;
        //Obtiene lo productos que son de la categoria Accesorio
        String SQLAccesorio = "SELECT SUM(dfv.cantidad) FROM tbDetalleFacturaVenta dfv, tbCategoriaProducto cp, tbProducto p, tbFacturaVenta fv WHERE dfv.codigoProducto = p.codigoProducto AND cp.idCategoriaProducto = p.idCategoriaProducto AND cp.idCategoriaProducto = 2 "+extra;
        //Obtiene los productos que son de la categoria Equupos de sonido
        String SQLEquipo = "SELECT SUM(dfv.cantidad) FROM tbDetalleFacturaVenta dfv, tbCategoriaProducto cp, tbProducto p, tbFacturaVenta fv WHERE dfv.codigoProducto = p.codigoProducto AND cp.idCategoriaProducto = p.idCategoriaProducto AND cp.idCategoriaProducto = 3 "+extra;
        //Obtiene los productos que son de la categoria Equipos Audiovisuales
        String SQLAudio = "SELECT SUM(dfv.cantidad) FROM tbDetalleFacturaVenta dfv, tbCategoriaProducto cp, tbProducto p, tbFacturaVenta fv WHERE dfv.codigoProducto = p.codigoProducto AND cp.idCategoriaProducto = p.idCategoriaProducto AND cp.idCategoriaProducto = 4 "+extra;
        //Obtiene los productos que son de la categoria Objetos tematicos
        String SQLTematicos = "SELECT SUM(dfv.cantidad) FROM tbDetalleFacturaVenta dfv, tbCategoriaProducto cp, tbProducto p, tbFacturaVenta fv WHERE dfv.codigoProducto = p.codigoProducto AND cp.idCategoriaProducto = p.idCategoriaProducto AND cp.idCategoriaProducto = 5 "+extra;
        try {
            //Preparación de las sentencias
            PreparedStatement SELECT1 = Conect.prepareStatement(SQLDecoracion);
            PreparedStatement SELECT2 = Conect.prepareStatement(SQLAccesorio);
            PreparedStatement SELECT3 = Conect.prepareStatement(SQLEquipo);
            PreparedStatement SELECT4 = Conect.prepareStatement(SQLAudio);
            PreparedStatement SELECT5 = Conect.prepareStatement(SQLTematicos);
            //Se guardan los datos con las ejecuciones
            ResultSet Decoracion = SELECT1.executeQuery();
            ResultSet Accesorios = SELECT2.executeQuery();
            ResultSet Equipo = SELECT3.executeQuery();
            ResultSet Audio = SELECT4.executeQuery();
            ResultSet Tematicos = SELECT5.executeQuery();
            //Se cargan los datos en el objeto
            //Si no hay nada se les coloca 0
            if (Decoracion != null && Decoracion.next()) {
                datos[0] = Decoracion.getInt(1);
            } else {
                datos[0] = 0;
            }
            if (Accesorios != null && Accesorios.next()) {
                datos[1] = Accesorios.getInt(1);
            } else {
                datos[1] = 0;
            }
            if (Equipo != null && Equipo.next()) {
                datos[2] = Equipo.getInt(1);
            } else {
                datos[2] = 0;
            }
            if (Audio != null && Audio.next()) {
                datos[3] = Audio.getInt(1);
            } else {
                datos[3] = 0;
            }
            if (Tematicos != null && Tematicos.next()) {
                datos[4] = Tematicos.getInt(1);
            } else {
                datos[4] = 0;
            }
        } catch (Exception e) {
            System.out.print("Modelo: " + e);
        }
        return datos;
    }
    
    //Método que devuelve la sumatoria de todos los monto totales de la renta
    public int totalRenta() {
        //Cantidad
        int total = 0;
        //Sentencia
        String SQL = "SELECT SUM(cantidad) FROM tbDetalleFacturaRenta";
        try {
            //Preparación de la sentencia
            PreparedStatement SELECT = Conect.prepareStatement(SQL);
            //Se guardan los datos de la ejecución
            ResultSet Datos = SELECT.executeQuery();
            //Se guadan los datos
            while (Datos.next()) {
                total = Datos.getInt(1);
            }
        } catch (Exception e) {
            System.out.print("Total : " + e);
        }
        //Se devuelven los datos
        return total;
    }
    //Método que carga todos los productos vendidos por categorias
    public int[] datosRenta(String extra) {
        //Vecto que devuelve las cantidades
        int[] datos = new int[5];
        //Sentencias por categoria
        String SQLDecoracion = "SELECT SUM(dfv.cantidad) FROM tbDetalleFacturaRenta dfv, tbCategoriaProducto cp, tbProducto p, tbFacturaRenta fv WHERE dfv.codigoProducto = p.codigoProducto AND cp.idCategoriaProducto = p.idCategoriaProducto AND dfv.idFacturaRenta = fv.idFacturaRenta AND cp.idCategoriaProducto = 1 "+extra;
        String SQLAccesorio = "SELECT SUM(dfv.cantidad) FROM tbDetalleFacturaRenta dfv, tbCategoriaProducto cp, tbProducto p, tbFacturaRenta fv WHERE dfv.codigoProducto = p.codigoProducto AND cp.idCategoriaProducto = p.idCategoriaProducto AND dfv.idFacturaRenta = fv.idFacturaRenta AND cp.idCategoriaProducto = 2 "+extra;
        String SQLEquipo = "SELECT SUM(dfv.cantidad) FROM tbDetalleFacturaRenta dfv, tbCategoriaProducto cp, tbProducto p, tbFacturaRenta fv WHERE dfv.codigoProducto = p.codigoProducto AND cp.idCategoriaProducto = p.idCategoriaProducto AND dfv.idFacturaRenta = fv.idFacturaRenta AND cp.idCategoriaProducto = 3 "+extra;
        String SQLAudio = "SELECT SUM(dfv.cantidad) FROM tbDetalleFacturaRenta dfv, tbCategoriaProducto cp, tbProducto p, tbFacturaRenta fv WHERE dfv.codigoProducto = p.codigoProducto AND cp.idCategoriaProducto = p.idCategoriaProducto AND dfv.idFacturaRenta = fv.idFacturaRenta AND cp.idCategoriaProducto = 4 "+extra;
        String SQLTematicos = "SELECT SUM(dfv.cantidad) FROM tbDetalleFacturaRenta dfv, tbCategoriaProducto cp, tbProducto p, tbFacturaRenta fv WHERE dfv.codigoProducto = p.codigoProducto AND cp.idCategoriaProducto = p.idCategoriaProducto AND dfv.idFacturaRenta = fv.idFacturaRenta AND cp.idCategoriaProducto = 5 "+extra;
        try {
            //Preparación de las sentencias
            PreparedStatement SELECT1 = Conect.prepareStatement(SQLDecoracion);
            PreparedStatement SELECT2 = Conect.prepareStatement(SQLAccesorio);
            PreparedStatement SELECT3 = Conect.prepareStatement(SQLEquipo);
            PreparedStatement SELECT4 = Conect.prepareStatement(SQLAudio);
            PreparedStatement SELECT5 = Conect.prepareStatement(SQLTematicos);
            //Se guardan los datos de las ejecuciones
            ResultSet Decoracion = SELECT1.executeQuery();
            ResultSet Accesorios = SELECT2.executeQuery();
            ResultSet Equipo = SELECT3.executeQuery();
            ResultSet Audio = SELECT4.executeQuery();
            ResultSet Tematicos = SELECT5.executeQuery();
            //Se guardan los datos en el vector, si no obtiene nada se le coloca 0
            if (Decoracion != null && Decoracion.next()) {
                datos[0] = Decoracion.getInt(1);
            } else {
                datos[0] = 0;
            }
            if (Accesorios != null && Accesorios.next()) {
                datos[1] = Accesorios.getInt(1);
            } else {
                datos[1] = 0;
            }
            if (Equipo != null && Equipo.next()) {
                datos[2] = Equipo.getInt(1);
            } else {
                datos[2] = 0;
            }
            if (Audio != null && Audio.next()) {
                datos[3] = Audio.getInt(1);
            } else {
                datos[3] = 0;
            }
            if (Tematicos != null && Tematicos.next()) {
                datos[4] = Tematicos.getInt(1);
            } else {
                datos[4] = 0;
            }

        } catch (Exception e) {
            System.out.print("Modelo: " + e+"\n");
        }
        //Se devuelve el vector con las cantidades  
        return datos;
    }
    
    //Método que devuelve la sumatoria de todos los monto totales de la venta
    public double totalVenta(String[] datos) {
        //Cantidad total
        double total = 1;
        //Variables que alterarán la sentencia
        String Monto, tabla, extra;
        try {
            //Se cargan las modificaciones
            Monto = datos[1];
            tabla = datos[0];
            extra = datos[2];
            //Sentencia
            String SQL = "SELECT SUM(" + Monto + ") FROM " + tabla+" "+extra;
            //Se prepara la sentencia
            PreparedStatement SELECT = Conect.prepareStatement(SQL);
            //Se guardan los datos de la ejecución
            ResultSet Datos = SELECT.executeQuery();
            while (Datos.next()) {
                //Se guardan la suma de los montos obtenidos durante la consulta
                total = Datos.getDouble(1);
                //Si no retorna nada se le asigna 1
                if (total==0.0) {
                    total=1;
                }
            }
        } catch (Exception e) {
            System.out.print("Total : " + e);
        }
        //Se retorna la cantidad
        return total;
    }
    
    //Método que carga los datos de los empleados
    public ArrayList<Object[]> empleados(String[] datos) {
        //Array que carga todos los empleados
        ArrayList<Object[]> empleado = new ArrayList<Object[]>();
        //Variables que alteran la sentencia
        String Monto, tabla, extra;
        try {
            //Se cargan los datos de la sentencia
            Monto = datos[1];
            tabla = datos[0];
            //Define si se va a agregar un rango o fecha
            extra = datos[2];
            //Sentencia
            String sentencia = "SELECT E.nombreEmpleado, FV." + Monto + "  FROM tbEmpleado E, " + tabla + " FV WHERE E.idEmpleado = FV.idEmpleado " + extra;
            //Se prepara la sentencia
            PreparedStatement SELECT = Conect.prepareStatement(sentencia);
            //Se guardan lso datos de la ejecución
            ResultSet Obtener = SELECT.executeQuery();
            //Se va revisando si contiene datos
            while (Obtener.next()) {
                //Se declara el vector de objetos
                //Se obtiene la cantidad vendida
                Object[] Datos = new Object[2];
                //Se obtiene el nombre del empleado
                Datos[0] = Obtener.getString(1);
                double cantidad = Obtener.getDouble(2);
                //Se guarda la cantidad
                Datos[1] = cantidad;
                boolean add = empleado.add(Datos);
            }
            //Se guardan los datos dentro del vector
            for (int i = 0; i < empleado.size(); i++) {
                Object[] datosw = empleado.get(i);
            }
            
        } catch (Exception e) {
            System.out.print("empleados" + e.toString());
        }
        //Se retorna el Array de vectores de objetos
        return empleado;
    }
    //Método que devuelve la cantidad total de todos los empleados
    public double TotalVentaEmpleados(String codigo) {
        //Cantidad total
        double ventas = 0.0;
        try {
            //Sentencia
            String SQL = "SELECT SUM(MontoTotalV) FROM tbFacturaVenta WHERE idEmpleado=?";
            //Preparación de la sentencia
            PreparedStatement SELECT = Conect.prepareStatement(SQL);
            //Se guardan los datos de la ejecución
            ResultSet datos = SELECT.executeQuery();
            //Se verifican la existencia de datos
            while (datos.next()) {
                //Se guardan los datos
                ventas = datos.getDouble(1);
            }
        } catch (Exception e) {
            System.out.println("TotalVentaEmpleados" + e.toString());
        }
        //Se retorna la cantidad total
        return ventas;
    }
}
