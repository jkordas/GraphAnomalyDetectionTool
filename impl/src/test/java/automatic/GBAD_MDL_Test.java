package automatic;

import GAD.algorithms.Anomaly;
import GAD.algorithms.GBAD_MDL;
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
public class GBAD_MDL_Test {
    @Test
    public void simpleTest() {
        DirectedGraph<StringVertex, StringEdge> g = GraphReader.parse("graphModels/MDLGraph.csv");

        List<Anomaly> anomalies = GBAD_MDL.getInstance().findAnomalies(g);
        assertEquals(3, anomalies.size());
    }
}
