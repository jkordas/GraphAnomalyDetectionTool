package GAD.generate;

import GAD.AnomalyDetector;
import GAD.algorithms.Anomaly;
import GAD.algorithms.AnomalyType;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.graph.Visualisation;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.VertexFactory;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 2016-05-09.
 */
public class CompleteGraph {
    private static CompleteGraphGenerator<StringVertex, StringEdge> completeGraphGenerator = new CompleteGraphGenerator<>(5);
    private static int counter = 0;

    public static void main(String[] args) {


        DirectedGraph<StringVertex, StringEdge> target1 = new SimpleDirectedGraph<>(StringEdge.class);
        DirectedGraph<StringVertex, StringEdge> target2 = new SimpleDirectedGraph<>(StringEdge.class);
        DirectedGraph<StringVertex, StringEdge> target3 = new SimpleDirectedGraph<>(StringEdge.class);
        DirectedGraph<StringVertex, StringEdge> target4 = new SimpleDirectedGraph<>(StringEdge.class);

        VertexFactory<StringVertex> vertexFactory = new VertexFactory<StringVertex>() {

            @Override
            public StringVertex createVertex() {
                return new StringVertex(++counter + "");
            }
        };

        completeGraphGenerator.generateGraph(target1, vertexFactory, null);
        counter = 0;
        completeGraphGenerator.generateGraph(target2, vertexFactory, null);
        counter = 0;
        completeGraphGenerator.generateGraph(target3, vertexFactory, null);
        counter = 0;
        completeGraphGenerator.generateGraph(target4, vertexFactory, null);

        StringVertex source = target1.vertexSet().iterator().next();
        source.setLabel("A");
        StringVertex t2 = target2.vertexSet().iterator().next();
        StringVertex t3 = target3.vertexSet().iterator().next();
        StringVertex t4 = target4.vertexSet().iterator().next();
        Graphs.addGraph(target1, target2);
        Graphs.addGraph(target1, target3);
        Graphs.addGraph(target1, target4);
        target1.addEdge(source, t2);
        target1.addEdge(source, t3);
        target1.addEdge(source, t4);


        List<Anomaly> anomalies = AnomalyDetector.findAnomalies(AnomalyType.MODIFICATION, target1);
        for (Anomaly anomaly : anomalies) {
            System.out.println(anomaly);
        }
        new Visualisation(target1).showGraph();
    }
}
