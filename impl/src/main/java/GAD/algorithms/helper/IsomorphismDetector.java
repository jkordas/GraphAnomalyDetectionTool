package GAD.algorithms.helper;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.Graph;
import org.jgrapht.alg.isomorphism.IsomorphismInspector;
import org.jgrapht.alg.isomorphism.VF2GraphIsomorphismInspector;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;

import java.util.Comparator;


/**
 * Created by jkordas on 15/03/16.
 */
public class IsomorphismDetector {
    private static Comparator<StringVertex> vertexComparator = (v1, v2) -> v1.getLabel().compareTo(v2.getLabel());
    private static Comparator<StringEdge> edgeComparator = (e1, e2) -> e1.getLabel().compareTo(e2.getLabel());

    private static Comparator<StringVertex> topologyVertexComparator = (v1, v2) -> {
        if (v1 != null && v2 != null) {
            return 0;
        }
        return 1;
    };
    private static Comparator<StringEdge> topologyEdgeComparator = (e1, e2) -> {
        if (e1 != null && e2 != null) {
            return 0;
        }
        return 1;
    };

    public static boolean isIsomorphic(Graph<StringVertex, StringEdge> g1, Graph<StringVertex, StringEdge> g2) {
        IsomorphismInspector<StringVertex, StringEdge> inspector = new VF2GraphIsomorphismInspector(g1, g2, vertexComparator,
                edgeComparator, false);
        return inspector.isomorphismExists();
    }

    public static boolean isTopologicallyIsomorphic(Graph<StringVertex, StringEdge> g1, Graph<StringVertex, StringEdge> g2) {
        IsomorphismInspector<StringVertex, StringEdge> inspector = new VF2GraphIsomorphismInspector(g1, g2, topologyVertexComparator,
                topologyEdgeComparator, false);
        return inspector.isomorphismExists();
    }


    public static boolean isSubgraphIsomorphic(Graph<StringVertex, StringEdge> g, Graph<StringVertex, StringEdge> s) {
        IsomorphismInspector<StringVertex, StringEdge> inspector = new VF2SubgraphIsomorphismInspector<>(g, s, vertexComparator,
                edgeComparator, false);
        return inspector.isomorphismExists();
    }

}
