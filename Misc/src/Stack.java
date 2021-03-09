public class Stack<T> {
    private Stack.Node<T> top;
    private int length;

    public Stack () {
        this.top = null;
        this.length = 0;
    }

    public void push (T value) {
        this.top = new Stack.Node<>(value, this.top);
        this.length++;
    }

    public T pop () {
        T val;
        if (this.isEmpty()) {
            val = null;
        }
        else {
            val = this.top.getValue();
            this.top = this.top.getNext();
            this.length--;
        }
        return val;
    }

    public boolean isEmpty () { return this.top == null; }

    public int size () { return this.length; }

    private static class Node<A> {
        private final A value;
        private final Stack.Node<A> next;

        public Node (A value, Stack.Node<A> next) {
            this.value = value;
            this.next = next;
        }

        public A getValue () { return this.value; }
        public Stack.Node<A> getNext () { return this.next; }
    }
}

