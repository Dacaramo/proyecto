/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ofertas;

import com.mycompany.ofertas.enums.Sector;
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

    @Override
    public void IngresarOferta(Oferta af) throws RemoteException {
          ofertas.add(af);
    }

    @Override
    public ArrayList<Oferta> retornarOfertas() throws RemoteException {
        return ofertas;
    }

    @Override
    public void SuscribirSector1(Sector s, int ID) throws RemoteException {
        for(Aspirante asp: aspirantes){
            if(asp.getId() == ID){
                asp.setSector1(s);
                asp.setNsubs(asp.getNsubs()+1);
            }
        }
    }

    @Override
    public void SuscribirSector2(Sector s, int ID) throws RemoteException {
        for(Aspirante asp: aspirantes){
            if(asp.getId() == ID){
                asp.setSector2(s);
                asp.setNsubs(asp.getNsubs()+1);
            }
        }
    }

    @Override
    public Aspirante retornarAspirante(int id) throws RemoteException {
        for(Aspirante asp: aspirantes){
            if(id == asp.getId()){
                return asp;
            }
        }
        return null;
    }
    
    
}