package GAD.io;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import com.opencsv.CSVReader;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkordas on 02/05/16.
 */
public class GraphReader {
    public static DirectedGraph<StringVertex, StringEdge> parse(String filePath) {
        return parse(new File(filePath));
    }

    public static DirectedGraph<StringVertex, StringEdge> parse(File file) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] nextLine;
        DirectedGraph<StringVertex, StringEdge> graph = new SimpleDirectedGraph<>(StringEdge.class);
        List<StringVertex> vertices = new ArrayList<>();
        vertices.add(null);//we start numbering ids from one
        try {
            while ((nextLine = reader.readNext()) != null && !nextLine[0].equals("EDGES")) {
                // nextLine[] is an array of values from the line
                StringVertex vertex = new StringVertex(nextLine[1].trim());
                graph.addVertex(vertex);
                vertices.add(vertex);
            }

            while ((nextLine = reader.readNext()) != null) {
                String label = "";
                if (nextLine.length > 2) {
                    label = nextLine[2].trim();
                }
                StringEdge edge = new StringEdge(label);

                int sourceId = Integer.parseInt(nextLine[0].trim());
                int targetId = Integer.parseInt(nextLine[1].trim());
                StringVertex source = vertices.get(sourceId);
                StringVertex target = vertices.get(targetId);

                graph.addEdge(source, target, edge);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }
}
