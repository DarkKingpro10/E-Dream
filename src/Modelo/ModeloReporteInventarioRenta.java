/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
//Importamos las clases a utilizar
import java.sql.Connection;
import Modelo.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import Vista.*;
/**
 *
 * @author Jesus Gerardo
 */
public class ModeloReporteInventarioRenta {
    //Creamos metodo que obtenga el nombre de la empresa asociado a un empleado
    public String obtenerEmpresa(int id){
        String empresa=null;
        ResultSet res = null;
        try{ 
            String sql = "SELECT epr.nombreEmpresa FROM tbEmpleado as emp, tbEmpresa as epr WHERE emp.idEmpresa = epr.idEmpresa AND emp.idEmpleado=?;";
            //pide importar clase Prepared Statement
            PreparedStatement cmd = Conexion.Conexion().prepareStatement(sql);
            cmd.setInt(1, id);
            res = cmd.executeQuery();
            while(res.next()){
                empresa = res.getString("nombreEmpresa");
            }
         }catch(Exception e ){
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText("¡Erro!No se ha podido completar el proceso de generar reporte, ¡ERROR! al cargar empresa ");
            error.setVisible(true);
        }
        return empresa;
    }
}
