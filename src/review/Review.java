package review;

public class Review {
    private double points;
    private String comment;

    public Review(){};

    public Review(double points, String comment){
        this.points = points;
        this.comment = comment;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public void setComment(String comment){
        this.comment = comment;
    }
}