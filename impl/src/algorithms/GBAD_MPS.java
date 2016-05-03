package algorithms;

import algorithms.helper.*;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by jkordas on 12/03/16.
 */
public class GBAD_MPS {
    //TODO: move params to config
    private static final int THRESHOLD = 10;
    private static final int BEST_SUBSTRUCTURES_LIMIT = 1;

    public static Map<DirectedGraph<StringVertex, StringEdge>, Integer> findAnomalies(DirectedGraph<StringVertex, StringEdge> g) {
        Map<DirectedGraph<StringVertex, StringEdge>, Integer> anomalies = new HashMap<>();
        List<DirectedGraph<StringVertex, StringEdge>> bestSubstructures = BestSubstructureFinder.bestSubstructures(g,
                BEST_SUBSTRUCTURES_LIMIT);

        for (DirectedGraph<StringVertex, StringEdge> bestSubstructure : bestSubstructures) {
            System.out.println("bestSubstructure: " + bestSubstructure);

            List<DirectedGraph<StringVertex, StringEdge>> bestSubstructureInstances = InstanceFinder.findInstances(g, bestSubstructure);
            List<DirectedGraph<StringVertex, StringEdge>> includedSubstructures = IncludedSubstructuresFinder.includedSubstructures
                    (bestSubstructure);

            List<DirectedGraph<StringVertex, StringEdge>> instances = new LinkedList<>();
            for (DirectedGraph<StringVertex, StringEdge> includedSubstructure : includedSubstructures) {
                instances.addAll(InstanceFinder.findInstances(g, includedSubstructure));
            }

            for (DirectedGraph<StringVertex, StringEdge> instance : instances) {
                System.out.println("instance: " + instance);
                if(!Utils.containsSubstructure(bestSubstructureInstances, instance)) {
                    int frequency = InstanceFinder.findInstances(g, instance).size() - bestSubstructureInstances.size();
                    int cost = TransformationCostCalculator.subgraphTransformationCost(instance, bestSubstructure);

                    int anomalyValue = frequency * cost;
                    System.out.println(anomalyValue);
                    if (anomalyValue > 0 && anomalyValue < THRESHOLD) {
                        anomalies.put(instance, anomalyValue);
                    }
                }
            }
        }

        return anomalies;
    }
}
