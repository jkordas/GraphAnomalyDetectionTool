package GAD.algorithms.helper;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

/**
 * Created by jkordas on 2016-05-09.
 */
public interface ITransformationCostCalculator {
    int transformationCost(DirectedGraph<StringVertex, StringEdge> g1, DirectedGraph<StringVertex, StringEdge> g2);

    int subgraphTransformationCost(DirectedGraph<StringVertex, StringEdge> s, DirectedGraph<StringVertex, StringEdge> g);
}
