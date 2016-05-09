package GAD.algorithms.helper.defaultImpl;

import GAD.algorithms.Algorithms;
import GAD.algorithms.helper.ICompressor;
import GAD.algorithms.utils.PreparedStringEdge;
import GAD.algorithms.utils.deepCopy.DeepCopy;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by jkordas on 2016-04-27.
 */
public class Compressor implements ICompressor {
    public DirectedGraph<StringVertex, StringEdge> compress(DirectedGraph<StringVertex, StringEdge> g,
                                                                   DirectedGraph<StringVertex, StringEdge> substructure) {
        DirectedGraph<StringVertex, StringEdge> compressedGraph = (DirectedGraph<StringVertex, StringEdge>) DeepCopy.copy(g);
        List<DirectedGraph<StringVertex, StringEdge>> instances = Algorithms.getInstance().findInstances
                (compressedGraph, substructure);
        Set<StringEdge> edges = compressedGraph.edgeSet();
        List<StringEdge> edgesToRemove = new LinkedList<>();
        List<PreparedStringEdge> edgesToAdd = new LinkedList<>();

        for (DirectedGraph<StringVertex, StringEdge> instance : instances) {
            StringVertex instanceReplaceVertex = new StringVertex("");//TODO vertex label
            compressedGraph.addVertex(instanceReplaceVertex);

            for (StringEdge edge : edges) {
                StringVertex source = compressedGraph.getEdgeSource(edge);
                StringVertex target = compressedGraph.getEdgeTarget(edge);

                if (instance.containsVertex(source) && !instance.containsVertex(target)) {
                    StringEdge replaceEdge = new StringEdge(edge.getLabel());
                    edgesToAdd.add(new PreparedStringEdge(instanceReplaceVertex, target, replaceEdge));
                    edgesToRemove.add(edge);
                }
                if (instance.containsVertex(target) && !instance.containsVertex(source)) {
                    StringEdge replaceEdge = new StringEdge(edge.getLabel());
                    edgesToAdd.add(new PreparedStringEdge(source, instanceReplaceVertex, replaceEdge));
                    edgesToRemove.add(edge);
                }
                if (instance.containsVertex(target) && instance.containsVertex(source)) {
                    edgesToRemove.add(edge);
                }

                //jezeli ktoras z oczekujacych krawedzi nalezy do usuwanej instancji to trzeba podmienic wierzcholek
                // na nowy
                for (PreparedStringEdge preparedEdge : edgesToAdd) {
                    if (instance.containsVertex(preparedEdge.getSource())) {
                        preparedEdge.setSource(instanceReplaceVertex);
                    }
                    if (instance.containsVertex(preparedEdge.getTarget())) {
                        preparedEdge.setTarget(instanceReplaceVertex);
                    }
                }

            }
        }
        compressedGraph.removeAllEdges(edgesToRemove);

        for (PreparedStringEdge preparedEdge : edgesToAdd) {
            compressedGraph.addEdge(preparedEdge.getSource(), preparedEdge.getTarget(), preparedEdge.getEdge());
        }

        for (Graph<StringVertex, StringEdge> instance : instances) {
            Set<StringVertex> vertexSet = instance.vertexSet();
            compressedGraph.removeAllVertices(vertexSet);
        }

        return compressedGraph;
    }
}
