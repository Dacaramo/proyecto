package proyecto;

import java.util.ArrayList;
import java.util.HashMap;
import com.mycompany.ofertas.Empleador;
import com.mycompany.ofertas.Oferta;
import com.mycompany.ofertas.enums.Sector;

public class Filtro {
    public static void main(String[] args) {
        HashMap<String, ArrayList<Ofertas>> ofertas = new HashMap<String, ArrayList<Ofertas>>();
        int cantiOfertas = 0, cantiSolicitudes = 0;

        ofertas.put("FUERZAS_MILITARES", new ArrayList<Oferta>());
        ofertas.put("GERENCIA", new ArrayList<Oferta>());
        ofertas.put("PROFESIONALES_INTELECTUALES", new ArrayList<Oferta>());
        ofertas.put("TECNICOS", new ArrayList<Oferta>());
        ofertas.put("APOYO_ADMINISTRATIVO", new ArrayList<Oferta>());

        while(true) //Aca adentro se manejan todos los eventos de ZeroMQ
        {
            //TODO: Recibir ofertas por parte del Empleador
            cantiOfertas++;

            //Se filtra y se guarda la oferta en base al sector al que corresponda
            if(filtrarOferta(ofertas, oferta))
                System.out.println("Oferta " + oferta.getId() + "filtrada correctamente");

            //TODO: Recibir solicitudes por parte del Aspirante
            cantiSolicitudes++;

            //Se filtra y se guarda la solicitud en base al sector al que corresponda
            if(filtrarOferta(ofertas, oferta))
                System.out.println("Oferta " + oferta.getId() + "filtrada correctamente");

            //TODO: Guardar la oferta en la DHT
        }
    }

    public static boolean filtrarOferta(HashMap<String, ArrayList<Ofertas>> ofertas, Oferta oferta) {
        return ofertas.get(oferta.getSector().toString()).add(oferta);
    }

    public static void mostrarOfertas(HashMap<String, ArrayList<Ofertas>> ofertas){
        System.out.println("# Ofertas");
        System.out.println("## FUERZAS_MILITARES (" + ofertas.get("FUERZAS_MILITARES").size() + ") ofertas");
        if(ofertas.get("FUERZAS_MILITARES").isEmpty())
            System.out.println("[X] No hay ofertas");
        for (Oferta oferta : ofertas.get("FUERZAS_MILITARES")) {
            System.out.println("*\t NOMBRE: " + oferta.getNombre() + "ID: " + oferta,getId());
        }
        System.out.println("## GERENCIA (" + ofertas.get("GERENCIA").size() + ") ofertas");
        if(ofertas.get("GERENCIA").isEmpty())
            System.out.println("[X] No hay ofertas");
        for (Oferta oferta : ofertas.get("GERENCIA")) {
            System.out.println("*\t NOMBRE: " + oferta.getNombre() + "ID: " + oferta,getId());
        }
        System.out.println("## PROFESIONALES_INTELECTUALES (" + ofertas.get("PROFESIONALES_INTELECTUALES").size() + ") ofertas");
        if(ofertas.get("PROFESIONALES_INTELECTUALES").isEmpty())
            System.out.println("[X] No hay ofertas");
        for (Oferta oferta : ofertas.get("PROFESIONALES_INTELECTUALES")) {
            System.out.println("*\t NOMBRE: " + oferta.getNombre() + "ID: " + oferta,getId());
        }
        System.out.println("## TECNICOS (" + ofertas.get("TECNICOS").size() + ") ofertas");
        if(ofertas.get("TECNICOS").isEmpty())
            System.out.println("[X] No hay ofertas");
        for (Oferta oferta : ofertas.get("TECNICOS")) {
            System.out.println("*\t NOMBRE: " + oferta.getNombre() + "ID: " + oferta,getId());
        }
        System.out.println("## APOYO_ADMINISTRATIVO (" + ofertas.get("APOYO_ADMINISTRATIVO").size() + ") ofertas");
        if(ofertas.get("APOYO_ADMINISTRATIVO").isEmpty())
            System.out.println("[X] No hay ofertas");
        for (Oferta oferta : ofertas.get("APOYO_ADMINISTRATIVO")) {
            System.out.println("*\t NOMBRE: " + oferta.getNombre() + "ID: " + oferta,getId());
        }
    }
}
