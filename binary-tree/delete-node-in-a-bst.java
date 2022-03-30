// https://leetcode.com/problems/delete-node-in-a-bst/

class Solution {
    
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        
        if(key < root.val) root.left = deleteNode(root.left, key); // key is small, goto left
        else if(key > root.val) root.right = deleteNode(root.right, key); // key is large, goto right
        else { // Found the node to be deleted
            if(root.left != null && root.right != null) { // 2 child [both left and right]
                int val = getMax(root.left, Integer.MIN_VALUE);
                root.val = val;
                root.left = deleteNode(root.left, val);
                return root;
            }
            else if(root.left != null) { // 1 child [present in left]
                return root.left;
            }
            else if(root.right != null) { // 1 child [present in right]
                return root.right;
            }
            else { // 0 child [leaf node]
                return null;
            }
        }
        return root;
    }
    
    // Max. will be the right most node since BST
    public static int getMax(TreeNode root, int max) {
        TreeNode curr = root;
        while(curr != null) {
            max = Math.max(max, curr.val);
            curr = curr.right;
        }
        return max;
    }
    
}
