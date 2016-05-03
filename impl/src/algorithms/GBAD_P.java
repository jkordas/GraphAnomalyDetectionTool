package algorithms;

import algorithms.helper.BestSubstructureFinder;
import algorithms.helper.InstanceFinder;
import algorithms.helper.StructureExtender;
import algorithms.helper.Utils;
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
public class GBAD_P {
    //TODO: move params to config
    private static final int THRESHOLD = 20;
    private static final int BEST_SUBSTRUCTURES_LIMIT = 1;

    public static Map<DirectedGraph<StringVertex, StringEdge>, Integer> findAnomalies(DirectedGraph<StringVertex, StringEdge> g) {
        Map<DirectedGraph<StringVertex, StringEdge>, Integer> anomalies = new HashMap<>();
        List<DirectedGraph<StringVertex, StringEdge>> bestSubstructures = BestSubstructureFinder.bestSubstructures(g,
                BEST_SUBSTRUCTURES_LIMIT);

        for (DirectedGraph<StringVertex, StringEdge> bestSubstructure : bestSubstructures) {
            System.out.println("bestSubstructure: " + bestSubstructure);

            List<DirectedGraph<StringVertex, StringEdge>> extendedStructures = new LinkedList<>();
            List<DirectedGraph<StringVertex, StringEdge>> instances = InstanceFinder.findInstances(g, bestSubstructure);
            for (DirectedGraph<StringVertex, StringEdge> instance : instances) {
                extendedStructures.addAll(StructureExtender.extendStructure(g, instance));
            }
            extendedStructures = new LinkedList<>(Utils.uniqueSetByGraphIsomorphism(extendedStructures));

            for (DirectedGraph<StringVertex, StringEdge> extendedStructure : extendedStructures) {
                System.out.println("extendedStructure: " + extendedStructure);
                float frequency = InstanceFinder.findInstances(g, extendedStructure).size();
                int probability = (int) (frequency / extendedStructures.size() * 100);
                System.out.println(probability);

                if (probability > 0 && probability < THRESHOLD) {
                    anomalies.put(extendedStructure, probability);
                }
            }
        }

        return anomalies;
    }
}
