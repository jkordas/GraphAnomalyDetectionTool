package algorithms;

import algorithms.helper.InstanceFinder;
import algorithms.helper.IsomorphismDetector;
import algorithms.utils.PreparedStringEdge;
import algorithms.utils.deepCopy.DeepCopy;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.*;

/**
 * Created by jkordas on 12/03/16.
 */
public class Utils {
    public static Graph<StringVertex, StringEdge> bestSubstructure(DirectedGraph<StringVertex, StringEdge> graph) {
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
                System.out.println("vertex: " + s.vertexSet().size());
                List<DirectedGraph<StringVertex, StringEdge>> instances = InstanceFinder.findInstances(graph, s);
                for (DirectedGraph<StringVertex, StringEdge> instance : instances) {
                    List<DirectedGraph<StringVertex, StringEdge>> extendedStructure = extendStructure(graph, instance);
                    childList.addAll(extendedStructure);
                }

                Set<DirectedGraph<StringVertex, StringEdge>> uniqueSet = uniqueSetByGraphIsomorphism(new HashSet<>(childList));
                childList = new LinkedList<>(uniqueSet);

                for (DirectedGraph<StringVertex, StringEdge> childInstance : childList) {
                    int descriptionLength = calculateDescriptionLength(graph, childInstance);
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
            if(bestChild != null) {
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

    public static int calculateDescriptionLength(Graph<StringVertex, StringEdge> graph) {
        return graph.edgeSet().size() + graph.vertexSet().size();
    }

    public static int calculateDescriptionLength(DirectedGraph<StringVertex, StringEdge> g, DirectedGraph<StringVertex, StringEdge> s) {
        DirectedGraph<StringVertex, StringEdge> compressedGraph = compress(g, s);

        return calculateDescriptionLength(s) + calculateDescriptionLength(compressedGraph);
    }

    public static DirectedGraph<StringVertex, StringEdge> compress(DirectedGraph<StringVertex, StringEdge> g,
                                                                   DirectedGraph<StringVertex, StringEdge> substructure) {
        DirectedGraph<StringVertex, StringEdge> compressedGraph = (DirectedGraph<StringVertex, StringEdge>) DeepCopy.copy(g);
        List<DirectedGraph<StringVertex, StringEdge>> instances = InstanceFinder.findInstances(compressedGraph, substructure);
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
