package review;

public class Review {
    private double points;
    private String comment;

    //complexitat O( 1 )
    public Review(){}

    //complexitat O( 1 )
    public Review(double points, String comment){
        this.points = points;
        this.comment = comment;
    }

    //complexitat O( 1 )
    public double getPoints() {
        return points;
    }

    //complexitat O( 1 )
    public void setPoints(double points){
        this.points = points;
    }

    //complexitat O( 1 )
    public void setComment(String comment){
        this.comment = comment;
    }
}