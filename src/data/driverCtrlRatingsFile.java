package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class driverCtrlRatingsFile {
    public static void main(String args[]) throws FileNotFoundException {
        CtrlRatingsFile CRF = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.println("\t 1- getInstance: obtenir única instància Controlador de Ratings");
        System.out.println("\t 2- getAll <String- ratings.test.known.csv>: obtenir una llista d'strings de tots els ratings del fitxer");
        String action;
        action = sc.next();
        while (!action.equals("end")) {
            if (action.equals("getInstance")) {
                if (CRF != null) System.out.println("Ja existeix la instància de CtrlRatingsFile");
                else {
                    CRF = CtrlRatingsFile.getInstance();
                    System.out.println("S'ha creat correctament la instància de CtrlRatingsFile");
                }
            }
            /*Suposarem que el fitxer és no buit i el fitxer sempre pren el nom de ratings.test.known.csv*/
            if (action.equals("getAll")) {
                File f = new File("DATA/ratings.test.known.csv");
                if (f.exists()) {
                    if (CRF != null) {
                        List<String> ratings = CRF.getAll("ratings.test.known.csv");
                        System.out.println("El fitxer ratings.test.known.csv existeix, s'ha llegit correctament i ha inserit els ratings a " + ratings);
                    }
                    else System.out.println("CtrlRatingsFile no iniciat");
                }
                else System.out.println("El fitxer no consta al sistema");
            }
            action = sc.next();
        }
    }
}
