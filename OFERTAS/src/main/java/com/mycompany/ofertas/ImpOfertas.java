/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ofertas;

import com.mycompany.ofertas.enums.Sector;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author juanc
 */
public interface ImpOfertas extends Remote {
    public void met1() throws RemoteException;
    public int suma(int a, int b) throws RemoteException;
    public ArrayList<Empleador> retornarEmpleador() throws RemoteException;
    public ArrayList<Aspirante> retornarAspirantes() throws RemoteException;
    public ArrayList<Oferta> retornarOfertas() throws RemoteException;
    public void registrarEmpleador(Empleador emp) throws RemoteException;
    public void registrarAspirante(Aspirante asp) throws RemoteException;
    public void IngresarOferta(Oferta af) throws RemoteException;
    public void SuscribirSector1(Sector s, int ID) throws RemoteException;
    public void SuscribirSector2(Sector s, int ID) throws RemoteException;
    public Aspirante retornarAspirante(int id) throws RemoteException;
}