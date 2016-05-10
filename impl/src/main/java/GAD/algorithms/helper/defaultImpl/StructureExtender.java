package GAD.algorithms.helper.defaultImpl;

import GAD.algorithms.helper.IStructureExtender;
import GAD.algorithms.utils.PreparedStringEdge;
import GAD.algorithms.utils.deepCopy.DeepCopy;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by jkordas on 2016-04-27.
 */
public class StructureExtender implements IStructureExtender {
    @Override
    public List<DirectedGraph<StringVertex, StringEdge>> extendStructure(Graph<StringVertex, StringEdge> g,
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
                Set<PreparedStringEdge> absentEdges = getAbsentEdges(g, s);
                addAbsentEdges(s, absentEdges);

                DirectedGraph<StringVertex, StringEdge> extendedStructure = (DirectedGraph<StringVertex, StringEdge>) DeepCopy.copy(s);

                removeAbsentEdges(s, absentEdges);
                s.removeEdge(source, target);
                s.removeVertex(target);

                extendedStructures.add(extendedStructure);
            }
            if (!s.containsVertex(source) && s.containsVertex(target)) {
                s.addVertex(source);
                s.addEdge(source, target, edge);
                Set<PreparedStringEdge> absentEdges = getAbsentEdges(g, s);
                addAbsentEdges(s, absentEdges);

                DirectedGraph<StringVertex, StringEdge> extendedStructure = (DirectedGraph<StringVertex, StringEdge>) DeepCopy.copy(s);

                removeAbsentEdges(s, absentEdges);
                s.removeEdge(source, target);
                s.removeVertex(source);

                extendedStructures.add(extendedStructure);
            }
        }

        return extendedStructures;
    }

    private void removeAbsentEdges(Graph<StringVertex, StringEdge> s, Set<PreparedStringEdge> absentEdges) {
        for (PreparedStringEdge absentEdge : absentEdges) {
            s.removeEdge(absentEdge.getSource(), absentEdge.getTarget());
        }
    }

    private void addAbsentEdges(Graph<StringVertex, StringEdge> s, Set<PreparedStringEdge> absentEdges) {
        for (PreparedStringEdge absentEdge : absentEdges) {
            s.addEdge(absentEdge.getSource(), absentEdge.getTarget(), absentEdge.getEdge());
        }
    }

    private Set<PreparedStringEdge> getAbsentEdges(Graph<StringVertex, StringEdge> g, Graph<StringVertex, StringEdge> s) {
        Set<PreparedStringEdge> absentEdges = new HashSet<>();
        Set<StringVertex> sVertexSet = s.vertexSet();

        for (StringVertex vertex : sVertexSet) {
            Set<StringEdge> vertexEdges = g.edgesOf(vertex);
            for (StringEdge edge : vertexEdges) {
                StringVertex source = g.getEdgeSource(edge);
                StringVertex target = g.getEdgeTarget(edge);

                if(s.containsVertex(source) && s.containsVertex(target) && !s.containsEdge(source, target)){
                    absentEdges.add(new PreparedStringEdge(source, target, edge));
                }
            }
        }
        //NOTE: absentEdges may contain duplicates, but it should not break anything

        return absentEdges;
    }
}
