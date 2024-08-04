// https://leetcode.com/problems/kth-smallest-element-in-a-bst/

// Using Morris Traversal
// --k kuki 1 based indexing hai

```
class Solution {
    
    public int kthSmallest(TreeNode root, int k) {
        
        TreeNode curr = root;
        int returnValue = -1;
        while(curr != null) {
            TreeNode left = curr.left;
            if(left == null) {
                if(--k == 0) returnValue = curr.val; // [Don't return from here, because tree will remain modified]
                curr = curr.right;
            }
            else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if(rightMostNode.right == null) { // create thread
                    rightMostNode.right = curr;
                    curr = curr.left;
                }
                else { // destroy thread
                    rightMostNode.right = null;
                    if(--k == 0) returnValue = curr.val;  // [Don't return from here, because tree will remain modified]
                    curr = curr.right;
                }
            }
        }
        return returnValue;
    }
    
    public TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        while(node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }
    
}
```

-------------------------------------------------------------------------------

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
-------------------------------------------------------------------------------

// Inorder Traversal is alreary sorted in BST
// Keeping k in the array of 1 size and passing in parameter instead of using static variable
// Time complexity : O(N), SC : O(1)
```
class Solution {
    
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return 0;
        return getElementsinInOrder(root, new int[] {k});
    }
    
    // NOTE : Passing k-- in the parameter will give you levels and not what is expected here
    // So, keeping k in array instead of creating static variable
    public int getElementsinInOrder(TreeNode root, int[] arr) {
        if(root == null) return -1;
       
        int leftSmallest = getElementsinInOrder(root.left, arr);
        if(leftSmallest != -1) return leftSmallest;
        
        arr[0]--;
        if(arr[0] == 0) return root.val;
        
        int rightSmallest = getElementsinInOrder(root.right, arr);
        if(rightSmallest != -1) return rightSmallest;
        
        return -1;
    }
    
}
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
