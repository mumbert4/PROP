package algoritmos;
import item.Item;
import java.util.*;

public interface RecommendationSystem {
    public default ArrayList<Item> calculate(Map<Integer, Map<Integer ,Double>> mapDistances) {
        return null;
    }
}
