/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorDetalleRenta;
import Controlador.ControladorVariables;
import Controlador.Validaciones;
import Recursos_Tipografias.Fuentes;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oliver
 */
public class FrmDetalleFacturaRenta extends javax.swing.JPanel {

    //Objeto de la notificaciOn de vacio
    //Objeto de las clases necesarias
    ControladorDetalleRenta Controlador = new ControladorDetalleRenta(); //Controlador
    Validaciones validaciones = new Validaciones(); //Validaciones
    //Listas que guardan los datos de las tablas y los codigos del producto
    ArrayList<String> CodigoProducto = new ArrayList<String>();
    ArrayList<Object[]> DatosTabla = new ArrayList<Object[]>();
    ArrayList<Object[]> DatosProducto = new ArrayList<Object[]>();
    //Modelo de la tabla
    DefaultTableModel tabla = new DefaultTableModel();
    //Se crea un modelo para el combobox
    DefaultComboBoxModel NombreProducto = new DefaultComboBoxModel();
    //Variables globales
    public static double precio = 0;
    public static double descuento = 0;
    public static double adelanto = 0;
    //Se agrega el formato de la localidad
    DecimalFormatSymbols localidad = new DecimalFormatSymbols(Locale.US);
    //Formato para los precios (Números decimales)
    DecimalFormat Formato = new DecimalFormat("###,###.##", localidad);

    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Morado = new Color(94,45,79);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);
    
    public FrmDetalleFacturaRenta() {
        initComponents();
        Colores();
        //Asignación de la fuente a los componentes
        Fuentes tipoFuente;
        tipoFuente = new Fuentes();
        //Se crea el los componentes
        JComponent[] codigo={txtCodigo};
        //Se asigna la validación
        validaciones.noCopiar(codigo);
        llenadoInicial();
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 1, 25));
        btnCancelar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnAgregar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnFinalizar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblCantidad.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblNombreProducto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblCodigoProducto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblPrecio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblDescuento.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblCategoria.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtCodigo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtCantidad.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtDescuento.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtPrecio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbCategoria.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbNombre.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblTituloRenta.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 1, 40));
        lblTotalRenta.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblTotalDescuento.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblAdelanto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtTotalRenta.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtTotalDescuento.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtAdelanto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        TableDetalle.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        //Se cargan los combobox con los datos
        
        cmbCategoria.setModel(Controlador.CargarCategoria());
        //Se cargan todos los productos en la lista
        //CodigoProducto = Controlador.CargaIDProductos();
        //Se crea el modelo de la tabla y sus columnas
        TableDetalle.setModel(tabla);
        tabla.addColumn("Codigo");
        tabla.addColumn("Nombre");
        tabla.addColumn("Cantidad");
        tabla.addColumn("Descuento");
        tabla.addColumn("Precio");
        tabla.addColumn("Total");
    }

    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                jPanel1.setBackground(Piel);
                jPanel4.setBackground(Piel);
                jPanel3.setBackground(Piel);
                jPanel2.setBackground(Oscuro);
                jPanel6.setBackground(Morado);
                break;
            case 2://Claro
                jPanel1.setBackground(Color.white);
                jPanel4.setBackground(Color.white);
                jPanel3.setBackground(Color.white);
                jPanel2.setBackground(AzulOscuro);
                jPanel6.setBackground(Celeste);
                break;
            default:
                jPanel1.setBackground(Piel);
                jPanel4.setBackground(Piel);
                jPanel3.setBackground(Piel);
                jPanel2.setBackground(Oscuro);
                jPanel6.setBackground(Morado);
                break;
        }        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTituloRenta = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableDetalle = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        lblNombreProducto = new javax.swing.JLabel();
        lblCodigoProducto = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        txtDescuento = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblTitulo = new javax.swing.JLabel();
        cmbNombre = new javax.swing.JComboBox<>();
        lblCategoria = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblTotalRenta = new javax.swing.JLabel();
        lblTotalDescuento = new javax.swing.JLabel();
        txtTotalRenta = new javax.swing.JTextField();
        txtTotalDescuento = new javax.swing.JTextField();
        lblAdelanto = new javax.swing.JLabel();
        txtAdelanto = new javax.swing.JTextField();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        setMinimumSize(new java.awt.Dimension(1090, 627));
        setPreferredSize(new java.awt.Dimension(1090, 627));
        setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(237, 172, 196));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(14, 13, 21));
        jPanel2.setLayout(new java.awt.BorderLayout());

        lblTituloRenta.setBackground(new java.awt.Color(14, 13, 21));
        lblTituloRenta.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTituloRenta.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloRenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloRenta.setText("Factura renta");
        jPanel2.add(lblTituloRenta, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 1090, 48);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 222, 212));

        TableDetalle.setBackground(new java.awt.Color(164, 188, 188));
        TableDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Cantidad", "Descuento", "Precio unitario", "Precio por categoria"
            }
        ));
        TableDetalle.setEnabled(false);
        TableDetalle.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(TableDetalle);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 48, 670, 480);

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setBorder(null);
        jScrollPane1.setAlignmentX(0.0F);
        jScrollPane1.setAlignmentY(0.0F);

        jPanel4.setBackground(new java.awt.Color(255, 222, 212));
        jPanel4.setPreferredSize(new java.awt.Dimension(150, 418));

        lblNombreProducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNombreProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/producto35px.png"))); // NOI18N
        lblNombreProducto.setText("Nombre producto");

        lblCodigoProducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCodigoProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCodigoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/codigoproducto35px.png"))); // NOI18N
        lblCodigoProducto.setText("Codigo producto");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        lblCantidad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/cantidad35px.png"))); // NOI18N
        lblCantidad.setText("Cantidad");

        lblDescuento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDescuento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/descuento35px.png"))); // NOI18N
        lblDescuento.setText("Descuento");

        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuento.setText("-");
        txtDescuento.setEnabled(false);

        lblPrecio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPrecio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/precio35px.png"))); // NOI18N
        lblPrecio.setText("Precio");

        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecio.setText("-");
        txtPrecio.setEnabled(false);

        btnAgregar.setBackground(new java.awt.Color(164, 188, 188));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/agregar30px.png"))); // NOI18N
        btnAgregar.setText("Agregar a la lista");
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });

        btnFinalizar.setBackground(new java.awt.Color(164, 188, 188));
        btnFinalizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/derecha30px.png"))); // NOI18N
        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(164, 188, 188));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/cancelar30px.png"))); // NOI18N
        btnCancelar.setText("Cancelar");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Detalles de renta");

        cmbNombre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Silla", "mesa" }));
        cmbNombre.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbNombrePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        lblCategoria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCategoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/categoria35px.png"))); // NOI18N
        lblCategoria.setText("Categoría");

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Decoración", "audivisual" }));
        cmbCategoria.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("$");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("%");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(cmbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAgregar)
                        .addGap(39, 39, 39)
                        .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addComponent(lblDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 9, 9))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(lblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addGap(61, 61, 61)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreProducto)
                    .addComponent(lblCodigoProducto))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategoria)
                    .addComponent(lblPrecio))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantidad)
                    .addComponent(lblDescuento))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnFinalizar))
                .addGap(28, 28, 28)
                .addComponent(btnCancelar)
                .addGap(58, 58, 58))
        );

        jScrollPane1.setViewportView(jPanel4);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(677, 48, 420, 578);

        jPanel6.setBackground(new java.awt.Color(94, 45, 79));

        lblTotalRenta.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalRenta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalRenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/totalpago35px.png"))); // NOI18N
        lblTotalRenta.setText("Total de renta:  $");

        lblTotalDescuento.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalDescuento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalDescuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/descuento35px.png"))); // NOI18N
        lblTotalDescuento.setText("Total de descuento:     $");

        txtTotalRenta.setEnabled(false);

        txtTotalDescuento.setEnabled(false);

        lblAdelanto.setForeground(new java.awt.Color(255, 255, 255));
        lblAdelanto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAdelanto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/adelante35px.png"))); // NOI18N
        lblAdelanto.setText("Adelanto:   $");

        txtAdelanto.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAdelanto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAdelanto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblTotalRenta, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalRenta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(txtTotalDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTotalRenta)
                        .addComponent(txtTotalRenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTotalDescuento))
                    .addComponent(txtTotalDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAdelanto)
                    .addComponent(txtAdelanto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jPanel1.add(jPanel6);
        jPanel6.setBounds(0, 530, 671, 96);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );
    }// </editor-fold>//GEN-END:initComponents
    //Método que llena la tabla y carga la lista para luego actualizar
    
    void llenadoInicial(){
        DatosProducto = Controlador.DatosProductos();
        NombreProducto.addElement("Sin seleccionar");
        for (int i = 0; i < DatosProducto.size(); i++) {
            Object[] dat = DatosProducto.get(i);
            CodigoProducto.add(dat[1].toString());
            NombreProducto.addElement(dat[0].toString());
        }
        cmbNombre.setModel(NombreProducto);
    }
    
    void llenar() {
        try {
            //Se declaran los vectores de objetos donde se almacenará la información
            //objeto que llena la tabla
            Object[] filas = new Object[6];
            //Objeto que llenará tbDetalleFacturaRenta
            Object[] Detalle = new Object[5];
            //Se llanan las primeras filas con los datos
            filas[0] = txtCodigo.getText();
            Detalle[3] = txtCodigo.getText();;
            filas[1] = cmbNombre.getSelectedItem().toString();
            filas[2] = txtCantidad.getText();
            Detalle[0] = txtCantidad.getText();
            filas[3] = txtDescuento.getText();
            filas[4] = txtPrecio.getText();
            //Se calcula el total y el descuento aplicada como el dinero descontado
            double total = (Integer.parseInt(txtCantidad.getText())) * (Double.parseDouble(txtPrecio.getText()));
            double des = ((Double.parseDouble(txtDescuento.getText())) / 100) * (Double.parseDouble(txtPrecio.getText()));
            //Se cambia el formato a dos decmales
            Detalle[1] = (String) Formato.format(des);
            //Se guardan el total restandole el descuento
            total = total - des;
            //Se guarda el resto de datos
            filas[5] = (String) (Formato.format(total - des));
            String NTotal = (String) Formato.format(total);
            Detalle[2] = NTotal;
            Detalle[4] = Controlador.codigoFactura();
            //Guardan los datos en variables globales
            precio = precio + total;
            descuento = descuento + des;
            adelanto = precio / 2;
            //Se asignan los datos para mostrarlos
            txtTotalRenta.setText((String) Formato.format(precio));
            txtTotalDescuento.setText((String) Formato.format(descuento));
            txtAdelanto.setText((String) Formato.format(adelanto));
            //Se guardan los datos en la lista para posteriormente agregarlos 
            //en la base de datos 
            DatosTabla.add(Detalle);
            //Se agregan los datos en el modelo de la tabla
            tabla.addRow(filas);
            //Se cargan los datos en la tabla por medio del modelo
            TableDetalle.setModel(tabla);
        } catch (Exception e) {
            //Se envía un error si hay problemas con la base de datos
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Errir al agregar un dato al detalle");
            error.TAnotiError.setText("Los datos obtenidos son incorrectos, por favor verifica la base de datos");
        }
    }

    //Método que obtiene los datos de la tabla para ingresarlos
    public void ingresar() {
        //Se declara el vector de objetos donde se guardaran los datos
        Object[] datos = new Object[6];
        try {
            //Se guardan los datos de la tabla para actualizar la factura
            for (int i = 0; i < TableDetalle.getRowCount() + 1; i++) {
                datos[0] = (String) TableDetalle.getValueAt(i + 1, 3); //Cantidad
                datos[1] = (String) TableDetalle.getValueAt(i + 1, 4); //Descuento
                datos[2] = (String) TableDetalle.getValueAt(i + 1, 6); //Total por producto
                datos[3] = (String) TableDetalle.getValueAt(i + 1, 1); //CodigoProducto
                datos[4] = Controlador.codigoFactura(); //CodigoFactura
                datos[5] = 1; //Estadp
            }
            //Se verifica el estado de la insercción
            if (Controlador.GuardarDetalle(datos)) {
            } else {
                //Se envía un error si hay datos incorrectos
                FrmNotiError error = new FrmNotiError();
                error.lblTituloError.setText("Error al cargar");
                error.TAnotiError.setText("Datos incorrectos");
            }
        } catch (Exception e) {
            //Se envía un error si hay problemas con la base de datos
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error al cargar");
            error.TAnotiError.setText("No se pudieron ingresar a la base de datos");
        }

    }

    //Método que actualiza la factura
    //Se ejecuata cuando se finaliza la factura
    public void actualizarFactura() {
        //Se crea un objeto dónde se guardarán los datos
        Object[] datos = new Object[4];
        datos[0] = txtTotalRenta.getText(); //Total de la renta
        datos[1] = txtAdelanto.getText(); //Adelanto
        datos[3] = Controlador.codigoFactura(); //Codigo de la última factura
        //Se envían los datos al controlador
        Controlador.setActualizarfactura(datos);
    }

    //Método que limpia los campos
    //Se ejecuta cada vez que se agregar un producto a la lista
    public void limpiar() {
        //Se reinician todos los valores a cero (0)
        cmbNombre.setSelectedIndex(0);
        cmbCategoria.setSelectedIndex(0);
        txtCodigo.setText("");
        txtPrecio.setText("0.0");
        txtDescuento.setText("0.0");
        txtCantidad.setText("");
        //Se bloquea para que no se ingrese nada mientras no haya un producto
        txtCantidad.setEnabled(false);
    }

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        //Se validan los datos
        validaciones.Codigos(evt, txtCodigo.getText());
        Controlador.ejecutar(txtCodigo.getText());
        //Objeto que obtendrá lso datos obtenidos por el buscador
        Object[] datos = Controlador.ejecutar(txtCodigo.getText());
        try {
            //Se asignan los valores obtenidos por la busqueda
            txtPrecio.setText(String.valueOf(datos[1]));
            txtDescuento.setText(String.valueOf(datos[2]));
            cmbCategoria.setSelectedIndex(Integer.parseInt(String.valueOf(datos[3])));
            cmbNombre.setSelectedIndex(Integer.parseInt(String.valueOf(datos[0])));
            /*Si no se encuentran los datos precio y descuento se les asignara un guión (-)
            Se verifica si ese guión existe dentro de ellos
            Si es así entonces la cantidad estara vacía y estará deshabilitada
             */
            if (txtPrecio.getText().contains("-")) {
                txtCantidad.setText("");
                txtCantidad.setEnabled(false);
            } else {
                /*Si cantidad y precio obtienen algo entonces se habilitara
                la posibilidad de escoger una cantidad para el detalle
                 */
                txtCantidad.setEnabled(true);
            }

        } catch (Exception e) {
            //Si ocurre un error en la busqueda entonces se deshabilitara
            //la opción de la cantidad y se pondra a 0
            txtCantidad.setEnabled(false);
            txtCantidad.setText("");
            System.out.print(e.toString());
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        //Se crea un objeto de la notificación
        FrmNoti1 notificación = new FrmNoti1();
        //Se verifica si la cantidad y el codigo estan vacíos
        if (txtCantidad.getText().isEmpty() || txtCodigo.getText().isEmpty()) {
            //Si estan vacíos se le notifica al cajero que faltan campoc por llenar
            notificación.setVisible(true);
            notificación.TAMensajeError.setText("Existen campos sin datos asignados");
            notificación.lblTituloNoti1.setText("¡Campos vacíos!");
        } else {
            //Si no entonces se verifica que exista la cantidad suficiente en el inventario
            if (Controlador.CantProductoIN(txtCodigo.getText()) >= Integer.parseInt(txtCantidad.getText())) {
                //Se llena la insertan los datos y se limpian
                llenar();
                limpiar();
            } else {
                //Obtenemos la cantidad existente de esos productos
                int cantidadP = Controlador.CantProductoIN(txtCodigo.getText());
                //Si la cantidad es menor a la que queda en el inventario entonces se le notifica al cajero
                notificación.TAMensajeError.setText("La cantidad propuesta es mayor a la cantidad en el inventario, por favor digite una menor a "+cantidadP+" del producto con nombre " + cmbNombre.getSelectedItem());
                notificación.lblTituloNoti1.setText("Producto insuficientes");
                notificación.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void cmbNombrePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbNombrePopupMenuWillBecomeInvisible
        //Se verifica la selección del producto
        if (cmbNombre.getSelectedIndex() != 0) {
            //Se desbloquea la cantidad
            txtCantidad.setEnabled(true);
            //Se traen los datos a base del codigo
            String codigo = CodigoProducto.get(cmbNombre.getSelectedIndex()-1);
            //Si es diferente de cero, entonces se cargan los datos en los componentes
            Object[] datos = Controlador.CargarDatosCMB(codigo);
            //Se cargan los datos del producto seleccionado
            txtCodigo.setText(datos[0].toString());
            txtPrecio.setText(datos[1].toString());
            txtDescuento.setText(datos[2].toString());
            cmbCategoria.setSelectedIndex(Integer.parseInt(String.valueOf(datos[3])));
        } else {
            //Se bloquea la cantidad
            txtCantidad.setText("");
            txtCantidad.setEnabled(false);
        }
    }//GEN-LAST:event_cmbNombrePopupMenuWillBecomeInvisible

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        //Se envía los datos al controlador para enviar los datos 
        //para finalizar la renta
        Controlador.setAdel(adelanto);
        Controlador.setDatos(DatosTabla);
        actualizarFactura();
        //Se abre el nuevo formulario para finalizar la renta
        FrmRentaFinalizada renta = new FrmRentaFinalizada();
        renta.setVisible(true);
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        //Sa valida que solo se admitan los números
        validaciones.soloNumeros(evt, 1, txtCantidad.getText());
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        //Se verifica que la cantidad máxima para el codigo no pueda ser más de 5
        if (txtCodigo.getText().length() < 5) {
        } else {
            //Sino se eliminan
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable TableDetalle;
    public static javax.swing.JButton btnAgregar;
    public static javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnFinalizar;
    public static javax.swing.JComboBox<String> cmbCategoria;
    public static javax.swing.JComboBox<String> cmbNombre;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JList<String> jList1;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel4;
    public static javax.swing.JPanel jPanel6;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JSeparator jSeparator1;
    public static javax.swing.JLabel lblAdelanto;
    public static javax.swing.JLabel lblCantidad;
    public static javax.swing.JLabel lblCategoria;
    public static javax.swing.JLabel lblCodigoProducto;
    public static javax.swing.JLabel lblDescuento;
    public static javax.swing.JLabel lblNombreProducto;
    public static javax.swing.JLabel lblPrecio;
    public static javax.swing.JLabel lblTitulo;
    public static javax.swing.JLabel lblTituloRenta;
    public static javax.swing.JLabel lblTotalDescuento;
    public static javax.swing.JLabel lblTotalRenta;
    public static javax.swing.JTextField txtAdelanto;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtDescuento;
    public static javax.swing.JTextField txtPrecio;
    public static javax.swing.JTextField txtTotalDescuento;
    public static javax.swing.JTextField txtTotalRenta;
    // End of variables declaration//GEN-END:variables
}
