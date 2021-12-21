package item;

import java.util.List;

public class ColumnDouble extends Column{
    double valueD;

    public ColumnDouble(double d){
        valueD = d;
    }

    public Double getValue(){
        return valueD;
    }

    public double difference(Column column, List<Column> allValues) {
        Pair<Double, Double> p = meanStd(allValues);
        double mean = p.getFirst();
        double standardDev = p.getSecond();
        double module = Math.abs(((ColumnDouble) column).getValue() - this.getValue());
        return (module - mean)/standardDev;
    }
}
