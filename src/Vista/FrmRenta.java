/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Recursos_Tipografias.Fuentes;
import Controlador.ControladorFacturaRenta;
import Controlador.ControladorVariables;
import java.text.SimpleDateFormat;
import Controlador.Validaciones;
import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import javax.swing.JComponent;

/**
 *
 * @author Oliver
 */
public class FrmRenta extends javax.swing.JPanel {

    //Objeto del controlador con los datos del usuario
    ControladorVariables variables = new ControladorVariables();
    //Se crea el controlador de la factura
    ControladorFacturaRenta factura = new ControladorFacturaRenta();
    //Se trae la clase de validaciones
    Validaciones validaciones = new Validaciones();
    //Se crea el formato de la fecha
    SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
    //Se selecciona la fecha de ahora
    LocalDate Fecha = LocalDate.now();
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
    
    public FrmRenta() {
        initComponents();
        
        Colores();
        
        //La alertas se colocaban como invisible
        NMAX1.setVisible(false);
        NMAX2.setVisible(false);
        //Creamos un arreglo de tipo JComponentes que almacenara el componente
        JComponent[] Telefono={txtTelefono};
        JComponent[] DUI={txtDUI};
        //Se asigna la validación
        validaciones.noCopiar(Telefono);
        validaciones.noCopiar(DUI);
        validaciones.noPegar(Telefono);
        validaciones.noPegar(DUI);
        //Se coloca la fuente a los componentes
        Fuentes tipoFuente = new Fuentes();
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 1, 60));
        btnCancelar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnCrearFactura.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblDUI.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblDireccion.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblFechaFinalizacion.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblNombreCliente.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txaDireccion.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtDUI.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtNombreCliente.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtTelefono.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        NMAX1.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 15));
        NMAX2.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 15));
        lblTituloPanel.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 40));
        btnRentaPendiente.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTitulo = new javax.swing.JPanel();
        lblTituloPanel = new javax.swing.JLabel();
        pnlDatos = new javax.swing.JPanel();
        PanelCliente = new javax.swing.JPanel();
        lblIconoCliente = new javax.swing.JLabel();
        lblNombreCliente = new javax.swing.JLabel();
        PanelDUI = new javax.swing.JPanel();
        lblDUI = new javax.swing.JLabel();
        lblIconoDUI = new javax.swing.JLabel();
        NMAX1 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtDUI = new javax.swing.JTextField();
        PanelTelefono = new javax.swing.JPanel();
        lblIconoTelefono = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        NMAX2 = new javax.swing.JLabel();
        PanelDirección = new javax.swing.JPanel();
        lblIconoDireccion = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        slpDireccion = new javax.swing.JScrollPane();
        txaDireccion = new javax.swing.JTextArea();
        PanelFecha = new javax.swing.JPanel();
        lblIconoFinalizacion = new javax.swing.JLabel();
        lblFechaFinalizacion = new javax.swing.JLabel();
        dcrFecha = new com.toedter.calendar.JDateChooser();
        pnlCrear = new javax.swing.JPanel();
        btnCrearFactura = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblIconoFactura = new javax.swing.JLabel();
        jXButton1 = new org.jdesktop.swingx.JXButton();
        btnRentaPendiente = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1090, 680));

        pnlTitulo.setBackground(new java.awt.Color(14, 13, 21));
        pnlTitulo.setAlignmentX(0.0F);
        pnlTitulo.setAlignmentY(0.0F);
        pnlTitulo.setPreferredSize(new java.awt.Dimension(1098, 55));

        lblTituloPanel.setBackground(new java.awt.Color(14, 13, 21));
        lblTituloPanel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTituloPanel.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloPanel.setText("Renta");
        lblTituloPanel.setMinimumSize(new java.awt.Dimension(404, 55));
        lblTituloPanel.setPreferredSize(new java.awt.Dimension(404, 55));

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(lblTituloPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(295, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTituloLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTituloPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlDatos.setBackground(new java.awt.Color(255, 222, 212));
        pnlDatos.setPreferredSize(new java.awt.Dimension(500, 800));

        PanelCliente.setBackground(new java.awt.Color(255, 222, 212));
        PanelCliente.setPreferredSize(new java.awt.Dimension(100, 100));

        lblIconoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/cliente35px.png"))); // NOI18N

        lblNombreCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNombreCliente.setText("Nombre del cliente");

        javax.swing.GroupLayout PanelClienteLayout = new javax.swing.GroupLayout(PanelCliente);
        PanelCliente.setLayout(PanelClienteLayout);
        PanelClienteLayout.setHorizontalGroup(
            PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClienteLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lblIconoCliente)
                .addGap(5, 5, 5)
                .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelClienteLayout.setVerticalGroup(
            PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIconoCliente)
            .addGroup(PanelClienteLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblNombreCliente))
        );

        PanelDUI.setBackground(new java.awt.Color(255, 222, 212));
        PanelDUI.setPreferredSize(new java.awt.Dimension(100, 100));

        lblDUI.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDUI.setText("Documento unico de identidad (Sin guión)");

        lblIconoDUI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/DUI50px.png"))); // NOI18N

        NMAX1.setForeground(new java.awt.Color(204, 0, 0));
        NMAX1.setText("Número máximo alcanzado");

        javax.swing.GroupLayout PanelDUILayout = new javax.swing.GroupLayout(PanelDUI);
        PanelDUI.setLayout(PanelDUILayout);
        PanelDUILayout.setHorizontalGroup(
            PanelDUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDUILayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblIconoDUI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDUI, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addGroup(PanelDUILayout.createSequentialGroup()
                        .addComponent(NMAX1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)))
                .addContainerGap())
        );
        PanelDUILayout.setVerticalGroup(
            PanelDUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDUILayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDUI)
                .addGap(0, 0, 0)
                .addComponent(NMAX1))
            .addComponent(lblIconoDUI)
        );

        txtNombreCliente.setMinimumSize(new java.awt.Dimension(150, 30));
        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });

        txtDUI.setMinimumSize(new java.awt.Dimension(150, 30));
        txtDUI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDUIKeyTyped(evt);
            }
        });

        PanelTelefono.setBackground(new java.awt.Color(255, 222, 212));
        PanelTelefono.setPreferredSize(new java.awt.Dimension(100, 100));

        lblIconoTelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/telefono35px.png"))); // NOI18N

        lblTelefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTelefono.setText("Teléfono (Sin guión)");

        NMAX2.setForeground(new java.awt.Color(204, 0, 0));
        NMAX2.setText("Número máximo alcanzado");

        javax.swing.GroupLayout PanelTelefonoLayout = new javax.swing.GroupLayout(PanelTelefono);
        PanelTelefono.setLayout(PanelTelefonoLayout);
        PanelTelefonoLayout.setHorizontalGroup(
            PanelTelefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTelefonoLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lblIconoTelefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelTelefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NMAX2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        PanelTelefonoLayout.setVerticalGroup(
            PanelTelefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTelefonoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PanelTelefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelTelefonoLayout.createSequentialGroup()
                        .addComponent(lblTelefono)
                        .addGap(0, 0, 0)
                        .addComponent(NMAX2))
                    .addComponent(lblIconoTelefono))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        PanelDirección.setBackground(new java.awt.Color(255, 222, 212));
        PanelDirección.setPreferredSize(new java.awt.Dimension(100, 100));

        lblIconoDireccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/mapa35px.png"))); // NOI18N

        lblDireccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDireccion.setText("Dirección");

        javax.swing.GroupLayout PanelDirecciónLayout = new javax.swing.GroupLayout(PanelDirección);
        PanelDirección.setLayout(PanelDirecciónLayout);
        PanelDirecciónLayout.setHorizontalGroup(
            PanelDirecciónLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDirecciónLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIconoDireccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelDirecciónLayout.setVerticalGroup(
            PanelDirecciónLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDirecciónLayout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addComponent(lblIconoDireccion))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDirecciónLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDireccion)
                .addContainerGap())
        );

        txtTelefono.setMinimumSize(new java.awt.Dimension(150, 30));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txaDireccion.setColumns(20);
        txaDireccion.setLineWrap(true);
        txaDireccion.setRows(5);
        txaDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txaDireccionKeyTyped(evt);
            }
        });
        slpDireccion.setViewportView(txaDireccion);

        PanelFecha.setBackground(new java.awt.Color(255, 222, 212));
        PanelFecha.setPreferredSize(new java.awt.Dimension(100, 100));

        lblIconoFinalizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/fecha35px.png"))); // NOI18N

        lblFechaFinalizacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblFechaFinalizacion.setText("Fecha de finalización");

        javax.swing.GroupLayout PanelFechaLayout = new javax.swing.GroupLayout(PanelFecha);
        PanelFecha.setLayout(PanelFechaLayout);
        PanelFechaLayout.setHorizontalGroup(
            PanelFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFechaLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblIconoFinalizacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaFinalizacion)
                .addContainerGap())
        );
        PanelFechaLayout.setVerticalGroup(
            PanelFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFechaLayout.createSequentialGroup()
                .addGroup(PanelFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFechaFinalizacion)
                    .addComponent(lblIconoFinalizacion))
                .addGap(35, 35, 35))
        );

        dcrFecha.setMinimumSize(new java.awt.Dimension(150, 30));
        dcrFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcrFechaPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcrFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PanelFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                .addComponent(PanelTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slpDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PanelDirección, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                        .addComponent(PanelCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                        .addGap(55, 55, 55)
                        .addComponent(PanelDUI, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDUI, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(366, 366, 366))
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelDUI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDUI, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelDirección, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(PanelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcrFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(slpDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCrear.setBackground(new java.awt.Color(94, 45, 79));
        pnlCrear.setMinimumSize(new java.awt.Dimension(300, 300));
        pnlCrear.setPreferredSize(new java.awt.Dimension(200, 200));

        btnCrearFactura.setBackground(new java.awt.Color(164, 188, 188));
        btnCrearFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/ticket30px.png"))); // NOI18N
        btnCrearFactura.setText("Crear factura");
        btnCrearFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFacturaActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(164, 188, 188));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/cancelar30px.png"))); // NOI18N
        btnCancelar.setText("Cancelar");

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Factura");

        lblIconoFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconoFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Ticket75x75.png"))); // NOI18N

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

        btnRentaPendiente.setBackground(new java.awt.Color(164, 188, 188));
        btnRentaPendiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/cancelar30px.png"))); // NOI18N
        btnRentaPendiente.setText("Renta pendientes");
        btnRentaPendiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRentaPendienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCrearLayout = new javax.swing.GroupLayout(pnlCrear);
        pnlCrear.setLayout(pnlCrearLayout);
        pnlCrearLayout.setHorizontalGroup(
            pnlCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrearLayout.createSequentialGroup()
                .addGroup(pnlCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCrearLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(pnlCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCrearFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRentaPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlCrearLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jXButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblIconoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCrearLayout.setVerticalGroup(
            pnlCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCrearLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jXButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(lblTitulo)
                .addGap(36, 36, 36)
                .addComponent(lblIconoFactura)
                .addGap(54, 54, 54)
                .addComponent(btnCrearFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnRentaPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 773, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(pnlCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                    .addComponent(pnlCrear, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                pnlDatos.setBackground(Piel);
                pnlTitulo.setBackground(Oscuro);
                pnlCrear.setBackground(Morado);
                PanelCliente.setBackground(Piel);
                PanelDUI.setBackground(Piel);
                PanelTelefono.setBackground(Piel);
                PanelDirección.setBackground(Piel);   
                PanelFecha.setBackground(Piel);        
                break;
            case 2://Claro
                pnlDatos.setBackground(Color.white);
                pnlTitulo.setBackground(AzulOscuro);
                pnlCrear.setBackground(Celeste);
                PanelCliente.setBackground(Color.white);
                PanelDUI.setBackground(Color.white);
                PanelTelefono.setBackground(Color.white);
                PanelDirección.setBackground(Color.white);   
                PanelFecha.setBackground(Color.white);   
                break;
            default:
                pnlDatos.setBackground(Piel);
                pnlTitulo.setBackground(Oscuro);
                pnlCrear.setBackground(Morado);
                PanelCliente.setBackground(Piel);
                PanelDUI.setBackground(Piel);
                PanelTelefono.setBackground(Piel);
                PanelDirección.setBackground(Piel);   
                PanelFecha.setBackground(Piel);   
                break;
        }        
    }
    
    private void btnCrearFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearFacturaActionPerformed
        //Se crea un objeto de las notificaciones
        FrmNoti1 notificación = new FrmNoti1();
        FrmNotiError error = new FrmNotiError();
        //Se revisa si los componentes están vaciós
        if (txtNombreCliente.getText().isEmpty() || txtDUI.getText().isEmpty() || txtTelefono.getText().isEmpty() || txaDireccion.getText().isEmpty() || dcrFecha.getDate() == null) {
            //Se envía la notificación de que lso campos están vacíos
            notificación.TAMensajeError.setText("Existen campos sin \n datos asignados");
            notificación.lblTituloNoti1.setText("¡Campos vacíos!");
            notificación.setVisible(true);
        } else {
            try {
                //Se crean las variables que se enviarán al controlador
                String fecha = Formato.format(dcrFecha.getDate()); //Se obtiene la fecha de la vista
                String Fe = Formato.format(FechaActual);
                java.sql.Date fechaFinal = java.sql.Date.valueOf(fecha); //Se cambia el formato al formato SQL
                java.sql.Date FechaAc = java.sql.Date.valueOf(Fe); //Se cambia el formato al formato SQL
                String cliente = txtNombreCliente.getText(); //nombre del cliente
                String DUI = txtDUI.getText(); //DUI
                int telefono = Integer.parseInt(txtTelefono.getText()); //Telefono
                String direccion = txaDireccion.getText(); //Dirección
                //Estas variables se igualan a cero porque en este momento no se tiene el datos, pero luego se actualizarán
                double monto = 0;
                double adelanto = 0;
                double vuelto = 0;
                double mora = 0;
                int empleado = variables.getEmpleado(); //IDEmpleado
                int tipoPago = 1; //Se coloca como 1 para insertar, 1 = efectivo
                int empresa = factura.CargarID(empleado); //IDEmpresa

                //Se verifica el estado de la inserción
                if (!factura.InsertarFactura(FechaAc, fechaFinal, monto, cliente, DUI, telefono, direccion, adelanto, vuelto, mora, empleado, tipoPago, empresa)) {
                    //Se confirma la correcta inserción
                    FrmNoti2 notificacion = new FrmNoti2();
                    notificacion.setVisible(true);
                    notificacion.lblTituloNoti2.setText("¡Factura Creada!");
                    notificacion.TANoti2.setText("La factura se ha \n creado exitosamente");
                    //Se cambia a detalleFacturaRenta
                    FrmContenedor.CambiarDetalleRenta();
                } else {
                    //Si hay un error, entonces se envía un error
                    error.lblTituloError.setText("Error al crear");
                    error.TAnotiError.setText("No se ha podido crear \n la factura, campos incorrectos");
                    error.setVisible(true);
                }
            } catch (Exception e) {
                //Si hay un problema con los datos, entonces se envía un error
                error.lblTituloError.setText("Error al crear");
                error.TAnotiError.setText("No se pudo guardar la factura \n en la base de datos, \n problemas de conexión");
                error.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnCrearFacturaActionPerformed

    private void txtDUIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDUIKeyTyped
        //Se evalua el máximo de carácteres
        validaciones.Cantidad(evt, txtDUI.getText(), 9);
        validaciones.soloNumeros(evt, 4, txtDUI.getText());
        //Se verifica la cantidad para mostrar la alerta
        if (txtDUI.getText().length() >= 8) {
            NMAX1.setVisible(true);
        } else {
            NMAX1.setVisible(false);
        }
    }//GEN-LAST:event_txtDUIKeyTyped
    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        //Se confirma el máximo de caracteres ingresados
        validaciones.Cantidad(evt, txtTelefono.getText(), 8);
        validaciones.soloNumeros(evt, 1, txtTelefono.getText());
        //Se verifica la cantidad para mostrar la alerta
        if (txtTelefono.getText().length() >= 8) {
            NMAX2.setVisible(true);
        } else {
            NMAX2.setVisible(false);
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
        validaciones.soloLetras(evt, 2, txtNombreCliente.getText());
    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void txaDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txaDireccionKeyTyped
        validaciones.soloLetras(evt, 5, txaDireccion.getText());
    }//GEN-LAST:event_txaDireccionKeyTyped

    private void dcrFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcrFechaPropertyChange
        //Se crea un objeto de la notificación
        FrmNotiError NError = new FrmNotiError();
        try {
            //Se verifica si la fecha es nula
            if (dcrFecha.getDate() == null) {
            } else {
                //Se verifica si la fecha esta después de la fecha de hoy
                if (dcrFecha.getDate().after(FechaActual)) {
                } else {
                    //Si es anterior se le notifica que no puede ser menor a la fecha de hoy
                    dcrFecha.setDate(null);
                    NError.setVisible(true);
                    NError.TAnotiError.setText("La fecha ingresada debe ser mayor o igual a la del día de ho");
                    NError.lblTituloError.setText("Fecha erronea");
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_dcrFechaPropertyChange

    private void btnRentaPendienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRentaPendienteActionPerformed
        FrmRentaFinalizadaPagar pagar = new FrmRentaFinalizadaPagar();
        pagar.setVisible(true);
    }//GEN-LAST:event_btnRentaPendienteActionPerformed

    private void jXButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXButton1MouseClicked
        if(ValidarBuscador == 0){
            FrmBuscador buscador = new FrmBuscador();
            buscador.setVisible(true);
            ValidarBuscador = 1;
        }
    }//GEN-LAST:event_jXButton1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel NMAX1;
    public static javax.swing.JLabel NMAX2;
    public static javax.swing.JPanel PanelCliente;
    public static javax.swing.JPanel PanelDUI;
    public static javax.swing.JPanel PanelDirección;
    public static javax.swing.JPanel PanelFecha;
    public static javax.swing.JPanel PanelTelefono;
    public static javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnCrearFactura;
    public static javax.swing.JButton btnRentaPendiente;
    public static com.toedter.calendar.JDateChooser dcrFecha;
    public static org.jdesktop.swingx.JXButton jXButton1;
    public static javax.swing.JLabel lblDUI;
    public static javax.swing.JLabel lblDireccion;
    public static javax.swing.JLabel lblFechaFinalizacion;
    public static javax.swing.JLabel lblIconoCliente;
    public static javax.swing.JLabel lblIconoDUI;
    public static javax.swing.JLabel lblIconoDireccion;
    public static javax.swing.JLabel lblIconoFactura;
    public static javax.swing.JLabel lblIconoFinalizacion;
    public static javax.swing.JLabel lblIconoTelefono;
    public static javax.swing.JLabel lblNombreCliente;
    public static javax.swing.JLabel lblTelefono;
    public static javax.swing.JLabel lblTitulo;
    public static javax.swing.JLabel lblTituloPanel;
    public static javax.swing.JPanel pnlCrear;
    public static javax.swing.JPanel pnlDatos;
    public static javax.swing.JPanel pnlTitulo;
    public static javax.swing.JScrollPane slpDireccion;
    public static javax.swing.JTextArea txaDireccion;
    public static javax.swing.JTextField txtDUI;
    public static javax.swing.JTextField txtNombreCliente;
    public static javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

}