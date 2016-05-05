package GAD.algorithms.helper;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

/**
 * Created by jkordas on 05/05/16.
 */
public interface ICompressor {
    DirectedGraph<StringVertex, StringEdge> compress(DirectedGraph<StringVertex, StringEdge> g,
                                                     DirectedGraph<StringVertex, StringEdge> substructure);
}
