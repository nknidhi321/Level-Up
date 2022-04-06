// https://practice.geeksforgeeks.org/problems/temple-offerings2831/1/#

class Solution{
    
    public int offerings(int n, int arr[]){
        
        // visualize from left
        int[] leftMin = new int[n];
        leftMin[0] = 1; 
        for(int i = 1; i < n; i++) {
            if(arr[i - 1] < arr[i]) {
                leftMin[i] = leftMin[i - 1] + 1; // Strictly increasing altitude
            }
            else {
                leftMin[i] = 1; // Same altitude or decreasing altitude
            }
        }
    
    
        // visualize from right    
        int[] rightMin = new int[n];
        rightMin[n - 1] = 1;
        for(int i = n - 2; i >= 0; i--) {
            if(arr[i + 1] < arr[i]) { 
                rightMin[i] = rightMin[i + 1] + 1;  // Strictly increasing altitude
            }
            else {
                rightMin[i] = 1; // Same altitude or decreasing altitude
            }
        }
        
        
        // Find max of all the ith index of leftMin & rightMax and get the sum
        int max = 0;
        for(int i = 0; i < n; i++) {
            max += Math.max(leftMin[i], rightMin[i]);
        }
        return max;
    }
}
