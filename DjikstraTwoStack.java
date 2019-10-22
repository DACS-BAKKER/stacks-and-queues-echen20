/*  Djikstra's Two Stack Problem
    Name: Ethan Chen
    Date Started: October 12, 2019

*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class DjikstraTwoStack { // Takes an equation as a string and solves it

    public static void main(String[] args) {
        Stack<Character> operators = new Stack<Character>(); // creates operator stack (stack 1)
        Stack<Integer> operands = new Stack<Integer>(); // creates operand stack (stack 2)

        StdOut.println("Welcome to the Djikstra Two Stack Equation Solver");
        StdOut.println("Please input an equation below: "); // use no parentheses for this
        String s = StdIn.readLine();

        String equation = alterEquation(s); // puts parentheses in correct place that applies to order of ops
        //String equation = "((3+45)*8)"; //practice equation for if already has parentheses
        StdOut.print(equation);

        StdOut.println(solveDjikstra(equation, operators, operands));

    }

    public static String alterEquation(String nonAltered) {

        /* This is a complicated method that takes a string with no parentheses
            and parses it into a string with right parentheses that adhere to the rules of
            the order of operations. Unfortunately, manually adding any parentheses will cause extra
            parentheses to occur, and therefore the dijkstra two stack will not work.

            e.g. input = "3+4*5-6/7" will work with dijkstra
                 input = "3+(4*5)-(6/7)" will not, despite them being the same thing

            It works by when it comes across a '*' or '/', it looks for the next operator and puts a parentheses
            before it. When it comes across a '+' or '-', it looks for the next '+' or '-' and puts a parentheses
            it. If either comes across the end of the charArray before then, it places a parentheses there.

            This took a lot of time
        */


        boolean multDivide = false;
        boolean addSubtract = false;

        String alteredEquation = "";

        char[] charArray = nonAltered.toCharArray();
        int z = -1;
        int y = -1;


        for (int x = 0; x < charArray.length; x++) {

            if(x == charArray.length-1) {

                alteredEquation += charArray[x];
                if (x == z) {
                    alteredEquation += ')';
                    z = -1;
                }
                if (x == y) {
                    alteredEquation += ')';
                    y = -1;
                }

            } else {
                if (x == z) {
                    alteredEquation += ')';
                    z = -1;
                }
                if (x == y) {
                    alteredEquation += ')';
                    y = -1;
                }
                alteredEquation += charArray[x];

            }

            if (multDivide) {

                if(x == charArray.length-1) {

                    alteredEquation += ')';
                    return alteredEquation;
                }

                while (charArray[x+1] != '*' && charArray[x+1] != '/' && charArray[x+1] != '+' && charArray[x+1] != '-' && (x+1) < (charArray.length-2)) {
                    x++;
                    alteredEquation += charArray[x];
                }
                multDivide = false;
                alteredEquation += ')';
            }

            if (addSubtract) {
                if (z == -1) {
                    z = x;
                    while (charArray[z] != '+' && charArray[z] != '-' && z < charArray.length - 1) {
                        z++;
                    }
                    addSubtract = false;
                    if(x == z) {
                        alteredEquation += ')';
                        return alteredEquation;
                    }
                } else  {
                    y = x;
                    while (charArray[y] != '+' && charArray[y] != '-' && y < charArray.length - 1) {
                        y++;
                    }
                    addSubtract = false;
                }
            }

                if (charArray[x] == '*' || charArray[x] == '/') {
                    multDivide = true;
                }
                if (charArray[x] == '+' || charArray[x] == '-') {
                    addSubtract = true;
                }

        }
        return alteredEquation;
    }


    //THIS IS THE ACTUAL DIJKSTRA TWO-STACK METHOD. IT TAKES A PARENTHETICAL EQUATION AND RETURNS THE ANSWER

        public static int solveDjikstra (String equation, Stack operators, Stack operands){
            int skipTimes = 0;

            char[] charArray = equation.toCharArray();

            for (int x = 0; x < charArray.length; x++) {
                if (skipTimes == 0) { // for if numbers longer than one character - see ** below
                    if (charArray[x] == '+' || charArray[x] == '-' || charArray[x] == '/' || charArray[x] == '*') {
                        operators.push(charArray[x]); //pushes operations to operator stack
                    }
                    if (Character.isDigit(charArray[x])) {
                        int value = 0;
                        int z = x;
                        while (Character.isDigit(charArray[z])) { //this is for finding numbers with more than one char
                            value = value * 10 + Character.getNumericValue(charArray[z]); //such as 456
                            z++; //skips over the next characters because would just repeat the following digits
                            skipTimes++; // ** skips over the next characters because would just repeat the following digits
                        }
                        skipTimes--;
                        operands.push(value); //pushes full numbers to operand stack
                    }
                    if (charArray[x] == ')') { // when it finds the right parentheses:
                        int a = (int) operands.pop(); // grabs one operand
                        int b = (int) operands.pop(); // grabs two operands
                        char op = (char) operators.pop(); // grabs an operator

                        if (op == '+') { // and does the operation on the two operands
                            operands.push(a + b);
                        }
                        if (op == '-') {
                            operands.push(b - a);
                        }
                        if (op == '/') {
                            operands.push(b / a);
                        }
                        if (op == '*') {
                            operands.push(a * b);
                        }
                    }
                } else {
                    skipTimes--; // for skipping over extra digits
                }
            }
            return (int) operands.pop(); // returns when everything is done
        }
    }

