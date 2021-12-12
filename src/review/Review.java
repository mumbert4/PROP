package review;

public class Review {
    private double points;
    private String comment;

    public Review(){}

    public Review(double points, String comment){// COMPLEXITAT -> O(1)
        this.points = points;
        this.comment = comment;
    }

    public double getPoints() {// COMPLEXITAT -> O(1)
        return points;
    }

    public void setPoints(int points){// COMPLEXITAT -> O(1)
        this.points = points;
    }

    public void setComment(String comment){// COMPLEXITAT -> O(1)
        this.comment = comment;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}