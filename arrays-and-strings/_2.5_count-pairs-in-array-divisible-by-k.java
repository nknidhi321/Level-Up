// https://practice.geeksforgeeks.org/problems/count-pairs-in-array-divisible-by-k/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article

class Solution {
    
    public static long countKdivPairs(int arr[], int n, int k) {
        int[] rem = new int[k]; // [0, k -1]
        for(int i = 0; i < n; i++) { // rem ka freq count
            rem[arr[i] % k]++;
        }
        
        // Now, generate pairs (Pairs k remainder ka summation k k equal hoga)
        long count = 0;
        
        // Suppose k = 7
        // (k * n + 0)  => Jo poora poora divisible h unlg k pair ka count
        int freq = rem[0];
        count += (freq * (freq - 1)) / 2;  // nC2 => Sab aapas me ek ek baar pair bnaenge
        
        int i = 1, j = k - 1;
        while(i <= j) {
            if(i == j) { // If k == 6 hota toh (k * n + 3) + (k * n + 3) ka form hota ek
                freq = rem[i];
                count += (freq * (freq - 1)) / 2;  // nC2
            }
            else {
                // (k * n + 1) + (k * n + 6)   
                // (k * n + 2) + (k * n + 5)   
                // (k * n + 3) + (k * n + 4)   
                count += rem[i] * rem[j];
            }
            i++; j--;
        }
        return count;
    }
    
}
