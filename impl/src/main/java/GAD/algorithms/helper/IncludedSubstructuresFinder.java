package GAD.algorithms.helper;

import GAD.algorithms.utils.CombinationGenerator;
import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DirectedSubgraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jkordas on 03/05/16.
 */
public class IncludedSubstructuresFinder {
    public static List<DirectedGraph<StringVertex, StringEdge>> includedSubstructures(DirectedGraph<StringVertex, StringEdge> s) {
        Set<DirectedGraph<StringVertex, StringEdge>> includedSubstructures = new HashSet<>();
        int n = s.vertexSet().size();
        StringVertex[] sVertices = s.vertexSet().toArray(new StringVertex[n]);

        for (int k = n - 1; k > 0; --k) {
            CombinationGenerator generator = new CombinationGenerator(n, k);
            while (generator.hasNext()) {
                Set<StringVertex> loadedVertices = Utils.loadVertices(sVertices, generator.next());
                Set<StringEdge> loadedEdges = Utils.loadEdges(s, loadedVertices);
                DirectedSubgraph<StringVertex, StringEdge> loadedGraph = new DirectedSubgraph<>(s, loadedVertices, loadedEdges);
                if(Utils.isGraphConnected(loadedGraph)){
                    includedSubstructures.add(loadedGraph);
                }
            }
        }

        Set<DirectedGraph<StringVertex, StringEdge>> graphs = Utils.uniqueSetByGraphIsomorphism(includedSubstructures);
        return new ArrayList<>(graphs);
    }
}
