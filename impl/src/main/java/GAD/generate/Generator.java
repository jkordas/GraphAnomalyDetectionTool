package GAD.generate;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.graph.Visualisation;
import javafx.util.Pair;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.generate.LinearGraphGenerator;
import org.jgrapht.generate.StarGraphGenerator;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.*;

/**
 * Created by jkordas on 2016-05-12.
 */
public class Generator {
    private static String [] vLabels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
    private static String [] eLabels = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
    private int substructuresNumber;
    private GraphGenerator<StringVertex, StringEdge, StringVertex> graphGenerator;
    private List<DirectedGraph<StringVertex, StringEdge>> substructures;

    private DirectedGraph<StringVertex, StringEdge> result;
    // connection type
    // map edges to modify

    private Map<Integer, Integer> verticesMap;// map vertices to modify

    // randomly added vertices - noise


    public DirectedGraph<StringVertex, StringEdge> getResult() {
        return result;
    }

    public Generator(int substructuresNumber, GraphGenerator<StringVertex, StringEdge, StringVertex> graphGenerator, Map<Integer, Integer> verticesMap) {
        this.substructuresNumber = substructuresNumber;
        this.graphGenerator = graphGenerator;
        this.verticesMap = verticesMap;

        substructures = new ArrayList<>(substructuresNumber);
        for (int i = 0; i < substructuresNumber; i++) {
            DirectedGraph<StringVertex, StringEdge> target = new SimpleDirectedGraph<>(StringEdge.class);
            graphGenerator.generateGraph(target, Factory.createVertexFactory(), null);
            substructures.add(target);
        }

        StringVertex source = substructures.get(0).vertexSet().iterator().next();
        DirectedGraph<StringVertex, StringEdge> merged = new SimpleDirectedGraph<>(StringEdge.class);

        for (DirectedGraph<StringVertex, StringEdge> substructure : substructures) {
            Graphs.addGraph(merged, substructure);
        }

        for (int i = 1; i < substructures.size(); i++) {
            StringVertex edgeTarget = substructures.get(i).vertexSet().iterator().next();
            merged.addEdge(source, edgeTarget);
        }

        if(verticesMap != null) {
            for (Integer subNumber : verticesMap.keySet()) {
                DirectedGraph<StringVertex, StringEdge> sub = substructures.get(subNumber);
                Iterator<StringVertex> iterator = sub.vertexSet().iterator();
                for (int i = 0; i < verticesMap.get(subNumber); i++) {
                    iterator.next().setLabel(vLabels[i]);
                }
            }
        }

        result = merged;
    }

    public static void main(String[] args) {
//        Generator g = new Generator(3, new CompleteGraphGenerator<>(5));
//        Generator g = new Generator(3, new LinearGraphGenerator<>(5));
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        map.put(2, 2);

        Generator g = new Generator(3, new StarGraphGenerator<>(7), map);

        new Visualisation(g.getResult()).showGraph();
    }
}
