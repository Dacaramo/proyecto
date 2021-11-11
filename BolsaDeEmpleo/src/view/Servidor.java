/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import model.Aspirante;
import model.Empleador;
import model.Oferta;
import org.zeromq.ZMQ;

/**
 *
 * @author juanc
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //listas para persistencia temporal mientras se incorpora el DHT
        ArrayList<Oferta> ofertasTemp = new ArrayList<>();
        ArrayList<Empleador> empleadoresTemp = new ArrayList<>();
        ArrayList<Aspirante> aspirantesTemp = new ArrayList<>();
        
        ZMQ.Context context = ZMQ.context(1);
        // Socket to talk to clients
        ZMQ.Socket socket = context.socket(ZMQ.REP);
        socket.bind ("tcp://*:5555");
        try {
            while (!Thread.currentThread ().isInterrupted ()) {
                /*byte[] reply = socket.recv(0);
                System.out.println("Received Hello");
                String request = "World" ;
                socket.send(request.getBytes (), 0);
                Thread.sleep(1000); // Do some 'work'*/
                Empleador emp = new Empleador();
                byte[] reply = socket.recv(0);
                String cad=new String(reply);
                StringTokenizer tok = new StringTokenizer(cad,",");
                emp.setId(tok.nextToken());
                emp.setNombre(tok.nextToken());
                emp.setClave(tok.nextToken());
                System.out.println("Empleador registrado");
                System.out.println("Datos:"+emp.getId()+" "+emp.getNombre()+" "+emp.getClave());
                empleadoresTemp.add(emp);
                
            }
        } catch(Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.out.println(sw.toString());
        }
        socket.close();
        context.term();
        
    }
    
}
