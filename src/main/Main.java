package main;



<<<<<<< HEAD
=======
import algoritmos.ContentBasedFiltering;
>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835
import algoritmos.collaborativeFiltering;
import data.CtrlDades;
import item.ItemManager;
import user.userManager;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;
<<<<<<< HEAD
=======

>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835


public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println();
        userManager manager = userManager.getInstance();
        CtrlDades CD = CtrlDades.getInstance();
<<<<<<< HEAD
=======

        CD.obtenir_dades(manager);
        System.out.println("User manager rellenat");

        ItemManager items = new ItemManager();


        List<String> users = manager.getUsuaris();
        List<Integer> usersInt = new LinkedList<>();
        for(String s : users) usersInt.add(Integer.parseInt(s));
        Collections.sort(users);
        Collections.sort(usersInt);

        items.fillMapDistances(CD.getItems());

        manager.setItemMan(items);

        //Passem el paràmetre 3
//        for (int i = 0; i < users.size(); ++i) {
//            manager.getItemsSemblants(users.get(i), 3);
//        }



        collaborativeFiltering col = new collaborativeFiltering(manager);
        ContentBasedFiltering cb = new ContentBasedFiltering(manager,items);
        col.kmeans(manager, items.getItems(),3);
        ArrayList<String> conjunt = col.getCluster(1);
        col.construirMatriuDiferencies(items.getItems(), conjunt);
        Scanner sc = new Scanner(System.in);
        String action;
        action = sc.next();

        while(!action.equals("end")){
            if(action.equals("Get_items_semblants")){
                String user_id = sc.next();
                cb.getItemsSemblants(user_id,3);
            }
            else if(action.equals("Cluster")){
                Integer i = sc.nextInt();
                conjunt= col.getCluster(i);
                col.construirMatriuDiferencies(items.getItems(), conjunt);
                col.writeCjtClusters();
                String user_id = sc.next();
                Integer item_id = sc.nextInt();
                System.out.println(col.recommended(user_id,item_id,items.getItems()));
            }
            action= sc.next();
        }


>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835

        CD.obtenir_dades(manager);
        System.out.println("User manager rellenat");

        ItemManager items = new ItemManager();

        List<String> users = manager.getUsuaris();
        List<Integer> usersInt = new LinkedList<>();
        for(String s : users) usersInt.add(Integer.parseInt(s));
        Collections.sort(users);
        Collections.sort(usersInt);

        items.fillMapDistances(CD.getItems());

        manager.setItemMan(items);

        //Passem el paràmetre 3
//        for (int i = 0; i < users.size(); ++i) {
//            manager.getItemsSemblants(users.get(i), 3);
//        }

        collaborativeFiltering al = new collaborativeFiltering(manager);
        al.construirMatriuDiferencies(items.getItems());
//        al.pinta_mat();
//
//        117838,2028,5.0
//        117838,1923,4.0
//        117838,318,3.0
//        117838,527,5.0
//        117838,923,5.0
//        117838,858,5.0
//        117838,1089,5.0
//        117838,110,5.0
//        117838,608,5.0
//        117838,260,3.0

        String user_id = "117838";
        Integer item_id = 608;
        System.out.println( "A l'usuari "+user_id+", creim que puntuara l'item: " + item_id + " amb puntuacio: " + al.recommended(user_id, item_id, items.getItems()));

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
