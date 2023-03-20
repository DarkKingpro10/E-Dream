package Vista;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Importamos las librerias a utilizar

import Controlador.Validaciones;
import Recursos_Tipografias.Fuentes;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import Controlador.ControladorEntregaRegistro;
import Controlador.ControladorVariables;
import java.awt.Color;
import java.awt.Image;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jesus Gerardo
 */
public class FrmEntregaRegistro extends javax.swing.JFrame {

    //Creamos objeto del controlador para acceder a sus metodos
    ControladorEntregaRegistro controller = new ControladorEntregaRegistro();
    //Creamos objeto de la clase fuentes para cambiar las fuentes a los componentes
    Fuentes tipoFuente = new Fuentes();
    //Creamos variables globales X y Y que ayudaran en metodo para mover el form
    int x, y;
    //Creamos objeto de la calse de validaciones para usar sus validaciones
    Validaciones validaciones = new Validaciones();
    //Creamos un JFormattedTextField que luego inicilizaremos dando el valor de un componente
    JFormattedTextField tf;
    //Creamos variable global
    int idRegistro;
    DefaultTableModel tbR = new DefaultTableModel();

    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);
    
    public FrmEntregaRegistro() {
        initComponents();
        Colores();
        //Cambiamos la fuente de los componentes
        lblTituloPrimerUso.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 20));
        lblProducto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblCantidad.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblPrecio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblDistribuidor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblDescripcion.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblFecha1.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblFecha2.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblFecha3.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAviso1.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAviso2.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        tbRegistro.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbProducto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        spnCantidad.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtPrecio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbProveedor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtDescripcion.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnAgregar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnModificar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnBuscar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        dcrFecha1.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        dcrFecha2.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        tbpPaneles.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtaIndicaciones.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblBuscarProducto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtBuscarProduct.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblMensaje.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnProducto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblMensaje.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        //Inicializamos el textbox asignandolo al Spiner de cantidad para que este sea un textbox
        tf = ((JSpinner.DefaultEditor) spnCantidad.getEditor()).getTextField();
        //Creamos un objeto de la clase Listener
        Listener Escuchar = new Listener();
        //Asiganamos un KeyListener al textbox
        tf.addKeyListener(Escuchar);
        //Cargamos los productos
        cargarProductos();
        //Cragamos los proveedores
        cargarProveedores();
        //Proceso para validar que no se pegue
        //Creamos arreglo de tipo JComponent, donde le pasaremos los components de java donde no se podra pegar
        JComponent[] pegar = {tf, txtPrecio};
        //Invocamos el objeto del controlador y el metodo pasando como parametro el arreglo
        validaciones.noPegar(pegar);
        //Desactivamos la opcion de editar en los jDateChooser
        dcrFecha1.getDateEditor().setEnabled(false);
        dcrFecha2.getDateEditor().setEnabled(false);
        dcrFechaRegistro.getDateEditor().setEnabled(false);
        //Colocamos un icono al form
        Image icon = new ImageIcon(getClass().getResource("/Recursos_img/E-Dream logo.png")).getImage();
        setIconImage(icon);
        //Ponemos el mensaje en oculto
        lblMensaje.setVisible(false);
        lblMensaje2.setVisible(false);
        //Cargamos los registros en la tabla
        cargarTabla();
    }
    
    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                jPanel2.setBackground(Piel);
                jPanel1.setBackground(Oscuro);
                break;
            case 2://Claro
                jPanel2.setBackground(Color.white);
                jPanel1.setBackground(Celeste);
                break;
            default:
                jPanel2.setBackground(Piel);
                jPanel1.setBackground(Oscuro);
                break;
        }        
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTituloPrimerUso = new javax.swing.JLabel();
        lblCerrar = new javax.swing.JLabel();
        btnMin = new javax.swing.JButton();
        tbpPaneles = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        lblProducto = new javax.swing.JLabel();
        cmbProducto = new javax.swing.JComboBox<>();
        lblCantidad = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblDistribuidor = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox<>();
        lblDescripcion = new javax.swing.JLabel();
        spAgregarDescripción = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        lblFecha1 = new javax.swing.JLabel();
        dcrFecha1 = new com.toedter.calendar.JDateChooser();
        lblFecha2 = new javax.swing.JLabel();
        dcrFecha2 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRegistro = new javax.swing.JTable();
        lblAviso2 = new javax.swing.JLabel();
        lblAviso1 = new javax.swing.JLabel();
        lblFecha3 = new javax.swing.JLabel();
        dcrFechaRegistro = new com.toedter.calendar.JDateChooser();
        lblEmpleado = new javax.swing.JLabel();
        txtEmpleado = new javax.swing.JTextField();
        lblhoradesblock = new javax.swing.JLabel();
        fmtHoraIngreso = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        txtaIndicaciones = new javax.swing.JTextArea();
        lblImagen = new javax.swing.JLabel();
        lblBuscarProducto = new javax.swing.JLabel();
        txtBuscarProduct = new javax.swing.JTextField();
        btnProducto = new javax.swing.JButton();
        lblMensaje = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        txtaIndicaciones2 = new javax.swing.JTextArea();
        lblMensaje2 = new javax.swing.JTextArea();
        lblBuscarDistribuidor = new javax.swing.JLabel();
        txtBuscarDistribuidor = new javax.swing.JTextField();
        btnDistribuidor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(14, 13, 21));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        lblTituloPrimerUso.setForeground(new java.awt.Color(240, 240, 240));
        lblTituloPrimerUso.setText("Registro de Entregas");

        lblCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Close.png"))); // NOI18N
        lblCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });

        btnMin.setBackground(new java.awt.Color(46, 14, 54));
        btnMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Min.png"))); // NOI18N
        btnMin.setBorder(null);
        btnMin.setBorderPainted(false);
        btnMin.setContentAreaFilled(false);
        btnMin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMin.setMaximumSize(new java.awt.Dimension(23, 22));
        btnMin.setMinimumSize(new java.awt.Dimension(23, 22));
        btnMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(297, 297, 297)
                .addComponent(lblTituloPrimerUso, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177)
                .addComponent(btnMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTituloPrimerUso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCerrar)
                        .addGap(8, 8, 8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        tbpPaneles.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 222, 216));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProducto.setBackground(new java.awt.Color(0, 0, 0));
        lblProducto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/producto40px.png"))); // NOI18N
        lblProducto.setText("Nombre producto:");
        jPanel2.add(lblProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        cmbProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbProducto.setMinimumSize(new java.awt.Dimension(29, 20));
        cmbProducto.setPreferredSize(new java.awt.Dimension(29, 20));
        jPanel2.add(cmbProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 65, 188, 32));

        lblCantidad.setBackground(new java.awt.Color(0, 0, 0));
        lblCantidad.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/CantidadProducto35px.png"))); // NOI18N
        lblCantidad.setText("Cantidad");
        jPanel2.add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 112, 147, -1));

        spnCantidad.setEditor(new javax.swing.JSpinner.NumberEditor(spnCantidad, ""));
        jPanel2.add(spnCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 153, 188, 35));

        lblPrecio.setBackground(new java.awt.Color(0, 0, 0));
        lblPrecio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Precio35px.png"))); // NOI18N
        lblPrecio.setText("Precio $");
        jPanel2.add(lblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 206, 740, -1));

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });
        jPanel2.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 252, 186, 36));

        lblDistribuidor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDistribuidor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Distribuidor50px.png"))); // NOI18N
        lblDistribuidor.setText("Distribuidor");
        jPanel2.add(lblDistribuidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 299, -1, -1));

        cmbProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cmbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 357, 191, 35));

        lblDescripcion.setBackground(new java.awt.Color(0, 0, 0));
        lblDescripcion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDescripcion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDescripcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Descripcion35px.png"))); // NOI18N
        lblDescripcion.setText("Descripción");
        jPanel2.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 398, 331, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        spAgregarDescripción.setViewportView(txtDescripcion);

        jPanel2.add(spAgregarDescripción, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 439, 224, 90));

        btnAgregar.setBackground(new java.awt.Color(164, 188, 188));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/AgregarProducto30px.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 12, 140, -1));

        btnModificar.setBackground(new java.awt.Color(164, 188, 188));
        btnModificar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/ModificarProducto30px.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 12, 147, -1));

        btnBuscar.setBackground(new java.awt.Color(164, 188, 188));
        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Buscador50px.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(581, 12, 147, -1));

        lblFecha1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFecha1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFecha1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/FEntrega35px.png"))); // NOI18N
        lblFecha1.setText("Fecha inicio");
        jPanel2.add(lblFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 147, -1));

        dcrFecha1.setBackground(new java.awt.Color(164, 188, 188));
        dcrFecha1.setDateFormatString("yyyy/MM/dd");
        dcrFecha1.setPreferredSize(new java.awt.Dimension(95, 35));
        dcrFecha1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dcrFecha1KeyReleased(evt);
            }
        });
        jPanel2.add(dcrFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 147, -1));

        lblFecha2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFecha2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFecha2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/FVenta35px.png"))); // NOI18N
        lblFecha2.setText("Fecha fin");
        jPanel2.add(lblFecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 150, -1));

        dcrFecha2.setBackground(new java.awt.Color(164, 188, 188));
        dcrFecha2.setDateFormatString("yyyy/MM/dd");
        dcrFecha2.setPreferredSize(new java.awt.Dimension(95, 35));
        dcrFecha2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dcrFecha2KeyReleased(evt);
            }
        });
        jPanel2.add(dcrFecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 147, -1));

        tbRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRegistroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbRegistro);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 160, 510, 270));

        lblAviso2.setForeground(new java.awt.Color(255, 0, 0));
        lblAviso2.setText("Verifique que la fecha de inicio sea menor que la fecha final\n");
        jPanel2.add(lblAviso2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 490, 450, -1));

        lblAviso1.setForeground(new java.awt.Color(255, 0, 0));
        lblAviso1.setText("*Solo tendra 30 minutos para modificar un registro");
        jPanel2.add(lblAviso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 450, -1));

        lblFecha3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFecha3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFecha3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/FEntrega35px.png"))); // NOI18N
        lblFecha3.setText("Fecha registro");
        jPanel2.add(lblFecha3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 170, -1));

        dcrFechaRegistro.setBackground(new java.awt.Color(164, 188, 188));
        dcrFechaRegistro.setDateFormatString("yyyy/MM/dd");
        dcrFechaRegistro.setPreferredSize(new java.awt.Dimension(95, 35));
        dcrFechaRegistro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dcrFechaRegistroKeyPressed(evt);
            }
        });
        jPanel2.add(dcrFechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 147, -1));

        lblEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        lblEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Empleado35px.png"))); // NOI18N
        lblEmpleado.setText("Empleado:");
        jPanel2.add(lblEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 220, -1));

        txtEmpleado.setEditable(false);
        txtEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmpleadoKeyTyped(evt);
            }
        });
        jPanel2.add(txtEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 210, 34));

        lblhoradesblock.setBackground(new java.awt.Color(255, 255, 255));
        lblhoradesblock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/hora23px.png"))); // NOI18N
        lblhoradesblock.setText("Hora Ingreso:");
        jPanel2.add(lblhoradesblock, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 540, -1, -1));

        fmtHoraIngreso.setEditable(false);
        fmtHoraIngreso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("hh:mm:ss "))));
        jPanel2.add(fmtHoraIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 590, 160, 30));

        tbpPaneles.addTab("Registros", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 222, 216));

        txtaIndicaciones.setEditable(false);
        txtaIndicaciones.setBackground(new java.awt.Color(255, 222, 216));
        txtaIndicaciones.setColumns(20);
        txtaIndicaciones.setLineWrap(true);
        txtaIndicaciones.setRows(5);
        txtaIndicaciones.setText("Ingrese el codigo del producto o su nombre, si se encuentra se seleccionara y podra continuar con el registro\n");
        txtaIndicaciones.setBorder(null);
        txtaIndicaciones.setOpaque(false);

        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/NoImage200px.png"))); // NOI18N
        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        lblBuscarProducto.setBackground(new java.awt.Color(0, 0, 0));
        lblBuscarProducto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/producto40px.png"))); // NOI18N
        lblBuscarProducto.setText("Nombre producto:");

        txtBuscarProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarProductKeyTyped(evt);
            }
        });

        btnProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Buscador50px.png"))); // NOI18N
        btnProducto.setText("Seleccionar Producto");
        btnProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductoActionPerformed(evt);
            }
        });

        lblMensaje.setEditable(false);
        lblMensaje.setBackground(new java.awt.Color(255, 222, 216));
        lblMensaje.setColumns(20);
        lblMensaje.setForeground(new java.awt.Color(0, 0, 153));
        lblMensaje.setLineWrap(true);
        lblMensaje.setRows(5);
        lblMensaje.setText("No se encontro el producto\n\n");
        lblMensaje.setBorder(null);
        lblMensaje.setOpaque(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtaIndicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(278, 278, 278))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblBuscarProducto))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtBuscarProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(btnProducto)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblMensaje)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(txtaIndicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblBuscarProducto)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProducto))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        tbpPaneles.addTab("Seleccionar Producto", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 222, 216));

        txtaIndicaciones2.setEditable(false);
        txtaIndicaciones2.setBackground(new java.awt.Color(255, 222, 216));
        txtaIndicaciones2.setColumns(20);
        txtaIndicaciones2.setLineWrap(true);
        txtaIndicaciones2.setRows(5);
        txtaIndicaciones2.setText("Ingrese el nombre del proveedor y si se encuentra se seleccionara y podra continuar con el registro");
        txtaIndicaciones2.setBorder(null);
        txtaIndicaciones2.setOpaque(false);

        lblMensaje2.setEditable(false);
        lblMensaje2.setBackground(new java.awt.Color(255, 222, 216));
        lblMensaje2.setColumns(20);
        lblMensaje2.setForeground(new java.awt.Color(0, 0, 153));
        lblMensaje2.setLineWrap(true);
        lblMensaje2.setRows(5);
        lblMensaje2.setText("No se encontro el producto\n\n");
        lblMensaje2.setBorder(null);
        lblMensaje2.setOpaque(false);

        lblBuscarDistribuidor.setBackground(new java.awt.Color(0, 0, 0));
        lblBuscarDistribuidor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBuscarDistribuidor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/producto40px.png"))); // NOI18N
        lblBuscarDistribuidor.setText("Nombre distribuidor:");

        txtBuscarDistribuidor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarDistribuidorKeyTyped(evt);
            }
        });

        btnDistribuidor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Buscador50px.png"))); // NOI18N
        btnDistribuidor.setText("Seleccionar Distribuidor");
        btnDistribuidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDistribuidorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(245, Short.MAX_VALUE)
                .addComponent(txtaIndicaciones2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(65, 65, 65)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(lblBuscarDistribuidor))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(txtBuscarDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(109, 109, 109)
                                    .addComponent(btnDistribuidor)))
                            .addGap(48, 48, 48))
                        .addComponent(lblMensaje2))
                    .addGap(18, 18, 18)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(txtaIndicaciones2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(512, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(210, 210, 210)
                    .addComponent(lblMensaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(4, 4, 4)
                    .addComponent(lblBuscarDistribuidor)
                    .addGap(10, 10, 10)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscarDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDistribuidor))
                    .addContainerGap(311, Short.MAX_VALUE)))
        );

        tbpPaneles.addTab("Seleccionar Distribuidor", jPanel4);

        getContentPane().add(tbpPaneles, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 760, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Creamos metodo que cargue los productos en el combobox
    void cargarProductos() {
        //Asignamos el modelo al combobox
        cmbProducto.setModel(controller.obtenerProductos());
    }

    //Creamos metodo que cargue los distribuidores en el combobox
    void cargarProveedores() {
        //Asiganmos el modelo del distribuidor
        cmbProveedor.setModel(controller.obtenerProveedores());
    }

    //Creamos metodo que cargue la imagen del producto
    void cargarBusqueda() {
        //Creamos una variable de tipo ImageIcon y le asignamos el valor de la imagen
        ImageIcon img = controller.cargarImagen();
        lblImagen.setIcon(img);
        //Colocamos el producto en el combobox
        cmbProducto.setSelectedIndex(controller.getPosicion());
        //enviamos mensaje de confirmacion
        lblMensaje.setText("Se ha encontrado el producto exitosamente, ya se ha seleccionado, puede continuar con el registro");
        lblMensaje.setVisible(true);
    }

    void cargarBusquedaDistri() {
        //Colocamos el distribuidor en el combobox guiandome de su posicion por lo que la obtenemos
        cmbProveedor.setSelectedIndex(controller.getPosicionDistri());
        //enviamos mensaje de confirmacion
        lblMensaje2.setText("Se ha encontrado el proveedor exitosamente, ya se ha seleccionado, puede continuar con el registro");
        lblMensaje2.setVisible(true);
    }

    //Creamos metodo que cargue el modelo de la tabla en la tabla obtenida del controlodar
    void cargarTabla() {
        tbRegistro.setModel(controller.obtenerRegistros());
    }

    //Creamos metodo que cargue los datos cargados al buscar
    public void BuscadorFechas() {
        //Creamos columnas   
        String[] titulo = {"N-Entrega", "Fecha entrega", "Hora ingreso", "Cantidad", "Precio", "Nombre producto", "Empleado", "Distribuidor"};
        //Le asignamos al resulset el metodo de buscar distribuidor en controlador
        ResultSet rs = Controlador.ControladorEntregaRegistro.BuscadorFechas();
        tbR = new DefaultTableModel(null, titulo);
        try {

            while (rs.next()) {
                //Agregamos las filas de datos
                tbR.addRow(new Object[]{rs.getString("idEntregaRegistro"), rs.getDate("fechaEntrega"), rs.getTime("horaIngreso"), rs.getInt("cantidad"), rs.getDouble("precio"), rs.getString("nombreProducto"), rs.getString("empleado"), rs.getString("nombreDistribuidor")});
                System.out.println(rs.getString("idEntregaRegistro"));
            }
            //Le asignamos el modelo a la tabla
            tbRegistro.setModel(tbR);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Creamos metodo que retulizaremos para verificar campos vacios
    boolean camposVacios() {
        if (tf.getText().equals("0") || txtPrecio.getText().isEmpty()) {
            FrmNoti1 error = new FrmNoti1();
            error.lblTituloNoti1.setText("¡Algo no esta bien!");
            error.TAMensajeError.setText("!Campos vacios o invalidos!Revisa que todos los campos esten lleno o no esten igual a 0");
            error.setVisible(true);
            return false;
        } else {
            return true;
        }
    }

    //Creamos metodo que limpie los datos
    void LimpiarCampos() {
        tf.setText("0");
        txtPrecio.setText("");
        txtDescripcion.setText("");
        txtEmpleado.setText("");
        dcrFechaRegistro.setDate(null);
    }

    //Creamos metodo que agregue registros pasando los parametros necesarios al controlador
    void agregarRegistros() {
        //Obtenemos los datos que luego seran pasados como parametros al metodo de agregado del controlador
        String producto = String.valueOf(cmbProducto.getSelectedItem());
        System.out.println(producto);
        String codigoProducto = controller.obtenerProducto(cmbProducto.getSelectedIndex());//Obtenemos el codigo de producto pasando el index del combobox
        int idProveedor = controller.obtenerProveedor(cmbProveedor.getSelectedIndex());//Obtenemos el id del proveedor pasando el index del combobox
        int cantidad = Integer.parseInt(tf.getText());//Obtenemos la cantidad seleccionada
        //Creamos variable double y le asignamos el valor del componente
        double number = Double.parseDouble(txtPrecio.getText());
        //Utilizamos la clase de Math.round para redondear el double
        double precio = (double) Math.round(number * 100d) / 100d;
        String descripcion = txtDescripcion.getText();//Obtenemos la descripcion
        cargarProductos();
        cmbProducto.setSelectedItem(producto);
        int cantidadi = controller.obtenerCantidad(cmbProducto.getSelectedIndex());//Obtenemos la cantidad incial del metodo en el controlador pasando como variable el index del combobox seleccionado
        //Una vez obtenido los datos, se envian como parametros al metodo de agregado de registros
        //en el controlador
        LimpiarCampos();//Limpiamos los campos
        controller.agregarRegistros(cantidad, precio, descripcion, codigoProducto, idProveedor, cantidadi);
        idRegistro = 0;//Reseteamos su valor para evitar errores
    }
    //Creamos metodo que actualice el registro

    void actualizarRegistro() {
        //Obtenemos los datos que luego seran pasados como parametros al metodo de agregado del controlador
        String codigoProducto = controller.obtenerProducto(cmbProducto.getSelectedIndex());//Obtenemos el codigo de producto pasando el index del combobox
        int idProveedor = controller.obtenerProveedor(cmbProveedor.getSelectedIndex());//Obtenemos el id del proveedor pasando el index del combobox
        int cantidad = Integer.parseInt(tf.getText());//Obtenemos la cantidad seleccionada
        //Creamos variable double y le asignamos el valor del componente
        double number = Double.parseDouble(txtPrecio.getText());
        //Utilizamos la clase de Math.round para redondear el double
        double precio = (double) Math.round(number * 100d) / 100d;
        String descripcion = txtDescripcion.getText();//Obtenemos la descripcion
        int cantidadi = controller.obtenerCantidadI(idRegistro);//Obtenemos la cantidad incial del metodo en el controlador pasando como variable el index del combobox seleccionado
        //Una vez obtenido los datos, se envian como parametros al metodo de agregado de registros
        //en el controlador
        LimpiarCampos();//Limpiamos los campos
        controller.actualizarRegistro(cantidad, precio, descripcion, codigoProducto, idProveedor, cantidadi, idRegistro);
        idRegistro = 0;//Reseteamos su valor para evitar errores
    }
    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        dispose();
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        //Inicializamos las variables x y y con la posicion actual del form
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
        //Creamos metodo que actualice la posicion del form dependendiendo donde el usuario lo coloque
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void txtBuscarProductKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductKeyTyped

    }//GEN-LAST:event_txtBuscarProductKeyTyped

    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
        // TODO add your handling code here:
        cargarProductos();
        //Verificamos que el producto exista
        if (!txtBuscarProduct.getText().isEmpty() && controller.confirmarProducto(txtBuscarProduct.getText()) == true) {
            lblMensaje.setVisible(false);
            cargarBusqueda();
        } else {
            lblMensaje.setVisible(true);
            ImageIcon img = new ImageIcon("src/Recursos_img/NoImage200px.png");
            lblImagen.setIcon(img);
        }
    }//GEN-LAST:event_btnProductoActionPerformed

    private void txtBuscarDistribuidorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDistribuidorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDistribuidorKeyTyped

    private void btnDistribuidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDistribuidorActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        cargarProveedores();
        //Verificamos que el producto exista
        if (!txtBuscarDistribuidor.getText().isEmpty() && controller.confirmarDistribuidor(txtBuscarDistribuidor.getText()) == true) {
            lblMensaje2.setVisible(false);
            cargarBusquedaDistri();
        } else {
            lblMensaje2.setVisible(true);
        }
    }//GEN-LAST:event_btnDistribuidorActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        //Validamos que no se ingresen datos nulos con el metodo
        if (camposVacios() == true) {
            agregarRegistros();
            cargarTabla();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        // TODO add your handling code here:
        //Validamos que solo se ingresen numeros y puntos invocando el metodo de SoloNumeros
        //del controlador pasando como opcion numero 5
        validaciones.soloNumeros(evt, 5, txtPrecio.getText());
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void btnMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinActionPerformed
        this.setExtendedState(1);
    }//GEN-LAST:event_btnMinActionPerformed

    private void tbRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRegistroMouseClicked
        // TODO add your handling code here:
        //Realizamos el metodo de seleccionado para visualizar los datos
        //Inicializamos variable del id
        idRegistro = Integer.valueOf(tbRegistro.getValueAt(tbRegistro.getSelectedRow(), 0).toString());
        //Colocamos la fecha en el componente FechaRegistro
        //Creamos un objeto de la clase SimpleDateFormat para formatear el campo
        SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");//Asignamos el formato de fecha
        //realizamos la accion para evitar cualquier posible fallo
        try {
            //Colocamos la fecha en el componente al formatear el string que se obtiene de la posicion
            dcrFechaRegistro.setDate(Formato.parse(tbRegistro.getValueAt(tbRegistro.getSelectedRow(), 1).toString()));
        } catch (Exception e) {
            //Si hay error no se hace nada
        }
        //Colocamos la hora de ingreso
        fmtHoraIngreso.setText(tbRegistro.getValueAt(tbRegistro.getSelectedRow(), 2).toString());
        //Asignamos la cantidad
        tf.setText(tbRegistro.getValueAt(tbRegistro.getSelectedRow(), 3).toString());
        //Asignamos el precio
        txtPrecio.setText(tbRegistro.getValueAt(tbRegistro.getSelectedRow(), 4).toString());
        //Asignamos el producto
        cmbProducto.setSelectedItem(tbRegistro.getValueAt(tbRegistro.getSelectedRow(), 5).toString());
        //Asignamos el proveedor
        cmbProveedor.setSelectedItem(tbRegistro.getValueAt(tbRegistro.getSelectedRow(), 7).toString());
        txtEmpleado.setText(tbRegistro.getValueAt(tbRegistro.getSelectedRow(), 6).toString());
        //Para seleccionar el metodo de distribuidor deberemos ejecutar un metodo que lo traiga
        txtDescripcion.setText(controller.obtenerDescripcion(idRegistro));
    }//GEN-LAST:event_tbRegistroMouseClicked

    private void txtEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpleadoKeyTyped
        //Ejecutamos validdacionm  solo se podran letras, y espacio
        validaciones.soloLetras(evt, 2, txtEmpleado.getText());
    }//GEN-LAST:event_txtEmpleadoKeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        //Primero evaluaremos la hora de ingreso y la comprobaremos con la hora actual, si esta es mas de 30 minutos no dejara actualizar
        java.util.Date fecha = dcrFechaRegistro.getDate();
        SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaU = Formato.format(fecha);//Asignamos el formato de fecha de año, mes, dia
        java.sql.Date fechaI = java.sql.Date.valueOf(fechaU);

        if (idRegistro == 0 || camposVacios() == false) {
            //Como es cero, no haremos nada
        } else {
            //Primero verificaremos que se intenta actualizar el mismo dia
            //Ya que no es cero significa que se selecciono un registro
            String horai = controller.obtenerhoraI(idRegistro);
            if (controller.diferencia(horai) == false && controller.diferenciaDia(fechaI) == true) {
                //Ejecutamos el metodo de actualizado
                actualizarRegistro();
                cargarTabla();
            } else {
                //Mostramos un mensaje informando que no se puede actualizar ya que paso el tiempo de actualizacion
                FrmNoti1 error = new FrmNoti1();
                error.lblTituloNoti1.setText("¡Ooopss!");
                error.TAMensajeError.setText("!No se puede!No se puede actualizar ya que paso el tiempo disponible para actualizar");
                error.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void dcrFechaRegistroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcrFechaRegistroKeyPressed

    }//GEN-LAST:event_dcrFechaRegistroKeyPressed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        try {
            java.util.Date fechaInicio = dcrFecha1.getDate();
            java.util.Date fechaFinal = dcrFecha2.getDate();
            SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaIB = Formato.format(fechaInicio);//Asignamos el formato de fecha de año, mes, dia
            String fechaFB = Formato.format(fechaFinal);//Asignamos el formato de fecha de año, mes, dia

            java.sql.Date fechaI = java.sql.Date.valueOf(fechaIB);
            java.sql.Date fechaFi = java.sql.Date.valueOf(fechaFB);
            System.out.println(fechaI);
            controller.setFechaI(fechaI);

            System.out.println(fechaFi);
            controller.setFechaFi(fechaFi);

        } catch (Exception e) {
            System.out.println(e);
        }
        BuscadorFechas();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void dcrFecha1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcrFecha1KeyReleased

    }//GEN-LAST:event_dcrFecha1KeyReleased

    private void dcrFecha2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcrFecha2KeyReleased
        //Creamos vriable que almacene lo digitado

    }//GEN-LAST:event_dcrFecha2KeyReleased

    //Creamos una clase que sera util para capturar las teclas del textfield que se creo y se le añadio el spinner de cantidad
    public class Listener implements KeyListener {

        public void keyTyped(KeyEvent e) {
            //Dentro de este metodo asignamos la validacion de solo numeros
            validaciones.soloNumeros(e, 1, tf.getText());
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

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
            java.util.logging.Logger.getLogger(FrmEntregaRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEntregaRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEntregaRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEntregaRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEntregaRegistro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDistribuidor;
    private javax.swing.JButton btnMin;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnProducto;
    private javax.swing.JComboBox<String> cmbProducto;
    private javax.swing.JComboBox<String> cmbProveedor;
    private com.toedter.calendar.JDateChooser dcrFecha1;
    private com.toedter.calendar.JDateChooser dcrFecha2;
    private com.toedter.calendar.JDateChooser dcrFechaRegistro;
    private javax.swing.JFormattedTextField fmtHoraIngreso;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAviso1;
    private javax.swing.JLabel lblAviso2;
    private javax.swing.JLabel lblBuscarDistribuidor;
    private javax.swing.JLabel lblBuscarProducto;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDistribuidor;
    private javax.swing.JLabel lblEmpleado;
    private javax.swing.JLabel lblFecha1;
    private javax.swing.JLabel lblFecha2;
    private javax.swing.JLabel lblFecha3;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JTextArea lblMensaje;
    private javax.swing.JTextArea lblMensaje2;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblTituloPrimerUso;
    private javax.swing.JLabel lblhoradesblock;
    private javax.swing.JScrollPane spAgregarDescripción;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tbRegistro;
    private javax.swing.JTabbedPane tbpPaneles;
    private javax.swing.JTextField txtBuscarDistribuidor;
    private javax.swing.JTextField txtBuscarProduct;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtEmpleado;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextArea txtaIndicaciones;
    private javax.swing.JTextArea txtaIndicaciones2;
    // End of variables declaration//GEN-END:variables
}
