package algoritmos;
import java.util.List;

interface RecommendationSystem {
    default List<Integer> calculate(String userId, int k, List<Integer> Items){ return null; }
}
