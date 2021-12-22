package item;

import java.util.List;
import estructures.Pair;

public class ColumnInteger extends Column{
    int valueInt;
    public ColumnInteger(int i){
        valueInt = i;
    }

    public Integer getValue(){
        return valueInt;
    }

    public double difference(Column column, List<Column> allValues) {
        Pair<Double, Double> p = meanStd(allValues);
        double mean = p.getFirst();
        double standardDev = p.getSecond();
        double module = Math.abs(((ColumnInteger) column).getValue() - this.getValue());
        return (module - mean)/standardDev;
    }
}
