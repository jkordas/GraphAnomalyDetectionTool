package GAD.graph;

import java.io.Serializable;

/**
 * Created by jkordas on 12/03/16.
 */
public class StringVertex implements Serializable{
    private String label;

    public StringVertex(String label) {
        this.label = label;
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

    //NOTE: equals cannot be overridden because jgrapht algorithms wont work correctly
}
