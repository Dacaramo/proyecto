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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.StringTokenizer;
import model.Aspirante;
import model.Empleador;
import model.Oferta;
import org.kth.dks.JDHT;
import org.kth.dks.dks_exceptions.DKSIdentifierAlreadyTaken;
import org.kth.dks.dks_exceptions.DKSRefNoResponse;
import org.kth.dks.dks_exceptions.DKSTooManyRestartJoins;
import org.zeromq.ZMQ;

/**
 *
 * @author juanc
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //listas para persistencia temporal mientras se incorpora el DHT
        ArrayList<Oferta> ofertas = new ArrayList<>();
        ArrayList<Empleador> empleadores = deserializarEmpleadores();
        ArrayList<Aspirante> aspirantes = deserializarAspirantes();
        
        ZMQ.Context context = ZMQ.context(1);
        // Socket to talk to clients
        ZMQ.Socket socket = context.socket(ZMQ.REP);
        socket.bind ("tcp://*:5555");
        try {
            
            String ref = "CAMBIAR ESTO POR LA REFERENCIA RECIBIDA DESDE EL FILTRO"; //TODO: Recibir la referencia a la JDHT instanciada en el filtro
            JDHT DHT = new JDHT(5550, ref); //TODO: Generar un numero de puerto distinto por cada servidor que se levante
            
            while (!Thread.currentThread ().isInterrupted ()) {
                
                byte[] reply = socket.recv(0);
                String cad=new String(reply);
                StringTokenizer tok = new StringTokenizer(cad,",");
                String fl = tok.nextToken();
                if(fl.equals("emp")){
                    Empleador emp = new Empleador();
                    emp.setId(tok.nextToken());
                    emp.setNombre(tok.nextToken());
                    emp.setClave(tok.nextToken());
                    System.out.println("Empleador registrado");
                    System.out.println("Datos:"+emp.getId()+" "+emp.getNombre()+" "+emp.getClave());
                    
                    empleadores.add(emp);
                    serializarEmpleadores(empleadores); //Se persisten los empleadores que se han ido registrando, en los archivos locales
                    DHT.put("empleadores", empleadores); //Se envian los empleadores registrados a la DHT    
                }
                else if(fl.equals("asp")){
                    System.out.println("holii");
                    Aspirante asp = new Aspirante();
                    asp.setId(tok.nextToken());
                    asp.setNombre(tok.nextToken());
                    asp.setClave(tok.nextToken());
                    asp.setEdad(Integer.parseInt(tok.nextToken()));
                    System.out.println("Aspirante registrado");
                    System.out.println("Datos:"+asp.getId()+" "+asp.getNombre()+" "+asp.getClave()+" "+asp.getEdad());
                    
                    aspirantes.add(asp);
                    serializarAspirantes(aspirantes); //Se persisten los aspirantes que se han ido registrando, en los archivos locales
                    DHT.put("aspirantes", aspirantes); //Se envian los aspirantes registrados a la DHT
                }
            }
            
            DHT.close();
            
        } catch(IOException | DKSTooManyRestartJoins |
            DKSIdentifierAlreadyTaken | DKSRefNoResponse ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            System.out.println(sw.toString());
        }
        
        socket.close();
        context.term();
        
    }
    
    //SEREALIZACION
    
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
    
    //DESEREALIZACION
    
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
