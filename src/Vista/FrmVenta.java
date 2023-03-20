/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorVariables;
import Controlador.ControladorVenta;
import Controlador.Validaciones;
import Recursos_Tipografias.Fuentes;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Oliver
 */
public class FrmVenta extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    //Creamos objeto del controlador de la clase validaciones
    Validaciones validaciones = new Validaciones();

    //Creamo arrayslist globales 
    ArrayList<Integer> CargarIdEmpresa = new ArrayList<Integer>();
    ArrayList<Integer> CargarIdE = new ArrayList<Integer>();
    ArrayList<Integer> CargarIdTipoP = new ArrayList<Integer>();
    ControladorVariables variables = new ControladorVariables();

    ControladorVenta obj = new ControladorVenta();

    //Se selecciona la fecha de ahora
    LocalDate Fecha = LocalDate.now();
    SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd"); //Se estable el formato de fehca
    ZoneId systemTimeZone = ZoneId.systemDefault();
    ZonedDateTime zonedDateTime = Fecha.atStartOfDay(systemTimeZone);
    Date FechaActual = Date.from(zonedDateTime.toInstant());
    
    //Validar si ya esta abierto el buscador
    public static int ValidarBuscador = 0;
    
    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Morado = new Color(94,45,79);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);
    
    public FrmVenta(){
        initComponents();
        
        Colores();
        
        Fuentes tipoFuente = new Fuentes();

        //Asignamos tipo de fuente a los componentes
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 40));
        lblNombre.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblTelefono.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblFecha.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblMonto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblTipoPago.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblVuelto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblFactura.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 1, 60));
        btnCrear.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnCancelar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbTipoPago.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        CargarTipoPago();
        System.out.println("empleado usuario" + variables.getEmpleado());
        //Asignamos lo que valdra el ArrayLits
        CargarIdEmpresa = obj.idEmpresa();
        CargarIdE = obj.idEmpleado();
        CargarIdTipoP = obj.idTipoPago();
        //Ponemos invisible el label de validacion
        NMAX2.setVisible(false);
        dtFecha.setDate(FechaActual);
    }

    //Cargar ComboBox empleado
    void CargarTipoPago() {
        //Creamos un defaultmodel llamado lista
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        //Le asignamos al Resultset el valor del metodo de resultset que se encuentra en controlador
        ResultSet res = Controlador.ControladorVenta.CargarTipoPagoControlador();
        try {
            while (res.next()) {
                //Agregamos el tipo de pago como elemento a la lista
                lista.addElement(res.getString("TipoPago"));
            }
        } catch (Exception e) {
            //Si ocurre un error mandamos a llamar al frm de errores
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de Conexión");
            error.TAnotiError.setText("Ocurrio un error al "
                    + "\n"
                    + "establecer conexión con"
                    + "\n"
                    + " la base de datos,"
                    + "\n"
                    + " verifique su acceso a "
                    + "\n"
                    + "internet o que los "
                    + "\n"
                    + "servicios del servidor "
                    + "\n"
                    + "estén activos");
            error.setVisible(true);
        }
        //Le asignamos el modelo al combobox
        cmbTipoPago.setModel(lista);
    }
    
    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                jPanel18.setBackground(Piel);
                jPanel3.setBackground(Oscuro);
                jPanel19.setBackground(Morado);
                break;
            case 2://Claro
                jPanel18.setBackground(Color.white);
                jPanel3.setBackground(AzulOscuro);
                jPanel19.setBackground(Celeste);
                break;
            default:
                jPanel18.setBackground(Piel);
                jPanel3.setBackground(Oscuro);
                jPanel19.setBackground(Morado);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        lblTipoPago = new javax.swing.JLabel();
        cmbTipoPago = new javax.swing.JComboBox<>();
        lblFecha = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        lblVuelto = new javax.swing.JLabel();
        txtVuelto = new javax.swing.JTextField();
        dtFecha = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        NMAX2 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        btnCrear = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblFactura = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jXButton1 = new org.jdesktop.swingx.JXButton();

        setMinimumSize(new java.awt.Dimension(1090, 680));
        setPreferredSize(new java.awt.Dimension(1090, 680));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 222, 212));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(122, 55));
        jPanel3.setLayout(new java.awt.BorderLayout());

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Venta");
        lblTitulo.setPreferredSize(new java.awt.Dimension(122, 55));
        lblTitulo.setRequestFocusEnabled(false);
        jPanel3.add(lblTitulo, java.awt.BorderLayout.CENTER);

        jPanel18.setBackground(new java.awt.Color(255, 222, 212));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombre.setBackground(new java.awt.Color(255, 255, 255));
        lblNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/cliente35px.png"))); // NOI18N
        lblNombre.setText("Nombre de cliente:");
        jPanel18.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 45, 228, -1));

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
        jPanel18.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 101, 275, 34));

        lblTelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/telefono35px.png"))); // NOI18N
        lblTelefono.setText("Teléfono de cliente:");
        jPanel18.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 261, -1));

        txtTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelKeyTyped(evt);
            }
        });
        jPanel18.add(txtTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 139, 37));

        lblTipoPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/tipopago35px.png"))); // NOI18N
        lblTipoPago.setText("Tipo de pago:");
        jPanel18.add(lblTipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, -1, -1));

        cmbTipoPago.setBackground(new java.awt.Color(164, 188, 188));
        cmbTipoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel18.add(cmbTipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 271, 37));

        lblFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/factura35px.png"))); // NOI18N
        lblFecha.setText("Fecha de factura:");
        jPanel18.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 234, -1));

        lblMonto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/totalpago35px.png"))); // NOI18N
        lblMonto.setText("Monto total de venta:");
        jPanel18.add(lblMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, -1, -1));

        txtMonto.setText("00.00");
        txtMonto.setEnabled(false);
        jPanel18.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, 140, 40));

        lblVuelto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/vuelto35px.png"))); // NOI18N
        lblVuelto.setText("Vuelto:");
        jPanel18.add(lblVuelto, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 430, -1, -1));

        txtVuelto.setText("00.00");
        txtVuelto.setEnabled(false);
        jPanel18.add(txtVuelto, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 130, 40));

        dtFecha.setEnabled(false);
        dtFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtFechaPropertyChange(evt);
            }
        });
        jPanel18.add(dtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 263, 37));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("$");
        jPanel18.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("$");
        jPanel18.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 440, -1, -1));

        NMAX2.setForeground(new java.awt.Color(204, 0, 0));
        NMAX2.setText("Número máximo alcanzado");
        jPanel18.add(NMAX2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, -1, -1));

        jPanel19.setBackground(new java.awt.Color(94, 45, 79));
        jPanel19.setPreferredSize(new java.awt.Dimension(325, 482));

        btnCrear.setBackground(new java.awt.Color(164, 188, 188));
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/ticket30px.png"))); // NOI18N
        btnCrear.setText("Crear factura");
        btnCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCrearMouseClicked(evt);
            }
        });
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(164, 188, 188));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/cancelar30px.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblFactura.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblFactura.setForeground(new java.awt.Color(255, 255, 255));
        lblFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFactura.setText("Factura");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/ticket75px.png"))); // NOI18N

        jXButton1.setBackground(new java.awt.Color(164, 188, 188));
        jXButton1.setBorder(null);
        jXButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Buscador50px.png"))); // NOI18N
        jXButton1.setText("Buscar Productos");
        jXButton1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jXButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jXButton1.setIconTextGap(25);
        jXButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(99, 99, 99))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(jXButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jXButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(lblFactura)
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addGap(55, 55, 55)
                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        //Cargamos notificacion de error
        FrmNotiError error = new FrmNotiError();
        //Se carga mensa de notificacion
        FrmNoti2 notificacion = new FrmNoti2();
        //Cargamos notificcion de camposvacios
        FrmNoti1 campos = new FrmNoti1();

        //Validamos que no eten campos sin llenar
        if (txtNombre.getText().isEmpty() || txtTel.getText().isEmpty() || dtFecha.getDate() == null) {
            campos.setVisible(true);
            campos.TAMensajeError.setText("Existen campos sin"
                    + "\n"
                    + " datos asignados");
            campos.lblTituloNoti1.setText("¡Campos vacíos!");
        } else {
            //Obtenemos el index de cada combobox
            int idTipoP = (Integer) cmbTipoPago.getSelectedIndex();

            //Para la fecha obtenemos con la clase date
            java.util.Date fecha = dtFecha.getDate();

            //enviando la información a la clase
            Controlador.ControladorVenta agregar = new Controlador.ControladorVenta();//Creamos objeto de controlador de empleado 

            //Cargamos los id de cada combobox
            //Integer empresa  = CargarIdEmpresa.get(idEmpresa);
            //Integer empleado = CargarIdE.get(idEmpleado);
            Integer tipoPago = CargarIdTipoP.get(idTipoP);
            int empleado = variables.getEmpleado();
            int empresa = obj.CargarEmpresa(empleado);
            System.out.println(empresa);

            //enviando la información a la clase
            SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaFac = Formato.format(fecha);//Asignamos el formato de fecha de año, mes, dia

            java.sql.Date fechaFactura = java.sql.Date.valueOf(fechaFac);
            agregar.setFechaFacturaVenta(fechaFactura);//Obtenemos la fecha de factura y se manda a controlador
            agregar.setMontoTotalV(Double.parseDouble(txtMonto.getText()));//Obtenemos el monto y se manda a controlador
            agregar.setNombreCliente(txtNombre.getText());//Obtenemos el nombre del cliente y se manda a controlador
            agregar.setTelefonoCliente(txtTel.getText());//Obtenemos el telefono de cliente y se manda a controlador
            agregar.setVuelto(Double.parseDouble(txtVuelto.getText()));//Obtenemis el vuelto y se manda a controlador
            agregar.setIdEmpleado(empleado);//Obtenemos el empleado y se mandamos a controlador
            agregar.setIdTipoPago(tipoPago);//Obtenemos el tipo de pago y se mandamos a controlador
            agregar.setIdEmpresa(empresa);//Obtenemos la empresa y se mandamos a controlador
            
            //enviando guardar a  SQLServer
            if (agregar.IngresarVentaController()) {//Si la condicion e cumple

                
                notificacion.lblTituloNoti2.setText("¡Factura Creada!");
                notificacion.TANoti2.setText("La factura se ha creado"
                        + "\n"
                        + " exitosamente");
                notificacion.setVisible(true);
                //Se cambia al siguiente panel
                FrmContenedor.CambiarDetalleVenta();
            } else {
                //Si ocurrre error se envia formulario de error
                error.setVisible(true);
                error.lblTituloError.setText("Error al crear");
                error.TAnotiError.setText("No se pudo guardar"
                        + "\n"
                        + " la factura en la base "
                        + "\n"
                        + "de datos, problemas de conexión");
            }
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed


    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed

    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearMouseClicked
        // FrmContenedor.CambiarDetalleVenta();
    }//GEN-LAST:event_btnCrearMouseClicked

    private void txtTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyTyped

        //Ejecutamos metodo de validacion de solo numeros
        validaciones.soloNumeros(evt, 1, txtTel.getText());

        //Validamos para que solo se puedan digitar 8 digitos
        if (txtTel.getText().length() > 7) {
            evt.consume();
            NMAX2.setVisible(true);
        } else {
            NMAX2.setVisible(false);
        }
    }//GEN-LAST:event_txtTelKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        //Ejecutamos metodo de solo letra con espacio
        validaciones.soloLetras(evt, 2, txtNombre.getText());
    }//GEN-LAST:event_txtNombreKeyTyped

    private void dtFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtFechaPropertyChange

    }//GEN-LAST:event_dtFechaPropertyChange

    private void jXButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXButton1MouseClicked
        if(ValidarBuscador == 0){
            FrmBuscador buscador = new FrmBuscador();
            buscador.setVisible(true);
            ValidarBuscador = 1;
        }
    }//GEN-LAST:event_jXButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NMAX2;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JComboBox<String> cmbTipoPago;
    private com.toedter.calendar.JDateChooser dtFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel3;
    private org.jdesktop.swingx.JXButton jXButton1;
    private javax.swing.JLabel lblFactura;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipoPago;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVuelto;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTel;
    private javax.swing.JTextField txtVuelto;
    // End of variables declaration//GEN-END:variables
}
