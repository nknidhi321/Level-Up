// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/construct-bst-from-inorder-traversal/ojquestion

// [At Leetcode]
```
class Solution {
    
    public TreeNode sortedArrayToBST(int[] inOrder) {
        return constructFromInOrder(0, inOrder.length - 1, inOrder);
    }
    
    public static TreeNode constructFromInOrder(int si, int ei, int[] inOrder) {
        if(si > ei) return null;
        
        int mid = si + (ei - si) / 2;
        TreeNode root = new TreeNode(inOrder[mid]);
        root.left = constructFromInOrder(si, mid - 1, inOrder);
        root.right = constructFromInOrder(mid + 1, ei, inOrder);
        return root;
    }
    
}
```
------------------------------------------------------------------------------------
