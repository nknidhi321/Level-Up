// https://practice.geeksforgeeks.org/problems/inorder-successor-in-bst/1/

class Solution {

    public Node inorderSuccessor(Node root, Node k) {
        Node returnValue = null;
        int prev = -1;
        Node curr = root;
        while (curr != null) {
            Node left = curr.left;
            if (left == null) {
                if (prev == k.data) returnValue = curr;
                prev = curr.data;
                curr = curr.right;
            } else {
                Node rightMostNode = getRightMostNode(left, curr);
                if (rightMostNode.right == null) { // thread create
                    rightMostNode.right = curr;
                    curr = curr.left;
                } else { // thread destroy
                    rightMostNode.right = null;
                    if (prev == k.data) returnValue = curr;
                    prev = curr.data;
                    curr = curr.right;
                }
            }
        }
        return returnValue;
    }

    public static Node getRightMostNode(Node node, Node curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }

}
