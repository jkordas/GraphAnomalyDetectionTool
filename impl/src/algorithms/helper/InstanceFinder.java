package algorithms.helper;

import algorithms.utils.Generator;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DirectedSubgraph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by jkordas on 24/04/16.
 */
public class InstanceFinder {
    public static List<DirectedGraph<StringVertex, StringEdge>> findInstances(DirectedGraph<StringVertex, StringEdge> g,
                                                                      DirectedGraph<StringVertex, StringEdge> substructure) {
        List<DirectedGraph<StringVertex, StringEdge>> instanceList = new LinkedList<>();
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
}
