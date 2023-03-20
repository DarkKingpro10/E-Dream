package Vista;

import Controlador.Encriptar;
import Controlador.ControladorPrimerUsuario;
import java.awt.Image;
import javax.swing.ImageIcon;
import Recursos_Tipografias.Fuentes;
import java.awt.Color;
import java.io.File;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import Controlador.Validaciones;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Ericksonn
 */
public class FrmPrimerUsoUsuario extends javax.swing.JFrame {

    public static java.util.Date fecha;
    public static String seleccion;
    public static String iden;
    byte[] imagen;
    FrmNotiError error = new FrmNotiError();
    FrmNoti2 noti2 = new FrmNoti2();
    FrmNoti3 noti3 = new FrmNoti3();
    Encriptar encriptar = new Encriptar();
    
    LocalDate Fecha = LocalDate.now();
    SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd"); //Se estable el formato de fehca
    ZoneId systemTimeZone = ZoneId.systemDefault();
    ZonedDateTime zonedDateTime = Fecha.atStartOfDay(systemTimeZone);
    Date FechaActual = Date.from(zonedDateTime.toInstant());

    int caso;

    ControladorPrimerUsuario contro = new ControladorPrimerUsuario();
    Controlador.Validaciones vali = new Validaciones();
    ArrayList<Integer> CargarIdTipoEmpleado = new ArrayList<Integer>();
    ArrayList<Integer> CargarIdUsuario = new ArrayList<Integer>();
    ArrayList<Integer> CargarIdEmpresa = new ArrayList<Integer>();

    Fuentes tipoFuente = new Fuentes();
    //Creamos variables globales X y Y que ayudaran en metodo para mover el form
    int x, y;

    public FrmPrimerUsoUsuario() {
        initComponents();

        lblIdEmpresa.setVisible(false);
        lblIdUsuario.setVisible(false);
        lblCorreoInv.setVisible(false);

        /*Utilizamos new ImageIcon(URL location).getImage() donde el argumento
        *location lo obtenemos a través del método getClass().getResource(String name)
        *que nos devuelve un URL absoluto del recurso que especificamos como String.
         */
        Image icon = new ImageIcon(getClass().getResource("/Recursos_img/E-Dream logo.png")).getImage();
        setIconImage(icon);

        lblTituloPrimerUsoUser.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 20));
        lblDatosUser.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        lblNombreEmpleado.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        lblApellidoEmpleado.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        lblDUI.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        lblTipoUser.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        cmbTipoEmpleado.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        lblUsuario.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        txtUsuario.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        lblClave.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        txtClave.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        lblConfirmarClave.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));
        txtConfirmarClave.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));

        //Cargamos combobox
        CargarIdUsuario();
        CargarTipoEmpleado();
        CargarIdEmpresa();

        CargarIdUsuario = contro.idUsuario();
        CargarIdTipoEmpleado = contro.idTipoEmpleado();
        CargarIdEmpresa = contro.idEmpresa();
        System.out.println(CargarIdTipoEmpleado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblTituloPrimerUsoUser = new javax.swing.JLabel();
        lblCerrar = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblDatosUser = new javax.swing.JLabel();
        lblNombreEmpleado = new javax.swing.JLabel();
        lblApellidoEmpleado = new javax.swing.JLabel();
        lblDUI = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblClave = new javax.swing.JLabel();
        lblConfirmarClave = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblTipoUser = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtApellidousu = new javax.swing.JTextField();
        txtNombreusu = new javax.swing.JTextField();
        txtConfirmarClave = new javax.swing.JTextField();
        txtClave = new javax.swing.JTextField();
        cmbTipoEmpleado = new javax.swing.JComboBox<>();
        btnSubir = new org.jdesktop.swingx.JXButton();
        btnRegistrarse2 = new org.jdesktop.swingx.JXButton();
        lblDUI1 = new javax.swing.JLabel();
        lblDUI2 = new javax.swing.JLabel();
        CmbSexo = new javax.swing.JComboBox<>();
        txtCorreo = new javax.swing.JTextField();
        txtDUI1 = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        lblDUI3 = new javax.swing.JLabel();
        lblDUI4 = new javax.swing.JLabel();
        FechaFundacion = new com.toedter.calendar.JDateChooser();
        lblAgregarVisualizar = new javax.swing.JLabel();
        lblIdUsuario = new javax.swing.JLabel();
        lblIdEmpresa = new javax.swing.JLabel();
        lblCorreoInv = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(14, 13, 21));

        jPanel2.setBackground(new java.awt.Color(14, 13, 21));

        jPanel3.setBackground(new java.awt.Color(14, 13, 21));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1242, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1242, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1242, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(920, 595));
        setUndecorated(true);
        setSize(new java.awt.Dimension(1000, 500));

        jPanel4.setBackground(new java.awt.Color(14, 13, 21));
        jPanel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel4MouseDragged(evt);
            }
        });
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel4MousePressed(evt);
            }
        });

        lblTituloPrimerUsoUser.setForeground(new java.awt.Color(240, 240, 240));
        lblTituloPrimerUsoUser.setText("Primer Uso Usuario");

        lblCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Close.png"))); // NOI18N
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(427, 427, 427)
                .addComponent(lblTituloPrimerUsoUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 336, Short.MAX_VALUE)
                .addComponent(lblCerrar)
                .addGap(49, 49, 49))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(lblTituloPrimerUsoUser))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 222, 216));
        jPanel5.setLayout(null);

        lblDatosUser.setText("Datos del usuario");
        jPanel5.add(lblDatosUser);
        lblDatosUser.setBounds(50, 10, 99, 16);

        lblNombreEmpleado.setText("Nombre Completo");
        jPanel5.add(lblNombreEmpleado);
        lblNombreEmpleado.setBounds(50, 60, 160, 16);

        lblApellidoEmpleado.setText("Apellido Completo:");
        jPanel5.add(lblApellidoEmpleado);
        lblApellidoEmpleado.setBounds(263, 58, 160, 16);

        lblDUI.setText("Correo:");
        jPanel5.add(lblDUI);
        lblDUI.setBounds(260, 230, 50, 16);

        lblUsuario.setText("Usuario:");
        jPanel5.add(lblUsuario);
        lblUsuario.setBounds(50, 330, 160, 16);

        lblClave.setText("Ingrese clave:");
        jPanel5.add(lblClave);
        lblClave.setBounds(260, 330, 160, 16);

        lblConfirmarClave.setText("Confirme Clave:");
        jPanel5.add(lblConfirmarClave);
        lblConfirmarClave.setBounds(490, 330, 160, 16);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel5.add(jSeparator1);
        jSeparator1.setBounds(460, 40, 18, 250);
        jPanel5.add(jSeparator2);
        jSeparator2.setBounds(50, 310, 640, 10);

        lblTipoUser.setText("Tipo de Empleado:");
        jPanel5.add(lblTipoUser);
        lblTipoUser.setBounds(490, 60, 146, 16);
        jPanel5.add(txtUsuario);
        txtUsuario.setBounds(50, 360, 160, 24);
        jPanel5.add(txtApellidousu);
        txtApellidousu.setBounds(260, 80, 160, 24);
        jPanel5.add(txtNombreusu);
        txtNombreusu.setBounds(50, 80, 160, 24);
        jPanel5.add(txtConfirmarClave);
        txtConfirmarClave.setBounds(490, 360, 160, 24);
        jPanel5.add(txtClave);
        txtClave.setBounds(260, 360, 160, 24);

        cmbTipoEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(cmbTipoEmpleado);
        cmbTipoEmpleado.setBounds(490, 80, 160, 26);

        btnSubir.setBackground(new java.awt.Color(51, 102, 255));
        btnSubir.setBorder(null);
        btnSubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Subir.png"))); // NOI18N
        btnSubir.setText("Subir Imagen");
        btnSubir.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnSubir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSubir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSubirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSubirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSubirMouseExited(evt);
            }
        });
        jPanel5.add(btnSubir);
        btnSubir.setBounds(730, 230, 150, 40);

        btnRegistrarse2.setBackground(new java.awt.Color(164, 188, 188));
        btnRegistrarse2.setBorder(null);
        btnRegistrarse2.setText("Registrar");
        btnRegistrarse2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegistrarse2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarse2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarse2MouseExited(evt);
            }
        });
        btnRegistrarse2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarse2ActionPerformed(evt);
            }
        });
        jPanel5.add(btnRegistrarse2);
        btnRegistrarse2.setBounds(730, 340, 150, 40);

        lblDUI1.setText("Sexo:");
        jPanel5.add(lblDUI1);
        lblDUI1.setBounds(260, 130, 52, 16);

        lblDUI2.setText("Edad:");
        jPanel5.add(lblDUI2);
        lblDUI2.setBounds(490, 130, 52, 16);

        CmbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino", " " }));
        jPanel5.add(CmbSexo);
        CmbSexo.setBounds(260, 173, 110, 26);

        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoKeyReleased(evt);
            }
        });
        jPanel5.add(txtCorreo);
        txtCorreo.setBounds(260, 260, 160, 24);

        txtDUI1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDUI1KeyTyped(evt);
            }
        });
        jPanel5.add(txtDUI1);
        txtDUI1.setBounds(50, 170, 160, 24);

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        jPanel5.add(txtTelefono);
        txtTelefono.setBounds(50, 260, 160, 24);

        lblDUI3.setText("DUI:");
        jPanel5.add(lblDUI3);
        lblDUI3.setBounds(50, 130, 160, 16);

        lblDUI4.setText("Teléfono:");
        jPanel5.add(lblDUI4);
        lblDUI4.setBounds(50, 230, 160, 16);

        FechaFundacion.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                FechaFundacionPropertyChange(evt);
            }
        });
        jPanel5.add(FechaFundacion);
        FechaFundacion.setBounds(490, 170, 170, 29);

        lblAgregarVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/NoImage200px.png"))); // NOI18N
        jPanel5.add(lblAgregarVisualizar);
        lblAgregarVisualizar.setBounds(700, 10, 200, 200);

        lblIdUsuario.setText("id");
        jPanel5.add(lblIdUsuario);
        lblIdUsuario.setBounds(310, 0, 100, 40);

        lblIdEmpresa.setText("idE");
        jPanel5.add(lblIdEmpresa);
        lblIdEmpresa.setBounds(550, 10, 41, 16);

        lblCorreoInv.setText("Correo Invalido");
        jPanel5.add(lblCorreoInv);
        lblCorreoInv.setBounds(330, 230, 90, 16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    ControladorPrimerUsuario PrimerUsuario = new ControladorPrimerUsuario();

    //Cargar ComboBox id de usuario
    void CargarIdUsuario() {
        ResultSet res = Controlador.ControladorPrimerUsuario.CargarIdUsuarioController();
        try {
            while (res.next()) {
                lblIdUsuario.setText(res.getString("idUsuario"));
            }
        } catch (Exception e) {
            error.lblTituloError.setText("Error de Conexión");
            error.TAnotiError.setText("Ocurrio un error al establecer \nconexión con la base de datos, \nverifique su acceso a internet \no que los servicios del servidor\n estén activos");
            error.setVisible(true);
        }
    }

    //Cargar ComboBox id de emprea
    void CargarIdEmpresa() {
        ResultSet res = Controlador.ControladorPrimerUsuario.CargarIdEmpresaController();
        try {
            while (res.next()) {
                lblIdEmpresa.setText(res.getString("idEmpresa"));
            }
        } catch (Exception e) {
            error.lblTituloError.setText("Error de Conexión");
            error.TAnotiError.setText("Ocurrio un error al establecer \nconexión con la base de datos, \nverifique su acceso a internet \no que los servicios del servidor\n estén activos");
            error.setVisible(true);
        }
    }

    //Cargar ComboBox Tipo Empleado    
    void CargarTipoEmpleado() {
        DefaultComboBoxModel lista = new DefaultComboBoxModel();
        ResultSet res = PrimerUsuario.CargarTipoEmpleadoControlador();
        try {
            while (res.next()) {
                lista.addElement(res.getString("tipoEmpleado"));
            }
        } catch (Exception e) {
            error.lblTituloError.setText("Error de Conexión");
            error.TAnotiError.setText("Error al conectar a internet o que los servicios del servidor estén activos");
            error.setVisible(true);
        }
        cmbTipoEmpleado.setModel(lista);
    }

    public String InsertarImagen() {
        try {
            //Es una clase java que nos permite mostrar fácilmente una ventana para la selección de un fichero
            JFileChooser ventana = new JFileChooser();
            //Se establece un filtro para que solo se muestren archivos con una determinada extensión
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
            //Agregamos el filtro a la ventana que abriremos
            ventana.setFileFilter(filtro);

            int seleccionar = ventana.showOpenDialog(this);
            //Comprobar si se ha dado clic en Aceptar
            if (seleccionar == JFileChooser.APPROVE_OPTION) {
                //Convertimos a tipo Archivo el componente seleccionado
                File archivo = ventana.getSelectedFile();
                //Esta función devuelve el nombre de ruta absoluta del objeto de archivo dado.
                seleccion = archivo.getAbsolutePath();
                //Obtenemos el ancho del Label donde la colocaremos
                int ancho = lblAgregarVisualizar.getWidth();
                //Obtenemos la altura del Label donde la colocaremos
                int alto = lblAgregarVisualizar.getHeight();
                //getToolkit Obtiene el kit de herramientas predeterminado y se puede obtener un objeto Toolkit mediante la invocación de este método
                // En este caso nos permite ir a nuestro equipo y obtener la ruta.
                //getImage carga todos los bits de la imagen en la memoria
                Image imagen = getToolkit().getImage(seleccion);
                //Image.SCALE_SMOOTH Elije un algoritmo de escalado de imagen que da mayor prioridad a la suavidad de la imagen que a la velocidad de escalado. 
                imagen = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                lblAgregarVisualizar.setIcon(new ImageIcon(imagen));
            }
        } catch (Exception e) {

        }
        return seleccion;
    }

    private void MetodoBotonUsuario() {
        if (ControladorPrimerUsuario.RevisarTablaUsuController() == true && ValidarCampoVacio() == true) {

            ControladorPrimerUsuario obj = new Controlador.ControladorPrimerUsuario();
            obj.setUsuarioE(txtUsuario.getText());
            //Obtenemos el valor int de retorno del Controlador (1 = Existe en la base)
            int acceso = ControladorPrimerUsuario.RevisarUsuarioController();

            if (acceso == 1) {

                if (txtClave.getText().equals(txtConfirmarClave.getText())) {
                    if (Controlador.ControladorPrimerUsuario.ActualizarContraseña(encriptar.Encriptar(txtConfirmarClave.getText()), txtUsuario.getText()) == true && RegistrarEmpleado() == true) {
                        FrmLogin login = new FrmLogin();
                        noti3.lblTituloError.setText("Empleado y contraseña actualizado.");
                        noti3.TANoti3.setText("Se ha creado el empleado y se ha actualizado la contraseña");
                        noti3.setVisible(true);
                        login.setVisible(true);
                    }
                } else {
                    error.lblTituloError.setText("Contraseña no valida");
                    error.TAnotiError.setText("Ingrese una contraseña correcta.");
                    error.setVisible(true);
                }
                //2 = No existe en la base
            } else if (acceso == 2) {
                error.lblTituloError.setText("Usuario incorrecto, verifique el usuario");
                error.TAnotiError.setText("El usuario que desea ingresar es invalido.");
                error.setVisible(true);
            } //3 un error de conexión
            else {
                error.lblTituloError.setText("No se encontro en la base");
                error.TAnotiError.setText("Ocurrió un error al establecer conexión con la base de datos, verifique que los servicios del servidor esten activos.");
                error.setVisible(true);
            }
        } else {
            caso = 1;
            if (RegistrarUsuario() == true && ValidarCampoVacio() == true) {
                System.out.println("No Existe");
                noti2.TANoti2.setText("Empleado y Usuario creado");
                noti2.TANoti2.setText("Primer empleado y usuario de la empresa fueron creados, bienvenido a E-Dream");
                noti2.setVisible(true);
                this.dispose();

            } else {
                error.lblTituloError.setText("Error al crear el empleado y usuario");
                error.TAnotiError.setText("Ocurrio un error al crear el  empleado y usuario, verifique que todo este correcto");
                error.setVisible(true);
            }
        }
    }

    public boolean ValidarCampoVacio() {
        if (txtUsuario.getText().isEmpty() || txtCorreo.getText().isEmpty() || txtApellidousu.getText().isEmpty() || txtDUI1.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtConfirmarClave.getText().isEmpty() || lblCorreoInv.isVisible()) {
            error.TAnotiError.setText("Verifique que todos los campos esten llenos");
            error.setVisible(true);
            return false;
        } else {
            return true;
        }
    }

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void btnSubirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubirMouseClicked
        InsertarImagen();
        btnSubir.setVisible(true);
    }//GEN-LAST:event_btnSubirMouseClicked

    private void btnRegistrarse2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarse2MouseClicked
//        MetodoBotonUsuario();
//        this.dispose();
    }//GEN-LAST:event_btnRegistrarse2MouseClicked

    private void btnRegistrarse2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarse2MouseEntered
        btnRegistrarse2.setBackground(new Color(51, 102, 255));
        btnRegistrarse2.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnRegistrarse2MouseEntered

    private void btnRegistrarse2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarse2MouseExited
        btnRegistrarse2.setBackground(new Color(164, 188, 188));
        btnRegistrarse2.setForeground(new Color(51, 51, 51));
    }//GEN-LAST:event_btnRegistrarse2MouseExited

    private void btnSubirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubirMouseEntered
        btnSubir.setBackground(new Color(0, 51, 102));
        btnSubir.setForeground(Color.white);
    }//GEN-LAST:event_btnSubirMouseEntered

    private void btnSubirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubirMouseExited
        btnSubir.setBackground(new Color(51, 102, 255));
        btnSubir.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnSubirMouseExited

    private void txtCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyReleased
        //Invocamos a un metodo en el controlador donde se evalue si el formato del correo es el correcto
        boolean confirm = ControladorPrimerUsuario.ConfirmarCorreo(txtCorreo.getText());
        //Verificamos si el resultado es null o false
        if (confirm == true) {
            lblCorreoInv.setVisible(false);
        } else {
            lblCorreoInv.setVisible(true);
        }
    }//GEN-LAST:event_txtCorreoKeyReleased

    private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MousePressed
        // TODO add your handling code here:
        //Inicializamos las variables x y y con la posicion actual del form
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel4MousePressed

    private void jPanel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseDragged
        // TODO add your handling code here:
        //Creamos metodo que actualice la posicion del form dependendiendo donde el usuario lo coloque
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jPanel4MouseDragged

    private void btnRegistrarse2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarse2ActionPerformed
        MetodoBotonUsuario();
//        
    }//GEN-LAST:event_btnRegistrarse2ActionPerformed

    private void txtDUI1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDUI1KeyTyped
        vali.soloNumeros(evt, 1, txtDUI1.getText());
        vali.Cantidad(evt, txtDUI1.getText(), 9);
    }//GEN-LAST:event_txtDUI1KeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        vali.soloNumeros(evt, 1, txtTelefono.getText());
        vali.Cantidad(evt, txtTelefono.getText(), 9);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void FechaFundacionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_FechaFundacionPropertyChange
        try {
             
            if (FechaFundacion.getDate() == null) {

            } else {
                if ((contro.validarFecha(FechaFundacion.getDate())) <= 18) {
                    FechaFundacion.setDate(null);
                    
                    error.TAnotiError.setText("La fecha ingresada debe ser menor o igual a la del día de hoy");
                    error.lblTituloError.setText("Fecha erronea");
                    error.setVisible(true);
                } 
                if (FechaFundacion.getDate().before(FechaActual)) {
                    
                }
                else {
                    FechaFundacion.setDate(null);
                    
                    error.TAnotiError.setText("La fecha ingresada debe ser menor o igual a la del día de hoy");
                    error.lblTituloError.setText("Fecha erronea");
                    error.setVisible(true);
                }
            }

        } catch (Exception e) {
            System.out.print("Error Inicio" + e.toString());
        }

    }//GEN-LAST:event_FechaFundacionPropertyChange

    boolean RegistrarUsuario() {
        boolean validacion = false;
        if (txtClave.getText().equals(txtConfirmarClave.getText())) {
            contro.setClaveE(encriptar.Encriptar(txtClave.getText()));
            contro.setUsuarioE(txtUsuario.getText());
            contro.setIdUsuarioE(Controlador.ControladorPrimerUsuario.RevisarUsuarioController());
            try {
                if (contro.InsercionPrimerUsoUsuarioController()) {
                    validacion = true;
                    RegistrarEmpleado();
                } else {
                    error.TAnotiError.setText("Ocurrio un error de inserción.");
                    error.setVisible(true);
                }
            } catch (Exception E) {
                validacion = false;
                error.lblTituloError.setText("Error al crear el usuario");
                error.TAnotiError.setText("Ocurrio un error al crear el usuario, verifique que todo este correcto");
                error.setVisible(true);
            }
        }
        return validacion;
    }

    boolean RegistrarEmpleado() {
        boolean validacion = false;
        try {
            String sexo;
            if (CmbSexo.getSelectedIndex() == 0) {
                sexo = "Femenino";
            } else {
                sexo = "Masculino";
            }

            int idTipoEmpleado;
            int tipoEmpleado;

            if (caso == 2) {
                idTipoEmpleado = cmbTipoEmpleado.getSelectedIndex();
                tipoEmpleado = CargarIdTipoEmpleado.get(idTipoEmpleado);
            } else {

                tipoEmpleado = 1;

            }
            System.out.println(tipoEmpleado);

            Controlador.ControladorPrimerUsuario agregar = new Controlador.ControladorPrimerUsuario();
            fecha = FechaFundacion.getDate();

            SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
            String edad = Formato.format(fecha);
            java.sql.Date fecha = java.sql.Date.valueOf(edad);

            agregar.setEdadUsuE(fecha);
            agregar.setNombreE(txtNombreusu.getText());
            agregar.setApellidoUsuE(txtApellidousu.getText());
            agregar.setDui(txtDUI1.getText());
            agregar.setSexoUsu(sexo);
            agregar.setCorreoUsuE(txtCorreo.getText());
            agregar.setIdTipoEmpleado(tipoEmpleado);
            agregar.setTelefonoUsuE(txtTelefono.getText());
            agregar.setImagenUsuE(seleccion);
            agregar.setIdEmpresaE(Integer.parseInt(lblIdEmpresa.getText()));
            agregar.setIdUsuarioE(agregar.obtenerIdUsuario(txtUsuario.getText()));
            if (agregar.IncercionEmpleadoController() == false) {
                validacion = false;
                error.lblTituloError.setText("Error de Inserción");
                error.TAnotiError.setText("El empleado no pudo ser ingresada");
                error.setVisible(true);
            } else {
                validacion = true;
                noti2.lblTituloNoti2.setText("Creación Exitosa");
                noti2.TANoti2.setText("Empresa creada, empresa ha sido creada con exito");
                noti2.setVisible(true);
                FrmLogin login = new FrmLogin();
                login.setVisible(true);
//                this.dispose();
            }
        } catch (Exception E) {
            error.lblTituloError.setText("Error de Inserción");
            error.TAnotiError.setText("El empleado no pudo ser ingresada");
            error.setVisible(true);
        }
        return validacion;
    }

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
            java.util.logging.Logger.getLogger(FrmPrimerUsoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrimerUsoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrimerUsoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrimerUsoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrimerUsoUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbSexo;
    private com.toedter.calendar.JDateChooser FechaFundacion;
    private org.jdesktop.swingx.JXButton btnRegistrarse2;
    private org.jdesktop.swingx.JXButton btnSubir;
    private javax.swing.JComboBox<String> cmbTipoEmpleado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAgregarVisualizar;
    private javax.swing.JLabel lblApellidoEmpleado;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JLabel lblClave;
    private javax.swing.JLabel lblConfirmarClave;
    private javax.swing.JLabel lblCorreoInv;
    private javax.swing.JLabel lblDUI;
    private javax.swing.JLabel lblDUI1;
    private javax.swing.JLabel lblDUI2;
    private javax.swing.JLabel lblDUI3;
    private javax.swing.JLabel lblDUI4;
    private javax.swing.JLabel lblDatosUser;
    private javax.swing.JLabel lblIdEmpresa;
    private javax.swing.JLabel lblIdUsuario;
    private javax.swing.JLabel lblNombreEmpleado;
    private javax.swing.JLabel lblTipoUser;
    private javax.swing.JLabel lblTituloPrimerUsoUser;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtApellidousu;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtConfirmarClave;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDUI1;
    private javax.swing.JTextField txtNombreusu;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
