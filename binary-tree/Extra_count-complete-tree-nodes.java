// https://leetcode.com/problems/count-complete-tree-nodes/ 
// https://www.youtube.com/watch?v=u-yWemKGWO0

// Jo v subtree full BT ho uska sara nodes (2^level - 1) formula se nikal lo,
// baaki k nodes ko jaise size function lga k nikalte hai wasie nilal lo.
// TC : log2N

```
class Solution {
    
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        
        int leftDepth = leftDepth(root);
        int rightDepth = rightDepth(root);
        
        if(leftDepth == rightDepth) { // Size of complete BT
            return (int)Math.pow(2, leftDepth) - 1; // Size of complete BT : (2 ^ level - 1)
        }
        else { // Else jaise size nikalte hai waise nikal do
            int leftSize = countNodes(root.left);
            int rightSize = countNodes(root.right);
            return leftSize + rightSize + 1;
        }
    }
    
    public static int leftDepth(TreeNode root) {
        if(root == null) return 0;
        return leftDepth(root.left) + 1;
    }
    
    public static int rightDepth(TreeNode root) {
        if(root == null) return 0;
        return rightDepth(root.right) + 1;
    }
    
}
```

----------------------------------------------------------------------------------------------------------

TC : O(N), SC : O(1)

```
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
```


----------------------------------------------------------------------------------------------------------
