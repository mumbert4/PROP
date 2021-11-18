package algoritmos;

import item.Item;
import user.activeUser;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

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
        prediccio(inputData);
    }
    //PREDICCIÓ a partir de inputData
    //prediction(Map<activeUser, HashMap<Item, Double>>);
    /**
     *
     * @param dades : Tenim les dades en un map dels usuaris amb un map de items i les valoracions
     * @return retorna la matriu de diferencies
     */
    private static void construirMatriuDiferencies(Map<activeUser, HashMap<Item, Double>> dades) {
        for (HashMap<Item, Double> usuari : dades.values()) {
            for (Map.Entry<Item, Double> e : usuari.entrySet()) {
                if (!diff.containsKey(e.getKey())) {
                    diff.put(e.getKey(), new HashMap<Item, Double>());
                    freq.put(e.getKey(), new HashMap<Item, Integer>());
                }
                for (Map.Entry<Item, Double> e2 : usuari.entrySet()) {
                    int oldCount = 0;
                    if (freq.get(e.getKey()).containsKey(e2.getKey())) {
                        oldCount = freq.get(e.getKey()).get(e2.getKey()).intValue();
                    }
                    double oldDiff = 0.0;
                    if (diff.get(e.getKey()).containsKey(e2.getKey())) {
                        oldDiff = diff.get(e.getKey()).get(e2.getKey()).doubleValue();
                    }
                    double observedDiff = e.getValue() - e2.getValue();
                    freq.get(e.getKey()).put(e2.getKey(), oldCount + 1);
                    diff.get(e.getKey()).put(e2.getKey(), oldDiff + observedDiff);
                }
            }
        }
        for (Item j : diff.keySet()) {
            for (Item i : diff.get(j).keySet()) {
                double oldValue = diff.get(j).get(i).doubleValue();
                int count = freq.get(j).get(i).intValue();
                diff.get(j).put(i, oldValue / count);
            }
        }
        for (activeUser usuari : dades.keySet()) {
            System.out.println(usuari.getName() + ":" + dades.get(usuari));
        }
    }

    /**
     * FEM LA PREDICCIÓ: donades les dades actuals, hem de predir les valoracions que falten.
     * Si no podem fer la predicció = -1
     */
    private static void prediccio(Map<activeUser, HashMap<Item, Double>> data) {
        HashMap<Item, Double> uPred = new HashMap<Item, Double>();
        HashMap<Item, Integer> uFreq = new HashMap<Item, Integer>();
        for (Item j : diff.keySet()) {
            uFreq.put(j, 0);
            uPred.put(j, 0.0);
        }
        for (Map.Entry<activeUser, HashMap<Item, Double>> e : data.entrySet()) {
            for (Item j : e.getValue().keySet()) {
                for (Item k : diff.keySet()) {
                    try {
                        double predictedValue = diff.get(k).get(j).doubleValue() + e.getValue().get(j).doubleValue();
                        double finalValue = predictedValue * freq.get(k).get(j).intValue();
                        uPred.put(k, uPred.get(k) + finalValue);
                        uFreq.put(k, uFreq.get(k) + freq.get(k).get(j).intValue());
                    } catch (NullPointerException e1) {
                    }
                }
            }
            HashMap<Item, Double> clean = new HashMap<Item, Double>();
            for (Item j : uPred.keySet()) {
                if (uFreq.get(j) > 0) {
                    clean.put(j, uPred.get(j).doubleValue()/uFreq.get(j).intValue());
                }
            }
            //donada una llista d'items que tinguem
            /*List<Integer> items = new LinkedList<>();
            for (Item j : items.forEach()) {
                if (e.getValue().containsKey(j)) {
                    clean.put(j, e.getValue().get(j));
                } else if (!clean.containsKey(j)) {
                    clean.put(j, -1.0);
                }
            }
            outputData.put(e.getKey(), clean);*/
        }
        for (activeUser user : outputData.keySet()) {
            System.out.println(user.getName() + ":");
            print(data.get(user));
        }
    }
    private static void print(HashMap<Item, Double> hashMap) {
        NumberFormat formatter = new DecimalFormat("#0.000");
        for (Item j : hashMap.keySet()) {
            System.out.println(" " + j.getId() + " --> " + formatter.format(hashMap.get(j).doubleValue()));
        }
    }
}
