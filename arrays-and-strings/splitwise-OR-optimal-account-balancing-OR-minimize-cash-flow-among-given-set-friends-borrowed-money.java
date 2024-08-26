// https://www.geeksforgeeks.org/problems/minimize-cash-flow/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
// https://www.geeksforgeeks.org/minimize-cash-flow-among-given-set-friends-borrowed-money/
// https://leetcode.com/problems/optimal-account-balancing/

/*
Time Complexity: O(N2) where N is the number of persons.
Space Complexity: O(N)

The idea is to use Greedy algorithm where at every step, settle all amounts of one person and recur for remaining n-1 persons. 
How to pick the first person? To pick the first person, calculate the net amount for every person where net amount is obtained by
subtracting all debts (amounts to pay) from all credits (amounts to be paid). 
Once net amount for every person is evaluated, find two persons with maximum and minimum net amounts. 
These two persons are the most creditors and debtors. 
The person with minimum of two is our first person to be settled and removed from list. 
Let the minimum of two amounts be x. We pay ‘x’ amount from the maximum debtor to maximum creditor and settle one person. 
If x is equal to the maximum debit, then maximum debtor is settled, else maximum creditor is settled.
The following is detailed algorithm.

Do following for every person Pi where i is from 0 to n-1.  

Compute the net amount for every person. The net amount for person ‘i’ can be computed by subtracting sum of all debts from sum of all credits.
Find the two persons that are maximum creditor and maximum debtor. 
Let the maximum amount to be credited maximum creditor be maxCredit and maximum amount to be debited from maximum debtor be maxDebit. 
Let the maximum debtor be Pd and maximum creditor be Pc.
Find the minimum of maxDebit and maxCredit. Let minimum of two be x. Debit ‘x’ from Pd and credit this amount to Pc
If x is equal to maxCredit, then remove Pc from set of persons and recur for remaining (n-1) persons.
If x is equal to maxDebit, then remove Pd from set of persons and recur for remaining (n-1) persons.
*/

class Solution {
    
    // Utility function to get index of minimum value in array
    private int getMin(int[] amount) {
        int minIndex = 0;
        for(int i = 1; i < amount.length; i++) {
            if(amount[i] < amount[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    // Utility function to get index of maximum value in array
    private int getMax(int[] amount) {
        int maxIndex = 0;
        for(int i = 1; i < amount.length; i++) {
            if(amount[i] > amount[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // Recursive function to settle amounts and store the transactions
    private void minCashFlowRec(int[] amount, int[][] result) {
        int mxCreditIdx = getMax(amount);
        int mxDebitIdx = getMin(amount);

        // BaseCase, If both amounts are settled, return
        if(amount[mxCreditIdx] == 0 && amount[mxDebitIdx] == 0) return; 

        // Find the minimum of the two amounts
        int min = Math.min(-amount[mxDebitIdx], amount[mxCreditIdx]);
        amount[mxCreditIdx] -= min;
        amount[mxDebitIdx] += min;

        // Store the transaction, ans
        result[mxDebitIdx][mxCreditIdx] = min;

        // Recur for the next set of transactions
        minCashFlowRec(amount, result);
    }

    // Function to find the minimum cash flow and return the transactions
    public int[][] minCashFlow(int[][] transaction, int n) {
        int[] amount = new int[n];
        for(int person = 0; person < n; person++) {
            for (int j = 0; j < n; j++) {
                amount[person] += (transaction[j][person] - transaction[person][j]);
            }
        }

        int[][] result = new int[n][n];
        minCashFlowRec(amount, result);
        return result;
    }
    
}


//----------------------------------------------------------------------------------------

/*
Optimized Solution:
Time complexity can be improved in the previous solution. Fetching min and max index takes one whole array iteration. 
Instead, we can use max_heap and min_heap to fetch these values in O(1) time and insertion of new entry will take O(logN) time.
Hence, overall time complexity is reduced.

Steps:

Create a vector called ‘amount’ just like in previous solution and store cash value each person will receive.
Create two priority_queues one for max_heap and another for min_heap purpose. 
maxQ will have only positive value. minQ will have only negative value.
Iterate over amount vector and insert index and its value in the queues. 
Zero amount will be ignored. Positive amount (credit) will go to maxQ. Negative amount (debit) will go to minQ
Iterate over the queues until empty and fetch max credit and min debit. 
If sum of both equals zero, then just print the result, else print result as mentioned and push remaining credit or debit back to the required queue.

*/
// GFG Code
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;
import java.util.Arrays;
import java.util.Collections;

// Class representing a pair of integers
class Pair {
    private int key;
    private int value;

    public Pair(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}

// Comparator for ascending order sorting of pairs based on values
class AscCmp implements Comparator<Pair> {
    public int compare(Pair p1, Pair p2) {
        return p1.getValue() - p2.getValue();
    }
}

// Comparator for descending order sorting of pairs based on values
class DscCmp implements Comparator<Pair> {
    public int compare(Pair p1, Pair p2) {
        return p2.getValue() - p1.getValue();
    }
}

// Class implementing the solution algorithm
class Solution {
    private PriorityQueue<Pair> minQ; // Priority queue for minimum values
    private PriorityQueue<Pair> maxQ; // Priority queue for maximum values

    // Constructor initializing the priority queues
    public Solution() {
        minQ = new PriorityQueue<>(new DscCmp());
        maxQ = new PriorityQueue<>(new AscCmp());
    }

    // Fills the priority queues with positive and negative amounts
    private void constructMinMaxQ(Vector<Integer> amount) {
        for (int i = 0; i < amount.size(); ++i) {
            if (amount.get(i) == 0)
                continue;
            if (amount.get(i) > 0) {
                maxQ.add(new Pair(i, amount.get(i)));
            } else {
                minQ.add(new Pair(i, amount.get(i)));
            }
        }
    }

    // Solves transactions until both queues are empty
    private void solveTransaction() {
        while (!minQ.isEmpty() && !maxQ.isEmpty()) {
            Pair maxCreditEntry = maxQ.poll();
            Pair maxDebitEntry = minQ.poll();

            int transaction_val = maxCreditEntry.getValue() + maxDebitEntry.getValue();

            int debtor = maxDebitEntry.getKey();
            int creditor = maxCreditEntry.getKey();
            int owed_amount;

            if (transaction_val == 0) {
                owed_amount = maxCreditEntry.getValue();
            } else if (transaction_val < 0) {
                owed_amount = maxCreditEntry.getValue();
                minQ.add(new Pair(maxDebitEntry.getKey(), transaction_val));
            } else {
                owed_amount = -maxDebitEntry.getValue();
                maxQ.add(new Pair(maxCreditEntry.getKey(), transaction_val));
            }

            // Print result
            System.out.println("Person " + debtor + " pays " + owed_amount + " to Person " + creditor);
        }
    }

    // Calculates the amount to be credited/debited to/from each person and solves the transactions
    public void minCashFlow(Vector<Vector<Integer>> graph) {
        int n = graph.size();

        // Calculate the cash to be credited/debited to/from each person and store in vector amount
        Vector<Integer> amount = new Vector<>(Collections.nCopies(n, 0));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int diff = graph.get(j).get(i) - graph.get(i).get(j);
                amount.set(i, amount.get(i) + diff);
            }
        }

        // Fill in both queues minQ and maxQ using amount vector
        constructMinMaxQ(amount);

        // Solve the transaction using minQ, maxQ, and amount vector
        solveTransaction();
    }
}

public class Main {
    public static void main(String[] args) {
        // Test case 1
        Vector<Vector<Integer>> graph = new Vector<>();
        graph.add(new Vector<>(Arrays.asList(0, 1000, 2000)));
        graph.add(new Vector<>(Arrays.asList(0, 0, 5000)));
        graph.add(new Vector<>(Arrays.asList(0, 0, 0)));

        System.out.println("Solution 1 : ");
        Solution S = new Solution();
        S.minCashFlow(graph);

        // Test case 2
        Vector<Vector<Integer>> graph2 = new Vector<>();
        graph2.add(new Vector<>(Arrays.asList(2, 63, 0, 85, 49)));
        graph2.add(new Vector<>(Arrays.asList(0, 76, 0, 0, 27)));
        graph2.add(new Vector<>(Arrays.asList(0, 0, 0, 17, 0)));
        graph2.add(new Vector<>(Arrays.asList(73, 32, 50, 6, 71)));
        graph2.add(new Vector<>(Arrays.asList(0, 86, 0, 0, 10)));

        System.out.println("Solution 2 : ");
        Solution S2 = new Solution();
        S2.minCashFlow(graph2);
    }
}
