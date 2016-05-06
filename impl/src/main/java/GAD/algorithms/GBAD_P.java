package GAD.algorithms;

import GAD.algorithms.helper.InstanceFinder;
import GAD.algorithms.helper.StructureExtender;
import GAD.algorithms.helper.defaultImpl.Utils;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jkordas on 12/03/16.
 */
public class GBAD_P {
    //TODO: move params to config
    private static final int THRESHOLD = 20;
    private static final int BEST_SUBSTRUCTURES_LIMIT = 1;

    public static List<Anomaly> findAnomalies(DirectedGraph<StringVertex, StringEdge> g) {
        List<Anomaly> anomalies = new LinkedList<>();
        List<DirectedGraph<StringVertex, StringEdge>> bestSubstructures = Algorithms.getInstance().bestSubstructures(g,
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
                System.out.println("extension probability: " + probability + " %");

                if (probability > 0 && probability < THRESHOLD) {
                    anomalies.add(new Anomaly(probability, extendedStructure));
                }
            }
        }

        return anomalies;
    }
}
