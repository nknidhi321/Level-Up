//https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

/*
Optimized Rajneesh Bhaiya's without NodeToRootPath extra space.
https://www.youtube.com/watch?v=s22QClql9LU&t=1703s
Time Complexity :-
1) If target node is the last node then to find target node it's O(N)
2) If k is Infinity, you will traverse the whole tree once again, So O(N)
=> 2O(N) => O(N)
Space Complexity : O(1)
*/

class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        kUnitDistance(root, target, k, ans);
        return ans;
    }
    
    public static int kUnitDistance(TreeNode root, TreeNode target, int k, List<Integer> ans) {
        if(root == null) return -1; //target node not found
        
        if(root == target) {
            kNodesDown(root, null, k, ans);
            return 1; //target node is found and from me to my parent the distance is 1
        }
        int left_kUnit = kUnitDistance(root.left, target, k, ans);
        if(left_kUnit != -1) {
            kNodesDown(root, root.left, k - left_kUnit, ans);
            return left_kUnit + 1;
        }
        int right_kUnit = kUnitDistance(root.right, target, k, ans);
        if(right_kUnit != -1) {
            kNodesDown(root, root.right, k - right_kUnit, ans);
            return right_kUnit + 1;
        }
        return -1; //target node not found
    }
    
    public static void kNodesDown(TreeNode root, TreeNode block, int k, List<Integer> ans) {
        if(root == null || root == block || k < 0) return;
        
        if(k == 0) {
            ans.add(root.val);
            return;
        }
        kNodesDown(root.left, block, k - 1, ans);
        kNodesDown(root.right, block, k - 1, ans);
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

/*
Rajneesh Bhaiya's with extra NodeToRootPath space 
TC : 2O(N) => O(N)
SC : O(N) (NodeToRootPath space, if target node is the rightmost node of the tree)
*/

class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<TreeNode> path = nodeToRootPath(root, target);
        List<Integer> ans = new ArrayList<>();
        TreeNode block = null;
        for(int i = 0; i < path.size(); i++) {
            kNodesDown(path.get(i), block, k - i, ans);
            block = path.get(i);
        }
        return ans;
    }
    
    public static void kNodesDown(TreeNode root, TreeNode block, int k, List<Integer> ans) {
        if(root == null || root == block || k < 0) return;
        
        if(k == 0) {
            ans.add(root.val);
            return;
        }
        kNodesDown(root.left, block, k - 1, ans);
        kNodesDown(root.right, block, k - 1, ans);
    }
    
    public List<TreeNode> nodeToRootPath(TreeNode root, TreeNode target) {
        if(root == null) return new ArrayList<>();
        
        if(root.val == target.val) {
            List<TreeNode> ans = new ArrayList<>();
            ans.add(root);
            return ans;
        }   
        List<TreeNode> leftSmallAns = nodeToRootPath(root.left, target);
        if(leftSmallAns.size() > 0) {
            leftSmallAns.add(root);
            return leftSmallAns;
        }
        List<TreeNode> rightSmallAns = nodeToRootPath(root.right, target);
        if(rightSmallAns.size() > 0) {
            rightSmallAns.add(root);
            return rightSmallAns;
        }
        return new ArrayList<>();
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

/*
CodeBix BFS Solution
Keep track of parent nodes in HashMap, and also keep track of visited nodes in HashSet to avoid infinite loop or recounting of the same nodes
Now, traverse radially in BFS fashion from target node and make sure to include parent node each time along with the child nodes, one day you will reach to k  
*/

class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        createParentMap(parentMap, root);
        
        
        List<Integer> list = new ArrayList<>();
        HashSet<TreeNode> visitedSet = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.offer(target);
        visitedSet.add(target);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                TreeNode node = queue.poll();
                
                if(k == 0){
                    list.add(node.val);
                }
                
                if(parentMap.containsKey(node) && !visitedSet.contains(parentMap.get(node))){
                    queue.offer(parentMap.get(node));
                    visitedSet.add(parentMap.get(node));
                }
               
                if(node.left != null && !visitedSet.contains(node.left)){
                    queue.offer(node.left);
                    visitedSet.add(node.left);
                }
                if(node.right != null && !visitedSet.contains(node.right)){
                    queue.offer(node.right);
                    visitedSet.add(node.right);
                }
            }
            k--;
            if(k < 0) // You have aready processed k == 0 nodes in BFS fashion, so simply break
                break;
        }
        return list;
    }
    
    public static void createParentMap(HashMap<TreeNode, TreeNode> parentMap, TreeNode root){
        if(root == null)
            return;
        
        if(root.left != null){
            parentMap.put(root.left, root);
        }
        if(root.right != null){
             parentMap.put(root.right, root);
        }
        createParentMap(parentMap, root.left);
        createParentMap(parentMap, root.right);
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
