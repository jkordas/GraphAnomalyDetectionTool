package GAD.algorithms;

import GAD.Config;
import GAD.algorithms.helper.IBestSubstructureFinder;
import GAD.algorithms.helper.ICompressor;
import GAD.algorithms.helper.IIncludedSubstructuresFinder;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 05/05/16.
 */
public class Algorithms implements IBestSubstructureFinder, ICompressor, IIncludedSubstructuresFinder {
    private static Algorithms ourInstance = new Algorithms();
    private Config config = Config.getInstance();

    private IBestSubstructureFinder bestSubstructureFinder = config.BEST_SUBSTRUCTURE_FINDER;
    private ICompressor compressor = config.COMPRESSOR;
    private IIncludedSubstructuresFinder includedSubstructureFinder = config.INCLUDED_SUBSTRUCTURE_FINDER;

    public static Algorithms getInstance() {
        return ourInstance;
    }

    private Algorithms() {
    }

    public DirectedGraph<StringVertex, StringEdge> bestSubstructure(DirectedGraph<StringVertex, StringEdge> graph) {
        final int SUBSTRUCTURES_LIMIT = 1;
        return bestSubstructureFinder.bestSubstructures(graph, SUBSTRUCTURES_LIMIT).get(0);
    }

    @Override
    public List<DirectedGraph<StringVertex, StringEdge>> bestSubstructures(DirectedGraph<StringVertex, StringEdge> graph,
                                                                           int substructuresLimit) {
        return bestSubstructureFinder.bestSubstructures(graph, substructuresLimit);
    }

    @Override
    public DirectedGraph<StringVertex, StringEdge> compress(DirectedGraph<StringVertex, StringEdge> g,
                                                            DirectedGraph<StringVertex, StringEdge> substructure) {
        return compressor.compress(g, substructure);
    }

    @Override
    public List<DirectedGraph<StringVertex, StringEdge>> includedSubstructures(DirectedGraph<StringVertex,
            StringEdge> s) {
        return includedSubstructureFinder.includedSubstructures(s);
    }
}
