/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloReporte;

/**
 *
 * @author Ericksonn
 */
public class ControladorReporte {
   
    ModeloReporte obj = new ModeloReporte();
    
    public String CargarNombreEmpresa(int ID) {
        return obj.NombreEmpresa(ID);
    }
    
}