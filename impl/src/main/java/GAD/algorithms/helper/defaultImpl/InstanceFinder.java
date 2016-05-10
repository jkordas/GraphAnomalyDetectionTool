package GAD.algorithms.helper.defaultImpl;

import GAD.algorithms.Algorithms;
import GAD.algorithms.helper.IInstanceFinder;
import GAD.algorithms.utils.CombinationGenerator;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DirectedSubgraph;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by jkordas on 24/04/16.
 */
public class InstanceFinder implements IInstanceFinder {
    @Override
    public List<DirectedGraph<StringVertex, StringEdge>> findInstances(DirectedGraph<StringVertex, StringEdge> g,
                                                                       DirectedGraph<StringVertex, StringEdge> s,
                                                                       boolean exactMatch) {
        List<DirectedGraph<StringVertex, StringEdge>> instanceList = new LinkedList<>();
        Set<StringVertex> sVertices = s.vertexSet();
        //NOTE: performance can be improved
        //reduce original graph to vertices contained in s
//        Set<StringVertex> gReducedVertices = new HashSet<>(g.vertexSet());
//        gReducedVertices.retainAll(sVertices);
//        DirectedSubgraph<StringVertex, StringEdge> gReduced = new DirectedSubgraph<>(g, gReducedVertices, g.edgeSet());

        StringVertex[] gVertices = g.vertexSet().toArray(new StringVertex[g.vertexSet().size()]);
        CombinationGenerator combinationGenerator = new CombinationGenerator(gVertices.length, sVertices.size());

        while (combinationGenerator.hasNext()) {
            Set<StringVertex> loadedVertices = Utils.loadVertices(gVertices, combinationGenerator.next());
            Set<StringEdge> loadedEdges = Utils.loadEdges(g, loadedVertices);
            DirectedSubgraph<StringVertex, StringEdge> loadedGraph = new DirectedSubgraph<>(g, loadedVertices, loadedEdges);

            if (exactMatch) {
                if (Algorithms.getInstance().isIsomorphic(loadedGraph, s)) {
                    instanceList.add(loadedGraph);
                }
            } else {
                if (Algorithms.getInstance().isTopologicallyIsomorphic(loadedGraph, s)) {
                    instanceList.add(loadedGraph);
                }
            }
        }

        return instanceList;
    }

}
