package algorithms;

import estructures.Pair;
import user.UserManager;

import java.util.*;


public class CollaborativeFiltering implements RecommendationSystem {
    public static Map<Integer,ArrayList<Pair<Integer,Double>>> differenceMatrix;
    private Map<String,Map<Integer, Double>> MatUserItems = new HashMap<>();
    private Map<Integer, ArrayList<String>> CjtClusters = new HashMap<>();
    UserManager manager;
    boolean evaluation;
    Kmeans km;

    /**
     * Retorna els k items que més se li recomanen a l'usuari seguitn l'algoritme de collaborative filtering
     * @param userId Identificador de l'usuari al que se li vol fer la recomanació
     * @param k Nombre de items que li volem recomanar
     * @param Items LLista de possibles items que li podem recomanar a l'usuari
     * @return Llista amb els k items que més li recomanem
     * Complexitat O (items.size² * users.size * max(num_reviews_user)  +  CjtClusters.size * max(UsersCjt.size)) + (users.size * max(num_reviews_usuari²) )  +   (items.size * num_reviews_user)  + s.size * (itemsVal.size * users.size * max(num_reviews_user) + itemsBons.size * (mapIt1.size + mapIt2.size))  +  (m.size) )
     */
    public List<Integer> calculate(String userId, int k,  List<Integer> Items){
        CjtClusters = km.getCjtClusters();
        ArrayList<String> conjunt = CjtClusters.get(1);
        buildDifferencesMatrix(Items, conjunt);
        return recommended(userId, Items, k);
    }

    /**
     * Constructora de l'algosrisme
     * @param mana UserManager assignat a l'algorisme
     * @param k Kmeans assignat a km
     * Complexitat O(1)
     */
    public CollaborativeFiltering(UserManager mana, Kmeans k){
        differenceMatrix = new HashMap<>();
        manager = mana;
        km = k;
        evaluation = false;
    }
    /* MatriuDiferencial computem la diferència entre els ratings de dos ítems
     * matDiferencies[item][item2] += rating - rating2
     * Cada vegada que un usuari puntua un ítem, actualitzem les cel·les adequades d'aquesta matriu per reflectir
     * la diferència acumulada entre aquesta valoració i totes les altres valoracions que l'usuari hagi fet.
     * */

    /**
     * Seteja a True si esteim utilitzant l'avaluator per el Collaborative Filtering, si ho esta fent voldrem que la recomanacio sigui de tots els items
     * Complexitat O(1)
     */
    public void setTrue(){
        evaluation = true;
    }

    /**
     * Rellena la matriu de diferencies entre els items
     * @param items LLista dels identificadors dels items
     * @param users LLista dels identificadors dels usuaris
     * Complexitat O( items.size² * users.size * max(num_reviews_user) )
     */
    public void buildDifferencesMatrix(List<Integer> items, List<String> users) {
        double dev;
        double suma=0;
        double numUsrs=1;
        for(int i = 0; i < items.size(); ++i){
            Integer item1 = items.get(i);
            Map<Integer,Double> aux = new HashMap<>();
            ArrayList<Pair<Integer,Double>> array = new ArrayList<>();
            for(int j = 0; j <= i; ++j){
                if(i != j){
                    suma=0;
                    Integer item2 = items.get(j);
                    List<String> aux2 = manager.getUsersItems(item1,item2);
                    List<String> usrs = new LinkedList<>();
                    for(String e: aux2){
                        if(users.contains(e))usrs.add(e);
                    }
                    numUsrs= usrs.size();
                    if(numUsrs == 0){}
                    else{
                        for(String usr : usrs){ //
                            Double rai1 = manager.getRaiting(usr, item1);
                            Double rai2 = manager.getRaiting(usr, item2);
                            dev= rai2 - rai1;
                            dev /=numUsrs;
                            suma+=dev; // SUMA DE LES DESVIACIONS DELS USUARIS QUE HAN VALORAT ITEM1 I ITEM2
                        }
                    }
                    array.add(new Pair(item2,suma));
                    aux.put(item2, suma);
                }
            }
            differenceMatrix.put(item1,array);
        }
    }

    /**
     * Diu si un arraylist de Pairs, conte un determinat item i la seva distancia
     * @param a ArrayList de Pairs
     * @param item Id de l'item concret que esteim cercant
     * @return Si l'arrayLiist conte l'item
     * Complexitat O(a.size)
     */
    boolean haveItem (ArrayList<Pair<Integer,Double>> a, Integer item){
        for(Pair p : a){
            if(p.getFirst()== item) return true;
        }
        return false;
    }

    /**
     * Donat un arrayList de Pairs, ens retorna la distancia d'un determinat item, previament hem revisat que l'arrayList conte l'item
     * @param a ArrayList de Pairs
     * @param item Item que volem saber la distancia
     * @return Distancia de l'item
     * Complexitat O(a.size)
     */
    double getDistancePair(ArrayList<Pair<Integer,Double>> a , Integer item){
        for (Pair p : a){
            if(p.getFirst() == item) return (double)p.getSecond();
        }
        return 0;
    }

    /**
     * Donats dos items, ens retorna la seva distancia que tenen dins de la matriu a l'hora de fer la recomanacio, per aixo a vegades retornam valors negatius
     * @param item1 Identificador item1
     * @param item2 Identificador item2
     * @return Distancio entre els dos items dins de la matriu
     * Complexitat O(array1.size + array2.size)      espacial= O( max(array1.size, array2.size) )
     */
    public double getDistance(Integer item1, Integer item2){
        ArrayList<Pair<Integer,Double>> array1 = differenceMatrix.get(item1);
        ArrayList<Pair<Integer,Double>> array2 = differenceMatrix.get(item2);
        Double diff= 0.0;
        if(array1 != null && !array1.isEmpty() && haveItem(array1,item2)){
            if(item1 < item2) diff= -getDistancePair(array1, item2);
            else diff = getDistancePair(array1, item2);
        }
        else if(array2 != null && !array2.isEmpty() && haveItem(array2,item1)){
            if(item1 < item2) diff= -getDistancePair(array2,item1);
            else diff = getDistancePair(array2,item1);
        }

        return diff;
    }

    //complexitat O ( (CjtClusters.size * max(UsersCjt.size)) + (users.size * max(num_reviews_usuari²) )  +   (items.size * num_reviews_user)  +
    // s.size * (itemsVal.size * users.size * max(num_reviews_user) + itemsBons.size * (mapIt1.size + mapIt2.size))  +  (m.size)   )
    /**
     * Donat un identificador d'usuari, una llista de items i un enter K, retorna els K items mes recomanats de la llista per a l'usuari, K no es te en compte si tenim la variable evaluation a TRUE
     * @param userId Identificador de l'usuari al que esteim fent una recomanacio
     * @param Items LLista dels possibles items que li podem recomanar
     * @param k Nombre de items que li recomanam
     * @return LLista dels k items (si evaluation es FALSE) que li recomanam a l'usuari
     * Complexitat O ( (CjtClusters.size * max(UsersCjt.size)) + (users.size * max(num_reviews_usuari²) )  +   (items.size * num_reviews_user)  +
     * s.size * (itemsVal.size * users.size * max(num_reviews_user) + itemsBons.size * (mapIt1.size + mapIt2.size))  +  (m.size)
     */
    public List<Integer> recommended(String userId, List<Integer> Items, int k){
        int i = findClusterUser(userId); //(CjtClusters.size * max(UsersCjt.size))

        Set<Integer> s = manager.itemsNoVal(userId,CjtClusters.get(i)); // (users.size * max(num_reviews_usuari²) )
        List<Integer> itemsVal = manager.getVal(userId, (ArrayList<Integer>) Items); // (items.size * num_reviews_user)

        Double mitjUs = manager.raiAve(userId);
        Map<Integer,Double> m = new HashMap<>();
        for(Integer itemId : s){ // complexitat O (s.size * (itemsVal.size * users.size * max(num_reviews_user) + itemsBons.size * (mapIt1.size + mapIt2.size)) )
            Double differenciaMitjanaVal = 0.0 , sumaRatingsUser = 0.0, prediccio = 0.0;
            List<Integer> itemsBons = new LinkedList<>();
            for(Integer item : itemsVal){
                if(manager.getUsersItems(item, itemId).size()>0 && item != itemId)  itemsBons.add(item); // ()
            }
            for(Integer item1: itemsBons) {
                differenciaMitjanaVal += getDistance(itemId, item1); // complexitat O (mapIt1.size + mapIt2.size)
            }
            if(itemsBons.size()!=0) differenciaMitjanaVal /= itemsBons.size();
            m.put(itemId,mitjUs + differenciaMitjanaVal);
        }
        LinkedHashMap<Integer, Double> newM = new LinkedHashMap<>();
        m.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> {
                    newM.put(x.getKey(), x.getValue());
                });

        List<Integer> finalRecommendation = new LinkedList<>();
        if(evaluation){ // complexitat O (m.size)
            for(Map.Entry<Integer, Double> entry : newM.entrySet()){
                finalRecommendation.add(entry.getKey());
            }
            evaluation = false;
        }
        else {
            int cont = 0;
            for(Map.Entry<Integer, Double> entry : newM.entrySet()){
                if(cont < k) finalRecommendation.add(entry.getKey());
                ++cont;
            }
        }
        return finalRecommendation;
    }

    /**
     * Donat el identificador d'un usuari, retorna l'index del cluster al que pertany
     * @param u1 Usuari que esteim cercant
     * @return Index del cluster on es troba l'usuari
     * Complexitat O (CjtClusters.size * max(UsersCjt.size))
     */
    private int findClusterUser(String u1){
        for (Map.Entry<Integer, ArrayList<String>> entry : CjtClusters.entrySet()){
            ArrayList<String> UsersCjt = entry.getValue();
            if(UsersCjt.contains(u1)){
                return entry.getKey();
            }
        }
        return -1;
    }
}
