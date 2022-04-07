// https://leetcode.com/problems/distribute-coins-in-binary-tree/ 

class Solution {
    
    public int distributeCoins(TreeNode root) {
        ans = 0;
        distributeCoins_(root);
        return ans;
    }
    
    public static int ans;
    
    public static int distributeCoins_(TreeNode root) {
        if(root == null) return 0;
        
        int left = distributeCoins_(root.left);
        int right = distributeCoins_(root.right);
        
        ans += Math.abs(left) + Math.abs(right); // Jitna extra ya deficient hoga utne he moves toh krwane parenge
        return left + right + root.val - 1; // -1 apne naam k coin ka //Jitne excess ya deficient hai return 
    }
    
}
