package item;

public class Column {

    public static class ColumnString extends Column{
        String valueS;

        //Complexitat O (1)
        public ColumnString(String s){
            valueS = s;
        }

        //Complexitat O (1)
        public String getValue(){
            return valueS;
        }
    }

    public static class ColumnInteger extends Column{
        int valueInt;

        //Complexitat O (1)
        public ColumnInteger(int i){
            valueInt = i;
        }

        //Complexitat O (1)
        public Integer getValue(){
            return valueInt;
        }
    }

    public static class ColumnDouble extends Column{
        double valueD;

        //Complexitat O (1)
        public ColumnDouble(double d){
            valueD = d;
        }

        //Complexitat O (1)
        public Double getValue(){
            return valueD;
        }
    }

    public static class ColumnBool extends Column{
        boolean valueB;

        //Complexitat O (1)
        public ColumnBool(boolean b){
            valueB = b;
        }

        //Complexitat O (1)
        public Boolean getValue(){
            return valueB;
        }
    }
}
