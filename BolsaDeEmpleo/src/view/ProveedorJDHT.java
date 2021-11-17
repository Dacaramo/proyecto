package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import model.Aspirante;
import model.ConsoleColors;
import model.Empleador;
import model.Oferta;
import model.Solicitud;
import model.enums.Capacidad;
import model.enums.Sector;
import org.kth.dks.JDHT;

public class ProveedorJDHT {
    public static void main(String[] args) throws Exception {
        char salir = 'n';
        String llave, valor;
        Scanner sc = new Scanner(System.in);
        
        try 
        {
            JDHT DHTExample = new JDHT();
            System.out.println("\n" + ConsoleColors.GREEN_BACKGROUND + ((JDHT) DHTExample).getReference() + ConsoleColors.RESET);
            
            do
            {
                System.out.print("\n");
                
                System.out.print("Ingrese la llave de la nueva entrada en la DHT: ");
                llave = sc.next();
                
                System.out.print("Ingrese el valor de la nueva entrada en la DHT: ");
                valor = sc.next();
                
                System.out.print("\n");
                
                DHTExample.put(llave, valor);
                

                
//                DHTExample.put("obj", oferta);
                
                //OFERTAS
                
                ArrayList<Oferta> ofs = new ArrayList<>();
                ArrayList<Capacidad> caps = new ArrayList<>();
                
                List<Capacidad> list = Arrays.asList(Capacidad.ADAPTABILIDAD, Capacidad.CONFIANZA, Capacidad.CREATIVIDAD);
                caps.addAll(list);
                
                ArrayList<Oferta> ofertas = new ArrayList<>();
                /*ofertas.add(new Oferta("Cuidandero", Sector.TECNICOS, 41, 1200f, new Empleador("Daniel Gomez", "gomita123", ofs), caps));
                ofertas.add(new Oferta("Secretario", Sector.APOYO_ADMINISTRATIVO,18, 1500f, new Empleador("Daniel Gomez", "gomita123", ofs), caps));
                ofertas.add(new Oferta("Medico", Sector.PROFESIONALES_INTELECTUALES, 35, 7000f, new Empleador("Daniel Gomez", "gomita123", ofs), caps));
                ofertas.add(new Oferta("Administrador", Sector.GERENCIA, 26, 1900f, new Empleador("Daniel Gomez", "gomita123", ofs), caps));
                */
                serializarOfertas(ofertas);
                
                //SOLICITUDES
                
                ArrayList<Solicitud> solicitudes = new ArrayList<>();
                //solicitudes.add(new Solicitud(null, "Pepito"));
                //solicitudes.add(new Solicitud(null, "Juan"));
                //solicitudes.add(new Solicitud(null, "Nico"));
                //solicitudes.add(new Solicitud(null, "Guillermo"));
                //solicitudes.add(new Solicitud(null, "Fernando"));
                
                serializarSolicitudes(solicitudes);
                
                //DESERIALIZAR
                
                ArrayList<Oferta> ofertasDes = deserializarOfertas();
                
                System.out.println("INFO DESERIALIZACION");
                
                for (Oferta o : ofertasDes) {
                    System.out.println(o.getId());
                    System.out.println(o.getNombre());
                    System.out.println(o.getSector().toString());
                    System.out.println(o.getEdadRequerida());
                    System.out.println(o.getSueldo());
                    //System.out.println("\t" + o.getEmpleador().getNombre());
                    //System.out.println("\t" + o.getEmpleador().getClave());
                    System.out.println(o.getCapacidadesRequeridas().toString());
                }
                
                System.out.print("Desea salir del programa? (s: si/n: no): ");
                salir = sc.next().charAt(0);
            }
            while(salir != 's');
            
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press Enter to terminate application... ");
            scanner.next();
            DHTExample.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void serializarOfertas(ArrayList<Oferta> ofertas) throws FileNotFoundException, IOException{
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/files/ofertas.json");
        gson.toJson(ofertas, fw);
        String json = gson.toJson(ofertas);
        System.out.println(json);
        fw.flush();
        fw.close();
    }
    
    public static void serializarSolicitudes(ArrayList<Solicitud> solicitudes) throws FileNotFoundException, IOException{
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/files/solicitudes.json");
        gson.toJson(solicitudes, fw);
        String json = gson.toJson(solicitudes);
        System.out.println(json);
        fw.flush();
        fw.close();
    }
    
    public static void serializarEmpleadores(ArrayList<Empleador> empleadores) throws FileNotFoundException, IOException{
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/files/empleadores.json");
        gson.toJson(empleadores, fw);
        String json = gson.toJson(empleadores);
        System.out.println(json);
        fw.flush();
        fw.close();
    }

    public static void serializarAspirantes(ArrayList<Aspirante> aspirantes) throws FileNotFoundException, IOException{
        Gson gson = new Gson();
        FileWriter fw = new FileWriter("src/files/aspirantes.json");
        gson.toJson(aspirantes, fw);
        String json = gson.toJson(aspirantes);
        System.out.println(json);
        fw.flush();
        fw.close();
    }  
    
    public static ArrayList<Oferta> deserializarOfertas() throws FileNotFoundException, IOException{
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/files/ofertas.json");
        Type tipoEncontrado = new TypeToken<ArrayList<Oferta>>(){}.getType();
        ArrayList<Oferta> ofertas = gson.fromJson(fr, tipoEncontrado);
        fr.close();
        
        return ofertas;
    }
    
    public static ArrayList<Solicitud> deserializarSolicitudes() throws FileNotFoundException, IOException{
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/files/solicitudes.json");
        Type tipoEncontrado = new TypeToken<ArrayList<Solicitud>>(){}.getType();
        ArrayList<Solicitud> solicitudes = gson.fromJson(fr, tipoEncontrado);
        fr.close();
        
        return solicitudes;
    }
    
    public static ArrayList<Empleador> deserializarEmpleadores() throws FileNotFoundException, IOException{
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/files/empleadores.json");
        Type tipoEncontrado = new TypeToken<ArrayList<Empleador>>(){}.getType();
        ArrayList<Empleador> empleadores = gson.fromJson(fr, tipoEncontrado);
        fr.close();
        
        return empleadores;
    }

    public static ArrayList<Aspirante> deserializarAspirantes() throws FileNotFoundException, IOException{
        Gson gson = new Gson();
        FileReader fr = new FileReader("src/files/aspirantes.json");
        Type tipoEncontrado = new TypeToken<ArrayList<Aspirante>>(){}.getType();
        ArrayList<Aspirante> aspirantes = gson.fromJson(fr, tipoEncontrado);
        fr.close();
        
        return aspirantes;
    }  
}
