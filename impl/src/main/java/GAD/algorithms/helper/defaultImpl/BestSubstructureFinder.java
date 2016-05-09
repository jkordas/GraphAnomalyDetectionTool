package GAD.algorithms.helper.defaultImpl;

import GAD.Config;
import GAD.algorithms.Algorithms;
import GAD.algorithms.helper.IBestSubstructureFinder;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.*;

/**
 * Created by jkordas on 2016-04-27.
 */
public class BestSubstructureFinder implements IBestSubstructureFinder {
    public List<DirectedGraph<StringVertex, StringEdge>> bestSubstructures(DirectedGraph<StringVertex, StringEdge> graph, int limit) {
        int maxSubstructureSize = Config.getInstance().MAX_SUBSTRUCTURE_SIZE;
        List<DirectedGraph<StringVertex, StringEdge>> parentList = new LinkedList<>();
        List<DirectedGraph<StringVertex, StringEdge>> childList = new LinkedList<>();
        SortedMap<Integer, DirectedGraph<StringVertex, StringEdge>> bestSubstructures = new TreeMap<>();

        Set<StringVertex> uniqueVertexSet = uniqueSetByVertexLabel(graph.vertexSet());
        for (StringVertex vertex : uniqueVertexSet) {
            DirectedGraph<StringVertex, StringEdge> s = new SimpleDirectedGraph<>(StringEdge.class);
            s.addVertex(vertex);
            parentList.add(s);
        }

        System.out.println("Best substructure search...");
        while (!parentList.isEmpty()) {
            DirectedGraph<StringVertex, StringEdge> bestChild = null;
            int bestChildDLValue = Integer.MAX_VALUE;

            while (!parentList.isEmpty()) {
                DirectedGraph<StringVertex, StringEdge> s = parentList.remove(0);
                List<DirectedGraph<StringVertex, StringEdge>> instances = Algorithms.getInstance().findInstances
                        (graph, s);
                for (DirectedGraph<StringVertex, StringEdge> instance : instances) {
                    List<DirectedGraph<StringVertex, StringEdge>> extendedStructure = Algorithms.getInstance().extendStructure(graph,
                            instance);
                    childList.addAll(extendedStructure);
                }

                Set<DirectedGraph<StringVertex, StringEdge>> uniqueSet = Utils.uniqueSetByGraphIsomorphism(new HashSet<>(childList));
                childList = new LinkedList<>(uniqueSet);

                for (DirectedGraph<StringVertex, StringEdge> childInstance : childList) {
                    int descriptionLength = Utils.calculateDescriptionLength(graph, childInstance);
                    if (bestSubstructures.isEmpty() || descriptionLength < bestSubstructures.lastKey()) {
                        bestSubstructures.put(descriptionLength, childInstance);
                        if (bestSubstructures.size() > limit) {
                            bestSubstructures.remove(bestSubstructures.lastKey());
                        }
                    }
                    if (descriptionLength < bestChildDLValue) {
                        bestChild = childInstance;
                        bestChildDLValue = descriptionLength;
                    }
                }
            }

            childList.clear();
            parentList.clear();
            System.out.println("Current substructure for extension size: " + Utils.calculateDescriptionLength(bestChild));
            if (bestChild != null && Utils.calculateDescriptionLength(bestChild) <= maxSubstructureSize) {
                parentList.add(bestChild);
            }
        }

        return new ArrayList<>(bestSubstructures.values());
    }

    private static Set<StringVertex> uniqueSetByVertexLabel(Set<StringVertex> vertexSet) {
        Set<StringVertex> uniqueVertexSet = new TreeSet<>((v1, v2) -> {
            return v1.getLabel().compareTo(v2.getLabel());
        });

        uniqueVertexSet.addAll(vertexSet); // eliminate duplicates

        return uniqueVertexSet;
    }

}
