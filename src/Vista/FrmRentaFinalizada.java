/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorCobroVenta;
import Controlador.ControladorDetalleRenta;
import Controlador.ControladorVariables;
import Controlador.Validaciones;
import Modelo.Conexion;
import Recursos_Tipografias.Fuentes;
import java.awt.Color;
import java.awt.Image;
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
import javax.swing.JComponent;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Oliver
 */
public class FrmRentaFinalizada extends javax.swing.JFrame {

    //Se crea el objeto de las clases
    ControladorDetalleRenta factura = new ControladorDetalleRenta();
    ControladorCobroVenta obj = new ControladorCobroVenta();
    Validaciones validaciones = new Validaciones();
    //Se crea el objeto del neuvo formulario
    FrmDetalleFacturaRenta renta = new FrmDetalleFacturaRenta();
    //Se obtiene el adelanto
    double adelanto = factura.getAdel();
    //Se obtienen los datos de los productos obtenidos del detalle
    ArrayList<Object[]> datos = factura.getDatos();
    //Se agrega el formato de la localidad
    DecimalFormatSymbols localidad = new DecimalFormatSymbols(Locale.US);
    //Formato para los precios (Números decimales)
    DecimalFormat Formato = new DecimalFormat("###,###.##", localidad);
    //Se crea los objetos de las notificaciones
    FrmNoti1 notificación = new FrmNoti1();
    FrmNoti2 Completado = new FrmNoti2();
    FrmNotiError error = new FrmNotiError();
    //Se crea la lista de los tipos de pago
    ArrayList<Integer> CargarIdTipoPago = new ArrayList<Integer>();
    //Creamos variables globales X y Y que ayudaran en metodo para mover el form
    //Objeto que guarda linea por linea del detalle
    Object[] producto;
    int x, y;
    
    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Morado = new Color(94,45,79);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);
    

    public FrmRentaFinalizada() {
        initComponents();
        setLocationRelativeTo(null);

        /*Utilizamos new ImageIcon(URL location).getImage() donde el argumento
        *location lo obtenemos a través del método getClass().getResource(String name)
        *que nos devuelve un URL absoluto del recurso que especificamos como String.
         */
        Image icon = new ImageIcon(getClass().getResource("/Recursos_img/E-Dream logo.png")).getImage();
        setIconImage(icon);

        Colores();
        //Se coloca la fuente a los componente
        Fuentes tipoFuente;
        tipoFuente = new Fuentes();
        //Se crea el los componentes
        JComponent[] Dinero = {txtDineroIngresado};
        //Se asigna la validación
        validaciones.noCopiar(Dinero);
        validaciones.noPegar(Dinero);
        CI.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 15));
        lblCostoTotal.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 20));
        lblDineroIngresado.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 20));
        lblTipoPago.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 20));
        lblVuelto.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 20));
        txtVuelto.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 20));
        cmbTipoPago.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVuelto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtDineroIngresado.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtCostoTotal.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 40));
        btnCancelar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnImprimir.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtCostoTotal.setText(String.valueOf(Formato.format(adelanto)));
        //Se esconde el mensaje
        CI.setVisible(false);
        //Se ejecuta el método para cargar el comobobox
        CargarTipopago();
        CargarIdTipoPago = obj.idTipoPago();
    }

    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                pnlContenedor.setBackground(Piel);
                onlTitulo.setBackground(Oscuro);
                break;
            case 2://Claro
                pnlContenedor.setBackground(Color.white);
                onlTitulo.setBackground(AzulOscuro);
                break;
            default:
                pnlContenedor.setBackground(Piel);
                onlTitulo.setBackground(Oscuro);
                break;
        }        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        onlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblCerrar = new javax.swing.JLabel();
        pnlContenedor = new javax.swing.JPanel();
        lblCostoTotal = new javax.swing.JLabel();
        txtCostoTotal = new javax.swing.JTextField();
        lblTipoPago = new javax.swing.JLabel();
        cmbTipoPago = new javax.swing.JComboBox<>();
        lblDineroIngresado = new javax.swing.JLabel();
        txtDineroIngresado = new javax.swing.JTextField();
        lblVuelto = new javax.swing.JLabel();
        txtVuelto = new javax.swing.JTextField();
        btnImprimir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CI = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 222, 212));
        setMinimumSize(new java.awt.Dimension(500, 600));
        setUndecorated(true);
        setSize(new java.awt.Dimension(500, 600));

        onlTitulo.setBackground(new java.awt.Color(14, 13, 21));
        onlTitulo.setPreferredSize(new java.awt.Dimension(95, 55));
        onlTitulo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                onlTituloMouseDragged(evt);
            }
        });
        onlTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                onlTituloMousePressed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
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

        lblCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/CloseRed.png"))); // NOI18N
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout onlTituloLayout = new javax.swing.GroupLayout(onlTitulo);
        onlTitulo.setLayout(onlTituloLayout);
        onlTituloLayout.setHorizontalGroup(
            onlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(onlTituloLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCerrar)
                .addContainerGap())
        );
        onlTituloLayout.setVerticalGroup(
            onlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lblCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlContenedor.setBackground(new java.awt.Color(255, 222, 212));
        pnlContenedor.setMinimumSize(new java.awt.Dimension(500, 600));
        pnlContenedor.setPreferredSize(new java.awt.Dimension(500, 600));

        lblCostoTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCostoTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCostoTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/costounitario35px.png"))); // NOI18N
        lblCostoTotal.setText("Costo total");

        txtCostoTotal.setText("0.0");
        txtCostoTotal.setEnabled(false);

        lblTipoPago.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTipoPago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/tipopago35px.png"))); // NOI18N
        lblTipoPago.setText("Tipo de pago");

        cmbTipoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTipoPago.setPreferredSize(new java.awt.Dimension(340, 40));

        lblDineroIngresado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDineroIngresado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDineroIngresado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/dineroingresado35px.png"))); // NOI18N
        lblDineroIngresado.setText("Dinero ingresado");

        txtDineroIngresado.setMinimumSize(new java.awt.Dimension(340, 40));
        txtDineroIngresado.setPreferredSize(new java.awt.Dimension(340, 40));
        txtDineroIngresado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDineroIngresadoFocusLost(evt);
            }
        });
        txtDineroIngresado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDineroIngresadoKeyReleased(evt);
            }
        });

        lblVuelto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblVuelto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVuelto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/vuelto35px.png"))); // NOI18N
        lblVuelto.setText("Vuelto");

        txtVuelto.setText("0.0");
        txtVuelto.setEnabled(false);
        txtVuelto.setMinimumSize(new java.awt.Dimension(340, 40));

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/imprimir30px.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setEnabled(false);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/cancelar30px.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("$");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("$");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("$");

        CI.setForeground(new java.awt.Color(204, 0, 51));
        CI.setText("Cantidad insuficiente");

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenedorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenedorLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(180, 180, 180))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenedorLayout.createSequentialGroup()
                        .addComponent(cmbTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(164, 164, 164))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenedorLayout.createSequentialGroup()
                        .addComponent(lblDineroIngresado, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenedorLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenedorLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDineroIngresado, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(177, 177, 177))))
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(177, 177, 177)
                        .addComponent(btnImprimir))
                    .addComponent(lblCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTipoPago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblCostoTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTipoPago)
                .addGap(18, 18, 18)
                .addComponent(cmbTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDineroIngresado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(CI)
                .addGap(12, 12, 12)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDineroIngresado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVuelto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(48, 48, 48)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(onlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(onlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    void CargarTipopago() {
        //Se crea el modelo
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        //Se obtiene los datos obtenidos desde el controlador
        ResultSet res = Controlador.ControladorCobroVenta.CargarTipoPagoController();
        try {
            //Se verifican la existencia de todos los datos
            while (res.next()) {
                //Se cargan los datos en el modelo
                lista.addElement(res.getString("TipoPago"));
            }
        } catch (Exception e) {
            //Si hay un error, entonces se notifica al usuario
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de Conexión");
            error.TAnotiError.setText("Ocurrio un error al establecer \n conexión con la base de \n datos, verifique su acceso \n a internet o que los servicios \n del servidor estén activos");
            error.setVisible(true);
        }
        //Se coloca el modelo al combobox
        cmbTipoPago.setModel(lista);
    }

    //Método que guarda los datos del detalle 
    public void CargarDatos() {
        for (int i = 0; i < datos.size(); i++) {
            try{
                //Se obtiene el objeto de la lista de objetos
                producto = datos.get(i);
                Object[] AcProducto = new Object[2];
                int can = factura.CantProductoIN(producto[3].toString());
                AcProducto[1] = producto[3];
                int obt = Integer.parseInt(producto[0].toString());
                AcProducto[0] = can - obt;
                //Se envían y se verifica si son correctos
                if (!factura.GuardarDetalle(producto) && !factura.ActualizarProductos(AcProducto)) {
                    System.out.print("Datos ingresados");
                } else {
                    //Si hay un error con los datos, se notifica al usuario
                    error.lblTituloError.setText("Error al ingresar");
                    error.TAnotiError.setText("Existen problemas con los datos");
                    error.setVisible(true);
                }
            } catch (Exception e) {
                //Si hay un error con al base se notifica
                error.lblTituloError.setText("Error al ingresar");
                error.TAnotiError.setText("Hubo problemas al guardarlos en la base de datos");
                error.setVisible(true);
            }
        }
    }

    void CrearFactura() {
        JasperReport factura;
        Connection conexion = Conexion.Conexion();
        Map parametro = new HashMap();
        try {
            parametro.put("CodigoFactura", producto[4]);
            factura = JasperCompileManager.compileReport("src/Reportes/TicketR.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(factura, parametro, conexion);
            JasperViewer ventana = new JasperViewer(jp, false);
            ventana.setTitle("Ticket de renta");
            ventana.setVisible(true);
        } catch (Exception e) {
            System.err.print(e);
        }
    }

    //Método que actualiza los datos de la factura
    public void Actulizar() {
        //Se cargan los datos desde el controlador
        Object[] datos = factura.getActualizarfactura();
        //Se obtiene el vuelto que se le devolverá al usuario
        datos[2] = txtVuelto.getText();
        //Se cargan los datos en el controlador
        factura.setActualizarfactura(datos);
        for (int i = 0; i < datos.length; i++) {
            System.out.println("Daot en eivado" + datos[i] + "\n");
        }
        //Se verifica el estado de la actualización
        if (!factura.ActualizarFactura()) {
            //Se envia un mensaje de confirmación
            Completado.lblTituloNoti2.setText("¡Factura finalizada!");
            Completado.TANoti2.setText("La renta a finalizado \n exitosamente");
            Completado.setVisible(true);
            //Se cierra el formualrio
            this.dispose();

        } else {
            //Si existe algun problema, se le notifica al usuario
            error.lblTituloError.setText("Error al crear");
            error.TAnotiError.setText("No se pudo guardar la factura \n en la base de datos, \n problema de conexión");
            error.setVisible(true);
        }

    }
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        //Se ejecutan los métodos para guardar el detalle y actualizar la factura
        CargarDatos();
        Actulizar();
        CrearFactura();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        //Se cierra el formulario
        this.dispose();
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void txtDineroIngresadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDineroIngresadoKeyReleased
        try {
            validaciones.soloNumeros(evt, 3, txtDineroIngresado.getText());
            if (!txtDineroIngresado.getText().isEmpty()) {
                //Se cargan los datos para evaluarlos
                double entregado = Double.parseDouble(txtDineroIngresado.getText());
                //Se verifica la cantidad sobrante
                double saldo = entregado - Double.parseDouble(txtCostoTotal.getText());
                //Se declaran la variable inicial
                String resultado = "0.0";
                //Si el resultado es menor a cero, se le notifica que se necesita ingresar más
                if (saldo < 0) {
                    CI.setVisible(true);
                    btnImprimir.setEnabled(false);
                } else {
                    //Sino se coloca le vuelto
                    CI.setVisible(false);
                    resultado = String.valueOf(Formato.format(saldo));
                    btnImprimir.setEnabled(true);
                }
                //Se coloca el texto del vuelto
                txtVuelto.setText(resultado);
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_txtDineroIngresadoKeyReleased

    private void txtDineroIngresadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDineroIngresadoFocusLost
        //Se verifica la cantidad del texto ingresado
        try{
            if (Double.parseDouble(txtDineroIngresado.getText()) < 0) {
            notificación.TAMensajeError.setText("El valor del monto \n no puede ser negativo");
            notificación.lblTituloNoti1.setText("Tipo de dato incorrecto");
            notificación.setVisible(true);
            }
        }catch(Exception e){
        }
        
    }//GEN-LAST:event_txtDineroIngresadoFocusLost

    private void onlTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onlTituloMousePressed
        // TODO add your handling code here:
        //Inicializamos las variables x y y con la posicion actual del form
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_onlTituloMousePressed

    private void onlTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onlTituloMouseDragged
        // TODO add your handling code here:
        //Creamos metodo que actualice la posicion del form dependendiendo donde el usuario lo coloque
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_onlTituloMouseDragged

    private void lblTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTituloMouseDragged
        //Creamos metodo que actualice la posicion del form dependendiendo donde el usuario lo coloque
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_lblTituloMouseDragged

    private void lblTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTituloMousePressed
        //Inicializamos las variables x y y con la posicion actual del form
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_lblTituloMousePressed

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
            java.util.logging.Logger.getLogger(FrmRentaFinalizada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRentaFinalizada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRentaFinalizada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRentaFinalizada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FrmRentaFinalizada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CI;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JComboBox<String> cmbTipoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JLabel lblCostoTotal;
    private javax.swing.JLabel lblDineroIngresado;
    private javax.swing.JLabel lblTipoPago;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVuelto;
    private javax.swing.JPanel onlTitulo;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JTextField txtCostoTotal;
    private javax.swing.JTextField txtDineroIngresado;
    private javax.swing.JTextField txtVuelto;
    // End of variables declaration//GEN-END:variables
}
