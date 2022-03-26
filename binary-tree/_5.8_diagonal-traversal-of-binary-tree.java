// https://practice.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1/#

// For your answer keep on travelling to the right, and for the next level diagonal traversal add only your left node in the queue
// O(2N) Every node will be visited twice once while adding once while removing
// You are required to preserve the diagonal traversal order so only BFS can be used here and not DFS

// https://practice.geeksforgeeks.org/problems/diagonal-sum-in-binary-tree/1/  here DFS can be used, because sum does not need any order preservance

class Tree {
    
     public ArrayList<Integer> diagonal(Node root) {
         if(root == null) return new ArrayList<>();
         
         ArrayList<Integer> ans = new ArrayList<>();
         LinkedList<Node> queue = new LinkedList<>();
         queue.add(root);
         
         while(!queue.isEmpty()) {
             int size = queue.size();
             while(size-- > 0) { // Travel all component of that diagonal level
                 Node node = queue.removeFirst();
                 while(node != null) { // Travelling on individual component  // Dry run on the given example TC
                     if(node.left != null) queue.addLast(node.left);  // Add only left node in the queue, for the next diagonal level
                     ans.add(node.data);  // Forming curr diagonal level answer
                     node = node.right;  // Move to right to travel all nodes of that diagonal
                 }
             }
         }
         return ans;
      }
}
