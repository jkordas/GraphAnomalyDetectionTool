package tests;

import algorithms.Utils;
import graph.StringEdge;
import graph.StringVertex;
import graph.Visualisation;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;

/**
 * Created by jkordas on 12/03/16.
 */
public class BestSubstructureCompress_example {
    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> g = TestUtils.createCompressGraph();


        Graph<StringVertex, StringEdge> bestSubstructure = Utils.bestSubstructure(g);
        new Visualisation(bestSubstructure).showGraph();

    }
}
