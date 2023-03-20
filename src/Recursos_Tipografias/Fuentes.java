/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos_Tipografias;

import java.awt.Font;
import java.io.InputStream;


/**
 *
 * @author lenny
 */

public class Fuentes {
    private Font font = null;
    public String MaxwellBold = "maxwell.bold.ttf";
    public String MaxwellLight = "maxwell.light.ttf";
    public String MaxwellRegular = "maxwell.regular.ttf";
    
    /*FONT.PLAIN = 0, Font.BOLD = 1, Font.ITALIC= 2*/
    
    /*tamanio = float*/

    
    public Font fuente (String fontName, int estilo, float tamanio){
        try{
            //Se carga la fuente
            InputStream inputs = getClass().getResourceAsStream(fontName);
            font = Font.createFont(Font.TRUETYPE_FONT, inputs);
        }catch (Exception ex){
            //Si existe un error se carga fuente por defecto Arial
            font = new Font("Arial", Font.PLAIN, 14);
        }
        Font tfont = font.deriveFont(estilo, tamanio);
        return tfont;
    }
}
