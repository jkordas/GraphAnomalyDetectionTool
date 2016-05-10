package GAD.algorithms;

import GAD.Config;
import GAD.algorithms.helper.defaultImpl.Utils;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jkordas on 12/03/16.
 */
public class GBAD_MPS extends GBAD_Algorithm {
    private static final int THRESHOLD = Config.getInstance().GBAD_MPS_THRESHOLD;

    private static GBAD_MPS ourInstance = new GBAD_MPS();

    public static GBAD_MPS getInstance() {
        return ourInstance;
    }

    private GBAD_MPS() {
    }

    @Override
    List<Anomaly> findAnomaliesForSubstructure(DirectedGraph<StringVertex, StringEdge> g,
                                               DirectedGraph<StringVertex, StringEdge> bestSubstructure) {
        List<Anomaly> anomalies = new LinkedList<>();

        List<DirectedGraph<StringVertex, StringEdge>> bestSubstructureInstances = Algorithms.getInstance().findInstances(g,
                bestSubstructure);
        List<DirectedGraph<StringVertex, StringEdge>> includedSubstructures = Algorithms.getInstance().includedSubstructures(
                bestSubstructure);

        List<DirectedGraph<StringVertex, StringEdge>> instances = new LinkedList<>();
        for (DirectedGraph<StringVertex, StringEdge> includedSubstructure : includedSubstructures) {
            instances.addAll(Algorithms.getInstance().findInstances(g, includedSubstructure));
        }

        for (DirectedGraph<StringVertex, StringEdge> instance : instances) {
            System.out.println("instance: " + instance);
            if (!Utils.containsSubstructure(bestSubstructureInstances, instance)) {
                int frequency = Algorithms.getInstance().findInstances(g, instance).size() - bestSubstructureInstances.size();
                int cost = Algorithms.getInstance().subgraphTransformationCost(instance, bestSubstructure);

                int anomalyValue = frequency * cost;
                System.out.println(anomalyValue);
                if (anomalyValue > 0 && anomalyValue < THRESHOLD) {
                    anomalies.add(new Anomaly(anomalyValue, instance));
                }
            }
        }

        return anomalies;
    }
}
