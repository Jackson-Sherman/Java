import java.util.Objects;

public class Pair<A,B>
{
    public A left;
    public B right;

    public Pair (A left, B right)
    {
        this.left = left;
        this.right = right;
    }

    public Pair ()
    {
        this(null, null);
    }

    public static <C,D> Pair<C,D>[] zip (C[] lefts, D[] rights)
    {
        assert lefts.length == rights.length;
        Pair<C,D>[] output = (Pair<C,D>[])new Object[lefts.length];
        for (int i = 0; i < output.length; i++)
        {
            output[i] = new Pair<>(lefts[i],rights[i]);
        }
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Pair<?,?> pair = (Pair<?,?>) o;
        return Objects.equals(left, pair.left) && Objects.equals(right, pair.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
