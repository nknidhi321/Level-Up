// https://leetcode.com/problems/binary-tree-maximum-path-sum/
// Kadan's Algo, Using Pair class for maxSoFar

class Solution {

	public class Pair {
        int nodeToNode;
        int maxSoFar; // nodeToNode_maxSoFar
        public Pair(int nodeToNode, int maxSoFar) {
            this.nodeToNode = nodeToNode;
            this.maxSoFar = maxSoFar;
        }
    }
    
    public int maxPathSum(TreeNode root) {
        Pair ans = maxPathSumHelper(root);
        return ans.maxSoFar;
    }
    
    public Pair maxPathSumHelper(TreeNode root) {
        if(root == null) return new Pair(Integer.MIN_VALUE, Integer.MIN_VALUE);
        
        // Mere ko apna maxPathSum find karne k liye chahiye
        // koi left ka maxSum "Path" dede
        // koi right ka maxSum "Path" dede
        // So mera answer hoga : left + right + mai khud 
        
        Pair left = maxPathSumHelper(root.left); 
        Pair right = maxPathSumHelper(root.right); 
        
        int leftNodeToNode = left.nodeToNode;
        int rightNodeToNode = right.nodeToNode;
        if(left.nodeToNode < 0) leftNodeToNode = 0;
        if(right.nodeToNode < 0) rightNodeToNode = 0;
        
        // Mera ans ya toh left ya right subtree me kvi pehle he bn gaya hoga, 
        // ya mai max path sum bna sakti hu
        int currMaxSoFar = Math.max(Math.max(left.maxSoFar, right.maxSoFar), 
                                    leftNodeToNode + rightNodeToNode + root.val); 
        
        // Khud se shuru hone wala longest path currNodeToNodedaal do
        int currNodeToNode = Math.max(leftNodeToNode, rightNodeToNode) + root.val;  
        
        return new Pair(currNodeToNode, currMaxSoFar); 
    }
    
}
//------------------------------------------------------------------------------------------
// Kadan's Algo, Using static for maxSoFar

class Solution {
    
    public static int max;
    
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return max;
    }
    
    public int maxPathSumHelper(TreeNode root) {
        if(root == null) return 0;
        
        // Mere ko apna maxPathSum find karne k liye chahiye
        // koi left ka maxSum "Path" dede
        // koi right ka maxSum "Path" dede
        // So mera answer hoga : left + right + mai khud 
        
        // left ya right se agar less than 0 aaegi toh hum "khudse" kahani bnana shuru karenge
        // Niche wala karja me hai toh mai apni alag khandan bnaunga, so left || right se maxSumPath 0 kar lo
        int leftMaxSumPath = Math.max(0, maxPathSumHelper(root.left)); 
        int rightMaxSumPath = Math.max(0, maxPathSumHelper(root.right)); 
        
        max = Math.max(max, leftMaxSumPath + rightMaxSumPath + root.val); // Mera tak ka max path sum
        
        // Any xth node will say mere se niche yahi MaxSumPath mil sakta hai aapko zyada se zyada
        return Math.max(leftMaxSumPath, rightMaxSumPath) + root.val; // And this will be required to my parent
    }
    
}
