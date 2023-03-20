package Modelo;

import Modelo.Conexion;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 * @author Oliver
 */
public class ModeloInventario {

    private Connection Conect;

    public ModeloInventario() {
        //Establecemos la conexion
        Conexion claseConectar = new Conexion();
        Conect = claseConectar.Conexion();
    }

    //Método que convierte los datos a un modelo de combobox
    public DefaultComboBoxModel CargarEstadoscmb() {
        //Creación del modelo que se retornará
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        try {
            String sql = "SELECT nombreTipo from tbEstadoProducto";
            //Creación del nuevo Prepared Statement
            PreparedStatement cmd = Conect.prepareStatement(sql);
            //Guarda los datos obtenidos en un ResultSet
            ResultSet res = cmd.executeQuery();
            //Si existe algo, entonces se llenará por la columna de la base de datos
            while (res.next()) {
                //Se agrega a la lista
                lista.addElement(res.getString("nombreTipo"));
            }

        } catch (Exception e) {
            System.err.print("Error en el modelo: " + e.getMessage());
        }
        //Retorn del modelo
        return lista;
    }

    //Método que convierte los datos a un modelo de combobox
    public DefaultComboBoxModel CargarTipoInventariocmb() {
        //Modelo de combobox que se devolverá
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        try {
            //Creación de la consulta
            String sql = "SELECT NombreTipo from tbTipoInventario";
            //Creación del nuevo Prepared Statement
            PreparedStatement cmd = Conect.prepareStatement(sql);
            //Se guardan los datos en un resultSet
            ResultSet res = cmd.executeQuery();
            //Se verifica si el resultSet contiene datos
            while (res.next()) {
                //Se agregan los datos al modelo del combobox
                lista.addElement(res.getString("nombreTipo"));
            }
        } catch (Exception e) {
            //Si se da un error, se imprime
            System.err.print("Error en el modelo: " + e.getMessage());
        }
        //Se retorna el modelo del combobox
        return lista;
    }

    public DefaultComboBoxModel CargarCategoriascmb() {
        //Se crear el model que se retornará
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        try {
            //Se crea la consulta
            String sql = "SELECT nombreTipo from tbCategoriaProducto";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conect.prepareStatement(sql);
            //Se guardan los datos 
            ResultSet res = cmd.executeQuery();
            //Se verifican si la consulta trajo datos de regreso
            while (res.next()) {
                //Se agregan los datos al modelo del combobox
                lista.addElement(res.getString("nombreTipo"));
            }
        } catch (Exception e) {
            //Si se da un error, se envia el 
            System.err.print("Error en el modelo: " + e.getMessage());
        }
        return lista;
    }

    public DefaultComboBoxModel CargarTamaniocmb() {
        //Se crea el modelo del combobox que se retornará
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        try {
            //Consulta para cargar los datos
            String sql = "SELECT tamaño from tbtamanio";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conect.prepareStatement(sql);
            //Se guarda la consulta en un resultSet
            ResultSet res = cmd.executeQuery();
            //Se verifican si hay datos en la consulta
            while (res.next()) {
                //Se cargan los datos en el modelo del combobox
                lista.addElement(res.getString("tamaño"));
            }
        } catch (Exception e) {
            //Si hay un error se envia el error
            System.err.print("Error en el modelo: " + e.getMessage());
        }
        return lista;
    }

    public DefaultComboBoxModel CargarDistribuidorcmb() {
        //Se crea el model que se retornará
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        try {
            //Se crea la consulta
            String sql = "SELECT nombreDistribuidor from tbDistribuidor";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conect.prepareStatement(sql);
            //Se cargan los datos en un resultSet
            ResultSet res = cmd.executeQuery();
            //Se verifican si hay datos provenientes de las consultas
            while (res.next()) {
                //Se agregan los datos al modelo del combobox
                lista.addElement(res.getString("nombreDistribuidor"));
            }
        } catch (Exception e) {
            //Se envi el error si es que este se da
            System.err.print("Error en el modelo: " + e.getMessage());
        }
        //Se retorna el modelo
        return lista;
    }

    //Método que se encarga de llenar los colores
    public DefaultComboBoxModel CargarColorcmb() {
        //Ccreación del modelo
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        try {
            //Creación de la consulta
            String sql = "SELECT nombreColor from tbColor";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conect.prepareStatement(sql);
            //Se cargan los datos en un resultSet
            ResultSet res = cmd.executeQuery();
            //Se verifica si existen datos dentor del resultset
            while (res.next()) {
                //se agregan los datos dentro del modelo
                lista.addElement(res.getString("nombreColor"));
            }
        } catch (Exception e) {
            //Si existe, se retorna el error
            System.err.print("Error en el modelo: " + e.getMessage());
        }

        //Se retorna el modelo
        return lista;
    }

    //Método que carga las marcas
    public DefaultComboBoxModel CargarMarcacmb() {
        //Se crea el modelo que se retornará
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        try {
            //Se crea la consutla
            String sql = "SELECT nombreMarca from tbMarca";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conect.prepareStatement(sql);
            //Se guardan los datos en un resultSet
            ResultSet res = cmd.executeQuery();
            //Se verifica si hay datos dentro del ResultSet
            while (res.next()) {
                //Se agrega al modelo
                lista.addElement(res.getString("nombreMarca"));
            }
        } catch (Exception e) {
            //Si hay unn error, se envía el mensaje
            System.err.print("Error en el modelo: " + e.getMessage());
        }
        //Se retorna el modelo
        return lista;
    }

    //Método que carga el tipo del inventario
    public DefaultComboBoxModel CargarTipocmb() {
        //Se crea el el modelo
        DefaultComboBoxModel lista = new DefaultComboBoxModel();;
        try {
            //Se crea la consulta
            String sql = "SELECT nombreTipo from tbTipoProducto";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conect.prepareStatement(sql);
            //Se guardan los datos dentro de un ResultSet
            ResultSet res = cmd.executeQuery();
            //Se verifican la existenci de datos
            while (res.next()) {
                //Se agregan los datos al modelo
                lista.addElement(res.getString("nombreTipo"));
            }
        } catch (Exception e) {
            //Se envía el error, cuando sea necesario
            System.err.print("Error en el modelo: " + e.getMessage());
        }
        //Retirna el modelo
        return lista;
    }

    //Método que carga los materiales    
    public DefaultComboBoxModel CargarMaterialcmb() {
        //Se crea el modelo
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        try {
            //Se crea la consulta
            String sql = "SELECT materialProducto from tbMaterialProducto";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conect.prepareStatement(sql);
            //Se guardan los datos en un resultSet
            ResultSet res = cmd.executeQuery();
            //Se verifican los si existen datos
            while (res.next()) {
                //Se guardan los datos en el modelo
                lista.addElement(res.getString("materialProducto"));
            }
        } catch (Exception e) {
            //Si hay error se retorna el mensaje
            System.err.print("Error en el modelo: " + e.getMessage());
        }
        //Retorna el modelo
        return lista;
    }

    //metodo para guardar
    public boolean guardarNuevoProducto(Object[] datos) {
        //Se crea el estaod de la actualización
        boolean res = false;
        try {
            //Realizar consulta INSERT
            String sql = "INSERT INTO tbProducto VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //se pasan por referencia por seguridad
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conect.prepareStatement(sql);
            //Lenar los parametros de la clase, se coloca en el orden de la tabla
            cmd.setString(1, String.valueOf(datos[0])); //Codigo del producto
            cmd.setString(2, String.valueOf(datos[1])); //Nombre del producto
            cmd.setDouble(3, Double.parseDouble(String.valueOf(datos[2]))); //Precio
            cmd.setInt(4, Integer.parseInt(String.valueOf(datos[3]))); //Cantidad
            FileInputStream fi = null;
            String ruta = String.valueOf(datos[4]); //Imagen
            File file = new File(ruta);
            fi = new FileInputStream(file);
            cmd.setBinaryStream(5, fi);
            cmd.setDate(6, Date.valueOf(String.valueOf(datos[5]))); //Fecha entrega
            cmd.setDate(7, Date.valueOf(String.valueOf(datos[6]))); //Fecha inicio
            cmd.setString(8, String.valueOf(datos[7])); //Descripción
            cmd.setInt(9, Integer.parseInt(String.valueOf(datos[8]))); //IDTamaño
            cmd.setInt(10, Integer.parseInt(String.valueOf(datos[9]))); //IDColor
            cmd.setInt(11, Integer.parseInt(String.valueOf(datos[10]))); //IDMarca
            cmd.setInt(12, Integer.parseInt(String.valueOf(datos[11]))); //IDDistribuidor
            cmd.setInt(13, Integer.parseInt(String.valueOf(datos[12]))); //IDTipo
            cmd.setInt(14, Integer.parseInt(String.valueOf(datos[13]))); //IDTipoInventario
            cmd.setInt(15, Integer.parseInt(String.valueOf(datos[14]))); //IDEstado
            cmd.setInt(16, Integer.parseInt(String.valueOf(datos[15]))); //IDCategoria
            cmd.setInt(17, Integer.parseInt(String.valueOf(datos[16]))); //IDMaterial
            cmd.setInt(18, Integer.parseInt(String.valueOf(datos[17]))); //Desciuento
            //Se verifica que se logre ejecutar correctamente
            if (!cmd.execute()) {
                //Se coloca el true si se inserto correctamente
                res = true;
            }
        } catch (Exception e) {
            //Se envia el error en caso se de
            System.out.println(e.toString());
        }
        //Se devueleve el objeto
        return res;
    }

    //Método que actualiza el producto
    public boolean ActualizarProducto(Object[] datos) {
        //Se crea el estado de la actulización
        boolean res = false;
        //Se obtiene la seleccion de la modalidad
        int eleccion = Integer.parseInt(String.valueOf(datos[18]));
        try { //Realizar consulta INSERT
            String sqlC = "UPDATE tbProducto set nombreProducto=?,precio=?,cantidad=?,imagenProducto=?,fechaEntrega=?,fechaInicioVentas=?,descripcion=?,idTamaño=?,idColor=?,idMarca=?,idDistribuidor=?,idTipo=?,idTipoInventario=?,idEstadoProducto=?,idCategoriaProducto=?,idMaterialProducto=?, descuento=? where codigoProducto =?"; //se pasan por referencia por seguridad
            String sqlS = "UPDATE tbProducto set nombreProducto=?,precio=?,cantidad=?,fechaEntrega=?,fechaInicioVentas=?,descripcion=?,idTamaño=?,idColor=?,idMarca=?,idDistribuidor=?,idTipo=?,idTipoInventario=?,idEstadoProducto=?,idCategoriaProducto=?,idMaterialProducto=?, descuento=? where codigoProducto =?";
            //Se instancia la sentencia
            PreparedStatement cmd;
            //Se averigua la opción
            if (eleccion == 1) {
                //pide importar clase Prepared Statement
                cmd = Conect.prepareStatement(sqlS);
                //Lenar los parametros de la clase, se coloca en el orden de la tabla
                cmd.setString(1, String.valueOf(datos[1])); //NombreProducto
                cmd.setDouble(2, Double.parseDouble(String.valueOf(datos[2]))); //¨Precio
                cmd.setInt(3, Integer.parseInt(String.valueOf(datos[3]))); //Cantidad
                cmd.setDate(4, Date.valueOf(String.valueOf(datos[5]))); //FechaEntrega
                cmd.setDate(5, Date.valueOf(String.valueOf(datos[6]))); //Fecha fin
                cmd.setString(6, String.valueOf(datos[7])); // Descripción 
                cmd.setInt(7, Integer.parseInt(String.valueOf(datos[8]))); //IDTamaño 
                cmd.setInt(8, Integer.parseInt(String.valueOf(datos[9]))); //IDColor 
                cmd.setInt(9, Integer.parseInt(String.valueOf(datos[10]))); //IDMarca
                cmd.setInt(10, Integer.parseInt(String.valueOf(datos[12]))); //IDDistribuidor
                cmd.setInt(11, Integer.parseInt(String.valueOf(datos[12]))); //IDTipo
                cmd.setInt(12, Integer.parseInt(String.valueOf(datos[13]))); //IDTipoInventario 
                cmd.setInt(13, Integer.parseInt(String.valueOf(datos[14]))); //IDEstado 
                cmd.setInt(14, Integer.parseInt(String.valueOf(datos[15]))); //IDCategoria 
                cmd.setInt(15, Integer.parseInt(String.valueOf(datos[16]))); //IDMaterial 
                cmd.setInt(16, Integer.parseInt(String.valueOf(datos[17]))); //Descuento
                cmd.setString(17, String.valueOf(datos[0])); //Identificador
            } else {
                //pide importar clase Prepared Statement
                cmd = Conect.prepareStatement(sqlC);
                FileInputStream fi = null;
                String ruta = String.valueOf(datos[4]); //Imagen
                File file = new File(ruta);
                fi = new FileInputStream(file);
                cmd.setBinaryStream(4, fi);
                //Lenar los parametros de la clase, se coloca en el orden de la tabla
                cmd.setString(1, String.valueOf(datos[1])); //NombreProducto
                cmd.setDouble(2, Double.parseDouble(String.valueOf(datos[2]))); //¨Precio
                cmd.setInt(3, Integer.parseInt(String.valueOf(datos[3]))); //Cantidad
                cmd.setDate(5, Date.valueOf(String.valueOf(datos[5]))); //FechaEntrega
                cmd.setDate(6, Date.valueOf(String.valueOf(datos[6]))); //Fecha fin
                cmd.setString(7, String.valueOf(datos[7])); // Descripción 
                cmd.setInt(8, Integer.parseInt(String.valueOf(datos[8]))); //IDTamaño 
                cmd.setInt(9, Integer.parseInt(String.valueOf(datos[9]))); //IDColor 
                cmd.setInt(10, Integer.parseInt(String.valueOf(datos[10]))); //IDMarca
                cmd.setInt(11, Integer.parseInt(String.valueOf(datos[12]))); //IDDistribuidor
                cmd.setInt(12, Integer.parseInt(String.valueOf(datos[12]))); //IDTipo
                cmd.setInt(13, Integer.parseInt(String.valueOf(datos[13]))); //IDTipoInventario 
                cmd.setInt(14, Integer.parseInt(String.valueOf(datos[14]))); //IDEstado 
                cmd.setInt(15, Integer.parseInt(String.valueOf(datos[15]))); //IDCategoria 
                cmd.setInt(16, Integer.parseInt(String.valueOf(datos[16]))); //IDMaterial 
                cmd.setInt(17, Integer.parseInt(String.valueOf(datos[17]))); //Descuento
                cmd.setString(18, String.valueOf(datos[0])); //Identificador
            }

            if (!cmd.execute()) {
                //Si se ejecuta, se devuelve un true
                res = true;
            }
        } catch (Exception e) {
            //Se envía el error
            System.out.println(e.toString());
        }
        //Se devuelve el objeto
        return res;
    }

    public boolean eliminarProducto(String Identificador) {
        //Estado de la eliminación
        boolean estado = false;
        try {
            //Se crea la sentencia
            String SQL = "UPDATE tbProducto SET idTipo=4 WHERE codigoProducto=?";
            //Se le prepara
            PreparedStatement ejecutar = Conect.prepareStatement(SQL);
            //Se le agrega el identificador para detectar el producto a eliminar
            ejecutar.setString(1, Identificador);
            //Se le aplica si fue correcta o no
            estado = ejecutar.execute();
        } catch (Exception e) {
            //Error si es que lo hay
            System.out.print("Error al eliminar" + e.toString());
        }
        //Se devuelve el estado
        return estado;
    }

    //Método que carga los detos en el menú
    public static Object[] CargarDatosMenu(String Identificador) {
        //Se crea el objeto dónde se guardarán todos los datos
        Object[] datos = new Object[18];
        try {
            //Se crea la consulta
            String SQL = "SELECT p.codigoProducto, p.nombreProducto, p.precio, p.cantidad, p.imagenProducto, p.fechaEntrega, p.fechaInicioVentas, p.descripcion, t.tamaño, c.nombreColor, m.nombreMarca, d.nombreDistribuidor, tp.nombreTipo, Ti.NombreTipo, ep.nombreTipo, cp.nombreTipo, mp.materialProducto, p.descuento from tbproducto p, tbtamanio t, tbColor c, tbMarca m, tbDistribuidor d, tbTipoProducto tp, tbTipoInventario Ti, tbEstadoProducto ep, tbCategoriaProducto cp, tbMaterialProducto mp where p.idCategoriaProducto = cp.idCategoriaProducto AND p.idColor = c.idColor AND p.idDistribuidor = d.idDistribuidor AND p.idEstadoProducto = ep.idEstadoProducto AND p.idMarca = m.idMarca AND p.idMaterialProducto = mp.idMaterialProducto AND p.idTamaño = t.idTamaño AND p.idTipo = tp.idTipo AND Ti.idTipoInventario = p.idTipoInventario AND p.codigoProducto = ?";
            //Se ejecuta
            PreparedStatement ejecucion = Conexion.Conexion().prepareStatement(SQL);
            //Se le asgina el codigo para cargar los datos
            ejecucion.setString(1, Identificador);
            //Se guradan los datos
            ResultSet DatosMenu = ejecucion.executeQuery();
            //Se verifican sin exstien atos
            if (DatosMenu.next()) {
                datos[0] = DatosMenu.getString(1); //Codigo producto
                datos[1] = DatosMenu.getString(2); //Nombre producto
                datos[2] = DatosMenu.getDouble(3); //Precio
                datos[3] = DatosMenu.getInt(4); //cantidad
                Blob blob = DatosMenu.getBlob(5);
                byte[] imagen = (blob.getBytes(1, (int) blob.length()));
                datos[4] = imagen; //Imagen
                datos[5] = DatosMenu.getDate(6); //Fecha Entrega
                datos[6] = DatosMenu.getDate(7); //Fecha Inicio 
                datos[7] = DatosMenu.getString(8); //Descripcion
                datos[8] = DatosMenu.getString(9); //Tamaño
                datos[9] = DatosMenu.getString(10); //Color
                datos[10] = DatosMenu.getString(11); //Marca
                datos[11] = DatosMenu.getString(12); //Distribuidor
                datos[12] = DatosMenu.getString(13); //Tipo producto
                datos[13] = DatosMenu.getString(14); //Tipo inventario
                datos[14] = DatosMenu.getString(15); //Estado producto
                datos[15] = DatosMenu.getString(16); //Categoria
                datos[16] = DatosMenu.getString(17); //Material
                datos[17] = DatosMenu.getInt(18); //Descuento
            } else {
                //Se envia el error si no se logra insertar
                System.out.print("ERROR INESPERADO");
            }
        } catch (Exception e) {
            //Error en dado caso ocurra
            System.out.print(e.toString());
        }
        //Se retorna el objeto
        return datos;
    }

    //Método que carga los ID de renta
    public ArrayList<String> CargasID() {
        //Declaración del ArrayList que se devolverá
        ArrayList<String> lista = new ArrayList<String>();
        try {
            //Se crea la sentencia
            String SQL = "SELECT codigoProducto from tbProducto WHERE idTipoInventario=2 AND idTipo BETWEEN 1 AND 3";
            //Se prepara la sentencia
            PreparedStatement cmd = Conect.prepareStatement(SQL);
            //Se ejecuta
            ResultSet Codigos = cmd.executeQuery();
            //Se obtienen los datos meta del ResultSet
            ResultSetMetaData TablaCodigos = Codigos.getMetaData();
            //Se revisa que estos contengan datos
            while (Codigos.next()) {
                for (int i = 0; i < TablaCodigos.getColumnCount(); i++) {
                    //Se crea una variable dónde se guardaran los datos
                    String fila = Codigos.getString(i + 1);
                    //Se agrega al ArrayList
                    lista.add(fila);
                }
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        //Se retorna el ArrayList
        return lista;
    }

    //Método que devuele los ID de la venta
    public ArrayList<String> CargasIDVenta() {
        //Se declara el ArrayList
        ArrayList<String> lista = new ArrayList<String>();
        try {
            //Se crea la sentencia
            String SQL = "SELECT codigoProducto from tbProducto WHERE idTipoInventario=1 AND idTipo BETWEEN 1 AND 3";
            //Preparación
            PreparedStatement cmd = Conect.prepareStatement(SQL);
            //Se guarda la ejecución en un ResultSet
            ResultSet CodigosVenta = cmd.executeQuery();
            //Se extrae los datos meta
            ResultSetMetaData TablaCodigosVenta = CodigosVenta.getMetaData();
            //Se declara un contador
            int contador = 0;
            //Se revisa si el ResultSet contiene datos
            while (CodigosVenta.next()) {
                for (int i = 0; i < TablaCodigosVenta.getColumnCount(); i++) {
                    //Se guarda el dato
                    String fila = CodigosVenta.getString(i + 1);
                    //Se añada al arrayList
                    lista.add(fila);
                }

            }
        } catch (Exception e) {
            //Se imprime el error
            System.out.print(e.toString());
        }
        //Se retorna al ArrayList
        return lista;
    }

    public static Object[] CargarDatosModificar(String Identificador) {
        //Se crea el objeto dónde se guardaran los datos
        Object[] datos = new Object[18];
        try {
            //Se crea la sentencia
            String SQL = "Select * from tbProducto where codigoProducto=?";
            //Preparar
            PreparedStatement ejecucion = Conexion.Conexion().prepareStatement(SQL);
            //Se le ingresa el identificador
            ejecucion.setString(1, Identificador);
            //Se almacenan los datos en un ResulSet
            ResultSet DatosMenu = ejecucion.executeQuery();
            //Se verifica que existan datos
            if (DatosMenu.next()) {
                datos[0] = DatosMenu.getString(1);  //Codigo
                datos[1] = DatosMenu.getString(2); //NombreProducto
                datos[2] = DatosMenu.getDouble(3); //Precio
                datos[3] = DatosMenu.getInt(4); //Cantidad
                Blob blob = DatosMenu.getBlob(5);
                datos[4] = blob.getBytes(1, (int) blob.length()); //Imagen
                datos[5] = DatosMenu.getDate(6); //Fecha inicio
                datos[6] = DatosMenu.getDate(7); //Fecha fin
                datos[7] = DatosMenu.getString(8); //Descripción
                datos[8] = DatosMenu.getInt(9); //Tamaño
                datos[9] = DatosMenu.getInt(10); //Color
                datos[10] = DatosMenu.getInt(11); //Marca
                datos[11] = DatosMenu.getInt(12); //Distribuidor
                datos[12] = DatosMenu.getInt(13); //Tipo producto
                datos[13] = DatosMenu.getInt(14); //Tipo inventario
                datos[14] = DatosMenu.getInt(15); //Estado producto
                datos[15] = DatosMenu.getInt(16); //Categoria
                datos[16] = DatosMenu.getInt(17); //Material
                datos[17] = DatosMenu.getInt(18); //Descuento
            } else {
                //Se no se encuentran los datos
                System.out.print("ERROR INESPERADO");
            }
        } catch (Exception e) {
            //Se imprime el error
            System.out.print(e.toString());
        }
        //Se devuelven los datos
        return datos;
    }

    //Método que carga la tablaRenta
    public ResultSet tablaRenta() {
        //Se instancia el resultset que se enviará
        ResultSet datos = null;
        try {
            //Se crea la sentencia
            Statement st = Conect.createStatement();
            //Se ejecuta la sentecia
            datos = st.executeQuery("SELECT p.nombreProducto, m.nombreMarca, e.nombreTipo, p.precio, p.cantidad from tbProducto p, tbMarca m, tbEstadoProducto e WHERE m.idMarca = p.idMarca AND e.idEstadoProducto = p.idEstadoProducto AND p.idTipoInventario =2 AND p.idTipo BETWEEN 1 AND 3");

        } catch (Exception e) {
            //Si se da un error, se devuelvo el error
            System.out.println(e.toString());
        }
        //Se retonra el ResultSet
        return datos;
    }

    //Método que carga la tablaVenta
    public ResultSet TablaVenta() {
        //Creación del resultSet
        ResultSet datos = null;
        try {
            //Se crea la sentencia
            Statement st = Conect.createStatement();
            //Se ejecuta
            datos = st.executeQuery("SELECT p.nombreProducto, m.nombreMarca, e.nombreTipo, p.precio, p.cantidad from tbProducto p, tbMarca m, tbEstadoProducto e WHERE m.idMarca = p.idMarca AND e.idEstadoProducto = p.idEstadoProducto AND p.idTipoInventario =1 AND p.idTipo BETWEEN 1 AND 3");
        } catch (Exception e) {
            //Se envian el error
            System.out.println(e.toString());
        }
        return datos;
    }

    public String Empresa(int ID) {
        String Empresa = null;
        try {
            String SQL = "SELECT em.nombreEmpresa, e.idEmpresa FROM tbEmpleado e, tbEmpresa em WHERE em.idEmpresa = e.idEmpresa and idEmpleado=?;";
            PreparedStatement SELECT = Conexion.Conexion().prepareStatement(SQL);
            SELECT.setInt(1, ID);
            ResultSet datos = SELECT.executeQuery();
            while (datos.next()) {
                Empresa = datos.getString("nombreEmpresa");
            }
        } catch (Exception e) {
            System.out.println("Modelo IDEmpresa" + e);
        }
        return Empresa;
    }

    public Connection getConect() {
        return Conect;
    }

    public void setConect(Connection Conect) {
        this.Conect = Conect;
    }
}
