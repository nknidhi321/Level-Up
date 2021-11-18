//https://practice.geeksforgeeks.org/problems/burning-tree/1#

class Solution {
    /*class Node {
    	int data;
    	Node left;
    	Node right;
    
    	Node(int data) {
    		this.data = data;
    		left = null;
    		right = null;
    	}
    }*/
    
    
    //======================================================================================
    public static int minTime(Node root, int target) {
        //Since you are using static variable
        //always remember to initialize it with default value before running any TC
        //Else it will take previous TC's maximum value as default value for the present TC
        maxTime = Integer.MIN_VALUE;
        burningTree(root, target);
        return maxTime;
    }
     
    public static int maxTime = Integer.MIN_VALUE;
      
    public static int burningTree(Node root, int fireNode) {
        if(root == null) return -1;
        if(root.data == fireNode) {
            burningTreeNode(root, null, 0);
            return 1;
        }
        
        int leftTime = burningTree(root.left, fireNode);
        if(leftTime != -1) {
            burningTreeNode(root, root.left, leftTime);
            return leftTime + 1;
        }
        
        int rightTime = burningTree(root.right, fireNode);
        if(rightTime != -1) {
            burningTreeNode(root, root.right, rightTime);
            return rightTime + 1;
        }
        return -1;
    }
        
    public static void burningTreeNode(Node root, Node blockerNode, int time) {
        if(root == null || root == blockerNode) return;
        
        maxTime = Math.max(maxTime, time);
        burningTreeNode(root.left, blockerNode, time + 1);
        burningTreeNode(root.right, blockerNode, time + 1);
    }
	//=============================================================================================================
	
}


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
//Same Solution at Pepcoding Portal
//https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/burning-tree-/ojquestion#!

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

	
	
	// =========================================================================================
	public static int maxTime = Integer.MIN_VALUE;

	public static int burningTree(TreeNode root, int fireNode) {
		if (root == null)
			return -1;
		if (root.val == fireNode) {
			burningTreeNode(root, null, 0);
			return 1;
		}

		int leftTime = burningTree(root.left, fireNode);
		if (leftTime != -1) {
			burningTreeNode(root, root.left, leftTime);
			return leftTime + 1;
		}

		int rightTime = burningTree(root.right, fireNode);
		if (rightTime != -1) {
			burningTreeNode(root, root.right, rightTime);
			return rightTime + 1;
		}
		return -1;
	}

	public static void burningTreeNode(TreeNode root, TreeNode blockerNode, int time) {
		if (root == null || root == blockerNode)
			return;

		maxTime = Math.max(maxTime, time);
		burningTreeNode(root.left, blockerNode, time + 1);
		burningTreeNode(root.right, blockerNode, time + 1);
	}
	// =======================================================================================


	
	public static TreeNode createTree(int[] arr, int[] IDX) {
		if (IDX[0] > arr.length || arr[IDX[0]] == -1) {
			IDX[0]++;
			return null;
		}
		TreeNode Treenode = new TreeNode(arr[IDX[0]++]);
		Treenode.left = createTree(arr, IDX);
		Treenode.right = createTree(arr, IDX);

		return Treenode;
	}

	public static void solve() {
		int n = scn.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = scn.nextInt();

		int[] IDX = new int[1];
		TreeNode root = createTree(arr, IDX);
		int fireNode = scn.nextInt();


		// =====================================================================================
		// Since you are using static variable
		// always remember to initialize it with default value before running any TC
		// Else it will take previous TC's maximum value as default value for the
		// present TC
		maxTime = Integer.MIN_VALUE;
		burningTree(root, fireNode);
		System.out.println(maxTime);
		// =====================================================================================

	}

	public static void main(String[] args) {
		solve();
	}
}
