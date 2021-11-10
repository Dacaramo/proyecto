/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.HashMap;
import model.Oferta;

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
        int cantiOfertas = 0, cantiSolicitudes = 0;

        ofertas.put("FUERZAS_MILITARES", new ArrayList<>());
        ofertas.put("GERENCIA", new ArrayList<>());
        ofertas.put("PROFESIONALES_INTELECTUALES", new ArrayList<>());
        ofertas.put("TECNICOS", new ArrayList<>());
        ofertas.put("APOYO_ADMINISTRATIVO", new ArrayList<>());

        while(true) //Aca adentro se manejan todos los eventos de ZeroMQ
        {
            // //TODO: Recibir ofertas por parte del Empleador
            // cantiOfertas++;

            // //Se filtra y se guarda la oferta en base al sector al que corresponda
            // if(filtrarOferta(ofertas, oferta))
            //     System.out.println("Oferta " + oferta.getId() + "filtrada correctamente");

            // //TODO: Recibir solicitudes por parte del Aspirante
            // cantiSolicitudes++;

            // //Se filtra y se guarda la solicitud en base al sector al que corresponda
            // if(filtrarOferta(ofertas, oferta))
            //     System.out.println("Oferta " + oferta.getId() + "filtrada correctamente");

            // //TODO: Guardar la oferta en la DHT
        }
    }
    
}
