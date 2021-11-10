package view;

import java.io.IOException;
import java.util.Scanner;
import model.ConsoleColors;
import org.kth.dks.JDHT;

public class ProveedorJDHT {
    public static void main(String[] args) throws Exception {
        char salir = 'n';
        String llave, valor;
        Scanner sc = new Scanner(System.in);
        
        try 
        {
            JDHT DHTExample = new JDHT();
            System.out.println("\n" + ConsoleColors.GREEN_BACKGROUND + ((JDHT) DHTExample).getReference() + ConsoleColors.RESET);
            
            do
            {
                System.out.print("\n");
                
                System.out.print("Ingrese la llave de la nueva entrada en la DHT: ");
                llave = sc.next();
                
                System.out.print("Ingrese el valor de la nueva entrada en la DHT: ");
                valor = sc.next();
                
                System.out.print("\n");
                
                DHTExample.put(llave, valor);
                
                System.out.print("Desea salir del programa? (s: si/n: no): ");
                salir = sc.next().charAt(0);
            }
            while(salir != 's');
            
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press Enter to terminate application... ");
            scanner.next();
            DHTExample.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
