package item;

public class Column {

    public static class ColumnString extends Column{
        String valueS;
        public ColumnString(String s){
            valueS = s;
        }

        public String getValue(){
            return valueS;
        }
    }

    public static class ColumnInteger extends Column{
        int valueInt;
        public ColumnInteger(int i){
            valueInt = i;
        }

        public Integer getValue(){
            return valueInt;
        }
    }

    public static class ColumnDouble extends Column{
        double valueD;
        public ColumnDouble(double d){
            valueD = d;
        }

        public Double getValue(){
            return valueD;
        }
    }

    public static class ColumnBool extends Column{
        boolean valueB;
        public ColumnBool(boolean b){
            valueB = b;
        }

        public Boolean getValue(){
            return valueB;
        }
    }
}
