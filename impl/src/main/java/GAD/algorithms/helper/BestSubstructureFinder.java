package GAD.algorithms.helper;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.*;

/**
 * Created by jkordas on 2016-04-27.
 */
public class BestSubstructureFinder {
    public static DirectedGraph<StringVertex, StringEdge> bestSubstructure(DirectedGraph<StringVertex, StringEdge> graph) {
        final int MAX_SUBSTRUCTURE_SIZE = 8;
        final int SUBSTRUCTURES_LIMIT = 1;
        return bestSubstructures(graph, MAX_SUBSTRUCTURE_SIZE, SUBSTRUCTURES_LIMIT).get(0);
    }

    public static List<DirectedGraph<StringVertex, StringEdge>> bestSubstructures(DirectedGraph<StringVertex, StringEdge> graph,
                                                                                  int substructuresLimit) {
        final int MAX_SUBSTRUCTURE_SIZE = 8;
        return bestSubstructures(graph, MAX_SUBSTRUCTURE_SIZE, substructuresLimit);
    }

    public static List<DirectedGraph<StringVertex, StringEdge>> bestSubstructures(DirectedGraph<StringVertex, StringEdge> graph,
                                                                                  int maxSubstructureSize, int substructuresLimit) {
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
                List<DirectedGraph<StringVertex, StringEdge>> instances = InstanceFinder.findInstances(graph, s);
                for (DirectedGraph<StringVertex, StringEdge> instance : instances) {
                    List<DirectedGraph<StringVertex, StringEdge>> extendedStructure = StructureExtender.extendStructure(graph, instance);
                    childList.addAll(extendedStructure);
                }

                Set<DirectedGraph<StringVertex, StringEdge>> uniqueSet = Utils.uniqueSetByGraphIsomorphism(new HashSet<>(childList));
                childList = new LinkedList<>(uniqueSet);

                for (DirectedGraph<StringVertex, StringEdge> childInstance : childList) {
                    int descriptionLength = Utils.calculateDescriptionLength(graph, childInstance);
                    if (bestSubstructures.isEmpty() || descriptionLength < bestSubstructures.lastKey()) {
                        bestSubstructures.put(descriptionLength, childInstance);
                        if (bestSubstructures.size() > substructuresLimit) {
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
