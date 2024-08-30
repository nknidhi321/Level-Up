// https://www.geeksforgeeks.org/problems/nearest-power0840/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card

/*
Given two integers N and M  you have to find out an integer which is a power of M and is nearest to N.
Note: If there are multiple answers possible to, print the greatest number possible.

 
Example 1:
Input:
N = 6, M = 3
Output:
9
Explanation:
Both 3 (31) and 9 (32) are equally
near to 6. But 9 is greater,
so the Output is 9.


Example 2:
Input:
N = 3, M = 2
Output:
4
Explanation:
Both 2 (21) and 4 (22) are equally
near to 3. But 4 is greater,
so the Output is 4.
*/
  
class Solution {
    
    static Long nearestPower(Long N, Long M) {
        // Step 1: Compute logarithms of N and M
        double logN = Math.log(N);   // logN = log(50)
        double logM = Math.log(M);   // logM = log(3)

        // Step 2: Calculate the exponent x = log(N) / log(M)
        double x = logN / logM;      // x = log(50) / log(3)

        // Step 3: Determine the closest lower and upper powers of M
        int lowerPowerIndex = (int) Math.floor(x);  // lowerPowerIndex = floor(x)
        Long lowerPower = (long) Math.pow(M, lowerPowerIndex);   // lowerPower = M^lowerPowerIndex
        Long upperPower = (long) Math.pow(M, lowerPowerIndex + 1); // upperPower = M^(lowerPowerIndex + 1)

        // Step 4: Compare the distances from N to these powers
        Long distanceToLower = N - lowerPower;   // distanceToLower = N - lowerPower
        Long distanceToUpper = upperPower - N;   // distanceToUpper = upperPower - N

        // Step 5: Choose the nearest power based on the smallest distance
        // If distances are equal, choose the larger power (upperPower)
        if (distanceToLower < distanceToUpper) {
            return lowerPower;   // lowerPower is closer to N
        } 
        else {
            return upperPower;   // upperPower is closer to N or equidistant
        }
    }
    
}
