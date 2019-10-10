public class Node {
   // private Item data;
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }

    public Node() {

    }

    public void setNext(Node n) {
        next = n;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
