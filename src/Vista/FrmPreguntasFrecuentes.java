/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
//Importamos las librerias a usar
import Controlador.ControladorVariables;
import Recursos_Tipografias.Fuentes;
import Controlador.PreguntasFrecuentesController;
import java.awt.Color;
import java.util.ArrayList;
/**
 *
 * @author Jesus Gerardo
 */
public class FrmPreguntasFrecuentes extends javax.swing.JPanel {
    //Creamos objeto del tipo de fuentes 
    Fuentes tipoFuente= new Fuentes();
    //Creamos objeto de la clase Preguntas frecuentes en el controlador
    PreguntasFrecuentesController preguclr= new PreguntasFrecuentesController();
    //Creamos variable que contabilize la pagina en la que se este
    int pag=1;
    //Creamos variable que indique que preguntas se debera colocar
    int preg=0;
    
    //Colores Modo Night
    Color Rojo = new Color(220,49,72);
    Color Oscuro = new Color(14,13,21);
    //Colores Modo Light
    Color AzulOscuro = new Color(33, 47, 74);
    Color Azul = new Color(32,64,111);
    
    public FrmPreguntasFrecuentes() {
        initComponents();
        Colores();
        txtaMensaje.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,18));
        lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold,0,40));
        btnPreg1.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular,0,20));
        btnPreg2.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular,0,20));
        btnPreg3.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular,0,20));
        btnPreg4.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular,0,20));
        txtaRespuesta.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular,0,20));
        preguclr.Preguntas();
        ArrayList <String> preguntas = new ArrayList<String>(preguclr.getPreguntas());
        btnPreg1.setText(preguntas.get(0));
        btnPreg2.setText(preguntas.get(1));
        btnPreg3.setText(preguntas.get(2));
        btnPreg4.setText(preguntas.get(3));
        PredecirAvaRet();
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
        btnPreg4 = new javax.swing.JTextArea();
        btnPreg3 = new javax.swing.JTextArea();
        btnPreg2 = new javax.swing.JTextArea();
        btnPreg1 = new javax.swing.JTextArea();
        scrpnlRespuesta = new javax.swing.JScrollPane();
        txtaRespuesta = new javax.swing.JTextArea();
        scrpnlMensaje = new javax.swing.JScrollPane();
        txtaMensaje = new javax.swing.JTextArea();
        lblIconoEDreams = new javax.swing.JLabel();
        btnAnteriorPag = new javax.swing.JButton();
        btnSiguientePag = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1090, 620));
        setMinimumSize(new java.awt.Dimension(1090, 620));
        setPreferredSize(new java.awt.Dimension(1090, 620));

        pnlTitulo.setBackground(new java.awt.Color(14, 13, 21));
        pnlTitulo.setPreferredSize(new java.awt.Dimension(144, 44));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Ayuda y Asistencia - Preguntas Frecuentes");
        lblTitulo.setAlignmentX(0.5F);

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(257, 257, 257))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addContainerGap())
        );

        pnlContenedor.setBackground(new java.awt.Color(220, 49, 72));
        pnlContenedor.setMaximumSize(new java.awt.Dimension(1090, 620));
        pnlContenedor.setMinimumSize(new java.awt.Dimension(1090, 620));
        pnlContenedor.setPreferredSize(new java.awt.Dimension(1090, 620));

        btnPreg4.setEditable(false);
        btnPreg4.setBackground(new java.awt.Color(255, 222, 216));
        btnPreg4.setColumns(20);
        btnPreg4.setLineWrap(true);
        btnPreg4.setRows(5);
        btnPreg4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPreg4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPreg4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreg4MouseClicked(evt);
            }
        });

        btnPreg3.setEditable(false);
        btnPreg3.setBackground(new java.awt.Color(255, 222, 216));
        btnPreg3.setColumns(20);
        btnPreg3.setLineWrap(true);
        btnPreg3.setRows(5);
        btnPreg3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPreg3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPreg3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreg3MouseClicked(evt);
            }
        });

        btnPreg2.setEditable(false);
        btnPreg2.setBackground(new java.awt.Color(255, 222, 216));
        btnPreg2.setColumns(20);
        btnPreg2.setLineWrap(true);
        btnPreg2.setRows(5);
        btnPreg2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPreg2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPreg2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreg2MouseClicked(evt);
            }
        });

        btnPreg1.setEditable(false);
        btnPreg1.setBackground(new java.awt.Color(255, 222, 216));
        btnPreg1.setColumns(20);
        btnPreg1.setLineWrap(true);
        btnPreg1.setRows(5);
        btnPreg1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPreg1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPreg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreg1MouseClicked(evt);
            }
        });

        txtaRespuesta.setEditable(false);
        txtaRespuesta.setColumns(20);
        txtaRespuesta.setLineWrap(true);
        txtaRespuesta.setRows(5);
        txtaRespuesta.setText("Seleccione primero una pregunta");
        scrpnlRespuesta.setViewportView(txtaRespuesta);

        scrpnlMensaje.setBorder(null);

        txtaMensaje.setEditable(false);
        txtaMensaje.setBackground(new java.awt.Color(220, 49, 72));
        txtaMensaje.setColumns(20);
        txtaMensaje.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        txtaMensaje.setLineWrap(true);
        txtaMensaje.setRows(5);
        txtaMensaje.setText("\t\t\tBienvenido!\nSelecciona las una de las preguntas  más realizadas en nuestra pagina web sobre el funcionmiento de E-Dream y demás curiosidades respecto al programa. Una vez seleccionada la pregunta automaticamente se mostrara su respuesta en el panel de abajo. Si deseas ver más preguntas solo aprieta los botones de adelante o retroceso y las preguntas cambiaran");
        txtaMensaje.setToolTipText("");
        txtaMensaje.setBorder(null);
        txtaMensaje.setCaretColor(new java.awt.Color(220, 49, 79));
        scrpnlMensaje.setViewportView(txtaMensaje);

        lblIconoEDreams.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Logo-Escenario-pequeño.png"))); // NOI18N

        btnAnteriorPag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Flecha izquierda.png"))); // NOI18N
        btnAnteriorPag.setBorderPainted(false);
        btnAnteriorPag.setContentAreaFilled(false);
        btnAnteriorPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorPagActionPerformed(evt);
            }
        });

        btnSiguientePag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos_img/Flecha derecha.png"))); // NOI18N
        btnSiguientePag.setBorderPainted(false);
        btnSiguientePag.setContentAreaFilled(false);
        btnSiguientePag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguientePagActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addComponent(btnAnteriorPag, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPreg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPreg2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(lblIconoEDreams, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPreg3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPreg4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                .addComponent(btnSiguientePag))
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(scrpnlRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenedorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrpnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(scrpnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(lblIconoEDreams)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContenedorLayout.createSequentialGroup()
                                .addComponent(btnPreg1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPreg2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlContenedorLayout.createSequentialGroup()
                                .addComponent(btnPreg3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPreg4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlContenedorLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAnteriorPag)
                                    .addComponent(btnSiguientePag))
                                .addGap(44, 44, 44)))
                        .addGap(13, 13, 13)))
                .addComponent(scrpnlRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(pnlContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 580, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    void Colores(){
        ControladorVariables variables = new ControladorVariables();
        switch(variables.getColor()){
            case 1://Oscuro
                pnlContenedor.setBackground(Rojo);
                txtaMensaje.setBackground(Rojo);
                pnlTitulo.setBackground(Oscuro);
                txtaMensaje.setForeground(Oscuro);    
                break;
            case 2://Claro
                pnlContenedor.setBackground(Azul);
                txtaMensaje.setBackground(Azul);
                pnlTitulo.setBackground(AzulOscuro);
                txtaMensaje.setForeground(Color.WHITE);
                break;
            default:
                pnlContenedor.setBackground(Rojo);
                pnlTitulo.setBackground(Oscuro);
                txtaMensaje.setForeground(Oscuro);
                break;
        }        
    }
    
    private void btnAnteriorPagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorPagActionPerformed
        // TODO add your handling code here:
        //Disminuimos el numero de pagina
        pag--;
        preg-=4;
        //Creamos array que guarde las preguntas
        //Creamos un arreglo que contenga el arreglo de las preguntas
        ArrayList <String> preguntas = new ArrayList<String>(preguclr.getPreguntas());
        //Colocamos las preguntas según el indice de inicio
        System.out.println(String.valueOf(preg));
        btnPreg1.setText(preguntas.get(preg));
        btnPreg2.setText(preguntas.get(preg+1));
        btnPreg3.setText(preguntas.get(preg+2));
        btnPreg4.setText(preguntas.get(preg+3));
        //Se predice el botón
        PredecirAvaRet();
    }//GEN-LAST:event_btnAnteriorPagActionPerformed

    private void btnSiguientePagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguientePagActionPerformed
        // TODO add your handling code here:
        //Disminuimos el numero de pagina
        pag++;
        preg+=4;
        //Creamos array que guarde las preguntas
        //Creamos un arreglo que contenga el arreglo de las preguntas
        ArrayList <String> preguntas = new ArrayList<String>(preguclr.getPreguntas());
        //Colocamos las preguntas según el indice de inicio
        btnPreg1.setText(preguntas.get(preg));
        btnPreg2.setText(preguntas.get(preg+1));
        btnPreg3.setText(preguntas.get(preg+2));
        btnPreg4.setText(preguntas.get(preg+3));
        //Se predice el botón
        PredecirAvaRet();
    }//GEN-LAST:event_btnSiguientePagActionPerformed

    private void btnPreg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreg1MouseClicked
        // TODO add your handling code here:
        txtaRespuesta.setText(preguclr.Respuesta(btnPreg1.getText()));
    }//GEN-LAST:event_btnPreg1MouseClicked

    private void btnPreg2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreg2MouseClicked
        // TODO add your handling code here:
        txtaRespuesta.setText(preguclr.Respuesta(btnPreg2.getText()));
    }//GEN-LAST:event_btnPreg2MouseClicked

    private void btnPreg3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreg3MouseClicked
        // TODO add your handling code here:
        txtaRespuesta.setText(preguclr.Respuesta(btnPreg3.getText()));
    }//GEN-LAST:event_btnPreg3MouseClicked

    private void btnPreg4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreg4MouseClicked
        // TODO add your handling code here:
        txtaRespuesta.setText(preguclr.Respuesta(btnPreg4.getText()));
    }//GEN-LAST:event_btnPreg4MouseClicked
    
    //Creamos metodo que prediga si existe posibilidad de retroceder o avanzar en las preguntas
    public void PredecirAvaRet(){
        //Creamos un arreglo que contenga el arreglo de las preguntas
        ArrayList <String> preguntas = new ArrayList<String>(preguclr.getPreguntas());
        //Creamos variable que nos diga el numero de paginas que tendra el formulario según las preguntas
        int npag=(preguntas.size())/4;
        //Creamos un proceso que prediga si esta en la ultima o primera pagina para desactivar los botones y que no se puedan usar
//        System.out.println(String.valueOf(preguntas.size()));
//        System.out.println(String.valueOf(pag)+"Numero pagina");
        if (pag>=npag) {
            //Si la pagina es igual al maximo
            btnSiguientePag.setVisible(false);
            
        }else{
            //Se activa al no ser
            btnSiguientePag.setVisible(true);
        }
        if (pag<=1) {
            //Si la pagina es igual al minimo
            btnAnteriorPag.setEnabled(false);
        }
        else{
            //si activa al no ser 
            btnAnteriorPag.setEnabled(true);
        }
    }
    void Posicionbotones(){
//        btnPreg1.setLocation(313, 76);
//        btnPreg2.setLocation(313, pag);
//        btnPreg3.setText(preguntas.get(preg+2));
//        btnPreg4.setText(preguntas.get(preg+3));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAnteriorPag;
    public javax.swing.JTextArea btnPreg1;
    public javax.swing.JTextArea btnPreg2;
    public javax.swing.JTextArea btnPreg3;
    public javax.swing.JTextArea btnPreg4;
    public javax.swing.JButton btnSiguientePag;
    private javax.swing.JLabel lblIconoEDreams;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JScrollPane scrpnlMensaje;
    private javax.swing.JScrollPane scrpnlRespuesta;
    private javax.swing.JTextArea txtaMensaje;
    private javax.swing.JTextArea txtaRespuesta;
    // End of variables declaration//GEN-END:variables
}
