package GAD.algorithms;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;

import java.util.List;

/**
 * Created by jkordas on 05/05/16.
 */
public enum AnomalyType {
    MODIFICATION {
        @Override
        public List<Anomaly> findAnomalies(DirectedGraph<StringVertex, StringEdge> graph) {
            return GBAD_MDL.getInstance().findAnomalies(graph);
        }
    },
    ADDITION {
        @Override
        public List<Anomaly> findAnomalies(DirectedGraph<StringVertex, StringEdge> graph) {
            return GBAD_P.getInstance().findAnomalies(graph);
        }
    },
    DELETION {
        @Override
        public List<Anomaly> findAnomalies(DirectedGraph<StringVertex, StringEdge> graph) {
            return GBAD_MPS.getInstance().findAnomalies(graph);
        }
    };

    public abstract List<Anomaly> findAnomalies(DirectedGraph<StringVertex, StringEdge> graph);
}
