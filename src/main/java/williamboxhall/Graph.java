package williamboxhall;

import java.util.Set;

public interface Graph {
    boolean adjacent(String source, String destination);

    void add(String source, String destination);

    void delete(String source, String destination);

    Set<String> neighbors(String source);
}
