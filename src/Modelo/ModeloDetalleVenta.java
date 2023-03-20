/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author pc
 */
public class ModeloDetalleVenta {

    private static ResultSet datosMenu;

    //Cargar combobox de producto
    public static ResultSet CargarProducto() {
        ResultSet res;
        try {
            //Se crea la consulta para seleccionar el tipo de empleado
            String sql = "SELECT nombreProducto from tbProducto where idTipoInventario = 1 AND cantidad > 0";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta
            res = cmdselect.executeQuery();
            System.out.println(res);
            return res;
        } catch (Exception e) {
            return res = null;
        } finally {
            try {
                //Cerramos la conexion
                Conexion.Conexion().close();
            } catch (Exception e) {

            }
        }
    }

    //Cargar combobox de id
    public static ResultSet CargarIdVenta() {
        ResultSet res;
        try {
            //Se crea la consulta para seleccionar el tipo de venta, el ultimo id
            String sql = "SELECT idFacturaVenta as idVenta FROM tbFacturaVenta WHERE idFacturaVenta = (SELECT MAX(idFacturaVenta) FROM tbFacturaVenta)";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta
            res = cmdselect.executeQuery();
            return res;
        } catch (Exception e) {
            return res = null;
        } finally {
            try {
                //Cerramos la conexion
                Conexion.Conexion().close();
            } catch (Exception e) {

            }
        }
    }

    //Para conseguir id de combobox de producto - sin usar Creo
    public ArrayList<String> ConseguirIdProducto() {
        //Creamos la consulta para seleccionar el codigo de producto
        String sql = "select codigoProducto from tbProducto where idTipoInventario=1 AND cantidad > 0";
        //Creamos un ArrayList
        ArrayList<String> arreglo = new ArrayList<String>();

        try {
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta 
            ResultSet cmdselect = cmdinsert.executeQuery();
            while (cmdselect.next()) {
                //Agregamos al arreglo
                arreglo.add(cmdselect.getString(1));
                System.out.println(cmdselect.getString(1));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //Retornamos el arreglo
        return arreglo;
    }

    //Para conseguir id de combobox de venta
    public ArrayList<Integer> ConseguirIdVenta() {
        //Creamos la consulta para seleccionar el id de factura de venta
        String sql = "SELECT idFacturaVenta  FROM tbFacturaVenta WHERE idFacturaVenta = (SELECT MAX(idFacturaVenta) FROM tbFacturaVenta);";
        ArrayList<Integer> arreglo = new ArrayList<Integer>();

        try {
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);
            //Ejecutamos la consulta 
            ResultSet cmdselect = cmdinsert.executeQuery();

            while (cmdselect.next()) {
                //Agregamos el id al arreglo
                arreglo.add(cmdselect.getInt(1));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //Se retorna el arreglo
        return arreglo;
    }

    public static ResultSet CargarTabla() {
        ResultSet res;
        try {
            //Creamos consulta para seleccionar campos de la tabla
            String sql = "select d.cantidad,d.descuento,d.costoTotalV,p.codigoProducto, v.idFacturaVenta from tbDetalleFacturaVenta d, tbProducto p, tbFacturaVenta v where p.codigoProducto = d.codigoProducto and v.idFacturaVenta = d.idFacturaVenta and  v.idFacturaVenta = (SELECT MAX(idFacturaVenta) FROM tbFacturaVenta)";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            //Ejecuto la consulta
            res = cmdselect.executeQuery();
            //Retorno la consulta
            return res;
        } catch (Exception e) {
            return res = null;
        } finally {
            try {
                //Se cierra la conexion
                Conexion.Conexion().close();
            } catch (Exception e) {

            }
        }
    }

    //método para guardar el ingreso de detalle de venta en la base de datos, creamos parametros
    public static boolean IngresarDetalleVenta(int Cantidad, Double Descuento, Double CostoTotalV, String codigoProducto, int idFacturaVenta) {
        //Craemos variable tiipo booleano llamada res
        boolean Insercion = false;
        try {//Realizar consulta INSERT
            String sql = "insert into tbDetalleFacturaVenta (cantidad,descuento,costoTotalV,codigoProducto,idFacturaVenta ) VALUES (?,?,?,?,?)";
            //pide importar clase Prepared StatementINSERT INTO tbEmpresa (nombreEmpresa, nit, anioFundacion, telefonoEmpresa, direccionEmpresa, idPais) VALUES (?,?,?,?,?,?)
            PreparedStatement cmdinsert = Conexion.Conexion().prepareStatement(sql);

            cmdinsert.setDouble(1, Cantidad);//Guardamos cantidad de productos a llevar
            cmdinsert.setDouble(2, Descuento);//Guardamos decuento
            cmdinsert.setDouble(3, CostoTotalV);//Guardamos costo total de venta
            cmdinsert.setString(4, codigoProducto);//Guardamos id de producto
            cmdinsert.setInt(5, idFacturaVenta);//Guardamos id de factura vennta

            if (!cmdinsert.execute()) {
                Insercion = true;
            }
            cmdinsert.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Insercion;

        } finally {
            try {
                //Cerramos conexion
                Conexion.Conexion().close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        //Retornamos insercion
        return Insercion;
    }

    //Creamos metodo de objeto para seleccionar todos los datos de la tabla y guardarlos  
    public static boolean CargarDatosMenu(int Cantidad, Double Descuento, Double CostoTotalV, String codigoProducto, int idFacturaVenta, String Identificador) {
        boolean Cargar = false;
        try {
            //Creamos consulta
            String SQL = "select * from tbDetalleFacturaVenta where idDetalleFacturaV=?";
            PreparedStatement ejecucion = Conexion.Conexion().prepareStatement(SQL);
            ejecucion.setString(1, Identificador);
            //Ejecutamos consulta
            setDatosMenu(ejecucion.executeQuery());
            //Guardamos datos en objeto
            if (getDatosMenu().next()) {

                Cantidad = getDatosMenu().getInt(2);//Obtenemos la cantidad
                Descuento = getDatosMenu().getDouble(3);//Obtenemos descuento
                CostoTotalV = getDatosMenu().getDouble(4);//Obtenemos el costo total de venta
                codigoProducto = getDatosMenu().getString(5);//Obtenemos el codigo de producto
                idFacturaVenta = getDatosMenu().getInt(6);//Obtenemos el id de la factura

            } else {
                System.out.print("ERROR INESPERADO");
            }
            if (!ejecucion.execute()) {
                Cargar = true;
            }

            ejecucion.close();
        } catch (Exception e) {
            System.out.println(e);
            return Cargar;

        } finally {
            try {
                //Cerramos la conexion
                Conexion.Conexion().close();
            } catch (Exception e) {
            }
        }
        //Retornamos cargar
        return Cargar;

    }

    //Cargamos descuento y costo de producto
    public static Object[] CargarDescuentoPrecio(String codigo) {
        //Creamos objeto de longitud 3
        Object[] datos = new Object[3];
        try {
            //Creamos consulta
            String SQL = "SELECT codigoProducto, precio, descuento FROM tbProducto WHERE codigoProducto=? AND cantidad > 0";
            PreparedStatement ejecucion = Conexion.Conexion().prepareStatement(SQL);
            ejecucion.setString(1, codigo);
            //Se ejecuta la consulta
            ResultSet DatosMenu = ejecucion.executeQuery();
            //Se guardan los datos
            if (DatosMenu.next()) {
                datos[0] = DatosMenu.getString(1);//codigo
                datos[1] = DatosMenu.getDouble(2);//precio
                datos[2] = DatosMenu.getInt(3);//decuento
            }

        } catch (Exception e) {
            System.out.print(e.toString());
            datos[0] = "-";
            datos[1] = "-";
            datos[2] = 0;

        }
        //Se retornan datos
        return datos;
    }

    //Cargamos los codigos de producto
    public ResultSet CargarCodigoProductos() {
        ResultSet productos = null;
        //Creamos consulta
        String SQL = "SELECT codigoProducto FROM tbProducto where idTipoInventario=1 AND cantidad > 0";
        try {
            PreparedStatement Select = Conexion.Conexion().prepareStatement(SQL);
            //Ejecutamos consulta
            productos = Select.executeQuery();
        } catch (SQLException e) {
            System.out.print("Error al cargar los productos> " + e.toString());
        }
        //retornamos productos
        return productos;
    }

    //Se crea metodo de ArrayList para guardar los codigos de producto
    public ArrayList<String> IDProductos() {
        //Creamos un ArrayList
        ArrayList<String> codigo = new ArrayList<>();
        try {
            ResultSet Codigos = CargarCodigoProductos();
            while (Codigos.next()) {
                //Agregamos los codigos de productos en codigo
                codigo.add(Codigos.getString(1));
            }
        } catch (SQLException e) {
            System.out.print("Error al cargar las categorias> " + e.toString());
        }
        //Retornamos codigo
        return codigo;
    }

    //Cargamos cantidades de productos
    public ResultSet CargarCantidades() {
        ResultSet productos = null;
        //Creamos consulta que seleccione las cantidades
        String SQL = "select cantidad from tbProducto where codigoProducto=? AND cantidad > 0";
        try {
            PreparedStatement Select = Conexion.Conexion().prepareStatement(SQL);
            //Ejecutamos codigo
            productos = Select.executeQuery();
        } catch (SQLException e) {
            System.out.print("Error al cargar las cantidades " + e.toString());
        }
        //Retornamos productos
        return productos;
    }
    //Creamos metodo de ArryList para almacenar las cantidades

    public ArrayList<Integer> CantidadesP() {
        //Creamos un ArrayList
        ArrayList<Integer> cantidad = new ArrayList<>();
        try {
            ResultSet Codigos = CargarCodigoProductos();
            while (Codigos.next()) {
                //Agregamos las cantidades en cantidad
                cantidad.add(Codigos.getInt(1));
            }
        } catch (SQLException e) {
            System.out.print("Error al cargar las cantidades " + e.toString());
        }
        //Retornamos cantidad
        return cantidad;

    }
    //Creamos metodo de objeto para cargar las cantidade

    public static Object[] CargarCantidad(String codigo) {
        Object[] datos = new Object[1];
        try {
            //Se crea consulta
            String SQL = "select cantidad from tbProducto where codigoProducto=?";
            PreparedStatement ejecucion = Conexion.Conexion().prepareStatement(SQL);
            ejecucion.setString(1, codigo);
            //Ejecutamos consulta
            ResultSet DatosMenu = ejecucion.executeQuery();
            if (DatosMenu.next()) {

                datos[0] = DatosMenu.getString(1);//Cantidad

            }

        } catch (Exception e) {
            System.out.print(e.toString());

        }
        //Retornamos datos
        return datos;
    }
    //Actualizamos inventario

    public static boolean ActualizarInventario(double cantidad, String codigoProducto) {
        boolean res = false;
        try { //Realizar consulta UPDATE
            String sql = "update tbProducto set cantidad = ? where codigoProducto = ?";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            //Lenar los parámetros de la clase, se coloca en el orden de la consulta
            // cmd.setInt(0, codigoP);

            cmd.setDouble(1, cantidad);//Guardamos cantidad
            cmd.setString(2, codigoProducto);//Guardamos codigo de producto

            //Si da error devuelve 1, caso contrario 
            //Tomar en cuenta que el ! es negación
            if (!cmd.execute()) {
                res = true;
            }
            //cerrando conexion
            cmd.close();
            Conexion.Conexion().close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Retorna res
        return res;
    }

    //Método que devuelve todos los datos del producto a ingresar
    //Se utiliza el codigo del producto para buscar que será ingresado por el usuario    
    public static Object[] CargarDatos(String codigo) {
        //Se crea el objeto dónde se almacenará los datos
        Object[] datos = new Object[4];
        //Se crea una variable para verificar si se ha encontrado el producto
        boolean encontrar = true;
        ResultSet DatosExtra;
        try {//Se crea la sentencia
            String SQL = "SELECT precio, descuento FROM tbProducto WHERE codigoProducto=? AND idTipoInventario=1 AND cantidad >0";
            //Se prepara la sentencia
            PreparedStatement SELECT = Conexion.Conexion().prepareStatement(SQL);
            //Se añade el codigo a la sentencia
            SELECT.setString(1, codigo);
            //Se almacena los datos obtenidos de la ejecución
            DatosExtra = SELECT.executeQuery();
            //Se verifica que devuelva el dato   
            if (DatosExtra.next()) {
                datos[1] = DatosExtra.getDouble(1);
                datos[2] = DatosExtra.getInt(2);

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
        try{
            //Declara la variable dónde se guardarán los valores
            ResultSet Datos;
            //Se crea la sentencia
            String SQL = "SELECT codigoProducto FROM tbProducto WHERE idTipoInventario=1 AND cantidad>0";
            //Se prepara la setencia
            PreparedStatement SELECT = Conexion.Conexion().prepareStatement(SQL);
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

        }
        catch (SQLException e) {
            //Si hay un error en la base de datos se devuelve un error
            System.out.print("Error al cargar el producto> " + e.toString());
        }
        //Se devuelve el objeto con todos los datos
        return datos;
    }

    /**
     * @return the datosMenu
     */
    public static ResultSet getDatosMenu() {
        return datosMenu;
    }

    /**
     * @param aDatosMenu the datosMenu to set
     */
    public static void setDatosMenu(ResultSet aDatosMenu) {
        datosMenu = aDatosMenu;
    }

}
