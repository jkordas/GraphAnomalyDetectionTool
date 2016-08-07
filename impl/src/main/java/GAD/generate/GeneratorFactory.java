package GAD.generate;

import GAD.AnomalyDetector;
import GAD.algorithms.Anomaly;
import GAD.algorithms.AnomalyType;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.io.GraphWriter;
import org.jgrapht.DirectedGraph;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.generate.StarGraphGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jkordas on 14/05/16.
 */
public class GeneratorFactory {
    public static Generator createGenerator(int substructuresNumber, GraphGenerator<StringVertex, StringEdge, StringVertex> graphGenerator, Map<Integer,
            Integer> verticesMap, Map<Integer, Integer> edgesMap, int randomVertices) {

        return new Generator(substructuresNumber, graphGenerator, verticesMap, edgesMap, randomVertices);
    }

    public static Generator createCustomStarGenerator(int structureSize) {
        StarGraphGenerator<StringVertex, StringEdge> graphGenerator = new StarGraphGenerator<>(structureSize);
        HashMap<Integer, Integer> vModifyMap = new HashMap<>();
        vModifyMap.put(0, 1);
        vModifyMap.put(2, 2);

        HashMap<Integer, Integer> eModifyMap = new HashMap<>();
        eModifyMap.put(0, 1);

        return createGenerator(5, graphGenerator, null, null, 4);
    }

    public static void main(String[] args) {
        Generator generator = createCustomStarGenerator(5);
        DirectedGraph<StringVertex, StringEdge> result = generator.getResult();
        GraphWriter.write(result, "star6.csv");
        //new Visualisation(result).showGraph();

        List<Anomaly> anomalies = AnomalyDetector.findAnomalies(AnomalyType.ADDITION, result);
        for (Anomaly anomaly : anomalies) {
            System.out.println(anomaly);
        }

    }
}
