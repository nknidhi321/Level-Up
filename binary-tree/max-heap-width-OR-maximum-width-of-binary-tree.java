// https://leetcode.com/problems/maximum-width-of-binary-tree/
//The idea is to get the 1st NodePair idx and the last NodePair idx at each level and get the difference + 1 (Inclusive) to find the maxWidth.

// Using heap indexing property: 1
// If we give 1 to root, suppose idx of root is idx = 1 , then it's left child would be 2 from (2 * idx) formula and
// right child would be 3 from (2 * idx + 1) formula and so on for the subtree.

// Rajneesh

```
class Solution {
    
    public static class Pair {
        int idx;
        TreeNode node;
        
        public Pair(TreeNode node, int idx) {
            this.idx = idx;
            this.node = node;
        }
    }
    
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        
        LinkedList<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        int maxNodes = 1;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            int leftMaxWidth = queue.getFirst().idx;
            int rightMaxWidth = queue.getFirst().idx;
            while(size-- > 0) {
                Pair pair = queue.removeFirst();      
                rightMaxWidth = pair.idx;
                
                if(pair.node.left != null) queue.addLast(new Pair(pair.node.left, 2 * pair.idx + 1));
                if(pair.node.right != null) queue.addLast(new Pair(pair.node.right, 2 * pair.idx + 2));
            }
            maxNodes = Math.max(maxNodes, rightMaxWidth - leftMaxWidth + 1);
        }
        return maxNodes;
    }
}
```

-------------------------------------------------------------------------------------------

// Mine

```
class Solution {
    
    class NodePair{
        TreeNode node;
        int idx;
        
        public NodePair(TreeNode node, int idx){
            this.node = node;
            this.idx = idx;
        }
    }
    
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        
        Queue<NodePair> queue = new LinkedList<>();
        queue.offer(new NodePair(root, 1));
        int maxWidth = 0;
        
        while(!queue.isEmpty()){
            NodePair firstNodePair = queue.peek();
            NodePair currentNodePair = null;
            int size = queue.size();
            while(size-- > 0){
                currentNodePair = queue.poll();
                if(currentNodePair.node.left != null)
                    queue.offer(new NodePair(currentNodePair.node.left, 2 * currentNodePair.idx));
                if(currentNodePair.node.right != null)
                    queue.offer(new NodePair(currentNodePair.node.right, 2 * currentNodePair.idx + 1));
            }
            maxWidth = Math.max(maxWidth, (currentNodePair.idx - firstNodePair.idx) + 1);
        }
        return maxWidth;
    }
}
```

--------------------------------------------------------------------------------------------------------------------------

// Mine

Using heap indexing property: 0

If we give 0 to root, suppose idx of root is idx = 0 , then it's left child would be 1 from (2 * idx + 1) formula and
right child would be 2 from (2 * idx + 2) formula and so on for the subtree.

```
class Solution {
    
    class NodePair{
        TreeNode node;
        int idx;
        
        public NodePair(TreeNode node, int idx){
            this.node = node;
            this.idx = idx;
        }
    }
    
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        
        Queue<NodePair> queue = new LinkedList<>();
        queue.offer(new NodePair(root, 0));
        int maxWidth = 0;
        
        while(!queue.isEmpty()){
            NodePair firstNodePair = queue.peek();
            NodePair currentNodePair = null;
            int size = queue.size();
            while(size-- > 0){
                currentNodePair = queue.poll();
                if(currentNodePair.node.left != null)
                    queue.offer(new NodePair(currentNodePair.node.left, 2 * currentNodePair.idx + 1));
                if(currentNodePair.node.right != null)
                    queue.offer(new NodePair(currentNodePair.node.right, 2 * currentNodePair.idx + 2));
            }
            maxWidth = Math.max(maxWidth, (currentNodePair.idx - firstNodePair.idx) + 1);
        }
        return maxWidth;
    }
}

```
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
