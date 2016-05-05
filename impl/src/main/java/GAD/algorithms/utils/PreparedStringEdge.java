package GAD.algorithms.utils;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;

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

    public void setSource(StringVertex source) {
        this.source = source;
    }

    public void setTarget(StringVertex target) {
        this.target = target;
    }
}
