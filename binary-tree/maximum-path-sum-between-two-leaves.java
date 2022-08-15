// codingninjas.com/codestudio/problems/maximum-path-sum-between-two-leaves_794950?leftPanelTab=1
// GFG pe wrong tc's hai

public class Solution {

	public static long max; // LeafToLeaf_MaxSoFar;
    // static me nai bnana hai toh heap me ans bna lo, ya Pair bna k return krwa lo
    
    public static long findMaxSumPath(TreeNode root) {
        max = Long.MIN_VALUE;
        maxPathSumHelper(root);
        return max == Long.MIN_VALUE ? -1 : max; 
        // Agar kvi v max update nai hua => Leaf to Leaf path aaj tak nahi mil paaya
        // So return -1, else jo v mila hai max me leaf to leaf max usko return kar do
    }
    
    public static long maxPathSumHelper(TreeNode root) {
        if(root == null) return Long.MIN_VALUE;
        if(root.left == null && root.right == null) return root.data;
        
        // Mere ko apna maxPathSum find karne k liye chahiye
        // koi left ka maxSum "Path" dede
        // koi right ka maxSum "Path" dede
        // So mera answer hoga : left + right + mai khud 
        
        long leftMaxSumPath = maxPathSumHelper(root.left); 
        long rightMaxSumPath = maxPathSumHelper(root.right); 
        
        if(root.left != null && root.right != null) {  // NOTE : Jab dono left && right exist krta hoga kisi node ka tvi wo LeafToLeaf kehlaega
            max = Math.max(max, leftMaxSumPath + rightMaxSumPath + root.data); // Mera tak ka max path sum
        }
        return Math.max(leftMaxSumPath, rightMaxSumPath) + root.data; // Khud se shuru hone wala longest path return krwa do
    }
    
}
