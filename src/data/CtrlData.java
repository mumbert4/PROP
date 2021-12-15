package data;

import user.UserManager;

import java.io.FileNotFoundException;
import java.util.*;


public class CtrlData {
    private static CtrlItemsFile CIF;
    private static CtrlRatingsFile CRF;
    private static CtrlPonderacionsFile CPF;
    private static CtrlData singletonO;

    // complexitat O (1)
    public static CtrlData getInstance() {
        if(singletonO == null) {
            singletonO = new CtrlData() {};
        } return singletonO;
    }

    // complexitat O (1)
    private CtrlData() {
        initializeCtrlData();
    }

    /* Inicialitzem controlador de dades */
    // complexitat O (1)
    public void initializeCtrlData() {
        CIF = CtrlItemsFile.getInstance();
        CRF = CtrlRatingsFile.getInstance();
        CPF = CtrlPonderacionsFile.getInstance();
    }

    public List<String> getPonderacions() throws FileNotFoundException {
        return CPF.getAll("PlantillaPelicules.csv");
    }

    // complexitat O (items.csv.size -> mida fitxer)
    public List<String> getItems() throws FileNotFoundException {
        return CIF.getAll("items.csv");
    }

    // complexitat O ( rai.size * max(rai.get(i).size) )
    public Map<Integer,Double> getUnknown(String userId) throws FileNotFoundException {
        List<String> rai = CRF.getAll("ratings.test.unknown.csv");
        System.out.println(userId);
        Map<Integer,Double> mapa = new HashMap<>();
        boolean trobat = false;
        String aux = "";
        int colIt = 0;
        int colRai = 0;
        int colAct = 0;
        int colUs = 0;
        for(int i = 0; i < rai.get(0).length(); ++i){
            if(rai.get(0).charAt(i) == ',' || i == rai.get(0).length()-1){
                if(aux.equals("userId")) colUs = colAct;
                else if (aux.equals("itemId")) colIt = colAct;
                else colRai = colAct;
                aux = "";
                ++colAct;
            }
            else aux += rai.get(0).charAt(i);
        }
        String user = "";
        int item = 0;
        double raiting = 0;
        for(int i = 1; i < rai.size(); ++i) {
            aux = "";
            colAct = 0;
            for(int j = 0; j < rai.get(i).length(); ++j){
                if(rai.get(i).charAt(j) == ',' || j == rai.get(i).length()-1){
                    if(colAct == colUs) user = aux;
                    else if(colAct == colIt) item = Integer.valueOf(aux);
                    else raiting = Double.valueOf(aux);
                    ++colAct;
                    aux = "";
                }
                else aux += rai.get(i).charAt(j);
            }
            if(trobat && !user.equals(userId)) break;
            if(user.equals(userId)){
                trobat = true;
                mapa.put(item,raiting);
            }
        }
        LinkedHashMap<Integer, Double> newM = new LinkedHashMap<>();
        mapa.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> {
                    newM.put(x.getKey(), x.getValue());
                });
        return newM;
    }

    // complexitat O ( rai.size * max(rai.get(i).size) )
    public void obtainData(UserManager manager) throws FileNotFoundException {
        List<String> rai = CRF.getAll("ratings.test.known.csv");
        String aux = "";
        int colUs = 0;
        int colIt = 0;
        int colRai = 0;
        int colAct = 0;
        for(int i = 0; i < rai.get(0).length(); ++i){
            if(rai.get(0).charAt(i) == ',' || i == rai.get(0).length()-1){
                if(aux.equals("userId")) colUs = colAct;
                else if (aux.equals("itemId")) colIt = colAct;
                else colRai = colAct;
                aux = "";
                ++colAct;
            }
            else aux += rai.get(0).charAt(i);
        }
//        System.out.println("Columna usuari: " + colUs + ", columna item: " + colIt + ", columna raitings: "+ colRai);
        String user = "";
        int item = 0;
        double raiting = 0;
        for(int i = 1; i < rai.size(); ++i){
            aux = "";
            colAct = 0;
            for(int j = 0; j < rai.get(i).length(); ++j){
                if(rai.get(i).charAt(j) == ',' || j == rai.get(i).length()-1){
                  if(colAct == colUs) user = aux;
                  else if(colAct == colIt) item = Integer.valueOf(aux);
                  else raiting = Double.valueOf(aux);
                  ++colAct;
                  aux = "";
                }
                else aux += rai.get(i).charAt(j);
            }
            if(!(manager.existUser(user))) manager.createUser(user,"","");
            manager.createReview(user, item, raiting,"");
        }
    }
}
