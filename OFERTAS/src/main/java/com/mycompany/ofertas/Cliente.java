package com.mycompany.ofertas;

import com.mycompany.ofertas.enums.Sector;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Cliente {

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
                        if(usuario.equals(empl.getNombre()) && clave.equals(empl.getClave())){
                            menuEmpleador(empl,o);
                        }
                    }
                    for (Aspirante aspi: asp){
                        if(usuario.equals(aspi.getNombre()) && clave.equals(aspi.getClave())){
                            menuAspirante(aspi,o);
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
                            e.setClave(sc.nextLine());
                            e.setId(GenerarCodigoUsuarios());
                            o.registrarEmpleador(e);
                            
                            ArrayList<Empleador> empImp = o.retornarEmpleador();
                            for(Empleador empleador: empImp){
                                System.out.println("ID: "+empleador.getId());
                                System.out.println("Nombre: "+empleador.getNombre());
                                System.out.println("clave: " +empleador.getClave());
                            }
                            break;
                        case 2:
                            Aspirante a = new Aspirante();
                            System.out.println("Ingrese su nombre de usuario: ");
                            a.setNombre(sc.nextLine());
                            System.out.println("Ingrese una clave: ");
                            a.setClave(sc.nextLine());
                            System.out.println("Ingrese su edad: ");
                            a.setEdad(sc.nextInt());
                            a.setId(GenerarCodigoUsuarios());
                            o.registrarAspirante(a);
                            
                            ArrayList<Aspirante> AspImp = o.retornarAspirantes();
                            for (Aspirante aspirante: AspImp){
                                System.out.println("ID: "+aspirante.getId());
                                System.out.println("Nombre: "+aspirante.getNombre());
                                System.out.println("clave: "+aspirante.getClave());
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
    public static void menuEmpleador(Empleador emp, ImpOfertas o) throws RemoteException{
        Scanner sc = new Scanner(System.in);
        int op = 0, op2 = 0;
        while (op!=4){
            System.out.println("*/-------MENU DE EMPLEADOR-------/*");
            System.out.println("1.Crear oferta.");
            System.out.println("2......");
            System.out.println("3......");
            System.out.println("4.SALIR");
            System.out.println("*/-------------------------------/*");
            op = sc.nextInt();
            sc.nextLine();
            switch(op){
                case 1:
                    Oferta of = new Oferta();
                    of.setId(GenerarCodigoOferta()+"");
                    System.out.println("Ingrese nombre de la oferta: ");
                    of.setNombre(sc.nextLine());
                    System.out.println("Ingrese los requisitos sobre la oferta:");
                    of.setRequisitos(sc.nextLine());
                    System.out.println("Seleccionar un sector:");
                    System.out.println("1.FUERZAS_MILITARES");
                    System.out.println("2.GERENCIA");
                    System.out.println("3.PROFESIONALES_INTELECTUALES");
                    System.out.println("4.TECNICOS");
                    System.out.println("5.APOYO_ADMINISTRATIVO");
                    op2 = sc.nextInt();
                    sc.nextLine();
                    switch(op2){
                        case 1:
                            of.setSector(Sector.FUERZAS_MILITARES);
                            break;
                        case 2:
                            of.setSector(Sector.GERENCIA);
                            break;
                        case 3:
                            of.setSector(Sector.PROFESIONALES_INTELECTUALES);
                            break;
                        case 4:
                            of.setSector(Sector.TECNICOS);
                            break;
                        case 5:
                            of.setSector(Sector.APOYO_ADMINISTRATIVO);
                            break;
                    }
                    System.out.println("Ingrese el sueldo:");
                    of.setSueldo(sc.nextFloat());
                    sc.nextLine();
                    System.out.println("Ingrese la edad requerida:");
                    of.setEdadRequerida(sc.nextInt());
                    sc.nextLine();
                    of.setEmpleador(emp);
                    o.IngresarOferta(of);
                    ArrayList<Oferta> ofertas = o.retornarOfertas();
                    for(Oferta ofer: ofertas){
                        System.out.println("ID: "+ofer.getId());
                        System.out.println("Nombre: "+ofer.getNombre());
                        System.out.println("Requisitos: "+ofer.getRequisitos());
                        System.out.println("Edad requerida: "+ofer.getEdadRequerida());
                        System.out.println("Empleador: " +ofer.getEmpleador().getNombre());
                        System.out.println("Sector: "+ofer.getSector());
                        System.out.println("Sueldo: "+ofer.getSueldo());
                    }
                    break;
            }
        }
    
    }
    public static void menuAspirante(Aspirante asp,ImpOfertas o){
        Scanner sc = new Scanner(System.in);
        int op = 0;
        while (op!=4){
            System.out.println("*/-------MENU DE ASPIRANTE-------/*");
            System.out.println("1.Suscribirse a sector");
            System.out.println("2......");
            System.out.println("3......");
            System.out.println("4.SALIR");
            System.out.println("*/-------------------------------/*");
            op = sc.nextInt();
            sc.nextLine();
            switch(op){
                case 1:
                     
                    break;
                case 2:
                    break;
                case 3:
                    break;
                   
            }
        }
    
    }
    public static int GenerarCodigoUsuarios(){
        Random ran = new Random();
        return ran.nextInt(9999) + 1000;
    }
    public static int GenerarCodigoOferta(){
        Random ran = new Random();
        return ran.nextInt(999999) + 100000;
    }
    
    
}
