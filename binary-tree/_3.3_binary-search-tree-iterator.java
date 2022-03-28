// https://leetcode.com/problems/binary-search-tree-iterator/

// Using Morris Traversal, SC : O(N)
/*

The only difference from original morris Traversal is, to break where ever you used to print your inOrder element, so that it can return nodes one by one.
Also, make sure to make curr as global variable, because in next() we cannot pass anything, and somehow we need the curr iterator, to proceed from the very next element

*/

```
class BSTIterator {

    public TreeNode curr = null;
    
    public BSTIterator(TreeNode root) {
        curr = root;
    }
    
    public int next() {
        int returnValue = -1;
        while(curr != null) {
            TreeNode left = curr.left;
            if(left == null) {
                returnValue = curr.val;
                curr = curr.right;
                break;
            }
            else {
                TreeNode rightMostNode = getRightMostNode(left);
                if(rightMostNode.right == null) { // thread create
                    rightMostNode.right = curr;
                    curr = curr.left;
                }
                else { // thread destroy
                    rightMostNode.right = null;
                    returnValue = curr.val;
                    curr = curr.right;
                    break;
                }
            }
        }
        return returnValue;
    }
    
    public boolean hasNext() {
        return curr != null;
    }
    
    public TreeNode getRightMostNode(TreeNode node) {
        while(node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }
    
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Using Stack
/*
    Since BST is balanced, so at max stack will contain height of the tree nodes, and for balanced tree height is logN, so space complexity is logN and TC is O(N)

    Intuition
    ---------
    Imagine recursive stack and try to replicate how it works in inOrder recursion and implement it in iterative fashion.

    InOrder : Left Root Right
    The idea is the moment you print an element, add all its right's left element in loop
    So, now stack's top is the leftmost element to be printed first.
    NOTE : For root node all it's left first, so stacks top is the leftmost node now.
*/

```
class BSTIterator {

    public Stack<TreeNode> stack;
    
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        addAllLeft(root);
    }
    
    public int next() {
        TreeNode topNode = stack.pop();
        addAllLeft(topNode.right);
        return topNode.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    public void addAllLeft(TreeNode node) {
        while(node != null) {
            stack.push(node);
            node = node.left;
        }
    } 
    
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
