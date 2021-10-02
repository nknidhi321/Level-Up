package recursion;

public class Permutation {

	public static int permutationDuplicates(String str, String ans) {
		if (str.length() == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			String ros = str.substring(0, i) + str.substring(i + 1);
			count += permutationDuplicates(ros, ans + ch);
		}
		return count;
	}

	public static int permutationUnique1(String str, String ans) {
		if (str.length() == 0) {
			System.out.println(ans);
			return 1;
		}

		boolean[] vis = new boolean[26];
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!vis[ch - 'a']) {
				vis[ch - 'a'] = true;
				String ros = str.substring(0, i) + str.substring(i + 1);
				count += permutationUnique1(ros, ans + ch);
			}
		}
		return count;
	}

	public static int permutationUnique2(String str, String ans) {
		if (str.length() == 0) {
			System.out.println(ans);
			return 1;
		}

		char prev = '$';
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (prev != ch) {
				String ros = str.substring(0, i) + str.substring(i + 1);
				count += permutationUnique2(ros, ans + ch);
			}
			prev = ch;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println("Count : " + permutationDuplicates("aabb", ""));
		System.out.println();
		System.out.println("Count : " + permutationUnique1("aabb", ""));
		System.out.println();
		System.out.println("Count : " + permutationUnique2("aabb", ""));
	}
}
