import java.util.HashSet;

public class SetListRelation<E> extends SetRelation<E>
{
    private HashSet<Pair<E,E>> relation;

    public SetListRelation (HashSet<Pair<E,E>> relation)
    {
        this.relation = relation;
    }

    public SetListRelation ()
    {
        this(new HashSet<>());
    }

    public SetListRelation (E[][] relation)
    {
        this(new HashSet<>());
        this.addAll(relation);
    }

    public void addAll (E[][] relation)
    {
        for (E[] pair : relation)
        {
            if (2 < pair.length)
            {
                this.addToRelation(pair[0], pair[1]);
            }
        }
    }

    public void addAll (E[] relation0, E[] relation1)
    {
        Pair<E,E>[] pairs = Pair.zip(relation0, relation1);

        for (Pair<E,E> pair : pairs)
        {
            this.addToRelation(pair);
        }
    }

    public void addToRelation (Pair<E,E> pair)
    {
        this.relation.add(pair);
        this.domrange.left.add(pair.left);
        this.domrange.right.add(pair.right);
    }

    public void addToRelation (E a, E b)
    {
        this.addToRelation(new Pair<>(a, b));
    }

    @Override
    public boolean relates(E a, E b) throws DomainException
    {
        return this.relation.contains(new Pair<>(a, b));
    }

    @Override
    public boolean hasExplicitDomain ()
    {
        return true;
    }

    @Override
    public Pair<HashSet<E>,HashSet<E>> getDomain (int count) {
        HashSet<E> domain = new HashSet<>();
        HashSet<E> range = new HashSet<>();
        for (Pair<E,E> pair : this.relation)
        {
            domain.add(pair.left);
            range.add(pair.right);
        }
        return new Pair<>(domain, range);
    }
}
