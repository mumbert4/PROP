package algoritmos;

import user.userManager;

import java.util.*;


public class collaborativeFiltering implements RecommendationSystem {
    public static Map<Integer , Map<Integer , Double>> matriuDiferencia;
    private Map<String,Map<Integer, Double>> MatUserItems = new HashMap<>();
    private Map<Integer, ArrayList<String>> CjtClusters = new HashMap<>();
    userManager manager;
    boolean avaluation;

    //Complexitat O ( items.size² * users.size * max(num_reviews_user)  +  CjtClusters.size * max(UsersCjt.size)) + (users.size * max(num_reviews_usuari²) )  +   (items.size * num_reviews_user)  +
    // s.size * (itemsVal.size * users.size * max(num_reviews_user) + itemsBons.size * (mapIt1.size + mapIt2.size))  +  (m.size)   )
    public List<Integer> calculate(String userId, int k,  List<Integer> Items){
        ArrayList<String> conjunt = getCluster(1);
        construirMatriuDiferencies(Items, conjunt);
        return recommended(userId, Items, k);
    }

    public collaborativeFiltering(userManager mana){
        matriuDiferencia = new HashMap<>();
        manager = mana;
        avaluation = false;
    }
    /* MatriuDiferencial computem la diferència entre els ratings de dos ítems
     * matDiferencies[item][item2] += rating - rating2
     * Cada vegada que un usuari puntua un ítem, actualitzem les cel·les adequades d'aquesta matriu per reflectir
     * la diferència acumulada entre aquesta valoració i totes les altres valoracions que l'usuari hagi fet.
     * */

    //complexitat O (1)
    public void setTrue(){
        avaluation = true;
    }

    
    // complexitat O (items.size² * users.size * max(num_reviews_user) )
    public void construirMatriuDiferencies(List<Integer> items, List<String> users) {
        double dev;
        double suma=0;
        double avg;
        double numUsrs=1;
        for(int i = 0; i < items.size(); ++i){
            Integer item1 = items.get(i);
            Map<Integer,Double> aux = new HashMap<>();
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
                    aux.put(item2, suma);
                }
            }
            matriuDiferencia.put(item1, aux);
        }
    }
    // complexitat O (mapIt1.size + mapIt2.size)            O ( max(mapIt1.size, mapIt2.size) )
    public double getDistancia(Integer item1, Integer item2){
        Map<Integer,Double> mapIt1= matriuDiferencia.get(item1);
        Map<Integer,Double> mapIt2= matriuDiferencia.get(item2);
        Double diff= 0.0;
        if(mapIt1 != null && !mapIt1.isEmpty() && mapIt1.containsKey(item2)){
            if(item1 < item2) diff= -mapIt1.get(item2);
            else diff = mapIt1.get(item2);
        }
        else if(mapIt2 != null && !mapIt2.isEmpty() && mapIt2.containsKey(item1)){
            if(item1 < item2) diff= -mapIt2.get(item1);
            else diff = mapIt2.get(item1);
        }
        return diff;
    }


    //complexitat O ( (CjtClusters.size * max(UsersCjt.size)) + (users.size * max(num_reviews_usuari²) )  +   (items.size * num_reviews_user)  +
    // s.size * (itemsVal.size * users.size * max(num_reviews_user) + itemsBons.size * (mapIt1.size + mapIt2.size))  +  (m.size)   )

    public List<Integer> recommended(String userId, List<Integer> Items, int k){
        int i = findClusterUser(userId); //(CjtClusters.size * max(UsersCjt.size))

        Set<Integer> s = manager.itemsNoVal(userId,getCluster(i)); // (users.size * max(num_reviews_usuari²) )
        List<Integer> itemsVal = manager.getVal(userId, Items); // (items.size * num_reviews_user)

        Double mitjUs = manager.raiAve(userId);
        Map<Integer,Double> m = new HashMap<>();
        for(Integer itemId : s){ // complexitat O (s.size * (itemsVal.size * users.size * max(num_reviews_user) + itemsBons.size * (mapIt1.size + mapIt2.size)) )
            Double differenciaMitjanaVal = 0.0 , sumaRatingsUser = 0.0, prediccio = 0.0;
            List<Integer> itemsBons = new LinkedList<>();
            for(Integer item : itemsVal){
                if(manager.getUsersItems(item, itemId).size()>0 && item != itemId)  itemsBons.add(item); // ()
            }
            for(Integer item1: itemsBons) {
                differenciaMitjanaVal += getDistancia(itemId, item1); // complexitat O (mapIt1.size + mapIt2.size)
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
        if(avaluation){ // complexitat O (m.size)
            for(Map.Entry<Integer, Double> entry : newM.entrySet()){
                finalRecommendation.add(entry.getKey());
            }
            avaluation = false;
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


    /**public void writeCjtClusters() {
        for (int i = 1; i <= CjtClusters.size(); ++i) {
            ArrayList<String> userInK = CjtClusters.get(i); //Los usuarios que pertenecen al cluster i
            System.out.println("Los usuarios que pertenecen al cluster " + i + " son:");
            for (int j = 0; j < userInK.size(); ++j) {
                System.out.println(userInK.get(j));
            }
        }
    }*/
    //complexitat O (idItems.size)
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

    //complexitat = O(1)
    public ArrayList<String> getCluster(int i){
        return CjtClusters.get(i);
    }

    //complexitat O (CjtClusters.size * max(UsersCjt.size))
    private int findClusterUser(String u1){
        for (Map.Entry<Integer, ArrayList<String>> entry : CjtClusters.entrySet()){
            ArrayList<String> UsersCjt = entry.getValue();
            if(UsersCjt.contains(u1)){
                return entry.getKey();
            }
        }
        return -1;
    }

    public void kmeans(userManager users, ArrayList<Integer> idItems, int k) {

        for (int i = 0; i < users.getUsuaris().size(); ++i) {

            String usernameAct = users.getUsuaris().get(i);
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

            int randomValue =  r.nextInt(users.getUsuaris().size());
            Map<Integer, Double> coords = MatUserItems.get(users.getUsuaris().get(randomValue));
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


}
