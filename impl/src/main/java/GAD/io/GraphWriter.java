package GAD.io;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import com.opencsv.CSVWriter;
import org.jgrapht.DirectedGraph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by jkordas on 02/05/16.
 */
public class GraphWriter {
    public static void write(DirectedGraph<StringVertex, StringEdge> graph, String fileName) {
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter("graphModels/generated/" + fileName), ',', CSVWriter.NO_QUOTE_CHARACTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<StringVertex, Integer> map = new HashMap<>();
        Set<StringVertex> vertexSet = graph.vertexSet();
        int count = 1;
        for (StringVertex vertex : vertexSet) {
            String[] s = {String.valueOf(count), vertex.getLabel()};
            writer.writeNext(s);
            map.put(vertex, count);
            count++;
        }

        writer.writeNext(new String[]{"EDGES"});

        for (StringVertex vertex : vertexSet) {
            Set<StringEdge> edges = graph.outgoingEdgesOf(vertex);
            for (StringEdge edge : edges) {
                writer.writeNext(new String[]{String.valueOf(map.get(vertex)), String.valueOf(map.get(graph.getEdgeTarget(edge)))});
            }
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
