// https://leetcode.com/problems/path-sum-iii/

// Same logic as number of substrings with sum equals to k
// You just have to figure out ki ek path ko agar explore kr liye hai toh 
// map se usko uda do kuki agar substring k nazariye se dekho toh
// kisi dusre path ka bnda mera substring ka part ho he nahio skta, 
// so udda do us path k bnde ko backtrack krte waqt
// TC : O(N)

class Solution {
    
    int ans;
    
    public int pathSum(TreeNode root, int tar) {
        ans = 0;
        Map<Long, Integer> map = new HashMap<>();
        map.put((long)0, 1);
        pathSum(root, 0, tar, map);
        return ans;
    }
    
    public void pathSum(TreeNode root, long ssf, int tar, Map<Long, Integer> map) {
        if(root == null) return;
        
        long prefixSum = ssf + root.val;
        if(map.containsKey(prefixSum - tar)) { // (prefixSum - tar) kya map me exist krta h ??
            ans += map.get(prefixSum - tar);  // Agar haan toh apna ans bnao
        }
        
        // Khud k prefixSum ko map me daal do
        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1); 
        
        pathSum(root.left, prefixSum, tar, map);
        pathSum(root.right, prefixSum, tar, map);
        
        // Backtrack from map, kuki tumko ek path chahiye
        // Aur is paint se tumhara ek new branch bnega, 
        // so pichle path k calculations ko uda do apne map se
        int freqOfPrefixSum = map.get(prefixSum);
        if(freqOfPrefixSum - 1 == 0) map.remove(prefixSum);
        else map.put(prefixSum, freqOfPrefixSum - 1);
    }
    
}

---------------------------------------------------------------------------------------------------------------------------------------

// TC : O(N^2)
// Think of it as an array, jisme number of subarrays nikalna hai, brute force tareeke se substrings nikalte hai O(N^2), 
// ye logic same hai, bus tree hai instead of array.
// Approach : Har node ko fix karo and usi node se sum bnane ka starting karo for rest of the below nodes, agar tar k equal hai toh ans bna lo apna.  
// so uska sum lete chalo and tar se dekh lo equal hai ya nahi, agar qual hai toh count badha lo simple.

class Solution {
    
    int count;
    public int pathSum(TreeNode root, int targetSum) {
        count = 0;
        fixNode(root , targetSum);
        return count;
    }
    
    public void fixNode(TreeNode node , int targetSum) {
        if(node == null) return;
        pathSum(node , 0 , targetSum);
        
        fixNode(node.left , targetSum);
        fixNode(node.right , targetSum);
    }
    
    public void pathSum(TreeNode node , long csum , int targetSum) {
        if(node == null) return;
        if(csum+node.val == targetSum) count++;
        pathSum(node.left , csum+node.val , targetSum);
        pathSum(node.right , csum+node.val , targetSum);
    }
    
}
