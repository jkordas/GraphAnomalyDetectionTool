package algorithms;

import algorithms.helper.Compressor;
import algorithms.helper.InstanceFinder;
import algorithms.helper.IsomorphismDetector;
import algorithms.helper.StructureExtender;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultGraphMapping;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.*;

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

    public static int transformationCost(DirectedGraph<StringVertex, StringEdge> g1, DirectedGraph<StringVertex, StringEdge> g2) {
        if(g1.vertexSet().size() != g2.vertexSet().size()){
            throw new IllegalArgumentException("Graph g1 and g2 must have the same vertex number.");
        }

        //rekurencyjnie tworz kolejne mapowania
        Map<StringVertex, StringVertex> g1ToG2 = new HashMap<>();

        //odwroc mape g1ToG2
        Map<StringVertex, StringVertex> g2ToG1 = new HashMap<>();



        DefaultGraphMapping<StringVertex, StringEdge> mapping = new DefaultGraphMapping<>(g1ToG2, g2ToG1, g1, g2);

        //oblicz koszt

        //zachowaj najmniejszy

        return 0;
    }
}
