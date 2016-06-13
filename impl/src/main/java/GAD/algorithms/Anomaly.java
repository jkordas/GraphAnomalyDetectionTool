package GAD.algorithms;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

/**
 * Created by jkordas on 05/05/16.
 */
public class Anomaly {
    private AnomalyType type;
    private int value;
    private DirectedGraph<StringVertex, StringEdge> structure;

    public Anomaly(AnomalyType type, int value, DirectedGraph<StringVertex, StringEdge> structure) {
        this.type = type;
        this.value = value;
        this.structure = structure;
    }

    public AnomalyType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public DirectedGraph<StringVertex, StringEdge> getStructure() {
        return structure;
    }

    @Override
    public String toString() {
        return "Anomaly value: " + getValue() + "\nStructure: " + getStructure();
    }
}
