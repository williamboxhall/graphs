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
        assertThat(graph.adjacent("x", "y"), is(false));

        graph.delete("x", "y");
        assertThat(graph.adjacent("x", "y"), is(false));
    }
}
