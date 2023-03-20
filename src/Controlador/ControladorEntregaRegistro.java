package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Realizamos las importaciones

import javax.swing.table.DefaultTableModel;
import Modelo.ModelEntregaRegistro;
import Vista.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import Controlador.ControladorVariables;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
/**
 *
 * @author Jesus Gerardo
 */
public class ControladorEntregaRegistro {

    //Creamos un Arraylist que almacene el id de los productos
    public static ArrayList<String> codigoProductos = new ArrayList<String>();
    //Creamos un ArrayList que almacene el nombre de los productos
    public static ArrayList<String> Productos = new ArrayList<String>();
    //Creamos un ArrayList que almacene la descripcion de los registros
    public static ArrayList<String> Descripcion = new ArrayList<String>();
    //Creamos un ArrayList que almacene el nombre de los proveedores
    public static ArrayList<String> Proveedores = new ArrayList<String>();
    //Creamos un ArrayList que almacene el id de los proveedores
    public static ArrayList<Integer> idProveedores = new ArrayList<Integer>();
    //Creamos un ArrayList que almacene el id de los registros
    public static ArrayList<Integer> idRegistros = new ArrayList<Integer>();
    //Creamos un ArrayList que almacene la fecha de Ingreso del registro
    public static ArrayList<java.util.Date> fechaIngreso = new ArrayList<java.util.Date>();
    //Creamos un ArrayList que almacene la hora de Ingreso del registro
    public static ArrayList<Time> horaIngreso = new ArrayList<Time>();
    //Creamos un ArrayListr que almacene la cantidad inicial del producto registrada tras el ingreso del registro
    public static ArrayList<Integer> cantidadInicial = new ArrayList<Integer>();
    //Creamos un ArrayListr que almacene la cantidad del producto registrada 
    public static ArrayList<Integer> cantidadProducto = new ArrayList<Integer>();
    //Creamos un arreglo de bytes que sera la imagen, este sera momentaneo
    public static ArrayList<byte[]> imagenProducto = new ArrayList<byte[]>();
    //Creamos variable que defina la posicion de los datos en el combobox
    private int posicion,posiciondistri,proveedor,idregistro;
    String producto;
    private static Date fechaI; 
    private static Date fechaFi;
    
    //Cremos metodo que manda parametro 
    public static ResultSet BuscadorFechas() {
        return Modelo.ModelEntregaRegistro.BuscadorFechas(fechaI,fechaFi);
    }
    //Creamos metodo que retornara un modelo de combobox
    //Creamos metodo que defina el modelo del estado de usuario
    public DefaultComboBoxModel obtenerProductos() {
        //Creamos objeto del modelo
        ModelEntregaRegistro model = new ModelEntregaRegistro();
        //Creamos un objeto de la clase DefaultComboBosModel que es el modelo del combo box
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        //Creamos un objeto de tipó resultSet que sera igual al resultSet obtenido del modelo
        ResultSet res = model.cargarProductos();
        //Limpiamos los arreglos para que se reseteen
        codigoProductos.clear();
        imagenProducto.clear();
        Productos.clear();
        cantidadProducto.clear();
        try {
            while (res.next()) {
                //Añadimos el codigo del producto al array de los productos
                codigoProductos.add(res.getString("codigoProducto"));
                //Añadimos las imagenes al ArrayList que contiene un arreglo de bytes
                imagenProducto.add(res.getBytes("imagenProducto"));
                //Añadimos los productos al ArrayList de productos
                Productos.add(res.getString("nombreProducto"));
                //Añadimos la cantidad del producto que existe en el ArrayList de cantidadProducto
                cantidadProducto.add(res.getInt("cantidad"));
                //Añadimos al modelo de combobox el producto
                lista.addElement(res.getString("nombreProducto"));
                System.out.println(cantidadProducto);
            }
            res.close();
        } catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido cargarlos productos");
            error.setVisible(true);
        }
        return lista;
    }

    //Creamos metodo que defina el modelo de un combobox y retorne con los proveedores en el
    public DefaultComboBoxModel obtenerProveedores() {
        //Creamos objeto del modelo
        ModelEntregaRegistro model = new ModelEntregaRegistro();
        //Creamos un objeto de la clase DefaultComboBosModel que es el modelo del combo box
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        //Creamos un objeto de tipó resultSet que sera igual al resultSet obtenido del modelo
        ResultSet res = model.cargarProveedores();
        //Limpiamos los arreglos para que se reseteen
        idProveedores.clear();
        Proveedores.clear();
        try {
            while (res.next()) {
                //Añadimos el id del distribuidor al array de los proveedores
                idProveedores.add(res.getInt("idDistribuidor"));
                //Añadimos el proveedor al array de los proveedores
                Proveedores.add(res.getString("nombreDistribuidor"));
                //Añadimos al modelo de combobox el distribuidor
                lista.addElement(res.getString("nombreDistribuidor"));
            }
            res.close();
        } catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido cargarlos productos");
            error.setVisible(true);
        }
        return lista;
    }

    //Creamos metodo que busque en el arreglo de codigo de productos y nombre de productos el producto
    public boolean confirmarProducto(String str) {
        posicion = 0;
        //Creamos variable confirm que se retornara para confirmar que se encontro o no se encontro el producto        
        boolean confirm = false, salida = false;
        //Creamos un bucle while que busque el nombre o el codigo del producto
        try {
            while (salida == false) {
                if (str.equals(codigoProductos.get(getPosicion())) || str.equals(Productos.get(getPosicion()))) {
                    salida = true;
                    confirm = true;
                } else {
                    posicion++;
                }
            }
        } catch (Exception e) {
            confirm = false;
        }
        //retornamos la confirmacion
        return confirm;
    }

     //Creamos metodo que busque en el arreglo de codigo de productos y nombre de productos el producto
    public boolean confirmarDistribuidor(String str) {
        posiciondistri = 0;
        //Creamos variable confirm que se retornara para confirmar que se encontro o no se encontro el producto        
        boolean confirm = false, salida = false;
        //Creamos un bucle while que busque el nombre o el codigo del producto
        try {
            while (salida == false) {
                if (str.equals(Proveedores.get(posiciondistri))) {
                    salida = true;
                    confirm = true;
                } else {
                    posiciondistri++;
                }
            }
        } catch (Exception e) {
            confirm = false;
        }
        //retornamos la confirmacion
        return confirm;
    }
    
    //Creamos metodo que retorne la imagen del producto
    //Creamos metodo para obtener la imagen del empleado
    public ImageIcon cargarImagen() {
        //Creamos una icono de tipó ImageIcon e inicializamos con null
        ImageIcon img = null;
        try {
            //Creamos un objeto de tipo BufferedImage e inicializamos con null
            BufferedImage bufferdImage = null;
            //Creamos un inputStream que contrendra un arreglo de bytes que sera el icono
            InputStream inputStream = new ByteArrayInputStream(imagenProducto.get(getPosicion()));
            //El icono sera igual a una operacion donde una clase ImageIo.read leera 
            //e interpretara el arreglo de bytes
            bufferdImage = ImageIO.read(inputStream);
            //Asiganmos un valor al icono de la imagen (img) y colocamos un reescalado de 
            //0 pixeles adjustando sus dimensiones a 104,140
            img = new ImageIcon(bufferdImage.getScaledInstance(206, 206, 0));
        } catch (Exception e) {
            //Al encontrar un error, enviara un icono que representara que el usuario no posee imagen
            img = new ImageIcon("src/Recursos_img/NoImage200px.png");
        }
        //Regresa el icono de la imagen
        return img;
    }
    //Creamos metodo que devuelva un modelo de tabla
    public DefaultTableModel obtenerRegistros(){
        //Creamos objeto del modelo
        ModelEntregaRegistro model = new ModelEntregaRegistro();
        //Creamos un objeto de tipo Modelo de tabla
        DefaultTableModel modelo = new DefaultTableModel();
        //Creamos objeto de tipo ResultSet
        ResultSet rs = model.cargarRegistros();
        //Añadimos las columnas a la tabla
        modelo.setColumnIdentifiers(new Object [] {"N-Entrega","Fecha entrega","Hora ingreso","Cantidad","Precio","Nombre producto","Empleado","Distribuidor"});
        //Reseteamos todos los datos
        fechaIngreso.clear();
        horaIngreso.clear();
        Descripcion.clear();
        cantidadInicial.clear();
        idRegistros.clear();
        try{
            while(rs.next()){
                //Ingresamos las fechas de ingreso de los registros en un arreglo
                fechaIngreso.add(rs.getDate("fechaEntrega"));
                //Ingresamos la hora de Ingreso al arraylist para almacenar sus datos
                horaIngreso.add(rs.getTime("horaIngreso"));
                //Ingresamos la descripcion al arraylist para almacenar sus datos
                Descripcion.add(rs.getString("descripcion"));
                //Ingresamos la cantidad incial al arraylist para almacenar sus datos
                cantidadInicial.add(rs.getInt("cantidadInicial"));
                //Ingresamos el id de registro al arraylist para almacenar sus datos
                idRegistros.add(rs.getInt("idEntregaRegistro"));
                //Añadimos a la tabla los datos o filas a la tabla
                modelo.addRow(new Object [] {rs.getString("idEntregaRegistro"),rs.getDate("fechaEntrega"),rs.getTime("horaIngreso"),rs.getInt("cantidad"),rs.getDouble("precio"),rs.getString("nombreProducto"),rs.getString("empleado"),rs.getString("nombreDistribuidor")});
            }
        }catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido cargar los datos en la tabla");
            error.setVisible(true);
        }
        return modelo;
    }
    //Creamos metodo que obtenga el codigo del producto guiandose por su 
    public String obtenerProducto(int id){
        String codigo = codigoProductos.get(id);
        return codigo;
    }
    //Creamos metodo que obtenga el id del proveedor guiandose por su index
    public int obtenerProveedor(int id){
        id = idProveedores.get(id);
        return id;
    }
    //Creamos metodo que obtenga la cantidad del producto para ser registrada como cantiad inicial
    public int obtenerCantidad(int id){
        id = cantidadProducto.get(id);
        return id;
    }
    //Creamos metodo que envie los parametros necesarios para la insercion de los registros en la bd
    public void agregarRegistros(int cantidad, double precio, String descripcion,String codigoProducto,int idProveedor,int cantidadi){
        //Creamos variable que obtenga la fecha del sistema
        java.sql.Date fechai = new java.sql.Date(new java.util.Date().getTime());
        //Creamos variabl que obtenga la hora del sistema
        Time horai = Time.valueOf(LocalTime.now());
        //Obtenemos el id del empleado que esta realizando el registro
        //Creamos un objeto de la clase para acceder a sus metodos
        ControladorVariables variables = new ControladorVariables();
        int idEmpleado = variables.getEmpleado();
        //Una vez obtenido todos los datos, ejecutaremos el metodo de agregado y pasaremos los parametros
        ModelEntregaRegistro  model = new ModelEntregaRegistro();
        //Ejecutamos el metodo evaluando si este devuelve como resultado true o false
        if (model.guardarRegistro(fechai, horai, cantidad, cantidadi, precio, descripcion, codigoProducto, idEmpleado, idProveedor)==true) {
            //Ya que logro hacer la insercion, mostramos un mensaje de felicitaciones y actualizamos la cantidad del producto en el inventario
            FrmNoti2 error = new FrmNoti2();
            error.lblTituloNoti2.setText("¡Felicitaciones!");
            error.TANoti2.setText("Has registrado con exito una entrega");
            error.setVisible(true);
            model.actualizarCantidad(codigoProducto, (cantidad+cantidadi));
        }else{
            //Ya que no se pudo hacer la insercion mostramos un mensaje de error
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido guardar el registro, intente de nuevo por favor");
            error.setVisible(true);
        }
    }
    //Creamos metodo que devuelva un String que sera la descripcion y reciba como parametro el id del registro
    public String obtenerDescripcion(int index){
        idregistro=0;
        String dsc =null;
        //Creamos variable confirm que se retornara para confirmar que se encontro o no se encontro el producto        
        boolean salida = false;
        //Creamos un bucle while que busque el nombre o el codigo del producto
        try {
            while (salida == false) {
                if (index==idRegistros.get(idregistro)) {
                    salida = true;
                    dsc = Descripcion.get(idregistro);
                    return dsc;
                } else {
                    idregistro++;
                }
            }
        } catch (Exception e) {
        }
        return dsc;
    }
    //Creamos metodo que retorne un String que sera la hora que se ingreso el registro, para esto pedira el id del registo
    public String obtenerhoraI(int index){
        idregistro=0;
        String horaI =null;
        //Creamos variable confirm que se retornara para confirmar que se encontro o no se encontro el producto        
        boolean salida = false;
        //Creamos un bucle while que busque el nombre o el codigo del producto
        try {
            while (salida == false) {
                if (index==idRegistros.get(idregistro)) {
                    salida = true;
                    horaI = String.valueOf(horaIngreso.get(idregistro));
                    return horaI;
                } else {
                    idregistro++;
                }
            }
        } catch (Exception e) {
        }
        return horaI;
    }
    //Creamos metodo que evalue y calcule los minutos entre las horas y devuelva un booleano
    public boolean diferencia(String horai) {
       boolean confirm=false;
        java.util.Date horaInicio = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            horaInicio = dateFormat.parse("2021-09-05 "+horai);//Omitir la fecha, solo es para ejemplo
            Calendar calInicio = Calendar.getInstance();
            calInicio.setTime(horaInicio);//pasamos horaInicio a Calendar
            Calendar calSalida = Calendar.getInstance();//hora actual sistema
            //calculamos diferencia
            int difHoras = calSalida.get(Calendar.HOUR_OF_DAY) - calInicio.get(Calendar.HOUR_OF_DAY);
            int difMinutos = calSalida.get(Calendar.MINUTE) - calInicio.get(Calendar.MINUTE);
            int difSegundos = calSalida.get(Calendar.SECOND) - calInicio.get(Calendar.SECOND);
            Calendar calDif = Calendar.getInstance();//variable para diferencia de tiempo
            calDif.set(Calendar.HOUR_OF_DAY, difHoras);
            calDif.set(Calendar.MINUTE, difMinutos);
            calDif.set(Calendar.SECOND, difSegundos);
            int totalminutos = calDif.get(Calendar.HOUR) * 60 + calDif.get(Calendar.MINUTE);
            //evaluamos que la diferencia no sea mayor que 30 minutos
            if (totalminutos>=30) {
                //Como es mayor a 30 retorna un valor falso
                confirm = false;
            }else{
                //Como es menor confirm sera igual a verdadero dando pase a que se pueda actualizar
                confirm = true;
            }
        } catch (ParseException ex) {
        }
        return confirm;
    }
    //Creamos metodo que devuelva un boolean que identifique si se puede actualizar guiandose por la diferencia de dia
    public boolean diferenciaDia(Date fecha){
        boolean confirm=false;
        try {
            //Creamos un objeto del calendario y obtenemos su instancia
            Calendar calendario = Calendar.getInstance();
            //Añadimos al calendiario la fecha del ingreso
            calendario.setTime(fecha);
            //Creamos variables año,mes y dia y las inicializamos obteniendo sus respectivos datos del calendario
            int año = calendario.get(Calendar.YEAR);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            int mes = (calendario.get(Calendar.MONTH)) + 1;//Se le suma uno ya que el calendario que se usa posee a enero como indice 0.
            //Creamos dos variables que seran de tipo LocalDate para comparar
            LocalDate f1 = LocalDate.of(año, mes, dia);//Añadimos a la fecha 1 el año,mes y dia
            LocalDate f2 = LocalDate.now();//Obtenemos la fecha actual
            //Creamos una variable de tipo Period que sera el periodo de tiempo entre fecha 1 y fecha 2
            Period periodo = Period.between(f1, f2);
            int diferencia = periodo.getDays();//Asiganamos la diferencia de dias a una variable
            //Evaluamos la variable
            if (diferencia<=0) {
                confirm = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return confirm;
    }
    //Creamos un metodo que obtenga la cantidad inicial del registro previamente ingresado
    public int obtenerCantidadI(int index){
        idregistro=0;
        int cantidad=0;
        //Creamos variable confirm que se retornara para confirmar que se encontro o no se encontro el producto        
        boolean salida = false;
        //Creamos un bucle while que busque el nombre o el codigo del producto
        try {
            while (salida == false) {
                if (index==idRegistros.get(idregistro)) {
                    salida = true;
                    cantidad = cantidadInicial.get(idregistro);
                } else {
                    idregistro++;
                }
            }
        } catch (Exception e) {
        }
        return cantidad;
    }
    //Creamos metodo que actualice el registro
    //Creamos metodo que envie los parametros necesarios para la insercion de los registros en la bd
    public void actualizarRegistro(int cantidad, double precio, String descripcion,String codigoProducto,int idProveedor,int cantidadi,int idRegistro){
        //Creamos variable que obtenga la fecha del sistema
        java.sql.Date fechai = new java.sql.Date(new java.util.Date().getTime());
        //Creamos variabl que obtenga la hora del sistema
        Time horai = Time.valueOf(LocalTime.now());
        //Obtenemos el id del empleado que esta realizando el registro
        //Creamos un objeto de la clase para acceder a sus metodos
        ControladorVariables variables = new ControladorVariables();
        int idEmpleado = variables.getEmpleado();
        //Una vez obtenido todos los datos, ejecutaremos el metodo de agregado y pasaremos los parametros
        ModelEntregaRegistro  model = new ModelEntregaRegistro();
        //Ejecutamos el metodo evaluando si este devuelve como resultado true o false
        if (model.actualizarRegistro(fechai, horai, cantidad, precio, descripcion, codigoProducto, idEmpleado, idProveedor, idRegistro)==true) {
            //Ya que logro hacer la insercion, mostramos un mensaje de felicitaciones y actualizamos la cantidad del producto en el inventario
            FrmNoti2 error = new FrmNoti2();
            error.lblTituloNoti2.setText("¡Felicitaciones!");
            error.TANoti2.setText("Has actualizado con exito un registro de entrega");
            error.setVisible(true);
            model.actualizarCantidad(codigoProducto, (cantidad+cantidadi));
        }else{
            //Ya que no se pudo hacer la insercion mostramos un mensaje de error
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido guardar el registro, intente de nuevo por favor");
            error.setVisible(true);
        }
    }
    //Metodo que retorne la posicion del producto en el combobox
    /**
     * @return the posicion
     */
    public int getPosicion() {
        return posicion;
    }
    //Metodo que retorne la posicion del distribuidor en el combobox
    public int getPosicionDistri() {
        return posiciondistri;
    }

    /**
     * @return the fechaI
     */
    public Date getFechaI() {
        return fechaI;
    }

    /**
     * @param fechaI the fechaI to set
     */
    public void setFechaI(Date fechaI) {
        this.fechaI = fechaI;
    }

    /**
     * @return the fechaFi
     */
    public Date getFechaFi() {
        return fechaFi;
    }

    /**
     * @param fechaFi the fechaFi to set
     */
    public void setFechaFi(Date fechaFi) {
        this.fechaFi = fechaFi;
    }
}
