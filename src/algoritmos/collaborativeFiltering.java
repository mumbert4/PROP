package algoritmos;
import java.util.*;


public class collaborativeFiltering {
    private static Map<Integer, HashMap<Integer, Double>> matDiferencies;

    private static Map<Integer, HashMap<Integer, Integer>> counter;

    public collaborativeFiltering(){
        matDiferencies = new HashMap<>();
        counter = new HashMap<Integer, HashMap<Integer, Integer>>();
    }
    /* MatriuDiferencial computem la diferència entre els ratings de dos ítems
     * matDiferencies[item][item2] += rating - rating2
     * Cada vegada que un usuari puntua un ítem, actualitzem les cel·les adequades d'aquesta matriu per reflectir
            * la diferència acumulada entre aquesta valoració i totes les altres valoracions que l'usuari hagi fet.
            * */

    /* MatriuCounter guarda el nombre de vegades que hem calculat una la diferència entre cada parell d'elements.
            * Per a cada parell de valoracions, modifiquem la cel·la adequada a freqs sumant un al nombre de vegades que hem vist
     * aquest parell de valoracions.
            * */
    public void pinta_mat(){
        for (Integer j : matDiferencies.keySet()) { // primer item
            System.out.println("Dieferencies item: " + j +" resta de items:");
            for (Integer i : matDiferencies.get(j).keySet()) { // segon item
                System.out.println("Item2: " + i + " :" +matDiferencies.get(j).get(i));
            }
            System.out.println();
            System.out.println();
        }
    }

    /**
     * @param dades : Tenim les dades en un map dels usuaris que provenen del cluster amb un map de items i les valoracions
     * @return retorna la matriu de diferencies
     */

    public void construirMatriuDiferencies(Map<String,Map<Integer,Double>>  dades) {
        for (Map<Integer,Double> usuari : dades.values()) { //Iterem per tots els users de dades
            for (Map.Entry<Integer, Double> e : usuari.entrySet()) { //Iterem per les dades dels users(items)
                if (!matDiferencies.containsKey(e.getKey())) { //si l'item i de l'usuari u no es troba a la matriu de diferències l'afegim
                    matDiferencies.put(e.getKey(), new HashMap<Integer, Double>());
                    counter.put(e.getKey(), new HashMap<Integer, Integer>());
                }
                for (Map.Entry<Integer, Double> e2: usuari.entrySet()) { //iterem per l'item j de l'usuari
                    int nombreVegades = 0;
                    if (counter.get(e.getKey()).containsKey(e2.getKey())) nombreVegades = counter.get(e.getKey()).get(e2.getKey());
                    double diffInicial = 0;
                    if (matDiferencies.get(e.getKey()).containsKey(e2.getKey())) diffInicial = matDiferencies.get(e.getKey()).get(e2.getKey());

                    double diferencia = e.getValue() - e2.getValue(); //difència entre valoració itemi i itemj
                    matDiferencies.get(e.getKey()).put(e2.getKey(), diffInicial + diferencia); //afegim per l'itemi, l'itemj i la dist amb aquest
                    counter.get(e.getKey()).put(e2.getKey(), nombreVegades + 1); //l'itemi-itemj, +1 cop
                }
            }
        }
        //CALCULA LA MITJANA(differencia/counter)
        for (Integer j : matDiferencies.keySet()) {
            for (Integer i : matDiferencies.get(j).keySet()) {
                matDiferencies.get(j).put(i, (double)matDiferencies.get(j).get(i)/counter.get(j).get(i));
            }
        }
    }

}
