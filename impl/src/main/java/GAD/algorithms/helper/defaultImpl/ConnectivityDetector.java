package GAD.algorithms.helper.defaultImpl;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;


/**
 * Created by jkordas on 15/03/16.
 */
public class ConnectivityDetector {

    public boolean isConnected(DirectedGraph<StringVertex, StringEdge> g) {
        ConnectivityInspector<StringVertex, StringEdge> inspector = new ConnectivityInspector<StringVertex, StringEdge>(g);
        return inspector.isGraphConnected();
    }
}
