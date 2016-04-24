package algorithms;


import algorithms.utils.Generator;
import algorithms.utils.IsomorphismDetector;
import algorithms.utils.PreparedStringEdge;
import algorithms.utils.deepCopy.DeepCopy;
import graph.StringEdge;
import graph.StringVertex;
import org.jgraph.graph.DefaultEdge;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedSubgraph;

import java.util.*;

/**
 * Created by jkordas on 12/03/16.
 */
public class Utils {
    public static Graph<StringVertex, DefaultEdge> bestSubstructure(Graph<StringVertex, DefaultEdge> graph) {
        Set<StringVertex> vertexSet = graph.vertexSet();
        List<Graph<StringVertex, DefaultEdge>> bestSubstructures = new ArrayList<>();


        //TODO
        return null;
    }

    public static int calculateDescriptionLength(Graph<StringVertex, DefaultEdge> graph) {
        return graph.edgeSet().size() + graph.vertexSet().size();
    }

    public static List<Graph<StringVertex, StringEdge>> findInstances(DirectedGraph<StringVertex, StringEdge> g,
                                                                      DirectedGraph<StringVertex, StringEdge> substructure) {
        List<Graph<StringVertex, StringEdge>> instanceList = new LinkedList<>();
        Set<StringVertex> subVertices = substructure.vertexSet();
        //TODO: filter vertices properly, equals cannot be overridden
        //Subgraph<StringVertex, StringEdge, Graph<StringVertex, StringEdge>> gReduced = new Subgraph<>(g, subVertices);

        StringVertex[] gVertices = g.vertexSet().toArray(new StringVertex[g.vertexSet().size()]);
        Generator generator = new Generator(gVertices.length, subVertices.size());

        while (generator.hasNext()){
            Set<StringVertex> loadedVertices = loadVertices(gVertices, generator.next());
            Set<StringEdge> loadedEdges = loadEdges(g, loadedVertices);
            DirectedSubgraph<StringVertex, StringEdge> loadedGraph = new DirectedSubgraph<>(g,
                    loadedVertices, loadedEdges);

            if(IsomorphismDetector.isIsomorphic(loadedGraph, substructure)) {
                instanceList.add(loadedGraph);
            }
        }

        return instanceList;
    }

    private static Set<StringEdge> loadEdges(DirectedGraph<StringVertex, StringEdge> g, Set<StringVertex>
            loadedVertices) {
        Set<StringEdge> edges = new HashSet<>();
        Set<StringEdge> allEdges = g.edgeSet();

        for (StringEdge edge : allEdges) {
            StringVertex source = g.getEdgeSource(edge);
            StringVertex target = g.getEdgeTarget(edge);
            if(loadedVertices.contains(source) && loadedVertices.contains(target)){
                edges.add(edge);
            }
        }

        return edges;
    }

    private static Set<StringVertex> loadVertices(StringVertex[] vertices, int [] arr) {
        Set<StringVertex> properVertices = new HashSet<>();
        for(int i = 0; i < arr.length; ++i) {
            if(arr[i] == 1) {
                properVertices.add(vertices[i]);
            }
        }

        return properVertices;
    }

    public static DirectedGraph<StringVertex, StringEdge> compress(DirectedGraph<StringVertex, StringEdge> g,
                                                                      DirectedGraph<StringVertex, StringEdge> substructure) {
        DirectedGraph<StringVertex, StringEdge> compressedGraph = (DirectedGraph<StringVertex, StringEdge>) DeepCopy.copy(g);
        List<Graph<StringVertex, StringEdge>> instances = findInstances(compressedGraph, substructure);
        Set<StringEdge> edges = compressedGraph.edgeSet();
        List<StringEdge> edgesToRemove = new LinkedList<>();
        List<PreparedStringEdge> edgesToAdd = new LinkedList<>();

        for (Graph<StringVertex, StringEdge> instance : instances) {
            StringVertex instanceReplaceVertex = new StringVertex("");//TODO vertex label
            compressedGraph.addVertex(instanceReplaceVertex);

            for (StringEdge edge : edges) {
                StringVertex source = compressedGraph.getEdgeSource(edge);
                StringVertex target = compressedGraph.getEdgeTarget(edge);

                if(instance.containsVertex(source) && !instance.containsVertex(target)){
                    StringEdge replaceEdge = new StringEdge(edge.getLabel());
                    edgesToAdd.add(new PreparedStringEdge(instanceReplaceVertex, target, replaceEdge));
                    edgesToRemove.add(edge);
                }
                if(instance.containsVertex(target) && !instance.containsVertex(source)) {
                    StringEdge replaceEdge = new StringEdge(edge.getLabel());
                    edgesToAdd.add(new PreparedStringEdge(source, instanceReplaceVertex, replaceEdge));
                    edgesToRemove.add(edge);
                }
                if(instance.containsVertex(target) && instance.containsVertex(source)) {
                    edgesToRemove.add(edge);
                }

                //jezeli ktoras z oczekujacych krawedzi nalezy do usuwanej instancji to trzeba podmienic wierzcholek
                // na nowy

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
