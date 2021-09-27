/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ofertas;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author juanc
 */
public class rmi extends UnicastRemoteObject implements ImpOfertas {
    
    ArrayList<Empleador> empleadores;
    ArrayList<Aspirante> aspirantes;
    ArrayList<Oferta> ofertas;

    public rmi()  throws RemoteException {
        empleadores = new ArrayList<>();
        aspirantes = new ArrayList<>();
        ofertas = new ArrayList<>();
    }

    
    
    @Override
    public void met1() throws RemoteException {
        System.out.println("to bien compa");
    }

    @Override
    public int suma(int a, int b) throws RemoteException {
       return a+b;
    }

    @Override
    public ArrayList<Empleador> retornarEmpleador()  throws RemoteException{
        return empleadores;
    }

    @Override
    public ArrayList<Aspirante> retornarAspirantes() throws RemoteException { 
        return aspirantes;
    }

    @Override
    public void registrarEmpleador(Empleador emp) throws RemoteException {
        empleadores.add(emp);
    }

    @Override
    public void registrarAspirante(Aspirante asp) throws RemoteException {
         aspirantes.add(asp);
    }
    
    
}