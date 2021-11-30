package algoritmos;
<<<<<<< HEAD
import user.userManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class collaborativeFiltering implements RecommendationSystem {
    public static Map<Integer,Map<Integer,Double>> matriuDiferencia;
    userManager manager;

=======
import java.util.*;

import item.ItemManager;
import user.userManager;


public class collaborativeFiltering implements RecommendationSystem {

    public static Map<Integer , Map<Integer , Double>> matriuDiferencia;
    private Map<String,Map<Integer, Double>> MatUserItems = new HashMap<>();
    private Map<Integer, ArrayList<String>> CjtClusters = new HashMap<>();
    userManager manager;
>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835
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

<<<<<<< HEAD
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

=======
    public void construirMatriuDiferencies(List<Integer> items, List<String> users) {
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
                    List<String> aux2 = manager.getUsers_items(item1,item2);
                    List<String> usrs = new LinkedList<>();
                    for(String e: aux2){
                        if(users.contains(e))usrs.add(e);
                    }
                    num_usrs= usrs.size();
                    if(num_usrs == 0){


                    }
                    else{
                        for(String usr : usrs){
                            Double rai1 = manager.getRaiting(usr, item1);
                            Double rai2 = manager.getRaiting(usr, item2);
                            dev= rai2 - rai1;
                            dev /=num_usrs;
                            suma+=dev; // SUMA DE LES DESVIACIONS DELS USUARIS QUE HAN VALORAT ITEM1 I ITEM2
                        }
                    }

                    aux.put(item2, suma);
                }
>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835
            }
            matriuDiferencia.put(item1, aux);
        }
    }

<<<<<<< HEAD
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
=======

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


        Double mitj_us = manager.raiAve(user_id);
        Double differenciaMitjanaVal = 0.0 , sumaRatingsUser = 0.0, prediccio = 0.0;
        List<Integer> items_bons = new LinkedList<>();
        for(Integer item : items_val){
            if(manager.getUsers_items(item, item_id).size()>0 && item != item_id)  items_bons.add(item);
        }

        for(Integer item1: items_bons) {

//            System.out.println(getDistancia(item1, item_id));
//            System.out.println(getDistancia(item_id, item1));
            differenciaMitjanaVal += getDistancia(item_id, item1);
        }
        if(items_bons.size()!=0) differenciaMitjanaVal /= items_bons.size();

        return mitj_us + differenciaMitjanaVal;

    }
    //Entenc que el primer Integer seria el id_usuari(User) i el segon Integer l'id_item amb el rating respectiu que li
    //ha donat l'usuari
    public void writeCjtClusters() {
        for (int i = 1; i <= CjtClusters.size(); ++i) {
            ArrayList<String> userInK = CjtClusters.get(i); //Los usuarios que pertenecen al cluster i
            System.out.println("Los usuarios que pertenecen al cluster " + i + " son:");
            for (int j = 0; j < userInK.size(); ++j) {
                System.out.println(userInK.get(j));
            }
        }
    }
    public double distancia(Map<Integer, Double> c1, Map<Integer, Double> c2, ArrayList<Integer> idItems) {
        double dist = 0;
        for (int i = 0; i < idItems.size(); ++i) {

            int iditemAct = idItems.get(i);

            double d1 = c1.get(iditemAct);
            double d2 = c2.get(iditemAct);

            double r = (d1 - d2) * (d1 - d2);

            dist += r;
        }
        return Math.sqrt(dist);
    }
    public ArrayList<String> getCluster(int i){
        return CjtClusters.get(i);
    }
    public void kmeans(userManager users, ArrayList<Integer> idItems, int k) {

        for (int i = 0; i < users.getUsuaris().size(); ++i) {

            String usernameAct = users.getUsuaris().get(i);
            Map<Integer,Double> RevUser = users.getReviewsUsers(usernameAct);
            Map<Integer,Double> AllItems = new HashMap<>();

            for (int j = 0; j < idItems.size(); ++j){
                int idItemAct = idItems.get(j);

                if(RevUser.containsKey(idItemAct)){
                    AllItems.put(idItemAct,RevUser.get(idItemAct));
                }
                else AllItems.put(idItemAct,2.5);
            }

            MatUserItems.put(usernameAct,AllItems);
        }

        int sizeCoords = idItems.size();
        ArrayList<Map<Integer, Double>> ListKelem = new ArrayList<>();

        for (int i = 0; i < k; ++i){

            Map<Integer, Double> actK = new HashMap<>();

            Random r = new Random();
            double randomValue;

            for (int j = 0; j < sizeCoords; ++j){
                randomValue = 5.0 * r.nextDouble();
                actK.put(idItems.get(j), randomValue);
            }
            ListKelem.add(actK);
        }

        for (int i = 0; i < 50; ++i) {

            if (i != 0) {
                for (int z = 1; z <= k; ++z) {
                    ArrayList<String> userInK = CjtClusters.get(z); //Los usuarios que pertenecen al cluster z
                    Map<Integer, Double> averageCoords = new HashMap<>();
                    for (int a = 0; a < userInK.size(); ++a) {
                        Map<Integer, Double> coordsActUser = MatUserItems.get(userInK.get(a)); //Coordenades del usuario actual
                        for (int b = 0; b < idItems.size(); ++b) {
                            double coordItemAct = coordsActUser.get(idItems.get(b)); //Coordenada de un item
                            if (a == 0) { //Si en la posicion b de averageCoords esta vacia
                                averageCoords.put(idItems.get(b), coordItemAct);
                            } else {
                                double d1 = averageCoords.get(idItems.get(b));
                                averageCoords.remove(idItems.get(b));
                                averageCoords.put(idItems.get(b), d1 + coordItemAct); //Voy sumando todas las coordenadas de los usuarios que se encuantran en el mismo cluster
                            }
                        }
                    }
                    //Dividir el sumatorio de coordenadas por el numero de usuarios que se encuentran en el cluster z
                    for (int a = 0; a < averageCoords.size(); ++a) {
                        double d = averageCoords.get(idItems.get(a));
                        averageCoords.remove(idItems.get(a));
                        averageCoords.put(idItems.get(a), d / userInK.size());
                    }
                    //Ahora modificamos las coordenadas del cluster z
                    if (!userInK.isEmpty()) ListKelem.set(z - 1, averageCoords);
                }
            }
            //Vaciamos el CjtClusters
            CjtClusters.clear();
            for (int z = 1; z <= k; ++z) {
                ArrayList<String> aux = new ArrayList<>();
                CjtClusters.put(z, aux);
            }

            for (Map.Entry<String, Map<Integer, Double>> entry : MatUserItems.entrySet()) {

                double distMin = Double.POSITIVE_INFINITY;
                int inK = 0;
                String usernameAct = entry.getKey();
                Map<Integer, Double> CoordActUser = entry.getValue();

                for (int j = 1; j <= k; ++j) {

                    Map<Integer, Double> CoordActK = ListKelem.get(j-1);
                    double distAct = distancia(CoordActUser, CoordActK, idItems);

                    if (distAct < distMin) {
                        distMin = distAct;
                        inK = j;
                    }

                }

                if (CjtClusters.containsKey(inK)) {
                    ArrayList<String> list = CjtClusters.get(inK);
                    list.add(usernameAct);
                    CjtClusters.remove(inK);
                    CjtClusters.put(inK, list);
                } else {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(usernameAct);
                    CjtClusters.put(inK, list);
                }
            }

        }
    }


>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835
}
