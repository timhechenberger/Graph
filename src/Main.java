import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(true,false);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(new Vertex(1),new Vertex(2),2);
        graph.addEdge(new Vertex(1),new Vertex(3),4);
        graph.addEdge(new Vertex(2),new Vertex(3),1);
        graph.addEdge(new Vertex(2),new Vertex(4),7);
        graph.addEdge(new Vertex(3),new Vertex(4),3);

        graph.breadthFirstTraversal(new Vertex(1));

    }

}