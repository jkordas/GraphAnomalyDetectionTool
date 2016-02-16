package graph;

//Directed edge
public class Edge {
    private Vertex start;
    private Vertex end;
    private String label;

    public Edge(String label) {
        this.label = label;
    }

    public void setStart(Vertex start) {
        this.start = start;
    }

    public void setEnd(Vertex end) {
        this.end = end;
    }

}