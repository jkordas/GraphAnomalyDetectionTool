package automatic;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jkordas on 02/05/16.
 */
public class GraphReader {
    @Test
    public void simpleTest() {
        DirectedGraph<StringVertex, StringEdge> graph = GAD.io.GraphReader.parse("graphModels/testGraph.csv");
        assertEquals(3, graph.vertexSet().size());
        assertEquals(2, graph.edgeSet().size());
    }
}
