import java.util.*;

public class DepthFirstSearch<T> extends Search<T> {
    public DepthFirstSearch(MyGraph<T> graph, Vertex<T> source) {
        super(source);

        dfs(graph, source);
    }

    private void dfs(MyGraph<T> graph, Vertex<T> current) {
        marked.add(current);

        for (Vertex<T> v : graph.adjacencyList(current)) {
            if (!marked.contains(v)) {
                edgeTo.put(v, current);
                dfs(graph, v);
            }
        }
    }
}
