package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/* Implementació de la classe que gestionarà les dades per la classe "Item" */
public class CtrlRatingsFile {
    private static CtrlRatingsFile singletonO;

    // complexitat O (1)
    public static CtrlRatingsFile getInstance() {
        if(singletonO == null) {
            singletonO = new CtrlRatingsFile() {};
        } return singletonO;
    }

    // complexitat O (1)
    private CtrlRatingsFile() {}

    // complexitat O (filename.size  -> tamany fitxer)
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
