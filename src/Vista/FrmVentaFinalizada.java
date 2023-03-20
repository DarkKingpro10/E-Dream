/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorCobroVenta;
import Controlador.ControladorVariables;
import Controlador.Validaciones;
import Modelo.Conexion;
import Recursos_Tipografias.Fuentes;
import java.awt.Color;
import java.awt.Image;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author SeyJR
 */
public class FrmVentaFinalizada extends javax.swing.JFrame {

    /**
     * Creates new form frmVentaFinalizada
     */
    //Creamos objeto del controlador de la clase validaciones
    Validaciones validaciones = new Validaciones();

    //Se crea formato de decimales
    DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.US);
    DecimalFormat Formato = new DecimalFormat("###,###.##",simbolos);
    //Se cre objeto de controlador cobro venta
    ControladorCobroVenta obj = new ControladorCobroVenta();

    //Creamos variables globales X y Y que ayudaran en metodo para mover el form
    private int x, y;
    
    //Se crena ArryLista globales
    ArrayList<Integer> CargarIdTipoPago = new ArrayList<Integer>();
    ArrayList<Integer> CargarIdVenta = new ArrayList<Integer>();
    ArrayList<Integer> CargarIdDetalle = new ArrayList<Integer>();
    ArrayList<Integer> CargarTotalV = new ArrayList<Integer>();
    
    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Morado = new Color(94,45,79);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);
    

    public FrmVentaFinalizada() {
        initComponents();
        setLocationRelativeTo(null);
        Colores();
        /*Utilizamos new ImageIcon(URL location).getImage() donde el argumento
        *location lo obtenemos a través del método getClass().getResource(String name)
        *que nos devuelve un URL absoluto del recurso que especificamos como String.
        */
        Image icon = new ImageIcon(getClass().getResource("/Recursos_img/E-Dream logo.png")).getImage();
        setIconImage(icon);

        //El tipo de fuente
        Fuentes tipoFuente;

        //Se le asigna tipo de fuente a todos los componentes
        tipoFuente = new Fuentes();
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 40));
        lblTotal.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblTipoPago.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblDinero.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblVuelto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnCancelar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnImprimir.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtTotal.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtDinero.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVuelto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbTipoPago.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));

        //Cargamos combobox
        CargarTipopago();
        CargarIdVenta();
        CargarIdDetalle();

        //Creamos objeto de controlador cobro venta
        ControladorCobroVenta ventaC = new ControladorCobroVenta();
        // txtTotal.setText(String.valueOf(ventaC.gettotalPago()));//Obtenemos el total de pago y se lo asignamos al textfield total
       
        //Cargamos metodo de cargar total al textbox
        txtTotal.setText(String.valueOf(obj.CargarSumaTotalV()));
        System.out.println(obj.CargarSumaTotalV());
       
        //Asignamos lo que valdra el ArrayLits
        CargarIdTipoPago = obj.idTipoPago();
        CargarIdVenta = obj.idVenta();
        CargarIdDetalle = obj.idDetalle();
        
        
        //Se pone invisible el label de validacion
        CI.setVisible(false);
    }
    
    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                jPanel1.setBackground(Piel);
                jPanel2.setBackground(Oscuro);
                break;
            case 2://Claro
                jPanel1.setBackground(Color.white);
                jPanel2.setBackground(AzulOscuro);
                break;
            default:
                jPanel1.setBackground(Piel);
                jPanel2.setBackground(Oscuro);
                break;
        }        
    }

    //Cargar ComboBox id
    void CargarIdVenta() {
        //Creamos un defaultmodel llamado lista
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        //Le asignamos al Resultset el valor del metodo de resultset que se encuentra en controlador
        ResultSet res = Controlador.ControladorDetalleVenta.CargarIdVentaController();
        try {
            while (res.next()) {
                //Agregamos el id de venta como elemento a la lista
                lista.addElement(res.getString("idVenta"));
            }
        } catch (Exception e) {
            //Si ocurre un error mandamos a llamar al frm de errores
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de Conexión");
            error.TAnotiError.setText("Ocurrio un error "
                    + "\n"
                    + "al establecer conexión"
                    + "\n"
                    + " con la base de datos,"
                    + "\n"
                    + " verifique su acceso a "
                    + "\n"
                    + "internet o que los servicios "
                    + "\n"
                    + "del servidor estén activos");
            error.setVisible(true);
        }
        //Le signamos el modelo al combobox
        cmbIdVenta.setModel(lista);
    }

    //Cargar ComboBox id
    void CargarIdDetalle() {
        //Creamos un defaultmodel llamado lista
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        //Le asignamos al Resultset el valor del metodo de resultset que se encuentra en controlador
        ResultSet res = Controlador.ControladorCobroVenta.CargarIdDetalleController();
        try {
            while (res.next()) {
                //Agregamos el id de detalle como elemento a la lista
                lista.addElement(res.getString("idDetalle"));
            }
        } catch (Exception e) {
            //Si ocurre un error mandamos a llamar al frm de errores
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de Conexión");
            error.TAnotiError.setText("Ocurrio un error "
                    + "\n"
                    + "al establecer conexión"
                    + "\n"
                    + " con la base de datos,"
                    + "\n"
                    + " verifique su acceso a "
                    + "\n"
                    + "internet o que los servicios "
                    + "\n"
                    + "del servidor estén activos");
            error.setVisible(true);
        }
        //Le signamos el modelo al combobox
        cmbIdDetalle.setModel(lista);
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
        CI = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblCerrar = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        lblTipoPago = new javax.swing.JLabel();
        cmbTipoPago = new javax.swing.JComboBox<>();
        lblDinero = new javax.swing.JLabel();
        txtDinero = new javax.swing.JTextField();
        lblVuelto = new javax.swing.JLabel();
        txtVuelto = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbIdVenta = new javax.swing.JComboBox<>();
        cmbIdDetalle = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 600));
        setUndecorated(true);
        setSize(new java.awt.Dimension(500, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 222, 212));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CI.setForeground(new java.awt.Color(204, 0, 51));
        CI.setText("Cantidad insuficiente");
        jPanel1.add(CI, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, -1, -1));

        jPanel2.setBackground(new java.awt.Color(14, 13, 21));
        jPanel2.setPreferredSize(new java.awt.Dimension(144, 44));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Cobro");
        lblTitulo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lblTituloMouseDragged(evt);
            }
        });
        lblTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblTituloMousePressed(evt);
            }
        });
        jPanel2.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 390, -1));

        lblCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/CloseRed.png"))); // NOI18N
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });
        jPanel2.add(lblCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 30, 32));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, -1));

        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/totalpago35px.png"))); // NOI18N
        lblTotal.setText("Total de pago:");
        jPanel1.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 348, -1));

        txtTotal.setEnabled(false);
        jPanel1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 121, 36));

        lblTipoPago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/tipopago35px.png"))); // NOI18N
        lblTipoPago.setText("Tipo de pago:");
        jPanel1.add(lblTipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 319, -1));

        cmbTipoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbTipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 126, 40));

        lblDinero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDinero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/dineroingresado35px.png"))); // NOI18N
        lblDinero.setText("Dinero ingresado:");
        jPanel1.add(lblDinero, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 323, -1));

        txtDinero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDineroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDineroKeyTyped(evt);
            }
        });
        jPanel1.add(txtDinero, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 124, 36));

        lblVuelto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVuelto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/vuelto35px.png"))); // NOI18N
        lblVuelto.setText("Vuelto:");
        jPanel1.add(lblVuelto, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 410, 510, -1));

        txtVuelto.setText("0");
        txtVuelto.setEnabled(false);
        txtVuelto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVueltoMouseClicked(evt);
            }
        });
        jPanel1.add(txtVuelto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, 120, 40));

        btnCancelar.setBackground(new java.awt.Color(164, 188, 188));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/cancelar30px.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 140, 40));

        btnImprimir.setBackground(new java.awt.Color(164, 188, 188));
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/imprimir30px.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel1.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 530, -1, 40));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("$");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("$");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("$");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, -1, -1));

        cmbIdVenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbIdVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        cmbIdDetalle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbIdDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Cargar ComboBox tipopago
    void CargarTipopago() {
        //Creamos un defaultmodel llamado lista
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        //Le asignamos al Resultset el valor del metodo de resultset que se encuentra en controlador
        ResultSet res = Controlador.ControladorCobroVenta.CargarTipoPagoController();
        try {
            while (res.next()) {
                //Agregamos el tipo de pago como elemento a la lista
                lista.addElement(res.getString("TipoPago"));
            }
        } catch (Exception e) {
            //Si ocurre un error mandamos a llamar al frm de errores
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de Conexión");
            error.TAnotiError.setText("Ocurrio un error al establecer "
                    + "\n"
                    + "conexión con la base de"
                    + "\n"
                    + " datos, verifique su acceso "
                    + "\n"
                    + "a internet o que los servicios "
                    + "\n"
                    + "del servidor estén activos");
            error.setVisible(true);
        }
        //Le signamos el modelo al combobox
        cmbTipoPago.setModel(lista);
    }

    //Metodo para calcular vuelto
    public void CalcularVuelto() {

        //Declaramos variables
        double vuelto;
        double costo = Double.parseDouble(txtTotal.getText());//Obtenemos el costo unitario
        double dineroIngresado = Double.parseDouble(txtDinero.getText());//Obtenemos el dinero ingresado

        //Operamos
        vuelto = (dineroIngresado - costo);

        //Le asignamos el vuelto al textfiel de vuelto
        txtVuelto.setText(String.valueOf(Formato.format(vuelto)));

    }
    //Metodo para limpiar campos

    public void Limpiar() {
        txtVuelto.setText("");
        txtTotal.setText("");
        txtDinero.setText("");

    }

    //Metodo para Actualizar venta
    public void ActualizarVenta() {

        try {

            //Declaramo variables para guardar el index del combobox
            int idDetalle = (Integer) cmbIdDetalle.getSelectedIndex();

            //Le asignamos el index del combobox al ArrayList
            Integer detalle = CargarIdDetalle.get(idDetalle);

            //Creamos objeto de controlador cobro venta
            Controlador.ControladorCobroVenta agregar = new Controlador.ControladorCobroVenta();
            //Guardamos el id de detalle
            agregar.setIdDetalle(detalle);

            //Guardamos en MysqL
            if (agregar.EliminarDetalleVentaController()) {

                //Para comprobar
                System.out.println("se elimino detalle");

                //Ejecutamos el metodo de cargar tabla para actualizar la eliminacion
                FrmDetalleFacturaVenta.mostrarDatos();
                //Limpiamos campos
                Limpiar();

            } else {
                System.out.println("No se elimino detalle");
            }
        } catch (Exception e) {
            System.out.println(e);

        }

    }
    
    //Creamos metodo para la conexion con reportes
    public void Reporte(){
         String path = "";
        try {
            //Establecemos la ruta donde esta en reporte
            path = getClass().getResource("/Reportes/FacturaV.jasper").getPath();

            System.out.println(path);
            //Se decodifica por algun caracter especial
            path = URLDecoder.decode(path, "UTF-8");
            //Se crea la conexion
            Connection cn = new Conexion().Conexion();
            //Se rea los prametros
            Map parametros = new HashMap();
            //Se crea el objeto de reporte
            JasperReport reporte = (JasperReport) JRLoader.loadObject(path);
            //Se crea el objeto impresion de reporte
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, cn);
            //Ahora e crea el vior donde e muestra el reporte
            JasperViewer visor = new JasperViewer(imprimir, false);//False es para dispose
            visor.setTitle("Reporte de proyectos");
            visor.setVisible(true);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    
    }

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        //Cerramos el form
        this.dispose();
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        //Cerramos el form
        this.dispose();

        //Ejecutamos metodo de actualiza la venta
        ActualizarVenta();
    }//GEN-LAST:event_btnCancelarActionPerformed


    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed

        //Creamos objeto de formulario de notificacion para campo vacios
        FrmNoti1 campo = new FrmNoti1();
        //Creamos objeto de form de error
        FrmNotiError error = new FrmNotiError();
        
        try {
            //Vliidamos que no este ningun campo vacio
            if (txtDinero.getText().isEmpty()) {
                //Mostramos notificcion
                campo.setVisible(true);
                campo.TAMensajeError.setText("Existen campos sin"
                        + "\n"
                        + " datos asignados");
                campo.lblTituloNoti1.setText("¡Campos vacíos!");
            } else {

                //Declaramos variables, obteniendo el index de los combobox
                int idTipoP = (Integer) cmbTipoPago.getSelectedIndex();
                int idVenta = (Integer) cmbIdVenta.getSelectedIndex();

                //Creamos objeto de conntrolaor cobro venta
                Controlador.ControladorCobroVenta agregar = new Controlador.ControladorCobroVenta();

                //enviando la información a la clase
                Integer tipoPago = CargarIdTipoPago.get(idTipoP);
                Integer venta = CargarIdVenta.get(idVenta);

                agregar.setMontoTotalV(Double.parseDouble(txtTotal.getText()));//Obtenemos y guardamos el monto total           
                agregar.setVuelto(Double.parseDouble(txtVuelto.getText()));//Obtenemos y guardamos el vuelto
                agregar.setIdTipoPago(tipoPago);//Obtenemos y guardamos el tipo de pago
                agregar.setIdVenta(venta);//Obtenemos y guardamos la venta

                //enviando guardar a  SQLServer
                if (agregar.ActualizarVentaController()) {
                    //Comprobando
                    //Se envia un mensaje de confirmación
                    FrmNoti2 notificacion = new FrmNoti2();
                    notificacion.lblTituloNoti2.setText("¡Factura finalizada!");
                    notificacion.TANoti2.setText("La venta a finalizado \n exitosamente");
                    notificacion.setVisible(true);
                    
                    this.dispose();
                    Reporte();

                } else {
                    error.setVisible(true);
                    error.lblTituloError.setText("Error al crear");
                    error.TAnotiError.setText("No se pudo guardar el detalle"
                            + "\n"
                            + " de factura en la base "
                            + "\n"
                            + "de datos, problemas de conexión");
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtVueltoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVueltoMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_txtVueltoMouseClicked

    private void txtDineroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDineroKeyReleased
        try {
            //Defclarbndo variables 
            double ingresado = Double.parseDouble(txtDinero.getText());//Obtenemos el dinero ingresado
            double total = Double.parseDouble(txtTotal.getText());//Obtenemos el total 

            //Condicionamos si el dinero ingreado es menor al total
            if (ingresado < total) {
                //El label de validacion sera visibñle
                CI.setVisible(true);
            } else {
                //Si el dinero ingresado es mayor que el total el lable seria invisible
                CI.setVisible(false);
                //Se ejecuta el metodo de calcular vuelto
                CalcularVuelto();
            }

        } catch (Exception e) {
            System.out.print(e.toString());
        }

    }//GEN-LAST:event_txtDineroKeyReleased

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        // TODO add your handling code here:
        //cerramos formulario
        dispose();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void txtDineroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDineroKeyTyped
        //Ejecutamos validcion donde solo se podran numeros y punto
        validaciones.soloNumeros(evt, 5, txtDinero.getText());
    }//GEN-LAST:event_txtDineroKeyTyped

    private void lblTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTituloMousePressed
        //Inicializamos las variables x y y con la posicion actual del form
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_lblTituloMousePressed

    private void lblTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTituloMouseDragged
        //Creamos metodo que actualice la posicion del form dependendiendo donde el usuario lo coloque
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_lblTituloMouseDragged

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
            java.util.logging.Logger.getLogger(FrmVentaFinalizada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVentaFinalizada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVentaFinalizada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVentaFinalizada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVentaFinalizada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CI;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JComboBox<String> cmbIdDetalle;
    private javax.swing.JComboBox<String> cmbIdVenta;
    private javax.swing.JComboBox<String> cmbTipoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JLabel lblDinero;
    private javax.swing.JLabel lblTipoPago;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblVuelto;
    private javax.swing.JTextField txtDinero;
    public javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtVuelto;
    // End of variables declaration//GEN-END:variables
}
