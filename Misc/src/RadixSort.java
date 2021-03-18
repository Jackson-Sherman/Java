import java.util.ArrayList;

public class RadixSort {
    public static void main (String[] args) {
        int length = 10;
        ArrayList<Integer> vs = new ArrayList<>(length);
        for (int i = 0; i < length; i++) vs.add((int)(1000*Math.random()));

        RadixSort r = new RadixSort(vs,10);
        System.out.println(r.data);
        int[] sorted = r.sort();
        System.out.println("{");
        for (int each : sorted) {
            System.out.println("  " + each);
        }
        System.out.println("}");
    }

    private ArrayList<Integer> data;
    private final int base;
    private int[] radixIndex;
    private int l;

    public RadixSort (ArrayList<Integer> data, int base) {
        this.data = data;
        this.base = base;
        int l = Integer.MIN_VALUE;
        this.radixIndex = new int[this.base];
        for (int i : this.data) {
            this.l = Math.max(i,this.l);
        }
        this.l = Integer.toString(this.l).length();
    }

    public RadixSort (int[] data, int base) {
        this.data = new ArrayList<Integer>(data.length);
        for (int i : data) this.data.add(i);
        this.base = base;
        int l = Integer.MIN_VALUE;
        this.radixIndex = new int[this.base];
        for (int i : this.data) {
            this.l = Math.max(i,this.l);
        }
        this.l = Integer.toString(this.l).length();
    }

    public int[] sort () {
        ArrayList<Integer> d = (ArrayList<Integer>) this.data.clone();
        int[] o = new int[d.size()];
        for (int i = 0; i < this.base; i++) {
            this.radixIndex[i] = 0;
        }
        int index = 0;
        while (index < this.l) {
            ArrayList<Integer> radix = new ArrayList<>(d.size());
            for (int val : d) {
                int ind = this.digit(val,index);
                if (this.radixIndex[ind] < radix.size()) radix.add(this.radixIndex[ind],val);
                else radix.add(val);
                this.inc(ind);
            }
            d = (ArrayList<Integer>) radix.clone();
        }
        for (int i = 0; i < d.size(); i++) {
            o[i] = d.get(i);
        }
        return o;
    }

    public void inc (int index) {
        for (int i = index+1; i < this.radixIndex.length; i++) this.radixIndex[i]++;
    }

    public int digit (int number, int index) {
        if (index < 0) return this.digit(number, 0);
        if (index > 0) return this.digit(number/this.base,--index);
        return number % base;
    }
}
