package tests;

import algorithms.Utils;
import algorithms.helper.InstanceFinder;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DirectedSubgraph;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by jkordas on 12/03/16.
 */
public class ExtendStructureCompress_example {
    public static void main(String[] args) {
        DirectedGraph<StringVertex, StringEdge> g = TestUtils.createCompressGraph();
        DirectedGraph<StringVertex, StringEdge> s = TestUtils.createCompressSubstructure1();


        List<DirectedGraph<StringVertex, StringEdge>> instances = InstanceFinder.findInstances(g, s);
        List<DirectedGraph<StringVertex, StringEdge>> extendedStructures = Utils.extendStructure(g, instances.get(0));

        System.out.println(extendedStructures);

        //---------------------
        DirectedGraph<StringVertex, StringEdge> g2 =
                new SimpleDirectedGraph<StringVertex, StringEdge>(StringEdge.class);

        StringVertex v1 = new StringVertex("1");
        StringVertex v2 = new StringVertex("2");
        StringVertex v3 = new StringVertex("3");

        g2.addVertex(v1);
        g2.addVertex(v2);
        g2.addVertex(v3);

        StringEdge e1 = new StringEdge("");
        StringEdge e2 = new StringEdge("");
        StringEdge e3 = new StringEdge("");
        g2.addEdge(v1, v2, e1);
        g2.addEdge(v2, v3, e2);
        g2.addEdge(v3, v1, e3);

        DirectedSubgraph<StringVertex, StringEdge> sub = new DirectedSubgraph<>(g2,
                new HashSet<>(Arrays.asList(v1, v2, v3)), new HashSet<>(Arrays.asList(e1, e2)));
        List<DirectedGraph<StringVertex, StringEdge>> extendedStructures2 = Utils.extendStructure(g2, sub);

        System.out.println(extendedStructures2);
    }
}
