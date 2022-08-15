// codingninjas.com/codestudio/problems/maximum-path-sum-between-two-leaves_794950?leftPanelTab=1
// GFG pe wrong tc's hai

// Using Pair class for leafToleaf

public class Solution {

	public static class Pair {
        long nodeToleaf;
        long leafToleaf;
        public Pair(long nodeToleaf, long leafToleaf) {
            this.nodeToleaf = nodeToleaf;
            this.leafToleaf = leafToleaf;
        }
    }
    
    public static long findMaxSumPath(TreeNode root) {
        Pair ans = maxPathSumHelper(root);
        return ans.leafToleaf == Long.MIN_VALUE ? -1 : ans.leafToleaf; 
        // Agar kvi v max update nai hua => Leaf to Leaf path aaj tak nahi mil paaya
        // So return -1, else jo v mila hai max me leaf to leaf max usko return kar do
    }
    
    public static Pair maxPathSumHelper(TreeNode root) {
        if(root == null) return new Pair(Long.MIN_VALUE, Long.MIN_VALUE);
        if(root.left == null && root.right == null) return new Pair(root.data, Long.MIN_VALUE);
        
        // Mere ko apna maxPathSum find karne k liye chahiye
        // koi left ka maxSum "Path" dede
        // koi right ka maxSum "Path" dede
        // So mera answer hoga : left + right + mai khud 
        
        Pair left = maxPathSumHelper(root.left); 
        Pair right = maxPathSumHelper(root.right); 
        
        long currLeafToleaf = Math.max(left.leafToleaf, right.leafToleaf); // Mera ans ya toh left ya right subtree me kvi pehle he bn gaya hoga
        if(root.left != null && root.right != null) { // Ya toh mai ans bnwa sakti hu
            currLeafToleaf = Math.max(currLeafToleaf, left.nodeToleaf + right.nodeToleaf + root.data); // Mera tak ka max path sum
        }
        long currNodeToleaf = Math.max(left.nodeToleaf, right.nodeToleaf) + root.data;  // Khud se shuru hone wala longest path currNodeToleaf me daal do
        
        return new Pair(currNodeToleaf, currLeafToleaf); 
    }
    
}

//--------------------------------------------------------------------------------------------------------------------------------------------
// Using static for leafToleaf

public class Solution {

    // static me nai bnana hai toh heap me ans bna lo, ya Pair bna k return krwa lo
    public static long max; // LeafToLeaf_MaxSoFar;
    
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
