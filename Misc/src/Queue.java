public class Queue<T> {
    public static void main (String[] args) {
        Queue<Integer> original = new Queue<>(new Integer[] {0,1,2,3});
        Queue<Integer> addingit = new Queue<>(new Integer[] {4,5,6,7});
        System.out.println("o  : " + original);
        System.out.println("a  : " + addingit);
        original.append(addingit);
        System.out.println("o+a: " + original);
    }
    private int length;
    private Queue.Node<T> head;
    private Queue.Node<T> tail;

    public Queue () {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public Queue (T[] values) {
        this();
        for (T each : values) this.enqueue(each);
    }

    public Queue (Queue<T>[] qs) {
        this();
        if (qs.length > 0) {
            for (Queue<T> q : qs) {
                while (!q.isEmpty()) {
                    this.enqueue(q.dequeue());
                }
            }
        }
    }

    public Queue (Queue<Queue<T>> qs) {
        this();
        while (!qs.isEmpty()) {
            Queue<T> q = qs.dequeue();
            while (!q.isEmpty()) {
                this.enqueue(q.dequeue());
            }
        }
    }

    public Queue<T> clone () {
        Queue<T> q = new Queue<>();
        for (int i = 0; i < this.length; i++) {
            T value = this.dequeue();
            q.enqueue(value);
            this.enqueue(value);
        }
        return q;
    }

    public boolean contains (T value) {
        boolean out = false;
        for (int i = 0; i < this.length; i++) {
            if (out) this.rotate();
            else {
                T curr = this.dequeue();
                if (curr.equals(value)) out = true;
                this.enqueue(curr);
            }
        }
        return out;
    }

    public T rotate () {
        T val = null;
        if (!this.isEmpty() && this.head != this.tail) {
            val = this.head.getValue();
            Queue.Node<T> node = new Queue.Node<>(val, null);
            this.head = this.head.getNext();
            this.tail.setNext(node);
            this.tail = node;

        }
        return val;
    }

    public int size () {
        return this.length;
    }

    public boolean isEmpty () { return this.head == null && this.tail == null; }

    public void enqueue (T value) {
        this.length++;
        if (this.isEmpty()) {
            Queue.Node<T> newNode = new Queue.Node<>(value, null);
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            Queue.Node<T> newNode = new Queue.Node<>(value, null);
            this.tail.setNext(newNode);
            this.tail = this.tail.getNext();
        }
        // System.out.println("enqueue(" + value + ")");
    }

    public void append (Queue<T> q) {
        if (this.isEmpty()) {
            this.head = q.head;
            this.tail = q.tail;
            this.length = q.length;
        }
        else {
            this.length += q.size();
            this.tail.setNext(q.head);
            this.tail = q.tail;
        }
    }

    public T dequeue () {
        T output;
        if (this.isEmpty()) {
            output = null;
        }
        else {
            this.length--;
            output = this.head.getValue();
            this.head = this.head.getNext();
            if (this.head == null) {
                this.tail = null;
            }
        }
        // System.out.println("dequeue=" + output);
        return output;
    }

    public T peek () {
        if (this.isEmpty()) return null;
        return this.head.getValue();
    }

    public String toString () {
        String out = "";
        for (int i = 0; i < this.size(); i++) out += "," + this.rotate();
        return "<" + out.substring(1) + ">";
    }

    private static class Node<A> {
        private final A value;
        private Queue.Node<A> next;

        public Node (A value, Queue.Node<A> next) {
            this.value = value;
            this.next = next;
        }

        public A getValue () { return this.value; }
        public Queue.Node<A> getNext () { return this.next; }

        public void setNext (Queue.Node<A> next) { this.next = next; }
    }
}
