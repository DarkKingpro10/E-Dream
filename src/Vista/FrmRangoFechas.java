/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorInventario;
import Controlador.ControladorVariables;
import Modelo.Conexion;
import Recursos_Tipografias.Fuentes;
import java.awt.Color;
import java.awt.Image;
import java.net.URLDecoder;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Sey
 */
public class FrmRangoFechas extends javax.swing.JFrame {

    /**
     * Creates new form FrmRangoFechas
     */
    //Importamos las variables del login
    ControladorVariables variables = new ControladorVariables();
    //Creamos objeto de cotrolador
    ControladorInventario controlador = new ControladorInventario();
    //Se selecciona la fecha de ahora
    LocalDate Fecha = LocalDate.now();
    SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd"); //Se estable el formato de fehca
    ZoneId systemTimeZone = ZoneId.systemDefault();
    ZonedDateTime zonedDateTime = Fecha.atStartOfDay(systemTimeZone);
    Date FechaActual = Date.from(zonedDateTime.toInstant());
    //Creamos variables globales X y Y que ayudaran en metodo para mover el form
    int x, y;
    
    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Morado = new Color(94,45,79);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);

    public FrmRangoFechas() {
        initComponents();
        Colores();
        setLocationRelativeTo(null);

        /*Utilizamos new ImageIcon(URL location).getImage() donde el argumento
        *location lo obtenemos a través del método getClass().getResource(String name)
        *que nos devuelve un URL absoluto del recurso que especificamos como String.
         */
        Image icon = new ImageIcon(getClass().getResource("/Recursos_img/E-Dream logo.png")).getImage();
        setIconImage(icon);

        //Clase que colocará la fuente
        Fuentes tipoFuente;
        tipoFuente = new Fuentes();
        //Colocación de la fuente a los componentes
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 30));
        lblInfo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 14));
        lblFechaInicio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblFechaFin.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        dtFechaInicio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        dtFechaFin.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnGenerar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        //Hacemos que no se pueda editar la fecha desde el textfield para evitar errores
        dtFechaInicio.getDateEditor().setEnabled(false);
        dtFechaFin.getDateEditor().setEnabled(false);
    }

    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                jPanel1.setBackground(Morado);
                jPanel2.setBackground(Oscuro);
                break;
            case 2://Claro
                jPanel1.setBackground(Celeste);
                jPanel2.setBackground(AzulOscuro);
                break;
            default:
                jPanel1.setBackground(Morado);
                jPanel2.setBackground(Oscuro);
                break;
        }        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblFechaInicio = new javax.swing.JLabel();
        lblFechaFin = new javax.swing.JLabel();
        dtFechaFin = new com.toedter.calendar.JDateChooser();
        dtFechaInicio = new com.toedter.calendar.JDateChooser();
        lblInfo = new javax.swing.JLabel();
        btnGenerar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(94, 45, 79));

        jPanel2.setBackground(new java.awt.Color(14, 13, 21));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(46, 14, 54));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Close.png"))); // NOI18N
        btnCerrar.setBorder(null);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCerrar.setIconTextGap(-6);
        btnCerrar.setMaximumSize(new java.awt.Dimension(23, 22));
        btnCerrar.setMinimumSize(new java.awt.Dimension(23, 22));
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Fechas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(43, 43, 43)
                .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblFechaInicio.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaInicio.setText("Fecha inicio:");

        lblFechaFin.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaFin.setText("Fecha Fin:");

        dtFechaFin.setPreferredSize(new java.awt.Dimension(95, 35));
        dtFechaFin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtFechaFinPropertyChange(evt);
            }
        });

        dtFechaInicio.setPreferredSize(new java.awt.Dimension(95, 35));

        lblInfo.setForeground(new java.awt.Color(255, 255, 255));
        lblInfo.setText("Escoja el rango de fechas del cual quiere generar el reporte");

        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(dtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(lblInfo)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void dtFechaFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtFechaFinPropertyChange
        FrmNotiError NError = new FrmNotiError();

        try {
            if (dtFechaFin.getDate() == null) {

            } else {
                if (dtFechaInicio.getDate() == null) {
                    NError.setVisible(true);
                    NError.TAnotiError.setText("Primero se debe ingresar la fecha de inicio");
                    NError.lblTituloError.setText("No se ha ingresado");
                } else {
                    if (/*dtFechaFin.getDate().after(FechaActual) && */dtFechaFin.getDate().after(dtFechaInicio.getDate())) {

                    } else {
                        dtFechaFin.setDate(null);
                        NError.setVisible(true);
                        NError.TAnotiError.setText("La fecha ingresada debe ser mayor a la fecha de ingreso");
                        NError.lblTituloError.setText("Fecha incorrecta");
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("Error Fin" + e.toString());
        }

    }//GEN-LAST:event_dtFechaFinPropertyChange

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        //Validamos campos no vacios
        if (dtFechaInicio.getDate() == null || dtFechaFin.getDate() == null) {
            FrmNoti1 error = new FrmNoti1();
            error.lblTituloNoti1.setText("¡Algo no esta bien!");
            error.TAMensajeError.setText("!Campos vacios o invalidos!Revisa que todos los campos esten llenos");
            error.setVisible(true);

        } else {
            Date FechaI = dtFechaInicio.getDate(); //Se obtiene la fecha de icnio
            Date FechaF = dtFechaFin.getDate(); //Se obtiene la fecha fin
            SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd"); //Se estable el formato de fehca
            //Se carga el formato en las fechas obtenidad
            String NFI = Formato.format(FechaI);
            String NFF = Formato.format(FechaF);
            //Se convierte de fecha de java a fecha de SQL
            java.sql.Date FechaInicio = java.sql.Date.valueOf(NFI);
            java.sql.Date FechaFin = java.sql.Date.valueOf(NFF);

            String path = "";
            //Obtenemos empresa y empleado para mandarlos como parametri
            String empleado = variables.getNombreE() + " " + variables.getApellidoE();
            String usuario = variables.getUsuario();
            int empleadoE = variables.getEmpleado();
            String empresaE = controlador.CargarEmpresa(empleadoE);
            System.out.println(empresaE);

            try {

                // String nombreEmpresa = res.getString("em.nombreEmpresa");
                path = getClass().getResource("/Reportes/Inventario_Venta_Vendidos.jasper").getPath();
                //
                System.out.println(path);
                //Se decodifica por algun caracter especial
                path = URLDecoder.decode(path, "UTF-8");
                //Se crea la conexion
                Connection cn = new Conexion().Conexion();
                //Se rea los prametros
                Map parametros = new HashMap();
                // parametros.clear();
                //el nombre que se dio al parametro en JasperReport fue "p1", y se debe llamar desde Java con
                //ese mismo nombre, a su lado se pasa el valor del parametro
                parametros.put("Empresa", empresaE);
                parametros.put("Usuario", empleado);
                parametros.put("Fecha_Inicio", FechaInicio);
                parametros.put("Fecha_Fin", FechaFin);
                //Se crea el objeto de reporte
                JasperReport reporte = (JasperReport) JRLoader.loadObject(path);
                //Se crea el objeto impresion de reporte
                JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, cn);
                //Ahora e crea el vior donde e muestra el reporte
                JasperViewer visor = new JasperViewer(imprimir, false);//False es para dispose
                visor.setTitle("Reporte de productos vendidos");
                visor.setVisible(true);
                this.dispose();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        //Cerramo el formulario
        this.dispose();
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        //Inicializamos las variables x y y con la posicion actual del form
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        //Creamos metodo que actualice la posicion del form dependendiendo donde el usuario lo coloque
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jPanel2MouseDragged

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
            java.util.logging.Logger.getLogger(FrmRangoFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRangoFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRangoFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRangoFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRangoFechas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGenerar;
    private com.toedter.calendar.JDateChooser dtFechaFin;
    private com.toedter.calendar.JDateChooser dtFechaInicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
