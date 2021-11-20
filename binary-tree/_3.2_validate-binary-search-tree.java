//https://leetcode.com/problems/validate-binary-search-tree/

/*

Intuition:-
Since it is BST, It's Inorder traversal will be sorted.
So, Using morrisInOrderTravsersal, wherever you print the algorithm do the following:-

Keep a prev pointer which will always carry the prev value of the current node,
And make a check that if prev is greater or equal to curr.val then it is not a BST.

NOTE :-
1) Make prev as Long, check the constraint, it exceeds Integer range.
2) Make sure to iterate the whole tree(Algo) to cut down the thread which you created while traversing or you may get wrong result.

*/

class Solution {
    public boolean isValidBST(TreeNode root) {
        return morrisInOrderTraversal(root);
    }
    
    public TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        while(node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }
    
    public boolean morrisInOrderTraversal(TreeNode root) {
        long prev = Long.MIN_VALUE;
        boolean flag = true;
        TreeNode curr = root;
        
        while(curr != null) {
            TreeNode left = curr.left;
            
            if(left == null) {
                if(prev >= curr.val && flag == true) flag = false;
                prev = curr.val;
                curr = curr.right;
            }
            else {
                TreeNode rightMostNode = getRightMostNode(left, curr);

                if(rightMostNode.right == null) {
                    rightMostNode.right = curr;
                    curr = curr.left;
                }
                else {
                    rightMostNode.right = null;
                    if(prev >= curr.val && flag == true) flag = false;
                    prev = curr.val;
                    curr = curr.right;
                }
            }
        }
        return flag;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


//Kevin

class Solution {
    public boolean isValidBST(TreeNode root) {
        return BST(root,null,null);
    }
    public static boolean BST(TreeNode root, Integer min, Integer max){
        if(root == null)
            return true;
        if((min != null && root.val <= min) || (max != null && root.val >= max))
            return false;
        return BST(root.left, min, root.val) && BST(root.right, root.val, max);
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
