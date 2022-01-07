// https://leetcode.com/problems/4sum-ii/ 

// TC : 2(N^2)

class Solution {
    
    public static int n;
    
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        n = A.length; int target = 0;
        
        // Store all the sum combination of C + D in HM
        HashMap<Integer, Integer> map = new HashMap<>();   // {Sum(A + B), Freq}
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int CD = C[i] + D[j];
                map.put(CD, map.getOrDefault(CD, 0) + 1);
            }
        }
        
        // Question boils down to finding 2 elements with a target,
        // Cannot use 2 pointer because nor array is sorted
        // nor there is just a single array to iterate from start and end
        // So, instead use HM and find for (target - something) in HM
        
        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int AB = A[i] + B[j];
                count += map.getOrDefault(target - AB, 0); // Search for target - AB in HM
            }
        }
        return count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Brute Force
// TLE // O(N^4)

class Solution {
    
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        for(int i = 0; i < nums1.length; i++) {
            for(int j = 0; j < nums2.length; j++) {
                for(int k = 0; k < nums3.length; k++) {
                    for(int l = 0; l < nums4.length; l++) {
                        if(nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
