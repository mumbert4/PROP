package data;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author Marta Granero I Martí
 */


public class CtrlDades {

    /* Inicialitzem controlador de dades */
    private static CtrlItemsFitxer CIF = CtrlItemsFitxer.getInstance();
    private static CtrlRatingsFitxer CRF = CtrlRatingsFitxer.getInstance();

    private static CtrlDades singletonO;

    public static CtrlDades getInstance(){
        if(singletonO == null) {
            singletonO = new CtrlDades() {};
        } return singletonO;
    }

    public void escriureItems() throws FileNotFoundException {
        System.out.println(CIF.getAll("items.csv"));
    }
    public List<String> getItems() throws FileNotFoundException {
        return CIF.getAll("items.csv");
    }
    public void escriureRatings() throws FileNotFoundException {
        System.out.println(CRF.getAll("ratings.db.csv"));
    }
}
