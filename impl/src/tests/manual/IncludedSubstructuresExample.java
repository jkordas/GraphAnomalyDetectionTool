package tests.manual;

import algorithms.helper.IncludedSubstructuresFinder;
import graph.StringEdge;
import graph.StringVertex;
import graph.Visualisation;
import org.jgrapht.DirectedGraph;
import tests.TestUtils;

import java.util.List;

/**
 * Created by jkordas on 03/05/16.
 */
public class IncludedSubstructuresExample {
    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> g = TestUtils.createCompressSubstructure2();
        List<DirectedGraph<StringVertex, StringEdge>> substructures = IncludedSubstructuresFinder.includedSubstructures(g);

        for (DirectedGraph<StringVertex, StringEdge> substructure : substructures) {
            System.out.println(substructure);
        }
        new Visualisation(g).showGraph();
    }
}
