// https://www.hackerrank.com/contests/w15/challenges/haunted-house
/*

Sample Input
6
1 2
1 4
0 3
0 1
3 4
0 2
  
Sample Output
3

*/
  
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] constraints = new int[N][2];

        for (int i = 0; i < N; i++) {
            constraints[i][0] = sc.nextInt();
            constraints[i][1] = sc.nextInt();
        }

        // I do not see any point of sorting, even with or without sorting 3 TC are failing
        // Sort by the upper bound (H_i)
        // Arrays.sort(constraints, (a, b) -> Integer.compare(a[1], b[1])); 

        int low = 1, high = N, result = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canFormGroup(mid, constraints)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(result);
    }

    private static boolean canFormGroup(int X, int[][] constraints) {
        int peopleCount = 0;

        for (int[] constraint : constraints) {
            int Li = constraint[0], Hi = constraint[1];

            // X - 1 because constraints is talking about other people not including you
            if (Li <= X - 1 && X - 1 <= Hi) { 
                peopleCount++;
            }
        }
        return peopleCount >= X;
    }
}
