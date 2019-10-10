import edu.princeton.cs.algs4.StdOut;

public class LinkedList {

    Node origin;

    public LinkedList() {
        origin = new Node();
    }

    public void addItem(int item) {
        Node currentNode = origin;
        Node newNode = new Node(item);
        while(currentNode.nextNode() != null) {
            currentNode = currentNode.nextNode();
        }
        currentNode.setNext(newNode);
    }

    public int getItemPosition(int item) {
        Node currentNode = origin;
        int counter = 0;
        while(!currentNode.getData().equals(item)) {
            if(currentNode.nextNode() == null) {
                return -1;
            }
            currentNode = currentNode.nextNode();
            counter ++;
        }
        return counter;
    }

    public void insert(int item, int place) {
        Node currentNode = origin;
        int counter = 0;
        Node newNode = new Node(item);

        while(counter != place-1) {
            if(currentNode.nextNode() == null) {
                return;
            }
            currentNode = currentNode.nextNode();
            counter ++;
        }

        Node tempNode = currentNode.nextNode();
        currentNode.setNext(newNode);
        newNode.setNext(tempNode);

    }

    public void remove(int place) {
        Node currentNode = origin;
        int counter = 0;

        while(counter != place-1) {
            if(currentNode.nextNode() == null) {
                return;
            }
            currentNode = currentNode.nextNode();
            counter ++;
        }

        currentNode.setNext(currentNode.nextNode().nextNode());

    }

    public void printList() {
        Node currentNode = origin;

        while(!currentNode.nextNode().equals(null)) {
            StdOut.println(currentNode.getData());
        }

    }

}
