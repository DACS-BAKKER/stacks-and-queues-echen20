/*  Reversed Linked List Program
    Name: Ethan Chen
    Date Started: October 13, 2019
*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

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

        StdOut.println("Welcome to the Reverse Linked List Tester");
        StdOut.println("Please add your first node");
        String newItem = StdIn.readString();
        LinkedList testList = new LinkedList(new Node(newItem));
        boolean wantsNext = true;
        boolean listens;

        while(wantsNext) {
            StdOut.println();
            listens = true;
            StdOut.println("Would you like to add another node? 1 - yes; 2 - no");
            int addOneMore = StdIn.readInt();
            if(addOneMore == 1) {
                wantsNext = true;
            } else if(addOneMore == 2) {
                wantsNext = false;
            } else {
                StdOut.println("Please just try again");
                listens = false;
            }

            if(wantsNext && listens) {
                StdOut.println("Please type the value you would like to add: ");
                String nextItem = StdIn.readString();
                Node nextNode = new Node(nextItem);
                testList.addItem(nextNode);
            }

        }

        StdOut.println();
        StdOut.println("What method would you like to use? Iterative (1) or Recursive (2) or time both (3)?");
        int method = StdIn.readInt();

        if(method == 1) {
            StdOut.println("The original list is: ");
            testList.printList();
            StdOut.println();
            reverseLinkedListIteratively(testList);
            StdOut.println("Now, the reversed list looks like: ");
            testList.printList();

        } else if(method == 2) {
            StdOut.println("The original list is: ");
            testList.printList();
            StdOut.println();
            reverseLinkedListRecursively(testList.origin, testList);
            StdOut.println("Now, the reversed list looks like: ");
            testList.printList();

        } else if(method == 3) {
            long stopwatch1 = System.nanoTime();
            reverseLinkedListIteratively(testList);
            long stopwatch2 = System.nanoTime();
            StdOut.println("The iterative method took " +  (stopwatch2-stopwatch1) + " nanoseconds to complete.");

            long stopwatch3 = System.nanoTime();
            reverseLinkedListRecursively(testList.origin, testList);
            long stopwatch4 = System.nanoTime();
            StdOut.println("The recursive method took " + (stopwatch4-stopwatch3) + " nanoseconds to complete.");

        } else {
            StdOut.println("1, 2, or 3 buddy. 1, 2, or 3.");
        }

        StdOut.println();
        StdOut.println();
        StdOut.println("Thank you for using this reversed linked list tester");

        //Initial code I used for testing if it reversed successfully
       /* LinkedList list = new LinkedList(new Node(3));
        list.addItem(4);
        list.addItem(5);
        list.addItem(6);
        list.addItem(7);
*/
    }


}
