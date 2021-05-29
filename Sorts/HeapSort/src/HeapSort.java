public class HeapSort<E extends Comparable<E>> {
    private E[] data;
    private int sortedIndex;

    public HeapSort (E[] data) {
        this.data = data;
        this.sortedIndex = data.length;
    }

    public void makeValid () {

    }

    private int parent (int i) {
        return (i - 1) / 2;
    }

    private int leftChild (int i) {
        return 2 * i + 1;
    }

    private int rightChild (int i) {
        return 2 * i + 2;
    }
}
