package algoritmos;
import item.Item;
import user.userManager;
import item.ItemManager;
import java.util.*;

interface RecommendationSystem {

    default List<Integer> calculate(String userId, int k, List<Integer> Items){return null;}
}
