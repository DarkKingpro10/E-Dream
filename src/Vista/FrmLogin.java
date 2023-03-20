package Vista;

import Controlador.ControladorVariables;
import Controlador.ControladorLogin;
import Controlador.Encriptar;
import com.sun.awt.AWTUtilities;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import Recursos_Tipografias.Fuentes;
import static Vista.FrmContenedor.PanelPrueba;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.sql.Blob;
import java.sql.Time;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;


/**
 *
 * @author lenny
 */
public class FrmLogin extends javax.swing.JFrame {

    //Creamos variables globales X y Y que ayudaran en metodo para mover el form
    private int x, y;
    
    FrmNotiError error = new FrmNotiError();
    FrmNoti1 notificacion = new FrmNoti1();
    Encriptar encriptar = new Encriptar();
    
    //Creando objeto de la clase
    FondoLogin fondo = new FondoLogin();
    Fuentes tipoFuente = new Fuentes();
    private Color Rojo = new Color(220, 49, 72);
    private Color Celeste = new Color(164,188,188);
    private int Contador1 = 0;
    private int Contador2 = 0;
    private int idusuario;
    private String User;
    private int intentos;
    private int idEstadoUsuario;
    private int idEmpleado;
    private int idTipoEmpleado;
    private String TipoEmpleado;
    private String nombreE;
    private String apellidoE;
    private String DUI;
    private Blob Imagen;
    private Time HoraBloqueo;
    private Time HoraDesbloqueo;
    SimpleDateFormat sdff = new SimpleDateFormat("HH:mm:ss");
    
    public FrmLogin() {
        //Pasan el valor del objeto de la clase
        this.setContentPane(fondo);
        initComponents();
        //Colocamos el texto de Usuario en el JTextField para indicar el valor del campo
        txtUser.setText("Usuario");
        //Marcamos la opción de ver contraseña por defecto para que se pueda observar el indicador del campo
        chbVer.setSelected(true);
        //Colocamos visible el texto en el JPasswordField
        txtPass.setEchoChar((char)0);
        //Forma rectangular de los botones
        ShapedButtonUI squareUI = new ShapedButtonUI();
        squareUI.setShape(ButtonShape.SQUARE, btnIniciar);
        btnIniciar.setUI(squareUI);
        btnIniciar.setPreferredSize(new Dimension(100, 100));
        
        /*Utilizamos new ImageIcon(URL location).getImage() donde el argumento
        *location lo obtenemos a través del método getClass().getResource(String name)
        *que nos devuelve un URL absoluto del recurso que especificamos como String.
        */
        Image icon = new ImageIcon(getClass().getResource("/Recursos_img/E-Dream logo.png")).getImage();
        setIconImage(icon);
        
        AWTUtilities.setWindowOpaque(this, false);
        txtPass.setFont(tipoFuente.fuente(tipoFuente.MaxwellLight, 0, 16));
        txtUser.setFont(tipoFuente.fuente(tipoFuente.MaxwellLight, 0, 16));
        lblOlvidar.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 1, 16));
        btnIniciar.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 1, 16));
        CheckMode.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 16));
        lblUsuario.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 1, 16));
        lblContra.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 1, 16));
        chbVer.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 1, 16));
    }
    
    void EstadoActivo(){
        ControladorLogin obj = new ControladorLogin();
        int Estado = 1;
        obj.setEstadoU(Estado);
        if (ControladorLogin.ActualizarEstado_Controller() == true){
            
        }
        else{
            error.lblTituloError.setText("Error de actualización");
            error.TAnotiError.setText("No se ha podido actualizar el estado del usuario a activo");
            error.setVisible(true);
        }
    }
    
    /**
    * Definimos la forma cuadrada
    */
    public enum ButtonShape {
        SQUARE
    }
    
    private class ShapedButtonUI extends BasicButtonUI{
        /** Button shape. */
        private ButtonShape shape;

        public ShapedButtonUI() {
            super();
        }

        public void setShape(ButtonShape shape, JButton button){
            // No pintamos el borde
            button.setBorderPainted(false);
            this.shape = shape;
        }

        public void paint(JComponent c) {
            // definamos las formas de nuestros botones
            Shape buttonShape = null;
            switch (shape) {
                case SQUARE:
                    buttonShape = new Rectangle(0, 0, c.getWidth(), c.getHeight());
                break;
            }
        }

        protected void paintButtonPressed(AbstractButton b) {
            // definamos las formas de nuestros botones
            Shape buttonShape = null;
            switch (shape) {
                case SQUARE:
                buttonShape = new Rectangle(0, 0, b.getWidth(), b.getHeight());
            break;
            }
        }
    }
    
    void ObtenerDatos(){
        try{
            //Creamos una lista para guardar los datos que traiga el controlador del modelo
            ArrayList<Object> datos = ControladorLogin.Nivel_Controller();
            //Asignamos los valores de la lista a las variables creadas en este Form
            if(datos.isEmpty() == false){
                idusuario = Integer.parseInt(String.valueOf(datos.get(0)));
                User = String.valueOf(datos.get(1));
                intentos = Integer.parseInt(String.valueOf(datos.get(2)));
                idEstadoUsuario = Integer.parseInt(String.valueOf(datos.get(3)));
                idEmpleado = Integer.parseInt(String.valueOf(datos.get(4)));
                idTipoEmpleado = Integer.parseInt(String.valueOf(datos.get(5)));
                TipoEmpleado = String.valueOf(datos.get(6));
                nombreE = String.valueOf(datos.get(7));
                apellidoE = String.valueOf(datos.get(8));
                DUI = String.valueOf(datos.get(9));
                Imagen = (Blob)datos.get(10);
            }
            if(datos.isEmpty() == true){
                notificacion.lblTituloNoti1.setText("Credenciales erroneas");
                notificacion.TAMensajeError.setText("No se ha encontrado valores similares en el sistema, verifique si ha ingresado correctamente sus credenciales.");
                notificacion.setVisible(true);
            }
        }
        //Si ocurre un error en el proceso, manda un mensaje de error de conexión
        catch (Exception e){
            error.lblTituloError.setText("Error de conexión");
            error.TAnotiError.setText("Ocurrió un error al establecer conexión con la base de datos, verifique que los servicios del servidor esten activos.");
            error.setVisible(true);
        }
        //Creamos un Objeto del Controlador "ControladorVariables" que almacenará diferentes datos que se utilizarán en otros forms
        ControladorVariables variables = new ControladorVariables();
        //Asignamos 
        variables.setidUsuario(idusuario);
        variables.setUsuario(User);
        variables.setIntento(intentos);
        variables.setEstadoU(idEstadoUsuario);
        variables.setEmpleado(idEmpleado);
        variables.setidTipoE(idTipoEmpleado);
        variables.setTipoE(TipoEmpleado);
        variables.setNombreE(nombreE);
        variables.setApellidoE(apellidoE);
        variables.setDUI(DUI);
        variables.setImagen(Imagen);
    }
    
    void EnvioDatosLogin(){
        //Creamos una variable String que contenga los datos del JPasswordField
        String contra=new String(txtPass.getPassword());
        //Crea un objeto de la clase ControladorLogin para llamar a los métodos
        Controlador.ControladorLogin obj = new Controlador.ControladorLogin();
        ControladorVariables variables = new ControladorVariables();
        //Asignamos los valores de los JText a los parametros en Controlador
        obj.setUsuario(txtUser.getText());
        obj.setClave_Usuario(encriptar.Encriptar(contra));
        //Mandamos a traer los datos que tenga ligado el usuario
        ObtenerDatos();
        //Obtenemos el valor int de retorno del Controlador (1 = Existe en la base)
        int acceso = Controlador.ControladorLogin.Acceso_Controller();
        if(acceso == 1 && variables.getEstadoU()< 5){
            //VALIDAR SI EXISTE UN USUARIO PERO SIN UN EMPLEADO LIGADO AL USUARIO        
            int UsuarioLigado = Controlador.ControladorLogin.ValidarUsuarioEmpleado_Controller();
            //Se encontro en la base un empleado con ese usuario ligado
            if(UsuarioLigado == 1){
                EstadoActivo();
                IdiomaColor();
                AbirContenedor();
                this.dispose();
            }
            //No se encontro en la base un empleado con ese usuario ligado
            else if (UsuarioLigado == 2){
                //Redirecciona al Usuario al Formulario de Primer Uso de Usuario donde va a crear su empleado
                FrmPrimerUsoUsuario primerUsuario = new FrmPrimerUsoUsuario();
                primerUsuario.setVisible(true);
                this.dispose();
            }
            else{
                error.lblTituloError.setText("No se encontro en la base");
                error.TAnotiError.setText("Ocurrió un error al establecer conexión con la base de datos, verifique que los servicios del servidor esten activos.");
                error.setVisible(true);
            }
        }
        //Tiene estado inactivo
        else if(acceso == 1 || variables.getEstadoU() == 5){
            ActualizarIntentos();
        }
        //2 = No existe en la base o tiene estado inactivo
        else if(acceso == 2 || variables.getEstadoU() == 5){
            ActualizarIntentos();
        }
        //3 un error de conexión
        else{
            error.lblTituloError.setText("No se encontro en la base");
            error.TAnotiError.setText("Ocurrió un error al establecer conexión con la base de datos, verifique que los servicios del servidor esten activos.");
            error.setVisible(true);
        }
    }
    
    
    void HabilitarIntento(){
        try{
            //Creamos objeto de la clase ControladorLogin
            ControladorLogin obj = new ControladorLogin();   
            //El estado activo = 1 lo mandamos al controlador
            int Estado = 1;
            obj.setEstadoU(Estado);
            //
            int nuevoIntento = 0;
            obj.setIntento(nuevoIntento);
            if (ControladorLogin.HabilitarIntentos_Controller()== true){
                FrmNoti2 noti2 = new FrmNoti2();
                noti2.lblTituloNoti2.setText("Estado Actualizado");
                noti2.TANoti2.setText("El estado del usuario es activo.");
                noti2.setVisible(true);
            }
            FrmNoti2 noti2 = new FrmNoti2();
            noti2.lblTituloNoti2.setText("Tiempo Terminado");
            noti2.TANoti2.setText("Ha acabado el tiempo de espera, tiene 5 intentos más.");
            noti2.setVisible(true);
            if (ControladorLogin.ActualizarIntentos_Controller() == false){
                error.lblTituloError.setText("Error de Actualización");
                error.TAnotiError.setText("Ha ocurrido un error en el momento de revisar horas.");
                error.setVisible(true);
            }
        }    
        catch (Exception e){
            error.lblTituloError.setText("Error de Conexión");
            error.TAnotiError.setText("Ocurrio un error al establecer conexión con la base de datos, verifique su acceso a internet o que los servicios del servidor estén activos");
            error.setVisible(true);
        }
    }
    
    //Metodo de actualizar intentos
    void ActualizarIntentos(){
        /*Fixed me*/
        try{
            //Creamos objeto de la clase ControladorLogin
            ControladorLogin obj = new ControladorLogin();
            //Creamos un objeto de la clase ControladorVariables donde se almacenaran los intentos y las horas
            ControladorVariables variable = new ControladorVariables();
            //Si los intentos son menores a 5
            if (variable.getIntento() < 5){
                //Se le sumará un intento
                variable.setIntento(variable.getIntento()+1);
                //Se lo asiganmos y ejecutamos el controlador de Actualizar intentos
                obj.setIntento(variable.getIntento());
                boolean obj2 = ControladorLogin.ActualizarIntentos_Controller();
                //Si se actualizo correctamente
                if(obj2== true){
                    int oportunidades = 5;
                    int totalOportunidades = oportunidades - variable.getIntento();
                    FrmNoti2 noti2 = new FrmNoti2();
                    noti2.lblTituloNoti2.setText("Oportunidades Restantes");
                    noti2.TANoti2.setText("Error al ingresar con usuario.\nSolo posee " + totalOportunidades + " intentos para acceder.");
                    noti2.setVisible(true);
                }
            }
            //Si el intento es mayor a 5
            if (variable.getIntento() == 5){
                ArrayList<Time> datos2 = ControladorLogin.Hora_Controller();
                //Si tiene datos de la hora block
                boolean containNull = true;
                for(int i = 0; i< datos2.size(); i++) {
                    if(datos2.get(i)!= null) {
                        containNull = false;
                        break;
                    }
		}
                // No contiene Null
                if (containNull == false){
                    //Obtenemos la hora de Bloqueo y Desbloqueo
                    HoraBloqueo = datos2.get(0);
                    HoraDesbloqueo = datos2.get(1);
                    //Obtenemos el valor de la hora actual
                    LocalTime HoraActual = LocalTime.now();
                    //Verifica si la hora actual es mayor al tiempo de espera de Desbloqueo en la base
                    if (HoraActual.compareTo(HoraDesbloqueo.toLocalTime()) > 0){
                        HabilitarIntento();
                    }
                    //Si no es mayor al tiempo de espera, se saltará un mensaje
                    else{
                        FrmNoti2 noti2 = new FrmNoti2();
                        noti2.lblTituloNoti2.setText("Tiempo Restante");
                        noti2.TANoti2.setText("Tendrá 5 oportunidades dentro de 3 minutos, por favor espere");
                        noti2.setVisible(true);
                    }
                }    
                //Si no obtiene datos de la hora Block
                else{
                    //Obtenemos Hora actual
                    LocalTime HoraActual = LocalTime.now();
                    //La hora de desbloqueo será 3 minutos extra a la hora actual
                    LocalTime HoraDesblock = HoraActual.plusMinutes(3);
                    //Pasamos el valor "hora1" y "hora2" de Long a Time de SQL
                    java.sql.Time HoraActual1 = Time.valueOf(HoraActual);
                    java.sql.Time HoraDesblock2 = Time.valueOf(HoraDesblock);
                    //Asiganmos las horas con formato TIME SQL
                    obj.setHoraInactivación(HoraActual1);
                    obj.setHoraActivacion(HoraDesblock2);
                    if (ControladorLogin.AgregarHoraI_Controller() == true){
                        FrmNoti2 noti2 = new FrmNoti2();
                        noti2.lblTituloNoti2.setText("Tiempo Restante");
                        noti2.TANoti2.setText("Tendrá 5 oportunidades dentro de 3 minutos, por favor espere");  
                        noti2.setVisible(true);
                        int Estado = 5;
                        obj.setEstadoU(Estado);
                        if (ControladorLogin.ActualizarEstado_Controller() == true){
                            noti2.lblTituloNoti2.setText("Estado Actualizado");
                            noti2.TANoti2.setText("Tendrá 5 oportunidades dentro de 3 minutos, por favor espere");
                            noti2.setVisible(true);
                        }
                    }
                }
            }
        }
        catch(Exception ex){//
            error.lblTituloError.setText("Error de Actualización");
            error.TAnotiError.setText("Ha ocurrido un error en el momento de actualizar intentos");
            error.setVisible(true);
        }
    }
    
    void IdiomaColor(){
        Controlador.ControladorVariables apariencia = new Controlador.ControladorVariables();
        if (CheckMode.isSelected() == true)
        {
            apariencia.setColor(2);
        }
        else if (CheckMode.isSelected() == false)
        {
            apariencia.setColor(1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUser = new javax.swing.JTextField();
        lblOlvidar = new javax.swing.JLabel();
        CheckMode = new javax.swing.JCheckBox();
        lblUsuario = new javax.swing.JLabel();
        lblContra = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnIniciar = new javax.swing.JButton();
        lblCerrar = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        chbVer = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(46, 14, 54));
        setMinimumSize(new java.awt.Dimension(1090, 620));
        setUndecorated(true);
        setSize(new java.awt.Dimension(1090, 620));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        txtUser.setText("Nombre de Usuario");
        txtUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUserFocusGained(evt);
            }
        });
        txtUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUserMouseClicked(evt);
            }
        });
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserKeyPressed(evt);
            }
        });

        lblOlvidar.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        lblOlvidar.setForeground(new java.awt.Color(46, 14, 54));
        lblOlvidar.setText("¿Olvidaste Contraseña?");
        lblOlvidar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblOlvidar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOlvidarMouseClicked(evt);
            }
        });

        CheckMode.setForeground(new java.awt.Color(255, 255, 255));
        CheckMode.setText("Modo Light");
        CheckMode.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckMode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CheckModeMouseClicked(evt);
            }
        });
        CheckMode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CheckModeKeyPressed(evt);
            }
        });

        lblUsuario.setForeground(new java.awt.Color(46, 14, 54));
        lblUsuario.setText("Usuario:");

        lblContra.setForeground(new java.awt.Color(46, 14, 54));
        lblContra.setText("Contraseña:");

        btnIniciar.setBackground(new java.awt.Color(164, 188, 188));
        btnIniciar.setForeground(new java.awt.Color(46, 14, 54));
        btnIniciar.setText("Iniciar");
        btnIniciar.setBorder(null);
        btnIniciar.setBorderPainted(false);
        btnIniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciar.setDefaultCapable(false);
        btnIniciar.setFocusPainted(false);
        btnIniciar.setFocusable(false);
        btnIniciar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnIniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIniciarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIniciarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIniciarMouseExited(evt);
            }
        });
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        btnIniciar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnIniciarKeyPressed(evt);
            }
        });

        lblCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/CloseN.png"))); // NOI18N
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });

        txtPass.setText("Contraseña");
        txtPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPassFocusGained(evt);
            }
        });
        txtPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPassMouseClicked(evt);
            }
        });
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPassKeyReleased(evt);
            }
        });

        chbVer.setForeground(new java.awt.Color(46, 14, 54));
        chbVer.setText("Mostrar Contraseña");
        chbVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chbVerMouseClicked(evt);
            }
        });
        chbVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbVerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(CheckMode)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(778, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuario)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(jSeparator1))
                        .addGap(103, 103, 103))
                    .addComponent(lblCerrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblOlvidar)
                        .addGap(119, 119, 119))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblContra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chbVer))
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContra)
                    .addComponent(chbVer))
                .addGap(17, 17, 17)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblOlvidar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addComponent(CheckMode)
                .addGap(19, 19, 19))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarMouseClicked
        //Valida los campos vacios
        String contra=new String(txtPass.getPassword());
        if(txtUser.getText().trim().equals("") || txtPass.getText().trim().equals("")){
            notificacion.lblTituloNoti1.setText("Campos vacios");
            notificacion.TAMensajeError.setText("Todos los campos son requeridos");
            notificacion.setVisible(true);
        }
        //Al dar clic se borrará el mensaje que llevan los Jtext por default
        else if(contra.equals("Contraseña") || txtUser.getText().trim().equals("Nombre de Usuario") ){
            FrmNoti1 notificación1 = new FrmNoti1();
            notificación1.lblTituloNoti1.setText("Campos vacios");
            notificación1.TAMensajeError.setText("Todos los campos son requeridos");
            notificación1.setVisible(true);
        }
        //Al validar todo, se envian los datos
        else{
            EnvioDatosLogin();
        }
    }//GEN-LAST:event_btnIniciarMouseClicked
    
    static public FrmContenedor contenedor = new FrmContenedor(); 
    public static void AbirContenedor(){
        contenedor.setVisible(true);
        try{
            ControladorVariables variables = new ControladorVariables();
            contenedor.lblNombre.setText(variables.getNombreE());
            contenedor.lblNivel.setText(variables.getTipoE());
            contenedor.EstadoModo();
            contenedor.ColocarImagen();
            contenedor.AccesosUsuarios();
        }
        catch(Exception e){
            FrmNotiError error = new FrmNotiError();
            error.lblTituloError.setText("Error al cargar");
            error.TAnotiError.setText("Ha ocurrido un error al intentar abrir el formulario Contenedor");
            error.setVisible(true);
        }
    }
    
    public static void CerrarContenedor(){
        contenedor.CerrarSubBotones();
        contenedor.PanelBlanco.setVisible(true);
        PanelPrueba.removeAll();
        PanelPrueba.add(contenedor.PanelBlanco);
        PanelPrueba.revalidate();
        PanelPrueba.repaint();
        contenedor.dispose();
    }
    
    private void lblOlvidarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOlvidarMouseClicked
        this.dispose();
        FrmRecuperarContraseña recuperar = new FrmRecuperarContraseña();
        recuperar.setVisible(true);
    }//GEN-LAST:event_lblOlvidarMouseClicked

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void txtUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUserMouseClicked
        if(txtUser.getText().equals("Nombre de Usuario")){
            txtUser.setText("");
        }
    }//GEN-LAST:event_txtUserMouseClicked

    private void CheckModeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CheckModeMouseClicked
        IdiomaColor();
    }//GEN-LAST:event_CheckModeMouseClicked

    private void txtUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserFocusGained
        if(Contador1 == 0){
            txtUser.setText("");
            Contador1 = 1;
        }
    }//GEN-LAST:event_txtUserFocusGained

    private void btnIniciarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarMouseEntered
        btnIniciar.setBackground(Rojo);
    }//GEN-LAST:event_btnIniciarMouseEntered

    private void btnIniciarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarMouseExited
        btnIniciar.setBackground(Celeste);
    }//GEN-LAST:event_btnIniciarMouseExited

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        //Inicializamos las variables x y y con la posicion actual del form
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        //Creamos metodo que actualice la posicion del form dependendiendo donde el usuario lo coloque
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_formMouseDragged

    private void txtPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyReleased
        // TODO add your handling code here:
        //Comprobamos que la contraseña igual sea la misma en ambos textfield
        //Creamos variable String que guarde los campos
//        String contra=new String(txtContraseña.getPassword());
//        String contrac=new String(txtContraConfirm.getPassword());
//        boolean confirm=obj.contraIgual(contra,contrac);
//        if (confirm==true) {
//            lblAvisoContra.setVisible(false);
//        }else{
//            lblAvisoContra.setVisible(true);
//        }
    }//GEN-LAST:event_txtPassKeyReleased

    private void chbVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chbVerMouseClicked
        // TODO add your handling code here:
        if (chbVer.isSelected()) {
            txtPass.setEchoChar((char)0);
        }else{
            txtPass.setEchoChar('*');
        }
    }//GEN-LAST:event_chbVerMouseClicked

    private void chbVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbVerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbVerActionPerformed

    private void txtPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassFocusGained
        // TODO add your handling code here:
        if(Contador2 == 0){
            txtPass.setText("");
            Contador2=2;
        }
    }//GEN-LAST:event_txtPassFocusGained

    private void txtPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPassMouseClicked
        // TODO add your handling code here:
        if(txtPass.getText().equals("Contraseña")){
            txtPass.setText("");
        }
    }//GEN-LAST:event_txtPassMouseClicked

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void txtUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            //Valida los campos vacios
            if(txtUser.getText().trim().equals("") || txtPass.getText().trim().equals("")){
                notificacion.lblTituloNoti1.setText("Campos vacios");
                notificacion.TAMensajeError.setText("Todos los campos son requeridos");
                notificacion.setVisible(true);
            }
            //Al dar clic se borrará el mensaje que llevan los Jtext por default
            else if(txtPass.getText().trim().equals("Contraseña") || txtUser.getText().trim().equals("Nombre de Usuario") ){
                FrmNoti1 notificación1 = new FrmNoti1();
                notificación1.lblTituloNoti1.setText("Campos vacios");
                notificación1.TAMensajeError.setText("Todos los campos son requeridos");
                notificación1.setVisible(true);
            }
            //Al validar todo, se envian los datos
            else{
                EnvioDatosLogin();
            }
        }
    }//GEN-LAST:event_txtUserKeyPressed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            //Valida los campos vacios
            if(txtUser.getText().trim().equals("") || txtPass.getText().trim().equals("")){
                notificacion.lblTituloNoti1.setText("Campos vacios");
                notificacion.TAMensajeError.setText("Todos los campos son requeridos");
                notificacion.setVisible(true);
            }
            //Al dar clic se borrará el mensaje que llevan los Jtext por default
            else if(txtPass.getText().trim().equals("Contraseña") || txtUser.getText().trim().equals("Nombre de Usuario") ){
                FrmNoti1 notificación1 = new FrmNoti1();
                notificación1.lblTituloNoti1.setText("Campos vacios");
                notificación1.TAMensajeError.setText("Todos los campos son requeridos");
                notificación1.setVisible(true);
            }
            //Al validar todo, se envian los datos
            else{
                EnvioDatosLogin();
            }
        }
    }//GEN-LAST:event_txtPassKeyPressed

    private void btnIniciarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIniciarKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            //Valida los campos vacios
            if(txtUser.getText().trim().equals("") || txtPass.getText().trim().equals("")){
                notificacion.lblTituloNoti1.setText("Campos vacios");
                notificacion.TAMensajeError.setText("Todos los campos son requeridos");
                notificacion.setVisible(true);
            }
            //Al dar clic se borrará el mensaje que llevan los Jtext por default
            else if(txtPass.getText().trim().equals("Contraseña") || txtUser.getText().trim().equals("Nombre de Usuario") ){
                FrmNoti1 notificación1 = new FrmNoti1();
                notificación1.lblTituloNoti1.setText("Campos vacios");
                notificación1.TAMensajeError.setText("Todos los campos son requeridos");
                notificación1.setVisible(true);
            }
            //Al validar todo, se envian los datos
            else{
                EnvioDatosLogin();
            }
        }
    }//GEN-LAST:event_btnIniciarKeyPressed

    private void CheckModeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CheckModeKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            //Valida los campos vacios
            if(txtUser.getText().trim().equals("") || txtPass.getText().trim().equals("")){
                notificacion.lblTituloNoti1.setText("Campos vacios");
                notificacion.TAMensajeError.setText("Todos los campos son requeridos");
                notificacion.setVisible(true);
            }
            //Al dar clic se borrará el mensaje que llevan los Jtext por default
            else if(txtPass.getText().trim().equals("Contraseña") || txtUser.getText().trim().equals("Nombre de Usuario") ){
                FrmNoti1 notificación1 = new FrmNoti1();
                notificación1.lblTituloNoti1.setText("Campos vacios");
                notificación1.TAMensajeError.setText("Todos los campos son requeridos");
                notificación1.setVisible(true);
            }
            //Al validar todo, se envian los datos
            else{
                EnvioDatosLogin();
            }
        }
    }//GEN-LAST:event_CheckModeKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            //Valida los campos vacios
            if(txtUser.getText().trim().equals("") || txtPass.getText().trim().equals("")){
                notificacion.lblTituloNoti1.setText("Campos vacios");
                notificacion.TAMensajeError.setText("Todos los campos son requeridos");
                notificacion.setVisible(true);
            }
            //Al dar clic se borrará el mensaje que llevan los Jtext por default
            else if(txtPass.getText().trim().equals("Contraseña") || txtUser.getText().trim().equals("Nombre de Usuario") ){
                FrmNoti1 notificación1 = new FrmNoti1();
                notificación1.lblTituloNoti1.setText("Campos vacios");
                notificación1.TAMensajeError.setText("Todos los campos son requeridos");
                notificación1.setVisible(true);
            }
            //Al validar todo, se envian los datos
            else{
                EnvioDatosLogin();
            }
        }
    }//GEN-LAST:event_formKeyPressed

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
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FrmLogin().setVisible(true);
            }
        });
    }
    
    private class FondoLogin extends JPanel{
        public Image imagen;
        public void paint(Graphics g){
            imagen = new ImageIcon(getClass().getResource("/Recursos_img/E-Dream Login.png")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CheckMode;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JCheckBox chbVer;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JLabel lblContra;
    private javax.swing.JLabel lblOlvidar;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
