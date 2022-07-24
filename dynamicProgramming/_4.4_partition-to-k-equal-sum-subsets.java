// https://leetcode.com/problems/partition-to-k-equal-sum-subsets/

class Solution {
    
    public boolean canPartitionKSubsets(int[] nums, int k) {
        
        int n = nums.length;
        int sum = 0, maxEle = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            maxEle = Math.max(maxEle, nums[i]);
        }
        
        // kya tumhara sum k equal parts me divide ho sakta hai? (sum % k != 0)
        // jitna ek subset sum ka sum hona chahiye tha, (maxEle > sum / k)
        // kya koi ek element aisa hai jo us subSet sum ko v exceed kar raha ho 
        if(sum % k != 0 || maxEle > sum / k) return false;
            
        boolean[] vis = new boolean[n];
        return canPartitionKSubsets_Rec(0, k, 0, sum / k, n, vis, nums);
    }
    
    public static boolean canPartitionKSubsets_Rec(int idx, int k, int ssf, int tar, int n, boolean[] vis, int[] nums) {
        if(k == 0) return true;
        if(ssf > tar) return false;
        if(ssf == tar) return canPartitionKSubsets_Rec(0, k - 1, 0, tar, n, vis, nums);
        
        boolean res = false;
        for(int i = idx; i < n; i++) {  // Ex : [1, 7, 6, ..]   tar = 7, here if you have taken 1 you will skip 7
            if(!vis[i]) {
                vis[i] = true;
                res = res || canPartitionKSubsets_Rec(i + 1, k, ssf + nums[i], tar, n, vis, nums);
                vis[i] = false;
            }
        }
        return res;
    }
    
}
