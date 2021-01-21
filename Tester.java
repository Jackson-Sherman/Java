import java.math.BigInteger;
public class Tester {
    public static void main(String[] args){
        BigInteger result;
        if(args.length < 1){
            System.out.println("please input a number in the command line");
        }
        else{
            int index = Integer.parseInt(args[0]);
            result = Tester.fib(index);
            System.out.println("the answer: " + result);
        }
    }
    public static BigInteger fib(int index){
        if (index == 0 || index == 1) return new BigInteger("1");
        else {
            BigInteger old = Tester.fib(index - 1);
            BigInteger older = Tester.fib(index - 2);
            return old.add(older);
        }
    }
}