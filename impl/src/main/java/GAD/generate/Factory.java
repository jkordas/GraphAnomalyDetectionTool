package GAD.generate;

import GAD.graph.StringVertex;
import org.jgrapht.VertexFactory;

/**
 * Created by jkordas on 2016-05-12.
 */
public class Factory {

    public static VertexFactory<StringVertex> createVertexFactory() {
        return new VertexFactory<StringVertex>() {
            private int counter = 0;

            @Override
            public StringVertex createVertex() {
                return new StringVertex(++counter + "");
            }
        };
    }
}
