import GAD.algorithms.Algorithms;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import manual.TestUtils;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jkordas on 12/03/16.
 */
public class TransformationCost {

    @Test
    public void simpleTest(){
        DirectedGraph<StringVertex, StringEdge> g1 = TestUtils.createCompressSubstructure1();

        DirectedGraph<StringVertex, StringEdge> g2 =
                new SimpleDirectedGraph<StringVertex, StringEdge>(StringEdge.class);

        StringVertex vs1 = new StringVertex("1");
        StringVertex vs2 = new StringVertex("2");
        StringVertex vs3 = new StringVertex("3");

//         add the vertices
        g2.addVertex(vs1);
        g2.addVertex(vs2);
        g2.addVertex(vs3);

        // add edges to create a circuit
        g2.addEdge(vs1, vs2);
        g2.addEdge(vs2, vs3);

        assertEquals(Algorithms.getInstance().transformationCost(g1, g2), 0);

        g2.addEdge(vs1, vs3);
        assertEquals(Algorithms.getInstance().transformationCost(g1, g2), 2);

        vs1.setLabel("4");
        assertEquals(Algorithms.getInstance().transformationCost(g1, g2), 3);
    }

    @Test
    public void complexTest(){
        DirectedGraph<StringVertex, StringEdge> g1 = TestUtils.createCompressSubstructure2();


        DirectedGraph<StringVertex, StringEdge> g2 =
                new SimpleDirectedGraph<StringVertex, StringEdge>(StringEdge.class);

        StringVertex vs1 = new StringVertex("1");
        StringVertex vs2 = new StringVertex("2");
        StringVertex vs3 = new StringVertex("3");
        StringVertex vs4 = new StringVertex("4");
        StringVertex vs5 = new StringVertex("5");

//         add the vertices
        g2.addVertex(vs1);
        g2.addVertex(vs2);
        g2.addVertex(vs3);
        g2.addVertex(vs4);
        g2.addVertex(vs5);

        // add edges to create a circuit
        g2.addEdge(vs1, vs2);
        g2.addEdge(vs2, vs3);
        g2.addEdge(vs3, vs4);
        g2.addEdge(vs4, vs5);
        g2.addEdge(vs1, vs4);

        assertEquals(Algorithms.getInstance().transformationCost(g1, g2), 0);

        g2.addEdge(vs4, vs1);
        assertEquals(Algorithms.getInstance().transformationCost(g1, g2), 2);
    }
}
