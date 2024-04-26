import java.util.*;

public class Graph {
    private ArrayList<Vertex> vertices;
    private boolean isWeighted;
    private boolean isDirected;

    public Graph(boolean inputIsWeighted, boolean inputIsDirected) {
        this.vertices = new ArrayList<Vertex>();
        this.isWeighted = inputIsWeighted;
        this.isDirected = inputIsDirected;
    }

    public Vertex addVertex(int data) {
        Vertex newVertex = new Vertex(data);
        this.vertices.add(newVertex);
        return newVertex;
    }

    public void addEdge(Vertex vertex1, Vertex vertex2, Integer weight) {
        if (!this.isWeighted) {
            weight = null;
        }
        vertex1.addEdge(vertex2, weight);
        if (!this.isDirected) {
            vertex2.addEdge(vertex1, weight);
        }
    }

    public Vertex getVertexByValue(int value) {
        for (Vertex v : this.vertices) {
            if (v.getData() == value) {
                return v;
            }
        }
        return null;
    }

    public void breadthFirstTraversal(Vertex start) {
        ArrayList<Vertex> visitedVertices = new ArrayList<Vertex>();
        visitedVertices.add(start);

        Queue<Vertex> visitQueue = new LinkedList<>();
        visitQueue.add(start);
        while (!visitQueue.isEmpty()) {
            Object current = visitQueue.remove();
            System.out.print(((Vertex) current).getData()+" ");
            for(Edge e : ((Vertex) current).getEdges()) {
                Vertex neighbor = e.getEnd();
                if(!visitedVertices.contains(neighbor)) {
                    visitedVertices.add(neighbor);
                    visitQueue.add(neighbor);
                }
            }
        }
    }

    public void depthFirstTraversal(Vertex start) {
        ArrayList<Vertex> visitedVertices = new ArrayList<>();
        depthFirstTraversalRecursive(start, visitedVertices);
    }

    private void depthFirstTraversalRecursive(Vertex current, ArrayList<Vertex> visitedVertices) {
        visitedVertices.add(current);
        System.out.print(current.getData()+" ");
        for (Edge e : current.getEdges()) {
            Vertex neighbor = e.getEnd();
            if (!visitedVertices.contains(neighbor)) {
                depthFirstTraversalRecursive(neighbor, visitedVertices);
            }
        }
    }

    public Map<String, Object> shortestPath(int start, int end) {
        Vertex startVertex = getVertexByValue(start);
        Vertex endVertex = getVertexByValue(end);
        if (startVertex == null || endVertex == null) {
            System.out.println("Start or end vertex not found!");
            return null;
        }

        // Initialisierung der Distanzen und Vorgänger
        HashMap<Vertex, Integer> distances = new HashMap<>();
        HashMap<Vertex, Vertex> previousVertices = new HashMap<>();
        for (Vertex v : vertices) {
            distances.put(v, Integer.MAX_VALUE);
            previousVertices.put(v, null);
        }
        distances.put(startVertex, 0);

        // Set für unbesuchte Knoten
        Set<Vertex> unvisited = new HashSet<>(vertices);

        while (!unvisited.isEmpty()) {
            // Knoten mit der geringsten Distanz auswählen
            Vertex current = null;
            for (Vertex v : unvisited) {
                if (current == null || distances.get(v) < distances.get(current)) {
                    current = v;
                }
            }

            // Wenn der Endknoten erreicht ist, brechen Sie ab
            if (current == endVertex) {
                break;
            }

            unvisited.remove(current);

            // Aktualisieren der Distanzen zu benachbarten Knoten
            for (Edge e : current.getEdges()) {
                Vertex neighbor = e.getEnd();
                int newDistance = distances.get(current) + e.getWeight();
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previousVertices.put(neighbor, current);
                }
            }
        }

        // Pfad vom Startknoten zum Endknoten rekonstruieren
        ArrayList<Vertex> path = new ArrayList<>();
        Vertex current = endVertex;
        while (current != null) {
            path.add(current);
            current = previousVertices.get(current);
        }
        Collections.reverse(path);

        // Rückgabe der kürzesten Distanz und des Pfades
        Map<String, Object> result = new HashMap<>();
        result.put("distance", distances.get(endVertex));
        result.put("path", path);
        return result;


    /*
    public static Dictionary[] dijkstra (Graph g, Vertex startingVertex){
        Dictionary<String, Integer> distances = new Hashtable<>();
        Dictionary<String, Vertex> previous = new Hashtable<>();
        PriorityQueue<QueueObject> queue = new PriorityQueue<QueueObject>();

        distances.put(startingVertex.getData(), 0);
        queue.add(new QueueObject(startingVertex, 0));

        for (Vertex v: g.getVertices()) {
            if(v != startingVertex){
                distances.put(v.getData(), Integer.MAX_VALUE);
            }
            previous.put(v.getData(), new Vertex("Null"));
        }

        while(queue.size() != 0){
            Vertex current = queue.poll().vertex;
            for (Edge e: current.getEdges()) {
                Integer alternate = distances.get(current.getData()) + e.getWeight();
                String neighborValue = e.getEnd().getData();
                if (alternate < distances.get(neighborValue)){
                    distances.put(neighborValue, alternate);
                    previous.put(neighborValue, current);
                    queue.add(new QueueObject(e.getEnd(), distances.get(neighborValue)));
                }
            }
        }

        return new Dictionary[]{distances, previous};
    }
 */
    }
}
