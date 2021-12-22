package algorithms;

import java.util.List;
import java.util.Map;

/**
 * Classe que implementa el metode de l'avaluador dels algorismes de Collaborative Filtering i Content Based
 */

public class Avaluator {
    /**
     * Constructora de la classe
     */
    public Avaluator(){}

    /**
     * Calcul del Discounted Cumulative Gain
     * @param l LLista dels items recomanats per un dels dos algorismes
     * @param m Mapa de l'identificador dels items i la puntuacio que l'usuari dona segons el fitxer raiting.test.unknown.csv
     * @return Fiabilitat de la prediccio del resultat dels nostres algorismes
     * Complexitat O( l.size * m.size )
     */
    public double DCG(List<Integer> l, Map<Integer,Double> m){
        double dcg = 0;
        for(int i = 0 ; i < l.size(); ++i){
            Integer item = l.get(i);
            Double rel;
            if(!m.containsKey(item)) rel = 0.0;
            else rel = m.get(item);
            dcg += (Math.pow(2.0, rel) - 1.0) / (Math.log((i+1) + 1) / Math.log(2));
        }
        return dcg;
    }
    /**
     * Evalua la fiabilitat de la prediccio dels nostres algorismes
     * @param rec Solucio de l'algorisme Collaborative Filtering
     * @param type Solucio de l'algorisme Content Based
     * @param unknown Mapa de l'identificador dels items i la puntuacio que l'usuari dona segons el fitxer raiting.test.unknown.csv
     * @return
     */
    public double evaluate(List<Integer> rec, String type, Map<Integer,Double> unknown){
        if (type.equals("col")) System.out.println ("Collaborative: "+ DCG(rec,unknown));
        else if (type.equals("con")) System.out.println ("Content Based: "+ DCG(rec,unknown));
        return DCG(rec,unknown);
    }
}

