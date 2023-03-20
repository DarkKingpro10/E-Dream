/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorInventario;
import Controlador.ControladorVariables;
import Controlador.Validaciones;
import Recursos_Tipografias.Fuentes;
import Modelo.ModeloInventario;
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
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oliver
 */
public class FrmInventario extends javax.swing.JPanel {
    //Validar si ya esta abierto el buscador
    public static int ValidarBuscador = 0;
    
    //Creación de los objetos
    ModeloInventario inventario = new ModeloInventario();
    ControladorInventario controlador = new ControladorInventario();
    Validaciones validaciones = new Validaciones();
    FrmNoti2 NInsert = new FrmNoti2();
    FrmNoti3 NDelete = new FrmNoti3();
    FrmNotiError NError = new FrmNotiError();
    //Creación de los modelos para cargar las tablas
    DefaultTableModel tablaVenta = new DefaultTableModel();
    DefaultTableModel tablaRenta = new DefaultTableModel();
    public static SpinnerNumberModel Spinner = new SpinnerNumberModel();
    //Vectores que guardarán los codigos de los productos
    ArrayList<String> codigos;
    ArrayList<String> codigoV;
    //Selecciones
    public static String seleccion;
    public static String iden;
    //Vectos de bytes que guardará la imagen seleccionada
    byte[] imagen;
    //Se selecciona la fecha de ahora
    LocalDate Fecha = LocalDate.now();
    SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd"); //Se estable el formato de fehca
    ZoneId systemTimeZone = ZoneId.systemDefault();
    ZonedDateTime zonedDateTime = Fecha.atStartOfDay(systemTimeZone);
    Date FechaActual = Date.from(zonedDateTime.toInstant());

    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Morado = new Color(94,45,79);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);
    
    /**
     * Creates new form frmInventario
     */
    public FrmInventario() {
        initComponents();
        Colores();
        //Clase que colocará la fuente
        Fuentes tipoFuente;
        tipoFuente = new Fuentes();
        
        btnEliminar.setVisible(false);
        //Colocación de la fuente a los componentes
        TableRenta.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        tableVenta.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnAgregar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnAgregarImagen.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnEliminar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnModificar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnVisualizarBuscar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbAgregarCategoria.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbAgregarMarca.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbAgregarCondicion.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbModificarCategoria.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbModificarEstado.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbModificarTipo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbModificarDistribuidor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarCantidad.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarCategoria.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarDescripción.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarFechaEntrega.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarFechaFin.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarMarca.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarNombre.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarPrecio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarVisualizar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarDistribuidor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregararMateriales.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarMaterial.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarColor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtAgregarNombre.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtAgregarPrecio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        spnAgregarCantidad.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbAgregarDistribuidor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarTamaño.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblVTipo1.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVTipoInventario.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarTipo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbAgregarTipo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarTipoIn.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarTipoIn1.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbModificarTipoInventario.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarTipo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarCantidad.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarCategoria.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarDescripción.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarEstado.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarFechaEntrega.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarFechaFin.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarTipo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarDistribuidor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarNombre.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarPrecio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarVisualizar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblNombreProducto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblTituloInventario.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 40));
        tbInventarioVenta.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        spnCantidad.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtModificarDescuento.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtModificarNombre.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnModificarImagen.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtModificarPrecio1.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblVCantidad.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblVCategoria.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblVColor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblVEstado.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblVFechaEntrega.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblVFechaInicio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblVMaterial.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblVNombre.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblVPrecio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblVTipo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblvDistribuidor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblvMarca.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblvTamaño.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblVDescuento.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVDescuento.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));

        txtVCantidad.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVCategoria.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVCodigo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVColor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVDistribuidor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVEstado.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVFechaEntrega.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVFechaInicio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVMarca.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVMaterial.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVNombre.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVPrecio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVTamaño.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVTipo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));

        cmbAgregarColor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbAgregarMaterial.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbAgregarTamanio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbModificarMaterial.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbModificarTamaño.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbModificarMarca.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbModificarColor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        dcrModificarFF.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        dcrModificarFI.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        dcrFechaEntrega.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        dcrFechaFin.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarDescuento.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtDescuento.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));

        lblAvisoDescuento.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 10));
        lblModificarTipo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarDistribuidor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarMarca.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarColor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarMateriales.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarTamaño.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txaAgregarDescripción.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txaModificarDescripción.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        tbpMenu.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        tbpInventario.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        this.cmbModificarTipo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        this.cmbAgregarTipo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAgregarTipo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblModificarTipo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        tbpGeneral.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));

        //Deshabilitar edición en los componentes de visualizar
        txtVCodigo.setEditable(false);
        txtVMarca.setEditable(false);
        txtVTipo.setEditable(false);
        txtVFechaEntrega.setEditable(false);
        txtVFechaInicio.setEditable(false);
        txtVPrecio.setEditable(false);
        txtVCantidad.setEditable(false);
        txtVNombre.setEditable(false);
        txtVColor.setEditable(false);
        txtVTipoInventario.setEditable(false);
        txtVEstado.setEditable(false);
        txtVDistribuidor.setEditable(false);
        txtVTamaño.setEditable(false);
        txtVCategoria.setEditable(false);
        txtVMaterial.setEditable(false);
        txtVDescuento.setEditable(false);
        
        //Se cargan los modelos de los combobox
        this.cmbAgregarCategoria.setModel(inventario.CargarCategoriascmb());
        this.cmbModificarTipoInventario.setModel(inventario.CargarTipoInventariocmb());
        this.cmbAgregarTipo.setModel(inventario.CargarTipoInventariocmb());
        this.cmbAgregarTamanio.setModel(inventario.CargarTamaniocmb());
        this.cmbAgregarDistribuidor.setModel(inventario.CargarDistribuidorcmb());
        this.cmbAgregarColor.setModel(inventario.CargarColorcmb());
        this.cmbAgregarMarca.setModel(inventario.CargarMarcacmb());
        this.cmbAgregarCondicion.setModel(inventario.CargarTipocmb());
        this.cmbAgregarMaterial.setModel(inventario.CargarMaterialcmb());
        this.cmbModificarCategoria.setModel(inventario.CargarCategoriascmb());
        this.cmbModificarEstado.setModel(inventario.CargarEstadoscmb());
        this.cmbModificarTipo.setModel(inventario.CargarTipocmb());
        this.cmbModificarMaterial.setModel(inventario.CargarMaterialcmb());
        this.cmbModificarMarca.setModel(inventario.CargarMarcacmb());
        this.cmbModificarDistribuidor.setModel(inventario.CargarDistribuidorcmb());
        this.cmbModificarColor.setModel(inventario.CargarColorcmb());
        this.cmbModificarTamaño.setModel(inventario.CargarTamaniocmb());
        //Cargar las tablas de los inevntario Venta y Renta
        lblAvisoDescuento.setVisible(false);
        lblAvisoDescuento1.setVisible(false);
        mostrarDatos();
        mostrarDatosVenta();
    }

    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                PanelVisualizarImagen.setBackground(Piel);
                PanelAgregar.setBackground(Piel);
                jPanel8.setBackground(Piel);
                jPanel7.setBackground(Piel);
                jPanel1.setBackground(Piel);
                PanelModificar.setBackground(Piel);
                jPanel6.setBackground(Piel);
                jPanel5.setBackground(Piel);
                jPanel4.setBackground(Piel);  
                PanelTitulo.setBackground(Oscuro);
                break;
            case 2://Claro
                PanelVisualizarImagen.setBackground(Color.white);
                PanelAgregar.setBackground(Color.white);
                PanelTitulo.setBackground(AzulOscuro);
                jPanel8.setBackground(Color.white);
                jPanel7.setBackground(Color.white);
                jPanel1.setBackground(Color.white);
                PanelModificar.setBackground(Color.white);
                jPanel6.setBackground(Color.white);
                jPanel5.setBackground(Color.white);
                jPanel4.setBackground(Color.white);
                break;
            default:
                PanelVisualizarImagen.setBackground(Piel);
                PanelAgregar.setBackground(Piel);
                PanelTitulo.setBackground(Oscuro);
                jPanel8.setBackground(Piel);
                jPanel7.setBackground(Piel);
                jPanel1.setBackground(Piel);
                PanelModificar.setBackground(Piel);
                jPanel6.setBackground(Piel);
                jPanel5.setBackground(Piel);
                jPanel4.setBackground(Piel);  
                break;
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

        PanelTitulo = new javax.swing.JPanel();
        lblTituloInventario = new javax.swing.JLabel();
        tbpGeneral = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        tbpInventario = new javax.swing.JTabbedPane();
        spInventarioRenta = new javax.swing.JScrollPane();
        TableRenta = new javax.swing.JTable();
        tbInventarioVenta = new javax.swing.JScrollPane();
        tableVenta = new javax.swing.JTable();
        PanelVisualizarImagen = new javax.swing.JPanel();
        lblIconoBuscar = new javax.swing.JLabel();
        btnVisualizarBuscar = new javax.swing.JButton();
        lblNombreProducto = new javax.swing.JLabel();
        PanelImagen = new javax.swing.JPanel();
        lblImagenProducto = new javax.swing.JLabel();
        lblVNombre = new javax.swing.JLabel();
        lblVPrecio = new javax.swing.JLabel();
        lblVCantidad = new javax.swing.JLabel();
        lblVFechaEntrega = new javax.swing.JLabel();
        lblVFechaInicio = new javax.swing.JLabel();
        lblvTamaño = new javax.swing.JLabel();
        lblVColor = new javax.swing.JLabel();
        lblvMarca = new javax.swing.JLabel();
        lblvDistribuidor = new javax.swing.JLabel();
        lblVTipo = new javax.swing.JLabel();
        lblVEstado = new javax.swing.JLabel();
        lblVCategoria = new javax.swing.JLabel();
        lblVMaterial = new javax.swing.JLabel();
        txtVCodigo = new javax.swing.JTextField();
        txtVNombre = new javax.swing.JTextField();
        txtVPrecio = new javax.swing.JTextField();
        txtVCantidad = new javax.swing.JTextField();
        txtVFechaEntrega = new javax.swing.JTextField();
        txtVFechaInicio = new javax.swing.JTextField();
        txtVTamaño = new javax.swing.JTextField();
        txtVColor = new javax.swing.JTextField();
        txtVMarca = new javax.swing.JTextField();
        txtVDistribuidor = new javax.swing.JTextField();
        txtVTipo = new javax.swing.JTextField();
        txtVEstado = new javax.swing.JTextField();
        txtVCategoria = new javax.swing.JTextField();
        txtVMaterial = new javax.swing.JTextField();
        txtVTipoInventario = new javax.swing.JTextField();
        lblVTipo1 = new javax.swing.JLabel();
        lblVDescuento = new javax.swing.JLabel();
        txtVDescuento = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        tbpMenu = new javax.swing.JTabbedPane();
        PanelAgregar = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnAgregarImagen = new javax.swing.JButton();
        lblAgregarVisualizar = new javax.swing.JLabel();
        lblAgregarFechaFin = new javax.swing.JLabel();
        lblAgregarFechaEntrega = new javax.swing.JLabel();
        dcrFechaEntrega = new com.toedter.calendar.JDateChooser();
        dcrFechaFin = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        lblAgregarDescripción = new javax.swing.JLabel();
        spAgregarDescripción = new javax.swing.JScrollPane();
        txaAgregarDescripción = new javax.swing.JTextArea();
        cmbAgregarTamanio = new javax.swing.JComboBox<>();
        cmbAgregarTipo = new javax.swing.JComboBox<>();
        lblAgregarTipo = new javax.swing.JLabel();
        lblAgregarTamaño = new javax.swing.JLabel();
        cmbAgregarMaterial = new javax.swing.JComboBox<>();
        lblAgregarMaterial = new javax.swing.JLabel();
        lblAgregarColor = new javax.swing.JLabel();
        cmbAgregarColor = new javax.swing.JComboBox<>();
        lblAgregarDescuento = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        lblAvisoDescuento = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblAgregarNombre = new javax.swing.JLabel();
        lblAgregarCantidad = new javax.swing.JLabel();
        txtAgregarNombre = new javax.swing.JTextField();
        lblAgregarPrecio = new javax.swing.JLabel();
        txtAgregarPrecio = new javax.swing.JTextField();
        spnAgregarCantidad = new javax.swing.JSpinner();
        lblAgregarCategoria = new javax.swing.JLabel();
        lblAgregarDistribuidor = new javax.swing.JLabel();
        cmbAgregarDistribuidor = new javax.swing.JComboBox<>();
        cmbAgregarCategoria = new javax.swing.JComboBox<>();
        lblAgregararMateriales = new javax.swing.JLabel();
        lblAgregarMarca = new javax.swing.JLabel();
        cmbAgregarMarca = new javax.swing.JComboBox<>();
        cmbAgregarCondicion = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        PanelModificar = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        PanelModificarVisualizarImagen = new javax.swing.JPanel();
        lblModificarVisualizar = new javax.swing.JLabel();
        btnModificarImagen = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblModificarFechaEntrega = new javax.swing.JLabel();
        dcrModificarFI = new com.toedter.calendar.JDateChooser();
        lblModificarFechaFin = new javax.swing.JLabel();
        dcrModificarFF = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        spModificarDescripción = new javax.swing.JScrollPane();
        txaModificarDescripción = new javax.swing.JTextArea();
        lblModificarDescripción = new javax.swing.JLabel();
        lblModificarMateriales = new javax.swing.JLabel();
        cmbModificarMaterial = new javax.swing.JComboBox<>();
        lblModificarTamaño = new javax.swing.JLabel();
        cmbModificarTamaño = new javax.swing.JComboBox<>();
        lblModificarMarca = new javax.swing.JLabel();
        lblModificarColor = new javax.swing.JLabel();
        cmbModificarMarca = new javax.swing.JComboBox<>();
        cmbModificarColor = new javax.swing.JComboBox<>();
        lblModificarTipoIn = new javax.swing.JLabel();
        cmbModificarTipoInventario = new javax.swing.JComboBox<>();
        lblModificarTipoIn1 = new javax.swing.JLabel();
        txtModificarDescuento = new javax.swing.JTextField();
        lblAvisoDescuento1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblModificarNombre = new javax.swing.JLabel();
        txtModificarNombre = new javax.swing.JTextField();
        lblModificarCantidad = new javax.swing.JLabel();
        lblModificarPrecio = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();
        lblModificarEstado = new javax.swing.JLabel();
        lblModificarCategoria = new javax.swing.JLabel();
        cmbModificarEstado = new javax.swing.JComboBox<>();
        cmbModificarCategoria = new javax.swing.JComboBox<>();
        lblModificarDistribuidor = new javax.swing.JLabel();
        lblModificarTipo = new javax.swing.JLabel();
        cmbModificarDistribuidor = new javax.swing.JComboBox<>();
        cmbModificarTipo = new javax.swing.JComboBox<>();
        txtModificarPrecio1 = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(1090, 620));
        setPreferredSize(new java.awt.Dimension(1090, 620));

        PanelTitulo.setBackground(new java.awt.Color(14, 13, 21));
        PanelTitulo.setAlignmentX(0.0F);
        PanelTitulo.setAlignmentY(0.0F);
        PanelTitulo.setPreferredSize(new java.awt.Dimension(1090, 0));
        PanelTitulo.setLayout(null);

        lblTituloInventario.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lblTituloInventario.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloInventario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloInventario.setText("Inventario");
        PanelTitulo.add(lblTituloInventario);
        lblTituloInventario.setBounds(370, 0, 410, 58);

        tbpInventario.setBackground(new java.awt.Color(46, 14, 54));
        tbpInventario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbpInventario.setPreferredSize(new java.awt.Dimension(812, 220));

        spInventarioRenta.setBackground(new java.awt.Color(46, 14, 54));
        spInventarioRenta.setBorder(null);

        TableRenta.setBackground(new java.awt.Color(164, 188, 188));
        TableRenta.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        TableRenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Marca", "Estado", "Costo", "Cantidad"
            }
        ));
        TableRenta.setGridColor(new java.awt.Color(0, 0, 0));
        TableRenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableRentaMouseClicked(evt);
            }
        });
        spInventarioRenta.setViewportView(TableRenta);
        if (TableRenta.getColumnModel().getColumnCount() > 0) {
            TableRenta.getColumnModel().getColumn(0).setMinWidth(40);
            TableRenta.getColumnModel().getColumn(0).setPreferredWidth(40);
            TableRenta.getColumnModel().getColumn(1).setMinWidth(20);
            TableRenta.getColumnModel().getColumn(1).setPreferredWidth(20);
            TableRenta.getColumnModel().getColumn(2).setMinWidth(20);
            TableRenta.getColumnModel().getColumn(2).setPreferredWidth(20);
            TableRenta.getColumnModel().getColumn(3).setPreferredWidth(10);
            TableRenta.getColumnModel().getColumn(4).setMinWidth(10);
            TableRenta.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        tbpInventario.addTab("Inventario Renta ", new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Inventario20px.png")), spInventarioRenta); // NOI18N

        tbInventarioVenta.setBorder(null);

        tableVenta.setBackground(new java.awt.Color(164, 188, 188));
        tableVenta.setModel(TableRenta.getModel());
        tableVenta.setGridColor(new java.awt.Color(0, 0, 0));
        tableVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVentaMouseClicked(evt);
            }
        });
        tbInventarioVenta.setViewportView(tableVenta);
        if (tableVenta.getColumnModel().getColumnCount() > 0) {
            tableVenta.getColumnModel().getColumn(0).setHeaderValue("Nombre");
            tableVenta.getColumnModel().getColumn(1).setHeaderValue("Marca");
            tableVenta.getColumnModel().getColumn(2).setHeaderValue("Estado");
            tableVenta.getColumnModel().getColumn(3).setHeaderValue("Costo");
            tableVenta.getColumnModel().getColumn(4).setHeaderValue("Cantidad");
        }

        tbpInventario.addTab("Inventario venta ", new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Inventario20px.png")), tbInventarioVenta); // NOI18N

        PanelVisualizarImagen.setBackground(new java.awt.Color(255, 222, 212));

        lblIconoBuscar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIconoBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Buscador50px.png"))); // NOI18N

        btnVisualizarBuscar.setBackground(new java.awt.Color(164, 188, 188));
        btnVisualizarBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnVisualizarBuscar.setText("Buscar existencia");
        btnVisualizarBuscar.setBorder(new javax.swing.border.MatteBorder(null));
        btnVisualizarBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarBuscarActionPerformed(evt);
            }
        });

        lblNombreProducto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNombreProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreProducto.setText("Codigo:");

        PanelImagen.setBackground(new java.awt.Color(255, 222, 212));
        PanelImagen.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        PanelImagen.setForeground(new java.awt.Color(0, 25, 0));
        PanelImagen.setPreferredSize(new java.awt.Dimension(370, 404));
        PanelImagen.setLayout(new java.awt.BorderLayout());

        lblImagenProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/NoImage200px.png"))); // NOI18N
        PanelImagen.add(lblImagenProducto, java.awt.BorderLayout.CENTER);

        lblVNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVNombre.setText("Nombre:");

        lblVPrecio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVPrecio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVPrecio.setText("Precio:");

        lblVCantidad.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVCantidad.setText("Cantidad:");

        lblVFechaEntrega.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVFechaEntrega.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVFechaEntrega.setText("Fecha Entrega:");

        lblVFechaInicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVFechaInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVFechaInicio.setText("Fecha Inicio:");

        lblvTamaño.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblvTamaño.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblvTamaño.setText("Tamaño:");

        lblVColor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVColor.setText("Color:");

        lblvMarca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblvMarca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblvMarca.setText("Marca:");

        lblvDistribuidor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblvDistribuidor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblvDistribuidor.setText("Distribuidor:");

        lblVTipo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVTipo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVTipo.setText("Estado:");

        lblVEstado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVEstado.setText("Condición:");

        lblVCategoria.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVCategoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVCategoria.setText("Categoria:");

        lblVMaterial.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVMaterial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVMaterial.setText("Material:");

        txtVCodigo.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVCodigo.setPreferredSize(new java.awt.Dimension(250, 30));

        txtVNombre.setMinimumSize(new java.awt.Dimension(250, 30));

        txtVPrecio.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVPrecio.setPreferredSize(new java.awt.Dimension(250, 30));

        txtVCantidad.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVCantidad.setPreferredSize(new java.awt.Dimension(250, 30));

        txtVFechaEntrega.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVFechaEntrega.setPreferredSize(new java.awt.Dimension(250, 30));

        txtVFechaInicio.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVFechaInicio.setPreferredSize(new java.awt.Dimension(250, 30));

        txtVTamaño.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVTamaño.setPreferredSize(new java.awt.Dimension(250, 30));

        txtVColor.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVColor.setPreferredSize(new java.awt.Dimension(250, 30));

        txtVMarca.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVMarca.setPreferredSize(new java.awt.Dimension(250, 30));

        txtVDistribuidor.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVDistribuidor.setPreferredSize(new java.awt.Dimension(250, 30));

        txtVTipo.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVTipo.setPreferredSize(new java.awt.Dimension(250, 30));

        txtVEstado.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVEstado.setPreferredSize(new java.awt.Dimension(250, 30));

        txtVCategoria.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVCategoria.setPreferredSize(new java.awt.Dimension(250, 30));

        txtVMaterial.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVMaterial.setPreferredSize(new java.awt.Dimension(250, 30));

        txtVTipoInventario.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVTipoInventario.setPreferredSize(new java.awt.Dimension(250, 30));

        lblVTipo1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVTipo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVTipo1.setText("Tipo:");

        lblVDescuento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVDescuento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVDescuento.setText("Descuento:");

        txtVDescuento.setMinimumSize(new java.awt.Dimension(250, 30));
        txtVDescuento.setPreferredSize(new java.awt.Dimension(250, 30));

        javax.swing.GroupLayout PanelVisualizarImagenLayout = new javax.swing.GroupLayout(PanelVisualizarImagen);
        PanelVisualizarImagen.setLayout(PanelVisualizarImagenLayout);
        PanelVisualizarImagenLayout.setHorizontalGroup(
            PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVisualizarImagenLayout.createSequentialGroup()
                .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVisualizarImagenLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblIconoBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PanelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVisualizarBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(PanelVisualizarImagenLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVisualizarImagenLayout.createSequentialGroup()
                                .addComponent(lblVFechaEntrega)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtVFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVisualizarImagenLayout.createSequentialGroup()
                                .addComponent(lblVFechaInicio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtVFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVisualizarImagenLayout.createSequentialGroup()
                        .addComponent(lblNombreProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVisualizarImagenLayout.createSequentialGroup()
                        .addComponent(lblVPrecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVisualizarImagenLayout.createSequentialGroup()
                        .addComponent(lblVCantidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVisualizarImagenLayout.createSequentialGroup()
                        .addComponent(lblVTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVisualizarImagenLayout.createSequentialGroup()
                        .addComponent(lblvMarca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelVisualizarImagenLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelVisualizarImagenLayout.createSequentialGroup()
                                .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(PanelVisualizarImagenLayout.createSequentialGroup()
                                        .addComponent(lblVTipo1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtVTipoInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelVisualizarImagenLayout.createSequentialGroup()
                                        .addComponent(lblVEstado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtVEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(38, 38, 38)
                                .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblvTamaño)
                                    .addComponent(lblVCategoria)
                                    .addComponent(lblVMaterial))
                                .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelVisualizarImagenLayout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtVTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtVCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(PanelVisualizarImagenLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtVMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(PanelVisualizarImagenLayout.createSequentialGroup()
                                .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblVNombre)
                                    .addComponent(lblVColor))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtVColor, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtVNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(PanelVisualizarImagenLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblvDistribuidor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblVDescuento)
                        .addGap(5, 5, 5)
                        .addComponent(txtVDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        PanelVisualizarImagenLayout.setVerticalGroup(
            PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVisualizarImagenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVisualizarImagenLayout.createSequentialGroup()
                        .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIconoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVisualizarBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelVisualizarImagenLayout.createSequentialGroup()
                        .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNombreProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtVCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblVNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtVNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)
                        .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblvMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVColor, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblvTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVTipoInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVTipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(29, 29, 29)
                .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVisualizarImagenLayout.createSequentialGroup()
                        .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblVFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVisualizarImagenLayout.createSequentialGroup()
                        .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblVMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)))
                .addGroup(PanelVisualizarImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblvDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbpInventario.addTab("Visualizar", new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/VisualizarInventario20px_1.png")), PanelVisualizarImagen, ""); // NOI18N

        jButton1.setBackground(new java.awt.Color(255, 222, 212));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setText("De click para abrir el formulario de registro y trabajar en conjunto con el inventario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 1061, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
        );

        tbpInventario.addTab("Registrar entrega", jPanel9);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(tbpInventario, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpInventario, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );

        tbpGeneral.addTab("Modo visualizar", jPanel2);

        tbpMenu.setBackground(new java.awt.Color(200, 86, 139));
        tbpMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbpMenu.setPreferredSize(new java.awt.Dimension(347, 928));
        //tbpMenu.setBackgroundAt(1, Color.BLACK);

        PanelAgregar.setBackground(new java.awt.Color(255, 222, 212));
        PanelAgregar.setMinimumSize(new java.awt.Dimension(349, 400));
        PanelAgregar.setPreferredSize(new java.awt.Dimension(349, 400));

        jPanel1.setBackground(new java.awt.Color(255, 222, 212));

        btnAgregar.setBackground(new java.awt.Color(164, 188, 188));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/AgregarProducto30px.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnAgregarImagen.setBackground(new java.awt.Color(164, 188, 188));
        btnAgregarImagen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAgregarImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Subir30px.png"))); // NOI18N
        btnAgregarImagen.setText("Cargar imagen");
        btnAgregarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarImagenActionPerformed(evt);
            }
        });

        lblAgregarVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/NoImage200px.png"))); // NOI18N

        lblAgregarFechaFin.setBackground(new java.awt.Color(0, 0, 0));
        lblAgregarFechaFin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregarFechaFin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAgregarFechaFin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/FVenta35px.png"))); // NOI18N
        lblAgregarFechaFin.setText("Fecha fin");

        lblAgregarFechaEntrega.setBackground(new java.awt.Color(0, 0, 0));
        lblAgregarFechaEntrega.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregarFechaEntrega.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAgregarFechaEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/FEntrega35px.png"))); // NOI18N
        lblAgregarFechaEntrega.setText("Fecha entrega");
        lblAgregarFechaEntrega.setToolTipText("");

        dcrFechaEntrega.setPreferredSize(new java.awt.Dimension(95, 35));
        dcrFechaEntrega.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcrFechaEntregaPropertyChange(evt);
            }
        });

        dcrFechaFin.setPreferredSize(new java.awt.Dimension(95, 35));
        dcrFechaFin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcrFechaFinPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAgregarVisualizar)
                            .addComponent(btnAgregarImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)))
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(lblAgregarFechaEntrega)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAgregarFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(dcrFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dcrFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAgregarFechaEntrega)
                    .addComponent(lblAgregarFechaFin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcrFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcrFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblAgregarVisualizar)
                .addGap(10, 10, 10)
                .addComponent(btnAgregarImagen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregar)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 222, 212));

        lblAgregarDescripción.setBackground(new java.awt.Color(0, 0, 0));
        lblAgregarDescripción.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregarDescripción.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAgregarDescripción.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Descripcion35px.png"))); // NOI18N
        lblAgregarDescripción.setText("Descripción");

        txaAgregarDescripción.setColumns(20);
        txaAgregarDescripción.setLineWrap(true);
        txaAgregarDescripción.setRows(5);
        txaAgregarDescripción.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txaAgregarDescripciónKeyTyped(evt);
            }
        });
        spAgregarDescripción.setViewportView(txaAgregarDescripción);

        cmbAgregarTamanio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbAgregarTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblAgregarTipo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregarTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/TipoProducto45.png"))); // NOI18N
        lblAgregarTipo.setText("Tipo");

        lblAgregarTamaño.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregarTamaño.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Tamaño50px.png"))); // NOI18N
        lblAgregarTamaño.setText("Tamaño");

        cmbAgregarMaterial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblAgregarMaterial.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregarMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Material50px.png"))); // NOI18N
        lblAgregarMaterial.setText("Material");
        lblAgregarMaterial.setToolTipText("");

        lblAgregarColor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregarColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Color50px.png"))); // NOI18N
        lblAgregarColor.setText("Color");

        cmbAgregarColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblAgregarDescuento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregarDescuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Descuento50px.png"))); // NOI18N
        lblAgregarDescuento.setText("Descuento");

        txtDescuento.setMinimumSize(new java.awt.Dimension(144, 35));
        txtDescuento.setPreferredSize(new java.awt.Dimension(144, 35));
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyTyped(evt);
            }
        });

        lblAvisoDescuento.setBackground(new java.awt.Color(255, 255, 255));
        lblAvisoDescuento.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        lblAvisoDescuento.setForeground(new java.awt.Color(255, 51, 51));
        lblAvisoDescuento.setText("No se admiten descuentos superiores al 100%");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spAgregarDescripción)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblAgregarTamaño, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbAgregarTamanio, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbAgregarTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblAgregarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblAgregarColor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbAgregarColor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblAgregarMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbAgregarMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAgregarDescripción, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAgregarDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAvisoDescuento, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDescuento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAgregarColor)
                    .addComponent(lblAgregarMaterial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAgregarColor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAgregarMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAgregarTamaño)
                    .addComponent(lblAgregarTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbAgregarTamanio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAgregarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAgregarDescuento)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAgregarDescripción)
                    .addComponent(lblAvisoDescuento))
                .addGap(30, 30, 30)
                .addComponent(spAgregarDescripción, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 222, 212));

        lblAgregarNombre.setBackground(new java.awt.Color(0, 0, 0));
        lblAgregarNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregarNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/producto40px.png"))); // NOI18N
        lblAgregarNombre.setText("Nombre producto:");

        lblAgregarCantidad.setBackground(new java.awt.Color(0, 0, 0));
        lblAgregarCantidad.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregarCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/CantidadProducto35px.png"))); // NOI18N
        lblAgregarCantidad.setText("Cantidad");

        txtAgregarNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAgregarNombreKeyTyped(evt);
            }
        });

        lblAgregarPrecio.setBackground(new java.awt.Color(0, 0, 0));
        lblAgregarPrecio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregarPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Precio35px.png"))); // NOI18N
        lblAgregarPrecio.setText("Precio $");

        txtAgregarPrecio.setMinimumSize(new java.awt.Dimension(144, 35));
        txtAgregarPrecio.setPreferredSize(new java.awt.Dimension(144, 35));
        txtAgregarPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAgregarPrecioKeyTyped(evt);
            }
        });

        spnAgregarCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                spnAgregarCantidadKeyTyped(evt);
            }
        });

        lblAgregarCategoria.setBackground(new java.awt.Color(0, 0, 0));
        lblAgregarCategoria.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Categoria35px.png"))); // NOI18N
        lblAgregarCategoria.setText("Categoria");

        lblAgregarDistribuidor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregarDistribuidor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Distribuidor50px.png"))); // NOI18N
        lblAgregarDistribuidor.setText("Distribuidor");

        cmbAgregarDistribuidor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbAgregarCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbAgregarCategoria.setPreferredSize(new java.awt.Dimension(144, 22));

        lblAgregararMateriales.setBackground(new java.awt.Color(0, 0, 0));
        lblAgregararMateriales.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregararMateriales.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAgregararMateriales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/TipoProducto35px.png"))); // NOI18N
        lblAgregararMateriales.setText("Tipo");
        lblAgregararMateriales.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblAgregarMarca.setBackground(new java.awt.Color(0, 0, 0));
        lblAgregarMarca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAgregarMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Marca35px.png"))); // NOI18N
        lblAgregarMarca.setText("Marca");

        cmbAgregarMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbAgregarMarca.setPreferredSize(new java.awt.Dimension(144, 22));

        cmbAgregarCondicion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("Limpiar Campos");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblAgregarNombre))
                    .addComponent(txtAgregarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(spnAgregarCantidad)
                                .addComponent(lblAgregarCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblAgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAgregarDistribuidor)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblAgregarPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAgregarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbAgregarCategoria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbAgregarCondicion, javax.swing.GroupLayout.Alignment.LEADING, 0, 147, Short.MAX_VALUE)
                            .addComponent(lblAgregararMateriales, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblAgregarMarca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbAgregarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbAgregarDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblAgregarNombre)
                .addGap(10, 10, 10)
                .addComponent(txtAgregarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAgregarPrecio)
                    .addComponent(lblAgregarCantidad))
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnAgregarCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAgregarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAgregarCategoria)
                    .addComponent(lblAgregarDistribuidor))
                .addGap(8, 8, 8)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAgregarDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAgregarMarca)
                    .addComponent(lblAgregararMateriales))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAgregarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAgregarCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout PanelAgregarLayout = new javax.swing.GroupLayout(PanelAgregar);
        PanelAgregar.setLayout(PanelAgregarLayout);
        PanelAgregarLayout.setHorizontalGroup(
            PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(PanelAgregarLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelAgregarLayout.setVerticalGroup(
            PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAgregarLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(419, Short.MAX_VALUE))
        );

        tbpMenu.addTab("Agregar", new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/AgregarProducto30px.png")), PanelAgregar); // NOI18N

        PanelModificar.setBackground(new java.awt.Color(255, 222, 212));
        PanelModificar.setMinimumSize(new java.awt.Dimension(1100, 400));
        PanelModificar.setPreferredSize(new java.awt.Dimension(1100, 400));

        jPanel4.setBackground(new java.awt.Color(255, 222, 212));

        PanelModificarVisualizarImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        PanelModificarVisualizarImagen.setMinimumSize(new java.awt.Dimension(200, 200));
        PanelModificarVisualizarImagen.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelModificarVisualizarImagen.setLayout(new java.awt.BorderLayout());

        lblModificarVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/NoImage200px.png"))); // NOI18N
        PanelModificarVisualizarImagen.add(lblModificarVisualizar, java.awt.BorderLayout.CENTER);

        btnModificarImagen.setBackground(new java.awt.Color(164, 188, 188));
        btnModificarImagen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnModificarImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Subir30px.png"))); // NOI18N
        btnModificarImagen.setText("Cargar imagen");
        btnModificarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarImagenActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(164, 188, 188));
        btnModificar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/ModificarProducto30px.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(164, 188, 188));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/EliminarProducto.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        lblModificarFechaEntrega.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarFechaEntrega.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblModificarFechaEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/FEntrega35px.png"))); // NOI18N
        lblModificarFechaEntrega.setText("Fecha inicio");

        dcrModificarFI.setBackground(new java.awt.Color(164, 188, 188));
        dcrModificarFI.setPreferredSize(new java.awt.Dimension(95, 35));

        lblModificarFechaFin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarFechaFin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblModificarFechaFin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/FVenta35px.png"))); // NOI18N
        lblModificarFechaFin.setText("Inicio Salida");

        dcrModificarFF.setBackground(new java.awt.Color(164, 188, 188));
        dcrModificarFF.setPreferredSize(new java.awt.Dimension(95, 35));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblModificarFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dcrModificarFI, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblModificarFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcrModificarFF, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(PanelModificarVisualizarImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnModificarImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblModificarFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcrModificarFI, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblModificarFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcrModificarFF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(PanelModificarVisualizarImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnModificarImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jPanel5.setBackground(new java.awt.Color(255, 222, 212));

        txaModificarDescripción.setColumns(20);
        txaModificarDescripción.setLineWrap(true);
        txaModificarDescripción.setRows(5);
        txaModificarDescripción.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txaModificarDescripciónKeyTyped(evt);
            }
        });
        spModificarDescripción.setViewportView(txaModificarDescripción);

        lblModificarDescripción.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarDescripción.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblModificarDescripción.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Descripcion35px.png"))); // NOI18N
        lblModificarDescripción.setText("Descripción");

        lblModificarMateriales.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarMateriales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Material50px.png"))); // NOI18N
        lblModificarMateriales.setText("Material");

        cmbModificarMaterial.setBackground(new java.awt.Color(164, 188, 188));
        cmbModificarMaterial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbModificarMaterial.setPreferredSize(new java.awt.Dimension(147, 35));

        lblModificarTamaño.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarTamaño.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Tamaño50px.png"))); // NOI18N
        lblModificarTamaño.setText("Tamaño");

        cmbModificarTamaño.setBackground(new java.awt.Color(164, 188, 188));
        cmbModificarTamaño.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblModificarMarca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Marca35px.png"))); // NOI18N
        lblModificarMarca.setText("Marca");

        lblModificarColor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Color50px.png"))); // NOI18N
        lblModificarColor.setText("Color");

        cmbModificarMarca.setBackground(new java.awt.Color(164, 188, 188));
        cmbModificarMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbModificarColor.setBackground(new java.awt.Color(164, 188, 188));
        cmbModificarColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbModificarColor.setMaximumSize(null);
        cmbModificarColor.setMinimumSize(new java.awt.Dimension(147, 35));
        cmbModificarColor.setPreferredSize(new java.awt.Dimension(147, 35));

        lblModificarTipoIn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarTipoIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/TipoProducto45.png"))); // NOI18N
        lblModificarTipoIn.setText("Tipo");

        cmbModificarTipoInventario.setBackground(new java.awt.Color(164, 188, 188));
        cmbModificarTipoInventario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbModificarTipoInventario.setPreferredSize(new java.awt.Dimension(147, 35));

        lblModificarTipoIn1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarTipoIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Descuento50px.png"))); // NOI18N
        lblModificarTipoIn1.setText("Descuento");

        txtModificarDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtModificarDescuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModificarDescuentoKeyTyped(evt);
            }
        });

        lblAvisoDescuento1.setBackground(new java.awt.Color(255, 255, 255));
        lblAvisoDescuento1.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        lblAvisoDescuento1.setForeground(new java.awt.Color(255, 51, 51));
        lblAvisoDescuento1.setText("No se admiten descuentos superiores al 100%");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbModificarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblModificarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbModificarColor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblModificarColor, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(spModificarDescripción, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblModificarTipoIn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblModificarMateriales, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbModificarMaterial, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblModificarTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbModificarTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblModificarTipoIn1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblModificarDescripción, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbModificarTipoInventario, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtModificarDescuento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAvisoDescuento1, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModificarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModificarColor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbModificarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbModificarColor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblModificarTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbModificarTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblModificarMateriales, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbModificarMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblModificarTipoIn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModificarTipoIn1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbModificarTipoInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModificarDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModificarDescripción, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAvisoDescuento1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spModificarDescripción, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 222, 212));

        lblModificarNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/producto40px.png"))); // NOI18N
        lblModificarNombre.setText("Nombre producto:");

        txtModificarNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModificarNombreKeyTyped(evt);
            }
        });

        lblModificarCantidad.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/CantidadProducto35px.png"))); // NOI18N
        lblModificarCantidad.setText("Cantidad");

        lblModificarPrecio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Precio35px.png"))); // NOI18N
        lblModificarPrecio.setText("Precio $");

        spnCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                spnCantidadKeyTyped(evt);
            }
        });

        lblModificarEstado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Estado35px.png"))); // NOI18N
        lblModificarEstado.setText("Condición");

        lblModificarCategoria.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Categoria35px.png"))); // NOI18N
        lblModificarCategoria.setText("Categoria");

        cmbModificarEstado.setBackground(new java.awt.Color(164, 188, 188));
        cmbModificarEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbModificarCategoria.setBackground(new java.awt.Color(164, 188, 188));
        cmbModificarCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblModificarDistribuidor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarDistribuidor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Distribuidor50px.png"))); // NOI18N
        lblModificarDistribuidor.setText("Distribuidor");

        lblModificarTipo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblModificarTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/TipoProducto35px.png"))); // NOI18N
        lblModificarTipo.setText("Estado");

        cmbModificarDistribuidor.setBackground(new java.awt.Color(164, 188, 188));
        cmbModificarDistribuidor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbModificarTipo.setBackground(new java.awt.Color(164, 188, 188));
        cmbModificarTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtModificarPrecio1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModificarPrecio1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(cmbModificarDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblModificarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(lblModificarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblModificarNombre))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtModificarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lblModificarCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(lblModificarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(lblModificarDistribuidor)
                                    .addGap(35, 35, 35))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(cmbModificarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(38, 38, 38)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtModificarPrecio1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbModificarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblModificarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbModificarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(1, 1, 1))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblModificarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtModificarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModificarCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModificarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModificarPrecio1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModificarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModificarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbModificarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbModificarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblModificarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModificarDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbModificarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbModificarDistribuidor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelModificarLayout = new javax.swing.GroupLayout(PanelModificar);
        PanelModificar.setLayout(PanelModificarLayout);
        PanelModificarLayout.setHorizontalGroup(
            PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelModificarLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        PanelModificarLayout.setVerticalGroup(
            PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelModificarLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tbpMenu.addTab("Modificar", new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/ModificarProducto30px.png")), PanelModificar); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(tbpMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 1085, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tbpGeneral.addTab("Modo edición", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tbpGeneral)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(PanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    //Método que carga lso datos en la tabla de renta

    public void mostrarDatos() {
        //Crea el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        //Obtiene los datos del controlador
        ResultSet rs = controlador.CargarTablaRenta();
        //Se le asignan las columnas
        modelo.setColumnIdentifiers(new Object[]{"Nombre", "Marca", "Estado", "Costo", "Cantidad"});
        try {
            //Se van guardando los datos
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString("nombreProducto"), rs.getString("nombreMarca"), rs.getString("nombreTipo"), rs.getDouble("precio"), rs.getInt("cantidad")});

            }
            //Se guarda el modelo en la tabla
            TableRenta.setModel(modelo);
        }catch(Exception e){
            //Se envia el error en dado caso no se puedan cargar
            NError.setVisible(true);
            NError.TAnotiError.setText("Error al cargar la tabla");
            NError.lblTituloError.setText("Datos no mostrados");
        }
    }

    //Método que carga los datos en la tabla de venta
    public void mostrarDatosVenta() {
        //Se crea el modelo
        DefaultTableModel modelo = new DefaultTableModel();
        //Se cargan los datos obtenidos del controlador
        ResultSet rs = controlador.CargarTablaVenta();
        //Se asignan las columnas
        modelo.setColumnIdentifiers(new Object[]{"Nombre", "Marca", "Estado", "Costo", "Cantidad"});
        try {
            //Se van cargando los datos por filas
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString("nombreProducto"), rs.getString("nombreMarca"), rs.getString("nombreTipo"), rs.getDouble("precio"), rs.getInt("cantidad")});
            }
            //Se coloca el modelo a la tabla
            tableVenta.setModel(modelo);
        }catch (Exception e){
            //Si no se puede cargar se envia un error
            NError.setVisible(true);
            NError.TAnotiError.setText("Error al cargar la tabla");
            NError.lblTituloError.setText("Datos no mostrados");
        }
    }

    //Método que limpia las tablas para que puedan volver a ser cargadas
    void limpiarTablas(){
        codigos = inventario.CargasID();
        tablaVenta.setRowCount(0);
        tablaRenta.setRowCount(0);
    }
    
    void LimpiarCampos(){
        lblImagenProducto.setIcon(null);
        lblModificarVisualizar.setIcon(null);
        seleccion = "";
        //Se extraen la información
        txtVNombre.setText(null);
        txtVCodigo.setText(null);
        txtVCantidad.setText(null);
        txtVCategoria.setText(null);
        txtVCodigo.setText(null);
        txtVColor.setText(null);
        txtVDistribuidor.setText(null);
        txtVEstado.setText(null);
        txtVFechaEntrega.setText(null);
        txtVFechaInicio.setText(null);
        txtVMarca.setText(null);
        txtVMaterial.setText(null);
        txtVTipoInventario.setText(null);
        txtVPrecio.setText(null);
        txtVTamaño.setText(null);
        txtVTipo.setText(null);
        txtVDescuento.setText(null);
        //Se agrega el cargan el resto de datos
        txtModificarNombre.setText(null);
        txtModificarPrecio1.setText(null);
        txaModificarDescripción.setText(null);
        txtModificarDescuento.setText(null);
    }
    
    void LlenarCampos(){
        //Se cargan los datos para le menú de visualizar
        Object[] datos = ControladorInventario.Cargarmenu(iden);//Visualizar contiene solamente datos del tipo String
        //Se cargan los datos para le menú de modificar
        Object[] DatosM = ControladorInventario.CargarMenuModificar(iden); //Modificar tiene datos especiales (Llaves primarias)
        try{
            //Se carga la cadena de byte
            byte[] imagene = (byte[]) DatosM[4]; //Posición de la imagen
            //Se leen los datos
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(imagene));
            //Se carga la imagen en los formularios
            //Variables que guardan las dimensiones
//            int ancho = lblImagenProducto.getWidth();
//            int alto =
            Image imagen = img.getScaledInstance(lblImagenProducto.getWidth(), lblImagenProducto.getHeight(), Image.SCALE_SMOOTH);
            //a la variable seleccion le asignamos la imagen
            seleccion = String.valueOf(img);
            lblImagenProducto.setIcon(new ImageIcon(imagen));
            lblModificarVisualizar.setIcon(new ImageIcon(imagen));
        }catch (Exception e){
            System.out.println(e);
            //Se envía un error si no es compatible
            FrmNotiError NError = new FrmNotiError();
            NError.setVisible(true);
            NError.TAnotiError.setText("¡Error al cargar la imagen!");
            NError.lblTituloError.setText("La imagen no pudo ser cargada");
        }
        //Se cargan los datos en visualizar
        //Se extraen la información
        txtVNombre.setText(String.valueOf(datos[1]));
        txtVCodigo.setText(String.valueOf(datos[0]));
        txtVCantidad.setText(String.valueOf(datos[3]));
        txtVCategoria.setText(String.valueOf(datos[15]));
        txtVCodigo.setText(String.valueOf(datos[0]));
        txtVColor.setText(String.valueOf(datos[9]));
        txtVDistribuidor.setText(String.valueOf(datos[11]));
        txtVEstado.setText(String.valueOf(datos[14]));
        txtVFechaEntrega.setText(String.valueOf(datos[5]));
        txtVFechaInicio.setText(String.valueOf(datos[6]));
        txtVMarca.setText(String.valueOf(datos[10]));
        txtVMaterial.setText(String.valueOf(datos[16]));
        txtVTipoInventario.setText(String.valueOf(datos[13]));
        txtVPrecio.setText(String.valueOf(datos[2]));
        txtVTamaño.setText(String.valueOf(datos[8]));
        txtVTipo.setText(String.valueOf(datos[12]));
        txtVDescuento.setText(String.valueOf(datos[17]));
        try {
            //Se cargan los datos en modificar
            //Se extraen la información
            //Se coloca el modelo
            spnCantidad.setModel(Spinner);
            Spinner.setValue(Integer.parseInt(String.valueOf(DatosM[3]))); //Cantidad
            //Se cargan los ID a los comboBox
            //A los combobox se les agrega -1 porque se trae desde la base empezando desde 1, pero los combobox empiezan desde 0
            cmbModificarCategoria.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[15])) - 1); //IDModificar
            cmbModificarColor.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[9])) - 1); //IDColor
            cmbModificarDistribuidor.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[11])) - 1); //IDDistribuidor
            cmbModificarMarca.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[10])) - 1); //IDMarca
            cmbModificarEstado.setSelectedIndex(Integer.parseInt(String.valueOf(String.valueOf(DatosM[14]))) - 1); //IDEstado
            cmbModificarTipo.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[12])) - 1); //IDTipoProducto
            cmbModificarTipoInventario.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[13])) - 1); //IDTipoInventario
            cmbModificarTamaño.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[8])) - 1); //IDTamaño
            cmbModificarMaterial.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[16])) - 1); //IDMaterial
            //Se crea el nuevo formato
            SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
            dcrModificarFI.setDate(Formato.parse(String.valueOf(DatosM[5])));
            dcrModificarFF.setDate(Formato.parse(String.valueOf(DatosM[6])));
            //Se agrega el cargan el resto de datos
            txtModificarNombre.setText(String.valueOf(DatosM[1]));
            txtModificarPrecio1.setText(String.valueOf(String.valueOf(DatosM[2])));
            txaModificarDescripción.setText(String.valueOf(DatosM[7]));
            txtModificarDescuento.setText(String.valueOf(DatosM[17]));
        } catch (Exception e) {
            //Se envía un error si es para cargar
            FrmNotiError NError = new FrmNotiError();
            NError.setVisible(true);
            NError.TAnotiError.setText("¡Error al cargar!");
            NError.lblTituloError.setText("Los datos seleccionados no concuerdan");
        }
    }

    //Método que carga la imagen
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

    //Método que obtiene la imagen para modificarla
    public String insertarModificar() {
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
                //Obtenemos el ancho del Label donde la colocaremos
                int ancho = lblModificarVisualizar.getWidth();
                //Obtenemos la altura del Label donde la colocaremos
                int alto = lblModificarVisualizar.getHeight();
                //getToolkit Obtiene el kit de herramientas predeterminado y se puede obtener un objeto Toolkit mediante la invocación de este método
                // En este caso nos permite ir a nuestro equipo y obtener la ruta.
                //getImage carga todos los bits de la imagen en la memoria
                Image imagen = getToolkit().getImage(seleccion);
                //Image.SCALE_SMOOTH Elije un algoritmo de escalado de imagen que da mayor prioridad a la suavidad de la imagen que a la velocidad de escalado. 
                imagen = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                lblModificarVisualizar.setIcon(new ImageIcon(imagen));
            }
        } catch (Exception e) {

        }
        return seleccion;
//        try {
//            //Es una clase java que nos permite mostrar fácilmente una ventana para la selección de un fichero
//            JFileChooser ventana = new JFileChooser();
//            //Se establece un filtro para que solo se muestren archivos con una determinada extensión
//            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
//            //Agregamos el filtro a la ventana que abriremos
//            ventana.setFileFilter(filtro);
//
//            int seleccionar = ventana.showOpenDialog(this);
//            //Comprobar si se ha dado clic en Aceptar
//            if (seleccionar == JFileChooser.APPROVE_OPTION) {
//                //Convertimos a tipo Archivo el componente seleccionado
//                File archivo = ventana.getSelectedFile();
//                //Esta función devuelve el nombre de ruta absoluta del objeto de archivo dado.
//                seleccion = archivo.getAbsolutePath();
//                //Obtenemos el ancho del Label donde la colocaremos
//                int ancho = lblAgregarVisualizar.getWidth();
//                //Obtenemos la altura del Label donde la colocaremos
//                int alto = lblAgregarVisualizar.getHeight();
//                //getToolkit Obtiene el kit de herramientas predeterminado y se puede obtener un objeto Toolkit mediante la invocación de este método
//                // En este caso nos permite ir a nuestro equipo y obtener la ruta.
//                //getImage carga todos los bits de la imagen en la memoria
//                Image imagen = getToolkit().getImage(seleccion);
//                //Image.SCALE_SMOOTH Elije un algoritmo de escalado de imagen que da mayor prioridad a la suavidad de la imagen que a la velocidad de escalado. 
//                imagen = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
//                lblAgregarVisualizar.setIcon(new ImageIcon(imagen));
//            }
//        } catch (Exception e) {
//            System.out.println("Problema");
//        }
//        return seleccion;
    }

    private void btnAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImagenActionPerformed
        //Ejecuta el método para cargar la imagen para insertarla por primera vez
        insertar();
    }//GEN-LAST:event_btnAgregarImagenActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            //Se crea un objeto dónde se guardarán los datos
            Object[] datos = new Object[18];
            Date FechaI = dcrFechaEntrega.getDate(); //Se obtiene la fecha de entrega
            Date FechaF = dcrFechaFin.getDate(); //Se obtiene la fecha e finalización
            SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd"); //Se estable el formato de fehca
            //Se carga el formato en las fechas obtenidad
            String NFI = Formato.format(FechaI);
            String NFF = Formato.format(FechaF);
            //Se convierte de fecha de java a fecha de SQL
            java.sql.Date FechaInicio = java.sql.Date.valueOf(NFI);
            java.sql.Date FechaFin = java.sql.Date.valueOf(NFF);
            File imagen = new File(seleccion); //Imagen
            //Se cargan los datos en el vecto de obejetos para enviarlo a insertar
            datos[0] = generarCodigo();//Codigo del producto
            datos[1] = txtAgregarNombre.getText(); //Nombre
            datos[2] = Double.parseDouble(txtAgregarPrecio.getText()); //Precio;
            String preCan = String.valueOf(spnAgregarCantidad.getValue()); //Variable temporal que se encarga de guardar la cantidad
            datos[3] = Integer.valueOf(preCan); //Cantidad;
            datos[4] = seleccion;
            datos[5] = FechaInicio;
            datos[6] = FechaFin;
            datos[7] = txaAgregarDescripción.getText(); //Descripción;
            datos[8] = cmbAgregarTamanio.getSelectedIndex() + 1; //IDTamaño;
            datos[9] = cmbAgregarColor.getSelectedIndex() + 1; //IDColor;
            datos[10] = cmbAgregarMarca.getSelectedIndex() + 1; //IDMarca;
            datos[11] = cmbAgregarDistribuidor.getSelectedIndex() + 1; //IDDistribuidor;
            datos[12] = cmbAgregarCondicion.getSelectedIndex() + 1; //IDTipoProducto;
            datos[13] = cmbAgregarTipo.getSelectedIndex() + 1; //IDTipoInventario;
            datos[14] = 1; //El valor es 1 debido a que si se registra un nuevo producto, es un hecho que no estará agotado
            datos[15] = cmbAgregarCategoria.getSelectedIndex() + 1; //IDCategoria;
            datos[16] = cmbAgregarMaterial.getSelectedIndex() + 1; //IDMaterial;
            datos[17] = Integer.parseInt(txtDescuento.getText()); //Descuento;
            //Se verifica si la inserción fue realizado con exito
            if (controlador.GuardarProducto(datos)) {
                //Se envía la confirmación
                NInsert.setVisible(true);
                NInsert.lblTituloNoti2.setText("¡Ingresado!");
                NInsert.TANoti2.setText("El producto fue ingresado correctamente");

            } else {
                //Se notifica si hay un error
                NError.setVisible(true);
                NError.TAnotiError.setText("Error al ingresar");
                NError.lblTituloError.setText("Problema al ingresar el nuevo producto");
            }
            //Se refrescan las tablas
            mostrarDatos();
            mostrarDatosVenta();
        } catch (Exception e) {
            //Si hay un error en la base se notifica
            NError.setVisible(true);
            NError.TAnotiError.setText("¡Error al ingresar!");
            NError.lblTituloError.setText("El producto no pudo ser ingresado, problema con la base de datos");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void TableRentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableRentaMouseClicked
        //Se selecciona el valor de la sección
        int fila = TableRenta.rowAtPoint(evt.getPoint());
        //Se cargan todos los codigos
        codigos = controlador.IDRenta();
        //Selección del ID del producto
        iden = codigos.get(fila);
        System.out.println(iden);
        //Mètodo que limpia los datos previos
        LimpiarCampos();
        LlenarCampos();
        
    }//GEN-LAST:event_TableRentaMouseClicked

    private void tableVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVentaMouseClicked
        //Se cra el indicador de la selección
        int fila = tableVenta.rowAtPoint(evt.getPoint());
        //Se cargan todos los ID de la venta
        codigoV = inventario.CargasIDVenta();
        //Se seleciona el codigo dependiendo del indicador
        iden = codigoV.get(fila);
        //Mètodo que limpia los datos previos
        LimpiarCampos();
        LlenarCampos();
    }//GEN-LAST:event_tableVentaMouseClicked

    private void btnModificarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarImagenActionPerformed
        insertarModificar();
    }//GEN-LAST:event_btnModificarImagenActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            //Se crea el objeto con las posiciones
            Object[] datos = new Object[19];
            //Se obtiene la fecha 
            Date FechaI = dcrModificarFI.getDate();
            Date FechaF = dcrModificarFF.getDate();
            //Se crea un formato aceptado por SQL
            SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
            //Se cambia rl formato
            String NFI = Formato.format(FechaI);
            String NFF = Formato.format(FechaF);
            //Se convierte de fecha JAVA a fecha SQL
            java.sql.Date FechaInicio = java.sql.Date.valueOf(NFI);
            java.sql.Date FechaFin = java.sql.Date.valueOf(NFF);
            //Se cargan los datos  al obejeto que se cargará
            if (lblModificarVisualizar.getText().isEmpty()) {
                datos[18] = 1;
            }else{
                datos[18] = 2;
            }
            datos[0] = iden;
            datos[1] = txtModificarNombre.getText(); //NombreProducto
            datos[2] = Double.parseDouble(txtModificarPrecio1.getText()); //Precio;
            String preCan = String.valueOf(spnCantidad.getValue()); //Variable que guarda el modelo del Spinner
            datos[3] = Integer.valueOf(preCan); //Cantidad;
            datos[4] = seleccion;
            datos[5] = FechaInicio;
            datos[6] = FechaFin;
            datos[7] = txaModificarDescripción.getText(); //Descripción;
            datos[8] = cmbModificarTamaño.getSelectedIndex() + 1; //IDTamaño
            datos[9] = cmbModificarColor.getSelectedIndex() + 1; //IDColor;
            datos[10] = cmbModificarMarca.getSelectedIndex() + 1; //IDMarca;
            datos[11] = cmbModificarDistribuidor.getSelectedIndex() + 1; //IDDistribuidor;
            datos[12] = cmbModificarTipo.getSelectedIndex() + 1; //IDTipoProducto;
            datos[13] = cmbModificarTipoInventario.getSelectedIndex() + 1; //IDTipoInventario;
            datos[14] = 1;
            datos[15] = cmbModificarCategoria.getSelectedIndex() + 1; //IDCategoria;
            datos[16] = cmbModificarMaterial.getSelectedIndex() + 1; //IDMaterial;
            datos[17] = Integer.valueOf(txtModificarDescuento.getText()); //Descuento;
            //Se revisa si la actualización es correcta
            if (controlador.ActualizarProducto(datos)) {
                //Si todo esta correcto, se envïa la información que fue completado
                NInsert.setVisible(true);
                NInsert.lblTituloNoti2.setText("¡Completado!");
                NInsert.TANoti2.setText("El producto fue correctamente modificado");
            } else {
                //Si hubo un error se le notifica al uruario
                NError.setVisible(true);
                NError.TAnotiError.setText("¡Error al cargar!");
                NError.lblTituloError.setText("Los datos obtenidos no son correctos");
            }
            //Se carga y convierte los datos
            File imagen = new File(seleccion);
            //Se refrescan las tablas
            mostrarDatos();
            mostrarDatosVenta();
        } catch (Exception e) {
            //Se envía un error si la base de datos contiene problemas
            NError.setVisible(true);
            NError.TAnotiError.setText("No se pudo modificar el producto");
            NError.lblTituloError.setText("Error en la base de datos");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //Se verifica el estado de la eliminación - Ocultar    
        if (controlador.EliminarProducto(seleccion)) {
            //Si es eliminado, entonces se le notifica al usuario
            NInsert.setVisible(true);
            NInsert.lblTituloNoti2.setText("¡Eliminado!");
            NInsert.TANoti2.setText("El producto fue correctamente Eliminado");
        } else {
            //Si hay un error, se notifica
            NError.setVisible(true);
            NError.TAnotiError.setText("¡Error al eliminar!");
            NError.lblTituloError.setText("El producto no fue correctamente eliminado");
        }
        //Se refrescan las tablas
        mostrarDatos();
        mostrarDatosVenta();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void dcrFechaEntregaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcrFechaEntregaPropertyChange
        try {
            if (dcrFechaEntrega.getDate() == null) {

            } else {
                if (dcrFechaEntrega.getDate().after(FechaActual)) {

                } else {
                    dcrFechaEntrega.setDate(null);
                    NError.setVisible(true);
                    NError.TAnotiError.setText("La fecha ingresada debe ser mayor o igual a la del día de hoy");
                    NError.lblTituloError.setText("Fecha erronea");
                }
            }

        } catch (Exception e) {
            System.out.print("Error Inicio" + e.toString());
        }

    }//GEN-LAST:event_dcrFechaEntregaPropertyChange

    private void dcrFechaFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcrFechaFinPropertyChange
        try{
            if (dcrFechaFin.getDate() == null) {

            } else {
                if (dcrFechaEntrega.getDate() == null) {
                    NError.setVisible(true);
                    NError.TAnotiError.setText("Primero se debe ingresar la fecha en la cual se recibirá el producto");
                    NError.lblTituloError.setText("No se ha ingresado");
                } else {
                    if (dcrFechaFin.getDate().after(FechaActual) && dcrFechaFin.getDate().after(dcrFechaEntrega.getDate())) {

                    } else {
                        dcrFechaFin.setDate(null);
                        NError.setVisible(true);
                        NError.TAnotiError.setText("La fecha ingresada debe ser mayor a la fecha de ingreso");
                        NError.lblTituloError.setText("Fecha incorrecta");
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("Error Fin" + e.toString());
        }
    }//GEN-LAST:event_dcrFechaFinPropertyChange

    private void txtAgregarNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgregarNombreKeyTyped
        validaciones.soloLetras(evt, 4, txtAgregarNombre.getText());
    }//GEN-LAST:event_txtAgregarNombreKeyTyped

    private void txtAgregarPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgregarPrecioKeyTyped
        validaciones.soloNumeros(evt, 5, txtAgregarPrecio.getText());
    }//GEN-LAST:event_txtAgregarPrecioKeyTyped

    private void spnAgregarCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spnAgregarCantidadKeyTyped
        validaciones.soloNumeros(evt, 1, spnAgregarCantidad.getModel().toString());
    }//GEN-LAST:event_spnAgregarCantidadKeyTyped

    private void txtDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyTyped
        validaciones.soloNumeros(evt, 1, txtDescuento.getText());
        validaciones.Cantidad(evt, txtDescuento.getText(), 3);
    }//GEN-LAST:event_txtDescuentoKeyTyped

    private void txaAgregarDescripciónKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txaAgregarDescripciónKeyTyped
        validaciones.soloLetras(evt, 4, txtAgregarNombre.getText());
    }//GEN-LAST:event_txaAgregarDescripciónKeyTyped

    private void txtModificarNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModificarNombreKeyTyped
        validaciones.soloLetras(evt, 4, txtModificarNombre.getText());
    }//GEN-LAST:event_txtModificarNombreKeyTyped

    private void spnCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spnCantidadKeyTyped
        validaciones.soloNumeros(evt, 1, spnCantidad.getModel().toString());
    }//GEN-LAST:event_spnCantidadKeyTyped

    private void txtModificarPrecio1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModificarPrecio1KeyTyped
        validaciones.soloNumeros(evt, 5, txtModificarPrecio1.getText());
    }//GEN-LAST:event_txtModificarPrecio1KeyTyped

    private void txaModificarDescripciónKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txaModificarDescripciónKeyTyped
        validaciones.soloLetras(evt, 4, txtModificarNombre.getText());
    }//GEN-LAST:event_txaModificarDescripciónKeyTyped

    private void txtModificarDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModificarDescuentoKeyTyped
        validaciones.soloNumeros(evt, 1, txtModificarDescuento.getText());
        validaciones.Cantidad(evt, txtDescuento.getText(), 3);
    }//GEN-LAST:event_txtModificarDescuentoKeyTyped

    private void btnVisualizarBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarBuscarActionPerformed
        if(ValidarBuscador == 0){
            FrmBuscador buscador = new FrmBuscador();
            buscador.setVisible(true);
            ValidarBuscador = 1;
        }
    }//GEN-LAST:event_btnVisualizarBuscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //Abrimos el formulario
        FrmEntregaRegistro registro = new FrmEntregaRegistro();
        registro.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyReleased
        try{
            int n = Integer.parseInt(txtDescuento.getText());
            if (n > 100) {
                lblAvisoDescuento.setVisible(true);
            } else {
                lblAvisoDescuento.setVisible(false);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_txtDescuentoKeyReleased

    private void txtModificarDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModificarDescuentoKeyReleased
        try{
            int n = Integer.parseInt(txtDescuento.getText());
            if (n > 100) {
                lblAvisoDescuento1.setVisible(true);
            } else {
                lblAvisoDescuento1.setVisible(false);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_txtModificarDescuentoKeyReleased

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        txtAgregarNombre.setText(null);
        txaAgregarDescripción.setText(null);
        txtAgregarPrecio.setText(null);
        spnAgregarCantidad.setValue(null);
    }//GEN-LAST:event_jButton2MouseClicked

    //Método que se encarga de generar el código para ingresar un nuevo producto
    public String generarCodigo() {
        //Se crea la variable que se retornará
        String codigo = null;
        //Se extraen las primeras 3 letras del producto
        String L1 = String.valueOf(txtAgregarNombre.getText().charAt(0));
        String L2 = String.valueOf(txtAgregarNombre.getText().charAt(1));
        String L3 = String.valueOf(txtAgregarNombre.getText().charAt(2));
        //Se obtiene un número aleatorio para decidir el orden en el que se mezclarán las letras
        int Eleccion = (int) (Math.random() * 3 + 1);
        //Se obtiene un número que se agregará al codigo
        int extra = (int) (Math.random() * 99);
        //Depende del número que se obtenga se creará el orden
        //El número extra siempre irá al final
        switch (Eleccion) {
            case 1:
                //Si es 1, las letras se ingresarán en orden
                codigo = L1 + L2 + L3 + String.valueOf(extra);
                break;
            case 2:
                //Si es 2, las letras se moverán dos espacios a la derecho
                codigo = L2 + L3 + L1 + String.valueOf(extra);
                break;
            case 3:
                //Si es 3, entonces se moverán 1 espacio hacia la derecha
                codigo = L3 + L1 + L2 + String.valueOf(extra);
        }
        //Se devuelve el codigo
        return codigo;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelAgregar;
    private javax.swing.JPanel PanelImagen;
    private javax.swing.JPanel PanelModificar;
    private javax.swing.JPanel PanelModificarVisualizarImagen;
    private javax.swing.JPanel PanelTitulo;
    private javax.swing.JPanel PanelVisualizarImagen;
    private javax.swing.JTable TableRenta;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarImagen;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnModificarImagen;
    private javax.swing.JButton btnVisualizarBuscar;
    private javax.swing.JComboBox<String> cmbAgregarCategoria;
    private javax.swing.JComboBox<String> cmbAgregarColor;
    private javax.swing.JComboBox<String> cmbAgregarCondicion;
    private javax.swing.JComboBox<String> cmbAgregarDistribuidor;
    private javax.swing.JComboBox<String> cmbAgregarMarca;
    private javax.swing.JComboBox<String> cmbAgregarMaterial;
    private javax.swing.JComboBox<String> cmbAgregarTamanio;
    private javax.swing.JComboBox<String> cmbAgregarTipo;
    public static javax.swing.JComboBox<String> cmbModificarCategoria;
    public static javax.swing.JComboBox<String> cmbModificarColor;
    public static javax.swing.JComboBox<String> cmbModificarDistribuidor;
    public static javax.swing.JComboBox<String> cmbModificarEstado;
    public static javax.swing.JComboBox<String> cmbModificarMarca;
    public static javax.swing.JComboBox<String> cmbModificarMaterial;
    public static javax.swing.JComboBox<String> cmbModificarTamaño;
    public static javax.swing.JComboBox<String> cmbModificarTipo;
    public static javax.swing.JComboBox<String> cmbModificarTipoInventario;
    private com.toedter.calendar.JDateChooser dcrFechaEntrega;
    private com.toedter.calendar.JDateChooser dcrFechaFin;
    public static com.toedter.calendar.JDateChooser dcrModificarFF;
    public static com.toedter.calendar.JDateChooser dcrModificarFI;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblAgregarCantidad;
    private javax.swing.JLabel lblAgregarCategoria;
    private javax.swing.JLabel lblAgregarColor;
    private javax.swing.JLabel lblAgregarDescripción;
    private javax.swing.JLabel lblAgregarDescuento;
    private javax.swing.JLabel lblAgregarDistribuidor;
    private javax.swing.JLabel lblAgregarFechaEntrega;
    private javax.swing.JLabel lblAgregarFechaFin;
    private javax.swing.JLabel lblAgregarMarca;
    private javax.swing.JLabel lblAgregarMaterial;
    private javax.swing.JLabel lblAgregarNombre;
    private javax.swing.JLabel lblAgregarPrecio;
    private javax.swing.JLabel lblAgregarTamaño;
    private javax.swing.JLabel lblAgregarTipo;
    public static javax.swing.JLabel lblAgregarVisualizar;
    private javax.swing.JLabel lblAgregararMateriales;
    private javax.swing.JLabel lblAvisoDescuento;
    private javax.swing.JLabel lblAvisoDescuento1;
    private javax.swing.JLabel lblIconoBuscar;
    public static javax.swing.JLabel lblImagenProducto;
    private javax.swing.JLabel lblModificarCantidad;
    private javax.swing.JLabel lblModificarCategoria;
    private javax.swing.JLabel lblModificarColor;
    private javax.swing.JLabel lblModificarDescripción;
    private javax.swing.JLabel lblModificarDistribuidor;
    private javax.swing.JLabel lblModificarEstado;
    private javax.swing.JLabel lblModificarFechaEntrega;
    private javax.swing.JLabel lblModificarFechaFin;
    private javax.swing.JLabel lblModificarMarca;
    private javax.swing.JLabel lblModificarMateriales;
    private javax.swing.JLabel lblModificarNombre;
    private javax.swing.JLabel lblModificarPrecio;
    private javax.swing.JLabel lblModificarTamaño;
    private javax.swing.JLabel lblModificarTipo;
    private javax.swing.JLabel lblModificarTipoIn;
    private javax.swing.JLabel lblModificarTipoIn1;
    public static javax.swing.JLabel lblModificarVisualizar;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblTituloInventario;
    private javax.swing.JLabel lblVCantidad;
    private javax.swing.JLabel lblVCategoria;
    private javax.swing.JLabel lblVColor;
    private javax.swing.JLabel lblVDescuento;
    private javax.swing.JLabel lblVEstado;
    private javax.swing.JLabel lblVFechaEntrega;
    private javax.swing.JLabel lblVFechaInicio;
    private javax.swing.JLabel lblVMaterial;
    private javax.swing.JLabel lblVNombre;
    private javax.swing.JLabel lblVPrecio;
    private javax.swing.JLabel lblVTipo;
    private javax.swing.JLabel lblVTipo1;
    private javax.swing.JLabel lblvDistribuidor;
    private javax.swing.JLabel lblvMarca;
    private javax.swing.JLabel lblvTamaño;
    private javax.swing.JScrollPane spAgregarDescripción;
    private javax.swing.JScrollPane spInventarioRenta;
    private javax.swing.JScrollPane spModificarDescripción;
    private javax.swing.JSpinner spnAgregarCantidad;
    public static javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tableVenta;
    private javax.swing.JScrollPane tbInventarioVenta;
    private javax.swing.JTabbedPane tbpGeneral;
    private javax.swing.JTabbedPane tbpInventario;
    private javax.swing.JTabbedPane tbpMenu;
    private javax.swing.JTextArea txaAgregarDescripción;
    public static javax.swing.JTextArea txaModificarDescripción;
    private javax.swing.JTextField txtAgregarNombre;
    private javax.swing.JTextField txtAgregarPrecio;
    private javax.swing.JTextField txtDescuento;
    public static javax.swing.JTextField txtModificarDescuento;
    public static javax.swing.JTextField txtModificarNombre;
    public static javax.swing.JTextField txtModificarPrecio1;
    public static javax.swing.JTextField txtVCantidad;
    public static javax.swing.JTextField txtVCategoria;
    public static javax.swing.JTextField txtVCodigo;
    public static javax.swing.JTextField txtVColor;
    public static javax.swing.JTextField txtVDescuento;
    public static javax.swing.JTextField txtVDistribuidor;
    public static javax.swing.JTextField txtVEstado;
    public static javax.swing.JTextField txtVFechaEntrega;
    public static javax.swing.JTextField txtVFechaInicio;
    public static javax.swing.JTextField txtVMarca;
    public static javax.swing.JTextField txtVMaterial;
    public static javax.swing.JTextField txtVNombre;
    public static javax.swing.JTextField txtVPrecio;
    public static javax.swing.JTextField txtVTamaño;
    public static javax.swing.JTextField txtVTipo;
    public static javax.swing.JTextField txtVTipoInventario;
    // End of variables declaration//GEN-END:variables
}
