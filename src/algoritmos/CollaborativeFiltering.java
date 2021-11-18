package algoritmos;

import item.Item;
import user.activeUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollaborativeFiltering implements RecommendationSystem {
    public ArrayList<Item> calculate(String s) {
        return null;
    }
    //slope one
    private static Map<Integer, HashMap<Item, Double>> diff = new HashMap<Integer, HashMap<Item, Double>>();
    private static Map<Integer, HashMap<Item, Integer>> freq = new HashMap<Integer, HashMap<Item, Integer>>();
    //input dades: Map<ActiveUser, HashMap<Item, Double>>
    //Ho inicialitzem amb el d'usuaris i els items amb les valoracions
    private static Map<activeUser, HashMap<Item, Double>> inputData;
    //Hauriem de construir la matriu de diferències
    //buildMatrixDifferences(Map<activeUser, HashMap<Item, Double>> input);
    //PREDICCIÓ a partir de inputData
    //prediction(Map<activeUser, HashMap<Item, Double>>);

    //Entenc que el primer Integer seria el id_usuari(User) i el segon Integer l'id_item amb el rating respectiu que li
    //ha donat l'usuari
    public ArrayList<Item> calculate(Map<Integer, Map<Integer, Double>> inputData) {
        for (Map<Integer, Double> usuari : inputData.values()) {
            for (Map.Entry<Integer, Double> e : usuari.entrySet()) {
                if (!diff.containsKey(e.getKey())) {
                    diff.put(e.getKey(), new HashMap<Item, Double>());
                    freq.put(e.getKey(), new HashMap<Item, Integer>());
                }
            }
        }
        //print(dades)
        return null;
    }
}
