import java.util.HashSet;

public class Main
{
    public static SetListRelation<Integer> sRel = new SetListRelation<>(new Integer[][]
        {
            {1,1},{1,2},{1,3},{2,1},{2,2},{2,3},{2,4},{3,1},{3,3}
        }
    );
    public static void main (String[] args)
    {
        boolean[][] relmat = Main.sRel.relation_matrix();
    }
}

//class S extends SetRelation<Integer>
//{
//    @Override
//    public boolean hasExplicitDomain() {
//        return false;
//    }
//
//    @Override
//    public boolean relates (Integer a, Integer b) throws DomainException
//    {
//        return false;
//    }
//}