package GAD.algorithms;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

/**
 * Created by jkordas on 05/05/16.
 */
public class Anomaly {
    private int value;
    private DirectedGraph<StringVertex, StringEdge> structure;

    public Anomaly(int value, DirectedGraph<StringVertex, StringEdge> structure) {
        this.value = value;
        this.structure = structure;
    }

    public int getValue() {
        return value;
    }

    public DirectedGraph<StringVertex, StringEdge> getStructure() {
        return structure;
    }
}
