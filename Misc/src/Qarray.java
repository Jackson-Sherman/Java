public class Qarray<E> {
    private E[] array;
    private int head;
    private int tail;

    public Qarray (int maxLength) {
        this.array = (E[]) new Object[maxLength+1];
        this.head = 0;
        this.tail = 0;
    }

    public Qarray (E[] values) {
        assert this.array != null;
        System.arraycopy(values, 0, this.array, 0, values.length+1);
        this.head = 0;
        this.tail = this.array.length-2;
    }

    public boolean isEmpty () { return this.head==this.tail; }
    public boolean isFull () { return (this.tail+1)%this.array.length==this.head; }

    public int size () {
        int s = this.tail < this.head ? this.array.length : 0;
        s += this.tail - this.head;
        return s;
    }

    public void enqueue (E value) {
        if (!this.isFull()) {
            this.array[this.tail] = value;
            this.tail++;
            this.tail %= this.array.length;
        }
    }

    public E dequeue () {
        E value = null;
        if (!this.isEmpty()) {
            value = this.array[this.head++];
            this.head %= this.array.length;
        }
        return value;
    }
}
