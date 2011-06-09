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
import java.util.Stack;

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

    public List<String> depthFirstTraversalFrom(String source) {
        return new DepthFirstTraversal().from(source);
    }

    private Set<String> adjacencyListFor(String source) {
        if (!nodeToAdjacencyList.containsKey(source)) {
            nodeToAdjacencyList.put(source, new HashSet<String>());
        }
        return nodeToAdjacencyList.get(source);
    }

    private class BreadthFirstTraversal {
        private Queue<String> queue;
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
                if (!traversal.contains(neighbor)) {
                    visit(neighbor);
                }
            }
        }

        private void visit(String node) {
            queue.add(node);
            traversal.add(node);
        }

        private void resetCollections() {
            queue = new LinkedList<String>();
            traversal = new ArrayList<String>();
        }
    }

    private class DepthFirstTraversal {
        private Stack<String> stack;
        private List<String> traversal;

        public List<String> from(String source) {
            resetCollections();
            visit(source);
            while (stackHasItemsToProcess()) {
                processNextItemInStack();
            }
            return traversal;
        }

        private boolean stackHasItemsToProcess() {
            return !stack.isEmpty();
        }

        private void processNextItemInStack() {
            String node = stack.peek();
            for (String neighbor : neighbors(node)) {
                if (!traversal.contains(neighbor)) {
                    visit(neighbor);
                    return;
                }
            }
            stack.pop();
        }

        private void visit(String node) {
            stack.push(node);
            traversal.add(node);
        }

        private void resetCollections() {
            stack = new Stack<String>();
            traversal = new ArrayList<String>();
        }
    }
}
