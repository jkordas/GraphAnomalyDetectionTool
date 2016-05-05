package manual;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;

/**
 * Created by jkordas on 24/04/16.
 */
public class TestUtils {

    public static DirectedGraph<StringVertex, StringEdge> createCompressGraph() {
        DirectedGraph<StringVertex, StringEdge> g =
                new SimpleDirectedGraph<StringVertex, StringEdge>(StringEdge.class);

        StringVertex v1 = new StringVertex("1");
        StringVertex v2 = new StringVertex("2");
        StringVertex v3 = new StringVertex("3");
        StringVertex v4 = new StringVertex("4");
        StringVertex v5 = new StringVertex("5");
        StringVertex v6 = new StringVertex("1");
        StringVertex v7 = new StringVertex("2");
        StringVertex v8 = new StringVertex("3");
        StringVertex v9 = new StringVertex("4");
        StringVertex v10 = new StringVertex("5");
        StringVertex v11 = new StringVertex("1");
        StringVertex v12 = new StringVertex("2");
        StringVertex v13 = new StringVertex("3");
        StringVertex v14 = new StringVertex("4");
        StringVertex v15 = new StringVertex("5");
        StringVertex v16 = new StringVertex("1");
        StringVertex v17 = new StringVertex("2");
        StringVertex v18 = new StringVertex("3");
        StringVertex v19 = new StringVertex("3");
        StringVertex v20 = new StringVertex("5");

//         add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);
        g.addVertex(v9);
        g.addVertex(v10);
        g.addVertex(v11);
        g.addVertex(v12);
        g.addVertex(v13);
        g.addVertex(v14);
        g.addVertex(v15);
        g.addVertex(v16);
        g.addVertex(v17);
        g.addVertex(v18);
        g.addVertex(v19);
        g.addVertex(v20);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v1, v4);
        g.addEdge(v4, v5);
        g.addEdge(v5, v6);
        g.addEdge(v6, v7);
        g.addEdge(v7, v8);
        g.addEdge(v8, v9);
        g.addEdge(v6, v9);
        g.addEdge(v9, v10);
        //
        g.addEdge(v11, v8);
        g.addEdge(v11, v12);
        g.addEdge(v12, v13);
        g.addEdge(v13, v14);
        g.addEdge(v11, v14);
        g.addEdge(v14, v15);
        g.addEdge(v7, v20);
        g.addEdge(v19, v2);
        g.addEdge(v16, v17);
        g.addEdge(v17, v18);
        g.addEdge(v18, v19);
        g.addEdge(v16, v19);
        g.addEdge(v19, v20);

        return g;
    }
    public static DirectedGraph<StringVertex, StringEdge> createGraphGBAD_MDL() {
        DirectedGraph<StringVertex, StringEdge> g =
                new SimpleDirectedGraph<StringVertex, StringEdge>(StringEdge.class);

        StringVertex v1 = new StringVertex("1");
        StringVertex v2 = new StringVertex("2");
        StringVertex v3 = new StringVertex("3");
        StringVertex v4 = new StringVertex("4");
        StringVertex v5 = new StringVertex("5");
        StringVertex v6 = new StringVertex("1");
        StringVertex v7 = new StringVertex("2");
        StringVertex v8 = new StringVertex("3");
        StringVertex v9 = new StringVertex("4");
        StringVertex v10 = new StringVertex("5");
        StringVertex v11 = new StringVertex("1");
        StringVertex v12 = new StringVertex("2");
        StringVertex v13 = new StringVertex("3");
        StringVertex v14 = new StringVertex("4");
        StringVertex v15 = new StringVertex("5");
        StringVertex v16 = new StringVertex("1");
        StringVertex v17 = new StringVertex("2");
        StringVertex v18 = new StringVertex("3");
        StringVertex v19 = new StringVertex("3");
        StringVertex v20 = new StringVertex("5");
        StringVertex v21 = new StringVertex("5");
        StringVertex v22 = new StringVertex("5");
        StringVertex v23 = new StringVertex("5");
        StringVertex v24 = new StringVertex("5");
        StringVertex v25 = new StringVertex("5");
        StringVertex v26 = new StringVertex("5");
        StringVertex v27 = new StringVertex("5");
        StringVertex v28 = new StringVertex("5");
        StringVertex v29 = new StringVertex("5");
        StringVertex v30 = new StringVertex("5");
        StringVertex v31 = new StringVertex("5");
        StringVertex v32 = new StringVertex("5");
        StringVertex v33 = new StringVertex("5");
        StringVertex v34 = new StringVertex("5");
        StringVertex v35 = new StringVertex("5");
        StringVertex v36 = new StringVertex("5");
        StringVertex v37 = new StringVertex("5");
        StringVertex v38 = new StringVertex("5");

//         add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);
        g.addVertex(v9);
        g.addVertex(v10);
        g.addVertex(v11);
        g.addVertex(v12);
        g.addVertex(v13);
        g.addVertex(v14);
        g.addVertex(v15);
        g.addVertex(v16);
        g.addVertex(v17);
        g.addVertex(v18);
        g.addVertex(v19);
        g.addVertex(v20);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v1, v4);
        g.addEdge(v4, v5);
        g.addEdge(v5, v6);
        g.addEdge(v6, v7);
        g.addEdge(v7, v8);
        g.addEdge(v8, v9);
        g.addEdge(v6, v9);
        g.addEdge(v9, v10);
        //
        g.addEdge(v11, v8);
        g.addEdge(v11, v12);
        g.addEdge(v12, v13);
        g.addEdge(v13, v14);
        g.addEdge(v11, v14);
        g.addEdge(v14, v15);
        g.addEdge(v7, v20);
        g.addEdge(v19, v2);
        g.addEdge(v16, v17);
        g.addEdge(v17, v18);
        g.addEdge(v18, v19);
        g.addEdge(v16, v19);
        g.addEdge(v19, v20);

        return g;
    }

    public static DirectedGraph<StringVertex, StringEdge> createCompressSubstructure1() {
        DirectedGraph<StringVertex, StringEdge> s =
                new SimpleDirectedGraph<StringVertex, StringEdge>(StringEdge.class);

        StringVertex vs1 = new StringVertex("1");
        StringVertex vs2 = new StringVertex("2");
        StringVertex vs3 = new StringVertex("3");

//         add the vertices
        s.addVertex(vs1);
        s.addVertex(vs2);
        s.addVertex(vs3);

        // add edges to create a circuit
        s.addEdge(vs1, vs2);
        s.addEdge(vs2, vs3);

        return s;
    }

    public static DirectedGraph<StringVertex, StringEdge> createCompressSubstructure2() {
        DirectedGraph<StringVertex, StringEdge> s =
                new SimpleDirectedGraph<StringVertex, StringEdge>(StringEdge.class);

        StringVertex vs1 = new StringVertex("1");
        StringVertex vs2 = new StringVertex("2");
        StringVertex vs3 = new StringVertex("3");
        StringVertex vs4 = new StringVertex("4");
        StringVertex vs5 = new StringVertex("5");

//         add the vertices
        s.addVertex(vs1);
        s.addVertex(vs2);
        s.addVertex(vs3);
        s.addVertex(vs4);
        s.addVertex(vs5);

        // add edges to create a circuit
        s.addEdge(vs1, vs2);
        s.addEdge(vs2, vs3);
        s.addEdge(vs3, vs4);
        s.addEdge(vs4, vs5);
        s.addEdge(vs1, vs4);

        return s;
    }
}
