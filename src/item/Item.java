package item;
import java.util.*;

public class Item {

    private int id;
    private ArrayList<Column> attributes;

    public Item() {}

    public Item(int id, ArrayList<Column> attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    //Getters
    public int getId() {
        return id;
    }

    public int getSizeAttributes() { return attributes.size(); }
    public Column getColumn(int i){return attributes.get(i);}

}
