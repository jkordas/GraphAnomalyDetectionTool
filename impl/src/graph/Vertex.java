package graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String value;
    private List<Edge> out = new ArrayList<>();
    private List<Edge> in = new ArrayList<>();

}