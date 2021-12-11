package domini;

import data.CtrlDades;
import user.userManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Marta Granero I Martí
 */


//Fa queries al CtrlDades i carrega les dades que es necessiten
public class CtrlDomini {
    private CtrlDades CDades;
    private List<String> items;
    private Map<Integer,Double> ratings;

    private static CtrlDomini singletonO;

    public CtrlDomini() {
        inicialitzarCtrlDomini();
    }

    /* Inicialitzem el controlador del domini*/
    private void inicialitzarCtrlDomini() {
        CDades = CtrlDades.getInstance();
    }

    public static CtrlDomini getInstance(){
        if(singletonO == null) {
            singletonO = new CtrlDomini() {};
        } return singletonO;
    }

    //Obtenir ratings known
    public void obtenirDades(userManager manager) throws FileNotFoundException {
        CDades.obtenirDades(manager);
    }

    //Obtenir items
    public List<String> getItems() throws IOException {
        return CDades.getItems();
    }

    //Obtenir ratings unknown
    public Map<Integer,Double> getRatings(String userId) throws IOException {
        return CDades.getUnknown(userId);
    }
}
