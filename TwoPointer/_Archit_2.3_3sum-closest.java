// https://leetcode.com/problems/3sum-closest/

// TC : O(N ^ 2)

class Solution {
    
    public int threeSumClosest(int[] arr, int target) {
        int n = arr.length, ans = -1; 
        int min = Integer.MAX_VALUE;
        Arrays.sort(arr);
        
        // 3 sum
        for(int start = 0; start <= n - 3; start++) {
            int smallTar = target - arr[start];
            int i = start + 1, j = n - 1;
            
            // 2 sum
            while(i < j) {
                int sum = arr[i] + arr[j];
                if(sum < smallTar) i++;
                else if(sum > smallTar) j--;
                else return sum + arr[start]; // found closest
                
                // compete ans
                int diff = Math.abs(target - (sum + arr[start]));
                if(diff < min) { 
                    min = diff;
                    ans = sum + arr[start];
                }
            }
        }
        return ans;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
