package williamboxhall;

import java.util.List;

public interface Graph {
    boolean adjacent(String source, String destination);

    void add(String source, String destination);

    void delete(String source, String destination);

    List<String> neighbors(String source);

    List<String> breadthFirstTraversalFrom(String source);
}
