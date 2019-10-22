/*  Reversed Linked List Program
    Name: Ethan Chen
    Date Started: October 13, 2019
*/

import edu.princeton.cs.algs4.StdOut;

public class ReverseLinkedList {

    // Iterative Method for Reversing a Linked List
    public static LinkedList reverseLinkedListIteratively(LinkedList l) {
        Node currentNode = l.origin.next; // starts at second node
        Node frontNode = currentNode.next; // starts at third node
        Node backNode = l.origin; // starts at first node
        l.origin.next = null; // removes first nodes pointer (second node saved in currentNode)

        while(frontNode != null) { // stops when currentNode is the last node
            currentNode.setNext(backNode); // sets currentNode to the one before it
            backNode = currentNode; // moving everything up a node
            currentNode = frontNode; // this is why you need front node, to save the next node after changing currentNode's pointer
            frontNode = currentNode.next;
        }
        currentNode.setNext(backNode); // at very end, set final nodes next to the one before
        l.origin = currentNode; // declare final node as new origin
        return l;
    }

    //Recursive Method for Reversing a Linked List
    public static void reverseLinkedListRecursively(Node start, LinkedList l) {
        /* node used to traverse location in list
           linkedList parameter solely to change origin in the list
           so that it prints starting from the correct node
         */

        if(start.next == null) { // base case - final node in list
            l.origin = start; // sets final node as new origin
        } else {
            reverseLinkedListRecursively(start.next, l);
            start.next.next = start; //changes pointer of next node to itself
            start.next = null;
        }
    }

    public static void main(String[] args) {

        LinkedList list = new LinkedList(new Node(3));
        list.addItem(4);
        list.addItem(5);
        list.addItem(6);
        list.addItem(7);

        reverseLinkedListRecursively(list.origin, list);
        list.printList();
    }


}
