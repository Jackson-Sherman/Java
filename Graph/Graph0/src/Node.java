import java.util.HashSet;
import java.util.Set;

public class Node<T> {
    public Set<Node<T>> set;
    public T value;

    public Node () {
        this(null);
    }

    public Node (T value) {
        this.value = value;
        this.set = new HashSet<>();
    }
}
