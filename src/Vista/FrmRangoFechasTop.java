package Vista;
//Importamos las librerias a utilizar
import Controlador.ControladorInventarioRenta;
import Controlador.ControladorVariables;
import Modelo.Conexion;
import Recursos_Tipografias.Fuentes;
import java.awt.Color;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Sey
 */
public class FrmRangoFechasTop extends javax.swing.JFrame {
    //Creamos variables X y Y que capturaran la posicion del form
    int x, y;
    
    private static java.util.Date fechaInicio;
    private static java.util.Date fechaFin;
    
    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Morado = new Color(94,45,79);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);
    
    public FrmRangoFechasTop() {
        initComponents();
        Colores();
        setLocationRelativeTo(null);
        //Clase que colocará la fuente
        Fuentes tipoFuente;
        tipoFuente = new Fuentes();
        //Colocación de la fuente a los componentes
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 20));
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
        lblTitulo = new javax.swing.JLabel();
        btnMin = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        lblFechaInicio = new javax.swing.JLabel();
        lblFechaFin = new javax.swing.JLabel();
        dtFechaFin = new com.toedter.calendar.JDateChooser();
        dtFechaInicio = new com.toedter.calendar.JDateChooser();
        lblInfo = new javax.swing.JLabel();
        btnGenerar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(94, 45, 79));
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

        jPanel2.setBackground(new java.awt.Color(14, 13, 21));

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Fechas");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
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
        dtFechaInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtFechaInicioPropertyChange(evt);
            }
        });

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInfo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInfo)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dtFechaFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtFechaFinPropertyChange
        
    }//GEN-LAST:event_dtFechaFinPropertyChange

    void CrearReporte(){
        ControladorVariables variables = new ControladorVariables();
        JasperReport factura;
        Connection conexion = Conexion.Conexion();
        Map parametro = new HashMap();
        try{
            parametro.put("FechaInicio", fechaInicio);
            parametro.put("FechaFin", fechaFin);
            parametro.put("Nombre", variables.getNombreE());
            factura = JasperCompileManager.compileReport("src/Reportes/Grafica_Top10_venta.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(factura,parametro, conexion);
            JasperViewer.viewReport(jp, false);
        }catch(Exception e){
            System.out.println(e.getMessage());
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("Error al crear Reporte");
            error.setVisible(true);
        }
    }
    
    private void dtFechaInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtFechaInicioPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_dtFechaInicioPropertyChange

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        if (dtFechaInicio.getDate() == null || dtFechaFin.getDate() == null){
            FrmNoti1 noti = new FrmNoti1();
            noti.lblTituloNoti1.setText("Campos Vacios");
            noti.TAMensajeError.setText("No ha seleccionado ninguna fecha de inicio o de fin");
            noti.setVisible(true);
        }
        else if(dtFechaInicio.getDate().after(dtFechaFin.getDate())){
            FrmNoti1 noti = new FrmNoti1();
            noti.lblTituloNoti1.setText("Fechas Invalidas");
            noti.TAMensajeError.setText("La fecha inicial debe ser menor a la fecha final.");
            noti.setVisible(true);
        }
        else{
            //Para la fecha obtenemos con la clase date
            java.util.Date fecha1 = dtFechaInicio.getDate();
            java.util.Date fecha2 = dtFechaFin.getDate();
            
            //enviando la información a la clase
            SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaI = Formato.format(fecha1);//Asignamos el formato de fecha de año, mes, dia
            String fechaF = Formato.format(fecha2);
            
            fechaInicio = java.sql.Date.valueOf(fechaI);
            fechaFin = java.sql.Date.valueOf(fechaF);
            CrearReporte();
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        //Cerramo el formulario
        this.dispose();
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinActionPerformed
        this.setExtendedState(1);
    }//GEN-LAST:event_btnMinActionPerformed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        //Creamos metodo que actualice la posicion del form dependendiendo donde el usuario lo coloque
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        //Inicializamos las variables x y y con la posicion actual del form
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

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
            java.util.logging.Logger.getLogger(FrmRangoFechasTop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRangoFechasTop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRangoFechasTop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRangoFechasTop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRangoFechasTop().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnMin;
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
