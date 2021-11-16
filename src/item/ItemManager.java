package item;

import java.util.*;

public class ItemManager{
    Map<Integer, Item> items;
    Map<Integer, ArrayList<Column>> MatItemsType;
    ArrayList<Integer> IdItems;
//    Map<Integer, Map<Integer ,Double>> mapDistances;

    public ItemManager(){
        items = new HashMap<>();
        MatItemsType = new HashMap<>();
        IdItems= new ArrayList<>();
    }

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

    int getColId(String fila){
        int col_act=1;
        int j = 0;
        String aux = "";
        while(j < fila.length()){
            if(fila.charAt(j)==','){
                if (aux.equals("id")) return col_act;
                else{
                    System.out.println("aux actual: " + aux);
                    aux= "";
                    ++col_act;
                    ++j;
                }
            }
            else{
                aux+= fila.charAt(j);
                ++j;
            }
        }
        return 0;
    }
    public void fillMapDistances(List<String> List_items) {

        System.out.println(List_items.get(0)); //FILA 0 -> COLUMNES
        //Cada fila és un string de la LinkedList
//        System.out.println(List_items.size()); //FILA 0 -> COLUMNES

        int column_id = getColId(List_items.get(0));

        System.out.println("Columna del ID: "+ column_id);

        for (int i = 1; i < List_items.size(); ++i) {// començam a 1 perque la 1 a fila no ens importa

            String id;
            ArrayList<Column> itmAux = new ArrayList<>();
            int idInt = -1;

            System.out.println();
            System.out.println(List_items.get(i));// cada fila de cada item

//            System.out.println(List_items.get(i).length()); // longitud de la fila

            int elem_act = 1;
            String aux = "";
//            List_items.get(i).charAt(j) != ','
            int j =0;
            Boolean desc= false;
            while(j < List_items.get(i).length()) {
                if(elem_act == column_id){ // si l'element actual es 6, esteima a la columna del ID
                    aux = "";
                    while(List_items.get(i).charAt(j) != ','){ // aixo es el id del item
                        aux+=List_items.get(i).charAt(j);
                        ++j;
                    }
                    System.out.println("IDITEM: " +aux);
                    idInt= Integer.parseInt(aux);
                    IdItems.add(idInt);
                    aux="";
                    ++elem_act;
                    ++j;
                }
                else{// ELEMENT ACTUAL NO ES EL ITEM ID
                    if(List_items.get(i).charAt(j) == '"'){
                        ++j;
                        aux="";
                        while(!(List_items.get(i).charAt(j)=='"' && List_items.get(i).charAt(j+1)==',' && List_items.get(i).charAt(j + 2) != ' ')){
                            aux+= List_items.get(i).charAt(j);
                            ++j;
                        }
                        System.out.println("DESCRIPCIO: " +aux);
                        j+=2; //PER COMENÇAR LA SEUENT ITERACIO EN UN STRING
                        Column actItem = new Column();
                        actItem.columnString(aux);
                        itmAux.add(actItem);
                        aux="";

                    } // COMENÇAM UNA DESCRIPCIO
                    else if (List_items.get(i).charAt(j) == ',') {
                        if (aux != "") {
                            System.out.println("NI PUTA IDEA: " +aux);
                            Column actItem = new Column();
                            if (isInt(aux)) {
                                actItem.columnInteger(Integer.parseInt(aux));
                                System.out.println(Integer.parseInt(aux));
                            } else if (isB(aux)) {
                                boolean val = Boolean.parseBoolean(aux);
                                actItem.columnBool(val);
                                System.out.println(Boolean.parseBoolean(aux));
                            } else if (isDbl(aux)) {
                                actItem.columnDouble(Double.parseDouble(aux));
                                System.out.println(Double.parseDouble(aux));
                            } else actItem.columnString(aux);

                            aux="";
                            itmAux.add(actItem);
                        }

                        ++elem_act;
                        ++j;
                    }
                    else{
                        aux+= List_items.get(i).charAt(j);
                        ++j;
                    }
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
