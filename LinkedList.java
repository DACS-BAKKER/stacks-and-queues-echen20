import edu.princeton.cs.algs4.StdOut;

public class LinkedList {

    Node origin;

    public LinkedList() {
        origin = new Node();
    }

    public void addItem(int item) {
        Node currentNode = origin;
        Node newNode = new Node(item);
        while(currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.setNext(newNode);
    }

    public int getItemPosition(int item) {
        Node currentNode = origin;
        int counter = 0;
        while(currentNode.data != item) {
            if(currentNode.next == null) {
                return -1;
            }
            currentNode = currentNode.next;
            counter ++;
        }
        return counter;
    }

    public void insert(int item, int place) {
        Node currentNode = origin;
        int counter = 0;
        Node newNode = new Node(item);

        while(counter != place-1) {
            if(currentNode.next == null) {
                return;
            }
            currentNode = currentNode.next;
            counter ++;
        }

        Node tempNode = currentNode.next;
        currentNode.setNext(newNode);
        newNode.setNext(tempNode);

    }

    public void remove(int place) {
        Node currentNode = origin;
        int counter = 0;

        while(counter != place-1) {
            if(currentNode.next == null) {
                return;
            }
            currentNode = currentNode.next;
            counter ++;
        }

        currentNode.setNext(currentNode.next.next);

    }

    public void printList() {
        Node currentNode = origin;

        while(!currentNode.next.equals(null)) {
            StdOut.println(currentNode.data);
        }

    }

}
