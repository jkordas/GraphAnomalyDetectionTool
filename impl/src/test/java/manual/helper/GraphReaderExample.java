package manual.helper;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.io.Visualisation;
import GAD.io.GraphReader;
import org.jgrapht.DirectedGraph;

/**
 * Created by jkordas on 02/05/16.
 */
public class GraphReaderExample {
    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> graph = GraphReader.parse("impl/graphModels/MDLGraph.csv");
        System.out.println(graph);
        new Visualisation(graph).showGraph();
    }
}
