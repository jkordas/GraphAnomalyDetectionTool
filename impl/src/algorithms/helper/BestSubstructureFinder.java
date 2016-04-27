package algorithms.helper;

import algorithms.Utils;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.*;

/**
 * Created by jkordas on 2016-04-27.
 */
public class BestSubstructureFinder {
    public static Graph<StringVertex, StringEdge> bestSubstructure(DirectedGraph<StringVertex, StringEdge> graph) {
        final int MAX_SUBSTRUCTURE_SIZE = 15;
        return bestSubstructure(graph, MAX_SUBSTRUCTURE_SIZE);
    }

    public static Graph<StringVertex, StringEdge> bestSubstructure(DirectedGraph<StringVertex, StringEdge> graph, int maxSubstructureSize) {
        List<DirectedGraph<StringVertex, StringEdge>> parentList = new LinkedList<>();
        List<DirectedGraph<StringVertex, StringEdge>> childList = new LinkedList<>();
        DirectedGraph<StringVertex, StringEdge> bestSubstructure = null;
        int bestSubstructureDLValue = Integer.MAX_VALUE;

        Set<StringVertex> uniqueVertexSet = uniqueSetByVertexLabel(graph.vertexSet());
        for (StringVertex vertex : uniqueVertexSet) {
            DirectedGraph<StringVertex, StringEdge> s = new SimpleDirectedGraph<>(StringEdge.class);
            s.addVertex(vertex);
            parentList.add(s);
        }

        while(!parentList.isEmpty()) {
            System.out.println(parentList.size());
            System.out.println(bestSubstructure);
            DirectedGraph<StringVertex, StringEdge> bestChild = null;
            int bestChildDLValue = Integer.MAX_VALUE;

            while (!parentList.isEmpty()) {
                DirectedGraph<StringVertex, StringEdge> s = parentList.remove(0);
                List<DirectedGraph<StringVertex, StringEdge>> instances = InstanceFinder.findInstances(graph, s);
                for (DirectedGraph<StringVertex, StringEdge> instance : instances) {
                    List<DirectedGraph<StringVertex, StringEdge>> extendedStructure = StructureExtender.extendStructure(graph, instance);
                    childList.addAll(extendedStructure);
                }

                Set<DirectedGraph<StringVertex, StringEdge>> uniqueSet = uniqueSetByGraphIsomorphism(new HashSet<>(childList));
                childList = new LinkedList<>(uniqueSet);

                for (DirectedGraph<StringVertex, StringEdge> childInstance : childList) {
                    int descriptionLength = Utils.calculateDescriptionLength(graph, childInstance);
                    if (descriptionLength < bestSubstructureDLValue) {
                        bestSubstructure = childInstance;
                        bestSubstructureDLValue = descriptionLength;
                    }
                    if (descriptionLength < bestChildDLValue) {
                        bestChild = childInstance;
                        bestChildDLValue = descriptionLength;
                    }
                }
            }

            childList.clear();
            parentList.clear();
            if(bestChild != null && Utils.calculateDescriptionLength(bestChild) <= maxSubstructureSize) {
                parentList.add(bestChild);
            }
        }

        return bestSubstructure;
    }

    private static Set<StringVertex> uniqueSetByVertexLabel(Set<StringVertex> vertexSet) {
        Set<StringVertex> uniqueVertexSet = new TreeSet<>((v1, v2) -> {
            return v1.getLabel().compareTo(v2.getLabel());
        });

        uniqueVertexSet.addAll(vertexSet); // eliminate duplicates

        return uniqueVertexSet;
    }

    private static Set<DirectedGraph<StringVertex, StringEdge>> uniqueSetByGraphIsomorphism(
            Set<DirectedGraph<StringVertex, StringEdge>> graphSet) {
        Set<DirectedGraph<StringVertex, StringEdge>> uniqueGraphSet = new HashSet<>();

        for (DirectedGraph<StringVertex, StringEdge> graph : graphSet) {
            boolean shouldBeAdded = true;
            for (DirectedGraph<StringVertex, StringEdge> uniqueGraph : uniqueGraphSet) {
                if(IsomorphismDetector.isIsomorphic(graph, uniqueGraph)) {
                    shouldBeAdded = false;
                    break;
                }
            }

            if(shouldBeAdded) {
                uniqueGraphSet.add(graph);
            }
        }

        return uniqueGraphSet;
    }
}
