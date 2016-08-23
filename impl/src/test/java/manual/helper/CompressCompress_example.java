package manual.helper;

import GAD.algorithms.Algorithms;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.io.Visualisation;
import manual.TestUtils;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;

/**
 * Created by jkordas on 12/03/16.
 */
public class CompressCompress_example {
    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> g = TestUtils.createCompressGraph();
        DirectedGraph<StringVertex, StringEdge> s = TestUtils.createCompressSubstructure1();


//        DirectedGraph<StringVertex, StringEdge> compressedGraph = Compressor.compress(g, s);

        DirectedGraph<StringVertex, StringEdge> s2 = new SimpleDirectedGraph<>(StringEdge.class);

        StringVertex vs1 = new StringVertex("1");
        StringVertex vs2 = new StringVertex("2");
        StringVertex vs3 = new StringVertex("3");
        StringVertex vs4 = new StringVertex("4");

//         add the vertices
        s2.addVertex(vs1);
        s2.addVertex(vs2);
        s2.addVertex(vs3);
        s2.addVertex(vs4);

        // add edges to create a circuit
        s2.addEdge(vs1, vs2);
        s2.addEdge(vs2, vs3);
        s2.addEdge(vs3, vs4);

        DirectedGraph<StringVertex, StringEdge> compressedGraph2 = Algorithms.getInstance().compress(g, s2);


        new Visualisation(compressedGraph2).showGraph();

    }
}
