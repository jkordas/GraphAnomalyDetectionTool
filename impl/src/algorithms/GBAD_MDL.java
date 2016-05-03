package algorithms;

import algorithms.helper.BestSubstructureFinder;
import algorithms.helper.InstanceFinder;
import algorithms.helper.TransformationCostCalculator;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jkordas on 12/03/16.
 */
public class GBAD_MDL {
    //TODO: move params to config
    private static final int THRESHOLD = 10;
    private static final int BEST_SUBSTRUCTURES_LIMIT = 1;

    public static Map<DirectedGraph<StringVertex, StringEdge>, Integer> findAnomalies(DirectedGraph<StringVertex, StringEdge> g) {
        Map<DirectedGraph<StringVertex, StringEdge>, Integer> anomalies = new HashMap<>();
        List<DirectedGraph<StringVertex, StringEdge>> bestSubstructures = BestSubstructureFinder.bestSubstructures(g,
                BEST_SUBSTRUCTURES_LIMIT);

        for (DirectedGraph<StringVertex, StringEdge> bestSubstructure : bestSubstructures) {
            System.out.println("bestSubstructure: " + bestSubstructure);

            List<DirectedGraph<StringVertex, StringEdge>> instances = InstanceFinder.findInstances(g, bestSubstructure, false);

            for (DirectedGraph<StringVertex, StringEdge> instance : instances) {
                System.out.println("instance: " + instance);
                int frequency = InstanceFinder.findInstances(g, instance).size();
                int cost = TransformationCostCalculator.transformationCost(instance, bestSubstructure);

                int anomalyValue = frequency * cost;
                if (anomalyValue > 0 && anomalyValue < THRESHOLD) {
                    anomalies.put(instance, anomalyValue);
                }
            }
        }

        return anomalies;
    }
}
