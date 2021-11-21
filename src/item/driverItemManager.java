package item;

import java.util.ArrayList;
import java.util.Scanner;

public class driverItemManager {

    static ArrayList<Column> getAtributes(String at){
        ArrayList<Column> cols = new ArrayList<>();
        int j = 0;
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
                Column actItem = new Column();
                actItem.columnString(aux);
                cols.add(actItem);
                aux = "";
            }

            else if (at.charAt(j) == ',' || j == at.length() - 1) {
                //System.out.println("NI PUTA IDEA: " + aux);
                Column actItem = new Column();
                if (isInt(aux)) {
                    actItem.columnInteger(Integer.parseInt(aux));
                    //System.out.println(Integer.parseInt(aux));
                } else if (isB(aux)) {
                    boolean val = Boolean.parseBoolean(aux);
                    actItem.columnBool(val);
                    //System.out.println(Boolean.parseBoolean(aux));
                } else if (isDbl(aux)) {
                    actItem.columnDouble(Double.parseDouble(aux));
                    //System.out.println(Double.parseDouble(aux));
                } else actItem.columnString(aux);
                aux = "";
                cols.add(actItem);
                ++j;
            }
            else {
                aux += at.charAt(j);
                ++j;
            }
        }
        return cols;
    }

    public static void main(String args[]){
        ItemManager manager = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.print("\t 1- initiate: iniciam el item manager");
        System.out.print("\t 2- createItem <Integer- item_id> <String- atributs>: cream un item amb els seus atributs");
        System.out.print("\t 3- existItem <Integer- item_id> : retorna si el manager conte el item");
        System.out.print("\t 4- getItems : retrona els items que conte el manager");
        System.out.print("\t 5- end : acaba amb el driver");
        String action;
        action = sc.next();
        while(!action.equals("end")){
            if(action.equals("initiate")){
                manager = new ItemManager();
                System.out.println("Manager iniciat");
            }
            else if(action.equals("createItem")){
                Integer item_id = sc.nextInt();
                String atributes = sc.next();
                if(manager != null){
                    if(!manager.existItem(item_id)) manager.createItem(item_id, getAtributes(atributes));
                    else System.out.println("Ja existeix aquestv item al manager");
                }
                else System.out.println("Manager no iniciat");
            }
            else if(action.equals("existItem")){
                Integer item_id = sc.nextInt();
                if (manager!= null) System.out.println(manager.existItem(item_id));
                else System.out.println("Manager no iniciat");
            }
            else if(action.equals("getItems")){
                if(manager!=null){
                    System.out.println(manager.getItems());
                }
                else System.out.println("Manager no iniciat");
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
