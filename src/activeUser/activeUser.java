package activeUser;
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

    public int numReviews(){
        return reviews.size();
    }


}
