// https://practice.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1/#

/*

    Intution:
    --------
    addLeftBoundary()     [without leaf]
    addLeafBoundary()     [left will be covered here]
    addRightBoundary()    [without leaf] [Do remember add right boundary in revese order]
    

    Note:
    ----
    addLeftBoundary() is not same as leftView() 
    Ex : [4, 5, 2, null, null, 3, 1, 6, 7]
                           4
                          / \
                        5     2
                             /  \
                            3    1
                           / \
                          6   7

    4, 5 + 3 & 6 will also be covered in leftView() but it will not be covered in addLeftBoundary(), only 4 & 5 will be covered
    Then how addLeftBoundary() works in the problem ?? Note 3 is not boundary, so either way it is not to be included and 6 will be covered in addLeafBoundary()
    
    Similarly, addRightBoundary() is not same as rightView()
*/

class Solution {
    
	ArrayList <Integer> boundary(Node root) {
	    ArrayList <Integer> list = new ArrayList<>();
	    if(!isLeaf(root)) list.add(root.data);
	    addLeftBoundary(root, list);
	    addLeafBoundary(root, list);
	    addRightBoundary(root, list);
	    return list;
	}
	
	public static void addLeftBoundary(Node root, ArrayList <Integer> list) {
        Node curr = root.left;
        while(curr != null) {
            if(!isLeaf(curr)) list.add(curr.data);
            if(curr.left != null) curr = curr.left;
            else curr = curr.right;
        }
    }

    public static void addLeafBoundary(Node root, ArrayList<Integer> list) {
        if(root == null) return;
        if(isLeaf(root)) list.add(root.data);
        
        addLeafBoundary(root.left, list);
        addLeafBoundary(root.right, list);
    }

    public static void addRightBoundary(Node root, ArrayList <Integer> list) {
        Node curr = root.right;
        ArrayList<Integer> stack = new ArrayList<>();
        while(curr != null) {
            if(!isLeaf(curr)) stack.add(curr.data);
            if(curr.right != null) curr = curr.right;
            else curr = curr.left;
        }
        
        for(int i = stack.size() - 1; i >= 0; i--) {
            list.add(stack.get(i));
        }
    }
    
    public static boolean isLeaf(Node node) {
        if(node.left == null && node.right == null) return true;
        return false;
    }
    
}
