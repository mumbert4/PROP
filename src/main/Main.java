package main;



import data.CtrlDades;
import item.ItemManager;
import user.userManager;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println();
        userManager manager = userManager.getInstance();
        CtrlDades CD = CtrlDades.getInstance();
//        CD.escriureItems();
//        CD.escriureRatings();
        List<String> user_ids= new LinkedList<String>();
        List<Integer> item_ids= new LinkedList<Integer>();
        List<Double> raitings = new LinkedList<Double>();
        CD.obtenir_dades(user_ids, item_ids, raitings);
        //System.out.println("Ara toca rellenar el item Manager");
        ItemManager items = new ItemManager();
        //System.out.println("Dades obtingudes, rellenam el USER MANAGER");
        for(int i = 0; i < user_ids.size(); ++i){
            String user_name = user_ids.get(i);
            int item_id = item_ids.get(i);
            Double raiting = raitings.get(i);
            if(!(manager.existUser(user_ids.get(i)))) manager.createUser(user_ids.get(i), "",""); //possam les passwords en blanc de moment
            manager.createReview(user_name,item_id,raiting,"");//possam comentari en blanc de moment
        }


        List<String> users = manager.getUsuaris();
        Collections.sort(users);
//        for (int i = 0; i < users.size(); ++i) {
//            String user_name = users.get(i);
//            System.out.println("user_id: " + user_name);
//            manager.getReviewsUsers(user_name);
//        }

;
        items.fillMapDistances(CD.getItems());

        //HEM DE FER QUE DONAT UN USER ID, ENS RETORI ELS K ELEMENTS MES SEMBLANTS A NES QUE ELL LI AGRADEN
        manager.setItemMan(items);
//        items.retornaItemsSemblants(628);
        manager.getItemsSemblants("1625");
































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
