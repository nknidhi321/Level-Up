// https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1/#

class Solution {

	public int maxSumIS(int arr[], int n)  {  
        
        int[] max = new int[n]; // Mere pe khatam hone wala increasing Subsequence ka max
        int maxIncreasing_sum = 0;
        
        for(int i = 0; i < n; i++) {
            max[i] = arr[i];
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] < arr[i]) { // Khud se chote elements pe jaao, no matter tum LIS ho ya nai
                    max[i] = Math.max(max[j] + arr[i], max[i]); // Bs strictly increasing hona chahiye
                }
            }
            
            // Cal. overall maxIncreasing_sum
            // NOTE : This maxIncreasing_sum might not be of the LIS,
            // Here, the only req. is your subsequence should be "increasing" and not "LIS"
            maxIncreasing_sum = Math.max(maxIncreasing_sum, max[i]); 
        }
        return maxIncreasing_sum;        
    }
	
}
