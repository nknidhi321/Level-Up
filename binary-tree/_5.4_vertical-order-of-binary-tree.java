// https://practice.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1#
// NOTE : Why minMax[0] ? To shift origin, make the least negative idx as 0th idx and so on... 

Do check for the follow up at below.

    
class Solution {

    public static class Pair {
        Node node;
        int vl; // vertical length or width
        
        public Pair(Node node, int vl) {
            this.node = node;
            this.vl = vl;
        }
    }
    
    static ArrayList<Integer> verticalOrder(Node root) {
        if(root == null) return new ArrayList<>();
        
        // Find minMax width or shadow width  
        int[] minMax = new int[2]; // {maxVerticalLeftSideWidth, maxVerticalRightSideWidth}
        widthOfShadow(root, minMax, 0);
        
        int len = minMax[1] - minMax[0] + 1;
        
        // ArrayList<ArrayList<Integer>> will contain all vertical vl as index of ArrayList and corresponding vertical list of node values
        // As it is ArrayList make sure your vl starts from 0 and not < 0
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();    
        for(int i = 0; i < len; i++) ans.add(new ArrayList<>());  // Intialize arrayList of that minMax width
        
        LinkedList<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, Math.abs(minMax[0])));  // Why minMax[0] ? To shift origin, make the least negative idx as 0th idx and so on...       // IMPORTANT
        
         while(!queue.isEmpty()) {
            int size = queue.size();
            
            while(size-- > 0) {
                Pair pair = queue.removeFirst();
                ans.get(pair.vl).add(pair.node.data);
                
                if(pair.node.left != null) queue.add(new Pair(pair.node.left, pair.vl - 1));
                if(pair.node.right != null) queue.add(new Pair(pair.node.right, pair.vl + 1));
            }
        }
        return addAllNodeValues(ans);
    }
    
  
    // Calculate width / shadow
    public static void widthOfShadow(Node root, int[] minMax, int idx) {
        if(root == null) return;
        
        minMax[0] = Math.min(minMax[0], idx);
        minMax[1] = Math.max(minMax[1], idx);
        widthOfShadow(root.left, minMax, idx - 1);
        widthOfShadow(root.right, minMax, idx + 1);  
    }
  
  
    // Take out list one by one from answer list and add values of those list it in another result list
    public static ArrayList<Integer> addAllNodeValues(ArrayList<ArrayList<Integer>> ans) {
        ArrayList<Integer> res = new ArrayList<>();
        for(ArrayList<Integer> smallAns : ans) {
            for(int val : smallAns) {
                res.add(val);
            }
        }
        return res;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Follow up :-
// https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
// If nodes are on same hl && vl then priority should be given to the node with smaller val while printing

// Using PQ and Comparable for sorting on horizontal level and then on val
```
class Solution {
    class Pair implements Comparable<Pair> {
        TreeNode TreeNode;
        int hl;
        int vl; // vertical length or width
        public Pair(TreeNode TreeNode, int hl, int vl) {
            this.TreeNode = TreeNode;
            this.hl = hl;
            this.vl = vl;
        }
        public int compareTo(Pair o) { // Agar hl same h toh val k basis pe chote bnde ko mangwao
            if((this.hl - o.hl) == 0) return this.TreeNode.val - o.TreeNode.val;
            else return this.hl - o.hl;
        }
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if(root == null) return new ArrayList<>();
        
        // Find minMax width or shadow width  
        int[] minMax = new int[2]; // {maxVerticalLeftSideWidth, maxVerticalRightSideWidth}
        widthOfShadow(root, minMax, 0);
        
        int len = minMax[1] - minMax[0] + 1;
        
        // ans will contain all vertical vl as index of ArrayList and corresponding vertical list of TreeNode values
        // As it is ArrayList make sure your vl starts from 0 and not < 0
        List<List<Integer>> ans = new ArrayList<>();    
        for(int i = 0; i < len; i++) ans.add(new ArrayList<>());  // Intialize arrayList of that minMax width
        
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        queue.add(new Pair(root, 0, Math.abs(minMax[0])));  // Why minMax[0] ? To shift origin, make the least negative idx as 0th idx and so on...       // IMPORTANT
        
         while(!queue.isEmpty()) {
            Pair pair = queue.remove();
            ans.get(pair.vl).add(pair.TreeNode.val);

            if(pair.TreeNode.left != null) queue.add(new Pair(pair.TreeNode.left, pair.hl + 1, pair.vl - 1));
            if(pair.TreeNode.right != null) queue.add(new Pair(pair.TreeNode.right, pair.hl + 1, pair.vl + 1));
        }
        return ans;
    }
  
    // Calculate width / shadow
    public static void widthOfShadow(TreeNode root, int[] minMax, int idx) {
        if(root == null) return;
        
        minMax[0] = Math.min(minMax[0], idx);
        minMax[1] = Math.max(minMax[1], idx);
        widthOfShadow(root.left, minMax, idx - 1);
        widthOfShadow(root.right, minMax, idx + 1);  
    }
    
}
```
-------------------------------------------------------------------------------
// Using PQ and Lambda function for sorting on horizontal level and then on val

class Solution {
    class Pair {
        TreeNode TreeNode;
        int hl;
        int vl; // vertical length or width
        public Pair(TreeNode TreeNode, int hl, int vl) {
            this.TreeNode = TreeNode;
            this.hl = hl;
            this.vl = vl;
        }
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if(root == null) return new ArrayList<>();
        
        // Find minMax width or shadow width  
        int[] minMax = new int[2]; // {maxVerticalLeftSideWidth, maxVerticalRightSideWidth}
        widthOfShadow(root, minMax, 0);
        
        int len = minMax[1] - minMax[0] + 1;
        
        // ans will contain all vertical vl as index of ArrayList and corresponding vertical list of TreeNode values
        // As it is ArrayList make sure your vl starts from 0 and not < 0
        List<List<Integer>> ans = new ArrayList<>();    
        for(int i = 0; i < len; i++) ans.add(new ArrayList<>());  // Intialize arrayList of that minMax width
        
        PriorityQueue<Pair> queue = new PriorityQueue<>((a, b) -> {
            if(a.hl == b.hl) return a.TreeNode.val - b.TreeNode.val;
            else return a.hl - b.hl;
        });
        queue.add(new Pair(root, 0, Math.abs(minMax[0])));  // Why minMax[0] ? To shift origin, make the least negative idx as 0th idx and so on...       // IMPORTANT
        
         while(!queue.isEmpty()) {
            Pair pair = queue.remove();
            ans.get(pair.vl).add(pair.TreeNode.val);

            if(pair.TreeNode.left != null) queue.add(new Pair(pair.TreeNode.left, pair.hl + 1, pair.vl - 1));
            if(pair.TreeNode.right != null) queue.add(new Pair(pair.TreeNode.right, pair.hl + 1, pair.vl + 1));
        }
        return ans;
    }
  
    // Calculate width / shadow
    public static void widthOfShadow(TreeNode root, int[] minMax, int idx) {
        if(root == null) return;
        
        minMax[0] = Math.min(minMax[0], idx);
        minMax[1] = Math.max(minMax[1], idx);
        widthOfShadow(root.left, minMax, idx - 1);
        widthOfShadow(root.right, minMax, idx + 1);  
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
/*
    Using Map<Vertical Level, PQ> + DFS
    Store PQ on each vertical level
    you do not need vl to store in Pair because every PQ is on each vertical level only, 
    so every vl will be same for that individual PQ, so hl is only required here.
*/
class Solution {
    
    static class Pair implements Comparable<Pair> {
        int val;
        int hl;
        Pair(int val, int hl) {
            this.val = val;
            this.hl = hl;
        }
        public int compareTo(Pair o) {
            if(this.hl == o.hl) return this.val - o.val;
            else return this.hl - o.hl;
        }
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, PriorityQueue<Pair>> map = new TreeMap<>();
        traverseTree(root,0, 0, map);
        
        List<List<Integer>> ans = new ArrayList<>();
        for(Map.Entry<Integer, PriorityQueue<Pair>> entry : map.entrySet()) {
            List<Integer> li = new ArrayList<>();
            PriorityQueue<Pair> pq = entry.getValue();
            while(!pq.isEmpty()) {
                Pair pair = pq.poll();
                li.add(pair.val);
            }
            ans.add(li);
        }
        return ans;
    }
    
    public void traverseTree(TreeNode root, int vl, int hl, Map<Integer, PriorityQueue<Pair>> map) {
        if(root == null) return;
        
        if(map.containsKey(vl)) {
            PriorityQueue<Pair> pq = map.get(vl);
            pq.add(new Pair(root.val, hl));
        }
        else {
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            pq.add(new Pair(root.val, hl));
            map.put(vl, pq);
        }
        
        traverseTree(root.left, vl - 1, hl + 1, map);
        traverseTree(root.right, vl + 1, hl + 1, map);
    }
    
}
