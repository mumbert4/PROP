package review;

import review.Review;

import java.util.HashMap;
import java.util.Map;

public class ReviewList {

    private Map<Integer, Review> RevList;

    public ReviewList(){
        RevList = new HashMap<Integer, Review>();
    }

    public void addReview(int idItem, Review r){
        if (itemAlreadyInList(idItem)) System.out.println("The item with id: " +idItem+" already has been valued by the user");
        else {
            RevList.put(idItem, r);
        }
    }
    public Review getReview(int item_id){
        return RevList.get(item_id);
    }

    public boolean itemAlreadyInList(int idItem) {
        return RevList.containsKey(idItem);
    }

    public int size(){
        return RevList.size();
    }

    public double getRaitings(){
        double points =0;
        for(Review r : RevList.values()){
            points += r.getPoints();
        }
        points /= RevList.size();
        return points;
    }
}
