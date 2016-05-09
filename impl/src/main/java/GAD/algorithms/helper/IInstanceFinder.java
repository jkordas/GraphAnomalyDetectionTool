package GAD.algorithms.helper;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 2016-05-09.
 */
public interface IInstanceFinder {
    List<DirectedGraph<StringVertex, StringEdge>> findInstances(DirectedGraph<StringVertex, StringEdge> g,
                                                                DirectedGraph<StringVertex, StringEdge> s,
                                                                boolean exactMatch);
}
