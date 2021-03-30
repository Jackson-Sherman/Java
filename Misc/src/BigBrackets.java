import java.util.ArrayList;
import java.util.HashMap;

public class BigBrackets {
    public int level;
    public String expression;
    public char bracket;
    public ArrayList<Pair<String,BigBrackets>> data;

    private BigBrackets (String s, int level, char bracket) {
        this.level = level + 1;
        this.bracket = bracket;
        this.expression = s;
        Pair<Character,Pair<Integer,Integer>> pair = BigBrackets.primaryConnective(s);
        if (pair == null)
    }

    public BigBrackets (String s) {
        this(s,0,'\u2e26');
    }

    public BigBrackets () {
        this("");
    }

    public static void main (String[] args) {
        String s = "2 * (3 - (2 + {5, 8} + 1) + {9, 6} + 0)";
    }

    private static Pair<Character,Pair<Integer,Integer>> primaryConnective (String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        char[] opens = "([{".toCharArray();
        char[] closes = ")]}".toCharArray();
        int[][] indeces = {{-1,-1},{-1,-1},{-1,-1}};
        Pair<Character,Pair<Integer,Integer>> pair = null;
        for (int i = 0; i < opens.length; i++) {
            int o = s.indexOf(opens[i]);
            int c = 0 < o ? -1 : s.lastIndexOf(closes[i],o);
            if (0 <= o && 0 <= c) {
                if (pair == null || o < pair.right.left) {
                    pair = new Pair<>(opens[i], new Pair<>(o, c));
                }
            }
        }
        return pair;
    }

    private static char opposite (char c) {
        return switch (c) {
            case '(' -> ')';
            case '{' -> '}';
            case '[' -> ']';
            case ')' -> '(';
            case '}' -> '{';
            case ']' -> '[';
            default -> '~';
        };
    }
}
