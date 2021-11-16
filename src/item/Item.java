package item;
import java.util.*;

public class Item {

    private int id;
    private String name;
    private ArrayList<Column> attributes;

    public Item() {}

    public Item(int id, String name, ArrayList<Column> attributes) {
        this.id = id;
        this.name = name;
        this.attributes = attributes;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSizeAttributes() { return attributes.size(); }

}