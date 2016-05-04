import algorithms.helper.InstanceFinder;
import graph.StringEdge;
import graph.StringVertex;
import manual.TestUtils;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jkordas on 12/03/16.
 */
public class FindInstances {

    @Test
    public void simpleTestCompressExample() {
        DirectedGraph<StringVertex, StringEdge> g = TestUtils.createCompressGraph();
        DirectedGraph<StringVertex, StringEdge> s1 = TestUtils.createCompressSubstructure1();
        DirectedGraph<StringVertex, StringEdge> s2 = TestUtils.createCompressSubstructure2();


        List<DirectedGraph<StringVertex, StringEdge>> instances1 = InstanceFinder.findInstances(g, s1);
        List<DirectedGraph<StringVertex, StringEdge>> instances2 = InstanceFinder.findInstances(g, s2);


        assertEquals(4, instances1.size());
        assertEquals(3, instances2.size());
    }

    @Test
    public void topologyInstanceMatch() {
        DirectedGraph<StringVertex, StringEdge> g = new SimpleDirectedGraph<>(StringEdge.class);

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
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v6);
        g.addEdge(v5, v6);
        g.addEdge(v4, v5);
        g.addEdge(v4, v1);

        DirectedGraph<StringVertex, StringEdge> s = new SimpleDirectedGraph<>(StringEdge.class);

        StringVertex vs1 = new StringVertex("A");
        StringVertex vs2 = new StringVertex("B");
        StringVertex vs3 = new StringVertex("C");

//         add the vertices
        s.addVertex(vs1);
        s.addVertex(vs2);
        s.addVertex(vs3);

        // add edges to create a circuit
        s.addEdge(vs1, vs2);
        s.addEdge(vs2, vs3);

        List<DirectedGraph<StringVertex, StringEdge>> instances1 = InstanceFinder.findInstances(g, s);
        assertEquals(2, instances1.size());

        vs1.setLabel("F");
        vs2.setLabel("G");
        vs3.setLabel("H");
        List<DirectedGraph<StringVertex, StringEdge>> instances2 = InstanceFinder.findInstances(g, s);
        List<DirectedGraph<StringVertex, StringEdge>> instancesInexact = InstanceFinder.findInstances(g, s, false);

        assertEquals(0, instances2.size());
        assertEquals(4, instancesInexact.size());

    }
}
