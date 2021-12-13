package algorithms;

import java.util.List;
import java.util.Map;

public class Avaluator {

    //complexitat O (1)
    public Avaluator(){}

    //complexitat (l.size * m.size)
    public double DCG(List<Integer> l, Map<Integer,Double> m){
        double dcg = 0;
        for(int i = 0 ; i < l.size(); ++i){
            Integer item = l.get(i);
            Double rel;
            if(!m.containsKey(item)) rel = 0.0;
            else rel = m.get(item);
            dcg += (Math.pow(2.0, rel) - 1.0) / (Math.log((i+1) + 1) / Math.log(2));
        }
        return dcg;
    }

    //complexitat (col.size * unknown.size + cb.size * unknown.size)
    public void evaluate(List<Integer> col, List<Integer> cb, Map<Integer,Double> unknown){
        System.out.println ("Collaborative: "+ DCG(col,unknown));
        System.out.println ("Content Based: "+ DCG(cb,unknown));
    }
}
