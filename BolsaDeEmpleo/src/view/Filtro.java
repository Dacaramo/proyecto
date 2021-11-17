/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import model.Aspirante;
import model.ConsoleColors;
import model.Empleador;
import model.Oferta;
import model.Solicitud;
import model.enums.Capacidad;
import model.enums.Sector;
import org.kth.dks.JDHT;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import static view.ProveedorJDHT.deserializarOfertas;
import static view.ProveedorJDHT.serializarOfertas;
import static view.ProveedorJDHT.serializarSolicitudes;

/**
 *
 * @author HP-PC
 */
public class Filtro { //ESTA CLASE CONTIENE LA INSTANCIA PRINCIPAL DE LA DHT

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int cantiOfertas = 0, cantiSolicitudes = 0;
        ZMQ.Context context = ZMQ.context(1);
        ZContext contx = new ZContext();
        HashMap<String, ArrayList<Oferta>> ofertas = deserializarOfertas();
        HashMap<String, ArrayList<Solicitud>> solicitudes = deserializarSolicitudes();

        ZMQ.Socket suscriber = contx.createSocket(SocketType.SUB);
        ZMQ.Socket suscriber2 = contx.createSocket(SocketType.SUB);
        ZMQ.Socket socket = context.socket(ZMQ.REQ);
        socket.connect("tcp://localhost:5556");
        suscriber.connect("tcp://localhost:5557");
        suscriber2.connect("tcp://localhost:5558");
        // Socket to talk to clients
        

        ZMQ.Poller poller = context.poller(1);
        poller.register(suscriber, ZMQ.Poller.POLLIN);
        poller.register(suscriber2, ZMQ.Poller.POLLIN);
        try {
            JDHT DHT = new JDHT();
            String ref = ((JDHT) DHT).getReference();
            System.out.println(ref); //TODO: Compartir esta referencia con todas las maquinas
            socket.send(ref.getBytes(), 0);

            while (!Thread.currentThread().isInterrupted()) //Aca adentro se manejan todos los eventos de ZeroMQ
            {
                String of = new String();
                String key = new String();
                Oferta oferta = new Oferta();
                int val = 0;
                String OFERTA = "10001 ";
                suscriber.subscribe(OFERTA.getBytes(ZMQ.CHARSET));
                //int val = poller.poll(1000);
                 byte [] offf;
               while((offf = suscriber.recv(ZMQ.NOBLOCK)) !=null){
                     
                    of = new String(offf);
                    System.out.println("OFERTA:" + of);
                    key = new String();
                    oferta = construirOferta(of, key);
                    if (ofertas.get(oferta.getSector().toString()).add(oferta)) { //Se guarda en el HashMap en memoria
                        System.out.println("Oferta " + oferta.getId() + "filtrada correctamente");
                        serializarOfertas(ofertas); //Se persisten las ofertas recolectadas en los archivos locales
                        cantiOfertas++;
                    }
               }
                String SOLICITUD = "10002 ";
                suscriber2.subscribe(SOLICITUD.getBytes(ZMQ.CHARSET));
                byte[] solll;
                
                      
                    while((solll = suscriber2.recv(ZMQ.NOBLOCK))!=null){
                        System.out.println("hoola");
                        String soli = new String(solll);
                        Solicitud solicitud = crearSolicitud(soli);

                        for (Map.Entry entry : ofertas.entrySet()) {
                            String key1 = entry.getKey().toString();
                            ArrayList<Oferta> oferta1 = (ArrayList<Oferta>) entry.getValue();
                            for (Oferta of1 : oferta1) {
                                if (of1.getNombre().equals(solicitud.getNombreOferta())) {
                                    if (solicitudes.get(key1).add(solicitud)) { //Se guarda en el HashMap en memoria
                                        System.out.println("Solicitud " + solicitud.getId() + "filtrada correctamente");
                                        serializarSolicitudes(solicitudes); //Se persisten las solicitudes recolectadas en los archivos locales
                                        cantiSolicitudes++;
                                    }
                                }
                            }
                        }
                    }
                
//                if(/*TODO: Si se recibe una solicitud por parte del Aspirante*/){
//                    //Se filtra y se guarda la solicitud en el filtro en base al sector (en el HashMap de solicitudes)
//                    if(solicitudes.get(solicitud.getSector().toString()).add(solicitud)) { //Se guarda en el HashMap en memoria
//                        System.out.println("Solicitud " + solicitud.getId() + "filtrada correctamente"); 
//                        serializarSolicitudes(solicitudes); //Se persisten las solicitudes recolectadas en los archivos locales
//                        cantiSolicitudes++;
//                    }
//                }
                if (cantiOfertas == 10) {
                    //Se envian las ofertas recolectadas a la DHT
                    DHT.put("ofertas-FUERZAS_MILITARES", ofertas.get("FUERZAS_MILITARES"));
                    DHT.put("ofertas-GERENCIA", ofertas.get("GERENCIA"));
                    DHT.put("ofertas-PROFESIONALES_INTELECTUALES", ofertas.get("PROFESIONALES_INTELECTUALES"));
                    DHT.put("ofertas-TECNICOS", ofertas.get("TECNICOS"));
                    DHT.put("ofertas-APOYO_ADMINISTRATIVO", ofertas.get("APOYO_ADMINISTRATIVO"));

                    //TODO: Se distribuyen las ofertas recolectadas entre los servidores por medio de la DHT
                    cantiOfertas = 0;
                }

                if (cantiSolicitudes == 10) {
                    //Se envian las solicitudes recolectadas a la DHT
                    DHT.put("solicitudes-FUERZAS_MILITARES", solicitudes.get("FUERZAS_MILITARES"));
                    DHT.put("solicitudes-GERENCIA", solicitudes.get("GERENCIA"));
                    DHT.put("solicitudes-PROFESIONALES_INTELECTUALES", solicitudes.get("PROFESIONALES_INTELECTUALES"));
                    DHT.put("solicitudes-TECNICOS", solicitudes.get("TECNICOS"));
                    DHT.put("solicitudes-APOYO_ADMINISTRATIVO", solicitudes.get("APOYO_ADMINISTRATIVO"));

                    //TODO: Se distribuyen las solicitudes recolectadas entre los servidores por medio de la DHT
                    cantiSolicitudes = 0;
                }
            }

            // DHT.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean esSolicitud(String cad) {
        StringTokenizer tok = new StringTokenizer(cad, ",");
        String flag = tok.nextToken();
        if (flag.equals("sol")) {
            return true;
        }
        return false;
    }

    public static Oferta construirOferta(String oferta, String key) {
        Oferta ofer = new Oferta();
        StringTokenizer tok = new StringTokenizer(oferta, ",");
        String idsec = tok.nextToken();
        String idof = tok.nextToken();
        ofer.setId(idof);
        key = idsec + "-" + idof;
        String idemp = tok.nextToken();
        ofer.setIdempleador(idemp);
        String nombre = tok.nextToken();
        ofer.setNombre(nombre);
        String sectorc = tok.nextToken();
        Sector sector = Sector.valueOf(sectorc);
        ofer.setSector(sector);
        int edad = Integer.parseInt(tok.nextToken());
        ofer.setEdadRequerida(edad);
        float precio = Float.parseFloat(tok.nextToken());
        ofer.setSueldo(precio);
        int cantCA = Integer.parseInt(tok.nextToken());
        ArrayList<Capacidad> cap = new ArrayList<>();
        for (int i = 0; i < cantCA; i++) {
            String sec = tok.nextToken();
            Capacidad c = Capacidad.valueOf(sec);
            cap.add(c);
        }
        ofer.setCapacidadesRequeridas(cap);
        return ofer;
    }

    public static Solicitud crearSolicitud(String sols) {
        Solicitud s = new Solicitud();
        StringTokenizer tok = new StringTokenizer(sols, ",");
        String soli = tok.nextToken();
        String idsolicitud = tok.nextToken();
        s.setId(idsolicitud);
        String idaspirante = tok.nextToken();
        s.setAspirante(idaspirante);
        String nombre = tok.nextToken();
        s.setNombreOferta(nombre);
        int cant = Integer.parseInt(tok.nextToken());
        ArrayList<Capacidad> cap = new ArrayList<>();
        for (int i = 0; i < cant; i++) {
            String capc = tok.nextToken();
            Capacidad c = Capacidad.valueOf(capc);
            cap.add(c);
        }
        s.setCapacidades(cap);
        return s;
    }
    //SERIALIZACION DE OFERTAS

    public static void serializarOfertas(HashMap<String, ArrayList<Oferta>> ofertas) throws FileNotFoundException, IOException {
        serializarOfertasApoyoAdministrativo(ofertas.get("APOYO_ADMINISTRATIVO"));
        serializarOfertasFuerzasMilitares(ofertas.get("FUERZAS_MILITARES"));
        serializarOfertasGerencia(ofertas.get("GERENCIA"));
        serializarOfertasProfesionalesIntelectuales(ofertas.get("PROFESIONALES_INTELECTUALES"));
        serializarOfertasTecnicos(ofertas.get("TECNICOS"));
    }

    public static void serializarOfertasApoyoAdministrativo(ArrayList<Oferta> ofertas) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/files/ofertas/ofertas-APOYO_ADMINISTRATIVO.json");
        gson.toJson(ofertas, fw);
        String json = gson.toJson(ofertas);
        System.out.println(json);
        fw.flush();
        fw.close();
    }

    public static void serializarOfertasFuerzasMilitares(ArrayList<Oferta> ofertas) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/files/ofertas/ofertas-FUERZAS_MILITARES.json");
        gson.toJson(ofertas, fw);
        String json = gson.toJson(ofertas);
        System.out.println(json);
        fw.flush();
        fw.close();
    }

    public static void serializarOfertasGerencia(ArrayList<Oferta> ofertas) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/files/ofertas/ofertas-GERENCIA.json");
        gson.toJson(ofertas, fw);
        String json = gson.toJson(ofertas);
        System.out.println(json);
        fw.flush();
        fw.close();
    }

    public static void serializarOfertasProfesionalesIntelectuales(ArrayList<Oferta> ofertas) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/files/ofertas/ofertas-PROFESIONALES_INTELECTUALES.json");
        gson.toJson(ofertas, fw);
        String json = gson.toJson(ofertas);
        System.out.println(json);
        fw.flush();
        fw.close();
    }

    public static void serializarOfertasTecnicos(ArrayList<Oferta> ofertas) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/files/ofertas/ofertas-TECNICOS.json");
        gson.toJson(ofertas, fw);
        String json = gson.toJson(ofertas);
        System.out.println(json);
        fw.flush();
        fw.close();
    }

    //SERIALIZACION DE SOLICITUDES
    public static void serializarSolicitudes(HashMap<String, ArrayList<Solicitud>> solicitudes) throws FileNotFoundException, IOException {
        serializarSolicitudesApoyoAdministrativo(solicitudes.get("APOYO_ADMINISTRATIVO"));
        serializarSolicitudesFuerzasMilitares(solicitudes.get("FUERZAS_MILITARES"));
        serializarSolicitudesGerencia(solicitudes.get("GERENCIA"));
        serializarSolicitudesProfesionalesIntelectuales(solicitudes.get("PROFESIONALES_INTELECTUALES"));
        serializarSolicitudesTecnicos(solicitudes.get("TECNICOS"));
    }

    public static void serializarSolicitudesApoyoAdministrativo(ArrayList<Solicitud> solicitudes) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/files/solicitudes/solicitudes-APOYO_ADMINISTRATIVO.json");
        gson.toJson(solicitudes, fw);
        String json = gson.toJson(solicitudes);
        System.out.println(json);
        fw.flush();
        fw.close();
    }

    public static void serializarSolicitudesFuerzasMilitares(ArrayList<Solicitud> solicitudes) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/files/solicitudes/solicitudes-FUERZAS_MILITARES.json");
        gson.toJson(solicitudes, fw);
        String json = gson.toJson(solicitudes);
        System.out.println(json);
        fw.flush();
        fw.close();
    }

    public static void serializarSolicitudesGerencia(ArrayList<Solicitud> solicitudes) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/files/solicitudes/solicitudes-GERENCIA.json");
        gson.toJson(solicitudes, fw);
        String json = gson.toJson(solicitudes);
        System.out.println(json);
        fw.flush();
        fw.close();
    }

    public static void serializarSolicitudesProfesionalesIntelectuales(ArrayList<Solicitud> solicitudes) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/files/solicitudes/solicitudes-PROFESIONALES_INTELECTUALES.json");
        gson.toJson(solicitudes, fw);
        String json = gson.toJson(solicitudes);
        System.out.println(json);
        fw.flush();
        fw.close();
    }

    public static void serializarSolicitudesTecnicos(ArrayList<Solicitud> solicitudes) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/files/solicitudes/solicitudes-TECNICOS.json");
        gson.toJson(solicitudes, fw);
        String json = gson.toJson(solicitudes);
        System.out.println(json);
        fw.flush();
        fw.close();
    }

    //DESERIALIZACION DE OFERTAS
    public static HashMap<String, ArrayList<Oferta>> deserializarOfertas() throws FileNotFoundException, IOException {
        HashMap<String, ArrayList<Oferta>> ofertas = new HashMap<String, ArrayList<Oferta>>();
        ofertas.put("APOYO_ADMINISTRATIVO", deserializarOfertasApoyoAdministrativo());
        ofertas.put("FUERZAS_MILITARES", deserializarOfertasFuerzasMilitares());
        ofertas.put("GERENCIA", deserializarOfertasGerencia());
        ofertas.put("PROFESIONALES_INTELECTUALES", deserializarOfertasProfesionalesIntelectuales());
        ofertas.put("TECNICOS", deserializarOfertasTecnicos());

        return ofertas;
    }

    public static ArrayList<Oferta> deserializarOfertasApoyoAdministrativo() throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/files/ofertas/ofertas-APOYO_ADMINISTRATIVO.json");
        Type tipoEncontrado = new TypeToken<ArrayList<Oferta>>() {
        }.getType();
        ArrayList<Oferta> ofertas = gson.fromJson(fr, tipoEncontrado);
        fr.close();

        return ofertas;
    }

    public static ArrayList<Oferta> deserializarOfertasFuerzasMilitares() throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/files/ofertas/ofertas-FUERZAS_MILITARES.json");
        Type tipoEncontrado = new TypeToken<ArrayList<Oferta>>() {
        }.getType();
        ArrayList<Oferta> ofertas = gson.fromJson(fr, tipoEncontrado);
        fr.close();

        return ofertas;
    }

    public static ArrayList<Oferta> deserializarOfertasGerencia() throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/files/ofertas/ofertas-GERENCIA.json");
        Type tipoEncontrado = new TypeToken<ArrayList<Oferta>>() {
        }.getType();
        ArrayList<Oferta> ofertas = gson.fromJson(fr, tipoEncontrado);
        fr.close();

        return ofertas;
    }

    public static ArrayList<Oferta> deserializarOfertasProfesionalesIntelectuales() throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/files/ofertas/ofertas-PROFESIONALES_INTELECTUALES.json");
        Type tipoEncontrado = new TypeToken<ArrayList<Oferta>>() {
        }.getType();
        ArrayList<Oferta> ofertas = gson.fromJson(fr, tipoEncontrado);
        fr.close();

        return ofertas;
    }

    public static ArrayList<Oferta> deserializarOfertasTecnicos() throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/files/ofertas/ofertas-TECNICOS.json");
        Type tipoEncontrado = new TypeToken<ArrayList<Oferta>>() {
        }.getType();
        ArrayList<Oferta> ofertas = gson.fromJson(fr, tipoEncontrado);
        fr.close();

        return ofertas;
    }

    //DESERIALIZACION DE SOLICITUDES
    public static HashMap<String, ArrayList<Solicitud>> deserializarSolicitudes() throws FileNotFoundException, IOException {
        HashMap<String, ArrayList<Solicitud>> solicitudes = new HashMap<String, ArrayList<Solicitud>>();
        solicitudes.put("APOYO_ADMINISTRATIVO", deserializarSolicitudesApoyoAdministrativo());
        solicitudes.put("FUERZAS_MILITARES", deserializarSolicitudesFuerzasMilitares());
        solicitudes.put("GERENCIA", deserializarSolicitudesGerencia());
        solicitudes.put("PROFESIONALES_INTELECTUALES", deserializarSolicitudesProfesionalesIntelectuales());
        solicitudes.put("TECNICOS", deserializarSolicitudesTecnicos());

        return solicitudes;
    }

    public static ArrayList<Solicitud> deserializarSolicitudesApoyoAdministrativo() throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/files/solicitudes/solicitudes-APOYO_ADMINISTRATIVO.json");
        Type tipoEncontrado = new TypeToken<ArrayList<Solicitud>>() {
        }.getType();
        ArrayList<Solicitud> solicitudes = gson.fromJson(fr, tipoEncontrado);
        fr.close();

        return solicitudes;
    }

    public static ArrayList<Solicitud> deserializarSolicitudesFuerzasMilitares() throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/files/solicitudes/solicitudes-FUERZAS_MILITARES.json");
        Type tipoEncontrado = new TypeToken<ArrayList<Solicitud>>() {
        }.getType();
        ArrayList<Solicitud> solicitudes = gson.fromJson(fr, tipoEncontrado);
        fr.close();

        return solicitudes;
    }

    public static ArrayList<Solicitud> deserializarSolicitudesGerencia() throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/files/solicitudes/solicitudes-GERENCIA.json");
        Type tipoEncontrado = new TypeToken<ArrayList<Solicitud>>() {
        }.getType();
        ArrayList<Solicitud> solicitudes = gson.fromJson(fr, tipoEncontrado);
        fr.close();

        return solicitudes;
    }

    public static ArrayList<Solicitud> deserializarSolicitudesProfesionalesIntelectuales() throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/files/solicitudes/solicitudes-PROFESIONALES_INTELECTUALES.json");
        Type tipoEncontrado = new TypeToken<ArrayList<Solicitud>>() {
        }.getType();
        ArrayList<Solicitud> solicitudes = gson.fromJson(fr, tipoEncontrado);
        fr.close();

        return solicitudes;
    }

    public static ArrayList<Solicitud> deserializarSolicitudesTecnicos() throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/files/solicitudes/solicitudes-TECNICOS.json");
        Type tipoEncontrado = new TypeToken<ArrayList<Solicitud>>() {
        }.getType();
        ArrayList<Solicitud> solicitudes = gson.fromJson(fr, tipoEncontrado);
        fr.close();

        return solicitudes;
    }
}
