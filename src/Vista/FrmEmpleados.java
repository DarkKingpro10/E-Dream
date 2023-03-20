/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorEmpleados;
import Controlador.ControladorVariables;
import Controlador.Validaciones;
import Modelo.modeloEmpleado;
import Recursos_Tipografias.Fuentes;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author SeyJR
 */
public class FrmEmpleados extends javax.swing.JPanel {

    /**
     * Creates new form FrmEmpleados
     */
    //Creamos objeto del controlador de la clase validaciones
    Validaciones validaciones = new Validaciones();

    //Creamos atributos globales
    public static Integer id;
    public static String seleccion;//Para Imagen
    public static String iden;

    //Creamos arreglos globales
    ArrayList<String> codigos;//Instanciamos
    ArrayList<String> codigoE;//Instanciamos
    ArrayList<Integer> CargarIdEmpresa = new ArrayList<Integer>();
    ArrayList<Integer> CargarIdTipo = new ArrayList<Integer>();
    ArrayList<Integer> CargarIdEstado = new ArrayList<Integer>();
    ArrayList<Integer> CargarIdUsuario = new ArrayList<Integer>();
    ArrayList<Integer> CargarIdUsuarioTotal = new ArrayList<Integer>();
    ArrayList<Integer> CargarIdEmpleado = new ArrayList<Integer>();
    static ArrayList<BufferedImage> campos = new ArrayList<BufferedImage>();//Para la imagen
    LocalDate Fecha = LocalDate.now();
    SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd"); //Se estable el formato de fehca
    ZoneId systemTimeZone = ZoneId.systemDefault();
    ZonedDateTime zonedDateTime = Fecha.atStartOfDay(systemTimeZone);
    java.util.Date FechaActual = java.util.Date.from(zonedDateTime.toInstant());

    //Creamos objetos de otras clases y forms
    modeloEmpleado empleado = new modeloEmpleado();
    FrmNotiError NError = new FrmNotiError();
    ControladorEmpleados obj = new ControladorEmpleados();

    //Creamos un default table model para la tabla
    DefaultTableModel modelo;

    //Creamos un tablerowsorter
    TableRowSorter trs;

    //Creamos componente para escoger una imagen
    JFileChooser i = new JFileChooser();
    FileNameExtensionFilter llenar = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");

    Object usuarioInicial;

    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Morado = new Color(94,45,79);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);
    
    public FrmEmpleados() {
        initComponents();
        Colores();
        //mostrarDatos();
        compararEstado();

        Fuentes tipoFuente;

        //Asignamos tipografia a companentes
        tipoFuente = new Fuentes();
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 40));
        lblNombre.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblApellido.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblDui.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblEdad.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblSexo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblTel.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblCorreo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblEmpresa.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblEstado.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblUsuario.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblTipo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnIngresar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnActualizar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnEliminar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnSubir.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtNombre.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtApellido.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtDui.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        dtEdad.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtTel.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtCorreo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        tbEmpleados.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));

        modelo = new DefaultTableModel();

        //Cargamos combobox 
        CargarEmpresa();
        CargarEstado();
        CargarTipo();
        CargarUsuario();
        // CargarIdUsuario();

        //Le asignamos el valor de los arraylist  el metodo de controlador donde conseguimos el id de cada combo box
        CargarIdEmpresa = obj.idEmpresa();
        CargarIdEstado = obj.idEstado();
        CargarIdTipo = obj.idTipo();
        //  CargarIdUsuario = ControladorEmpleados.idUsuario();
        //   CargarIdUsuarioTotal = obj.idUsuarioTotal();

        //Hacemos visible el label de validaciones
        NMAX2.setVisible(false);
        NMAX3.setVisible(false);

        lblCorreoInv.setVisible(false);

        lblDuiE.setVisible(false);
        lblTelefonoE.setVisible(false);
        lblCorreoE.setVisible(false);

        //   usuarioInicial = cmbUsuario.getSelectedItem();
        //Desactivamos la opcion de editar en los jDateChooser
        dtEdad.getDateEditor().setEnabled(false);

    }
    
    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                jPanel3.setBackground(Piel);
                jPanel4.setBackground(Piel);
                jPanel2.setBackground(Oscuro);
                break;
            case 2://Claro
                jPanel3.setBackground(Color.white);
                jPanel4.setBackground(Color.white);
                jPanel2.setBackground(AzulOscuro);
                break;
            default:
                jPanel3.setBackground(Piel);
                jPanel4.setBackground(Piel);
                jPanel2.setBackground(Oscuro);
                break;
        }        
    }


    //Aignamos modelos a los combobox
    void CargarEmpresa() {
        cmbEmpresa.setModel(obj.ObtenerEmpresas());
    }

    void CargarEstado() {
        cmbEstado.setModel(obj.ObtenerEstados());
    }

    void CargarUsuario() {
        cmbUsuario.setModel(obj.ObtenerUsuarios());
    }

    void CargarTipo() {
        cmbTipo.setModel(obj.ObtenerTipos());
    }

    void CargarUsuarioTotal() {
        cmbUsuario.setModel(obj.ObtenerUsuariosTotales());
    }

    //Creamos metodo para cargar atos en la tabla
    public void compararEstado() {
        //Creamos un default table model para guardar datos de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        //Le asignamos el metodo de Resultset de controlador  a Resultset "rs"
        ResultSet rs = obj.CargarTablaController();
        //Le asignamo el nombre de cada columna al modelo de la tabla
        modelo.setColumnIdentifiers(new Object[]{"Nombre", "Apellido", "DUI", "Edad", "Telefono", "Correo", "Foto", "Sexo", "Empresa", "Estado", "Tipo", "Usuario"});

        try {
            while (rs.next()) {
                //Creamos un String que almacene el usuario
                String usuario = (rs.getString("usuario"));
                System.out.println(usuario);
                if (usuario == null) {
                    usuario = "No asignado";
                } else {
                    usuario = usuario;
                    System.out.println(usuario);
                }
                //Agregamos el dto correspondiente de la base de datos a cada columna
                modelo.addRow(new Object[]{rs.getString("nombreEmpleado"), rs.getString("apellidoEmpleado"), rs.getString("DUI"), rs.getDate("edadEmpleado"), rs.getString("telefonoEmpleado"), rs.getString("correoEmpleado"), rs.getBinaryStream("imagenEmpleado"), rs.getString("sexo"), rs.getString("nombreEmpresa"), rs.getString("estadoEmpleado"), rs.getString("tipoEmpleado"), usuario});

            }
            //Le asignamos el modelo a la tabla de empleados
            tbEmpleados.setModel(modelo);

        } catch (Exception e) {
            //Imprimimos error 
            System.out.println(e);
        }
    }

    //Creamos metodo para inertar imagen en componente lblAgregarViualizar
    public String insertar() {
        try {
            //Es una clase java que nos permite mostrar fácilmente una ventana para la selección de un fichero
            JFileChooser ventana = new JFileChooser();
            //Se establece un filtro para que solo se muestren archivos con una determinada extensión
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
            //Agregamos el filtro a la ventana que abriremos
            ventana.setFileFilter(filtro);

            int seleccionar = ventana.showOpenDialog(this);
            //Comprobar si se ha dado clic en Aceptar
            if (seleccionar == JFileChooser.APPROVE_OPTION) {
                //Convertimos a tipo Archivo el componente seleccionado
                File archivo = ventana.getSelectedFile();
                //Esta función devuelve el nombre de ruta absoluta del objeto de archivo dado.
                seleccion = archivo.getAbsolutePath();
                lblRutaImagen.setText(seleccion);
                //Obtenemos el ancho del Label donde la colocaremos
                int ancho = lblAgregarVisualizar.getWidth();
                //Obtenemos la altura del Label donde la colocaremos
                int alto = lblAgregarVisualizar.getHeight();

                //getToolkit Obtiene el kit de herramientas predeterminado y se puede obtener un objeto Toolkit mediante la invocación de este método
                // En este caso nos permite ir a nuestro equipo y obtener la ruta.
                //getImage carga todos los bits de la imagen en la memoria
                Image imagen = getToolkit().getImage(seleccion);
                //Image.SCALE_SMOOTH Elije un algoritmo de escalado de imagen que da mayor prioridad a la suavidad de la imagen que a la velocidad de escalado. 
                imagen = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                lblAgregarVisualizar.setIcon(new ImageIcon(imagen));
            }
        } catch (Exception e) {

        }
        return seleccion;
    }

    //Metodo para limpiar campos
    void limpiar() {

        txtNombre.setText("");
        txtApellido.setText("");
        txtDui.setText("");
        dtEdad.setDate(null);
        txtTel.setText("");
        txtCorreo.setText("");

        lblRutaImagen.setText("");
        NMAX3.setVisible(false);
        NMAX2.setVisible(false);
        lblAgregarVisualizar.setIcon(null);
    }

    //Metodo para ingresar empleado
    public void IngresarEmpleado() {

        if ((obj.validarEdad(dtEdad.getDate())) >= 18) {
            System.out.println("Es mayor a 18 años");

            //Creamos variable de tipo string para el exo
            String sexo;
            //Creamos objeto de formulario de notificacion de campos vacios
            FrmNoti1 campos = new FrmNoti1();

            //Validamos que no este ningun campo sin llenar
            if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtDui.getText().isEmpty() || txtTel.getText().isEmpty() || txtCorreo.getText().isEmpty() || dtEdad.getDate() == null || lblRutaImagen.getText().isEmpty()) {
                campos.setVisible(true);
                campos.TAMensajeError.setText("Existen campos sin"
                        + "\n"
                        + " datos asignados");
                campos.lblTituloNoti1.setText("¡Campos vacíos!");
            } else {

                //Para la fecha obtenemos con la clase date
                java.util.Date fecha = dtEdad.getDate();

                //Condicionamos el sexo si el indice del cmbSexo es igual a 0 se le aignara el sexo femenino
                if (cmbSexo.getSelectedIndex() == 0) {
                    sexo = "Femenino";
                } else {
                    //Si no, se le asigna masculino
                    sexo = "Masculino";
                }

                //Obtenemos el index de cada combobox
                int idEmpresa = cmbEmpresa.getSelectedIndex();
                idEmpresa = obj.ObtenerIdEmpresa(idEmpresa);
                int idEstado = cmbEstado.getSelectedIndex();
                idEstado = obj.ObtenerIdEstado(idEstado);
                String usuario = (String) cmbUsuario.getSelectedItem();
                int idUsuario = obj.ObtenerIdUsuario(usuario);
                System.out.println("idUSUARIO " + idUsuario);
                System.out.println(usuario);
                System.out.println(idUsuario);
                int idTipo = cmbTipo.getSelectedIndex();
                idTipo = obj.ObtenerIdTipo(idTipo);
                //  CargarIdUsuario.get(usuario);

                //enviando la información a la clase
                ControladorEmpleados obj = new ControladorEmpleados();//Creamos objeto e controlador de empleado 
                obj.setNombreE(txtNombre.getText());//Obtenemos el nombre de empleado y lo mandamos a controlador
                obj.setApellidoE(txtApellido.getText());//Obtenemos el apellido de empleado y lo mandamos a controlador
                obj.setDuiE(txtDui.getText());//Obtenemos el dui de empleado y lo mandamos a controlador

                SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
                String edad = Formato.format(fecha);//Asignamos el formato de fecha de año, mes, dia

                java.sql.Date fechaNaci = java.sql.Date.valueOf(edad);
                obj.setEdadE(fechaNaci); //Asignamos la fecha de nacimiento y lo mandmos al comtrolador
                obj.setSexoE(sexo); //Aignamos la fecha de nacimiento y lo mandamos al controlador
                obj.setTelefonoE(txtTel.getText()); //Obtenemos el telefono de empleado y lo mandamos a controlador
                obj.setCorreoE(txtCorreo.getText());//Obtenemos el correo de empleado y lo mandamos a controlador
                obj.setCodigoEmpresa(idEmpresa);//Asignamos la empresa y la mandamos a controlador
                obj.setCodigoTipo(idTipo);//Asignamos el tipo de empleado y lo mandamos a controlador
                obj.setCodigoUsuario(idUsuario);//Asignamos el usuario y lo mandamos a controlador
                obj.setCodigoEstado(idEstado);//Asignamo el estado de empleado y lo mandamos a controlador

                obj.setImagenP(seleccion);//Asignamos la imagen para mandarla a controlador
                //   obj.setIdUsuarioActu(idUsuario);//Asignamos el usuario y lo mandamos a controlador

                //enviando guardar a  SQLServer
                if (obj.IngresarEmpleadoController() && obj.ActualizarIdEstadoUsuarioController()) {//Si se da esta condicion
                    //Se actualiza la tabla

                    compararEstado();

                    //Se muestra menaje de notificacion
                    FrmNoti2 notificacion = new FrmNoti2();
                    notificacion.setVisible(true);
                    notificacion.lblTituloNoti2.setText("¡Empleado Creado!");
                    notificacion.TANoti2.setText("El nuevo empleado"
                            + "\n"
                            + " se ha creado");

                    CargarUsuario();
                    //llamamos metodo para limpiar campos
                    limpiar();

                } else {
                    //Si da error llamamos el formulario de error
                    FrmNotiError error = new FrmNotiError();
                    error.setVisible(true);
                    error.lblTituloError.setText("Error al crear");
                    error.TAnotiError.setText("No se pudo guardar el empleado"
                            + "\n"
                            + "creado en la base"
                            + "\n"
                            + "de datos, problemas de conexión");
                }
            }

        } else {
            //Si da error llamamos el formulario de error
            FrmNotiError error = new FrmNotiError();
            error.setVisible(true);
            error.lblTituloError.setText("Error al crear");
            error.TAnotiError.setText("No se pudo guardar el empleado"
                    + "\n"
                    + "porque no es mayor a 18"
                    + "\n"
                    + "años");
        }
    }

    //Metodo para actualizar empleado
    public void ActualizarEmpleado() {
        try {

            //Creamos variable de tipo string para el exo
            String sexo;
            //Para la fecha obtenemos con la clase date
            java.util.Date fecha = dtEdad.getDate();

            //Condicionamos el sexo si el indice del cmbSexo es igual a 0 se le aignara el sexo femenino
            if (cmbSexo.getSelectedIndex() == 0) {
                sexo = "Femenino";
            } else {
                //Si no, se le asigna masculino
                sexo = "Masculino";
            }

            //Obtenemos el index de cada combobox
            int idEmpresa = cmbEmpresa.getSelectedIndex();
            idEmpresa = obj.ObtenerIdEmpresa(idEmpresa);
            System.out.println(idEmpresa);
            int idEstado = cmbEstado.getSelectedIndex();
            idEstado = obj.ObtenerIdEstado(idEstado);
            System.out.println(idEstado);
            String usuario = (String) cmbUsuario.getSelectedItem();
            int idUsuario = obj.ObtenerIdUsuario(usuario);
            System.out.println(idUsuario);
            int idTipo = cmbTipo.getSelectedIndex();
            idTipo = obj.ObtenerIdTipo(idTipo);
            System.out.println(idTipo);
            // CargarIdUsuario.get(usuario);

            //Comparamos si lo que hay en el texto esta vacio, actualizamos todos los datos menos la imagen
            if (lblRutaImagen.getText().isEmpty()) {
                //enviando la información a la clase
                ControladorEmpleados obj = new ControladorEmpleados();//Creamos objeto de controlador de empleado 
                obj.setNombreE(txtNombre.getText());//Obtenemos el nombre de empleado y lo mandamos a controlador
                obj.setApellidoE(txtApellido.getText());//Obtenemos el apellido de empleado y lo mandamos a controlador
                obj.setDuiE(txtDui.getText());//Obtenemos el dui de empleado y lo mandamos a controlador

                SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
                String edad = Formato.format(fecha);//Asignamos el formato de fecha de año, mes, dia

                java.sql.Date fechaNaci = java.sql.Date.valueOf(edad);
                obj.setEdadE(fechaNaci); //Asignamos la fecha de nacimiento y lo mandmos al comtrolador
                obj.setSexoE(sexo); //Aignamos la fecha de nacimiento y lo mandamos al controlador
                obj.setTelefonoE(txtTel.getText()); //Obtenemos el telefono de empleado y lo mandamos a controlador
                obj.setCorreoE(txtCorreo.getText());//Obtenemos el correo de empleado y lo mandamos a controlador
                obj.setCodigoEmpresa(idEmpresa);//Asignamos la empresa y la mandamos a controlador
                obj.setCodigoTipo(idTipo);//Asignamos el tipo de empleado y lo mandamos a controlador
                obj.setCodigoUsuario(idUsuario);//Asignamos el usuario y lo mandamos a controlador
                obj.setCodigoEstado(idEstado);//Asignamos el estado de empleado y lo mandamos a controlador

                obj.setCodigoE(obj.getCodigoEVista());//Asignamos al codigo de empleado el id que le corresponde
                obj.ActualizarCampoUsuarioController();
                //enviando guardar a  SQLServer
                if (obj.ActuallizaSinImagenController() && obj.ActualizarIdEstadoUsuarioControllerT(obj.getCodigoUsuInicial())) {//Si se da esta condicion
                    //Actualizamos tabla

                    compararEstado();
                    obj.ActualizarIdEstadoUsuarioController();
                    CargarUsuario();
                    //Se muestra menaje de notificacion
                    FrmNoti2 notificacion = new FrmNoti2();
                    notificacion.setVisible(true);
                    notificacion.lblTituloNoti2.setText("¡Empleado Actualizado!");
                    notificacion.TANoti2.setText("Los datos del empleado "
                            + "\n"
                            + " se han actualizado");
                    //Limpiamos campos
                    limpiar();
                } else {
                    //Si da eerror llamamos al formulario de error
                    FrmNotiError error = new FrmNotiError();
                    error.lblTituloError.setText("Error al actualizar");
                    error.TAnotiError.setText("Ocurrio un error al actualizar "
                            + "\n"
                            + "conexión con la base de datos, "
                            + "\n"
                            + "verifique su acceso a internet "
                            + "\n"
                            + "o que los servicios del servidor"
                            + "\n"
                            + " estén activos");
                    error.setVisible(true);
                }
            } else {

                //enviando la información a la clase
                ControladorEmpleados obj = new ControladorEmpleados();//Creamos objeto de controlador de empleado 
                obj.setNombreE(txtNombre.getText());//Obtenemos el nombre de empleado y lo mandamos a controlador
                obj.setApellidoE(txtApellido.getText());//Obtenemos el apellido de empleado y lo mandamos a controlador
                obj.setDuiE(txtDui.getText());//Obtenemos el dui de empleado y lo mandamos a controlador

                SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
                String edad = Formato.format(fecha);//Asignamos el formato de fecha de año, mes, dia

                java.sql.Date fechaNaci = java.sql.Date.valueOf(edad);
                obj.setEdadE(fechaNaci); //Asignamos la fecha de nacimiento y lo mandmos al comtrolador
                obj.setSexoE(sexo); //Aignamos la fecha de nacimiento y lo mandamos al controlador
                obj.setTelefonoE(txtTel.getText()); //Obtenemos el telefono de empleado y lo mandamos a controlador
                obj.setCorreoE(txtCorreo.getText());//Obtenemos el correo de empleado y lo mandamos a controlador
                obj.setCodigoEmpresa(idEmpresa);//Asignamos la empresa y la mandamos a controlador
                obj.setCodigoTipo(idTipo);//Asignamos el tipo de empleado y lo mandamos a controlador
                obj.setCodigoUsuario(idUsuario);//Asignamos el usuario y lo mandamos a controlador
                obj.setCodigoEstado(idEstado);//Asignamos el estado de empleado y lo mandamos a controlador

                obj.setImagenP(seleccion);//Asignamos la imagen para mandarla a controlador     
                obj.setCodigoE(obj.getCodigoEVista());//Asignamos al codigo de empleado el id que le corresponde

                //enviando guardar a  SQLServer
                if (obj.ActualizarEmpleadoController() && obj.ActualizarIdEstadoUsuarioControllerT(obj.getCodigoUsuInicial())) {//Si se da esta condicion
                    //Actualizamos tabla
                    compararEstado();
                    obj.ActualizarIdEstadoUsuarioController();
                    CargarUsuario();

                    //Se muestra menaje de notificacion
                    FrmNoti2 notificacion = new FrmNoti2();
                    notificacion.setVisible(true);
                    notificacion.lblTituloNoti2.setText("¡Empleado Actualizado!");
                    notificacion.TANoti2.setText("Los datos del empleado "
                            + "\n"
                            + " se han actualizado");
                    //Limpiamos campos
                    limpiar();

                } else {
                    //Si da eerror llamamos al formulario de error
                    FrmNotiError error = new FrmNotiError();
                    error.lblTituloError.setText("Error al actualizar");
                    error.TAnotiError.setText("Ocurrio un error al actualizar "
                            + "\n"
                            + "conexión con la base de datos, "
                            + "\n"
                            + "verifique su acceso a internet "
                            + "\n"
                            + "o que los servicios del servidor"
                            + "\n"
                            + " estén activos");
                    error.setVisible(true);
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }

    //Metodo para actualizar el estado de usuario
//    public void ActualizarIdEstadoUsuario() {
//        try {
//           
//               ControladorEmpleados obj = new ControladorEmpleados();//Creamos objeto de controlador de empleado 
//               
//                obj.setCodigoUsuario(obj.getCodigoUsuario());//Asignamos el usuario y lo mandamos a controlador
//            
//
//                enviando guardar a  SQLServer
//                if (obj.ActualizarIdEstadoUsuarioController()) {//Si se da esta condicion
//                    System.out.println("Se actualizo el id de usuario");
//                    Cargamos modelo
//                    CargarUsuario();
//                } else {
//                    Si da eerror llamamos al formulario de error
//                    System.out.println("No se actualizo el id de usaurio");
//                }
//            
//        } catch (Exception e) {
//            System.out.print(e.toString());
//        }
//    }
    //Metodo para actualizar el estado de usuario
//    public void ActualizarIdEstadoUsuarioT() {
//        try {
//           
//            int idUsuario = cmbUsuario.getSelectedIndex();
//            System.out.println("int id actu "+idUsuario);
//            idUsuario=obj.ObtenerIdUsuarioT(idUsuario);
//
//                //enviando la información a la clase
//                ControladorEmpleados obj = new ControladorEmpleados();//Creamos objeto de controlador de empleado 
//               
//                obj.setCodigoUsuario(idUsuario);//Asignamos el usuario y lo mandamos a controlador
//                Object usuarioSele = cmbUsuario.getSelectedItem();
//                System.out.println(usuarioSele);
//                if (usuarioSele.equals(usuarioInicial)) {
//                     System.out.println("usuario mismo");
//                    
//                   
//            }else{
//                    System.out.println("usuario diferente");
//                //enviando guardar a  SQLServer
//                        if (obj.ActualizarIdEstadoUsuarioControllerT()) {//Si se da esta condicion
//                            System.out.println("Se actualizo el id de usuario de usurios completos");
//                            CargarUsuario();
//
//                        } else {
//                            //Si da eerror llamamos al formulario de error
//                            System.out.println("No se actualizo el id de usaurio completos");
//                        }
//                }
//                
//                
//            
//        } catch (Exception e) {
//            System.out.print(e.toString());
//        }
//    }
    //Metodo para eliminar empleado
    public void EliminarEmpleado() {
        //Creamos objeto de controlador de empleado
        ControladorEmpleados obj = new ControladorEmpleados();

        String usuario = (String) cmbUsuario.getSelectedItem();
        int idUsuario = obj.ObtenerIdUsuario(usuario);
        System.out.println(idUsuario);
        //Enviamos codigo de empleado 
        obj.setCodigoE(Integer.parseInt(obj.getCodigoEVista().toString()));
        obj.setCodigoUsuario(idUsuario);
        //confirmando que quiere eliminar
        int eliminar = JOptionPane.showConfirmDialog(this, "Está seguro que desea eliminar?", "Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (eliminar == 0) {
            //enviando el registro a eliminar en SQLServer
            if (obj.EliminarEmpleadoController() && obj.ActualizarEliminar()) {

                compararEstado();
                CargarUsuario();
                //Abrimos notificacion para motrar que los datos se han eliminado
                FrmNoti2 notificacion = new FrmNoti2();
                notificacion.setVisible(true);
                notificacion.lblTituloNoti2.setText("¡Registro Eliminado!");
                notificacion.TANoti2.setText("El registro de empleado"
                        + "\n"
                        + " se ha eliminado");
                //Limpiamos campos
                limpiar();
            } else {
                //Si surge un error enviamos menssaje
                FrmNotiError error = new FrmNotiError();
                error.lblTituloError.setText("Error al eliminar");
                error.TAnotiError.setText("Ocurrio un error al eliminar "
                        + "\n"
                        + "los datos, "
                        + "\n"
                        + "verifique su acceso a internet "
                        + "\n"
                        + "o que los servicios del servidor"
                        + "\n"
                        + " estén activos");
                error.setVisible(true);
            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        TEmpleado = new javax.swing.JScrollPane();
        tbEmpleados = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lblAgregarVisualizar = new javax.swing.JLabel();
        btnSubir = new javax.swing.JButton();
        lblNombre = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        lblDui = new javax.swing.JLabel();
        txtDui = new javax.swing.JTextField();
        lblEdad = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        cmbSexo = new javax.swing.JComboBox<>();
        lblTel = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        lblEmpresa = new javax.swing.JLabel();
        cmbEmpresa = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        lblUsuario = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        cmbUsuario = new javax.swing.JComboBox<>();
        cmbTipo = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        btnIngresar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        dtEdad = new com.toedter.calendar.JDateChooser();
        NMAX2 = new javax.swing.JLabel();
        NMAX3 = new javax.swing.JLabel();
        lblCorreoInv = new javax.swing.JLabel();
        lblRutaImagen = new javax.swing.JLabel();
        lblTelefonoE = new javax.swing.JLabel();
        lblDuiE = new javax.swing.JLabel();
        lblCorreoE = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1090, 620));
        setPreferredSize(new java.awt.Dimension(1090, 620));
        setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(255, 222, 212));

        jPanel2.setBackground(new java.awt.Color(14, 13, 21));
        jPanel2.setPreferredSize(new java.awt.Dimension(144, 44));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Empleados");
        lblTitulo.setMaximumSize(new java.awt.Dimension(100, 100));
        lblTitulo.setMinimumSize(new java.awt.Dimension(690, 79));
        lblTitulo.setPreferredSize(new java.awt.Dimension(144, 44));
        jPanel2.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(367, 0, 371, 38));

        jPanel3.setBackground(new java.awt.Color(91, 63, 105));

        jPanel7.setBackground(new java.awt.Color(255, 222, 212));
        jPanel7.setPreferredSize(new java.awt.Dimension(1090, 700));

        jPanel5.setBackground(new java.awt.Color(91, 63, 105));

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        TEmpleado.setBackground(new java.awt.Color(150, 86, 149));
        TEmpleado.setPreferredSize(new java.awt.Dimension(720, 650));

        tbEmpleados.setBackground(new java.awt.Color(164, 188, 188));
        tbEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "DUI", "Edad", "Telefono", "Correo", "Foto", "Sexo", "Empresa", "Estado de empleado", "Tipo de empleado", "Usuario"
            }
        ));
        tbEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEmpleadosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbEmpleadosMousePressed(evt);
            }
        });
        TEmpleado.setViewportView(tbEmpleados);

        jTabbedPane1.addTab("Vizualizar datos", TEmpleado);

        jPanel4.setBackground(new java.awt.Color(255, 222, 212));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblAgregarVisualizar.setBackground(new java.awt.Color(102, 102, 102));
        lblAgregarVisualizar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAgregarVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(lblAgregarVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 230, 230));

        btnSubir.setBackground(new java.awt.Color(164, 188, 188));
        btnSubir.setText("Subir foto");
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });
        jPanel4.add(btnSubir, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, -1, 50));

        lblNombre.setBackground(new java.awt.Color(255, 255, 255));
        lblNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/NombreEmpleado35px.png"))); // NOI18N
        lblNombre.setText("Nombre:");
        jPanel4.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });
        jPanel4.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 160, 34));

        lblApellido.setBackground(new java.awt.Color(255, 255, 255));
        lblApellido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Empleado35px.png"))); // NOI18N
        lblApellido.setText("Apellido:");
        jPanel4.add(lblApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, -1, -1));

        lblDui.setBackground(new java.awt.Color(255, 255, 255));
        lblDui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/DUI35px.png"))); // NOI18N
        lblDui.setText("DUI: (Sin guión)");
        jPanel4.add(lblDui, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, -1));

        txtDui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDuiActionPerformed(evt);
            }
        });
        txtDui.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDuiKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDuiKeyTyped(evt);
            }
        });
        jPanel4.add(txtDui, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 150, 35));

        lblEdad.setBackground(new java.awt.Color(255, 255, 255));
        lblEdad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Años35px.png"))); // NOI18N
        lblEdad.setText("Fecha Nacimiento");
        jPanel4.add(lblEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, 200, -1));

        lblSexo.setBackground(new java.awt.Color(255, 255, 255));
        lblSexo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Genero35px.png"))); // NOI18N
        lblSexo.setText("Sexo:");
        jPanel4.add(lblSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 110, -1));

        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino" }));
        jPanel4.add(cmbSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, 140, 35));

        lblTel.setBackground(new java.awt.Color(255, 255, 255));
        lblTel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Telefono35px.png"))); // NOI18N
        lblTel.setText("Teléfono: (Sin guión)");
        jPanel4.add(lblTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, -1, -1));

        lblCorreo.setBackground(new java.awt.Color(255, 255, 255));
        lblCorreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Correo35px.png"))); // NOI18N
        lblCorreo.setText("Correo:");
        jPanel4.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, -1, -1));

        txtTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelActionPerformed(evt);
            }
        });
        txtTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelKeyTyped(evt);
            }
        });
        jPanel4.add(txtTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, 150, 36));

        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });
        jPanel4.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 310, 152, 36));

        lblEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        lblEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Empresa35px.png"))); // NOI18N
        lblEmpresa.setText("Empresa:");
        jPanel4.add(lblEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 50, -1, -1));

        cmbEmpresa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(cmbEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, 150, 40));

        lblEstado.setBackground(new java.awt.Color(255, 255, 255));
        lblEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/TipoUsuario35px.png"))); // NOI18N
        lblEstado.setText("Estado empleado:");
        jPanel4.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 160, -1, -1));

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, 150, 36));

        lblUsuario.setBackground(new java.awt.Color(255, 255, 255));
        lblUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Usuario35px.png"))); // NOI18N
        lblUsuario.setText("Usuario:");
        jPanel4.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 260, -1, -1));

        lblTipo.setBackground(new java.awt.Color(255, 255, 255));
        lblTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/TipoUsuario35px.png"))); // NOI18N
        lblTipo.setText("Tipo de empleado:");
        jPanel4.add(lblTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 340, -1, -1));

        cmbUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbUsuario.setMinimumSize(new java.awt.Dimension(150, 34));
        cmbUsuario.setPreferredSize(new java.awt.Dimension(150, 34));
        cmbUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbUsuarioItemStateChanged(evt);
            }
        });
        jPanel4.add(cmbUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 300, -1, -1));

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 390, 146, 34));

        btnActualizar.setBackground(new java.awt.Color(164, 188, 188));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/ModificarEmpleado30px.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel4.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 143, 47));

        btnIngresar.setBackground(new java.awt.Color(164, 188, 188));
        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/AgregarEmpleado30px.png"))); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        jPanel4.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 143, 50));

        btnEliminar.setBackground(new java.awt.Color(164, 188, 188));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/EliminarEmpleado30px.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel4.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 169, 47));

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel4.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 150, 34));
        jPanel4.add(dtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, 150, 35));

        NMAX2.setForeground(new java.awt.Color(204, 0, 0));
        NMAX2.setText("Número máximo alcanzado");
        jPanel4.add(NMAX2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, 160, 40));

        NMAX3.setForeground(new java.awt.Color(204, 0, 0));
        NMAX3.setText("Número máximo alcanzado");
        jPanel4.add(NMAX3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 160, 40));

        lblCorreoInv.setForeground(new java.awt.Color(153, 0, 0));
        lblCorreoInv.setText("Correo Invalido");
        jPanel4.add(lblCorreoInv, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, -1, -1));
        jPanel4.add(lblRutaImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 140, 20));

        lblTelefonoE.setForeground(new java.awt.Color(255, 0, 0));
        lblTelefonoE.setText("Este telefono ya existe");
        jPanel4.add(lblTelefonoE, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 350, -1, -1));

        lblDuiE.setForeground(new java.awt.Color(255, 0, 0));
        lblDuiE.setText("Este Dui ya existe");
        jPanel4.add(lblDuiE, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, -1, -1));

        lblCorreoE.setForeground(new java.awt.Color(255, 0, 0));
        lblCorreoE.setText("Este correo ya existe");
        jPanel4.add(lblCorreoE, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 350, -1, -1));

        jScrollPane1.setViewportView(jPanel4);

        jTabbedPane1.addTab("Datos", jScrollPane1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1077, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Datos");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDuiActionPerformed

    }//GEN-LAST:event_txtDuiActionPerformed

    private void txtTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelActionPerformed

    }//GEN-LAST:event_txtTelActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        //Invocamos a un metodo en el controlador donde se evalue si el usuario esta registrad
        ActualizarEmpleado();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        insertar();
    }//GEN-LAST:event_btnSubirActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        //Ejecutamos metodo para ingresar empleado
        IngresarEmpleado();

        //  ActualizarIdEstadoUsuario();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void tbEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEmpleadosMouseClicked

        CargarUsuarioTotal();
        //Obtenemos el ancho del Label donde la colocaremos
        int anchoI = lblAgregarVisualizar.getWidth();
        //Obtenemos la altura del Label donde la colocaremos
        int altoI = lblAgregarVisualizar.getHeight();
        //Creamos validacion para ver que el campo esta nulo o no
        if (tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 11) == null) {
            cmbUsuario.setSelectedIndex(0);

            //Creamos una variable de tipo int para almacenar la filas
            int fila = tbEmpleados.rowAtPoint(evt.getPoint());
            //Le asignamos al Arraylist codigoE el metodo de Cargar Id de controlador de empleado
            codigoE = obj.CargarIDE();
            //Le asignamos al atributo iden la fila obtenida
            iden = codigoE.get(fila);
            //Enviamos el atributo iden al objeto CargarDatosMenu

            //Creamos un objetos llamado datos y le asignamos 
            Object[] datos = obj.CargarMenu(iden);

            //Generamos un try-catch por si surge algun error
            try {
                //Objeto que se encarga de guardar la imagen convertida
                BufferedImage img = null;
                //Cremos yn byte para enviar la imagen 
                byte[] imagenE = (byte[]) datos[7];

                try {
                    //Creamos objeto para leer los arreglos de bytes de la imagen
                    img = ImageIO.read(new ByteArrayInputStream(imagenE));
                    //a la variable seleccion le asignamos la imagen
                    seleccion = String.valueOf(img);

                } catch (IOException e) {
                    //Si surge error mostramos mensaje de error
                    NError.setVisible(true);
                    NError.TAnotiError.setText("¡Error al procesar!");
                    NError.lblTituloError.setText("La imagen no pudo ser cargada");
                }
                ImageIcon visualizar = new ImageIcon(img);
                //Le aignamos el icono
                lblAgregarVisualizar.setIcon(visualizar);
            } catch (Exception e) {
                //Si surge error mostramos mensaje de error
                NError.setVisible(true);
                NError.TAnotiError.setText("¡Error al procesar!");
                NError.lblTituloError.setText("La imagen no pudo ser "
                        + "\n"
                        + "cargada");
            }

            try {

                //Enviamos el id de empleado a guardar en controlador
                obj.setCodigoEVista(Integer.parseInt(String.valueOf(datos[0])));

                //Enviamos cada elemento a su respectivo componente
                txtNombre.setText(String.valueOf(String.valueOf(datos[1])));//Enviamos el nombre de empleado a su textfield
                txtApellido.setText(String.valueOf(String.valueOf(datos[2])));//Enviamos el apellido de empleado a su textfield
                txtDui.setText(String.valueOf(String.valueOf(datos[3])));//Enviamos el dui de empleado a su textfield
                SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");//Asignamos el formato de fecha
                dtEdad.setDate(Formato.parse(String.valueOf((datos[4]))));//Enviamos la fecha de nacimiento a su date time picker
                txtTel.setText(String.valueOf(String.valueOf((datos[5]))));//Enviamos el telefono de empleado a su textfield
                txtCorreo.setText(String.valueOf(String.valueOf(datos[6]))); //Enviamos el correo de empleado a su textfield

                cmbSexo.setSelectedItem(String.valueOf(datos[8]));//Enviamos el sexo a su combobox
                // cmbEmpresa.setSelectedIndex(Integer.parseInt(String.valueOf(datos[9])) - 1);//Enviamos la empresa a su combobox
                // cmbEstado.setSelectedIndex(Integer.parseInt(String.valueOf(datos[10])) - 1);//Enviamos el estado a su combobox
                // cmbTipo.setSelectedIndex(Integer.parseInt(String.valueOf(datos[11])) - 1);//Enviamos el tipo  de empleado a su combobox
                // cmbUsuario.setSelectedIndex(Integer.parseInt(String.valueOf(datos[12])) -1);//Enviamos el usuraio a su combobox

                cmbEmpresa.setSelectedItem(tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 8).toString());
                cmbEstado.setSelectedItem(tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 9).toString());
                cmbTipo.setSelectedItem(tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 10).toString());

            } catch (Exception e) {
                //Si surge error imprimimos error
                NError.setVisible(true);
                NError.TAnotiError.setText("¡Error al cargar los datos!");
                NError.lblTituloError.setText("Error al cargar");
            }
        } else {
            // String usuarioInicial = (String) cmbUsuario.getSelectedItem();
            int idUsuarioInicial = obj.ObtenerIdUsuarioInicial(tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 11).toString());
            obj.setCodigoUsuInicial(idUsuarioInicial);

            //Creamos una variable de tipo int para almacenar la filas
            int fila = tbEmpleados.rowAtPoint(evt.getPoint());
            //Le asignamos al Arraylist codigoE el metodo de Cargar Id de controlador de empleado
            codigoE = obj.CargarIDE();
            //Le asignamos al atributo iden la fila obtenida
            iden = codigoE.get(fila);
            //Enviamos el atributo iden al objeto CargarDatosMenu

            //Creamos un objetos llamado datos y le asignamos 
            Object[] datos = obj.CargarMenu(iden);

            //Generamos un try-catch por si surge algun error
            try {
                //Objeto que se encarga de guardar la imagen convertida
                BufferedImage img = null;
                //Cremos yn byte para enviar la imagen 
                byte[] imagenE = (byte[]) datos[7];

                try {
                    //Creamos objeto para leer los arreglos de bytes de la imagen
                    img = ImageIO.read(new ByteArrayInputStream(imagenE));
                    //a la variable seleccion le asignamos la imagen
                    seleccion = String.valueOf(img);

                } catch (IOException e) {
                    //Si surge error mostramos mensaje de error
                    NError.setVisible(true);
                    NError.TAnotiError.setText("¡Error al procesar!");
                    NError.lblTituloError.setText("La imagen no pudo ser cargada");
                }
                ImageIcon visualizar = new ImageIcon(img.getScaledInstance(anchoI, anchoI, Image.SCALE_SMOOTH));
                //Le aignamos el icono
                lblAgregarVisualizar.setIcon(visualizar);
            } catch (Exception e) {
                //Si surge error mostramos mensaje de error
                NError.setVisible(true);
                NError.TAnotiError.setText("¡Error al procesar!");
                NError.lblTituloError.setText("La imagen no pudo ser "
                        + "\n"
                        + "cargada");
            }

            try {
                //Enviamos el id de empleado a guardar en controlador
                obj.setCodigoEVista(Integer.parseInt(String.valueOf(datos[0])));

                //Enviamos cada elemento a su respectivo componente
                txtNombre.setText(String.valueOf(String.valueOf(datos[1])));//Enviamos el nombre de empleado a su textfield
                txtApellido.setText(String.valueOf(String.valueOf(datos[2])));//Enviamos el apellido de empleado a su textfield
                txtDui.setText(String.valueOf(String.valueOf(datos[3])));//Enviamos el dui de empleado a su textfield
                SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");//Asignamos el formato de fecha
                dtEdad.setDate(Formato.parse(String.valueOf((datos[4]))));//Enviamos la fecha de nacimiento a su date time picker
                txtTel.setText(String.valueOf(String.valueOf((datos[5]))));//Enviamos el telefono de empleado a su textfield
                txtCorreo.setText(String.valueOf(String.valueOf(datos[6]))); //Enviamos el correo de empleado a su textfield

                cmbSexo.setSelectedItem(String.valueOf(datos[8]));//Enviamos el sexo a su combobox
                // cmbEmpresa.setSelectedIndex(Integer.parseInt(String.valueOf(datos[9])) - 1);//Enviamos la empresa a su combobox
                // cmbEstado.setSelectedIndex(Integer.parseInt(String.valueOf(datos[10])) - 1);//Enviamos el estado a su combobox
                // cmbTipo.setSelectedIndex(Integer.parseInt(String.valueOf(datos[11])) - 1);//Enviamos el tipo  de empleado a su combobox
                // cmbUsuario.setSelectedIndex(Integer.parseInt(String.valueOf(datos[12])) -1);//Enviamos el usuraio a su combobox

                cmbEmpresa.setSelectedItem(tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 8).toString());
                cmbEstado.setSelectedItem(tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 9).toString());
                cmbUsuario.setSelectedItem(tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 11).toString());
                cmbTipo.setSelectedItem(tbEmpleados.getValueAt(tbEmpleados.getSelectedRow(), 10).toString());
            } catch (Exception e) {
                //Si surge error imprimimos error
                NError.setVisible(true);
                NError.TAnotiError.setText("¡Error al cargar los datos!");
                NError.lblTituloError.setText("Error al cargar");
            }
        }
    }//GEN-LAST:event_tbEmpleadosMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        EliminarEmpleado();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyTyped
        //Ejecutamos validacion de solo num
        validaciones.soloNumeros(evt, 1, txtTel.getText());
        //Validamos para que solo se puedan digitar 8 digitos
        if (txtTel.getText().length() > 7) {
            evt.consume();
            NMAX2.setVisible(true);
        } else {
            NMAX2.setVisible(false);
        }
    }//GEN-LAST:event_txtTelKeyTyped

    private void txtDuiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDuiKeyTyped
        //Ejecutamos validacion de solo numeros
        validaciones.soloNumeros(evt, 1, txtDui.getText());

        //Validamos que solo se puedan digitar los 8 digitos
        if (txtDui.getText().length() > 7) {
            evt.consume();
            NMAX3.setVisible(true);
        } else {
            NMAX3.setVisible(false);
        }
    }//GEN-LAST:event_txtDuiKeyTyped

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped

        //Ejecutamos validcion donde solo se podran letras,y espacio
        validaciones.soloLetras(evt, 2, txtNombre.getText());
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        //Ejecutamos validdacionm  solo se podran letras, y espacio
        validaciones.soloLetras(evt, 2, txtApellido.getText());
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        //Ejecutamos validacion de solo se podran letras, y espacio y caracteres especiales
        // validaciones.soloLetras(evt, 4, txtCorreo.getText());
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void txtCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyReleased

//        Invocamos a un metodo en el controlador donde se evalue si el dui existe
        obj.setCorreoE(txtCorreo.getText());
        boolean confirmar = obj.ConfirmarCorreo();
        //Verificamos si el resultado es null o false
        if (confirmar == true) {
            lblCorreoE.setVisible(true);
        } else {
            lblCorreoE.setVisible(false);
        }

        //Invocamos a un metodo en el controlador donde se evalue si el formato del correo es el correcto
        boolean confirm = ControladorEmpleados.ConfirmarCorreo(txtCorreo.getText());
        //Verificamos si el resultado es null o false
        if (confirm == true) {
            lblCorreoInv.setVisible(false);
        } else {
            lblCorreoInv.setVisible(true);
        }
    }//GEN-LAST:event_txtCorreoKeyReleased

    private void txtDuiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDuiKeyReleased
        //Invocamos a un metodo en el controlador donde se evalue si el dui existe
        obj.setDuiE(txtDui.getText());
        boolean confirm = obj.ConfirmarDui();
        //Verificamos si el resultado es null o false
        if (confirm == true) {
            lblDuiE.setVisible(true);
        } else {
            lblDuiE.setVisible(false);
        }
    }//GEN-LAST:event_txtDuiKeyReleased

    private void txtTelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyReleased
        //Invocamos a un metodo en el controlador donde se evalue si el telefono existe
        obj.setTelefonoE(txtTel.getText());
        boolean confirm = obj.ConfirmarTelefono();
        //Verificamos si el resultado es null o false
        if (confirm == true) {
            lblTelefonoE.setVisible(true);
        } else {
            lblTelefonoE.setVisible(false);
        }
    }//GEN-LAST:event_txtTelKeyReleased

    private void tbEmpleadosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEmpleadosMousePressed
        CargarUsuarioTotal();
    }//GEN-LAST:event_tbEmpleadosMousePressed

    private void cmbUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbUsuarioItemStateChanged

    }//GEN-LAST:event_cmbUsuarioItemStateChanged

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NMAX2;
    private javax.swing.JLabel NMAX3;
    private javax.swing.JScrollPane TEmpleado;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnSubir;
    private javax.swing.JComboBox<String> cmbEmpresa;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbSexo;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JComboBox<String> cmbUsuario;
    private com.toedter.calendar.JDateChooser dtEdad;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAgregarVisualizar;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblCorreoE;
    private javax.swing.JLabel lblCorreoInv;
    private javax.swing.JLabel lblDui;
    private javax.swing.JLabel lblDuiE;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEmpresa;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRutaImagen;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTel;
    private javax.swing.JLabel lblTelefonoE;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tbEmpleados;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDui;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
