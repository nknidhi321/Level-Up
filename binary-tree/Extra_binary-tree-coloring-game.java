// https://leetcode.com/problems/binary-tree-coloring-game/

/*
Claim is :-

    Red(Oponent, x) k chances minimize karne hai
    Blue(Khud) k chances maximize krne hai

    So, oponent k chances minimize kaise karoge ??
    jab hum apne turn pe oponent k maxSide k neighbour ko blue mark kar k aa jaaye, tab baat bn sakti hai.
    
    Now, oponent(x) k neighbour wale sides kon kon se hai ?? => x ka left size && right size && parent size 
    Ab in teeno me se jiska v maxSize hai, hum usko blue mark kar k aa jaaenge.
    
    And why this greedy approach is working ? Kuki hum apne jaiso ko he mark kar saktey hai
    And if I block a red's neighour with blue then red can never use that path to mark red,
    kuki we can only move to our neighbours jo ki mere jaise dikhte ho, so red can never hop blue and choose a nbr.
    
    Now, tino sides me se jo v max hai, agar wo n/2 se zyada hai, tvi mai jeet sakti hu, otherwise nai.
    Mere jeetne k liye aadhe se zyada log blue marked hone chahiye tvi baat bnegi. 
*/

class Solution {
    
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        int[] childSize = new int[2]; // {leftChildSizeOfx, rightChildSizeOfx}
        
        int sizeOfWholeTree = size(root, x, childSize);
        
        int leftChildSizeOfx = childSize[0];
        int rightChildSizeOfx = childSize[1];
        
        int parentSizeOfx =  sizeOfWholeTree - leftChildSizeOfx - rightChildSizeOfx - 1; // x k naam ka -1
        
        int max = Math.max(parentSizeOfx, Math.max(leftChildSizeOfx, rightChildSizeOfx));
        
        return max > n / 2  ? true : false;
    }
    
    public static int size(TreeNode root, int x, int[] childSize) {
        if(root == null) return 0;
        
        int leftSize = size(root.left, x, childSize);
        int rightSize = size(root.right, x, childSize);
        
        if(root.val == x) {
            childSize[0] = leftSize;
            childSize[1] = rightSize;
        }
        
        return leftSize + rightSize + 1;
    } 
    
}

----------------
// Same approach
// Efficient Approach
// Not travelling the entire tree once you found x
// The above solution is travelling the entire tree to calculate size

class Solution {
    
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        if(root == null) return false;
        if(root.val == x) {
            int leftSubtreeSize = size(root.left);
            int rightSubtreeSize = size(root.right);
            int parentRemainingSubtreeSize = n - leftSubtreeSize - rightSubtreeSize - 1;
            if(parentRemainingSubtreeSize > leftSubtreeSize + rightSubtreeSize + 1) return true;
            if(leftSubtreeSize > parentRemainingSubtreeSize + rightSubtreeSize + 1) return true;
            if(rightSubtreeSize > parentRemainingSubtreeSize + leftSubtreeSize + 1) return true;
            return false;
        }
        return btreeGameWinningMove(root.left, n, x) || btreeGameWinningMove(root.right, n, x);
    }
    
    public int size(TreeNode root) {
        if(root == null) return 0;
        return size(root.left) + size(root.right) + 1;
    }
    
}
----------------------------------------------------------------------------------------------------------------------
