package item;

import java.util.*;
/**
 * Aquesta classe ens gestiona i guarda tots els items del sistema i al distancia que hi ha entre els items
 * Disposa d'un map de Items, amb el seu identificador com a clau i la seva instància de la classe Item com a valor
 * Una matriu de distancies entre els items
 * Un array amb els IDs de tots els items
 */


public class ItemManager{
    Map<Integer, Item> items;
    ArrayList<ArrayList<Pair<Integer,Double>>> mapDistances;
    ArrayList<Integer> IdItems;


    /**
     * Constructora de la classe item manager
     * No te paràmetres d'entrada
     * Complexitat O (1)
     */
    public ItemManager(){
        items = new HashMap<>();
        mapDistances = new ArrayList<>();
        IdItems = new ArrayList<>();
    }


    /**
     * Metode que retorna si existeix un item dins del Item Manager
     * @param id Id de l'item que volm saber si existeix
     * @return Si l'item esta a la nostra base de dades
     * Complexitat O (items.size)
     */
    public boolean existItem(int id){
        return items.containsKey(id);
    }



    /**
     * Creadora de un item, si l'item ja no existeix, el crea i l'afegeix a items
     * @param id ID de l'item que volem crear
     * @param attributes Array amb els atributs de l'item
     * Complexitat O (items.size)
     */
    public void createItem(int id, ArrayList<Column> attributes){
        if(existItem(id)) System.out.println("The item with id: " +id+" already exists");
        else {
            Item item = new Item(id,attributes);
            items.put(id, item);
        }
    }

    /**
     * Obte tots els IDs dels items que existeixen
     * @return IDs dels items existents
     * Complexitat O(IdItems.size)
     */
    public ArrayList<Integer> getItems(){
        return IdItems;
    }



    /**
     * Elimina l' item passat per paràmetre de items
     * @param id ID de l'item que volem eliminar
     * Complexitat O (items.size)
     */
    public void deleteItem(int id) {
        if(!existItem(id)) System.out.println("The item with id: " +id+" does not exist");
        else items.remove(id);
    }

    
    /**
     * Retorna els K items més similars a l'item passat per paràmetre
     * @param itemId Item del qual volem items similars
     * @param k Nombre de items similars que volem 
     * @return Llista ordenada ascendenment dels items i la seva respectiva distancia a l'item passat per paràmetre
     * Complexitat O (items.size)
     */
    public List<Integer> returnSimilarItems(int itemId , int k) {

        ArrayList<Pair<Integer,Double>> distances = mapDistances.get(IdItems.indexOf(itemId));
        int k2 = Math.min(distances.size(),k); //parametre k



        Collections.sort(distances, new Comparator<Pair>(){
            @Override public int compare (Pair p1, Pair p2){
                double x1 = (double) p1.getSecond();
                double x2 = (double) p2.getSecond();
                if(x1 > x2) return 1;
                if(x2 > x1) return -1;
                return 0;

            }
        });


        List<Integer> kIt = new LinkedList<>();

        for(Pair p : distances){
            if(k2 > 0){
                kIt.add((Integer)p.getFirst());
                --k2;
            }
            else break;
        }


        return kIt;
    }



    /**
     * Crea les columnes de cada Item existent
     * @param listItems Llista de tots els items amb les seves característiques
     * Complexitat O (listItems.size)
     */
    private void createColumns(List<String> listItems) {
        int columnId = getColId(listItems.get(0));
        for (int i = 1; i < listItems.size(); ++i) {// començam a 1 perque la 1 a fila no ens importa
            String id;
            ArrayList<Column> itmAux = new ArrayList<>();
            int idInt = -1;



            int elemAct = 1;
            String aux = "";
            int j = 0;
            while (j < listItems.get(i).length()) {
                if (elemAct == columnId) { // si l'element actual es id, esteima a la columna del ID
                    aux = "";
                    while (listItems.get(i).charAt(j) != ',') { // aixo es el id del item
                        aux += listItems.get(i).charAt(j);
                        ++j;
                    }
                    //System.out.println("IDITEM: " + aux);
                    idInt = Integer.parseInt(aux);
                    IdItems.add(idInt);
                    aux = "";
                    ++elemAct;
                    ++j;
                } else {// ELEMENT ACTUAL NO ES EL ITEM ID
                    if (listItems.get(i).charAt(j) == '"') {
                        ++j;
                        aux = "";
                        while (!(listItems.get(i).charAt(j) == '"' && listItems.get(i).charAt(j + 1) == ',' && listItems.get(i).charAt(j + 2) != ' ')) {
                            aux += listItems.get(i).charAt(j);
                            ++j;
                        }
                        //System.out.println("DESCRIPCIO: " + aux);
                        j += 2; //PER COMENÇAR LA SEUENT ITERACIO EN UN STRING
                        Column.ColumnString actItem = new Column.ColumnString(aux);
                        itmAux.add(actItem);
                        aux = "";
                    }

                    else if (listItems.get(i).charAt(j) == ',' || j == listItems.get(i).length() - 1) {
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
                        ++elemAct;
                        ++j;
                    }
                    else {
                        aux += listItems.get(i).charAt(j);
                        ++j;
                    }

                }
            }
            createItem(idInt, itmAux);
        }
    }




    /**
     * Calcula la distancia entre dos strings utilitzant l'algorisme de Jaro-Winkler
     * @param s1 Primer string a comparar
     * @param s2 Segon string a comparar
     * @return Distancia entre els dos strings
     * Complexitat O(s1.size * s2.size)-> Temporal  O(s1.size + s2.size)-> espacial
     */
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



    /**
     * Rellena la matriu de distancies entre els items
     * @param itemString String dels items i de les seves columnes
     * Complexitat O (items.size² *  max(getSizeAttributes()))
     */
    public void fillMapDistances(List<String> itemString) {
        createColumns(itemString);
        Collections.sort(IdItems);

        //Calcutating distances
        for(int i = 0; i < IdItems.size(); ++i){
            
            ArrayList internArray = new ArrayList();
            int id1 = IdItems.get(i);
            for(int j = 0; j < IdItems.size(); ++j) {
                int id2 = IdItems.get(j);
                double dist = 0;
                if (i != j) {


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
                        }
                    }

                    
                    internArray.add(new Pair(id2,dist));
                }


            }
            
            mapDistances.add(internArray);
        }
    }





    /**
     * Comprova si un string donat és del tipus Int
     * @param input String que comprovam
     * @return Retorna si el string és del tipus Int
     * Complexitat O (1)
     */
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


    /**
     * Comprova si un string donat és del tipus Double
     * @param input String que comprovam
     * @return Retorna si el string és del tipus Double
     * Complexitat O (1)
     */
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


    /**
     * Comprova si un string donat és del tipus Bool
     * @param input String que comprovam
     * @return Retorna si el string és del tipus Bool
     * Complexitat O (1)
     */
    private boolean isB(String input) {
        return input.equals("True") || input.equals("False") || input.equals("true") || input.equals("false") || input.equals("TRUE") || input.equals("FALSE");
    }

    /**
     * Donada la primera fila del fitxer csv, ens diu la columna on es troba l'atribut Id
     * @param fila Primera fila del fitxer csv
     * @return Posició de la columna de l'atribut Id
     * Complexitat O (fila.size)
     */
    int getColId(String fila){
        int colAct=1;
        int j = 0;
        String aux = "";
        while(j < fila.length()){
            if(fila.charAt(j)==','){
                if (aux.equals("id")) return colAct;
                else{
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
