package williamboxhall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AdjacencyListDirectedGraphTest {
    @Test
    public void isAGraph() {
        assertThat(Graph.class.isAssignableFrom(AdjacencyListDirectedGraph.class), is(true));
    }

    @Test
    public void addsASingleEdgeBetweenTwoNodes() {
        Graph graph = new AdjacencyListDirectedGraph();
        assertThat(graph.adjacent("x", "y"), is(false));

        graph.add("x", "y");
        assertThat(graph.adjacent("x", "y"), is(true));
    }

    @Test
    public void canAddAnEdgeToNodesThatAlreadyHaveAnEdge() {
        Graph graph = new AdjacencyListDirectedGraph();

        graph.add("x", "y");
        assertThat(graph.adjacent("x", "y"), is(true));

        graph.add("x", "y");
        assertThat(graph.adjacent("x", "y"), is(true));
    }

    @Test
    public void addsSeveralEdgesToSeveralNodes() {
        Graph graph = new AdjacencyListDirectedGraph();
        assertThat(graph.adjacent("x", "y"), is(false));
        assertThat(graph.adjacent("y", "z"), is(false));
        assertThat(graph.adjacent("z", "x"), is(false));

        graph.add("x", "y");
        graph.add("y", "z");
        graph.add("z", "x");
        assertThat(graph.adjacent("x", "y"), is(true));
        assertThat(graph.adjacent("y", "z"), is(true));
        assertThat(graph.adjacent("z", "x"), is(true));
    }

    @Test
    public void addsMultipleEdgesOffOneNode() {
        Graph graph = new AdjacencyListDirectedGraph();
        assertThat(graph.adjacent("x", "y"), is(false));
        assertThat(graph.adjacent("x", "z"), is(false));

        graph.add("x", "y");
        graph.add("x", "z");
        assertThat(graph.adjacent("x", "y"), is(true));
        assertThat(graph.adjacent("x", "z"), is(true));
    }

    @Test
    public void removesAnEdgeBetweenTwoNodes() {
        Graph graph = new AdjacencyListDirectedGraph();
        assertThat(graph.adjacent("x", "y"), is(false));

        graph.add("x", "y");
        assertThat(graph.adjacent("x", "y"), is(true));

        graph.delete("x", "y");
        assertThat(graph.adjacent("x", "y"), is(false));
    }

    @Test
    public void removeDoesNothingWhenEdgeDoesNotExistBeforeRemoval() {
        Graph graph = new AdjacencyListDirectedGraph();
        graph.delete("x", "y");
        assertThat(graph.adjacent("x", "y"), is(false));
    }

    @Test
    public void canListNeighborsForANode() {
        Graph graph = new AdjacencyListDirectedGraph();
        graph.add("x", "y");
        graph.add("x", "z");

        assertThat(graph.neighbors("x").size(), is(2));
        assertThat(graph.neighbors("x").contains("y"), is(true));
        assertThat(graph.neighbors("x").contains("z"), is(true));
    }

    @Test
    public void listsNothingForNodeWithNoNeighbors() {
        Graph graph = new AdjacencyListDirectedGraph();

        assertThat(graph.neighbors("x").size(), is(0));
        assertThat(graph.neighbors("x").contains("y"), is(false));

        graph.add("x", "y");
        graph.delete("x", "y");
        assertThat(graph.neighbors("x").size(), is(0));
        assertThat(graph.neighbors("x").contains("y"), is(false));
    }

    @Test
    public void allowsBreadthFirstTraversal() {
        assertThat(complexUndirectedGraph().breadthFirstTraversalFrom("a").toString(), is("[a, b, c, f, d, e, h, g, j, i]"));
    }

     @Test
    public void allowsDepthFirstTraversal() {
        assertThat(complexUndirectedGraph().depthFirstTraversalFrom("a").toString(), is("[a, b, d, c, h, f, g, e, j, i]"));
    }

    private Graph complexUndirectedGraph() {
        Graph graph = new AdjacencyListDirectedGraph();
        bidirectionallyAssociate(graph, "a", "b");
        bidirectionallyAssociate(graph, "a", "c");
        bidirectionallyAssociate(graph, "a", "f");
        bidirectionallyAssociate(graph, "b", "d");
        bidirectionallyAssociate(graph, "b", "e");
        bidirectionallyAssociate(graph, "c", "d");
        bidirectionallyAssociate(graph, "c", "h");
        bidirectionallyAssociate(graph, "e", "d");
        bidirectionallyAssociate(graph, "e", "g");
        bidirectionallyAssociate(graph, "e", "j");
        bidirectionallyAssociate(graph, "f", "h");
        bidirectionallyAssociate(graph, "h", "d");
        bidirectionallyAssociate(graph, "h", "g");
        bidirectionallyAssociate(graph, "g", "i");
        bidirectionallyAssociate(graph, "i", "j");
        return graph;
    }

    private void bidirectionallyAssociate(Graph graph, String source, String destination) {
        graph.add(source, destination);
        graph.add(destination, source);
    }
}
