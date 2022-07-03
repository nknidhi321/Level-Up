// https://practice.geeksforgeeks.org/problems/kth-largest-element-in-bst/1/#
// Kth largest == Iterate from back in asc sorted inOrder

// Using Morris Traversal from end, SC : O(1)

class Solution {
    
    public int kthLargest(Node root, int k) {
        
        int returnValue = -1;
        Node curr = root;
        while(curr != null) {
            Node right = curr.right;
            if(right == null) {
                // System.out.println(curr.data);
                if(--k == 0) returnValue = curr.data;
                curr = curr.left;
            }
            else {
                Node leftMostNode = getLeftMostNode(right, curr);
                if(leftMostNode.left == null) { // thread create
                    leftMostNode.left = curr;
                    curr = curr.right;
                }
                else { // thread destroy
                    leftMostNode.left = null;
                    // System.out.println(curr.data);
                    if(--k == 0) returnValue = curr.data;
                    curr = curr.left;
                }
            }
        }
        return returnValue;
    }
    
    public static Node getLeftMostNode(Node node, Node curr) {	
	while (node.left != null && node.left != curr) { 
	    node = node.left;
	}
	return node;
    }
	
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Using morris Traversal from start, SC : O(1)

class Solution {
    
    public int kthLargest(Node root,int k) {
        
        int size = sizeOfBST(root);
        k = size - k + 1; // Kth largest == Iterate from back in asc sorted inOrder
        
        int count = 0;
        int returnValue = -1;
        Node curr = root;
        while(curr != null) {
            Node left = curr.left;
            if(left == null) {
                if(++count == k) returnValue = curr.data;
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
                    if(++count == k) returnValue = curr.data;
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
	
	public static int sizeOfBST(Node root) {
	    if(root == null) return 0;
	    
	    int size = 0;
	    size += sizeOfBST(root.left);
	    size += sizeOfBST(root.right);
	    return size + 1;
	}
	
}


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Using Stack, SC : O(H) => O(logN) => height of BST(balanced)

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

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
