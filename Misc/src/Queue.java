public class Queue<T> {

    private Queue.Node<T> head;
    private Queue.Node<T> tail;

    public Queue () {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty () { return this.head == null && this.tail == null; }

    public void enqueue (T value) {
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

    public T dequeue () {
        T output;
        if (this.isEmpty()) {
            output = null;
        }
        else {
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
