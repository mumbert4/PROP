package review;

import java.util.*;


public class ReviewList {

    private Map<Integer, Review> RevList;

    public ReviewList(){
        RevList = new HashMap<Integer, Review>();
    }

    public void addReview(int itemId, Review r){ // = O( RevList.size() )
        if (itemAlreadyInList(itemId)) System.out.println("The item with id: " +itemId+" already has been valued by the user");
        else {
            RevList.put(itemId, r);
        }
    }
    public Review getReview(int itemId){// COMPLEXITAT -> O(1)
        return RevList.get(itemId);
    }

    public boolean itemAlreadyInList(int itemId) { // = O( RevList.size() )
        return RevList.containsKey(itemId);
    }

    public int size(){ // = O( 1 )
        return RevList.size();
    }

    public double getRaitings(){ // O( RevList.size )
        double points =0;
        for(Review r : RevList.values()){
            points += r.getPoints();
        }
        points /= RevList.size();
        return points;
    }

    public Map<Integer, Double> getReviewsU(int k) { // O( RevList.size )             clau:idItem, valor:rating de l'usuari a l'ítem
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

    public boolean hasValuated(Integer itemId){ // = O( RevList.size() )
        return RevList.containsKey(itemId);
    }

}
