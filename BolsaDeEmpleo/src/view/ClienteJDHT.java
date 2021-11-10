package view;

import java.io.IOException;
import org.kth.dks.JDHT;
import org.kth.dks.dks_exceptions.DKSIdentifierAlreadyTaken;
import org.kth.dks.dks_exceptions.DKSRefNoResponse;
import org.kth.dks.dks_exceptions.DKSTooManyRestartJoins;

public class ClienteJDHT {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        try 
        {
            JDHT myDHT = new JDHT(5550, "dksref://192.168.1.10:4440/0/4220662422/0/6885128012772432190");

            String value = (String) myDHT.get("Java SE API");
            System.out.println("\n\n" + value);
            myDHT.close();
        }
        catch (IOException | DKSTooManyRestartJoins |
            DKSIdentifierAlreadyTaken | DKSRefNoResponse ex) {
              ex.printStackTrace();
            }
    }
}
