// https://leetcode.com/problems/longest-univalue-path/

class Solution {
    
    class Pair {
        int data; // data of immediate child
        int len; // nodeToNode max len of immediate child data
        int max; // maxLenSoFar from all subtrees
        public Pair(int data, int len, int max) {
            this.data = data;
            this.len = len;
            this.max = max;
        }
    }
    
    public int longestUnivaluePath(TreeNode root) {
        if(root == null) return 0;  
        return getLongPath(root).max;
    }
    
    public Pair getLongPath(TreeNode root) {
        if(root == null) return new Pair((int)1e9, -1, -(int)1e9);
        
        Pair l = getLongPath(root.left);
        Pair r = getLongPath(root.right);
        
        int currMax, currLen; 
        if(l.data == root.val && r.data == root.val) { // curve path
            currMax = Math.max(Math.max(l.max, r.max), l.len + 2 + r.len);
            currLen = Math.max(l.len, r.len) + 1;
        }
        else if(l.data == root.val) { // left path
            currMax = Math.max(Math.max(l.max, r.max), l.len + 1);
            currLen = l.len + 1;
        }
        else if(r.data == root.val) { // right path
            currMax = Math.max(Math.max(l.max, r.max), 1 + r.len);
            currLen = 1 + r.len;
        }
        else  { // (l.data != root.val && r.data != root.val) // new path 
            currMax = Math.max(Math.max(l.max, r.max), 0);
            currLen = 0;
        }
        
        // NOTE : You cannot set currLen here at once, 
        // because you need to set your currLen acc to what "data" is received
        // from your childs. So, currLen will be formed accordingly.
        
        return new Pair(root.val, currLen, currMax);
    }
    
}
