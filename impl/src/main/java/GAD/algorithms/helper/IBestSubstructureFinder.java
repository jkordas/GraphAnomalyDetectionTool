package GAD.algorithms.helper;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 05/05/16.
 */
public interface IBestSubstructureFinder {
    List<DirectedGraph<StringVertex, StringEdge>> bestSubstructures(DirectedGraph<StringVertex, StringEdge> graph, int limit);
}
