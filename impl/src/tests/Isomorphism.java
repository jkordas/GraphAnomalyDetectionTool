package tests;

import algorithms.utils.IsomorphismDetector;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jkordas on 15/03/16.
 */
public class Isomorphism {
    private IsomorphismDetector detector = new IsomorphismDetector();


    @Test
    public void simpleTest() {
        Graph<StringVertex, StringEdge> g1 = new SimpleGraph<StringVertex, StringEdge>(StringEdge.class);
        Graph<StringVertex, StringEdge> g2 = new SimpleGraph<StringVertex, StringEdge>(StringEdge.class);

        StringVertex a = new StringVertex("A");
        StringVertex b = new StringVertex("B");
        g1.addVertex(a);
        g1.addVertex(b);
        g1.addEdge(a, b);

        StringVertex a2 = new StringVertex("A");
        StringVertex b2 = new StringVertex("B");
        g2.addVertex(a2);
        g2.addVertex(b2);
        StringEdge e2 = new StringEdge("");
        g2.addEdge(a2, b2, e2);


        assertEquals(detector.isIsomorphic(g1, g2), true);

        g1.addVertex(new StringVertex("C"));
        assertEquals(detector.isIsomorphic(g1, g2), false);
    }
}
