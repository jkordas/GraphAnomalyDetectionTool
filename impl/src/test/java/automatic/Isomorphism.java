package automatic;

import GAD.algorithms.Algorithms;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jkordas on 15/03/16.
 */
public class Isomorphism {

    @Test
    public void simpleTest() {
        DirectedGraph<StringVertex, StringEdge> g1 = new SimpleDirectedGraph<>(StringEdge.class);
        DirectedGraph<StringVertex, StringEdge> g2 = new SimpleDirectedGraph<>(StringEdge.class);

        StringVertex a = new StringVertex("A");
        StringVertex b = new StringVertex("B");
        g1.addVertex(a);
        g1.addVertex(b);
        g1.addEdge(a, b);

        StringVertex a2 = new StringVertex("A");
        StringVertex b2 = new StringVertex("B");
        g2.addVertex(b2);
        g2.addVertex(a2);
        StringEdge e2 = new StringEdge("");
        g2.addEdge(a2, b2, e2);


        assertEquals(true, Algorithms.getInstance().isIsomorphic(g1, g2));

        g1.addVertex(new StringVertex("C"));
        assertEquals(false, Algorithms.getInstance().isIsomorphic(g1, g2));
    }

    @Test
    public void simpleTopologyTest() {
        DirectedGraph<StringVertex, StringEdge> g1 = new SimpleDirectedGraph<>(StringEdge.class);
        DirectedGraph<StringVertex, StringEdge> g2 = new SimpleDirectedGraph<>(StringEdge.class);

        StringVertex a = new StringVertex("A");
        StringVertex b = new StringVertex("B");
        g1.addVertex(a);
        g1.addVertex(b);
        g1.addEdge(a, b);

        StringVertex a2 = new StringVertex("C");
        StringVertex b2 = new StringVertex("D");
        g2.addVertex(b2);
        g2.addVertex(a2);
        StringEdge e2 = new StringEdge("");
        g2.addEdge(a2, b2, e2);


        assertEquals(true, Algorithms.getInstance().isTopologicallyIsomorphic(g1, g2));

        g1.addVertex(new StringVertex("C"));
        assertEquals(false, Algorithms.getInstance().isTopologicallyIsomorphic(g1, g2));
    }

    //Subgraph isomorphism in jgrapht library does not work as expected for egdes
//    @Test
//    public void simpleSubgraphTest() {
//        DirectedGraph<StringVertex, StringEdge> g1 = new SimpleDirectedGraph<>(StringEdge.class);
//        DirectedGraph<StringVertex, StringEdge> g2 = new SimpleDirectedGraph<>(StringEdge.class);
//
//        StringVertex a = new StringVertex("A");
//        StringVertex b = new StringVertex("B");
//        StringVertex c = new StringVertex("C");
//        g1.addVertex(a);
//        g1.addVertex(b);
//        g1.addVertex(c);
//        g1.addEdge(a, b);
//        g1.addEdge(b, c);
//
//
//        StringVertex a2 = new StringVertex("A");
//        StringVertex b2 = new StringVertex("B");
//        StringVertex c2 = new StringVertex("C");
//        g2.addVertex(a2);
//        g2.addVertex(b2);
//        g2.addVertex(c2);
//        g2.addEdge(a2, b2);
//        g2.addEdge(b2, c2);
//        g2.addEdge(c2, a2);
//
//
//        assertEquals(true, Algorithms.getInstance().isSubgraphIsomorphic(g2, g1));
//    }

}
