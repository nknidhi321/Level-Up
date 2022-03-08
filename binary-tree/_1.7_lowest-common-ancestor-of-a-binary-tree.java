// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

/*
      Get the path from p to root and q to root, store them in list
      The idea is, the value of all the nodes, upto the LCA from the root will be the same from the root till the LCA.
      Check this in path1 (for p) and path2 (for q), values will be same upto a certain position from root to LCA. (From right to left in the list, since the p or q node is added first in the list and then the parent nodes above them).
      So, keep a while loop iterating from the right of the list and the moment you find different value iterating from the root (end of the list), the prev at that point will contain your LCA TreeNode, since prev contains the previous TreeNode.
*/

// Rajneesh
// Using extra space

```
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> path1 = findPath(root, p.val);
        ArrayList<TreeNode> path2 = findPath(root, q.val);
        
        int i = path1.size() - 1;
        int j = path2.size() - 1;
        TreeNode prev = null; 
        while(i >= 0 && j >= 0){
            if(path1.get(i).val != path2.get(j).val) break;
            prev = path1.get(i);
            i--; j--;
        }
        return prev;
    }
    
    public static ArrayList<TreeNode> findPath(TreeNode root, int target){
        if(root == null) return new ArrayList<>();
        
        if(root.val == target){
            ArrayList<TreeNode> ans = new ArrayList<>();
            ans.add(root);
            return ans;
        }
        
        ArrayList<TreeNode> left = findPath(root.left, target);
        if(left.size() > 0){
            left.add(root);
            return left;
        }
        ArrayList<TreeNode> right = findPath(root.right, target);
        if(right.size() > 0){
            right.add(root);
            return right;
        }
        
        return new ArrayList<>(); 
    }
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Rajneesh
// Using static LCANode but without extra space

```
class Solution {
    
    public static TreeNode LCANode;
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        
        LCANode = null;
        LCA_Util(root, p, q);
        return LCANode;
    }
        
    public static boolean LCA_Util(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        
        boolean selfPresent = (root == p || root == q); // When either p or q will be LCA

        boolean leftPresent = LCA_Util(root.left, p, q);
        if (LCANode != null) return true; // LCANode mil gaya sidhe return

        boolean rightPresent = LCA_Util(root.right, p, q);
        if (LCANode != null) return true; // LCANode mil gaya sidhe return

        if ((leftPresent && rightPresent) || (leftPresent && selfPresent) || (rightPresent && selfPresent)) LCANode = root;

        return leftPresent || rightPresent || selfPresent;
    }
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
