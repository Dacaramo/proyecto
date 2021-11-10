/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Scanner;
import org.zeromq.ZMQ;

/**
 *
 * @author juanc
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
    private static void Registro(){
       
         ZMQ.Context context = ZMQ.context(1);
        // Socket to talk to server
        System.out.println("Connecting to hello world server");
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
                System.out.println("Ingrese un nombre de usuario:");
                String Nombre = sc.nextLine();
                System.out.println("Enviando nombre.....");
                socket.send(Nombre.getBytes(),0);
                break;
            case 2:
                break;
        }
        socket.close();
        context.term();
    }
    
}
