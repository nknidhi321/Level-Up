// https://practice.geeksforgeeks.org/problems/level-order-traversal-in-spiral-form/1
// Using single Data Structure
// Using LinkedList, as doubly ended queue, dono taraf se data process karna hai

class Spiral {

    public ArrayList<Integer> findSpiral(Node root) {
        if(root == null) return new ArrayList<>();
        
        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.addFirst(root); // Odd level

        int level = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                if(level % 2 == 0) { // Even level 
                    Node node = queue.removeFirst(); // Aage se nikalo, piche se daalo
                    ans.add(node.data);
                    if(node.left != null) queue.addLast(node.left); // left pehle
                    if(node.right != null) queue.addLast(node.right); // fir right
                }            
                else { // Odd level
                    Node node = queue.removeLast(); // Piche se nikalo, aage se daalo
                    ans.add(node.data);
                    if(node.right != null) queue.addFirst(node.right); // right pehle
                    if(node.left != null) queue.addFirst(node.left); // fir left
                }            
            }
            level++;
        }
        return ans;
    }
    
}
//--------------------------------------------------------------------------------------------------
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

-------------------------------------------------------------------------------------------------------

// Using ArrayDeque
// Single DS

```
class Solution {
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        
        List<List<Integer>> ans = new LinkedList<>();
        Deque<TreeNode> dQueue = new ArrayDeque<>();
        dQueue.add(root);
        int flag = 0;
        
        while(!dQueue.isEmpty()) {
            int size = dQueue.size();
            List<Integer> smallerList = new ArrayList<>();
            while(size-- > 0) {
                if(flag == 0) { //As usual, aage se nikalo, piche daalo
                    TreeNode currNode = dQueue.removeFirst();
                    smallerList.add(currNode.val);
                    if(currNode.left != null) dQueue.add(currNode.left);
                    if(currNode.right != null) dQueue.add(currNode.right);
                }
                else { // flag == 1 //Sab ulta, piche se nikalo and aage daao
                    TreeNode currNode = dQueue.removeLast();
                    smallerList.add(currNode.val);
                    if(currNode.right != null) dQueue.addFirst(currNode.right); // Right will be added first
                    if(currNode.left != null) dQueue.addFirst(currNode.left); // Then Left 
                }
            }
            ans.add(smallerList);
            flag = (flag + 1) % 2;
        }
        return ans;
    }
    
}
```

---------------------------------------------------------------------------------------------------------------------

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

// DFS, do in preOrder
/*
    In DFS call, simply get the xth level arrayList and add at last 
    Now, after you come out of the DFS call, reverse the ans list at desired levels.
    Doing this just to escape shifting in the other DFS solution.
*/

```
class Solution {
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        
        List<List<Integer>> ans = new LinkedList<>();
        zigzagLevelOrder_DFS(root, 0, ans);
        
        for(int level = 1; level < ans.size(); level+=2) {
            Collections.reverse(ans.get(level));
        }
        return ans;
    }
    
    public static void zigzagLevelOrder_DFS(TreeNode root, int level, List<List<Integer>> ans) {
        if(root == null) return;
        
        // Make sure to do it preOrder, so that new ArrayList gets added beforehand at every level
        if(ans.size() == level) ans.add(new ArrayList<>());
        ans.get(level).add(root.val);
        
        zigzagLevelOrder_DFS(root.left, level + 1, ans);
        zigzagLevelOrder_DFS(root.right, level + 1, ans);
    }
    
}
```
----------------------------------------------------------------------------------------------------
