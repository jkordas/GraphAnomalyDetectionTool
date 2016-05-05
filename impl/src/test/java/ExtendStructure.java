import GAD.algorithms.helper.InstanceFinder;
import GAD.algorithms.helper.StructureExtender;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import manual.TestUtils;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DirectedSubgraph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jkordas on 12/03/16.
 */
public class ExtendStructure {

    @Test
    public void simpleTestCompressExample() {
        DirectedGraph<StringVertex, StringEdge> g = TestUtils.createCompressGraph();
        DirectedGraph<StringVertex, StringEdge> s = TestUtils.createCompressSubstructure1();

        int extendedSubstructuresNumber = 0;
        List<DirectedGraph<StringVertex, StringEdge>> instances = InstanceFinder.findInstances(g, s);
        for (DirectedGraph<StringVertex, StringEdge> instance : instances) {

            List<DirectedGraph<StringVertex, StringEdge>> extendedStructures = StructureExtender.extendStructure(g, instance);
            extendedSubstructuresNumber += extendedStructures.size();
        }


        assertEquals(13, extendedSubstructuresNumber);
    }

    @Test
    public void edgeTest() {
        DirectedGraph<StringVertex, StringEdge> g2 = new SimpleDirectedGraph<>(StringEdge.class);

        StringVertex v1 = new StringVertex("1");
        StringVertex v2 = new StringVertex("2");
        StringVertex v3 = new StringVertex("3");

        g2.addVertex(v1);
        g2.addVertex(v2);
        g2.addVertex(v3);

        StringEdge e1 = new StringEdge("");
        StringEdge e2 = new StringEdge("");
        StringEdge e3 = new StringEdge("");
        g2.addEdge(v1, v2, e1);
        g2.addEdge(v2, v3, e2);
        g2.addEdge(v3, v1, e3);

        DirectedSubgraph<StringVertex, StringEdge> sub = new DirectedSubgraph<>(g2, new HashSet<>(Arrays.asList(v1, v2, v3)),
                new HashSet<>(Arrays.asList(e1, e2)));
        List<DirectedGraph<StringVertex, StringEdge>> extendedStructures2 = StructureExtender.extendStructure(g2, sub);

        assertEquals(3, extendedStructures2.get(0).edgeSet().size());
    }
}
