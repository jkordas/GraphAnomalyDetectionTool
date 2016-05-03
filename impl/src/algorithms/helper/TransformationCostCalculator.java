package algorithms.helper;

import algorithms.utils.PermutationGenerator;
import graph.StringEdge;
import graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultGraphMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by jkordas on 30/04/16.
 */
public class TransformationCostCalculator {
    public static int transformationCost(DirectedGraph<StringVertex, StringEdge> g1, DirectedGraph<StringVertex, StringEdge> g2) {
        if(g1.vertexSet().size() != g2.vertexSet().size()){
            throw new IllegalArgumentException("Graph g1 and g2 must have the same vertex number.");
        }
        int size = g1.vertexSet().size();
        int minCost = Integer.MAX_VALUE;

        StringVertex[] vertices1 = g1.vertexSet().toArray(new StringVertex[size]);
        StringVertex[] vertices2 = g2.vertexSet().toArray(new StringVertex[size]);

        PermutationGenerator permutationGenerator = new PermutationGenerator(size);
        while(permutationGenerator.hasNext()) {
            int cost = 0;
            int [] permutation = permutationGenerator.next();
            Map<StringVertex, StringVertex> g1ToG2 = new HashMap<>();
            Map<StringVertex, StringVertex> g2ToG1 = new HashMap<>();

            for(int i = 0; i < size; ++i) {
                g1ToG2.put(vertices1[i], vertices2[permutation[i]]);
                g2ToG1.put(vertices2[permutation[i]], vertices1[i]);
            }

            DefaultGraphMapping<StringVertex, StringEdge> mapping = new DefaultGraphMapping<>(g1ToG2, g2ToG1, g1, g2);

            //oblicz koszt

            //krawedzie
            cost += getEdgesCost(g1.edgeSet(), mapping, true);
            cost += getEdgesCost(g2.edgeSet(), mapping, false);

            //wierzcholki
            cost += getVerticesCost(g1.vertexSet(), mapping);

            if(cost < minCost) {
                minCost = cost;
            }
        }

        return minCost;
    }

    private static int getEdgesCost(Set<StringEdge> edgeSet, DefaultGraphMapping<StringVertex, StringEdge> mapping, boolean forward) {
        int cost = 0;
        for (StringEdge edge : edgeSet) {
            StringEdge edgeCorrespondence = mapping.getEdgeCorrespondence(edge, forward);
            if(edgeCorrespondence != null) {
                if(!edge.getLabel().equals(edgeCorrespondence.getLabel())){
                    cost++;
                }
            } else {
                cost += 2;
            }
        }
        return cost;
    }

    private static int getVerticesCost(Set<StringVertex> vertexSet, DefaultGraphMapping<StringVertex, StringEdge> mapping) {
        int cost = 0;

        for (StringVertex vertex : vertexSet) {
            StringVertex vertexCorrespondence = mapping.getVertexCorrespondence(vertex, true);
            if(vertexCorrespondence != null) {
                if(!vertex.getLabel().equals(vertexCorrespondence.getLabel())){
                    cost++;
                }
            } else {
                throw new RuntimeException("Vertex not found.");
            }
        }

        return cost;
    }
}
