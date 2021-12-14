package review;
import java.util.Scanner;

public class driverReview {
    public static void main(String[] args){
        Review review = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.println("\t1. createReview: creem una nova review sense paràmetres");
        System.out.println("\t2. createReviewParam <Double- points> <String- comment>: creem una nova Review amb punts i comentari");
        System.out.println("\t3. getPoints: obtenim el #punts");
        System.out.println("\t4. setPoints <Double- points>: setegem el #punts");
        System.out.println("\t5. setComment <Double- comment>: setegem el comentari");

        String action;
        action = sc.next();
        while(!action.equals("end")) {
            if (action.equals("createReview")) {
                review = new Review();
                if (review != null) System.out.println("Review s'ha creat correctament");
                else System.out.println("Review no creada correctament");
            }
            if (action.equals("createReviewParam")) {
                System.out.print("Indica # de punts: ");
                Double punts = sc.nextDouble();
                System.out.print("Indica el comentari: ");
                String comment = sc.next();
                review = new Review(punts,comment);
                if (review != null) System.out.println("Review amb paràmetres s'ha creat correctament");
                else System.out.println("Review amb paràmetres creada correctament");
            }
            if (action.equals("getPoints")) {
                if (review != null) System.out.println("Numero de punts: " + review.getPoints());
                else System.out.println("Review no iniciada");
            }
            if (action.equals("setPoints")) {
                System.out.println("Introdueix el # de punts: ");
                Double punts = sc.nextDouble();
                if (review != null) review.setPoints(punts);
                else System.out.println("Review no iniciada");
            }
            if (action.equals("setComment")) {
                System.out.println("Introdueix el comentari: ");
                String comentari = sc.nextLine();
                if (review != null) review.setComment(comentari);
                else System.out.println("Review no iniciada");
            }
            action = sc.next();
        }
    }
}
