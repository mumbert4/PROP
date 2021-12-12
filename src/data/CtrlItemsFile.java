package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/* Implementació de la classe que gestionarà les dades per la classe "Item" */
public class CtrlItemsFile {
    /*
    Volem únicament una instància d'aquesta classe en el sistema, ja que la classe no
    té estat(atributs dinàmics).

    Declarem la constrtuctora com a privada i afegim una operació estàtica que retorni sempre
    la mateixa instància. Per accedir a aquesta instància ho farem amb la crida CtrlItemsFitxer.getInstance()
    */
    private static CtrlItemsFile singletonO;

    // complexitat O (1)
    public static CtrlItemsFile getInstance() {
        if(singletonO == null) {
            singletonO = new CtrlItemsFile() {};
        } return singletonO;
    }

    // complexitat O (1)
    public CtrlItemsFile() {}

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
