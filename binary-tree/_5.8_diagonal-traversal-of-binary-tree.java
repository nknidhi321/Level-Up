// https://practice.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1/#

// For your answer keep on travelling to the right, and for the next level diagonal traversal add only your left node in the queue
// O(2N) Every node will be visited twice once while adding once while removing
// You are required to preserve the diagonal traversal order so only BFS can be used here and not DFS

// https://practice.geeksforgeeks.org/problems/diagonal-sum-in-binary-tree/1/  here DFS can be used, because sum does not need any order preservance

class Tree {
    
     public ArrayList<Integer> diagonal(Node root) {
         if(root == null) return new ArrayList<>();
         
         ArrayList<Integer> ans = new ArrayList<>();
         LinkedList<Node> queue = new LinkedList<>();
         queue.add(root);
         
         while(!queue.isEmpty()) {
             int size = queue.size();
             while(size-- > 0) { // Travel all component of that diagonal level
                 Node node = queue.removeFirst();
                 while(node != null) { // Travelling on individual component  // Dry run on the given example TC
                     if(node.left != null) queue.addLast(node.left);  // Add only left node in the queue, for the next diagonal level
                     ans.add(node.data);  // Forming curr diagonal level answer
                     node = node.right;  // Move to right to travel all nodes of that diagonal
                 }
             }
         }
         return ans;
      }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/diagonal-order-of-a-binarytree/ojquestion
// Same question as above just expecting answer in separate diagonal level in list 

import java.util.*;

public class Main {
    public static Scanner scn = new Scanner(System.in);

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }


    //=======================================================================================================================================
    public static ArrayList<ArrayList<Integer>> diagonalOrder(TreeNode root) {
         if(root == null) return new ArrayList<>();
         
         ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
         LinkedList<TreeNode> queue = new LinkedList<>();
         queue.add(root);
         
         int level = 0;
         while(!queue.isEmpty()) {
             int size = queue.size();
             while(size-- > 0) { // Travel all component of that diagonal level
                 TreeNode node = queue.removeFirst();
                 while(node != null) { // Travelling on individual component  // Dry run on the given example TC
                     if(node.left != null) queue.addLast(node.left);  // Add only left node in the queue, for the next diagonal level
                     if(ans.size() == level) ans.add(new ArrayList<>());
                     
                     ans.get(level).add(node.val);  // Forming curr diagonal level answer
                     node = node.right;  // Move to right to travel all nodes of that diagonal
                 }
             }
             level++;
         }
         return ans;
    }
    //===========================================================================================================================================



    public static TreeNode createTree(int[] arr, int[] IDX) {
        if (IDX[0] > arr.length || arr[IDX[0]] == -1) {
            IDX[0]++;
            return null;
        }
        TreeNode node = new TreeNode(arr[IDX[0]++]);
        node.left = createTree(arr, IDX);
        node.right = createTree(arr, IDX);

        return node;
    }

    public static void solve() {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();

        int[] IDX = new int[1];
        TreeNode root = createTree(arr, IDX);

        ArrayList<ArrayList<Integer>> ans = diagonalOrder(root);
        int idx = 0;
        for (ArrayList<Integer> i : ans) {
            System.out.print(idx++ + " -> ");
            for (Integer j : i)
                System.out.print(j + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        solve();
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
