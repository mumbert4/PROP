package main;



import algoritmos.collaborativeFiltering;
import data.CtrlDades;
import item.ItemManager;
import user.userManager;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;



public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println();
        userManager manager = userManager.getInstance();
        CtrlDades CD = CtrlDades.getInstance();

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
        List<Integer> Its= new ArrayList<>(Arrays.asList(5044, 143));
        Map<String,Map<Integer,Double>> mat= new HashMap();
//        for(int i = 0; i < usersInt.size(); ++i ){
//
//            Integer user_id = usersInt.get(i);
////            System.out.println(user_id);
//            Map<Integer,Double> rev = manager.getReviewsUsers(String.valueOf(user_id));
//            mat.put(String.valueOf(user_id), rev);
//        }
        for(int i = 0; i < Its.size(); ++i ){

            Integer user_id = Its.get(i);
//            System.out.println(user_id);
            Map<Integer,Double> rev = manager.getReviewsUsers(String.valueOf(user_id));
            mat.put(String.valueOf(user_id), rev);
        }
        collaborativeFiltering al = new collaborativeFiltering();
        al.construirMatriuDiferencies(mat);
        al.pinta_mat();




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
