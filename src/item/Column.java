package item;

public class Column {

    boolean valueB;
    int valueInt;
    double valueD;
    String valueS;

    private boolean bBoolean = false;
    private boolean bInteger = false;
    private boolean bDouble = false;
    private boolean bString = false;

    public Column(){}

    public void columnBool(boolean b){
        this.valueB = b;
        this.bBoolean = true;
    }

    public void columnInteger(int i){
        this.valueInt = i;
        this.bInteger = true;
    }

    public void columnDouble(double d){
        this.valueD = d;
        this.bDouble = true;
    }

    public void columnString (String s) {
        this.valueS = s;
        this.bString = true;

    }

    //Getters

    public boolean isBoolean(){
        return bBoolean;
    }
    public boolean isInteger(){
        return bInteger;
    }

    public boolean isDouble(){
        return bDouble;
    }

    public boolean isString(){
        return bString;
    }


    public boolean valueBoolean(){
        return valueB;
    }

    public Integer valueInteger(){
        return valueInt;
    }

    public Double valueDouble(){
        return valueD;
    }

    public String valueString() {
        return valueS;
    }
}
