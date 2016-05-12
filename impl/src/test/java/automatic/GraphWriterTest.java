package automatic;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.io.GraphReader;
import GAD.io.GraphWriter;
import manual.TestUtils;
import org.jgrapht.DirectedGraph;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

/**
 * Created by jkordas on 10/05/16.
 */
public class GraphWriterTest {

    @Test
    public void readWriteTest() {
        String fileName = "test.csv";
        Path path = FileSystems.getDefault().getPath("graphModels/generated", fileName);
        DirectedGraph<StringVertex, StringEdge> graph = TestUtils.createCompressGraph();
        GraphWriter.write(graph, fileName);

        DirectedGraph<StringVertex, StringEdge> parsedGraph = GraphReader.parse(path.toAbsolutePath().toString());

        assertEquals(graph.vertexSet().size(), parsedGraph.vertexSet().size());
        assertEquals(graph.edgeSet().size(), parsedGraph.edgeSet().size());
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
