/*
 * Purpose: Honors Design and Analysis of Algorithms Assignment 1 Problem 1
 * Status: Complete and thoroughly tested 
 * Last update: 02/06/20 
 * Submitted: 02/06/20 
 * Comment: test suite and sample run attached 
 * @author: Nick Bovee
 * @version: 2020.6.2
 */

public class Driver { //formerly NQueensP1
    private static int n;
    private static int[] solution;
    private static long comparisons;
    private static boolean solFound;
    private static StringBuilder sb;

    public static void main(String args[]) {
        if (args.length == 0) {
            System.out.println("Enter a size for n when calling NQueensP1.");
        } else {
            comparisons = 0;
            solFound = false;
            n = Integer.parseInt(args[0]);
            solution = new int[n];
            sb = new StringBuilder();
            System.out.println("Solving for " + n + " Queens:");
            solver();
            display();
        }
    }

    private static void solver() {
        boolean loop = true;
        for (int i = 0; i < n && loop == true; i++) // i = active index
        {
            boolean valid = false;
            for (int j = 0; j < n && valid == false; j++) {
                solution[i] = j;
                valid = ruleCheck(i);
                // check viability and store loop over j until viable or
                // exhausted
                boolean back = false; //flag to reduce code duplication
                if (valid == false && j == n - 1) {
                    back = true;
                } else if (valid == true && i == n - 1) {
                    // solution found, end or store
                    sb.append("Solution for " + n + " Queens:\n");
                    sb.append(format(solution));
                    solFound = true;
                    loop = false;
                }
                if(back == true)
                {
                    i = backtrack(i);
                    if (i < 0) // if domain of 0 exhausted, then can exit the solver
                    {
                        loop = false;
                    } else {
                        j = solution[i];
                    }
                    valid = false;
                }
            }
        }
    }

    private static int backtrack(int i) {
        boolean cont = true;
        while (cont == true) // while back to the most recent non-exhausted domain
        {
            if (i >= 0) {
                comparisons++;
                if (solution[i] == n - 1) {
                    i--;
                } else {
                    cont = false;
                }
            } else {
                cont = false;
            }
        }
        return i;
    }

    private static void display() {
        if (solFound == false) {
            sb.append("No solution for " + n + " Queens.\n");
        }
        sb.append("Comparisons made: " + comparisons + ".");
        System.out.println(sb.toString());
    }

    private static boolean ruleCheck(int index) // send index to validate for simplicity
    {
        boolean result = true;
        int i = 0;
        while (result == true && i < index) {
            comparisons += 2;
            if (solution[index] == solution[i]) {
                comparisons--;
                result = false; // rook check
            } else if (Math.abs(index - i) == Math.abs(solution[index] - solution[i])) {
                result = false;
            }
            i++;
        }
        return result;
    }

    private static String format(int[] sol) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (sol[j] == i) {
                    result.append("Q ");
                } else {
                    result.append("* ");
                }
            }
            int temp = result.length() - 1;
            result.replace(temp, temp + 1, "\n");
        }
        return result.toString();
    }
}
