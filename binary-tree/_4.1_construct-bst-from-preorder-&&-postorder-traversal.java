// https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
// Follow up for postoder is at below


// NOTE : PreOrder && PostOder me jo tree ki "structure and value" hoti h wo ditto same bnti h but this is not true for inorder,
// structure maybe retain kr v sakte par guarenteed same value retain nahi kr paaoge


// Construct BST from PreOrder
// Strictly increasing and decreasing
// Mentioned  <= and >= for base condition of min and max checking
// NOTE : Suppose given preorder ka ek "BT" bna k deta aur kaha jata ki same structure wali "BST" bna k do
// from given preorder array then => min, max range set kar k he BST bna paaoge, in O(1) Space and O(N) time

```
class Solution {
    
    public TreeNode bstFromPreorder(int[] preorder) { 
        int[] idx = new int[] {0};
        return Util(Integer.MIN_VALUE, Integer.MAX_VALUE, idx, preorder); 
    }
    
    
    // This idx[] array for iterating over the given array, alternative for not using static idx
    public TreeNode Util(int min, int max, int[] idx, int[] preorder) { 

        // Jis idx pe tum khare ho, agar wo tumhare "min < ele < max" k range me nahi aata then wo bnda waha nahi lg sakta
        // => So, tum apne parent ko null return kr do
        if(idx[0] == preorder.length || preorder[idx[0]] <= min || preorder[idx[0]] >= max) return null; 
        
        TreeNode root = new TreeNode(preorder[idx[0]++]);
        
        root.left = Util(min, root.val, idx, preorder); // Agar aap left me jaaoge => Aap right boundary set karoge 
        root.right = Util(root.val, max, idx, preorder);// Agar aap right me jaaoge => Aap left boundary set karoge
        return root;
    }
    
}
```

------------------------------------------------------------------------------------------------------------------------
// https://practice.geeksforgeeks.org/problems/construct-bst-from-post-order/1#

// Construct BST from PostOrder
// Not strictly increasing or decreasing
// NOTE : Suppose given PostOrder ka ek "BT" bna k deta aur kaha jata ki same structure wali "BST" bna k do
// from given PostOrder array then => min, max range set kar k he BST bna paaoge, in O(1) Space and O(N) time

```
class GFG   {
    
    public static Node constructTree(int postorder[], int n) {
        int[] idx = new int[] {n - 1};
        return Util(Integer.MIN_VALUE, Integer.MAX_VALUE, idx, postorder);
    }
    
    public static Node Util(int min, int max, int[] idx, int[] postorder) {
        if(idx[0] == -1 || postorder[idx[0]] < min || postorder[idx[0]] > max) return null;
        
        Node root = new Node(postorder[idx[0]--]);
        
        root.right = Util(root.data, max, idx, postorder);
        root.left = Util(min, root.data, idx, postorder);
        return root;
    }
    
}
```
-----------------------------------------------------------------------------------------------------------------------------
