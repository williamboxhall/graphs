package williamboxhall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyListGraph implements Graph {
    private Map<String, List<String>> nodeToAdjacencyList = new HashMap<String, List<String>>();

    public boolean adjacent(String source, String destination) {
        List<String> adjacencyList = nodeToAdjacencyList.get(source);
        return adjacencyList != null && adjacencyList.contains(destination);
    }

    public void add(String source, String destination) {
        nodeToAdjacencyList.put(source, listContaining(destination));
    }

    private List<String> listContaining(String destination) {
        List<String> list = new ArrayList<String>();
        list.add(destination);
        return list;
    }

    public void delete(String source, String destination) {
    }

    public Set<String> neighbors(String source) {
        return null;
    }
}
