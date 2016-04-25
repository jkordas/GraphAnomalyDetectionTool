package tests;

import algorithms.helper.InstanceFinder;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 12/03/16.
 */
public class FindInstancesCompress_example {
    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> g = TestUtils.createCompressGraph();
        DirectedGraph<StringVertex, StringEdge> s = TestUtils.createCompressSubstructure2();


        List<DirectedGraph<StringVertex, StringEdge>> instances = InstanceFinder.findInstances(g, s);
        for (DirectedGraph<StringVertex, StringEdge> instance : instances) {
            System.out.println(instance);
        }

    }
}
