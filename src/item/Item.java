package item;
import java.util.*;

public class Item {

    private int id;
    private ArrayList<Column> attributes;

    //complexitat O ( 1 )
    public Item() {}

    //complexitat O ( attributes.size )
    public Item(int id, ArrayList<Column> attributes) {
        this.id = id;
        this.attributes = attributes;
    }
    //Getters

    //complexitat O ( 1 )
    public int getId() {
        return id;
    }

    //complexitat O ( 1 )
    public int getSizeAttributes() {
        return attributes.size();
    }

    //complexitat O ( 1 )
    public Column getColumn(int i){
        return attributes.get(i);
    }

}
