/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
//Importamos las librerias a utilizar
import Controlador.ControladorVariables;////Libreria para obtener datos preestablecidos en login
import Recursos_Tipografias.Fuentes;//Para usar metodos que obtenegan datos de la bd
import Controlador.ControladorModificarUsuario;//Para obtener el resto de datos del usuario
import Controlador.Validaciones;//Para usar las validaciones
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Jesus Gerardo
 */
public class FrmModificarUsuario extends javax.swing.JPanel {
    //Creamos objeto de la clase de Fuentes
    Fuentes tipoFuente= new Fuentes();
    //Creamos objeto del controlador de la clase validaciones
    Validaciones validaciones = new Validaciones();
    //Creamos variable String que definira si hay una imagen a actualizar o no hay
    String ruta=null;
    
    //Colores Modo Night
    Color Rojo = new Color(220,49,72);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Azul = new Color(32,64,111);
    
    public FrmModificarUsuario() {
        initComponents();
        Colores();
        //Ejecutamos metodo que cargue todos los datos del perfil del empleado
        CargarDatos();
        //Le asiganamos una fuente a cada componente del form
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,40));
        lblMensaje.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,20));
        lblUsuario.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,20));
        lblNombre.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,20));
        lblApellido.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,20));
        lblDUI.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,20));
        lblEdad.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,20));
        lblTelefono.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,20));
        lblCorreo.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,20));
        lblTipoUser.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,20));
        lblTipoUser1.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,20));
        lblCambiarImg.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,20));
        btnCancelar.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,20));
        btnGuardar.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,20));
        lblSexo.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,20));
        cmbSexo.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular,0,20));
        //Ocultamos el mensaje que nos dira si la sintaxis del correo esta mala
        lblCorreoInvalido.setVisible(false);
        //Ejecutamos el metodo de validaciones
        Validaciones();
    }
    
    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                pnlContenedor.setBackground(Rojo);
                pnlTitulo.setBackground(Oscuro);
                lblMensaje.setForeground(Oscuro);
                lblUsuario.setForeground(Oscuro);
                lblTipoUser.setForeground(Oscuro);       
                lblTipoUser1.setForeground(Oscuro);
                lblCambiarImg.setForeground(Oscuro);        
                lblNombre.setForeground(Oscuro);
                lblDUI.setForeground(Oscuro);
                lblTelefono.setForeground(Oscuro);
                lblSexo.setForeground(Oscuro);
                lblApellido.setForeground(Oscuro);  
                lblEdad.setForeground(Oscuro);
                lblCorreo.setForeground(Oscuro);        
                break;
            case 2://Claro
                pnlContenedor.setBackground(Azul);
                pnlTitulo.setBackground(AzulOscuro);
                lblMensaje.setForeground(Color.WHITE);
                lblUsuario.setForeground(Color.WHITE);
                lblTipoUser.setForeground(Color.WHITE);       
                lblTipoUser1.setForeground(Color.WHITE);
                lblCambiarImg.setForeground(Color.WHITE);        
                lblNombre.setForeground(Color.WHITE);
                lblDUI.setForeground(Color.WHITE);
                lblTelefono.setForeground(Color.WHITE);
                lblSexo.setForeground(Color.WHITE);
                lblApellido.setForeground(Color.WHITE);  
                lblEdad.setForeground(Color.WHITE);
                lblCorreo.setForeground(Color.WHITE);
                break;
            default:
                pnlContenedor.setBackground(Rojo);
                pnlTitulo.setBackground(Oscuro);
                lblMensaje.setForeground(Oscuro);
                lblUsuario.setForeground(Oscuro);
                lblTipoUser.setForeground(Oscuro);       
                lblTipoUser1.setForeground(Oscuro);
                lblCambiarImg.setForeground(Oscuro);        
                lblNombre.setForeground(Oscuro);
                lblDUI.setForeground(Oscuro);
                lblTelefono.setForeground(Oscuro);
                lblSexo.setForeground(Oscuro);
                lblApellido.setForeground(Oscuro);  
                lblEdad.setForeground(Oscuro);
                lblCorreo.setForeground(Oscuro);
                break;
        }        
    }
    
    //Creamos metodo que cargue cada componenete del formulario
    void CargarDatos(){
        //Creamos objeto del controlado de variable para obtener metodos precargados
        ControladorVariables variable = new ControladorVariables();
        //Creamos objeto de la clase del controlador de Modificar Usuario
        ControladorModificarUsuario controller = new ControladorModificarUsuario();
        //Asiganamos a cada componente los datos respectivos
        lblUsuario.setText("Usuario: "+ variable.getUsuario());//Asignamos el usuario
        lblTipoUser1.setText(variable.getTipoE());//Asignamos el tipo de usuario
        txtNombreEmpleado.setText(variable.getNombreE());//Asignamos el nombre del empleado
        txtApellido.setText(variable.getApellidoE());//Asignamos el apellido del empleado
        txtDUI.setText(variable.getDUI());//Asignamos el dui del empleado
        //Evaluamos el sexo del empleado si es maculino
        if (controller.getSexo().equals("Masculino")||controller.getSexo().equals("masculino")||controller.getSexo().equals("M")||controller.getSexo().equals("m")) {
            //Asiganmos masculino
            cmbSexo.setSelectedItem("Masculino");
        }else{
            //Como no es masculino colocamos femenino
            cmbSexo.setSelectedItem("Femenino");
        }
        txtTelefono.setText(controller.getTelefono());//Colocamos el número de telefono
        txtCorreo.setText(controller.getCorreo());//Colocamos el correo
        txtEdad.setText(controller.edadEmpleado());
        //Creamos una variable de tipo ImageIcon y le asignamos el valor de la imagen
        ImageIcon img = controller.cargarImagen();
        //Agregamos la imagen al componente
        lblImagen.setIcon(img);
    }
    //Creamos metodo de validaciones
    void Validaciones(){
        //Validamos los componentes en los que no se podra copiar
        //Creamos un arreglo de tipo JComponent donde indicaremos lo componentes que no queremos que se copie
        JComponent[] copiar ={txtDUI,txtTelefono};
        //Invocamos el objeto del controlador y el metodo pasando como parametro el arreglo
        validaciones.noCopiar(copiar);
        //Validamos los componentes en los que no se podra pegar
        JComponent[] pegar = {txtTelefono,txtNombreEmpleado,txtApellido};
        //Invocamos el objeto del controlador y el metodo pasando como parametro el arreglo
        validaciones.noPegar(pegar);
    }
    //Metodo que obtiene la imagen
    void obtenerImg(){
        //Creamos un objeto de tipo JFileChooser que sera la venta que busacara por los archivos
        JFileChooser fileChooser = new JFileChooser(); 
        //Creamos un filtro de archivos para que no se acepten cualquier tipo de archivo
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
        //añadimos los filtros a la ventana
        fileChooser.setFileFilter(extensionFilter);
        //Si se selecciona un archivo
        if (fileChooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
            //La ruta obtiene la direccion del archivo
            ruta = fileChooser.getSelectedFile().getAbsolutePath();
            //Se crea una imagen que tendra el icono del archivo
            Image icono = new ImageIcon(ruta).getImage();
            //Se reescala la imagen a un icono con las medidas del label y renderizado suave
            ImageIcon img = new ImageIcon(icono.getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH));
            //Se añade la imagen al componente
            lblImagen.setIcon(img);
        }
    }
    //metodo que regresa un arreglo de bytes que sera la imagen seleccionada
    private byte[] cargarImagen(){
        try{
           //Creamos un objeto de tipo File que sera igual al archivo que se encuentre en la ruta 
           File imagen = new File(ruta); 
           //Creamos un arreglo de bytes que obtendra bytes de numeros que seran iguales al tamaño del archivo
           byte[] icono = new byte[(int) imagen.length()];
           //Creamos un objeto de tipo InputStream que poseera el archivo y ayudara a leer la imagen
           InputStream input = new FileInputStream(imagen);
           //Leemos el archivo de la imagen con ayuda del input.read que leera el archivo
           input.read(icono);
//           devolvemos el valor de icono
           return icono;
        }catch(Exception e){
            System.out.println(e);
            byte[] no = {1};
            return no;
        }
    }
    //metodo de actualizar con imagen
    void actImagen(){
        //Creamos objeto de la clase del controlador de Modificar Usuario
        ControladorModificarUsuario controller = new ControladorModificarUsuario();
       //Ejecutamos el metodo de actualizar y enviamos sus parametros y si este
        //si este resulta verdadero sigifica que se ejecuto
        if ((controller.ActualizarEmpleado(txtNombreEmpleado.getText(), 
            txtApellido.getText(), Integer.parseInt(txtTelefono.getText()), 
            txtCorreo.getText(), cargarImagen(), String.valueOf(cmbSexo.getSelectedItem()),1))==true) {
            FrmNoti1 error = new FrmNoti1();
            //Enviamos el error y lo mostramos
            error.lblTituloNoti1.setText("¡Excelente!");
            error.TAMensajeError.setText("Se actualizo el empleado exitosamente");
            error.setVisible(true);
        } 
    }
    //metodo que actualice sin imagen
    void actEmpleado(){
       //Ejecutamos el metodo de actualizar y enviamos sus parametros y si este
        //si este resulta verdadero sigifica que se ejecuto
//        //Creamos objeto de la clase del controlador de Modificar Usuario
        ControladorModificarUsuario controller = new ControladorModificarUsuario();
       //Ejecutamos el metodo de actualizar y enviamos sus parametros y si este
        //si este resulta verdadero sigifica que se ejecuto
        if ((controller.ActualizarEmpleado(txtNombreEmpleado.getText(), 
            txtApellido.getText(), Integer.parseInt(txtTelefono.getText()), 
            txtCorreo.getText(), cargarImagen(), String.valueOf(cmbSexo.getSelectedItem()),2))==true) {
            FrmNoti1 error = new FrmNoti1();
            //Enviamos el error y lo mostramos
            error.lblTituloNoti1.setText("¡Excelente!");
            error.TAMensajeError.setText("Se actualizo el empleado exitosamente");
            error.setVisible(true);
        } 
    }
    //Creamos metodo que actualize los datos
    void actualizarDatos(){
       //Comprobamos que no existan campos vacios
        if (txtNombreEmpleado.getText().isEmpty() || txtApellido.getText().isEmpty()
            ||txtTelefono.getText().isEmpty()) {
            //Creamos objeto del formulario 1
            FrmNoti1 error = new FrmNoti1();
            //Enviamos el error y lo mostramos
            error.lblTituloNoti1.setText("¡Algo no esta bien!");
            error.TAMensajeError.setText("!Campos vacios!evisa que todos los campos esten lleno");
            error.setVisible(true);
        }else{
            //Revisamos la sintaxis del correo
            if (lblCorreoInvalido.isVisible()) {
                //Creamos objeto del formulario 1
                FrmNoti1 error = new FrmNoti1();
                //Enviamos el error y lo mostramos
                error.lblTituloNoti1.setText("¡Algo no esta bien!");
                error.TAMensajeError.setText("Correo invalido, por favor revisa el correo de nuevo");
                error.setVisible(true);
            }else{
                //Como esta buena podemos continuar
                //Evaluamos si hubo actualización en la imagen
                if (ruta==null) {
                    //Como hubo no actualizacion ejecutamos un metodo de actualizacion sin imagen
                    actEmpleado();
                }else{
                    //Como hubo actualizacion ejecutamos un metodo en actualizacion con imagen
                    actImagen();
                }
            }
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

        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlContenedor = new javax.swing.JPanel();
        lblImagen = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblTipoUser = new javax.swing.JLabel();
        lblTipoUser1 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombreEmpleado = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lblDUI = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtDUI = new javax.swing.JTextField();
        lblMensaje = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JToggleButton();
        btnCancelar = new javax.swing.JButton();
        btnAgregarImagen = new javax.swing.JButton();
        lblCambiarImg = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        cmbSexo = new javax.swing.JComboBox<>();
        txtTelefono = new javax.swing.JTextField();
        lblCorreoInvalido = new javax.swing.JLabel();
        lblCorreoInvalido1 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1090, 620));

        pnlTitulo.setBackground(new java.awt.Color(14, 13, 21));
        pnlTitulo.setPreferredSize(new java.awt.Dimension(144, 44));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Perfil");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pnlContenedor.setBackground(new java.awt.Color(220, 49, 72));
        pnlContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Empleado-ModificarUsuario.png"))); // NOI18N
        lblImagen.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        pnlContenedor.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, -1, -1));

        lblUsuario.setText("Usuario:");
        pnlContenedor.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        lblTipoUser.setText("Tipo de Usuario:");
        pnlContenedor.add(lblTipoUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        lblTipoUser1.setText("Cajero");
        pnlContenedor.add(lblTipoUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, -1, -1));

        lblNombre.setText("Nombre Empleado:");
        pnlContenedor.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, -1, -1));

        txtNombreEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreEmpleadoKeyTyped(evt);
            }
        });
        pnlContenedor.add(txtNombreEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 160, -1));

        lblApellido.setText("Apellido Empleado:");
        pnlContenedor.add(lblApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, -1, -1));

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });
        pnlContenedor.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 210, 170, -1));

        lblDUI.setText("DUI:");
        pnlContenedor.add(lblDUI, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 30, -1));

        lblCorreo.setText("Correo:");
        pnlContenedor.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 310, -1, -1));

        txtEdad.setEditable(false);
        pnlContenedor.add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(828, 262, 170, -1));

        lblTelefono.setText("Teléfono:");
        pnlContenedor.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 90, -1));

        txtDUI.setEditable(false);
        txtDUI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDUIActionPerformed(evt);
            }
        });
        pnlContenedor.add(txtDUI, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 270, 160, -1));

        lblMensaje.setText("Si deseas cambiar tus datos solo modificalos y aprieta el boton guardar.¡Usar datos reales!");
        pnlContenedor.add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, -1));

        lblEdad.setText("Edad:");
        pnlContenedor.add(lblEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, -1, -1));

        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoKeyReleased(evt);
            }
        });
        pnlContenedor.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 310, 170, -1));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Guardar 52px.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGuardar.setIconTextGap(10);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlContenedor.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 450, 160, 60));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/cancel_52px.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCancelar.setIconTextGap(10);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlContenedor.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, 160, 60));

        btnAgregarImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Cargar50px.png"))); // NOI18N
        btnAgregarImagen.setContentAreaFilled(false);
        btnAgregarImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarImagenActionPerformed(evt);
            }
        });
        pnlContenedor.add(btnAgregarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, -1, -1));

        lblCambiarImg.setText("Cambiar Imagen:");
        pnlContenedor.add(lblCambiarImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, -1, -1));

        lblSexo.setText("Sexo:");
        pnlContenedor.add(lblSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, -1, -1));

        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));
        pnlContenedor.add(cmbSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 380, 160, -1));

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        pnlContenedor.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 310, 160, -1));

        lblCorreoInvalido.setForeground(new java.awt.Color(0, 0, 102));
        lblCorreoInvalido.setText("¡Correo Invalido!");
        pnlContenedor.add(lblCorreoInvalido, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 340, -1, -1));

        lblCorreoInvalido1.setForeground(new java.awt.Color(0, 0, 102));
        lblCorreoInvalido1.setText("Teléfono sin guión");
        pnlContenedor.add(lblCorreoInvalido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
            .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDUIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDUIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDUIActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtNombreEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEmpleadoKeyTyped
        // TODO add your handling code here:
        //Validamos que solo se escriban letras y se admita espacio
        validaciones.soloLetras(evt, 2, txtNombreEmpleado.getText());
    }//GEN-LAST:event_txtNombreEmpleadoKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        // TODO add your handling code here:
        //Validamos que solo se escriban letras y se admita espacio
        validaciones.soloLetras(evt, 2, txtApellido.getText());
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
        //Validamos que solo se permitan numeros
        validaciones.soloNumeros(evt, 1, txtTelefono.getText());
        //Validamos que solo se ingresen 8 digitos
        validaciones.Cantidad(evt, txtTelefono.getText(), 8);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyReleased
        // TODO add your handling code here:
        //Creamos obeto del controlador
        //Creamos objeto de la clase del controlador de Modificar Usuario
        ControladorModificarUsuario controller = new ControladorModificarUsuario();
        //Validaremos que la sintaxis del correo este correcta
        //Invocamos a un metodo en el controlador donde se evalue si el formato del correo es el correcto
        boolean confirm = controller.ConfirmarCorreo(txtCorreo.getText());
        //Verificamos si el resultado es null o false
        if (confirm==true) {
            lblCorreoInvalido.setVisible(false);
        }else{
            lblCorreoInvalido.setVisible(true);
        }
    }//GEN-LAST:event_txtCorreoKeyReleased

    private void btnAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImagenActionPerformed
        // TODO add your handling code here:
        //Asignamos el metodo que obtiene la imagen
        obtenerImg();
    }//GEN-LAST:event_btnAgregarImagenActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        actualizarDatos();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        //Como se cancelo colocamos los valores a su estado original
        ruta=null;
        CargarDatos();
    }//GEN-LAST:event_btnCancelarActionPerformed
         
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarImagen;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JToggleButton btnGuardar;
    private javax.swing.JComboBox<String> cmbSexo;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblCambiarImg;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblCorreoInvalido;
    private javax.swing.JLabel lblCorreoInvalido1;
    private javax.swing.JLabel lblDUI;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipoUser;
    private javax.swing.JLabel lblTipoUser1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDUI;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNombreEmpleado;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}

