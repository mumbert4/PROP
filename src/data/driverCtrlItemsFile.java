package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class driverCtrlItemsFile {
    public static void main(String args[]) throws FileNotFoundException {
        CtrlItemsFile CIF = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.println("\t 1- getInstance: obtenir única instància Controlador de Items");
        System.out.println("\t 2- getAll <String- items.csv>: obtenir una llista d'strings de tots els items del fitxer");
        String action;
        action = sc.next();
        while (!action.equals("end")) {
            if (action.equals("getInstance")) {
                if (CIF != null) System.out.println("Ja existeix la instància de CtrlItemsFile");
                else {
                    CIF = CtrlItemsFile.getInstance();
                    System.out.println("S'ha creat correctament la instància de CtrlItemsFile");
                }
            }
            /*Suposarem que el fitxer és no buit i el fitxer sempre pren el nom de items.csv*/
            if (action.equals("getAll")) {
                File f = new File("DATA/items.csv");
                if (f.exists()) {
                    if (CIF != null) {
                        List<String> items = CIF.getAll("items.csv");
                        System.out.println("El fitxer items.csv existeix, s'ha llegit correctament i ha inserit els items a: \n" + items);
                    } else System.out.println("CtrlItemsFile no iniciat");
                } else System.out.println("El fitxer no consta al sistema");
            }
            action = sc.next();
        }
    }
}