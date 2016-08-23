package performance;

import GAD.algorithms.Algorithms;
import GAD.generate.Generator;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import com.opencsv.CSVWriter;
import org.jgrapht.DirectedGraph;
import org.jgrapht.generate.LinearGraphGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by jkordas on 07/08/16.
 */
public class PatternSearchLinear {


    public static void main(String[] args) throws IOException, InterruptedException {

       new PatternSearchLinear().calculate();

    }

    public void calculate() throws IOException {
        String csv = "performanceResults/patternSearchLinear_3.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));

        String [] header = " ,3,4,5,6,7,8,9,10,11,12,13,14,15".split(",");
        writer.writeNext(header);

        int randomVerticesInsertNumber = 10;

        for (int i = 3; i < 4; i++) {
            String result = "substructures number: " + i;

            for (int j = 3; j < 16; j++) {
                Generator g = new Generator(i, new LinearGraphGenerator<>(j), null, null, randomVerticesInsertNumber);

                long startTime = System.currentTimeMillis();

                List<DirectedGraph<StringVertex, StringEdge>> bestSubstructures = Algorithms.getInstance().bestSubstructures(g.getResult(), 1);
                long endTime   = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                result = result + "," + totalTime;

                System.out.println("res: " + result);
            }
            String [] record = result.split(",");
            writer.writeNext(record);
        }

        writer.close();
    }
}
