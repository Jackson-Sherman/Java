public class LinkedList<T> {
    public static void main (String[] args) {
        for (int i = 0; i < 4; i++) {
            LinkedList<Integer> l = new LinkedList<>();
            l.add(8);
            l.add(4);
            l.add(2);
            l.add(1);
            System.out.print(l);System.out.println(" - " + l.size());
            System.out.println("l.remove(" + i + ")");
            l.remove(i);
            System.out.print(l);System.out.println(" - " + l.size());
            System.out.println();
        }
    }

    private LinkedList.Node<T> head;
    private int length;

    public LinkedList () {
        this.head = null;
        this.length = 0;
    }

    public void add(T value) {
        if (this.head == null) this.head = new LinkedList.Node<T>(value, null);
        else {
            LinkedList.Node<T> c = this.head;
            while (!c.isFinal()) {
                c = c.getNext();
            }
            c.setNext(new LinkedList.Node<T>(value, null));
        }
        this.length++;
    }

    public T get (int index) {
        if (index < 0) index += this.length;
        if (this.length <= index || index < 0) return null;

        LinkedList.Node<T> node = this.head;
        while (index > 0 && !node.isFinal()) {
            index--;
            node = node.getNext();
        }
        if (node!=null) return node.getData();
        return null;
    }

    public void set (int index, T value) {
        if (index < 0) index += this.length;
        if (0 <= index && index < this.length) {
            LinkedList.Node<T> node = this.head;
            while (index > 0) {
                index--;
                node = node.getNext();
            }
            LinkedList.Node<T> next = node.getNext();
            node = new LinkedList.Node<>(value, next);
        }
    }

    public T remove (int index) {
        T output = null;
        if (index < 0) index += this.length;
        if (index < this.length) {
            if (index == 0) {
                output = this.head.getData();
                this.head = this.head.getNext();
                this.length--;
            }
            else if (0 < index) {

                LinkedList.Node<T> node = this.head;
                while (index > 1) {
                    index--;
                    node = node.getNext();
                }
                LinkedList.Node<T> toBeRemoved = node.getNext();
                LinkedList.Node<T> removedNext = toBeRemoved.getNext();
                output = toBeRemoved.getData();
                node.setNext(removedNext);
                this.length--;
            }
        }
        return output;
    }

    public int size () { return this.length; }

    public void insert (int index, T value) {
        if (index < 0) index += this.length;
        if (0 <= index && index <= this.length) {
            LinkedList.Node<T> newbie;
            if (index == 0) {
                newbie = new LinkedList.Node<>(value, this.head);
                this.head = newbie;
            } else {
                LinkedList.Node<T> node = this.head;
                while (index > 1) {
                    index--;
                    node = node.getNext();
                }
                newbie = new LinkedList.Node<>(value, node.getNext());
                node.setNext(newbie);
            }
            this.length++;
        }
    }

    public void push (T value) {
        LinkedList.Node<T> newbie = new LinkedList.Node<>(value, this.head);
        this.head = newbie;
        this.length++;
    }

    public T pop () {
        T value = this.head.getData();
        this.head = this.head.getNext();
        this.length--;
        return value;
    }

    public String toString() {
        if (this.head == null) return "\u27E8\u27E9";
        else return "\u27E8" + LinkedList.toStringAux(this.head) + "\u27E9";

    }

    private static String toStringAux (LinkedList.Node node) {
        String s = node.toString();
        if (node.isFinal()) return s;
        else return s + "," + LinkedList.toStringAux(node.getNext());
    }

    private static class Node<T> {
        private T data;
        private LinkedList.Node<T> next;

        public Node (T value, LinkedList.Node<T> next) {
            this.data = value;
            this.next = next;
        }

        public T getData () {return this.data;}
        public LinkedList.Node<T> getNext () {return this.next;}

        public void setData (T value) {this.data = value;}
        public void setNext (LinkedList.Node<T> next) {this.next = next;}

        public boolean isEmpty () {return this.data==null;}
        public boolean isFinal () {return this.next==null;}

        public String toString () {return this.getData().toString();}

        public LinkedList.Node<T> clone () { return new LinkedList.Node<T>(this.getData(), this.getNext()); }
    }
}

