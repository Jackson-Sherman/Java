public class Tester {
    public static void main(String[] args) {
        System.out.println();
        for (int i = 0; i < 10; i++) System.out.println(Float.intBitsToFloat(Tester.randInt()));
        System.out.println();
        int a = Float.floatToIntBits(0f);
        float[] fs = {-1f, 0f, 1f, Float.NEGATIVE_INFINITY, Float.NaN, Float.POSITIVE_INFINITY};
        String s = "";
        for (int i = 0; i < 6; i++) {
            s += "\n\t";
            String m = Float.toString(fs[i]);
            while (m.length() < 9) m = " " + m;
            s += m;
            s += " : ";
            m = Integer.toBinaryString(Float.floatToIntBits(fs[i]));
            while (m.length() < 32) m = "0" + m;
            s += m;
            for (int j = i; j < 6; j++) {
                System.out.println(f2s(fs[i]) + " * " + f2s(fs[j]) + " = " + f2s(fs[i]*fs[j]));
            }
        }
        System.out.println();
        System.out.println("{" + s + "\n}");
    }
    public static int randInt () {
        return Integer.parseUnsignedInt(Tester.randIntAux(""),2);
    }
    private static String randIntAux (String s) {
        if (s.length() < 32) {
            String c = Math.random() < 0.5 ? "0" : "1";
            return Tester.randIntAux(s.concat(c));
        }
        else return s;
    }
    public static String f2s (float v) {
        return switch (Float.floatToIntBits(v)) {
            case 0xbf800000 -> "-1/1";
            case 0x00000000 -> " 0/1";
            case 0x80000000 -> "-0/1";
            case 0x3f800000 -> " 1/1";
            case 0xff800000 -> "-1/0";
            case 0x7fc00000 -> " NaN";
            case 0x7f800000 -> " 1/0";
            default -> "";
        };
    }
    public static float getSign (long n, long d) {
        long m = n * d;float nf = Float.NEGATIVE_INFINITY, pf = Float.POSITIVE_INFINITY;
        return m == 0L ? (n == 0L ? (d == 0L ? Float.NaN : 0f) : (n < 0L ? nf: pf)) : (m < 0L ? -1f : 1f);
    }
    public static float getSign2 (long n, long d) {
        long m = n * d;float nf = Float.NEGATIVE_INFINITY, pf = Float.POSITIVE_INFINITY;
        return m == 0L ? n == 0L ? d == 0L ? Float.NaN : 0f : n < 0L ? nf: pf : m < 0L ? -1f : 1f;
    }
    public static int rand (int dom, int max) {
        if (dom == 0) return 0;
        int r = (int)Math.floor(1 + max * Math.random());
        if (dom < 0) r *= -1;
        return r;
    }
    public static String form (int n, int s) {
        if (Integer.toString(Math.abs(n)).length() % 2 != s % 2) return " " + Tester.form(n,s-1);
        boolean neg = n < 0;
        String st = Integer.toString(Math.abs(n));
        while (st.length() < s) {
            if (neg) {
                neg = false;
                st = "-" + st;
            }
            else {
                st = " " + st;
            }
            st = st + " ";
        }
        return st;
    }
}
