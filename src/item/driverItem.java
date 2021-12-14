package item;

import java.util.ArrayList;
import java.util.Scanner;

public class driverItem {
    static ArrayList<Column> getAtributes(String at){
        ArrayList<Column> cols = new ArrayList<>();
        int j = 1;
        String aux = "";
        while(j < at.length()){
            if (at.charAt(j) == '"') {
                ++j;
                aux = "";
                while (!(at.charAt(j) == '"' && at.charAt(j + 1) == ',' && at.charAt(j + 2) != ' ')) {
                    aux += at.charAt(j);
                    ++j;
                }
                //System.out.println("DESCRIPCIO: " + aux);
                j += 2; //PER COMENÇAR LA SEUENT ITERACIO EN UN STRING
                Column.ColumnString actItem = new Column.ColumnString(aux);
                System.out.println(aux);
                cols.add(actItem);
                aux = "";
            }
            else if (at.charAt(j) == ',' || j == at.length() - 1) {
                //System.out.println("NI PUTA IDEA: " + aux);
                if (isInt(aux)) {
                    Column.ColumnInteger actItem = new Column.ColumnInteger(Integer.parseInt(aux));
                    cols.add(actItem);
                    //System.out.println(Integer.parseInt(aux));
                } else if (isB(aux)) {
                    Column.ColumnBool actItem = new Column.ColumnBool(Boolean.parseBoolean(aux));
                    cols.add(actItem);
                    //System.out.println(Boolean.parseBoolean(aux));
                } else if (isDbl(aux)) {
                    Column.ColumnDouble actItem = new Column.ColumnDouble(Double.parseDouble(aux));
                    cols.add(actItem);
                    //System.out.println(Double.parseDouble(aux));
                } else {
                    Column.ColumnString actItem = new Column.ColumnString(aux);
                    cols.add(actItem);
                }
                System.out.println(aux);
                aux = "";
                ++j;
            }
            else {
                aux += at.charAt(j);
                ++j;
            }
        }
        return cols;
    }

    public static void main(String[] args){
        Item item = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.println("\t 1- createItem <Integer- itemId> <String-atributs> : crear un nou item");
        System.out.println("\t 2- getId : obtenir el id de l'item");
        System.out.println("\t 3- sizeCol : obtenir quantitat d'atributs");
        System.out.println("\t 4- getCol <Integer- numAtribut> : obtenir un atribut en concret");
        String action;
        action = sc.next();
        while(!action.equals("end")){
            if(action.equals("createItem")){
                Integer itemId = sc.nextInt();
                String atributes = sc.nextLine();
                item = new Item(itemId,getAtributes(atributes));
            }
            if(action.equals("getId")){
                if(item!= null) System.out.println("Id del item: " + item.getId());
                else  System.out.println("Item no iniciat");
            }
            else if(action.equals("sizeCol")){
                if(item!= null) System.out.println("Num atributs: " + item.getSizeAttributes());
                else  System.out.println("Item no iniciat");
            }
            else if(action.equals("getCol")){
                Integer i = sc.nextInt();
                if(item!= null) System.out.println("Atribut: " + item.getColumn(i));
                else  System.out.println("Item no iniciat");
            }
            action = sc.next();
        }
    }
    private static boolean isInt(String input) {
        try{
            int inputDbl = Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }

    private static boolean isDbl(String input) {
        try{
            double inputDbl = Double.parseDouble(input);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }

    private static boolean isB(String input) {
        return input.equals("True") || input.equals("False") || input.equals("true") || input.equals("false") || input.equals("TRUE") || input.equals("FALSE");
    }
}
