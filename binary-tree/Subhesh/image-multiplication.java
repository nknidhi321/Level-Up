// https://practice.geeksforgeeks.org/problems/image-multiplication0627/1
// Similar to symmetry of tree

 class Solution {
     
    long mod = (long)(1e9 + 7);
    
    public long imgMultiply(Node root) {
        return ((long)(root.data * root.data) % mod + getAns(root.left, root.right)) % mod;         
    }
    
    public long getAns(Node left, Node right) {
        if(left == null || right == null) return 0;
        return (((left.data * right.data) % mod) + (getAns(left.left, right.right) + getAns(left.right, right.left)) % mod) % mod;
    }
    
}
