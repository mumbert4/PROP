package algoritmos;
import item.Item;
import java.util.*;

public interface RecommendationSystem {
    public default ArrayList<Item> calculate(String s) {
        return null;
    }
}
