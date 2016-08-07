package GAD.generate;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.graph.Visualisation;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.generate.StarGraphGenerator;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.*;

/**
 * Created by jkordas on 2016-05-12.
 */
public class Generator {
    private static String [] vLabels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
    private static String [] eLabels = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
    private List<DirectedGraph<StringVertex, StringEdge>> substructures;

    private DirectedGraph<StringVertex, StringEdge> result = new SimpleDirectedGraph<>(StringEdge.class);
    // connection type


    public DirectedGraph<StringVertex, StringEdge> getResult() {
        return result;
    }

    public Generator(int substructuresNumber, GraphGenerator<StringVertex, StringEdge, StringVertex> graphGenerator, Map<Integer,
            Integer> verticesMap, Map<Integer, Integer> edgesMap, int randomVertices) {
        substructures = new ArrayList<>(substructuresNumber);
        for (int i = 0; i < substructuresNumber; i++) {
            DirectedGraph<StringVertex, StringEdge> target = new SimpleDirectedGraph<>(StringEdge.class);
            graphGenerator.generateGraph(target, Factory.createVertexFactory(), null);
            substructures.add(target);
        }

        StringVertex source = substructures.get(0).vertexSet().iterator().next();

        for (DirectedGraph<StringVertex, StringEdge> substructure : substructures) {
            Graphs.addGraph(result, substructure);
        }

        for (int i = 1; i < substructures.size(); i++) {
            StringVertex edgeTarget = substructures.get(i).vertexSet().iterator().next();
            result.addEdge(source, edgeTarget);
        }

        modifyVertices(verticesMap);
        modifyEdges(edgesMap);
        addRandomVertices(randomVertices);
    }

    private void addRandomVertices(int randomVertices) {
        Random random = new Random();

        for (int i = 0; i < randomVertices; i++) {
            StringVertex v = new StringVertex(vLabels[i % vLabels.length] + " " + i);
            int subNumber = random.nextInt(substructures.size());
            DirectedGraph<StringVertex, StringEdge> substructure = substructures.get(subNumber);

            int vertexNum = random.nextInt(substructure.vertexSet().size());
            Iterator<StringVertex> iterator = substructure.vertexSet().iterator();

            for (int j = 0; j < vertexNum - 1; j++) {
                iterator.next();
            }
            StringVertex source = iterator.next();

            result.addVertex(v);
            result.addEdge(source, v);
        }
    }

    private void modifyVertices(Map<Integer, Integer> vModifyMap) {
        if(vModifyMap != null) {
            for (Integer subNumber : vModifyMap.keySet()) {
                DirectedGraph<StringVertex, StringEdge> sub = substructures.get(subNumber);
                Iterator<StringVertex> iterator = sub.vertexSet().iterator();
                for (int i = 0; i < vModifyMap.get(subNumber); i++) {
                    iterator.next().setLabel(vLabels[i]);
                }
            }
        }
    }

    private void modifyEdges(Map<Integer, Integer> eModifyMap) {
        if(eModifyMap != null) {
            for (Integer subNumber : eModifyMap.keySet()) {
                DirectedGraph<StringVertex, StringEdge> sub = substructures.get(subNumber);
                Iterator<StringEdge> iterator = sub.edgeSet().iterator();
                for (int i = 0; i < eModifyMap.get(subNumber); i++) {
                    iterator.next().setLabel(eLabels[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
//        Generator g = new Generator(3, new CompleteGraphGenerator<>(5));
//        Generator g = new Generator(3, new LinearGraphGenerator<>(5));
        HashMap<Integer, Integer> vModifyMap = new HashMap<>();
        vModifyMap.put(0, 1);
        vModifyMap.put(2, 2);

        HashMap<Integer, Integer> eModifyMap = new HashMap<>();
        eModifyMap.put(0, 1);


        Generator g = new Generator(3, new StarGraphGenerator<>(7), vModifyMap, eModifyMap, 10);

        new Visualisation(g.getResult()).showGraph();
    }
}
