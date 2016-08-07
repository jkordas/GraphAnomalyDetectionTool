package GAD.io;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.graph.Visualisation;
import org.jgrapht.DirectedGraph;

/**
 * Created by jkordas on 14/05/16.
 */
public class Viewer {
    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> parsed = GraphReader.parse("graphModels/generated/star6.csv");
        new Visualisation(parsed).showGraph();
    }
}
