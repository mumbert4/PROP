package review;

import java.util.*;


public class ReviewList {

    private Map<Integer, Review> RevList;

    //complexitat O( 1 )
    public ReviewList(){
        RevList = new HashMap<Integer, Review>();
    }

    // = O( RevList.size() )
    public void addReview(int itemId, Review r){
        if (itemAlreadyInList(itemId)) System.out.println("The item with id: " +itemId+" already has been valued by the user");
        else {
            RevList.put(itemId, r);
        }
    }
    //complexitat O( 1 )
    public Review getReview(int itemId){
        return RevList.get(itemId);
    }

    //complexitat O( RevList.size() )
    public boolean itemAlreadyInList(int itemId) {
        return RevList.containsKey(itemId);
    }

    //complexitat O( 1 )
    public int size(){
        return RevList.size();
    }

    //complexitat O( RevList.size() )
    public double getRaitings(){
        double points =0;
        for(Review r : RevList.values()){
            points += r.getPoints();
        }
        points /= RevList.size();
        return points;
    }

    //complexitat O( RevList.size() )
    public Map<Integer, Double> getReviewsU(int k) {//clau:idItem, valor:rating de l'usuari a l'ítem
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

    //complexitat O( RevList.size() )
    public boolean hasValuated(Integer itemId){
        return RevList.containsKey(itemId);
    }
}
