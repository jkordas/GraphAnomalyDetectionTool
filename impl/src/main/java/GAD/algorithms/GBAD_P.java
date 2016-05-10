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
public class GBAD_P extends GBAD_Algorithm {
    private static final int THRESHOLD = Config.getInstance().GBAD_P_THRESHOLD;

    private static GBAD_P ourInstance = new GBAD_P();

    public static GBAD_P getInstance() {
        return ourInstance;
    }

    private GBAD_P() {
    }

    @Override
    List<Anomaly> findAnomaliesForSubstructure(DirectedGraph<StringVertex, StringEdge> g,
                                               DirectedGraph<StringVertex, StringEdge> bestSubstructure) {
        List<Anomaly> anomalies = new LinkedList<>();

        List<DirectedGraph<StringVertex, StringEdge>> extendedStructures = new LinkedList<>();
        List<DirectedGraph<StringVertex, StringEdge>> instances = Algorithms.getInstance().findInstances(g, bestSubstructure);
        for (DirectedGraph<StringVertex, StringEdge> instance : instances) {
            extendedStructures.addAll(Algorithms.getInstance().extendStructure(g, instance));
        }
        extendedStructures = new LinkedList<>(Utils.uniqueSetByGraphIsomorphism(extendedStructures));

        for (DirectedGraph<StringVertex, StringEdge> extendedStructure : extendedStructures) {
            System.out.println("extendedStructure: " + extendedStructure);
            float frequency = Algorithms.getInstance().findInstances(g, extendedStructure).size();
            int probability = (int) (frequency / extendedStructures.size() * 100);
            System.out.println("extension probability: " + probability + " %");

            if (probability > 0 && probability < THRESHOLD) {
                anomalies.add(new Anomaly(probability, extendedStructure));
            }
        }

        return anomalies;
    }
}
