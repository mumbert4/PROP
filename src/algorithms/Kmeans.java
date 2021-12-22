package algorithms;

import user.UserManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Kmeans {
    private Map<String,Map<Integer, Double>> MatUserItems = new HashMap<>();
    private Map<Integer, ArrayList<String>> CjtClusters = new HashMap<>();

    /**
     * Donades les coordenades de dos usuaris calcula la distància que hi haurà entre aquests usuaris.
     * @param c1 coordenada usuari1
     * @param c2 coordenada usuari2
     * @param idItems Array de tots IDs dels items existents
     * @return Calcula la distància que hi haurà entre aquests usuaris
     * Complexitat O (idItems.size)
     */
    private double distance(Map<Integer, Double> c1, Map<Integer, Double> c2, ArrayList<Integer> idItems) {
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

    /**
     *
     * @param users usuaris totals que es troben a UserManager
     * @param idItems Array de tots IDs dels items existents
     * @param k paràmetre k del sistema
     * Complexitat O(users.size() * items.size())
     */
    public void kmeans(UserManager users, ArrayList<Integer> idItems, int k) {
        for (int i = 0; i < users.getUsers().size(); ++i) {
            String usernameAct = users.getUsers().get(i);
            Map<Integer,Double> RevUser = users.getReviewsUsers(usernameAct, k);
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
        ArrayList<Map<Integer, Double>> ListKelem = new ArrayList<>();
        for (int i = 0; i < k; ++i){
            Random r = new Random();
            int randomValue =  r.nextInt(users.getUsers().size());
            Map<Integer, Double> coords = MatUserItems.get(users.getUsers().get(randomValue));
            ListKelem.add(coords);
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
                    double distAct = distance(CoordActUser, CoordActK, idItems);

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

    /**
     * Es una consultora que retorna l’atribut CjtClusters
     * @return retorna l’atribut CjtClusters.
     * Complexitat O (1)
     */
    public Map<Integer, ArrayList<String>> getCjtClusters() {
        return CjtClusters;
    }
}
