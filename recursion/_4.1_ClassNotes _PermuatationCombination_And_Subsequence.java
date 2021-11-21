/*
 
Single Coin : Cannot pick the same coin again, so idx + 1
Infinite Coin : Can pick the same coin again, so idx

-> Permutation is arrangement so check every possibility starting from 0 to n - 1 from any idx th index.
-> Combination will never turn back to check for a possibility, it will always move forward to check for it's possibilities,
		from any idx th index to  n - 1 
	

NOTE:
=====
Special type question:-
permutation with single coin because of marking it as visited

*/

package recursion;

import java.util.ArrayList;

public class _4_PermuatationCombination_And_Subsequence {
	
	//Using for loops (Permutation Combination method) =============================================================================================================

	
	//Without idx
	public static int permutationInfiCoins(int[] arr, int tar, String ans) {
		if (tar == 0) {  //idx will never be = arr.length, so no need to check at base case *
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int ele : arr) {   // * because for loop bacha legi before making the recursive calls
			if (tar - ele >= 0)
				count += permutationInfiCoins(arr, tar - ele, ans + ele);
		}
		return count;
	}

	
	//With idx
	public static int permutationInfiCoins(int[] arr, int tar, int idx, String ans) {
		if (tar == 0) { //idx will never be = arr.length, so no need to check at base case *
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = 0; i < arr.length; i++) { // * because for loop bacha legi before making the recursive calls
			if (tar - arr[i] >= 0)
				count += permutationInfiCoins(arr, tar - arr[i], idx, ans + arr[i]);
		}
		return count;
	}
	
	
	public static int combinationInfiCoins(int[] arr, int tar, int idx, String ans) {
		if (tar == 0) { //idx will never be = arr.length, so no need to check at base case for idx *
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = idx; i < arr.length; i++) { // * because for loop bacha legi before making the recursive calls 
			if (tar - arr[i] >= 0)
				count += combinationInfiCoins(arr, tar - arr[i], i, ans + arr[i]);
		}
		return count;
	}

	
	public static int combinationSingleCoins(int[] arr, int tar, int idx, String ans) {

		//NOTE: If you do not make this idx == arr.length check then also, code would not be wrong because for idx == arr.length for loop will not run and 0 will be returned
		if (tar == 0 || idx == arr.length) {  //idx will be == arr.length, so check at base case, *
			if (tar == 0) {  
				System.out.println(ans);
				return 1;
			}
			return 0;
		}

		int count = 0;
		for (int i = idx; i < arr.length; i++) { // for loop bacha legi before making the recursive calls
			if (tar - arr[i] >= 0)
				count += combinationSingleCoins(arr, tar - arr[i], i + 1, ans + arr[i]);
		}
		return count;
	}

	
	//permutationSingleCoins is special because you mark it as visited,
	//to ignore the used coin for that specific path,
	//us used coin ko dobara call lgne se bachana h somehow, so mark it visited
	public static int permutationSingleCoins(int[] arr, int tar, String ans) {
		if (tar == 0) { //idx will never be = arr.length, so no need to check at base case for idx *
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = 0; i < arr.length; i++) { // * because for loop bacha legi before making the recursive calls
			int val = arr[i]; //Storing the original value, to mark it visited for further calls
			if (arr[i] > 0 && tar - arr[i] >= 0) { //arr[i] > 0 this condition is checking if it visited or not
				arr[i] = -val; //Marking visited, as negative value
				count += permutationSingleCoins(arr, tar - val, ans + val);
				arr[i] = val; //Marking unvisited, replacing the original value so that it can be part of another path
			}
		}

		return count;
	}

	
	//Using Pick / Not pick (Subsequence method) ======================================================================================================================

	public static int combinationSingleCoins_sub(int[] arr, int tar, int idx, String ans) {
		if (tar == 0 || idx == arr.length) {  //idx will be == arr.length, so check at base case, *
			if (tar == 0) {  
				System.out.println(ans);
				return 1;
			}
			return 0;
		}

		int count = 0;

		if (tar - arr[idx] >= 0) // * because there's no bachane wali for loop before making the recursive calls
			count += combinationSingleCoins_sub(arr, tar - arr[idx], idx + 1, ans + arr[idx]);
		count += combinationSingleCoins_sub(arr, tar, idx + 1, ans);

		return count;
	}

	public static int combinationInfiCoins_sub(int[] arr, int tar, int idx, String ans) {
		if (tar == 0 || idx == arr.length) { //idx will be == arr.length, so check at base case, *
			if (tar == 0) {
				System.out.println(ans);
				return 1;
			}
			return 0;
		}

		int count = 0;

		if (tar - arr[idx] >= 0) // * because there's no bachane wali for loop before making the recursive calls
			count += combinationInfiCoins_sub(arr, tar - arr[idx], idx, ans + arr[idx]);
		count += combinationInfiCoins_sub(arr, tar, idx + 1, ans);

		return count;
	}

	public static int PermutationInfiCoins_sub(int[] arr, int tar, int idx, String ans) {
		if (tar == 0 || idx == arr.length) {  //idx will be == arr.length, so check at base case, *
			if (tar == 0) {
				System.out.println(ans);
				return 1;
			}
			return 0;
		}

		int count = 0;

		if (tar - arr[idx] >= 0) // * because there's no bachane wali for loop before making the recursive calls
			count += PermutationInfiCoins_sub(arr, tar - arr[idx], 0, ans + arr[idx]);
		count += PermutationInfiCoins_sub(arr, tar, idx + 1, ans);

		return count;
	}

	
	//PermutationSingleCoins_sub is special because you mark it as visited,
	//to ignore the used coin for that specific path,
	//us used coin ko dobara call lgne se bachana h somehow, so mark it visited
	public static int PermutationSingleCoins_sub(int[] arr, int tar, int idx, String ans) {
		if (tar == 0 || idx == arr.length) { //idx will be == arr.length, so check at base case, *
			if (tar == 0) {
				System.out.println(ans);
				return 1;
			}
			return 0;
		}

		int count = 0;

		// * because there's no bachane wali for loop before making the recursive calls
		if (arr[idx] > 0 && tar - arr[idx] >= 0) { //arr[i] > 0 this condition is checking if it visited or not
			int val = arr[idx]; //Storing the original value, to mark it visited for further calls
			arr[idx] = -val;  //Marking visited, as negative value
			count += PermutationSingleCoins_sub(arr, tar - val, 0, ans + val);
			arr[idx] = val;  //Marking unvisited, replacing the original value so that it can be part of another path
		}
		count += PermutationSingleCoins_sub(arr, tar, idx + 1, ans);

		return count;
	}

	
	// ==========================================================================================================================

	public static void subseq(String str, int idx, ArrayList<String> ans) {

	}

	public static void combinationPermutation() {
		int[] arr = {1, 2, 3, 4};
		int tar = 6;
		String ans = "";

		 System.out.println("count : " + permutationInfiCoins(arr, tar, ans));
		 System.out.println("count : " + permutationInfiCoins(arr, tar, 0, ans));
		// System.out.println(combinationInfiCoins(arr, tar, 0, ans));
		// System.out.println(combinationSingleCoins(arr, tar, 0, ans));
		// System.out.println(permutationSingleCoins(arr, tar, ans));
	}

	public static void combinationPermutation_Sub() {
		int[] arr = { 2, 3, 5, 7 };
		int tar = 10;
		String ans = "";

		// System.out.println(combinationSingleCoins_sub(arr, tar, 0, ans));
		// System.out.println(combinationInfiCoins_sub(arr, tar, 0, ans));
		// System.out.println(PermutationInfiCoins_sub(arr, tar, 0, ans));
		System.out.println(PermutationSingleCoins_sub(arr, tar, 0, ans));
	}

	public static void main(String[] args) {
		// combinationPermutation_Sub();
		combinationPermutation();
	}

}
