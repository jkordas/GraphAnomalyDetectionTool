package graph;

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

//    @Override
//    public boolean equals(Object obj) {
//        if(!(obj instanceof StringVertex)){
//            return false;
//        }
//        StringVertex s = (StringVertex) obj;
//        return s.getLabel().equals(label);
//    }
}
