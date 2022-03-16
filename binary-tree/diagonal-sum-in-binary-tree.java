// https://practice.geeksforgeeks.org/problems/diagonal-sum-in-binary-tree/1/#
// DFS
// When you go on left give + 1 
// When you go on right give + 0,
// Why ? Because you need to use diagonal level as index of arrayList

class Tree {
    
    public static ArrayList <Integer> diagonalSum(Node root) {
        if(root == null) return new ArrayList<>();
           
        ArrayList<Integer> ans = new ArrayList<>();
        diagonalSum_Util(root, ans, 0);
        return ans;
    }
    
    public static void diagonalSum_Util(Node root, ArrayList<Integer> ans, int diaLevel) {
        if(root == null) return;
        if(diaLevel == ans.size()) ans.add(0);  // You want to add nth node sum and nth idx does not exist, so create it for the 1st time
        ans.set(diaLevel, ans.get(diaLevel) + root.data);  // get the idx and set your evaluated sum in the ArrayList
           
        diagonalSum_Util(root.left, ans, diaLevel + 1);  // Left so + 1
        diagonalSum_Util(root.right, ans, diaLevel + 0);  // Right so + 0
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// BFS

class Tree {
    
    public static ArrayList <Integer> diagonalSum(Node root) {
        if(root == null) return new ArrayList<>();
         
         ArrayList<Integer> ans = new ArrayList<>();
         LinkedList<Node> queue = new LinkedList<>();
         queue.add(root);
         
         while(!queue.isEmpty()) {
             int size = queue.size();
             int sum = 0;
             while(size-- > 0) { // Travel all component of that diagonal level
                 Node node = queue.removeFirst();
                 while(node != null) { // Travelling on individual component  // Dry run on the given example TC
                     if(node.left != null) queue.addLast(node.left);  // Add only left node in the queue, for the next diagonal level
                     sum += node.data;  // Forming curr diagonal level answer
                     node = node.right;  // Move to right to travel all nodes of that diagonal
                 }
             }
             ans.add(sum);
         }
         return ans;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
