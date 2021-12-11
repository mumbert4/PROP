package user;
import review.ReviewList;
import review.Review;

import java.util.Map;

public class activeUser {

    private String userName;
    private String password;
    private ReviewList reviews;

    public activeUser(String name, String passwd){
        userName = name;
        password = passwd;
        reviews = new ReviewList();
    }

    public void updateName(String newName){
        userName = newName;
    }

    public void updatePasswd(String newPasswd){
        password = newPasswd;
    }

    public String getName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public void addReview(int itemId, Review r){
        reviews.addReview(itemId, r);
    }

    //    mitjana raitings de un usuari
    public int numReviews(){
        return reviews.size();
    }
    public void setComment(int itemId, String comment){
        Review r = reviews.getReview(itemId);
        r.setComment(comment);
    }
    public void setPoints(int itemId, int points){
        Review r = reviews.getReview(itemId);
        r.setPoints(points);
    }
    public Review getReview(int itemId){
        return reviews.getReview(itemId);
    }

    public double raiAve(){
        return reviews.getRaitings();
    }

    public Map<Integer,Double> getReviewsUsers(int k) {
        return reviews.getReviewsU(k);
    }

    public boolean hasValuated(Integer itemId){
        return reviews.hasValuated(itemId);
    }
}
