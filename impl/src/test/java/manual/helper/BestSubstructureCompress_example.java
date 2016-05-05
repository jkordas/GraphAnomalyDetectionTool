package manual.helper;

import GAD.algorithms.Algorithms;
import GAD.algorithms.helper.Utils;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.io.GraphReader;
import org.jgrapht.DirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 12/03/16.
 */
public class BestSubstructureCompress_example {
    public static void main(String[] args) {
//        DirectedGraph<StringVertex, StringEdge> g = manual.TestUtils.createCompressGraph();
        DirectedGraph<StringVertex, StringEdge> g = GraphReader.parse("impl/graphModels/MDLGraph.csv");

        List<DirectedGraph<StringVertex, StringEdge>> substructures = Algorithms.getInstance().bestSubstructures(g, 3);

        for (DirectedGraph<StringVertex, StringEdge> substructure : substructures) {
            System.out.println(substructure);
            System.out.println(Utils.calculateDescriptionLength(g, substructure));
        }

    }
}
