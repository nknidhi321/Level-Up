//https://pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/node-to-root-path-binary-tree/ojquestion

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
  
 //=================================================================================================================

  //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
  //$$$$$$$$$$$$$$$===============================$$$$$$$$$$$$$$$$$$$$$$$$$
  //$$$$$$$$$$$$$$$==========IMPORTANT============$$$$$$$$$$$$$$$$$$$$$$$$$ 
  //$$$$$$$$$$$$$$$===============================$$$$$$$$$$$$$$$$$$$$$$$$$
  //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	
  public static boolean nodeToRootPath_01(TreeNode root, int data, ArrayList<TreeNode> ans) {
    // write your code here
		if(root == null) return false;
		
		if(root.val == data) {
			ans.add(root);
			return true;
		}		
		if(nodeToRootPath_01(root.left, data, ans)) {
			ans.add(root);
			return true;
		}
		if(nodeToRootPath_01(root.right, data, ans)) {
			ans.add(root);
			return true;	
		}
		return false;
	}

  public static void nodeToRootPath_02(TreeNode root, int data, ArrayList<TreeNode> ans) {
	// write your code here
		if(root == null) return;
		
		if(root.val == data) {
			ans.add(root);
			return;
		}		
		nodeToRootPath_02(root.left, data, ans);
		if(ans.size() > 0) {
			ans.add(root);
			return;
		}
		nodeToRootPath_02(root.right, data, ans);
		if(ans.size() > 0) {
			ans.add(root);
			return;	
		}
	}
	
  public static ArrayList<TreeNode> nodeToRootPath_03(TreeNode root, int data) {
    // write your code here
    if(root == null) return new ArrayList<>();
		
	if(root.val == data) {
		ArrayList<TreeNode> ans = new ArrayList<>();
		ans.add(root);
		return ans;
	}		
	ArrayList<TreeNode> leftSmallAns = nodeToRootPath_03(root.left, data);
	if(leftSmallAns.size() > 0) {
		leftSmallAns.add(root);
		return leftSmallAns;
	}
	ArrayList<TreeNode> rightSmallAns = nodeToRootPath_03(root.right, data);
	if(rightSmallAns.size() > 0) {
		rightSmallAns.add(root);
		return rightSmallAns;	
	}
	return new ArrayList<>();
  }
  
  //===========================================================================================================

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

    int data = scn.nextInt();
    
    // ArrayList<TreeNode> ans = new ArrayList<>();
    // if(!nodeToRootPath_01(root, data, ans)) System.out.println();
    // for(TreeNode node : ans)
    // System.out.print(node.val + " ");
    
    // ArrayList<TreeNode> ans = new ArrayList<>();
    // nodeToRootPath_02(root, data, ans);
    // if (ans.size() == 0)  System.out.println();
    // for (TreeNode node : ans)
    //   System.out.print(node.val + " ");
      
    ArrayList<TreeNode> ans = nodeToRootPath_03(root, data);
    if (ans.size() == 0)  System.out.println();
    for (TreeNode node : ans)
      System.out.print(node.val + " ");
  }

  public static void main(String[] args) {
    solve();
  }
}
