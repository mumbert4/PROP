package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import data.CtrlItemsFitxer;
import data.CtrlRatingsFitxer;
import java.util.stream.Collectors;


/**
 * @author Marta Granero I Martí
 */


public class CtrlDades {

    /* Inicialitzem controlador de dades */
    private static final CtrlItemsFitxer CIF = CtrlItemsFitxer.getInstance();
    private static final CtrlRatingsFitxer CRF = CtrlRatingsFitxer.getInstance();
    //private static CtrlUsersFitxer CUF = CtrlUsersFitxer.getInstance();

    private static CtrlDades singletonO;

    public static CtrlDades getInstance(){
        if(singletonO == null) {
            singletonO = new CtrlDades() {};
        } return singletonO;
    }

    /**
    public static CtrlDades getItems() throws FileNotFoundException {
        return CIF.getAll("items.csv");
    }
     */

    /**
    public static CtrlDades getRatings() throws FileNotFoundException {
        return CRF.getAll("ratings.db.csv");
    }
     */

    public void escriureItems() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("DATA/items.csv"));
        String line;
        List<String> myList = new LinkedList<String>();
        List<String> myList1 = new LinkedList<String>();
        List<String> myList2 = new LinkedList<String>();
        while ((line = br.readLine()) != null) {
            // use comma as separator
            String[] columna = line.split(",");
            Collections.addAll(myList, columna[0]);
            Collections.addAll(myList1, columna[1]);
            Collections.addAll(myList2, columna[2]);
            System.out.println(columna[0]);
        }
        System.out.println(myList);
        System.out.println("\n");
        System.out.println(myList1);
        System.out.println("\n");
        System.out.println(myList2);
        System.out.println("\n");
        /*
        //COMPTAR EL # DE COLUMNES//
        String s = CRF.getAll("items.csv").get(0);
        System.out.println("no of words = "+s.split(",").length);
        /*Converir un element(item) de la linked list en una llista d'strings*/
        /*
        String [] cols = s.split("\\s*,\\s*");
        List<String> container = Arrays.asList(cols);
        System.out.println(container.contains("adult"));
        System.out.println(container.contains("genres"));

        for (int i = 0; i < container.size(); ++i) {
            if (container.get(i).equals("adult")) {
                System.out.println("HOLA");
            }
        }

        String [] fila0 = CRF.getAll("items.csv").get(2).split("\\s*,\\s*");
        List<String> cont = Arrays.asList(fila0);

        //iterar sobre els atributs de l'item
        for (int i = 0; i < cont.size(); ++i) {
            System.out.println(cont.get(i));
        }

        //printa l'item(que és una llista d'elements)
        System.out.println(cont);
        */
        //System.out.println(CRF.getAll("items.csv"));
    }
    public void escriureRatings() throws FileNotFoundException {
        System.out.println(CRF.getAll("ratings.db.csv"));
    }
}
