public class Tester
{
    public static void main (String[] args)
    {
        Matrix.Boolean b = new Matrix.Boolean(new int[][]
            {
                {1,0,0,0},
                {1,0,0,0},
                {1,0,0,0},
                {1,1,1,1}
            }
        );
        
        System.out.println("*");
        System.out.println(b);
        
        Matrix.Boolean m = b.multiply(b);
        System.out.println(m);
    }
}