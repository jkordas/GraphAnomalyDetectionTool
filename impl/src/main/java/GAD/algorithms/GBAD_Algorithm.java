package GAD.algorithms;

import GAD.Config;
import GAD.algorithms.helper.defaultImpl.Utils;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jkordas on 10/05/16.
 */
public abstract class GBAD_Algorithm {
    private static final int BEST_SUBSTRUCTURES_LIMIT = Config.getInstance().BEST_SUBSTRUCTURES_LIMIT;

    abstract List<Anomaly> findAnomaliesForSubstructure(DirectedGraph<StringVertex, StringEdge> g,
                                                        DirectedGraph<StringVertex, StringEdge> bestSubstructure);

    public List<Anomaly> findAnomalies(DirectedGraph<StringVertex, StringEdge> g) {
        List<Anomaly> anomalies = new LinkedList<>();
        List<DirectedGraph<StringVertex, StringEdge>> bestSubstructures = Algorithms.getInstance().bestSubstructures(g,
                BEST_SUBSTRUCTURES_LIMIT);

        for (DirectedGraph<StringVertex, StringEdge> bestSubstructure : bestSubstructures) {
            System.out.println("bestSubstructure: " + bestSubstructure);

            anomalies.addAll(findAnomaliesForSubstructure(g, bestSubstructure));
        }

        Utils.sortAnomalies(anomalies);
        return anomalies;
    }

}
