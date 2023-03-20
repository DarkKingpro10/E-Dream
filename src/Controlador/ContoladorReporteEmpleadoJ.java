/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Vista.FrmNotiError;//Ayudara a mostrar un mensaje de error
import Modelo.Conexion;
import java.net.URLDecoder;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import Modelo.ModeloReporteInventarioRenta;//Para usar metodos del modelo que conecten con la bd
import Controlador.ControladorVariables;
/**
 *
 * @author Jesus Gerardo
 */
public class ContoladorReporteEmpleadoJ {
    //Creamos objeto del modelo para acceder a sus clases
    ModeloReporteInventarioRenta model = new ModeloReporteInventarioRenta();
    //Creamos objeto del controlador de variables para acceder a sus variables
    ControladorVariables controller = new ControladorVariables();
    //Creamos variables globales de utilidad
    String empresa = model.obtenerEmpresa(controller.getEmpleado());
    String usuario = (controller.getNombreE()+" "+controller.getApellidoE());
    //Creamos metodo que devuelva un arreglo bidimensional de Strings que son fechas
    HashMap fechas(){
        //Creampos un variable de tipo HashMap que almacene distintos valores
        HashMap<String,String> fechas = new HashMap<String,String>();
        //Creamos un objeto de tipo LocalDate que almacene la fecha del sistema actual a la fecvha que se ejecutara el sistema
        LocalDate fecha = LocalDate.now();
        //Creamos variables que almacenen las fechas que seran parametros
        String añopi= (String.valueOf((fecha.getYear())-1)+"-01"+"-01");//Asignamos la fecha de inicio del año pasado respecto al actual
        String añopf= (String.valueOf((fecha.getYear())-1)+"-12"+"-31");//Asignamos la fecha final del año pasado respecto al actual
        String mespi= (String.valueOf(fecha.getYear())+"-"+String.valueOf((fecha.getMonthValue())-1)+"-01"); //Asignamos el mes pasado respecto al actual
        //Creamos un ciclo for que ayudara a que se obtenga correctamente la fecha fin del año pasado
        //creamos variable del mes fin
        String mespf=null;
        for (int i = 31; i > 0; i--) {
            try{
                //Dentro del try agregamos a un objeto del LocalDate una fecha, 
                //si esta da error es incorrecta continuara restando los dias hasta encontrar la fecha adecuada
                //Esto es en caso el mes sea febrero o parecido donde no se puede definir correctamente un dia de fin
                LocalDate prueba = LocalDate.of(fecha.getYear(),(fecha.getMonthValue())-1,i);
                mespf = (String.valueOf(fecha.getYear())+"-"+String.valueOf((fecha.getMonthValue())-1)+"-"+String.valueOf(i));//Ya que la fecha es correcta, agregamos la fecha de finalizacion del mes anterior
                //Nos salimos del ciclo for ya que ya obtuvimos la fecha correcta
                break;
            }catch(Exception e){                
                //No se hace nada
            }
        }
        fechas.put("anoanteriorI", añopi);//Asiganmos el año anterior inicio
        fechas.put("anoanteriorF", añopf);//Asignamos el año anterior fin
        fechas.put("mesanteriorI", mespi);//Asignamos el mes anterior inicio
        fechas.put("mesanteriorF", mespf);//Asignamos el mes anterior fin
        //Creamos un objeto que 
        return fechas;
    }
    ///Creamos metodo que cargue el reporte    
    public void generarReporteEmpleado(){
        //Creamos una variable de tipo HashMap y le asignamos los valores que retorne
        //al ejecutar el metodo de fechas
        HashMap fecha = fechas();
        String path = "";
        try {
            path = getClass().getResource("/Reportes/ReporteEmpleado.jasper").getPath();
            //Se decodifica por algun caracter especial
            path = URLDecoder.decode(path, "UTF-8");
            //Se crea la conexion
            Connection cn = Conexion.Conexion();
            //Se rea los prametros
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            //  parametros.clear();     
            //el nombre que se dio al parametro en JasperReport fue "p1", y se debe llamar desde Java con
            //ese mismo nombre, a su lado se pasa el valor del parametro
            parametros.put("Empresa", empresa);//Asigno el nombre de la empresa
            parametros.put("usuario", usuario);//Asigno el nombre del usuario
            parametros.put("anoanteriorI", fecha.get("anoanteriorI"));//asigno la fecha de inicio del año anterior
            parametros.put("anoanteriorF", fecha.get("anoanteriorF"));//asigno la fecha de fin del año anterior
            parametros.put("mesanteriorI", fecha.get("mesanteriorI"));//asigno la fecha de inicio del mes anterior
            parametros.put("mesanteriorF", fecha.get("mesanteriorF"));//asigno la fecha de fin del mes anterior
            parametros.put("anoanterior", fecha.get("anoanteriorI"));//asigno la fecha de inicio del año anterior
            parametros.put("mesanterior", fecha.get("mesanteriorI"));//asigno la fecha de inicio del mes anterior
            //Se crea el objeto de reporte
            JasperReport reporte = (JasperReport) JRLoader.loadObject(path);
            //Se crea el objeto impresion de reporte
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, cn);
            //Ahora e crea el vior donde e muestra el reporte
            JasperViewer visor = new JasperViewer(imprimir, false);//False es para dispose
            visor.setTitle("Reporte de empleados");
            visor.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
