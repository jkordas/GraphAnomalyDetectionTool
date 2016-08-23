package GAD.algorithms;

import GAD.Config;
import GAD.algorithms.helper.*;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;

import java.util.List;

/**
 * Created by jkordas on 05/05/16.
 */
public class Algorithms implements IBestSubstructureFinder, ICompressor, IIncludedSubstructuresFinder, IInstanceFinder,
        IIsomorphismDetector, IStructureExtender, ITransformationCostCalculator {
    private static Algorithms instance = new Algorithms();
    private Config config = Config.getInstance();

    private IBestSubstructureFinder bestSubstructureFinder = config.BEST_SUBSTRUCTURE_FINDER;
    private ICompressor compressor = config.COMPRESSOR;
    private IIncludedSubstructuresFinder includedSubstructureFinder = config.INCLUDED_SUBSTRUCTURE_FINDER;
    private IInstanceFinder instanceFinder = config.INSTANCE_FINDER;
    private IIsomorphismDetector isomorphismDetector = config.ISOMORPHISM_DETECTOR;
    private IStructureExtender structureExtender = config.STRUCTURE_EXTENDER;
    private ITransformationCostCalculator transformationCostCalculator = config.TRANSFORMATION_COST_CALCULATOR;

    public static Algorithms getInstance() {
        return instance;
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

    public List<DirectedGraph<StringVertex, StringEdge>> findInstances(DirectedGraph<StringVertex, StringEdge> g,
                                                                       DirectedGraph<StringVertex, StringEdge> s) {
        return findInstances(g, s, true);
    }

    @Override
    public List<DirectedGraph<StringVertex, StringEdge>> findInstances(DirectedGraph<StringVertex, StringEdge> g,
                                                                       DirectedGraph<StringVertex, StringEdge> s, boolean exactMatch) {
        return instanceFinder.findInstances(g, s, exactMatch);
    }

    @Override
    public boolean isIsomorphic(Graph<StringVertex, StringEdge> g1, Graph<StringVertex, StringEdge> g2) {
        return isomorphismDetector.isIsomorphic(g1, g2);
    }

    @Override
    public boolean isTopologicallyIsomorphic(Graph<StringVertex, StringEdge> g1, Graph<StringVertex, StringEdge> g2) {
        return isomorphismDetector.isTopologicallyIsomorphic(g1, g2);
    }

    @Override
    public boolean isSubgraphIsomorphic(Graph<StringVertex, StringEdge> g, Graph<StringVertex, StringEdge> s) {
        return isomorphismDetector.isSubgraphIsomorphic(g, s);
    }

    @Override
    public List<DirectedGraph<StringVertex, StringEdge>> extendStructure(Graph<StringVertex, StringEdge> g, Graph<StringVertex,
            StringEdge> s) {
        return structureExtender.extendStructure(g, s);
    }

    @Override
    public int transformationCost(DirectedGraph<StringVertex, StringEdge> g1, DirectedGraph<StringVertex, StringEdge> g2) {
        return transformationCostCalculator.transformationCost(g1, g2);
    }

    @Override
    public int subgraphTransformationCost(DirectedGraph<StringVertex, StringEdge> s, DirectedGraph<StringVertex, StringEdge> g) {
        return transformationCostCalculator.subgraphTransformationCost(s, g);
    }
}
