/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Oliver
 */
public class ModeloDetalleRenta {

    private Connection Conect;

    public ModeloDetalleRenta() {
        //Se iguala la conexión con el resultado
        Conect = Conexion.Conexion();
    }

    //Método que obtiene todos el nombre y el codigo de los productos
    public ArrayList<Object[]> DatosProductos(){
        //Se crea el arreglo de objetos que se devolverá con los datos
    ArrayList<Object[]> lista = new ArrayList<>();
    try{
        //Sentencia
        String SQL = "SELECT nombreProducto, codigoProducto FROM tbProducto WHERE idTipoInventario = 2 AND cantidad > 0;";
        //Preparación de la sentencia
        PreparedStatement SELECT = Conect.prepareStatement(SQL);
        //Se obtienen los datos de le ejecución
        ResultSet Datos = SELECT.executeQuery();
        //Se agregan tantas filas como sean necesarias a la cantidad
        while(Datos.next()){
            //Se crea el arreglo de objetos para guardar los datos
            Object[] NC = new Object[2];
            NC[0] = Datos.getString(1); //Nombre del producto
            NC[1] = Datos.getString(2); //Codigo del producto
            lista.add(NC);
        }
    }catch(Exception e){
        System.out.println("Error en los producto"+e);
    }
    return lista;
}
    


    //Método que devuelve el modelo para los combobox
    public DefaultComboBoxModel Categorias() {
        //Modelo del combobox que se devolverá
        DefaultComboBoxModel producto = new DefaultComboBoxModel();
        try {
            //Sentencia
            String SQL = "SELECT nombreTipo FROM tbCategoriaProducto";
            //Se prepara la sentencia
            PreparedStatement Select = Conect.prepareStatement(SQL);
            //Se guardan los datos de la ejecuación
            ResultSet p = Select.executeQuery();
            //Se agrega un valor por defecto
            producto.addElement("Sin seleccionar");
            //Se verifica la cantidad máxima de los datos
            while (p.next()) {
                //Se agregan los datos al modelo
                producto.addElement(p.getString("nombreTipo"));
            }
        } catch (SQLException e) {
            //Si hay un problema se envía un error
            System.out.print("Error al cargar los producto> " + e.toString());
        }
        //Se retorna el modelo
        return producto;
    }

    //Método que devuelve todos los datos del producto a ingresar
    //Se utiliza el codigo del producto para buscar que será ingresado por el usuario
    public Object[] CargarDatos(String codigo) {
        //Se crea el objeto dónde se almacenará los datos
        Object[] datos = new Object[4];
        //Se crea una variable para verificar si se ha encontrado el producto
        boolean encontrar = true;
        ResultSet DatosExtra;
        try {
            //Se crea la sentencia
            String SQL = "SELECT precio, descuento, idCategoriaProducto FROM tbProducto WHERE codigoProducto=? AND idTipoInventario=2 AND cantidad >0";
            //Se prepara la sentencia
            PreparedStatement SELECT = Conect.prepareStatement(SQL);
            //Se añade el codigo a la sentencia
            SELECT.setString(1, codigo);
            //Se almacena los datos obtenidos de la ejecución
            DatosExtra = SELECT.executeQuery();
            //Se verifica que devuelva el dato
            if (DatosExtra.next()) {
                //Se guardan los datos
                datos[1] = DatosExtra.getDouble(1);
                datos[2] = DatosExtra.getInt(2);
                datos[3] = DatosExtra.getInt(3);
            } else {
                //Si no hay datos se encarga datos predefinidos
                datos[1] = '-';
                datos[2] = "-";
                datos[3] = 0;
            }
        } catch (SQLException e) {
            //Se envía un error si ocurre
            System.out.print("Error " + e.toString());
        }
        try {
            //Declara la variable dónde se guardarán los valores
            ResultSet Datos;
            //Se crea la sentencia
            String SQL = "SELECT codigoProducto FROM tbProducto WHERE idTipoInventario=2 AND cantidad > 0";
            //Se prepara la setencia
            PreparedStatement SELECT = Conect.prepareStatement(SQL);
            //Se guarda los datos de la ejecución
            Datos = SELECT.executeQuery();
            //Se crea un contador
            int ID = 0;
            //Se verifica que todavía hayan datos y que todavía no se haya encontrado el correcto
            while (Datos.next() && encontrar) {
                //Se incrementa el número de la busqueda
                ID++;
                //Se guarda el valor para compararlo si es el correcto
                String Codigillo = Datos.getString(1);
                //Se verifica que el valor sea el mismo y que todavía no se haya encontrado
                if (Codigillo.equals(codigo) && encontrar) {
                    //Si es así, se guarda que ya se ha encontrado 
                    encontrar = false;
                    //Se asigna en le objeto que se devolvera
                    datos[0] = ID;
                } else {
                    //Sino se dirá que es 0, para que seleccione el valor por defecto
                    datos[0] = 0;
                }
            }
        } catch (SQLException e) {
            //Si hay un error en la base de datos se devuelve un error
            System.out.print("Error al cargar el producto> " + e.toString());
        }
        //Se devuelve el objeto con todos los datos
        return datos;
    }

    //Método que carga los datos elegidos por el combobox
    public Object[] CargarDatosCMB(String codigo) {
        //Objeto que guardará todos los datos
        Object[] datos = new Object[4];
        try {
            //Se crea la sentencia
            String SQL = "SELECT codigoProducto, precio, descuento, idCategoriaProducto FROM tbProducto WHERE codigoProducto=?";
            //Se prepara la sentencia
            PreparedStatement SELECT = Conect.prepareStatement(SQL);
            //Se la inserta el codigo a la sentencias
            SELECT.setString(1, codigo);
            //Se guardan los datos de la ejecución
            ResultSet informacion = SELECT.executeQuery();
            //Se verifican la cantidad de filas
            if (informacion.next()) {
                //Se guardan los datos
                datos[0] = informacion.getString(1); //Codigo del producto
                datos[1] = informacion.getDouble(2); //Precio del producto
                datos[2] = informacion.getInt(3); //Descuento del producto
                datos[3] = informacion.getInt(4); //IDde la categoria
            }
        } catch (Exception e) {
            //Si hay un error se cargan datos predefinidos
            datos[0] = ""; //Codigo
            datos[1] = "-"; //Precio
            datos[2] = "-"; //Descuento
            datos[3] = 0; //ID
        }
        //Se retorna el objeto con lso datos
        return datos;
    }

    //Métododo que insertará los productos
    public int IDInsertar() {
        //Variable que contendrá el valor de la Factura
        int codigo = 0;
        //Creación de la sentencia
        String SQL = "SELECT idFacturaRenta FROM tbFacturaRenta WHERE idFacturaRenta = (SELECT MAX(idFacturaRenta) FROM tbFacturaRenta)";
        try {
            //Se prepara la sentencia
            PreparedStatement SELECT = Conect.prepareStatement(SQL);
            //Se guardan los datos de la ejecución
            ResultSet dataEn = SELECT.executeQuery();
            //Se verifican la existencia de la fila en la ejecuación
            while (dataEn.next()) {
                //Se guarda los codigo de la factura
                codigo = dataEn.getInt(1);
            }
        } catch (Exception e) {
            //Se envia un error si existe
            System.out.print(e.toString());
        }
        //Se devuelve el codigo de la última factura
        return codigo;
    }

    //Método que devuelve la confirmación de guardar los productos en la factura
    public boolean GuardarFactura(Object[] datos) {
        //Se crea la variable que contendrá el estado de las inserciones
        boolean completado = false;
        //Se crea la sentencia
        String SQL = "INSERT INTO tbDetalleFacturaRenta VALUES(?,?,?,?,?,?);";
        try {
            //Se prepara la sentencia
            PreparedStatement INSERT = Conect.prepareStatement(SQL);
            //Se insertan los datos a la sentencia
            for (int i = 0; i < datos.length; i++) {
                System.out.println("Datos modeloado> " + datos[i] + "\n");
            }
            INSERT.setInt(1, Integer.parseInt(String.valueOf(datos[0]))); //Cantidad
            INSERT.setDouble(2, Double.parseDouble(String.valueOf(datos[1]))); //Descuento
            INSERT.setDouble(3, Double.parseDouble(String.valueOf(datos[2]))); //CostoTotal
            INSERT.setString(4, String.valueOf(datos[3])); //codigoProducto
            INSERT.setInt(5, Integer.parseInt(String.valueOf(datos[4]))); //IDFactura
            INSERT.setInt(6, 1);//IdEstado
            //Se guarda el estado de la ejecución
            completado = INSERT.execute();
        } catch (Exception e) {
            //Si hay un error, entonces se muestra
            System.out.print("Modelo Detalle>" + e.toString());
        }
        //Se envía el estado
        return completado;
    }

    //Método que actualiza la factura con los datos encontraados
    public boolean ActualizarFactura(Object[] datos) {
        //Se crea la variable que devolverá el estado
        boolean estado = false;
        //Se crea la sentencia
        String SQL = "UPDATE tbFacturaRenta SET MontoTotalR = ?, adelantoPago = ?, vuelto =? WHERE idFacturaRenta=?";
        try {
            //Se prepara la sentencia
            PreparedStatement UPDATE = Conect.prepareStatement(SQL);
            //Se insertan los datos a la sentenica
            UPDATE.setDouble(1, Double.parseDouble(String.valueOf(datos[0]))); //MontoTotal
            UPDATE.setDouble(2, Double.parseDouble(String.valueOf(datos[1]))); //Adelanto
            UPDATE.setDouble(3, Double.parseDouble(String.valueOf(datos[2]))); //Vuelto
            UPDATE.setInt(4, Integer.parseInt(String.valueOf(datos[3]))); //IDFactura}}
            //Se obtiene el estado de la actualización
            estado = UPDATE.execute();
        } catch (Exception e) {
            //Si hay un error, entonces se muestra
            System.out.print("Modelo factura>" + e.toString());
        }
        //Se retorna el estado
        return estado;
    }

    //Métod que obtiene la cantidad del producto a agregar en el detalle de renta
    public int cantidadProducto(String codigo){
        //Variable que se devolvera
        int cant = 0;
        try {
            //Sentencia
            String SQL = "SELECT cantidad FROM tbProducto WHERE codigoProducto = ?;";
            //Preparación de la sentencia
            PreparedStatement SELECT = Conect.prepareStatement(SQL);
            //Se inserta el codigo del producto a seleccionar
            SELECT.setString(1, codigo);
            //Se ejecuta la sentencia
            ResultSet datos = SELECT.executeQuery();
            //Se verifica si obtuvo el valor
            while (datos.next()) {
                //Se agrega el valor obtenido a la variable que se obtendra
                cant = datos.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Cantidad encontrada: " + e);
        }
        return cant;
    }

    //Método que actualizara la cantidad restante del producto
    public boolean ActualizaProductosC(Object[] datos) {
        //Se obtiene el estado de la consulta
        boolean resultado = false;
        try {
            //Se obtiene la cantidad restante
            int cantidad = Integer.parseInt(datos[0].toString());
            //Se obtiene el codigo del producto a actualizar
            String codigo = datos[1].toString();
            //Se crea la sentencia
            String SQL = "UPDATE tbProducto SET cantidad = ? WHERE codigoProducto = ?;";
            //Se prepara la sentencia
            PreparedStatement UPDATE = Conect.prepareStatement(SQL);
            //Se inserta la cantidad a la sentencia
            UPDATE.setInt(1, cantidad);
            //Se inserta el codigo del producto a la sentencia
            UPDATE.setString(2, codigo);
            //Se obtiene el estado de la sentencia
            resultado = UPDATE.execute();
        } catch (Exception e) {
            //Error
            System.out.println("Problemna" + e);
        }
        //Se devuelve el estado
        return resultado;
    }
}
