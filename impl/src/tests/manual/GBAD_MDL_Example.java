package tests.manual;

import algorithms.GBAD_MDL;
import algorithms.utils.MapUtil;
import graph.StringEdge;
import graph.StringVertex;
import io.GraphReader;
import org.jgrapht.DirectedGraph;

import java.util.List;
import java.util.Map;

/**
 * Created by jkordas on 12/03/16.
 */
public class GBAD_MDL_Example {
    public static void main(String[] args) {
//        DirectedGraph<StringVertex, StringEdge> g = TestUtils.createCompressGraph();
        DirectedGraph<StringVertex, StringEdge> g = GraphReader.parse("impl/graphModels/MDLGraph.csv");

        Map<DirectedGraph<StringVertex, StringEdge>, Integer> anomalies = GBAD_MDL.findAnomalies(g);

        List<Map.Entry<DirectedGraph<StringVertex, StringEdge>, Integer>> entries = MapUtil.sortByValue(anomalies);
        for (Map.Entry<DirectedGraph<StringVertex, StringEdge>, Integer> entry : entries) {
            System.out.println("--------");
            System.out.println(entry.getValue());
            System.out.println(entry.getKey());
        }
    }
}
