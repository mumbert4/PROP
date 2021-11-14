package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

/**
 * @author Marta Granero I Martí
 */

public class CtrlUsersFitxer {
    /*
Volem únicament una instància d'aquesta classe en el sistema, ja que la classe no
té estat(atributs dinàmics).

Declarem la constrtuctora com a privada i afegim una operació estàtica que retorni sempre
la mateixa instància. Per accedir a aquesta instància ho farem amb la crida CtrlItemFitxer.getInstance()
*/
    private static CtrlUsersFitxer singletonO;

    public static CtrlUsersFitxer getInstance() {
        if(singletonO == null) {
            singletonO = new CtrlUsersFitxer() {};
        } return singletonO;
    }

    public CtrlUsersFitxer() {}

    public List<String> getAll(String filename) throws FileNotFoundException {
        LinkedList<String> users = new LinkedList<>();
        FileReader fr = new FileReader("DATA/"+filename);
        Scanner scan = new Scanner(fr);

        while(scan.hasNextLine()) {
            users.add(scan.nextLine()+"\n");
        }
        return users;
    }
}
