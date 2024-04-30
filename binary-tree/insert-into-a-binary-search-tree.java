// https://leetcode.com/problems/insert-into-a-binary-search-tree/

class Solution {
    
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // Since it's a BST, you will land only once on this condition where the node should be inserted
        if(root == null) return new TreeNode(val, null, null);  
      
        if(val < root.val) root.left = insertIntoBST(root.left, val);
        if(val > root.val) root.right = insertIntoBST(root.right, val);
        return root;
    }
    
}
