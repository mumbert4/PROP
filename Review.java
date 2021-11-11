import java.util.*;

public class Review {
    private int points;
    private String comment;

    public Review(){};

    public Review(int points, String comment){
        this.points = points;
        this.comment = comment;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public void setComment(String comment){
        this.comment = comment;
    }
}