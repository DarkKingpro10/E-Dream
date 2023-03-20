package Controlador;

import java.sql.ResultSet;
import Modelo.ModeloDistribuidor;
import java.util.ArrayList;

/**
 *
 * @author Ericksonn
 */
public class ControladorDistribuidor {

    /**
     * @return the textoB
     */
    public String getTextoB() {
        return textoB;
    }

    /**
     * @param textoB the textoB to set
     */
    public void setTextoB(String textoB) {
        this.textoB = textoB;
    }

    ModeloDistribuidor modelo = new ModeloDistribuidor();

    public boolean IngresarDistribuidorController() {
        return Modelo.ModeloDistribuidor.IngresarDistribuidor(nombreDistribuidor);
    }

    public boolean ActualizarDistribuidorController() {
        return Modelo.ModeloDistribuidor.ActualizarDistribuidor(nombreDistribuidor, idDistribuidor);
    }

    public boolean EliminarDistribuidorController() {
        return Modelo.ModeloDistribuidor.EliminarDistribuidor(idDistribuidor);
    }

    public static ResultSet CargarTablaController() {
        return Modelo.ModeloDistribuidor.CargarTabla();
    }

    public ArrayList<Integer> CargarIdDistribuidor() {
        return modelo.IDDistribuidor();
    }

    public Object[] CargarMenu(Integer Identificador) {
        return Modelo.ModeloDistribuidor.CargarDatosDistribuidor(Identificador);
    }
    //Cremos metodo que manda parametro del distribuidor
    public static ResultSet BuscadorD() {
        return Modelo.ModeloDistribuidor.BuscadorDistribuidor(textoB);
    }

    //Decalracion de variables  
    private static String nombreDistribuidor;
    private static Integer idDistribuidor;
    private static Integer idDisVista;
    private static String textoB;

    public String getNombreDistribuidor() {
        return nombreDistribuidor;
    }

    public void setNombreDistribuidor(String nombreDistribuidor) {
        this.nombreDistribuidor = nombreDistribuidor;
    }

    public Integer getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(Integer idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public Integer getIdDisVista() {
        return idDisVista;
    }

    public void setIdDisVista(Integer idDisVista) {
        this.idDisVista = idDisVista;
    }
}
