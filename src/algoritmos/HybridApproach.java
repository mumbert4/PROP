package algoritmos;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HybridApproach {
    public HybridApproach(){ //CONSTRUCTOR BUID
    }
    private Set<Integer> union(List<Integer> l1, List<Integer> l2) {
        Set<Integer> s = new HashSet<>();
        s.addAll(l1);
        s.addAll(l2);
        return s;
    }
    public Set<Integer> recommendation(List<Integer> l1, List<Integer> l2) {
        return union(l1, l2);
    }
}

