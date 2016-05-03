package tests.manual;

import algorithms.helper.IsomorphismDetector;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;

/**
 * Created by jkordas on 15/03/16.
 */
public class IsomorphismExample {

    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> g1 = new SimpleDirectedGraph<>(StringEdge.class);
        DirectedGraph<StringVertex, StringEdge> g2 = new SimpleDirectedGraph<>(StringEdge.class);

        StringVertex a = new StringVertex("A");
        StringVertex b = new StringVertex("B");
        StringVertex c = new StringVertex("C");
        g1.addVertex(a);
        g1.addVertex(b);
        g1.addVertex(c);

        g1.addEdge(a, b);
        g1.addEdge(b, c);

        StringVertex a2 = new StringVertex("A");
        StringVertex b2 = new StringVertex("B");
        StringVertex c2 = new StringVertex("C");
        g2.addVertex(a2);
        g2.addVertex(b2);
        g2.addVertex(c2);
        g2.addEdge(a2, b2);
        g2.addEdge(b2, c2);
        g2.addEdge(c2, a2);

        IsomorphismDetector.isIsomorphic(g1, g2);
    }

}
