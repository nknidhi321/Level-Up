// https://www.lintcode.com/problem/915/

// Travelling Right Root Left because, you have to find predecessor, basically iterating from end and the first value < num is your ans
// Since u have to find predecessor, you can discard the right half of the tree by comparing it with the given num(boundary)
// TC 
// Average case : O(logN)
// Worst Case : O(N) [A number beyond the max of the tree]

public class Solution {
 
    public TreeNode inorderPredecessor(TreeNode root, TreeNode num) {
        TreeNode[] ans = new TreeNode[] {null};
        findLargestSmallerKey(root, num, ans);
        return ans[0];
    }
    

    boolean findLargestSmallerKey(TreeNode root, TreeNode num, TreeNode[] ans) {
        if(root == null) return false;
        
        if(root.val < num.val) {  // Optimization // TC : O(logN)
          if(findLargestSmallerKey(root.right, num, ans)) return true;
        }
        if(root.val < num.val) {
          ans[0] = root;
          return true;
        }
        if(findLargestSmallerKey(root.left, num, ans)) return true;
        
        return false;
    }

}

------------------------------------------------------------------------------------------------------------------

// TC : O(N)
// Morris inorder traversal
    
public class Solution {

    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        TreeNode returnValue = null;
        TreeNode prev = null;
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                if (curr.val == p.val) returnValue = prev;
                prev = curr;
                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if (rightMostNode.right == null) { // thread create
                    rightMostNode.right = curr;
                    curr = curr.left;
                } else { // thread destroy
                    rightMostNode.right = null;
                    if (curr.val == p.val) returnValue = prev;
                    prev = curr;
                    curr = curr.right;
                }
            }
        }
        return returnValue;
    }

    public static TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }

}
