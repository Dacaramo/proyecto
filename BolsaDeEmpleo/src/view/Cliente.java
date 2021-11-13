/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Scanner;
import model.Aspirante;
import model.Empleador;
import org.zeromq.ZMQ;

/**
 *
 * @author juanc
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
       int opcion = 0;
       Scanner sc = new Scanner(System.in);
       System.out.println("SISTEMA DE EMPLEO");
       do{
           System.out.println("1.Ingresar");
           System.out.println("2.Registrarse");
           System.out.println("3.Salir");
           opcion  = sc.nextInt();
           sc.nextLine();
           switch(opcion){
               case 1:
                   break;
               case 2:
                   Registro();
                   break;
           }
                   
       }while(opcion != 3);
      
    }
    private static void Ingreso() throws InterruptedException{
    ZMQ.Context context = ZMQ.context(1);
        // Socket to talk to server
        System.out.println("Conectando con el servidor....");
        ZMQ.Socket socket = context.socket(ZMQ.REQ);
        socket.connect ("tcp://localhost:5555");
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario:");
        String nombre = sc.nextLine();
        System.out.println("Ingrese su clave:");
        String clave = sc.nextLine();
        String solicitud = "sol"+","+nombre+","+clave;
        System.out.println("Enviando solicitud....");
        socket.send(solicitud.getBytes(),0);
      
    }
    private static void Registro() throws InterruptedException{
       
         ZMQ.Context context = ZMQ.context(1);
        // Socket to talk to server
        System.out.println("Conectando con el servidor....");
        ZMQ.Socket socket = context.socket(ZMQ.REQ);
        socket.connect ("tcp://localhost:5555");
        
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Registro de usuarios:");
        System.out.println("Seleccione el tipo de usuario:");
        System.out.println("1.Empleador");
        System.out.println("2.Aspirante");
        opcion = sc.nextInt();
        sc.nextLine();
        switch(opcion){
            case 1:
                Empleador aux = new Empleador();
                System.out.println("Ingrese un nombre de usuario:");
                String Nombre = sc.nextLine();
                //System.out.println("Enviando nombre.....");
                aux.setNombre(Nombre);
                System.out.println("Ingrese clave de ingreso:");
                String clave = sc.nextLine();
                aux.setClave(clave);
                System.out.println("Enviando datos....");
                socket.send(aux.toString().getBytes(),0);
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
                int edad =sc.nextInt();
                asp.setEdad(edad);
                System.out.println("Enviando datos....");
                socket.send(asp.toString().getBytes(),0);
                Thread.sleep(1000); 
                break;
        }
        socket.close();
        context.term();
    }
    
}
