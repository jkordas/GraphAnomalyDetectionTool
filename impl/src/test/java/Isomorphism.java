import GAD.algorithms.Algorithms;
import GAD.algorithms.helper.defaultImpl.IsomorphismDetector;
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


    //TODO add topology isomorphism detector


}
