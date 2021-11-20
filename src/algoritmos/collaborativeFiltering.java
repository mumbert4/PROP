package algoritmos;
import java.util.*;
import user.userManager;


public class collaborativeFiltering {

    public static Map<Integer , Map<Integer , Double>> matriuDiferencia;
    userManager manager;
    public collaborativeFiltering(userManager mana){
        matriuDiferencia = new HashMap<>();
        manager = mana;
    }
    /* MatriuDiferencial computem la diferència entre els ratings de dos ítems
     * matDiferencies[item][item2] += rating - rating2
     * Cada vegada que un usuari puntua un ítem, actualitzem les cel·les adequades d'aquesta matriu per reflectir
     * la diferència acumulada entre aquesta valoració i totes les altres valoracions que l'usuari hagi fet.
     * */
    public void pinta_mat(){
        for (Integer j : matriuDiferencia.keySet()) { // primer item
            System.out.println("Dieferencies item: " + j +" resta de items:");
            for (Integer i : matriuDiferencia.get(j).keySet()) { // segon item
                System.out.println("Item2: " + i + " :" + matriuDiferencia.get(j).get(i));
            }
            System.out.println();
            System.out.println();
        }
    }

    public void construirMatriuDiferencies(List<Integer> items) {
        double dev;
        double suma=0;
        double avg;
        double num_usrs=1;
        for(int i = 0; i < items.size(); ++i){
            Integer item1 = items.get(i);
            Map<Integer,Double> aux = new HashMap<>();
            for(int j = 0; j <= i; ++j){
                if(i != j){
                    suma=0;
                    Integer item2 = items.get(j);
                    List<String> usrs = manager.getUsers_items(item1,item2);
//                    System.out.println(usrs);
//                    System.out.println(usrs.size());

                    num_usrs= usrs.size();
                    if(num_usrs == 0){


                    }
                    else{
                        for(String usr : usrs){
                            Double rai1 = manager.getRaiting(usr, item1);
                            Double rai2 = manager.getRaiting(usr, item2);
                            dev= rai2 - rai1;
                            dev /=num_usrs;
//                            System.out.println("Desvacio: " + dev);
                            suma+=dev; // SUMA DE LES DESVIACIONS DELS USUARIS QUE HAN VALORAT ITEM1 I ITEM2
                        }
                    }

                    aux.put(item2, suma);
                }
            }
            matriuDiferencia.put(item1, aux);
        }
    }


    public double getDistancia(Integer item1, Integer item2){
        Map<Integer,Double> map_it1= matriuDiferencia.get(item1);
        Map<Integer,Double> map_it2= matriuDiferencia.get(item2);
        Double diff= 0.0;
        if(map_it1 != null && !map_it1.isEmpty() && map_it1.containsKey(item2)){
            if(item1 < item2) diff= -map_it1.get(item2);
            else diff = map_it1.get(item2);
        }
        else if(map_it2 != null && !map_it2.isEmpty() && map_it2.containsKey(item1)){
            if(item1 < item2) diff= -map_it2.get(item1);
            else diff = map_it2.get(item1);
        }
        return diff;
    }
    public Double recommended(String user_id, Integer item_id, List<Integer> Items){

        List<Integer> items_val = manager.getVal(user_id, Items);
//        List<Integer> items_no_val1 = manager.getNoVal(user_id,Items);
//        System.out.println("ABANS: "+ items_no_val1);
//        System.out.println();
//        System.out.println();

        Double mitj_us = manager.raiAve(user_id);
        System.out.println("Mitjana usuari: " + mitj_us);
        Double differenciaMitjanaVal = 0.0 , sumaRatingsUser = 0.0, prediccio = 0.0;
        List<Integer> items_bons = new LinkedList<>();
        for(Integer item : items_val){
            if(manager.getUsers_items(item, item_id).size()>0 && item != item_id)  items_bons.add(item);
        }
        System.out.println(items_bons);

        for(Integer item1: items_bons){

            System.out.println(getDistancia(item1,item_id));
            System.out.println(getDistancia(item_id,item1));
            differenciaMitjanaVal += getDistancia(item_id, item1);
//            if(matriuDiferencia.get(item_id).get(item1)!= null){
//                System.out.println("Diferencia entre "+ item1+ " i " + item_id+" es:" + matriuDiferencia.get(item_id).get(item1));
//
//            }
//            else {
//                System.out.println("Items que no calculam la diferencia:" + item1 + " " + item_id);
//            }
        }
//        System.out.println(differenciaMitjanaVal);

//        System.out.println(items_no_val.size());
//        System.out.println(items_no_val.size());
        if(items_bons.size()!=0) differenciaMitjanaVal /= items_bons.size();

        return mitj_us + differenciaMitjanaVal;

    }

}
