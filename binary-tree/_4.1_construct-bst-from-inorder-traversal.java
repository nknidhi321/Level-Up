// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/construct-bst-from-inorder-traversal/ojquestion

import java.util.Scanner;

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


    //==========================================================================
    public static TreeNode constructFromInOrder(int[] inOrder) {
        return constructFromInOrder(0, inOrder.length - 1, inOrder);
    }
    
    public static TreeNode constructFromInOrder(int si, int ei, int[] inOrder) {
        if(si > ei) return null;
        if (si == ei) new TreeNode(inOrder[si]);
        
        int mid = si + (ei - si) / 2;
        TreeNode root = new TreeNode(inOrder[mid]);
        root.left = constructFromInOrder(si, mid - 1, inOrder);
        root.right = constructFromInOrder(mid + 1, ei, inOrder);
        return root;
    }
    //==========================================================================


    public static void display(TreeNode node) {
        if (node == null)
            return;

        StringBuilder sb = new StringBuilder();
        sb.append((node.left != null ? node.left.val : "."));
        sb.append(" -> " + node.val + " <- ");
        sb.append((node.right != null ? node.right.val : "."));

        System.out.println(sb.toString());

        display(node.left);
        display(node.right);

    }

    public static void solve() {
        int n = scn.nextInt();
        int[] in = new int[n];
        for (int i = 0; i < n; i++)
            in[i] = scn.nextInt();

        TreeNode root = constructFromInOrder(in);
        display(root);
    }

    public static void main(String[] args) {
        solve();
    }
}
