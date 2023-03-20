/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Ericksonn
 */
public class ModeloReporte {

    public String NombreEmpresa(int ID) {
        String Empresa = null;
        try {
            String SQL = "SELECT em.nombreEmpresa, e.idEmpresa FROM tbEmpleado e, tbEmpresa em WHERE em.idEmpresa = e.idEmpresa and idEmpleado=?;";
            PreparedStatement SELECT = Conexion.Conexion().prepareStatement(SQL);
            SELECT.setInt(1, ID);
            ResultSet datos = SELECT.executeQuery();
            while (datos.next()) {
                Empresa = datos.getString("nombreEmpresa");
            }
        } catch (Exception e) {
            System.out.println("Modelo IDEmpresa" + e);
        }
        return Empresa;
    }

    public static ResultSet CargarTabla() {
        ResultSet res;
        try {
            String sql = "SELECT TOP 10 p.nombreProducto, SUM(d.cantidad) RENTADO\n"
                    + "FROM   tbProducto p, tbDetalleFacturaRenta d\n"
                    + "where p.codigoProducto = d.codigoProducto\n"
                    + "GROUP  BY nombreProducto\n"
                    + "ORDER  BY RENTADO DESC;";
            //pide importar clase Prepared Statement
            PreparedStatement cmdselect = Conexion.Conexion().prepareStatement(sql);
            res = cmdselect.executeQuery();
            return res;
        } catch (Exception e) {
            return res = null;
        } finally {
            try {
                Conexion.Conexion().close();
            } catch (Exception e) {

            }
        }
    }

}
