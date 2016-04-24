package graph;

import org.jgrapht.graph.DefaultEdge;

import java.io.Serializable;

/**
 * Created by jkordas on 12/03/16.
 */
public class StringEdge extends DefaultEdge implements Serializable{
    private String label;

    public StringEdge(String label) {
        this.label = label;
    }

    public StringEdge() {
        label = "";
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof StringEdge)){
            return false;
        }
        StringEdge s = (StringEdge) obj;
        return s.getLabel().equals(label);
    }
}
