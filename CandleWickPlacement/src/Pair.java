public class Pair<A,B> {
    public A x;
    public B y;

    public Pair () {
        this(null, null);
    }

    public Pair (A x, B y) {
        this.x = x;
        this.y = y;
    }
}
