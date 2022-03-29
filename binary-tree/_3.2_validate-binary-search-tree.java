// https://leetcode.com/problems/validate-binary-search-tree/
// Morris is the best appoach then stack and then maxmin return type method.

/*

    Intuition :-
    ---------
    Since it is BST, It's Inorder traversal will be sorted.
    So, Using morrisInOrderTravsersal, wherever you print the algorithm do the following:-

    Keep a prev pointer which will always carry the prev value of the current node,
    And make a check that if prev is greater or equal to curr.val then it is not a BST.

    NOTE :-
    1) Make prev as Long, check the constraint, it exceeds Integer range.
    2) The moment you find that it is not validBST, "do not" return, make sure to unmodify the threads which you created.
        So, make sure to iterate the whole tree(Algo) to cut down the thread which you created while traversing or you may get wrong result.

    Jaha jaha print kar rahe the morrisInOrderTraversal me, bs waha waha check lga lo prev bada toh nahi hai curr se

*/

class Solution {
    
    public boolean isValidBST(TreeNode root) {
        return morrisInOrderTraversal(root);
    }
    
    public TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        while(node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }
    
    public boolean morrisInOrderTraversal(TreeNode root) {
        long prev = Long.MIN_VALUE;
        boolean flag = true;
        TreeNode curr = root;
        
        while(curr != null) {
            TreeNode left = curr.left;
            
            if(left == null) {
                
                // Yaha print hota tha, so check
                if(prev >= curr.val && flag == true) flag = false;
                prev = curr.val;
                
                curr = curr.right;
            }
            else {
                TreeNode rightMostNode = getRightMostNode(left, curr);

                if(rightMostNode.right == null) { // thread creation block
                    rightMostNode.right = curr;
                    curr = curr.left;
                }
                else { // thread destroying block
                    rightMostNode.right = null;
                    
                    // Yaha print hota tha, so check
                    if(prev >= curr.val && flag == true) flag = false;
                    prev = curr.val;
                    
                    curr = curr.right;
                }
            }
        }
        return flag;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Using stack,  TC : O(N), SC : (logN) [height of balanced BST]

class Solution {
    
    public boolean isValidBST(TreeNode root) {
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        addAllLeft(root, stack);
        
        long prev = -(long)1e13;
        while(!stack.isEmpty()) {
            TreeNode topNode = stack.pop();
            if(prev >= topNode.val) return false; // Checking prev was strictly smaller or not
            
            prev = topNode.val; // move prev to the topNode val, for next iteration  
            addAllLeft(topNode.right, stack);
        }
        return true;
    }
    
    public static void addAllLeft(TreeNode node, Stack<TreeNode> stack) {
        while(node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
// Using pair class return type
// take long, check constraints
// Rajneesh

class Solution {
    
    public class Pair {
        long min;
        long max;
        boolean isBST;
        
        public Pair() {
            this.min = (long)1e13; 
            this.max = -(long)1e13;
            this.isBST = true;
        }
    }
    
    public boolean isValidBST(TreeNode root) {
        return isValidBST_Util(root).isBST;
    }
        
    public Pair isValidBST_Util(TreeNode root) {
        if(root == null) return new Pair();
        
        Pair lp = isValidBST_Util(root.left);
        Pair rp = isValidBST_Util(root.right);
        
        Pair myPair = new Pair();
        
        // left && right subtree bst hai && aap left subtree k max se bade hone chahiye && right subtree k min se chote hone chahiye 
        if(lp.isBST && rp.isBST && lp.max < root.val && root.val < rp.min) {  
            myPair.isBST = true;
            myPair.min = Math.min(lp.min, root.val);  // Apna min set karne k liye, left k min ki zaroorat hai
            myPair.max = Math.max(rp.max, root.val);  // Apna max set karne k liye, right k max ki zaroorat hai
        }
        else {
            myPair.isBST = false;
        }
        return myPair;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Kevin
// max min

class Solution {
    public boolean isValidBST(TreeNode root) {
        return BST(root,null,null);
    }
    public static boolean BST(TreeNode root, Integer min, Integer max){
        if(root == null)
            return true;
        if((min != null && root.val <= min) || (max != null && root.val >= max))
            return false;
        return BST(root.left, min, root.val) && BST(root.right, root.val, max);
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
