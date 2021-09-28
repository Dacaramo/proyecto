/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ofertas;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author juanc
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op = 0;
        try {//25.86.91.11
            Registry miRegistro = LocateRegistry.getRegistry("localhost", 1099);
            ImpOfertas o = (ImpOfertas)Naming.lookup("//localhost/ImpOfertas");
            
           while(op!=3){
            System.out.println("*/-------SISTEMA DE OFERTAS LABORALES-------/*");
            System.out.println("1.INGRESAR");
            System.out.println("2.REGISTRARSE");
            System.out.println("3.SALIR");
            System.out.println("*/------------------------------------------/*");
            op = sc.nextInt();
            sc.nextLine();
            switch(op){
                case 1:
                    System.out.println("Ingrese nombre de Usuario: ");
                    String usuario = sc.nextLine();
                    System.out.println("Ingrese clave: ");
                    System.out.println("*/------------------------------------------/*");
                    String clave = sc.nextLine();
                    ArrayList<Empleador> emp = o.retornarEmpleador();
                    ArrayList<Aspirante> asp = o.retornarAspirantes();
                    for(Empleador empl:emp){
                        if(usuario.equals(empl.getNombre()) && clave.equals(empl.getPassword())){
                            menuEmpleador();
                        }
                    }
                    for (Aspirante aspi: asp){
                        if(usuario.equals(aspi.getNombre()) && clave.equals(aspi.getPassword())){
                            menuAspirante();
                        }
                    
                    }
                    break;
                case 2:
                    System.out.println("*/--------SELECCIONE TIPO DE USUARIO--------/*");
                    System.out.println("1.EMPLEADOR");
                    System.out.println("2.ASPIRANTE");
                    System.out.println("*/------------------------------------------/*");
                    int op2 = sc.nextInt();
                    sc.nextLine();
                    switch(op2){
                        case 1:
                            Empleador e = new Empleador();
                            System.out.println("Ingrese su nombre de usuario: ");
                            e.setNombre(sc.nextLine());
                            System.out.println("Ingrese una clave: ");
                            e.setPassword(sc.nextLine());
                            e.setId(GenerarCodigoUsuarios());
                            o.registrarEmpleador(e);
                            
                            ArrayList<Empleador> empImp = o.retornarEmpleador();
                            for(Empleador empleador: empImp){
                                System.out.println("ID: "+empleador.getId());
                                System.out.println("Nombre: "+empleador.getNombre());
                                System.out.println("clave: " +empleador.getPassword());
                            }
                            break;
                        case 2:
                            Aspirante a = new Aspirante();
                            System.out.println("Ingrese su nombre de usuario: ");
                            a.setNombre(sc.nextLine());
                            System.out.println("Ingrese una clave: ");
                            a.setPassword(sc.nextLine());
                            System.out.println("Ingrese su edad: ");
                            a.setEdad(sc.nextInt());
                            a.setId(GenerarCodigoUsuarios());
                            o.registrarAspirante(a);
                            
                            ArrayList<Aspirante> AspImp = o.retornarAspirantes();
                            for (Aspirante aspirante: AspImp){
                                System.out.println("ID: "+aspirante.getId());
                                System.out.println("Nombre: "+aspirante.getNombre());
                                System.out.println("clave: "+aspirante.getPassword());
                                System.out.println("Edad: "+aspirante.getEdad());
                            }
                            break;
                    
                    }
                    break;
            
            }
        }
        } catch (Exception e) {
            System.out.println("Error de conexion......"+e);
        }
    }
    public static void menuEmpleador(){
        Scanner sc = new Scanner(System.in);
        int op = 0;
        while (op!=4){
            System.out.println("*/-------MENU DE EMPLEADOR-------/*");
            System.out.println("1......");
            System.out.println("2......");
            System.out.println("3......");
            System.out.println("4.SALIR");
            System.out.println("*/-------------------------------/*");
            op = sc.nextInt();
            sc.nextLine();
        }
    
    }
    public static void menuAspirante(){
        Scanner sc = new Scanner(System.in);
        int op = 0;
        while (op!=4){
            System.out.println("*/-------MENU DE ASPIRANTE-------/*");
            System.out.println("1......");
            System.out.println("2......");
            System.out.println("3......");
            System.out.println("4.SALIR");
            System.out.println("*/-------------------------------/*");
            op = sc.nextInt();
            sc.nextLine();
        }
    
    }
    public static int GenerarCodigoUsuarios(){
        Random ran = new Random();
        return ran.nextInt(9999) + 1000;
    }
    
}
