package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;


public class driverCtrlPonderacionsFile {

    public static void main(String args[]) throws FileNotFoundException{
        CtrlPonderacionsFile CPF = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.println("\t 1-getInstance : obtenir única instància Controlador de Ponderacions");
        System.out.println("\t 2-getAll <String- PlantillaPelicules.csv> : obtenir obtenir llista d'strings de totes les ponderacions del fitxer ");
        System.out.println("\t 3-end : Acaba el driver ");
        String action = sc.next();
        while(!action.equals("end")){
            if(action.equals("getInstance")){
                if(CPF != null) System.out.println("Ja exiteix la instància de CtrlPonderacionsFile");
                else {
                    CPF = CtrlPonderacionsFile.getInstance();
                    System.out.println("S'ha creat correctament la instància de CtrlPonderacionsFile");
                }
            }
            else if(action.equals("getAll")){
                File f = new File("PLANTILLES/PlantillaPelicules.csv");
                if(f.exists()){
                    if(CPF !=null){
                        List<String> ponderacions = CPF.getAll("PlantillaPelicules.csv");
                        System.out.println("El fitxer PlantillaPelicules.csv existeix, s'ha llegit correctament i ha inserit els items a :\n" + ponderacions);

                    }
                    else System.out.println("CtrlPonderacionsFile no iniciat");
                }
                else System.out.println("El fitxer no consta al sistema");
            }
            action= sc.next();

        }

    }

}
