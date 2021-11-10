package view;

import java.io.IOException;
import java.util.Scanner;
import org.kth.dks.JDHT;

public class ProveedorJDHT {
    public static void main(String[] args) throws Exception {
        try 
        {
            JDHT DHTExample = new JDHT();
            DHTExample.put("Java SE API", "http://docs.oracle.com/javase/8/docs/api/");

            System.out.println("\n\n" + ((JDHT) DHTExample).getReference());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Press Enter to terminate application... ");
            scanner.next();
            DHTExample.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
