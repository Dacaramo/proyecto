/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import model.Aspirante;
import model.Empleador;
import model.Oferta;
import model.enums.Sector;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 *
 * @author juanc
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static String TA = new String();
    public static String UAC = new String();
    public static String IDA = new String();
    public static void main(String[] args) throws InterruptedException {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        ZMQ.Context context = ZMQ.context(1);
        // Socket to talk to server

        System.out.println("Conectando con el servidor....");
        ZMQ.Socket socket = context.socket(ZMQ.REQ);
        socket.connect("tcp://localhost:5555");
        ZMQ.Poller poller = context.poller(1);
        poller.register(socket, ZMQ.Poller.POLLIN);

        

        System.out.println("SISTEMA DE EMPLEO");
        do {
            System.out.println("1.Ingresar");
            System.out.println("2.Registrarse");
            System.out.println("3.Salir");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese su nombre de usuario:");
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese su clave:");
                    String clave = sc.nextLine();
                    String solicitud = "sol" + "," + nombre + "," + clave;
                    System.out.println("Enviando solicitud....");
                    socket.send(solicitud.getBytes(), 0);

                    poller.poll(1000);
                    byte[] reply = socket.recv(0);
                    String res = new String(reply);
                    System.out.println(res);

                    if (res.equals("nf")) {
                        System.out.println("El usuario no esta registrado en la base de datos");
                    } else {
                        //System.out.println("tutututututut");
                        StringTokenizer tok = new StringTokenizer(res, ",");
                        TA = tok.nextToken();
                        IDA = tok.nextToken();
                        UAC = tok.nextToken();
                        System.out.println("actual" + " " + TA + " " + IDA + " " + UAC);

                    }
                    break;
                case 2:
                    System.out.println("Registro de usuarios:");
                    System.out.println("Seleccione el tipo de usuario:");
                    System.out.println("1.Empleador");
                    System.out.println("2.Aspirante");
                    int opcion1 = sc.nextInt();
                    sc.nextLine();
                    switch (opcion1) {
                        case 1:
                            Empleador aux = new Empleador();
                            System.out.println("Ingrese un nombre de usuario:");
                            String Nombre = sc.nextLine();
                            //System.out.println("Enviando nombre.....");
                            aux.setNombre(Nombre);
                            System.out.println("Ingrese clave de ingreso:");
                            String clave1 = sc.nextLine();
                            aux.setClave(clave1);
                            System.out.println("Enviando datos....");
                            socket.send(aux.toString().getBytes(), 0);
                            socket.recv(0);
                            Thread.sleep(1000);
                            break;
                        case 2:
                            Aspirante asp = new Aspirante();
                            System.out.println("Ingrese un nombre de usuario:");
                            String NombreA = sc.nextLine();
                            asp.setNombre(NombreA);
                            System.out.println("Ingrese clave de ingreso:");
                            String claveA = sc.nextLine();
                            asp.setClave(claveA);
                            System.out.println("Ingrese su edad:");
                            int edad = sc.nextInt();
                            asp.setEdad(edad);
                            System.out.println("Enviando datos....");
                            socket.send(asp.toString().getBytes(), 0);
                            socket.recv(0);
                            Thread.sleep(1000);
                            break;
                    }
                    break;
            }

        } while (opcion != 3);
        socket.close();
        context.term();
    }

    public static void Emp() {
        Scanner sc = new Scanner(System.in);
        ZContext context = new ZContext();
        ZMQ.Socket pub = context.createSocket(SocketType.PUB);
        pub.bind("tcp://*:5556");
        pub.bind("ipc://weather");
        
        Random srandom = new Random(System.currentTimeMillis());
        int op;
        System.out.println("Menu empleador:");
        System.out.println("1.Crear oferta");
        System.out.println("2.Ver mis ofertas");
        System.out.println("3.salir");
        op = sc.nextInt();
        do{
            switch(op){
                case 1:
                    Oferta of = new Oferta();
                {
                    String Idempleador;
                    //of.setIdempleador(Idempleador);
                }
                    String idsub = new String();
                    int opOF;
                    System.out.println("Seleccione el sector de la oferta:");
                    System.out.println("1.FUERZAS_MILITARES");
                    System.out.println("2.GERENCIA");
                    System.out.println("3.PROFESIONALES_INTELECTUALES");
                    System.out.println("4.TECNICOS");
                    System.out.println("5.APOYO_ADMINISTRATIVO");
                    opOF = sc.nextInt();
                    sc.nextLine();
                    if(opOF == 1){                     
                        of.setSector(Sector.FUERZAS_MILITARES);
                        idsub = "10001 ";
                    }else if(opOF == 2){                       
                        of.setSector(Sector.GERENCIA);
                        idsub = "10002 ";
                    }else if(opOF == 3){                      
                        of.setSector(Sector.PROFESIONALES_INTELECTUALES);
                        idsub = "10003 ";
                    }else if(opOF == 4){
                        of.setSector(Sector.TECNICOS);
                        idsub = "10004 ";
                    }else if(opOF == 5){
                        of.setSector(Sector.APOYO_ADMINISTRATIVO);
                        idsub = "10005 ";
                    }
                    System.out.println("Ingrese el titulo de la oferta:");
                    of.setNombre(sc.nextLine());
                    System.out.println("Ingrese la edad requerida:");
                    of.setEdadRequerida(sc.nextInt());
                    
                    break;

                case 2:
                    break;
                
            }
        }while(op!=3);
        
    }
}
