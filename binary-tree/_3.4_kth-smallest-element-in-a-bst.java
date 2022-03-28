// https://leetcode.com/problems/kth-smallest-element-in-a-bst/

// Using stack, TC : O(N), SC : O(logN) 

```
class Solution {
    
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        addAllLeft(root, stack);
        
        int count = 0;
        while(!stack.isEmpty()) {
            TreeNode topNode = stack.pop();
            if(++count == k) return topNode.val;
            addAllLeft(topNode.right, stack);
        }
        return -1;
    }
    
    public static void addAllLeft(TreeNode node, Stack<TreeNode> stack) {
        while(node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    
}
```

-------------------------------------------------------------------------------

// Inorder Traversal is alreary sorted in BST
// Time complexity : O(N), SC : O(N)

```
class Solution {
    
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return 0;
        List<Integer> list = new ArrayList<>();
        getElementsinInOrder(root, list);
        return list.get(k-1);
    }
    
    public static void getElementsinInOrder(TreeNode root, List<Integer> list){
        if(root == null) return;
       
        getElementsinInOrder(root.left, list);
        list.add(root.val);
        getElementsinInOrder(root.right, list);
    }
    
}
```

---------------------------------------------------------------------------------

// Brute force approach 
// Travelling in preOrder
// TC : O(N) + O(NlogN), Adding all nodes val in list O(N) + Sorting O(NlogN) 
// SC : O(N)

```
class Solution {
 
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return 0;
        List<Integer> list = new ArrayList<>();
        getElementsInPreOrder(root, list);
        Collections.sort(list);
        return list.get(k-1);
    }
    
    public static void getElementsInPreOrder(TreeNode root, List<Integer> list){
        if(root == null) return;  
        
        list.add(root.val);
        getElementsInPreOrder(root.left, list);
        getElementsInPreOrder(root.right, list);
    }
    
}
```

-----------------------------------------------------------------------------------
