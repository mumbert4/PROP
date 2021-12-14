package data;

import user.UserManager;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class driverCtrlData {
    public static void main(String args[]) throws FileNotFoundException {
        CtrlData CD = null;
        UserManager userManager = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.println("\t 1- getInstance: obtenir única instància Controlador de Dades amb l'inicialització pertinent del Controlador d'Items i Ratings");
        System.out.println("\t 2- getItems: obté els items que es troben al file items.csv a través del CtrlItemsFile");
        System.out.println("\t 3- getUnknown <String- userId>: obté els ratings que es troben al file ratings.unknown.test.csv a través del CtrlRatingsFile");
        System.out.println("\t 4- obtainData <UserManager- userManager>: obté els ratings que es troben al file ratings.known.test.csv a través del CtrlRatingsFile");
        String action;
        action = sc.next();
        while (!action.equals("end")) {
            if (action.equals("getInstance")) {
                if (CD != null) System.out.println("Ja existeix la instància de CtrlData");
                else {
                    CD = CtrlData.getInstance();
                    CD.initializeCtrlData(); //no és correte fer que initializeCtrlData() sigui publica diria
                    System.out.println("S'ha creat correctament la instància de CtrlData amb els corresponents Controladors CIF i CRF");
                }
            }
            if (action.equals("getItems")) {
                if (CD != null) {
                    CD.getItems();
                    System.out.println("obté els items que es troben al file items.csv a través del CtrlItemsFile");
                }
                else System.out.println("CtrlData no iniciat");
            }
            if (action.equals("getUnknown")) {
                if (CD != null) {
                    System.out.print("Introdueix el userId de l'usuari: ");
                    String userId = sc.next();
                    if (CD.getUnknown(userId).size() != 0) {
                        System.out.println("obté els items que es troben al file ratings.test.unknown.csv a través del CtrlRatingsFile del user " + userId);
                    }
                    else  System.out.println("No tenim items per aquest usuari al file ratings.test.unknown.csv a través del CtrlRatingsFile del user " + userId);
                }
                else System.out.println("CtrlData no iniciat");
            }
            if (action.equals("obtainData")) {
                if (CD != null) {
                    userManager = UserManager.getInstance();
                    if (userManager != null) {
                        CD.obtainData(userManager);
                        System.out.println("S'ha omplert corretament el userManager ");
                    }
                    else System.out.println("UserManager no iniciat");
                }
                else System.out.println("CtrlData no iniciat");
            }
            action = sc.next();
        }
    }
}
