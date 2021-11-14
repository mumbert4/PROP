package main;
//
//<<<<<<< HEAD
//import userManager.userManager;
//import activeUser.activeUser;
//
//public class Main {
//    public static void main(String[] args) {
//        userManager manager = userManager.getInstance();
//        activeUser user = new activeUser("Miquel", "1234");
//        manager.addUser(user);
//        manager.createReview("Miquel",16, 5.5, "Ha estat molt be");
//        manager.createReview("Miquel",16, 5.5, "Ha estat molt be");
//        System.out.println("Usuari Miquel ha fet: " + manager.numReviews("Miquel")+ " reviews en total");
//=======
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
//        CD.escriureItems();
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

        //2-aquesta millor diria
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
