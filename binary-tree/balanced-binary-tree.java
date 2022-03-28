// https://leetcode.com/problems/balanced-binary-tree/

TC : O(N), Using Pair class
```
class Solution {

    public static class Pair {
        int height;
        boolean isSubtreeBal;
        
        public Pair() { // Consider it base case and set values
            height = -1;  // height in terms of edge
            isSubtreeBal = true;
        }
    }
    
    public boolean isBalanced(TreeNode root) {
        Pair ans = isBalanced_Util(root);
        return ans.isSubtreeBal;
    }
      
    public Pair isBalanced_Util(TreeNode root) {
        if(root == null) return new Pair();
        
        Pair currPair = new Pair();
        
        Pair l = isBalanced_Util(root.left);
        Pair r = isBalanced_Util(root.right);
        
        if(l.isSubtreeBal && r.isSubtreeBal && Math.abs(l.height - r.height) <= 1) currPair.isSubtreeBal = true;
        else currPair.isSubtreeBal = false;
        
        currPair.height = Math.max(l.height, r.height) + 1;
        return currPair;
    }
    
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

TC : O(N), Using global varibale
```
class Solution {
    
		public boolean isBalanced = true;
		
		public boolean isBalanced(TreeNode root) {
        height(root);
        return isBalanced; 
    }
    
    public int height(TreeNode root) {
        if(root == null) return -1;
        
        int lh = height(root.left);
        int rh = height(root.right);
        
        if(!(Math.abs(lh - rh) <= 1)) {
            isBalanced = false;
        }
        return Math.max(lh, rh) + 1;
    }
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

TC : O(N^2), Using another get method for height and returning isBalanced is own recursive function

```
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        
        return isBalanced(root.left) 
            && isBalanced(root.right) 
            && (Math.abs(height(root.left) - height(root.right)) <= 1);
    }
    
    public static int height(TreeNode root){
        if(root == null) return 0;    //height in terms of nodes
        
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
```
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
