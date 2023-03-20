/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Date;
import Modelo.ModeloFacturaRenta;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Oliver
 */
public class ControladorFacturaRenta {

    //Se crea el objeto del modelo
    ModeloFacturaRenta factura = new ModeloFacturaRenta();

    //Se crea un método que retornará el modelo del combobox lleno para los empleados
    public DefaultComboBoxModel LLenarEmpleados() {
        return factura.cmbEmpleado();
    }

    //Se crea un método que retornará el modelo del combobox lleno para las empresas registradas
    public DefaultComboBoxModel LlenarEmpresa() {
        return factura.cmbEmpresa();
    }

    //Método que se encarga de ejecutar el INSERT y devolver el estado de la misma
    public boolean InsertarFactura(Date FechaActual, Date Fecha, double Monto, String Nombre, String DUI, int Telefono, String Direccion, double adelanto, double vuelto, double mora, int idEmpleado, int idTipo, int idEmpresa) {
        return factura.InsertarFecturaRenta(FechaActual, Fecha, Monto, Nombre, DUI, Telefono, Direccion, adelanto, vuelto, mora, idEmpleado, idTipo, idEmpresa);
    }

    //Método que devuelve el IDEmpresa
    public int CargarID(int ID) {
        return factura.IdEmpresa(ID);
    }
}
