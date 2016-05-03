package tests.manual;

import algorithms.helper.BestSubstructureFinder;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 12/03/16.
 */
public class BestSubstructureExample {
    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> g =
                new SimpleDirectedGraph<>(StringEdge.class);

        StringVertex v1 = new StringVertex("A");
        StringVertex v2 = new StringVertex("B");
        StringVertex v3 = new StringVertex("C");
        StringVertex v4 = new StringVertex("A");
        StringVertex v5 = new StringVertex("B");
        StringVertex v6 = new StringVertex("C");

//         add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);

        // add edges to create a circuit
        StringEdge e = new StringEdge("AA");

        g.addEdge(v1, v2, e);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v5);
        g.addEdge(v5, v6);
        g.addEdge(v6, v1);

        System.out.println(g);

        List<DirectedGraph<StringVertex, StringEdge>> substructures = BestSubstructureFinder.bestSubstructures(g, 3);

        for (DirectedGraph<StringVertex, StringEdge> substructure : substructures) {
            System.out.println(substructure);
        }
    }
}
