package review;

import java.util.Scanner;

public class driverReviewList {
    public static void main(String[] args){
        ReviewList reviewList = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.println("\t 1- createReviewList : creem la ReviewList");
        System.out.println("\t 2- addReview <Integer- idItem> <Review- review(<Double- points> <String- comment>) : afegim una nova review d'un item a la llista de valoracions de l'usuari");
        System.out.println("\t 3- getReview <Integer- idItem>: obtenim una review d'un ítem de la llista de valoracions d'un usuari");
        System.out.println("\t 4- itemAlreadyInList <Integer- idItem>: comprova si l'usuari ja ha valorat un ítem i es troba a la llista de valoracions");
        System.out.println("\t 5- size: retorna el numero de reviews fetes per l'usuari");
        System.out.println("\t 6- getRaitings: retorna la mitjana de puntuació de totes les reviews que ha hi ha a la llista de valoracions");
        System.out.println("\t 7- getReviewsU <Integer- K>: obtenim els k millors ítems o menys que ha valorat l’usuari");
        System.out.println("\t 8- hasValuated <Integer- idItem>: comprova si un item ja ha set avaluat per l'usuari");
        String action;
        action = sc.next();
        while (!action.equals("end")) {
            if (action.equals("createReviewList")) {
                reviewList = new ReviewList();
                if (reviewList != null) System.out.println("ReviewList s'ha creat correctament");
                else System.out.println("ReviewList no creada correctament");
            }
            else if (action.equals("addReview")) {
                System.out.print("Introdueix l'id de l'item: ");
                int itemId = sc.nextInt();
                System.out.print("Introdueix la valoració de l'ítem: ");
                double punts = sc.nextDouble();
                System.out.print("Introdueix el comentari sobre l'ítem: ");
                String comment = sc.next();
                Review review = new Review(punts,comment);
                if (reviewList != null) {
                    if (!reviewList.hasValuated(itemId)) {
                        reviewList.addReview(itemId, review);
                        System.out.println("S'ha afegit correctament la valoració " + review.getPoints() + " a l'ítem " + itemId);
                    }
                    else System.out.println("Ja existeix una valoració per aquest item");
                }
                else System.out.println("ReviewList no iniciada");
            }
            else if (action.equals("getReview")) { //idItem - valoracio
                System.out.print("Introdueix l'id de l'item: ");
                int itemId = sc.nextInt();
                if (reviewList != null) {
                    if (reviewList.getReview(itemId) != null) System.out.println("S'ha retornat correctament la review de l´item " + itemId);
                    else System.out.println("No tenim una review per aquest item");
                }
                else System.out.println("ReviewList no iniciada");
            }
            else if (action.equals("itemAlreadyInList")) {
                System.out.print("Introdueix l'id de l'item: ");
                int itemId = sc.nextInt();
                if (reviewList != null) {
                    if (!reviewList.hasValuated(itemId)) System.out.println("L'usuari no ha avaluat l'ítem " + itemId);
                    else System.out.println("L'usuari ja ha avaluat aquest ítem!");
                }
                else System.out.println("ReviewList no iniciada");
            }

            else if (action.equals("size")) {
                if (reviewList != null) System.out.println("La mida de la llista de valoracions és " + reviewList.size());
                else System.out.println("ReviewList no iniciada");
            }
            else if (action.equals("getRaitings")) {
                if (reviewList != null) System.out.println("La mitjana de valoracions de llista de reviews és " + reviewList.getRaitings());
                else System.out.println("ReviewList no iniciada");
            }
            else if (action.equals("getReviewsU")) {
                System.out.print("Introdueix el paràmetre k del sistema: ");
                int k = sc.nextInt(); //k del sistema
                if (reviewList != null) {
                    System.out.println("Els " + k + " millors ítems que ha avaluat l'usuari són " + reviewList.getReviewsU(k));
                }
                else System.out.println("ReviewList no iniciada");
            }
            if (action.equals("hasValuated")) {
                System.out.print("Introdueix l'id de l'item: ");
                int itemId = sc.nextInt();
                if (reviewList != null) {
                    if (!reviewList.hasValuated(itemId)) {
                        System.out.println("L'item " + itemId + " encara no ha set avaluat per l'usuari.");
                    }
                    else System.out.println("L'item " + itemId + " ja ha set avaluat per l'usuari.");
                }
                else System.out.println("ReviewList no iniciada");
            }
            action = sc.next();
        }
    }
}
