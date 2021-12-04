/*
	Example baac :	0 -> b, 	1 -> a, 	2 -> a, 		3 -> c		 
	0 -> b 		:  	ros = aac
	1 -> a 		: 	ros = bac	// At 1 and 2, Both ros is same, so duplicate answers will be generated 
	2 -> a 		: 	ros = bac	// At 1 and 2, Both ros is same, so duplicate answers will be generated
	3 -> c		:	ros = baa

	So, to generate unique permutation, you have to somehow stop the call from 2nd idx, because both ros will generate same duplicate answers

	2 ways to  achieve this:-

		1) Make visited array of 26 size "locally" and mark it visited, as you keep encountering the characters, if !vis[] => call
		2) Sort the String before making the very first call, and keep a check of prev and curr character, if prev != curr => call
*/


package recursion;

public class Permutation {

	//Duplicates
	//Need not be sorted
	public static int permutationDuplicates(String str, String ans) {
		if (str.length() == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			String ros = str.substring(0, i) + str.substring(i + 1); // Make ith char as part of your answer and pass rest of the string 
			count += permutationDuplicates(ros, ans + ch);
		}
		return count;
	}

	
	//==========================================================================================================================================
	
	//Visited
	//Need not be sorted
	public static int permutationUnique1(String str, String ans) {
		if (str.length() == 0) {
			System.out.println(ans);
			return 1;
		}

		boolean[] vis = new boolean[26];  // At every level visited should be newly created
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!vis[ch - 'a']) { // If that ith char is not visited, make the call
				vis[ch - 'a'] = true;
				String ros = str.substring(0, i) + str.substring(i + 1);
				count += permutationUnique1(ros, ans + ch);
			}
		}
		return count;
	}

	
	//=============================================================================================================================================
	
	//Prev, curr
	//Your string must be sorted
	public static int permutationUnique2(String str, String ans) {
		if (str.length() == 0) {
			System.out.println(ans);
			return 1;
		}

		char prev = '$';
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (prev != ch) { // Compare prev and curr char, if not same make the call //make sure your string must be sorted to make this check
				String ros = str.substring(0, i) + str.substring(i + 1);
				count += permutationUnique2(ros, ans + ch);
			}
			prev = ch; //Make curr char as your prev char for the next iteration
		}
		return count;
	}
	
	//=================================================================================================================================================
	
	public static void main(String[] args) {
		
		System.out.println("Count : " + permutationDuplicates("bbcaa", ""));  // duplicate
		System.out.println();
		
		System.out.println("Count : " + permutationUnique1("bbcaa", ""));  // visited
		System.out.println();
		
		System.out.println("Count : " + permutationUnique2("aabbcc", ""));  // prev, curr
		System.out.println();
	}
}
