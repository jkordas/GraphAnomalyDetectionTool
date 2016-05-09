package GAD.algorithms.helper.defaultImpl;

import GAD.algorithms.Algorithms;
import GAD.algorithms.Anomaly;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jkordas on 12/03/16.
 */
public class Utils {

    public static int calculateDescriptionLength(DirectedGraph<StringVertex, StringEdge> graph) {
        return graph.edgeSet().size() + graph.vertexSet().size();
    }

    public static int calculateDescriptionLength(DirectedGraph<StringVertex, StringEdge> g, DirectedGraph<StringVertex, StringEdge> s) {
        DirectedGraph<StringVertex, StringEdge> compressedGraph = Algorithms.getInstance().compress(g, s);

        return calculateDescriptionLength(s) + calculateDescriptionLength(compressedGraph);
    }

    public static boolean isGraphConnected(DirectedGraph<StringVertex, StringEdge> g) {
        ConnectivityInspector<StringVertex, StringEdge> inspector = new ConnectivityInspector<>(g);
        return inspector.isGraphConnected();
    }

    public static Set<DirectedGraph<StringVertex, StringEdge>> uniqueSetByGraphIsomorphism(
            Set<DirectedGraph<StringVertex, StringEdge>> graphSet) {
        Set<DirectedGraph<StringVertex, StringEdge>> uniqueGraphSet = new HashSet<>();

        for (DirectedGraph<StringVertex, StringEdge> graph : graphSet) {
            boolean shouldBeAdded = true;
            for (DirectedGraph<StringVertex, StringEdge> uniqueGraph : uniqueGraphSet) {
                if (Algorithms.getInstance().isIsomorphic(graph, uniqueGraph)) {
                    shouldBeAdded = false;
                    break;
                }
            }

            if (shouldBeAdded) {
                uniqueGraphSet.add(graph);
            }
        }

        return uniqueGraphSet;
    }

    public static Set<DirectedGraph<StringVertex, StringEdge>> uniqueSetByGraphIsomorphism(
            List<DirectedGraph<StringVertex, StringEdge>> graph) {
        Set<DirectedGraph<StringVertex, StringEdge>> graphSet = new HashSet<>(graph);


        return uniqueSetByGraphIsomorphism(graphSet);
    }

    public static boolean containsSubstructure(List<DirectedGraph<StringVertex, StringEdge>> substructureInstances,
                                               DirectedGraph<StringVertex, StringEdge> instance) {
        for (DirectedGraph<StringVertex, StringEdge> substructureInstance : substructureInstances) {
            Set<StringVertex> intersection = new HashSet<>(substructureInstance.vertexSet()); // use the copy constructor
            intersection.retainAll(instance.vertexSet());
            if (intersection.size() > 0) {
                return true;
            }
        }
        return false;
    }

    public static Set<StringEdge> loadEdges(DirectedGraph<StringVertex, StringEdge> g, Set<StringVertex> loadedVertices) {
        Set<StringEdge> edges = new HashSet<>();
        Set<StringEdge> allEdges = g.edgeSet();

        for (StringEdge edge : allEdges) {
            StringVertex source = g.getEdgeSource(edge);
            StringVertex target = g.getEdgeTarget(edge);
            if (loadedVertices.contains(source) && loadedVertices.contains(target)) {
                edges.add(edge);
            }
        }

        return edges;
    }

    public static Set<StringVertex> loadVertices(StringVertex[] vertices, int[] arr) {
        Set<StringVertex> properVertices = new HashSet<>();
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == 1) {
                properVertices.add(vertices[i]);
            }
        }

        return properVertices;
    }

    public static void sortAnomalies(List<Anomaly> anomalies) {
        anomalies.sort((a, b) -> a.getValue() < b.getValue() ? -1 : a.getValue() == b.getValue() ? 0 : 1);
    }
}
