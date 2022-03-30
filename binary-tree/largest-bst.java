// https://practice.geeksforgeeks.org/problems/largest-bst/1

class Solution {
    
    public static class BSTPair {
        boolean isBST;
        int min;
        int max;
        int size;
        Node largestRoot;
        
        public BSTPair() {
            isBST = true;
            min = (int)1e9;
            max = -(int)1e9;
            size = 0;
            largestRoot = null;
        }
    }
    
    static int largestBst(Node root) {
        return largestBST_(root).size;
    }
    
    public static BSTPair largestBST_(Node root) {
        if(root == null) return new BSTPair();
    
        BSTPair lp = largestBST_(root.left);
        BSTPair rp = largestBST_(root.right);
    
        BSTPair myPair = new BSTPair();
        myPair.isBST = false;
        if (lp.isBST && rp.isBST && lp.max < root.data && root.data < rp.min) {
            myPair.isBST = true;
            myPair.min = Math.min(lp.min, root.data);
            myPair.max = Math.max(rp.max, root.data);
            myPair.size = lp.size + rp.size + 1;
            myPair.largestRoot = root;
        }
        else {
            if (lp.size > rp.size) {
                myPair.size = lp.size;
                myPair.largestRoot = lp.largestRoot;
            }
            else {
                myPair.size = rp.size;
                myPair.largestRoot = rp.largestRoot;
            }
        }
        return myPair;
    }
    
}
