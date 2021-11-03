//https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/count-all-single-child-parent-in-binary-tree/ojquestion

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

  //====================================================================================================================
 
  //PostOrder
  public static int countExactlyOneChild_01(TreeNode root) {
    if(root == null || (root.left == null && root.right == null)) return 0;
    
    if(root.left == null) return countExactlyOneChild_01(root.right) + 1;
    else if(root.right == null) return countExactlyOneChild_01(root.left) + 1;
    else return countExactlyOneChild_01(root.left) + countExactlyOneChild_01(root.right);
  }

  //=====================================================================================================================
 
  //PreOrder //Using array of size 1 in parameter
  //If you don't want to keep a static count variable then create an array for that variable(will work same as static) and pass in parameter
  public static void countExactlyOneChild_02(TreeNode root, int[] count) {
    if(root == null || (root.left == null && root.right == null)) return;
    
    if(root.left == null || root.right == null) {
        count[0]++;
        if(root.left == null) 
            countExactlyOneChild_02(root.right, count);  //No other calls will be done after this line, so return is not required
        else //if(root.right == null) 
            countExactlyOneChild_02(root.left, count);   //No other calls will be done after this line, so return is not required
    }
    else {
        countExactlyOneChild_02(root.left, count);
        countExactlyOneChild_02(root.right, count);
    }
  }

  //=====================================================================================================================
 
  //PreOrder //Using ArrayList in parameter
  public static void countExactlyOneChild_03(TreeNode root, ArrayList<Integer> list) {
    if(root == null || (root.left == null && root.right == null)) return;
    
    if(root.left == null || root.right == null) list.add(root.val);
    
    //For the nodes where left or right child does not exist, and still we are making the call,
    //that will be handled in base case when root == null
    countExactlyOneChild_03(root.left, list); 
    countExactlyOneChild_03(root.right, list);
  }
  
  //======================================================================================================================

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

    System.out.println(countExactlyOneChild_01(root));
 
    // int[] count = new int[1];
    // countExactlyOneChild_02(root, count);
    // System.out.println(count[0]);
 
    // ArrayList<Integer> list = new ArrayList<>();
    // countExactlyOneChild_03(root, list);
    // System.out.println(list.size());
  }

  public static void main(String[] args) {
    solve();
  }
}
