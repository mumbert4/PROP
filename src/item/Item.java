package item;
import java.util.*;


/**
 *Classe que representa la instància d'un item, format pel seu ID i els seus atributs
 */

public class Item {

    private int id;
    private ArrayList<Column> attributes;

    /**
     * Constructora de la classe item
     * Sense paràmetres d'entrada
     * Complexittat O (1)
     */
    public Item() {}


    /**
     * COntructora de la classe item
     * @param id ID que se li assignara al item
     * @param attributes Array dels atributs de l'item
     * Complexitat O (attributes.size)
     */
    public Item(int id, ArrayList<Column> attributes) {
        this.id = id;
        this.attributes = attributes;
    }
    //Getters



    /**
     * Obtenir el ID del Item
     * @return id del Item
     * Complexitat O (1)
     */
    public int getId() {
        return id;
    }



    /**
     * Obtenir el nombre d'atributs que te
     * @return Nombre d'atributs del item
     * Complexitat O(1)
     */
    public int getSizeAttributes() {
        return attributes.size();
    }


    /**
     * Obtenir un atribut concret
     * @param i Posició de l'atribut que volem
     * @return Valor de l'atribut
     * Complexitat O(1)
     */
    public Column getColumn(int i){
        return attributes.get(i);
    }

}
