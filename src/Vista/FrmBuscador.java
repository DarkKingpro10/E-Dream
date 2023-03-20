/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorBuscador;
import Controlador.ControladorInventario;
import Controlador.ControladorVariables;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Lenny
 */
public class FrmBuscador extends javax.swing.JFrame {
    public static SpinnerNumberModel Spinner = new SpinnerNumberModel();
    private int dato = 0; 
    private static int Cantidad = 0;
    private int Existencias = 0;
    private static String Palabra = "";
    private int Valores = 0;
    private int anchura;
    private int altura;
    private int Retroceso = 0;
    private static String CodigoProducto = "";
    private static String Codigo1 = "";
    private static String Codigo2 = "";
    private static String Codigo3 = "";
    private static String Codigo4 = "";
    private static String Codigo5 = "";
    private static String Codigo6 = "";
    private ArrayList<Blob>datos = new ArrayList<Blob>();
    private ArrayList<String> datos2 = new ArrayList<String>();
    ControladorBuscador obj = new ControladorBuscador();
    FrmNoti1 notificacion = new FrmNoti1();
    FrmNotiError error = new FrmNotiError();
    //Creamos variables globales X y Y que ayudaran en metodo para mover el form
    int x, y;
    
    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Morado = new Color(94,45,79);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);
    
    public FrmBuscador() {
        initComponents();
        Colores();
        /*Utilizamos new ImageIcon(URL location).getImage() donde el argumento
        *location lo obtenemos a través del método getClass().getResource(String name)
        *que nos devuelve un URL absoluto del recurso que especificamos como String.
        */
        Image icon = new ImageIcon(getClass().getResource("/Recursos_img/E-Dream logo.png")).getImage();
        setIconImage(icon);
        
        ExistenciaProductos();
        btnRegresar.setVisible(false);
        ContarImagenes();
    }
    
    void LimpiarCampos(){
        ImageIcon nulo = new ImageIcon("src/Recursos_img/NoImage200px.png");
        FrmInventario.lblImagenProducto.setIcon(nulo);
        FrmInventario.lblModificarVisualizar.setIcon(nulo);
        //Se extraen la información
        FrmInventario.txtVNombre.setText(null);
        FrmInventario.txtVCodigo.setText(null);
        FrmInventario.txtVCantidad.setText(null);
        FrmInventario.txtVCategoria.setText(null);
        FrmInventario.txtVCodigo.setText(null);
        FrmInventario.txtVColor.setText(null);
        FrmInventario.txtVDistribuidor.setText(null);
        FrmInventario.txtVEstado.setText(null);
        FrmInventario.txtVFechaEntrega.setText(null);
        FrmInventario.txtVFechaInicio.setText(null);
        FrmInventario.txtVMarca.setText(null);
        FrmInventario.txtVMaterial.setText(null);
        FrmInventario.txtVTipoInventario.setText(null);
        FrmInventario.txtVPrecio.setText(null);
        FrmInventario.txtVTamaño.setText(null);
        FrmInventario.txtVTipo.setText(null);
        FrmInventario.txtVDescuento.setText(null);
        FrmInventario.seleccion="";
        //Se agrega el cargan el resto de datos
        FrmInventario.txtModificarNombre.setText(null);
        FrmInventario.txtModificarPrecio1.setText(null);
        FrmInventario.txaModificarDescripción.setText(null);
        FrmInventario.txtModificarDescuento.setText(null);
    }
    
    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                pnlContenedor.setBackground(Piel);
                pnlTitulo.setBackground(Oscuro);
                break;
            case 2://Claro
                pnlContenedor.setBackground(Color.white);
                pnlTitulo.setBackground(AzulOscuro);
                break;
            default:
                pnlContenedor.setBackground(Piel);
                pnlTitulo.setBackground(Oscuro);
                break;
        }        
    }
    
    void LlenarCampos(){
        //Se cargan los datos para le menú de visualizar
        Object[] datos = ControladorInventario.Cargarmenu(CodigoProducto);//Visualizar contiene solamente datos del tipo String
        //Se cargan los datos para le menú de modificar
        Object[] DatosM = ControladorInventario.CargarMenuModificar(CodigoProducto); //Modificar tiene datos especiales (Llaves primarias)
        try{
            LimpiarCampos();
            //Se carga la cadena de byte
            byte[] imagene = (byte[]) DatosM[4];
            //Se leen los datos
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(imagene));
            //Se carga la imagen en los formularios
            Image imagen = img.getScaledInstance(FrmInventario.lblImagenProducto.getWidth(), FrmInventario.lblImagenProducto.getHeight(), Image.SCALE_SMOOTH);
            FrmInventario.lblImagenProducto.setIcon(new ImageIcon(imagen));
            FrmInventario.lblModificarVisualizar.setIcon(new ImageIcon(imagen));
        }catch (Exception e){
            //Se envía un error si no es compatible
            FrmNotiError NError = new FrmNotiError();
            NError.setVisible(true);
            NError.TAnotiError.setText("¡Error al cargar la imagen!");
            NError.lblTituloError.setText("La imagen no pudo ser cargada");
        }
        //Se cargan los datos en visualizar
        //Se extraen la información
        FrmInventario.txtVNombre.setText(String.valueOf(datos[1]));
        FrmInventario.txtVCodigo.setText(String.valueOf(datos[0]));
        FrmInventario.txtVCantidad.setText(String.valueOf(datos[3]));
        FrmInventario.txtVCategoria.setText(String.valueOf(datos[15]));
        FrmInventario.txtVCodigo.setText(String.valueOf(datos[0]));
        FrmInventario.txtVColor.setText(String.valueOf(datos[9]));
        FrmInventario.txtVDistribuidor.setText(String.valueOf(datos[11]));
        FrmInventario.txtVEstado.setText(String.valueOf(datos[14]));
        FrmInventario.txtVFechaEntrega.setText(String.valueOf(datos[5]));
        FrmInventario.txtVFechaInicio.setText(String.valueOf(datos[6]));
        FrmInventario.txtVMarca.setText(String.valueOf(datos[10]));
        FrmInventario.txtVMaterial.setText(String.valueOf(datos[16]));
        FrmInventario.txtVTipoInventario.setText(String.valueOf(datos[13]));
        FrmInventario.txtVPrecio.setText(String.valueOf(datos[2]));
        FrmInventario.txtVTamaño.setText(String.valueOf(datos[8]));
        FrmInventario.txtVTipo.setText(String.valueOf(datos[12]));
        FrmInventario.txtVDescuento.setText(String.valueOf(datos[17]));
        try {
            //Se cargan los datos en modificar
            //Se extraen la información
            //Se coloca el modelo
            FrmInventario.spnCantidad.setModel(Spinner);
            FrmInventario.Spinner.setValue(Integer.parseInt(String.valueOf(DatosM[3]))); //Cantidad
            //Se cargan los ID a los comboBox
            //A los combobox se les agrega -1 porque se trae desde la base empezando desde 1, pero los combobox empiezan desde 0
            FrmInventario.cmbModificarCategoria.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[15])) - 1); //IDModificar
            FrmInventario.cmbModificarColor.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[9])) - 1); //IDColor
            FrmInventario.cmbModificarDistribuidor.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[11])) - 1); //IDDistribuidor
            FrmInventario.cmbModificarMarca.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[10])) - 1); //IDMarca
            FrmInventario.cmbModificarEstado.setSelectedIndex(Integer.parseInt(String.valueOf(String.valueOf(DatosM[14]))) - 1); //IDEstado
            FrmInventario.cmbModificarTipo.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[12])) - 1); //IDTipoProducto
            FrmInventario.cmbModificarTipoInventario.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[13])) - 1); //IDTipoInventario
            FrmInventario.cmbModificarTamaño.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[8])) - 1); //IDTamaño
            FrmInventario.cmbModificarMaterial.setSelectedIndex(Integer.parseInt(String.valueOf(DatosM[16])) - 1); //IDMaterial
            //Se crea el nuevo formato
            SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
            FrmInventario.dcrModificarFI.setDate(Formato.parse(String.valueOf(DatosM[5])));
            FrmInventario.dcrModificarFF.setDate(Formato.parse(String.valueOf(DatosM[6])));
            //Se agrega el cargan el resto de datos
            FrmInventario.txtModificarNombre.setText(String.valueOf(DatosM[1]));
            FrmInventario.txtModificarPrecio1.setText(String.valueOf(String.valueOf(DatosM[2])));
            FrmInventario.txaModificarDescripción.setText(String.valueOf(DatosM[7]));
            FrmInventario.txtModificarDescuento.setText(String.valueOf(DatosM[17]));
        } catch (Exception e) {
            //Se envía un error si es para cargar
            FrmNotiError NError = new FrmNotiError();
            NError.setVisible(true);
            NError.TAnotiError.setText("¡Error al cargar!");
            NError.lblTituloError.setText("Los datos seleccionados no concuerdan");
        }
    }
    
    public void EnviarDatos(){
        try{
            switch(dato){
                case 1:
                    CodigoProducto = Codigo1;
                    LlenarCampos();
                    break;
                case 2:
                    CodigoProducto = Codigo2;
                    LlenarCampos();
                    break;
                case 3:
                    CodigoProducto = Codigo3;
                    LlenarCampos();
                    break;
                case 4:
                    CodigoProducto = Codigo4;
                    LlenarCampos();
                    break;
                case 5:
                    CodigoProducto = Codigo5;
                    LlenarCampos();
                    break;
                case 6:
                    CodigoProducto = Codigo6;
                    LlenarCampos();
                    break;
                default:
                    break;
            }
        }
        catch(Exception e){
            
        }
    }
    
    void ExistenciaProductos(){
        Existencias = ControladorBuscador.ExistenciaProductosControlador();
        if(Existencias > 6){
            btnAvanzar.setVisible(true);
        }
        else if(Existencias < 6 && Existencias >= 0){
            btnAvanzar.setVisible(false);
        }
        else{
            error.TAnotiError.setText("Ha ocurrido un error al establecer la conexión con la base de datos. No se ha podido obtener la cantidad de productos.");
            error.setVisible(true);
        }
    }
    
    void ExistenciaProductos2(){
        Existencias = ControladorBuscador.ExistenciaProductosControlador2();
        if(Existencias > 6){
            btnAvanzar.setVisible(true);
        }
        else if(Existencias < 6 && Existencias >= 0){
            btnAvanzar.setVisible(false);
        }
        else{
            error.TAnotiError.setText("Ha ocurrido un error al establecer la conexión con la base de datos. No se ha podido obtener la cantidad de productos.");
            error.setVisible(true);
        }
    }
    
    void ContadorRetroceso(){
        if(Retroceso == 0){
            btnRegresar.setVisible(false);
        }else{
            btnRegresar.setVisible(true);
        }
    }
    
    void ExistenciaProductos3(){
        Existencias = ControladorBuscador.ExistenciaProductosControlador3();
        if(Existencias > 6){
            btnAvanzar.setVisible(true);
        }
        else if(Existencias < 6 && Existencias >= 0){
            btnAvanzar.setVisible(false);
        }
        else{
            error.TAnotiError.setText("Ha ocurrido un error al establecer la conexión con la base de datos. No se ha podido obtener la cantidad de productos.");
            error.setVisible(true);
        }
    }
    
    void ContarImagenes(){
        ContadorRetroceso();
        anchura = lblImagen1.getWidth();
        altura = lblImagen1.getHeight();
        obj.setCantidad(Cantidad);
        datos = ControladorBuscador.ObtenerImagenesControlador();
        if(datos.isEmpty()== false){
            Valores = datos.size();
            ExistenciaProductos3();
            CargarImagenes();
        }
        else{
            
        }
    }
    
    void ContarImagenes2(){
        ContadorRetroceso();
        anchura = lblImagen1.getWidth();
        altura = lblImagen1.getHeight();
        obj.setCantidad(Cantidad);
        obj.setNombreP(Palabra);
        datos = ControladorBuscador.ObtenerImagenesControlador2();
        if(datos.isEmpty()== false){
            Valores = datos.size();
            ExistenciaProductos3();
            CargarImagenes2();
        }
        else{
            
        }
    }
    
    void VaciarFotos(){
        lblImagen1.setIcon(null);
        lblImagen2.setIcon(null);
        lblImagen3.setIcon(null);
        lblImagen4.setIcon(null);
        lblImagen5.setIcon(null);
        lblImagen6.setIcon(null);
        Codigo1 = null;
        Codigo2 = null;
        Codigo3 = null;
        Codigo4 = null;
        Codigo5 = null;
        Codigo6 = null;
        CodigoProducto = null;
    }
    
    void CargarImagenes(){
        try{
            switch(Valores){
            case 0:
                VaciarFotos();
                lblImagen1.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen1));
                lblImagen2.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen2));
                lblImagen3.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen3));
                lblImagen4.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen4));
                lblImagen5.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen5));
                lblImagen6.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen6));
                break;    
            case 1:
                VaciarFotos();
                lblImagen2.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen2));
                lblImagen3.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen3));
                lblImagen4.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen4));
                lblImagen5.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen5));
                lblImagen6.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen6));
                CargarImagen1();
                CargarCodigo1();
                break;
            case 2:
                VaciarFotos();
                lblImagen3.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen3));
                lblImagen4.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen4));
                lblImagen5.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen5));
                lblImagen6.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen6));
                CargarImagen1();
                CargarImagen2();
                CargarCodigo1();
                CargarCodigo2();
                break;
            case 3:
                VaciarFotos();
                lblImagen4.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen4));
                lblImagen5.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen5));
                lblImagen6.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen6));
                CargarImagen1();
                CargarImagen2();
                CargarImagen3();
                CargarCodigo1();
                CargarCodigo2();
                CargarCodigo3();
                break;
            case 4:          
                VaciarFotos();
                lblImagen5.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen5));
                lblImagen6.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen6));
                CargarImagen1();
                CargarImagen2();
                CargarImagen3();
                CargarImagen4();
                CargarCodigo1();
                CargarCodigo2();
                CargarCodigo3();
                CargarCodigo4();
                break;
            case 5:  
                VaciarFotos();
                lblImagen6.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen6));
                CargarImagen1();
                CargarImagen2();
                CargarImagen3();
                CargarImagen4();
                CargarImagen5();
                CargarCodigo1();
                CargarCodigo2();
                CargarCodigo3();
                CargarCodigo4();
                CargarCodigo5();
                break;
            case 6:
                VaciarFotos();
                CargarImagen1();
                CargarImagen2();
                CargarImagen3();
                CargarImagen4();
                CargarImagen5();
                CargarImagen6();
                CargarCodigo1();
                CargarCodigo2();
                CargarCodigo3();
                CargarCodigo4();
                CargarCodigo5();
                CargarCodigo6();
                break;
            default:
                VaciarFotos();
                lblImagen1.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen1));
                lblImagen2.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen2));
                lblImagen3.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen3));
                lblImagen4.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen4));
                lblImagen5.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen5));
                lblImagen6.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen6));
                break;
            }
        }
        catch(Exception e){
            
        }
    }
    
    void CargarImagenes2(){
        try{
            switch(Valores){
            case 0:
                VaciarFotos();
                lblImagen1.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen1));
                lblImagen2.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen2));
                lblImagen3.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen3));
                lblImagen4.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen4));
                lblImagen5.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen5));
                lblImagen6.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen6));
                break;    
            case 1:
                VaciarFotos();
                lblImagen2.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen2));
                lblImagen3.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen3));
                lblImagen4.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen4));
                lblImagen5.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen5));
                lblImagen6.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen6));
                CargarCodigo1_2();
                CargarImagen1();
                break;
            case 2:
                VaciarFotos();
                lblImagen3.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen3));
                lblImagen4.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen4));
                lblImagen5.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen5));
                lblImagen6.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen6));
                CargarCodigo1_2();
                CargarCodigo2_2();
                CargarImagen1();
                CargarImagen2();
                break;
            case 3:
                VaciarFotos();
                lblImagen4.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen4));
                lblImagen5.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen5));
                lblImagen6.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen6));
                CargarCodigo1_2();
                CargarCodigo2_2();
                CargarCodigo3_2();
                CargarImagen1();
                CargarImagen2();
                CargarImagen3();
                break;
            case 4:          
                VaciarFotos();
                lblImagen5.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen5));
                lblImagen6.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen6));
                CargarCodigo1_2();
                CargarCodigo2_2();
                CargarCodigo3_2();
                CargarCodigo4_2();
                CargarImagen1();
                CargarImagen2();
                CargarImagen3();
                CargarImagen4();
                break;
            case 5:  
                VaciarFotos();
                lblImagen6.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen6));
                CargarCodigo1_2();
                CargarCodigo2_2();
                CargarCodigo3_2();
                CargarCodigo4_2();
                CargarCodigo5_2();
                CargarImagen1();
                CargarImagen2();
                CargarImagen3();
                CargarImagen4();
                CargarImagen5();
                break;
            case 6:
                VaciarFotos();
                CargarCodigo1_2();
                CargarCodigo2_2();
                CargarCodigo3_2();
                CargarCodigo4_2();
                CargarCodigo5_2();
                CargarCodigo6_2();
                CargarImagen1();
                CargarImagen2();
                CargarImagen3();
                CargarImagen4();
                CargarImagen5();
                CargarImagen6();
                break;
            default:
                VaciarFotos();
                lblImagen1.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen1));
                lblImagen2.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen2));
                lblImagen3.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen3));
                lblImagen4.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen4));
                lblImagen5.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen5));
                lblImagen6.setIcon(CambiarIcono("/Recursos_img/NoImage200px.png", lblImagen6));
                break;
            }
        }
        catch(Exception e){
            
        }
    }
    
    void CargarImagen1(){
        try{
            Blob foto = datos.get(0);
            byte[] recuperar = foto.getBytes(1, (int) foto.length());
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(recuperar));
            Image imagen = img.getScaledInstance(anchura, altura, Image.SCALE_SMOOTH);
            lblImagen1.setIcon(new ImageIcon(imagen));
        }
        catch(Exception e){
        
        }
    }
    
    void CargarImagen2(){
        try{
            Blob foto = datos.get(1);
            byte[] recuperar = foto.getBytes(1, (int) foto.length());
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(recuperar));
            Image imagen = img.getScaledInstance(anchura, altura, Image.SCALE_SMOOTH);
            lblImagen2.setIcon(new ImageIcon(imagen));
        }
        catch(Exception e){
        
        }
    }
        
    void CargarImagen3(){
        try{
            Blob foto = datos.get(2);
            byte[] recuperar = foto.getBytes(1, (int) foto.length());
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(recuperar));
            Image imagen = img.getScaledInstance(anchura, altura, Image.SCALE_SMOOTH);
            lblImagen3.setIcon(new ImageIcon(imagen));
        }
        catch(Exception e){
        
        }
    }    
    
    void CargarImagen4(){
        try{
            Blob foto = datos.get(3);
            byte[] recuperar = foto.getBytes(1, (int) foto.length());
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(recuperar));
            Image imagen = img.getScaledInstance(anchura, altura, Image.SCALE_SMOOTH);
            lblImagen4.setIcon(new ImageIcon(imagen));
        }
        catch(Exception e){
        
        }
    }
    
    void CargarImagen5(){
        try{
            Blob foto = datos.get(4);
            byte[] recuperar = foto.getBytes(1, (int) foto.length());
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(recuperar));
            Image imagen = img.getScaledInstance(anchura, altura, Image.SCALE_SMOOTH);
            lblImagen5.setIcon(new ImageIcon(imagen));
        }
        catch(Exception e){
        
        }
    }
    
    void CargarImagen6(){
        try{
            Blob foto = datos.get(5);
            byte[] recuperar = foto.getBytes(1, (int) foto.length());
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(recuperar));
            Image imagen = img.getScaledInstance(anchura, altura, Image.SCALE_SMOOTH);
            lblImagen6.setIcon(new ImageIcon(imagen));
        }
        catch(Exception e){
            
        }
    }
    
    void CargarCodigo1(){
        try{
            datos2 = ControladorBuscador.ObtenerCodigoControlador();
            if(datos2.isEmpty()== false){
                Codigo1 = datos2.get(0);
            }
        }
        catch(Exception e){
        
        }
    }
    
    void CargarCodigo1_2(){
        try{
            datos2 = ControladorBuscador.ObtenerCodigoControlador2();
            if(datos2.isEmpty()== false){
                Codigo1 = datos2.get(0);
            }
        }
        catch(Exception e){
        
        }
    }
    
    void CargarCodigo2(){
        try{
            datos2 = ControladorBuscador.ObtenerCodigoControlador();
            if(datos2.isEmpty()== false){
                Codigo2 = datos2.get(1);
            }
        }
        catch(Exception e){
        
        }
    }
    
    void CargarCodigo2_2(){
        try{
            datos2 = ControladorBuscador.ObtenerCodigoControlador2();
            if(datos2.isEmpty()== false){
                Codigo2 = datos2.get(1);
            }
        }
        catch(Exception e){
        
        }
    }
        
    void CargarCodigo3(){
        try{
            datos2 = ControladorBuscador.ObtenerCodigoControlador();
            if(datos2.isEmpty()== false){
                Codigo3 = datos2.get(2);
            }
        }
        catch(Exception e){
        
        }
    }
    
    void CargarCodigo3_2(){
        try{
            datos2 = ControladorBuscador.ObtenerCodigoControlador2();
            if(datos2.isEmpty()== false){
                Codigo3 = datos2.get(2);
            }
        }
        catch(Exception e){
        
        }
    }

    void CargarCodigo4(){
        try{
            datos2 = ControladorBuscador.ObtenerCodigoControlador();
            if(datos2.isEmpty()== false){
                Codigo4 = datos2.get(3);
            }
        }
        catch(Exception e){
        
        }
    }    
    
    void CargarCodigo4_2(){
        try{
            datos2 = ControladorBuscador.ObtenerCodigoControlador2();
            if(datos2.isEmpty()== false){
                Codigo4 = datos2.get(3);
            }
        }
        catch(Exception e){
        
        }
    }    
    
    void CargarCodigo5(){
        try{
            datos2 = ControladorBuscador.ObtenerCodigoControlador();
            if(datos2.isEmpty()== false){
                Codigo5 = datos2.get(4);
            }
        }
        catch(Exception e){
        
        }
    }
    
    void CargarCodigo5_2(){
        try{
            datos2 = ControladorBuscador.ObtenerCodigoControlador2();
            if(datos2.isEmpty()== false){
                Codigo5 = datos2.get(4);
            }
        }
        catch(Exception e){
        
        }
    }
    
    void CargarCodigo6(){
        try{
            datos2 = ControladorBuscador.ObtenerCodigoControlador();
            if(datos2.isEmpty()== false){
                Codigo6 = datos2.get(5);
            }
        }
        catch(Exception e){
        
        }
    }
    
    void CargarCodigo6_2(){
        try{
            datos2 = ControladorBuscador.ObtenerCodigoControlador2();
            if(datos2.isEmpty()== false){
                Codigo6 = datos2.get(5);
            }
        }
        catch(Exception e){
        
        }
    }
    
    public Icon CambiarIcono(String url, JLabel label) {
        ImageIcon icon = new ImageIcon(getClass().getResource(url));
        int ancho = label.getWidth();
        int alto = label.getHeight();
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return icono;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblCerrar = new javax.swing.JLabel();
        pnlContenedor = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        lblImagen3 = new javax.swing.JLabel();
        lblImagen6 = new javax.swing.JLabel();
        lblTexto = new javax.swing.JLabel();
        lblImagen2 = new javax.swing.JLabel();
        lblImagen5 = new javax.swing.JLabel();
        lblImagen4 = new javax.swing.JLabel();
        lblImagen1 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JLabel();
        btnAvanzar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        getContentPane().setLayout(null);

        pnlTitulo.setBackground(new java.awt.Color(14, 13, 21));
        pnlTitulo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlTituloMouseDragged(evt);
            }
        });
        pnlTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTituloMousePressed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Búsqueda");

        lblCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Close.png"))); // NOI18N
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTituloLayout.createSequentialGroup()
                .addContainerGap(752, Short.MAX_VALUE)
                .addComponent(lblCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlTituloLayout.createSequentialGroup()
                    .addGap(0, 264, Short.MAX_VALUE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 264, Short.MAX_VALUE)))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlTituloLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        getContentPane().add(pnlTitulo);
        pnlTitulo.setBounds(0, 0, 800, 56);

        pnlContenedor.setBackground(new java.awt.Color(255, 222, 212));
        pnlContenedor.setMinimumSize(new java.awt.Dimension(800, 600));
        pnlContenedor.setPreferredSize(new java.awt.Dimension(800, 600));
        pnlContenedor.setLayout(null);

        lblBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Buscador50px.png"))); // NOI18N
        lblBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBuscarMouseClicked(evt);
            }
        });
        pnlContenedor.add(lblBuscar);
        lblBuscar.setBounds(570, 20, 30, 30);

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });
        pnlContenedor.add(txtBusqueda);
        txtBusqueda.setBounds(270, 20, 290, 30);

        lblImagen3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/NoImage200px.png"))); // NOI18N
        lblImagen3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImagen3.setMaximumSize(new java.awt.Dimension(300, 300));
        lblImagen3.setMinimumSize(new java.awt.Dimension(300, 300));
        lblImagen3.setPreferredSize(new java.awt.Dimension(350, 350));
        lblImagen3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagen3MouseClicked(evt);
            }
        });
        pnlContenedor.add(lblImagen3);
        lblImagen3.setBounds(520, 100, 190, 190);

        lblImagen6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/NoImage200px.png"))); // NOI18N
        lblImagen6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImagen6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagen6MouseClicked(evt);
            }
        });
        pnlContenedor.add(lblImagen6);
        lblImagen6.setBounds(520, 330, 190, 190);

        lblTexto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTexto.setText("Productos encontrados:");
        pnlContenedor.add(lblTexto);
        lblTexto.setBounds(300, 60, 186, 22);

        lblImagen2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/NoImage200px.png"))); // NOI18N
        lblImagen2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImagen2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagen2MouseClicked(evt);
            }
        });
        pnlContenedor.add(lblImagen2);
        lblImagen2.setBounds(300, 100, 190, 190);

        lblImagen5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/NoImage200px.png"))); // NOI18N
        lblImagen5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImagen5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagen5MouseClicked(evt);
            }
        });
        pnlContenedor.add(lblImagen5);
        lblImagen5.setBounds(300, 330, 190, 190);

        lblImagen4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/NoImage200px.png"))); // NOI18N
        lblImagen4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImagen4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagen4MouseClicked(evt);
            }
        });
        pnlContenedor.add(lblImagen4);
        lblImagen4.setBounds(80, 330, 190, 190);

        lblImagen1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/NoImage200px.png"))); // NOI18N
        lblImagen1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImagen1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagen1MouseClicked(evt);
            }
        });
        pnlContenedor.add(lblImagen1);
        lblImagen1.setBounds(80, 100, 190, 190);

        btnRegresar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Izquierda.png"))); // NOI18N
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegresarMouseClicked(evt);
            }
        });
        pnlContenedor.add(btnRegresar);
        btnRegresar.setBounds(10, 280, 40, 60);

        btnAvanzar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAvanzar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Derecha.png"))); // NOI18N
        btnAvanzar.setText("jLabel1");
        btnAvanzar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAvanzar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAvanzarMouseClicked(evt);
            }
        });
        pnlContenedor.add(btnAvanzar);
        btnAvanzar.setBounds(750, 280, 41, 60);

        getContentPane().add(pnlContenedor);
        pnlContenedor.setBounds(-2, 56, 800, 550);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBuscarMouseClicked
        if(txtBusqueda.getText().trim().equals("")){
            notificacion.lblTituloNoti1.setText("Campos vacios");
            notificacion.TAMensajeError.setText("Todos los campos son requeridos");
            notificacion.setVisible(true);
        }
        else{
            Retroceso = 0;
            Cantidad = 0;
            Palabra = txtBusqueda.getText();
            btnAvanzar.setVisible(false);
            ExistenciaProductos2();
            ContarImagenes2();
        }
    }//GEN-LAST:event_lblBuscarMouseClicked

    private void btnRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseClicked
        Retroceso = Retroceso - 1;
        btnRegresar.setVisible(true);
        this.Cantidad = Cantidad - 6;
        ContarImagenes();
    }//GEN-LAST:event_btnRegresarMouseClicked

    private void btnAvanzarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvanzarMouseClicked
        Retroceso = Retroceso + 1;
        btnRegresar.setVisible(true);
        this.Cantidad = Cantidad + 6;
        ContarImagenes();
    }//GEN-LAST:event_btnAvanzarMouseClicked

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        FrmInventario inventario = new FrmInventario();
        inventario.ValidarBuscador = 0;
        this.dispose();
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void lblImagen1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagen1MouseClicked
        dato = 1;
        EnviarDatos();
    }//GEN-LAST:event_lblImagen1MouseClicked

    private void lblImagen2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagen2MouseClicked
        dato = 2;
        EnviarDatos();
    }//GEN-LAST:event_lblImagen2MouseClicked

    private void lblImagen3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagen3MouseClicked
        dato = 3;
        EnviarDatos();
    }//GEN-LAST:event_lblImagen3MouseClicked

    private void lblImagen4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagen4MouseClicked
        dato = 4;
        EnviarDatos();
    }//GEN-LAST:event_lblImagen4MouseClicked

    private void lblImagen5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagen5MouseClicked
        dato = 5;
        EnviarDatos();
    }//GEN-LAST:event_lblImagen5MouseClicked

    private void lblImagen6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagen6MouseClicked
        dato = 6;
        EnviarDatos();
    }//GEN-LAST:event_lblImagen6MouseClicked

    private void pnlTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTituloMousePressed
        //Inicializamos las variables x y y con la posicion actual del form
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_pnlTituloMousePressed

    private void pnlTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTituloMouseDragged
        //Creamos metodo que actualice la posicion del form dependendiendo donde el usuario lo coloque
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_pnlTituloMouseDragged

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        Palabra = txtBusqueda.getText();
        obj.setNombreP(txtBusqueda.getText());
    }//GEN-LAST:event_txtBusquedaKeyTyped

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmBuscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBuscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBuscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBuscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBuscador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAvanzar;
    private javax.swing.JLabel btnRegresar;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JLabel lblImagen1;
    private javax.swing.JLabel lblImagen2;
    private javax.swing.JLabel lblImagen3;
    private javax.swing.JLabel lblImagen4;
    private javax.swing.JLabel lblImagen5;
    private javax.swing.JLabel lblImagen6;
    private javax.swing.JLabel lblTexto;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
