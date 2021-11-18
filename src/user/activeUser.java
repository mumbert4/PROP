package user;
import review.ReviewList;
import review.Review;

public class activeUser {
    private String user_name;
    private String password;
    ReviewList reviews;

    public activeUser(String name, String passwd){
        user_name = name;
        password = passwd;
        reviews = new ReviewList();
    }

    void updateName(String newName){
        user_name = newName;
    }

    void updatePasswd(String newPasswd){
        password = newPasswd;
    }

    public String getName(){
        return user_name;
    }

    public String getPassword(){
        return password;
    }

    public void addReview(int item_id, Review r){
        reviews.addReview(item_id, r);
    }

    //    mitjana raitings de un usuari
    public int numReviews(){
        return reviews.size();
    }
    public void setComment(int item_id, String comment){
        Review r = reviews.getReview(item_id);
        r.setComment(comment);
    }
    public void setPoints(int item_id, int points){
        Review r = reviews.getReview(item_id);
        r.setPoints(points);
    }
    public Review getReview(int item_id){
        return reviews.getReview(item_id);
    }

    public double raiAve(){
        return reviews.getRaitings();
    }

    public void getReviewsUsers() {
        reviews.getReviewsU();
    }
}
