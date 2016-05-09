package GAD.algorithms.helper;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;

import java.util.List;

/**
 * Created by jkordas on 2016-05-09.
 */
public interface IStructureExtender {
    List<DirectedGraph<StringVertex, StringEdge>> extendStructure(Graph<StringVertex, StringEdge> g,
                                                                  Graph<StringVertex, StringEdge> s);
}
