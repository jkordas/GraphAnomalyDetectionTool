package manual.helper;

import GAD.algorithms.Algorithms;
import GAD.algorithms.helper.defaultImpl.IncludedSubstructuresFinder;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.graph.Visualisation;
import manual.TestUtils;
import org.jgrapht.DirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 03/05/16.
 */
public class IncludedSubstructuresExample {
    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> g = TestUtils.createCompressSubstructure2();
        List<DirectedGraph<StringVertex, StringEdge>> substructures = Algorithms.getInstance().includedSubstructures(g);

        for (DirectedGraph<StringVertex, StringEdge> substructure : substructures) {
            System.out.println(substructure);
        }
        new Visualisation(g).showGraph();
    }
}
