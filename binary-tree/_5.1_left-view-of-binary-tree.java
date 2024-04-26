// https://practice.geeksforgeeks.org/problems/left-view-of-binary-tree/1/#

// Do level order traversal and add left child first in your queue, so the 1st element popped at every level is your answer
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
----------------------------------------------------------------
// PreOrder Traversal
class Tree {
    ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        leftView(root, 0, ans);
        return ans;
    }
    
    public void leftView(Node root, int level, ArrayList<Integer> ans) {
        if(root == null) return;
        
        if(ans.size() == level) ans.add(root.data); //visiting level for the 1st time
        leftView(root.left, level + 1, ans);
        leftView(root.right, level + 1, ans);
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Follow up :-
// https://leetcode.com/problems/find-bottom-left-tree-value/
// Last level ki sbse pehli node is your answer

class Solution {
    
    public int findBottomLeftValue(TreeNode root) {
    
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        int leftMostValue = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            leftMostValue = queue.getFirst().val;
            
            while(size-- > 0) {
                TreeNode curr = queue.removeFirst();
                if(curr.left != null) queue.add(curr.left);
                if(curr.right != null) queue.add(curr.right);
            }
        }
        return leftMostValue;
    }

}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
