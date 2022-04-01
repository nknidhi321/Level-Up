// https://leetcode.com/problems/binary-tree-maximum-path-sum/

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
