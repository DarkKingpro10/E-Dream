/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorGraficas;
import Controlador.ControladorVariables;
import java.awt.Color;
import java.awt.Graphics;
import Recursos_Tipografias.Fuentes;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Oliver
 */
public class FrmGraficasEmpleados extends javax.swing.JPanel {

    //Objeto de mensajes de error
    FrmNotiError NError = new FrmNotiError();
    //Formato para enviar las fechas como parametro
    SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd"); //Se estable el formato de fehca
    //Se crea al objeto del controlador 
    ControladorGraficas controlador = new ControladorGraficas();
    //Se crea la lista que se llenará con los datos del empleado
    ArrayList<Object[]> DatosEmpleados = new ArrayList();
    //Vector que tendrá los 10 empleados a mostrar
    double[] ListaEmpleados = new double[10];
    //double[] ListaEmpleados = new double[]{500, 520, 360, 254, 41, 25, 254, 58, 254, 2500};
    //Vector que almacena el porcentaje
    int[] Grafica = new int[11];
    //Vector que almacena el porcentaje
    int[] GraficaNiveles = new int[11];
    //Se obtiene el valor total la cantidad de Venta
    double totalVenta;
    //posición del vector
    int posicion = 0;
    //Se obtiene la fecha actual
    //Se selecciona la fecha de ahora
    LocalDate Fecha = LocalDate.now();
    //Fecha de hoy
    java.sql.Date hoy = java.sql.Date.valueOf(LocalDate.now());
    String FechaHoy = "'" + hoy + "'";
    //Variable para buscar el total
    String buscar = "";
    
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
    public FrmGraficasEmpleados() {
        initComponents();
        Colores();
        //Asignaciones de fuentes
        Fuentes tipoFuente = new Fuentes();
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 1, 60));
        cmbTipo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbFrecuencia.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        repaint();
        llenarTabla();
    }

    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                PanelContenedor.setBackground(Piel);
                PanelTitulo.setBackground(Oscuro);
                jPanel2.setBackground(Piel);
                break;
            case 2://Claro
                PanelContenedor.setBackground(Color.white);
                PanelTitulo.setBackground(AzulOscuro);
                jPanel2.setBackground(Color.white);
                break;
            default:
                PanelContenedor.setBackground(Piel);
                PanelTitulo.setBackground(Oscuro);
                jPanel2.setBackground(Piel);
                break;
        }        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        PanelContenedor = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableEmpleados = new javax.swing.JTable();
        cmbTipo = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        cmbFrecuencia = new javax.swing.JComboBox<>();
        dcrFechaInicio = new com.toedter.calendar.JDateChooser();
        dcrFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1090, 620));

        PanelTitulo.setBackground(new java.awt.Color(14, 13, 21));
        PanelTitulo.setLayout(null);

        lblTitulo.setBackground(new java.awt.Color(14, 13, 21));
        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Empleados con más ventas");
        PanelTitulo.add(lblTitulo);
        lblTitulo.setBounds(120, 0, 930, 48);

        PanelContenedor.setBackground(new java.awt.Color(255, 222, 212));
        PanelContenedor.setPreferredSize(new java.awt.Dimension(300, 587));
        PanelContenedor.setRequestFocusEnabled(false);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(400, 300));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 300));

        TableEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Cantidad", "Validación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableEmpleados.setMinimumSize(new java.awt.Dimension(175, 100));
        TableEmpleados.setPreferredSize(new java.awt.Dimension(175, 200));
        TableEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableEmpleados);
        if (TableEmpleados.getColumnModel().getColumnCount() > 0) {
            TableEmpleados.getColumnModel().getColumn(0).setPreferredWidth(20);
            TableEmpleados.getColumnModel().getColumn(1).setPreferredWidth(80);
        }

        cmbTipo.setBackground(new java.awt.Color(164, 188, 188));
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Venta", "Renta" }));
        cmbTipo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbTipoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 222, 212));

        cmbFrecuencia.setBackground(new java.awt.Color(164, 188, 188));
        cmbFrecuencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "El inicio de todo", "Ayer", "Últimos 3 días", "Última semana", "Última quincena", "Último mes", "Último año", "Personalizado" }));
        cmbFrecuencia.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbFrecuenciaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        dcrFechaInicio.setBackground(new java.awt.Color(164, 188, 188));
        dcrFechaInicio.setEnabled(false);
        dcrFechaInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcrFechaInicioPropertyChange(evt);
            }
        });

        dcrFechaFin.setBackground(new java.awt.Color(164, 188, 188));
        dcrFechaFin.setEnabled(false);
        dcrFechaFin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcrFechaFinPropertyChange(evt);
            }
        });

        jLabel1.setText("Fecha de inicio");

        jLabel2.setText("Fecha de fin");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcrFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcrFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcrFechaInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcrFechaFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout PanelContenedorLayout = new javax.swing.GroupLayout(PanelContenedor);
        PanelContenedor.setLayout(PanelContenedorLayout);
        PanelContenedorLayout.setHorizontalGroup(
            PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedorLayout.createSequentialGroup()
                .addContainerGap(644, Short.MAX_VALUE)
                .addGroup(PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelContenedorLayout.createSequentialGroup()
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(229, 229, 229))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelContenedorLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelContenedorLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))))
        );
        PanelContenedorLayout.setVerticalGroup(
            PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelContenedorLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PanelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    //Método que asigna los grados de cada empleados

    public void grafica() {
        /*Se divide la cantidad de cada empleado entre el total de todas las ventas
        la división dará un número decimal, que se multiplicará por el total de grados
        de un circulo (360°), ese resultado se aproximará hacía arriba y lo devolverá como 
        un entero
         */
        Grafica[0] = (int) Math.ceil((360 * (ListaEmpleados[0] / totalVenta)));
        Grafica[1] = (int) Math.ceil((360 * (ListaEmpleados[1] / totalVenta)));
        Grafica[2] = (int) Math.ceil((360 * (ListaEmpleados[2] / totalVenta)));
        Grafica[3] = (int) Math.ceil((360 * (ListaEmpleados[3] / totalVenta)));
        Grafica[4] = (int) Math.ceil((360 * (ListaEmpleados[4] / totalVenta)));
        Grafica[5] = (int) Math.ceil((360 * (ListaEmpleados[5] / totalVenta)));
        Grafica[6] = (int) Math.ceil((360 * (ListaEmpleados[6] / totalVenta)));
        Grafica[7] = (int) Math.ceil((360 * (ListaEmpleados[7] / totalVenta)));
        Grafica[8] = (int) Math.ceil((360 * (ListaEmpleados[8] / totalVenta)));
        Grafica[9] = (int) Math.ceil((360 * (ListaEmpleados[9] / totalVenta)));
        Grafica[10] = GraficaOtros();

    }

    //Método que guarda los puntos de cada posición
    public void graficaNiveles() {
        grafica(); //Ejecuta el método de asignación de grados
        /*Va guardando la suma de los porcentajes para guardar
        los puntos de referencia
         */
        GraficaNiveles[0] = 0;
        GraficaNiveles[1] = Grafica[0];
        GraficaNiveles[2] = GraficaNiveles[1] + Grafica[1];
        GraficaNiveles[3] = GraficaNiveles[2] + Grafica[2];
        GraficaNiveles[4] = GraficaNiveles[3] + Grafica[3];
        GraficaNiveles[5] = GraficaNiveles[4] + Grafica[4];
        GraficaNiveles[6] = GraficaNiveles[5] + Grafica[5];
        GraficaNiveles[7] = GraficaNiveles[6] + Grafica[6];
        GraficaNiveles[8] = GraficaNiveles[7] + Grafica[7];
        GraficaNiveles[9] = GraficaNiveles[8] + Grafica[8];
        GraficaNiveles[10] = GraficaNiveles[9] + Grafica[9];
    }

    //Método que grafica la cantidad residual de porcentajes
    public int GraficaOtros() {
        int total = 360; //Selecciona la cantidad total de grados
        double Cantidad = 0; //Suma de todas las cantidades
        double porcentaje = 0; //Porcentaje que se le restará
        //Se van sumando las cantidades posición por posición
        for (int i = 0; i < ListaEmpleados.length; i++) {
            //Suma la cantidad
            Cantidad = Cantidad + ListaEmpleados[i];
        }
        //Divide el la cantidad total menos el total de la venta
        porcentaje = Cantidad / totalVenta;
        //Guarda los grados residuales
        total = (int) Math.ceil(total - (total * porcentaje));
        //Devuelve la cantidad total
        return total;

    }

    public void llenarTabla() {
        //Modelo que llenará la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        //Se agregan las columnas
        modelo.setColumnIdentifiers(new Object[]{"Nombre", "Cantidad", "Validación"});
        //Selecciona los empleados dependiendo de la opció 
        int seleccion = cmbTipo.getSelectedIndex();
        //Se instancia los vectores que guardaran los datos
        ListaEmpleados = new double[10];
        String[] Tipo = new String[3];
        String[] Tipo1 = new String[3];
        try {
            //Se verifica si se ha seleccionado la opción de personalizada
            if (cmbFrecuencia.getSelectedIndex() == 7) {
                //Se habilita los JDateChooser
                dcrFechaFin.setEnabled(true);
                dcrFechaInicio.setEnabled(true);
                //Se verifican si están vacíos
                if (!dcrFechaFin.getDate().toString().isEmpty() && !dcrFechaInicio.getDate().toString().isEmpty()) {
                    //Guardan ambas fechas en Date
                    String Date1 = new SimpleDateFormat("yyyy-MM-dd").format(dcrFechaInicio.getDate());
                    String Date2 = new SimpleDateFormat("yyyy-MM-dd").format(dcrFechaFin.getDate());
                    //Se convierten en fechas de SQL
                    java.sql.Date Fecha1 = java.sql.Date.valueOf(Date1);
                    java.sql.Date Fecha2 = java.sql.Date.valueOf(Date2);
                    //Se verifica la modalidad, Venta o Renta
                    if (seleccion == 0) {
                        //Se guardan las modificaciones a la sentencia
                        Tipo[0] = "tbFacturaVenta";
                        Tipo[1] = "MontoTotalV";
                        //Agrega a la sentencia el rango de fechas
                        Tipo[2] = " AND fechaFacturaVenta BETWEEN '" + Fecha1 + "' AND '" + Fecha2 + "'";
                        //Se guardan las modificaciones a la sentencia
                        Tipo1[0] = "tbFacturaVenta";
                        Tipo1[1] = "MontoTotalV";
                        //Agrega a la sentencia el rango de fechas
                        Tipo1[2] = " WHERE fechaFacturaVenta BETWEEN '" + Fecha1 + "' AND '" + Fecha2 + "'";
                    } else if (seleccion == 1) {
                        //Se guardan las modificaciones a la sentencia
                        Tipo[0] = "tbFacturaRenta";
                        Tipo[1] = "MontoTotalR";
                        //Agrega a la sentencia el rango de fechas
                        Tipo[2] = " AND fechaFacturaRenta BETWEEN '" + Fecha1 + "' AND '" + Fecha2 + "'";
                        //Se guardan las modificaciones a la sentencia
                        Tipo1[0] = "tbFacturaRenta";
                        Tipo1[1] = "MontoTotalR";
                        //Agrega a la sentencia el rango de fechas
                        Tipo1[2] = " WHERE fechaFacturaRenta BETWEEN '" + Fecha1 + "' AND '" + Fecha2 + "'";
                    }
                    //Se buscan los datos de los empleados con las modificaciones en la sentencia
                    DatosEmpleados = controlador.Empleados(Tipo);
                    //Se obtiene el valor total la cantidad de Venta
                    totalVenta = controlador.cantidadTotalVenta(Tipo1);
                }
                //Si no esta en la modalidad de personalizada se escoge los rangos predeterminados
            } else {
                //Se verifica la modalidad, Venta o Renta
                if (seleccion == 0) {
                    //Se guardan las modificaciones a la sentencia
                    Tipo[0] = "tbFacturaVenta";
                    Tipo[1] = "MontoTotalV";
                    //Se agrega los rangos predeterminados
                    Tipo[2] = eleccion(cmbFrecuencia.getSelectedIndex());
                    //Se guardan las modificaciones a la sentencia
                    Tipo1[0] = "tbFacturaVenta";
                    Tipo1[1] = "MontoTotalV";
                    //Se agrega los rangos predeterminados
                    Tipo1[2] = buscar;
                } else if (seleccion == 1) {
                    //Se guardan las modificaciones a la sentencia
                    Tipo[0] = "tbFacturaRenta";
                    Tipo[1] = "MontoTotalR";
                    //Se agrega los rangos predeterminados
                    Tipo[2] = eleccionRenta(cmbFrecuencia.getSelectedIndex());
                    //Se guardan las modificaciones a la sentencia
                    Tipo1[0] = "tbFacturaRenta";
                    Tipo1[1] = "MontoTotalR";
                    //Se agrega los rangos predeterminados
                    Tipo1[2] = buscar;
                }
            }
            //Se buscan los datos de los empleados con las modificaciones en la sentencia
            DatosEmpleados = controlador.Empleados(Tipo);
            //Se obtiene el valor total la cantidad de Venta
            totalVenta = controlador.cantidadTotalVenta(Tipo1);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Se guardan los datos en la tabla
        for (int i = 0; i < DatosEmpleados.size(); i++) {
            Object[] datos = DatosEmpleados.get(i);
            //Se agrega la fila a la tabla
            modelo.addRow(datos);
        }
        //Se repinta la gráfica
        graficaNiveles();
        repaint();

        //Se agrega el modelo a la tabla
        TableEmpleados.setModel(modelo);
        //Se agrega la columna de verificaciones
        Check(2, TableEmpleados);
    }

    //Método que cambia la columna a la de validación
    public void Check(int columna, JTable tabla) {
        //Se crear el modelo de la columan
        TableColumn col = tabla.getColumnModel().getColumn(columna);
        //Se crea la edición de la validación
        col.setCellEditor(tabla.getDefaultEditor(Boolean.class));
        //Se regrafican la tabla
        col.setCellRenderer(tabla.getDefaultRenderer(Boolean.class));
    }
    private void TableEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEmpleadosMouseClicked

        //Se selecciona la posición de la tabla seleccionada
        int seleccion = TableEmpleados.getSelectedRow();
        //Se instancia un contador
        int contador = 0;
        int posic = 0;
        //Validación booleana
        boolean val = Boolean.parseBoolean(String.valueOf(TableEmpleados.getValueAt(seleccion, 2)));
        //Se verifican si encuentra la cantidad en el vector graficado
        boolean encontrado = true;
        //Valor vacío para agregar
        boolean vacio = true;
        try {
            //Se obtiene la cantidad de la tabla
            double cantidad = (double) TableEmpleados.getValueAt(seleccion, 1);
            //Se verifica si ha sido seleccionado o no
            if (val) {
                while (posic <= 11 && vacio) {
                    if (ListaEmpleados[posic] == 0.0) {
                        //Si lo encuentra lo agrega
                        ListaEmpleados[posic] = cantidad;
                        //Se va sumando la posición global

                        vacio = false;
                    } else if (posic == 11) {
                        ListaEmpleados[posicion] = cantidad;
                        posicion++;
                        if (posicion < 10) {
                            posicion = 0;
                        }
                        vacio = true;
                    }
                    posic++;
                }
                //Se busca el dato
                if (posicion < 10) {

                } else {
                    //Si llega al 9, entonces se reinicia
                    System.out.print(ListaEmpleados[0]);
                    posicion = 0;
                    //Se agrega la cantidad a la lista
                    ListaEmpleados[posicion] = cantidad;
                }
                //Se repinta la gráfica
                graficaNiveles();
                repaint();

                //Si no esta seleccionado pero si se toca
            } else if (val == false) {
                //Se busca en el vector de cantidades gráficadad
                while (contador < ListaEmpleados.length && encontrado) {
                    //Se verifica si la cantidad es igual al dato en la tabla
                    if (ListaEmpleados[contador] == Double.parseDouble(String.valueOf(TableEmpleados.getValueAt(seleccion, 1)))) {

                        System.out.println(contador + " cantidad> " + ListaEmpleados[contador]);
                        //Se reinicia a 0
                        ListaEmpleados[contador] = 0.0;
                        //Se cambia a que se ha encontrado el dato
                        encontrado = false;
                        //Se resignan los grados
                        graficaNiveles();
                        //Se repinta la gráfica
                        repaint();
                    } else {
                        //Se suma al contador
                        contador++;
                    }
                }

            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_TableEmpleadosMouseClicked

    private void cmbTipoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbTipoPopupMenuWillBecomeInvisible

        llenarTabla();
    }//GEN-LAST:event_cmbTipoPopupMenuWillBecomeInvisible

    private void cmbFrecuenciaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbFrecuenciaPopupMenuWillBecomeInvisible
        llenarTabla();


    }//GEN-LAST:event_cmbFrecuenciaPopupMenuWillBecomeInvisible

    private void dcrFechaInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcrFechaInicioPropertyChange
        if (validacionFecha()) {
            llenarTabla();
        }
    }//GEN-LAST:event_dcrFechaInicioPropertyChange

    private void dcrFechaFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcrFechaFinPropertyChange
        if (validacionFecha()) {
            llenarTabla();
        }
    }//GEN-LAST:event_dcrFechaFinPropertyChange
    //Método que obtiene la selecciona el rango predeterminado - Venta
    public String eleccion(int selc) {
        //Extra de la sentencia
        String extra = "";
        try {
            //Se crea la variable globales
            String FechaSeleccionada;
            Calendar calendario = Calendar.getInstance();
            java.sql.Date Seleccion;
            //Se escoge la opción 
            switch (selc) {
                case 0:
                    //Si es 0 no se hace nada
                    break;
                case 1:
                    //Si es 1, se busca desde la fecha de ayer
                    calendario.add(Calendar.DATE, -1);
                    String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(yesterday);
                    FechaSeleccionada = "'" + Seleccion + "'";
                    extra = "AND fechaFacturaVenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    buscar = "WHERE fechaFacturaVenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 2:
                    //Si es 2, Se busca desde hace tres días
                    calendario.add(Calendar.DATE, -3);
                    String Days3 = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Days3);
                    FechaSeleccionada = "'" + Seleccion + "'";
                    extra = "AND fechaFacturaVenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    //Se le asigna la busqueda personalizada al total
                    buscar = "WHERE fechaFacturaVenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 3:
                    //Si es 3, Se busca desde hace 1 semana
                    calendario.add(Calendar.DATE, -7);
                    String Days7 = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Days7);
                    FechaSeleccionada = "'" + Seleccion + "'";
                    extra = "AND fechaFacturaVenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    //Se le asigna la busqueda personalizada al total
                    buscar = "WHERE fechaFacturaVenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 4:
                    //Si es 4, Se busca desde hace 2 semana
                    calendario.add(Calendar.DATE, -14);
                    String Days14 = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Days14);
                    FechaSeleccionada = "'" + Seleccion + "'";
                    extra = "AND fechaFacturaVenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    //Se le asigna la busqueda personalizada al total
                    buscar = "WHERE fechaFacturaVenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 5:
                    //Si es 5, Se busca desde hace 1 mes
                    calendario.add(Calendar.MONTH, -1);
                    String Month = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Month);
                    FechaSeleccionada = "'" + Seleccion + "'";
                    extra = "AND fechaFacturaVenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    //Se le asigna la busqueda personalizada al total
                    buscar = "WHERE fechaFacturaVenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 6:
                    //Si es 6, Se busca desde hace 1 año
                    calendario.add(Calendar.YEAR, -1);
                    String Year = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Year);
                    FechaSeleccionada = "'" + Seleccion + "'";
                    extra = "AND fechaFacturaVenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    //Se le asigna la busqueda personalizada al total
                    buscar = "WHERE fechaFacturaVenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 7:
                    //Si es 7, se habilitan los JDateChooser para la modalidas personalizada
                    dcrFechaInicio.setEnabled(true);
                    dcrFechaFin.setEnabled(true);

            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Se retorna la sentencia
        return extra;
    }

    //Método que obtiene la selecciona el rango predeterminado - Renta
    public String eleccionRenta(int selc) {
        //Extra de la sentencia
        String extra = "";
        try {
            //Se crea las variable globales
            String FechaSeleccionada;
            Calendar calendario = Calendar.getInstance();
            java.sql.Date Seleccion;
            //Se escoge la opción 
            switch (selc) {
                case 0:
                    //Si es 0 no se hace nada
                    break;
                case 1:
                    //Si es 1, se busca desde la fecha de ayer
                    calendario.add(Calendar.DATE, -1);
                    String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(yesterday);
                    FechaSeleccionada = "'" + Seleccion + "'";
                    extra = "AND fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    //Se le asigna la busqueda personalizada al total
                    buscar = "WHERE fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 2:
                    //Si es 2, Se busca desde hace tres días
                    calendario.add(Calendar.DATE, -3);
                    String Days3 = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Days3);
                    FechaSeleccionada = "'" + Seleccion + "'";
                    extra = "AND fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    //Se le asigna la busqueda personalizada al total
                    buscar = "WHERE fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 3:
                    //Si es 3, Se busca desde hace 1 semana
                    calendario.add(Calendar.DATE, -7);
                    String Days7 = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Days7);
                    FechaSeleccionada = "'" + Seleccion + "'";
                    extra = "AND fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    //Se le asigna la busqueda personalizada al total
                    buscar = "WHERE fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 4:
                    //Si es 4, Se busca desde hace 2 semana
                    calendario.add(Calendar.DATE, -14);
                    String Days14 = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Days14);
                    FechaSeleccionada = "'" + Seleccion + "'";
                    extra = "AND fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    //Se le asigna la busqueda personalizada al total
                    buscar = "WHERE fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 5:
                    //Si es 5, Se busca desde hace 1 mes
                    calendario.add(Calendar.MONTH, -1);
                    String Month = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Month);
                    FechaSeleccionada = "'" + Seleccion + "'";
                    extra = "AND fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    //Se le asigna la busqueda personalizada al total
                    buscar = "WHERE fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 6:
                    //Si es 6, Se busca desde hace 1 año
                    calendario.add(Calendar.YEAR, -1);
                    String Year = new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime());
                    Seleccion = java.sql.Date.valueOf(Year);
                    FechaSeleccionada = "'" + Seleccion + "'";
                    extra = "AND fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    //Se le asigna la busqueda personalizada al total
                    buscar = "WHERE fechaFacturaRenta BETWEEN " + FechaSeleccionada + " AND " + FechaHoy;
                    break;
                case 7:
                    //Si es 7, se habilitan los JDateChooser para la modalidas personalizada
                    dcrFechaInicio.setEnabled(true);
                    dcrFechaFin.setEnabled(true);

            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Se retorna la modificación de la sentencia
        return extra;
    }

    boolean validacionFecha() {
        try {
            if (dcrFechaInicio.getDate().after(obtenerman())) {
                //Se envia el error en dado caso no se puedan cargar
                NError.setVisible(true);
                NError.TAnotiError.setText("La fecha no puede ser mayor a la del día de hoy");
                NError.lblTituloError.setText("Fecha incorrecta");
                dcrFechaInicio.setDate(null);
                return false;
            } else if (dcrFechaFin.getDate().after(obtenerman())) {
                //Se envia el error en dado caso no se puedan cargar
                NError.setVisible(true);
                NError.TAnotiError.setText("La fecha no puede ser mayor a la del día de hoy");
                NError.lblTituloError.setText("Fecha incorrecta");
                dcrFechaFin.setDate(null);
                return false;
            } else if (dcrFechaFin.getDate().before(dcrFechaInicio.getDate())) {
                //Se envia el error en dado caso no se puedan cargar
                NError.setVisible(true);
                NError.TAnotiError.setText("La fecha del fin no puede ser menor a la de la fecha de inicio");
                NError.lblTituloError.setText("Fecha incorrecta");
                dcrFechaFin.setDate(null);
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    //M[etodo que obtiene la fecha de mañana
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
    private javax.swing.JTable TableEmpleados;
    private javax.swing.JComboBox<String> cmbFrecuencia;
    private javax.swing.JComboBox<String> cmbTipo;
    private com.toedter.calendar.JDateChooser dcrFechaFin;
    private com.toedter.calendar.JDateChooser dcrFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
    @Override
    //Método que pinta la gráfica
    public void paint(Graphics g) {
        super.paint(g);
        if (true) {
            //Se ejecutan los métodos para cargas 
            grafica();
            /*Son 10 datos máximos, y aquí se van gráficando 1 por 1
            se colocan la cantidad de grados de cada punto
            ,se coloca el punto desde dónde empezará los grados
            también los colores y tamaño de la gráfica total
             */
            g.setColor(new Color(192, 57, 43)); //Rojo oscuro
            g.fillArc(50, 100, 500, 500, GraficaNiveles[0], Grafica[0]);

            g.setColor(new Color(231, 76, 60)); //Rojo claro
            g.fillArc(50, 100, 500, 500, GraficaNiveles[1], Grafica[1]);

            g.setColor(new Color(241, 196, 15)); //Amarillo
            g.fillArc(50, 100, 500, 500, GraficaNiveles[2], Grafica[2]);

            g.setColor(new Color(142, 68, 173)); //Morado
            g.fillArc(50, 100, 500, 500, GraficaNiveles[3], Grafica[3]);

            g.setColor(new Color(41, 128, 185)); //Azul oscuro
            g.fillArc(50, 100, 500, 500, GraficaNiveles[4], Grafica[4]);

            g.setColor(new Color(19, 141, 117)); //Aqua oscuro
            g.fillArc(50, 100, 500, 500, GraficaNiveles[5], Grafica[5]);

            g.setColor(new Color(39, 174, 96)); //Verde oscuro
            g.fillArc(50, 100, 500, 500, GraficaNiveles[6], Grafica[6]);

            g.setColor(new Color(88, 214, 141)); //Verde claro
            g.fillArc(50, 100, 500, 500, GraficaNiveles[7], Grafica[7]);

            g.setColor(new Color(52, 152, 219)); //Azul claro 
            g.fillArc(50, 100, 500, 500, GraficaNiveles[8], Grafica[8]);

            g.setColor(new Color(0, 0, 0)); //Negro   
            g.fillArc(50, 100, 500, 500, GraficaNiveles[9], Grafica[9]);

            g.setColor(new Color(150, 152, 154)); //gris
            g.fillArc(50, 100, 500, 500, GraficaNiveles[10], Grafica[10]);
            g.fillRect(265, 60, 30, 30);
            //Explica lo que significa el gris
            g.setColor(new Color(0, 0, 0)); //Explicación gris
            g.drawString("Sin seleccionar", 300, 80);

        }
    }
}
