package manual;

import GAD.algorithms.Anomaly;
import GAD.algorithms.GBAD_P;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.graph.Visualisation;
import GAD.io.GraphReader;
import org.jgrapht.DirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 12/03/16.
 */
public class GBAD_P_Example {
    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> g = GraphReader.parse("graphModels/PGraphVertices.csv");
        new Visualisation(g).showGraph();

        List<Anomaly> anomalies = GBAD_P.getInstance().findAnomalies(g);

        for (Anomaly anomaly : anomalies) {
            System.out.println("--------");
            System.out.println(anomaly);
        }
    }
}
