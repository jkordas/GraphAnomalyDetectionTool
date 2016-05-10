package GAD.algorithms;

import GAD.Config;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jkordas on 12/03/16.
 */
public class GBAD_MDL extends GBAD_Algorithm {
    private static final int THRESHOLD = Config.getInstance().GBAD_MDL_THRESHOLD;

    private static GBAD_MDL ourInstance = new GBAD_MDL();

    public static GBAD_MDL getInstance() {
        return ourInstance;
    }

    private GBAD_MDL() {
    }

    @Override
    List<Anomaly> findAnomaliesForSubstructure(DirectedGraph<StringVertex, StringEdge> g,
                                               DirectedGraph<StringVertex, StringEdge> bestSubstructure) {
        List<Anomaly> anomalies = new LinkedList<>();

        List<DirectedGraph<StringVertex, StringEdge>> instances = Algorithms.getInstance().findInstances(g, bestSubstructure, false);

        for (DirectedGraph<StringVertex, StringEdge> instance : instances) {
            System.out.println("instance: " + instance);
            int frequency = Algorithms.getInstance().findInstances(g, instance).size();
            int cost = Algorithms.getInstance().transformationCost(instance, bestSubstructure);

            int anomalyValue = frequency * cost;
            if (anomalyValue > 0 && anomalyValue < THRESHOLD) {
                anomalies.add(new Anomaly(anomalyValue, instance));
            }
        }

        return anomalies;
    }
}
