package GAD.algorithms.helper;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 2016-05-06.
 */
public interface IIncludedSubstructuresFinder {
    List<DirectedGraph<StringVertex, StringEdge>> includedSubstructures(DirectedGraph<StringVertex, StringEdge> s);
}
