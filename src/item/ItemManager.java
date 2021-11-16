package item;

import java.util.*;

public class ItemManager{

    Map<Integer, Item> items;

    Map<Integer, ArrayList<Column>> MatItemsType;

    Map<Integer, Map<Integer ,Double>> mapDistances;

    ArrayList<Integer> IdItems;


    public ItemManager(){items = new HashMap<>();}

    public boolean existItem(int id){
        return items.containsKey(id);
    }

    public void createItem(int id, String name, ArrayList<Column> attributes){
        if(existItem(id)) System.out.println("The item with id: " +id+" already exists");
        else {
            Item item = new Item(id, name, attributes);
            items.put(id, item);
        }
    }

    public void deleteItem(int id) {
        if(!existItem(id)) System.out.println("The item with id: " +id+" does not exist");
        else items.remove(id);
    }

    private void createColumns(LinkedList<String> items) {
        int column_id = -1;
        for (int i = 0; i < items.size(); ++i) {

            String id;
            ArrayList<Column> itmAux = new ArrayList<>();
            int idInt = -1;

            for (int j = 0; j < items.get(i).length() && items.get(i).charAt(j) != ','; ++j) {
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
                    IdItems.add(idInt);
                }
            }
            MatItemsType.put(idInt, itmAux);
        }
    }

    public void fillMapDistances(LinkedList<String> items) {
        createColumns(items);

        //Calcutating distances
        for(int i = 0; i < MatItemsType.size(); ++i){

            Map<Integer , Double> internMap = new HashMap<>();
            int id1 = IdItems.get(i);

            for(int j = i+1; j < MatItemsType.size(); ++j){

                double dist = 0;
                int id2 = IdItems.get(j);

                for (int k = 0; k < MatItemsType.get(id1).size(); ++k){

                    if (MatItemsType.get(id1).get(k).isBoolean()){
                        boolean b1 = MatItemsType.get(id1).get(k).valueBoolean();
                        boolean b2 = MatItemsType.get(id2).get(k).valueBoolean();
                        if (b1 != b2 ) ++dist;
                    }
                    else if (MatItemsType.get(id1).get(k).isInteger()){
                        int i1 = MatItemsType.get(id1).get(k).valueInteger();
                        int i2 = MatItemsType.get(id2).get(k).valueInteger();
                        dist += (Math.abs(i1 -i2)/ (i1 + i2));
                    }
                    else if (MatItemsType.get(id1).get(k).isDouble()){
                        double d1 = MatItemsType.get(id1).get(k).valueDouble();
                        double d2 = MatItemsType.get(id2).get(k).valueDouble();
                        dist += (Math.abs(d1 - d2)/ (d1 + d2));
                    }
                    else {
                        String s1 = MatItemsType.get(id1).get(k).valueString();
                        String s2 = MatItemsType.get(id2).get(k).valueString();
                        if (!s1.equals(s2)) ++dist;
                    }
                }
                internMap.put(id2, dist);
            }
            mapDistances.put(id1,internMap);
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