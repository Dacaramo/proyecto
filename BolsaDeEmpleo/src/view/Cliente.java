/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import model.Aspirante;
import model.Empleador;
import model.Oferta;
import model.Solicitud;
import model.enums.Capacidad;
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

                        if (TA.equals("emp")) {
                            Emp();
                        }else{
                            asp();
                        }

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
        pub.bind("tcp://*:5557");
        pub.bind("ipc://weather");

        Random srandom = new Random(System.currentTimeMillis());
        int op = 0;

        do {
            System.out.println("Menu empleador:");
            System.out.println("1.Crear oferta");
            System.out.println("2.Ver mis ofertas");
            System.out.println("3.salir");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    Oferta of = new Oferta();
                    ArrayList<Capacidad> cps = new ArrayList<>();
                    of.setIdempleador(IDA);
                    String idsub = new String();
                    int opOF,
                     opCA = 0;
                    System.out.println("Seleccione el sector de la oferta:");
                    System.out.println("1.FUERZAS_MILITARES");
                    System.out.println("2.GERENCIA");
                    System.out.println("3.PROFESIONALES_INTELECTUALES");
                    System.out.println("4.TECNICOS");
                    System.out.println("5.APOYO_ADMINISTRATIVO");
                    System.out.println("6.NINGUNO");
                    opOF = sc.nextInt();
                    sc.nextLine();
                    if (opOF == 1) {
                        of.setSector(Sector.FUERZAS_MILITARES);
                        idsub = "10001";
                    } else if (opOF == 2) {
                        of.setSector(Sector.GERENCIA);
                        idsub = "10002";
                    } else if (opOF == 3) {
                        of.setSector(Sector.PROFESIONALES_INTELECTUALES);
                        idsub = "10003";
                    } else if (opOF == 4) {
                        of.setSector(Sector.TECNICOS);
                        idsub = "10004";
                    } else if (opOF == 5) {
                        of.setSector(Sector.APOYO_ADMINISTRATIVO);
                        idsub = "10005";
                    } else if (opOF == 6) {
                        of.setSector(Sector.NINGUNO);
                        idsub = "10006";
                    }
                    do {

                        System.out.println("Seleccione las capacidades:");
                        System.out.println("1.PREGRADO");
                        System.out.println("2.MAESTRIA");
                        System.out.println("3.DOCTORADO");
                        System.out.println("4.EXPERIENCIA_LABORAL");
                        System.out.println("5.ADAPTABILIDAD");
                        System.out.println("6.COMUNICACION");
                        System.out.println("7.RESOLUCION_PROBLEMAS");
                        System.out.println("8.CREATIVIDAD");
                        System.out.println("9.CONFIANZA");
                        System.out.println("10.HONESTIDAD");
                        System.out.println("11.PACIENCIA");
                        System.out.println("12.Terminar seleccion");
                        opCA = sc.nextInt();
                        sc.nextLine();
                        if (opCA == 1) {
                            cps.add(Capacidad.PREGRADO);
                        } else if (opCA == 2) {
                            cps.add(Capacidad.MAESTRIA);
                        } else if (opCA == 3) {
                            cps.add(Capacidad.DOCTORADO);
                        } else if (opCA == 4) {
                            cps.add(Capacidad.EXPERIENCIA_LABORAL);
                        } else if (opCA == 5) {
                            cps.add(Capacidad.ADAPTABILIDAD);
                        } else if (opCA == 6) {
                            cps.add(Capacidad.COMUNICACION);
                        } else if (opCA == 7) {
                            cps.add(Capacidad.RESOLUCION_PROBLEMAS);
                        } else if (opCA == 8) {
                            cps.add(Capacidad.CREATIVIDAD);
                        } else if (opCA == 9) {
                            cps.add(Capacidad.CONFIANZA);
                        } else if (opCA == 10) {
                            cps.add(Capacidad.HONESTIDAD);
                        } else if (opCA == 11) {
                            cps.add(Capacidad.PACIENCIA);
                        }

                    } while (12 != opCA);
                    of.setCapacidadesRequeridas(cps);
                    System.out.println("Ingrese el titulo de la oferta:");
                    of.setNombre(sc.nextLine());
                    System.out.println("Ingrese la edad requerida:");
                    of.setEdadRequerida(sc.nextInt());
                    System.out.println("Ingrese el sueldo:");
                    of.setSueldo(sc.nextFloat());
                    sc.nextLine();
                    String ofertaING = idsub + " ," + of.toString();
                    System.out.println(ofertaING);
                    pub.send(ofertaING, 0);
                    break;

                case 2:
                    break;

            }
        } while (op != 3);
        pub.close();
    }

    public static void asp() {
        Scanner sc = new Scanner(System.in);
        ZContext context = new ZContext();
        ZMQ.Socket pub = context.createSocket(SocketType.PUB);
        ZMQ.Context context1 = ZMQ.context(1);
        ZMQ.Socket re = context1.socket(SocketType.REQ);
        re.connect("tcp://localhost:5558");
        pub.bind("tcp://*:5557");
        pub.bind("ipc://weather");

        int op = 0;

        do {
            System.out.println("Menu aspirantes:");
            System.out.println("1.Enviar solicitud");
            System.out.println("2.buzon");
            System.out.println("3.salir");
            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1:
                    Solicitud solicitud = new Solicitud();
                    ArrayList<Capacidad> cps = new ArrayList<>();
                    System.out.println("Escriba el nombre de la oferta:");
                    String nombre = sc.nextLine();
                    solicitud.setNombreOferta(nombre);
                    int opCA = 0;
                    do {

                        System.out.println("Seleccione las capacidades:");
                        System.out.println("1.PREGRADO");
                        System.out.println("2.MAESTRIA");
                        System.out.println("3.DOCTORADO");
                        System.out.println("4.EXPERIENCIA_LABORAL");
                        System.out.println("5.ADAPTABILIDAD");
                        System.out.println("6.COMUNICACION");
                        System.out.println("7.RESOLUCION_PROBLEMAS");
                        System.out.println("8.CREATIVIDAD");
                        System.out.println("9.CONFIANZA");
                        System.out.println("10.HONESTIDAD");
                        System.out.println("11.PACIENCIA");
                        System.out.println("12.Terminar seleccion");
                        opCA = sc.nextInt();
                        sc.nextLine();
                        if (opCA == 1) {
                            cps.add(Capacidad.PREGRADO);
                        } else if (opCA == 2) {
                            cps.add(Capacidad.MAESTRIA);
                        } else if (opCA == 3) {
                            cps.add(Capacidad.DOCTORADO);
                        } else if (opCA == 4) {
                            cps.add(Capacidad.EXPERIENCIA_LABORAL);
                        } else if (opCA == 5) {
                            cps.add(Capacidad.ADAPTABILIDAD);
                        } else if (opCA == 6) {
                            cps.add(Capacidad.COMUNICACION);
                        } else if (opCA == 7) {
                            cps.add(Capacidad.RESOLUCION_PROBLEMAS);
                        } else if (opCA == 8) {
                            cps.add(Capacidad.CREATIVIDAD);
                        } else if (opCA == 9) {
                            cps.add(Capacidad.CONFIANZA);
                        } else if (opCA == 10) {
                            cps.add(Capacidad.HONESTIDAD);
                        } else if (opCA == 11) {
                            cps.add(Capacidad.PACIENCIA);
                        }

                    } while (12 != opCA);
                    solicitud.setAspirante(IDA);
                    solicitud.setCapacidades(cps);
                    String solicitudS = "sol"+","+solicitud.toString();
                    System.out.println(solicitudS);
                    pub.send(solicitudS,0);
                    break;
                case 2:
                    break;
            }
        } while (op != 3);
    }
}
