/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
//Importamos las librerias a utilizar
import java.awt.List;
import java.util.ArrayList;
import Modelo.ModelPreguntasFrecuentes;
import Vista.FrmPreguntasFrecuentes;
import java.sql.ResultSet;
/**
 *
 * @author Jesus Gerardo
 */
public class PreguntasFrecuentesController {
    //Creamos un ArrayList de preguntas que almacenara las preguntas
    private ArrayList <String> Preguntas = new ArrayList<String>();
    /**
     * @return the Preguntas
     */
    public ArrayList <String> getPreguntas() {
        return Preguntas;
    }
    //Creamos metodo que añada las preguntas de la base de datos al ArrayList
    public void Preguntas(){
        //Creamos objeto de la clase del modelo de las preguntas
        ModelPreguntasFrecuentes obj = new ModelPreguntasFrecuentes();
        //Creamos una variable de tipo ResultSet que sea igual al metodo Preguntas dentro del modelo
        ResultSet res = obj.CargarPreguntasFrecuentes();
        //Añadimos al arrayList Preguntas todas las preguntas de la bd
        try{
            while(res.next()){
                Preguntas.add(res.getString("pregunta"));
            }
            res.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    //Creamos metodo que cargue las preguntas en los botones
    public String Respuesta(String str){
        //Creamos objeto de la clase del modelo de las preguntas
        ModelPreguntasFrecuentes obj = new ModelPreguntasFrecuentes();
        //Creamos una variable de tipo ResultSet que sea igual al metodo Preguntas dentro del modelo
        ResultSet res = obj.CargarPreguntasFrecuentes();
        //Creamos variable que sera la respuesta
        String r="";
        //Evaluamos si la preguntas consultada "str" es igual a una pregunta para obtener su respuesta
        try{
//            for (int i = 0; i < Preguntas.size(); i++) {
//                while(res.next()){
//                    if (str.equals(Preguntas.get(i))) {
//                        r=res.getString("respuesta");
//                        res.close();
//                        return r;
//                    }else{
//                        i++;
//                    }
//                }
//            }
                int i=0;
                while(res.next()){
                    if (str.equals(Preguntas.get(i))) {
                        r=res.getString("respuesta");
                        res.close();
                        return r;
                    }else{
                        i++;
                    }
                }
        }catch(Exception e){
            System.out.println(e);
        }
        return r;
    }
}
