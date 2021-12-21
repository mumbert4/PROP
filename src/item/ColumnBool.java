package item;

import java.util.List;

public class ColumnBool extends Column{
    boolean valueB;
    public ColumnBool(boolean b){
        valueB = b;
    }
    public Boolean getValue(){
        return valueB;
    }
    public double difference(Column column, List<Column> allValues) {
        Pair<Double, Double> p = meanStd(allValues);
        double mean = p.getFirst();
        double standardDev = p.getSecond();
        double module;
        if (((ColumnBool) column).getValue() == this.getValue()) module = 0;
        else module = 1;
        return (module - mean)/standardDev;
    }
}
