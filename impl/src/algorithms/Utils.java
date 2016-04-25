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
        Set<StringVertex> uniqueVertexSet = uniqueSetByVertexLabel(graph.vertexSet());
        List<DirectedGraph<StringVertex, StringEdge>> bestSubstructures = new ArrayList<>();

        for (StringVertex vertex : uniqueVertexSet) {
            DirectedGraph<StringVertex, StringEdge> s = new SimpleDirectedGraph<>(StringEdge.class);
            s.addVertex(vertex);

            List<DirectedGraph<StringVertex, StringEdge>> instances = InstanceFinder.findInstances(graph, s);
            for (DirectedGraph<StringVertex, StringEdge> instance : instances) {
                List<DirectedGraph<StringVertex, StringEdge>> extendedStructure = extendStructure(graph, instance);
                bestSubstructures.addAll(extendedStructure);
            }
        }
        System.out.println(Arrays.toString(bestSubstructures.toArray()));
        System.out.println(Arrays.toString(uniqueSetByGraphIsomorphism(new HashSet<>(bestSubstructures)).toArray()));


        //TODO
        return null;
    }

    private static Set<StringVertex> uniqueSetByVertexLabel(Set<StringVertex> vertexSet) {
        Set<StringVertex> uniqueVertexSet = new TreeSet<>(new Comparator<StringVertex>() {
            public int compare(StringVertex v1, StringVertex v2) {
                return v1.getLabel().compareTo(v2.getLabel());
            }
        });

        uniqueVertexSet.addAll(vertexSet); // eliminate duplicates

        return uniqueVertexSet;
    }

    private static Set<DirectedGraph<StringVertex, StringEdge>> uniqueSetByGraphIsomorphism(
            Set<DirectedGraph<StringVertex, StringEdge>> graphSet) {
        Set<DirectedGraph<StringVertex, StringEdge>> uniqueGraphSet = new TreeSet<>((g1, g2) -> {
            System.out.println("-------------");
            System.out.println(g1);
            System.out.println(g2);
            if (IsomorphismDetector.isIsomorphic(g1, g2)) {
                System.out.println("isomorphic");
                return 0;
            }

            return g1.hashCode() - g2.hashCode();
        });

        for (DirectedGraph<StringVertex, StringEdge> graph : graphSet) {
            boolean shouldBeAdded = true;
            //TODO

            uniqueGraphSet.add(graph);
            System.out.println(Arrays.toString(uniqueGraphSet.toArray()));

        }
//        uniqueGraphSet.addAll(graphSet); // eliminate duplicates

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
