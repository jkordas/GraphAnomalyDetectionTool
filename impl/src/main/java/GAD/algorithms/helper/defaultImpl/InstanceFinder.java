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
                                                                       DirectedGraph<StringVertex, StringEdge>
                                                                               substructure,
                                                                       boolean exactMatch) {
        List<DirectedGraph<StringVertex, StringEdge>> instanceList = new LinkedList<>();
        Set<StringVertex> subVertices = substructure.vertexSet();
        //TODO: filter vertices properly, equals cannot be overridden
        //Subgraph<StringVertex, StringEdge, Graph<StringVertex, StringEdge>> gReduced = new Subgraph<>(g, subVertices);

        StringVertex[] gVertices = g.vertexSet().toArray(new StringVertex[g.vertexSet().size()]);
        CombinationGenerator combinationGenerator = new CombinationGenerator(gVertices.length, subVertices.size());

        while (combinationGenerator.hasNext()) {
            Set<StringVertex> loadedVertices = Utils.loadVertices(gVertices, combinationGenerator.next());
            Set<StringEdge> loadedEdges = Utils.loadEdges(g, loadedVertices);
            DirectedSubgraph<StringVertex, StringEdge> loadedGraph = new DirectedSubgraph<>(g, loadedVertices, loadedEdges);

            //TODO: interface
            if (exactMatch) {
                if (Algorithms.getInstance().isIsomorphic(loadedGraph, substructure)) {
                    instanceList.add(loadedGraph);
                }
            } else {
                if (Algorithms.getInstance().isTopologicallyIsomorphic(loadedGraph, substructure)) {
                    instanceList.add(loadedGraph);
                }
            }
        }

        return instanceList;
    }

}
