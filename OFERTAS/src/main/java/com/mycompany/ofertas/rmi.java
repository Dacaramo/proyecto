/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ofertas;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author juanc
 */
public class rmi extends UnicastRemoteObject implements ImpOfertas {

    public rmi() {
    }
    
    @Override
    public void met1() throws RemoteException {
        System.out.println("to bien compa");
    }
    
}