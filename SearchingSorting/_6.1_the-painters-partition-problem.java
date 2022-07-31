// https://practice.geeksforgeeks.org/problems/the-painters-partition-problem1535/1
// Ditto Book Allocation

 class Solution {
     
    public static long minTime(int[] arr,int n,int k) {
        
        long max = -(int)1e9, sum = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            sum += arr[i];
        }
        
        // si : Sbse max length ko paint krne me jitna time lgega utna toh atleast time rakhna he parega
        // ei : Saare lemgth ko paint karne me max. kitna time lgega, sum of the whole array 
        long si = max, ei = sum, probAns = -1;
        while(si <= ei) {
            long probTime = si + (ei - si) / 2;
            if(isPossible(arr, probTime, k)) {
                probAns = probTime;
                ei = probTime - 1;
            }
            else {
                si = probTime + 1;
            }
        }
        return probAns;
    }
    
    public static boolean isPossible(int[] arr, long probTime, int k) {
        int n = arr.length;
        long sum = 0;
        int count = 1;
        for(int i = 0; i < n; i++) {
            sum += arr[i];
            if(sum > probTime) {
                count++;
                sum = arr[i];
            }
        }
        return count <= k;
    } 
    
}
