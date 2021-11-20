package algoritmos;
import user.userManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class collaborativeFiltering implements RecommendationSystem {
    public static Map<Integer,Map<Integer,Double>> matriuDiferencia;
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
        double dev, suma=0, avg, num_usrs=1;
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
                    num_usrs=usrs.size();
                    for(String usr : usrs){
                        Double rai1 = manager.getRaiting(usr, item1);
                        Double rai2 = manager.getRaiting(usr, item2);
                        dev= rai1 - rai2 ;
//                        System.out.println("Desvacio: " + dev);
                        suma+=dev; // SUMA DE LES DESVIACIONS DELS USUARIS QUE HAN VALORAT ITEM1 I ITEM2
                    }
                    avg = suma/num_usrs;
                    aux.put(item2, avg);
                }

            }
            matriuDiferencia.put(item1, aux);
        }
    }

    public Double recommended(String user_id, Integer item_id, List<Integer> Items){
        Map<Integer,Double> items_val = manager.getVal(user_id, Items);
        Double differenciaMitjanaVal = 0.0 , sumaRatingsUser = 0.0, prediccio = 0.0;

        for(Map.Entry<Integer,Double> j : items_val.entrySet()){
            Integer item1 = j.getKey();
            System.out.println(matriuDiferencia.get(item_id).get(item1));
            differenciaMitjanaVal += Math.abs(matriuDiferencia.get(item_id).get(item1));
            sumaRatingsUser += j.getValue();
        }
        System.out.println(sumaRatingsUser);
        System.out.println(differenciaMitjanaVal);
        System.out.println(items_val.size());
        return (double) ((sumaRatingsUser + differenciaMitjanaVal)/items_val.size());
    }
}
