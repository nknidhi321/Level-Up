//https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/in-order-morris-traversal-in-binarytree/ojquestion

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


 //===============================================================================================================================================================
	/*	   

	 # ALGO:-
	   
	 	Print kab karna h:
	 		a. left is null
	 		b. thread is used or cut down
	 		
	 	Left pe kab jana h:
	 		a. when we create a thread
	 	
	 	Right pe kab jana h: 
	 		a. left is null
	 		b. thread is cut down 

	
	# Space Complexity:- O(1)
	
	# Time Complexity:-
		Average number of times nodes are visited is 4 to 6 times. Therefore, TC: O(5N) 
		For right skewed tree each node is visited only ones. Therefore, TC: O(N) [Left null hai, right pe chale gaye]
		For left skewed tree each node is visited thrice. Therefore, TC: O(3N)
	 
	*/
	
	public static TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
		
		//Jabtak node ka right null ya curr k equal nahi aa jaata right pe jaatey jaao
		while(node.right != null && node.right != curr) {
			node = node.right;
		}
		
		//Jis din node ka right null ya curr k equal aa gaya wahi tumhara rightMostNode h
		return node;
	}
	
	public static ArrayList<Integer> morrisInOrderTraversal(TreeNode root){
		ArrayList<Integer> ans = new ArrayList<>();
		TreeNode curr = root;
		while(curr != null) {
			TreeNode left = curr.left;
			if(left == null) { //Inorder me jab left null ho jata h 
				ans.add(curr.val); //Tab usko print karte h
				curr = curr.right; //And right pe move kar jaatey h
			}
			else {
				TreeNode rightMostNode = getRightMostNode(left, curr);
				
				//Thread creation //Thread bna diya left pe chale jaao
				if(rightMostNode.right == null) { //Agar null h, mtlb thread nahi bna aaj tak, toh thread banao
					rightMostNode.right = curr; //Thread create ho gaya
					curr = curr.left; //And now move to left
				}
				
				//Thread destroy //Thread kaat diya right pe chale jaao
				else { //rightMostNode.right == curr //Agar null nahi h, mtlb curr k equal h, mtlb thread bna hua h, toh usko kato to maintain original tree
					rightMostNode.right = null; //Thread cut down ho gaya
					ans.add(curr.val); //Inorder wala root print karo
					curr = curr.right; // And now move to right
				}
			}
		}
		return ans;
	}
	//====================================================================================================================================================================
	

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

    ArrayList<Integer> ans = morrisInOrderTraversal(root);
    for (Integer i : ans)
      System.out.print(i + " ");

  }

  public static void main(String[] args) {
    solve();
  }
}
