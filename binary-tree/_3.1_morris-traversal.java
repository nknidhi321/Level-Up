//https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/in-order-morris-traversal-in-binarytree/ojquestion
//https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/pre-order-morris-traversal-in-binary-tree/ojquestion

package tree;

import java.util.ArrayList;

public class _3_TraversalSet {

	public static class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		TreeNode(int val) {
			this.val = val;
		}
	}

	
	// =============================================================================================================================================
	/*
	 # ALGO morrisInOrderTraversal:-
	  
	   Left pe kab jana h: 
	  		a. when we create a thread
	  
	   Right pe kab jana h: 
      			a. left is null 
	 	 	b. thread is cut down [Left subtree and root is processed]
	  
	   Print kab karna h: 
	  		a. left is null 
	  		b. thread is cut down [Left subtree and root is processed]
	  
	  
	 # Space Complexity:- O(1)
	  
	 # Time Complexity:- 
	  	Average number of times nodes are visited is 4 to 6 times. Therefore, TC: O(5N) 
	  	For right skewed tree each node is visited only ones. Therefore, TC: O(N) [Left null hai, right pe chale gaye]
	  	For left skewed tree each node is visited thrice. Therefore, TC: O(3N)
	  
	 */

	public static TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
		
		// In while loops condition, node ka right null hoga agar aapne kvi thread nai bnaya
		// In while loops condition, node ka right curr k equal hoga agar already thread bna hua hai
		
		// Jabtak node ka right null ya curr k equal nahi aa jaata right pe jaatey jaao
		while (node.right != null && node.right != curr) { 
			node = node.right;
		}

		// Jis din node ka right null ya curr k equal aa gaya wahi tumhara rightMostNode h
		return node;
	}

	
	// Answer kab bnao? Jab left null ho + thread destroy krte waqt 
	public static ArrayList<Integer> morrisInOrderTraversal(TreeNode root) {
		ArrayList<Integer> ans = new ArrayList<>();
		TreeNode curr = root;
		while (curr != null) {
			TreeNode left = curr.left;
			if (left == null) { // Inorder me jab left null ho jata h
				ans.add(curr.val); // Tab usko print karte h
				curr = curr.right; // And right pe move kar jaatey h
			} else {
				TreeNode rightMostNode = getRightMostNode(left, curr);

				// Thread creation //Thread bna diya left pe chale jaao
				if (rightMostNode.right == null) { // Agar null h, mtlb thread nahi bna aaj tak, toh thread banao
					rightMostNode.right = curr; // Thread create ho gaya
					curr = curr.left; // And now move to left
				}

				// Thread destroy //Thread kaat diya right pe chale jaao
				else { // rightMostNode.right == curr // thread bna hua h, toh usko kato to maintain original tree
					rightMostNode.right = null; // Thread cut down ho gaya
					ans.add(curr.val); // Inorder wala root print karo
					curr = curr.right; // And now move to right
				}
			}
		}
		return ans;
	}

	
	/*
	 NOTE:- Difference between morrisInOrderTraversal and morrisPreOrderTraversal ?
	  
		Jab left null ho tab answer banao 
					   +
		morrisInOrderTraversal : Thread destroy krte waqt answer bnao 
		
		---------------------------------------------------------------------
		
		Jab left null ho tab answer banao 
					   +
		morrisPreOrderTraversal : Thread create krte waqt answer bnao
	 
	 
	 
	 # ALGO morrisPreOrderTraversal:-
	  
	   Left pe kab jana h: 
	  		a. when we create a thread
	  
	   Right pe kab jana h: 
      			a. left is null 
	 	 	b. thread is cut down [Root and left subtree is processed]
	  
	   Print kab karna h: 
	  		a. left is null 
	  		b. thread is created  
	
	*/
	
	public static ArrayList<Integer> morrisPreOrderTraversal(TreeNode root) {
		ArrayList<Integer> ans = new ArrayList<>();
		TreeNode curr = root;
		while (curr != null) {
			TreeNode left = curr.left;
			if (left == null) {
				ans.add(curr.val);
				curr = curr.right;
			} else {
				TreeNode rightMostNode = getRightMostNode(left, curr);

				// thread create
				if (rightMostNode.right == null) {
					rightMostNode.right = curr;
					ans.add(curr.val); // Thread create krte waqt answer bnao  			// ONLY DIFFERENCE
					curr = curr.left;
				}

				// thread destroy
				else {
					rightMostNode.right = null;
					curr = curr.right;
				}
			}
		}
		return ans;
	}
	// ================================================================================================================================================

	
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(7);
		root.left = new TreeNode(3);
		root.right = new TreeNode(12);

		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(6);
		root.right.left = new TreeNode(9);
		root.right.right = new TreeNode(13);

		root.left.left.left = new TreeNode(0);
		root.left.left.right = new TreeNode(2);

		root.left.right.left = new TreeNode(4);
		root.left.right.left.right = new TreeNode(5);
		
		/*
		 
							  7
							  
						  /	         \
						  
				               3	           12
						   	
					   /       \	   	 /    \
						   
				        1            6 	       9        13
						 
				     /     \	    /
						  
				   0        2     4
				   	
				   	            \
				   	 	  
				   	              5
							
					
		*/
		
		System.out.println(morrisInOrderTraversal(root));
		//[0, 1, 2, 3, 4, 5, 6, 7, 9, 12, 13]
		System.out.println(morrisPreOrderTraversal(root));
		//[7, 3, 1, 0, 2, 6, 4, 5, 12, 9, 13]

	}

}
