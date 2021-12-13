package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/* Implementació de la classe que gestionarà les dades per la classe "Item" */
public class CtrlItemsFile {
    private static CtrlItemsFile singletonO;

    // complexitat O (1)
    public static CtrlItemsFile getInstance() {
        if(singletonO == null) {
            singletonO = new CtrlItemsFile() {};
        } return singletonO;
    }

    // complexitat O (1)
    private CtrlItemsFile() {}

    // complexitat O (filename.size -> tamany fitxer)
    public List<String> getAll(String filename) throws FileNotFoundException {
        LinkedList<String> items = new LinkedList<String>();
        FileReader fr = new FileReader("DATA/"+filename);
        Scanner scan = new Scanner(fr);

        while(scan.hasNextLine()) {
            items.add(new String(scan.nextLine()+"\n"));
        }
        return items;
    }
}
