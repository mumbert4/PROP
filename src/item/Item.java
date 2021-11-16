package item;
import java.util.*;

public class Item {

    private int id;
    private String name;
    private ArrayList<Integer> attributes;

    public Item() {}

    public Item(int id, String name, ArrayList<Integer> attributes) {
        this.id = id;
        this.name = name;
        this.attributes = attributes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
