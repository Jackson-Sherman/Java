import java.util.HashSet;

public abstract class SetRelation<E>
{
    public Pair<HashSet<E>,HashSet<E>> domrange;

    public abstract boolean hasExplicitDomain ();

    public Pair<HashSet<E>,HashSet<E>> getDomain ()
    {
        return this.getDomain(20);
    }

    public abstract Pair<HashSet<E>,HashSet<E>> getDomain (int count);

    public abstract boolean relates (E a, E b) throws DomainException;

    /**
     * Returns a 2d boolean relation array.
     * @param domain domain on which to check
     * @param range range on which to check
     * @return a 2d array of booleans with size n
     */
    boolean[][] relation_matrix (HashSet<E> domain, HashSet<E> range)
    {

    }

    /**
     * Returns a 2d boolean relation array.
     * @param domain the domain and range of the relation
     * @return a 2d array of booleans
     */
    boolean[][] relation_matrix (HashSet<E> domain)
    {
        return this.relation_matrix(domain, domain);
    }

    /**
     * Returns a 2d boolean relation array.
     * @param domrange the domain and range of the relation
     * @return a 2d array of booleans
     */
    boolean[][] relation_matrix (Pair<HashSet<E>,HashSet<E>> domrange)
    {
        return this.relation_matrix(domrange.left, domrange.right);
    }

    /**
     * Returns a 2d boolean relation array.
     * @return a 2d array of booleans with size n
     */
    public boolean[][] relation_matrix ()
    {
        return this.relation_matrix(this.getDomain(10));
    }

    public String matrixToString (boolean[][] matrix, Pair<E[] >)
}

class DomainException extends Exception
{
    public DomainException (String s)
    {
        super(s);
    }
}

