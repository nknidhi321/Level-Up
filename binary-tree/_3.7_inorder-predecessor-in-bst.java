// https://www.lintcode.com/problem/915/

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
