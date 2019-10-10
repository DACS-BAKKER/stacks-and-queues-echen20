public class Node <Item>{
    private Item data;
    private Node<Item> next;

    public Node(Item data) {
        this.data = data;
    }

    public Node() {

    }

    public Item getData() {
        return data;
    }

    public void setNext(Node n) {
        next = n;
    }

    public Node nextNode() {
        return next;
    }

    public void delete() {
        this.data = null;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
