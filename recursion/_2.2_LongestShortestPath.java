// No platform to submit

package recursion;

public class LongestShortestPath {

	public static class Pair {
		String psf = "";
		int len = 0;
		
		public Pair(String psf, int len) {
			this.psf = psf;
			this.len = len;
		}
	}
	
	public static Pair longestPath(int sr, int sc, int n, int m, int[][] arr, int[][] dir, char[] dirS) {
		if(sr == n - 1 && sc == m - 1)	return new Pair("", 0);
		
		arr[sr][sc] = 1; //Block
		
		//Pair ansPair = new Pair("", 0);  //Wrong because there's no separate marking for n - 1 and m - 1
		//Because both smallAns and ansPair values are same for both psf and len
		//Hence, to differentiate between smallAns and ansPair mark len of ansPair as (int)-1e9
		
		Pair ansPair = new Pair("", (int)-1e9);
		
		for(int d = 0; d < dir.length; d++) {
			int r = sr + dir[d][0];
			int c = sc + dir[d][1];
			if(r >= 0 && r < n && c >= 0 && c < m && arr[r][c] != 1) {
				Pair smallAnsPair = longestPath(r, c, n, m, arr, dir, dirS);
				
				//smallAnsPair.len != (int)-1e9 //This is important because if all the cells around n - 1 and m - 1 would be blocked,
				//still your code will run without even reaching to the last cell and give you wrong ans
				if(smallAnsPair.len != (int)-1e9 && smallAnsPair.len + 1 > ansPair.len) {
					ansPair.len = smallAnsPair.len + 1;
					ansPair.psf = smallAnsPair.psf + dirS[d];
				}
			}
		}
		arr[sr][sc] = 0; //Unblock
		return ansPair;
	}
	
	public static Pair shortestPath(int sr, int sc, int n, int m, int[][] arr, int[][] dir, char[] dirS) {
		if(sr == n - 1 && sc == m - 1)	return new Pair("", 0);
		
		arr[sr][sc] = 1; //Block
		
		//Pair ansPair = new Pair("", 0);  //Wrong because there's no separate marking for n - 1 and m - 1
		//Because both smallAns and ansPair values are same for both psf and len
		//Hence, to differentiate between smallAns and ansPair mark len of ansPair as (int)1e9
		
		Pair ansPair = new Pair("", (int)1e9);
		
		for(int d = 0; d < dir.length; d++) {
			int r = sr + dir[d][0];
			int c = sc + dir[d][1];
			if(r >= 0 && r < n && c >= 0 && c < m && arr[r][c] != 1) {
				Pair smallAnsPair = shortestPath(r, c, n, m, arr, dir, dirS);
				
				//smallAnsPair.len != (int)1e9 //This is important because if all the cells around n - 1 and m - 1 would be blocked,
				//still your code will run without even reaching to the last cell and give you wrong ans
				if(smallAnsPair.len != (int)1e9 && smallAnsPair.len + 1 < ansPair.len) {
					ansPair.len = smallAnsPair.len + 1;
					ansPair.psf = smallAnsPair.psf + dirS[d];
				}
			}
		}
		arr[sr][sc] = 0; //Unblock
		return ansPair;
	}
	
	public static void main(String[] args) {
		int[][] arr = new int[3][3];
		int n = arr.length, m = arr[0].length;
		char[] dirS = {'D', 'R', 'U', 'L'};
		int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		
		Pair longestPathAns = longestPath(0, 0, n, m, arr, dir, dirS); 
		System.out.println(longestPathAns.psf + "@" + longestPathAns.len);
		
		Pair shortestPathAns = shortestPath(0, 0, n, m, arr, dir, dirS); 
		System.out.println(shortestPathAns.psf + "@" + shortestPathAns.len);
	}

}
