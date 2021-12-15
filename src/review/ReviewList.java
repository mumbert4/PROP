package review;

import java.util.*;

/**
 * Classe de ReviewList, guarda en un mapa RevList, els items valorats i les instancies de reviews fetes per un activeUser
 */
public class ReviewList {

    private Map<Integer, Review> RevList;



    /**
     * Constructora de la classe ReviewList
     * Complexitat O (1)
     */
    public ReviewList(){
        RevList = new HashMap<Integer, Review>();
    }


    /**
     * Afegeix una review a la llista de reviews de l'usuari, comprova que l'item no ha estat ja valorat per l'usuari
     * @param itemId Item que vol valorar l'usuari
     * @param r Review que ha fet l'usuari
     * Complexitat O (RevList.size)
     */
    public void addReview(int itemId, Review r){
        if (hasValuated(itemId)) System.out.println("The item with id: " +itemId+" already has been valued by the user");
        else {
            RevList.put(itemId, r);
        }
    }



    /**
     * Obte la instancia de la review d'un item concret que ha fet l'usuari, l'usuari ha d'haver valorat l'item
     * @param itemId Item del que es vol obtenir la review
     * @return Retorna la instancia de la review que ha fet l'usuari
     * Complexitat O(1)
     */
    public Review getReview(int itemId){
        return RevList.get(itemId);
    }







    /**
     * Retorna el nombre de reviews que ha fet l'usuari
     * @return Nombre de reviews fetes
     * Complexitat O (1)
     */
    public int size(){
        return RevList.size();
    }


    /**
     * Obte la puntuació mitjana que un usuari ha donat a tots els items que ha valorat
     * @return Puntuació mitjana d'un usuari
     * Complexitat O(RevList.size)
     */
    public double getRaitings(){
        double points =0;
        for(Review r : RevList.values()){
            points += r.getPoints();
        }
        points /= RevList.size();
        return points;
    }



    /**
     * Retorna els K items valorats que més puntuació ha donat l'usuari amb la seva puntuació
     * @param k Nombre d'items que volem
     * @return K items millor valorats per l'usuari
     * Complexitat O(RevList.size)
     */
    public Map<Integer, Double> getReviewsU(int k) {
        Map<Integer, Double> ratings = new HashMap<>();
        for (Map.Entry<Integer, Review> r : RevList.entrySet()) {
            ratings.put(r.getKey(),r.getValue().getPoints());
        }

        LinkedHashMap<Integer, Double> ratingsOrdenats = new LinkedHashMap<>();
        ratings.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> {
                    ratingsOrdenats.put(x.getKey(), x.getValue());
                });
        Map<Integer,Double> aux= new HashMap<>();
        for(Map.Entry<Integer,Double> m : ratingsOrdenats.entrySet()){
            if(k > 0){
                aux.put(m.getKey(), m.getValue());
                --k;
            }
            else break;
        }
        return aux;
    }

    /**
     * Comprova si l'usuari ha valorat ja un item o no
     * @param itemId Item que volem saber si l'usuari ha valorat
     * @return Si l'usuari ha valorat l'item
     * Complexitat O (RevList.size)
     */
    public boolean hasValuated(Integer itemId){
        return RevList.containsKey(itemId);
    }
}
