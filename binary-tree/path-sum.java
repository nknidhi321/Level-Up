// https://leetcode.com/problems/path-sum/

class Solution {
    
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        if(root.left == null && root.right == null) {
            return (targetSum - root.val) == 0 ? true : false;  
        }
        
        if(hasPathSum(root.left, targetSum - root.val)) return true;
        if(hasPathSum(root.right, targetSum - root.val)) return true;
        return false;
    }
}
