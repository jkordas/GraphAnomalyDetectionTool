package algorithms.utils;

import graph.StringEdge;
import graph.StringVertex;

/**
 * Created by jkordas on 24/04/16.
 */
public class PreparedStringEdge {
    private StringVertex source;
    private StringVertex target;
    private StringEdge edge;

    public PreparedStringEdge(StringVertex source, StringVertex target, StringEdge edge) {
        this.source = source;
        this.target = target;
        this.edge = edge;
    }

    public StringVertex getSource() {
        return source;
    }

    public StringVertex getTarget() {
        return target;
    }

    public StringEdge getEdge() {
        return edge;
    }
}
