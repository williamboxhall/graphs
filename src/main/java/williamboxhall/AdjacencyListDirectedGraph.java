package williamboxhall;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AdjacencyListDirectedGraph implements Graph {
    private Map<String, Set<String>> nodeToAdjacencyList = new HashMap<String, Set<String>>();

    public boolean adjacent(String source, String destination) {
        return adjacencyListFor(source).contains(destination);
    }

    public void add(String source, String destination) {
        adjacencyListFor(source).add(destination);
    }

    private Set<String> adjacencyListFor(String source) {
        if (!nodeToAdjacencyList.containsKey(source)) {
            nodeToAdjacencyList.put(source, new HashSet<String>());
        }
        return nodeToAdjacencyList.get(source);
    }

    public void delete(String source, String destination) {
        nodeToAdjacencyList.get(source).remove(destination);
    }

    public Set<String> neighbors(String source) {
        return null;
    }
}
