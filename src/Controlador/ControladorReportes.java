/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloReporte;
import java.sql.ResultSet;

/**
 *
 * @author Ericksonn
 */
public class ControladorReportes {

    ModeloReporte obj = new ModeloReporte();

    public String CargarNombreEmpresa(int ID) {
        return obj.NombreEmpresa(ID);
    }

    public static ResultSet CargarTablaController() {
        return Modelo.ModeloReporte.CargarTabla();
    }

}
