package algorithms;

import domain.CtrlDomain;
import item.ItemManager;
import user.UserManager;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class driverAvaluator {
    public static void main(String args[]) throws IOException{
        UserManager users = null;
        ItemManager items = null;
        ContentBasedFiltering cb = null;
        CtrlDomain CDomini = null;
        Scanner sc = new Scanner(System.in);
        CollaborativeFiltering col = null;
        Avaluator av = null;
        System.out.println("Instruccions");
        System.out.println("\t 1-Initialize : inicialitza l'avaluador, l'ItemManager, el UserManager i els algoritmes de Content Based i Collaborative Filtering");
        System.out.println("\t 2-Evaluate <String- UserId>  : donat un userId evalua la prediccio dels nostres algoritmes");
        System.out.println("\t 3-end : Acaba amb el driver");
        String action = sc.next();
        while(!action.equals("end")){
            if(action.equals("Initialize")){
                if(av ==null){
                    users = UserManager.getInstance();
                    CDomini = CtrlDomain.getInstance();
                    CDomini.obtainData(users);
                    items = new ItemManager();
                    items.fillPonderacions(CDomini.getPonderacions());
                    items.fillMapDistances(CDomini.getItems());
                    users.setItemMan(items);
                    cb = new ContentBasedFiltering(users,items);
                    col = new CollaborativeFiltering(users);
                    col.kmeans(users, items.getItems(), 3);
                    col.buildDifferencesMatrix(items.getItems(), col.getCluster(1) );
                    av = new Avaluator();
                    System.out.println("Tot inicialitzat correctament");

                }
                else {
                    System.out.println("Ja esta inicilitzat");
                }
            }
            else if(action.equals("Evaluate")){
                if(av != null){
                    System.out.print("Insereix el userId: ");
                    String userId = sc.next();
                    col.setTrue();
                    if(users.existUser(userId)) {

                        List<Integer> colItenms = col.calculate(userId,0,items.getItems());
                        int aux = users.numReviews(userId);
                        int k = items.getItems().size() - aux;
                        Map<Integer,Double> unknown = CDomini.getRatings(userId);
                        List<Integer> cbItems = cb.calculate(userId,k,items.getItems());
                        av.evaluate(colItenms, cbItems, unknown);
                    }
                    else System.out.println("L'usuari donat no exiteix");

                }
                else System.out.println("Inicialitza primer tot");
            }
            action = sc.next();
        }
    }
}
