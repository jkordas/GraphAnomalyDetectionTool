package GAD.io;

import GAD.graph.StringEdge;
import GAD.graph.StringVertex;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;

import javax.swing.*;
import java.awt.*;

public class Visualisation
        extends JApplet {

    private Graph<StringVertex, StringEdge> graph;
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    public Visualisation(Graph<StringVertex, StringEdge> graph)
    {
        this.graph = graph;
        init();
    }

    public void showGraph() {
        JFrame frame = new JFrame();
        frame.getContentPane().add(this);
        frame.setTitle("JGraphT Adapter to JGraph Demo");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    public void init()
    {
        // create a visualization using JGraph, via an adapter
        JGraphXAdapter<StringVertex, StringEdge> jgxAdapter = new JGraphXAdapter<>(graph);

        getContentPane().add(new mxGraphComponent(jgxAdapter));
        resize(DEFAULT_SIZE);

        // positioning via jgraphx layouts
//        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
//        layout.execute(jgxAdapter.getDefaultParent());

        // positioning via jgraphx layouts
        mxGraphLayout layout = new mxOrganicLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());


        // that's all there is to it!...
    }
}