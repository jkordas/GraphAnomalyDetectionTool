package manual.helper;

import GAD.AnomalyDetector;
import GAD.algorithms.Anomaly;
import GAD.algorithms.AnomalyType;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.io.GraphReader;
import org.jgrapht.DirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 05/05/16.
 */
public class AnomalyDetectorExample {
    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> g = GraphReader.parse("graphModels/MPSGraph.csv");

        List<Anomaly> anomalies = AnomalyDetector.findAnomalies(AnomalyType.DELETION, g);
        anomalies.sort((a, b) -> a.getValue() < b.getValue() ? -1 : a.getValue() == b.getValue() ? 0 : 1);

        for (Anomaly anomaly : anomalies) {
            System.out.println("--------");
            System.out.println(anomaly.getValue());
            System.out.println(anomaly.getStructure());
        }
    }
}
