// https://leetcode.com/problems/recover-binary-search-tree/

class Solution {
    
    public void recoverTree(TreeNode root) {
        TreeNode a = null, b = null;
        TreeNode prev = null, curr = root;
        while(curr != null) {
            TreeNode left = curr.left;
            if(left == null) {
                if(prev != null && prev.val > curr.val) {
                    if(b != null) b = curr;
                    else {
                        a = prev;
                        b = curr;
                    }
                }
                
                prev = curr;
                curr = curr.right;
            }
            else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if(rightMostNode.right == null) {
                    rightMostNode.right = curr; // thread create
                    curr = curr.left; // move to left
                }
                else {
                    if(prev != null && prev.val > curr.val) {
                        if(b != null) b = curr;
                        else {
                            a = prev;
                            b = curr;
                        }
                    }
                    
                    rightMostNode.right = null; // thread break
                    prev = curr;
                    curr = curr.right; // move to right
                }
            }
        }
        
        // Swap a and b
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
    
    public TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        while(node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    } 
    
}
