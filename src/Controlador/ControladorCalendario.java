/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.ResultSet;

/**
 *
 * @author lenny
 */
public class ControladorCalendario {
    public static ResultSet TraerFechasControlador(){
        return Modelo.ModeloCalendario.ObtenerFechaRenta();
    }

    public static boolean ActualizarRenta_Controller(){
        return Modelo.ModeloCalendario.ActualizarEstadoRenta();
    }
}
