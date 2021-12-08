package domini;

import data.CtrlDades;
import user.userManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
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
        items = new LinkedList<>();
        ratings = new HashMap<>();
    }

    public static CtrlDomini getInstance(){
        if(singletonO == null) {
            singletonO = new CtrlDomini() {};
        } return singletonO;
    }

    //Obtenir items
    public List<String> carregarDadesItems() throws IOException {
        return CDades.getItems();
    }

    //Obtenir ratings unknown
    public Map<Integer,Double> carregarUnknown(String userId) throws FileNotFoundException {
        return CDades.getUnknown(userId);
    }

    //Obtenir ratings known
    public void obtenirDades(userManager manager) throws FileNotFoundException {
        CDades.obtenir_dades(manager);
    }

    public void getItems() throws IOException {
        items = singletonO.carregarDadesItems();
    }
}
