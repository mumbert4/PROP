package main;

import data.CtrlDades;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println();
        CtrlDades CD = CtrlDades.getInstance();
        CD.escriureItems();
        CD.escriureRatings();

        /* Crea el fitxer users.csv on hi podrem afegir els nous usuaris que es registrin a a la nostra app */
        /* Aquí ha de crear-se la funció de CrearPerfil(signUp), CarregarPerfil(logIn), EsborrarPerfil i
           ModificarPerfil */

        /* DUES FORMES DIFERENTS D'INSERIR INFO EN UN CSV*/

        //1
        FileOutputStream fileOut = new FileOutputStream("DATA/users.csv");
        //Instantiating the DataOutputStream class
        DataOutputStream outputStream = new DataOutputStream(fileOut);
        /* Writing UTF data to the output stream */
        outputStream.writeChars("marta");
        outputStream.flush();

        //2-aquest millor diria
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("DATA/users2.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String builder = "marta";
        Objects.requireNonNull(pw).write(builder);
        pw.close();
    }
}
