package item;

import java.util.*;

public class ItemManager{
    Map<Integer, Item> items;
    Map<Integer, Map<Integer ,Double>> mapDistances; //id item1    id item2  dist
    ArrayList<Integer> IdItems;

    public ItemManager(){
        items = new HashMap<>();
        mapDistances = new HashMap<>();
        IdItems = new ArrayList<>();
    }

    //commplexitat O (items.size)
    public boolean existItem(int id){
        return items.containsKey(id);
    }

    //commplexitat O (items.size)
    public void createItem(int id, ArrayList<Column> attributes){
        if(existItem(id)) System.out.println("The item with id: " +id+" already exists");
        else {
            Item item = new Item(id,attributes);
            items.put(id, item);
        }
    }

    //commplexitat O (IdItems.size)
    public ArrayList<Integer> getItems(){
        return IdItems;
    }

    //commplexitat O (items.size)
    public void deleteItem(int id) {
        if(!existItem(id)) System.out.println("The item with id: " +id+" does not exist");
        else items.remove(id);
    }

    //Retornem donat un item, retornem els k items amb menys distancia,k=min(#items,k) items pareguts
    //Map<Integer ,Double>> mapDistances; id item1  dist
    //complexiatat O (items.size)
    public Map<Integer, Double> returnSimilarItems(int itemId , int k) {
        Map<Integer,Double> distances = mapDistances.get(itemId);
        int k2 = Math.min(distances.size(),k); //parametre k
        LinkedHashMap<Integer, Double> dists = new LinkedHashMap<>();
        distances.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .forEachOrdered(x -> {
                    dists.put(x.getKey(), x.getValue());
                });

        Iterator<Map.Entry<Integer,Double>> itr = dists.entrySet().iterator();
        Map<Integer, Double> kIt = new HashMap<>();
        for (Map.Entry<Integer,Double> e : dists.entrySet()) {
            if (k2 > 0) {
                kIt.put(e.getKey(), e.getValue());

                --k2;
            }
            else break;
        }
        LinkedHashMap<Integer, Double> kOr = new LinkedHashMap<>();
        kIt.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .forEachOrdered(x -> {
                    kOr.put(x.getKey(), x.getValue());
                });
;
        return kOr;
    }

    //complexitat O (listItems.size)
    private void createColumns(List<String> List_items) {
        int column_id = getColId(List_items.get(0));
//        System.out.println("Columna del ID: "+ column_id);
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
                        Column.ColumnString actItem = new Column.ColumnString(aux);
                        itmAux.add(actItem);
                        aux = "";
                    }

                    else if (List_items.get(i).charAt(j) == ',' || j == List_items.get(i).length() - 1) {
                        //System.out.println("NI PUTA IDEA: " + aux);

                        if (isInt(aux)) {
                            Column.ColumnInteger actItem = new Column.ColumnInteger(Integer.parseInt(aux));
                            itmAux.add(actItem);
                            //System.out.println(Integer.parseInt(aux));
                        } else if (isB(aux)) {
                            Column.ColumnBool actItem = new Column.ColumnBool(Boolean.parseBoolean(aux));
                            itmAux.add(actItem);
                            //System.out.println(Boolean.parseBoolean(aux));
                        } else if (isDbl(aux)) {
                            Column.ColumnDouble actItem = new Column.ColumnDouble(Double.parseDouble(aux));
                            itmAux.add(actItem);
                            //System.out.println(Double.parseDouble(aux));
                        } else{
                            Column.ColumnString actItem = new Column.ColumnString(aux);
                            itmAux.add(actItem);
                        }
                        aux = "";
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

    //Calcular distàncies entre dos strings-Algorisme de jaro-Winkler
    //complexitat O (s1.size * s2.size)   O (s1.size + s2.size) espacial
    static double jaroDistance(String s1, String s2) {
        //Strings iguals
        if (s1 == s2) {
            return 1.0;
        }
        //Mida dels dos strings
        int n = s1.length(), m = s2.length();
        if (n == 0 || m == 0) return 0.0;
        int maxDist = (int)Math.floor(Math.max(n, m)/2) - 1;
        int match = 0;

        //Hashes pels matches
        int hashS1[] = new int[n];
        int hashS2[] = new int[m];

        for (int i = 0; i < n; i++) {
            //Mira si hi ha algun match
            for (int j = Math.max(0, i - maxDist); j < Math.min(m, i + maxDist + 1); j++) {
                //Tenim match
                if (s1.charAt(i) == s2.charAt(j) && hashS2[j] == 0) {
                    hashS1[i] = hashS2[j] = 1;
                    match++;
                    break;
                }
            }
        }
        //No tenim match
        if (match == 0) {
            return 0.0;
        }
        //Nombre de transposicions
        double t = 0;
        int point = 0;
        for (int i = 0; i < n; i++) {
            if (hashS1[i] == 1) {
                while (hashS2[point] == 0) {
                    point++;
                }

                if (s1.charAt(i) != s2.charAt(point++)) {
                    t++;
                }
            }
        }
        t /= 2;
        return (((double)match)/((double)n) + ((double)match)/((double)m) + ((double)match - t)/((double)match))/ 3.0;
    }

    //commplexitat O (s1.size * s2.size)  O (s1.size + s2.size) espacial
    static double jaroWinkler(String s1, String s2){
        double jaroDist = jaroDistance(s1, s2);
        if (jaroDist > 0.7) {
            int prefix = 0;
            for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
                if (s1.charAt(i) == s2.charAt(i)) {
                    prefix++;
                } else break;
            }
            prefix = Math.min(4, prefix);
            jaroDist += 0.1 * prefix * (1 - jaroDist);
        }
        return jaroDist;
    }

    //commplexitat O (items.size² *  max(getSizeAttributes()))
    public void fillMapDistances(List<String> itemString) {
        createColumns(itemString);
        Collections.sort(IdItems);
        //Calcutating distances
        for(int i = 0; i < items.size(); ++i){
            Map<Integer , Double> internMap = new HashMap<>();
            int id1 = IdItems.get(i);
            for(int j = 0; j < items.size(); ++j) {
                if (i != j) {
                    double dist = 0;
                    int id2 = IdItems.get(j);
                    for (int k = 0; k < items.get(id1).getSizeAttributes(); ++k) {
                        if (items.get(id1).getColumn(k) instanceof Column.ColumnBool) {
                            Column.ColumnBool colb1 = (Column.ColumnBool) items.get(id1).getColumn(k);
                            Column.ColumnBool colb2 = (Column.ColumnBool) items.get(id2).getColumn(k);
                            boolean b1 = colb1.getValue();
                            boolean b2 = colb2.getValue();
                            if (b1 != b2) ++dist;

                        } else if (items.get(id1).getColumn(k) instanceof Column.ColumnInteger) {

                            Column.ColumnInteger coli1 = (Column.ColumnInteger) items.get(id1).getColumn(k);
                            Column.ColumnInteger coli2 = (Column.ColumnInteger) items.get(id2).getColumn(k);
                            int i1 = coli1.getValue();
                            int i2 = coli2.getValue();
                            if (i1 + i2 != 0) dist += (Math.abs(i1 - i2) / (i1 + i2));
                            else dist += (Math.abs(i1 - i2) / (i1 + i2 + 1));

                        } else if (items.get(id1).getColumn(k) instanceof Column.ColumnDouble) {

                            Column.ColumnDouble cold1 = (Column.ColumnDouble) items.get(id1).getColumn(k);
                            Column.ColumnDouble cold2 = (Column.ColumnDouble) items.get(id2).getColumn(k);

                            double d1 = cold1.getValue();
                            double d2 = cold2.getValue();

                            if (d1 + d2 != 0) dist += (Math.abs(d1 - d2) / (d1 + d2));
                            else dist += (Math.abs(d1 - d2) / (d1 + d2 + 1));
                        } else {

                            Column.ColumnString cols1 = (Column.ColumnString) items.get(id1).getColumn(k);
                            Column.ColumnString cols2 = (Column.ColumnString) items.get(id2).getColumn(k);

                            String s1 = cols1.getValue();
                            String s2 = cols2.getValue();
                            dist += (1 - jaroWinkler(s1, s2));
                            //if (!s1.equals(s2)) ++dist;
                        }
                    }
                    internMap.put(id2, dist);
                }
                mapDistances.put(id1, internMap);
            }
        }
    }

    //commplexitat O (1)
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

    //commplexitat O (1)
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

    //commplexitat O (1)
    private boolean isB(String input) {
        return input.equals("True") || input.equals("False") || input.equals("true") || input.equals("false") || input.equals("TRUE") || input.equals("FALSE");
    }

    //commplexitat O (fila.size)
    int getColId(String fila){
        int colAct=1;
        int j = 0;
        String aux = "";
        while(j < fila.length()){
            if(fila.charAt(j)==','){
                if (aux.equals("id")) return colAct;
                else{
//                    System.out.println("aux actual: " + aux);
                    aux = "";
                    ++colAct;
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
