package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import Vista.FrmNotiError;

/**
 *
 * @author Lenny Clase que permite enlazar con SQL Server
 */
public class Conexion {

    public static Connection Conexion() {
        //Se crea un objeto de tipo Conecction "conexion = null"
        Connection conexion = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //*pedira importar import java.sql.DriverManager;*/
            conexion = DriverManager.getConnection("jdbc:sqlserver://LENNYX\\SERVIDORSQL; databaseName = dbEdream2; IntegratedSecurity=true;");
            //conexion = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-KE6MB5J; databaseName=dbEdream; IntegratedSecurity=true;");
            //conexion = DriverManager.getConnection("jdbc:sqlserver://4.tcp.ngrok.io:18259; databaseName=dbEdream; user=Practica; password=Equipo");
            //conexion = DriverManager.getConnection("jdbc:sqlserver://6.tcp.ngrok.io:14312; databaseName=dbEdream; user=Practica; password=Equipo");
            //conexion = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-DBOLE54\\ERICKSONN; databaseName = dbEdream; IntegratedSecurity=true;");
            //conexion = DriverManager.getConnection("jdbc:sqlserver://LEGION-Y545\\MYSERVER; databaseName=dbEdream; IntegratedSecurity=true;");
            //Si "conexion" obtiene la dirección de la base de datos en el sistema local, ya no será null.
            if (conexion != null) {
                //Mandamos al Boton de "Estado de Base: Activo" en el Contenedor la bola Azul
                EstadoConeccion(1);
            }
        } catch (Exception exception) {
            FrmNotiError error = new FrmNotiError();
            error.TAnotiError.setText(exception.getMessage());
            error.setVisible(true);
            System.out.println(exception);
        }
        return conexion;
    }

    public static boolean EstadoConeccion(int valor) {
        if (valor == 1) {
            return true;
        } else {
            return false;
        }

    }
}
