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

----------------------------------------------------------------------------------------------------------------------

// Here we are travelling : root, left, right
// Using PreOrder traversal : root, left, right

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        rightSideView(root, 0, ans);
        System.out.println(ans);
        return ans;
    }
    
    public static void rightSideView(TreeNode root, int level, List<Integer> ans) {
        if(root == null) return;
        
        if(ans.size() == level) ans.add(root.val); //visiting level for the 1st time
        else ans.set(level, root.val); // already visited level, replace that index/level value
        
        rightSideView(root.left, level + 1, ans);
        rightSideView(root.right, level + 1, ans);
    }
}

------------------------------------------------------------------------------------------------------------------------

// Here we are travelling : root, right, left
// Note this is not PostOrder trversal 
// In PostOrder trversal we travel like left, right, root
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        rightSideView(root, 0, ans);
        System.out.println(ans);
        return ans;
    }
    
    public static void rightSideView(TreeNode root, int level, List<Integer> ans) {
        if(root == null) return;
        
        if(ans.size() == level) ans.add(root.val); //visiting level for the 1st time
        
        rightSideView(root.right, level + 1, ans);
        rightSideView(root.left, level + 1, ans);
        
    }
}
