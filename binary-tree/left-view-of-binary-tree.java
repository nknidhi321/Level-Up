// https://practice.geeksforgeeks.org/problems/left-view-of-binary-tree/1/#

class Tree {
  
    ArrayList<Integer> leftView(Node root) {
        if(root == null) return new ArrayList<>();
               
        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            Node curr = queue.getFirst();
            ans.add(curr.data);
            
            while(size-- > 0) {
                curr = queue.removeFirst();
                if(curr.left != null) queue.add(curr.left);
                if(curr.right != null) queue.add(curr.right);
            }
        }
        return ans;
    }
}
