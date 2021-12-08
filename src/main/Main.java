package main;

import algoritmos.Avaluator;
import algoritmos.ContentBasedFiltering;
import algoritmos.HybridApproach;
import algoritmos.collaborativeFiltering;
import domini.CtrlDomini;
import item.ItemManager;
import presentacio.CtrlPresentacio;
import user.userManager;

import java.util.*;


public class Main {
    public static int valorK = 3;

    public static void main(String[] args) throws Exception{
        System.out.println();
        userManager manager = userManager.getInstance();
        CtrlDomini CDomini = CtrlDomini.getInstance();
        CtrlPresentacio CPres = CtrlPresentacio.getInstance();

        CDomini.obtenirDades(manager);

        System.out.println("User manager rellenat");
        ItemManager items = new ItemManager();

        List<String> users = manager.getUsuaris();
        List<Integer> usersInt = new LinkedList<>();
        for(String s : users) usersInt.add(Integer.parseInt(s));
        Collections.sort(users);
        Collections.sort(usersInt);

        items.fillMapDistances(CDomini.carregarDadesItems());
        manager.setItemMan(items);

        collaborativeFiltering col = new collaborativeFiltering(manager);
        ContentBasedFiltering cb = new ContentBasedFiltering(manager,items);
        HybridApproach hb = new HybridApproach(cb, col);
        Avaluator av = new Avaluator();

        col.kmeans(manager, items.getItems(),valorK);
        ArrayList<String> conjunt = col.getCluster(1);
        col.construirMatriuDiferencies(items.getItems(), conjunt);
        Scanner sc = new Scanner(System.in);
        System.out.println("1- Obtenir items recomanats a usuari (Content Based)");
        System.out.println("2- Obtenir items recomanats a usuari (Collaborative)");
        System.out.println("3- Hybrid");
        System.out.println("4- Evaluator");

        String action;
        action = sc.next();

        while(!action.equals("end")){
            if(action.equals("1")){
                System.out.print("Indrodueix l'usuari del que vols obtenir les recomanacions:");
                String user_id = sc.next();
                List<Integer> items_rec = cb.calculate(user_id,valorK,items.getItems());
                System.out.println("Items recomanats a l'user "+ user_id + " :" + items_rec);
            }
            else if(action.equals("2")){
                System.out.print("Indrodueix l'usuari del que vols obtenir les recomanacions:");
                String user_id = sc.next();
                List<Integer> recommendations = col.calculate(user_id,valorK, items.getItems());
                System.out.println("Items recomanats a l'user "+ user_id + " :" + recommendations);
            }
            else if(action.equals("3")){
                System.out.print("Indrodueix l'usuari del que vols obtenir les recomanacions:");
                String user_id = sc.next();
                List<Integer> items_rec = hb.calculate(user_id, valorK, items.getItems());
                System.out.println(items_rec);
            }
            else if (action.equals("4")){
                col.setTrue();
                System.out.print("Indrodueix l'usuari del que vols obtenir les recomanacions:");
                String userId = sc.next();
                List<Integer> colItems = col.calculate(userId, 0, items.getItems());
                int aux = manager.numReviews(userId);
                int k = items.getItems().size() - aux;
                Map<Integer,Double> unknown = CDomini.carregarUnknown(userId); //llista dels items no valorats ordenada per valoracio
                List<Integer> cbItems = cb.calculate(userId,k,items.getItems());
                av.evaluate(colItems, cbItems, unknown);
            }
            action= sc.next();
        }
    }
}