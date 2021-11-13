/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.HashMap;
import model.Oferta;
import model.Solicitud;

/**
 *
 * @author HP-PC
 */
public class Filtro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashMap<String, ArrayList<Oferta>> ofertas = new HashMap<String, ArrayList<Oferta>>();
        HashMap<String, ArrayList<Solicitud>> solicitudes = new HashMap<String, ArrayList<Solicitud>>();
        int cantiOfertas = 0, cantiSolicitudes = 0;

        ofertas.put("FUERZAS_MILITARES", new ArrayList<>());
        ofertas.put("GERENCIA", new ArrayList<>());
        ofertas.put("PROFESIONALES_INTELECTUALES", new ArrayList<>());
        ofertas.put("TECNICOS", new ArrayList<>());
        ofertas.put("APOYO_ADMINISTRATIVO", new ArrayList<>());
        
        solicitudes.put("FUERZAS_MILITARES", new ArrayList<>());
        solicitudes.put("GERENCIA", new ArrayList<>());
        solicitudes.put("PROFESIONALES_INTELECTUALES", new ArrayList<>());
        solicitudes.put("TECNICOS", new ArrayList<>());
        solicitudes.put("APOYO_ADMINISTRATIVO", new ArrayList<>());

        while(true) //Aca adentro se manejan todos los eventos de ZeroMQ
        {            
//            if(/*TODO: Si se recibe una oferta por parte del Empleador*/){
//                //Se filtra y se guarda la oferta en el filtro en base al sector (HashMap de ofertas)
//                if(ofertas.get(oferta.getSector().toString()).add(oferta)){
//                    System.out.println("Oferta " + oferta.getId() + "filtrada correctamente");
//                    cantiOfertas++;
//                }   
//            }
               
//            if(/*TODO: Si se Recibe una solicitud por parte del Aspirante*/){
//                //Se filtra y se guarda la solicitud en el filtro en base al sector (HashMap de solicitudes)
//                if(solicitudes.get(solicitud.getSector().toString()).add(solicitud)) {
//                    System.out.println("Solicitud " + solicitud.getId() + "filtrada correctamente");
//                    cantiSolicitudes++;
//                }
//            }
  
            if(cantiOfertas == 10){
                //TODO: Guardar las ofertas recolectadas en la DHT
            }
            
            if(cantiSolicitudes == 10){
                //TODO: Guardar las solicitudes recolectadas en la DHT
            }
        }
    }
    
    public static boolean fitrarOferta(HashMap<String, ArrayList<Oferta>> ofertas, Oferta oferta){
        return ofertas.get(oferta.getSector().toString()).add(oferta);
    }
    
}
