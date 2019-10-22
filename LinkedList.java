/*  LinkedList Practice Example (In-Class Partnered With Elven)
    Name: Ethan Chen
    Date Started: October 10, 2019
*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedList {
    /*
    This class is made solely as part of our in-class partnered assignment and also as a source to test my methods for
    the reverse linked list problems as part of the problem set. In terms of the reverse linked list, the only
    necessary methods are addItem (in order to create the linked list) and printList (to print it at the end), but the
    others are also included as well.
     */

    Node origin;

    public LinkedList() {

    }

    public LinkedList(Node firstNode) {
        origin = firstNode;
    }

    public void addItem(int item) {
        Node newNode = new Node(item);
        if (origin != null) {
            Node currentNode = origin;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.setNext(newNode);
        } else {
            origin = newNode;
        }
    }

    public int getItemPosition(int item) {
        if (origin != null) {
            Node currentNode = origin;
            int counter = 0;
            while (!currentNode.data.equals(item)) {
                if (currentNode.next == null) {
                    return -1;
                }
                currentNode = currentNode.next;
                counter++;
            }
            return counter;
        } else {
            return -1;
        }
    }

    public void insert(int item, int place) {
        if (origin != null) {
            Node currentNode = origin;
            int counter = 0;
            Node newNode = new Node(item);

            while (counter != place - 1) {
                if (currentNode.next == null) {
                    return;
                }
                currentNode = currentNode.next;
                counter++;
            }

            Node tempNode = currentNode.next;
            currentNode.setNext(newNode);
            newNode.setNext(tempNode);
        }
    }

    public void remove(int place) {
        if (origin != null) {
            Node currentNode = origin;
            int counter = 0;

            while (counter != place - 1) {
                if (currentNode.next == null) {
                    return;
                }
                currentNode = currentNode.next;
                counter++;
            }

            currentNode.setNext(currentNode.next.next);
        }
    }

    public void printList() {
        if(origin != null) {
            Node currentNode = origin;

            while (currentNode.next != null) {
                StdOut.print(currentNode.data + "=>");
                currentNode = currentNode.next;
            }
            StdOut.print(currentNode.data);
        } else {
            StdOut.println("Empty Linked List");
        }
    }

}
