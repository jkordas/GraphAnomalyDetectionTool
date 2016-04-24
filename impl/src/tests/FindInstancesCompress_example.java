package tests;

import algorithms.Utils;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;

import java.util.List;

/**
 * Created by jkordas on 12/03/16.
 */
public class FindInstancesCompress_example {
    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> g = TestUtils.createCompressGraph();
        DirectedGraph<StringVertex, StringEdge> s = TestUtils.createCompressSubstructure2();


        List<Graph<StringVertex, StringEdge>> instances = Utils.findInstances(g, s);
        for (Graph<StringVertex, StringEdge> instance : instances) {
            System.out.println(instance);
        }

    }
}
