// https://practice.geeksforgeeks.org/problems/kth-largest-element-in-bst/1/#

class Solution {
    
    public int kthLargest(Node root,int k) {
        Stack<Node> stack = new Stack<Node>();
        addAllRight(root, stack);
        
        int count = 0;
        while(!stack.isEmpty()) {
            Node topNode = stack.pop();
            if(++count == k) return topNode.data;
            addAllRight(topNode.left, stack);
        }
        return -1;
    }
    
    public static void addAllRight(Node node, Stack<Node> stack) {
        while(node != null) {
            stack.push(node);
            node = node.right;
        }
    }
    
}
