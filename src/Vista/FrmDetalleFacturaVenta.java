/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorCobroVenta;
import Controlador.ControladorDetalleVenta;
import Controlador.ControladorVariables;
import Controlador.Validaciones;
import Recursos_Tipografias.Fuentes;
import static Vista.FrmContenedor.PanelPrueba;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oliver
 */
public class FrmDetalleFacturaVenta extends javax.swing.JPanel {

    /**
     * Creates new form FrmDetalleFactura
     */
    //Creamos objeto del controlador de la clase validaciones
    Validaciones validaciones = new Validaciones();

    //Creamos objeto de controldor
    ControladorDetalleVenta obj = new ControladorDetalleVenta();

    //Se crean ArrayList globales para los codigos de los combobiox
    ArrayList<String> CargarIdProducto = new ArrayList<String>();
    ArrayList<Integer> CargarIdVenta = new ArrayList<Integer>();
    ArrayList<String> CodigoProducto = new ArrayList<String>();
    ArrayList<Integer> Cantidades = new ArrayList<Integer>();//Pra las cantidades del inventario
    DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.US);
    DecimalFormat Formato = new DecimalFormat("###,###.##", simbolos);//Formato de decimales 
    ArrayList<String> codigos;

    //Colores Modo Night
    Color Piel = new Color(255,222,212);
    Color Morado = new Color(94,45,79);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Celeste = new Color(61, 111, 137);
    
    public FrmDetalleFacturaVenta() {
        initComponents();
        Colores();
        cmbIdVenta.setVisible(false);
        
        Fuentes tipoFuente = new Fuentes();

        //Se le establece el tipo de fuente a los componentes
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 40));
        lblDetalle.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 25));
        lblCodigo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblProducto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblCantidad.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblCostoUnitario.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblCosto2.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        lblDescuento.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtCodigo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtCantidad.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        txtCostoUnitario.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        cmbProducto.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnIngresar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnEliminar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnRegresar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        btnFinalizar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));
        tbDetalleVenta.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 20));

        //Cargar el combobox de empresa
        CargarProducto();

        //Cargar combobox de id
        CargarIdVenta();

        //Asignamos lo que valdra el ArrayLits
        CargarIdProducto = obj.idProducto();
        CargarIdVenta = obj.idVenta();

        //Cargamos tabla
        mostrarDatos();

        //Se pone invisible el boton de finalizar
        btnEliminar.setVisible(false);
        //Se pone deshabilitado el boton de finalizar
        btnFinalizar.setEnabled(false);

        //Le asignamos el valor de los arraylist  el metodo de controlador donde conseguimos el id de cada combo box
        CodigoProducto = obj.CargaIDProductos();
        Cantidades = obj.CargarCantidades();
    }
    
    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                PanelDetalleFacturaVenta.setBackground(Piel);
                jPanel1.setBackground(Piel);
                jPanel3.setBackground(Piel);
                jPanel2.setBackground(Oscuro);
                jPanel4.setBackground(Morado);
                break;
            case 2://Claro
                PanelDetalleFacturaVenta.setBackground(Color.white);
                jPanel1.setBackground(Color.white);
                jPanel3.setBackground(Color.white);
                jPanel2.setBackground(AzulOscuro);
                jPanel4.setBackground(Celeste);
                break;
            default:
                PanelDetalleFacturaVenta.setBackground(Piel);
                jPanel1.setBackground(Piel);
                jPanel3.setBackground(Piel);
                jPanel2.setBackground(Oscuro);
                jPanel4.setBackground(Morado);
                break;
        }        
    }

    //Cargamos la tabla 
    public static void mostrarDatos() {
        //Creamos un defaul table model para guardar datos de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        //Le asignamos el metodo de Resultset de controlador  a Resultset "rs"
        ResultSet rs = Controlador.ControladorDetalleVenta.CargarTablaController();
        //Le asignamo el nombre de cada columna al modelo de la tabla
        modelo.setColumnIdentifiers(new Object[]{"Cantidad", "Descuento", "Costo Total", "Codigo Producto", "N.Factura"});
        try{
            while (rs.next()) {
                //Agregamos el dto correspondiente de la base de datos a cada columna
                modelo.addRow(new Object[]{rs.getInt("cantidad"), rs.getDouble("descuento"), rs.getDouble("costoTotalV"), rs.getString("codigoProducto"), rs.getInt("idFacturaVenta")});

            }
            //Le asignamos el modelo a la tabla de detalle venta
            tbDetalleVenta.setModel(modelo);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //Metodo para calcular porcentaje de descuento y costo
    public void CalcularPorcentaje() {
        ControladorCobroVenta ventaC = new ControladorCobroVenta();

        try {
            //Creamos variables
            double descuentoT;
            double Total;
            double descuento;
            double desc = Integer.parseInt(txtDescuentoP.getText());//Se obtiene el descuento del textfield
            double costo = Double.parseDouble(txtCostoUnitario.getText());//Se obtiene el costo uniatrio del textfield
            int cantidad = Integer.parseInt(txtCantidad.getText());//Se obtiene la cantidad del textfield 

            //Se operan los datos
            desc = (desc / 100);

            Total = (costo * cantidad);
            //Se muestra el costo con formato decimal
            txtCosto.setText(String.valueOf(Total));
            //  txtCosto.setText(Formato.format(Total));

            //Calculamos descuento
            descuento = Total * desc;
            descuentoT = Total - descuento;

            //Se envia el total al set de total pago
            ventaC.settotalPago(descuentoT);
            //Se envia el descuento al textfield de descuento
            txtDescuento.setText(String.valueOf(Formato.format(descuentoT)));
            System.out.println(String.valueOf("Total" + Total));
        } catch (Exception e) {
            System.out.println("No es posible cargar el total con descuento aplicado");
            System.out.println(e);
        }
    }

    //Cargar ComboBox empresa
    void CargarProducto() {
        //Creamos un defaultmodel llamado lista
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        //Le asignamos al Resultset el valor del metodo de resultset que se encuentra en controlador
        ResultSet res = Controlador.ControladorDetalleVenta.CargarProductoController();
        try {
            //Agregamos elemento default
            lista.addElement("Sin seleccionar");
            while (res.next()) {
                //Agregamos el nombre de producto como elemento a la lista
                lista.addElement(res.getString("nombreProducto"));
            }
        } catch (Exception e) {
            //Si ocurre un error mandamos a llamar al frm de errores
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de Conexión");
            error.TAnotiError.setText("Ocurrio un error al establecer conexión con la base de datos, verifique su acceso a internet o que los servicios del servidor estén activos");
            error.setVisible(true);
        }
        //Se agrega el modelo al combobox
        cmbProducto.setModel(lista);
    }

    //Cargar ComboBox id
    void CargarIdVenta() {
        //Creamos un defaultmodel llamado lista
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        //Le asignamos al Resultset el valor del metodo de resultset que se encuentra en controlador
        ResultSet res = Controlador.ControladorDetalleVenta.CargarIdVentaController();
        try {
            while (res.next()) {
                //Agregamos el id de venta como elemento a la lista
                lista.addElement(res.getString("idVenta"));
            }
        } catch (Exception e) {
            //Si ocurre un error mandamos a llamar al frm de errores
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error de Conexión");
            error.TAnotiError.setText("Ocurrio un error al establecer "
                    + "\n"
                    + "conexión con la base de datos, "
                    + "\n"
                    + "verifique su acceso a internet "
                    + "\n"
                    + "o que los servicios del servidor"
                    + "\n"
                    + " estén activos");
            error.setVisible(true);
        }
        //Se agrega el modelo al combobox
        cmbIdVenta.setModel(lista);
    }

    //Se crea metodo para limpiar campo
    void limpiarCampos() {
        txtCodigo.setText("");
        txtCantidad.setText("");
        txtCostoUnitario.setText("");
        txtDescuentoP.setText("");
        txtCosto.setText("00.00");
        txtDescuento.setText("00.00");
        cmbProducto.setSelectedIndex(0);
    }

    //Metodo para actualizar cantidad de inventario
    void RestaProductos() {

        //Creamos objeto de notificacion de campos vacios
        FrmNoti1 campo = new FrmNoti1();

        //Validamos que no esten campos sin llenar
        if (txtCantidad.getText().isEmpty() || txtCodigo.getText().isEmpty() || cmbProducto.getSelectedIndex() == 0 || txtDescuento.getText().isEmpty() || txtCosto.getText().isEmpty()) {
            //Mostramos notificacion
            campo.setVisible(true);
            campo.TAMensajeError.setText("Existen campos sin"
                    + "\n"
                    + " datos asignados");
            campo.lblTituloNoti1.setText("¡Campos vacíos!");
        } else {
            //Se crean variables
            int cantidadFinal;
            int CantidadP = Integer.parseInt(txtCantidad.getText());//Obtenemos cantidad pedida
            int CantidadR = obj.getCantidadI();//Obtenemos cantidad de inventario

            //Condicionmos si la cantidad de productos restantes en el inventario es menor  la cantidad pedida
            if (CantidadR < CantidadP) {
                //Se muestra mensaje de error
                FrmNotiError error = new FrmNotiError();
                error.lblTituloError.setText("Error de Cantidad");
                error.TAnotiError.setText("No hay suficientes recursos, "
                        + "\n"
                        + "elija una cantidad menor a "+CantidadR+"\ndel producto con nombre\n"+cmbProducto.getSelectedItem());
                error.setVisible(true);
            } else {
                //Si la cantidad restante es mayor que la cantidad pedida se restan
                cantidadFinal = CantidadR - CantidadP;

                //Se envia el resultado al set de cantidad total de controlador
                obj.setCantidadT(cantidadFinal);
                System.out.println(cantidadFinal);

                //Se ejecuta el metodo para actualizar el inventario
                ActualizarInventarioC();
                //Se insertan datos en la base de datos tabla venta
                InsertarDatosVenta();

            }
        }

    }

    //Actualizamos inventario
    void ActualizarInventarioC() {
        //Se obtiene la cantidad total de inventario de controlador
        obj.setCantidadI(obj.getCantidadT());
        //Se obtiene el codigo de producto
        obj.setCodigoProducto(txtCodigo.getText());

        //enviando guardar a  SQLServer
        if (obj.ActualizarInventarioController()) {
            System.out.println("Se actualizo");//Para confirmar
            // JOptionPane.showMessageDialog(this, "Datos actualizados");

            //Limpiamos campos
            //limpiar();
        } else {
            System.out.println("No se actualizo");
            //JOptionPane.showMessageDialog(this, "Datos no actualizados");
        }
    }

    //Metodo de insertar datos de detalle  venta
    public void InsertarDatosVenta() {
        //Creamos objeto de form de error
        FrmNotiError error = new FrmNotiError();

        //Se obtiene el id de venta 
        int idVenta = (Integer) cmbIdVenta.getSelectedIndex();

        //Se crea objeto de controlador 
        Controlador.ControladorDetalleVenta agregar = new Controlador.ControladorDetalleVenta();

        //Se le asigna a venta el id de venta guardao en el Arraylist de cargarIdVenta
        Integer venta = CargarIdVenta.get(idVenta);

        //enviando la información a la clase
        agregar.setCostoTotalV(Double.parseDouble(txtCosto.getText()));//Se obtiene el costo y se envia a controlador
        agregar.setDescuento(Double.parseDouble(txtDescuento.getText()));//e obtiene el descuento y e envia al controldor
        agregar.setCantidad(Integer.parseInt(txtCantidad.getText()));//e obtienen la cntidad y se envia al controlador
        agregar.setIdFacturaVenta(venta);//Se obtienen el id de vent y se envia a controlador
        agregar.setCodigoProducto(txtCodigo.getText());//Se obtiene el codigo dde producto y e envia a controlador

        //Datos aparte
        agregar.setCostoUnitario(Double.parseDouble(txtCostoUnitario.getText()));//Se obtiene el costo unitario y e envia al controlador

        //enviando guardar a  SQLServer
        //Se condiciona
        if (agregar.IngresarDetalleFacturaVentaController()) {
            //Se muestran los datos actualizados de las tablas
            mostrarDatos();
            //Se muestra formulario de notificacion
            FrmNoti2 notificacion = new FrmNoti2();
            notificacion.setVisible(true);
            notificacion.lblTituloNoti2.setText("¡Detalle Creado!");
            notificacion.TANoti2.setText("El detalle de factura"
                    + "\n"
                    + " se ha creado");
            btnFinalizar.setEnabled(true);

        } else {
            //Se muestra el form de error
            error.setVisible(true);
            error.lblTituloError.setText("Error al crear");
            error.TAnotiError.setText("No se pudo guardar el detalle"
                    + "\n"
                    + " de factura en la base "
                    + "\n"
                    + "de datos, problemas de conexión");
        }

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
        PanelDetalleFacturaVenta = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        cmbIdVenta = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        cmbProducto = new javax.swing.JComboBox<>();
        lblCostoUnitario = new javax.swing.JLabel();
        txtCostoUnitario = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        lblDetalle = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        lblDescuento1 = new javax.swing.JLabel();
        txtDescuentoP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        lblCantidadR = new javax.swing.JLabel();
        lblCantidadT = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtCosto = new javax.swing.JTextField();
        lblCosto2 = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        tbDetalleV = new javax.swing.JScrollPane();
        tbDetalleVenta = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1090, 627));
        setPreferredSize(new java.awt.Dimension(1090, 627));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelDetalleFacturaVenta.setBackground(new java.awt.Color(255, 222, 212));
        PanelDetalleFacturaVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(14, 13, 21));
        jPanel2.setForeground(new java.awt.Color(60, 63, 65));
        jPanel2.setPreferredSize(new java.awt.Dimension(144, 44));

        lblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Detalle de factura de venta");
        lblTitulo.setMaximumSize(new java.awt.Dimension(100, 100));
        lblTitulo.setMinimumSize(new java.awt.Dimension(690, 79));
        lblTitulo.setPreferredSize(new java.awt.Dimension(144, 44));

        cmbIdVenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        PanelDetalleFacturaVenta.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, -1));

        jPanel3.setBackground(new java.awt.Color(255, 222, 212));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });
        jPanel3.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 120, 30));

        lblCodigo.setText("Código de producto:");
        jPanel3.add(lblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        lblProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/producto35px.png"))); // NOI18N
        lblProducto.setText("Producto:");
        jPanel3.add(lblProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        cmbProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbProducto.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbProductoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel3.add(cmbProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 130, 31));

        lblCostoUnitario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/costounitario35px.png"))); // NOI18N
        lblCostoUnitario.setText("Costo unitario:");
        jPanel3.add(lblCostoUnitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        txtCostoUnitario.setEnabled(false);
        jPanel3.add(txtCostoUnitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 270, 120, 31));

        btnEliminar.setBackground(new java.awt.Color(164, 188, 188));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/eliminar30px.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 140, 40));

        btnRegresar.setBackground(new java.awt.Color(164, 188, 188));
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/regresar30px.png"))); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegresarMouseClicked(evt);
            }
        });
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarbtnRegresarActionPerformed(evt);
            }
        });
        jPanel3.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 130, 40));

        btnFinalizar.setBackground(new java.awt.Color(164, 188, 188));
        btnFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/derecha30px.png"))); // NOI18N
        btnFinalizar.setText("Finalizar");
        btnFinalizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnFinalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFinalizarMouseClicked(evt);
            }
        });
        jPanel3.add(btnFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 130, 40));

        lblDetalle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDetalle.setText("Detalle de venta");
        jPanel3.add(lblDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(-11, 0, 370, -1));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("$");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 10, -1));

        btnIngresar.setBackground(new java.awt.Color(164, 188, 188));
        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/ingresar30px.png"))); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        jPanel3.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 140, 40));

        lblDescuento1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDescuento1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescuento1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/descuento35px.png"))); // NOI18N
        lblDescuento1.setText("Descuento");
        jPanel3.add(lblDescuento1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, -1, -1));

        txtDescuentoP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuentoP.setText("-");
        txtDescuentoP.setEnabled(false);
        jPanel3.add(txtDescuentoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 100, 30));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("%");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, -1, -1));

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        jPanel3.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 100, 30));

        lblCantidad.setBackground(new java.awt.Color(255, 255, 255));
        lblCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/cantidad35px.png"))); // NOI18N
        lblCantidad.setText("Cantidad:");
        jPanel3.add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, -1, -1));
        jPanel3.add(lblCantidadR, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 50, 40));
        jPanel3.add(lblCantidadT, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 60, 40));

        PanelDetalleFacturaVenta.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, 340, 585));

        jPanel4.setBackground(new java.awt.Color(94, 45, 79));
        jPanel4.setPreferredSize(new java.awt.Dimension(685, 118));

        txtCosto.setText("00.00");
        txtCosto.setEnabled(false);

        lblCosto2.setForeground(new java.awt.Color(255, 255, 255));
        lblCosto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/totalpago35px.png"))); // NOI18N
        lblCosto2.setText("Total de venta:          $");

        lblDescuento.setForeground(new java.awt.Color(255, 255, 255));
        lblDescuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/descuento35px.png"))); // NOI18N
        lblDescuento.setText("Total con descuento:  $");

        txtDescuento.setText("00.00");
        txtDescuento.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(lblCosto2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDescuento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCosto2)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescuento)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        PanelDetalleFacturaVenta.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 680, 90));

        tbDetalleV.setBackground(new java.awt.Color(94, 45, 79));
        tbDetalleV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDetalleVMouseClicked(evt);
            }
        });

        tbDetalleVenta.setBackground(new java.awt.Color(164, 188, 188));
        tbDetalleVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cantidad", "Descuento", "Costo unitario", "Costo total de venta", "Producto", "Factura"
            }
        ));
        tbDetalleV.setViewportView(tbDetalleVenta);

        PanelDetalleFacturaVenta.add(tbDetalleV, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 680, 490));

        jPanel1.add(PanelDetalleFacturaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 640));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 1, 1110, 640));
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegresarbtnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarbtnRegresarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresarbtnRegresarActionPerformed

    private void btnFinalizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizarMouseClicked
        //Se crea objeto de venta finalizada
        FrmVentaFinalizada finalizadaventa = new FrmVentaFinalizada();
        finalizadaventa.setVisible(true);//Se hace visible el form

    }//GEN-LAST:event_btnFinalizarMouseClicked

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        try {
            //Se ejcuta metodo para restar productos
            RestaProductos();
            //Se limpian campos
            limpiarCampos();

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void tbDetalleVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetalleVMouseClicked

    }//GEN-LAST:event_tbDetalleVMouseClicked

    private void btnRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseClicked
        // TODO add your handling code here:

        //Se crea objeto de frm de venta
        FrmVenta venta = new FrmVenta();

        //Se abre el form
        venta.setLocation(0, 0);
        PanelPrueba.removeAll();
        PanelPrueba.add(venta);
        PanelPrueba.revalidate();
        PanelPrueba.repaint();
    }//GEN-LAST:event_btnRegresarMouseClicked

    //Se crea metodo para obtener las cantidades de todos los productos
    void Seleccion() {

        //Creamos variable, obtene,os el codigo del producto
        String codigo = txtCodigo.getText();
        //Enviamo el codigo al metodo de cargar cantidad
        Object[] datos = obj.CargarDatoCantidad(codigo);

        //Se envia el dato del objeto al set de cantidad
        obj.setCantidadI(Integer.parseInt(datos[0].toString()));
        System.out.println("Cantidad" + datos[0].toString());

    }

    private void cmbProductoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbProductoPopupMenuWillBecomeInvisible
        try {
            //Condicionamos si el index seleccionado del combobox e diferente de 0
            if (cmbProducto.getSelectedIndex() != 0) {
                //Creamo variable que almacene el index seleccionado, restamos uno
                int seleccion = cmbProducto.getSelectedIndex() - 1;
                //Creamos variable que almacene el codigo de producto del ArrayList
                String codigo = CodigoProducto.get(seleccion);

                //Se almacena el codigo en el objeto datos
                Object[] datos = obj.CargarDatosCmb(codigo);

                //Mandamos los datos  recibidos a cada rato correspoondiente 
                txtCodigo.setText(datos[0].toString());//Se obtiene el codigo e producto
                txtCostoUnitario.setText(String.valueOf(datos[1]));//Se obtiene el costo unitario
                txtDescuentoP.setText(datos[2].toString());//Se obtiene el descuento

                //Se ejecuta el metodo de cantidades
                Seleccion();

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cmbProductoPopupMenuWillBecomeInvisible

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        //Se ejecuta para calcular el porcentaje
        CalcularPorcentaje();
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        //Ejecutamos validcion donde solo se podran numeros
        validaciones.soloNumeros(evt, 1, txtCantidad.getText());
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        //Creamos condicionante que evalue la longitud de los datos inertdos en txtCodigo
        if (txtCodigo.getText().length() < 6) {
            //Mandamos el codigo de producto
            obj.ejecutar(txtCodigo.getText());
            //Creamos arreglo de tipo objeto para guardar codigo de producto
            Object[] datos = obj.ejecutar(txtCodigo.getText());
            try {
                //Enviamos a que se muestren los dtaos
                cmbProducto.setSelectedIndex(Integer.parseInt(String.valueOf(datos[0])));//Producto
                txtCostoUnitario.setText(String.valueOf(datos[1]));//Costo unitario
                txtDescuentoP.setText(String.valueOf(datos[2]));//Descuento del proucto
            } catch (Exception e) {
                System.out.print(e.toString());
            }
        }
    }//GEN-LAST:event_txtCodigoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel PanelDetalleFacturaVenta;
    public static javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnFinalizar;
    public static javax.swing.JButton btnIngresar;
    public static javax.swing.JButton btnRegresar;
    public static javax.swing.JComboBox<String> cmbIdVenta;
    public static javax.swing.JComboBox<String> cmbProducto;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel4;
    public static javax.swing.JLabel lblCantidad;
    public static javax.swing.JLabel lblCantidadR;
    public static javax.swing.JLabel lblCantidadT;
    public static javax.swing.JLabel lblCodigo;
    public static javax.swing.JLabel lblCosto2;
    public static javax.swing.JLabel lblCostoUnitario;
    public static javax.swing.JLabel lblDescuento;
    public static javax.swing.JLabel lblDescuento1;
    public static javax.swing.JLabel lblDetalle;
    public static javax.swing.JLabel lblProducto;
    public static javax.swing.JLabel lblTitulo;
    public static javax.swing.JScrollPane tbDetalleV;
    public static javax.swing.JTable tbDetalleVenta;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtCosto;
    public static javax.swing.JTextField txtCostoUnitario;
    public static javax.swing.JTextField txtDescuento;
    public static javax.swing.JTextField txtDescuentoP;
    // End of variables declaration//GEN-END:variables
}
