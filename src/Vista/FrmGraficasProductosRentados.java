/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorGraficas;
import Controlador.ControladorInventarioRenta;
import Controlador.ControladorVariables;
import Modelo.Conexion;
import Recursos_Tipografias.Fuentes;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
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
 * @author Oliver
 */
public class FrmGraficasProductosRentados extends javax.swing.JPanel {

    //Cramos el objeto del controlador
    ControladorGraficas controlador = new ControladorGraficas();
    //Objeto de mensajes de error
    FrmNotiError NError = new FrmNotiError();
    //Formato para enviar las fechas como parametro
    SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd"); //Se estable el formato de fecha
    //Se obtiene la fecha actual
    //Se selecciona la fecha de ahora
    LocalDate Fecha = LocalDate.now();
    java.sql.Date hoy = java.sql.Date.valueOf(LocalDate.now());
    //Fecha de hoy prepara para la sentencia
    String FechaHoy = "'" + hoy + "'";
    //Vector que guarda todas la cantidades de los productos vendidos
    int[] datos;
    //Se crean los parámetros que se usarán para el reporte
    static Map Parametros = new HashMap();

    
    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Morado = new Color(94,45,79);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);
    
    /**
     * Creates new form frmGraficas
     */
    public FrmGraficasProductosRentados() {
        initComponents();
        Colores();
        Filtro();
        //Fuentes para los componentes
        Fuentes tipoFuente = new Fuentes();
        dcrFin.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        dcrInicio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbCategoria.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 1, 50));
        btnImprimir.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        btnImprimir1.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        btnGrafica.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        lblCategoria.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        lblFechaInicio.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        lblFechaFin.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        PanelContenedor = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cmbCategoria = new javax.swing.JComboBox<>();
        dcrInicio = new com.toedter.calendar.JDateChooser();
        lblCategoria = new javax.swing.JLabel();
        lblFechaInicio = new javax.swing.JLabel();
        dcrFin = new com.toedter.calendar.JDateChooser();
        lblFechaFin = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        btnImprimir1 = new javax.swing.JButton();
        btnGrafica = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1090, 620));
        setPreferredSize(new java.awt.Dimension(1090, 620));

        PanelTitulo.setBackground(new java.awt.Color(14, 13, 21));

        lblTitulo.setBackground(new java.awt.Color(14, 13, 21));
        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Productos más rentados");

        javax.swing.GroupLayout PanelTituloLayout = new javax.swing.GroupLayout(PanelTitulo);
        PanelTitulo.setLayout(PanelTituloLayout);
        PanelTituloLayout.setHorizontalGroup(
            PanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTituloLayout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addGap(290, 290, 290))
        );
        PanelTituloLayout.setVerticalGroup(
            PanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        PanelContenedor.setBackground(new java.awt.Color(255, 222, 212));

        jPanel1.setBackground(new java.awt.Color(255, 222, 212));

        cmbCategoria.setBackground(new java.awt.Color(164, 188, 188));
        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "El inicio de todo", "Ayer", "Últimos 3 días", "Última semana", "Última quincena", "Último mes", "Último año", "Personalizado" }));
        cmbCategoria.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbCategoriaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        dcrInicio.setBackground(new java.awt.Color(164, 188, 188));
        dcrInicio.setEnabled(false);
        dcrInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcrInicioPropertyChange(evt);
            }
        });

        lblCategoria.setText("Categorias de busqueda");

        lblFechaInicio.setText("Fecha inicio");

        dcrFin.setBackground(new java.awt.Color(164, 188, 188));
        dcrFin.setEnabled(false);
        dcrFin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcrFinPropertyChange(evt);
            }
        });

        lblFechaFin.setText("Fecha fin");

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Imprimir 30px.png"))); // NOI18N
        btnImprimir.setText("Generar productos rentados");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnImprimir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Imprimir 30px.png"))); // NOI18N
        btnImprimir1.setText("Generar productos no rentados");
        btnImprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir1ActionPerformed(evt);
            }
        });

        btnGrafica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Imprimir 30px.png"))); // NOI18N
        btnGrafica.setText("Generar datos de gráfica");
        btnGrafica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dcrFin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcrInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaFin)
                            .addComponent(lblFechaInicio)
                            .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCategoria)
                            .addComponent(btnImprimir1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFechaInicio)
                .addGap(12, 12, 12)
                .addComponent(dcrInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblFechaFin)
                .addGap(18, 18, 18)
                .addComponent(dcrFin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnImprimir)
                .addGap(18, 18, 18)
                .addComponent(btnImprimir1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(btnGrafica))
        );

        javax.swing.GroupLayout PanelContenedorLayout = new javax.swing.GroupLayout(PanelContenedor);
        PanelContenedor.setLayout(PanelContenedorLayout);
        PanelContenedorLayout.setHorizontalGroup(
            PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelContenedorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        PanelContenedorLayout.setVerticalGroup(
            PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedorLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PanelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
    }// </editor-fold>//GEN-END:initComponents

    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                PanelContenedor.setBackground(Piel);
                PanelTitulo.setBackground(Oscuro);
                jPanel1.setBackground(Piel);
                break;
            case 2://Claro
                PanelContenedor.setBackground(Color.white);
                PanelTitulo.setBackground(AzulOscuro);
                jPanel1.setBackground(Color.white);
                break;
            default:
                PanelContenedor.setBackground(Piel);
                PanelTitulo.setBackground(Oscuro);
                jPanel1.setBackground(Piel);
                break;
        }        
    }
    
    private void cmbCategoriaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbCategoriaPopupMenuWillBecomeInvisible
        //Se ejecuta el método para fechas predeterminadas
        Filtro();
    }//GEN-LAST:event_cmbCategoriaPopupMenuWillBecomeInvisible

    private void dcrInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcrInicioPropertyChange
        //Se ejecuta el método para fechas personalizadas
        personalizada();
    }//GEN-LAST:event_dcrInicioPropertyChange

    private void dcrFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcrFinPropertyChange
        //Se ejecuta el método para fechas personalizadas
        personalizada();
    }//GEN-LAST:event_dcrFinPropertyChange

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        //Ya que necesitamos parametros de fecha, invocamos un form que los obtenga
        FrmRangoFechasJ fechas = new FrmRangoFechasJ();
        fechas.setVisible(true);
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir1ActionPerformed
        ControladorInventarioRenta controller = new ControladorInventarioRenta();
        //Ejecutamos el metodo que genere el Reporte
        controller.generarRentaNo();
    }//GEN-LAST:event_btnImprimir1ActionPerformed

    private void btnGraficaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficaActionPerformed
        CrearFactura();
    }//GEN-LAST:event_btnGraficaActionPerformed

    void CrearFactura() {
        JasperReport factura;
        Connection conexion = Conexion.Conexion();
        try {
            factura = JasperCompileManager.compileReport("src/Reportes/ReporteProductosRentados.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(factura, Parametros, conexion);
            JasperViewer ventana = new JasperViewer(jp, false);
            ventana.setTitle("Ticket de renta");
            ventana.setVisible(true);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    //Método que recoge los parametros de busqueda y regrafica   
    public void Filtro() {
        //Opción seleccionada de las categorias de busqueda
        int eleccion = cmbCategoria.getSelectedIndex();
        //Extra de la sentencia, se llena solo si hay parametros de busqueda
        String extra = "";
        try {
            //Para las busquedas predeterminadas se usa la variable del inicio de la busqueda
            String FechaSeleccionada;
            Calendar calendario = Calendar.getInstance();
            java.sql.Date Seleccion;
            //Se escoge la opción 
            switch (eleccion) {
                case 0:
                    //Si es 0 solo se agrega una fecha predeterminada
                    llenarParametros("1950-01-01", hoy.toString());
                    break;
                case 1:
                    //Si es 1, se busca desde la fecha de ayer
                    calendario.add(Calendar.DATE, -1);
                    String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(yesterday);
                    //Se prepara la fecha para ser enviada
                    FechaSeleccionada = "'" + Seleccion + "'";
                    //Se llenan los posibles parametros para el reporte
                    llenarParametros(Seleccion.toString(), hoy.toString());

                    //Se llena la busqueda
                    extra = "AND fv.fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 2:
                    //Si es 2, Se busca desde hace tres días
                    calendario.add(Calendar.DATE, -3);
                    String Days3 = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Days3);
                    //Se prepara la fecha para ser enviada
                    FechaSeleccionada = "'" + Seleccion + "'";
                    //Se llenan los posibles parametros para el reporte
                    llenarParametros(Seleccion.toString(), hoy.toString());

                    //Se llena la busqueda
                    extra = "AND fv.fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 3:
                    //Si es 3, Se busca desde hace 1 semana
                    calendario.add(Calendar.DATE, -7);
                    String Days7 = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Days7);
                    //Se prepara la fecha para ser enviada
                    FechaSeleccionada = "'" + Seleccion + "'";
                    //Se llenan los posibles parametros para el reporte
                    llenarParametros(Seleccion.toString(), hoy.toString());

                    //Se llena la busqueda
                    extra = "AND fv.fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 4:
                    //Si es 4, Se busca desde hace 2 semana
                    calendario.add(Calendar.DATE, -14);
                    String Days14 = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Days14);
                    //Se prepara la fecha para ser enviada
                    FechaSeleccionada = "'" + Seleccion + "'";
                    //Se llenan los posibles parametros para el reporte
                    llenarParametros(Seleccion.toString(), hoy.toString());

                    //Se llena la busqueda
                    extra = "AND fv.fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 5:
                    //Si es 5, Se busca desde hace 1 mes
                    calendario.add(Calendar.MONTH, -1);
                    String Month = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Month);
                    //Se prepara la fecha para ser enviada
                    FechaSeleccionada = "'" + Seleccion + "'";
                    //Se llenan los posibles parametros para el reporte
                    llenarParametros(Seleccion.toString(), hoy.toString());
                    //Se llena la busqueda
                    extra = "AND fv.fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 6:
                    //Si es 6, Se busca desde hace 1 año
                    calendario.add(Calendar.YEAR, -1);
                    String Year = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Year);
                    //Se prepara la fecha para ser enviada
                    FechaSeleccionada = "'" + Seleccion + "'";
                    //Se llenan los posibles parametros para el reporte
                    llenarParametros(Seleccion.toString(), hoy.toString());
                    //Se llena la busqueda
                    extra = "AND fv.fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 7:
                    //Si es 7, se habilitan los JDateChooser para la modalidas personalizada
                    dcrInicio.setEnabled(true);
                    dcrFin.setEnabled(true);
                    break;
            }
            System.out.println("Parametros Filtro" + Parametros);
            //Se cargan los datos encontrados
            datos = controlador.DatosGraficaRenta(extra);
            //Se ejecuta el método para graficar
            repaint();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //Método que ejecuta las fechas personalizadas
    void personalizada() {
        try {
            //Se crea la extra sentencia
            String extra;
            //Se validan las fecha
            if (validacionFecha()) {
                //Se convierten a una fecha simple
                String Fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(dcrInicio.getDate());
                String Fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(dcrFin.getDate());
                if (Fecha1.isEmpty() || Fecha2.isEmpty()) {
                    //Si están vacíos entonces no se agrega nada
                    extra = "";
                } else {
                    //Se llenan los posibles parametros para el reporte
                    llenarParametros(Fecha1, Fecha2);
                    //Se prepara la sentencia
                    extra = "AND fv.fechaFacturaRenta BETWEEN '" + Fecha1 + "' AND '" + Fecha2 + "'";
                }

                //Se recogen los datos
                datos = controlador.DatosGraficaRenta(extra);
                //Se vuelve a graficar
                repaint();
            }
        } catch (Exception e) {
        }
    }

    //Método que se encarga de llenar los parametros para la factura
    void llenarParametros(String Fecha1, String Fecha2) {
        Parametros.clear();
        //Se llena el parámetro con el nombre del empleado
        Parametros.put("IDEmpleado", Controlador.ControladorVariables.nombreE);
        Parametros.put("FechaFin", Fecha2);
        Parametros.put("FechaInicio", Fecha1);
        System.out.println("COmprabante:" + Parametros);
    }

    boolean validacionFecha() {
        try {
            if (dcrInicio.getDate().after(obtenerman())) {
                //Se envia el error en dado caso no se puedan cargar
                NError.setVisible(true);
                NError.TAnotiError.setText("La fecha no puede ser mayor a la del día de hoy");
                NError.lblTituloError.setText("Fecha incorrecta");
                dcrInicio.setDate(null);
                return false;
            } else if (dcrFin.getDate().after(obtenerman())) {
                //Se envia el error en dado caso no se puedan cargar
                NError.setVisible(true);
                NError.TAnotiError.setText("La fecha no puede ser mayor a la del día de hoy");
                NError.lblTituloError.setText("Fecha incorrecta");
                dcrFin.setDate(null);
                return false;
            } else if (dcrFin.getDate().before(dcrInicio.getDate())) {
                //Se envia el error en dado caso no se puedan cargar
                NError.setVisible(true);
                NError.TAnotiError.setText("La fecha del fin no puede ser menor a la de la fecha de inicio");
                NError.lblTituloError.setText("Fecha incorrecta");
                dcrFin.setDate(null);
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    Date obtenerman() {
        Date man;
        //Se obtiene la fecha de mañana
        Calendar m = Calendar.getInstance();
        m.add(Calendar.DATE, 0);
        man = Date.from(m.toInstant());
        return man;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelContenedor;
    private javax.swing.JPanel PanelTitulo;
    private javax.swing.JButton btnGrafica;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnImprimir1;
    private javax.swing.JComboBox<String> cmbCategoria;
    private com.toedter.calendar.JDateChooser dcrFin;
    private com.toedter.calendar.JDateChooser dcrInicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
      public void paint(Graphics g) {
        super.paint(g);
        //Se guardan los datos del vector en variable globales
        double Decoracion = datos[0];
        double accesorio = datos[1];
        double sonido = datos[2];
        double visual = datos[3];
        double tematicos = datos[4];
        //Se ordena el vector para obtener el valor más alto
        Arrays.sort(datos);
        //Se obtiene el valor máximo que será un 10% más grande que el verdadero máximo encontrado
        //Fines esteticos
        int Nivelmaximo = (int) (datos[4] * 0.10) + datos[4];
        //Se calculan el número que tendrá cada nivel de la gráfica
        //lineas negras
        int Nivel9 = (int) ((int) Nivelmaximo - (Nivelmaximo * 0.1));
        int Nivel8 = (int) ((int) Nivelmaximo - (Nivelmaximo * 0.2));
        int Nivel7 = (int) ((int) Nivelmaximo - (Nivelmaximo * 0.3));
        int Nivel6 = (int) ((int) Nivelmaximo - (Nivelmaximo * 0.4));
        int Nivel5 = (int) ((int) Nivelmaximo - (Nivelmaximo * 0.5));
        int Nivel4 = (int) ((int) Nivelmaximo - (Nivelmaximo * 0.6));
        int Nivel3 = (int) ((int) Nivelmaximo - (Nivelmaximo * 0.7));
        int Nivel2 = (int) ((int) Nivelmaximo - (Nivelmaximo * 0.8));
        int Nivel1 = (int) ((int) Nivelmaximo - (Nivelmaximo * 0.9));
        int Nivel0 = (int) ((int) Nivelmaximo - (Nivelmaximo * 1));

        //Se obtiene la cantidad de pixeles que la barra tendrá en la gráfica
        //No pueden superar el nivel máximo
        //Primero se dividen y luego de multiplican por el máximo de pixeles
        //establecidos por la gráfica        
        double GDecoracion = (Decoracion / Nivelmaximo) * 800;
        double Gaccesorio = (accesorio / Nivelmaximo) * 800;
        double Gsonido = (sonido / Nivelmaximo) * 800;
        double Gvisual = (visual / Nivelmaximo) * 800;
        double Gtematicos = (tematicos / Nivelmaximo) * 800;
        //Se convierten a números enteros
        int BDecoracion = (int) GDecoracion;
        int BAccesorio = (int) Gaccesorio;
        int BSonido = (int) Gsonido;
        int BVisual = (int) Gvisual;
        int BTematicos = (int) Gtematicos;
        //Se gráficas las barras
        g.setColor(new Color(0, 0, 0)); //negro
        g.fillRect(20, 80, 800, 5);
        g.fillRect(20, 75, 5, 400);
        g.fillRect(100, 75, 5, 400);
        g.fillRect(180, 75, 5, 400);
        g.fillRect(260, 75, 5, 400);
        g.fillRect(340, 75, 5, 400);
        g.fillRect(420, 75, 5, 400);
        g.fillRect(500, 75, 5, 400);
        g.fillRect(580, 75, 5, 400);
        g.fillRect(660, 75, 5, 400);
        g.fillRect(740, 75, 5, 400);
        g.fillRect(820, 75, 5, 400);
        //Se gráfican los números bandera de las barras
        g.drawString(String.valueOf(Nivel0), 18, 68);
        g.drawString(String.valueOf(Nivel1), 98, 68);
        g.drawString(String.valueOf(Nivel2), 178, 68);
        g.drawString(String.valueOf(Nivel3), 258, 68);
        g.drawString(String.valueOf(Nivel4), 338, 68);
        g.drawString(String.valueOf(Nivel5), 418, 68);
        g.drawString(String.valueOf(Nivel6), 498, 68);
        g.drawString(String.valueOf(Nivel7), 578, 68);
        g.drawString(String.valueOf(Nivel8), 658, 68);
        g.drawString(String.valueOf(Nivel9), 738, 68);
        g.drawString(String.valueOf(Nivelmaximo), 818, 68);

        //Se gráfican la cantidad de productos de decoración
        g.setColor(new Color(220, 49, 72)); //Rojo
        g.fillRect(20, 160, BDecoracion, 30);
        g.fillRect(20, 530, 30, 30);
        g.drawString("Decoracion", 60, 550);

        //Se gráfican la cantidad de productos temáticos
        g.setColor(new Color(104, 43, 41)); //Morado super oscuro
        g.fillRect(20, 220, BTematicos, 30);
        g.fillRect(160, 530, 30, 30);
        g.drawString("Objetos tematicos", 200, 550);

        //Se gráfica la cantidad de productos audivisuales
        g.setColor(new Color(94, 45, 79)); //Morado normal
        g.fillRect(20, 280, BVisual, 30);
        g.fillRect(320, 530, 30, 30);
        g.drawString("Equipo audiovisual", 360, 550);

        //Se gráfica la cantidad de productos de sonido
        g.setColor(new Color(197, 0, 0)); //Rojo
        g.fillRect(20, 340, BSonido, 30);
        g.fillRect(490, 530, 30, 30);
        g.drawString("Equipo de sonido", 530, 550);

        //Se gráfica la cantidad de accesorios
        g.setColor(new Color(85, 85, 120)); //Azul oscuro
        g.fillRect(20, 400, BAccesorio, 30);
        g.fillRect(650, 530, 30, 30);
        g.drawString("Accesorios", 690, 550);

    }
}
