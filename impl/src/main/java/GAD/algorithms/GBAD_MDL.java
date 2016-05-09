package GAD.algorithms;

import GAD.algorithms.helper.defaultImpl.Utils;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jkordas on 12/03/16.
 */
public class GBAD_MDL {
    //TODO: move params to config
    private static final int THRESHOLD = 10;
    private static final int BEST_SUBSTRUCTURES_LIMIT = 1;

    public static List<Anomaly> findAnomalies(DirectedGraph<StringVertex, StringEdge> g) {
        List<Anomaly> anomalies = new LinkedList<>();
        List<DirectedGraph<StringVertex, StringEdge>> bestSubstructures = Algorithms.getInstance().bestSubstructures(g,
                BEST_SUBSTRUCTURES_LIMIT);

        for (DirectedGraph<StringVertex, StringEdge> bestSubstructure : bestSubstructures) {
            System.out.println("bestSubstructure: " + bestSubstructure);

            List<DirectedGraph<StringVertex, StringEdge>> instances = Algorithms.getInstance().findInstances(g,
                    bestSubstructure, false);

            for (DirectedGraph<StringVertex, StringEdge> instance : instances) {
                System.out.println("instance: " + instance);
                int frequency = Algorithms.getInstance().findInstances(g, instance).size();
                int cost = Algorithms.getInstance().transformationCost(instance, bestSubstructure);

                int anomalyValue = frequency * cost;
                if (anomalyValue > 0 && anomalyValue < THRESHOLD) {
                    anomalies.add(new Anomaly(anomalyValue, instance));
                }
            }
        }

        Utils.sortAnomalies(anomalies);
        return anomalies;
    }
}
