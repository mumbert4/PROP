package review;

import java.util.*;


public class ReviewList {

    private Map<Integer, Review> RevList;

    public ReviewList() {
        RevList = new HashMap<Integer, Review>();
    }

    public void addReview(int idItem, Review r) {
        if (itemAlreadyInList(idItem))
            System.out.println("The item with id: " + idItem + " already has been valued by the user");
        else {
            RevList.put(idItem, r);
        }
    }

    public Review getReview(int item_id) {
        return RevList.get(item_id);
    }

    public boolean itemAlreadyInList(int idItem) {
        return RevList.containsKey(idItem);
    }

    public int size() {
        return RevList.size();
    }

    public double getRaitings() {
        double points = 0;
        for (Review r : RevList.values()) {
            points += r.getPoints();
        }
        points /= RevList.size();
        return points;
    }

    public Map<Integer, Double> getReviewsU() { //clau:id_item, valor:rating de l'usuari a l'ítem
        Map<Integer, Double> ratings = new HashMap<>();
        for (Map.Entry<Integer, Review> r : RevList.entrySet()) {
<<<<<<< HEAD
            ratings.put(r.getKey(), r.getValue().getPoints());
=======
            ratings.put(r.getKey(),r.getValue().getPoints());
>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835
            //System.out.println(r.getValue().getPoints());
        }

        LinkedHashMap<Integer, Double> ratingsOrdenats = new LinkedHashMap<>();
        ratings.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> {
                    ratingsOrdenats.put(x.getKey(), x.getValue());
                });
<<<<<<< HEAD
        Map<Integer, Double> aux = new HashMap<>();
        int k = 3;
        for (Map.Entry<Integer, Double> m : ratingsOrdenats.entrySet()) {
            if (k > 0) {
                aux.put(m.getKey(), m.getValue());
                --k;
            } else break;
=======
        Map<Integer,Double> aux= new HashMap<>();
        int k = 3;
        for(Map.Entry<Integer,Double> m : ratingsOrdenats.entrySet()){
            if(k > 0){
                aux.put(m.getKey(), m.getValue());
                --k;
            }
            else break;
>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835
        }
//        System.out.println("Mapa ordenat descendentment: " + ratingsOrdenats);
//        System.out.println("Mapa ordenat descendentment, nomes k items: " + aux);
        return aux;
    }

<<<<<<< HEAD
    public boolean hasValuated(Integer item_id) {
        return RevList.containsKey(item_id);
    }
}


=======
    public boolean hasValuated(Integer item_id){
        return RevList.containsKey(item_id);
    }

}
>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835
