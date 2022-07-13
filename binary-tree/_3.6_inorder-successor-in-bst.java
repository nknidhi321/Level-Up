// https://practice.geeksforgeeks.org/problems/inorder-successor-in-bst/1/

// Travelling Left Root Right because, you have to find successor, basically iterating from start and the first value > num is your ans
// Since u have to find successor, you can discard the left half of the tree by comparing it with the given num(boundary)
// TC 
// Average case : O(logN)
// Worst Case : O(N) [A number beyond the min of the tree]

public class Solution {
   
    public TreeNode inorderSuccessor(TreeNode root, TreeNode num) {
        TreeNode[] ans = new TreeNode[] {null};
        findInOrderSuccessor(root, num, ans);
        return ans[0];
    }
    

    boolean findInOrderSuccessor(TreeNode root, TreeNode num, TreeNode[] ans) {
        if(root == null) return false;
        
        if(root.val > num.val) {  // Optimization // TC : O(logN)
          if(findInOrderSuccessor(root.left, num, ans)) return true;
        }
        if(root.val > num.val) {
          ans[0] = root;
          return true;
        }
        if(findInOrderSuccessor(root.right, num, ans)) return true;
        
        return false;
    }

}

---------------------------------------------------------------------------------------------------------
    
class Solution {

    public Node inorderSuccessor(Node root, Node k) {
        Node returnValue = null;
        int prev = -1;
        Node curr = root;
        while (curr != null) {
            Node left = curr.left;
            if (left == null) {
                if (prev == k.data) returnValue = curr;
                prev = curr.data;
                curr = curr.right;
            } else {
                Node rightMostNode = getRightMostNode(left, curr);
                if (rightMostNode.right == null) { // thread create
                    rightMostNode.right = curr;
                    curr = curr.left;
                } else { // thread destroy
                    rightMostNode.right = null;
                    if (prev == k.data) returnValue = curr;
                    prev = curr.data;
                    curr = curr.right;
                }
            }
        }
        return returnValue;
    }

    public static Node getRightMostNode(Node node, Node curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }

}
