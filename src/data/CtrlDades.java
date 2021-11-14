package data;

import java.io.FileNotFoundException;

/**
 * @author Marta Granero I Martí
 */


public class CtrlDades {

    private static CtrlItemsFitxer CIF = CtrlItemsFitxer.getInstance();
    private static CtrlRatingsFitxer CRF = CtrlRatingsFitxer.getInstance();

    private static CtrlDades singletonO;

    public static CtrlDades getInstance() {
        if(singletonO == null) {
            singletonO = new CtrlDades() {};
        } return singletonO;
    }

    public void escriureItems() throws FileNotFoundException {
        System.out.println(CIF.getAll("items.csv"));
    }
    public void escriureRatings() throws FileNotFoundException {
        System.out.println(CRF.getAll("ratings.db.csv"));
    }
}
