// https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1/#
// Level order traversal karo and har ek vertical level pe jo v last node mile use update kar do apne list me 

class Solution {

    public static class Pair {
        Node node = null;
        int vl = 0;

        Pair(Node node, int vl) {
            this.node = node;
            this.vl = vl;
        }
    }

    public ArrayList<Integer> bottomView(Node root) {
        if(root == null) return new ArrayList<>();

        ArrayList<Integer> ans = new ArrayList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, minMax, 0);
        int width = minMax[1] - minMax[0] + 1;
        for(int i = 0; i < width; i++) ans.add(null);

        LinkedList<Pair> queue = new LinkedList<>();
        queue.addLast(new Pair(root, Math.abs(minMax[0])));

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Pair currPair = queue.removeFirst();
                Node node = currPair.node;
                int vl = currPair.vl;
            
                ans.set(vl, node.data);
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
