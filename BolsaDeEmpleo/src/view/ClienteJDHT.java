package view;

import java.io.IOException;
import java.util.Scanner;
import model.Oferta;
import org.kth.dks.JDHT;
import org.kth.dks.dks_exceptions.DKSIdentifierAlreadyTaken;
import org.kth.dks.dks_exceptions.DKSRefNoResponse;
import org.kth.dks.dks_exceptions.DKSTooManyRestartJoins;

public class ClienteJDHT {
    public static void main(String[] args) throws Exception {
        char salir = 'n';
        String ref, llave;
        Scanner sc = new Scanner(System.in);

        try 
        {
            System.out.print("\n\nIngrese el ref de la DHT generado por el otro programa: ");
            ref = sc.next();  
            
            JDHT myDHT = new JDHT(5550, ref);
            
            do
            {
                System.out.print("\n");
                
                System.out.print("Ingrese la llave del valor a buscar en la DHT: ");
                llave = sc.next();
                
//                String value = (String) myDHT.get(llave);
//                System.out.print("El valor es " + value);

                Oferta oferta = (Oferta) myDHT.get(llave);
                System.out.println("----");
                System.out.println(oferta.getId());
                System.out.println(oferta.getEdadRequerida());
                System.out.println(oferta.getNombre());
                System.out.println(oferta.getSector().toString());
                System.out.println(oferta.getSueldo());
                System.out.println("----");
                
                System.out.print("\n\n");
                
                System.out.print("Desea salir del programa? (s: si/n: no): ");
                salir = sc.next().charAt(0);
            }
            while(salir != 's');
            
            myDHT.close();
        }
        catch (IOException | DKSTooManyRestartJoins |
            DKSIdentifierAlreadyTaken | DKSRefNoResponse ex) {
              ex.printStackTrace();
            }
    }
}
