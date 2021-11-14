package item;
import java.util.*;

public class ItemManager{

    Map<Integer, Item> items;

    Map<Integer, Map<Integer ,Double>> mapDistances;


    public ItemManager(){
        items = new HashMap<Integer, Item>();
    }

    boolean existItem(int id){
        return items.containsKey(id);
    }

    void createItem(int id, String name, ArrayList<Integer> attributes){
        if(existItem(id)) System.out.println("The item with id: " +id+" already exists");
        else {
            Item item = new Item(id, name, attributes);
            items.put(id, item);
        }
    }

    void eliminarItem(int id) {
        if(!existItem(id)) System.out.println("The item with id: " +id+" does not exist");
        else items.remove(id);
    }

}
