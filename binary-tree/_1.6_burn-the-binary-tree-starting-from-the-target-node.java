//https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/

package tree;

import java.util.ArrayList;
import java.util.HashSet;

public class _1_FindSet {

	public static class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		TreeNode(int val) {
			this.val = val;
		}
	}
	
	
	// ===============================================================================================================================================
	public static int burningTree(TreeNode root, int fireNode, ArrayList<ArrayList<Integer>> ans) {
		if (root == null)
			return -1; // fireNode not found so return -1

		if (root.val == fireNode) { // fireNode found

			// blocker will be null
			// fireNode will be burnt in time 0
			burningTreeNode(root, 0, null, ans);

			// parent node from fireNode will be burnt in time 1, so return 1
			return 1;
		}

		// fireNode se mere node(root) ko include kar k burn krne me kitna time lga #
		int leftTime = burningTree(root.left, fireNode, ans);

		if (leftTime != -1) { // Agar leftTime -1 nahi hai, mtlb left me fireNode tha
			// #Jitna v time lga, wo time pass hoga
			// And blocker will be left node kuki jaha se fire lga k aa chuke h waha wapas
			// fire nahi lgana
			burningTreeNode(root, leftTime, root.left, ans);
			return leftTime + 1; // mai apne parent ko +1 time me burn karungi
		}

		// fireNode se mere node ko include kar k burn krne me kitna time lga *
		int rightTime = burningTree(root.right, fireNode, ans);

		if (rightTime != -1) { // Agar rightTime -1 nahi hai, mtlb right me fireNode tha
			// *Jitna v time lga, wo time pass hoga
			// And blocker will be right node kuki jaha se fire lga k aa chuke h waha wapas
			// fire nahi lgana
			burningTreeNode(root, rightTime, root.right, ans);
			return rightTime + 1; // mai apne parent ko +1 time me burn karungi
		}
		return -1;
	}

	// This is simple k nodes down
	public static void burningTreeNode(TreeNode root, int time, TreeNode blocker, ArrayList<ArrayList<Integer>> ans) {
		if (root == null || root == blocker)
			return;

		if (time == ans.size())
			ans.add(new ArrayList<>());
		ans.get(time).add(root.val);
		burningTreeNode(root.left, time + 1, blocker, ans);
		burningTreeNode(root.right, time + 1, blocker, ans);
	}
	// ===============================================================================================================================================

	
	
	// ================================================================================================================================================
	/*
	 Follow up question :-
	 Same as above burningTree + Kuch nodes( >= 1) pe paani hai. [Aur jin nodes pe paani hai, waha se ab radially kisi v nodes pe fire nahi lag sakti]
	 */

	public static int burningTreeWithWater(TreeNode root, int fireNode, HashSet<Integer> waterSet,
			ArrayList<ArrayList<Integer>> ans) {
		if (root == null)
			return -1; // FireNode not found

		// FireNode found
		if (root.val == fireNode) {
			if (!waterSet.contains(root.val)) { // FireNode pe paani nahi hai, toh chalo node ko burn karne
				burningTreeNodeWithWater(root, 0, null, waterSet, ans);
				return 1; // FireNode se parent ko burn karne me 1 unit time lgega
			}
			return -2; // FireNode pe paani hai, ek v node ko burn nahi kar paaega
		}

		int leftTime = burningTreeWithWater(root.left, fireNode, waterSet, ans);

		// Left subtree jal k aa chuka h
		if (leftTime > 0) {
			if (!waterSet.contains(root.val)) { // Agar mere node pe paani nahi hai, toh hum khud ko v jalane chale gaye
				burningTreeNodeWithWater(root, leftTime, root.left, waterSet, ans);
				return leftTime + 1; // Mai apne parent ko +1 time me burn karungi
			}
			return -2; // Mere node pe he paani hai, kisi ko aage nahi jala paaenge, so return directly
		}

		// Left subtree nahi jala kuki FireNode pe paani tha
		if (leftTime == -2)
			return -2;

		int rightTime = burningTreeWithWater(root.right, fireNode, waterSet, ans);

		// Right subtree jal k aa chuka h
		if (rightTime > 0) {
			if (!waterSet.contains(root.val)) { // Agar mere node pe paani nahi hai, toh hum khud ko v jalane chale gaye
				burningTreeNodeWithWater(root, rightTime, root.right, waterSet, ans);
				return rightTime + 1; // Mai apne parent ko +1 time me burn karungi
			}
			return -2; // Mere node pe he paani hai, kisi ko aage nahi jala paaenge, so return directly
		}

		// Right subtree nahi jala kuki FireNode pe paani tha
		if (rightTime == -2)
			return -2;

		return -1; // FireNode not found
	}

	// This is simple k nodes down
	public static void burningTreeNodeWithWater(TreeNode root, int time, TreeNode blocker, HashSet<Integer> waterSet,
			ArrayList<ArrayList<Integer>> ans) {
		if (root == null || root == blocker || waterSet.contains(root.val))
			return;

		if (time == ans.size())
			ans.add(new ArrayList<>());
		ans.get(time).add(root.val);
		burningTreeNodeWithWater(root.left, time + 1, blocker, waterSet, ans);
		burningTreeNodeWithWater(root.right, time + 1, blocker, waterSet, ans);
	}
	// ====================================================================================================================================================

	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(12);
		root.left = new TreeNode(13);
		root.right = new TreeNode(10);
		root.right.left = new TreeNode(14);
		root.right.right = new TreeNode(15);
		TreeNode left = root.right.left;
		TreeNode right = root.right.right;
		left.left = new TreeNode(21);
		left.right = new TreeNode(24);
		right.left = new TreeNode(22);
		right.right = new TreeNode(23);

		// burningTree
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		burningTree(root, 14, ans);
		for (ArrayList<Integer> subList : ans) {
			System.out.println(subList);
		}

		// burningTreeWithWater
		ArrayList<ArrayList<Integer>> anss = new ArrayList<>();
		HashSet<Integer> waterSet = new HashSet<>();
		waterSet.add(13);
		waterSet.add(22);
		waterSet.add(21);
		burningTreeWithWater(root, 14, waterSet, anss);
		for (ArrayList<Integer> subList : ans) {
			System.out.println(subList);
		}

	}

}
