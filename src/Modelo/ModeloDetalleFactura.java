/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Oliver
 */
public class ModeloDetalleFactura {
    private Connection Conect;

    public ModeloDetalleFactura(){
        Conect = Conexion.Conexion();
    }
    public ResultSet CargarProductos(){
        ResultSet productos = null;
        String SQL = "SELECT nombreProducto FROM tbProducto";
        try{
            PreparedStatement Select = Conect.prepareStatement(SQL);
            productos = Select.executeQuery();
        }catch(SQLException e){
            System.out.print("Error al cargar los productos> "+e.toString());
        }
        
        return productos;
    }
    public ResultSet CargarCodigoProductos(){
        ResultSet productos = null;
        String SQL = "SELECT codigoProducto FROM tbProducto";
        try{
            PreparedStatement Select = Conect.prepareStatement(SQL);
            productos = Select.executeQuery();
        }catch(SQLException e){
            System.out.print("Error al cargar los productos> "+e.toString());
        }
        
        return productos;
    }
    public DefaultComboBoxModel Productos(){
        DefaultComboBoxModel producto = new DefaultComboBoxModel();
        ResultSet p = CargarProductos();
        try{
            producto.addElement("Sin seleccionar");
            while(p.next()){
                producto.addElement(p.getString(1));
            }
        }catch(SQLException e){
            System.out.print("Error al cargar las categorias> "+e.toString());
        }
        return producto;
    }
    public ArrayList<String> IDProductos(){
        ArrayList<String> codigo = new ArrayList<>();
        try{
            ResultSet Codigos = CargarCodigoProductos();
            while(Codigos.next()){
                codigo.add(Codigos.getString(1));
            }
        }catch(SQLException e){
            System.out.print("Error al cargar las categorias> "+e.toString());
        }
        return codigo;
    }
        public ResultSet CargarCategorias(){
        ResultSet productos = null;
        String SQL = "SELECT nombreTipo FROM tbCategoriaProducto";
        try{
            PreparedStatement Select = Conect.prepareStatement(SQL);
            productos = Select.executeQuery();
        }catch(SQLException e){
            System.out.print("Error al cargar las categorias> "+e.toString());
        }
        
        return productos;
    }
    public DefaultComboBoxModel Categorias(){
        DefaultComboBoxModel producto = new DefaultComboBoxModel();
        ResultSet p = CargarCategorias();
        try{
            producto.addElement("Sin seleccionar");
            while(p.next()){
                producto.addElement(p.getString("nombreTipo"));
            }
        }catch(SQLException e){
            System.out.print("Error al cargar los producto> "+e.toString());
        }
        return producto;
    }
    
    public Object[] CargarDatos(String codigo){
        Object[] datos = new Object[4];
        boolean encontrar=true;
        ResultSet DatosExtra;
        try{
            String SQL = "SELECT precio, descuento, idCategoriaProducto FROM tbProducto WHERE codigoProducto=?";
            PreparedStatement SELECT = Conect.prepareStatement(SQL);
            SELECT.setString(1, codigo);
            DatosExtra = SELECT.executeQuery();
               if (DatosExtra.next()) { 
                   datos[1] = DatosExtra.getDouble(1);
                   datos[2] = DatosExtra.getInt(2);
                   datos[3] = DatosExtra.getInt(3);
            }else{
                   datos[1] = '-';
                   datos[2] = "-";
                   datos[3] = 0;
               }
        }catch(SQLException e){
            System.out.print("Error "+e.toString());
        }
        try{
            ResultSet Datos;
            String SQL = "SELECT codigoProducto FROM tbProducto";
            PreparedStatement SELECT = Conect.prepareStatement(SQL);
            Datos = SELECT.executeQuery();
            int ID =0;
            while(Datos.next()&&encontrar){
                ID++;
                String Codigillo = Datos.getString(1);
                if (Codigillo.equals(codigo)&&encontrar) {
                    encontrar=false;
                    datos[0] = ID;
                }else{
                    datos[0] = 0;
                }
            }
            
        }catch(SQLException e){
            System.out.print("Error al cargar el producto> "+e.toString());
        }
        return datos;
    }
    public Object[] CargarDatosCMB(String codigo){
        Object[] datos = new Object[4];
        ResultSet informacion;
        try{
            String SQL = "SELECT codigoProducto, precio, descuento, idCategoriaProducto FROM tbProducto WHERE codigoProducto=?";
            PreparedStatement SELECT = Conect.prepareStatement(SQL);
            SELECT.setString(1, codigo);
            informacion = SELECT.executeQuery();
            if (informacion.next()) {
                datos[0] = informacion.getString(1);
                datos[1] = informacion.getDouble(2);
                datos[2] = informacion.getInt(3);
                datos[3] = informacion.getInt(4);
            }
        }catch(Exception e){
            System.out.print(e.toString());
                datos[0] = "-";
                datos[1] = "-";
                datos[2] = "-";
                datos[3] = 0;
        }
        return datos;
    }
    
    public int IDInsertar(){
        int codigo=0;
        String SQL = "SELECT idFacturaRenta FROM tbFacturaRenta WHERE idFacturaRenta = (SELECT MAX(idFacturaRenta) FROM tbFacturaRenta)";
        try{
            PreparedStatement SELECT = Conect.prepareStatement(SQL);
            ResultSet dataEn = SELECT.executeQuery();
            while(dataEn.next()){
                codigo = dataEn.getInt(1);
            }
        }catch(Exception e){
            System.out.print(e.toString());
        }
        return codigo;
    }
    public boolean GuardarFactura(Object[] datos){
        boolean completado=false;
        String SQL = "INSERT INTO tbDetalleFacturaRenta VALUES(?,?,?,?,?,?);";
        try{
            PreparedStatement INSERT = Conect.prepareStatement(SQL);
            System.out.print(datos[0]+"\n");
            System.out.print(datos[1]+"\n");
            System.out.print(datos[2]+"\n");
            System.out.print(datos[3]+"\n");
            System.out.print(datos[4]+"\n");
            INSERT.setInt(1, Integer.parseInt(String.valueOf(datos[0]))); //Cantidad
            INSERT.setDouble(2, Double.parseDouble(String.valueOf(datos[1]))); //Descuento
            INSERT.setDouble(3, Double.parseDouble(String.valueOf(datos[2]))); //CostoTotal
            INSERT.setString(4, String.valueOf(datos[3])); //codigoProducto
            INSERT.setInt(5, Integer.parseInt(String.valueOf(datos[4]))); //IDFactura
            INSERT.setInt(6, 1);//IdEstado
            completado = INSERT.execute();
        }catch(Exception e){
            System.out.print("Modelo>"+e.toString());
        }
        return completado;
    }
    public boolean ActualizarFactura(Object[] datos){
        boolean estado = false;
        String SQL = "UPDATE tbFacturaRenta SET MontoTotalR = ?, adelantoPago = ?, vuelto =? WHERE idFacturaRenta=?";
        try{
            System.out.print(datos[0]+"\n");
            System.out.print(datos[1]+"\n");
            System.out.print(datos[2]+"\n");
            System.out.print(datos[3]+"\n");
            PreparedStatement UPDATE = Conect.prepareStatement(SQL);
            UPDATE.setDouble(1, Double.parseDouble(String.valueOf(datos[0])));
            UPDATE.setDouble(2, Double.parseDouble(String.valueOf(datos[1])));
            UPDATE.setDouble(3, Double.parseDouble(String.valueOf(datos[2])));
            UPDATE.setInt(4, Integer.parseInt(String.valueOf(datos[3])));
            estado = UPDATE.execute();
            
        }catch(Exception e){}
        return estado;
    }
    
    
    
}
