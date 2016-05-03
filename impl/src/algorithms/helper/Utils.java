package algorithms.helper;

import algorithms.utils.CombinationGenerator;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.HashSet;
import java.util.LinkedList;
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
        DirectedGraph<StringVertex, StringEdge> compressedGraph = Compressor.compress(g, s);

        return calculateDescriptionLength(s) + calculateDescriptionLength(compressedGraph);
    }

    static List<DirectedGraph<StringVertex, StringEdge>> includedSubstructures(DirectedGraph<StringVertex, StringEdge> s){
        List<DirectedGraph<StringVertex, StringEdge>> includedSubstructures = new LinkedList<>();
        int n = s.vertexSet().size();

        for(int k = n - 1; k > 0; --k) {
            CombinationGenerator generator = new CombinationGenerator(n, k);
            while (generator.hasNext()){
                //TODO
            }
        }

        return includedSubstructures;
    }

    static Set<DirectedGraph<StringVertex, StringEdge>> uniqueSetByGraphIsomorphism(
            Set<DirectedGraph<StringVertex, StringEdge>> graphSet) {
        Set<DirectedGraph<StringVertex, StringEdge>> uniqueGraphSet = new HashSet<>();

        for (DirectedGraph<StringVertex, StringEdge> graph : graphSet) {
            boolean shouldBeAdded = true;
            for (DirectedGraph<StringVertex, StringEdge> uniqueGraph : uniqueGraphSet) {
                if (IsomorphismDetector.isIsomorphic(graph, uniqueGraph)) {
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

    static Set<StringEdge> loadEdges(DirectedGraph<StringVertex, StringEdge> g, Set<StringVertex> loadedVertices) {
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

    static Set<StringVertex> loadVertices(StringVertex[] vertices, int[] arr) {
        Set<StringVertex> properVertices = new HashSet<>();
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == 1) {
                properVertices.add(vertices[i]);
            }
        }

        return properVertices;
    }
}
