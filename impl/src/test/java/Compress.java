import GAD.algorithms.Algorithms;
import GAD.algorithms.helper.Utils;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import manual.TestUtils;
import org.jgrapht.DirectedGraph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jkordas on 12/03/16.
 */
public class Compress {
    @Test
    public void simpleTestCompressExample() {
        DirectedGraph<StringVertex, StringEdge> g = TestUtils.createCompressGraph();
        DirectedGraph<StringVertex, StringEdge> s1 = TestUtils.createCompressSubstructure1();
        DirectedGraph<StringVertex, StringEdge> s2 = TestUtils.createCompressSubstructure2();

        DirectedGraph<StringVertex, StringEdge> compressedGraph1 = Algorithms.getInstance().compress(g, s1);
        DirectedGraph<StringVertex, StringEdge> compressedGraph2 = Algorithms.getInstance().compress(g, s2);

        assertEquals(24, Utils.calculateDescriptionLength(compressedGraph1));//12+12
        assertEquals(17, Utils.calculateDescriptionLength(compressedGraph2));//8 + 9
    }
}
