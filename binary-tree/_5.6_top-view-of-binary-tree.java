// https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1/
// Level order traversal me vertical level pe jo sbse pehla banda hoga wo sab answer hai 

/*
     Why dfs won't work ?? Because it will go in the depth first and not on the top/surface level
     See below TC
     
     Input:           1
                    /   \
                   2      3
                    \   
                     4  
                       \
                        5
                         \
                          6
     
     Output: Top view of the above binary tree is: 2 1 3 6
*/

class Solution {
     public static class Pair {
        Node node = null;
        int vl = 0;
        Pair(Node node, int vl) {
            this.node = node;
            this.vl = vl;
        }
    }

    public static ArrayList<Integer> topView(Node root) {
        if(root == null) return new ArrayList<>();

        ArrayList<Integer> ans = new ArrayList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, minMax, 0);
        int width = minMax[1] - minMax[0] + 1;
        for(int i = 0; i < width; i++) ans.add(null);

        LinkedList<Pair> queue = new LinkedList<>();
        queue.addLast(new Pair(root, Math.abs(minMax[0]))); // Root vl be minVerticalNode, root's left minVerticalNode - 1, roots right minVerticalNode + 1

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Pair currPair = queue.removeFirst();
                Node node = currPair.node;
                int vl = currPair.vl;
                if(ans.get(vl) == null) ans.set(vl, node.data);
                
                if(node.left != null) queue.addLast(new Pair(node.left, vl - 1));
                if(node.right != null) queue.addLast(new Pair(node.right, vl + 1));
            }
        }
        
        return ans;
    }
    
      
    public static void widthOfShadow(Node root, int[] minMax, int idx) {
        if(root == null) return;
        
        minMax[0] = Math.min(minMax[0], idx);
        minMax[1] = Math.max(minMax[1], idx);
        widthOfShadow(root.left, minMax, idx - 1);
        widthOfShadow(root.right, minMax, idx + 1);  
    }
    
}

//---------------------------------------------------------------------------------------------------------------------------------------------------------

// Using HM to escape widthOfShadow() traversal, logic same hai, bus ye traversal bacha lenge 

class Solution {
     public static class Pair {
        Node node = null;
        int vl = 0;
        Pair(Node node, int vl) {
            this.node = node;
            this.vl = vl;
        }
    }

    public static ArrayList<Integer> topView(Node root) {
        if(root == null) return new ArrayList<>();

        ArrayList<Integer> ans = new ArrayList<>();
        int minVerticalNode = (int)1e9, maxVerticalNode = -(int)1e9; // Same minMax concept
        
        // Using HM to keep vl ans, not using Array kuki index's(vl) neg nai ho skte 
        Map<Integer, Integer> map = new HashMap<>(); 

        LinkedList<Pair> queue = new LinkedList<>(); // Level order se he krna hoga, dfs wont work
        queue.addLast(new Pair(root, 0)); // Root vl be 0, root's left -1, roots right 1

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Pair currPair = queue.removeFirst();
                Node node = currPair.node;
                int vl = currPair.vl;
                if(!map.containsKey(vl)) map.put(vl, node.data); // Forming ans
                minVerticalNode = Math.min(minVerticalNode, vl); // Cal. left min width/ver height
                maxVerticalNode = Math.max(maxVerticalNode, vl); // Cal. right max width/ver height
                
                if(node.left != null) queue.addLast(new Pair(node.left, vl - 1));
                if(node.right != null) queue.addLast(new Pair(node.right, vl + 1));
            }
        }
        
        // return ans in List, make sure to shift the origin(not actually here), neg vl
        for(int i = minVerticalNode; i <= maxVerticalNode; i++) {
            int val = map.get(i); // fetching in asc. order
            ans.add(val); // Origin shifting 
        }
        
        return ans;
    }

}
