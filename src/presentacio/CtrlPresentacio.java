package presentacio;

import domini.CtrlDomini;

/**
 * @author Marta Granero I Martí
 */

public class CtrlPresentacio {

    private CtrlDomini CDomini;

    private static CtrlPresentacio singletonO;

    public CtrlPresentacio() {
        CDomini = CtrlDomini.getInstance();
    }

    public static CtrlPresentacio getInstance(){
        if(singletonO == null) {
            singletonO = new CtrlPresentacio(){};
        } return singletonO;
    }
}
