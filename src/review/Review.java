package review;

/**
 *Classe de review (valoració) d'un item
 * Només conte la puntuació donada per l'usuari i el un comentari (opcional)
 */

public class Review {
    private double points;
    private String comment;



    /**
     * Constructora de la classe de review buida
     * Complexitat O(1)
     */
    public Review(){}

    /**
     * Constructora de la classe review
     * @param points Puntuació donada per l'usuari a l'item
     * @param comment Comentari que ha realitzat l'usuari sobre l'item
     * Complexitat O (1)
     */
    public Review(double points, String comment){
        this.points = points;
        this.comment = comment;
    }



    /**
     * Consultora de la puntuació d'una review
     * @return Puntuació que ha donat l'usuari en la seva review
     * Complexitat O(1)
     */
    public double getPoints() {
        return points;
    }


    /**
     * Setejadora de la puntuació, modifica la puntuació que ha donat l'usuari per una nova
     * @param points Nova puntuació donada per l'usuari
     * Complexitat O (1)
     */
    public void setPoints(double points){
        this.points = points;
    }


    /**
     * Setejadora del comentari d'una review, modifica el comentari que ha fet l'usuari per un de nou
     * @param comment Nou comentari que ha fet l'usuari sobre l'item
     * Complexitat O(1)
     */
    public void setComment(String comment){
        this.comment = comment;
    }
}