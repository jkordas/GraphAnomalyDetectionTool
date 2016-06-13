package manual;

import GAD.AnomalyDetector;
import GAD.algorithms.Anomaly;
import GAD.algorithms.GBAD_MDL;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.graph.Visualisation;
import GAD.io.GraphReader;
import org.jgrapht.DirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 12/03/16.
 */
public class Demo {
    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> g = GraphReader.parse("impl/graphModels/MDLGraph.csv");

        new Thread(new Runnable() {
            @Override
            public void run() {
                new Visualisation(g).showGraph();
            }
        }).start();

        List<Anomaly> anomalies = AnomalyDetector.findAnomalies(g);

        System.out.println("RESULTS. --------------------------- Algorithms detected " + anomalies.size() + " anomalies: ----------------------------------");
        for (Anomaly anomaly : anomalies) {
            System.out.println("Anomaly type: " + anomaly.getType());
            System.out.println("Anomaly value: " + anomaly.getValue());
            System.out.println("Anomaly structure: " + anomaly.getStructure());
            System.out.println("--------");
        }
    }
}
