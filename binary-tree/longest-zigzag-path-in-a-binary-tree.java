// https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/

class Solution {
    
    public static class Pair {
        int forwardSlash;  // Apne se shuru hone wala forwardSlash ka count
        int backwardSlash; // Apne se shuru hone wala backwardSlash ka count
        int maxSoFar; // Ab tak ka max forwardSlash ya backwardSlash
        
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
        if(root == null) return new Pair(-1, -1, 0); // Why -1? counting in terms of edge
        
        Pair l = longestZigZag_Util(root.left);
        Pair r = longestZigZag_Util(root.right);
        
        int left = r.backwardSlash + 1;  // Mere ko include kar k left zigzag
        int right = l.forwardSlash + 1;  // Mere ko include kar k right zigzag
        
        // Max zigzag can come from either left or right or it can be the chain including me
        int maxSoFar = Math.max(Math.max(l.maxSoFar, r.maxSoFar), Math.max(left, right));  
        
        return new Pair(left, right, maxSoFar);
    }
}
