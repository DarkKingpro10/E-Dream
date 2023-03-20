/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Validaciones;
import Controlador.ControladorPagarRenta;
import Controlador.ControladorVariables;
import Modelo.Conexion;
import javax.swing.JComponent;
import Recursos_Tipografias.Fuentes;
import java.awt.Color;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Oliver
 */
public class FrmRentaFinalizadaPagar extends javax.swing.JFrame {

    //Objeto para las validaciones
    Validaciones val = new Validaciones();
    //Declarando arreglo de objetos
    Object[] datos;
    //Objeto del controlador
    ControladorPagarRenta controlador = new ControladorPagarRenta();
    //Se agrega el formato de la localidad
    DecimalFormatSymbols localidad = new DecimalFormatSymbols(Locale.US);
    //Formato para los precios (Números decimales)
    DecimalFormat Formato = new DecimalFormat("###,###.##", localidad);
    //Lista que guarda todos los productos
    ArrayList<Object[]> productos;

    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Morado = new Color(94,45,79);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);
    
    /**
     * Creates new form FrmRentaFinalizadaPagar
     */
    public FrmRentaFinalizadaPagar() {
        //Se coloca la fuente a los componente
        Fuentes tipoFuente;
        initComponents();
        Colores();
        tipoFuente = new Fuentes();
        //Fuente a los textfield
        CI.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 15));
        txtMonto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtPago.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtFecha.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtCodigo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtEstado.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtCliente.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtDUI.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtMora.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtVuelto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));

        //Fuente a los labels
        lblMonto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblPagar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblFecha.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblCodigo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblEstado.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblCliente.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblDUI.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblMora.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 40));
        lblVuelto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));

        //Fuente a los botones
        btnGuardar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnCancelar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        //Se crea el objeto los componentes
        JComponent[] pago = {txtPago};
        //Se valida el copiar y oegar
        val.noCopiar(pago);
        val.noPegar(pago);
        //Se oculta el alerta
        CI.setVisible(false);
        this.setLocationRelativeTo(null);
    }

    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                jPanel1.setBackground(Piel);
                pnlTitulo.setBackground(Oscuro);
                break;
            case 2://Claro
                jPanel1.setBackground(Color.white);
                pnlTitulo.setBackground(AzulOscuro);
                break;
            default:
                jPanel1.setBackground(Piel);
                pnlTitulo.setBackground(Oscuro);
                break;
        }        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlTitulo = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        lblMonto = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        lblCliente = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        lblMora = new javax.swing.JLabel();
        txtMora = new javax.swing.JTextField();
        lblDUI = new javax.swing.JLabel();
        txtDUI = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtEstado = new javax.swing.JTextField();
        lblPagar = new javax.swing.JLabel();
        txtPago = new javax.swing.JTextField();
        CI = new javax.swing.JLabel();
        lblVuelto = new javax.swing.JLabel();
        txtVuelto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1090, 628));

        jPanel1.setBackground(new java.awt.Color(255, 222, 212));

        pnlTitulo.setBackground(new java.awt.Color(14, 13, 21));
        pnlTitulo.setAlignmentX(0.0F);
        pnlTitulo.setAlignmentY(0.0F);
        pnlTitulo.setPreferredSize(new java.awt.Dimension(100, 100));
        pnlTitulo.setLayout(null);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Close.png"))); // NOI18N
        jLabel9.setText("jLabel9");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        pnlTitulo.add(jLabel9);
        jLabel9.setBounds(1020, 20, 20, 23);

        lblTitulo.setBackground(new java.awt.Color(14, 13, 21));
        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Pagar renta final");
        lblTitulo.setMinimumSize(new java.awt.Dimension(404, 55));
        lblTitulo.setPreferredSize(new java.awt.Dimension(404, 55));
        pnlTitulo.add(lblTitulo);
        lblTitulo.setBounds(90, 0, 920, 55);

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        lblEstado.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblEstado.setText("Estado:");

        lblFecha.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFecha.setText("Fecha realizada:");

        txtFecha.setEnabled(false);

        lblMonto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblMonto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMonto.setText("Monto a pagar:");

        txtCliente.setEnabled(false);

        lblCliente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCliente.setText("Cliente:");

        txtMonto.setEnabled(false);

        lblMora.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblMora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMora.setText("Mora:");

        txtMora.setEnabled(false);

        lblDUI.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblDUI.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDUI.setText("DUI:");

        txtDUI.setEnabled(false);

        lblCodigo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblCodigo.setText("Código de la factura:");

        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnGuardar.setText("Pagar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtEstado.setEnabled(false);

        lblPagar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblPagar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPagar.setText("Monto ingresado:");

        txtPago.setEnabled(false);
        txtPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPagoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPagoKeyTyped(evt);
            }
        });

        CI.setForeground(new java.awt.Color(204, 0, 51));
        CI.setText("La cantidad ingresada es insuficiente");

        lblVuelto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblVuelto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVuelto.setText("Vuelto:");

        txtVuelto.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(lblDUI, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDUI, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(322, 322, 322)
                                .addComponent(lblMora, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMora, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblEstado))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(lblPagar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CI, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(92, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(339, 339, 339))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(lblVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(lblCodigo)
                    .addContainerGap(825, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstado)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFecha)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCliente)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDUI, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtMora, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDUI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(lblMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3))))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(125, 125, 125)
                    .addComponent(lblCodigo)
                    .addContainerGap(474, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //Se extraen los datos obtenidos
        double monto = Double.parseDouble(datos[4].toString());
        double adelanto = Double.parseDouble(datos[5].toString());

        if (Double.parseDouble(txtPago.getText()) >= (monto - adelanto)) {
            if (controlador.GuardarRenta(Double.parseDouble(txtMonto.getText()), Integer.parseInt(txtCodigo.getText()))) {
                //Se confirma la correcta inserción
                FrmNoti2 notificacion = new FrmNoti2();
                //Se le notifica que se ha finalizazo la renta
                CrearFactura(Integer.parseInt(txtCodigo.getText()), Double.parseDouble(txtMonto.getText()));
                notificacion.lblTituloNoti2.setText("¡Factura finalizada!");
                notificacion.TANoti2.setText("La factura se ha finalizado \n ¡Gracias por su compra!");
                notificacion.setVisible(true);
                limpiar();
            }
        } else {
            System.out.println("Monto insuficiente");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        //Se cierra el formulario
        dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        val.soloNumeros(evt, 1, txtCodigo.getText());
        try {
            //Se busca la renta
            buscar(Integer.parseInt(txtCodigo.getText()));
        } catch (Exception e) {
            System.out.println("Buscar: "+e);
        }

    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoKeyTyped
        //Validación, solo números y puntos
        val.formatoDecimal(evt, txtPago.getText());
        val.soloNumeros(evt, 5, txtPago.getText());
    }//GEN-LAST:event_txtPagoKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //Limpia todos los campos
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoKeyReleased
        //Validación, solo números y puntos
        val.soloNumeros(evt, 5, txtPago.getText());
        if (Double.parseDouble(txtPago.getText()) < Double.parseDouble(txtMonto.getText())) {
            //Se muestra la alerta de insuficiente
            CI.setVisible(true);
            //Se deshabilita el botón
            btnGuardar.setEnabled(false);
        } else {
            //Se coloca el vuelto
            txtVuelto.setText(String.valueOf(Formato.format(Double.parseDouble(txtPago.getText()) - Double.parseDouble(txtMonto.getText()))));
            //Se oculta la alerta
            CI.setVisible(false);
            //Se habilita el botón
            btnGuardar.setEnabled(true);
        }
    }//GEN-LAST:event_txtPagoKeyReleased
    //Método que busca los datos de la renta
    void buscar(int codigo) {
        try {
            //Se intenta obtener los datos a partir del codigo de factura ingresado
            datos = controlador.FinalizarFactura(codigo);
            txtCliente.setText(datos[0].toString()); //Nombre del cliente
            txtEstado.setText(datos[1].toString()); //Estado de la factura
            txtDUI.setText(datos[2].toString()); //GUI del cliente
            double mora = Double.parseDouble(String.valueOf(String.valueOf(datos[3])));
            txtMora.setText(String.valueOf(mora)); //Mora, en caso exista
            double monto = Double.parseDouble(String.valueOf(datos[4])); //Monto total de la factura
            double adelanto = Double.parseDouble(String.valueOf(datos[5])); //Alenato pagado en el primer pago
            txtMonto.setText(String.valueOf(monto - adelanto)); //Se coloca el monto del segundo pago
            txtFecha.setText(datos[6].toString()); //Fecha que se creó la factura
            //Se habilita el textbox para ingresar el monto
            if (txtCliente.getText().contains("-")) {
                txtPago.setEnabled(false);
            }else{
                txtPago.setEnabled(true);
            }
            
        } catch (Exception e) {
            System.out.println(e);
            //Se deshabilita si no lo encuentra
            txtPago.setEnabled(false);
        }

    }

    //Método que limpia todos los campos
    void limpiar() {
        txtCliente.setText("");
        txtEstado.setText("");
        txtDUI.setText("");
        txtMora.setText("");
        txtMonto.setText("");
        txtFecha.setText("");
        txtPago.setText("");
        btnGuardar.setEnabled(false);
        txtPago.setEnabled(false);
    }
    //Método muestra la factura
    void CrearFactura(int codigo, double monto) {
        JasperReport factura;
        //Conexión
        Connection conexion = Conexion.Conexion();
        //Parametros a enviar
        Map parametro = new HashMap();
        try {
            //Método que regresa los productos al inventario
            obtenerProductos(codigo);
            //Ingreso de parametros
            parametro.put("CodigoFactura", codigo);
            parametro.put("Monto", monto);
            //Busqueda del reporte
            factura = JasperCompileManager.compileReport("src/Reportes/TicketPagarR.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(factura, parametro, conexion);
            JasperViewer ventana = new JasperViewer(jp, false);
            ventana.setTitle("Ticket de renta final");
            ventana.setVisible(true);
        } catch (Exception e) {
            System.err.print(e);
        }
    }
    //Método que obtiene e ingresa los productos
    void obtenerProductos(int Codigo){
        //Obtiene los productos pertenecientes a una factura de renta
        productos = controlador.ProductosRenta(Codigo);
        //Se verifican las cantidad de productos
        for (int i = 0; i < productos.size(); i++) {
            Object[] datos = productos.get(1);
            //se obtiene el codigo de producto
            String codigoProducto = String.valueOf(datos[0]);
            //Se obtiene la cantidad en la factura
            int cantidad = Integer.parseInt(String.valueOf(datos[1]));
            //Se obtiene la cantidad del inventarui
            int CantidadFactura = controlador.ProductosInventario(codigoProducto);
            //Se suman las cantidades como la devolución
            int total = cantidad + CantidadFactura;
            if (controlador.confirmacion(codigoProducto, total)) {
            }
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
            java.util.logging.Logger.getLogger(FrmRentaFinalizadaPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRentaFinalizadaPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRentaFinalizadaPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRentaFinalizadaPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FrmRentaFinalizadaPagar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CI;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDUI;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblMora;
    private javax.swing.JLabel lblPagar;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVuelto;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDUI;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtMora;
    private javax.swing.JTextField txtPago;
    private javax.swing.JTextField txtVuelto;
    // End of variables declaration//GEN-END:variables
}
