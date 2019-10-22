/*  Josephus Problem
    Name: Ethan Chen
    Date Started: October 11, 2019
*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class JosephusProblem {

    public static int numPeople;
    public static int skipSpace;
    public static int solveMethod;

    public static void main(String[] args) { // UI

        StdOut.println("Welcome to the Josephus Problem Solver");
        StdOut.println("How many people would you like to use in this problem?");
        numPeople = StdIn.readInt();
        StdOut.println("What increments of people would you like to kill people?");
        skipSpace = StdIn.readInt();
        StdOut.println("How would you like to solve this problem? 0: Iteratively; 1: Recursively; 2: Time All 3");
        solveMethod = StdIn.readInt();


        Queue josephusQueue = new Queue(); // creates queue with each item's value being its location in the queue
        for (int x = 0; x < numPeople; x++) {
            Node space = new Node(x);
            josephusQueue.enqueue(space);
        }

        StdOut.println();
        if(solveMethod == 0) {
            StdOut.println("You should put yourself in the " + solveJosephusIteratively(skipSpace, numPeople, false) + " position to survive.");
        } else if(solveMethod == 1) {
            StdOut.println("This method is not currently working");
            StdOut.println("You should put yourself in the " + solveJosephusRecursively2(skipSpace, josephusQueue, true) + " position to survive.");
        } else if(solveMethod == 2) {
            Stopwatch stopIterative = new Stopwatch();
            solveJosephusIteratively(skipSpace, numPeople, false);
            StdOut.println("The iterative method took " + stopIterative.elapsedTime() + " seconds to complete");

            Stopwatch stopRecursive2 = new Stopwatch();
            solveJosephusRecursively2(skipSpace, josephusQueue, false);
            StdOut.println("The better recursive method took " + stopRecursive2.elapsedTime() + " seconds to complete");
        } else {
            StdOut.println("Please try again and read the console this time.");
        }


    }

    public static int solveJosephusIteratively(int skipSpace, int numPeople, boolean print) {
        Queue josephusQueue = new Queue();
        for (int x = 0; x < numPeople; x++) { // creates josephus queue inside method instead of outside
            Node space = new Node(x);
            josephusQueue.enqueue(space);
        }

        while(josephusQueue.size() != 1) { // repeats until queue is one item long
            for(int x = 0; x<skipSpace-1; x++) { // repeats skipSpace-1 times
                josephusQueue.enqueue(josephusQueue.dequeue()); // moves item from front to back
            }
            String dequed = josephusQueue.dequeue().toString(); // removes the item at skipSpace
            if (print) { // whether or not to print (for purposes of UI)
                StdOut.println(dequed + " was killed.");
            }

        }
        return Integer.valueOf(josephusQueue.dequeue().toString());
    }

/* IMPERFECT METHOD --- this only works when skipSpace <= 2
    public static int solveJosephusRecursively(int skipSpace, Queue josephusQueue, boolean print) { //My Original Solution to the Josephus Problem
        if (josephusQueue.size() == 1) {
            return Integer.valueOf(josephusQueue.dequeue().toString());
        } else {
            Queue firstPartNewJosephusQueue = new Queue();
            Queue fullNewJosephusQueue = new Queue();
            for (int x = 0; x < skipSpace - 1; x++) {
                firstPartNewJosephusQueue.enqueue(josephusQueue.dequeue());
            }

            String dequed = josephusQueue.dequeue().toString();
            if(print) {
                StdOut.println(dequed + " was killed.");
            }
            int currJosephusQueueSize = josephusQueue.size();
            for (int x = 0; x < currJosephusQueueSize; x++) {
                fullNewJosephusQueue.enqueue(josephusQueue.dequeue());
            }

            int currFirstPartNewJosephusQueueSize = firstPartNewJosephusQueue.size();
            for (int x = 0; x < currFirstPartNewJosephusQueueSize; x++) {
                fullNewJosephusQueue.enqueue(firstPartNewJosephusQueue.dequeue());
            }
            return solveJosephusRecursively(skipSpace, fullNewJosephusQueue, print);
        }
    }
*/

    public static int solveJosephusRecursively2(int skipSpace, Queue josephusQueue, boolean print) { //takes queue as parameter
        if(josephusQueue.size() == 1) { //base case if one item
            return Integer.valueOf(josephusQueue.dequeue().toString()); // returns the value of the final item
        } else {
            for(int x = 0; x<skipSpace-1; x++) { // repeats skipSpace-1 times
                josephusQueue.enqueue(josephusQueue.dequeue()); // moves item from front to back
            }
            String dequed = josephusQueue.dequeue().toString();
            if(print) {
                StdOut.println(dequed + " was killed.");
            }
            return solveJosephusRecursively2(skipSpace, josephusQueue, print); // does it all again with shortened list
        }
    }

}
