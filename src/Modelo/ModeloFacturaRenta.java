/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static Modelo.Conexion.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Oliver
 */
public class ModeloFacturaRenta {

    //Se crea la conexión
    private Connection Conect;

    public ModeloFacturaRenta() {
        //Se iguala lo que contendrá la variable de conexión
        Conect = Conexion();
    }

    //Método que se encarga de retornar el modelo del combobox de empleados   
    public DefaultComboBoxModel cmbEmpleado() {
        //Se crea el modelo
        DefaultComboBoxModel Empleados = new DefaultComboBoxModel();
        try {
            ///Se crea la sentencia
            String SQL = "SELECT nombreEmpleado FROM tbEmpleado";
            //Se prepara la sentencia
            PreparedStatement Select = Conect.prepareStatement(SQL);
            //Se guarda lo que devolverá la ejecución
            ResultSet empleados = Select.executeQuery();
            //Se revisa la cantidad de filas que devuelve
            while (empleados.next()) {
                //Se cargan los datos dentro del modelo
                Empleados.addElement(empleados.getString("nombreEmpleado"));
            }
        } catch (Exception e) {
            //Se notifica
            System.out.print(e.toString());
        }
        //Se retorna el modelo
        return Empleados;
    }

    //Método que retornará el modelo para el combobox Empresa
    public DefaultComboBoxModel cmbEmpresa() {
        //Se crea el modelo que se retornará
        DefaultComboBoxModel empresa = new DefaultComboBoxModel();
        try {
            //Sentencia
            String SQL = "SELECT nombreEmpresa FROM tbEmpresa";
            //Preparación
            PreparedStatement Select = Conect.prepareStatement(SQL);
            //Se guarda lo que retorne la ejecución
            ResultSet combo = Select.executeQuery();
            while (combo.next()) {
                //Se cargan los datos dentro del combobox
                empresa.addElement(combo.getString("nombreEmpresa"));
            }
        } catch (Exception e) {
            //Se envía error si en dado caso surge un problema
            System.out.print(e.toString());
        }
        //Se retorna el modelo
        return empresa;
    }

    //Método que se encargan de insert la factura
    public boolean InsertarFecturaRenta(Date FechaActual, Date Fecha, double Monto, String Nombre, String DUI, int Telefono, String Direccion, double adelanto, double vuelto, double mora, int idEmpleado, int idTipo, int idEmpresa) {
        //Se crea el estado
        boolean estado = false;
        //Se crea la sentencia
        String SQL = "INSERT INTO tbFacturaRenta VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            //Se prepara la sentencia
            PreparedStatement Insert = Conect.prepareStatement(SQL);
            //Se insertan los datos a la sentencia
            Insert.setDate(1, FechaActual);
            Insert.setDate(2, Fecha); //Fecha
            Insert.setDouble(3, Monto); //Monto
            Insert.setString(4, Nombre); //Nombre
            Insert.setString(5, DUI); //DUI
            Insert.setDouble(6, Telefono); //Telefono
            Insert.setString(7, Direccion); //Dirección
            Insert.setDouble(8, adelanto); //Adelanto 50% del total
            Insert.setDouble(9, vuelto); //Vuelto (puede ser 0)
            Insert.setDouble(10, mora); //Mora que inicialmente será 0
            Insert.setInt(11, idEmpleado); //IDEmpleado
            Insert.setInt(12, idTipo); //IDTipo
            Insert.setInt(13, idEmpresa); //IDEMpresa
            //Se obtiene el estado de la ejecución
            estado = Insert.execute();
        } catch (SQLException e) {
            //Se envía un error si en en dado caso exista
            System.out.print(e.toString() + "\n");
        }
        //Se retorna el estado
        return estado;
    }

    public int IdEmpresa(int ID) {
        int Empresa = 0;
        try {
            String SQL = "SELECT idEmpresa FROM tbEmpleado WHERE idEmpleado=?;";
            PreparedStatement SELECT = Conect.prepareStatement(SQL);
            SELECT.setInt(1, ID);
            ResultSet datos = SELECT.executeQuery();
            while (datos.next()) {
                Empresa = datos.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Modelo IDEmpresa" + e);
        }
        return Empresa;
    }
}
