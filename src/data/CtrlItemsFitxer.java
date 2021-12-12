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
public class CtrlItemsFitxer {
    /*
    Volem únicament una instància d'aquesta classe en el sistema, ja que la classe no
    té estat(atributs dinàmics).

    Declarem la constrtuctora com a privada i afegim una operació estàtica que retorni sempre
    la mateixa instància. Per accedir a aquesta instància ho farem amb la crida CtrlItemFitxer.getInstance()
    */
    private static CtrlItemsFitxer singletonO;

    // complexitat O (1)
    public static CtrlItemsFitxer getInstance() {
        if(singletonO == null) {
            singletonO = new CtrlItemsFitxer() {};
        } return singletonO;
    }


    // complexitat O (1)
    public CtrlItemsFitxer() {}

    // complexitat O (filename.size  -> tamany ficher)
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
