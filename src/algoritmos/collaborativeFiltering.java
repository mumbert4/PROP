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
            for(int j = 0; j < items.size(); ++j){
                if(i != j){
                    suma=0;
                    Integer item2 = items.get(j);
                    List<String> usrs = manager.getUsers_items(item1,item2);
//                    System.out.println(usrs);
//                    System.out.println(usrs.size());

                    num_usrs= usrs.size();
                    if(num_usrs == 0){
                        suma = -1;
                    }
                    else{
                        for(String usr : usrs){
                            Double rai1 = manager.getRaiting(usr, item1);
                            Double rai2 = manager.getRaiting(usr, item2);
                            dev= Math.abs(rai1 - rai2) ;
                            dev /=num_usrs;
//                        System.out.println("Desvacio: " + dev);
                            suma+=dev; // SUMA DE LES DESVIACIONS DELS USUARIS QUE HAN VALORAT ITEM1 I ITEM2
                        }
                    }
                     //SI NUM_USRS ES 0, HEM DE RETORNAR UNA VALORACIO RANDOM
                    aux.put(item2, suma);
                }

            }
            matriuDiferencia.put(item1, aux);
        }
    }

    public Double recommended(String user_id, Integer item_id, List<Integer> Items){

//        Map<Integer,Double> items_val = manager.getVal(user_id, Items);
        List<Integer> items_no_val1 = manager.getNoVal(user_id,Items);
       System.out.println("ABANS: "+ items_no_val1);
       System.out.println();
       System.out.println();

        Double mitj_us = manager.raiAve(user_id);
        System.out.println("Mitjana usuari: " + mitj_us);
        Double differenciaMitjanaVal = 0.0 , sumaRatingsUser = 0.0, prediccio = 0.0;
        List<Integer> items_no_val = new LinkedList<>();
        for(Integer item1 : Items){
            for(Integer item2 : items_no_val1){
                if(item1 != item2){
                    if(matriuDiferencia.get(item1).get(item2) != -1 && !items_no_val.contains(item2)){
                        items_no_val.add(item2);
                    }
                }
            }
        }
        System.out.println("DESPRES:" + items_no_val);

        for(Integer item1: items_no_val){
            if(matriuDiferencia.get(item1).get(item_id)!= null) differenciaMitjanaVal += matriuDiferencia.get(item_id).get(item1);
            else {
                System.out.println("Items que no calculam la diferencia:" + item1 + " " + item_id);
            }
        }
        System.out.println(differenciaMitjanaVal);

        System.out.println(items_no_val.size());
        System.out.println(items_no_val.size());
        differenciaMitjanaVal /= items_no_val.size();
        return mitj_us + differenciaMitjanaVal;

    }

}
