package automatic;

import GAD.AnomalyDetector;
import GAD.algorithms.Anomaly;
import GAD.algorithms.AnomalyType;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.io.GraphReader;
import org.jgrapht.DirectedGraph;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jkordas on 12/03/16.
 */
public class GBAD_P_Test {
    @Test
    public void simpleTest() {
        DirectedGraph<StringVertex, StringEdge> g = GraphReader.parse("graphModels/PGraphPublication.csv");

        List<Anomaly> anomalies = AnomalyDetector.findAnomalies(AnomalyType.ADDITION, g);
        assertEquals(6, anomalies.size());
    }
}
