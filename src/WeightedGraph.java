import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WeightedGraph<T> {
    private final boolean undirected;
    private final Map<Vertex<T>, List<Edge<Vertex<T>>>> map = new HashMap<>();

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(Vertex<T> v) {
        if (hasVertex(v))
            return;

        map.put(v, new LinkedList<>());
    }

    public void addEdge(Vertex<T> source, Vertex<T> dest, double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest)
                || source.equals(dest))
            return;

        map.get(source).add(new Edge<>(source, dest, weight));

        if (undirected)
            map.get(dest).add(new Edge<>(dest, source, weight));
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<T> v : map.keySet()) {
            count += map.get(v).size();
        }

        if (undirected)
            count /= 2;

        return count;
    }

    public boolean hasVertex(Vertex<T> v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(Vertex<T> source, Vertex<T> dest) {
        if (!hasVertex(source)) return false;

        return map.get(source).contains(new Edge<>(source, dest));
    }

    public List<Vertex<T>> adjacencyList(Vertex<T> v) {
        if (!hasVertex(v)) return null;

        List<Vertex<T>> vertices = new LinkedList<>();
        for (Edge<Vertex<T>> e : map.get(v)) {
            vertices.add(e.getDest());
        }

        return vertices;
    }

    public Iterable<Edge<Vertex<T>>> getEdges(Vertex<T> v) {
        if (!hasVertex(v)) return null;

        return map.get(v);
    }
}