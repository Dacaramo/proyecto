package com.mycompany.ofertas;

import java.rmi.registry.Registry;

public class Servidor {

   public static void main(String[] args) {
        try {
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(1099);
            r.rebind("ImpOfertas", new rmi());
            System.out.println("Servidor Conectado");
        } catch (Exception e) {
            System.out.println("Servidor no conectado " + e);
        }

        if(RepositorioProyecto.connect() != null)
            System.out.println("La conexión con la base de datos a sido exitosa");
    }
    
}
