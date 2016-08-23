package GAD;

import GAD.algorithms.Anomaly;
import GAD.algorithms.AnomalyType;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import GAD.io.Visualisation;
import GAD.io.GraphReader;
import org.jgrapht.DirectedGraph;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jkordas on 05/05/16.
 */
public class AnomalyDetector {
    @Option(name="-a",usage="additions")
    private boolean additions;

    @Option(name="-d",usage="deletions")
    private boolean deletions;

    @Option(name="-m",usage="modifications")
    private boolean modifications;

    @Option(name="-all",usage="all")
    private boolean all;

    @Option(name="-show",usage="show")
    private boolean show;

    @Option(name="-max-substructure-size",usage="max substructure size")
    private int maxSubstructureSize = -1;

    @Option(name="-best-substructures-limit",usage="best substructures limit")
    private int bestSubstructuresLimit = -1;

    @Option(name="-a-threshold",usage="additions threshold")
    private int aThreshold = -1;

    @Option(name="-d-threshold",usage="deletions threshold")
    private int dThreshold = -1;

    @Option(name = "-m-threshold", usage = "modifications threshold")
    private int mThreshold = -1;



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
            if(maxSubstructureSize > 0){
                Config.getInstance().MAX_SUBSTRUCTURE_SIZE = maxSubstructureSize;
                System.out.println("Max substructure size set to: " + maxSubstructureSize);
            }
            if(bestSubstructuresLimit > 0){
                Config.getInstance().BEST_SUBSTRUCTURES_LIMIT = bestSubstructuresLimit;
                System.out.println("Best substructures limit set to: " + bestSubstructuresLimit);
            }
            if(mThreshold > 0){
                Config.getInstance().GBAD_MDL_THRESHOLD = mThreshold;
                System.out.println("Modifications threshold set to: " + mThreshold);
            }
            if(aThreshold > 0){
                Config.getInstance().GBAD_P_THRESHOLD = aThreshold;
                System.out.println("Additions threshold set to: " + aThreshold);
            }
            if(dThreshold > 0){
                Config.getInstance().GBAD_MPS_THRESHOLD = dThreshold;
                System.out.println("Deletions threshold set to: " + dThreshold);
            }

            System.out.println("Input file: " + in.getAbsolutePath() + " exists.");
            DirectedGraph<StringVertex, StringEdge> inGraph = GraphReader.parse(in);

            if(show) {
                new Visualisation(inGraph).showGraph();
            }

            List<Anomaly> anomalies = new LinkedList<>();

            if(all || additions){
                System.out.println("Detecting additions");
                anomalies.addAll(AnomalyType.ADDITION.findAnomalies(inGraph));
            }
            if(all || deletions){
                System.out.println("Detecting deletions");
                anomalies.addAll(AnomalyType.DELETION.findAnomalies(inGraph));
            }
            if(all || modifications){
                System.out.println("Detecting modifications");
                anomalies.addAll(AnomalyType.MODIFICATION.findAnomalies(inGraph));
            }


            System.out.println("Found " + anomalies.size() + " anomalies.");

            for (Anomaly anomaly : anomalies) {
                System.out.println(anomaly);
            }
        }


    }
}
