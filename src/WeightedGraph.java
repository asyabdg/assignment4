import java.util.*;

public class WeightedGraph<T> {
    private final boolean undirected;
    private final Map<Vertex<T>, Map<Vertex<T>, Double>> map = new HashMap<>();

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(Vertex<T> v) {
        if (hasVertex(v))
            return;

        map.put(v, new HashMap<>());
    }

    public void addEdge(Vertex<T> source, Vertex<T> dest, double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest) || source.equals(dest))
            return;

        map.get(source).put(dest, weight);

        if (undirected) {
            map.get(dest).put(source, weight);
        }
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Map<Vertex<T>, Double> edges : map.values()) {
            count += edges.size();
        }

        if (undirected) {
            count /= 2;
        }

        return count;
    }

    public boolean hasVertex(Vertex<T> v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(Vertex<T> source, Vertex<T> dest) {
        return map.containsKey(source) && map.get(source).containsKey(dest);
    }

    public Map<Vertex<T>, Double> adjacencyList(Vertex<T> v) {
        return map.get(v);
    }
}
