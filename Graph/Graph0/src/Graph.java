import java.util.HashSet;
import java.util.Set;

public class Graph<E> {
    public Set<Node<E>> nodes;

    public Graph () {
        this.nodes = new HashSet<>();
    }
}
