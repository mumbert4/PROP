package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Marta Granero I Martí
 */

/* Implementació de la classe que gestionarà les dades per la classe "Item" */
public class CtrlRatingsFitxer {

    private static CtrlRatingsFitxer singletonO;

    public static CtrlRatingsFitxer getInstance() {
        if(singletonO == null) {
            singletonO = new CtrlRatingsFitxer() {};
        } return singletonO;
    }
    public CtrlRatingsFitxer() {}

    public List<String> getAll(String filename) throws FileNotFoundException {
        LinkedList<String> ratings = new LinkedList<>();
        FileReader fr = new FileReader("DATA/"+filename);
        Scanner scan = new Scanner(fr);

        while(scan.hasNextLine()) {
            ratings.add(scan.nextLine() + "\n");
        }
        return ratings;
    }
}
