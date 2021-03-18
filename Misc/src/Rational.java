import java.util.ArrayList;

public class Rational {
    private final float sign;
    private Val num;
    private Val den;

    public static Rational NaN = new Rational(0,0);
    public static Rational POSITIVE_INFINITY = new Rational(1,0);
    public static Rational NEGATIVE_INFINITY = new Rational(-1,0);


    public static void main(String[] args) {
        Rational x = new Rational(9, 6);
        Rational y = new Rational(5, 7);
        Rational xpy = x.mul(y);
        Rational ypx = y.mul(x);
        System.out.println("  x = " + x);
        System.out.println("  y = " + y);
        System.out.println("x*y = " + xpy);
        System.out.println("y*x = " + ypx);
    }

    public Rational () { this(1); }

    public Rational (long integer) {
        this(integer, 1);
    }

    public Rational (long numerator, long denominator) {
        this(Rational.getSign(numerator, denominator),new Val(numerator),new Val(denominator));
    }

    public Rational (float sign, Val numerator, Val denominator) {
        int i = Float.floatToIntBits(sign);
        this.sign = Float.intBitsToFloat(i == 0x80000000 ? 0x0 : i);
        this.num = numerator;
        this.den = denominator;
        this.reduce();
    }

    private static float getSign(long n, long d) {
        long m = n * d;
        //return m==0L ? n==0L ? d==0L ? Float.NaN : 0f : n<0L ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY : m<0L ? -1f : 1f;
        float o;
        if (m == 0L) {
            if (n == 0L) {
                o = d == 0L ? Float.NaN : 0f;
            } else {
                o = n < 0L ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
            }
        } else {
            o = m < 0L ? -1f : 1f;
        }
        return o;
    }

    public double toDouble () {
        return (double)this.num.product() / (double)this.den.product();
    }

    public void reduce () {
        if (Math.abs(this.sign) == Float.POSITIVE_INFINITY) {
            this.num = new Val(1);
            this.den = new Val(0);
        }
        else if (this.sign == 0f) {
            this.num = new Val(0);
            this.den = new Val(1);
        }
        else if (this.sign == this.sign) {
            ArrayList<Integer> on = new ArrayList<>(this.num.factors.size());
            on.addAll(this.num.factors);
            this.num.factors.clear();
            ArrayList<Integer> od = new ArrayList<>(this.den.factors.size());
            od.addAll(this.den.factors);
            this.den.factors.clear();
            this.reduceAux(on, od);
        }
    }

    private void reduceAux (ArrayList<Integer> n, ArrayList<Integer> d) {
        if (!n.isEmpty() && !d.isEmpty()) {
            int cn = n.get(0);
            int cd = d.get(0);
            if (cn == cd) {
                n.remove(0);
                d.remove(0);
            }
            else if (cn < cd) this.num.factors.add(n.remove(0));
            else this.den.factors.add(d.remove(0));
            this.reduceAux(n, d);
        }
        else if (!d.isEmpty()) {
            this.den.factors.addAll(d);
        }
        else if (!n.isEmpty()) {
            this.num.factors.addAll(n);
        }
    }

    public String toString() {
             if (this.sign == Float.NEGATIVE_INFINITY) return "-1/0";
        else if (this.sign == Float.POSITIVE_INFINITY) return "1/0";
        else if (!(this.sign == this.sign)) return "0/0";
        else if (this.sign == 0f) return "0/1";
        else    return this.num.product() + "/" + this.den.product();
    }

    public Rational mul(Rational other) {
        Val n = this.num.mul(other.num);
        Val d = this.den.mul(other.den);
        return new Rational(this.sign * other.sign,n,d);
    }

    public boolean equals (Rational other) {
        this.reduce();
        other.reduce();
        if (Float.floatToIntBits(this.sign) == Float.floatToIntBits(other.sign)){//this.pos == other.pos) {
            if (this.num.product() == other.num.product()) {
                if (this.den.product() == other.den.product()) {
                    return true;
                }
            }
        }
        return false;
    }
/*
    public int compare (Rational other) {
        if (this.pos && ! other.pos){}
    }

    public Rational add(Rational other) {
        int numer = this.num * other.den + other.num * this.den;
        int denom = this.den * other.den;
        return new Rational(numer, denom);
    }

    public Rational sub(Rational other) {
        int numer = this.num * other.den - other.num * this.den;
        int denom = this.den * other.den;
        return new Rational(numer, denom);
    }

    public Rational div(Rational other) {
        int numer = this.num * other.den;
        int denom = this.den * other.num;
        return new Rational(numer, denom);
    }*/

    private static class Val {
        public ArrayList<Integer> factors;
        public static Val ZERO = new Val(0);
        public static Val ONE = new Val(1);

        public Val (long value) {
            value = Math.abs(value);
                 if (value == 0) this.factors = null;
            else if (value == 1) this.factors = new ArrayList<>();
            else                 this.factor(value);
        }

        public Val () { this.factors = new ArrayList<>(); }

        public Val (Val v) { this(v.factors); }

        public Val (ArrayList<Integer> v) {
            if (v == null) this.factors = null;
            this.factors = new ArrayList<>();
            this.factors.addAll(v);
        }

        public boolean is0 () {
            return this.factors == null;
        }

        public boolean is1 () {
            if (this.is0()) return false;
            else            return this.factors.isEmpty();
        }

        public void factor (long n) {
            if (n < 0) this.factor(-n);
            int max = (int)Math.ceil(Math.sqrt(n));
            this.factors = new ArrayList<>();
            this.factorAux2(n, max);
        }

        private void factorAux (long n, int c, int max) {
            if (n == 1) { /*break the loop*/ }
            else {
                if (max < c) {
                    this.factors.add((int) n);
                    // break the loop
                }
                else if (n % c == 0) {
                    this.factors.add(c);
                    this.factorAux(n / c, c, max);
                }
                else this.factorAux(n, c + 2, max);
            }
        }

        private void factorAux2 (long n, int max) {
            if (n == 1) { /*break the loop*/ }
            else {
                if (n % 2 == 0) {
                    this.factors.add(2);
                    this.factorAux2(n / 2, max);
                } else {
                    this.factorAux(n, 3, max);
                }
            }
        }

        public Val mul (Val other) {
            if (this.is0() || other.is0()) return new Val(0);
            if (this.is1() && other.is1()) return new Val(1);
            if (this.is1()) return new Val(other);
            if (other.is1()) return new Val(this);
            ArrayList<Integer> t = new ArrayList<>(this.factors.size());
            ArrayList<Integer> o = new ArrayList<>(other.factors.size());
            t.addAll(this.factors);
            o.addAll(other.factors);
            return new Val(Val.merge(t, o, new ArrayList<>()));
        }

        private static ArrayList<Integer> merge (ArrayList<Integer> v0, ArrayList<Integer> v1, ArrayList<Integer> o) {
            if (!v0.isEmpty() && !v1.isEmpty()) {
                int i0 = v0.get(0);
                int i1 = v1.get(0);
                if (i0 <= i1) {
                    o.add(v0.remove(0));
                }
                if (i1 <= i0) {
                    o.add(v1.remove(0));
                }
                return Val.merge(v0, v1, o);
            }
            if ( v0.isEmpty() && !v1.isEmpty()) o.addAll(v1);
            if (!v0.isEmpty() &&  v1.isEmpty()) o.addAll(v0);
            return o;
        }

        public long product() {
            if (this.factors == null) return 0;
            ArrayList<Integer> arr = new ArrayList<>(this.factors.size());
            arr.addAll(this.factors);
            return Val.product(arr);
        }

        private static long product (ArrayList<Integer> arr) {
            if (arr.size() < 1) return 1;
            long v = arr.remove(0);
            if (arr.size() < 1) return v;
            return v * Val.product(arr);
        }

        public String toString () {
            return Long.toString(this.product());
        }
    }

    public static class Par<E> {
        public E left;
        public E right;
        public Par (E left, E right){
            this.left  = left;
            this.right = right;
        }
        public Par () {}
    }
}