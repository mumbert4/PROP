package item;

import java.util.*;

public class ItemManager{

    Map<Integer, Item> items;

    Map<Integer, ArrayList<Column>> MatItemsType;

    Map<Integer, Map<Integer ,Double>> mapDistances;


    public ItemManager(){items = new HashMap<>();}

    boolean existItem(int id){
        return items.containsKey(id);
    }

    void createItem(int id, String name, ArrayList<Integer> attributes){
        if(existItem(id)) System.out.println("The item with id: " +id+" already exists");
        else {
            Item item = new Item(id, name, attributes);
            items.put(id, item);
        }
    }

    void deleteItem(int id) {
        if(!existItem(id)) System.out.println("The item with id: " +id+" does not exist");
        else items.remove(id);
    }

    void fillMapDistances(LinkedList<String> items) {

        System.out.println(items.get(0)); //FILA 0 -> COLUMNES
        //Cada fila és un string de la LinkedList
        System.out.println(items.size()); //FILA 0 -> COLUMNES

        int column_id = -1;

        for (int i = 0; i < items.size(); ++i) {

            String id;
            ArrayList<Column> itmAux = new ArrayList<>();
            int idInt = -1;

            for (int j = 0; j < items.get(i).length() && items.get(i).charAt(j) != ','; ++j) {
                System.out.print(items.get(i).charAt(j));

                String elmCol = items.get(items.get(i).length() * i + j);

                if (i == 0) {
                    if (elmCol.equals("id")) column_id = j;
                }

                else if (j != column_id){

                    Column actItem = new Column();

                    if (isInt(elmCol)) {
                        actItem.columnInteger(Integer.parseInt(elmCol));
                    }

                    else if (isB(elmCol)) {
                        boolean val = Boolean.parseBoolean(elmCol);
                        actItem.columnBool(val);
                    }

                    else if (isDbl(elmCol)){
                        actItem.columnDouble(Double.parseDouble(elmCol));
                    }

                    else actItem.columnString(elmCol);

                    itmAux.add(actItem);
                }

                else {
                    idInt = Integer.parseInt(elmCol);
                }
            }

            MatItemsType.put(idInt, itmAux);
        }




    }

    private boolean isInt(String input) {
        try{
            int inputDbl = Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }

    private boolean isDbl(String input) {
        try{
            double inputDbl = Double.parseDouble(input);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }

    private boolean isB(String input) {
        return input.equals("True") || input.equals("False") || input.equals("true") || input.equals("false") || input.equals("TRUE") || input.equals("FALSE");
    }

}
