public class Stack<T> {
    public static void main (String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(0);
        s.push(1);
        s.push(2);
        s.pop();
        s.push(3);
        s.pop();
        s.pop();
        s.pop();
        s.pop();
    }

    private Stack.Node<T> top;
    private int length;

    public Stack () {
        this.top = null;
        this.length = 0;
    }

    public void push (T value) {
        this.top = new Stack.Node<>(value, this.top);
        this.length++;
        System.out.println("push(" + value.toString() + ")");
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
        System.out.println("pop()=" + val);
        return val;
    }

    public boolean isEmpty () { return this.top == null; }

    public int size () { return this.length; }

    private static class Node<A> {
        private A value;
        private Stack.Node<A> next;

        public Node (A value, Stack.Node<A> next) {
            this.value = value;
            this.next = next;
        }

        public A getValue () { return this.value; }
        public Stack.Node<A> getNext () { return this.next; }

        public void setValue(A value) { this.value = value; }
        public void setNext(Stack.Node<A> next) { this.next = next; }
    }
}

