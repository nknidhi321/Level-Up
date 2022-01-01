// https://practice.geeksforgeeks.org/problems/key-pair5616/1#

// HashSet
// TC: O(N)
// SC: O(N)

class Solution {

    boolean hasArrayTwoCandidates(int nums[], int n, int target) {
        
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            if(set.contains(target - nums[i])) return true;
            
            // Tumhara koi other half nai mil paaya => Tum kisi k other half ho saktey ho
            else set.add(nums[i]); 
        }
        return false;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Two Pointer
// TC : O(nlogn)
// SC : O(1)

class Solution {

    boolean hasArrayTwoCandidates(int nums[], int n, int target) {
        Arrays.sort(nums);

		// Two Pointer
        int start = 0, end = nums.length - 1;
        while(start < end) {
            int sum = nums[start] + nums[end];
            if(sum == target) return true;
            else if(sum < target) start++;
            else end--;
        }
        return false;
    }

}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
