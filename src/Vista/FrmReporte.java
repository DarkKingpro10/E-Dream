package Vista;

import Recursos_Tipografias.Fuentes;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import Modelo.ModeloDistribuidor;
import Controlador.ControladorDistribuidor;
import Controlador.ControladorVariables;
import Modelo.Conexion;
import java.awt.Color;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 * @author SeyJR y Ericksson
 */
public class FrmReporte extends javax.swing.JPanel {

    ModeloDistribuidor distribuidor = new ModeloDistribuidor();
    ControladorDistribuidor controlador = new ControladorDistribuidor();
    ArrayList<Integer> CodigoDistribuidor = new ArrayList<Integer>();

    public static Integer iden;
    private TableRowSorter trs;

    ArrayList<Integer> CodigoD;
    FrmNoti2 NInsert = new FrmNoti2();
    FrmNoti3 NDelete = new FrmNoti3();
    FrmNotiError NError = new FrmNotiError();
    DefaultTableModel tablaDistribuidor = new DefaultTableModel();

    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Morado = new Color(94,45,79);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);
    
    public FrmReporte() {
        initComponents();
        Fuentes tipoFuente;
        tipoFuente = new Fuentes();
        Colores();
        //Fuentes de Tipografia
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 40));
        lblDistribuidor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnEliminar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnActualizar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnIngresar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtDistribuidor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtBuscar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        tbDistribuidor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));

        tablaDistribuidor = new DefaultTableModel();
        tablaDistribuidor.addColumn("Id");
        tablaDistribuidor.addColumn("Distribuidor");
        this.tbDistribuidor.setModel(tablaDistribuidor);
        MostrarDatos();
        CodigoDistribuidor = controlador.CargarIdDistribuidor();
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblDistribuidor = new javax.swing.JLabel();
        txtDistribuidor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDistribuidor = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 222, 212));

        jPanel2.setBackground(new java.awt.Color(14, 13, 21));
        jPanel2.setPreferredSize(new java.awt.Dimension(144, 44));

        lblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Proveedor");
        lblTitulo.setMaximumSize(new java.awt.Dimension(100, 100));
        lblTitulo.setMinimumSize(new java.awt.Dimension(690, 79));
        lblTitulo.setPreferredSize(new java.awt.Dimension(144, 44));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblDistribuidor.setBackground(new java.awt.Color(255, 255, 255));
        lblDistribuidor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Distribuidor50px.png"))); // NOI18N
        lblDistribuidor.setText("Proveedor:");

        jPanel3.setBackground(new java.awt.Color(91, 63, 105));

        tbDistribuidor.setAutoCreateRowSorter(true);
        tbDistribuidor.setBackground(new java.awt.Color(164, 188, 188));
        tbDistribuidor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Distribuidor"
            }
        ));
        tbDistribuidor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDistribuidorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDistribuidor);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        btnIngresar.setBackground(new java.awt.Color(164, 188, 188));
        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/agregar30px.png"))); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(164, 188, 188));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Actualizar30px.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(164, 188, 188));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Eliminar30px.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Buscador50px.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(191, 191, 191))
                            .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblDistribuidor)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addGap(63, 63, 63))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDistribuidor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    //Hacemos que muestre los datos en la tabla
    public void MostrarDatos() {
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = Controlador.ControladorDistribuidor.CargarTablaController();
        modelo.setColumnIdentifiers(new Object[]{"Id", "Distribuidor"});
        try {
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getInt("idDistribuidor"), rs.getString("nombreDistribuidor")});
            }
            tbDistribuidor.setModel(modelo);
        } catch (Exception e) {
            NError.setVisible(true);
            NError.TAnotiError.setText("Error al cargar la tabla");
            NError.lblTituloError.setText("Datos no mostrados");
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Controlador.ControladorDistribuidor obj = new Controlador.ControladorDistribuidor();
        controlador.setIdDistribuidor(controlador.getIdDisVista());
        if (obj.EliminarDistribuidorController()) {
            // compararEstado();
            MostrarDatos();
            FrmNoti2 notificacion = new FrmNoti2();
            notificacion.setVisible(true);
            notificacion.lblTituloNoti2.setText("¡Distribuidor Eliminado!");
            notificacion.TANoti2.setText("El distribuidor\n se ha eliminado");
        } else {
            FrmNotiError error = new FrmNotiError();
            error.setVisible(true);
            error.lblTituloError.setText("Error Al Eliminar");
            error.TAnotiError.setText("No se pudo eliminar el dato,\nproblemas de conexión");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        Controlador.ControladorDistribuidor obj = new Controlador.ControladorDistribuidor();
        obj.setNombreDistribuidor(txtDistribuidor.getText());

        if (obj.IngresarDistribuidorController()) {
            // compararEstado();
            MostrarDatos();
            FrmNoti2 notificacion = new FrmNoti2();
            notificacion.setVisible(true);
            notificacion.lblTituloNoti2.setText("¡Distribuidor Creado!");
            notificacion.TANoti2.setText("El distribuidor\n se ha creado");
        } else {
            FrmNotiError error = new FrmNotiError();
            error.setVisible(true);
            error.lblTituloError.setText("Error Al Crear");
            error.TAnotiError.setText("No se pudo guardar el dato\n,problemas de conexión");
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void tbDistribuidorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDistribuidorMouseClicked
        int fila = tbDistribuidor.rowAtPoint(evt.getPoint());

        ModeloDistribuidor distribuidor = new ModeloDistribuidor();
        CodigoD = distribuidor.IDDistribuidor();
        iden = CodigoD.get(fila);
        distribuidor.CargarDatosDistribuidor(iden);

        Object[] datos = controlador.CargarMenu(iden);
        try {
            controlador.setIdDisVista(Integer.parseInt(String.valueOf(datos[0])));
            txtDistribuidor.setText(String.valueOf(String.valueOf(datos[1])));
        }catch(Exception e){
            NError.setVisible(true);
            NError.TAnotiError.setText("Error al cargar los datos!");
            NError.lblTituloError.setText("Error al cargar");
            System.out.println(e);
        }
    }//GEN-LAST:event_tbDistribuidorMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        Controlador.ControladorDistribuidor obj = new Controlador.ControladorDistribuidor();

        controlador.setNombreDistribuidor(txtDistribuidor.getText());
        controlador.setIdDistribuidor(controlador.getIdDisVista());

        //enviando guardar a  SQLServer
        if (obj.ActualizarDistribuidorController()) {
            MostrarDatos();
            FrmNoti2 notificacion = new FrmNoti2();
            notificacion.setVisible(true);
            notificacion.lblTituloNoti2.setText("¡Distribuidor Actualizado!");
            notificacion.TANoti2.setText("El distribuidor\n se ha actualizado");
        } else {
            FrmNotiError error = new FrmNotiError();
            error.setVisible(true);
            error.lblTituloError.setText("Error al Actualizar");
            error.TAnotiError.setText("No se pudo guardar el dato,\nproblemas de conexión");
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    public void BuscadorDistribuidor() {
        //Creamos columnas
        String[] titulo = {"Id", "Distribuidor"};
        //Le asignamos al resulset el metodo de buscar distribuidor en controlador
        ResultSet rs = Controlador.ControladorDistribuidor.BuscadorD();
        tablaDistribuidor = new DefaultTableModel (null,titulo);
        try {
           
         while (rs.next()) {
                //Agregamos las filas de datos
                tablaDistribuidor.addRow(new Object[]{rs.getInt("idDistribuidor"), rs.getString("nombreDistribuidor")});
            }
           //Le asignamos el modelo a la tabla
           tbDistribuidor.setModel(tablaDistribuidor);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    
    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
     //Mandamos lo que ibtenga el textbox al set en controldor
     String buscador = txtBuscar.getText();
     controlador.setTextoB(buscador);
     //Ejecutamos metodo de cargar datos cargados
     BuscadorDistribuidor();
       
    }//GEN-LAST:event_txtBuscarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDistribuidor;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tbDistribuidor;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDistribuidor;
    // End of variables declaration//GEN-END:variables
}
