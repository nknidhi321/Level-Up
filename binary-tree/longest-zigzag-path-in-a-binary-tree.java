// https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/

// returning maxSoFar in Pair class 
```
class Solution {
    
    public static class Pair {
        int forwardSlash;  // Apne se shuru hone wala forwardSlash ka count
        int backwardSlash; // Apne se shuru hone wala backwardSlash ka count
        int maxSoFar; // Ab tak ka max forwardSlash ya backwardSlash
        // maxSoFar ko static le lete tb v baat bn jaati, har nodes pe max compete krwa lete
        
        public Pair(int forwardSlash, int backwardSlash, int maxSoFar) {
            this.forwardSlash = forwardSlash;
            this.backwardSlash = backwardSlash;
            this.maxSoFar = maxSoFar;
        }
    }
    
    public int longestZigZag(TreeNode root) {
        Pair pair = longestZigZag_Util(root);
        return pair.maxSoFar;
    }
    
    public static Pair longestZigZag_Util(TreeNode root) {
        if(root == null) return new Pair(-1, -1, 0);  // {left, right, maxSoFar}
        
        Pair l = longestZigZag_Util(root.left);
        Pair r = longestZigZag_Util(root.right);
        
        // Mujhe left/forward zigzag bnana hai => child node ka right/backward slope use krna hoga
        int left = l.backwardSlash + 1;  // Mere ko include kar k left zigzag
        
        // Mujhe right/backward zigzag bnana hai => child node ka left/forward slope use krna hoga
        int right = r.forwardSlash + 1;  // Mere ko include kar k right zigzag
        
        // Max zigzag can come from either left or right or it can be the chain including me
        int maxSoFar = Math.max(Math.max(l.maxSoFar, r.maxSoFar), Math.max(left, right));  
        
        return new Pair(left, right, maxSoFar);
    }
}
```
-------------------------------------------------------------
// returning maxSoFar in static or global
```
class Solution {
    
    int maxSoFar; // har nodes pe max compete krwa hai

    public class Pair {
        int forwardSlash;  // Apne se shuru hone wala forwardSlash ka count
        int backwardSlash; // Apne se shuru hone wala backwardSlash ka count
        
        public Pair(int forwardSlash, int backwardSlash) {
            this.forwardSlash = forwardSlash;
            this.backwardSlash = backwardSlash;
        }
    }
    
    public int longestZigZag(TreeNode root) {
        maxSoFar = 0;
        longestZigZag_Util(root);
        return maxSoFar;
    }
    
    public Pair longestZigZag_Util(TreeNode root) {
        if(root == null) return new Pair(-1, -1);
        
        Pair l = longestZigZag_Util(root.left);
        Pair r = longestZigZag_Util(root.right);
        
        // Mujhe left/forward zigzag bnana hai => child node ka right/backward slope use krna hoga
        int left = l.backwardSlash + 1;  // Mere ko include kar k left zigzag
        
        // Mujhe right/backward zigzag bnana hai => child node ka left/forward slope use krna hoga
        int right = r.forwardSlash + 1;  // Mere ko include kar k right zigzag
        
        // Including me max zigzag can come from either left or right, or maxSoFar can be maxSoFar
        maxSoFar = Math.max(maxSoFar, Math.max(left, right));  
        
        return new Pair(left, right);
    }
    
}
```
-------------------------------------------------------------
