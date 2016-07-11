package GAD;

import GAD.algorithms.Anomaly;
import GAD.algorithms.AnomalyType;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.io.GraphReader;
import org.jgrapht.DirectedGraph;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by jkordas on 05/05/16.
 */
public class AnomalyDetector {
    @Option(name="-a",usage="additions")
    private boolean additions;

    @Option(name="-in",usage="input file",metaVar="INPUT")
    private File in = null;

    public static List<Anomaly> findAnomalies(AnomalyType anomalyType, DirectedGraph<StringVertex, StringEdge> graph) {
        return anomalyType.findAnomalies(graph);
    }

    public static List<Anomaly> findAnomalies(DirectedGraph<StringVertex, StringEdge> graph) {
        List<Anomaly> anomalies = AnomalyType.ADDITION.findAnomalies(graph);
        anomalies.addAll(AnomalyType.DELETION.findAnomalies(graph));
        anomalies.addAll(AnomalyType.MODIFICATION.findAnomalies(graph));
        return anomalies;
    }

    public static void main(String[] args) throws IOException {
        new AnomalyDetector().doMain(args);
    }

    public void doMain(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        // if you have a wider console, you could increase the value;
        // here 80 is also the default
        parser.setUsageWidth(80);

        try {
            // parse the arguments.
            parser.parseArgument(args);

        } catch( CmdLineException e ) {
            // if there's a problem in the command line,
            // you'll get this exception. this will report
            // an error message.
            System.err.println(e.getMessage());
            System.err.println("java SampleMain [options...] arguments...");
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();

            // print option sample. This is useful some time
            return;
        }

        if(in != null && in.exists()) {
            System.out.println("input exists");
            DirectedGraph<StringVertex, StringEdge> inGraph = GraphReader.parse(in);

            if(additions){
                System.out.println("detecting additions");
                List<Anomaly> anomalies = AnomalyType.ADDITION.findAnomalies(inGraph);

                for (Anomaly anomaly : anomalies) {
                    System.out.println(anomaly);
                }
            }
        }


    }
}
