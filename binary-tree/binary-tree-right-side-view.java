// https://leetcode.com/problems/binary-tree-right-side-view/
// Do level order traversal and add right child first in your queue, so the 1st element popped at every level is your answer

class Solution {
    
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) return new ArrayList<>();
               
        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            TreeNode curr = queue.getFirst();
            ans.add(curr.val);
            
            while(size-- > 0) {
                curr = queue.removeFirst();
                if(curr.right != null) queue.add(curr.right);
                if(curr.left != null) queue.add(curr.left);
            }
        }
        return ans;    
    }
}
