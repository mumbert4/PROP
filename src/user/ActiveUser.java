package user;
import review.ReviewList;
import review.Review;

import java.util.Map;


/**
 * Classe que representa un usuari actiu dins del sistema, format pel seu userName (identificador), el seu password i les reviews que ha realitzat
 */

public class ActiveUser {

    private String userName;
    private String password;
    private ReviewList reviews;


    /**
     * Constructora de la classe ActiveUser, crea un usuari sense reviews
     * @param name userName (identificador) del usuari
     * @param passwd Password del usuari
     * Complexitat O(1)
     */
    public ActiveUser(String name, String passwd){
        userName = name;
        password = passwd;
        reviews = new ReviewList();
    }

    /**
     * Actualitza el userName antic per un de nou
     * @param newName nou userName del usuari
     * Complexitat O(1)
     */
    public void updateName(String newName){
        userName = newName;
    }

    /**
     * Actualitza el password antic per un de nou
     * @param newPasswd nou password del usuari
     * Complexitat O(1)
     */
    public void changePassword(String newPasswd){
        password = newPasswd;
    }

    /**
     * Obte el userName de l'usuari
     * @return userName de la instància
     * Complexitat O(1)
     */
    public String getName(){
        return userName;
    }

    /**
     * Obte el password del usuari
     * @return password de la instància
     * Complexitat O(1)
     */
    public String getPassword(){
        return password;
    }

    /**
     * Afegeix una review nova a la llista de reviews
     * @param itemId Item que esteim valorant
     * @param r La review del item
     * Complexitat O(reviews.size)
     */
    public void addReview(int itemId, Review r){
        reviews.addReview(itemId, r);
    }

    /**
     * Obte el nombre de reviews que ha realitzat l'usuari
     * @return Nombre de reviews fetes
     * Complexitat O(1)
     */
    public int numReviews(){
        return reviews.size();
    }

    /**
     * Actualitza el comentari d'una review feta per l'usuari
     * @param itemId Item del qual volem actualitzar la seva review
     * @param comment Comentari nou que posarem a la review
     * Complexitat O(1)
     */
    public void setComment(int itemId, String comment){
        Review r = reviews.getReview(itemId);
        r.setComment(comment);
    }

    /**
     * Actualitza els punts d'una review feta per l'usuari
     * @param itemId Item del qual volem actualitzar la seva review
     * @param points Nova puntuació que li donarem al item
     * Complexitat O(1)
     */
    public void setPoints(int itemId, Double points){
        Review r = reviews.getReview(itemId);
        r.setPoints(points);
    }

    /**
     * Obte la review d'un item concret evaluat per l'usuari
     * @param itemId Item del que volem obtenir la review
     * @return Review del item
     * Complexitat O(1)
     */
    public Review getReview(int itemId){
        return reviews.getReview(itemId);
    }

    /**
     * Retorna la mitjana de les valoracions que ha realitzat el usuari
     * @return Mitjana de valoració de les reviews del usuari
     * Complexitat O( reviews.size )
     */
    public double raiAve(){
        return reviews.getRaitings();
    }

    /**
     * Obte les K reviews amb més valoració que ha donat l'usuari
     * @param k Nombre de reviews que volem
     * @return Map de tamany K amb els items i la valoració donada per l'usuari
     * Complexitat O(reviews.size)
     */
    public Map<Integer,Double> getReviewsUsers(int k) {
        return reviews.getReviewsU(k);
    }

    /**
     * Comprova si l'usuari ha valorat un item
     * @param itemId Item que volem saber si l'usuari ha valorat
     * @return Si l'usuari ha valorat l'item
     * Complexitat O (reviews.size)
     */
    public boolean hasValuated(Integer itemId){
        return reviews.hasValuated(itemId);
    }
}