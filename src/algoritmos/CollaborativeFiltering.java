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

                    //Item, <Item,double>
    private static Map<Item, HashMap<Item, Double>> diff = new HashMap<Item, HashMap<Item, Double>>();
    private static Map<Item, HashMap<Item, Integer>> freq = new HashMap<Item, HashMap<Item, Integer>>();
    private static Map<activeUser, HashMap<Item, Double>> inputData;
    private static Map<activeUser, HashMap<Item, Double>> outputData = new HashMap<>();


    //n -> nombreUsuaris, Map<User, Map<items,rating>> dades que tenim dels users i items iratings
    public static void slopeOne(int n, Map<activeUser, HashMap<Item, Double>> dades) {
        //guardem a inputData el Map<User, Map<Item,rating>> dades que tenim
        inputData = dades; //initializeData posaria aquestes dades a la matriu, però podem passar ja el
        System.out.println("Slope One - Abans Predicció\n");
        //Hauriem de construir la matriu de diferències
        construirMatriuDiferencies(inputData);
        System.out.println("\nSlope One - Amb Predicció\n");
        //Fem la predicció a partir de les dades d'entrada que rebem a través del map
        //prediccio(inputData);
    }
    //PREDICCIÓ a partir de inputData
    //prediction(Map<activeUser, HashMap<Item, Double>>);
    /**
     *
     * @param dades : Tenim les dades en un map dels usuaris amb un map de items i les valoracions
     * @return retorna la matriu de diferencies
     */
    private static void construirMatriuDiferencies(Map<activeUser, HashMap<Item, Double>> dades) {
       //
    }
}
