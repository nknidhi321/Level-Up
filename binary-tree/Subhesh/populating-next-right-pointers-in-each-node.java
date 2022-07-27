// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

class Solution {
    
    public Node connect(Node root) {
        if(root == null) return root;
    
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size > 0) { // Ek iteration me 2 nodes nikalna is wrong approach kuki 2nd wale bnde ko v toh koi point krna chahiye
                Node node1 = queue.poll(); // So, that's why peek krwa lo 2nd bnde ko and 1st k piche lga do
                size--;
                Node node2 = size == 0 ? null : queue.peek(); // "Is level" k queue me agar sirf 1 he bnda tha toh wo null ko point karega
                node1.next = node2;
                
                if(node1.left != null) queue.add(node1.left);
                if(node1.right != null) queue.add(node1.right);
            }
        }
        return root;
    }
    
}
