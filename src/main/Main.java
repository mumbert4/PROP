//package main;
//
//import algorithms.Avaluator;
//import algorithms.ContentBasedFiltering;
//import algorithms.HybridApproach;
//import algorithms.CollaborativeFiltering;
//import domain.CtrlDomain;
//import item.ItemManager;
//import presentation.CtrlPresentation;
//import user.UserManager;
//
//import java.util.*;
//
//
//public class Main {
//    public static int valorK = 3;
//
//    public static void main(String[] args) throws Exception{
//        System.out.println();
//        UserManager manager = UserManager.getInstance();
//        CtrlDomain CDomini = CtrlDomain.getInstance();
//
//        CtrlPresentation CPresentacio = CtrlPresentation.getInstance();
//
//        CDomini.obtainData(manager);
//
//        System.out.println("User manager rellenat");
//        ItemManager items = new ItemManager();
//
//        List<String> users = manager.getUsers();
//        List<Integer> usersInt = new LinkedList<>();
//        for(String s : users) usersInt.add(Integer.parseInt(s));
//        Collections.sort(users);
//        Collections.sort(usersInt);
//        items.fillPonderacions(CDomini.getPonderacions());
//        items.fillMapDistances(CDomini.getItems());
//
//        manager.setItemMan(items);
//
//        CollaborativeFiltering col = new CollaborativeFiltering(manager);
//        ContentBasedFiltering cb = new ContentBasedFiltering(manager,items);
//        HybridApproach hb = new HybridApproach(cb, col);
//        Avaluator av = new Avaluator();
//
//        col.kmeans(manager, items.getItems(),valorK);
//        ArrayList<String> conjunt = col.getCluster(1);
//        col.buildDifferencesMatrix(items.getItems(), conjunt);
//        Scanner sc = new Scanner(System.in);
//        System.out.println("1- Obtenir items recomanats a usuari (Content Based)");
//        System.out.println("2- Obtenir items recomanats a usuari (Collaborative)");
//        System.out.println("3- Hybrid");
//        System.out.println("4- Evaluator");
//
//        String action;
//        action = sc.next();
//
//        while(!action.equals("end")){
//            if(action.equals("1")){
//                System.out.print("Indrodueix l'usuari del que vols obtenir les recomanacions:");
//                String userId = sc.next();
//                List<Integer> itemsRec = cb.calculate(userId,valorK,items.getItems());
//                System.out.println("Items recomanats a l'user "+ userId + " :" + itemsRec);
//            }
//            else if(action.equals("2")){
//                System.out.print("Indrodueix l'usuari del que vols obtenir les recomanacions:");
//                String userId = sc.next();
//                List<Integer> recommendations = col.calculate(userId,valorK, items.getItems());
//                System.out.println("Items recomanats a l'user "+ userId + " :" + recommendations);
//            }
//            else if(action.equals("3")){
//                System.out.print("Indrodueix l'usuari del que vols obtenir les recomanacions:");
//                String userId = sc.next();
//                List<Integer> itemsRec = hb.calculate(userId, valorK, items.getItems());
//                System.out.println(itemsRec);
//            }
//            else if (action.equals("4")){
//                col.setTrue();
//                System.out.print("Indrodueix l'usuari del que vols obtenir les recomanacions:");
//                String userId = sc.next();
//                List<Integer> colItems = col.calculate(userId, 0, items.getItems());
//                int aux = manager.numReviews(userId);
//                int k = items.getItems().size() - aux;
//                Map<Integer,Double> unknown = CDomini.getRatings(userId); //llista dels items no valorats ordenada per valoracio
//                List<Integer> cbItems = cb.calculate(userId,k,items.getItems());
//                av.evaluate(colItems, cbItems, unknown);
//            }
//            action= sc.next();
//        }
//    }
//}