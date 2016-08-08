package performance;

import GAD.algorithms.Algorithms;
import GAD.generate.Generator;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.generate.RingGraphGenerator;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 * Created by jkordas on 07/08/16.
 */
public class PatternSearchRing {


    public static void main(String[] args) throws IOException {
        Handler handler = new FileHandler("patternSearchRing.log");
        Logger.getLogger("").addHandler(handler);

        int randomVerticesInsertNumber = 10;

        for (int i = 3; i < 8; i++) {
            for (int j = 3; j < 8; j++) {
                Generator g = new Generator(i, new RingGraphGenerator<>(j), null, null, randomVerticesInsertNumber);

                long startTime = System.currentTimeMillis();

                List<DirectedGraph<StringVertex, StringEdge>> bestSubstructures = Algorithms.getInstance().bestSubstructures(g.getResult(), 1);
                long endTime   = System.currentTimeMillis();
                long totalTime = endTime - startTime;


                System.out.println(bestSubstructures.size());
                System.out.println(bestSubstructures.get(0));
                Logger.getLogger("patternSearchRing").info(i + "," + j + " " + totalTime);

            }

        }


    }
}
