public class Number2Chars {
    public static void main (String[] args) {
        String number = "3176742749";
        System.out.println();
        System.out.println(number);
        System.out.println();
        int[] list = long2list(number);
        for (int v: list) {
            System.out.print(v + " -");
            char[] chars = toChar(v);
            for (int i = 0; i < 4; i++) {
                System.out.print(" ");
                if (i + chars.length >= 4) {
                    System.out.print(chars[i-(4-chars.length)]);
                }
            }
            System.out.println();
        }
    }
//0
    //0
    //0 1
    //  0 1 2
    // 0 1 2 3
    public static char[] toChar(int i) {
        return switch (i) {
            case 0 -> new char[] {'0'};
            case 1 -> new char[] {'1'};
            case 2 -> new char[] {'a','b','c'};
            case 3 -> new char[] {'d','e','f'};
            case 4 -> new char[] {'g','h','i'};
            case 5 -> new char[] {'j','k','l'};
            case 6 -> new char[] {'m','n','o'};
            case 7 -> new char[] {'p','q','r','s'};
            case 8 -> new char[] {'t','u','v'};
            case 9 -> new char[] {'w','x','y','z'};
            default-> new char[] {' '};
        };
    }

    public static int[] long2list (String s) {
        int[] out = new int[s.length()];
        for (int i = 0; i < s.length(); i++) out[i] = Integer.parseInt(s.substring(i,i+1));
        return out;
    }
}
