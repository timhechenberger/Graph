public class Edge {
    private Vertex start;
    private Vertex end;
    private Integer weight;

    public Edge(Vertex startV, Vertex endV, int inputweight)
    {
        this.start = startV;
        this.end = endV;
        this.weight = inputweight;
    }

    public Vertex getStart()
    {
        return this.start;
    }

    public Vertex getEnd()
    {
        return this.end;
    }

    public int getWeight()
    {
        return this.weight;
    }

}
