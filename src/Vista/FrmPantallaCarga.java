
package Vista;

import Controlador.ControladorLogin;
import com.sun.awt.AWTUtilities;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import Recursos_Tipografias.Fuentes;
import java.awt.Color;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

/**
 *
 * @author lenny
 */
public class FrmPantallaCarga extends javax.swing.JFrame {

    //Creando objeto de la clase
    FondoPanel fondo = new FondoPanel();
    public static FrmNotiError error = new FrmNotiError();
    Fuentes tipoFuente;
    
    private static HashMap Autores = new HashMap();
    private static HashMap Frases = new HashMap();
    private static int Random, count = 1;
    
    public FrmPantallaCarga() {
        //Pasan el valor del objeto de la clase
        this.setContentPane(fondo);
        initComponents();
        lblTMensaje.setBackground(new Color (0,0,0,0));
        AWTUtilities.setWindowOpaque(this, false);
        
        /*Utilizamos new ImageIcon(URL location).getImage() donde el argumento
        *location lo obtenemos a través del método getClass().getResource(String name)
        *que nos devuelve un URL absoluto del recurso que especificamos como String.
        */
        Image icon = new ImageIcon(getClass().getResource("/Recursos_img/E-Dream logo.png")).getImage();
        setIconImage(icon);
        
        tipoFuente = new Fuentes();
        lblTMensaje.setFont(tipoFuente.fuente(tipoFuente.MaxwellBold, 0, 18));
        lblAutor.setFont(tipoFuente.fuente(tipoFuente.MaxwellRegular, 0, 16));  
        
        Autores.put(1,"Franklin D. Roosevelt");
        Autores.put(2,"Mahatma Gandhi");
        Autores.put(3,"Mahatma Gandhi");
        Autores.put(4,"W. Clement Stone");
        Autores.put(5,"Louise Hay");
        Autores.put(6,"Anonimo");
        Autores.put(7,"Napoleon Hill");
        Autores.put(8,"Henry Ford");
        Autores.put(9,"Dalai Lama");
        Autores.put(10,"William Hazlitt.");
        Autores.put(11,"Carrie Wilkerson");
        Autores.put(12,"Og Mandino");
        Autores.put(13,"Woody Allen");
        Autores.put(14,"Sófocles");
        Autores.put(15,"Robert Collier");
        Autores.put(16,"Albert Schweitzer");
        Autores.put(17,"Herman Cain");
        Autores.put(18,"Víctor Hugo");
        Autores.put(19,"Malcolm Forbes");
        Autores.put(20,"Henry Ford");  
        Autores.put(21,"Confucio");  
        Autores.put(22,"Walter Bagehot");  
        Autores.put(23,"William Shakespeare");  
        Autores.put(24,"Horacio");
        Autores.put(25,"J.R.R. Tolkien");
        
        Frases.put(1,"No basta con querer: debes preguntarte a ti mismo qué vas a hacer\n\t     para conseguir lo que quieres.");
        Frases.put(2,"La felicidad aparece cuando lo que piensas, lo que dices y lo que\n\t\t haces está en armonía.");
        Frases.put(3,"\n\tLa acción expresa las distintas prioridades.");
        Frases.put(4,"\n   Apunta a la luna. Si fallas, podrías dar a una estrella.");
        Frases.put(5,"Cómo comienzas tu día es cómo vives tu día. Cómo vives tu día es \n\t\tcómo vives tu vida.");
        Frases.put(6,"La vida es como un piano. Las teclas blancas representan felicidad\ny las negras tristeza. Conforme pasa el tiempo, te das cuenta de que\n\tlas teclas negras también hacen música.");
        Frases.put(7,"Cualquier cosa que la mente del hombre puede concebir y creer, puede\n\t\t  ser conseguida.");
        Frases.put(8,"Cualquier persona que deja de aprender es viejo, ya sea a los veinte\nu ochenta. Cualquier persona que sigue aprendiendo se mantiene\njoven. La cosa más grande en la vida es mantener la mente joven.");
        Frases.put(9,"\n\tCuando pierdas, no pierdas la lección");
        Frases.put(10,"\n\tCuanto más hacemos, más podemos hacer.");
        Frases.put(11,"\nCuanto más tiempo pase sin que actúes, más dinero estás dejando de\n\t\t           ganar.");
        Frases.put(12,"Da siempre lo mejor que tienes. Lo que plantes ahora, lo cosecharás\n\t\t       más tarde.");
        Frases.put(13,"\n\tEl 80% del éxito se basa simplemente en insistir.");
        Frases.put(14,"\n\t       El éxito depende del esfuerzo.");
        Frases.put(15,"\nEl éxito es la suma de pequeños esfuerzos repetidos un día sí y otro\n\t\t        también.");
        Frases.put(16,"\n\tHaz algo maravilloso. La gente va a imitarlo.");
        Frases.put(17,"\nEl éxito no es la clave de la felicidad. La felicidad es la clave del éxito.");
        Frases.put(18,"El éxito no se logra sólo con cualidades especiales. Es sobre todo un\n            trabajo de constancia, de método y de organización.");
        Frases.put(19,"\n\tEl fracaso es éxito si aprendemos de él.");
        Frases.put(20,"\nEl fracaso es solo la oportunidad de comenzar de nuevo de forma\n\t\t  más inteligente.");
        Frases.put(21,"\n\nEl hombre que mueve montañas comienza cargando pequeñas piedras.");
        Frases.put(22,"\nEl mayor placer de la vida es hacer lo que la gente dice que no puedes.");
        Frases.put(23,"\n      El placer y la acción hacen que las horas parezcan cortas.");
        Frases.put(24,"\n      El que ha comenzado, se encuentra ya a medio camino.");
        Frases.put(25,"\nEl trabajo que nunca se empieza es el que tarda más en finalizarse.");
        
        Tiempito();
    }
    
    public void Tiempito(){
        Timer CambioText = new Timer();
        TimerTask duracion2 = new TimerTask(){
            @Override
            public void run(){
            Random  rnd = new Random();
            Random = (int)(rnd.nextDouble() * 20 + 1);
            lblTMensaje.setText(Frases.get(Random).toString());
            lblAutor.setText(Autores.get(Random).toString());
            }
        };
        CambioText.schedule(duracion2, 0, 3000);
        
        TimerTask TareaEnd = new TimerTask(){
            @Override
            public void run(){
                dispose();
                ExistenciaEmpresa();
            }
        };
        CambioText.schedule(TareaEnd, 9000);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAutor = new javax.swing.JLabel();
        lblTMensaje = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(829, 441));
        setUndecorated(true);
        setSize(new java.awt.Dimension(829, 441));

        lblAutor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAutor.setForeground(new java.awt.Color(255, 255, 255));
        lblAutor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAutor.setText("autor");

        lblTMensaje.setEditable(false);
        lblTMensaje.setColumns(20);
        lblTMensaje.setForeground(new java.awt.Color(255, 255, 255));
        lblTMensaje.setRows(5);
        lblTMensaje.setTabSize(5);
        lblTMensaje.setAutoscrolls(false);
        lblTMensaje.setBorder(null);
        lblTMensaje.setFocusable(false);
        lblTMensaje.setMaximumSize(new java.awt.Dimension(220, 50));
        lblTMensaje.setMinimumSize(new java.awt.Dimension(220, 50));
        lblTMensaje.setOpaque(false);
        lblTMensaje.setPreferredSize(new java.awt.Dimension(220, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(lblAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(183, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(355, Short.MAX_VALUE)
                .addComponent(lblTMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAutor)
                .addGap(22, 22, 22))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FrmPantallaCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPantallaCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPantallaCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPantallaCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run(){
                new FrmPantallaCarga().setVisible(true);
            }
        });
    }
    
    void ExistenciaEmpresa(){
        int ExistenciaEmpresa = ControladorLogin.ValidarExistenciaEmpresa_Controller();
        switch(ExistenciaEmpresa){
            case 1:
                    ExistenciaPrimerUsuario();
                break;
            case 2:
                FrmPrimerUso primerU = new FrmPrimerUso();
                primerU.setVisible(true);
                break;
            case 3:
                error.lblTituloError.setText("Error de existencia");
                error.TAnotiError.setText("Ha ocurrido un error al momento de validar la existencia de una Empresa.");
                break;
            default:
                break;
        }
    }
    
    void ExistenciaPrimerUsuario(){
        int ExistenciaPrimerUsuario = ControladorLogin.ValidarExistenciaPrimerUsuario_Controller();
        switch(ExistenciaPrimerUsuario){
            case 1:
                FrmLogin login = new FrmLogin();
                login.setVisible(true);
                break;
            case 2:
                FrmPrimerUsoUsuario primerUsuario = new FrmPrimerUsoUsuario();
                primerUsuario.setVisible(true);
                break;
            case 3:
                error.lblTituloError.setText("Error de existencia");
                error.TAnotiError.setText("Ha ocurrido un error al momento de validar la existencia de un Primer Usuario.");
                break;
            default:
                break;
        }
    }
    
    private class FondoPanel extends JPanel {
        public Image imagen;
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/Recursos_img/E-Dream Pantalla de Carga 4.png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel lblAutor;
    private static javax.swing.JTextArea lblTMensaje;
    // End of variables declaration//GEN-END:variables
}
