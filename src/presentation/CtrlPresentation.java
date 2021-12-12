package presentation;

import domain.CtrlDomain;

/**
 * @author Marta Granero I Martí
 */

public class CtrlPresentation {

    private CtrlDomain cDomain;

    private static CtrlPresentation singletonO;

    public CtrlPresentation() {
        cDomain = CtrlDomain.getInstance();
    }

    public static CtrlPresentation getInstance(){
        if(singletonO == null) {
            singletonO = new CtrlPresentation(){};
        } return singletonO;
    }
}
