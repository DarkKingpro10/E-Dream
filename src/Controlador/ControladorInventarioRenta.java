/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
//Importamos las librerias a usar
import Vista.FrmNotiError;//Ayudara a mostrar un mensaje de error
import Modelo.ModeloReporteInventarioRenta;//Para usar metodos del modelo que conecten con la bd
import Controlador.ControladorVariables;//Para usar variables generales
import Modelo.Conexion;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Jesus Gerardo
 */
public class ControladorInventarioRenta {
    //Creamos objeto del modelo para acceder a sus clases
    ModeloReporteInventarioRenta model = new ModeloReporteInventarioRenta();
    //Creamos objeto del controlador de variables para acceder a sus variables
    ControladorVariables controller = new ControladorVariables();
    //Creamos variables globales de utilidad
    String empresa = model.obtenerEmpresa(controller.getEmpleado());
    String usuario = (controller.getNombreE()+" "+controller.getApellidoE());
    //Creamos metodo que genere el reporte de renta con los productos que si se han rentado
    public void generarRentaSi(String fecha1, String fecha2){
        // TODO add your handling code here:
        String path = "";
        try {
            path = getClass().getResource("/Reportes/ReporteInventarioSIRentado.jasper").getPath();
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
            parametros.put("Empresa", empresa);
            parametros.put("usuario", usuario);
            parametros.put("fecha1", fecha1);
            parametros.put("fecha2", fecha2);
            //Se crea el objeto de reporte
            JasperReport reporte = (JasperReport) JRLoader.loadObject(path);
            //Se crea el objeto impresion de reporte
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, cn);
            //Ahora e crea el vior donde e muestra el reporte
            JasperViewer visor = new JasperViewer(imprimir, false);//False es para dispose
            visor.setTitle("Reporte de productos rentados");
            visor.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Creamos metodo que genere el reporte de renta con los productos que no se han rentado
    public void generarRentaNo(){
        // TODO add your handling code here:
        String path = "";
        try {
            path = getClass().getResource("/Reportes/ReporteInventarioRentado.jasper").getPath();
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
            parametros.put("Empresa", empresa);
            parametros.put("usuario", usuario);
            //Se crea el objeto de reporte
            JasperReport reporte = (JasperReport) JRLoader.loadObject(path);
            //Se crea el objeto impresion de reporte
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, cn);
            //Ahora e crea el vior donde e muestra el reporte
            JasperViewer visor = new JasperViewer(imprimir, false);//False es para dispose
            visor.setTitle("Reporte de productos no rentados");
            visor.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
