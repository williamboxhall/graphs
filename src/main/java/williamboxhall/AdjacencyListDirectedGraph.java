package williamboxhall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyListDirectedGraph implements Graph {
    private Map<String, List<String>> nodeToAdjacencyList = new HashMap<String, List<String>>();

    public boolean adjacent(String source, String destination) {
        return adjacencyListFor(source).contains(destination);
    }

    public void add(String source, String destination) {
        adjacencyListFor(source).add(destination);
    }

    private List<String> adjacencyListFor(String source) {
        if (!nodeToAdjacencyList.containsKey(source)) {
            nodeToAdjacencyList.put(source, new ArrayList<String>());
        }
        return nodeToAdjacencyList.get(source);
    }

    public void delete(String source, String destination) {
    }

    public Set<String> neighbors(String source) {
        return null;
    }
}
