/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorCalendario;
import Controlador.ControladorVariables;
import Modelo.Conexion;
import com.toedter.calendar.IDateEvaluator;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import Recursos_Tipografias.Fuentes;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Lenny
 */
public class FrmCalendario extends javax.swing.JPanel {
    Fuentes tipoFuente;
    private static java.util.Date fechaInicio;
    private static java.util.Date fechaFin;
    
    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);
    
    public static Color Morado = new Color(46, 14, 54);
    
    public FrmCalendario(){
        initComponents();
        Colores();
        CambiarEstadoRenta();
        Separador();
        
        tipoFuente = new Fuentes();
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 40));
        calendario.setBackground(Color.red);
        calendario.setDecorationBordersVisible(true);
        calendario.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 17));
        
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
                jPanel2.setBackground(Celeste);
                break;
            default:
                jPanel1.setBackground(Piel);
                jPanel2.setBackground(Oscuro);
                break;
        }        
    }

    
    void CambiarEstadoRenta(){
        if(ControladorCalendario.ActualizarRenta_Controller() == true){
            FrmNoti1 noti = new FrmNoti1();
            noti.lblTituloNoti1.setText("Revisar Rentas");
            noti.TAMensajeError.setText("Existen rentas pendientes, se ha encontrado rentas que excedieron el tiempo de entrega, contacte con ellos.");
            noti.setVisible(true);
        }
        else{
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de Actualización");
            error.TAnotiError.setText("No se ha podido actualizar rentas");
            error.setVisible(true);
        }
    }
    
    void Separador(){
        ResultSet res = Controlador.ControladorCalendario.TraerFechasControlador();
        try{
            while(res.next()){
                StringTokenizer st = new StringTokenizer(res.getString("fechaDevolucion"), "/-");
                String año = st.nextToken();
                String mes = st.nextToken();
                String dia = st.nextToken();
                int diaI = Integer.parseInt(dia);
                int mesI = Integer.parseInt(mes);
                int añoI = Integer.parseInt(año);
                display(diaI, mesI, añoI);
            }
        }
        catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de Conexión");
            error.TAnotiError.setText("Ocurrio un error al establecer conexión con la base de datos, verifique su acceso a internet o que los servicios del servidor estén activos");
            error.setVisible(true);
        }
    }
    
    public static String leerFecha(JDateChooser j){
        Date fecha = calendario.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(fecha);
    }
    
    public static List<Date> ListaFRenta = new ArrayList<>();
    
    private static class HighlightEvaluator implements IDateEvaluator {
        void add(Date fechas){
            ListaFRenta.add(fechas);
        }
        
        public boolean isSpecial(Date date) {
            return ListaFRenta.contains(date);
        }

        public Color getSpecialForegroundColor() {
            return Color.WHITE.darker();
        }


        public Color getSpecialBackroundColor() {
            return Morado;
        }

        public String getSpecialTooltip() {
            return "Fecha de devolución";
        }

        public boolean isInvalid(Date date) {
            return false;
        }

        public Color getInvalidForegroundColor() {
            return null;
        }

        public Color getInvalidBackroundColor() {
            return Color.PINK;
        }

        public String getInvalidTooltip() {
            return null;
        }
    }
    
    void display(int dia, int mes, int año) {
        HighlightEvaluator evaluator = new HighlightEvaluator();
        evaluator.add(CrearFecha(dia, mes, año));
        calendario.getDayChooser().addDateEvaluator(evaluator);
    }

    private Date CrearFecha(int dia, int mes, int año) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, año);
        c.set(Calendar.MONTH, mes-1);
        c.set(Calendar.DAY_OF_MONTH, dia);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return (c.getTime());
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
        jPanel2 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        calendario = new com.toedter.calendar.JCalendar();
        dtFechaInicio = new com.toedter.calendar.JDateChooser();
        dtFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        CmbEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 222, 212));

        jPanel2.setBackground(new java.awt.Color(14, 13, 21));
        jPanel2.setPreferredSize(new java.awt.Dimension(144, 44));

        lblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Calendario");
        lblTitulo.setMaximumSize(new java.awt.Dimension(100, 100));
        lblTitulo.setMinimumSize(new java.awt.Dimension(690, 79));
        lblTitulo.setPreferredSize(new java.awt.Dimension(144, 44));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(684, 684, 684))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        calendario.setBackground(new java.awt.Color(220, 49, 72));
        calendario.setForeground(new java.awt.Color(46, 14, 54));
        calendario.setToolTipText("");
        calendario.setDecorationBackgroundColor(new java.awt.Color(220, 49, 72));
        calendario.setDecorationBordersVisible(true);
        calendario.setNullDateButtonText("");
        calendario.setSundayForeground(new java.awt.Color(255, 153, 51));
        calendario.setWeekOfYearVisible(false);
        calendario.setWeekdayForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Fecha Fin:");

        jLabel2.setText("Fecha Inicio:");

        btnImprimir.setText("Imprimir");
        btnImprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImprimirMouseClicked(evt);
            }
        });

        CmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendiente", "Cancelada", "Demorada" }));

        jLabel3.setText("Estado Renta:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, Short.MAX_VALUE)
            .addComponent(calendario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(dtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(dtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dtFechaInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dtFechaFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(CmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnImprimir))
                .addGap(18, 18, 18)
                .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    void CrearReporte(){
        ControladorVariables variables = new ControladorVariables();
        int Estado = CmbEstado.getSelectedIndex();
        JasperReport factura;
        Connection conexion = Conexion.Conexion();
        Map parametro = new HashMap();
        try{
            parametro.put("FechaInicio", fechaInicio);
            parametro.put("FechaFin", fechaFin);
            parametro.put("Estado", Estado+1);
            parametro.put("Nombre", variables.getNombreE());
            factura = JasperCompileManager.compileReport("src/Reportes/ReporteVentasPendientes.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(factura,parametro, conexion);
            JasperViewer.viewReport(jp, false);
        }catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("Error al crear Reporte");
            error.setVisible(true);
        }
    }
    
    private void btnImprimirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImprimirMouseClicked
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
    }//GEN-LAST:event_btnImprimirMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbEstado;
    private javax.swing.JButton btnImprimir;
    private static com.toedter.calendar.JCalendar calendario;
    private com.toedter.calendar.JDateChooser dtFechaFin;
    private com.toedter.calendar.JDateChooser dtFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
