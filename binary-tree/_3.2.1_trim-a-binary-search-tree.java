// https://leetcode.com/problems/trim-a-binary-search-tree/

// Simple BS

class Solution {
    
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null) return root;
        
        if(low <= root.val && root.val <= high) { // within range
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
        else if(low > root.val) { // out of range
            return trimBST(root.right, low, high);
        }
        else if(high < root.val) { // out of range
            return trimBST(root.left, low, high);
        }
        return root;
    }
    
}
