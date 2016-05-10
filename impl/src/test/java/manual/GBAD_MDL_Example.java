package manual;

import GAD.algorithms.Anomaly;
import GAD.algorithms.GBAD_MDL;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.io.GraphReader;
import org.jgrapht.DirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 12/03/16.
 */
public class GBAD_MDL_Example {
    public static void main(String[] args) {
//        DirectedGraph<StringVertex, StringEdge> g = manual.TestUtils.createCompressGraph();
        DirectedGraph<StringVertex, StringEdge> g = GraphReader.parse("graphModels/MDLGraph.csv");

        List<Anomaly> anomalies = GBAD_MDL.getInstance().findAnomalies(g);
        anomalies.sort((a, b) -> a.getValue() < b.getValue() ? -1 : a.getValue() == b.getValue() ? 0 : 1);

        for (Anomaly anomaly : anomalies) {
            System.out.println("--------");
            System.out.println(anomaly.getValue());
            System.out.println(anomaly.getStructure());
        }
    }
}
