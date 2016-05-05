package GAD;

import GAD.algorithms.Anomaly;
import GAD.algorithms.AnomalyType;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 05/05/16.
 */
public class AnomalyDetector {
    public static List<Anomaly> findAnomalies(AnomalyType anomalyType, DirectedGraph<StringVertex, StringEdge> graph) {
        return anomalyType.findAnomalies(graph);
    }
}
