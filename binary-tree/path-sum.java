// https://leetcode.com/problems/path-sum/

// Ex : root = [1,2] , tar = 1, Note here root 1 cannot be considered as leaf
// Because A leaf is a node with no children, so answer is false

// Ex : root = [1] , tar = 1, here root 1 is root + leaf, so answer is true

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        return hasPathSum_(root, targetSum);
    }
    public boolean hasPathSum_(TreeNode root, int targetSum) {
        if(root == null) return false;
        if(root.left == null && root.right == null) {
            if(targetSum - root.val == 0) return true;
            return false;
        }
        return hasPathSum_(root.left, targetSum - root.val) || hasPathSum_(root.right, targetSum - root.val);
    }
}
