package main;



import algoritmos.ContentBasedFiltering;
import algoritmos.HybridApproach;
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



        collaborativeFiltering col = new collaborativeFiltering(manager);
        ContentBasedFiltering cb = new ContentBasedFiltering(manager,items);
        HybridApproach hb = new HybridApproach();
        col.kmeans(manager, items.getItems(),3);
        ArrayList<String> conjunt = col.getCluster(1);
        col.construirMatriuDiferencies(items.getItems(), conjunt);
        Scanner sc = new Scanner(System.in);
        System.out.println("1- Obtenir items recomanats a usuari (Content Based)");
        System.out.println("2- Obtenir items recomanats a usuari (Collaborative)");
        System.out.println("3- Hybrid");

        String action;
        action = sc.next();



        while(!action.equals("end")){
            if(action.equals("1")){
                String user_id = sc.next();

                List<Integer> items_rec = cb.getItemsSemblants(user_id,3);
                System.out.println("Items recomanats a l'user "+ user_id + " :" + items_rec);
            }
            else if(action.equals("2")){
                col.construirMatriuDiferencies(items.getItems(), conjunt);
                col.writeCjtClusters();

                System.out.print("Indrodueix el cluster que vols i el usuari del que vols obtenir les recomanacions:");

                Integer i = sc.nextInt();
                String user_id = sc.next();

                Map<Integer,Double> recommendations = col.recommended(user_id, i , items.getItems());


                System.out.println(recommendations);
            }
            else if(action.equals("3")){
                col.construirMatriuDiferencies(items.getItems(), conjunt);
                col.writeCjtClusters();
                System.out.print("Indrodueix el cluster que vols i el usuari del que vols obtenir les recomanacions:");

                Integer i = sc.nextInt();
                String user_id = sc.next();

                Map<Integer,Double> recommendations = col.recommended(user_id, i , items.getItems());
                List<Integer> items_col = new LinkedList<>();
                for(Map.Entry<Integer,Double> e : recommendations.entrySet()) items_col.add(e.getKey());

                List<Integer> items_cb= cb.getItemsSemblants(user_id,3);

                Set<Integer> items_rec = hb.calculate(items_col,items_cb);
                System.out.println(items_rec);
            }
            action= sc.next();
        }




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
