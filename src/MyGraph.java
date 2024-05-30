import java.util.*;

public class MyGraph<T> {
    private final boolean undirected;
    private final Map<Vertex<T>, List<Vertex<T>>> map = new HashMap<>();

    public MyGraph() {
        this(true);
    }

    public MyGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(Vertex<T> v) {
        if (!map.containsKey(v)) {
            map.put(v, new LinkedList<>());
        }
    }

    public void addEdge(Vertex<T> source, Vertex<T> dest) {
        if (!map.containsKey(source)) {
            addVertex(source);
        }

        if (!map.containsKey(dest)) {
            addVertex(dest);
        }

        if (!map.get(source).contains(dest) && !source.equals(dest)) {
            map.get(source).add(dest);
            if (undirected) {
                map.get(dest).add(source);
            }
        }
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<T> v : map.keySet()) {
            count += map.get(v).size();
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
        if (!map.containsKey(source)) return false;
        return map.get(source).contains(dest);
    }

    public List<Vertex<T>> adjacencyList(Vertex<T> v) {
        return map.getOrDefault(v, new LinkedList<>());
    }
}
