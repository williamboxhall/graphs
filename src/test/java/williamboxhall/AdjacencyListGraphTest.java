package williamboxhall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AdjacencyListGraphTest {
    @Test
    public void isAGraph() {
        assertThat(Graph.class.isAssignableFrom(AdjacencyListGraph.class), is(true));
    }

    @Test
    public void addsAnEdgeBetweenTwoNodes() {
        Graph graph = new AdjacencyListGraph();
        assertThat(graph.adjacent("x", "y"), is(false));

        graph.add("x", "y");
        assertThat(graph.adjacent("x", "y"), is(true));
    }

    @Test
    public void canAddAnEdgeToNodesThatAlreadyHaveAnEdge() {
        Graph graph = new AdjacencyListGraph();

        graph.add("x", "y");
        assertThat(graph.adjacent("x", "y"), is(true));

        graph.add("x", "y");
        assertThat(graph.adjacent("x", "y"), is(true));
    }
}
