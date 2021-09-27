/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ofertas;

import java.rmi.registry.Registry;

/**
 *
 * @author juanc
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        try {
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(1099);
            r.rebind("ImpOfertas", new rmi());
            System.out.println("Servidor Conectado");
        } catch (Exception e) {
            System.out.println("Servidor no conectado "+e);
        }
    }
    
}
