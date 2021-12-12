package user;
import review.ReviewList;
import review.Review;

import java.util.Map;

public class activeUser {

    private String userName;
    private String password;
    private ReviewList reviews;

    public activeUser(String name, String passwd){//complexitat O (1)
        userName = name;
        password = passwd;
        reviews = new ReviewList();
    }

    public void updateName(String newName){//complexitat O (1)
        userName = newName;
    }

    public void updatePasswd(String newPasswd){//complexitat O (1)
        password = newPasswd;
    }

    public String getName(){//complexitat O (1)
        return userName;
    }

    public String getPassword(){//complexitat O (1)
        return password;
    }

    //complexitat O ( reviews.size )
    public void addReview(int itemId, Review r){
        reviews.addReview(itemId, r);
    }

    //complexitat O (1)
    public int numReviews(){
        return reviews.size();
    }

    //complexitat O (1)
    public void setComment(int itemId, String comment){
        Review r = reviews.getReview(itemId);
        r.setComment(comment);
    }

    //complexitat O (1)
    public void setPoints(int itemId, int points){
        Review r = reviews.getReview(itemId);
        r.setPoints(points);
    }

    //complexitat O (1)
    public Review getReview(int itemId){
        return reviews.getReview(itemId);
    }

    //complexitat O ( reviews.size )
    public double raiAve(){
        return reviews.getRaitings();
    }


    //complexitat O ( reviews.size )
    public Map<Integer,Double> getReviewsUsers(int k) {
        return reviews.getReviewsU(k);
    }

    //complexitat O ( reviews.size )
    public boolean hasValuated(Integer itemId){
        return reviews.hasValuated(itemId);
    }
}
