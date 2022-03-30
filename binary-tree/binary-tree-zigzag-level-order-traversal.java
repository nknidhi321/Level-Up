// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

// BFS
/*
    Approach
    --------
    Keep toggling flag for alternate levels.
    If flag is 0, simply add the smallerList in the ans list
    If flag is 1, reverse the smallerList and then add in the ans list
*/

```
class Solution {
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        
        List<List<Integer>> ans = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int flag = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> smallerList = new ArrayList<>();
            while(size-- > 0) {
                TreeNode currNode = queue.removeFirst();
                smallerList.add(currNode.val);
                
                if(currNode.left != null) queue.add(currNode.left);
                if(currNode.right != null) queue.add(currNode.right);
            }
            if(flag == 0) ans.add(smallerList);
            else { // flag == 1
                Collections.reverse(smallerList);
                ans.add(smallerList);
            }
            flag = (flag + 1) % 2;
        }
        return ans;
    }
    
}
```

-----------------------------------------------------------------------------------------------------

// DFS, do in preOrder
/*
    Approach
    --------
    If level is even, simply get the xth level arrayList and add at last 
    If level is odd, simply get the xth level arrayList and add at 0th idx
    NOTE : Cost of shifting will be be added when you add at 0th idx  
*/

```
class Solution {
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        
        List<List<Integer>> ans = new LinkedList<>();
        zigzagLevelOrder_DFS(root, 0, ans);
        return ans;
    }
    
    public static void zigzagLevelOrder_DFS(TreeNode root, int level, List<List<Integer>> ans) {
        if(root == null) return;
        
        // Make sure to do it preOrder, so that new ArrayList gets added beforehand at every level
        if(ans.size() == level) ans.add(new ArrayList<>());
        if(level % 2 == 0) ans.get(level).add(root.val); // Adding at the end of the list
        else ans.get(level).add(0, root.val); // Adding at 0th idx to get in revese order
        
        zigzagLevelOrder_DFS(root.left, level + 1, ans);
        zigzagLevelOrder_DFS(root.right, level + 1, ans);
    }
    
}
```
-------------------------------------------------------------------------------------------------------
