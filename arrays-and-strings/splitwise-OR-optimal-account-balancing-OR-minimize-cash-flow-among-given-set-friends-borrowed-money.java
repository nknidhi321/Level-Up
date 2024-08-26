// https://www.geeksforgeeks.org/problems/minimize-cash-flow/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
// https://www.geeksforgeeks.org/minimize-cash-flow-among-given-set-friends-borrowed-money/
// https://leetcode.com/problems/optimal-account-balancing/

/*
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
