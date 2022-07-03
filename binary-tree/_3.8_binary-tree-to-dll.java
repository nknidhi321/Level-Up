// https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1#
// The left and right pointers in nodes are to be used as previous and next pointers respectively in converted DLL

class Solution {

    public Node bToDLL(Node root) {
        
        Node dummy = new Node(-1);
        Node prev = dummy;
        
        Node curr = root;
        while(curr != null) {
            Node left = curr.left;
            if(left == null) {
                
                // Code for DLL
                prev.right = curr; // Create links for DLL
                curr.left = prev;  // Create links for DLL
                prev = prev.right;  // prev ko tail pe pahucha do
                
                curr = curr.right;
            }
            else {
                Node rightMostNode = getRightMostNode(left, curr);
                if(rightMostNode.right == null) { // thread create
                    rightMostNode.right = curr;
                    curr = curr.left;
                }
                else { // thread destroy
                    rightMostNode.right = null;

                    // Code for DLL
                    prev.right = curr; // Create links for DLL
                    curr.left = prev;  // Create links for DLL
                    prev = prev.right;  // prev ko tail pe pahucha do
                    
                    curr = curr.right;
                }
            }
        }
        
        Node head = dummy.right; // Escaping dummy node
        head.left = dummy.right = null; // Breaking link from dummy node
        return head;
    }
    
    public static Node getRightMostNode(Node node, Node curr) {	
        while(node.right != null && node.right != curr) { 
            node = node.right;
        }
        return node;
	  }
        
}
