/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.modeloEmpleado;
import Vista.FrmEmpleados;
import Vista.FrmNotiError;
import java.sql.Connection;
import java.sql.Date;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author pc
 */
public class ControladorEmpleados {

    //Cargamos combobox con el nombre de empresa 
    public static ResultSet CargarEmpresaControlador() {
        return Modelo.modeloEmpleado.CargarEmpresa();
    }

    //Cargamos combobox de estado de empleado
    public static ResultSet CargarEstadoEmpleadoController() {
        return Modelo.modeloEmpleado.CargarEstado();
    }
    //Cargamos combobox de tipo de empleado

    public static ResultSet CargarTipoEmpleadoController() {
        return Modelo.modeloEmpleado.CargarTipoEmpleado();
    }
    //Cargamos combobox de usuario

    public static ResultSet CargarUsuarioController() {
        return Modelo.modeloEmpleado.CargarUsuario();
    }

    public static ResultSet CargarUsuarioTotalController() {
        return Modelo.modeloEmpleado.CargarUsuarioTotal();
    }

    //Obtenemos la tabla 
    public static ResultSet CargarTablaController() {
        return Modelo.modeloEmpleado.getTablaResumida();
    }

    //Cargamos id de empresa 
    public ArrayList<Integer> idEmpresa() {
        return Modelo.modeloEmpleado.ConseguirIdEmpresa();
    }
    //Cargamo id de estado de empleado

    public static ArrayList<Integer> idEstado() {
        return modeloEmpleado.ConseguirIdEstado();
    }
    //Cargamos id de tipo de empleado

    public static ArrayList<Integer> idTipo() {
        return Modelo.modeloEmpleado.ConseguirIdTipo();
    }
    //Cargamos id de usuario

    public static ArrayList<Integer> idUsuario() {
        return Modelo.modeloEmpleado.ConseguirIdUsuario();
    }

    //Total de id de usuario
    public ArrayList<Integer> idUsuarioTotal() {
        return Modelo.modeloEmpleado.ConseguirIdUsuarioTotal();
    }
    //Cargamos id de empleado   

    public ArrayList<String> CargarIDE() {
        return Modelo.modeloEmpleado.CargasIDE();
    }

    //Regresamos datos para metodo de inserccion
    public boolean IngresarEmpleadoController() {
        return Modelo.modeloEmpleado.guardarEmpleado(nombreE, apellidoE, duiE, edadE, telefonoE, correoE, imagenP, sexoE, codigoEmpresa, codigoEstado, codigoTipo, codigoUsuario);
    }
    //Regresamos datos para metodo de actualizar datos

    public boolean ActualizarEmpleadoController() {
        return Modelo.modeloEmpleado.modificarEmpleado(nombreE, apellidoE, duiE, edadE, telefonoE, correoE, imagenP, sexoE, codigoEmpresa, codigoEstado, codigoTipo, codigoUsuario, codigoE);
    }

    //Regresamos datos de actualizar estado ed usaurio
    public boolean ActualizarIdEstadoUsuarioController() {
        return Modelo.modeloEmpleado.ActualizarIdEstadoUsuario(codigoUsuario);
    }
    
     public boolean ActualizarIdEstadoUsuarioControllerT(int idUsuarioInicial) {
        return Modelo.modeloEmpleado.ActulizarIdEstadoUsuarioT(idUsuarioInicial);
    }
     //Metodo para actualizar el usuario de empleado
      public boolean ActualizarCampoUsuarioController() {
        return Modelo.modeloEmpleado.ActualizarCampoIdUsuario(codigoUsuario);
    }

      public boolean ActualizarEliminar() {
        return Modelo.modeloEmpleado.ActualizarIdEstadoUsuarioEliminar(codigoUsuario);
    }
     
    //Regresamos datos para metodo de actualizar datos sin imagen
    public boolean ActuallizaSinImagenController() {
        return Modelo.modeloEmpleado.modificarSinImagen(nombreE, apellidoE, duiE, edadE, telefonoE, correoE, sexoE, codigoEmpresa, codigoEstado, codigoTipo, codigoUsuario, codigoE);
    }

    //Regresamos metodo para metodo de eliminar datos
    public boolean EliminarEmpleadoController() {
        return Modelo.modeloEmpleado.eliminarEmpleado(codigoE);
    }
    

    //Llamos el metodo de cargar datos de tabla que lo tiene almacenados
    public Object[] CargarMenu(String Identificador) {
        return Modelo.modeloEmpleado.CargarDatosMenu(Identificador);
    }
    
    public int ObtenerIdUsuario(String Nusuario){
        return Modelo.modeloEmpleado.ConsultarUsuario(Nusuario);
    }
    
    public int ObtenerIdUsuarioInicial(String NusuarioI){
        return Modelo.modeloEmpleado.ConsultarUsuario(NusuarioI);
    }

    modeloEmpleado obj = new modeloEmpleado();
    public static ArrayList<Integer> idEmpresa = new ArrayList<Integer>();
    public static ArrayList<Integer> idEstado = new ArrayList<Integer>();
    public static ArrayList<Integer> idU = new ArrayList<Integer>();
    public static ArrayList<Integer> idTipo = new ArrayList<Integer>();
     public static ArrayList<Integer> idUT = new ArrayList<Integer>();

    //Creamos metodo que defina el modelo  de empresa
    public DefaultComboBoxModel ObtenerEmpresas() {
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        ResultSet res = obj.CargarEmpresa();
        try {
            while (res.next()) {
                //Añadimos el id al array de  empresa
                idEmpresa.add(res.getInt("idEmpresa"));
                System.out.println("Arrays" + idEmpresa);
                lista.addElement(res.getString("nombreEmpresa"));
            }
            res.close();
        } catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido cargar las empresas");
            error.setVisible(true);
        }
        return lista;
    }

    //Creamos metodo que defina el modelo  de estado
    public DefaultComboBoxModel ObtenerEstados() {
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        ResultSet res = obj.CargarEstado();
        try {
            while (res.next()) {
                //Añadimos el id al array de los estados de usuario
                idEstado.add(res.getInt("idEstadoEmpleado"));
                System.out.println("Arrays" + idEstado);
                lista.addElement(res.getString("estadoEmpleado"));
            }
            res.close();
        } catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido cargar los estados de empleado");
            error.setVisible(true);
        }
        return lista;
    }

    //Creamos metodo que defina el modelo  de usuario
    public DefaultComboBoxModel ObtenerTipos() {
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        ResultSet res = obj.CargarTipoEmpleado();
        try {
            while (res.next()) {
                //Añadimos el id al array de los estados de usuario
                idTipo.add(res.getInt("idTipoEmpleado"));
                System.out.println("Arrays" + idTipo);
                lista.addElement(res.getString("tipoEmpleado"));
            }
            res.close();
        } catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido cargar los tipos de empleado");
            error.setVisible(true);
        }
        return lista;
    }

    //Creamos metodo que defina el modelo  de usuario
    public DefaultComboBoxModel ObtenerUsuarios() {
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        ResultSet res = obj.CargarUsuario();
        try {
                        lista.addElement("Sin seleccionar");

            while (res.next()) {
                //Añadimos el id al array de los estados de usuario
                idU.add(res.getInt("idUsuario"));
                System.out.println("Arrays" + idU);
                lista.addElement(res.getString("usuario"));
            }
            res.close();
        } catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido cargar los usuarios");
            error.setVisible(true);
        }
        return lista;
    }

     //Creamos metodo que defina el modelo  de usuario totales
    public DefaultComboBoxModel ObtenerUsuariosTotales() {
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        ResultSet res = obj.CargarUsuarioTotal();
        try {
                        lista.addElement("Sin seleccionar");

            while (res.next()) {
                //Añadimos el id al array de los estados de usuario
                idUT.add(res.getInt("idUsuario"));
                System.out.println("Arrays" + idUT);
                lista.addElement(res.getString("usuario"));
            }
            res.close();
        } catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido cargar los usuarios");
            error.setVisible(true);
        }
        return lista;
    }
    //Obtenemos id de empresa
    public int ObtenerIdEmpresa(int id) {
        id = idEmpresa.get(id);
        return id;
    }

    //Obtenemos id de estado de empleado
    public int ObtenerIdEstado(int id) {
        id = idEstado.get(id);
        return id;
    }

    //Obtenemos id de usuario
    public int ObtenerIdU(int id) {
        id = idU.get(id);
        return id;
    }
    //Obtenemos id de tipo de empleado

    public int ObtenerIdTipo(int id) {
        id = idTipo.get(id);
        return id;
    }
    //Obtenemos el id de usuario totales
     public int ObtenerIdUsuarioT(int id) {
        id = idUT.get(id);
        return id;
    }

    //Creamos metodo con expresiones regulares que verifique que el correo tenga la sintaxis correcta
    public static boolean ConfirmarCorreo(String str) {
        //Creamos el patron del correo que sera la sintaxis correcta
        // Patrón para validar el email
        Pattern patron = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]{3}))+");
        //Creamos un objeto de la clase matcher que comparará el patron con el email
        Matcher sintaxis = patron.matcher(str);
        if (sintaxis.find() == true) {
            return true;
        } else {
            return false;

        }
    }

    public int validarEdad(java.util.Date fecha) {
        //Creamos un objeto del calendario y obtenemos su instancia
        Calendar calendario = Calendar.getInstance();
        //Añadimos al calendiario la fecha de nacimiento del empleado
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
        //retornamos el peridodo convertido con los años que han pasado
        return periodo.getYears();
    }

    //Creamos metodo que indique si existe el usuario
    public boolean ConfirmarDui() {
        //Creamos objeto del modelo
        modeloEmpleado obj = new modeloEmpleado();
        //Creamos objeto de tipo boleano para confirmar
        boolean confirm = false;
        //Creamos objeto de tipo resultSet para guardar el resultado
        ResultSet res = obj.ObtenerDui(duiE);
        //Verificamos si en el resultado obtenido es null o existente
        try {
            if (res.next()) {
                //Como se encontro resultado la confirmación sera verdadera                
                confirm = true;
            } else {
                //Como no se encontro resultado la confirmación sera falsa
                confirm = false;
            }
        } catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido encontrar el usuario");
            error.setVisible(true);
        }
        //retornamos la confirmación
        return confirm;
    }

    //Creamos metodo que indique si existe el telefono
    public boolean ConfirmarTelefono() {
        //Creamos objeto del modelo
        modeloEmpleado obj = new modeloEmpleado();
        //Creamos objeto de tipo boleano para confirmar
        boolean confirm = false;
        //Creamos objeto de tipo resultSet para guardar el resultado
        ResultSet res = obj.ObtenerTelefono(telefonoE);
        //Verificamos si en el resultado obtenido es null o existente
        try {
            if (res.next()) {
                //Como se encontro resultado la confirmación sera verdadera                
                confirm = true;
            } else {
                //Como no se encontro resultado la confirmación sera falsa
                confirm = false;
            }
        } catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido encontrar el telefono");
            error.setVisible(true);
        }
        //retornamos la confirmación
        return confirm;
    }

    //Creamos metodo que indique si existe el correo
    public boolean ConfirmarCorreo() {
        //Creamos objeto del modelo
        modeloEmpleado obj = new modeloEmpleado();
        //Creamos objeto de tipo boleano para confirmar
        boolean confirm = false;
        //Creamos objeto de tipo resultSet para guardar el resultado
        ResultSet res = obj.ObtenerCorreo(correoE);
        //Verificamos si en el resultado obtenido es null o existente
        try {
            if (res.next()) {
                //Como se encontro resultado la confirmación sera verdadera                
                confirm = true;
            } else {
                //Como no se encontro resultado la confirmación sera falsa
                confirm = false;
            }
        } catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido encontrar el telefono");
            error.setVisible(true);
        }
        //retornamos la confirmación
        return confirm;
    }

    //Creamos metodo que indique si existe el usuario
    public boolean ConfirmarUsuario() {
        //Creamos objeto del modelo
        modeloEmpleado obj = new modeloEmpleado();
        //Creamos objeto de tipo boleano para confirmar
        boolean confirm = false;
        //Creamos objeto de tipo resultSet para guardar el resultado
        ResultSet res = obj.ObtenerUsuario(codigoUsuario);
        //Verificamos si en el resultado obtenido es null o existente
        try {
            if (res.next()) {
                //Como se encontro resultado la confirmación sera verdadera                
                confirm = true;
            } else {
                //Como no se encontro resultado la confirmación sera falsa
                confirm = false;
            }
        } catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido encontrar el usuario");
            error.setVisible(true);
        }
        //retornamos la confirmación
        return confirm;
    }
    
     //Creamos metodo que indique si existe el usuario totales
    public boolean ConfirmarUsuarioT() {
        //Creamos objeto del modelo
        modeloEmpleado obj = new modeloEmpleado();
        //Creamos objeto de tipo boleano para confirmar
        boolean confirm = false;
        //Creamos objeto de tipo resultSet para guardar el resultado
        ResultSet res = obj.ObtenerUsuarioT(codigoUsuario);
        //Verificamos si en el resultado obtenido es null o existente
        try {
            if (res.next()) {
                //Como se encontro resultado la confirmación sera verdadera                
                confirm = true;
            } else {
                //Como no se encontro resultado la confirmación sera falsa
                confirm = false;
            }
        } catch (Exception e) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Error!No se ha podido encontrar el usuario");
            error.setVisible(true);
        }
        //retornamos la confirmación
        return confirm;
    }

    
    //Atributos
    private Integer codigoE; //Atributo de codigo de empleado
    private static String nombreE;// Atributo dde nombre de empleado
    private static String apellidoE;// Atributo de apellido de empleado
    private static String duiE;// Atributo dde dui de empleado
    private static Date edadE;// Atributo de fecha de nacimeinto de de empleado
    private static String correoE;// Atributo de correo de empleado
    private static byte[] logoE;// Atributo dd logo de empleado
    private static String sexoE;// Atributo de sexo de empleado
    private static String telefonoE;// Atributo de telefono de empleado
    private static String direccionE;// Atributo de direccion de empleado
    private static Integer codigoEmpresa;// Atributo de id de emmpresa
    private static Integer codigoEstado;// Atributo de id de estado de empleado
    private static Integer codigoTipo;// Atributo de id tipo de empleado
    private static Integer codigoUsuario;// Atributo de id de usuario
    private static String imagenP;// Atributo de imagen de empleado
    private static ResultSet datosMenu;// Atributo de resultset para guardar datos

    private static Integer codigoEVista;// Atributo dde id de empleado guarado en vista
    private Date añoNaci;
    private static String usuario;
    private static Integer idUsuarioActu;
    private static Integer codigoUsuInicial;
    //Se generan get y set de cada atributo
    /**
     * @return the nombreE
     */
    public static String getNombreE() {
        return nombreE;
    }

    /**
     * @param aNombreE the nombreE to set
     */
    public static void setNombreE(String aNombreE) {
        nombreE = aNombreE;
    }

    /**
     * @return the apellidoE
     */
    public static String getApellidoE() {
        return apellidoE;
    }

    /**
     * @param aApellidoE the apellidoE to set
     */
    public static void setApellidoE(String aApellidoE) {
        apellidoE = aApellidoE;
    }

    /**
     * @return the duiE
     */
    public static String getDuiE() {
        return duiE;
    }

    /**
     * @param aDuiE the duiE to set
     */
    public static void setDuiE(String aDuiE) {
        duiE = aDuiE;
    }

    /**
     * @return the edadE
     */
    public static Date getEdadE() {
        return edadE;
    }

    /**
     * @param aEdadE the edadE to set
     */
    public static void setEdadE(Date aEdadE) {
        edadE = aEdadE;
    }

    /**
     * @return the correoE
     */
    public static String getCorreoE() {
        return correoE;
    }

    /**
     * @param aCorreoE the correoE to set
     */
    public static void setCorreoE(String aCorreoE) {
        correoE = aCorreoE;
    }

    /**
     * @return the logoE
     */
    public static byte[] getLogoE() {
        return logoE;
    }

    /**
     * @param aLogoE the logoE to set
     */
    public static void setLogoE(byte[] aLogoE) {
        logoE = aLogoE;
    }

    /**
     * @return the sexoE
     */
    public static String getSexoE() {
        return sexoE;
    }

    /**
     * @param aSexoE the sexoE to set
     */
    public static void setSexoE(String aSexoE) {
        sexoE = aSexoE;
    }

    public static String getTelefonoE() {
        return telefonoE;
    }

    /**
     * @param aTelefonoE the telefonoE to set
     */
    public static void setTelefonoE(String aTelefonoE) {
        telefonoE = aTelefonoE;
    }

    /**
     * @return the direccionE
     */
    public static String getDireccionE() {
        return direccionE;
    }

    /**
     * @param aDireccionE the direccionE to set
     */
    public static void setDireccionE(String aDireccionE) {
        direccionE = aDireccionE;
    }

    /**
     * @return the codigoEmpresa
     */
    public static Integer getCodigoEmpresa() {
        return codigoEmpresa;
    }

    /**
     * @param aCodigoEmpresa the codigoEmpresa to set
     */
    public static void setCodigoEmpresa(Integer aCodigoEmpresa) {
        codigoEmpresa = aCodigoEmpresa;
    }

    /**
     * @return the codigoEstado
     */
    public static Integer getCodigoEstado() {
        return codigoEstado;
    }

    /**
     * @param aCodigoEstado the codigoEstado to set
     */
    public static void setCodigoEstado(Integer aCodigoEstado) {
        codigoEstado = aCodigoEstado;
    }

    /**
     * @return the codigoTipo
     */
    public static Integer getCodigoTipo() {
        return codigoTipo;
    }

    /**
     * @param aCodigoTipo the codigoTipo to set
     */
    public static void setCodigoTipo(Integer aCodigoTipo) {
        codigoTipo = aCodigoTipo;
    }

    /**
     * @return the codigoUsuario
     */
    public static Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    /**
     * @param aCodigoUsuario the codigoUsuario to set
     */
    public static void setCodigoUsuario(Integer aCodigoUsuario) {
        codigoUsuario = aCodigoUsuario;
    }

    /**
     * @return the imagenP
     */
    public static String getImagenP() {
        return imagenP;
    }

    /**
     * @param aImagenP the imagenP to set
     */
    public static void setImagenP(String aImagenP) {
        imagenP = aImagenP;
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

    /**
     *
     *
     *
     * /
     *
     **
     * @return the codigoE
     */
    public Integer getCodigoE() {
        return codigoE;
    }

    /**
     * @param codigoE the codigoE to set
     */
    public void setCodigoE(Integer codigoE) {
        this.codigoE = codigoE;
    }

    /**
     * @return the codigoEVista
     */
    public Integer getCodigoEVista() {
        return codigoEVista;
    }

    /**
     * @param codigoEVista the codigoEVista to set
     */
    public void setCodigoEVista(Integer codigoEVista) {
        this.codigoEVista = codigoEVista;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the idUsuarioActu
     */
    public Integer getIdUsuarioActu() {
        return idUsuarioActu;
    }

    /**
     * @param idUsuarioActu the idUsuarioActu to set
     */
    public void setIdUsuarioActu(Integer idUsuarioActu) {
        this.idUsuarioActu = idUsuarioActu;
    }

    /**
     * @return the codigoUsuInicial
     */
    public Integer getCodigoUsuInicial() {
        return codigoUsuInicial;
    }

    /**
     * @param codigoUsuInicial the codigoUsuInicial to set
     */
    public void setCodigoUsuInicial(Integer codigoUsuInicial) {
        this.codigoUsuInicial = codigoUsuInicial;
    }

}
