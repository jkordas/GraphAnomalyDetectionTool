package tests;

import algorithms.Utils;
import graph.StringEdge;
import graph.StringVertex;
import graph.Visualisation;
import org.jgrapht.DirectedGraph;

/**
 * Created by jkordas on 12/03/16.
 */
public class CompressCompress_example {
    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> g = TestUtils.createCompressGraph();
        DirectedGraph<StringVertex, StringEdge> s = TestUtils.createCompressSubstructure1();


        DirectedGraph<StringVertex, StringEdge> compressedGraph = Utils.compress(g, s);
        new Visualisation(compressedGraph).showGraph();

    }
}
