package algorithms;

import domain.CtrlDomain;
import item.ItemManager;
import user.UserManager;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class driverHybridApproach {
    public static void main(String args[]) throws IOException{
        UserManager users = null;
        ItemManager items = null;
        ContentBasedFiltering cb = null;
        CtrlDomain CDomini = null;
        Scanner sc = new Scanner(System.in);
        CollaborativeFiltering col = null;
        HybridApproach hb = null;
        System.out.println("Instruccions");
        System.out.println("\t 1-Initialize : inicialitza l'algoritme de Hybrid Approach, l'ItemManager, el UserManager i els algoritmes de Content Based i Collaborative Filtering");
        System.out.println("\t 2-Calculate <String- UserId> <Integer - K> <List<Integer> Items> : recomana K elements de la llista Items a l'usuari amb identificador UserId");
        System.out.println("\t 3-end : Acaba amb el driver");
        String action = sc.next();
        while(!action.equals("end")){
            if(action.equals("Initialize")){
                if(hb ==null){
                    users = UserManager.getInstance();
                    CDomini = CtrlDomain.getInstance();
                    CDomini.obtainData(users);
                    items = new ItemManager();
                    //items.fillPonderacions(CDomini.getPonderacions());
                    //items.fillMapDistances(CDomini.getItems());
                    users.setItemMan(items);
                    cb = new ContentBasedFiltering(users,items);
                    //col = new CollaborativeFiltering(users);
                    //col.kmeans(users, items.getItems(), 3);
                    //col.buildDifferencesMatrix(items.getItems(), col.getCluster(1) );
                    hb = new HybridApproach(cb, col);
                    System.out.println("Tot inicialitzat correctament");

                }
                else {
                    System.out.println("Ja esta inicilitzat");
                }
            }
            else if(action.equals("Calculate")){
                if(hb != null){
                    System.out.print("Insereix el userId: ");
                    String userId = sc.next();
                    System.out.print("Insereix el valor K: ");
                    Integer k = sc.nextInt();
                    if(users.existUser(userId)) {
                        List<Integer> recommendations = hb.calculate(userId,k,items.getItems());
                        System.out.println("Items recomanats a l'usuari "+ userId + " :"+recommendations);
                    }
                    else System.out.println("L'usuari donat no exiteix");

                }
                else System.out.println("Inicialitza primer tot");
            }
            action = sc.next();
        }
    }
}
