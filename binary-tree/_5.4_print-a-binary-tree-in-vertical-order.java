// https://practice.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1#
// NOTE : Why minMax[0] ? To shift origin, make the least negative idx as 0th idx and so on... 


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
