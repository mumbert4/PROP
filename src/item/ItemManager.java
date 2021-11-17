package item;

import java.util.*;

public class ItemManager{

    Map<Integer, Item> items;
    //Map<Integer, ArrayList<Column>> MatItemsType;
    Map<Integer, Map<Integer ,Double>> mapDistances; //id item1    id item2  dist
    ArrayList<Integer> IdItems;

    public ItemManager(){
        items = new HashMap<>();
        mapDistances = new HashMap<>();
        IdItems = new ArrayList<>();
    }

    public boolean existItem(int id){
        return items.containsKey(id);
    }

    public void createItem(int id, ArrayList<Column> attributes){
        if(existItem(id)) System.out.println("The item with id: " +id+" already exists");
        else {
            Item item = new Item(id,attributes);
            items.put(id, item);
        }
    }

    public void deleteItem(int id) {
        if(!existItem(id)) System.out.println("The item with id: " +id+" does not exist");
        else items.remove(id);
    }

    public void printDistances() {
        for(int i = 0; i < items.size(); ++i) {
            int id1 = IdItems.get(i);
            for (int j = i + 1; j < items.size(); ++j) {
                int id2 = IdItems.get(j);
                double dist = mapDistances.get(id1).get(id2);
                System.out.println("La distancia entre los items con id " + id1 + " y " + id2 + " es : " + dist);
            }
        }
    }

    //Retornem k=min(#items,3) items pareguts
    //Map<Integer, Map<Integer ,Double>> mapDistances; //id item1    id item2  dist
    public void retornaItemsSemblants() {
        Map<Integer,Double> distances = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer,Double>> item1 : mapDistances.entrySet()) {
            int k = Math.min(item1.getValue().entrySet().size(),3); //parametre k
            //System.out.println(item1.getKey()  + " " + item1.getValue());
            for (Map.Entry<Integer,Double> item2 : item1.getValue().entrySet()) {
                //System.out.println(item2.getKey() + " " + item2.getValue());
                distances.put(item2.getKey(),item2.getValue());
            }
            LinkedHashMap<Integer, Double> dists = new LinkedHashMap<>();
            distances.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                    .forEachOrdered(x -> {
                        dists.put(x.getKey(), x.getValue());
                    });
            System.out.println("Mapa ordenat ascendentment: " + dists);

            Iterator<Map.Entry<Integer,Double>> itr = dists.entrySet().iterator();
            for (Map.Entry<Integer,Double> e : dists.entrySet()) {
                if (k > 0) {
                    System.out.println(item1.getKey() + " " + e.getKey() + " " + e.getValue());
                    --k;
                } else break;
            }
            System.out.println();
            break;
        }
    }

    private void createColumns(List<String> List_items) {

        int column_id = getColId(List_items.get(0));
        System.out.println("Columna del ID: "+ column_id);

        for (int i = 1; i < List_items.size(); ++i) {// començam a 1 perque la 1 a fila no ens importa

            String id;
            ArrayList<Column> itmAux = new ArrayList<>();
            int idInt = -1;

            //System.out.println();
            //System.out.println(List_items.get(i));// cada fila de cada item

//            System.out.println(List_items.get(i).length()); // longitud de la fila

            int elem_act = 1;
            String aux = "";
//            List_items.get(i).charAt(j) != ','
            int j = 0;
            while (j < List_items.get(i).length()) {
                if (elem_act == column_id) { // si l'element actual es id, esteima a la columna del ID
                    aux = "";
                    while (List_items.get(i).charAt(j) != ',') { // aixo es el id del item
                        aux += List_items.get(i).charAt(j);
                        ++j;
                    }
                    //System.out.println("IDITEM: " + aux);
                    idInt = Integer.parseInt(aux);
                    IdItems.add(idInt);
                    aux = "";
                    ++elem_act;
                    ++j;
                } else {// ELEMENT ACTUAL NO ES EL ITEM ID
                    if (List_items.get(i).charAt(j) == '"') {
                        ++j;
                        aux = "";
                        while (!(List_items.get(i).charAt(j) == '"' && List_items.get(i).charAt(j + 1) == ',' && List_items.get(i).charAt(j + 2) != ' ')) {
                            aux += List_items.get(i).charAt(j);
                            ++j;
                        }
                        //System.out.println("DESCRIPCIO: " + aux);
                        j += 2; //PER COMENÇAR LA SEUENT ITERACIO EN UN STRING
                        Column actItem = new Column();
                        actItem.columnString(aux);
                        itmAux.add(actItem);
                        aux = "";
                    }

                    else if (List_items.get(i).charAt(j) == ',' || j == List_items.get(i).length() - 1) {
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
                        itmAux.add(actItem);
                        ++elem_act;
                        ++j;
                    }
                    else {
                        aux += List_items.get(i).charAt(j);
                        ++j;
                    }

                }
            }
            //System.out.println("Ahora mismo el id es : " + idInt + " y el tamaño de Array es : " + itmAux.size());
            createItem(idInt, itmAux);
        }
    }

    public void fillMapDistances(List<String> itemString) {
        createColumns(itemString);
        Collections.sort(IdItems);

        //Calcutating distances
        for(int i = 0; i < items.size(); ++i){

            Map<Integer , Double> internMap = new HashMap<>();
            int id1 = IdItems.get(i);

            for(int j = i+1; j < items.size(); ++j){

                double dist = 0;
                int id2 = IdItems.get(j);

                for (int k = 0; k < items.get(id1).getSizeAttributes(); ++k){

                    if (items.get(id1).getColumn(k).isBoolean()){
                        boolean b1 = items.get(id1).getColumn(k).valueBoolean();
                        boolean b2 = items.get(id2).getColumn(k).valueBoolean();
                        if (b1 != b2 ) ++dist;
                    }
                    else if (items.get(id1).getColumn(k).isInteger()){
                        int i1 = items.get(id1).getColumn(k).valueInteger();
                        int i2 = items.get(id2).getColumn(k).valueInteger();
                        if (i1+i2 != 0) dist += (Math.abs(i1 -i2)/ (i1 + i2));
                        else dist += (Math.abs(i1 -i2)/ (i1 + i2 + 1));
                    }
                    else if (items.get(id1).getColumn(k).isDouble()){
                        double d1 = items.get(id1).getColumn(k).valueDouble();
                        double d2 = items.get(id2).getColumn(k).valueDouble();
                        if (d1+d2 != 0) dist += (Math.abs(d1 - d2)/ (d1 + d2));
                        else dist += (Math.abs(d1 - d2)/ (d1 + d2 + 1));
                    }
                    else {
                        String s1 = items.get(id1).getColumn(k).valueString();
                        String s2 = items.get(id2).getColumn(k).valueString();
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

    int getColId(String fila){
        int col_act=1;
        int j = 0;
        String aux = "";

        while(j < fila.length()){

            if(fila.charAt(j)==','){
                if (aux.equals("id")) return col_act;
                else{
                    System.out.println("aux actual: " + aux);
                    aux = "";
                    ++col_act;
                    ++j;
                }
            }

            else{
                aux += fila.charAt(j);
                ++j;
            }
        }
        return 0;
    }
}
