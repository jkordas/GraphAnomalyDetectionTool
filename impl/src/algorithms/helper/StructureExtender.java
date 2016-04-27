package algorithms.helper;

import algorithms.utils.deepCopy.DeepCopy;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by jkordas on 2016-04-27.
 */
public class StructureExtender {
    public static List<DirectedGraph<StringVertex, StringEdge>> extendStructure(Graph<StringVertex, StringEdge> g,
                                                                                Graph<StringVertex, StringEdge> s) {
        List<DirectedGraph<StringVertex, StringEdge>> extendedStructures = new LinkedList<>();
        Set<StringEdge> edges = g.edgeSet();

        for (StringEdge edge : edges) {
            StringVertex source = g.getEdgeSource(edge);
            StringVertex target = g.getEdgeTarget(edge);

            //extend with edge
            if (s.containsVertex(source) && s.containsVertex(target) && !s.containsEdge(source, target)) {
                s.addEdge(source, target, edge);
                DirectedGraph<StringVertex, StringEdge> extendedStructure = (DirectedGraph<StringVertex, StringEdge>) DeepCopy.copy(s);
                s.removeEdge(source, target);

                extendedStructures.add(extendedStructure);
            }

            //extend with vertex plus edge
            if (s.containsVertex(source) && !s.containsVertex(target)) {
                s.addVertex(target);
                s.addEdge(source, target, edge);
                DirectedGraph<StringVertex, StringEdge> extendedStructure = (DirectedGraph<StringVertex, StringEdge>) DeepCopy.copy(s);
                s.removeEdge(source, target);
                s.removeVertex(target);

                extendedStructures.add(extendedStructure);
            }
            if (!s.containsVertex(source) && s.containsVertex(target)) {
                s.addVertex(source);
                s.addEdge(source, target, edge);
                DirectedGraph<StringVertex, StringEdge> extendedStructure = (DirectedGraph<StringVertex, StringEdge>) DeepCopy.copy(s);
                s.removeEdge(source, target);
                s.removeVertex(source);

                extendedStructures.add(extendedStructure);
            }
        }

        return extendedStructures;
    }
}
