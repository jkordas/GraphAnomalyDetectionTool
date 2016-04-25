package algorithms.helper;

import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.Graph;
import org.jgrapht.experimental.equivalence.EquivalenceComparator;
import org.jgrapht.experimental.isomorphism.AdaptiveIsomorphismInspectorFactory;
import org.jgrapht.experimental.isomorphism.GraphIsomorphismInspector;

/**
 * Created by jkordas on 15/03/16.
 */
public class IsomorphismDetector {
    private static EquivalenceComparator<StringVertex, Graph<StringVertex, StringEdge>> vertexComparator = new
            EquivalenceComparator<StringVertex, Graph<StringVertex, StringEdge>>() {
        @Override
        public boolean equivalenceCompare(StringVertex stringVertex, StringVertex v1, Graph<StringVertex, StringEdge> graph,
                                          Graph<StringVertex, StringEdge> c1) {
            return stringVertex.getLabel().equals(v1.getLabel());
        }

        @Override
        public int equivalenceHashcode(StringVertex stringVertex, Graph<StringVertex, StringEdge> graph) {
            return 0;
        }
    };

    private static EquivalenceComparator<StringEdge, Graph<StringVertex, StringEdge>> edgeComparator = new
            EquivalenceComparator<StringEdge, Graph<StringVertex, StringEdge>>() {

        @Override
        public boolean equivalenceCompare(StringEdge stringEdge, StringEdge e1, Graph<StringVertex, StringEdge> stringVertexStringEdgeGraph, Graph<StringVertex, StringEdge> c1) {
            return stringEdge.getLabel().equals(e1.getLabel());
        }

        @Override
        public int equivalenceHashcode(StringEdge stringEdge, Graph<StringVertex, StringEdge> stringVertexStringEdgeGraph) {
            return 0;
        }
    };

    public static boolean isIsomorphic(Graph<StringVertex, StringEdge> g1, Graph<StringVertex, StringEdge>g2) {
        GraphIsomorphismInspector<StringEdge> inspector = AdaptiveIsomorphismInspectorFactory.createIsomorphismInspector(g1, g2, vertexComparator, edgeComparator);
        return inspector.isIsomorphic();
    }


}
