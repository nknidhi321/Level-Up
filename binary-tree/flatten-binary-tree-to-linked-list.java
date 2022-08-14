// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
// The idea is always keep a track of the newRightNode at any root and link it as per the question. 

class Solution {
    
    TreeNode newRightNode = null;
    
    public void flatten(TreeNode root){
         if(root == null) return;
        
        flatten(root.right);
        flatten(root.left);
        root.left = null;
        root.right = newRightNode;
        newRightNode = root;
    }
    
}
