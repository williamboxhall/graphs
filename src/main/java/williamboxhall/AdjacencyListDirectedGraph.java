package williamboxhall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AdjacencyListDirectedGraph implements Graph {
    private Map<String, Set<String>> nodeToAdjacencyList = new HashMap<String, Set<String>>();

    public boolean adjacent(String source, String destination) {
        return adjacencyListFor(source).contains(destination);
    }

    public void add(String source, String destination) {
        adjacencyListFor(source).add(destination);
    }

    public void delete(String source, String destination) {
        adjacencyListFor(source).remove(destination);
    }

    public List<String> neighbors(String source) {
        List<String> neighbors = new ArrayList<String>(adjacencyListFor(source));
        Collections.sort(neighbors);
        return neighbors;
    }

    public List<String> breadthFirstTraversalFrom(String source) {
        return new BreadthFirstTraversal().from(source);
    }

    private Set<String> adjacencyListFor(String source) {
        if (!nodeToAdjacencyList.containsKey(source)) {
            nodeToAdjacencyList.put(source, new HashSet<String>());
        }
        return nodeToAdjacencyList.get(source);
    }

    private class BreadthFirstTraversal {
        private Queue<String> queue;
        private Set<String> visited;
        private List<String> traversal;

        private List<String> from(String source) {
            resetCollections();
            visit(source);
            while (queueHasItemsToProcess()) {
                processNextItemInQueue();
            }
            return traversal;
        }

        private boolean queueHasItemsToProcess() {
            return !queue.isEmpty();
        }

        private void processNextItemInQueue() {
            String node = queue.remove();
            for (String neighbor : neighbors(node)) {
                if (!visited.contains(neighbor)) {
                    visit(neighbor);
                }
            }
        }

        private void visit(String neighbor) {
            visited.add(neighbor);
            queue.add(neighbor);
            traversal.add(neighbor);
        }

        private void resetCollections() {
            queue = new LinkedList<String>();
            visited = new HashSet<String>();
            traversal = new ArrayList<String>();
        }
    }
}
