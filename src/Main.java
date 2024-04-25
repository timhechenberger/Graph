import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        ArrayList<Vertex> vertexArrayList = new ArrayList<Vertex>();
        vertexArrayList.add(v1);
        vertexArrayList.add(v2);
        vertexArrayList.add(v3);
        vertexArrayList.add(v4);

        Graph graph = new Graph(true,false);
        graph.addVertex(v1.getData());
        graph.addVertex(v2.getData());
        graph.addVertex(v3.getData());
        graph.addVertex(v4.getData());
        graph.addEdge(v1,v2,2);
        graph.addEdge(v1,v3,4);
        graph.addEdge(v2,v3,1);
        graph.addEdge(v2,v4,7);
        graph.addEdge(v3,v4,3);

        System.out.println("breadthFirstTraversal");
        graph.breadthFirstTraversal(v1);

        System.out.println("\n");

        System.out.println("depthFirstTraversal");
        graph.depthFirstTraversal(v1,vertexArrayList);



    }
}