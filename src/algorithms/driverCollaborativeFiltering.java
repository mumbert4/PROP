package algorithms;

import domain.CtrlDomain;
import user.UserManager;

import java.io.IOException;
import java.util.Scanner;

public class driverCollaborativeFiltering {
    public static void main(String args[]) throws IOException{
        UserManager users = null;
        //ItemManager items = null;
        CollaborativeFiltering col = null;
        CtrlDomain CDomini = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions");
        System.out.println("\t 1-Initialize : inicialitza l'algoritme de CollaborativeFiltering, l'ItemManager i el UserManager");
        System.out.println("\t 2-Calculate <String- UserId> <Integer - K> <List<Integer> Items> : recomana K elements de la llista Items a l'usuari amb identificador UserId");
//        System.out.println("\t 3-getDistance <Integer- Item1> <Integer- Item2> : Retorna la distancia entre els dos items donats");
        System.out.println("\t 4-end : Acaba amb el driver");

        String action = sc.next();
        while(!action.equals("end")){
            if(action.equals("Initialize")){
                if(col == null){
                    users = UserManager.getInstance();
                    CDomini = CtrlDomain.getInstance();
                    CDomini.obtainData(users);
                    //items = new ItemManager();
//                    items.fillMapDistances(CDomini.getItems());
//                    items.fillPonderacions(CDomini.getPonderacions());
                    //users.setItemMan(items);
                    //col = new CollaborativeFiltering(users);
                    //col.kmeans(users, items.getItems(), 3);
                    //col.buildDifferencesMatrix(items.getItems(), col.getCluster(1) );
                    System.out.println("Tot inicialitzat correctament");
                }
                else System.out.println("Ja esta inicialitzat");
            }
            else if(action.equals("Calculate")){
                if(col !=null){
                    System.out.print("Insereix el userId: ");
                    String userId = sc.next();
                    System.out.print("Insereix el valor K: ");
                    Integer k = sc.nextInt();
                    if(users.existUser(userId)) {
                        //List<Integer> recommendations = col.calculate(userId,k,items.getItems());
                        //System.out.println("Items recomanats a l'usuari "+ userId + " :"+recommendations);
                    }
                    else System.out.println("L'usuari donat no exiteix");

                }
                else System.out.println("Inicialitza primer de tot");
            }
//            else if(action.equals("getDistance")){
//                if(col !=null){
//                    System.out.println("Insereix els ids dels dos items");
//                    Integer item1 = sc.nextInt();
//                    Integer item2 = sc.nextInt();
//                    if(items.existItem(item1) && items.existItem(item2)){
//                        System.out.println("Distancia entre els items: " + col.getDistance(item1,item2));
//                    }
//                    else System.out.println("Un dels items no existeix");
//                }
//                else System.out.println("Inicialitza primer de tot");
//            }
            action = sc.next();
        }



    }
}
