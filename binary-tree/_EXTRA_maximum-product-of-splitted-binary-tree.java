// https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/

/*
    Just reach to a node and assume if you would break your left edge
    Ask your leftSubTree to get the sum and get the remaining by subtracting the leftSubTree sum from the whole treeSum 
    Similarly, for right edge
    Lastly, compete both of the answers with answerSoFar 
*/

```
class Solution {
    
    int mod = (int)1e9 + 7;

    public int maxProduct(TreeNode root) {
        long treeSum = sumOfTree(root);
        long[] ans = new long[1];
        calMaxProduct(root, treeSum, ans);
        return (int)(ans[0] % mod); // Asked in the question to do the mod at the end
    }
    
    public long calMaxProduct(TreeNode root, long treeSum, long[] ans) {
        if(root == null) return 0;
        
        // Ex1 : [1,2,3,4,5,6]  forward slash is the edge to be removed
        // So, ask your leftsubtree to get the sum
        long leftSubtreeSum = calMaxProduct(root.left, treeSum, ans);
        long remainingTreeSum = treeSum - leftSubtreeSum;
        ans[0] = Math.max(ans[0], leftSubtreeSum * remainingTreeSum);
        
        // Ex2 : [1,null,2,3,4,null,null,5,6]  backward slash is the edge to be removed
        // So, ask your rightsubtree to get the sum
        long rightSubtreeSum = calMaxProduct(root.right, treeSum, ans);
        remainingTreeSum = treeSum - rightSubtreeSum;
        ans[0] = Math.max(ans[0], rightSubtreeSum * remainingTreeSum);
        
        return leftSubtreeSum + rightSubtreeSum + root.val;
    }
    
    public long sumOfTree(TreeNode root) {
        if(root == null) return 0;
        return sumOfTree(root.left) + sumOfTree(root.right) + root.val;
    }
    
}
```
