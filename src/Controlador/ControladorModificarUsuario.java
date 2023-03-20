/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
//Importamos las librerias a utilizar
import Controlador.ControladorVariables;//Libreria para obtener datos preestablecidos en login
import Modelo.ModeloModificarUsuario;//Para usar metodos que obtenegan datos de la bd
import Vista.FrmNotiError;//Para mostrar un error
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 *
 * @author Jesus Gerardo
 */
public class ControladorModificarUsuario {
    //creamos constructor del controlador que guardara actomaticamente las variables
    public ControladorModificarUsuario(){
        //Ejecutamos el metodo que guarda los datos en las variables
        guardarDatos();
    }
    //Creamos objeto del controlador para usar sus metodos 
    ControladorVariables controller = new ControladorVariables();
    //Creamos variables donde se almacenara los datos del empleado
    private String sexo;//Se almacenara el sexo del empleado
    private String telefono;
    private String correo;
    private Date añoNaci;
    private byte[] imagen;
    //Creamos variable de IdUsuario y la inciamos con el id obtenido en el login
    private int IdEmpleado=controller.getEmpleado();
    //Creamos metodo que guarde los datos obtenido del modelo en las variables
    void guardarDatos(){
        //Creamos objeto del modelo
        ModeloModificarUsuario model = new ModeloModificarUsuario();
        //Creamos variable de tipo resultset que guardara los datos obtenidos del modelo
        ResultSet rs = model.datosEmpleado(IdEmpleado);
        try{
            while(rs.next()){
                sexo=rs.getString("sexo");//Asignamos el sexo
                telefono=rs.getString("telefonoEmpleado");//Asiganamos el numero de telefono
                correo=rs.getString("correoEmpleado");//Asignamos el correo
                añoNaci=rs.getDate("edadEmpleado");//Asignamos el año de nacimiento
                imagen=rs.getBytes("imagenEmpleado");//Asignamos la imagen
            }
        }catch(Exception e){
            //Invocamos el formulario de error creando un objeto de su clase
            FrmNotiError error = new FrmNotiError();
            //Enviamos los mensajes de error
            error.TAnotiError.setText("¡Error!No se ha podido cargar los datos en la tabla");
            //Mostramos el formulario
            error.setVisible(true);
        }
    }
    //Creamos metodo con expresiones regulares que verifique que el correo tenga la sintaxis correcta
    public boolean ConfirmarCorreo(String str){
        //Creamos el patron del correo que sera la sintaxis correcta
        // Patrón para validar el email
        Pattern patron = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]{3}))+");
        //Creamos un objeto de la clase matcher que comparará el patron con el email
        Matcher sintaxis = patron.matcher(str);
        if (sintaxis.find()==true) {
            //Como coincide enviamos un valor positivo
            return true;
        }else{
            //Como no existe enviamos un valor falso
            return false;
        }
    }
    //Creamos metodo que nos devuelva la edad del empleado
    public String edadEmpleado(){
        //Creamos un objeto del calendario y obtenemos su instancia
        Calendar calendario = Calendar.getInstance();
        //Añadimos al calendiario la fecha de nacimiento del empleado
        calendario.setTime(añoNaci);
        //Creamos variables año,mes y dia y las inicializamos obteniendo sus respectivos datos del calendario
        int año=calendario.get(Calendar.YEAR);
        int dia=calendario.get(Calendar.DAY_OF_MONTH);
        int mes=(calendario.get(Calendar.MONTH))+1;//Se le suma uno ya que el calendario que se usa posee a enero como indice 0.
        //Creamos dos variables que seran de tipo LocalDate para comparar
        LocalDate f1 = LocalDate.of(año, mes, dia);//Añadimos a la fecha 1 el año,mes y dia
        LocalDate f2 = LocalDate.now();//Obtenemos la fecha actual
        //Creamos una variable de tipo Period que sera el periodo de tiempo entre fecha 1 y fecha 2
        Period periodo = Period.between(f1, f2);
        //retornamos el peridodo convertido en string
        return String.valueOf(periodo.getYears());
    }
    //Creamos metodo para obtener la imagen del empleado
    public ImageIcon cargarImagen(){
        //Creamos una icono de tipó ImageIcon e inicializamos con null
        ImageIcon img=null;
        try{
            //Creamos un objeto de tipo BufferedImage e inicializamos con null
            BufferedImage bufferdImage = null;
            //Creamos un inputStream que contrendra un arreglo de bytes que sera el icono
            InputStream inputStream = new ByteArrayInputStream(imagen);
            //El icono sera igual a una operacion donde una clase ImageIo.read leera 
            //e interpretara el arreglo de bytes
            bufferdImage = ImageIO.read(inputStream);
            //Asiganmos un valor al icono de la imagen (img) y colocamos un reescalado de 
            //0 pixeles adjustando sus dimensiones a 104,140
            img = new ImageIcon(bufferdImage.getScaledInstance(104, 104, 0));
        }catch(Exception e){
            //Al encontrar un error, enviara un icono que representara que el usuario no posee imagen
           img = new ImageIcon("src/Recursos_img/Empleado-ModificarUsuario.png");
        }
        //Regresa el icono de la imagen
        return img;
    }
    //Creamos metodo que ejecute el metodo de actualizar en el modelo
    public boolean ActualizarEmpleado(String nombre, String apellido,int celular, String correo,byte[] img,String sex,int opcion){
        boolean res=false;
        //Creamos objeto del modelo
        ModeloModificarUsuario model = new ModeloModificarUsuario();
        //Ejecutamos el metodo y le enviamos los parametros
        switch(opcion){
            case 1://Quiere actualizar con imagen
                if((model.actualizarEmpleadoImg(IdEmpleado, nombre, apellido, celular, correo, img, sex))==true){
                    res=true;
                }
            break;
            case 2://Actualizar sin imagen
                if((model.actualizarEmpleado(IdEmpleado, nombre, apellido, celular, correo,sex))==true){
                    res=true;
                }
            break;
            default://Por si llega a dar error se envia un valor falso
                res=false;
        }
        return res;
    }
    //Creamos los metodos que devolveran las variables
    /**
     * @return the sexo
     */
    //Devuelve el sexo
    public String getSexo() {
        return sexo;
    }
    /**
     * @return the telefono
     */
    //Devuelve el telefono
    public String getTelefono() {
        return telefono;
    }
    /**
     * @return the telefono
     */
    //Devuelve el correo
    public String getCorreo() {
        return correo;
    } 
}
