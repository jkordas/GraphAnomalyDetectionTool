package GAD.algorithms.helper;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.Graph;

/**
 * Created by jkordas on 2016-05-09.
 */
public interface IIsomorphismDetector {
    boolean isIsomorphic(Graph<StringVertex, StringEdge> g1, Graph<StringVertex, StringEdge> g2);

    boolean isTopologicallyIsomorphic(Graph<StringVertex, StringEdge> g1, Graph<StringVertex, StringEdge> g2);

    boolean isSubgraphIsomorphic(Graph<StringVertex, StringEdge> g, Graph<StringVertex, StringEdge> s);
}
